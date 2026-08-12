
/*
	*	DEVELOPED BY : INESH
	*	2018-01-08
	*	JB02012018-02276 NetAsset system documents upload facility
*/
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
  

public class LAKDL_AF_MAS_document_types extends javax.servlet.http.HttpServlet { 

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
			 
			String m_screenDisplayName = "Document Types";
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - "+m_screenDisplayName+"</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/test.js'></SCRIPT>"); //Testing purpose only comment this when you transfer 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			//=========================================================================================================================
			out.println("function load_roll_out_value(){");
			out.println(" 		help_box.innerHTML=\" System Administration - "+m_screenDisplayName+" - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println(" 		help_box.innerHTML=\" System Administration - "+m_screenDisplayName+" - \"+m_val;"); 
			out.println("}"); 
						
			out.println("function new_window(){	"); 
			out.println(" 	window.location.href=window.location.href;"); 
			out.println("}"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("			window.location.href=window.location.href;"); 
			out.println("		}"); 
			out.println("}");
			
			//=========================================================================================================================
			out.println("function beforeSubmit(){		");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_document_types_save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("}		");
			
			out.println("		");
			out.println("		");
			out.println("		");
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select2\"+");
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("					if(document.Form1.HID_HELP_TYPE.value==\"DOCUMENT_TYPE\"){"); 
			out.println("						help_update_document_types();"); 
	  		out.println("					}"); 
			
			
			out.println("				}else{"); 
			out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("					return false;"); 
			out.println("				} "); 			
			out.println("			}else{	"); 
			out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("				}	"); 			
			out.println("		}else{");
			out.println("			clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println(" 	if(oBj.valout[2]==' '){");//**
			out.println(" 		Close();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");  

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}");
			
			out.println("function load_screen_status(m_val){");
			out.println("    if(m_val==\"NEW\"){");
			out.println("        new_window();");
			out.println("    }else if(m_val==\"EDIT\"){");
			out.println("        document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("        document.Form1.hid_status.value=\"Edit\";");
			out.println("        document.Form1.SCREEN_NAME.value=\"EDIT\";");	
		    out.println("        clear_data();");
			out.println("    }else if(m_val==\"DACT\"){");
			out.println("        document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("        document.Form1.hid_status.value=\"De-activate\";");
			out.println("        document.Form1.SCREEN_NAME.value=\"DACT\";");	
			out.println("        clear_data();");
			out.println("    }else if(m_val==\"RACT\"){");
			out.println("        document.Form1.BUT_HELP_MAIN.disabled=false;");
			out.println("        document.Form1.hid_status.value=\"Re-activate\";");
			out.println("        document.Form1.SCREEN_NAME.value=\"RACT\";");
			out.println("        clear_data();");
			out.println("    }");
			out.println("}");
			
			out.println("function document_code_blur(obj){");
			out.println("    if(document.Form1.SCREEN_NAME.value == 'NEW'){");
			out.println("        if(obj.value!=''){");
			out.println("            checkNewDocType(obj);");
			out.println("        }");
			out.println("    }else if(document.Form1.SCREEN_NAME.value == 'EDIT'){");
			out.println("        ");
			out.println("    }");
			out.println("}");
			out.println("function checkNewDocType(obj){");
			out.println("    document.Form1.HID_VAL_TYPE.value ='NEW_DOC_TYPE_VALIDATE';");
			out.println("    var m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_sql_validations_2?chksql=m_prime_chk_LAKDL_AF_MAS_document_types&data_val=\"+obj.value;        ");
			out.println("    load_interface(m_url,'XML');");
			out.println("}");
			out.println("function getEditData(val){");			
			out.println("    document.Form1.HID_VAL_TYPE.value ='GET_EDIT_DATA';");
			out.println("    var m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_sql_validations_2?chksql=m_get_data_LAKDL_AF_MAS_document_types&data_val=\"+val;        ");
			out.println("    load_interface(m_url,'XML');");
			out.println("}");
			out.println("function getDeactValidate(val){");			
			out.println("    document.Form1.HID_VAL_TYPE.value ='GET_DEACT_CHECK_DATA';");
			out.println("    var m_url = servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_sql_validations_2?chksql=m_is_document_types_used&data_val=\"+val;        ");			
			out.println("    load_interface(m_url,'XML');");
			out.println("}"); 
			out.println("function get_vector(data_vec) {");
			
			out.println("    if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.HID_VAL_TYPE.value == 'NEW_DOC_TYPE_VALIDATE'){");
			out.println("        alert('Record already exists.');");
			out.println("        new_window();");
			out.println("    }");
			out.println("    if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.HID_VAL_TYPE.value == 'GET_EDIT_DATA'){        ");
			out.println("        setEditData()");
			out.println("    }");
			
			//out.println("    alert(data_vec.length+'length');");
			
			out.println("    if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"DACT\" && document.Form1.HID_VAL_TYPE.value == 'GET_DEACT_CHECK_DATA'){        ");	
		    out.println("     	if(data_vec == 'ALREADY_USED'){");
			out.println("        	alert('Document Type already used can not deactivate');");
			out.println("        	clear_data();");
			out.println("    	}"); 
			out.println("    }"); 
			out.println("}");
			out.println("function setEditData(data_vec){");
			out.println("    if(document.Form1.HID_DOC_CODE.value = oBj.valout[5]){");
			out.println("        document.Form1.TXT_DOCUMENT_CODE.value     = oBj.valout[2];");
			out.println("        document.Form1.TXT_DOC_DESCR.value         = oBj.valout[3];");
			out.println("        document.Form1.TXT_NUM_OF_SLOTS.value     = oBj.valout[4]; ");			
			out.println("    	if(document.Form1.SCREEN_NAME.value == \"DACT\"){");
			//out.println("    		setTimeout(getDeactValidate(oBj.valout[2]),2000);");			
			out.println("    		data_vec = null;");	
			out.println("    		getDeactValidate(document.Form1.HID_DOC_CODE.value);");			
			out.println("    	}");			
			out.println("    }");	
			out.println("}"); 
			
			
			out.println("function validateNumberOfDoc(obj){");
			out.println("    if(isNaN(obj.value)){");
			out.println("        alert('Please enter valid amount!');");
			out.println("        obj.value ='';");
			out.println("    }else{");
			out.println(" 		if(parseInt(obj.value) > 0){ ");
			out.println("        	obj.value = parseInt(obj.value);");
			out.println("    	}else{");
			out.println(" 		 	alert('Please enter valid amount!');");
			out.println("        	obj.value ='';");
			out.println(" 		}");
			out.println("    }");
			out.println("}");
			out.println("function save_window(){");
			out.println("    var msg ='Are you sure you want to Save?';");
			out.println("    if(document.Form1.SCREEN_NAME.value == \"NEW\"){");
			out.println("        msg ='Are you sure you want to Save?';");
			out.println("    }else if(document.Form1.SCREEN_NAME.value == \"EDIT\"){");
			out.println("        msg ='Are you sure you want to Modify?';");
			out.println("    }");
			out.println("    if(confirm(msg)){");
			out.println("        if(validateFields()){");
			out.println("            beforeSubmit();");
			out.println("        }        ");
			out.println("    }");
			out.println("}");
			out.println("function validateFields(){");
			out.println("    DIV_TXT_DOCUMENT_CODE.style.color='black';");
			out.println("    DIV_TXT_NUM_OF_SLOTS.style.color='black';");
			out.println("    var isValid = true;");
			out.println("    if(document.Form1.TXT_DOCUMENT_CODE.value ==''){");
			out.println("        DIV_TXT_DOCUMENT_CODE.style.color='red';");
			out.println("        isValid = false;");
			out.println("    }else if(document.Form1.TXT_NUM_OF_SLOTS.value ==''){");
			out.println("        DIV_TXT_NUM_OF_SLOTS.style.color='red';");
			out.println("        isValid = false;");
			out.println("    }");
			out.println("    if(!isValid){");
			out.println("        alert('Please fill the fields marked in red.');");
			out.println("    }");
			out.println("    return isValid;");
			out.println("}");
			out.println("function help_update() {");
			out.println("    document.Form1.HID_HELP_TYPE.value=\"DOCUMENT_TYPE\";");
			out.println("    m_sql = \"m_help_TXT_DOCUMENT_TYPE_sql\";");
			out.println("    if(document.Form1.SCREEN_NAME.value == \"EDIT\"||document.Form1.SCREEN_NAME.value == \"DACT\"){");
			out.println("        m_criteria = document.Form1.TXT_DOCUMENT_CODE.value+\"@\"+\"Y@\";");
			out.println("    }else if(document.Form1.SCREEN_NAME.value == \"RACT\"){");
			out.println("        m_criteria = document.Form1.TXT_DOCUMENT_CODE.value+\"@\"+\"N@\";");
			out.println("    }");
			out.println("    HelpBox('1','10','0');");
			out.println("}");
			out.println("function help_update_document_types(){    ");			
			out.println("    document.Form1.HID_DOC_CODE.value = oBj.valout[5];");			
			out.println("    getEditData(oBj.valout[5]);");			
			out.println("}");
			out.println("function clear_data(){");
			out.println("    document.Form1.HID_HELP_TYPE.value = '';");
			out.println("    document.Form1.HID_VAL_TYPE.value = '';");
			out.println("    document.Form1.TXT_DOCUMENT_CODE.value = '';");
			out.println("    document.Form1.TXT_DOC_DESCR.value = '';");
			out.println("    document.Form1.TXT_NUM_OF_SLOTS.value = '';    ");
			out.println("}");
			out.println("function Close(){    ");
			out.println("    clear_data();");
			out.println("}");
			out.println("function validateDesc(obj){    ");
			out.println("    if(obj.value!=''){");
			out.println(" var letterNumber = /^[0-9\\ /a-zA-Z_-]+$/;  ");
			out.println("  	if(!obj.value.match(letterNumber)){   ");			
			out.println("    alert( \"Invalid characters are included \" ); ");
			out.println(" 	 obj.value=''; ");
			out.println(" 	 obj.focus(); ");
			out.println("    return false;");
			out.println(" 	}");
			out.println(" 	}");
			out.println("}");
		
			//=========================================================================================================================
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> ");
			out.println("<input  type='hidden' value='' name='HID_DOC_CODE'> ");
			out.println("<input  type='hidden' value='New' name='hid_status'> ");
			out.println("<input  type='hidden' value='' name='HID_HELP_TYPE'> ");			
			out.println("<input  type='hidden' value='' name='HID_VAL_TYPE'> ");	

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
			//=====================================================================================================================================================
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - "+m_screenDisplayName+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"De-activate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Re-activate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
      		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			//=====================================================================================================================================================
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' border='0' class='table'>"); 

			out.println("<tr>"); 
			out.println(" 	<td width='20%'></td>");
			out.println(" 	<td width='30%'></td>");
			out.println(" 	<td width='50%'></td>");
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println(" 	<td><div id='DIV_TXT_DOCUMENT_CODE'>Document Type Name *</div></td>");
			out.println(" 	<td><input class='txt_input' type='text' name='TXT_DOCUMENT_CODE' onBlur =\"document_code_blur(this);\" maxlength='10' size='10' onblur=\"\">");
			out.println("		<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>");
			out.println(" 	<td></td>");
			out.println("</tr>");
			
			out.println("<tr>"); 
			out.println(" 	<td><div id='DIV_TXT_DOC_DESCR'>Description</div></td>");
			out.println(" 	<td><TEXTAREA class='txt_input' name='TXT_DOC_DESCR' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"\"  onBlur =\"validateDesc(this);\" onKeyDown=\"\" onKeyUp=\"\" ></TEXTAREA></td>");
			out.println(" 	<td></td>");
			out.println("</tr>");
			
			out.println("<tr>");  
			out.println(" 	<td><div id='DIV_TXT_NUM_OF_SLOTS'>Number of Slots *</div></td>");
			out.println(" 	<td><input class='txt_input' type='text' name='TXT_NUM_OF_SLOTS' maxlength='3' size='3'  onblur=\"validateNumberOfDoc(this)\"></td>");
			out.println(" 	<td></td>");
			out.println("</tr>");
			
			//---------------------------------------------------------------------**
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>");

			//--------------------------------------------------***/
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