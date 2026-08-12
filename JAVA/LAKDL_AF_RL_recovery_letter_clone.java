//CREATED BY :	KANISHKA DILSHAN
//DATE/TIME  :	29-07-2015

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RL_recovery_letter_clone extends javax.servlet.http.HttpServlet { 
	
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
			out.println("<TITLE>Recovery Letter Generation - Recovery Letter Setup - Clone</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/jquery/fancybox/jquery-1.4.1.min.js'></script>");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.core.min.js' type=\"text/javascript\"></script>");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.datepicker.min.js' type=\"text/javascript\"></script>");
			out.println("       <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href='"+m_html_client_url+"/jquery/grid/themes/redmond/jquery-ui-1.7.1.custom.css' />"); 
			out.println("       <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href='"+m_html_client_url+"/jquery/grid/themes/ui.jqgrid.css\' />");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/js/i18n/grid.locale-en.js' type=\"text/javascript\"></script>");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/js/jquery.jqGrid.min.js' type=\"text/javascript\"></script>");
			out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/jquery/fancybox/jquery.fancybox-1.3.0.pack.js'></script>");
			out.println("       <link rel=\"stylesheet\" href='"+m_html_client_url+"/jquery/fancybox/jquery.fancybox-1.3.0.css'type='text/css' media=\"screen\">"); 
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.draggable.min.js' type=\"text/javascript\"></script>");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.resizable.min.js' type=\"text/javascript\"></script>");
			out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.dialog.min.js' type=\"text/javascript\"></script>");
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_vector(data_vec) {");

			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='TARGET' && document.Form1.TXT_TARGET_TRANSACTION.value !=\"\" ){");
			out.println("				alert('Record already exists.. Cannot Clone');");
			out.println("    			document.Form1.TXT_TARGET_TRANSACTION.value=''; ");
			out.println("    			help_update_target();");
			out.println("			}");
			
			out.println("}");
			
			
			
			
			out.println("function validate_data(){"); 
			out.println(" 	if(document.Form1.TXT_BASE_TRANSACTION.value==\"\"){  "); 
			out.println(" 		DIV_BASE_TRANSACTION.style.color='red';");
			out.println(" 		return false;"); 
			out.println(" 	}"); 
			out.println(" 	else if(document.Form1.TXT_TARGET_TRANSACTION.value==\"\"){  "); 
			out.println(" 		DIV_TARGET_TRANSACTION.style.color='red';");
			out.println(" 		return false;"); 
			out.println(" 	}else{"); 
			out.println(" 		return true;"); 
			out.println(" 	}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println(" 		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println(" 			document.Form1.elements[i].disabled=false;");
			out.println(" 		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RL_save_letter_clone';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RL_recovery_letter_clone';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RL_recovery_letter_clone';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RL_recovery_letter_clone\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Recovery Letter Generation - Recovery Letter Documents - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Recovery Letter Generation - Recovery Letter Documents - \"+document.Form1.hid_status.value;"); 
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
			
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
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
			out.println("		if(document.Form1.hid_help_type.value==\"BASE\"){"); 
			out.println("    		document.Form1.TXT_BASE_TRANSACTION.value 	= oBj.valout[2];");
			out.println("    		document.Form1.hid_base_desc.value 			= oBj.valout[3];");
			out.println("			loadGrid(); ");
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"TARGET\"){"); 
			out.println("    		document.Form1.TXT_TARGET_TRANSACTION.value=oBj.valout[2];");
			out.println("    		if(document.Form1.TXT_TARGET_TRANSACTION.value==document.Form1.TXT_BASE_TRANSACTION.value){ ");
			out.println("			 	alert('Cannot perform Cloning For the Same Transaction Type.');"); 
			out.println("			 	clear_data(); ");
			out.println("			}"); 	
			out.println("			checkTargetTransactionType(); "); 
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
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_data() {");
			
			
			out.println("		if(document.Form1.hid_help_type.value==\"BASE\"){"); 
			out.println("    		document.Form1.elements[\"TXT_BASE_TRANSACTION\"].value = '';"); 
			out.println("			loadGrid(); ");
			out.println("		}"); 
			
			out.println("		if(document.Form1.hid_help_type.value==\"TARGET\"){"); 
			out.println("    		document.Form1.elements[\"TXT_TARGET_TRANSACTION\"].value = '';"); 
			out.println("		}");
			out.println("}");
				
			out.println(" function checkTargetTransactionType(){ ");
			out.println(" 	assignState('TARGET'); ");
			out.println("   m_url = '" + m_class_url + "/" + m_fschema_name + "AF_RL_sql_validations?chksql=RECOVERY_LETTER_CLONE_BASE_SETUP'+ ");
			out.println("           '&base_code='+document.Form1.TXT_TARGET_TRANSACTION.value+");
			out.println("           '';");
			out.println(" 	load_interface(m_url,'XML');");
			out.println(" }");
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"BASE\";"); 
			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BASE_TRANSACTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_target() {"); 
			out.println("    document.Form1.hid_help_type.value=\"TARGET\";"); 
			out.println("    m_sql = \"m_help_TXT_TRAN_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_TARGET_TRANSACTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
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
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
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
			out.println("    m_sql = \"m_help_TXT_DOC_ID_sql\";");
			out.println("    m_criteria = document.Form1.TXT_DOC_ID.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("           function loadGrid() {");
			
			out.println("          m_caption = 'Document Setup For the Transaction Type '+document.Form1.hid_base_desc.value; ");
			
			out.println("           jQuery('#TABLE_DOCUMENT_CHARGES').jqGrid('GridUnload'); ");
			
			out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_RL_sql_validations?chksql=RECOVERY_LETTER_CLONE_BASE_SETUP'+ ");
			out.println("               		'&base_code='+document.Form1.TXT_BASE_TRANSACTION.value+");
			out.println("               		'';");
			
			out.println("               jQuery('#TABLE_DOCUMENT_CHARGES').jqGrid({");
			out.println("                   url: m_url,");
			out.println("                   loadonce: 1,");
			out.println("                   datatype: 'xml',");
			out.println("                   colNames: [");
			out.println("                       'Document Name',");
			out.println("                       'Dependent Document name',");
			out.println("                       'Duration' ");
			out.println("                   ],");
			out.println("                   colModel: [");
			out.println("                       {name: 'DOCUMENT_ID',                index: 'DOCUMENT_ID',                align: 'left',      width: 350     },");
			out.println("                       {name: 'DEPENDENT_DOCUMENT_ID',      index: 'DEPENDENT_DOCUMENT_ID',      align: 'left',      width: 350     },");
			out.println("                       {name: 'DURATION',              	 index: 'DURATION',              	  align: 'center',    width: 100     }");
			out.println("                   ],");
			out.println("                   mtype: 'GET',");
			out.println("                   rownumbers: false,");
			out.println("                   rowNum: -1,");
			out.println("                   gridview: true,");
			out.println("                   height: 'auto',");
			out.println("                   gridComplete: function() {");
			out.println("                       var int_rec_count = $('#TABLE_DOCUMENT_CHARGES').jqGrid('getGridParam', 'records');");
			out.println("                       $('#hid_rec_count').val(int_rec_count);");
			out.println("                   },");
			out.println("                   caption: m_caption  ");//,
			out.println("               });");
			out.println("           }");
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_row' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_base_desc' VALUE=\"\">");
			out.println("           <input type = \"hidden\" id = \"hid_rec_count\" name = \"hid_rec_count\" />");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Recovery Letter Generation - Recovery Letter Setup - Clone</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\" disabled></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\" disabled></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_BASE_TRANSACTION'  class=div_input>Base Transaction Type *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BASE_TRANSACTION' maxlength='10' size='20' onChange=\"help_update();\" >");  
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update();\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TARGET_TRANSACTION'  class=div_input>Target Transaction Type *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_TARGET_TRANSACTION' maxlength='10' size='10' onChange=\"help_update_target()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_PRO' value=\"Help\" onClick=\"help_update_target()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<hr>");
			out.println("<br>");
			
			out.println("                                               <div id = \"DIV_DOCUMENT_CHARGES\">");
			out.println("                                                   <table id = \"TABLE_DOCUMENT_CHARGES\"></table>");
			out.println("                                               </div>");
			
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