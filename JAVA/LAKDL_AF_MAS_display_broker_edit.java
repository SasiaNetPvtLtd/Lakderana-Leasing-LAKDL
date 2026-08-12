//--
//ID:1.14 Broker Creation Process
//SCREEN NAME:SYSTEM ADMINISTRATION - BROKER
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME:2006.07.20
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_broker_edit extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Brokers</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("		  else	if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_BROKER_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_BROKER_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
			out.println("help_update()");
			//out.println("				alert('Invalid Code,Use Help');");
			//out.println("				document.Form1.TXT_BROKER_CODE.value='';");
			//out.println("       document.Form1.TXT_BROKER_CODE.focus();");
			//out.println("				new_window();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H2' && document.Form1.TXT_LOCATION_CODE.value !='' ){");
			out.println("help_button_1()"); 

			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_LOCATION_CODE.value='';");
			//out.println("       document.Form1.TXT_LOCATION_CODE.focus();");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length > 0 && document.Form1.hid_help_status.value == 'H2' && document.Form1.TXT_LOCATION_CODE.value !=''){");
			
			//out.println("help_button_1()"); 
			out.println("assign_data_loc(data_vec) ");
			
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_LOCATION_CODE.value='';");
			//out.println("       document.Form1.TXT_LOCATION_CODE.focus();");
			out.println("			}");

			
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H3' && document.Form1.TXT_CITY_CODE.value !='' && document.Form1.SCREEN_NAME.value!=\"NEW\"  ){");
			//out.println("				alert('Invalid Code');");
			out.println("				document.Form1.TXT_CITY_CODE.value='';");
			out.println("       document.Form1.TXT_CITY_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H4' && document.Form1.TXT_POSTAL_CODE.value !='' && document.Form1.SCREEN_NAME.value!=\"NEW\"  ){");
			//out.println("				alert('Invalid Code');");
			out.println("				document.Form1.TXT_POSTAL_CODE.value='';");
			out.println("       document.Form1.TXT_POSTAL_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H5' && document.Form1.TXT_SECTOR_CODE.value !='' ){");
			out.println("help_button_4()");
			
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_SECTOR_CODE.value='';");
			//out.println("       document.Form1.TXT_SECTOR_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H6'){");
			out.println("				alert('ID No already exist');");
			out.println("				document.Form1.TXT_ID_NO.value='';");
			out.println("       document.Form1.TXT_ID_NO.focus();");
			out.println("			}");
			
			/*out.println("		  else	if(data_vec.length>0 && document.Form1.hid_help_status.value == 'H6'){");
			out.println("				alert('ID No already exist');");
			out.println("				document.Form1.TXT_ID_NO.value='';");
			out.println("       document.Form1.TXT_ID_NO.focus();");
			out.println("			}");*/
			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
			out.println("if((document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"RACT\" ) && (document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"NEW\")) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_broker&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value==\"NEW\" ) ");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_broker&data_val=\"+obj.value;");
			
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value==\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_broker&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H2' && document.Form1.SCREEN_NAME.value!=\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H2' && document.Form1.SCREEN_NAME.value==\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H3' && document.Form1.SCREEN_NAME.value!=\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H3' && document.Form1.SCREEN_NAME.value==\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H4' && document.Form1.SCREEN_NAME.value!=\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H4' && document.Form1.SCREEN_NAME.value==\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_postal_codes&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5'  && document.Form1.SCREEN_NAME.value!=\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_business_sector&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5'  && document.Form1.SCREEN_NAME.value==\"RACT\" ) ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_business_sector&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H6') ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_broker_idno&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function makeRequest1(obj) {");
			//out.println("if(document.Form1.hid_help_status.value == 'H6') ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location_add&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url)");
			out.println("}");

			out.println("function assign_data(data_vec){ ");
			out.println("    document.Form1.TXT_BROKER_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_TITLE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_ID_NO.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_CONTACT_NO.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[12];"); 
			out.println("    document.Form1.TXT_SECTOR_CODE.value=data_vec[13];"); 
			out.println("    document.Form1.TXT_COMMISSION_RATE.value=data_vec[14];"); 
			out.println("    document.Form1.TXT_COMMISSION_AMOUNT.value=data_vec[15];"); 
			out.println("}");
			
			out.println("function assign_data_loc(data_vec){ ");
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=data_vec[5];"); 
			/*out.println("    document.Form1.TXT_CONTACT_NO.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_SECTOR_CODE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_COMMISSION_RATE.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_COMMISSION_AMOUNT.value=data_vec[8];"); */
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			//out.println("if(document.Form1.TXT_BROKER_CODE.value==\"\"){  "); 
			//out.println("DIV_TXT_BROKER_CODE.style.color='red';");
			//out.println("document.Form1.TXT_BROKER_CODE.focus()");
			//out.println("return false;"); 
			//out.println("}"); 
			out.println("if(document.Form1.TXT_TITLE.value==\"\"){  "); 
			out.println("DIV_TXT_TITLE.style.color='red';");
			out.println("document.Form1.TXT_TITLE.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			out.println("document.Form1.TXT_FIRST_NAME.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			out.println("document.Form1.TXT_LAST_NAME.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ID_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ID_NO.style.color='red';");
			out.println("document.Form1.TXT_ID_NO.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS1.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS1.style.color='red';");
			out.println("document.Form1.TXT_ADDRESS1.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS2.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS2.style.color='red';");
			out.println("document.Form1.TXT_ADDRESS2.focus()");

			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			out.println("document.Form1.TXT_LOCATION_CODE.focus()");

			out.println("return false;"); 
			
			out.println("}"); 
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
		
			out.println("		if(validate_data()){"); 
			
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_broker';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			
			out.println("function val_com_rate(obj){");
			out.println("   if(isNaN(obj) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   document.Form1.TXT_COMMISSION_RATE.value='';");
			out.println("   document.Form1.TXT_COMMISSION_RATE.focus();");
			out.println("   }");
			// Modified by Thamali Jayatunga on 2009.10.12, Added code to call function format_number.
			out.println("if(document.Form1.TXT_COMMISSION_RATE.value!=\"\"){");
			out.println("format_number(document.Form1.TXT_COMMISSION_RATE,2)");
			out.println("}");

			out.println("}");
			
			out.println("function val_com_amount(obj){");
			out.println("   if(isNaN(obj) ) { ");
			out.println("   alert('You have typed an incorrect character as a number');");
			out.println("   document.Form1.TXT_COMMISSION_AMOUNT.value='';");
			out.println("   document.Form1.TXT_COMMISSION_AMOUNT.focus();");
			out.println("   }");
			out.println("if(document.Form1.TXT_COMMISSION_AMOUNT.value!=\"\"){");
			// Modified by Thamali Jayatunga on 2009.10.12, Replace 25 with 21.
			out.println("format_number(document.Form1.TXT_COMMISSION_AMOUNT,21)");
			out.println("}");

			out.println("}");
			
			/*out.println("function val_nic(object){ ");
			out.println(" m_objval = object; ");
			out.println(" m_length = object.toString().length; ");
			out.println(" if(m_length<10) {");
			out.println("  alert(' ID No length should be 10'); ");
			out.println("  document.Form1.TXT_ID_NO.focus();  ");
			out.println("break   ");
			out.println("   ");
			out.println(" }");
			out.println(" else {");
			out.println("   for(var i = 0; i<m_length; i++){");
			out.println("    m_char = m_objval.charAt(i); ");
			
			out.println(" if(m_length<10) {");
			out.println("  alert(' ID No length should be 10'); ");
			out.println("  document.Form1.TXT_ID_NO.focus();  ");
			out.println("break   ");
			out.println("   ");
			out.println(" }");
			
			
			out.println("      if(i==9){ ");
			out.println("        if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X'){ ");
			out.println("          alert('ID No Final Character should be X or V '); ");
			out.println("          document.Form1.TXT_ID_NO.focus();  ");
			out.println("          ");
			out.println("        }  ");
			out.println("      } ");
			out.println("      else if(i>=0 && i<=8){  ");
			out.println("          if(m_char == ' '){");
			out.println("           alert('spaces cannot be given '); ");
			out.println("           document.Form1.TXT_ID_NO.focus();  ");
			out.println("            ");
			out.println("          }"); 
			out.println("          else if(isNaN(m_char)){ ");
			out.println("           i++; ");
			out.println("           alert('ID No contains an Invalid Character at position '+i+' ?');");
			out.println("           document.Form1.TXT_ID_NO.focus();  ");
			out.println("          }");
			out.println("      }  ");
			out.println("   } ");
			out.println(" }");*/
			//out.println("} ");
			
			out.println("function load_lock(){	"); 
			
			/*			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_POSTAL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SECTOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_COMMISSION_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_COMMISSION_AMOUNT.disabled=true;"); 
*/
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_broker_edit';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_broker';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
				//out.println("	alert(validate_data())");
			out.println("before_submit();"); 
		
			out.println("}"); 
			
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_broker\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Brokers - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Brokers - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.TXT_BROKER_CODE.disabled=true;");  // thamali 2013.09.11
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_BROKER_CODE.disabled=false;");  // thamali 2013.09.11
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_POSTAL_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_MOBILE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_FAX_NO.disabled=true;"); 
			out.println("document.Form1.TXT_SECTOR_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_COMMISSION_RATE.disabled=true;"); 
			out.println("document.Form1.TXT_COMMISSION_AMOUNT.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.TXT_BROKER_CODE.disabled=false;");  // thamali 2013.09.11
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.hid_save.value=\"Modify\";");

			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value=\"Deactivate\";");

			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save.value=\"Reactivate\";");

			out.println("}else if(m_val==\"BLAK\"){");  
			out.println("document.Form1.hid_status.value=\"Blaklist\";");
			out.println("document.Form1.hid_save.value=\"Blaklist\";");

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
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();"); //Comment by Chandana on 15/05/2007
      out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			//out.println("if(city_arry.length==0 && document.Form1.hid_val.value=='t1' || document.Form1.hid_val.value=='t3') {");//**
			out.println("document.Form1.TXT_BROKER_CODE.value='';"); 
			out.println("document.Form1.TXT_BROKER_CODE.focus();"); 
			
			out.println("document.Form1.TXT_TITLE.value='MR';"); 
			out.println("document.Form1.TXT_FIRST_NAME.value='';");  
			out.println("document.Form1.TXT_LAST_NAME.value='';"); 
			out.println("document.Form1.TXT_ID_NO.value='';"); 
			out.println("document.Form1.TXT_ADDRESS1.value='';"); 
			out.println("document.Form1.TXT_ADDRESS2.value='';"); 
			out.println("document.Form1.TXT_LOCATION_CODE.value='';"); 
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_POSTAL_CODE.value='';"); 
			out.println("document.Form1.TXT_CONTACT_NO.value='';"); 
			out.println("document.Form1.TXT_MOBILE_NO.value='';"); 
			out.println("document.Form1.TXT_FAX_NO.value='';"); 
			out.println("document.Form1.TXT_SECTOR_CODE.value='';"); 
			out.println("document.Form1.TXT_COMMISSION_RATE.value='';"); 
			out.println("document.Form1.TXT_COMMISSION_AMOUNT.value='';"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"1\"){");
			out.println("document.Form1.TXT_LOCATION_CODE.value='';"); 
			// Added By Samitha Kulatilaka On 2009-10-15 (2 Lines)
			out.println("document.Form1.TXT_CITY_CODE.value='';"); 
			out.println("document.Form1.TXT_POSTAL_CODE.value='';"); 
			out.println("document.Form1.TXT_LOCATION_CODE.focus();"); 
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println("document.Form1.TXT_SECTOR_CODE.value='';"); 
			out.println("document.Form1.TXT_SECTOR_CODE.focus();"); 
			out.println("}");
			out.println("}");
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("bttn_pres=1");

			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[7];"); 

			
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_POSTAL_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_POSTAL_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("bttn_pres=4");
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_SECTOR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SECTOR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_SECTOR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("bttn_pres=99");
			
			//out.println("if");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			//out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\" ||document.Form1.SCREEN_NAME.value==\"REAT\" ) { ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\" ||document.Form1.SCREEN_NAME.value==\"RACT\" ) { "); // added by udara 10-06-2015
			out.println("       m_sql = \"m_help_TXT_BROKER_CODE_sql_New\";"); 
			out.println("       if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("           m_criteria = document.Form1.TXT_BROKER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("       } ");
			//out.println("       else if(document.Form1.SCREEN_NAME.value==\"REAT\"){");
			out.println("       else if(document.Form1.SCREEN_NAME.value==\"RACT\"){"); // added by udara 10-06-2015
			out.println("           m_criteria = document.Form1.TXT_BROKER_CODE.value+\"@\"+\"N@\";"); 
			out.println("       }else{}"); 
			out.println("    }else{");
			out.println("      m_sql = \"m_help_TXT_BROKER_CODE_BLAK_sql_New\";");
			out.println("      m_criteria = document.Form1.TXT_BROKER_CODE.value+\"@\";"); 
			out.println("     }"); 
            
			//out.println("    HelpBox('1','10','13');"); 
			out.println("    HelpBox('1','10','12');"); 
			out.println("}"); 

      out.println(" function assign_help_status(obj){");
			//out.println(" alert('ok');");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("}");
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_BROKER_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_POSTAL_CODE.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_CONTACT_NO.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_MOBILE_NO.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_FAX_NO.value=oBj.valout[14];"); 
			out.println("    document.Form1.TXT_SECTOR_CODE.value=oBj.valout[15];"); 
			out.println("    document.Form1.TXT_COMMISSION_RATE.value=oBj.valout[16];"); 
			out.println("    document.Form1.TXT_COMMISSION_AMOUNT.value=oBj.valout[17];"); 
			out.println("    document.Form1.Hid_payee_code.value=oBj.valout[18];"); 
			

			out.println("}"); 
			
				out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); //m_help_TXT_BROKER_CODE_sql
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
									
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_help_TXT_BROKER_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_BROKER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			// added by udara 31-10-2014
			out.println("function View_all_blacklist(){");	
			out.println("    m_sql = \"m_help_TXT_BROKER_CODE_BLACKLIST_sql\";");
			out.println("    m_criteria = document.Form1.TXT_BROKER_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			// end by udara 31-10-2014
			
			out.println("function check_amt(){");	
			out.println("if(document.Form1.TXT_COMMISSION_RATE.value!=''){");
			out.println("alert('Please enter either rate or amount')");
			out.println("document.Form1.TXT_COMMISSION_AMOUNT.value=''");
			//out.println("document.Form1.TXT_COMMISSION_AMOUNT.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_rate(){");	
			out.println("if(document.Form1.TXT_COMMISSION_AMOUNT.value!=''){");
			out.println("alert('Please enter either rate or amount')");
			out.println("document.Form1.TXT_COMMISSION_RATE.value=''");
			//out.println("document.Form1.TXT_COMMISSION_RATE.focus()");
			out.println("return false;"); 
			out.println("}"); 
			out.println("}"); 
						
			out.println("function check_number_precent(obj,size){");
			out.println("if(obj.value!='' && obj.value!='-' ) ");
			out.println("if(isnumberok(obj,size)){ ");
			
			out.println("if( parseInt(obj.value) <= 100 ){ ");
			out.println("format_number(obj,size) ;");
			out.println("}");
			out.println("else");
			out.println("{");
			out.println("alert('Number can not exceed 100'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("} ");
			out.println("} ");
			

			out.println("</script>"); 
			//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">");  // load_screen_status(\"EDIT\") load_roll_value(\"Edit\");
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_screen_status('EDIT'),load_roll_value('Edit')\">");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_AD_BROKER\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_payee_code' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Brokers</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\" disabled ></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  // commented by udara 10-04-2015
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\" disabled ></td>");  // added by udara 10-04-2015
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Blacklist\");' onClick='load_screen_status(\"BLAK\")' value=\"Black-list\"></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all_blacklist()' value=\"View-Blacklist\"></td>"); // added by udara 31-10-2014
			out.println("<td width='10%'></td>");  
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

			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_BROKER_CODE'  class=div_input>Broker Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BROKER_CODE' maxlength='10' size='10' disabled onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_BROKER_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TITLE'  class=div_input>Title *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			
			out.println("<td width='20%' >Title *</td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DEFAULT_VALUE' maxlength='1' size='1'></td>"); 
			out.println("<td width='40%' ><select class='txt_input' type='text' name='TXT_TITLE' maxlength='1' size='1'>");  
			out.println("<option value=\"MR\" selected>Mr</option>");			
			out.println("<option value=\"MRS\" >Mrs</option>");		
			out.println("<option value=\"MISS\" >Miss</option>");		
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='*%'></td>"); 
			
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>First Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' style=\"width:250px;\" maxlength='200' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>Last Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' style=\"width:250px;\" maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_ID_NO'  class=div_input>ID No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='12' size='10'onblur=\"val_nic(this),assign_help_status('H6'),makeRequest(document.Form1.TXT_ID_NO)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS1'  class=div_input>Address 1 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS1' style=\"width:250px;\" maxlength='100' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_ADDRESS2'  class=div_input>Address 2 *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS2' style=\"width:250px;\" maxlength='100' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Location Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H2'),makeRequest1(document.Form1.TXT_LOCATION_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr>"); 
			out.println("<td width='30%' >Country Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_COUNTRY_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H5'),makeRequest1(document.Form1.TXT_COUNTRY_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_COUNTRY_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr>"); 

			out.println("<td width='20%' >City Code</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H3'),makeRequest(document.Form1.TXT_CITY_CODE)\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_2()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='20%' >Postal Code</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_POSTAL_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H4'),makeRequest(document.Form1.TXT_POSTAL_CODE)\" disabled></td>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_POSTAL_CODE' value=\"Help\" onClick=\"help_button_3()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' >Contact No </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTACT_NO' maxlength='60' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='20%' >Mobile No </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MOBILE_NO' maxlength='30' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			//out.println("<td width='20%' >Fax No </td>");  // commented by udara 28-04-2022
			out.println("<td width='20%' >Account Number </td>"); // added by udara 28-04-2022
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FAX_NO' maxlength='60' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='20%' >Sector Code</td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SECTOR_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H5'),makeRequest(document.Form1.TXT_SECTOR_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SECTOR_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			// Modified by Thamali Jayatunga on 2009.10.12, Replace maxlength 22 with 5.
			out.println("<td width='20%' >Commission Rate </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_COMMISSION_RATE' maxlength='5' size='22' onblur='check_rate(),val_com_rate(this.value)' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			// Modified by Thamali Jayatunga on 2009.10.12, Replace maxlength 21 with 25.
			out.println("<td width='20%' >Commission Amount </td>"); 
			out.println("<td width='40%' ><input class='txt_input' style='{text-align=right}' type='text' name='TXT_COMMISSION_AMOUNT' maxlength='25' size='22' onblur='check_amt(),val_com_amount(this.value)' ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>Vat Reg No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_VAT_REG_NO' maxlength='20' size='3' onblur=\"\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_VALIDITY'  class=div_input>WHT</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_WHT' maxlength='5' size='3' onblur=\"check_number_precent(this,3)\"></td>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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
