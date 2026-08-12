// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         
        
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_OP_Invoice_Allocation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Invoice Allocation</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("		document.Form1.TXT_TOT_SETTLE_AMOUNT.value=data_vec[0];");
			out.println("		makeRequest_2();");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_option.value==\"2\"){");
			out.println("		document.Form1.TXT_TOT_PENDING_BAL.value=data_vec[0];");
			out.println("		document.Form1.hid_option.value=99;");
			out.println("		}");
			out.println("		else{");
			out.println("		document.Form1.hid_option.value=99;");
			out.println("		}");
			out.println("}");
			
			out.println("function makeRequest_1() {");
			out.println("		document.Form1.hid_option.value=1;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_sql_validations?chksql=m_total_settlement_val&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest_2() {");
			out.println("		document.Form1.hid_option.value=2;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_sql_validations?chksql=m_tot_pending_bal&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&facility_no=\"+document.Form1.TXT_FACILITY_NO.value;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else if(document.Form1.TXT_FACILITY_NO.value==\"\"){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		return false;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			  

			out.println("function load_calendar() {");
			out.println("		popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\",\"oBj\",\"left=450,top=200,width=320,height=230\");");
			out.println("}");
			
			out.println("function load_c_date(val) {");
     	out.println("	 v_dd = val.substr(0,val.indexOf('-'));");
   		out.println("  if(v_dd.length <2)"); 
   		out.println("  	v_dd = 0+v_dd;");
  		out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("	 v_mm = val.substr(0,val.indexOf('-'));");
  		out.println("  if(v_mm.length <2)");
   	  out.println("		v_mm = 0+v_mm;");
  		out.println("   v_yy = val.substr(val.indexOf('-')+1,val.length);");
     	out.println("		document.Form1.TXT_ADJ_DD.value=v_dd;");
   		out.println("   document.Form1.TXT_ADJ_MM.value=v_mm;");
   		out.println("   document.Form1.TXT_ADJ_YY.value=v_yy;");
 			out.println("}");
			
			out.println("function before_submit(){ "); 
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Invoice_Allocation';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function check_status(num,num2) {");
			out.println("	if(document.Form1.elements['SETTLE_CHK_'+num+'_'+num2].checked){");
			out.println("		document.Form1.elements['SETTLE_CHK_'+num+'_'+num2].value=\"YES\";");
			out.println("		document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].disabled=true;");
			out.println("	}");
			out.println("	else{");
			out.println(" 	document.Form1.elements['SETTLE_CHK_'+num+'_'+num2].value=\"NO\";");
			out.println(" 	document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].disabled=false;");
			out.println("		document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].value=0;");
			out.println("	}");
			out.println("}");
			
			out.println("function chk_bal(num,num2) {");
			out.println("		m_tot=unformat_noobject(document.Form1.elements['BAL_AMOUNT_'+num].value);");
			out.println("		m_num5=unformat_noobject(document.Form1.elements['hid_invoice_count_'+num].value);");
			out.println("		m_ent_val=unformat_noobject(document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].value);");
			out.println("		m_org_settle_val=unformat_noobject(document.Form1.elements['BAL_AMT_'+num+'_'+num2].value);");
			out.println("		m_val=0;");
			out.println("		if(parseFloat(m_ent_val)>0){");
			out.println("			if(parseFloat(m_ent_val)>parseFloat(m_org_settle_val)){");
			out.println("				alert('Setttle amount greater than the Balance amount');");
			out.println(" 			document.Form1.elements['SETTLE_CHK_'+num+'_'+num2].value=\"NO\";");
			out.println(" 			document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].disabled=false;");
			out.println("				document.Form1.elements['SETTLE_AMT_'+num+'_'+num2].value=0;");
			out.println("			}");
			out.println("			for(k=0;k<parseInt(m_num5);k++){");
			out.println("				m_val=m_val+parseFloat(unformat_noobject(document.Form1.elements['SETTLE_AMT_'+num+'_'+k].value));");
			out.println("			}");
			out.println("			if(parseFloat(m_val)>parseFloat(m_tot)){");
			out.println("				alert('Allocated amount mismatch with the Total Receipt amount');");
			out.println("				for(k=0;k<parseInt(m_num5);k++){");
			out.println(" 				document.Form1.elements['SETTLE_CHK_'+num+'_'+k].value=\"NO\";");
			out.println(" 				document.Form1.elements['SETTLE_AMT_'+num+'_'+k].disabled=false;");
			out.println("					document.Form1.elements['SETTLE_AMT_'+num+'_'+k].value=0;");
			out.println("				}");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_Allocation';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_Allocation';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_INVOICE_ALLOCATION\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Operation Process - Invoice Allocation - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Operation Process - Invoice Allocation - \"+document.Form1.hid_status.value;"); 
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
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_2();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("						help_update_value_3();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("						help_update_value_4();"); 
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

			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");

			out.println("function help_update_facility() {"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_VIEW_sql\";");
			out.println("  	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";"); 
			out.println("	}");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_client() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CLIENT_INVOICE_ALLO_ENTER_sql\";");
			out.println(" 	m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println("		HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_MANAGER_NAME.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function help_update_value_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_CLIENT_MANAGER_NAME.value=oBj.valout[5];"); 
			out.println("		makeRequest_1();");
			out.println("}");
			
			out.println("function help_bal_receipts() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_INV_ALLO_RECEIPTS_sql\";");
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_RECEIPT_NO.value+\"@\";");
			out.println("		HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_RECEIPT_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_bal_invoices() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql\";");
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_BATCH_NO.value+\"@\";");
			out.println("		HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_BATCH_NO.value=oBj.valout[3];"); 
			out.println("}");
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
			out.println("function help_update_receipt_slip(){");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_print_receipt\";");
			out.println("	popupwin = window.showModalDialog(m_url,\"dialogWidth:350em; dialogHeight:50em; center:yes; status:no\");"); 
			out.println("}");

			
			out.println("function get_invoice_settlement() {");
			out.println("		settlement_data.innerHTML=\"\";");
			out.println("		if(validate_data()){");
			out.println("			if(document.Form1.TXT_ALLOCATION_MODE.value==\"POD_ALLO\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_invoice_settlement?chksql=LOAD_SETTLEMENT_POD_ALLO&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("			else if(document.Form1.TXT_ALLOCATION_MODE.value==\"INV_SP_ALLO\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_invoice_settlement?chksql=LOAD_SETTLEMENT_INV_SP_ALLO&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("			else if(document.Form1.TXT_ALLOCATION_MODE.value==\"INV_ALLO\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_invoice_settlement?chksql=LOAD_SETTLEMENT_INV_ALLO&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("			else if(document.Form1.TXT_ALLOCATION_MODE.value==\"INV_SP_ALLO_2\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_invoice_settlement?chksql=LOAD_SETTLEMENT_INV_SP_ALLO_2&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("			else if(document.Form1.TXT_ALLOCATION_MODE.value==\"INV_ALLO_ALL\"){");
			out.println("				m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_invoice_settlement?chksql=LOAD_SETTLEMENT_INV_ALLO_ALL&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value+\"&RECEIPT_NO=\"+document.Form1.TXT_RECEIPT_NO.value+\"&BATCH_NO=\"+document.Form1.TXT_BATCH_NO.value;");	
			out.println("				load_interface(m_url,'NO');");
			out.println("			}");
			out.println("		}");
			out.println("}");
			
			out.println("function show_avaiable_for_settlement() {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_AVAIL_SETTLEMENT&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=600,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("function show_pending_balance() {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_validations_normal?chksql=LOAD_AVAIL_BALANCE&CLIENT_CODE=\"+document.Form1.TXT_CLIENT_CODE.value+\"&FACILITY_NO=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=600,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		settlement_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("</script>"); 
		  	
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Invoice Allocation </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			//out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='help_update_receipt_slip()' value=\"FR - Print\"></td>");
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='10' size='10' onblur=\"help_update_facility()\"  >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\">");
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Details\" onClick=\"show_facility(document.Form1.TXT_FACILITY_NO.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code	*</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10'  onblur=\"help_update_client()\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_client()\" disabled>");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Details\" onClick=\"show_client(document.Form1.TXT_CLIENT_CODE.value)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_DESC'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_MANAGER_NAME'  class=div_input>Client Manager Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_MANAGER_NAME' maxlength='50' size='50'  style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' class=div_input>Total amount available for Settlements</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOT_SETTLE_AMOUNT' maxlength='25' size='50' style='width:150;text-align:right;' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TOT_SETTLE_AMOUNT_MAIN' value=\"Details\" onClick=\"show_avaiable_for_settlement()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' class=div_input>Total pending Balance</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOT_PENDING_BAL' maxlength='25' size='50' style='width:150;text-align:right;' disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_TOT_PENDING_BAL_MAIN' value=\"Details\" onClick=\"show_pending_balance()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' class=div_input>Receipt No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' maxlength='25' size='50' style='width:150;text-align:left;' onblur=\"help_bal_receipts()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_11' value=\"Help\" onClick=\"help_bal_receipts()\"  >");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_13' value=\"Details\" onClick=\"show_receipt_details(document.Form1.TXT_RECEIPT_NO.value)\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' class=div_input>Invoice No</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BATCH_NO' maxlength='25' size='50' style='width:150;text-align:left;'  onblur=\"help_bal_invoices()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_12' value=\"Help\" onClick=\"help_bal_invoices()\">");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_14' value=\"Details\" onClick=\"show_invoice_batch_details(document.Form1.TXT_BATCH_NO.value)\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<td width='30%' >Allocation Mode</td>"); 
			out.println("<td width='40%' ><select name='TXT_ALLOCATION_MODE' class='txt_input' style='width:250'>");
			out.println("<OPTION value=\"POD_ALLO\" >PD Allocation</OPTION>");
			//out.println("<OPTION value=\"INV_SP_ALLO_SCH\" >Invoice specific Allocation with schedule</OPTION>");
			out.println("<OPTION value=\"INV_ALLO\" SELECTED>Invoice Allocation</OPTION>");
			out.println("<OPTION value=\"INV_SP_ALLO\">Invoice Specific</OPTION>");
			out.println("<OPTION value=\"INV_SP_ALLO_2\">Invoice Specific2</OPTION>");
			out.println("<OPTION value=\"INV_ALLO_ALL\">Allocate all Invoices with Debtors</OPTION>");
			out.println("</select>");
			out.println("<input class='but_input' type='button' name='BUT_ALLOCATE_MAIN' value=\"Allocate\" onClick=\"get_invoice_settlement()\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</table>"); 
			out.println("<hr>");
			out.println("<DIV id='settlement_data'  class=div_input></DIV>");
			
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
