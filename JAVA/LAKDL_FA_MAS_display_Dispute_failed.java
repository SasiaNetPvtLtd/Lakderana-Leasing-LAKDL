// DEVELOPED BY : UDARA SOMATHILAKE FOR COMFAC FACTORING    DATE:26-10-2009

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_FA_MAS_display_Dispute_failed extends javax.servlet.http.HttpServlet { 
   
   ServletOutputStream out = null;
   public ResultSet rs; // Added by Udara Somathilake on 29-10-2009
   Statement stmt; // Added by Udara Somathilake on 29-10-2009
   Connection conn; // Added by Udara Somathilake on 29-10-2009
   
   public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
      
      try { 
         
         out = res.getOutputStream();
        //HttpSession session = req.getSession(true);
         
        //int m_index = m_screen_url.lastIndexOf("/");
        // m_screen_url = m_screen_url.substring(m_index+1);
        // Vector userScreens = (Vector)session.getValue("logon.screen"); 

        
         
         LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
         String m_html_client_url = m_sn_methods.html_client_url.trim();
         // String m_html_client_home_url = m_sn_methods.html_client_home_url.trim();
		 String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
		 String m_fschema_name=m_sn_methods.client_name.trim();		
         String m_header_name=m_sn_methods.header_name.trim(); 	 	
         String m_schema_name = m_sn_methods.schema_name.trim();
         String company_name=m_sn_methods.company_name.trim();
		 String user_name=m_sn_methods.username;  
         conn = m_sn_methods.met_user_validate(req); 

			

         stmt=conn.createStatement();
         
         res.setStatus(HttpServletResponse.SC_OK); 
         res.setContentType("text/html"); 
         
         out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
         out.println("<HTML>"); 
         out.println("<HEAD>"); 
         out.println("<TITLE>System Administration - Disputes</TITLE>"); 
         
         out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/validate.js\"></script>");
         //out.println("       <script language1.2 = \"JavaScript\" src = \"" + m_html_client_url + "/user_rights_data_gateway.js\"></script>"); //Waruna 20010/06/15
         //out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/factoring_drill_down.js\"></script>");
         out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
         out.println("       <link rel = \"stylesheet\" type = \"text/css\" href = \"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
         
         out.println("<SCRIPT language=\"JavaScript\">"); 
         out.println("var isOpen=false;"); 
			
         out.println("function clearIsOpen(){"); 
         out.println("   isOpen=false;"); 
         out.println("}");
         
         
         out.println("function clear_fields(){"); 
         out.println("  document.Form1.TXT_DISPUTE_CODE.value=\"\";"); 
         out.println("  document.Form1.TXT_DISPUTE_DESC.value=\"\";"); 
         out.println("  document.Form1.TXT_DISPUTE_CODE.focus();"); 
         out.println("}		");
         /*
         out.println("function HelpBox(Start,End,Hid_No,link_id) {");
         out.println("     document.getElementById(link_id).href=servlet_client_url+\":\"+client_t3_port+\"/"+m_fschema_name+"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select\"+"); 
         out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
         out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
         out.println("    \"&Hid_No=\"+Hid_No+\"&link_id=\"+link_id;");
         out.println("    $(\"#\"+link_id).trigger(\"click\");");
         out.println("}");
         */
         out.println("function load_rights(){");    //waruna 2010/06/15
         //out.println("check_user_rights('"+m_screen_url+"','"+username+"','"+company_name+"');"); //Added by Chandana on 08/06/2010
         out.println("}"); 
         out.println(""); 
         
         out.println("function helpCheck(valout,Hid_No,link_id){");
         out.println("	if(valout[1]==\"Next\"){"); 
         out.println("		Next(valout[2],valout[3],Hid_No,link_id);"); 
         out.println("	} "); 
         out.println("	else if(valout[1]==\"Prev\"){"); 
         out.println("		Prev(valout[2],valout[3],Hid_No,link_id);"); 
         out.println("	}	"); 
         out.println("}  ");
         
         
         /*
		 out.println("function help_close(){");  
         out.println("   $.fancybox.close();"); 
         out.println("}");
		*/
         
         
         out.println("function Prev(Start,End,Hid_No,link_id){"); 
         out.println("	HelpBox(Start,End,Hid_No,link_id);"); 
         out.println("}"); 
         
         
         out.println("function Next (Start,End,Hid_No,link_id){"); 
         out.println("	HelpBox(Start,End,Hid_No,link_id);"); 
         out.println("}");
         
         
         out.println("function help_update_value_assign_99_newhelp(data) {"); 
         out.println("	if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
         out.println("		document.Form1.TXT_DISPUTE_CODE.value=data.colomn_2;"); 
         out.println("		document.Form1.TXT_DISPUTE_DESC.value=data.colomn_3;"); 
         out.println("		document.Form1.TXT_ACT_POINT.value=data.colomn_4;"); // US
         out.println("	}"); 
         out.println("	else{");
         out.println("		clear_fields();");
         out.println("	}");
         out.println("}"); 
         
         
         
         out.println("function setData(data){"); 
         out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
         out.println("						help_update_value_assign_99_newhelp(data);"); 
         out.println("					}"); 
         out.println("}");
         
         
         
         out.println("var m_sav_msg='';");
         out.println("function get_vector(data_vec) {");
         out.println("		if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
         out.println("			alert('Record already exist');");
         out.println("			help_update('help_update');");
         out.println("		}");
         out.println("		else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_DISPUTE_CODE.value!=\"\"  && document.Form1.hid_help_status.value!=\"2\"){");
         out.println("			alert('Selected dispute code incorrect please use help');");
         out.println("			help_update('help_update');");
         out.println("		}");
         out.println("		else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_DISPUTE_CODE.value!=\"\"  && document.Form1.hid_help_status.value!=\"2\"){");
         out.println("    	document.Form1.TXT_DISPUTE_CODE.value=data_vec[0];"); 
         out.println("    	document.Form1.TXT_DISPUTE_DESC.value=data_vec[1];"); 
         out.println("		}");
         out.println("}");
         
         out.println("function makeRequest(obj1,obj2) {");
         out.println("	if(obj1.value!=\"\"){");
         out.println("		document.Form1.hid_help_status.value=obj2;");
         out.println("		obj3=document.Form1.SCREEN_NAME.value;");
         out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations?chksql=m_prime_chk_NETFAC_AF_MAS_DISPUTE&data_val=\"+obj1.value+\"&data_va2=\"+obj2+\"&data_va3=\"+obj3;"); // US CHANGE
         //out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations?chksql=m_prime_chk_NETFAC_AF_MAS_DISPUTE&data_val=\"+obj1.value+\"&data_va2=\"+obj2+\"&data_va3=\"+obj3\";"); // US CHANGE
         //out.println("window.open(m_url);");
         out.println("		load_interface(m_url,'XML');");
         out.println("	}");
         out.println("}");
         
         out.println("function clear_data(){"); 
         out.println("  document.Form1.TXT_DISPUTE_CODE.value=\"\";"); 
         out.println("  document.Form1.TXT_DISPUTE_DESC.value=\"\";"); 
         out.println("  document.Form1.TXT_DISPUTE_CODE.focus();"); 
         out.println("}");
         
         out.println("function validate_data(){"); 
         out.println("	//validations goes here"); 
         out.println("	if(document.Form1.TXT_DISPUTE_CODE.value==\"\" ){  "); 
         //out.println("		DIV_TXT_DISPUTE_CODE.style.color='red';");
         out.println("		document.getElementById(\"DIV_TXT_DISPUTE_CODE\").style.color='red';");
         out.println("		return false;"); 
         out.println("	}"); 
         out.println("	else if(document.Form1.TXT_DISPUTE_DESC.value==\"\"){  "); 
         //out.println("		DIV_TXT_DISPUTE_DESC.style.color='red';");
         out.println("		document.getElementById(\"DIV_TXT_DISPUTE_DESC\").style.color='red';");
         out.println("		return false;"); 
         out.println("	}"); 
         out.println("	else{"); 
         out.println("		return true;"); 
         out.println("	}"); 
         out.println("}"); 			
         
         out.println("function assignState(val){");
         out.println("	document.Form1.hid_chk_status.value=val;");
         out.println("}");
         
         // Added by Udara Somathilake on 26/10/2009
         out.println("function disCodeValidate(){");
         out.println("if(isNaN(document.Form1.TXT_DISPUTE_CODE.value))");
         out.println("{");
         out.println("alert('Dispute Code should be a number');");
         out.println("document.Form1.TXT_DISPUTE_CODE.value='';");
         out.println("document.Form1.TXT_DISPUTE_CODE.focus();");
         out.println("return false;");
         out.println("}"); //first if
         out.println("else if(document.Form1.TXT_DISPUTE_CODE.value.length>3)");
         out.println("{");
         out.println("alert('Dispute Code should be 3 digit number');");
         out.println("document.Form1.TXT_DISPUTE_CODE.value='';");
         out.println("document.Form1.TXT_DISPUTE_CODE.focus();");
         out.println("return false;");
         out.println("}"); // else if
         out.println("else");
         out.println("{");
         out.println("return true;");
         out.println("}");
         out.println("}"); //end func
         
         // Function added by Udara Somathilake on 27/10/2009
         /*
         out.println("function numsCheckOnBlur(){	"); 
         out.println("if(isNaN(document.Form1.TXT_DISPUTE_CODE.value))");
         out.println("{	");
         out.println("alert('Dispute code should be a number');");
         out.println("document.Form1.TXT_DISPUTE_CODE.value='';");
         out.println("document.Form1.TXT_DISPUTE_CODE.focus();");
         out.println("}	");//if
         out.println("else{	");
         out.println("makeRequest(document.Form1.TXT_DISPUTE_CODE,'1')");
         out.println("}	");//else
         out.println("}	");// end func
         
         */
         //TO ALLOW SEARCH BY DESCRIPTION MODIFIED ABOVE FUNCTION 2010-03-19 MADHAWA 
         out.println("function numsCheckOnBlur(){	"); 
         
         out.println("if(!isNaN(document.Form1.TXT_DISPUTE_CODE.value)){");
         out.println("makeRequest(document.Form1.TXT_DISPUTE_CODE,'1')");
         out.println("}	");//else
         out.println("}	");// end func
         
         
         out.println("function before_submit(){ "); 
         out.println("	get_display_msg();");
         out.println("	if(validate_data()){"); 
         out.println("		if(confirm(\"Are you sure you want to \"+m_sav_msg+\" ?\")){ ");
         out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
         out.println("				document.Form1.elements[i].disabled=false;");
         out.println("			}");
         out.println("			if(validate_data()){"); 
         out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MAS_Save_Dispute';");  
         out.println("				document.Form1.submit();	"); 
         out.println("			}"); 
         out.println("		}"); 
         out.println("	}"); 
         out.println("else { "); 
         out.println("	alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
         out.println("	}");
         out.println("} "); 
         
         out.println("function load_lock(){	"); 
        // out.println("document.oncontextmenu=new Function(\"return false\");"); 
         out.println("}	"); 
         
         out.println("function clear_window(){	"); 
         out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
         out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_Dispute';"); 
         out.println("		}"); 
         out.println("}"); 
         
         out.println("function new_window(){	"); 
         out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_Dispute';"); 
         out.println("}"); 
         
         // out.println("function save_window(){	"); 
         // out.println("	before_submit();"); 
         // out.println("}"); 
         
         //Added by Udara Somathilake on 26-10-2009
         out.println("function save_window(){	"); 
         out.println("if(disCodeValidate()==true)"); 
         out.println("{"); 
         out.println("	before_submit();"); 
         out.println("}"); // end if
         out.println("}"); 
         
         
         out.println("function load_help_msg() {"); 
         out.println("    m_help_message = \"m_help_msg_NETFAC_FA_MAS_DISPUTE\";"); // us this is for menu bar help
         out.println("    HelpBox_msg(m_help_message);"); 
         out.println("}"); 	
         
         out.println("function HelpBox_msg(m_help_message) {"); 
         out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
         out.println("  \"&help_message_in=\"+m_help_message);"); 
         out.println("}"); 
         
         out.println("function load_roll_value(m_val){"); 
         out.println("	document.getElementById('help_box').innerHTML=\" System Administration - Disputes - \"+m_val;"); 
         out.println("}"); 
         
         out.println("function load_roll_out_value(){");
         out.println("	document.getElementById('help_box').innerHTML=\" System Administration - Disputes - \"+document.Form1.hid_status.value;"); 
         out.println("}"); 
         
         out.println("function load_screen_status(m_val){"); 
         //out.println("	if(confirm(\"Are You Sure\")){ ");
         out.println("		if(m_val==\"NEW\"){"); 
         out.println("			new_window();"); 
         out.println("			document.Form1.BUT_HELP_MAIN.disabled=true;");
         out.println("		}"); 
         out.println("		else if(m_val==\"HELP\"){"); 
         out.println("			load_help_msg();"); 
         out.println("		}"); 
         out.println("		else if(m_val!=\"EDIT\"){"); 
         out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;"); 
         out.println("			document.Form1.TXT_DISPUTE_DESC.disabled=true;"); 
         out.println("			document.Form1.TXT_ACT_POINT.disabled=true;"); 
         out.println("		}"); 
         out.println("		else if(m_val==\"EDIT\"){"); 
         out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;"); 
         out.println("			document.Form1.TXT_DISPUTE_DESC.disabled=false;");
         out.println("			document.Form1.TXT_ACT_POINT.disabled=false;"); 
         out.println("		}"); 
         out.println("		else{");
         out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;");
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
         //out.println("	}"); 
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
         
         /*out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
         out.println("    oBj = new MyDialog();"); 
         out.println("    oBj.valout[1]  = \" \";"); 
         out.println("    oBj.valout[2]  = \" \";"); 
         out.println("    oBj.valout[3]  = \" \";"); 
         out.println("    oBj.valout[4]  = \" \";"); // us
         out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select\"+"); 
         out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
         out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
         out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
         out.println("	if(oBj.valout[1] !=\" \"){"); 
         out.println("		if(oBj.valout[1] !=\"Close\"){"); 
         out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
         out.println("				if(oBj.valout[1]!=\"Next\"){"); 
         out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
         out.println("						help_update_value_assign_99();"); 
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
         out.println("	 	else{	"); 
         out.println("	 		clear_data();	"); 
         out.println("	 	}	"); 
         out.println("	}"); 
         out.println("	else{	"); 
         out.println("		clear_data();	"); 
         out.println("	}");
         out.println("}"); 
         
         out.println("function Prev(Start,End,Hid_No){"); 
         out.println("		HelpBox(Start,End,Hid_No);"); 
         out.println("}"); 
         
         out.println("function Next (Start,End,Hid_No){"); 
         out.println("		HelpBox(Start,End,Hid_No);"); 
         out.println("}"); 
         */
         out.println("function help_update(link_id) {");  
         out.println(" document.Form1.hid_help_type.value=\"99\";");
         out.println(" m_sql = \"m_help_DIV_TXT_DISPUTE_CODE_sql\";"); //us This is for help pop up menu
		 out.println(" if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
         out.println("  	m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\"+document.Form1.TXT_DISPUTE_DESC.value+\"@\"+\"Y@\";"); 
         out.println(" } ");
         out.println(" else if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
         out.println(" 	if(document.Form1.hid_help_status.value==\"1\"){ ");
         out.println("  		m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\"+\"@\"+\"@\";"); 
         out.println(" 	}else if(document.Form1.hid_help_status.value==\"2\"){ ");
         out.println(" 		m_sql = \"m_help_DIV_TXT_DISPUTE_DESC_sql\";"); 
         out.println("  		m_criteria = \"@\"+document.Form1.TXT_DISPUTE_DESC.value+\"@\"+\"@\";"); 
         out.println(" 	} ");
         out.println(" 	else{ ");
         out.println("  		m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\"+document.Form1.TXT_DISPUTE_DESC.value+\"@\"+\"@\";"); 
         out.println(" 	} ");
         out.println(" } ");
         out.println(" else{");
         out.println("   m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\"+document.Form1.TXT_DISPUTE_DESC.value+\"@\"+\"N@\";");
         out.println(" }"); 
		 out.println(" HelpBox('1','10','0');"); 
         //out.println("}"); 
         out.println("}"); 
         
         
         out.println("function help_update_value_assign_99() {"); 
         out.println("	if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
         out.println("		document.Form1.TXT_DISPUTE_CODE.value=oBj.valout[2];"); 
         out.println("		document.Form1.TXT_DISPUTE_DESC.value=oBj.valout[3];"); 
         out.println("		document.Form1.TXT_ACT_POINT.value=oBj.valout[4];"); // US
         out.println("	}"); 
         out.println("	else{");
         out.println("		clear_data();");
         out.println("	}");
         out.println("}"); 
         
        /*
	     out.println("function init_help_view() { ");   // This Method should call after every dynamic element addition 
         out.println("   $(\"a.helpview\").fancybox({"); 
         out.println("    'hideOnContentClick': false, 'width':750 , 'height': 400,"); 
         out.println("    'overlayShow': true, ");
         out.println("    'changeSpeed':0, 'speedIn':200, 'speedOut':200,"); 
         out.println("    'opacity':true,'onClosed' : clearIsOpen, 'centerOnScroll':true, 'easingIn':'swing', "); 
         out.println("    'easingOut':'swing' ,transitionIn:'elastic' ,transitionOut:'elastic',"); 
         out.println("    'type':'iframe','showCloseButton':true,'enableEscapeButton':true,'modal':false,'overlayOpacity':0.1");
         out.println("  });"); 
         out.println("}"); 
		*/
         
         
        /*
	     out.println("$(document).ready(function() { ");
         out.println("  init_help_view();"); 
         out.println("});"); 
		*/
         
         /*
         out.println("$('a').live('click', function() {"); 
         out.println("  init_help_view();");			
         out.println("}); ");  
         */
			
         /*
		
		 out.println("function HelpView(Start,End,Hid_No,link_view_id) {");
         out.println("     document.getElementById(link_view_id).href=servlet_client_url+\":\"+client_t3_port+\"/"+m_fschema_name+"FA_MAS_View_Help_Servlet?class_in=\"+client_name+\"FA_MAS_View_help_select\"+"); 
         out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
         out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
         out.println("    \"&Hid_No=\"+Hid_No+\"&link_id=\"+link_view_id;");
         out.println("    $(\"#\"+link_view_id).trigger(\"click\");");
         out.println("}");
         
         out.println("function viewPrev(Start,End,Hid_No,link_view_id){"); 
         out.println("	HelpView(Start,End,Hid_No,link_view_id);"); 
         out.println("}"); 
         
         
         out.println("function viewNext (Start,End,Hid_No,link_view_id){"); 
         out.println("	HelpView(Start,End,Hid_No,link_view_id);"); 
         out.println("}");
         
         out.println("function help_view_Check(valout,Hid_No,link_view_id){");
         out.println("	if(valout[1]==\"Next\"){"); 
         out.println("		viewNext(valout[2],valout[3],Hid_No,link_view_id);"); 
         out.println("	} "); 
         out.println("	else if(valout[1]==\"Prev\"){"); 
         out.println("		viewPrev(valout[2],valout[3],Hid_No,link_view_id);"); 
         out.println("	}	"); 
         out.println("}  ");
		
         */
		 ///////////////////////////////////////////////////////
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
			out.println(" url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+");//FA_MAS_help_select 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\"; ");
			out.println("  window.open(url);");
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select\"+"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"100\"){"); 
	  	    out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	    out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
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
			out.println("if(bttn_help==\"99\"){;");
			out.println("}");
			out.println("if(bttn_help==\"2\"){;");
			out.println("}");
			out.println("if(bttn_help==\"1\"){;");
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

			
		    out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
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
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
	
			
		 ///////////////////////////////////////////////////////	
			
			
			
         
         
         /*out.println("function HelpView(Start,End,Hid_No,Max) {"); 
         out.println(" oBj = new MyDialog();"); 
         out.println(" oBj.valout[1]  = \" \";"); 
         out.println(" oBj.valout[2]  = \" \";"); 
         out.println(" oBj.valout[3]  = \" \";"); 
         out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"FA_MAS_View_help_select\"+"); 
         out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
         out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
         out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
         out.println("	"); 
         out.println("	if(oBj.valout[1] !=\" \"){"); 
         out.println("		if(oBj.valout[1] !=\"Close\"){"); 
         out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
         out.println("				if(oBj.valout[1]!=\"Next\"){"); 
         out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
         out.println("						help_update_value_assign_99();"); 
         out.println("					}"); 
         out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
         out.println("						help_value_assign_1();"); 
         out.println("					}"); 
         out.println("				}"); 
         out.println("				else{"); 
         out.println("					ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
         out.println("					return false;"); 
         out.println("				} "); 
         out.println("			}"); 
         out.println("			else{	"); 
         out.println("				ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
         out.println("			}	"); 
         out.println("		}		"); 
         out.println("	}	"); 
         out.println("}"); 
         
         out.println("function ViewPrev(Start,End,Hid_No){"); 
         out.println("    HelpView(Start,End,Hid_No);"); 
         out.println("}"); 
         
         out.println("function ViewNext(Start,End,Hid_No){"); 
         out.println("    HelpView(Start,End,Hid_No);"); 
         out.println("}"); 
         */
         /*
		 out.println("function View_all(link_id){");	
         out.println("   if(!isOpen){");     
         out.println("      isOpen=true;");            
         out.println("    m_sql = \"m_help_DIV_TXT_DISPUTE_CODE_sql\";");
         out.println("    m_criteria = document.Form1.TXT_DISPUTE_CODE.value+\"@\"+\"Y@\";"); // View All
         out.println("    HelpView('1',50,'0',link_id);"); 
         out.println("}"); 
         out.println("}"); 
		 */
         
         out.println("</script>"); 
         out.println("</HEAD>"); 
         
         out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_rights(),load_roll_value('New')\">"); 
         out.println("<FORM NAME='Form1' method='post'>"); 
         out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
         out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
         out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
         out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
         out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
         out.println("<tr>"); 
         out.println("<td width='8' valign='top'> </td>"); 
         out.println("<td class='border_wht' valign='top'> "); 
         out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
         out.println("<tr> "); 
         out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
         out.println("</tr>"); 
         out.println("<tr> "); 
         out.println("<td height='1'> </td>"); 
         out.println("</tr>"); 
         out.println("<tr>"); 
         //out.println("<td style='height: 327px'>"); 
         out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
         out.println("<tr>"); 
         out.println("<td height='1'> </td>"); 
         out.println("</tr>"); 
         out.println("<tr>"); 
         out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Disputes </td>"); 
         out.println("</tr>"); 
         out.println("<tr>"); 
         out.println("<td  height='10px' class='pdn_txtpos'>"); 
         out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
         out.println("<tr><td width='10%' align='center'><input type=\"button\" id=\"BUT_NEW\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
         out.println("<td width='10%' align='left'><input type=\"button\" id=\"BUT_EDIT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
         out.println("<td width='10%' align='left'><input type=\"button\" id=\"BUT_DEACT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
         out.println("<td width='10%' align='left'><input type=\"button\" id=\"BUT_REACT\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
         out.println("<td width='10%' align='left'><input type=\"button\" id=\"BUT_VIEW\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all(\"view_all\")' value=\"View All\"><a onClick=\"View_all('view_all')\" style=' text-decoration: none;cursor: text;' id=\"view_all\" class=\"helpview iframe\" href=\"\"> </a></td>");
         //out.println("<td width='6%'></td>");  
         out.println("<td width='10%' align='left'><input type=\"button\" id=\"BUT_SAVE\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");   
         out.println("<td width='10%' align='left'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>"); 
         //out.println("<td width='100%'></td>"); 
			out.println("<td width='*%'></td>"); 
         out.println("<td width='10%' align='right'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>"); 
         out.println("<td width='10%' align='right'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
         out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
         out.println("</table>");  
         out.println("</td></tr><tr>");  
         out.println("<td class='line' height='1'> </td>");  
         out.println("</tr><tr>");  
         out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
         out.println("<br >");
         
         out.println("<table align='center' width='100%' class='table'>"); 
         out.println("<tr>"); 
         out.println("<td width='30%' ><DIV id='DIV_TXT_DISPUTE_CODE'  class=div_input>Dispute Code *</DIV></td>"); 
         //out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DISPUTE_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_DISPUTE_CODE,'1')\">"); 
         out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DISPUTE_CODE' maxlength='3' size='10' onblur=\"numsCheckOnBlur();\">");
         out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('help_update')\" disabled><a onClick=\"help_update('help_update')\" style=' text-decoration: none;cursor: text;' id=\"help_update\" class=\"fancylink iframe\" href=\"\"> </a></td>"); 
         out.println("<td width='*%'></td>"); 
         out.println("</tr>");
         
         out.println("<tr >"); 
         out.println("<td width='30%' ><DIV id='DIV_TXT_DISPUTE_DESC'  class=div_input>Dispute Description *</DIV></td>"); 
         // out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DISPUTE_DESC' maxlength='50' size='50' style='width:200' onblur=\"makeRequest(document.Form1.TXT_DISPUTE_DESC,'2')\"></td>"); // com us
         out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DISPUTE_DESC' maxlength='50' size='50' style='width:200' ></td>");
         out.println("<td width='*%'></td>"); 
         out.println("</tr>");
         
         // Added by Udara Somathilake on 29-10-2009
         rs= stmt.executeQuery("SELECT ACTIVATION_POINT_CODE,INITCAP(ACTIVATION_DESC) FROM "+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT WHERE ACTIVE_STATUS='Y'");
         
         out.println("<tr >"); 
         out.println("<td width='30%' >Activation Point</td>"); 
         out.println("<td width='40%' ><select name='TXT_ACT_POINT' class='txt_input'   style='width:280' >"); // US
         
         while(rs.next()){
            if(rs.getString(1).equals("B001")){
               out.println("<option value=\""+rs.getString(1)+"\" selected>"+rs.getString(2)+"</option>");
            }
            else{
               out.println("<option value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
            }
         }
         
         out.println("</select>");
         out.println("</tr>"); 
         // End Udara 29-10-2009
         
         
         
         
         out.println("</table>"); 
         out.println("<br>"); 
         out.println("<table align='center' width='100%'>"); 
         out.println("<tr>"); 
         out.println("<td width='100%' class='note'></td>"); 
         out.println("</tr>"); 
         out.println("</table>"); 
         out.println("</form>"); 
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
