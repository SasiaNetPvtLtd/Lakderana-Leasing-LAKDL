
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - INDICATIVE QUOTATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_indicative_quotation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods   m_sn_methods = new LAKDL_AF_CO_conn_methods  (); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Indicative Quotation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var y=0;");
			out.println("var j=0;");
			out.println("var x=0;");
			out.println("var val_of=0;");
			out.println("var x_of=0;");
			out.println("var lineno=0;");
			out.println("var m_row;");
			out.println("var hid_x=0;");
			out.println("var m_row1;");
			out.println("var arr_size=0;");
			out.println("var price_arry=new Array();");
			out.println("var qty_arry=new Array();");		
			out.println("var con_arry=new Array();");		
			out.println("var make_arry=new Array();");		
			out.println("var model_arry=new Array();");	
			out.println("var net_arry=new Array();");		
			out.println("var opt_arry=new Array();");		
			
						
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value!='TN'){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			
			out.println("				alert(document.Form1.hid_field.value)");
			out.println("if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_field.value=='TN'){");
			out.println("alert(data_vec)");
			out.println("				fill_fields(data_vec);");
			out.println("			}");
		
			
			out.println("}");
			
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_field.value=val;");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_indicative_quotation&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest1(valu) {");
			out.println("alert('rrr'+valu)");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_indicative_quotation_pricing_det&data_val=\"+valu;");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_QUOTATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_QUOTATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_INQUIRY_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INQUIRY_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PRICING_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PRICING_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_OPTION_ID.value==\"\"){  "); 
			out.println("DIV_TXT_OPTION_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_QTY.value==\"\"){  "); 
			out.println("DIV_TXT_QTY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_GROSS_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_GROSS_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_GROSS_RENTAL.value==\"\"){  "); 
			out.println("DIV_TXT_GROSS_RENTAL.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_RENTAL.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_RENTAL.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_RESIDUAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_RESIDUAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CONDITION_OF_ASSET.value==\"\"){  "); 
			out.println("DIV_TXT_CONDITION_OF_ASSET.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ITEM_CATEGORY.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_CATEGORY.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ITEM_SUB_CAT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_SUB_CAT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MAKE_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MAKE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SUB_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_MODEL_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PERIOD.value==\"\"){  "); 
			out.println("DIV_TXT_PERIOD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_GROSS_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_GROSS_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NET_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_NET_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_INSTALLMENT_NO.value==\"\"){  "); 
			out.println("DIV_TXT_INSTALLMENT_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_GRENTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_GRENTAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NET_RENTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_NET_RENTAL_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_RENT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_RENT_AMOUNT.style.color='red';");
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
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_MAS_save_indicative_quotation';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_indicative_quotation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_MAS_display_indicative_quotation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_indicative_quotation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Indicative Quotation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Indicative Quotation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_INQUIRY_NO.disabled=true;"); 
			out.println("document.Form1.TXT_PRICING_NO.disabled=true;"); 
			out.println("document.Form1.TXT_OPTION_ID.disabled=true;"); 
			out.println("document.Form1.TXT_QTY.disabled=true;"); 
			out.println("document.Form1.TXT_GROSS_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_GROSS_RENTAL.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_RENTAL.disabled=true;"); 
			out.println("document.Form1.TXT_RESIDUAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_CONDITION_OF_ASSET.disabled=true;"); 
			out.println("document.Form1.TXT_ITEM_CATEGORY.disabled=true;"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MAKE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PERIOD.disabled=true;"); 
			out.println("document.Form1.TXT_GROSS_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_NET_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_INSTALLMENT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_GRENTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_NET_RENTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_RENT_AMOUNT.disabled=true;"); 

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

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_PRO_help_select\"+"); 
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
			
			out.println("function HelpBox1(Start,End,Hid_No,Max) {"); 
			//out.println("alert('row1'+row1)");
			//out.println("alert('row'+row)");
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Servlet?class_in=\"+client_name+\"AF_MK_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); */
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");

			/*out.println("window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_PRO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No);"); */
			
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
			out.println("		help_value_assign_3(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 

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
			out.println("		help_value_assign_8(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("		help_value_assign_9(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_value_assign_10();"); 
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
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_QUOTATION_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			//out.println("    m_sql = \"m_help_TXT_INQUIRY_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_INQUIRY_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3(row1,row) {"); 
			out.println("alert('row1'+row1+'row'+row)");
			out.println("    pricing=\"text_price_\"+row1+\"_\"+row;");
			out.println(" alert(pricing)");
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_PRICING_NO_sql\";"); 
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    m_criteria = document.Form1.elements[pricing].value+\"@Y@\";"); 
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_3(row1,row) {"); 
			out.println("    pricing1=\"text_price_\"+row1+\"_\"+row;");
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println("    netamt=\"text_netamt_\"+row1+\"_\"+row;");
			//out.println("    mrent=\"text_monrent_\"+row1+\"_\"+row;");
			out.println("    period=\"text_period_\"+row1+\"_\"+row;");
			out.println("    initpay=\"text_initpay_\"+row1+\"_\"+row;");
			out.println("    ipay=\"text_ipay_\"+row1+\"_\"+row;");
			
			out.println("    document.Form1.elements[pricing1].value=oBj.valout[2];");
			//out.println("alert
			out.println("if (oBj.valout[4]=='null' || oBj.valout[4]==''){");
			out.println("    document.Form1.elements[make].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[make].value=oBj.valout[4];");
			out.println(" }");
			
			out.println("if (oBj.valout[5]=='null' || oBj.valout[5]==''){");
			out.println("    document.Form1.elements[model].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[model].value=oBj.valout[5];");
			out.println(" }");

			out.println("if (oBj.valout[27]=='null' || oBj.valout[27]==''){");
			out.println("    document.Form1.elements[netamt].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[netamt].value=oBj.valout[27];");
			out.println(" }");
			
			out.println("if (oBj.valout[27]=='null' || oBj.valout[27]==''){");
			out.println("    document.Form1.elements[netamt].value='';");
			out.println(" }");
			out.println("else{    document.Form1.elements[netamt].value=oBj.valout[27];");
			out.println(" }");
			out.println("document.Form1.hid_field.value ='TN'");
			out.println("makeRequest1(oBj.valout[2])");
			//out.println("  document.Form1.elements[mrent].value=oBj.valout[41] + '+ VAT of' +oBj.valout[46]+ '(0+1)';");
			out.println("   document.Form1.elements[period].value=oBj.valout[21];");
			
			out.println("    document.Form1.elements[initpay].value='Rs.'+ oBj.valout[25]+'/-';");
			out.println("    document.Form1.elements[ipay].value='Rs.'+ oBj.valout[26]+'/-';");
			out.println("}"); 



			out.println("function fill_fields(data_vec) {"); 
			out.println("    mrent=\"text_monrent_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");
			out.println("    mrent1=\"text_monvat_\"+document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value;");

			out.println("  document.Form1.elements[mrent].value='Rs.'+ data_vec[0]+'/-';");
			out.println("  document.Form1.elements[mrent1].value='Rs.'+ data_vec[1]+'/-';");

			out.println("}"); 


			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_OPTION_ID_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_OPTION_ID.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_OPTION_ID.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_5(row1,row) {"); 
			out.println("    condition=\"text_conasst_\"+row1+\"_\"+row;");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_CONDITION_OF_ASSET_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[condition].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_6() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CATEGORY_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_CATEGORY.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_6() {"); 
			out.println("    document.Form1.TXT_ITEM_CATEGORY.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_7() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_7() {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_8(row1,row) {"); 
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");

			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_MAKE_CODE_sql\";"); 
			
			out.println("    m_criteria = document.Form1.elements[make].value+\"@Y@\";"); 
			
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_8(row1,row) {"); 
			out.println("    make=\"text_make_\"+row1+\"_\"+row;");
			out.println("    document.Form1.elements[make].value=oBj.valout[2];"); 

			//out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_9(row1,row) {"); 
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
			out.println(" document.Form1.hid_row1_no.value=row1;");
			out.println(" document.Form1.hid_row_no.value=row;");
		
			out.println("    document.Form1.hid_help_type.value=\"9\";"); 
			out.println("    m_sql = \"m_help_TXT_MODEL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[model].value+\"@Y@\";"); 
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_9(row1,row) {"); 
			out.println("    model=\"text_model_\"+row1+\"_\"+row;");
  		out.println("    document.Form1.elements[model].value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_10() {"); 
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_SUB_MODEL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_MODEL_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_10() {"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_QUOTATION_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_QUOTATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox1('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_QUOTATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_INQUIRY_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_OPTION_ID.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_QTY.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_GROSS_AMOUNT.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_GROSS_RENTAL.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_VAT_RENTAL.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_RESIDUAL_AMOUNT.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_CONDITION_OF_ASSET.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_ITEM_CATEGORY.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT_CODE.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_MAKE_CODE.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[17];"); 
			out.println("    document.Form1.TXT_PERIOD.value=oBj.valout[18];"); 
			out.println("    document.Form1.TXT_GROSS_AMOUNT.value=oBj.valout[19];"); 
			out.println("    document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[20];"); 
			out.println("    document.Form1.TXT_NET_AMOUNT.value=oBj.valout[21];"); 
			out.println("    document.Form1.TXT_INSTALLMENT_NO.value=oBj.valout[22];"); 
			out.println("    document.Form1.TXT_GRENTAL_AMOUNT.value=oBj.valout[23];"); 
			out.println("    document.Form1.TXT_NET_RENTAL_AMOUNT.value=oBj.valout[24];"); 
			out.println("    document.Form1.TXT_VAT_RENT_AMOUNT.value=oBj.valout[25];"); 

			out.println("}"); 
			//-------------------------To add fields dynamically-------------------------------------------------------------------------
			//---------------------------------------------------------------------------------------------------------------------------
			out.println("function assign(bt) {");
			
			out.println("document.Form1.hid_bt_click.value=bt");
			out.println("}");
						
			//--------------------------------------999999999999999999999999999999999999999999999999999999
			out.println("function load(row1,row) {");
			out.println("m_row = '<table>'+");
      out.println("	 '<tr><td><b>Option ('+row1+')<input type=hidden name=option_'+row+' value=1></td>'+");
			out.println("  '<td><input type=button name=\"del_'+row1+'\" value=\" X \" onclick=option_del('+row1+','+row1+')></td>'+");

			out.println("	 '</tr>'+");
			out.println("	 '</table>'+");
			out.println("	'<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("	'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table>'+");
			
			
			out.println("  '<table>'+");
      out.println("  '<tr><td><input type=text name=\"text_price_'+row1+'_'+row+'\"><input type=\"button\" name=BUT_PRICING_NO_'+row1+'_'+row+' value=\" ? \" onClick=\"help_button_3('+row1+','+row+')\"></td>'+");
			
			out.println("  '<td><input type=text name=\"text_qty_'+row1+'_'+row+'\"></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+row1+'_'+row+'\">'+");
			//out.println("	 '<input type=\"button\" name=\"BUT_CONDITION_OF_ASSET_'+row1+'_'+row+'\" value=\" ? \" onClick=\"help_button_5('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+row1+'_'+row+'\">'+");
			out.println("  '<input type=\"button\" name=\"BUT_MAKE_CODE_'+row1+'_'+row+'\" value=\" ? \" onClick=\"help_button_8('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+row1+'_'+row+'\">'+");
			out.println("  '<input type=\"button\" name=\"BUT_MODEL_CODE_'+row1+'_'+row+'\" value=\" ? \" onClick=\"help_button_9('+row1+','+row+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+row1+'_'+row+'\" ></td>'+");
			//onblur=\"assign('TN'),makeRequest1('+row1+','+row+')\"
		
      out.println("  '<td><input type=button name=\"add_'+row1+'_'+row+'\" value=Add onclick=load_new('+row1+','+row+')></td>'+");
      out.println("  '<td><input type=button name=\"del_'+row1+'_'+row+'\" value=Del onclick=load_price('+row1+','+row+')></td>'+");
			out.println("	 '</tr>'+");
			out.println("	 '</table>'+");
			//***//
			
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+row1+'_'+row+'\"  size=\"50\"></td><td>+ VAT of </td><td><input type=text name=\"text_monvat_'+row1+'_'+row+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+row1+'_'+row+'\" size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+row1+'_'+row+'\" size=\"50\"></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+row1+'_'+row+'\"  size=\"50\"></td></tr></table>';");
			//***//

			
			//out.println("	 '</table>';");
			out.println("	change1.innerHTML=m_row; ");
			out.println("		document.Form1.hid_opt.value =  1;");
			
			out.println("}");

			out.println("function load_new(row1,row) {");
			out.println("m_row = '';");
		 	out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+j+')</td>'+");
			out.println("  '<td><input type=button name=\"del_'+j+'\" value=\" X \" onclick=option_del('+j+','+j+')></td>'+");

			out.println("	 '</tr>'+");
			//out.println("	 '</table><table>';");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table><table>';");
			
			
			out.println("if(row1==j){	");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value)+1;");
			out.println("}	 ");
			out.println("else{");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("}");
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
				
			out.println("if(row1==j && i==parseFloat(m_next)-1){	");
			out.println("m_row =   m_row+   "); 
      out.println("  '<tr><td><input type=text name=\"text_price_'+j+'_'+i+'\"><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
      out.println("  '<td><input type=text name=\"text_qty_'+j+'_'+i+'\"></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+j+'_'+i+'\">'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+j+'_'+i+'\">'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+j+'_'+i+'\">'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+j+'_'+i+'\"></td>'+");
			
			out.println("  '<td><input type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=load_new('+j+','+m_next+')></td>'+");
      out.println("  '<td><input type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("	 '</tr>'+");
			
				//***//
			
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\"  size=\"50\"></td><td>+ VAT of </td><td><input type=text name=\"text_monvat_'+j+'_'+i+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\"></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+i+'\"  size=\"50\"></td></tr></table>';");
			//***//



			out.println("}else{");
			out.println("m_row =   m_row+ ");   
			out.println("  '<tr><td><input type=text name=\"text_price_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+j+' value=\" ? \" onClick=\"help_button_3('+j+','+j+')\"></td>'+");
      out.println("  '<td><input type=text name=\"text_qty_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'>'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");

			
 
			
			out.println("  '<td><input type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=load_new('+j+','+m_next+')></td>'+");
      out.println("  '<td><input type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("	 '</tr>'+");
			
			//***//
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+' size=\"50\"></td></tr></table>';");
			//out.println(" alert(document.Form1.hid_row1_no.value+\"_\"+document.Form1.hid_row_no.value);");
			
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td><td>+ VAT of </td><td><input type=text name=\"text_monvat_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");

			
			
			//***//



			out.println("}	");		
 			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+i+'></table>';");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			out.println("		change1.innerHTML=m_row; ");
			out.println("}");

		
			
			out.println("function load_price(row1,row) {");
			out.println("m_row = '';");
		  out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+j+')</td>'+");
			out.println("  '<td><input type=button name=\"del_'+j+'\" value=\" X \" onclick=option_del('+j+','+j+')></td>'+");
			out.println("	 '</tr>'+");
			//out.println("	 '</table><table>';");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table><table>';");
			
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("z=0;");
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
 			out.println("if(i==row && j==row1){");
 			out.println("}else{	");
 			out.println("m_val_ass=parseFloat(m_next)-1;	");
 			out.println("m_row =   m_row+");    
      out.println("  '<tr><td><input type=text name=\"text_price_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+z+' value=\" ? \" onClick=\"help_button_3('+j+','+z+')\"></td>'+");
 			
      out.println("  '<td><input type=text name=\"text_qty_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'>'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+z+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+z+' value=\" ? \" onClick=\"help_button_8('+j+','+z+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+z+' value=\" ? \" onClick=\"help_button_9('+j+','+z+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+j+'_'+z+'\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");
				
			
	
			out.println("'<td><input type=button name=\"add_'+j+'_'+z+'\" value=Add onclick=load_new('+j+','+m_val_ass+')></td>'+");
 			out.println("'<td><input type=button name=\"del_'+j+'_'+z+'\" value=Del onclick=load_price('+j+','+z+')></td>'+");
			out.println("	 '</tr>'+");
			
			//***//
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+' size=\"50\"></td></tr></table>';");

			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+z+'\"  size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td>VAT of </td><td><input type=text name=\"text_monvat_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td> VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+z+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");

			//***//



 			out.println("z=z+1;");
 			out.println("}	");
 			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+z+'></table>';");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			out.println("hid_x=parseFloat(document.Form1.hid_opt.value);");
 			//out.println("alert('m_row='+m_row);");
			out.println("change1.innerHTML=m_row; ");
			out.println("}");

			out.println("function load_option(row1,row) {");
			out.println("m_row = '';");
		  out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value)+1;j++){");
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+j+')</td>'+");
			out.println("  '<td><input type=button name=\"del_'+j+'\" value=\" X \" onclick=option_del('+j+','+j+')></td>'+");

			out.println("	 '</tr>'+");
			//out.println("	 '</table><table>';");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table><table>';");
		
		
			out.println("if(parseFloat(document.Form1.hid_opt.value)==j){");
		  out.println("m_next=1;");
			out.println("}	 ");
			out.println("else{");
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			out.println("}");
			//out.println("alert('m_next='+m_next);			");	
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
 			//out.println("alert('m_row='+m_row);");
			out.println("if(parseFloat(document.Form1.hid_opt.value)==j){");
			out.println("m_row =   m_row+    ");
      out.println("'<tr><td><input type=text name=\"text_price_'+j+'_'+i+'\"><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
 			out.println("  '<td><input type=text name=\"text_qty_'+j+'_'+i+'\"></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+j+'_'+i+'\">'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+j+'_'+i+'\">'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+j+'_'+i+'\">'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+j+'_'+i+'\"></td>'+");
				
			out.println("'<td><input type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=load_new('+j+','+m_next+')></td>'+");
 			out.println("'<td><input type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("	 '</tr>'+");
			
			//***//
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\"  size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\"></td></tr></table>';");
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\"  size=\"50\"></td><td>+ VAT of </td><td><input type=text name=\"text_monvat_'+j+'_'+i+'\"  size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\"></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\"></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+i+'\"  size=\"50\"></td></tr></table>';");
		
			
			
			
			//***//



			out.println("}else{");
		 	out.println("m_row =   m_row+ ");   
      out.println("'<tr><td><input type=text name=\"text_price_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
 			
			out.println("  '<td><input type=text name=\"text_qty_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'>'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_9('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");

				
			
			out.println("'<td><input type=button name=\"add_'+j+'_'+i+'\" value=Add onclick=load_new('+j+','+m_next+')></td>'+");
 			out.println("'<td><input type=button name=\"del_'+j+'_'+i+'\" value=Del onclick=load_price('+j+','+i+')></td>'+");
			out.println("'</tr>'+");
			
			//***//
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+' size=\"50\"></td></tr></table>';");
			
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td><td>+ VAT of </td><td><input type=text name=\"text_monvat_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");

			
			//***//


 			out.println("}	");			
			out.println("}");
			out.println("m_row =   m_row+'<input type=hidden name=option_'+j+' value='+i+'></table>';");
			out.println("}");
			out.println("document.Form1.hid_opt.value=j");
			//out.println("hid_x=parseFloat(document.Form1.hid_opt.value);");
 		//out.println("alert('hid1131='+hid_x);");
			out.println("change1.innerHTML=m_row; ");
			out.println("}");
			
//-------------------------------------------------------------------------------------------------
			out.println("function option_del(row1,row) {");	
			
			out.println("m_row = '';");
 			//out.println("alert('hid_opt='+document.Form1.hid_opt.value);");
			out.println("z=0;");
		  out.println("for(j=0;j<parseFloat(document.Form1.hid_opt.value);j++){");
 			
			out.println("if(j==row1){");
			out.println("}else{	");
				
			out.println("m_row = m_row+'<table>'+");
      out.println("  '<tr><td><b>Option ('+j+')</td>'+");
			out.println("  '<td><input type=button name=\"del_'+z+'\" value=\" X \" onclick=option_del('+z+','+z+')></td>'+");
			out.println("	 '</tr>'+");
			//out.println("	 '</table><table>';");
			out.println("'</table><table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr><td width=\"18%\" ><b>Pricing No *</b></td>'+"); 
			out.println("'<td width=\"14%\" ><b>Quantity*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Condition of Assets*</b></td>'+"); 
			out.println("'<td width=\"18%\" ><b>Make Code*</b></td>'+"); 
			out.println("'<td width=\"17%\" ><b>Model Code*</b></td>'+"); 
			out.println("'<td width=\"*%\" ><b>Net Amount*</b></td>'+"); 
			out.println("'</tr></table><table>';");
			
			//out.println("alert('j='+j);	");		
			
		  out.println("m_next=parseFloat(document.Form1.elements[\"option_\"+j].value);");
			//out.println("alert('m_nextyryyr='+m_next);	");		
			
		
			
			
			
	   	out.println("for(i=0;i<parseFloat(m_next);i++){");
			//out.println("for(i=0;i<parseFloat(hidx);i++){");
 			//out.println("if(i==row ){");
 			//out.println("}else{	");
 			
			out.println("m_val_ass=parseFloat(m_next);	");
			//out.println("alert('m_val_ass='+m_val_ass);	");		
			out.println("m_row =   m_row+");    
      out.println("  '<table><tr><td><input type=text name=\"text_price_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_price_\"+j+\"_\"+i].value+'><input type=\"button\" name=BUT_PRICING_NO_'+j+'_'+i+' value=\" ? \" onClick=\"help_button_3('+j+','+i+')\"></td>'+");
 		
			out.println("  '<td><input type=text name=\"text_qty_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_qty_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td><input type=text name=\"text_conasst_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_conasst_\"+j+\"_\"+i].value+'>'+");
			//out.println("	 '<input type=\"button\" name=BUT_CONDITION_OF_ASSET_'+z+'_'+i+' value=\" ? \" onClick=\"help_button_5()\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_make_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_make_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MAKE_CODE_'+z+'_'+i+' value=\" ? \" onClick=\"help_button_8('+j+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_model_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_model_\"+j+\"_\"+i].value+'>'+");
			out.println("  '<input type=\"button\" name=BUT_MODEL_CODE_'+z+'_'+i+' value=\" ? \" onClick=\"help_button_9('+z+','+i+')\"></td>'+"); 
			out.println("  '<td><input type=text name=\"text_netamt_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_netamt_\"+j+\"_\"+i].value+'></td>'+");
		

			out.println("'<td><input type=button name=\"add_'+z+'_'+i+'\" value=Add onclick=load_new('+z+','+m_val_ass+')></td>'+");
 			out.println("'<td><input type=button name=\"del_'+z+'_'+i+'\" value=Del onclick=load_price('+z+','+i+')></td>'+");
			
			
			
			//out.println("  '<td><input type=text name=\"text_monrent_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+' size=\"50\"></td>'+");
			//out.println("  '<td><input type=text name=\"text_period_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+' size=\"50\"></td>'+");
			//out.println("  '<td><input type=text name=\"text_initpay_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+' size=\"50\"></td>'+");


			out.println("	 '</tr>'+");
			
			//***//
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+' size=\"50\"></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+z+'_'+i+'\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+' size=\"50\"></td></tr></table>';");
			
			//out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td><td>+ VAT of Rs.</td><td><input type=text name=\"text_monvat_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			//out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td><td>+ VAT of</td><td><input type=text name=\"text_ipay_'+j+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
			out.println("  '<table><tr><td width=\"*%\" ><b>Monthly Rental - </b></td><td><input type=text name=\"text_monrent_'+z+'_'+i+'\"  size=\"50\" value='+document.Form1.elements[\"text_monrent_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td>VAT of </td><td><input type=text name=\"text_monvat_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_monvat_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Period - </b></td><td><input type=text name=\"text_period_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_period_\"+j+\"_\"+i].value+'></td></tr>'+");
			out.println("  '<tr><td width=\"*%\" ><b>Initial Payment - </b></td><td><input type=text name=\"text_initpay_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_initpay_\"+j+\"_\"+i].value+'></td>'+");
			out.println("  '<td> VAT of</td><td><input type=text name=\"text_ipay_'+z+'_'+i+'\" size=\"50\" value='+document.Form1.elements[\"text_ipay_\"+j+\"_\"+i].value+'></td></tr></table>';");
		
			//***//
				
 			//out.println("}	");
				
 			out.println("}");
				
			//out.println("alert('z='+z);	");		
			out.println("m_row =   m_row+'<input type=hidden name=option_'+z+' value='+i+'></table>';");
			out.println("z=z+1;");
			
			out.println("}");
			//out.println("alert('m_row='+m_row);	");		
			
			out.println("}	");
			out.println("document.Form1.hid_opt.value=z");
 			out.println("change1.innerHTML=m_row; ");
			
			out.println("}");
			//-------------------------------------------------------------------------------------------------------------------------------------
			//-------------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load(x,y)\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_opt' VALUE=\"1\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_bt_click' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row1_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_field' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Indicative Quotation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
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


			out.println("<table align='center' width='100%' class='table' cellpadding='2' cellspacing='2'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_QUOTATION_NO'  class=div_input>Quatation No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_QUOTATION_NO' maxlength='15' size='15' onblur=\"makeRequest(document.Form1.TXT_QUOTATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_INQUIRY_NO'  class=div_input>Inquiry No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INQUIRY_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_INQUIRY_NO' value=\"Help\" onClick=\" help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_OPTION_ID'  class=div_input>OPTION_ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OPTION_ID' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_OPTION_ID' value=\"Help\" onClick=\"help_button_3()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ADD' value=\"Add\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input>PRICING_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRICING_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_QTY'  class=div_input>QTY *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_QTY' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); */
	
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GROSS_RENTAL'  class=div_input>Gross Rental *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GROSS_RENTAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_RENTAL'  class=div_input>VAT_RENTAL *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_RENTAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

*/
			/*out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_PRICING_NO'  class=div_input>PRICING_NO *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PRICING_NO' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_PRICING_NO' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_OPTION_ID'  class=div_input>OPTION_ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OPTION_ID' maxlength='15' size='15'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_OPTION_ID' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_QTY'  class=div_input>QTY *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_QTY' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_GROSS_AMOUNT'  class=div_input>GROSS_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GROSS_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_AMOUNT'  class=div_input>VAT_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_GROSS_RENTAL'  class=div_input>GROSS_RENTAL *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GROSS_RENTAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_RENTAL'  class=div_input>VAT_RENTAL *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_RENTAL' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_RESIDUAL_AMOUNT'  class=div_input>RESIDUAL_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RESIDUAL_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_CONDITION_OF_ASSET'  class=div_input>CONDITION_OF_ASSET *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CONDITION_OF_ASSET' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_CATEGORY'  class=div_input>ITEM_CATEGORY *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_CATEGORY' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ITEM_CATEGORY' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_SUB_CAT_CODE'  class=div_input>ITEM_SUB_CAT_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_ITEM_SUB_CAT_CODE' value=\"Help\" onClick=\"help_button_6()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_MAKE_CODE'  class=div_input>MAKE_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MAKE_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MAKE_CODE' value=\"Help\" onClick=\"help_button_7()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_MODEL_CODE'  class=div_input>MODEL_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_MODEL_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MODEL_CODE' value=\"Help\" onClick=\"help_button_8()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_MODEL_CODE'  class=div_input>SUB_MODEL_CODE *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_MODEL_CODE' maxlength='10' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SUB_MODEL_CODE' value=\"Help\" onClick=\"help_button_9()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_PERIOD'  class=div_input>PERIOD *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PERIOD' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_GROSS_AMOUNT'  class=div_input>GROSS_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GROSS_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_AMOUNT'  class=div_input>VAT_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_NET_AMOUNT'  class=div_input>NET_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NET_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_INSTALLMENT_NO'  class=div_input>INSTALLMENT_NO *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INSTALLMENT_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_GRENTAL_AMOUNT'  class=div_input>GRENTAL_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GRENTAL_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_NET_RENTAL_AMOUNT'  class=div_input>NET_RENTAL_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NET_RENTAL_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_VAT_RENT_AMOUNT'  class=div_input>VAT_RENT_AMOUNT *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_RENT_AMOUNT' maxlength='22' size='22'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			*/
			out.println("<br>");
			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td width='30%'><input type=\"button\" name=BUT_MORE value=\"  Add Options  \" onClick=\"load_option(document.Form1.hid_opt.value,0)\"</tr></table>");

			

			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=change1></div></td></tr></table>");
			/*out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='2' cellpadding='2'><tr>");
			out.println("<td width='100%'><div id=change2></div></td></tr></table>");
			out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='2' cellpadding='2'><tr>");
			out.println("<td width='100%'><div id=change3></div></td></tr></table>");*/
			
			out.println("<br>"); 
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
