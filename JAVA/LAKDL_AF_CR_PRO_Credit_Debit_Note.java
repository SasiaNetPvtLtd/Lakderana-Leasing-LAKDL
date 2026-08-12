// DEVELOP BY : NUWAN DE SILVA

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Credit_Debit_Note extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs2;
	Statement stmt,stmt1;
	Connection conn;
	String reqstr;
	String m_chksql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
			String m_finance_no="";
			String m_close_status="";
			String my_screen_name="";
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_chksql=req.getParameter("chksql");
			
			
			/*if(req.getParameter("finance_no")!=null){
					m_finance_no = req.getParameter("finance_no");
					m_close_status  = req.getParameter("close_status");
					my_screen_name  = req.getParameter("my_screen_name");
					
				}*/
				
				
				
			if(m_chksql.equals("main_page")){		
					 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Invoice Adjustments </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			
		out.println("function Fill_Finance_no(){");
		
		out.println("if('"+m_finance_no+"'!=''){");
		//out.println("alert('"+m_finance_no+"');");
		out.println("    document.Form1.HID_CLOSE_STS.value='"+m_close_status+"';");
		out.println("    document.Form1.Hid_my_scr_name.value='"+my_screen_name+"';");
		
		
		out.println("document.Form1.TXT_FINANCE_NO.value='"+m_finance_no+"'");
		out.println("document.Form1.TXT_FINANCE_NO.disabled=true");
		out.println("document.Form1.BUT_HELP_FINANCE.disabled=true");
		out.println("assignState('M1');");
	  out.println("makeRequest(document.Form1.TXT_FINANCE_NO);");
		out.println("}");
		out.println(" }");
					
			out.println("function makeRequest(obj) {");
									
			out.println("if(document.Form1.hid_chk_status.value=='M1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_invoice_adjustments_finance_no&data_val=\"+obj.value+\"&ac_status=Y\";");
						
			out.println("else if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_invoice_adjustments_invoice_no&data_val2=\"+obj.value+\"&data_val=\"+document.Form1.TXT_FINANCE_NO.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_cr_dr_number_validations&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			
	    //out.println("window.open(m_url);")	;
			out.println("load_interface(m_url,'XML');");
			
		
			out.println("}");
			
			
			out.println("function get_vector(data_vec) {");
						
			out.println("			if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("     help_finance_no();}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_data_finance(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_INVOICE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2'){");
			out.println("     help_invoice_number();}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_INVOICE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     assign_data_invoice(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_CREDIT_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M3'){");
			out.println("     help_credit_no();}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_CREDIT_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M3' ){");
			out.println("     assign_data_credit_no(data_vec);");
			out.println("			}");
			
	   out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	if(document.Form1.TXT_CREDIT_NO.value==\"\" && document.Form1.SCREEN_NAME.value!=\"NEW\" ){  "); 
			out.println("		DIV_TXT_CREDIT_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			/*out.println("	else if(document.Form1.TXT_INVOICE_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			*/
			out.println("	else if(document.Form1.TXT_AMOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_AMOUNT.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			
			/*out.println("	else if((document.Form1.TXT_ADUSTED_DATE_DD.value==\"\" && document.Form1.TXT_ADUSTED_DATE_MM.value==\"\" && document.Form1.TXT_ADUSTED_DATE_YY.value==\"\") && document.Form1.SCREEN_NAME.value==\"NEW\" ){  "); 
			out.println("		DIV_TXT_ADUSTED_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			*/
			
			
						
			
			out.println("	else{"); 
			//out.println("		invoice_validation.innerHTML=\"Invoice Validation Successfull\";");
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			  
			
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){");
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_Save_Credit_Debit_Note';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("		else{");
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("		} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Debit_Note?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Debit_Note?chksql=main_page';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_INVOICE_ADJUSTMENTS\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Credit Process - Credit Debit Note- \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Credit Process - Credit Debit Note - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			
			out.println("		if(m_val==\"NEW\"){"); 
			
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("			new_window();"); 
			out.println("		}"); 
			
			out.println("		}"); 
			
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			
			out.println("else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_CREDIT.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_NO.disabled=false;"); 
						
			out.println("		document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			//out.println("		document.Form1.TXT_INVOICE_NO.disabled=true;"); 
			out.println("		document.Form1.BUT_HELP_FINANCE.disabled=true;"); 
			out.println("		document.Form1.BUT_HELP_INVOICE.disabled=true;"); 
					
			out.println("}"); 
			
			out.println("else if(m_val==\"DEL\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.BUT_HELP_CREDIT.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_NO.disabled=false;"); 
			
			out.println("		document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("		document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("		document.Form1.TXT_CR_DR_TYPE.disabled=true;"); 
			out.println("		document.Form1.TXT_NARRATIONS_CODE.disabled=true;"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("		document.Form1.TXT_REMARKS.disabled=true;"); 
			//out.println("		document.Form1.BUT_HELP_FINANCE.disabled=true;"); 
			//out.println("		document.Form1.BUT_HELP_INVOICE.disabled=true;"); 
			//out.println("		document.Form1.TXT_REMARKS.disabled=true;"); 
			//out.println("		document.Form1.TXT_DOC_REF_NO.disabled=true;"); 
			//out.println("		document.Form1.TXT_ADJUSTED_TYPE.disabled=true;"); 
			//out.println("		document.Form1.TXT_ADUSTED_DATE_DD.disabled=true;"); 
			//out.println("		document.Form1.TXT_ADUSTED_DATE_MM.disabled=true;"); 
			//out.println("		document.Form1.TXT_ADUSTED_DATE_YY.disabled=true;"); 
			
			out.println("}"); 
			out.println("}"); 
						
			
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DEL\"){");  
			out.println("			document.Form1.hid_status.value=\"Delete\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			
			//out.println("	}"); 
			
			out.println("}"); 
			   
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DEL\"){");  
			out.println("		m_sav_msg=\"Delete\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
			out.println("		m_sav_msg=\"Reactivate\";");  
			out.println("	}");
			out.println("	else{");  
			out.println("		m_sav_msg=\"\";");  
			out.println("	}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("	this.valout   = new Array(10);"); 
			out.println("}"); 
			
						
		/*	
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
			out.println("	"); 
			
			//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
						
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			
			  out.println("if(oBj.valout[0]=='Next')  {");
				out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[0]=='Prev') {");
				out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");
				out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("if(IfCount=='99'){"); 
				out.println("		assign_credit_number(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		assign_finance_no(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		assign_invoice_number(oBj);"); 
				out.println("}");
			

			
						
									
			out.println("	}"); 
			out.println("	}"); 
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
			out.println("	}");
			
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); */
			
			//-----------------------------------------------------------------------------------

			//===================================================================================
			
				out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
	    out.println("if(IfCount=='99'){"); 
			out.println("		assign_credit_number(oBj);");  
			out.println("}");
			
			out.println("else if(IfCount=='100'){"); 
			out.println("		assign_finance_no(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='2'){"); 
			out.println("		assign_invoice_number(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='3'){"); 
			out.println("		assign_client_code(oBj);"); 
			out.println("}");

				
		
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
			out.println("	clear_data();");//Added To The Clear The Area Code
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
			
			//===================================================================================
			
			out.println("function clear_data() {");
			out.println("}");
			
			
			out.println("function help_credit_no() {"); 
			out.println("    Crit = document.Form1.TXT_CREDIT_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_cr_dr_number_help','99');"); 		
			out.println("    } ");
			
			out.println("function assign_credit_number(oBj) {");
			out.println("		document.Form1.TXT_CREDIT_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_AMOUNT.value=oBj.valout[6];"); 
			out.println("		format_number(document.Form1.TXT_AMOUNT,22);");
			out.println("		document.Form1.TXT_CR_DR_TYPE.value=oBj.valout[7];"); 
			out.println("		document.Form1.TXT_REMARKS.value=oBj.valout[8];"); 
			out.println("		document.Form1.TXT_NARRATIONS_CODE.value=oBj.valout[9];"); 
			out.println("}");
			
			
			out.println("function assign_data_credit_no(oBj) {");

			/*out.println("		document.Form1.TXT_CREDIT_NO.value=data_vec[0];"); 
			out.println("		document.Form1.TXT_FINANCE_NO.value=data_vec[1];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=data_vec[3];"); 
			out.println("		document.Form1.TXT_INVOICE_NO.value=data_vec[4];"); 
			out.println("		document.Form1.TXT_TOTAL_AMOUNT.value=data_vec[5];"); 
			out.println("		document.Form1.TXT_BAL_TO_BE_RECEVIED.value=data_vec[6];"); 
			out.println("		document.Form1.TXT_AMOUNT_ADJUST.value=data_vec[7];"); 
			out.println("		getDateValues(data_vec[8]);");
			out.println("		document.Form1.TXT_CREDIT_TYPE.value=data_vec[9];"); 
			out.println("		getDateValues_Adusted(data_vec[10]);");
			out.println("		document.Form1.TXT_REMARKS.value=data_vec[11];"); 
			out.println("		document.Form1.TXT_DOC_REF_NO.value=data_vec[12];"); 
			out.println("		document.Form1.TXT_ADJUSTED_TYPE.value=data_vec[13];");
			out.println("		document.Form1.TXT_NARRATIONS_CODE.value=data_vec[14];"); 
			*/
			
			out.println("		document.Form1.TXT_CREDIT_NO.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_FINANCE_NO.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_AMOUNT.value=data_vec[2];"); 
			out.println("		format_number(document.Form1.TXT_AMOUNT,22);");
			out.println("		document.Form1.TXT_CR_DR_TYPE.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_REMARKS.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_NARRATIONS_CODE.value=data_vec[2];"); 

			out.println("}");
			
			
			
			
			
			out.println("function help_finance_no() {"); 
								
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_CR_DR_NOTES_FINANCE_NO','100');"); 		
			out.println("    } ");
				
			out.println("function assign_finance_no(oBj) {"); 
			out.println("		document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
		
			out.println("}");
			
			
			
			out.println("function help_client_code() {"); 
								
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_NO_4','3');"); 		
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE_new','3');"); 
			out.println("    } ");
			
				
			out.println("function assign_client_code(oBj) {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
		
			out.println("}");
			
			
			out.println("function assign_data_finance(data_vec) {"); 
			out.println("		document.Form1.TXT_FINANCE_NO.value=data_vec[0];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=data_vec[2];"); 
			out.println("}");
		

			out.println("function help_invoice_number() {"); 
				
			out.println("    Crit =document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_INVOICE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_CR_DR_NOTES_INVOICE_NO','2');"); 		
			
			out.println("    } ");		
			
			
				out.println("function assign_invoice_number() {"); 
				
			out.println("		document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_BAL_TO_BE_RECEVIED.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_AMOUNT_ADJUST.value=oBj.valout[4];"); 
			out.println("		format_number(document.Form1.TXT_TOTAL_AMOUNT,25);");
			out.println("		format_number(document.Form1.TXT_BAL_TO_BE_RECEVIED,25);");
			out.println("		format_number(document.Form1.TXT_AMOUNT_ADJUST,25);");
			
			out.println("getDateValues(oBj.valout[5]);");
			
			out.println("}");
			
			
					
			out.println("function assign_data_invoice(data_vec) {"); 
			
			out.println("		document.Form1.TXT_INVOICE_NO.value=data_vec[0];"); 
			out.println("		document.Form1.TXT_TOTAL_AMOUNT.value=data_vec[1];"); 
			out.println("		document.Form1.TXT_BAL_TO_BE_RECEVIED.value=data_vec[2];"); 
			out.println("		document.Form1.TXT_AMOUNT_ADJUST.value=data_vec[2];"); 
				
			out.println("getDateValues(data_vec[3]);");

			out.println("}");

			
					
		//!-----------Function To Hold The Current Make Request -------------//
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
		//------------------------------------------------------------------------


			out.println("function getDateValues(dval){");
			out.println("document.Form1.TXT_INVOICE_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_INVOICE_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_INVOICE_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("function getDateValues_Adusted(dval){");
			
			out.println("if(dval!='-'){");
			out.println("document.Form1.TXT_ADUSTED_DATE_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_ADUSTED_DATE_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_ADUSTED_DATE_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("}");
			
			
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			//out.println("validate_number(obj);");
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function validate_number(obj){");
							
			out.println("if( parseFloat(unformat_noobject(obj.value))>parseFloat(unformat_noobject(document.Form1.TXT_BAL_TO_BE_RECEVIED.value)) ){");
			
			out.println("alert('Amount should be less than balace to be received');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("return false;");
			out.println("}"); 
			out.println("else"); 
			out.println("{"); 
			out.println("return true;");
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function close_screen() {");
			out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("window.opener.get_Application_numbers('FINANCE_NO','ASC');");			
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else { "); 
			out.println("		     close_window();"); 
			out.println("		}"); 
			out.println("}");
			
			
			//-(2007-03-22)----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("function view() {"); 
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("alert('Please enter a Finance no to view letter')");
			out.println("}");
			out.println("else if(document.Form1.TXT_INVOICE_NO.value==\"\"){");
			out.println("alert('Please enter a Invoice no to view letter')");
			out.println("}");

			out.println("else{");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Notes_Letter?finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&client=\"+document.Form1.TXT_CLIENT_CODE.value+\"&invoice_no=\"+document.Form1.TXT_INVOICE_NO.value+\"&print=TRUE&aouthname=TEST1&chksql=MAIN\";");
			out.println("window.open(m_url,'displayWindow3','left=150,top=90,width=680,height=800,toolbar=0,location=0,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");

			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_INVOICE_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_INVOICE_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_INVOICE_DATE_YY.value=v_yy;");
			out.println("  }");	
			 out.println(" else if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_ADUSTED_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_ADUSTED_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_ADUSTED_DATE_YY.value=v_yy;");
			out.println("  }");	
			//out.println(" if((document.Form1.TXT_END_DATE_DD.value !=\"\")&&(document.Form1.TXT_END_DATE_MM.value !=\"\")&&(document.Form1.TXT_END_DATE_YY.value !=\"\") && (document.Form1.TXT_START_DATE_DD.value !=\"\")&&(document.Form1.TXT_START_DATE_MM.value !=\"\")&&(document.Form1.TXT_START_DATE_YY.value !=\"\")){");
			//out.println("chk_validity()");
			//out.println("}");	
			out.println("}");				
			
			
			
			//=========added by nuwan de silva 19-12-07=============
			//===========validate the number=======================
			
			out.println("	function chk_comment_length(obj){ ");

			out.println(" var remarks_length=obj.value.toString().length;");
			
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			
			out.println("} ");
						
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
      out.println("} ");
			
			
			//================================================


			
			
			
			
			
			
			
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),Fill_Finance_no()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_CREDIT_DEBIT_NOTE\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_my_scr_name' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"HID_CLOSE_STS\" value=\"N\">");
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Credit Debit Note  </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'  onClick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'  onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");'  onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
			//out.println("<td width='10%' align='center'></td>");  
			//out.println("<td width='10%' align='center'></td>");
			//out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
			//-(2007-03-22)----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Letter\");'  onclick='view()' value=\"View Letter\"></td>"); 
			
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_CREDIT_NO'  class=div_input>Reference No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CREDIT_NO' maxlength='15' size='15' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_CREDIT_NO)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_CREDIT' value=\"...\" onClick=\"help_credit_no()\" disabled></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE' value=\"...\" onClick=\"help_finance_no()\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");

		
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code	</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' ></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_client_code()\"></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			//out.println("<td width='*%'></td>"); 
			//out.println("</tr>");
			//out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:250' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("</table>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_INVOICE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_INVOICE' value=\"Help\" onClick=\"help_invoice_number()\"></td>");
			//out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Details\" onClick=\"show_invoice_batch_details(document.Form1.TXT_INVOICE_BATCH_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			


			
			/*out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_AMOUNT'  class=div_input>Total Amount Rs.</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' maxlength='25' size='50' style='width:118;text-align:right;'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_BAL'  class=div_input>Balance To Be Received</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BAL_TO_BE_RECEVIED' maxlength='25' size='50' style='width:118;text-align:right;'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	

			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_INVOICE_AMOUNT_ADJUST'  class=div_input>Adjusted Amount Rs.</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AMOUNT_ADJUST' maxlength='25' size='50' style='width:118;text-align:right;' onBlur=\"check_number(document.Form1.TXT_AMOUNT_ADJUST,25)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			
						
			out.println("<tr >");
			out.println("<td width=\"25%\"><DIV id=\"DIV_TXT_INVOICE_BATCH_DATE\" class=div_input>Invoice Date </DIV></td>");
			out.println("<td width=\"30%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_DATE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_DATE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_DATE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_BATCH_DATE_DD,document.Form1.TXT_INVOICE_BATCH_DATE_MM,document.Form1.TXT_INVOICE_BATCH_DATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			
			out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Amount Rs. *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='25' size='50' style='width:118;text-align:right;' onBlur=\"check_number(this,22)\"  ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='25%' >Credit/Debit Type</td>"); 
			out.println("<td width='30%' ><select name='TXT_CR_DR_TYPE' class='txt_input' >");
			out.println("<option value=\"CR\"      SELECTED>Credit</option>");
			out.println("<option value=\"DR\" 			>Debit </option>");
			out.println("</select>");
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='25%' >Credit Debit Narrations</td>"); 

			out.println("<td width=\"30%\" ><select class=\"txt_input\" type=\"text\" name=TXT_NARRATIONS_CODE maxlength=15  >");  //style=\"width:145px\" 

																
			rs = stmt.executeQuery("SELECT NARRATIONS_CODE,initcap(NARRATIONS) "+
		 "FROM "+m_schema_name+".AF_CO_MAS_CR_DB_NARRATIONS "+
		 "WHERE ACTIVE_STATUS='Y' "+
		 "ORDER BY NARRATIONS_CODE ASC ");


		
			boolean more = rs.next();		
			while(more){
			out.println("<option value="+rs.getString(1)+" selected>"+rs.getString(2)+"</option>");
			more = rs.next();		
			}
			more=rs.next();
			
			out.println("</select>");
			out.println("</td> ");
			out.println("</tr>"); 


			
			
			//----------------------------------------------------------------------------------------------------------------------------------


			
			/*out.println("<tr >");
			out.println("<td width=\"25%\"><DIV id=\"DIV_TXT_ADUSTED_DATE\" class=div_input>Adjusted Date *</DIV></td>"); //modified by nuwan de silva 26-06-07
			out.println("<td width=\"30%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_ADUSTED_DATE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ADUSTED_DATE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_ADUSTED_DATE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_ADUSTED_DATE_DD,document.Form1.TXT_ADUSTED_DATE_MM,document.Form1.TXT_ADUSTED_DATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			*/
			
			
			/*out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_REMARKS'  class=div_input>Remarks</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REMARKS' maxlength='25' size='50' style='width:200;' onBlur=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			*/
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' border=0 class='table' >"); 
			
			out.println("<tr>"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_REMARKS'  class=div_input>Remarks</DIV></td>"); 
			out.println("<td width='*%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:350px; height:50px;\" maxlength='500' size='500'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
		
		  /*out.println("<tr >"); 
			out.println("<td width='25%' ><DIV id='DIV_TXT_DOC_REF_NO'  class=div_input>Document Reference Number</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DOC_REF_NO' maxlength='25' size='50'  onBlur=\"\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
							
			out.println("<tr >"); 
			out.println("<td width='25%' >Adjusted Type</td>"); 
			out.println("<td width='30%' ><select name='TXT_ADJUSTED_TYPE' class='txt_input'>");
			out.println("<option value=\"CR_NOTE\" SELECTED>Credit Note</option>");
			out.println("<option value=\"IN_TYPE\" 				 >Interest</option>");
			out.println("</select>");
			out.println("</tr>"); 
			*/
			
			
			out.println("</table>");
    				
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			
		}
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
