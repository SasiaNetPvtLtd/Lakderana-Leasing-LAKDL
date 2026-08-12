//ID         :1.9 USER CREATION PROCESS
//SCREEN NAME:SYSTEM ADMINISTRATION - USER
//CREATED BY: NUWAN DE SILVA
//DATE/TIME:24-07-06
//NOTES:


// Modified by Mahela  on : 19-03-2007
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_user extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Statement stmt;
	public ResultSet rs;
	Connection conn;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_user_id = "";
			String m_close_status  = "N";

			
			if(req.getParameter("user_id")!=null){
					m_user_id = req.getParameter("user_id"); 
					m_close_status = req.getParameter("close_status");
			}
			
			stmt=conn.createStatement();
			
			String m_minimum_length="",m_maximum_length="",m_lowercase_char="",m_uppercase_char="",m_numeric_char="";
			String m_numeric_embedded="",m_special_char="",m_special_char_embedded="",m_password_change_attempt="";
			String m_allow_user_name="",m_repeat_password="",m_password_change_minimum_gap="",m_password_expiration_days="",m_warning_message_days="";
			
			      rs = stmt.executeQuery(" " +
                    "   SELECT  " +
										"		NVL(MINIMUM_LENGTH,0), " +
										"		NVL(MAXIMUM_LENGTH,0), " +
										"		NVL(LOWERCASE_CHAR,0), " +
										"		NVL(UPPERCASE_CHAR,0), " +
										"		NVL(NUMERIC_CHAR,0), " +
										"		NUMERIC_EMBEDDED, " +
										"		NVL(SPECIAL_CHAR,0), " +
										"		SPECIAL_CHAR_EMBEDDED, " +
										"		NVL(PASSWORD_CHANGE_ATTEMPT,0), " +
										"		ALLOW_USER_NAME, " +
										"		NVL(REPEAT_PASSWORD,0), " +
										"		NVL(PASSWORD_CHANGE_MINIMUM_GAP,0), " +
										"		NVL(PASSWORD_EXPIRATION_DAYS,0), " +
										"		NVL(WARNING_MESSAGE_DAYS,0) " +
                    "   FROM " + m_schema_name + ".CO_CO_MAS_PASSWORD_POLICY A " );
										
     if(rs.next()){
			m_minimum_length              = rs.getString(1);
			m_maximum_length              = rs.getString(2);
			m_lowercase_char              = rs.getString(3);
			m_uppercase_char              = rs.getString(4);
			m_numeric_char                = rs.getString(5);
			m_numeric_embedded            = rs.getString(6);
			m_special_char                = rs.getString(7);
			m_special_char_embedded       = rs.getString(8);
			m_password_change_attempt     = rs.getString(9);
			m_allow_user_name             = rs.getString(10);
			m_repeat_password             = rs.getString(11);
			m_password_change_minimum_gap = rs.getString(12);
			m_password_expiration_days    = rs.getString(13);
			m_warning_message_days        = rs.getString(14);
			}										
			

			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Users</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println(" var m_minimum_length              ='"+m_minimum_length+"';  ");
			out.println(" var m_maximum_length              ='"+m_maximum_length+"';  ");
			out.println(" var m_lowercase_char              ='"+m_lowercase_char+"';  ");
			out.println(" var m_uppercase_char              ='"+m_uppercase_char+"';  ");
			out.println(" var m_numeric_char                ='"+m_numeric_char+"';  ");
			out.println(" var m_numeric_embedded            ='"+m_numeric_embedded+"';  ");
			out.println(" var m_special_char                ='"+m_special_char+"';  ");
			out.println(" var m_special_char_embedded       ='"+m_special_char_embedded+"';  ");
			out.println(" var m_password_change_attempt     ='"+m_password_change_attempt+"';  ");
			out.println(" var m_allow_user_name             ='"+m_allow_user_name+"';  ");
			out.println(" var m_repeat_password             ='"+m_repeat_password+"';  ");
			out.println(" var m_password_change_minimum_gap ='"+m_password_change_minimum_gap+"';  ");
			out.println(" var m_password_expiration_days    ='"+m_password_expiration_days+"';  ");
			out.println(" var m_warning_message_days        ='"+m_warning_message_days+"';  ");
			
						//validate password
			out.println("function passwordvalidator(obj) {");
			out.println("if(obj.value!=''){");
			out.println("var passed = validatePassword(obj.value, {");
	    out.println("length:   [m_minimum_length, m_maximum_length], ");
	    out.println("lower:    m_lowercase_char,");
	    out.println("upper:    m_uppercase_char,");
	    out.println("numeric:  m_numeric_char,");
	    out.println("special:  m_special_char,");
	    out.println("badWords: [\"password\", \"sasianet\", \"netasset\"],");
	    out.println("badSequenceLength: 0");
      out.println("});");
			
			out.println("if(!passed){");
			out.println("var msg=\"The password supplied does not meet the minimum complexity requrements.please select "+
			                      "another password that match all the following criteria. is at least \"+m_minimum_length+\" characters; "+
												    //"has not been used in the previous \"+m_password_change_minimum_gap+\" passwords; "+
														//"must not have changed with the last \"+m_password_change_minimum_gap+\" days; "+
														//"does not contain your username;"+
														"contains at least three of the following character groups.;"+
														"at least \"+m_uppercase_char+\" english characters(A throu Z);"+
														"at least \"+m_lowercase_char+\" english lowercase characters(a throu z);"+
														"at least \"+m_numeric_char+\"   numberic(0 throu 9);"+
														"at least \"+m_special_char+\"   non-apphabetic characters(such as !,#,%);"+
														"type the requirements in oth text boxes.\"");
												
			out.println("alert(msg);");
			out.println("obj.value='';");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println(" function fill_user(){");
			out.println("    document.Form1.HID_CLOSE_STS.value='"+m_close_status+"';");
			out.println(" 	 if(\""+m_user_id+"\" !=\"\" ){");
			out.println("    	document.Form1.TXT_USER_ID.value =\""+m_user_id+"\" ; ");
			out.println("    	load_screen_status(\"EDIT\");");
			out.println("		 	assignState('M1')");
			out.println("		 	makeRequest(document.Form1.TXT_USER_ID)");
			out.println("  	 }");
			out.println("}");
			
			out.println("function close_screen() {");
			out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
			out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		      window.close();"); 
			out.println("		     }"); 
			out.println("		 }"); 
			out.println("		else { "); 
			out.println("		     close_window();"); 
			out.println("		}"); 
			out.println("}");
			

			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exists.');");
			out.println("				;");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M7'){");
			out.println("				alert('Record already exists.');");
			out.println("       help_new_desc(); ");
			out.println("       document.Form1.TXT_NAME.value=\"\" ; ");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				help_update();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.TXT_LOCATION_CODE.value!=\"\"){");
			out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.TXT_LOCATION_CODE.value=\"\" "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length== 0 &&  document.Form1.hid_chk_status.value=='M8' && document.Form1.TXT_EMP_ID.value!=\"\"){");
			out.println("				help_button_2();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.TXT_DIVISION_CODE.value!=\"\"){");
			out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.TXT_DIVISION_CODE.value=\"\" "); 
			out.println("       document.Form1.TXT_DIVISION_CODE.focus();  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M5' && document.Form1.TXT_DESIGNATION_CODE.value!=\"\"){");
			out.println("				alert('Invalid Record, Use Help');");
			out.println("       document.Form1.TXT_DESIGNATION_CODE.value=\"\" "); 
			out.println("       document.Form1.TXT_DESIGNATION_CODE.focus()  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M8' ){");
			out.println("       document.Form1.TXT_LOCATION_CODE.value=data_vec[1] "); 
			out.println("       document.Form1.TXT_DIVISION_CODE.value=data_vec[2] "); 
			out.println("       document.Form1.TXT_DESIGNATION_CODE.value=data_vec[3] "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M6'){");
			out.println("			display_data(data_vec);   "); 
			//out.println("           document.Form1.TXT_USER_ID.focus(); "); // added by udara 22-08-2017 // re commented by udara 07-12-2017
			out.println("			}");
			//added by nuwan de silva on 13-12-2007 -----------------------------------------------------------
			out.println("			else");
			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_USER_TYPE' && document.Form1.TXT_USER_GROUP.value!=\"\" ){");
			out.println("  	 makeRequest1('NEW','M_GROUP');");
			//out.println("       document.Form1.TXT_USER_GROUP.value=data_vec[0] "); 
			//out.println("       load_user_rights(document.Form1.TXT_USER_GROUP); "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length == 0 && document.Form1.hid_chk_status.value=='M_USER_TYPE' && document.Form1.TXT_USER_GROUP.value!=\"\" ){");
			out.println("				help_user_group();");
			out.println("			}");
			
			
			out.println("}");

			
			out.println("function assign_data(data_vec){ ");
			out.println("    document.Form1.TXT_USER_ID.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_NAME.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_USER_TYPE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_EMP_ID.value=data_vec[4];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=data_vec[5];"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=data_vec[6];");
			out.println("    document.Form1.TXT_PASSWORD.value=data_vec[7];");
			out.println("    document.Form1.TXT_CPASSWORD.value=data_vec[7];");
			//out.println("    document.Form1.TXT_USER_GROUP.value=data_vec[8];");  //added by nuwan de silva on 13-12-2007
			out.println("if(data_vec[8]=='-' || data_vec[8]=='null') { ");
			out.println("    document.Form1.TXT_USER_GROUP.value='';");  //added by nuwan de silva on 13-12-2007
			out.println("}else {");
			out.println("    document.Form1.TXT_USER_GROUP.value=data_vec[8];");  //added by nuwan de silva on 13-12-2007
			out.println("}");
			
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			out.println("    makeRequest_rights(document.Form1.TXT_USER_ID)");
			out.println("    }");
			out.println(" ");
			out.println("}");
			
			out.println(" function display_data(data_vec){");
			out.println(" m_size=data_vec.length; ");
			out.println("  k=0; ");
			out.println(" while(m_size>k){");
			out.println("  m_stat = data_vec[k]; ");
			out.println("  m_chke = 'chke_'+data_vec[k+1]; ");
			out.println("  m_chkv = 'chkv_'+data_vec[k+1]; ");	
			out.println(" if(m_stat=='Y'){ ");
			out.println("  document.Form1.elements[m_chke].checked=true; ");
			out.println("  document.Form1.elements[m_chkv].checked=true; ");
			out.println("  document.Form1.elements[m_chke].value='on';");
			out.println("  document.Form1.elements[m_chkv].value='on';");
			out.println(" } ");
			out.println(" else{");
			out.println("  document.Form1.elements[m_chke].checked=false; ");
			out.println("  document.Form1.elements[m_chke].value='off';");
			out.println("  document.Form1.elements[m_chkv].checked=true; ");
			out.println("  document.Form1.elements[m_chkv].value='on';");
			out.println(" }");
			out.println("k=k+2;");
			out.println(" }");
			out.println("}");
			
			  out.println("function makeRequest1(obj,obj1) {");
				//out.println(" alert('ok');  ");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {");
				out.println("http_request = new XMLHttpRequest();");
				out.println("if (http_request.overrideMimeType) {");
				out.println("     http_request.overrideMimeType('text/xml');");
				out.println("}");
				out.println("} else if (window.ActiveXObject) { ");
				out.println("    try {");
				out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
				out.println("    } catch (e) {");
				out.println("        try {");
				out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
				out.println("        } catch (e) {}");
				out.println("    }");
				out.println("}");
				out.println("if (!http_request) {");
				out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
				out.println("    return false;");
				out.println("}");
				out.println("url=\"\";");
				out.println("if(obj1==\"M1\"){");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+obj.value;");
				out.println("}else{");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"users_frame2?chksql=SCORE_DETAILS&model=\"+obj;");
				//out.println("window.open(url);");

				out.println("}");
				out.println("http_request.onreadystatechange = function() {");
				out.println("alertContents(http_request,1,obj1); ");
				//out.println("window.open(url);");
				out.println("};");
				
				
				out.println("http_request.open('GET',url, true);");
				out.println("http_request.send(null);");
				out.println("}");
				
				
				// added by udara 20-01-2016
				
				out.println("function makeRequest2(obj,obj1,obj2) {");
				//out.println(" alert('ok');  ");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {");
				out.println("http_request = new XMLHttpRequest();");
				out.println("if (http_request.overrideMimeType) {");
				out.println("     http_request.overrideMimeType('text/xml');");
				out.println("}");
				out.println("} else if (window.ActiveXObject) { ");
				out.println("    try {");
				out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
				out.println("    } catch (e) {");
				out.println("        try {");
				out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
				out.println("        } catch (e) {}");
				out.println("    }");
				out.println("}");
				out.println("if (!http_request) {");
				out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
				out.println("    return false;");
				out.println("}");
				out.println("url=\"\";");
				out.println("if(obj1==\"M1\"){");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+obj.value;");
				out.println("}else{");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"users_frame2?chksql=SCORE_DETAILS&model=\"+obj;");
				//out.println("window.open(url);");

				out.println("}");
				out.println("http_request.onreadystatechange = function() {");
				out.println("alertContents(http_request,1,obj1); ");
				//out.println("window.open(url);");
				
				out.println("    makeRequest_rights(document.Form1.TXT_USER_ID)");
				
				out.println("};");
				
				
				out.println("http_request.open('GET',url, true);");
				out.println("http_request.send(null);");
				out.println("}");
				
				// end by udara 20-01-2016
				
				
				
			 out.println("function check_exec(rownum){ ");
			 out.println("m_chke=\"chke_\"+rownum; ");
			 out.println("m_chke=document.Form1.elements[m_chke];");
			 out.println("m_chk=\"chkv_\"+rownum; ");
			 out.println("m_chk=document.Form1.elements[m_chk];");
			 out.println("if (m_chke.checked==true){");
			 out.println("m_chk.checked=true;");
			 out.println("m_chke.value='on';");	
			 out.println("}else{");
			 out.println("m_chk.checked=false;");
			 out.println("m_chke.value='off';");		
			 out.println("}");			
			 out.println("}");
				
			 out.println("function check_view(rownum){ ");
			 //out.println("m_chke=document.Form1.elements[m_chke];"); //comment by nuwan de silva on 13-12-2007
			 out.println("m_chk=\"chkv_\"+rownum; ");
			 out.println("m_chk=document.Form1.elements[m_chk];");
			 out.println("if (m_chk.checked==true){");
			 out.println("m_chk.value='on';");
			 out.println("}else{");
			 out.println("m_chk.value='off';");
			 out.println("}");			
			 out.println("}");
				
			// thamali 2012.02.13
			out.println("function check_section_wise(obj,count){");
            out.println("var objID=obj.name.substring(3)");
            out.println(" if (document.getElementById(\"CH_\"+objID).value==\"Check All\"){ ");
            out.println("    document.getElementById(\"CH_\"+objID).value=\"Uncheck All\" ");
            out.println("    document.getElementById(\"CH_\"+objID).checked=\"true\" ");
            out.println(" }else if (document.getElementById(\"CH_\"+objID).value==\"Uncheck All\") {");
            out.println("    document.getElementById(\"CH_\"+objID).value=\"Check All\" ");
            out.println("    document.getElementById(\"CH_\"+objID).checked=\"false\" ");
            out.println("}");
			out.println("for (var i=0; i < count; i++ ) {");

            out.println(" if (document.getElementById(\"CH_\"+objID).value==\"Uncheck All\") {");
            out.println("   document.getElementById(\"chkv_\"+objID+\"_\"+i).checked = true;");
            out.println("   document.getElementById(\"chkv_\"+objID+\"_\"+i).value = \"on\" ;");
			out.println("   document.getElementById(\"chke_\"+objID+\"_\"+i).checked = true;");
            out.println("   document.getElementById(\"chke_\"+objID+\"_\"+i).value = \"on\" ;");

            out.println(" }else if (document.getElementById(\"CH_\"+objID).value==\"Check All\") {");
            out.println("   document.getElementById(\"chkv_\"+objID+\"_\"+i).checked = false;");
            out.println("   document.getElementById(\"chkv_\"+objID+\"_\"+i).value = \"off\" ;");
			out.println("   document.getElementById(\"chke_\"+objID+\"_\"+i).checked = false;");
            out.println("   document.getElementById(\"chke_\"+objID+\"_\"+i).value = \"off\" ;");

            out.println("}");
            out.println("};");
            out.println("  }");

				
			
			  out.println("function alertContents(http_request,count,obj) {");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("      	if(http_request.responseText!=\"\"){");
				out.println(" 				m_data=http_request.responseText;");
				out.println("					if(obj==\"M1\"){");
				out.println("						if(m_data==\"Y\"){");
				out.println("							if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("							alert('Record already exsist');");
				out.println("							new_window();");
				out.println("							}");	
				out.println("						}");
				out.println("					}");
				out.println("					else{");
				out.println("						//alert(m_data);");
				out.println("           user_details.innerHTML=''; ");
				out.println("						user_details.innerHTML=m_data;");
				
				out.println("					  if(obj==\"M_GROUP\"){"); //ADDED BY NUWAN DE SILVA 19-12-07
				out.println(" 					load_user_rights(document.Form1.TXT_USER_GROUP);");
				out.println("					   }else {");
				out.println(" 					fill_user();");
				out.println("					}");
				
				out.println("					}");
				out.println("				}");
				out.println("    } else {");
				out.println("        alert('There was a problem with the request.');");
				out.println("    }");
				out.println(" }");
				out.println("}");
			
			out.println("function makeRequest_User() {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"users_frame2?chksql=SCORE_DETAILS&model=NEW\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'NO');");
			out.println("}");
			
			out.println("function makeRequest_rights(obj) {");
			out.println("document.Form1.hid_chk_status.value='M6';");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user_access&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}else if(document.Form1.hid_chk_status.value=='M2'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}else if(document.Form1.hid_chk_status.value=='M3'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}else if(document.Form1.hid_chk_status.value=='M4'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_division&data_val=\"+obj.value+\"&ac_status=Y\";");
      out.println("}else if(document.Form1.hid_chk_status.value=='M5'){");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_designation&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}else if(document.Form1.hid_chk_status.value=='M7' && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_user_name&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}else if(document.Form1.hid_chk_status.value=='M8'){");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_user_fill_empdata&data_val=\"+obj.value+\"&ac_status=Y\";");
			//added by nuwan de silva on 13-12-2007 ______________________________________
			out.println("}else if(document.Form1.hid_chk_status.value=='M_USER_TYPE'){");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_group_user&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_USER_ID.value==\"\"){  "); 
			out.println("DIV_TXT_USER_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_LOCATION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LOCATION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_USER_TYPE.value==\"\"){  "); 
			out.println("DIV_TXT_USER_TYPE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_EMP_ID.value==\"\"){  "); 
			out.println("DIV_TXT_EMP_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DIVISION_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_DIVISION_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			
			// added by udara 07-10-2016
			out.println("else if(document.Form1.TXT_USER_GROUP.value==\"\"){  "); 
			out.println("DIV_TXT_USER_GROUP.style.color='red';");
			out.println("return false;"); 
			out.println("}");
			// end by udara 07-10-2016
			
			out.println("else if(document.Form1.TXT_PASSWORD.value==\"\"){  "); 
			out.println("DIV_TXT_PASSWORD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
      out.println("else if(document.Form1.TXT_CPASSWORD.value==\"\"){  "); 
			out.println("DIV_TXT_CPASSWORD.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
      out.println("else if(document.Form1.TXT_PASSWORD.value != document.Form1.TXT_CPASSWORD.value){"); 
		  out.println("check_pass();");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		m_option = document.Form1.hid_status.value;"); 
			out.println("		if(m_option=='New') {");
			out.println("			m_sav_msg = 'Are you sure you want to Save?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Edit') {");
			out.println("			m_sav_msg = 'Are you sure you want to Modify?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Deactivate') {");
			out.println("			m_sav_msg = 'Are you sure you want to Deactivate?'; ");
			out.println("		}"); 
			out.println("		else if(m_option=='Reactivate') {");
			out.println("			m_sav_msg = 'Are you sure you want to Reactivate?'; ");
			out.println("		}"); 
			out.println("		if(validate_data()){"); 
			out.println("     for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("      document.Form1.elements[i].disabled=false;");
			out.println("     }");
			out.println("		if(confirm(m_sav_msg)){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_user';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		else { "); 
			out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			out.println("		}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_user';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_user';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 

      out.println("function check_pass(){	"); 
			out.println(" if(document.Form1.TXT_PASSWORD.value != document.Form1.TXT_CPASSWORD.value){"); 
			out.println("   alert('The password confirmation does not match ');"); 
      out.println("   document.Form1.TXT_CPASSWORD.value='';");
			out.println(" }"); 
      out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_user\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Users - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Users - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_NAME.disabled=true;"); 
			out.println("document.Form1.TXT_LOCATION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_USER_TYPE.disabled=true;"); 
			out.println("document.Form1.TXT_EMP_ID.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_DESIGNATION_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_EMP_ID.disabled=true;"); 
			out.println("document.Form1.TXT_USER_GROUP.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_USER_GROUP.disabled=true;"); 
			
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

			//new
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
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else"); 
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
			out.println("		assign_group();"); 
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

			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 

			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_USER_ID.value='';"); 
			out.println("document.Form1.TXT_NAME.value='';"); 
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"5\"){");
			out.println("document.Form1.TXT_USER_GROUP.value='';"); 
			out.println("}");
			
			out.println("}");	
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			//out.println("    m_sql = \"m_help_TXT_EMP_ID_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_EMP_ID.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','10');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_EMP_ID.value=oBj.valout[2];"); 
			//out.println("    document.Form1.TXT_EMP_ID.focus();"); 
			out.println("ful_name=oBj.valout[4]+' '+oBj.valout[5] "); 
			//out.println("    document.Form1.TXT_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_NAME.value=ful_name;");  //modified by nuwan de silva 23-07-07
			out.println("assignState('M8')");//added by nuwan de silva on 13-12-2007
			out.println("makeRequest(document.Form1.TXT_EMP_ID);"); //added by nuwan de silva on 13-12-2007
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_DIVISION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DIVISION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_4() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    m_sql = \"m_help_TXT_DESIGNATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESIGNATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 
			
			// -- added by nuwan de silva on 13-12-2007 -------------------------------
			out.println("function help_user_group() {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			out.println("    m_sql = \"m_help_group_user_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_USER_GROUP.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
		    out.println("function assign_group() {"); 
			//out.println("  	 makeRequest1('NEW','M_GROUP');"); // commented by udara 06-10-2016
			out.println("    document.Form1.TXT_USER_GROUP.value=oBj.valout[2];"); 
			//out.println("load_user_rights(document.Form1.TXT_USER_GROUP);"); // commented by udara 06-10-2016
			
			out.println("  document.Form1.TXT_USER_GROUP.focus(); "); // added by udara 07-12-2017
			
			out.println("}"); 
			// -- end by nuwan de silva on 13-12-2007 ---------------------------------

			out.println("function help_value_assign_4() {"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_new_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"33\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_NAME_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_NAME.value+\"@\" ;"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 


			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" ){ ");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\"+\"M@\";"); 
			out.println("    } ");
			out.println("		 else if(document.Form1.SCREEN_NAME.value==\"DACT\"){	");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_USER_ID.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_USER_TYPE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_EMP_ID.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_DESIGNATION_CODE.value=oBj.valout[8];"); 
			
			//out.println("    document.Form1.TXT_PASSWORD.value=oBj.valout[9];");  // commented by udara 07-12-2017
			//out.println("    document.Form1.TXT_CPASSWORD.value=oBj.valout[9];"); // commented by udara 07-12-2017
			
			out.println("if(oBj.valout[11]=='-' || oBj.valout[11]=='null') { ");
			out.println("    document.Form1.TXT_USER_GROUP.value='';");  //added by nuwan de silva on 13-12-2007
			out.println("}else {");
			out.println("    document.Form1.TXT_USER_GROUP.value=oBj.valout[11];");  //added by nuwan de silva on 13-12-2007
			out.println("}");
			
			out.println("           document.Form1.TXT_USER_ID.focus(); "); // added by udara 07-12-2017
			
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			//out.println("  	 makeRequest1('NEW','M2');");
			out.println("    makeRequest_rights(document.Form1.TXT_USER_ID)"); // commented by udara 20-01-2016 // re-enabled by udara on 30-11-2017
			//out.println("  	 makeRequest2('NEW','M6',document.Form1.TXT_USER_ID);"); // added by udara 20-01-2016 // commented by udara on 30-11-2017
			out.println("   }");
			out.println("}"); 
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:45em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_view_TXT_USER_ID_sql\";");
			out.println("    m_criteria = document.Form1.TXT_USER_ID.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			// added by nuwan de silva on 13-12-2007 ________________________________________
			out.println("function load_user_rights(obj) {");
			out.println("document.Form1.hid_chk_status.value='M6';");
      out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_group_access&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			// end by nuwan de silva on 13-12-2007 ________________________________________
			

			out.println("</script>"); 
			//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest1('NEW','M2')\">"); // commented by udara 06-10-2016 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); // added by udara 06-10-2016
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='HID_CLOSE_STS' VALUE=\"N\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Users</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  // commented by udara 30-11-2017
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\");makeRequest1(\"NEW\",\"M2\");' value=\"Edit\"></td>"); // added by udara 30-11-2017
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"Deactivate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Reactivate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_ID'  class=div_input>User ID *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_USER_ID)\">"); // commented by udara 22-08-2017
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='10' size='10' onblur=\"\">"); // commented by udara 22-08-2017
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_PASSWORD'  class=div_input>User Password *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='password' name='TXT_PASSWORD' maxlength='10' size='10' onblur=\"passwordvalidator(this)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CPASSWORD'  class=div_input>Confirm Password *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='password' name='TXT_CPASSWORD' maxlength='10' size='10' onblur=\"passwordvalidator(this);check_pass()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_TYPE'  class=div_input>User Type *</DIV></td>"); 
			out.println("<td width='40%' ><select name='TXT_USER_TYPE' class='txt_input'>");
			out.println("<option value=\"IND\" SELECTED >Individual         </option>");
			out.println("<option value=\"COR\"          >Corp               </option>");
			out.println("<option value=\"SOL\"          >Sole Proprietorship</option>");
			out.println("<option value=\"PAR\"          >Partnership        </option>");
			out.println("<option value=\"LIL\"          >Limited Liability  </option>");
			out.println("<option value=\"NGN\"          >Non Governmental   </option>");
			out.println("</select></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_EMP_ID'  class=div_input>Employee ID *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_EMP_ID' maxlength='10' size='10' onblur=\"assignState('M8'),makeRequest(document.Form1.TXT_EMP_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_EMP_ID' value=\"Help\" onClick=\"help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NAME'  class=div_input>User Name *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NAME' maxlength='200' size='50' onblur=\"assignState('M7'),makeRequest(document.Form1.TXT_NAME)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Location Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_LOCATION_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_1()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Division Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='10' onblur=\"assignState('M4'),makeRequest(document.Form1.TXT_DIVISION_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_DIVISION_CODE' value=\"Help\" onClick=\"help_button_3()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Designation Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DESIGNATION_CODE' maxlength='10' size='10' onblur=\"assignState('M5'),makeRequest(document.Form1.TXT_DESIGNATION_CODE)\" DISABLED>"); 
			//out.println("<input class='but_input' type='button' name='BUT_TXT_DESIGNATION_CODE' value=\"Help\" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*
			// _____________________ added by nuwan de silva on 13-12-2007 ______________________________________________________________
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_USER_GROUP'  class=div_input>User Group</DIV></td>"); 
			out.println("<td width='30%' ><select name=\"TXT_USER_GROUP\" class=\"txt_input\" onChange=\"load_user_rights(this)\">"); 
			
														
			rs=stmt.executeQuery(" SELECT GROUP_ID,GROUP_DESC "+
													   " FROM "+m_schema_name+".CO_CO_MAS_GROUP "+
														 " WHERE  ACTIVE_STATUS ='Y' ");
			
			boolean more=rs.next();
			while(more){
			out.println("<option value=\""+rs.getString(1)+"\" >"+rs.getString(2)+"</option>+");		
			more=rs.next();
			} 
			out.println("<option value=\"NONE\" SELECTED >None</option>+");		
			out.println("</select><td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_GROUP'  class=div_input>User Group *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_USER_GROUP' maxlength='10' size='10' onblur=\"assignState('M_USER_TYPE'),makeRequest(this)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_USER_GROUP' value=\"Help\" onClick=\"help_user_group()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			out.println("<br>"); 
			out.println("<DIV id='user_details'  class=div_input></DIV>");
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr>");  
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  // commented by udara 30-11-2017
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\");makeRequest1(\"NEW\",\"M2\");' value=\"Edit\"></td>"); // added by udara 30-11-2017
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"Deactivate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Reactivate\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr>");  
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validatepassword.js'></SCRIPT>"); 
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
