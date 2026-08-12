
// created by udara on 17-05-2012

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_Cr_book_pledge extends javax.servlet.http.HttpServlet { 

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
			
			String m_chksql=req.getParameter("chksql");
			
			String m_upload=req.getParameter("upload");
			//String m_tomcat_url = "https://dev-lakdl.sasianet.com:/myserver/servlet"; // dev
			String m_tomcat_url = "https://dev-lakdl.sasianet.com:8087/lakdllive/servlet"; // live
			//String m_tomcat_url = "http://www.bkptst.lakdl.lk:8087/lakdllive/servlet";//App Bk Tomcat 
			//out.println("m_chksql="+m_chksql);
			//out.println("m_upload="+m_upload);
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Upload Cr Book List</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 



			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("		if(document.Form1.hid_upload.value==\"null\"){"); 
			//out.println("           document.Form1.AUTHORIZE.disabled=true;"); // commented by udara 11-07-2019
			
			out.println("         }	"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge';"); 
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
			out.println("help_box.innerHTML=\" System Administration - Upload Cr Book List - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Upload Cr Book List - \"+document.Form1.hid_status.value;"); 
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
			
			out.println("  }"); 
			out.println("  else{");
			out.println("     document.Form1.BUT_HELP_MAIN.disabled=false; ");
            out.println("  }"); 
			
			out.println("  document.Form1.SCREEN_NAME.value=m_val;"); 
			
			out.println("  if(m_val==\"NEW\"){");
			out.println("    document.Form1.hid_status.value=\"New\";"); 
			out.println("    document.Form1.hid_save.value=\"Save\";"); 
			out.println("    document.Form1.hid_screen_name.value='NEW';");
		
			out.println("    document.Form1.BUT_HELP_MAIN.disabled=true;");
			out.println("  } ");
			out.println("  else if(m_val==\"EDIT\"){");  
			out.println("    document.Form1.hid_status.value=\"Edit\";"); 
			out.println("    document.Form1.hid_save.value=\"Modify\";"); 
			out.println("    document.Form1.hid_screen_name.value='EDIT';"); 
		
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
		//	out.println("   murl=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
		    out.println("   murl=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_CR_BOOK_help_select\"+"); 
			
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\";");
			//out.println("   window.open(murl);");
	//		out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_CR_BOOK_help_select\"+"); 

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
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_update_value_assign_10();"); 
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
		
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		
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
		 //   out.println("    document.Form1.TXT_SCHEDULE_CODE.value='';"); 
		//	out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value='';");
		//	out.println("    document.Form1.TXT_ITEM_CAT.value='';");
		//	out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		//    out.println("    document.Form1.TXT_ITEM_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		//    out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"3\"){");
		//    out.println("    document.Form1.txt_in_company.value='';"); 
			out.println("}");
			
			// added by udara 19-07-2019
			out.println("else if(document.Form1.hid_help_type.value==\"10\"){");
			out.println("    document.Form1.TXT_LOAN_NO.disabled = false;"); 
			out.println("    document.Form1.TXT_BANK_CODE.disabled = false;"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.disabled = false;");
			
			out.println("    document.Form1.TXT_LOAN_NO.value = '';"); 
			
			out.println("}");
			// end by udarar 19-07-2019
			
			
		    //out.println("window.close();");
		    out.println(" }");
				
			out.println("function clear_data() {");//**
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
		//    out.println("    document.Form1.TXT_SCHEDULE_CODE.value='';"); 
		//	out.println("    document.Form1.TXT_SCHEDULE_DESCRIPTION.value='';");
		//	out.println("    document.Form1.TXT_ITEM_CAT.value='';");
		//	out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){");
		    out.println("    document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"2\"){");
		//    out.println("    document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"3\"){");
		//    out.println("    document.Form1.txt_in_company.value='';"); 
			out.println("}");
			
			// added by udara 19-07-2019
			out.println("else if(document.Form1.hid_help_type.value==\"10\"){");
			out.println("    document.Form1.TXT_LOAN_NO.disabled = false;"); 
			out.println("    document.Form1.TXT_BANK_CODE.disabled = false;"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.disabled = false;");
			out.println("    document.Form1.TXT_LOAN_NO.value = '';"); 
			out.println("}");
			// end by udarar 19-07-2019
			
			
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
			out.println("function help_branch() {"); 
			out.println("bttn_help=\"99\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql_new\";"); 
			//out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql\";"); 
            out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+document.Form1.TXT_BANK_CODE.value+\"@\"+\"Y@\";");
			out.println("    HelpBox('1','10','8');"); 
			out.println("}"); 
			
			out.println("function help_bank() {");
			out.println("bttn_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			out.println("var bank=document.Form1.TXT_BANK_CODE.value.substring(0,1)");
			//out.println("    m_criteria = bank+\"@Y@\";"); 
			out.println("    m_criteria = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
		
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_loan() {");
			out.println("bttn_help=\"10\";");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    m_sql = \"m_help_TXT_LOAN_NO_sql\";"); 
			
			//out.println("    m_criteria = document.Form1.TXT_LOAN_NO.value+\"@Y@\";"); // commented by udara 11-07-2019
			out.println("    m_criteria = document.Form1.TXT_LOAN_NO.value+\"@Y@\"+document.Form1.TXT_BANK_CODE.value+\"@\"+document.Form1.TXT_BRANCH_CODE.value+\"@\";"); // added by udara 11-07-2019
		
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");
		    out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[4];");
		//	out.println("    document.Form1.TXT_ITEM_CAT.value=oBj.valout[4];");
		//	out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[5];");
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
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[2];"); 
			out.println("}"); 	
		
			out.println("function help_update_value_assign_10() {"); 
			out.println("    document.Form1.TXT_LOAN_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[3];"); // added by udara 11-07-2019
			out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[4];"); // added by udara 11-07-2019
			
			// added by udara 19-07-2019
			out.println("    document.Form1.TXT_LOAN_NO.disabled = true;"); 
			out.println("    document.Form1.TXT_BANK_CODE.disabled = true;"); 
			out.println("    document.Form1.TXT_BRANCH_CODE.disabled = true;");
			// end by udara 19-07-2019
			
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
			
			out.println("function upload_save(type){");
			
			// added by udara 19-07-2019
			out.println("       document.Form1.TXT_LOAN_NO.disabled = false;"); 
			out.println("       document.Form1.TXT_BANK_CODE.disabled = false;"); 
			out.println("       document.Form1.TXT_BRANCH_CODE.disabled = false;");
			// end by udara 19-07-2019

		    //	out.println("   	var m_path = document.getElementById('datafile').value; ");
			out.println("   	document.Form1.action='"+m_tomcat_url+"/"+m_fschema_name+"AF_PRO_Save_Cr_book_pledge_upload_excel';");
			out.println("		document.Form1.submit();");
			
			
			out.println("}");
			
			
			// added by udara 24-12-2014
			
		//	out.println("function upload_excel(type){");
		//	out.println("before_upload();"); 
		//	out.println("   }");
			
			
			out.println("function upload_excel(type){");
			//out.println("alert('check');");
			
			out.println("   	if(document.Form1.TXT_BANK_CODE.value=='' && document.Form1.TXT_LOAN_NO.value==''){ ");
			out.println("     		alert('Bank cannot be blank');  ");
			out.println("   	}");
			out.println("   	else if(document.Form1.TXT_BRANCH_CODE.value=='' && document.Form1.TXT_LOAN_NO.value==''){ ");
			out.println("     		alert('Branch cannot be blank'); ");
			out.println("   	}");
			out.println("   	else if(document.Form1.datafile.value==''){ ");
			out.println("     		alert('Select Excel Sheet'); ");
			out.println("   	}");
			out.println("   	else{");
			
			// added by udara 23-12-2019
			out.println("       document.Form1.TXT_LOAN_NO.disabled = false;"); 
			out.println("       document.Form1.TXT_BANK_CODE.disabled = false;"); 
			out.println("       document.Form1.TXT_BRANCH_CODE.disabled = false;");
			// end by udara 23-12-2019
			
			//out.println("   	document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge_upload_excel';"); // commented by udara 28-06-2019
			out.println("   	document.Form1.action='"+m_tomcat_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge_upload_excel';"); // added by udara 28-06-2019
			out.println("		document.Form1.submit();");
			out.println("   	}");
			
			out.println("   }");
			
			
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_upload' VALUE='"+m_upload+"' >");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Upload Cr Book List</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'  onclick='load_screen_status(\"NEW\")'  value=\"New\" ></td>");  
			out.println("    <td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Upload\");' onclick='upload_excel(\"run\")' value=\"Upload\"></td>");
			
			out.println("<td width='*%'></td>");
            out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'   onclick='close_window()' value=\"Close\"></td>");  
			out.println("</tr>"); 
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 
			
		
		    out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_BANK_CODE'  class=div_input>Bank *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5' onblur=\"assign_help_status('H2'),makeRequest(document.Form1.TXT_BANK_CODE)\" onkeypress=\"check_bank()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_BANK_CODE' value=\"Help\" onClick=\"help_bank()\"></td>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
       
	        out.println("<tr >"); 
            out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_BRANCH_CODE)\" onkeypress=\"check_branch()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
            out.println("<td width='20%' ><DIV id='DIV_TXT_LOAN_NO'  class=div_input>Loan No</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOAN_NO' maxlength='50' size='50' onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_LOAN_NO)\" onkeypress=\"check_branch()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_loan()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_DOCUMENT'  class=div_input>Select Excel Sheet </DIV></td>"); 
			out.println("<td width='*%' ><input class=''  type=\"file\" size=\"50\"  name=\"datafile\" id=\"datafile\" >"); 
			//out.println("<input type=\"file\" name=\"datafile\" id=\"datafile\" size=\"20\"   > ");
			out.println("</td>");
			out.println("</tr>");
				
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' >"); 
			out.println("<h4> Upload Instructions</h4>");
			out.println("<ul>");
		    out.println("<li>Excel format should only contain <b>FINANCE_NO</b> column and <b>VEHICLE_NO</b> column, and only files with extention <b>.xls</b> are allowed.</li>"); 
			out.println("<li>If the Loan No is not selected a new Loan No will be generated, if a Loan No is selected the contracts in the excel will be pledged under the selected Loan No.</li>"); 
			out.println("<li>Always refer exception list if available after upload, these contracts will not be pledged.</li>");
	     	out.println("</ul>"); 
			out.println("</tr>"); 
			out.println("</tr>"); 
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
