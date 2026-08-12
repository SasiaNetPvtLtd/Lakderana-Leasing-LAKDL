
//--
//SCREEN NAME:Client Letter
//CREATED BY:Chandana
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_PRO_Client_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Client Letter</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			
					
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_item_sub_cat();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     help_item_sub_cat();");
			out.println("			}");

      out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");

					
			
			out.println("}");
			
						
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M1' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_sql_validations?chksql=m_prime_chk_LAKDL_AF_CO_PRO_item_sub_category&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_chk_status.value=='M1' && (document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"EDIT\"))");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_sql_validations?chksql=m_prime_chk_LAKDL_AF_CO_PRO_item_sub_category_del&data_val=\"+obj.value+\"&ac_status=Y\";");
										
		//	out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

     
			
			out.println(" function assign_help_status(obj){");
      out.println(" document.Form1.hid_help_status.value =obj; ");
      out.println("}");
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\"){  "); 
			out.println("VDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VAT_RATE.value==\"\" ){  "); 
			out.println("DIV_TXT_VAT_RATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Edit\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"Deactivate\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Deactivate ? '");
			out.println("   }"); 
			out.println("   else if(m_status == \"Reactivate\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Reactivate ? '");
			out.println("   }");
			out.println("		if(validate_data()){");
			out.println("		if(confirm(m_save_msg)){ ");
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			//out.println("		if(validate_data()){"); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_Save_VAT_on_rental';");  
			out.println("		document.Form1.submit();	"); 
			//out.println("		}"); 
			out.println("		}");
			out.println("		}");
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_Client_Letter';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_Client_Letter';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
		
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_PRO_vat_on_rental\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_PRO_Help_Msg_Servlet?class_in=\"+client_name+\"AF_CO_PRO_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Client Letter - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Client Letter - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 
      out.println("document.Form1.VAL_DAY.disabled=true;"); 
			out.println("document.Form1.VAL_MONTH.disabled=true;"); 
			out.println("document.Form1.VAL_YEAR.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_RATE.disabled=true;"); 
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");  
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
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_Help_Servlet?class_in=\"+client_name+\"AF_PRO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
					
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("		clear_data();");
			out.println("		}"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		assign_help_client_code();"); 
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
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function clear_data() {");
			out.println("document.Form1.TXT_CLIENT_CODE.value='';"); 
			out.println("}");
			

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
						
			
			out.println("function check_number(obj,size){");
			out.println("if(obj.value!='')"); 
			out.println("if(isnumberok(obj,size)){"); 
			out.println("format_number(obj,size)"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function help_item_sub_cat() {"); 
			
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_NEW\";");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";}");
			
			out.println("    HelpBox('1','10','0');"); 
			
			out.println("}"); 
			
			
			
			
			
			out.println("function help_client_code() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			
			
			
			
			
			out.println("function assign_help_client_code() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			
		 	out.println("function assign_values() {"); 
				
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\"){ ");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=data_vec[1];"); 
			out.println("getDateValues(data_vec[2]) ");
			out.println("    document.Form1.TXT_VAT_RATE.value=data_vec[3];"); 
			out.println("    format_number(document.Form1.TXT_VAT_RATE,6);"); 
			out.println("}"); 
			
			out.println("else {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=data_vec[1];"); 
			out.println("}"); 
			
			out.println("}"); 
			
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
				
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
				
			out.println("     document.Form1.VAL_DAY.value=v_date;");
			out.println("     document.Form1.VAL_MONTH.value=v_month;");
			out.println("     document.Form1.VAL_YEAR.value=val;");
			
				
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			
			out.println("function getDateValues(dval){");
			out.println("document.Form1.VAL_DAY.value=dval.substring(0,2)");
			out.println("document.Form1.VAL_MONTH.value=dval.substring(3,5)");
			out.println("document.Form1.VAL_YEAR.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			out.println("function letter() {"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==''){");
			out.println("alert('Please enter a client no to view letter')");
			out.println("}");
			out.println("else{");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_PRO_Genarate_Letter?chksql=view_letter&data_val=\"+document.Form1.TXT_CLIENT_CODE.value+\"&header=\"+document.Form1.TXT_HEADER.value+\"&body=\"+document.Form1.TXT_BODY.value+\"&signature=\"+document.Form1.TXT_AUTHORIZED_SIG.value+\"&ac_status=Y\";");
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=600,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		//	out.println("window.open(m_url,'displayWindow3','left=150,top=90,width=680,height=800,toolbar=0,location=0,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("}");
			
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client Letter</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='10%'></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			//out.println("<td width='10%'></td>");  
			//out.println("<td width='10%'></td>");  
			//out.println("<td width='10%'></td>");  
			//out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
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

				out.println("<tr class=tr_input>");
			  out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input><B>Client Code</B></DIV></td>"); 
			  out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			  out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"help_client_code()\" ></td>"); 
			  out.println("<td width='*%'></td>"); 
			  out.println("</tr>");
			
		    out.println("<td width='20%' ><DIV id='DIV_TXT_HEADER'  class=div_input><B>Header</B></DIV></td>"); 
			  out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_HEADER' maxlength='100' size='100'  style='width:450'></td>"); 
			  out.println("<td width='*%'></td>"); 
			  out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_BODY'  class=div_input><B> Body </B></DIV></td>"); 
				out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_BODY' style=\"width:450px; height:100px;\" maxlength='1000' size='1000'></TEXTAREA></td>"); 
			  out.println("<td width='*%'></td>"); 
			  out.println("</tr>"); 
					
        out.println("<td width='20%' ><DIV id='DIV_TXT_AUTHORIZED_SIG'  class=div_input><B>Authorized Signatory</B></DIV></td>"); 
			  out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AUTHORIZED_SIG' maxlength='100' size='100'  style='width:250'></td>"); 
			  out.println("<td width='*%'></td>"); 
			  out.println("</tr>");
		    out.println("</table>"); 
				
				out.println("<br>"); 
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
		    out.println("<tr>"); 
				out.println("<td width='50%' ></td>"); 
		    out.println("<td width='10%'><input class='but_input' type='button' name='BUT_BCODE' value=\"Letter\" onClick=\"letter()\" style='{cursor:hand;}'></td>"); 
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
