
//--
//ID - 1.11 Employee Creation Process
//SCREEN NAME:SYSTEM ADMINISTRATION - EMPLOYEE
//CREATED BY:M.M. WICKRAMASEKARA
//DATE/TIME:2006.07.19
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_employee extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Employees</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H1'){");
			out.println("				alert('Record already exists.');");
			out.println("				new_window();");
			out.println("			}");
			out.println("		  else	if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_EMP_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_EMP_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
			out.println("       help_update();");
			//out.println("				alert('Invalid Code,Use Help');");
			//out.println("				document.Form1.TXT_EMP_CODE.value='';");
			//out.println("       document.Form1.TXT_EMP_CODE.focus();");
			//out.println("				new_window();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H2'&& document.Form1.TXT_LOCATION_CODE.value !='' ){");
			out.println("       help_button_1();");
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_LOCATION_CODE.value='';");
			//out.println("       document.Form1.TXT_LOCATION_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H3'&& document.Form1.TXT_AREA_CODE.value !='' ){");
			out.println("       help_button_2();");
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_AREA_CODE.value='';");
			//out.println("       document.Form1.TXT_AREA_CODE.focus();");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length > 0 && document.Form1.hid_help_status.value == 'H3'&& document.Form1.TXT_AREA_CODE.value !='' ){");
			out.println("       assign_help_status('H9');");
			out.println("       makeRequest(document.Form1.TXT_AREA_CODE);");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H4'&& document.Form1.TXT_CITY_CODE.value !='' ){");
			out.println("       help_button_3();");
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_CITY_CODE.value='';");
			//out.println("       document.Form1.TXT_CITY_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H5'&& document.Form1.TXT_DESIGNATION_CODE.value !='' ){");
			out.println("       help_button_4();");
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_DESIGNATION_CODE.value='';");
			//out.println("       document.Form1.TXT_DESIGNATION_CODE.focus();");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length > 0 && document.Form1.hid_help_status.value == 'H5'&& document.Form1.TXT_DESIGNATION_CODE.value !='' ){");
			out.println("       assign_help_status('H8');");
			out.println("       makeRequest(document.Form1.TXT_DESIGNATION_CODE);");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length == 0 && document.Form1.hid_help_status.value == 'H6'&& document.Form1.TXT_DIVISION_CODE.value !='' ){");
			out.println("       help_button_5();");
			//out.println("				alert('Invalid Code');");
			//out.println("				document.Form1.TXT_DIVISION_CODE.value='';");
			//out.println("       document.Form1.TXT_DIVISION_CODE.focus();");
			out.println("			}");
			out.println("		  else	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H7'){");
			out.println("				alert('ID No Already Exists.');");
			out.println("				document.Form1.TXT_ID_NO.value='';");
			out.println("       document.Form1.TXT_ID_NO.focus();");
			out.println("			}");
			
			out.println("		  else	if(document.Form1.hid_help_status.value == 'H8' ){");
			out.println("				document.Form1.TXT_DIVISION_CODE.value=data_vec[1]; ");
			out.println("			}");
			out.println("		  else	if(document.Form1.hid_help_status.value == 'H9' ){");
			out.println("				document.Form1.TXT_CITY_CODE.value=data_vec[1]; ");
			out.println("			}");
			
			out.println("		  else	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H10'){");
			out.println("				alert('Record already exists.');"); 
			out.println("       help_new_desc();");
			out.println("       document.Form1.TXT_FIRST_NAME.value=\"\" ;"); 
			out.println("       document.Form1.TXT_LAST_NAME.value=\"\" ;"); 
			out.println("			}");
			
			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if((document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"RACT\") && (document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"NEW\"))");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value==\"NEW\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee1&data_val=\"+obj.value;");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value==\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H2' && document.Form1.SCREEN_NAME.value!=\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H2' && document.Form1.SCREEN_NAME.value==\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H3' && document.Form1.SCREEN_NAME.value!=\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_area&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H3' && document.Form1.SCREEN_NAME.value==\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_area&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H4'  && document.Form1.SCREEN_NAME.value!=\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H4'  && document.Form1.SCREEN_NAME.value==\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5' && document.Form1.SCREEN_NAME.value!=\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_designation&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H5' && document.Form1.SCREEN_NAME.value==\"RACT\" )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_designation&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H6' && document.Form1.SCREEN_NAME.value!=\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_division&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H6' && document.Form1.SCREEN_NAME.value==\"RACT\"  )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_division&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee_id&data_val=\"+obj.value;");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H8')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee_fill_division&data_val=\"+obj.value;");
			out.println("else");
			out.println("if(document.Form1.hid_help_status.value == 'H9')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee_fill_city&data_val=\"+obj.value;");
			
			out.println("    else if(document.Form1.hid_help_status.value=='H10' && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FIRST_NAME.value!=\"\" &&  document.Form1.TXT_LAST_NAME.value!=\"\") {");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee_chk_name&fname=\"+obj.value+\"&lname=\"+document.Form1.TXT_LAST_NAME.value ;");
			out.println("}");
		//	out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

      out.println("function assign_data(data_vec){ ");
			out.println("    document.Form1.TXT_EMP_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_TITLE.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_ADDRESS.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_AREA_CODE.value=data_vec[6];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=data_vec[7];"); 
			out.println("    document.Form1.TXT_CONTACT_NO.value=data_vec[8];"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=data_vec[9];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=data_vec[10];"); 
			out.println("    document.Form1.TXT_EPF_NO.value=data_vec[11];"); 
			out.println("    document.Form1.TXT_ID_NO.value=data_vec[12];"); 
			
			
			
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_EMP_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_EMP_CODE.style.color='red';");
			//out.println("alert('Please Enter Employee Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TITLE.value==\"\"){  "); 
			out.println("DIV_TXT_TITLE.style.color='red';");
			//out.println("alert('Please Enter Title.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FIRST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FIRST_NAME.style.color='red';");
			//out.println("alert('Please Enter First Name.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LAST_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_LAST_NAME.style.color='red';");
			//out.println("alert('Please Enter Last Name.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ADDRESS.value==\"\"){  "); 
			out.println("DIV_TXT_ADDRESS.style.color='red';");
			//out.println("alert('Please Enter Address.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			//out.println("alert('Please Enter Location Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESIGNATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_DESIGNATION_CODE.style.color='red';");
			//out.println("alert('Please Enter Designation Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DIVISION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_DIVISION_CODE.style.color='red';");
			//out.println("alert('Please Enter Division Code.'); ");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_ID_NO.value==\"\"){  "); 
			out.println("DIV_TXT_ID_NO.style.color='red';");
			//out.println("alert('Please Enter ID No.'); ");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			
			out.println("m_option = document.Form1.hid_status.value;"); 
			out.println("if(m_option=='New') {");
			out.println("m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Edit') {");
			out.println("m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Deactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Deactivate?'; ");
			out.println("		}"); 
			out.println("else if(m_option=='Reactivate') {");
			out.println("m_sav_msg = 'Are you sure you want to Reactivate?'; ");
			out.println("		}"); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_employee';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 
			
/*			out.println("function val_nic(object){ ");
			out.println(" m_objval = object; ");
			out.println(" m_length = object.toString().length; ");
			out.println(" if(m_length<10) {");
			out.println("  alert(' ID No length should be 10'); ");
			out.println("  document.Form1.TXT_ID_NO.focus();  ");
			out.println("   ");
			out.println(" }");
			out.println(" else {");
			out.println("   for(var i = 0; i<m_length; i++){");
			out.println("    m_char = m_objval.charAt(i); ");
			out.println("      if(i==9){ ");
			out.println("        if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X'){ ");
			out.println("          alert('ID No Final Character should be X or V '); ");
			out.println("          document.Form1.TXT_ID_NO.focus();  ");
			out.println("          ");
			out.println("        }  ");
			out.println("      } ");
			out.println("      else if(i>=0 && i<=8){  ");
			//out.println("           alert('m_char'+m_char);");
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
			out.println(" }");
			out.println("} "); */
//added by Prabash on 19-08-2011--------------------------------**

out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
		//	out.println("alert('aasf'+document.Form1.SCREEN_NAME.value);");
			out.println("var date1='' ");
			out.println("var date2='' ");
			out.println("var date3='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.BR_DAY.value=v_date;");
			out.println("     document.Form1.BR_MONTH.value=v_month;");
			out.println("     document.Form1.BR_YEAR.value=val;");
			out.println("	}");
			
			 out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.JO_DAY.value=v_date;");
			out.println("     document.Form1.JO_MONTH.value=v_month;");
			out.println("     document.Form1.JO_YEAR.value=val;");
			out.println("	}");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("		v_date=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_date.length<2)");
			out.println("			v_date=0+v_date");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("			v_month=val.substr(0,val.indexOf('-'));");
			out.println("		if(v_month.length<2)");
			out.println("			v_month=0+v_month");
			out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.RE_DAY.value=v_date;");
			out.println("     document.Form1.RE_MONTH.value=v_month;");
			out.println("     document.Form1.RE_YEAR.value=val;");
			out.println("	}");
			out.println("}");


//--------------------------------------------------------------**
//Added by Prabash on 19-08-2011--------------------------------**
out.println("function check_date(objDD,objMM,objYY){ ");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");
//--------------------------------------------------------------**
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_employee';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_employee';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_employee\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Employees - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Employees - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_TITLE.disabled=true;"); 
			out.println("document.Form1.TXT_FIRST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LAST_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_ADDRESS.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_AREA_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_CONTACT_NO.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_EPF_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ID_NO.disabled=true;"); 
			out.println("document.Form1.BR_DAY.disabled=true;"); 
			out.println("document.Form1.BR_MONTH.disabled=true;"); 
			out.println("document.Form1.BR_YEAR.disabled=true;"); 
			out.println("document.Form1.JO_DAY.disabled=true;"); 
			out.println("document.Form1.JO_MONTH.disabled=true;"); 
			out.println("document.Form1.JO_YEAR.disabled=true;"); 
			out.println("document.Form1.RE_DAY.disabled=true;"); 
			out.println("document.Form1.RE_MONTH.disabled=true;"); 
			out.println("document.Form1.RE_YEAR.disabled=true;"); 

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
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); //--pra
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
			out.println("		if(document.Form1.hid_help_type.value==\"33\"){"); 
			out.println("		help_new_desc_value_assign_33();"); 
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
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
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
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_AREA_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_AREA_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_AREA_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[4];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_DESIGNATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESIGNATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[5];"); 
			out.println("}"); 

			out.println("function help_button_5() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_TXT_DIVISION_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DIVISION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_5() {"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_new_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"33\";"); 
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_NAME_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FIRST_NAME.value+\"@\"+document.Form1.TXT_LAST_NAME.value+\"@\";"); 
			out.println("    HelpBox('1','10','10');"); 
			out.println("}"); 
			
			
			out.println("function help_new_desc_value_assign_33() {"); 
			out.println("}");

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_EMP_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_EMP_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','10');"); 
			out.println("}"); 
 
     
			
			out.println(" function assign_help_status(obj){");
			//out.println(" alert('ok');");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("}");
			out.println("function help_update_value_assign_99() {"); 
			
			out.println("var dobdate=oBj.valout[16]; "); 	// added by Prabash on 19-08-2011
			out.println("var jodate=oBj.valout[17]; ");		// added by Prabash on 19-08-2011
			out.println("var redate=oBj.valout[18]; ");		// added by Prabash on 19-08-2011
			
			out.println("    document.Form1.TXT_EMP_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TITLE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_ADDRESS.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_AREA_CODE.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[9];"); 
			out.println("    document.Form1.TXT_CONTACT_NO.value=oBj.valout[10];"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=oBj.valout[11];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[12];"); 
			out.println("    document.Form1.TXT_EPF_NO.value=oBj.valout[13];"); 
			out.println("    document.Form1.TXT_ID_NO.value=oBj.valout[14];"); 
			
		// ----------added by Prabash on 22-08-2011	--------------------**
		
			out.println ("if (dobdate!= 'null'){");
			out.println(" 	 document.Form1.BR_DAY.value=dobdate.substring(8,10);"); 
			out.println(" 	 document.Form1.BR_MONTH.value=dobdate.substring(5,7);"); 
			out.println(" 	 document.Form1.BR_YEAR.value=dobdate.substring(0,4);"); 
			out.println("	}");
			
				out.println ("else{");
				out.println(" 	 document.Form1.BR_DAY.value='';"); 
				out.println(" 	 document.Form1.BR_MONTH.value='';"); 
				out.println(" 	 document.Form1.BR_YEAR.value='';"); 
				out.println("	}");
			
			out.println ("if (jodate!= 'null'){");
			out.println(" 	 document.Form1.JO_DAY.value=jodate.substring(8,10);"); 
			out.println(" 	 document.Form1.JO_MONTH.value=jodate.substring(5,7);"); 
			out.println(" 	 document.Form1.JO_YEAR.value=jodate.substring(0,4);"); 
			out.println("	}");
			
				out.println ("else{");
				out.println(" 	 document.Form1.JO_DAY.value='';"); 
				out.println(" 	 document.Form1.JO_MONTH.value='';"); 
				out.println(" 	 document.Form1.JO_YEAR.value='';"); 
				out.println("	}");
			
			out.println ("if (redate!= 'null'){");
			out.println(" 	 document.Form1.RE_DAY.value=redate.substring(8,10);"); 
			out.println(" 	 document.Form1.RE_MONTH.value=redate.substring(5,7);"); 
			out.println(" 	 document.Form1.RE_YEAR.value=redate.substring(0,4);"); 
			out.println("	}");
			
				out.println ("else{");
				out.println(" 	 document.Form1.RE_DAY.value='';"); 
				out.println(" 	 document.Form1.RE_MONTH.value='';"); 
				out.println(" 	 document.Form1.RE_YEAR.value='';"); 
				out.println("	}");
			//-------------------------------------------------------**	
			out.println("}"); 
			
			out.println("function clear_data() {");//**
			
			
		//	out.println("if( document.Form1.hid_help_status.value == 'H1' && document.Form1.SCREEN_NAME.value!=\"NEW\") {");//**
		/*	out.println("document.Form1.TXT_EMP_CODE.value='';"); 
			out.println("document.Form1.TXT_EMP_CODE.focus() ;"); 
			out.println("document.Form1.TXT_FIRST_NAME.value='';"); 
			out.println("document.Form1.TXT_LAST_NAME.value='';"); 
			*/
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("    document.Form1.TXT_EMP_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_TITLE.value=\"MR\";"); 
			out.println("    document.Form1.TXT_FIRST_NAME.value=\"\";"); 
			out.println("    document.Form1.TXT_LAST_NAME.value=\"\";"); 
			out.println("    document.Form1.TXT_ADDRESS.value=\"\";"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_AREA_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CONTACT_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_EPF_NO.value=\"\";"); 
			out.println("    document.Form1.TXT_ID_NO.value=\"\";");
			out.println(" }");
			out.println("if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=\"\";"); 
			out.println(" }");
			
			out.println("if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("    document.Form1.TXT_AREA_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=\"\";"); 

			out.println(" }");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=\"\";"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=\"\";"); 

			out.println(" }");
			out.println("}");
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      //out.println("window.close();"); //Comment by Chandana on 15/05/2007
      out.println(" }");
			
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No+\"&Screen_Name=EMP\", oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_EMP_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); //Added by Prabash 0n 19-08-2011
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Employees</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
      out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' border='0' class='table'>"); 

			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_EMP_CODE'  class=div_input>Employee Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EMP_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_EMP_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_TITLE'  class=div_input>Title *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TITLE' maxlength='5' size='5'></td>"); 
			out.println("<td width='40%' ><select name=\"TXT_TITLE\" class=\"txt_input\" >");
			out.println("<OPTION value=\"MR\">Mr</option>");
			out.println("<OPTION value=\"MISS\">Miss</option>");
			out.println("<OPTION value=\"Mrs\">Mrs</option>");
			out.println("<OPTION value=\"REV\">Rev</option>");
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_FIRST_NAME'  class=div_input>First Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIRST_NAME' maxlength='200' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LAST_NAME'  class=div_input>Last Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LAST_NAME' maxlength='50' size='20' onblur=\"assign_help_status('H10'),makeRequest(document.Form1.TXT_FIRST_NAME)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ADDRESS'  class=div_input>Address *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ADDRESS' maxlength='200' size='20'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Location Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H2'),makeRequest(document.Form1.TXT_LOCATION_CODE)\"  >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >Area Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AREA_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H3'),makeRequest(document.Form1.TXT_AREA_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_AREA_CODE' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' >City Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CITY_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H4'),makeRequest(document.Form1.TXT_CITY_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_CITY_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >Contact No </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTACT_NO' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_DESIGNATION_CODE'  class=div_input>Designation Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DESIGNATION_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H5'),makeRequest(document.Form1.TXT_DESIGNATION_CODE)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_DESIGNATION_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Division Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='10'onblur=\"assign_help_status('H6'),makeRequest(document.Form1.TXT_DIVISION_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_DIVISION_CODE' value=\"Help\" onClick=\"help_button_5()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' >EPF No </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_EPF_NO' maxlength='15' size='15'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 

			out.println("<td width='30%' ><DIV id='DIV_TXT_ID_NO'  class=div_input>ID No *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ID_NO' maxlength='12' size='10' onblur=\"val_nic(this),assign_help_status('H7'),makeRequest(document.Form1.TXT_ID_NO)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			//----------Added by Prabash on 19-08-2011---------Support #2973----**
			
			out.println("<tr class=tr_input>");
			out.println("<td width='30%' ID=VDATE>Date Of Birth</td>");
			out.println("<td width='40%' ><input name=\"BR_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.BRDAY,document.Form1.BR_MONTH,document.Form1.BR_YEAR)> ");
			out.println("    <input name=\"BR_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.BR_DAY,document.Form1.BR_MONTH,document.Form1.BR_YEAR)> ");
			out.println("    <input name=\"BR_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.BR_DAY,document.Form1.BR_MONTH,document.Form1.BR_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");
			out.println("<td width='*%'></td>");
			
			out.println("<tr >");	
			out.println("<td width='30%' ID=VDATE>Date Of Joined</td>");
			out.println("<td width='40%' ><input name=\"JO_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.JO_DAY,document.Form1.JO_MONTH,document.Form1.JO_YEAR)> ");
			out.println("    <input name=\"JO_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.JO_DAY,document.Form1.JO_MONTH,document.Form1.JO_YEAR)> ");
			out.println("    <input name=\"JO_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.JO_DAY,document.Form1.JO_MONTH,document.Form1.JO_YEAR)><a href style='{cursor:hand; }'onclick=load_calendar('2')>   Calendar</a> ");
			out.println("<td width='*%'></td>");
			
			out.println("<tr >");	
			out.println("<td width='30%' ID=VDATE>Date Of Resign</td>");
			out.println("<td width='40%' ><input name=\"RE_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.RE_DAY,document.Form1.RE_MONTH,document.Form1.RE_YEAR)> ");
			out.println("    <input name=\"RE_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.RE_DAY,document.Form1.RE_MONTH,document.Form1.RE_YEAR)> ");
			out.println("    <input name=\"RE_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.RE_DAY,document.Form1.RE_MONTH,document.Form1.RE_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("<td width='*%'></td>");
			out.println("</tr >");
	
			//---------------------------------------------------------------------**
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>");
/*		//--------------------------------------------------**
			//	out.println("<table align='center' width='100%' class='table' border='1'>"); 	
				out.println("<table align='center' width='100%' border='1' class='table'>"); 
			
			out.println("<tr class=tr_input>");
			out.println("<td width='30%' ID=VDATE>Date Of Birth</td>");
			out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
		out.println("<tr >");	
			//	out.println("</td>");
		//	out.println("<td width='2%'></td>"); 
			out.println("<td width='30%' ID=VDATE>Date Of Joined</td>");
			out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
		//	out.println("</td>");
		out.println("<tr >");	
		//	out.println("<td width='2%'></td>"); 
			out.println("<td width='30%' ID=VDATE>Date Of Resign</td>");
			out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
		//	out.println("</td>");
		out.println("</tr >");
	
		
			out.println("</table>");
			//--------------------------------------------------***/
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
		//	out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_nic.js'></SCRIPT>"); 
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
