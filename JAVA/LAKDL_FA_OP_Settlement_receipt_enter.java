// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_Settlement_receipt_enter extends javax.servlet.http.HttpServlet { 

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
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out=res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			m_username=m_sn_methods.username;
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Settlement Receipt Entry</TITLE>"); 
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
			out.println("			document.Form1.TXT_CHEQUE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_CHEQUE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_CHEQUE_YY.value=data_vec[2];");
			out.println("	  	document.Form1.hid_option.value=\"99\"");
			out.println("	  	get_exchange_rate();");
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
			out.println("		document.Form1.TXT_INVOICE_NO.focus();");
			out.println("}"); 
			
			out.println("function get_rpt_currency_amt(){"); 
			out.println("		m_amt=0;");
			out.println("		m_amt=parseFloat(unformat_noobject(document.Form1.TXT_EXCHANGE_RATE.value))*parseFloat(unformat_noobject(document.Form1.TXT_INVOICE_AMOUNT.value));");
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=m_amt;");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("}");   

			out.println("function validate_data(){"); 
			out.println("	m_flag=true;"); 
			out.println("	if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"CS\" ){  "); 
			out.println("		if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("			DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}"); 
			out.println("		if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"DS\" ){  "); 
			out.println("		if(document.Form1.TXT_DEBTOR_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_DEBTOR_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IS\" ){  "); 
			out.println("		if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("			DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}"); 
			out.println("		if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_INVOICE_BATCH_NO.value==\"\" ){  "); 
			out.println("			DIV_TXT_INVOICE_BATCH_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_DEBTOR_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_DEBTOR_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_INVOICE_NO.value==\"\" ){  "); 
			out.println("			DIV_TXT_INVOICE_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\" ){  "); 
			out.println("		if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("			DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}"); 
			out.println("		if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_DEBTOR_CODE.value==\"\" ){  "); 
			out.println("			DIV_TXT_DEBTOR_CODE.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_INVOICE_DD.value==\"\" ){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_INVOICE_MM.value==\"\" ){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_INVOICE_YY.value==\"\" ){  "); 
			out.println("		DIV_TXT_INVOICE_DATE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_INVOICE_AMOUNT.value==\"\"){  "); 
			out.println("		DIV_TXT_INVOICE_AMOUNT.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	if(document.Form1.TXT_SETTLEMENT_MODE.value!=\"CASH\"){  "); 
			out.println("		if(document.Form1.TXT_PAYER_ACCOUNT.value==\"\"){  "); 
			out.println("			DIV_TXT_PAYER_ACCOUNT.style.color='red';");
			//out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_PAYER_BRANCH.value==\"\"){  "); 
			out.println("			DIV_TXT_PAYER_BRANCH.style.color='red';");
			//out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("		if(document.Form1.TXT_REF_NO.value==\"\"){  "); 
			out.println("			DIV_TXT_REF_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("	}");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
			out.println("			if(document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value==\"\"){");
			out.println("				DIV_TXT_SETTLEMENT_RECEIPT_NO.style.color='red';");
			out.println("				m_flag=false;"); 
			out.println("			}");
			out.println("	}");
			out.println("		if(document.Form1.TXT_TEMP_RECEIPT_NO.value==\"\"){  "); //Added By Sandun on 09-09-09
			out.println("			DIV_TXT_TEMP_RECEIPT_NO.style.color='red';");
			out.println("			m_flag=false;"); 
			out.println("		}");
			out.println("	if(m_flag){");			
			out.println("		return true;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		alert('Please enter all required fields marked with a *  and or mark highlighted with red color on screen');");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("}"); 
			
			
			out.println("function check_cheque_amount()");
			out.println("{ ");
			
			out.println("    var total_allocation=0; ");
			out.println("    var cheque_amount=parseFloat(unformat_noobject(document.Form1.TXT_INVOICE_AMOUNT.value)); ");
			out.println("    var total_invoice_allocation=0 ");
			/*******************validate invice wise allocation against cheque amount****************/
			out.println("    if((document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\")&&(parseInt(document.Form1.NUM_CHKS.value) >0))");
			out.println("    {  ");
			out.println("        for(var i=0;i<parseInt(document.Form1.NUM_CHKS.value);i++) ");
			out.println("        {  ");
			out.println("           if(document.Form1.elements[\"TXT_POD_STATUS_\"+i].checked==true){ ");
			//out.println("             total_invoice_allocation=total_invoice_allocation+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_ALLO_BALANCE_\"+i].value)); ");
			out.println("             total_invoice_allocation=parseFloat(total_invoice_allocation)+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_ALLO_BALANCE_\"+i].value)); ");
			out.println("           }  ");
			out.println("        }  ");
			out.println("    }  ");
			out.println("    var count_redeposit=parseInt(document.Form1.hid_redeposit_receipt.value); ");
			out.println("    var return_allo=0; ");
			out.println("    for(var j=1;j<=count_redeposit;j++) ");
			out.println("    { ");
			//out.println("        return_allo=return_allo+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+j].value)); ");
			out.println("        return_allo=parseFloat(return_allo)+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+j].value)); ");
			out.println("    } ");
			
			// commented below by udara on 26-09-2011
			/*
			out.println("    total_allocation=total_invoice_allocation+return_allo;");
			out.println("    if(total_allocation>cheque_amount){ ");
			out.println("       return false; ");
			out.println("    }else {");
			out.println("       return true;");
			out.println("    }");
			*/
			
			// added below by udara on 26-09-2011
			//out.println(" 	if((parseFloat(total_invoice_allocation)>parseFloat(cheque_amount)) || (parseFloat(return_allo)>parseFloat(cheque_amount))){	");
			out.println(" 	if((  Math.round(parseFloat(total_invoice_allocation)*100)/100 > Math.round(parseFloat(cheque_amount)*100)/100  ) || ( Math.round(parseFloat(return_allo)*100)/100 > Math.round(parseFloat(cheque_amount)*100)/100  )){	");
			out.println("       return false; ");
			out.println("   }");
			out.println("   else {");
			out.println("       return true;");
			out.println("   }");
			
			/*******************validate invice wise allocation against cheque amount*******************/
			out.println("} ");
			
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("     if(check_cheque_amount()){  ");//validation against the receipts for all types of allocations
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Settlement_receipt';");  
			out.println("			document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	  }");//end alloaction validation
			out.println("     else{ ");	
			out.println("         alert('Allocation amount exceeds the cheque amount ,Please check the allocation'); ");
			out.println("	  }"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Settlement_receipt_enter';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Settlement_receipt_enter';"); 
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
			out.println("	help_box.innerHTML=\"  Operation Process - Settlement Receipt Entry - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Operation Process - Settlement Receipt Entry - \"+document.Form1.hid_status.value;"); 
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
			out.println("			document.Form1.TXT_SETTLEMENT_RECEIPT_NO.disabled=false;");
			out.println("			document.Form1.BUT_HELP_MAIN_10.disabled=false;");
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
			out.println("					else if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("						help_update_value_7();"); 
	  		out.println("					}"); 
			out.println("					else if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("						help_update_value_8();"); 
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
			
			out.println("function load_corres_invoice_list() {");
			out.println("		if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\"){");
			out.println("			if(document.Form1.TXT_DEBTOR_CODE.value!=\"\"){");
			out.println("				document.Form1.hid_loop_count.value=3;");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_INVOICES_RECEIPT&DEBTOR_CODE=\"+document.Form1.TXT_DEBTOR_CODE.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function load_corres_invoice_list_edit() {");
			out.println("		if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\"){");
			out.println("			if(document.Form1.TXT_DEBTOR_CODE.value!=\"\"){");
			out.println("				document.Form1.hid_loop_count.value=1;");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_INVOICES_RECEIPT_EDIT&POD_CODE=\"+document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value+\"&DEBTOR_CODE=\"+document.Form1.TXT_DEBTOR_CODE.value+\"&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("		}");
			//out.println("		load_rebank_list();");
			out.println("}");
			
			out.println("function load_rebank_list() {");
			//out.println("		alert(document.Form1.hid_redeposit_receipt.value);");
			out.println("		if(parseInt(document.Form1.hid_redeposit_receipt.value)>0){");
			out.println("			document.Form1.hid_loop_count.value=2;");
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=REBANK_RECEIPT_EDIT&RECEIPT_NO=\"+document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value;");
			//out.println("			window.open(m_url);");
			out.println("			load_interface(m_url,'NO');");
			out.println("		}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(parseInt(document.Form1.hid_loop_count.value)==1){");
			out.println("			invoice_detail_data.innerHTML=m_data;");
			out.println("			document.Form1.hid_loop_count.value=99;");
			out.println("			load_rebank_list();");
			out.println("		}");
			out.println("		else if(parseInt(document.Form1.hid_loop_count.value)==2){");
			out.println("			re_deposit_receipt_list.innerHTML=m_data;");
			out.println("			document.Form1.hid_loop_count.value=99;");
			out.println("		}");
			out.println("		else if(parseInt(document.Form1.hid_loop_count.value)==3){");
			out.println("			invoice_detail_data.innerHTML=m_data;");
			out.println("			document.Form1.hid_loop_count.value=99;");
			out.println("		}");
			out.println("		else{");
			out.println("		}");
			out.println("}");
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_SETTLE_ENTER_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
						
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_update_batch(){");
			out.println(" 	document.Form1.hid_help_type.value=\"2\";");
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_EDIT_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_INVOICE_BATCH_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_2() {");
			out.println("		document.Form1.TXT_INVOICE_BATCH_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[3];"); 
			out.println("		load_corres_invoice_list();");
			out.println("}"); 

			out.println("function help_update_debtor_invoice() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_DETAILS_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_INVOICE_BATCH_NO.value+\"@\"+document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_INVOICE_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_INVOICE_NO.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_INVOICE_AMOUNT.value=oBj.valout[6];"); 
			out.println("		get_rpt_currency_amt();format_num(document.Form1.TXT_INVOICE_AMOUNT,4);");
			out.println("}");
			
			out.println("function help_payer_account() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql\";"); 
			out.println("	if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"CS\"){");
			out.println(" 	m_criteria = document.Form1.TXT_PAYER_ACCOUNT.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"DS\"){");  
			out.println(" 	m_criteria = document.Form1.TXT_PAYER_ACCOUNT.value+\"@\"+document.Form1.TXT_DEBTOR_CODE.value+\"@\";");
			out.println("	}");
			out.println("	else{");  
			out.println(" 	m_criteria = document.Form1.TXT_PAYER_ACCOUNT.value+\"@\"+document.Form1.TXT_DEBTOR_CODE.value+\"@\";");
			out.println("	}");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_5() {"); 
			out.println("		document.Form1.TXT_PAYER_ACCOUNT.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_PAYER_BRANCH.value=oBj.valout[4];"); 
			out.println("}");

			out.println("function help_collection_officer(){");
			out.println(" 	document.Form1.hid_help_type.value=\"7\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CR_CLIENT_MGT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_COLL_OFFICER.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_7() {"); 
			out.println("		document.Form1.TXT_COLL_OFFICER.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_receipt_no(){");
			out.println(" 	document.Form1.hid_help_type.value=\"6\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_EDIT_SETTLE_DETAILS_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','20');"); 
			out.println("}"); 		
			
			out.println("function help_update_value_6() {"); 
			out.println("		document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_SETTLEMENT_TYPE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[9];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[8];"); 
			out.println("		document.Form1.TXT_INVOICE_BATCH_NO.value=oBj.valout[10];"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[11];"); 
			out.println("		document.Form1.TXT_INVOICE_NO.value=oBj.valout[12];"); 
			out.println("		document.Form1.TXT_INVOICE_DD.value=oBj.valout[13];"); 
			out.println("		document.Form1.TXT_INVOICE_MM.value=oBj.valout[14];"); 
			out.println("		document.Form1.TXT_INVOICE_YY.value=oBj.valout[15];"); 
			out.println("		document.Form1.TXT_INVOICE_AMOUNT.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CURRENCY_CODE.value=oBj.valout[21];"); 
			out.println("		document.Form1.TXT_EXCHANGE_RATE.value=oBj.valout[22];"); 
			out.println("		document.Form1.TXT_INVOICE_RPT_CURR_AMT.value=oBj.valout[23];"); 
			out.println("		document.Form1.TXT_SETTLEMENT_MODE.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_PAYER_ACCOUNT.value=oBj.valout[7];"); 
			out.println("		document.Form1.TXT_PAYER_BRANCH.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_REF_NO.value=oBj.valout[20];"); 
			out.println("		document.Form1.TXT_INVOICE_COMMENTS.value=oBj.valout[16];"); 
			out.println("		document.Form1.TXT_CHEQUE_DD.value=oBj.valout[17];"); 
			out.println("		document.Form1.TXT_CHEQUE_MM.value=oBj.valout[18];"); 
			out.println("		document.Form1.TXT_CHEQUE_YY.value=oBj.valout[19];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[24];"); 
			out.println("		document.Form1.TXT_DEBTOR_NAME.value=oBj.valout[25];");
			out.println("		document.Form1.TXT_TEMP_RECEIPT_NO.value=oBj.valout[26];"); 
			out.println("		document.Form1.TXT_COLL_OFFICER.value=oBj.valout[27];");
			out.println("		document.Form1.hid_redeposit_receipt.value=oBj.valout[28];");
			out.println("		format_num(document.Form1.TXT_INVOICE_AMOUNT,4);");
			out.println("		format_num(document.Form1.TXT_EXCHANGE_RATE,4);");
			out.println("		format_num(document.Form1.TXT_INVOICE_RPT_CURR_AMT,4);");
			out.println("		document.Form1.TXT_RECE_SETT_TYPE.value=oBj.valout[29];"); //ADDED BY ASHINI ON 22-02-2008
			out.println("		document.Form1.TXT_RETURN_CHEQUE_SETTLEMENT.value=oBj.valout[30]; "); // Added by Udara on 27-05-2011
			out.println("		get_settlement_type();");
			out.println("		load_corres_invoice_list_edit();");
			out.println("		alert(\"Loading...\");"); //Modified by Thamali Jayatunga on 2009.10.21
			out.println("		load_rebank_list();");
			out.println("}");
			
			out.println("function help_update_previous_ref_no(){");
			out.println(" 	document.Form1.hid_help_type.value=\"8\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_RETURN_SETTLE_DETAILS_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_PRE_REF_NO.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
	
			out.println("function help_update_value_8() {"); 
			out.println("		document.Form1.TXT_PRE_REF_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_RECEIPT_AMT.value=oBj.valout[6];"); 
			out.println("		document.Form1.TXT_RECEIPT_SETTLE_AMT.value=0;");//Modified by Dineth on 06-05-2009 
			out.println("		format_num(document.Form1.TXT_RECEIPT_AMT,4);"); 
			out.println("		format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);"); 
			out.println("}");
			

			out.println("function generate_redeposit_information(){ ");////added 2011-04-25
			out.println("var x='<table align=\"center\" width=\"100%\" class=\"table\">'+ "); 
			out.println("'<tr>'+"); 
			out.println("'<td width=\"15%\" ><DIV id=\"DIV_TXT_REBANK_CODE\"  class=div_input>Returned Cheque Details</DIV></td>'+"); 
			out.println("'<td width=\"80%\" ><input class=\"txt_input\" type=\"text\" name=\"TXT_PRE_REF_NO\" maxlength=\"25\" size=\"25\" onblur=\"help_update_previous_ref_no()\"> '+ "); 
			out.println("'<input class=\"txt_input\" type=\"text\" name=\"TXT_RECEIPT_AMT\" maxlength=\"25\" size=\"25\" disabled> '+ "); 
			out.println("'<input class=\"txt_input\" type=\"text\" name=\"TXT_RECEIPT_SETTLE_AMT\" maxlength=\"25\" size=\"25\" onblur=\"check_value(this);format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);\" \"> ' +"); //onchange=\"check_value(this);format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);  commented by 2011-04-21 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_MAIN_11\" value=\"Help\" onClick=\"help_update_previous_ref_no()\"> '+"); 
			out.println("'<input class=\"but_input\" type=\"button\" name=\"BUT_HELP_MAIN_12\" value=\"Add\" onClick=\"add_re_deposit_receipt()\"></td> '+"); 
			out.println("'<td width=\"*%\"></td> '+"); 
			out.println("'</tr>'+");
			out.println("'</table>';");
			out.println("       document.getElementById('return_information').innerHTML=x; ");
			out.println("}");
			
			// commented by udara on 27-05-2011
			
			out.println("function get_settlement_type(){"); 
			out.println("	if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"CS\"||document.Form1.TXT_SETTLEMENT_TYPE.value==\"RS\"){");
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=true;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=false;");
			out.println("        document.getElementById('return_information').innerHTML=\"\" ;");
			out.println("        document.Form1.hid_redeposit_receipt.value =0; ");
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	
			out.println("        document.Form1.TXT_DEBTOR_NAME.value=\"\";  ");//added 2011-04-25
			out.println("        document.Form1.TXT_DEBTOR_CODE.value=\"\";  ");//added 2011-04-25
			out.println("        document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
			out.println("        if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            document.Form1.value=0;");//added 2011-04-25
			out.println("        }");//added 2011-04-25
			out.println("	   if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"RS\"){"); //added 2011-04-25
			//ADD THE REDEOSIT INFORMATION SECTION
			out.println("            document.Form1.TXT_DEBTOR_NAME.value=\"\";  ");//added 2011-04-25
			out.println("            document.Form1.TXT_DEBTOR_CODE.value=\"\";  ");//added 2011-04-25
			out.println("            document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
			out.println("            generate_redeposit_information(); ");//added 2011-04-25
			out.println("              if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            		document.Form1.value=0;");//added 2011-04-25
			out.println("              }");//added 2011-04-25
			out.println("       }");//added 2011-04-25
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"DS\"){");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=false;");
			out.println("        document.getElementById('return_information').innerHTML=\"\";");
			out.println("        document.Form1.hid_redeposit_receipt.value =0; ");
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	
			out.println("        document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
		    out.println("              if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            		document.Form1.value=0;");//added 2011-04-25
			out.println("              }");//added 2011-04-25
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\"){");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=true;");
			out.println("        document.getElementById('return_information').innerHTML=\"\";");
			out.println("        document.Form1.hid_redeposit_receipt.value =0; ");//added 2011-04-25
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");//added 2011-04-25
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	//added 2011-04-25
			out.println("	}"); 
			out.println("	else{");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=false;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=false;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=true;");
			out.println("	}"); 
			out.println("}");
			
			
			
			// Added by Udara on 27-05-2011
			out.println("function get_settlement_type_ret_chk_settl(){"); // TXT_RETURN_CHEQUE_SETTLEMENT
			out.println(" 	if(document.Form1.TXT_RETURN_CHEQUE_SETTLEMENT.value == \"YES\"){ "); // 'YES'
			//out.println("            document.Form1.TXT_DEBTOR_NAME.value=\"\";  ");
			//out.println("            document.Form1.TXT_DEBTOR_CODE.value=\"\";  ");
			out.println("            document.getElementById('invoice_detail_data').innerHTML=\"\"; ");
			out.println("            generate_redeposit_information(); ");
			out.println("            if(document.Form1.NUM_CHKS){");
			out.println("            		document.Form1.value=0;");
			out.println("            }");
			out.println("	}");
			out.println("	else{"); // 'NO'
			out.println("            document.getElementById('invoice_detail_data').innerHTML=\"\"; ");
			out.println("            document.getElementById('return_information').innerHTML=''; ");
			out.println("				 document.getElementById('re_deposit_receipt_list').innerHTML=''; ");
			out.println("   			 document.Form1.hid_redeposit_receipt.value='0'; ");
			out.println("	}");
			out.println("}");
			// End by Udara on 27-05-2011
			
			
			/*
			// Added by udara on 27-05-2011
			out.println("function get_settlement_type(){"); 
			out.println("	if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"CS\"||document.Form1.TXT_RETURN_CHEQUE_SETTLEMENT.value==\"YES\"){");
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=true;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=false;");
			out.println("        document.getElementById('return_information').innerHTML=\"\" ;");
			
			out.println("         if(document.Form1.TXT_RETURN_CHEQUE_SETTLEMENT.value!=\"YES\") ");
			out.println("        		document.Form1.hid_redeposit_receipt.value =0; ");
			
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	
			out.println("        document.Form1.TXT_DEBTOR_NAME.value=\"\";  ");//added 2011-04-25
			out.println("        document.Form1.TXT_DEBTOR_CODE.value=\"\";  ");//added 2011-04-25
			out.println("        document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
			out.println("        if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            document.Form1.value=0;");//added 2011-04-25
			out.println("        }");//added 2011-04-25
			out.println("	   if(document.Form1.TXT_RETURN_CHEQUE_SETTLEMENT.value==\"YES\"){"); //added 2011-04-25
			//ADD THE REDEOSIT INFORMATION SECTION
			out.println("            document.Form1.TXT_DEBTOR_NAME.value=\"\";  ");//added 2011-04-25
			out.println("            document.Form1.TXT_DEBTOR_CODE.value=\"\";  ");//added 2011-04-25
			out.println("            document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
			out.println("            generate_redeposit_information(); ");//added 2011-04-25
			out.println("              if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            		document.Form1.value=0;");//added 2011-04-25
			out.println("              }");//added 2011-04-25
			out.println("       }");//added 2011-04-25
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"DS\"){");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=false;");
			out.println("        document.getElementById('return_information').innerHTML=\"\";");
			out.println("        document.Form1.hid_redeposit_receipt.value =0; ");
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	
			out.println("        document.getElementById('invoice_detail_data').innerHTML=\"\"; ");//added 2011-04-25
		    out.println("              if(document.Form1.NUM_CHKS){");//added 2011-04-25
			out.println("            		document.Form1.value=0;");//added 2011-04-25
			out.println("              }");//added 2011-04-25
			out.println("	}");
			out.println("	else if(document.Form1.TXT_SETTLEMENT_TYPE.value==\"IB\"){");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=true;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=true;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=true;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=true;");
			out.println("        document.getElementById('return_information').innerHTML=\"\";");
			out.println("        document.Form1.hid_redeposit_receipt.value =0; ");//added 2011-04-25
			out.println("        document.Form1.hid_redeposit_tot.value =0; ");//added 2011-04-25
			out.println("        document.getElementById('re_deposit_receipt_list').innerHTML=\"\"; ");	//added 2011-04-25
			out.println("	}"); 
			out.println("	else{");  
			out.println("		 document.Form1.TXT_INVOICE_BATCH_NO.disabled=false;");
			out.println("		 document.Form1.TXT_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.TXT_DEBTOR_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_2.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_MAIN_1.disabled=false;");
			out.println("		 document.Form1.BUT_DEBTOR_INVOICE_NO_1.disabled=false;");
			out.println("		 document.Form1.TXT_FACILITY_NO.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_1.disabled=false;");
			out.println("		 document.Form1.TXT_CLIENT_CODE.disabled=false;");
			out.println("		 document.Form1.BUT_HELP_MAIN_CDETAIL.disabled=true;");
			out.println("	}"); 
			out.println("}");
			*/
			
			
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
			out.println("     document.Form1.TXT_INVOICE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_INVOICE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_INVOICE_YY.value=v_yy;");
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
			out.println("     document.Form1.TXT_CHEQUE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_CHEQUE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_CHEQUE_YY.value=v_yy;");
			out.println("  }");		
			out.println("}");				
			
			out.println("function add_re_deposit_receipt(){"); 
			
			out.println("   if(parseFloat(unformat_noobject(document.Form1.TXT_RECEIPT_SETTLE_AMT.value))>0){");//Added by Dineth on 07-05-2009
			out.println("		if(document.Form1.TXT_PRE_REF_NO.value!=\"\"){"); 
			out.println("		m_deposit_count=parseInt(document.Form1.hid_redeposit_receipt.value);");
			out.println("		m_deposit_tot=parseFloat(document.Form1.hid_redeposit_tot.value);");//Added by Dineth on 30-04-2009
			out.println("		m_string=re_deposit_receipt_list.innerHTML;");
			out.println("		m_deposit_count++;");
			out.println("		re_deposit_receipt_list.innerHTML=re_deposit_receipt_list.innerHTML+'<table  width=\"70%\">'+"); 
			out.println("		 	'<tr>'+"); 
			out.println("		 	'<td width=\"1%\" class=div_input><b>'+m_deposit_count+'</b></td>'+"); 
			out.println("		 	'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_NO_'+m_deposit_count+'\" disabled   value=\"'+document.Form1.TXT_PRE_REF_NO.value+'\"></td>'+"); 
			out.println("		 	'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_TOTAL_'+m_deposit_count+'\" disabled value=\"'+document.Form1.TXT_RECEIPT_AMT.value+'\"></td>'+");
			out.println("		 	'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_AMT_'+m_deposit_count+'\" disabled value=\"'+document.Form1.TXT_RECEIPT_SETTLE_AMT.value+'\">'+"); 
			out.println("		 	'<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_receipt('+m_deposit_count+')\" ></td>'+");
			out.println("		 	'</tr>'+"); 
			out.println("		 	'</table>';"); 
			out.println("   m_deposit_tot=m_deposit_tot+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+m_deposit_count].value));");//Added by Dineth on 30-04-2009
			out.println("   document.Form1.hid_redeposit_tot.value=m_deposit_tot;");//Added by Dineth on 30-04-2009
			out.println("		document.Form1.hid_redeposit_receipt.value=m_deposit_count;");
			out.println("		document.Form1.TXT_PRE_REF_NO.value=\"\";"); 
			out.println("		document.Form1.TXT_RECEIPT_AMT.value=\"\";"); 
			out.println("		document.Form1.TXT_RECEIPT_SETTLE_AMT.value=\"\";");
			
			out.println("		}");
			//Added by Dineth on 07-05-2009
			out.println("    receipt_obj=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_NO_\"+m_deposit_count]; ");//Added by Dineth on 13-05-2009
			out.println("    if((!validate_set_amt())||(!validate_receipt_no(receipt_obj,m_deposit_count))){");
			out.println("      delete_receipt(m_deposit_count); ");
			out.println("    }");
			out.println("  }else{");
			out.println("    alert('Please enter redeposit settlement value'); ");
			out.println("  }");
			//End by Dineth on 07-05-2009
			out.println("}"); 
			
			out.println("function delete_receipt(m_num){"); 
			out.println("   m_deposit_tot=0;");//Added by Dineth on 07-05-2009
			out.println("		m_deposit_count=0;");
			out.println("		m_str_data=\"\";");
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_redeposit_receipt.value);k++){");
			out.println("				if(m_num!=k){");
			out.println("					m_deposit_count++;");
			out.println("		 			obj1=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_NO_\"+k].value;"); 
			out.println("		 			obj2=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_TOTAL_\"+k].value;"); 
			out.println("		 			obj3=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+k].value;"); 
			out.println("   			m_deposit_tot=m_deposit_tot+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+k].value));");//Added by Dineth on 30-04-2009
			out.println("					m_str_data=m_str_data+'<table  width=\"70%\">'+"); 
			out.println("		 			'<tr>'+"); 
			out.println("		 			'<td width=\"1%\" class=div_input><b>'+m_deposit_count+'</b></td>'+"); 
			out.println("		 			'<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_NO_'+m_deposit_count+'\" disabled   value=\"'+obj1+'\"></td>'+"); 
			out.println("		 			'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_TOTAL_'+m_deposit_count+'\" disabled value=\"'+obj2+'\"></td>'+");
			out.println("		 			'<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_RE_DEPOSIT_RECEIPT_AMT_'+m_deposit_count+'\" disabled value=\"'+obj3+'\">'+"); 
			out.println("		 			'<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_receipt('+m_deposit_count+')\" ></td>'+");
			out.println("		 			'</tr>'+"); 
			out.println("		 			'</table>';"); 
			out.println("				}");
			out.println("		}");
			out.println("		re_deposit_receipt_list.innerHTML=m_str_data;");
			out.println("		document.Form1.hid_redeposit_receipt.value=m_deposit_count;");
			out.println("   document.Form1.hid_redeposit_tot.value=m_deposit_tot;");//Added by Dineth on 30-04-2009
			out.println("}"); 
			
			//Added by Dineth on 30-04-2009
			out.println("   function validate_set_amt(){");
			out.println("   var m_flag=true;");
			out.println("   if(parseFloat(unformat_noobject(document.Form1.TXT_INVOICE_AMOUNT.value))<parseFloat(document.Form1.hid_redeposit_tot.value)){");
			out.println("       alert('Settlement amount cannot be greater than re-deposit balance'); ");
			out.println("       return false; ");
			out.println("       }");
			out.println("   else{");
			//out.println("   alert(document.Form1.hid_redeposit_tot.value);");
			out.println("   return true;");
			out.println("   }");
			out.println("   }");
			//End by Dineth on 30-04-2009
			//Added by Dineth on 06-05-2009
			out.println("function check_value(obj){");
			out.println("		if(parseFloat(unformat_noobject(document.Form1.TXT_INVOICE_AMOUNT.value))<obj.value){");
			out.println("         alert('Re-Deposit amount cannot increase settlement amount'); ");
			out.println("         document.Form1.TXT_RECEIPT_SETTLE_AMT.value=0;");
			out.println("		}");
			out.println("		else if(parseFloat(unformat_noobject(document.Form1.TXT_RECEIPT_AMT.value))<obj.value){");
			out.println("         alert('Re-Deposit amount cannot increase receipt amount'); ");
			out.println("         document.Form1.TXT_RECEIPT_SETTLE_AMT.value=0;");
			out.println("		}");
			out.println("}");
			
			out.println("function validate_receipt_no(obj,row_id){");
			out.println("   m_flag=true;");
			out.println("   m_deposit_count=parseInt(document.Form1.hid_redeposit_receipt.value);");
			out.println("  if(m_deposit_count>1){");
			out.println("   for(var i=1;i<=m_deposit_count;i++){");
			out.println("    if(row_id!=i){ ");//skip the row where the validate_receipt_no function is being trigered
			out.println("     if(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_NO_\"+i].value==obj.value){");
			out.println("       alert('You cannot enter same receipt number again');");
			out.println("       m_flag=false;");
			out.println("       break; ");
			out.println("   	}");
			out.println("     else{");
			out.println("       m_flag=true;");
			out.println("     }");
			out.println("    }");//end skip the row where the validate_receipt_no function is being trigered....check the new receipt in all rows except in the current row where the newly added row. 
			out.println("   }");
			out.println("  } ");
			out.println(" return m_flag;");
			out.println("}");
			//End by Dineth on 06-05-2009
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();get_system_date();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_loop_count' VALUE=\"99\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_redeposit_receipt' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_redeposit_tot' VALUE=\"0\">");//Added by Dineth on 30-04-2009
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Settlement Receipt Entry </td>"); 
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
			out.println("<td width='15%' ><DIV id='DIV_TXT_SETTLEMENT_RECEIPT_NO'  class=div_input>Receipt No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SETTLEMENT_RECEIPT_NO' maxlength='25' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_10' value=\"Help\" onClick=\"help_update_receipt_no()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_SETTLEMENT_TYPE'  class=div_input>Settlement Type</DIV></td>");
			out.println("<td width='40%' ><SELECT name=\"TXT_SETTLEMENT_TYPE\" class=\"txt_input\" style='width:200'  onchange=\"get_settlement_type()\">");
			out.println("<OPTION value=\"CS\" selected>Client wise Settlements</OPTION>");
			out.println("<OPTION value=\"DS\">Debtor wise Settlements</OPTION>");
			//out.println("<OPTION value=\"IS\">Invoice wise Settlements</OPTION>");
			out.println("<OPTION value=\"IB\">Invoices Settlement</OPTION>");
			out.println("<OPTION value=\"RS\">Return Cheque Settlement</OPTION>"); // commented by Udara on 27-05-2011
			out.println("</td>");
			out.println("</tr>");
			
			// Added by Udara on 27-05-31
			out.println("<tr >");
			out.println("<td width='15%' ><DIV id='DIV_TXT_RETURN_CHEQUE_SETTLEMENT'  class=div_input>Return Cheque Settlement</DIV></td>");
			//out.println("<td width='40%' ><SELECT name=\"TXT_RETURN_CHEQUE_SETTLEMENT\" class=\"txt_input\" style='width:100'  onchange=\"get_settlement_type() \">");
			out.println("<td width='40%' ><SELECT name=\"TXT_RETURN_CHEQUE_SETTLEMENT\" class=\"txt_input\" style='width:100'  onchange=\"get_settlement_type_ret_chk_settl() \">");
			out.println("<OPTION value=\"NO\" selected >No </OPTION>");
			out.println("<OPTION value=\"YES\">Yes</OPTION>");
			out.println("</SELECT></td>");
			out.println("</tr>");
			// End by Udara on 27-05-2011
			
			out.println("</table>");

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='20' size='10' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code	</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_CDETAIL' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_BATCH_NO'  class=div_input>Invoice Batch No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_BATCH_NO' maxlength='25' size='10' onblur=\"help_update_batch()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' value=\"Help\" onClick=\"help_update_batch()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DEBTOR_CODE'  class=div_input>Debtor Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_CODE' maxlength='10' size='10' onblur=\"help_update_debtor()\" tabindex=\"1\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Help\" onClick=\"help_update_debtor()\" disabled>");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN_1' value=\"Details\" onClick=\"show_client(document.Form1.TXT_DEBTOR_CODE.value)\" disabled></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_DEBTOR_DESC'  class=div_input>Debtor Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEBTOR_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_NO'  class=div_input>Invoice No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='TEXT' name='TXT_INVOICE_NO' maxlength='25' size='50' style='width:150'  tabindex=\"2\" disabled>"); 
			out.println("<input class='but_input' type='BUTTON' name='BUT_DEBTOR_INVOICE_NO' value=\"Help\" onClick=\"help_update_debtor_invoice()\" disabled>");
			out.println("<input class='but_input' type='BUTTON' name='BUT_DEBTOR_INVOICE_NO_1' value=\"Details\" onClick=\"show_invoice(document.Form1.TXT_INVOICE_NO.value)\" disabled></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");	
			out.println("</table>");
			out.println("<table>"); 
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='invoice_detail_data'  class=div_input></DIV></td>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<hr>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_INVOICE_DATE\" class=div_input>Value Date *</DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_DD\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_DD,document.Form1.TXT_INVOICE_MM,document.Form1.TXT_INVOICE_YY)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_MM\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_DD,document.Form1.TXT_INVOICE_MM,document.Form1.TXT_INVOICE_YY)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_INVOICE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_INVOICE_DD,document.Form1.TXT_INVOICE_MM,document.Form1.TXT_INVOICE_YY)\"> <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"1\")>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_AMOUNT'  class=div_input>Settlement Amount *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_AMOUNT' maxlength='25' size='50' style='width:150;text-align:right;'   value=\"0\" onchange=\"get_rpt_currency_amt();format_num(document.Form1.TXT_INVOICE_AMOUNT,4);\"  tabindex=\"3\"></td>"); 
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
			out.println("<td width='15%' ><DIV id='DIV_TXT_SETTLEMENT_MODE'  class=div_input>Settlemeny Mode *</DIV></td>");
			out.println("<td width='40%' ><SELECT name=\"TXT_SETTLEMENT_MODE\" class=\"txt_input\" > ");
			out.println("<OPTION value=\"CASH\" >Cash</OPTION>");
			out.println("<OPTION value=\"CHEQUE\" SELECTED>Cheque</OPTION>");
			out.println("<OPTION value=\"BANKTR\">Bank Transfer</OPTION>");
			out.println("</td>");
			out.println("</tr>");
			//--------------------------------ADDED BY ASHINI ON 22-08-2008----------------------------
			out.println("<tr >"); 
			out.println("<td width='15%' >Receipt Settlement Type</td>"); 
			out.println("<td width='40%' ><select name='TXT_RECE_SETT_TYPE' class='txt_input' style='width:200' >");
			out.println("<option value=\"NR\" SELECTED>Normal Receipt</option>");
			out.println("<option value=\"RR\">Returnable Receipt</option>");
			out.println("</select></td>");
			out.println("<td width='*%'></td>");			
			out.println("</tr>");
			//------------------------------END MODIFICATIONS DONE BY ASHINI-22-08-2008----------------
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYER_ACCOUNT'  class=div_input>Payer Account *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYER_ACCOUNT' maxlength='20' size='10'>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_ACC' value=\"Help\" onClick=\"help_payer_account()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_PAYER_BRANCH'  class=div_input>Payer Branch Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_PAYER_BRANCH' maxlength='20' size='10' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REF_NO'  class=div_input>Reference No/Cheque No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REF_NO' maxlength='10' size='10' style='width:150'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width=\"15%\"><DIV id=\"DIV_TXT_CHEQUE_DATE\" class=div_input>Cheque Date </DIV></td>");
			out.println("<td width=\"40%\"><input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_DD\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_CHEQUE_DD,document.Form1.TXT_CHEQUE_MM,document.Form1.TXT_CHEQUE_YY)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_MM\" maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_CHEQUE_DD,document.Form1.TXT_CHEQUE_MM,document.Form1.TXT_CHEQUE_YY)\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=\"TXT_CHEQUE_YY\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_CHEQUE_DD,document.Form1.TXT_CHEQUE_MM,document.Form1.TXT_CHEQUE_YY)\"> <a href style=\"{cursor:hand; }\" onclick=load_calendar(\"2\")>   Calendar</a></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_INVOICE_COMMENTS'  class=div_input>Comments </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_INVOICE_COMMENTS' maxlength='200' size='200' style='width:200' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_TEMP_RECEIPT_NO'  class=div_input>Temp Receipt No * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEMP_RECEIPT_NO' maxlength='20' size='200' style='width:150' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_COLL_OFFICER'  class=div_input>Collection Officer </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COLL_OFFICER' maxlength='10' size='10' value=\""+m_username+"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_COLL' value=\"Help\" onClick=\"help_collection_officer()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<hr>");
			out.println("<div id=\"return_information\" >");
			/*
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REBANK_CODE'  class=div_input>Re-Deposit Information</DIV></td>"); 
			out.println("<td width='80%' ><input class='txt_input' type='text' name='TXT_PRE_REF_NO' maxlength='25' size='25' onblur=\"help_update_previous_ref_no()\">"); 
			out.println("<input class='txt_input' type='text' name='TXT_RECEIPT_AMT' maxlength='25' size='25' disabled>"); 
			out.println("<input class='txt_input' type='text' name='TXT_RECEIPT_SETTLE_AMT' maxlength='25' size='25' onblur=\"check_value(this);format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);\" \">"); //onchange=\"check_value(this);format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);  commented by 2011-04-21 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_11' value=\"Help\" onClick=\"help_update_previous_ref_no()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_12' value=\"Add\" onClick=\"add_re_deposit_receipt()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
			*/
			out.println("</div>");
			out.println("<table align='center' width='100%' class='table'>");
			out.println("<tr>"); 
			out.println("<td width='100%'><DIV id='re_deposit_receipt_list'  class=div_input></DIV></td>");
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
