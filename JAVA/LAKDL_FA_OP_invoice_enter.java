// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_invoice_enter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name.trim();
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out=res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
											
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Invoice Entry</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_EXCHANGE_RATE.value=data_vec[0];");
			out.println("			format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("			document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=0;");
			out.println("			get_rpt_currency_amt();");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("		}");
			out.println("		else if(data_vec.length==0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_EXCHANGE_RATE.value=0;");
			out.println("			format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("			document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=0;");
			out.println("			get_rpt_currency_amt();");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_option.value==\"2\"){");
			out.println("			document.Form1.TXT_INVOICE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_INVOICE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_INVOICE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_INVOICE_BATCH_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_INVOICE_BATCH_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_INVOICE_BATCH_DATE_YY.value=data_vec[2];");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("	  	get_exchange_rate();");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_option.value==\"3\"){");
			out.println("			document.Form1.TXT_DEBTOR_CODE.value=data_vec[0];");
			out.println("			document.Form1.TXT_DEBTOR_NAME.value=data_vec[1];");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_option.value==\"4\"){");
			out.println("			if(document.Form1.hid_status.value!=\"Edit\"){");  
			out.println("			DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("			if(confirm('Invoice already exesist with Batch No:'+data_vec[1]+' Please Check'))");
			out.println("					show_invoice_details_ref_no(data_vec[0]);");
			out.println("			}");
			out.println("		}");
			out.println("		else if(data_vec.length==0 && document.Form1.hid_option.value==\"3\"){");
			out.println("			help_update_debtor();");
			out.println("		}");
			out.println("}");
			
			out.println("function get_exchange_rate() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_date=document.Form1.TXT_INVOICE_DD.value+\"-\"+document.Form1.TXT_INVOICE_MM.value+\"-\"+document.Form1.TXT_INVOICE_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_exchange_rate&CURR_CODE=\"+document.Form1.TXT_CURRENCY_CODE.value+\"&VAL_DATE=\"+m_date;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"2\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_debtor_name() {");
			out.println("   if(document.Form1.TXT_DEBTOR_CODE.value != '') {");
			out.println("	  	document.Form1.hid_option.value=\"3\";");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_debtor_name&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value+\"&debtor_code=\"+document.Form1.TXT_DEBTOR_CODE.value+\"\";");
			out.println("			load_interface(m_url,'XML');");
			out.println("   }");
			out.println("}");
			
			out.println("function get_check_invoice_no() {");
			out.println("   if(document.Form1.TXT_CLIENT_CODE.value!='' && document.Form1.TXT_INVOICE_NO.value!='') {");
			out.println("	  	document.Form1.hid_option.value=\"4\";");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_check_invoice_no&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&invoice_no=\"+document.Form1.TXT_INVOICE_NO.value;");
			out.println("			load_interface(m_url,'XML');");
			out.println("   }");
			out.println("}");
			
			out.println("function clear_debtor(){"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=\"\";");
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=\"\";");
			out.println("		document.Form1.TXT_INVOICE_NO.value=\"\";");
			out.println("		document.Form1.TXT_INVOICE_AMOUNT.value=\"\";");
			out.println("		document.Form1.TXT_INVOICE_DD.value=\"\";");
			out.println("		document.Form1.TXT_INVOICE_MM.value=\"\";");
			out.println("		document.Form1.TXT_INVOICE_YY.value=\"\";");
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value=0;");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=0;");
			out.println("}"); 
			
			out.println("function total_invoices(){"); 
			out.println("		m_amt=0;");
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_invoice_count.value);k++){");
			out.println("				obj=document.Form1.elements[\"TXT_INVOICE_AMOUNT_\"+k].value;");
			out.println("				m_amt=m_amt+parseFloat(unformat_noobject(obj));");
			out.println("		}");
			out.println("		document.Form1.TXT_INVOICE_COUNT.value=document.Form1.hid_invoice_count.value;");
			out.println("		document.Form1.TXT_INVOICE_BATCH_AMOUNT.value=m_amt;");
			out.println("		format_num(document.Form1.TXT_INVOICE_BATCH_AMOUNT,4);");
			out.println("		invoice_batch_total.innerHTML=\"BATCH TOTAL=\"+document.Form1.TXT_INVOICE_BATCH_AMOUNT.value;");
			out.println("		invoice_batch_count.innerHTML=\"INVOICE COUNT=\"+document.Form1.TXT_INVOICE_COUNT.value;");
			//out.println("		if(!isNaN(parseFloat(document.Form1.TXT_INVOICE_NO.value))){");
			//out.println("		document.Form1.TXT_INVOICE_NO.value=parseFloat(document.Form1.TXT_INVOICE_NO.value)+1;");
			//out.println("		}");
			//out.println("		else{");
			//out.println("		document.Form1.TXT_INVOICE_NO.value=\"\";");
			//out.println("		}");
			out.println("		document.Form1.TXT_INVOICE_NO.focus();");
			out.println("}"); 
			
			out.println("function get_rpt_currency_amt(){"); 
			out.println("		m_amt=0;");
			out.println("		m_amt=parseFloat(unformat_noobject(document.Form1.TXT_EXCHANGE_RATE.value))*parseFloat(unformat_noobject(document.Form1.TXT_INVOICE_AMOUNT.value));");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=m_amt;");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("}"); 
			
			out.println("function add_invoice(){"); 
			out.println("		 m_dir_count=parseInt(document.Form1.hid_invoice_count.value);");
			out.println("		 m_dir_count++;");
			out.println("		 if(validate_invoice_data(m_dir_count)){");
			out.println("		 	if(parseInt(document.Form1.hid_invoice_count.value)==0){");
			out.println("		 		invoice_detail_data.innerHTML=\"\";"); 
			out.println("		 	}");
			out.println("		 	invoice_detail_data.innerHTML=invoice_detail_data.innerHTML+'<table  width=\"100%\">'+"); 
			out.println("		 	'<tr>'+"); 
			out.println("		 	'<td width=\"1%\" class=div_input><b>'+m_dir_count+'</b></td>'+"); 
			out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_'+m_dir_count+'\" disabled   value=\"'+document.Form1.TXT_DEBTOR_CODE.value+'\"></td>'+"); 
			out.println("		 	'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" style=\"width:200\" name=\"TXT_DEBTOR_NAME_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_DEBTOR_NAME.value+'\"></td>'+");
			out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_INVOICE_NO.value+'\"></td>'+"); 
			out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_REFERENCE_NO_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_REFERENCE_NO.value+'\"></td>'+"); //add by indika 04/09/08
			//out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SERIAL_NO_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_SERIAL_NO.value+'\"></td>'+"); //add by indika 03/09/08
			out.println("		 	'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_INVOICE_AMOUNT.value+'\"  style=\"text-align:right;\"></td>'+");
			out.println("		 	'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_CODE_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_DISPUTE_CODE.value+'\"  style=\"text-align:right;\"></td>'+");
			out.println("		 	'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_AMOUNT_'+m_dir_count+'\" disabled value=\"'+document.Form1.TXT_DISPUTE_AMOUNT.value+'\"  style=\"text-align:right;\"></td>'+");
			out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_DD_'+m_dir_count+'\" value=\"'+document.Form1.TXT_INVOICE_DD.value+'\"  style=\"width:30\" disabled>'+");
			out.println("		 	'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_MM_'+m_dir_count+'\"  value=\"'+document.Form1.TXT_INVOICE_MM.value+'\"  style=\"width:30\" disabled>'+");
			out.println("		 	'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_YY_'+m_dir_count+'\"   value=\"'+document.Form1.TXT_INVOICE_YY.value+'\"  style=\"width:30\" disabled>'+");
			out.println("		 	'<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_COMMENTS_'+m_dir_count+'\" value=\"'+document.Form1.TXT_INVOICE_COMMENTS.value+'\">'+");			
			out.println("		 	'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_CURRENCY_CODE_'+m_dir_count+'\" value=\"'+document.Form1.TXT_CURRENCY_CODE.value+'\">'+");			
			out.println("		 	'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EXCHANGE_RATE_'+m_dir_count+'\" value=\"'+document.Form1.TXT_EXCHANGE_RATE.value+'\">'+");			
			out.println("		 	'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_RPT_CURR_AMT_'+m_dir_count+'\" value=\"'+document.Form1.TXT_INVOICE_RPT_CURR_AMT.value+'\">'+");			
			out.println("		 	'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_SEQ_NO_'+m_dir_count+'\" value=\"'+document.Form1.TXT_INVOICE_SEQ_NO.value+'\">'+");			
			out.println("		 	'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EDIT_STATUS_'+m_dir_count+'\" value=\"Y\">'+");			
			out.println("		 	'<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"Edit\" onClick=\"edit_invoice('+m_dir_count+')\" ><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_invoice('+m_dir_count+')\" ></td>'+");
			out.println("		 	'</tr>'+"); 
			out.println("		 	'</table>';"); 
			out.println("			DIV_TXT_DEBTOR_CODE.style.color='black';");
			out.println("			DIV_TXT_INVOICE_NO.style.color='black';");
			out.println("			DIV_TXT_REFERENCE_NO.style.color='black';");//add by indika 04/09/08
			//out.println("			DIV_TXT_SERIAL_NO.style.color='black';");//add by indika 03/09/08
			out.println("			DIV_TXT_INVOICE_DATE.style.color='black';");
			out.println("			DIV_TXT_INVOICE_DATE.style.color='black';");
			out.println("			DIV_TXT_INVOICE_DATE.style.color='black';");
			out.println("			DIV_TXT_INVOICE_AMOUNT.style.color='black';");
			out.println("		 	document.Form1.hid_invoice_count.value=m_dir_count;");
			out.println("		 	total_invoices();");
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function edit_invoice(mnum){"); 
			out.println("   obj1=document.Form1.elements[\"TXT_DEBTOR_CODE_\"+mnum].value;");
			out.println("   obj2=document.Form1.elements[\"TXT_DEBTOR_NAME_\"+mnum].value;");
			out.println("   obj3=document.Form1.elements[\"TXT_INVOICE_NO_\"+mnum].value;");
			out.println("   obj13=document.Form1.elements[\"TXT_REFERENCE_NO_\"+mnum].value;");//add by indika 04/09/08
			//out.println("   obj13=document.Form1.elements[\"TXT_SERIAL_NO_\"+mnum].value;");//add by indika 03/09/08
			out.println("   obj4=document.Form1.elements[\"TXT_INVOICE_AMOUNT_\"+mnum].value;");
			out.println("   obj5=document.Form1.elements[\"TXT_INVOICE_DD_\"+mnum].value;");
			out.println("   obj6=document.Form1.elements[\"TXT_INVOICE_MM_\"+mnum].value;");
			out.println("   obj7=document.Form1.elements[\"TXT_INVOICE_YY_\"+mnum].value;");
			out.println("   obj8=document.Form1.elements[\"TXT_INVOICE_COMMENTS_\"+mnum].value;");
			out.println("   obj9=document.Form1.elements[\"TXT_CURRENCY_CODE_\"+mnum].value;");
			out.println("   obj10=document.Form1.elements[\"TXT_EXCHANGE_RATE_\"+mnum].value;");
			out.println("   obj11=document.Form1.elements[\"TXT_INVOICE_RPT_CURR_AMT_\"+mnum].value;");
			out.println("   obj12=document.Form1.elements[\"TXT_INVOICE_SEQ_NO_\"+mnum].value;");
			out.println("   obj15=document.Form1.elements[\"TXT_DISPUTE_CODE_\"+mnum].value;");
			out.println("   obj14=document.Form1.elements[\"TXT_DISPUTE_AMOUNT_\"+mnum].value;");
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=obj1;");
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=obj2;");
			out.println("		document.Form1.TXT_INVOICE_NO.value=obj3;");
			out.println("		document.Form1.TXT_REFERENCE_NO.value=obj13;");//add by indika 04/09/08
			//out.println("		document.Form1.TXT_SERIAL_NO.value=obj13;");//add by indika 03/09/08
			out.println("		document.Form1.TXT_INVOICE_AMOUNT.value=obj4;");
			out.println("		document.Form1.TXT_INVOICE_DD.value=obj5;");
			out.println("		document.Form1.TXT_INVOICE_MM.value=obj6;");
			out.println("		document.Form1.TXT_INVOICE_YY.value=obj7;");
			out.println("		document.Form1.TXT_INVOICE_COMMENTS.value=obj8;");
			out.println("		document.Form1.TXT_CURRENCY_CODE.value=obj9;");
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value=obj10;");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=obj11;");
			out.println("		document.Form1.TXT_INVOICE_SEQ_NO.value=obj12;");
			out.println("		document.Form1.TXT_DISPUTE_AMOUNT.value=obj14;");
			out.println("		document.Form1.TXT_DISPUTE_CODE.value=obj15;");
			out.println("   delete_invoice(mnum);");
			out.println("		total_invoices();");
			out.println("		}");
			
			out.println("function delete_invoice(mnum){");	
			out.println("	if(parseInt(mnum)>0){");
			out.println("		 m_dir_count=parseInt(document.Form1.hid_invoice_count.value);");
			out.println("    m_count=0;");
			out.println("    m_str=\"\";");
			out.println("    m_str_main=\"\";");
			//out.println("		 alert(invoice_detail_data.innerHTML);");
			out.println("    for(k=1;k<=m_dir_count;k++){");
			out.println("    	if(k!=mnum){");
			out.println("		 		m_count++;");
			out.println("    		obj1=document.Form1.elements[\"TXT_DEBTOR_CODE_\"+k].value;");
			out.println("    		obj2=document.Form1.elements[\"TXT_DEBTOR_NAME_\"+k].value;");
			out.println("    		obj3=document.Form1.elements[\"TXT_INVOICE_NO_\"+k].value;");
			out.println("    		obj13=document.Form1.elements[\"TXT_REFERENCE_NO_\"+k].value;");//add by indika 04/09/08
			//out.println("    		obj3=document.Form1.elements[\"TXT_SERIAL_NO_\"+k].value;");//add by indika 03/09/08
			out.println("    		obj4=document.Form1.elements[\"TXT_INVOICE_AMOUNT_\"+k].value;");
			out.println("    		obj5=document.Form1.elements[\"TXT_INVOICE_DD_\"+k].value;");
			out.println("    		obj6=document.Form1.elements[\"TXT_INVOICE_MM_\"+k].value;");
			out.println("    		obj7=document.Form1.elements[\"TXT_INVOICE_YY_\"+k].value;");
			out.println("    		obj8=document.Form1.elements[\"TXT_INVOICE_COMMENTS_\"+k].value;");
			out.println("    		obj9=document.Form1.elements[\"TXT_CURRENCY_CODE_\"+k].value;");
			out.println("    		obj10=document.Form1.elements[\"TXT_EXCHANGE_RATE_\"+k].value;");
			out.println("    		obj11=document.Form1.elements[\"TXT_INVOICE_RPT_CURR_AMT_\"+k].value;");
			out.println("   		obj12=document.Form1.elements[\"TXT_INVOICE_SEQ_NO_\"+k].value;");
			out.println("   		obj14=document.Form1.elements[\"TXT_EDIT_STATUS_\"+k].value;");
			out.println("   		obj15=document.Form1.elements[\"TXT_DISPUTE_CODE_\"+k].value;");
			out.println("   		obj16=document.Form1.elements[\"TXT_DISPUTE_AMOUNT_\"+k].value;");
			out.println("		 		if(obj14==\"Y\"){");
			out.println("		 		m_str='<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"1%\" class=div_input><b>'+m_count+'</b></td>'+"); 
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_'+m_count+'\" disabled   value=\"'+obj1+'\"></td>'+"); 
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" style=\"width:200\" name=\"TXT_DEBTOR_NAME_'+m_count+'\" disabled value=\"'+obj2+'\"></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_'+m_count+'\" disabled value=\"'+obj3+'\"></td>'+"); 
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_REFERENCE_NO_'+m_count+'\" disabled value=\"'+obj13+'\"></td>'+"); //add by indika 04/09/08
			//out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SERIAL_NO_'+m_count+'\" disabled value=\"'+obj13+'\"></td>'+"); //add by indika 03/09/08
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_'+m_count+'\" disabled value=\"'+obj4+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_CODE_'+m_count+'\" disabled value=\"'+obj15+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_AMOUNT_'+m_count+'\" disabled value=\"'+obj16+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_DD_'+m_count+'\" value=\"'+obj5+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_MM_'+m_count+'\"  value=\"'+obj6+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_YY_'+m_count+'\"  value=\"'+obj7+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_COMMENTS_'+m_count+'\" value=\"'+obj8+'\">'+");			
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_CURRENCY_CODE_'+m_count+'\"  value=\"'+obj9+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EXCHANGE_RATE_'+m_count+'\"   value=\"'+obj10+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_RPT_CURR_AMT_'+m_count+'\" value=\"'+obj11+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_SEQ_NO_'+m_count+'\" value=\"'+obj12+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EDIT_STATUS_'+m_count+'\" value=\"Y\">'+");	
			out.println("		 		'<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"Edit\" onClick=\"edit_invoice('+m_count+')\" ><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_invoice('+m_count+')\" ></td>'+");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';"); 
			out.println("		 		}else{"); 
			out.println("		 		m_str='<table  width=\"100%\">'+"); 
			out.println("		 		'<tr>'+"); 
			out.println("		 		'<td width=\"1%\" class=div_input><b>'+m_count+'</b></td>'+"); 
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_'+m_count+'\" disabled   value=\"'+obj1+'\"></td>'+"); 
			out.println("		 		'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" style=\"width:200\" name=\"TXT_DEBTOR_NAME_'+m_count+'\" disabled value=\"'+obj2+'\"></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_'+m_count+'\" disabled value=\"'+obj3+'\"></td>'+"); 
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_REFERENCE_NO_'+m_count+'\" disabled value=\"'+obj13+'\"></td>'+"); //add by indika 04/09/08
			//out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_SERIAL_NO_'+m_count+'\" disabled value=\"'+obj13+'\"></td>'+"); //add by indika 03/09/08
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_'+m_count+'\" disabled value=\"'+obj4+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_CODE_'+m_count+'\" disabled value=\"'+obj15+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DISPUTE_AMOUNT_'+m_count+'\" disabled value=\"'+obj16+'\" style=\"text-align:right;\" ></td>'+");
			out.println("		 		'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_DD_'+m_count+'\" value=\"'+obj5+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_MM_'+m_count+'\"  value=\"'+obj6+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_YY_'+m_count+'\"  value=\"'+obj7+'\"  style=\"width:30\" disabled>'+");
			out.println("		 		'<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_COMMENTS_'+m_count+'\" value=\"'+obj8+'\">'+");			
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_CURRENCY_CODE_'+m_count+'\"  value=\"'+obj9+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EXCHANGE_RATE_'+m_count+'\"   value=\"'+obj10+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_RPT_CURR_AMT_'+m_count+'\" value=\"'+obj11+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_SEQ_NO_'+m_count+'\" value=\"'+obj12+'\">'+");
			out.println("		 		'<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EDIT_STATUS_'+m_count+'\" value=\"N\">'+");	
			out.println("		 		'<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"Edit\" onClick=\"edit_invoice('+m_count+')\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_invoice('+m_count+')\" disabled></td>'+");
			out.println("		 		'</tr>'+"); 
			out.println("		 		'</table>';"); 
			out.println("		 		}"); 
			out.println("		 		m_str_main=m_str_main+m_str;");
			out.println("    	}");
			out.println("	 	 }");
			out.println("		 invoice_detail_data.innerHTML=m_str_main;");
			out.println("		 document.Form1.hid_invoice_count.value=m_count;");
			out.println("		 total_invoices();");
			out.println("	}");
			out.println("}"); 
			
			out.println("function validate_data(){"); 
			out.println("	m_flag=true;");
			out.println("	if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_INVOICE_BATCH_DATE_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_BATCH_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_INVOICE_BATCH_DATE_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_BATCH_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_INVOICE_BATCH_DATE_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_BATCH_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(parseInt(document.Form1.hid_invoice_count.value)>0){  "); 
			out.println("		invoice_validation.innerHTML=\"Invoice Validation Started\";");
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_invoice_count.value);k++){");
			out.println("				obj=document.Form1.elements[\"TXT_INVOICE_AMOUNT_\"+k].value;");
			out.println("				obj1=document.Form1.elements[\"TXT_INVOICE_NO_\"+k].value;");
			out.println("				obj2=document.Form1.elements[\"TXT_DEBTOR_NAME_\"+k].value;");
			out.println("				if(parseFloat(unformat_noobject(obj))<=0){");
			out.println("					invoice_validation.innerHTML=\"Invoice Validation Started - Checking Invoice Amount - Error - \"+obj2+\" Invoice No:\"+obj1;");
			out.println("					m_flag=false;"); 
			out.println("				}");
			out.println("				else{");
			out.println("					invoice_validation.innerHTML=\"Invoice Validation Started - Checking Invoice Amount \";");
			out.println("				}");
			out.println("		}");
			out.println("		obj1_temp=document.Form1.elements[\"TXT_INVOICE_NO_1\"].value;");
			out.println("		obj3_temp=document.Form1.elements[\"TXT_DEBTOR_CODE_1\"].value;");
			out.println("		for(k=2;k<=parseInt(document.Form1.hid_invoice_count.value);k++){");
			out.println("				obj1=document.Form1.elements[\"TXT_INVOICE_NO_\"+k].value;");
			out.println("				obj2=document.Form1.elements[\"TXT_DEBTOR_NAME_\"+k].value;");
			out.println("				obj3=document.Form1.elements[\"TXT_DEBTOR_CODE_\"+k].value;");
			out.println("				if(obj1_temp==obj1){");
			out.println("						invoice_validation.innerHTML=\"Invoice Validation Started - Checking Invoice Nos - Error - \"+obj2+\" Invoice No:\"+obj1;");
			out.println("						m_flag=false;"); 
			out.println("				}");
			out.println("				else{");
			out.println("					invoice_validation.innerHTML=\"Invoice Validation Started - Checking Invoice Nos \";");
			out.println("					obj1_temp=document.Form1.elements[\"TXT_INVOICE_NO_\"+k].value;");
			out.println("				}"); 
			out.println("		}");
			out.println("	}");
			out.println("	if(m_flag){");
			out.println("		invoice_validation.innerHTML=\"Invoice Validation Successfull\";");
			out.println("		return true;");
			out.println("	}");
			out.println("	else{"); 
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("}"); 			  
			
			out.println("function validate_invoice_data(m_new_invoice_count){"); 
			out.println("	if(document.Form1.TXT_DEBTOR_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_DEBTOR_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			
//================================== edit by indika 03/09/08 =======================================================
			
			//out.println("	else if(document.Form1.TXT_SERIAL_NO.value==\"\" ){  "); 
			//out.println("		DIV_TXT_SERIAL_NO.style.color='red';");
			//out.println("		return false;"); 
			//out.println("	}");
			
			//out.println("	else if(document.Form1.TXT_REFERENCE_NO.value==\"\" ){  "); 
			//out.println("		DIV_TXT_REFERENCE_NO.style.color='red';");
			//out.println("		return false;"); 
			//out.println("	}");
			
//================================== end by indika 03/09/08 =========================================================
			
			out.println("	else if(document.Form1.TXT_INVOICE_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_INVOICE_DD.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_INVOICE_MM.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_INVOICE_YY.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_INVOICE_AMOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_AMOUNT.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else {"); 
			out.println("	    for (var i = 1; i < m_new_invoice_count; i++) {"); 
			out.println("	        var invoice_in_list = document.Form1.elements['TXT_INVOICE_NO_' + i].value;"); 
			out.println("	        var new_invoice = document.Form1.elements['TXT_INVOICE_NO'].value;"); 
			out.println("	        if (new_invoice == invoice_in_list) {"); 
			out.println("	            alert('Invoice no. exists in the current list.');"); 
			out.println("	            document.Form1.elements['TXT_INVOICE_NO'].select();"); 
			out.println("	            return false;"); 
			out.println("	        }"); 
			out.println("	    }"); 
			out.println("	}"); 
			// out.println("	else{");
			out.println("		return true;");
			// out.println("	}");
			out.println("}"); 
			
			// Modifed by Thamali Jayatunga on 2009.10.19, Added if(validate_data())
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println(" if (validate_date()){");
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_invoice_enter';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function create_debtor(){	"); 
			out.println("		if(confirm(\"Are you sure you want to create new Debtor ?\")){ "); 
			out.println("		window.open(\""+m_class_url+"/"+m_fschema_name+"FA_CR_display_client_creation\",\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700\");");
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_enter';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_invoice_enter';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_INVOICE_ENTER\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Operation Process - Invoice Entry - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Operation Process - Invoice Entry - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("	if(confirm(\"Are You Sure\")){ ");
			out.println("		if(m_val==\"NEW\"){"); 
			out.println("			new_window();"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("		if(m_val==\"NEW\"){");
			out.println("			document.Form1.hid_status.value=\"New\";"); 
			out.println("		}");
			out.println("		else if(m_val==\"EDIT\"){");  
			out.println("			document.Form1.hid_status.value=\"Edit\";");  
			out.println("		}");
			out.println("		else if(m_val==\"DACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("		}");
			out.println("		else if(m_val==\"RACT\"){");  
			out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("		}");
			out.println("		else{");  
			out.println("			document.Form1.hid_status.value=\"\";");  
			out.println("		}"); 
			out.println("	}"); 
			out.println("}"); 
			   
			out.println("function get_display_msg(){"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("		m_sav_msg=\"Save\";"); 
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
			out.println("		m_sav_msg=\"Modify\";");  
			out.println("	}");
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
			out.println("		m_sav_msg=\"Deactivate\";");  
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

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_1();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_3();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_update_value_4();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("						help_update_value_5();"); 
	  	out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("						help_update_value_6();"); 
	  	out.println("					}"); 
		    out.println("					else if(document.Form1.hid_help_type.value==\"11\"){"); 
			out.println("						help_update_value_11();"); 
	  	    out.println("					}"); 
			out.println("				}"); 
			out.println("				else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 
			out.println("			}"); 
			out.println("			else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("			}	"); 
			out.println("	 	}"); 
			out.println("	}"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("		HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function makeRequest_update_invoice_detail() {");
			out.println("	  document.Form1.hid_option.value=\"3\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_PRO_sql_validations_normal?chksql=EDIT_BATCH_NO&batch_no=\"+document.Form1.TXT_INVOICE_BATCH_NO.value+\"&serial_no='000007'\";");
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_PRO_sql_validations_normal?chksql=EDIT_BATCH_NO&batch_no=\"+document.Form1.TXT_INVOICE_BATCH_NO.value+\";");
			//out.println("		window.open(m_url);");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function help_update_facility() {"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println("	}");
			out.println("	else{");
			out.println(" 	document.Form1.hid_help_type.value=\"6\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_ENTER_EDIT_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println("	}");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			
			out.println("function help_update_dispute_code() {"); 
			//out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"11\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DISPUTE_CODE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\";");
			//out.println("	}");
			//out.println("	else{");
			//out.println(" 	document.Form1.hid_help_type.value=\"66\";"); 
			//out.println(" 	m_sql = \"m_help_DIV_TXT_DISPUTE_INVOICE_ENTER_EDIT_sql\";"); 
			//out.println("	 	m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\";");
			//out.println("	}");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}");  	
			
			out.println("function help_update_batch(){");
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_BATCH_EDIT_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_INVOICE_BATCH_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_5() {"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_AMOUNT.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_INVOICE_COUNT.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_DATE_DD.value=oBj.valout[5].substring(0,2);"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_DATE_MM.value=oBj.valout[5].substring(3,5);"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_DATE_YY.value=oBj.valout[5].substring(6,10);"); 
			out.println("		format_num(document.Form1.TXT_INVOICE_BATCH_AMOUNT,4);");
			out.println("		document.Form1.hid_invoice_count.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_BATCH_NO.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_BATCH_SERIAL_NO.value=oBj.valout[7];");//add by indika on 04/09/08 
			out.println("		makeRequest_update_invoice_detail();");
			out.println("}");
			
			out.println("function help_update_value_6() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_TOT_CREDIT_LIMIT.value=oBj.valout[6];"); 
			out.println("		format_num(document.Form1.TXT_TOT_CREDIT_LIMIT,4);");
			out.println("		document.Form1.BUT_HELP_MAIN_2.disabled=false;");
			out.println("		document.Form1.TXT_INVOICE_BATCH_NO.disabled=false;");
			out.println("}");
			
			out.println("function help_update_value_11() { ");
			out.println("		document.Form1.TXT_DISPUTE_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_FACILITY_MANAGER_NAME.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_TOT_CREDIT_LIMIT.value=oBj.valout[6];"); 
			out.println("		format_num(document.Form1.TXT_TOT_CREDIT_LIMIT,4);");
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_2() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_update_bank() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_INVOICE_BANK_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_CHEQUE_BANK.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_CHEQUE_BANK.value=oBj.valout[2];"); 
			out.println("}");
			out.println("function help_update_branch() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_INVOICE_BRANCH_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_CHEQUE_BANK.value+\"@\"+document.Form1.TXT_CHEQUE_BRANCH.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_CHEQUE_BRANCH.value=oBj.valout[2];"); 
			out.println("}");

			out.println("function add_invoice_batch_list() {"); 
			out.println("		if(document.Form1.hid_status.value!=\"Edit\"){");  
			out.println("			get_check_invoice_no();");
			out.println("			if(confirm(\"Are you sure you want to add this Invoice?\")){"); 
			out.println("				add_invoice();");
			out.println("			}");
			out.println("			else{");
			out.println("				//clear_debtor();");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function create_debtor(){	"); 
			out.println("	if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("   alert('Faciliy No cannot be empty ?');");
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("   alert('Client Code cannot be empty ?');");
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else { ");
			out.println("   if(confirm(\"Are you sure you want to create new Debtor ?\")){ "); 
			out.println("   m_url = \""+m_class_url+"/"+m_fschema_name+"FA_OP_display_debtor_creation?facility_no=\"+document.Form1.TXT_FACILITY_NO.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"\";");
			out.println("		window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=900\");");
			out.println("		}"); 
			out.println(" }"); 
			out.println("}"); 
			
			//Added by Mahela on 27-12-2006
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_INVOICE_BATCH_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_INVOICE_BATCH_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_INVOICE_BATCH_DATE_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 	
			out.println("     v_dd = val.substr(0,val.indexOf('-'))");
			out.println("     if(v_dd.length <2) ");
			out.println("     v_dd = 0+v_dd ");
			out.println("     val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("     v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("     if(v_mm.length <2) ");
			out.println("     v_mm = 0+v_mm ");
			out.println("     v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_INVOICE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_INVOICE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_INVOICE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");				
			
			
			// Modifed by Thamali Jayatunga on 2009.10.19, Added function validate_date 
		  out.println("function validate_date(){");
	    out.println("  m_from_dd = document.Form1.TXT_INVOICE_DD.value ");
	    out.println("  m_from_mm = document.Form1.TXT_INVOICE_MM.value ");
	    out.println("  m_from_yy = document.Form1.TXT_INVOICE_YY.value ");
	    out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
	    out.println("    if(!checkMonthLength(document.Form1.TXT_INVOICE_DD,document.Form1.TXT_INVOICE_MM,document.Form1.TXT_INVOICE_YY)){  "); 
	    out.println("       return false;"); 
	    out.println("    }");
	    out.println("    else {");
	    out.println("         return true; "); 
	    out.println("    }");
	    out.println(" }");
	    out.println(" else {");
	    out.println("     alert(' Date cannot be Empty ')");
			out.println("return false;");
	    out.println(" }");
	    out.println("}");

			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();get_system_date();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Invoice Entry </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='10' size='10' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code	</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FACILITY_MANAGER_NAME'  class=div_input>Facility Manager Name*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_MANAGER_NAME' maxlength='50' size='50'  style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_TOT_CREDIT_LIMIT'  class=div_input>Total Facility Credit Limit</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOT_CREDIT_LIMIT' maxlength='25' size='50' style='width:200' onchange=format_num(document.Form1.TXT_TOT_CREDIT_LIMIT,4) disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_BATCH_NO'  class=div_input>Invoice Batch No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_BATCH_NO' maxlength='10' size='10' onblur=\"help_update_batch()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' value=\"Help\" onClick=\"help_update_batch()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_UPLOAD' value=\"Upload Invoices\" onClick=\"help_upload_invoices()\"  style='width:100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_BATCH_NO'  class=div_input>Client Refrence Batch No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_BATCH_NO' maxlength='25' size='50' style='width:200'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			
//================================================================= add by indika 04/09/08 ===============================================================================
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_BATCH_SERIAL_NO'  class=div_input>Batch Serial No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BATCH_SERIAL_NO' maxlength='25' size='50' style='width:200'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
//================================================================= end of addin by indika =================================================================================

			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_BATCH_AMOUNT'  class=div_input>Total Batch Amount Rs.</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_BATCH_AMOUNT' maxlength='25' size='50' style='width:200;text-align:right;'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_COUNT'  class=div_input>No of Invoices</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_COUNT' maxlength='25' size='50' style='width:100;text-align:right;'  disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_BATCH_DATE\" class=div_input>Invoice Batch Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_BATCH_DATE_DD\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_BATCH_DATE_MM\" maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_BATCH_DATE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_BATCH_DATE_DD,document.Form1.TXT_INVOICE_BATCH_DATE_MM,document.Form1.TXT_INVOICE_BATCH_DATE_YY)\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"1\")>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("</table>");
    
			out.println("<hr>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='50%' style='font-family: Tahoma, Arial, Helvetica;font-size: 14px;color: #FF0000;'><b><div id=\"invoice_batch_count\"></b></td>");
			out.println("<td width='50%' style='font-family: Tahoma, Arial, Helvetica;font-size: 14px;color: #FF0000;'><b><div id=\"invoice_batch_total\"></b></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DEBTOR_CODE'  class=div_input>Debtor Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_CODE' maxlength='10' size='10'  onblur=\"get_debtor_name()\" tabindex=\"1\">"); 
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Help\" onClick=\"help_update_debtor()\">");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_DEBTOR_CODE.value)\">");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_CLEAR' value=\"Clear\" onClick=\"clear_debtor()\">");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_CREATION' value=\"Create Debtor\" style='width:75' onClick=\"create_debtor()\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DEBTOR_DESC'  class=div_input>Debtor Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='25' size='50' style='width:150'  tabindex=\"2\" onblur=\"get_check_invoice_no()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	

//========================================================= Add by Indika 03/09/08 ===========================================================================================
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REFERENCE_NO'  class=div_input>Reference No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REFERENCE_NO' maxlength='25' size='50' tabindex=\"3\" style='width:150' /*onblur=\"get_check_invoice_no()*/\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			//out.println("<tr >"); 
			//out.println("<td width='15%' ><DIV id='DIV_TXT_SERIAL_NO'  class=div_input>Serial No *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SERIAL_NO' maxlength='25' size='50' style='width:150' /*onblur=\"get_check_invoice_no()*/\"></td>"); 
			//out.println("<td width='*%'></td>"); 
			//out.println("</tr>");
			
//=================================================== End of adding by Indika 03/09/08 =======================================================================================
			
			out.println("<tr >");
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_DATE\" class=div_input>Invoice Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_DD\" maxlength=\"2\" size=\"2\"  tabindex=\"4\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_MM\" maxlength=\"2\" size=\"2\"  tabindex=\"4\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_DD,document.Form1.TXT_INVOICE_MM,document.Form1.TXT_INVOICE_YY)\"  tabindex=\"5\">[DD-MM-YYYY] <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"2\")>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_AMOUNT'  class=div_input>Invoice Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_AMOUNT' maxlength='25' size='50' style='width:150;text-align:right;'   value=\"0\" /*onchange=\"get_rpt_currency_amt();format_num(document.Form1.TXT_INVOICE_AMOUNT,4);add_invoice_batch_list()\"*/  onblur=\"get_rpt_currency_amt();format_num(document.Form1.TXT_INVOICE_AMOUNT,4);add_invoice_batch_list()\" tabindex=\"5\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_CURRENCY'  class=div_input>Currency *</DIV></td>");
			out.println("<td width='40%' ><SELECT onchange=\"get_exchange_rate()\" name=\"TXT_CURRENCY_CODE\" class=\"txt_input\" > ");
			
			rs = stmt.executeQuery ("SELECT CURR_CODE,CURR_SYMBOL,REP_CURR FROM "+m_schema_name+".AF_CO_MAS_CURRENCY ORDER  BY DEFAULT_VALUE DESC ");
			while(rs.next()){
			out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' maxlength='25' size='50' style='width:150;text-align:right;'  value=\"0\" onchange=\"format_num(document.Form1.TXT_EXCHANGE_RATE,4)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_RPT_CURR_AMT'  class=div_input>Reporting Currency Amount </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_RPT_CURR_AMT' maxlength='25' size='50' style='width:150;text-align:right;' value=\"0\" onchange=\"format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4)\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_COMMENTS'  class=div_input>Comments </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_COMMENTS' maxlength='200' size='200' style='width:200' >");
			out.println("<input class='txt_input' type='hidden' name='TXT_INVOICE_SEQ_NO' maxlength='200' size='200' style='width:200' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DISPUTE_CODE'  class=div_input>Dispute Code</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DISPUTE_CODE' maxlength='25' size='50' style='width:100;text-align:right;'  >");
		    out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"help_update_dispute_code()\"></td>");//help_update_facility
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DISPUTE_AMOUNT'  class=div_input>Dispute Amount</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' onblur='format_num(document.Form1.TXT_DISPUTE_AMOUNT,4)' name='TXT_DISPUTE_AMOUNT' maxlength='25' size='50' style='width:100;text-align:right;'  ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
									
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><input class=\"but_input\" type=\"button\" name=\"BUT_HELP_INVOICE_MAIN_ADD\" value=\"Add\" onClick=\"add_invoice()\"></td>"); 
			out.println("<td width='40%' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
						
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='invoice_validation'  class=div_input style='color:red'></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
									
			out.println("<hr>"); 
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%' class=div_input><b>Entered Invoice List</b></td>");
			out.println("</tr>");
			out.println("</table>"); 

			out.println("<br>"); 
			/*out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='15%' class=div_input><b>Debtor Code</b></td>");
			out.println("<td width='20%' class=div_input><b>Name</b></td>");
			out.println("<td width='10%' class=div_input><b>Invoice No</b></td>");
			out.println("<td width='15%' class=div_input><b>Amount Rs.</b></td>");
			out.println("<td width='*%' class=div_input></td>");
			out.println("</tr>");
			out.println("</table>"); */
			
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='invoice_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 

			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
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
