
//--
//SCREEN NAME:PAYMENT DETAILS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_display_payment_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Payment Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			out.println("var arr_asst=new Array();");	
			out.println("var m_row1;");	
			out.println("var m_row2;");
			out.println("var m_row3;");
			out.println("var header;");		
			out.println("var array_asst=new Array();");
			out.println("var array_docu=new Array();");
			out.println("var doc_row=0;");
			out.println("var ln=0;");
			out.println("var ln_row=0;");
			out.println("var len=0;");
			out.println("var len_doc=0;");
			out.println("var row_val=0;");
			
			
			out.println("var b_val=0;");
			
			out.println("var othr_docu=new Array();");
			out.println("var othr_docu_det=new Array();");
			out.println("var ln_row_othr=0;");
			
			out.println("var arr_fill=new Array();");
			out.println("var ast_fill=new Array();");
			
			
			
			
			
			
			
			
			
			
			
			
			
			//**
			/*
					out.println("var ln=0;");
			out.println("var arr_size=0;");
			
			out.println("var b_flag=0;");
			out.println("var b_chk_doc_state=0;");
			
			
			out.println("var line_doc=0;");
			out.println("var arr_size_doc=0;");
			out.println("var no_row=0;");
			out.println("var row_count=0;");
			out.println("var row_val=0;");
			out.println("var b_chk_state_val=0;");
			
			
			
			
			out.println("var array_invoice=new Array();");
			out.println("var array_gross=new Array();");
			out.println("var array_vat=new Array();");
			out.println("var array_net=new Array();");
			
			out.println("var array_invoice_data=new Array();");
			out.println("var array_inv_documents=new Array();");
			
			
			
			out.println("var array_doc=new Array();");*/
			
			
			
				
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_st.value!=\"A1\" && document.Form1.hid_st.value!=\"A2\" && document.Form1.hid_st.value!=\"A3\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
		
			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_st.value==\"A1\"){");
			out.println("asset_det(data_vec)");
		//	out.println("ast_fill=data_vec;");
			out.println("			}");

			out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_st.value==\"A2\"){");
			out.println("fill_fields(array_asst,data_vec);");
			
			
			//out.println("arr_fill=data_vec;");
		//	out.println("assig('A3')");
					//	out.println("display_other(othr_docu_det)");

			//out.println("makeRequest3()"); 

			out.println("			}");
		//	out.println("alert('A3')");
	//	out.println("			alert('oo'+data_vec)");
			out.println(" else if(data_vec.length>0  && document.Form1.hid_st.value==\"A3\"){");
			//out.println("alert('document.Form1.hid_st.value'+document.Form1.hid_st.value)");
		
		//out.println("display_other(data_vec);");
		
	//	out.println("fill_fields(ast_fill,arr_fill);");
			out.println("othr_docu_det=data_vec;");
			out.println("display_other(data_vec)");
			//out.println("			alert('o333'+othr_docu_det)");
			out.println("			}");

			
			out.println("}");
			
			
			out.println("function assig(val) {");//Use to identify on which text box focus is on.
			out.println("document.Form1.hid_st.value=val;");
			//out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
		//	out.println("document.Form1.hid_act.value='Y';");//To seperatly identify active status accordingly.
			//out.println("}");
			//out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			//out.println("document.Form1.hid_act.value='N';");
			//out.println("}");
			out.println("}");

			
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_payment_details&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
						
			out.println("function makeRequest1(obj) {");
			out.println("alert('tttt')");
			out.println("assig('A1')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Asset_details&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url)");
			out.println("}");
			

			out.println("function makeRequest2(val) {");
			out.println("assig('A2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Doc_details&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function makeRequest3() {");
			out.println("assig('A3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_othr_Doc_details&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
		//	out.println("window.open(m_url)");
			out.println("}");
			
			/*out.println("function app_name(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_payment_details&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
*/

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PURCHASE_ORDER_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VENDER_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDER_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_NET.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_NET.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_VAT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_VAT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_INQUARY_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INQUARY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_STATUS.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_STATUS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CO_APPLICANT.value==\"\"){  "); 
			out.println("DIV_TXT_CO_APPLICANT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_STAGE.value==\"\"){  "); 
			out.println("DIV_TXT_STAGE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_REMARK.value==\"\"){  "); 
			out.println("DIV_TXT_REMARK.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_STATUS.value==\"\"){  "); 
			out.println("DIV_TXT_STATUS.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_payment_details';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_payment_details';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_payment_details';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_payment_details\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Payment Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Payment Details - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_VENDER_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_NET.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_VAT.disabled=true;"); 
			out.println("document.Form1.TXT_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_INQUARY_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_APPLICATION_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_CO_APPLICANT.disabled=true;"); 
			out.println("document.Form1.TXT_STAGE.disabled=true;"); 
			out.println("document.Form1.TXT_REMARK.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
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
/*
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("		help_value_assign_8();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("		help_value_assign_9();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_value_assign_10();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"11\"){"); 
			out.println("		help_value_assign_11();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"12\"){"); 
			out.println("		help_value_assign_12();"); 
	  	out.println("		}"); 
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
			out.println("	}	"); 
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
			
				
			*/
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
		
		//	out.println("	window.open('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');"); 
			out.println("	"); 

		
			//out.println("alert('aaa--'+oBj.valout[2]+'--'+oBj.valout[3]+'--sfsfsfsf'+oBj.valout[0]);");
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("else if(oBj.valout[4] != \" \"){ ");
			out.println("Crit = oBj.valout[4];");
			out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
			out.println("}	");
			out.println("}");	
			
			
			out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
			out.println("}");		
				
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
				
			out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
			
		
					
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1(oBj) {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_VENDER_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_VENDER_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2(oBj) {"); 
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_button_3(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_APP_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("makeRequest1(document.Form1.TXT_APPLICATION_NO)");
			out.println("    document.Form1.TXT_APP_NAME.value=oBj.valout[5];"); 
			
			
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[2];"); 
			out.println("makeRequest1(document.Form1.TXT_APPLICATION_NO)"); 
			out.println("}"); 

			out.println("function help_button_9() {"); 
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    m_sql = \"m_help_TXT_CO_APPLICANT_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CO_APPLICANT.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9(oBj) {"); 
			out.println("    document.Form1.TXT_CO_APPLICANT.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_10() {"); 
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_STAGE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_STAGE.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_10(oBj) {"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_11() {"); 
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    m_sql = \"m_help_TXT_REMARK_sql\";"); 
			out.println("    Crit = document.Form1.TXT_REMARK.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_11(oBj) {"); 
			out.println("    document.Form1.TXT_REMARK.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_12() {"); 
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    m_sql = \"m_help_TXT_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_12(oBj) {"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[2];"); 
			out.println("}");
			
			/*
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_VENDER_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VENDER_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_INQUARY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUARY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_STATUS_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8() {"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_9() {"); 
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    m_sql = \"m_help_TXT_CO_APPLICANT_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CO_APPLICANT.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9() {"); 
			out.println("    document.Form1.TXT_CO_APPLICANT.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_10() {"); 
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_STAGE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_STAGE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_10() {"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_11() {"); 
			out.println("    document.Form1.hid_help_type.value=\"11\";"); 
			out.println("    m_sql = \"m_help_TXT_REMARK_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_REMARK.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_11() {"); 
			out.println("    document.Form1.TXT_REMARK.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_12() {"); 
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    m_sql = \"m_help_TXT_STATUS_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_12() {"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[2];"); 
			out.println("}");*/
			

			/*out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			*/
				
			
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    m_sql = \"m_help_TXT_QUOTATION_NO_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 

			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("     Crit = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("     Crit = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 

			
			
			
			
			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];");
				out.println("makeRequest1(document.Form1.TXT_APPLICATION_NO)"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[4];"); 
			
		
			out.println("    document.Form1.TXT_TOTAL_NET.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_TOTAL_VAT.value=oBj.valout[6];");
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[8];"); 
			
			
			out.println("    document.Form1.TXT_APP_NAME.value=oBj.valout[10];");
			out.println("    document.Form1.TXT_TOTAL.value=oBj.valout[11];");
			//out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[10];"); 
		//	out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[9];"); 
		//	out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[10];"); 
			
			
		//	out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[12];"); 
		///	out.println("    document.Form1.TXT_CO_APPLICANT.value=oBj.valout[13];"); 
		//	out.println("    document.Form1.TXT_STAGE.value=oBj.valout[14];"); 
		///	out.println("    document.Form1.TXT_REMARK.value=oBj.valout[15];"); 
		//	out.println("    document.Form1.TXT_STATUS.value=oBj.valout[16];"); 
		
	
			out.println("}"); 

			
			/*out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_VENDER_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TOTAL_NET.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TOTAL_VAT.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_INQUARY_NO.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_CO_APPLICANT.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_STAGE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_REMARK.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[16];"); 

			out.println("}"); 
			
			*/
			
			
			
			
			
			
			
//###############################################################################################################################################################################
//###############################################################################################################################################################################
			out.println("function display_fields(data_vec){");//To fill data for assets.
			out.println("alert('EEEdata_vec'+data_vec)");
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			//out.println("if(document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.TXT_ITEM_CODE.value!=''){");
			
			
			out.println("change1.innerHTML='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" class=\"pdn_txtpos1\" style=\"{font:bold;}\"><u>Asset Id</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table >';"); 

			out.println("if(data_vec.length==0){");
			//out.println("alert('g')");
			//out.println("header()");
    
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			//out.println("'<td width=\"30%\" style=\"{font:bold;}\">Asset Id</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td><input type=hidden name=TXT_ASSET_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
//out.println("makeRequest2()");
		  out.println("j=j+1;");	
			//	out.println("ln=ln+1;");
			 //-- out.println("arr_size=arr_size+1;");
		    
				//--out.println("get_doc();");		
				
			
			out.println("}");
			
			out.println("else{");
			
			out.println("while(i<data_vec.length){");
			//out.println("alert('g99')");
				
			out.println("change1.innerHTML+='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
		//	out.println("'<td width=\"30%\" style=\"{font:bold;}\">Asset Id</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+");
			out.println("'<tr>'+"); 
			out.println("'<tr>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<td width=\"30%\" ><b>'+data_vec[i]+'<input type=\"hidden\" name=TXT_ASSET_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' value='+data_vec[i+1]+' maxlength=\"20\" value=\"\" maxlength=\"20\" size=\"20\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' value='+data_vec[i+2]+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' value='+data_vec[i+3]+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");

			
			//out.println("var st='TXT_STATUS_'+j;");
			//out.println("var rm='TXT_REMARK_'+j;");
   		//out.println("if (document.Form1.elements[st].value=='xx'){");
			//out.println("document.Form1.elements[st].value='';");
			//out.println("}");
				
			//out.println("if (document.Form1.elements[rm].value=='xx'){");
			//out.println("document.Form1.elements[rm].value='';");
			//out.println("}");
		//	out.println("makeRequest2()");
					//--	out.println("display_documents2(data_vec);");
//---out.println("get_doc();");
					//	out.println("display_documents2(array_inv_documents,j);");

			out.println("j=j+1;");
			
			out.println("i=i+1;");
			
			out.println("}"); 
			out.println("}");
 			
			out.println("document.Form1.hid_val.value=j-1");
			//out.println("change1.innerHTML=m_row1;");
      out.println("}"); 
			
			
			
			//*******************************************************************************************
			
			out.println("function display_asset_det(data_vec,val){");//To fill data for assets.
			//out.println("alert('display_asset_det'+data_vec)");
			//out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			//out.println("if(document.Form1.TXT_VALUATION_NO.value!='' && document.Form1.TXT_ITEM_CODE.value!=''){");
				

		
		
		
			out.println("if(data_vec.length==0){");
			//out.println("alert('g')");
			//out.println("header()");
				out.println("header='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 
    
			out.println("m_row2='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			
			out.println("'<tr>'+"); 
			out.println("'<td><input type=hidden name=TXT_DOC_CODE_'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' maxlength=\"20\" value=\"\" size=\"20\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
		  out.println("j=j+1;");	
			
			out.println("}");
			
			out.println("else{");
			
			out.println("while(i<data_vec.length){");
			//out.println("alert('g99')");
					out.println("header='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 
			out.println("m_row2='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			//out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			//out.println("'<td width=\"30%\" >Product Description *</td>'+"); 
			//out.println("'<td width=\"30%\" >Status *</td>'+"); 
			//out.println("'<td width=\"30%\" >Remarks *</td>'+"); 
			out.println("'</tr >'+");
			out.println("'<tr>'+"); 
			out.println("'<tr>'+"); 
			out.println("'</tr>'+"); 
			out.println("'<td width=\"30%\" ><b>'+data_vec[i]+'<input type=\"hidden\" name=TXT_DOC_CODE_'+j+' value='+data_vec[i]+' maxlength=\"10\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"30%\" ><input class=\"but_input\" type=\"button\" name=BUT_FILED_CODE_'+j+' value=\"Help\" onClick=\"help_button_6('+j+')\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input name=TXT_FILED_DESC_'+j+' value='+data_vec[i+1]+' maxlength=\"20\" value=\"\" maxlength=\"20\" size=\"20\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_STATUS_'+j+' value='+data_vec[i+2]+' maxlength=\"10\" value=\"\" size=\"10\"></td>'+"); 
			//out.println("'<td width=\"40%\" ><input class=\"text_input\" type=\"text\" name=TXT_REMARK_'+j+' value='+data_vec[i+3]+' maxlength=\"100\" value=\"\" size=\"100\"></td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");

			
			//out.println("var st='TXT_STATUS_'+j;");
			//out.println("var rm='TXT_REMARK_'+j;");
   		//out.println("if (document.Form1.elements[st].value=='xx'){");
			//out.println("document.Form1.elements[st].value='';");
			//out.println("}");
				
			//out.println("if (document.Form1.elements[rm].value=='xx'){");
			//out.println("document.Form1.elements[rm].value='';");
			//out.println("}");
			
			out.println("m_row3='<TR>'+header+m_row2+'</TR>';");
     	out.println("array_doc[j]=m_row3;");

			//	out.println("array_net=m_row1+m_row2;");
			out.println("j=j+1;");
			out.println("i=i+3;");
			//out.println("alert('m_row1'+m_row1)");
			out.println("}"); 
			out.println("}"); 
			out.println("document.Form1.hid_val.value=j-1");	
		//	out.println("}");
		//	out.println("else {");
			//	out.println("change1.innerHTML=m_row1+m_row2;");
				
						out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    			out.println("array_doc.join(\" \")+'</table>';");

		//	out.println("}");
				//out.println("makeRequest2()");
      out.println("}"); 




			out.println("function asset_det(data_vec){");
			out.println("array_asst=data_vec;");
			out.println("b_val=0;");
			out.println("row_val=0;");
			out.println("doc_row=0;");
			out.println("doc_det();");
			out.println("}");
			
			
			out.println("function doc_det(){");
			out.println("assig('A2');"); 
			out.println("while(doc_row<array_asst.length){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Doc_details&ac_status=Y\";");
		  out.println("load_interface(m_url,'XML');");
		  out.println("doc_row=doc_row+1;");
			out.println("}");
			out.println("}");

			out.println("function fill_fields(asst_vec,doc_vec){");
			out.println("if(b_val==0){");
		 	out.println("change1.innerHTML=\"\"");
		  out.println("header();");
			out.println("}");
				
		 	//out.println("change1.innerHTML=\"\"");
			//out.println("header_asst()");
			out.println("if(doc_vec.length==0){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><tr>'+");									
			out.println("'<td width=\"25%\" style=\"{font:bold;}\">'+asst_vec[row_val]+'<input type=hidden class=\"txt_input\" name=TXT_ASSET_CODE_'+ln+' maxlength=\"50\" size=\"50\" value='+asst_vec[row_val]+'>'+");
			out.println("'</td></tr></table>';");
			out.println("ln=ln+1;");
		//	out.println("len=len+1;");
			out.println("doc_det();");		
		  out.println("}");
				
	   	out.println("else");

			out.println("if(doc_vec.length>0){");
			//out.println("while(ln<asst_vec.length){");//--
			
		
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><tr>'+");									
			out.println("'<td width=\"25%\" style=\"{font:bold;}\">'+asst_vec[row_val]+'<input class=\"txt_input\" type=\"hidden\" name=TXT_ASSET_CODE_'+ln+' maxlength=\"50\" size=\"50\" value='+asst_vec[row_val]+'>'+");
			out.println("'</td></tr></table>';");
			
			out.println("fill_docs(doc_vec);");
			out.println("doc_det();");
			out.println("}");//==
		
				
			out.println("row_val=row_val+1;");
			
		 	out.println("ln=ln+1;");
			out.println("len=len+1;");
				
						//-----
			
			//out.println("makeRequest3()"); 
			
						//------
			
		//	out.println("makeRequest3()"); 
			//out.println("m_desc='<td width=\"30%\">'+doc_vec[i+1]+'</td>';");	


			//---

			//-----
			//out.println("alert('finis3333h')"); //--
			
			//out.println("assig('A3')");
			
			//out.println("display_other(othr_docu_det)");
			out.println("}"); 
			

			out.println("function fill_docs(doc_vec){");
			out.println("var i=0;");
			out.println("var ln_row=0;");
			//out.println("header_doc()");
			out.println("m_header='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Description *</td>'+"); 
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Status *</td>'+"); 
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 

			out.println("while(i<doc_vec.length){");
			
			out.println("m_code='<td width=\"30%\">'+doc_vec[i]+'</td>';");	
			out.println("m_desc='<td width=\"30%\">'+doc_vec[i+1]+'</td>';");	

			out.println("m_status='<td width=\"26%\"><input type=\"checkbox\" name=CHK_STATUS'+ln_row+' VALUE=\"\" onclick=\"change_val_req('+ln_row+')\"></td>';");			
			out.println("m_remark='<td width=\"*%\"><input type=\"text\" class=\"txt_input\" name=TXT_REMARK'+ln_row+' value=\"\" maxlength=\"20\" size=\"20\"></td>';");		


			out.println("m_hid_input='<input type=\"Hidden\" name=hid_TXT_DOC_CODE'+ln_row+'	value='+doc_vec[i]+'>'+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_DESCRIPTION'+ln_row+'	VALUE='+doc_vec[i+1]+'>';");

		  out.println("m_row3='<tr>'+m_code+m_desc+m_status+m_remark+'</tr>'+m_hid_input;");
     	out.println("array_docu[ln_row]=m_row3;");
    	out.println("i=i+3;");
			out.println("ln_row=ln_row+1;");//--
	
			out.println("}"); 
			out.println("change1.innerHTML+=m_header+'<table align=\"center\" border\"4\" width=\"100%\" class=\"table\">'+");
	    out.println("array_docu.join(\" \")+'</table>';");
			
			
	
			out.println("}");

		
			out.println("function header(){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr>'+");
			out.println("'<td width=\"20%\" class=\"pdn_txtpos1\" style=\"{font:bold;}\" align=\"left\"><u>Asset Id</b></td>'+");
			out.println("'</tr></table><br>';");
			out.println("b_val=1;");
     	out.println("}");
			
			
			out.println("function display_other(doc_vec_othr){");
			//out.println("alert('uu'+doc_vec_othr.length)");
			out.println("var i=0;");
			out.println("ln_row_othr=0;");	
			//out.println("if(document.Form1.TXT_APPLICATION_NO.value!=''){");
			//out.println("header_doc()");
			out.println("m_header='<table align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println("'<tr >'+");
			out.println("'<td class=\"pdn_txtpos1\" style=\"{font:bold;}\"><u>Other Documents</td>'+");
			out.println("'</tr >'+");
			out.println("'<tr >'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Document Code</td>'+");
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Description *</td>'+"); 
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Status *</td>'+"); 
			out.println("'<td width=\"30%\" style=\"{font:bold;}\">Remarks *</td>'+"); 
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 

			out.println("while(i<doc_vec_othr.length){");
			
			out.println("m_code='<td width=\"30%\">'+doc_vec_othr[i]+'</td>';");	
			out.println("m_desc='<td width=\"30%\">'+doc_vec_othr[i+1]+'</td>';");	

			out.println("m_status='<td width=\"26%\"><input type=\"checkbox\" name=CHK_ST'+ln_row_othr+' VALUE=\"\" onclick=\"change_val_req('+ln_row_othr+')\"></td>';");			
			out.println("m_remark='<td width=\"*%\"><input type=\"text\" class=\"txt_input\" name=TXT_REMARK'+ln_row_othr+' value=\"\" maxlength=\"20\" size=\"20\"></td>';");		


			out.println("m_hid_input1='<input type=\"Hidden\" name=hid_TXT_DOC'+ln_row_othr+'	value='+doc_vec_othr[i]+'>'+");
			out.println("'<input type=\"Hidden\" name=hid_TXT_DES'+ln_row_othr+'	VALUE='+doc_vec_othr[i+1]+'>';");

		  out.println("m_row4='<tr>'+m_code+m_desc+m_status+m_remark+'</tr>'+m_hid_input1;");
     	out.println("othr_docu[ln_row_othr]=m_row4;");
    //	out.println("i=i+3;");
		//	out.println("ln_row_othr=ln_row_othr+1;");//--
	
		//	out.println("}"); 
			out.println("change2.innerHTML+=m_header+'<table align=\"center\" border\"1\" width=\"100%\" class=\"table\">'+");
	    out.println("othr_docu.join(\" \")+'</table>';");
			
			 	out.println("i=i+3;");
			out.println("ln_row_othr=ln_row_othr+1;");//--
	
			out.println("}"); 

	//out.println("}");
			out.println("}");


//###############################################################################################################################################################################
//###############################################################################################################################################################################
	/*			out.println("function get_vector_values(data_vec){");
			
			out.println("array_invoice_data=data_vec;");
			out.println("b_chk_state_val=0;");
			out.println("row_val=0;");
			out.println("row_count=0;");
			
			out.println("get_doc();");
			out.println("}");
			
			
			
			
			out.println("function get_doc(){");
			out.println("assig('A2');"); 
		
					out.println("alert('A2'+array_invoice_data.length);"); 
			out.println("while(row_count<array_invoice_data.length){");
			
			 			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Doc_details&ac_status=Y\";");

		 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_doc&data_val=\"+array_invoice_data[row_count]+\"&ac_status=Y\";");
		
		  out.println("load_interface(m_url,'XML');");
		  out.println("row_count=row_count+3;");
		
		//		out.println("window.open(m_url);");
			
			 out.println("}");
			
	
			out.println("}");
			
				
			

					out.println("function display_data2(data_vec_inv,data_vec_doc){");
			out.println("alert('data_vec_inv'+data_vec_inv)");
		    out.println("if(b_chk_state_val==0){");
		 		out.println("change1.innerHTML=\"\"");
		  	//out.println("header();");
				out.println("}");
				
		  //out.println("j=0;");
			//out.println("i=0;");
			
			  out.println("if(data_vec_doc.length==0){");
			
			
			  out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\">'+data_vec_inv[row_val]+'<input type=hidden class=\"txt_input\" name=TXT_ASSET_CODE'+ln+' maxlength=\"50\" size=\"50\" value='+data_vec_inv[row_val]+' onblur=\"validate_invoice_number('+ln+')\">'+");
				//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+ln+' value=\"Help\" onClick=\"help_button_3('+ln+')\"></TD>'+");
			 //	out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+ln+' maxlength=\"10\" value='+data_vec_inv[row_val+1]+' size=\"10\"></TD>'+");
				//out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+ln+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+' size=\"10\"></TD>'+");
				//out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+ln+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+3]+' >'+");
				//out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+ln+' value=\" * \" onClick=\"del_row('+ln+')\">'+");
				out.println("'</td></tr></table>';");
				out.println("ln=ln+1;");
			  out.println("arr_size=arr_size+1;");
		    
				out.println("get_doc();");		
				
		    out.println("}");
				
		 		
				
		    out.println("else");
			  out.println("if(data_vec_doc.length>0){");
				out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"25%\">'+data_vec_inv[row_val]+'<input class=\"txt_input\" type=\"hidden\" name=TXT_ASSET_CODE'+ln+' maxlength=\"50\" size=\"50\" value='+data_vec_inv[row_val]+' onblur=\"validate_invoice_number('+ln+')\">'+");
			//	out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_INVOICE_NO_HELP'+ln+' value=\"Help\" onClick=\"help_button_3('+ln+')\"></TD>'+");
			// 	out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_GROSS'+ln+' maxlength=\"10\" value='+data_vec_inv[row_val+1]+' size=\"10\"></TD>'+");
		//		out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_VAT'+ln+' maxlength=\"50\" value='+data_vec_inv[row_val+2]+' size=\"10\"></TD>'+");
		//		out.println("'<TD WIDTH=\"25%\"><input class=\"txt_input\" type=\"text\" name=TXT_NET'+ln+' maxlength=\"2\" size=\"10\" value='+data_vec_inv[row_val+3]+' >'+");
			//	out.println("'<input class=\"but_input\" type=\"button\" name=BUT_DEL'+ln+' value=\" * \" onClick=\"del_row('+ln+')\">'+");
				out.println("'</td></tr></table>';");

				out.println("display_documents2(data_vec_doc);");
				
				out.println("get_doc();}");
					
   					
		//	out.println("j=j+1;");
			  out.println("row_val=row_val+2;");
		  
			
	//		out.println("}"); //End of for loop;
			
//			out.println("}");




			out.println("ln=ln+1;");
			out.println("arr_size=arr_size+1;");
			
	//		out.println("cal_values();");
//			out.println("disable_fields(arr_size)");
			
			
      out.println("}"); 
			
			


	out.println("function display_documents2(data_vec_doc){");
			
			out.println("alert('eeeeeeeeeedata_vec_doc'+data_vec_doc);");
		//	out.println("alert('display_vec_doc lenth'+data_vec_doc.length);");
					
		 // out.println("var j=1;");
			out.println("var i=0;");
			
					//	out.println("header_doc();	");		
			     
					 out.println("while(i<data_vec_doc.length){");
			
			//    out.println("alert('i value'+i);");									
			
						out.println("m_code='<TD WIDTH=\"20%\">'+data_vec_doc[i]+'</TD>';");		
		  			out.println("m_description='<TD WIDTH=\"20%\">'+data_vec_doc[i+1]+'</TD>';");
						out.println("m_remark='<TD WIDTH=\"20%\"><INPUT TYPE=\"TEXT\" class=\"txt_input\" NAME=TXT_REMARK'+line_doc+' VALUE=\"\" maxlength=\"20\" size=\"20\"></td>';");		
						out.println("m_required='<TD WIDTH=\"20%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED'+line_doc+' VALUE=\"\" onclick=\"change_val_req('+line_doc+')\"></td>';");			
						out.println("m_not_applicable='<TD WIDTH=\"20%\"><INPUT TYPE=\"checkbox\" NAME=CHK_NOT_APPLICABLE'+line_doc+' VALUE=\"\" onclick=\"change_val_not_app('+line_doc+')\"></td>';");			
			
						out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DOC_CODE'+line_doc+'	VALUE='+data_vec_doc[i]+'>'+");
						out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DESCRIPTION'+line_doc+'	VALUE='+data_vec_doc[i+1]+'>';");
			 
	    
	  			  out.println("m_writedata='<TR>'+m_code+m_description+m_remark+m_required+m_not_applicable+'</TR>'+m_hid_input;");
     				out.println("array_doc[line_doc]=m_writedata;");
    
		  			//out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    			//out.println("array_doc.join(\" \")+'</table>';");
      
					//	out.println("j=j+1;");
						out.println("i=i+3;");
						out.println("line_doc=line_doc+1;");
						out.println("arr_size_doc=arr_size_doc+1;");		
						out.println("}"); //End while loop
						
						out.println("change1.innerHTML='<table align=\"center\" border\"4\" width=\"100%\" class=\"table\">'+");
	    			out.println("array_doc.join(\" \")+'</table>';");
		
			
			     // out.println("line_doc=line_doc+1;");
			    //  out.println("arr_size_doc=arr_size_doc+1;");
			
			
			out.println("}");

			

*/
//------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),makeRequest3()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Payment Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_PURCHASE_ORDER_NO'  class=div_input>Purchase Order No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onblur=\"makeRequest(document.Form1.TXT_PURCHASE_ORDER_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('0','10','7','m_help_TXT_PURCHASE_ORDER_NO_sql','99')\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='20'>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_6('0','10','0','m_help_TXT_FINANCE_NO_sql','6')\"></td>"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			//onblur=\"makeRequest1(document.Form1.TXT_APPLICATION_NO)\"
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"makeRequest1(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_3('0','10','0','m_help_TXT_APP_NO_sql','3')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>Application Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APP_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VENDER_CODE'  class=div_input>Supplier No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VENDER_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_VENDER_CODE' value=\"Help\" onClick=\"help_button_2('0','10','0','m_help_TXT_VENDER_CODE_sql','2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>Supplier Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_NET'  class=div_input>Net Amount</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{ text-align=\"right\";}\" type='text' name='TXT_TOTAL_NET' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_VAT'  class=div_input>Vat Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{ text-align=\"right\";}\" type='text' name='TXT_TOTAL_VAT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' style=\"{ text-align=\"right\";}\" type='text' name='TXT_TOTAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VENDER_CODE'  class=div_input>VENDER_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VENDER_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_VENDER_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_NET'  class=div_input>TOTAL_NET *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_NET' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_VAT'  class=div_input>TOTAL_VAT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_VAT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>NAME *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME' maxlength='20' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>APPLICATION_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			
			/*--
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>CLIENT_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>CLIENT_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='22' size='22'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NO' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_INQUARY_NO'  class=div_input>INQUARY_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INQUARY_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_INQUARY_NO' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			--*/
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>FINANCE_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='20'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); */
			
			/*--
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_STATUS'  class=div_input>APPLICATION_STATUS *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_STATUS' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_STATUS' value=\"Help\" onClick=\"help_button_7()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CO_APPLICANT'  class=div_input>CO_APPLICANT *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CO_APPLICANT' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CO_APPLICANT' value=\"Help\" onClick=\"help_button_8()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_STAGE'  class=div_input>STAGE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_STAGE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_STAGE' value=\"Help\" onClick=\"help_button_9()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_REMARK'  class=div_input>REMARK *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REMARK' maxlength='100' size='100'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_REMARK' value=\"Help\" onClick=\"help_button_10()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_STATUS'  class=div_input>STATUS *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_STATUS' maxlength='1' size='1'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_STATUS' value=\"Help\" onClick=\"help_button_11()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			--*/
			out.println("</table>"); 
			out.println("<br>"); 
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
out.println("<br>"); 
out.println("<br>"); 
out.println("<br>"); 

			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change2></div></td></tr></table>");

			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
