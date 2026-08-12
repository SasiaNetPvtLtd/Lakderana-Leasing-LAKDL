// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

   
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_MAS_display_Business_sub_sector_detail extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>System Administration - Business Sub Sector Detail</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("			alert('Record already exsist');");
			out.println("			help_update();");
			out.println("		}");
			out.println("		else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value!=\"\"  && document.Form1.hid_help_status.value!=\"2\"){");
			out.println("			alert('Selected Product Features incorrect please use help');");
			out.println("			help_update();");
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value!=\"\"  && document.Form1.hid_help_status.value!=\"2\"){");
			out.println("    	document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value=data_vec[0];"); 
			out.println("    	document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value=data_vec[1];"); 
			out.println("    	document.Form1.TXT_SUB_SECTOR_CODE.value=data_vec[2];"); 
			out.println("    	document.Form1.TXT_SUB_SECTOR_DESC=data_vec[3];"); 
			out.println("    	document.Form1.TXT_DEFAULT_VALUE.value=data_vec[4];"); 
			out.println("		}");
			out.println("		else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.hid_help_status.value==\"2\"){");
			out.println("			alert('Record already exsist');");
			out.println("		}");
			out.println("}");
	  
			out.println("function makeRequest(obj1,obj2) {");
			out.println("	if(obj1.value!=\"\"){");
			out.println("		document.Form1.hid_help_status.value=obj2;");
			out.println("		obj3=document.Form1.SCREEN_NAME.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MAS_sql_validations?chksql=m_prime_chk_FA_MAS_SUB_SECTOR_DETAIL&data_val=\"+obj1.value+\"&data_va2=\"+obj2+\"&data_va3=\"+obj3;");
			//out.println("window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println("	}");
			out.println("}");
			
			out.println("function clear_data(){"); 
			out.println("  document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value=\"\";"); 
			out.println("  document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value=\"\";"); 
			out.println("  document.Form1.TXT_SUB_SECTOR_CODE.value=\"\";"); 
			out.println("  document.Form1.TXT_SUB_SECTOR_DESC.value=\"\";"); 
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	//validations goes here"); 
			out.println("	if(document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value==\"\" ){  "); 
			out.println("		DIV_TXT_SUB_SECTOR_DETAIL_CODE.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else if(document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value==\"\"){  "); 
			out.println("		DIV_TXT_SUB_SECTOR_DESC.style.color='red';");
			out.println("		return false;"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		return true;"); 
			out.println("	}"); 
			out.println("}"); 			
			
			out.println("function assignState(val){");
			out.println("	document.Form1.hid_chk_status.value=val;");
			out.println("}");

			out.println("function before_submit(){ "); 
			out.println("	get_display_msg();");
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure you want to \"+m_sav_msg+\"\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_MAS_Save_Product_Features';");  
			out.println("				document.Form1.submit();	"); 
			out.println("			}"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("//document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_Business_sub_sector_detail';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_MAS_display_Business_sub_sector_detail';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_MAS_SUB_SECTOR_DETAIL\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" System Administration - Business Sub Sector Detail - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" System Administration - Business Sub Sector Detail - \"+document.Form1.hid_status.value;"); 
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
			out.println("			document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.disabled=true;"); 
			out.println("			document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 
			out.println("		}"); 
			out.println("		else if(m_val==\"EDIT\"){"); 
			out.println("			document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("			document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.disabled=false;"); 
			out.println("			document.Form1.TXT_DEFAULT_VALUE.disabled=false;"); 
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

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("						help_update_value_assign_99();"); 
	  	out.println("					}"); 
			out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("						help_update_value_assign_2();"); 
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
			
			out.println("function help_update() {"); 
			out.println(" document.Form1.hid_help_type.value=\"99\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_SUB_SECTOR_DETAIL_CODE_sql\";"); 
			out.println(" if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("  	m_criteria = document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value+\"@\"+document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value+\"@\"+\"Y@\";"); 
			out.println(" } ");
			out.println(" else if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println(" 	if(document.Form1.hid_help_status.value==\"1\"){ ");
			out.println("  		m_criteria = document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value+\"@\"+\"@\"+\"@\";"); 
			out.println(" 	}else if(document.Form1.hid_help_status.value==\"2\"){ ");
			out.println("  		m_criteria = \"@\"+document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value+\"@\"+\"@\";"); 
			out.println(" 	} ");
			out.println(" 	else{ ");
			out.println("  		m_criteria = document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value+\"@\"+document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value+\"@\"+\"@\";"); 
			out.println(" 	} ");
			out.println(" } ");
			out.println(" else{");
			out.println("   m_criteria = document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value+\"@\"+document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value+\"@\"+\"N@\";");
			out.println(" }"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_sub_sector() {"); 
			out.println(" document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" m_criteria = document.Form1.TXT_SUB_SECTOR_CODE.value+\"@\";");
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		document.Form1.TXT_SUB_SECTOR_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_SUB_SECTOR_DESC.value=oBj.valout[3];"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("	if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("		document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value=oBj.valout[2];"); 
			out.println("		document.Form1.TXT_SUB_SECTOR_DETAIL_DESC.value=oBj.valout[3];"); 
			out.println("		document.Form1.TXT_SUB_SECTOR_CODE.value=oBj.valout[4];"); 
			out.println("		document.Form1.TXT_SUB_SECTOR_DESC.value=oBj.valout[5];"); 
			out.println("		document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[6];"); 
			out.println("	}"); 
			out.println("	else{");
			out.println("		clear_data();");
			out.println("	}");
			out.println("}"); 
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
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
			
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_help_DIV_TXT_SUB_SECTOR_DETAIL_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_SUB_SECTOR_DETAIL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Business Sub Sector Detail</td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_SECTOR_DETAIL_CODE'  class=div_input>Sub Sector Detail Code *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SECTOR_DETAIL_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_SUB_SECTOR_DETAIL,'1')\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_SUB_SECTOR_DETAIL_DESC'  class=div_input>Sub Sector Detail Description *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SECTOR_DETAIL_DESC' maxlength='50' size='50' style='width:200' onblur=\"makeRequest(document.Form1.TXT_SUB_SECTOR_DETAIL_DESC,'2')\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_SECTOR'  class=div_input>Sub Sector Code </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SECTOR_CODE' maxlength='10' size='100' onblur=\"makeRequest(document.Form1.TXT_SUB_SECTOR_CODE,'3')\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_sub_sector()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_SUB_SECTOR_DESC'  class=div_input>Sub Sector Detail Description *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SECTOR_DESC' maxlength='50' size='50' style='width:200' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Default Value</td>"); 
			out.println("<td width='40%' ><select name='TXT_DEFAULT_VALUE' class='txt_input'>");
			out.println("<option value=\"Y\">Yes</option>");
			out.println("<option value=\"N\"SELECTED>No</option>");
			out.println("</select>");
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
