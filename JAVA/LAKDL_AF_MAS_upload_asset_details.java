
// created by udara on 17-05-2012

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_upload_asset_details extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			Connection conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_class_url_2=m_sn_methods.servlet_client_url.trim()+":"+"8087/lakdllive/servlet"; // added by udara 03-06-2017
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Upload Asset Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 



			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_nationality\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Upload Asset Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Upload Asset Details - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){");
		
			//out.println("  alert(m_val);   ");
		
			out.println("  if(m_val==\"NEW\"){"); 
			out.println("      new_window();"); 
			out.println("      document.Form1.BUT_HELP_MAIN.disabled=true; ");
	        out.println("  }"); 
			out.println("  else if(m_val==\"HELP\"){"); 
			out.println("      load_help_msg();"); 
			out.println("  }"); 
			out.println("  else if(m_val!=\"EDIT\"){"); 
			//out.println("      document.Form1.BUT_HELP_MAIN.disabled=false;"); 	
			//out.println("     document.Form1.TXT_DESCRIPTION.disabled=true;"); 
			out.println("  }"); 
			out.println("  else{");
			out.println("     document.Form1.BUT_HELP_MAIN.disabled=false; ");
            out.println("  }"); 
			
			out.println("  document.Form1.SCREEN_NAME.value=m_val;"); 
			
			out.println("  if(m_val==\"NEW\"){");
			out.println("    document.Form1.hid_status.value=\"New\";"); 
			out.println("    document.Form1.hid_save.value=\"Save\";"); 
			out.println("    document.Form1.hid_screen_name.value='NEW';");
			//out.println("   alert(document.Form1.hid_screen_name.value); "); // 
			out.println("    document.Form1.BUT_HELP_MAIN.disabled=true;");
			out.println("  } ");
			out.println("  else if(m_val==\"EDIT\"){");  
			out.println("    document.Form1.hid_status.value=\"Edit\";"); 
			out.println("    document.Form1.hid_save.value=\"Modify\";"); 
			out.println("    document.Form1.hid_screen_name.value='EDIT';"); 
			//out.println("   alert(document.Form1.hid_screen_name.value); "); //
			//out.println("    document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("  }");
			out.println("  else if(m_val==\"DACT\"){");  
			out.println("    document.Form1.hid_status.value=\"Deactivate\";"); 
			out.println("    document.Form1.hid_save.value=\"Deactivate\";"); 
			out.println("    document.Form1.hid_screen_name.value='DEACT';"); 
			out.println("  }");
			out.println("  else if(m_val==\"RACT\"){");  
			out.println("    document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("    document.Form1.hid_save.value=\"Reactivate\";"); 
			out.println("    document.Form1.hid_screen_name.value='REACT';"); 
			out.println("  } ");
			out.println("  else{");  
			out.println("    document.Form1.hid_status.value=\"\";");  
			out.println("  }"); 
			
			//out.println("  alert(document.Form1.hid_screen_name.value); ");
			
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
			out.println("   murl=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\";");
			//out.println("   window.open(murl);");
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
			out.println("		help_update_value_assign_1();"); 
	  	    out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_update_value_assign_2();"); 
	  	    out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_update_value_assign_3();"); 
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
			//out.println("	clear_data();");
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
		    out.println("    document.Form1.TXT_SCHEDULE_CODE.value='';"); 
			out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value='';");
			out.println("    document.Form1.TXT_ITEM_CAT.value='';");
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		    out.println("    document.Form1.TXT_ITEM_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		    out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("}");
			
			
			
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("Close();"); 
			
			out.println("	}	"); 
			out.println("}"); 
			
			
			out.println(" function Close(){");//**
			//out.println("clear_data()	");
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
		    out.println("    document.Form1.TXT_SCHEDULE_CODE.value='';"); 
			out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value='';");
			out.println("    document.Form1.TXT_ITEM_CAT.value='';");
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		    out.println("    document.Form1.TXT_ITEM_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		    out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"3\"){");
		    out.println("    document.Form1.txt_in_company.value='';"); 
			out.println("}");
			
			
		    //out.println("window.close();");
		    out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
		    out.println("    document.Form1.TXT_SCHEDULE_CODE.value='';"); 
			out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value='';");
			out.println("    document.Form1.TXT_ITEM_CAT.value='';");
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		    out.println("    document.Form1.TXT_ITEM_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		    out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"3\"){");
		    out.println("    document.Form1.txt_in_company.value='';"); 
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
			
			
			// ============== Shedule Help ==============================================			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_SCHEDULE_CODE_sql\";"); 
			//out.println("    m_criteria = document.Form1.TXT_SCHEDULE_CODE.value+\"@\"+\"Y@\";"); 
			//out.println("   alert(document.Form1.hid_screen_name.value); ");
			out.println("	if(document.Form1.hid_screen_name.value==\"REACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_SCHEDULE_CODE.value+\"@\"+\"N@\";"); 
			out.println("}");
			out.println("	else{ ");
			out.println("    m_criteria = document.Form1.TXT_SCHEDULE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("}");
			
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_SCHEDULE_CODE.value=oBj.valout[2];");
		    out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_ITEM_CAT.value=oBj.valout[4];");
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[5];");
			out.println("}"); 			
			// ============== End Shedule Help ===========================================
			
			// ============== Item Category Help =========================================
			out.println("function help_update_item_cat() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_Code_desc_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_1() {"); 
			out.println("    document.Form1.TXT_ITEM_CAT.value=oBj.valout[2];"); 
			out.println("}"); 			
			// ============== End Item Category Help =====================================
			
			// ============== Item Sub Category Help =====================================
			out.println("function help_update_item_sub_cat() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];");
			out.println("}"); 			
			// ============== End Item Sub Category Help ==================================
			
			out.println("function help_company() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_insurance_company_code\";"); 
			out.println("    m_criteria = document.Form1.txt_in_company.value+\"@\"+\"Y@\";");  
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
				
			out.println("function help_update_value_assign_3() {");
			out.println("    document.Form1.txt_in_company.value=oBj.valout[2];"); 
			out.println("}");
			
			
			
			out.println(" function assign_help_status(obj){");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"||document.Form1.SCREEN_NAME.value==\"EDIT\"||document.Form1.SCREEN_NAME.value==\"DACT\" ){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" ){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");

			out.println("}");

			// commented by udara 24-12-2014
			/*
			out.println("function generate_from_excel(type){");

			out.println("   if(document.Form1.TXT_ITEM_SUB_CAT.value==''){ ");
			out.println("     	alert('Vehicle type cannot be blank'); ");
			out.println("   }");
			out.println("   else if(document.Form1.txt_in_company.value==''){ ");
			out.println("     	alert('Insurance company cannot be blank'); ");
			out.println("   }");
			out.println("   else{");
			out.println("   	var m_path = document.getElementById('datafile').value; ");
			out.println("   	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel';");
			out.println("		document.Form1.submit();");
			out.println("   }");
			
			out.println("}");
			*/
			
			// added by udara 24-12-2014
			
			out.println("function generate_from_excel(type){");
			
			out.println("   if(type=='run'){");

			out.println("   	if(document.Form1.TXT_ITEM_SUB_CAT.value==''){ ");
			out.println("     		alert('Vehicle type cannot be blank'); ");
			out.println("   	}");
			out.println("   	else if(document.Form1.txt_in_company.value==''){ ");
			out.println("     		alert('Insurance company cannot be blank'); ");
			out.println("   	}");
			out.println("   	else{");
			out.println("   		var m_path = document.getElementById('datafile').value; ");
			//out.println("   		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel';");
			//out.println("			document.Form1.submit();");
			
			out.println("   	     if(document.Form1.hid_screen_name.value=='NEW'){ ");
			//out.println("   	         alert('AF_MAS_upload_asset_details_upload_excel'); ");
			//out.println("   		     document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel';"); // commented by udara 03-06-2017
			out.println("   		     document.Form1.action='"+m_class_url_2+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel';"); // for live
			out.println("   	     }");
			out.println("   	     else{");
			out.println("   		     document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel_3';");
			//out.println("   	         alert('AF_MAS_upload_asset_details_upload_excel_3'); ");
			out.println("   	     }");
			
			out.println("			document.Form1.submit();");
			
			out.println("   	}");
			
			out.println("   }");
			out.println("   else if(type=='view'){");
			
			//out.println("       alert('View');          ");
			/*
			out.println("       var m_path = document.getElementById('datafile').value; ");
			out.println("   	var m_schedule_no = document.Form1.TXT_SCHEDULE_CODE.value; ");
			*/
			//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_schedule_details_upload_excel?rpt_type=\"+m_rpt_type+\"&schedule_no=\"+m_schedule_no+\"&path=\"+m_path;");
			/*
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_schedule_details_upload_excel_2?rpt_type=\"+m_rpt_type+\"&schedule_no=\"+m_schedule_no+\"&path=\"+m_path;");
			out.println("   	window.open(m_url,'TheNewpop','fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes'); ");
			*/
			
			out.println("   	var m_vehicle_type = document.Form1.TXT_ITEM_SUB_CAT.value; ");
			out.println("   	var m_ins_company  = document.Form1.txt_in_company.value; ");
			out.println("   	var m_path = document.getElementById('datafile').value; ");
			
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_upload_asset_details_upload_excel_2?vehicle_type=\"+m_vehicle_type+\"&ins_company=\"+m_ins_company+\"&path=\"+m_path;");
			out.println("   	window.open(m_url,'TheNewpop','fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes'); ");
			
			out.println("   }");
			
			out.println("}");
			
			// end by udara 24-12-2014
			
			out.println("function validate_schedule_code(obj_val){");
			//out.println("	alert(obj_val); ");
			out.println("	var act_status = ''; ");

			out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_chk_scedule_code_check&sch_code=\"+obj_val+\"&act_status=\"+act_status;");
			out.println("   load_interface(m_url,'XML');");
			
			out.println("}");
			
			out.println("function get_vector(data_vec) {");
			out.println("	if(data_vec.length>0 && document.Form1.hid_screen_name.value==\"NEW\" ){");
			out.println("		alert('Record already exists');");
			out.println("		new_window();");
			out.println("	}");
			out.println("}");
			
			out.println("function validate_percentage(obj){");
			
			out.println("	if(isNaN(obj.value)){");
			out.println("      alert('Entered value should be a number'); ");
			out.println("      obj.value=''; ");
			out.println("      obj.focus(); ");
			out.println("   }");
			out.println("   else{");
			out.println("       if(obj.value>100){");	
			out.println("   	 	alert('Entered value should be a percentage'); ");
			out.println("      		obj.value=''; ");
			out.println("      		obj.focus(); ");
			out.println("   	}");
			out.println("   }");
			
			out.println("}");
			
			// added by udara 19-07-2018
			out.println("function flag_enable(){");
			out.println("   if(document.Form1.flag_this.checked==true){ ");
			out.println("      document.Form1.flag_this.value = 'Y'; ");
			out.println("   }");
			out.println("   else{");
			out.println("      document.Form1.flag_this.value = 'N'; ");
			out.println("   }");
			//out.println("   alert(document.Form1.flag_this.value); ");
			out.println("}");
			// end by udara 19-07-2018

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post' enctype = \"multipart/form-data\" >"); 
			out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_m_user' VALUE='"+m_username+"' >");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_screen_name' VALUE=\"NEW\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Upload Asset Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'  onclick='load_screen_status(\"NEW\")'  value=\"New\" ></td>");  
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onclick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");'  onclick='load_screen_status(\"DACT\")' value=\"Deactivate\" ></td>");  
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");'  onclick='load_screen_status(\"RACT\")' value=\"Reactivate\" ></td>");
			
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");'        onclick='generate_from_excel(\"view\")' value=\"Edit\"></td>");
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");'  onclick='load_screen_status(\"DACT\")' value=\"Deactivate\" ></td>");  // commented by udara 12-01-2014
			//out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");'  onclick='load_screen_status(\"RACT\")' value=\"Reactivate\" ></td>");  // commented by udara 12-01-2014
			
			out.println("<td width='*%'></td>");
  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'    onclick='generate_from_excel(\"run\")' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'   onclick='close_window()' value=\"Close\"></td>");  
			out.println("</tr>"); 
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 
			/*
			out.println("<tr>"); 
			
			out.println("<td width='10%' ><DIV id='DIV_TXT_SCHEDULE_CODE'  class=div_input>Schedule No.</DIV></td>"); 
			out.println("<td width='30%' >");
		    out.println("  <input class='txt_input' type='text' name='TXT_SCHEDULE_CODE' maxlength='10' size='10' onblur='validate_schedule_code(this.value);' >"); 
			out.println("  <input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled > ");
		    out.println("  <input class='but_input' style='width:100px' type='button' name='BUT_VIEW_REPORT' value=\"View Report\" onClick=\"generate_from_excel('view');\" > "); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<td width='10%' ><DIV id='DIV_TXT_SCHEDULE_DESCRIPTION'  class=div_input>Schedule Description</DIV></td>"); 
			out.println("<td width='30%' >");
		    out.println("  <input class='txt_input' type='text' name='TXT_SCHEDULE_DESCRIPTION' maxlength='100' size='100' style='width:200px' onblur=\"\">"); 
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			/*
			out.println("<tr>"); 
			
			out.println("<td width='10%' ><DIV id='DIV_TXT_ITEM_CAT'  class=div_input>Item Category</DIV></td>"); 
			out.println("<td width='30%' >");
		    out.println("  <input class='txt_input' type='text' name='TXT_ITEM_CAT' maxlength='10' size='10' onblur=\"\">"); 
			out.println("  <input class='but_input' type='button' name='BUT_HELP_ITEM_CAT' value=\"Help\" onClick=\"help_update_item_cat()\" > ");
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			//out.println("<td width='10%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Item Sub Category</DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Vehicle Type</DIV></td>"); 
			out.println("<td width='30%' >");
		    out.println("  <input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"\">"); 
			out.println("  <input class='but_input' type='button' name='BUT_HELP_ITEM_SUB_CAT' value=\"Help\" onClick=\"help_update_item_sub_cat()\" > ");
			out.println("  <input class='but_input' style='width:100px' type='button' name='BUT_VIEW_REPORT' value=\"View Report\" onClick=\"generate_from_excel('view');\" > "); // added by udara 24-12-2014
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'  valign=\"top\">Insurance Company</td>"); 
			out.println("<td width='30%' align ='left'  valign=\"top\"><input type=\"text\" value=\"\" class=\"txt_input\"  name=\"txt_in_company\" value=\"\" maxlength=18 >");  //style=\"width:150px;\"			
			out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" ></td>"); 			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");


			
			// <input type="file" name="datafile" size="40">
			
			out.println("<tr>"); 
			out.println("<td width='10%' > Select Excel Sheet </td>"); 
			out.println("<td width='30%' >");
		    out.println("<input type=\"file\" name=\"datafile\" id=\"datafile\" size=\"20\"   > "); // value=\"D:\\SasiaNet_Products\\NetAsset\\LAKDL\\UPLOAD\\Schedule_Details\" accept=\"*.xls\"
			//out.println("<input class='but_input' style='width:100px' type='button' name='BUT_RUN_REPORT'  value=\"Run Update\"  onClick=\"generate_from_excel('run');\" > ");
			out.println("</td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>");
			out.println("<td width='10%' align ='left'>Basic Commission Rate</td>"); 
			out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_comm_rate\" value=\"\" maxlength=18 onblur=\"validate_percentage(this);\"></td>"); 	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr>");
			out.println("<td width='10%' align ='left'>RCC Commission Rate</td>"); 
			out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_rate\" value=\"\" maxlength=18 onblur=\"validate_percentage(this);\"></td>"); 	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			// added by udara 19-07-2018
			out.println("<tr>");
			out.println("<td width='10%' align ='left'>Flag This</td>"); 
			out.println("<td width='30%' align ='left'>  <input type=\"checkbox\" name=\"flag_this\" id=\"flag_this\" value=\"N\" onclick=\"flag_enable();\" > </td>"); 	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			// end by udara 19-07-2018
			
			/*
			out.println("<tr>");
			out.println("<td width='10%' align ='left'>Uploading User</td>"); 
			out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_upload_user\" VALUE='"+m_username+"'  disabled ></td>"); 	
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/

			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
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
