// DEVELOP BY : Nuwan De Silva For Factoring
// Date       : 18-03-2010
            
   
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_OP_Charges_addition_approval extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>Operation Process - Chargees Addition Approval</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_display_client.value==\"0\"){");
			out.println("		}");
			out.println("}");
			out.println("function validate_data(){"); 
			out.println("	m_flag=false;");
			out.println("	if(document.Form1.TXT_FACILITY_NO.value==\"\" ){  "); 
			out.println("		DIV_TXT_FACILITY_NO.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		m_flag=true;"); 
			out.println("	}"); 
			out.println("	if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("		DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("		m_flag=false;"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		m_flag=true;"); 
			out.println("	}"); 
			out.println("	if(parseInt(document.Form1.hid_invoice_count.value)>0){  "); 
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_invoice_count.value);k++){");
			out.println("				obj1=document.Form1.elements[\"TXT_TRAN_AMOUNT_\"+k].value;");
			out.println("				obj2=document.Form1.elements[\"TXT_BAL_AMOUNT_\"+k].value;");
			out.println("				obj3=document.Form1.elements[\"TXT_REC_OK_\"+k];");
			out.println("				obj4=document.Form1.elements[\"TXT_BALNCE_\"+k].value;");
			out.println("				if(obj3.checked){");
			out.println("					if(parseFloat(unformat_noobject(obj4))<parseFloat(unformat_noobject(obj1))){");
			out.println("						alert(\"Transfer adjustment value should less than the balance value\");");
			out.println("						m_flag=false;"); 
			out.println("					}");
			out.println("					else{"); 
			out.println("						m_flag=true;"); 
			out.println("					}"); 
			out.println("				}");
			out.println("		}");
			out.println("	}");
			out.println("	if(parseInt(document.Form1.hid_invoice_count.value)>0){  "); 
			out.println("		for(k=1;k<=parseInt(document.Form1.hid_invoice_count.value);k++){");
			out.println("				obj1=document.Form1.elements[\"TXT_SOURCE_\"+k].value;");
			out.println("				obj3=document.Form1.elements[\"TXT_REC_OK_\"+k];");
			out.println("				if(obj3.checked){");
			out.println("					if(obj1==\"\"){");
			out.println("						alert(\"Source Document should be exsist\");");
			out.println("						m_flag=false;"); 
			out.println("					}");
			out.println("					else{"); 
			out.println("						m_flag=true;"); 
			out.println("					}"); 
			out.println("				}");
			out.println("		}");
			out.println("	}");
			out.println("	if(m_flag){");
			out.println("		return true;");
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		return false;"); 
			out.println("	}");
			out.println("}"); 			  
			out.println("function get_balance(obj4){ ");
			out.println("	var k =obj4 ;");
			out.println("	obj1=unformat_noobject(document.Form1.elements[\"TXT_TRAN_AMOUNT_\"+k].value);");
			out.println("	obj2=unformat_noobject(document.Form1.elements[\"TXT_BAL_AMOUNT_\"+k].value);");
			out.println("	obj3=unformat_noobject(document.Form1.elements[\"TXT_BALNCE_\"+k].value);");
			out.println("	obj2=obj3;");
			out.println("	if(parseFloat(obj3)<parseFloat(obj1)){");
			out.println("		alert(\"Transfer adjustment value should less than the balance value\");");
		  out.println("		document.Form1.elements[\"TXT_TRAN_AMOUNT_\"+k].value=0;");
			out.println("	}"); 	
			out.println("	else{"); 
			out.println("	}"); 	
			out.println("}"); 	
			
			
			
			
			out.println("function before_submit(){ ");   
			out.println("		if(confirm(\"Are You Sure, you want to Save?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Charges_addition_approval';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
					
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Charges_addition_approval';"); 
			out.println("		}"); 
			out.println("			document.Form1.SCREEN_NAME.value=\"NEW\";");
			out.println("}"); 
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Charges_addition_approval';"); 
			out.println("}"); 
			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 
			out.println("function delete_window(){	"); 
			out.println("			document.Form1.SCREEN_NAME.value=\"DELETE\";");
			out.println("}"); 
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_CR_INVOICE_ADJUSTMENTS\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\"  Operation Process -  Chargees Addition Approval - \"+m_val;"); 
  		out.println("		if(m_val==\"New\"){");
			out.println("			document.Form1.SCREEN_NAME.value=\"NEW\";");
			out.println("		}");
			out.println("		else if(m_val==\"Delete\"){");  
			out.println("			document.Form1.SCREEN_NAME.value=\"DELETE\";");
			out.println("		}");
			out.println("		else if(m_val==\"Cancel\"){");  
			out.println("			document.Form1.SCREEN_NAME.value=\"NEW\";");
			out.println("		}");			
			out.println("}"); 
			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\"  Operation Process -  Chargees Addition Approval - \"+document.Form1.hid_status.value;"); 
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
			out.println("			document.Form1.SCREEN_NAME.value=\"NEW\";");
			out.println("		}");
			out.println("		else if(m_val==\"DELETE\"){");  
			out.println("			document.Form1.hid_status.value=\"Delete\";");
			out.println("			document.Form1.SCREEN_NAME.value=\"DELETE\";");
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
			out.println("	else if(document.Form1.SCREEN_NAME.value==\"DELETE\"){");  
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
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select_2\"+"); 
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
			out.println("					if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("						help_update_value_5();"); 
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
						
			
			
			
			
			
			
			out.println("function check_value_val(obj1,obj2){");
			out.println("		if(parseFloat(unformat_number(obj1))<parseFloat(unformat_number(obj2))){");
			out.println("		alert('Adjustment value should be less than the balance invoice amount');");
			out.println("		obj2.value =0;");
			out.println("		}");
			out.println("}");
			
			out.println("function help_update_facility() {"); 
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_NON_SALE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println("	}");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
		  out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_NON_SALE_DELE_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\";");
			out.println("	}");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println("function help_receipte(){");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_RECEIPT_DETAILS_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_NAME.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("	}");
			out.println("	if(document.Form1.SCREEN_NAME.value==\"DELETE\"){");
			out.println(" 	document.Form1.hid_help_type.value=\"5\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_RECEIPT_DETAILS_DELE_sql\";"); 
			out.println("	 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_NAME.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("	}");
			out.println("}"); 
			out.println("function help_update_value_5() {"); 
			out.println("		document.Form1.TXT_RECEIPT_NO.value=oBj.valout[2];"); 
			out.println("		makeRequest_get_receipt_detail();");
			out.println("}");
			
			out.println("function help_update_value_1() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_CLIENT_NAME.value=oBj.valout[4];"); 
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Charges_addition_approval_sql?chksql=LOAD_DETAILS\";");
			out.println("		load_interface(m_url,'NO');");
			out.println("}");		
			
			out.println("function get_vector_normal(m_data){");
			out.println("detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function chang_status(k){");
			out.println("if(document.Form1.elements['TXT_REC_OK_'+k].checked==true){");			
			out.println("document.Form1.elements['TXT_REC_OK_'+k].value='on';");	
			out.println("}");	
			out.println("else if(document.Form1.elements['TXT_REC_OK_'+k].checked==false){");			
			out.println("document.Form1.elements['TXT_REC_OK_'+k].value='off';");	
			out.println("}");	
			out.println("}");			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload='makeRequest()'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk' VALUE=\"N\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Operation Process -  Chargees Addition Approval </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()'  value=\"Save\"></td>");  //
			out.println("<td width='10%' align='center'></td>");  
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
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<DIV id='detail_data'  class=div_input></DIV>");
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
