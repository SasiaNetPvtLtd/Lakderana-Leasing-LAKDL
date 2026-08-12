// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
   

public class LAKDL_FA_OP_Cheque_Return_Settlement extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>Operation Process - Cheque Return Settlement</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("	m_flag=true;"); 
			out.println("	if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_RECEIPT_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_RECEIPT_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(parseInt(document.Form1.hid_redeposit_receipt.value)==0){  "); 
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	if(m_flag){");
			out.println("		m_allo_amount=0;"); 
			out.println("		for (var i=1;i<=parseInt(document.Form1.hid_redeposit_receipt.value);i++) {");
			out.println("			m_allo_amount=parseFloat(m_allo_amount)+parseFloat(unformat_noobject(document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+i].value));");
			out.println("		}");
			out.println("		if(parseFloat(unformat_noobject(document.Form1.TXT_BAL_AMT.value))>=parseFloat(m_allo_amount)){");
			out.println("			return true;"); 
			out.println("		}"); 
			out.println("		else{"); 
			out.println("			alert(\"Allocation about should less than the Receipt balnce amount\");"); 
			out.println("			return false;"); 
			out.println("		}");
			out.println("	}");
			out.println("	else{"); 
			out.println("		alert('Please enter all required fields marked with a *  and or mark highlighted with red color on screen');");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Chq_Return_Settlement';");  
			out.println("			document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
		
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Cheque_Return_Settlement';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Cheque_Return_Settlement';"); 
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
			out.println("	help_box.innerHTML=\"  Operation Process - Cheque Return Settlement - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Operation Process - Cheque Return Settlement - \"+document.Form1.hid_status.value;"); 
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
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:35em; dialogHeight:25em; center:yes; status:no\");"); 
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
		
			out.println("function help_update_receipt_no(){");
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_EDIT_SETTLE_DETAILS_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_SETTLEMENT_RECEIPT_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','20');"); 
			out.println("}"); 		
			
			out.println("function help_update_value_2() {"); 
			out.println("		load_rebank_list();");
			out.println("}");
			
			out.println("function help_update_previous_ref_no(){");
			out.println(" 	document.Form1.hid_help_type.value=\"3\";");
			//out.println("alert(document.Form1.TXT_RECEIPT_TYPE.value);");
			out.println("if(document.Form1.TXT_RECEIPT_TYPE.value=='CS'){"); //Added By Sandun on 14-08-2009
			out.println(" 	m_sql = \"m_help_DIV_TXT_RETURN_SETTLE_DETAILS_CLIENT_sql\";"); 
			out.println("}else{");
			out.println(" 	m_sql = \"m_help_DIV_TXT_RETURN_SETTLE_DETAILS_ALL_sql\";"); 
			out.println("}"); 
			out.println(" 	m_criteria = document.Form1.TXT_PRE_REF_NO.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_R_CODE.value+\"@\"+document.Form1.TXT_RECEIPT_TYPE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
	
			out.println("function help_update_value_3() {"); 
			out.println("		document.Form1.TXT_PRE_REF_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_RECEIPT_AMT.value=oBj.valout[8];"); 
			out.println("		document.Form1.TXT_RECEIPT_SETTLE_AMT.value=oBj.valout[6];"); 
			out.println("		format_num(document.Form1.TXT_RECEIPT_AMT,4);"); 
			out.println("		format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);"); 
			out.println("}");	
			
			out.println("function help_get_receipt_details(){");
			out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
			//out.println(" 	m_sql = \"m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql\";");//Commented by Dineth on 27-04-2009
			//out.println(" 	m_sql = \"m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql1\";");//Added by Dineth on 27-04-2009 
			out.println(" 	m_sql = \"m_help_DIV_TXT_CHQ_RETURN_SETTLEMENT_DETAILS_sql1\";");	
			out.println(" 	m_criteria = document.Form1.TXT_RECEIPT_NO.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_4() {"); 
			out.println("		document.Form1.TXT_RECEIPT_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_R_CODE.value=oBj.valout[8];"); 
			out.println("		document.Form1.TXT_CLIENT_R_NAME.value=oBj.valout[7];"); 
			out.println("		document.Form1.TXT_CLIENT_R_SETT.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_REC_AMT.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_BAL_AMT.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_RECEIPT_TYPE.value=oBj.valout[9];"); 
			out.println("		format_num(document.Form1.TXT_REC_AMT,4);"); 
			out.println("		format_num(document.Form1.TXT_BAL_AMT,4);"); 
			out.println("}");	

			out.println("function add_re_deposit_receipt(){"); 
			out.println("		if(document.Form1.TXT_PRE_REF_NO.value!=\"\"){"); 
			out.println("		m_deposit_count=parseInt(document.Form1.hid_redeposit_receipt.value);");
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
			out.println("		document.Form1.hid_redeposit_receipt.value=m_deposit_count;");
			out.println("		document.Form1.TXT_PRE_REF_NO.value=\"\";"); 
			out.println("		document.Form1.TXT_RECEIPT_AMT.value=\"\";"); 
			out.println("		document.Form1.TXT_RECEIPT_SETTLE_AMT.value=\"\";"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function delete_receipt(m_num){"); 
			out.println("		m_deposit_count=0;");
			out.println("		m_str_data=\"\";");
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_redeposit_receipt.value);k++){");
			out.println("				if(m_num!=k){");
			out.println("					m_deposit_count++;");
			out.println("		 			obj1=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_NO_\"+k].value;"); 
			out.println("		 			obj2=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_TOTAL_\"+k].value;"); 
			out.println("		 			obj3=document.Form1.elements[\"TXT_RE_DEPOSIT_RECEIPT_AMT_\"+k].value;"); 
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
			out.println("}"); 
			
			out.println("function show_detail_balance() {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_rpt_detail?chksql=LOAD_CHEQUE_RETURN_AVAILABLE_BAL&facility_code=\"+document.Form1.TXT_FACILITY_NO.value;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=700,height=550,scrollbars=2\");");
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_loop_count' VALUE=\"99\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_redeposit_receipt' VALUE=\"0\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process - Cheque Return Settlement</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input>Facility No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='20' size='10' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_2' value=\"Details\" onClick=\"show_detail_balance()\"></td>"); 
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
			out.println("</table>"); 
			out.println("<br>");
			out.println("<hr>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_RECEIPT_NO'  class=div_input>Receipt No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_RECEIPT_NO' maxlength='20' size='10' onblur=\"help_get_receipt_details()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_get_receipt_details()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_R_CODE'  class=div_input>Client/Debtor Code</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_R_CODE' maxlength='10' size='10' style='width:100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_R_NAME'  class=div_input>Client/Debtor Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_R_NAME' maxlength='10' size='10' style='width:250' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CLIENT_R_NAME'  class=div_input>Settle Details</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_R_SETT' maxlength='10' size='10' style='width:250' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_BAL_AMT'  class=div_input>Receipt Amount</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_REC_AMT' maxlength='10' size='50' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_BAL_AMT'  class=div_input>Available Amt for Rtn Chq</DIV></td>"); //Balance Amount
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BAL_AMT' maxlength='10' size='50' disabled></td>"); 
			out.println("<td width='*%'><input type='hidden' name='TXT_RECEIPT_TYPE'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<hr>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REBANK_CODE'  class=div_input>Unsettled Return Cheques</DIV></td>"); 
			out.println("<td width='80%' ><input class='txt_input' type='text' name='TXT_PRE_REF_NO' maxlength='25' size='25' onblur=\"help_update_previous_ref_no()\">"); 
			out.println("<input class='txt_input' type='text' name='TXT_RECEIPT_AMT' maxlength='25' size='25' disabled>"); 
			out.println("<input class='txt_input' type='text' name='TXT_RECEIPT_SETTLE_AMT' maxlength='25' size='25' onchange=\"format_num(document.Form1.TXT_RECEIPT_SETTLE_AMT,4);\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_11' value=\"Help\" onClick=\"help_update_previous_ref_no()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_12' value=\"Add\" onClick=\"add_re_deposit_receipt()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>"); 
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
