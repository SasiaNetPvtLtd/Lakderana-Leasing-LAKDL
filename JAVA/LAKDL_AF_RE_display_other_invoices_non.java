//--
//SCREEN NAME:COLLECTION - OTHER INVOICES
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME: 2006/11/13
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_display_other_invoices_non extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs3;
	Statement stmt,stmt1;
	Connection conn;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			out = res.getOutputStream(); 
			
			String m_inv_type="";
			String m_payee="";
			String m_payee_name="";
			
			m_inv_type = req.getParameter("inv_type");
			m_payee    = req.getParameter("payee");
			
			if(m_inv_type==null && m_payee==null){
				m_inv_type = "";
				m_payee    = "";
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoicing - Debit Note </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function val_rate(obj) {");
			out.println("	if(isNaN(obj)) {");
			out.println("		alert('You have typed an incorrect character as a number');");
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value='';");
			out.println("		document.Form1.TXT_EXCHANGE_RATE.focus();");
			out.println("	}");
			out.println("}");
			out.println("function get_vector(data_vec) {");
			
			// commented by udara 30-07-2014
			/*
			out.println("			if(data_vec.length>0 && document.Form1.hid_help_status.value==\"ex_rate\"){");
			//out.println("				alert('Record already exsist');");
			out.println("				document.Form1.TXT_EXCHANGE_RATE.value=data_vec[0];"); 	
			out.println("			}");
			*/
			
			// added by udara 30-07-2014
			out.println("			if(document.Form1.hid_help_status.value==\"ex_rate\"){");
			out.println("				if(data_vec.length>0 && document.Form1.hid_help_status.value==\"ex_rate\"){");
			out.println("					document.Form1.TXT_EXCHANGE_RATE.value=data_vec[0];"); 		
			out.println("				}");	
			out.println("               Onload_payee(); ");
			out.println("			}");
			// added by udara 30-07-2014
			
			// added by udara on 04-07-2013
			out.println("			else if((data_vec.length>0) && (document.Form1.hid_help_status.value==\"check_premium\") ){");
			//out.println("			   alert(data_vec[0]); ");
			//out.println("              document.Form1.hid_prem_check.value = data_vec[0];  ");
			/*
			out.println("                if((data_vec[0]=='0') || (document.Form1.TXT_INVOICE_TYPE.value!=\"INSURANCE\") ){ ");
			out.println("                     before_submit();"); 
			out.println("			     }");
			out.println("                else{ ");
			out.println("                	  alert('This finance no. has entered as Insurance Premium type');  ");
			out.println("			     }");
			*/
			
			out.println("                if((data_vec[0]=='1') && (document.Form1.TXT_INVOICE_TYPE.value==\"INSURANCE\") ){ ");
			out.println("                	  alert('This contract number has outstanding insurance');  ");
			out.println("			     }");
			out.println("                else{ ");
			out.println("                     before_submit();"); 
			out.println("			     }");
			out.println("			}");
			// added by udara on 04-07-2013
			
			// added by udara on 05-07-2013
			out.println("else if(data_vec.length>0 && document.Form1.hid_help_status.value=='check_app_status' ){");
			//out.println("     alert(data_vec[0]); ");	
			out.println("     if(data_vec[0]=='TERMI') ");	
			out.println("        alert('This is a terminated contract and cannot proceed'); ");
			out.println("     else ");
			out.println("        save_window(); ");
			out.println("}");
			// end by udara on 05-07-2013
			
			
			out.println("			else if(data_vec.length==0 && document.Form1.hid_help_status.value==\"ex_rate\"){");
			out.println("				document.Form1.TXT_EXCHANGE_RATE.value=0;"); 	
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.hid_help_status.value==\"invoice_no\" && document.Form1.TXT_INVOICE_NO.value != \"\" ){");
			out.println("				assign_invoice(data_vec);"); 	
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.hid_help_status.value==\"invoice_no\" && document.Form1.TXT_INVOICE_NO.value != \"\" ){");
			out.println("				help_update();"); 	
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.hid_help_status.value==\"finance_no\" && document.Form1.TXT_FINANCE_NO.value != \"\" ){");
			out.println("				assign_finance(data_vec);"); 	
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.hid_help_status.value==\"finance_no\" && document.Form1.TXT_FINANCE_NO.value != \"\" ){");
			out.println("				help_finance();"); 	
			out.println("			}");
			
			out.println("			else if(document.Form1.hid_help_status.value==\"sub_charge_type\" ){");
			out.println("				display_account_type(data_vec);"); 
			//added by kanishka dilshan on 29-05-2013
			out.println("				Check_due_date(document.Form1.TXT_INVOICE_TYPE,document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);"); 
			//end kanishka dilshan on 29-05-2013
			out.println("			}");
				//kanishka
			out.println("			else if(document.Form1.hid_help_status.value==\"get_due_date\" ){");
			out.println("    			m_due_date=data_vec[0]; ");
			out.println(" 				document.Form1.TXT_DUE_DATE_DD.value = m_due_date.substring(0,2);");
			out.println(" 				document.Form1.TXT_DUE_DATE_MM.value = m_due_date.substring(3,5);");
			out.println(" 				document.Form1.TXT_DUE_DATE_YY.value = m_due_date.substring(6,10);");
			out.println("			}");
			
			out.println("}");
			//Added by Dineth on 2008-12-10
			out.println("function validate_date(obj3,obj2,obj1){");
			//out.println(" if(document.Form1.TXT_INVOICE_TYPE.value != 'INSURANCE'){ "); // modified by udara on 13-03-2013
			out.println(" if(obj1==document.Form1.TXT_VALUE_DATE_YY && obj2==document.Form1.TXT_VALUE_DATE_MM && obj3==document.Form1.TXT_VALUE_DATE_DD){");
			out.println(" if(obj1.value>document.Form1.hid_cur_yy.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value=document.Form1.hid_cur_yy.value;");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value=document.Form1.hid_cur_mm.value;");
			out.println(" document.Form1.TXT_DUE_DATE_DD.value=document.Form1.hid_cur_dd.value;");
			out.println(" }");
			out.println(" else if(obj1.value==document.Form1.hid_cur_yy.value && obj2.value>document.Form1.hid_cur_mm.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value=document.Form1.hid_cur_yy.value;");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value=document.Form1.hid_cur_mm.value;");
			out.println(" document.Form1.TXT_DUE_DATE_DD.value=document.Form1.hid_cur_dd.value;");
			
			out.println(" }");
			out.println(" else if(obj1.value==document.Form1.hid_cur_yy.value && obj2.value==document.Form1.hid_cur_mm.value && obj3.value>document.Form1.hid_cur_dd.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value=document.Form1.hid_cur_yy.value;");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value=document.Form1.hid_cur_mm.value;");
			out.println(" document.Form1.TXT_DUE_DATE_DD.value=document.Form1.hid_cur_dd.value;");
			
			out.println(" }");
			out.println(" }");
			out.println(" else if(obj1==document.Form1.TXT_DUE_DATE_YY && obj2==document.Form1.TXT_DUE_DATE_MM && obj3==document.Form1.TXT_DUE_DATE_DD){");
			out.println(" if(obj1.value>document.Form1.hid_cur_yy.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" }");
			out.println(" else if(obj1.value==document.Form1.hid_cur_yy.value && obj2.value>document.Form1.hid_cur_mm.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" }");
			out.println(" else if(obj1.value==document.Form1.hid_cur_yy.value && obj2.value==document.Form1.hid_cur_mm.value && obj3.value>document.Form1.hid_cur_dd.value){");
			out.println(" alert('cannot exceed current date');");
			out.println(" obj1.value=document.Form1.hid_cur_yy.value;");
			out.println(" obj2.value=document.Form1.hid_cur_mm.value;");
			out.println(" obj3.value=document.Form1.hid_cur_dd.value;");
			out.println(" }");
			
			out.println(" }");
			
			//out.println("  }");	// close by udara on 13-03-2013
			
			out.println(" }");
			//Added by Dineth on 2008-12-10
			out.println("function Change_Type(obj){");
			out.println(" document.Form1.hid_help_status.value = 'sub_charge_type'; ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_sub_charge_account_type&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}"); 
			
			String mm_date="";
			//Added by Kanishka dilshan on 27-05-2013
			out.println("function Check_due_date(obj,dd,mm,yy){");
			out.println(" var m_date=dd.value+\"-\"+mm.value+\"-\"+yy.value; ");
			out.println(" document.Form1.hid_help_status.value = 'get_due_date'; ");
		//	out.println(" alert(m_date);");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_insurance_due_date&date=\"+m_date+\"&type=\"+obj.value;");
			out.println(" load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function sub_charge_reference_code_help() {"); 
			out.println("    document.Form1.hid_help_type.value=\"7\";");//Added by Dineth on 01-06-2009
			//out.println("    Crit = document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    Crit =document.Form1.TXT_PAYEE_CODE.value+\"@\"+document.Form1.TXT_INVOICE_TYPE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_sub_charge_payee_code','7');"); 
			out.println("}"); 
			
			out.println("function assign_sub_charge_reference_code_help() {"); 
			out.println("document.Form1.TXT_PAYEE_CODE.value=oBj.valout[2];");
			out.println("document.Form1.TXT_PAYEE_NAME.value=oBj.valout[4];");
			out.println("}"); 
			
			
			out.println("function display_account_type(data_vec){");
			out.println("if(data_vec[0]=='L'){");
			out.println("m_table_invoice_type.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" >'+");		
			out.println("'<tr>'+");
			//out.println("'<TD WIDTH=\"20%\"  align=\"left\">Payee Code *</TD>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><DIV id=\"DIV_TXT_PAYEE_CODE\"  class=div_input>Payee Code *</DIV></TD>'+"); // added by udara 30-07-2014
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_CODE maxlength=\"10\" size=\"10\" style=\"{width:150px;}\" value=\"\" onblur=\"sub_charge_reference_code_help()\"   >'+");//
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_PAYEE_CODE  value=\"Help\" onClick=sub_charge_reference_code_help()></TD>'+");
			out.println("'<td  >Payee Name</td>'+");
			out.println("'<td  ><input name=\"TXT_PAYEE_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\"  disabled></td>'+");
			out.println("'</tr>'+");
			
			out.println("'</table>';");
			out.println("load_payee_deta();");//Added By Sandun on 24-12-2008
			out.println("}");
			out.println("else if(data_vec[0]=='I'){");
			out.println("m_table_invoice_type.innerHTML=\"\"; ");
			out.println("}");	
		
			// added by udara 27-11-2015
			out.println("else if(data_vec[0]=='D'){");
			out.println("   m_table_invoice_type.innerHTML=\"\"; ");
			out.println("}");
			out.println("else if(data_vec[0]=='E'){");
			out.println("   m_table_invoice_type.innerHTML=\"\"; ");
			out.println("}");
			// end by udara 27-11-2015
		
		
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println(" document.Form1.hid_help_status.value = 'invoice_no'; ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_invoice_no&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest2(obj) {");
			out.println(" document.Form1.hid_help_status.value = 'finance_no'; ");
		  //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_finance_no&data_val=\"+obj.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_get_finance_no_not_in_termination&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function get_excharate() {");
			out.println(" document.Form1.hid_help_status.value = 'ex_rate'; ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=get_excharate&CURR_CODE=\"+document.Form1.TXT_CURRENCY_CODE.value+\"&VAL_DATE=\"+document.Form1.TXT_VALUE_DATE_DD.value+\"-\"+document.Form1.TXT_VALUE_DATE_MM.value+\"-\"+document.Form1.TXT_VALUE_DATE_YY.value;");
			//out.println("   window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			// added by udara on 04-07-2013
			out.println("function get_check_premium() {");
			out.println("   document.Form1.hid_help_status.value = 'check_premium'; ");
			out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=check_premium&FINANCE_NO=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("   load_interface(m_url,'XML');");
			out.println("}");
			// end by udara on 04-07-2013
			
			// added by udara 05-07-2013
			out.println("function check_app_status(){ ");
			
			out.println("    if(document.Form1.TXT_FINANCE_NO.value==''){");
			out.println("      alert('Please enter the contract number.'); ");
			out.println("    }");
			out.println("    else{");
			out.println("       document.Form1.hid_help_status.value='check_app_status' ");
			out.println("       m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_sql_validations?chksql=check_app_status&FINANCE_NO=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("	    load_interface(m_url,'XML');");
			out.println("    }");
			out.println("}");
			// added by udara 05-07-2013
			
			
			out.println("function befor_clear(){");
			out.println("    document.Form1.TXT_INVOICE_NO.value='';"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value='';"); 
			out.println("    document.Form1.TXT_NET_AMOUNT.value='';"); 
			out.println("    document.Form1.TXT_VAT_AMOUNT.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value='';");
			out.println("    document.Form1.TXT_DUE_DATE_DD.value='';"); 
			out.println("    document.Form1.TXT_DUE_DATE_MM.value='';"); 
			out.println("    document.Form1.TXT_DUE_DATE_YY.value='';"); 
			//out.println("    document.Form1.TXT_BALANCE_TO_BE_RECEIVED.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("    document.Form1.TXT_REMARKS.value='';"); 
			out.println(" 	 load_sys_date();	");
			//out.println("    document.Form1.TXT_CURRENCY_CODE.value=data_vec[9];"); 
			//out.println("    document.Form1.TXT_EXCHANGE_RATE.value=data_vec[10];"); 
			//out.println("    document.Form1.TXT_INVOICE_TYPE.value=data_vec[11];");	
			out.println("}");
			
			out.println("function assign_invoice(data_vec) {");
			out.println("    document.Form1.TXT_INVOICE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[1];"); 
			out.println("    assign_value_date(data_vec[2])");
			//out.println("    document.Form1.TXT_VALUE_DATE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_NET_AMOUNT.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_VAT_AMOUNT.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=data_vec[5];"); 
			out.println("    assign_due_date(data_vec[6])");
			//out.println("    document.Form1.TXT_DUE_DATE.value=data_vec[8];"); 
			//out.println("    document.Form1.TXT_BALANCE_TO_BE_RECEIVED.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_REMARKS.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_CURRENCY_CODE.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_INVOICE_TYPE.value=data_vec[11];");			
			out.println("}");
			
			out.println("function assign_finance(data_vec) {");
			//out.println("    document.Form1.TXT_INVOICE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_CURRENCY_CODE.value=data_vec[2];"); 	
			out.println("    document.Form1.TXT_CLIENT_NAME.value=data_vec[3];"); 	
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println(" m_sub =0;");
			
			// added by udara 30-07-2014
			
			out.println("if(document.Form1.TXT_INVOICE_TYPE.value==\"INSURANC\"){  "); 	
			out.println("   if(document.Form1.TXT_PAYEE_CODE.value==\"\"){  "); 	
			out.println("      DIV_TXT_PAYEE_CODE.style.color='red';");
			out.println("      m_sub =1;");
			out.println("   }");
			out.println("}");
			
			// end by udara 30-07-2014
			
			
			out.println("if(document.Form1.TXT_INVOICE_NO.value==\"\" && document.Form1.SCREEN_NAME.value != \"NEW\" ){  "); 	
			out.println("DIV_TXT_INVOICE_NO.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}");
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_VALUE_DATE_DD.value==\"\" || document.Form1.TXT_VALUE_DATE_MM.value==\"\" || document.Form1.TXT_VALUE_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_VALUE_DATE.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_NET_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_NET_AMOUNT.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_VAT_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_VAT_AMOUNT.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_TOTAL_AMOUNT.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_AMOUNT.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_DUE_DATE_DD.value==\"\" || document.Form1.TXT_DUE_DATE_MM.value==\"\" || document.Form1.TXT_DUE_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_DUE_DATE.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			out.println(" if(document.Form1.TXT_EXCHANGE_RATE.value==\"\"){  "); 
			out.println("DIV_TXT_EXCHANGE_RATE.style.color='red';");
			out.println(" m_sub =1;");
			//out.println("return false;"); 
			out.println("}"); 
			//	out.println(" if(!check_dates()) { ");
			//	out.println(" m_sub =1;");
			//	out.println("}"); 
			
			out.println("if(m_sub==1 ){ ");
			out.println(" return false;"); 
			out.println("}"); 
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			
			out.println("   document.Form1.save_button.disabled = true; "); // added by udara 14-06-2018
			
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Edit\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"Delete\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("   }"); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_save_msg)){ ");
		
			// commented by udara 12-11-2013
			//out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("		document.Form1.elements[i].disabled=false;");
			//out.println("		}");
			
			
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_other_invoices';");      // commented by udara on 01-10-2013
			
			// commented by udara on 11-11-2013
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_other_invoices_non';");  // added by udara on 01-10-2013
			//out.println("		document.Form1.submit();	"); 
			
			// added by udara 11-11-2013
			out.println("       m_val_msg = 'Please recheck the due date'; ");
			out.println("		if(confirm(m_val_msg)){ "); 
			
			// added by udara 12-11-2013
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			
			out.println("          document.Form1.save_button.disabled = true; "); // added by udara 14-06-2018
			
			
			out.println("		   document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_other_invoices_non';");  // added by udara on 01-10-2013
			out.println("		   document.Form1.submit();	"); 
			out.println("		}");
			// end by udara 11-11-2013
			
			out.println("		}"); 
			
			// added by udara 14-06-2018
			out.println("		else{");
			out.println("          document.Form1.save_button.disabled = false; ");
			out.println("		}"); 
			// end by udara 14-06-2018
			
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			
			out.println("function load_calendar(num) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_VALUE_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_VALUE_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_VALUE_DATE_YY.value=v_yy;");
			
			out.println("     document.Form1.TXT_DUE_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_DUE_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_DUE_DATE_YY.value=v_yy;");
			out.println(" Check_due_date(document.Form1.TXT_INVOICE_TYPE,document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY); ");//kanishka
			//Added by Dineth on 2008-12-10
			out.println("      validate_date(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);");
			//End by Dineth on 2008-12-10
			//out.println("  checkMonthLength(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY)");
			out.println("  }");		
			out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 	
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_DUE_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_DUE_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_DUE_DATE_YY.value=v_yy;");

			//Added by Dineth on 2008-12-10
			//out.println("      validate_date(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)");
			//End by Dineth on 2008-12-10
			//out.println("  checkMonthLength(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)");
			out.println("  }");		
			out.println("}");				
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_other_invoices_non';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_other_invoices_non';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("get_check_premium(); ");
			//out.println(" if(document.Form1.hid_prem_check.value!='1') ");
			//out.println("   before_submit();"); 
			//out.println(" else ");
			//out.println("  alert('Insurance Premium Type available');");
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Other_invoices\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Invoicing - Debit Note - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Invoicing - Debit Note - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 			
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" document.Form1.BUT_HELP_MAIN.disabled=true; ");
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println(" new_window();"); 
			out.println(" }"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val==\"DEL\"){"); 
			out.println(" if(confirm(\"Are you sure you want to  Delete a record?\")){  ");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_VALUE_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_NET_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_DUE_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_DUE_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_DUE_DATE_YY.disabled=true;"); 
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_REMARKS.disabled=true;"); 
			out.println("document.Form1.TXT_CURRENCY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_EXCHANGE_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_INVOICE_TYPE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_FINANCE_NO.disabled=true;");
			out.println("befor_clear();");
			out.println(" }"); 
			out.println("}"); 
			
			/*out.println("else if(m_val==\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println(" befor_clear();");
			out.println(" }"); 
			out.println("}"); */
			
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;"); 
			out.println(" befor_clear();");
			out.println(" }"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.TXT_INVOICE_NO.disabled=false;"); 
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
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		} else "); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		invoice_assign(oBj);"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		receipt_assign(oBj);"); 
			out.println("		}");
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		account_assign(oBj);"); 
			out.println("		}");
			out.println("		if(IfCount==\"5\"){"); 
			out.println("		lease_assign(oBj);"); 
			out.println("		}");
			out.println("		if(IfCount==\"6\"){"); 
			out.println("		term_assign(oBj);"); 
			out.println("		}");
			out.println("		if(IfCount==\"7\"){"); 
			out.println("		assign_sub_charge_reference_code_help(oBj);"); 
			out.println("		}");
			
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{");
			out.println("	clear_data();");
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
			out.println(""); 
			
			
			/*out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
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
			out.println(""); */
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			//out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_INVOICE_NO.value+\"@\"+\"Y@\";"); 
			//out.println("HelpBox('1','10','9',Crit,'InvoiceSql','1');");
			out.println("HelpBox('1','10','9',Crit,'InvoiceSql_debit_note','1');");
			//out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_finance() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			//out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
		  //out.println("    HelpBox('1','10','0',Crit,'FinanceSql','2');");
			out.println("    HelpBox('1','10','0',Crit,'FinanceSqlWithoutTermination','2');"); // Commented by : Samith
			//out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function clear_data(){");
			out.println(" if(document.Form1.hid_help_type.value==\"1\")");
			out.println("    document.Form1.TXT_INVOICE_NO.value='';"); 
			out.println(" else if(document.Form1.hid_help_type.value==\"2\")");
			out.println("    document.Form1.TXT_FINANCE_NO.value='';");
			out.println(" else if(document.Form1.hid_help_type.value==\"7\")");//Added by Dineth on 01-06-2009
			out.println("    document.Form1.TXT_PAYEE_CODE.value='';");//Added by Dineth on 01-06-2009
			out.println("}"); 
			
			out.println("function finance_assign(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_CURRENCY_CODE.value=oBj.valout[6];"); 
			out.println("}"); 
			
			out.println("function assign_value_date(obj){ ");
			out.println(" document.Form1.TXT_VALUE_DATE_DD.value = obj.substring(0,2)");
			out.println(" document.Form1.TXT_VALUE_DATE_MM.value = obj.substring(3,5)");
			out.println(" document.Form1.TXT_VALUE_DATE_YY.value = obj.substring(6,10)");
			
			//added by nuwan de silva on 04-12-2007______________________________________
			out.println(" document.Form1.TXT_DUE_DATE_DD.value = obj.substring(0,2)");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value = obj.substring(3,5)");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value = obj.substring(6,10)");
			//___________________________________________________________________________
			out.println("}"); 
			
			out.println("function load_sys_date(){ ");
			rs3 = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
				"FROM  DUAL ");
			//out.println(" m_sysdate = ''   ");													
			while(rs3.next()){
				out.println(" m_sysdate = '"+rs3.getString(1)+"'");			
			}
			out.println(" document.Form1.TXT_VALUE_DATE_DD.value = m_sysdate.substring(0,2)");
			out.println(" document.Form1.TXT_VALUE_DATE_MM.value = m_sysdate.substring(3,5)");
			out.println(" document.Form1.TXT_VALUE_DATE_YY.value = m_sysdate.substring(6,10)");
			
			//added by nuwan de silva on 04-12-2007_________________________________________
			out.println(" document.Form1.TXT_DUE_DATE_DD.value = m_sysdate.substring(0,2)");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value = m_sysdate.substring(3,5)");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value = m_sysdate.substring(6,10)");
			
			//Added by Dineth on 2008-12-10
			out.println("     document.Form1.hid_cur_dd.value=m_sysdate.substring(0,2);");
			out.println("     document.Form1.hid_cur_mm.value=m_sysdate.substring(3,5);");
			out.println("     document.Form1.hid_cur_yy.value=m_sysdate.substring(6,10);");
			
			//End by Dineth on 2008-12-10
			out.println("}"); 
			
			out.println("function assign_due_date(obj){ ");
			out.println(" document.Form1.TXT_DUE_DATE_DD.value = obj.substring(0,2)");
			out.println(" document.Form1.TXT_DUE_DATE_MM.value = obj.substring(3,5)");
			out.println(" document.Form1.TXT_DUE_DATE_YY.value = obj.substring(6,10)");
			//out.println(" checkMonthLength(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY);");
			out.println("}"); 
			
			out.println("function invoice_assign(oBj) {"); 
			
			out.println("    document.Form1.TXT_INVOICE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
			out.println("    assign_value_date(oBj.valout[4])");
			//out.println("    document.Form1.TXT_VALUE_DATE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_NET_AMOUNT.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_VAT_AMOUNT.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[7];"); 
			out.println("    assign_due_date(oBj.valout[8])");
			//out.println("    document.Form1.TXT_DUE_DATE.value=oBj.valout[8];"); 
			//out.println("    document.Form1.TXT_BALANCE_TO_BE_RECEIVED.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[10];"); 
			//out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[10];"); //Added by nuwan de silva on 04-12-2007
			out.println("    document.Form1.TXT_REMARKS.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_CURRENCY_CODE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_INVOICE_TYPE.value=oBj.valout[14];"); 
			out.println("    document.Form1.HID_SUS_REF_NO.value=oBj.valout[15];"); 
			out.println("}"); 
			
			out.println("function check_date_value(objDD,objMM,objYY){");
			out.println("if(objDD.value!='' && objMM.value!='' && objYY.value!='')");
			out.println("checkMonthLength(objDD,objMM,objYY)");
			out.println("}");				
			
			/*out.println("function check_dates(){ ");
			out.println("if(document.Form1.TXT_VALUE_DATE_DD.value!=\"\" || document.Form1.TXT_VALUE_DATE_MM.value!=\"\" || document.Form1.TXT_VALUE_DATE_YY.value !=\"\" ){  "); 
			out.println(" if(!checkMonthLength(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY)) {");
			out.println(" return false;"); 
			out.println(" }"); 
			out.println(" else if(document.Form1.TXT_DUE_DATE_DD.value!=\"\" || document.Form1.TXT_DUE_DATE_MM.value!=\"\" || document.Form1.TXT_DUE_DATE_YY.value!=\"\" ){  "); 
			out.println("   if(!checkMonthLength(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)){ ");
			out.println("   return false;"); 
			out.println("   }"); 
			out.println("   else {");
			out.println("   return true; }"); 
			out.println(" }"); 
			out.println(" else {");
			out.println("   return true; }"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DUE_DATE_DD.value!=\"\" || document.Form1.TXT_DUE_DATE_MM.value!=\"\" || document.Form1.TXT_DUE_DATE_YY.value!=\"\" ){  "); 
			out.println("  if(!checkMonthLength(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)){");
			out.println("  return false;"); 
			out.println("  }"); 
			out.println("  else {");
			out.println("  return true; }"); 
			out.println("}"); 
			out.println("else { ");
			out.println("  return true; }"); 
			out.println("}"); 
			*/
			
			out.println("function format_num() {");
			out.println("   document.Form1.TXT_BALANCE_TO_BE_RECEIVED.value=format_noobject(document.Form1.TXT_BALANCE_TO_BE_RECEIVED.value);");
			out.println("}"); 
			
			out.println("function cal_tot_amount(obj) {");
			out.println("   if(obj.value != '' && isnumberok(obj,21) ) {");
			out.println("   obj.value=format_noobject(obj.value); ");
			out.println("		}");
			out.println("		else if(obj.value != '') { ");
			out.println("   alert('Please enter a number ');");
			out.println("   obj.value='' ");
			out.println("   obj.focus(); ");
			out.println("		}");
			out.println("		m_tot = parseFloat(unformat_noobject(document.Form1.TXT_NET_AMOUNT.value)) + parseFloat(unformat_noobject(document.Form1.TXT_VAT_AMOUNT.value)) ");
			out.println("   if(!isNaN(m_tot)){ ");
			out.println(" 	document.Form1.TXT_TOTAL_AMOUNT.value = m_tot; ");
			out.println("   document.Form1.TXT_TOTAL_AMOUNT.value=format_noobject(document.Form1.TXT_TOTAL_AMOUNT.value);");
			out.println("		}");
			out.println("}");
			
			
			//=========added by nuwan de silva 22-05-07=============
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
			
			out.println("function Onload_payee(){");//Added By Sandun on 24-12-2008
			// commented by udara 21-10-2013
			/*
			out.println("m_payee    = '"+m_payee+"';");
			out.println("m_inv_type = '"+m_inv_type+"' ;");
			out.println("if(m_payee != \"\" && m_inv_type!= \"\"){");
			out.println("document.Form1.TXT_INVOICE_TYPE.value = '"+m_inv_type+"';");
			out.println("Change_Type(document.Form1.TXT_INVOICE_TYPE);");
			out.println("} ");
			*/
			
			// commented by udara 30-07-2014
			/*
			// added by udara 21-10-2013
			out.println("Change_Type(document.Form1.TXT_INVOICE_TYPE);");
			out.println("m_table_invoice_type.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" >'+");		
			out.println("'<tr>'+");
			out.println("'<TD WIDTH=\"20%\"  align=\"left\">Payee Code *</TD>'+");
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_CODE maxlength=\"10\" size=\"10\" style=\"{width:150px;}\" value=\"\" onblur=\"sub_charge_reference_code_help()\"   >'+");//
			out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_PAYEE_CODE  value=\"Help\" onClick=sub_charge_reference_code_help()></TD>'+");
			out.println("'<td  >Payee Name</td>'+");
			out.println("'<td  ><input name=\"TXT_PAYEE_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\"  disabled></td>'+");
			out.println("'</tr>'+");
			out.println("'</table>';");
			out.println("load_payee_deta();");
			// end by udara 21-10-2013
			*/
			
			out.println("Change_Type(document.Form1.TXT_INVOICE_TYPE);"); // added by udara 30-07-2014
			
			out.println("} ");
			
			out.println("function load_payee_deta(){");//Added By Sandun on 24-12-2008
			out.println("m_payee    = '"+m_payee+"';");
			out.println("m_inv_type = '"+m_inv_type+"' ;");
			out.println("if(m_payee != \"\" && m_inv_type!= \"\"){");			
			rs1 = stmt1.executeQuery ("SELECT PAYEE_NAME FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF WHERE PAYEE_CODE='"+m_payee+"'");
			if(rs1.next()){
				m_payee_name = rs1.getString(1);
			}
			out.println("document.Form1.TXT_PAYEE_CODE.value = '"+m_payee+"';");
			out.println("document.Form1.TXT_PAYEE_NAME.value = '"+m_payee_name+"';");
			out.println("get_excharate();");
			out.println("} ");
			out.println("} ");
			
			out.println("</script>"); 
			//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date(),load_lock(),get_excharate(),load_roll_value('New'),Onload_payee()\">"); 
			  //out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date(),load_lock(),get_excharate(),load_roll_value('New'),Onload_payee()\">");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date(),load_lock(),get_excharate(),load_roll_value('New')\">"); // added by udara 30-07-2014
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_prem_check' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_OTHER_INVOICES\">");  // added by nuwan de silva on 05-12-2007
			out.println("<INPUT TYPE='Hidden' NAME='HID_SUS_REF_NO' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cur_yy' VALUE=\"\">");//Added by Dineth on 2008-12-10
			out.println("<INPUT TYPE='Hidden' NAME='hid_cur_mm' VALUE=\"\">");//Added by Dineth on 2008-12-10
			out.println("<INPUT TYPE='Hidden' NAME='hid_cur_dd' VALUE=\"\">");//Added by Dineth on 2008-12-10
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoicing - Debit Note </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  //Commented By Sandun on 07-11-2008
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"save_button\" onClick='check_app_status()' value=\"Save\"></td>"); // modified by udara 05-07-2013out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr colspan=4 >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_NO' maxlength='15' size='15' style=\"{width:150px;}\" onblur=\"makeRequest(document.Form1.TXT_INVOICE_NO)\" disabled >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' >Invoice Type </td>"); 
			out.println("<td><SELECT  name=\"TXT_INVOICE_TYPE\" class=\"txt_input\"   onChange=\"Change_Type(this); \"  style=\"{width:150px;}\"> ");
			
			/*rs1 = stmt.executeQuery ("SELECT INVOICE_TYPE_CODE, INVOICE_DESC "+
									"FROM   "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD "+
															"WHERE INVOICE_TYPE_CODE NOT IN('INV_GENER')");
															
			*/
			
			// commented by udara on 21-10-2013
			/*
			rs1 = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION "+
				" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
				" WHERE ACTIVE_STATUS='Y' "+
				//" AND SUB_TYPE_CODE <> 'INSURANCE' "+ // added by udara on 03-07-2013
				" ");
			
			while(rs1.next()){
				out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
			}
			*/
			
			// added by udara on 21-10-2013
			rs1 = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION "+
				" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
				" WHERE ACTIVE_STATUS='Y' "+
				" AND SUB_TYPE_CODE = 'INSURANCE' "+ // added by udara on 03-07-2013
				" ");
			
			while(rs1.next()){
				out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
			}
			
			rs1 = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION "+
				" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
				" WHERE ACTIVE_STATUS='Y' "+
				" AND SUB_TYPE_CODE <> 'INSURANCE' "+ // added by udara on 03-07-2013
				" ");
			
			while(rs1.next()){
				out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
			}
			// end by udara on 21-10-2013
			
			out.println("</td>");
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_TYPE' maxlength='15' size='15'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table' celspacing='0' >"); 
			out.println("<tr><td width='*%' ><DIV id=m_table_invoice_type></DIV></td></tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO'     onblur=\"makeRequest2(this)\"  maxlength='20' size='20' style=\"{width:150px;}\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_finance()\"></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			// added by nuwan de silva on 04-12-2007
			out.println("<tr >"); 
			out.println("<td width='20%' >Client Code </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' disabled ></td>"); 
			out.println("<td width='10%'>Client Name</td>"); 
			//out.println("<td width='*%'></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' style=\"{width:250px;}\" maxlength='10' size='10' disabled ></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALUE_DATE'  class=div_input>Value Date *</DIV></td>"); 
			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_DD maxlength=\"2\" size=\"2\" onBlur=check_date_value(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);validate_date(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY); onChange=\"Change_Type(this); \" disabled >");//Modified by Dineth on 2008-12-10
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_MM  maxlength=\"2\" size=\"2\"  onBlur=check_date_value(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);validate_date(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY); onChange=\"Check_due_date(document.Form1.TXT_INVOICE_TYPE,document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);\" disabled >");//Modifed by Dineth on 2008-12-10
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_YY maxlength=\"4\" size=\"4\"  onBlur=check_date_value(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);validate_date(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY); onChange=\"Check_due_date(document.Form1.TXT_INVOICE_TYPE,document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);\"   disabled > ");//Modified by Dineth on 2008-12-10  //<a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
			out.println("</td> ");
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VALUE_DATE' maxlength='7' size='7'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_NET_AMOUNT'  class=div_input>Net Amount *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_NET_AMOUNT' STYLE=\"{text-align:right;}\" onblur=\"cal_tot_amount(this)\"  maxlength='25' size='22'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_VAT_AMOUNT'  class=div_input>VAT Amount *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_VAT_AMOUNT' Value=\"0.00\" STYLE=\"{text-align:right;}\" onblur=\"cal_tot_amount(this)\"  maxlength='25' size='22'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_TOTAL_AMOUNT'  class=div_input>Total Amount *</DIV></td>"); 
			out.println("<td width='30%'  ><input class='txt_input' type='text' name='TXT_TOTAL_AMOUNT' STYLE=\"{text-align:right;}\"  maxlength='22' size='22' disabled ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<td width='20%' ><DIV id='DIV_TXT_DUE_DATE'  class=div_input>Due Date * </DIV></td>"); 
			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_DUE_DATE_DD maxlength=\"2\" size=\"2\"  onBlur=\"check_date_value(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)\" disabled  >");//Modified by Dineth on 2008-12-10
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DUE_DATE_MM  maxlength=\"2\" size=\"2\" onBlur=\"check_date_value(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)\"  disabled >");//Modified by Dineth on 2008-12-10
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DUE_DATE_YY maxlength=\"4\" size=\"4\"  onBlur=\"check_date_value(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)\"   disabled >   ");//Modified by Dineth on 2008-12-10 // date disabled by kanishka and comment calender on 29-05-2013 <a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>
			out.println("</td> ");
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DUE_DATE' maxlength='7' size='7'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BALANCE_TO_BE_RECEIVED'  class=div_input>Balance To Be Received *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BALANCE_TO_BE_RECEIVED' STYLE=\"{text-align:right;}\" onblur=\"format_num()\"  maxlength='22' size='22'></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); */
			
			/*out.println("<tr >"); 
			out.println("<td width='20%' >Client Code </td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr >"); 
			out.println("<td width='20%' >Remarks </td>"); 
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_REMARKS'   maxlength='100' size='100' style=\"{width:250px;}\" ></td>"); 
			out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_REMARKS' style=\"width:250px; height:35px;\" maxlength='1000' size='100'  onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' >Currency Code </td>"); 
			out.println("<td><SELECT onchange=get_excharate() name=TXT_CURRENCY_CODE class=\"txt_input\" > ");
			rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
				"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
				"ORDER  BY DEFAULT_VALUE DESC ");
			while(rs.next()){
				out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
			}
			out.println("</td>");
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CURRENCY_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_EXCHANGE_RATE'  class=div_input>Exchange Rate *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_EXCHANGE_RATE' STYLE=\"{text-align:right;}\"  maxlength='10' size='22' onBlur='val_rate(this.value)'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
