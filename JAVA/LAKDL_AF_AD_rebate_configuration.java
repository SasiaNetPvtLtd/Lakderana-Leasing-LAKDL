/*
	*	Inesh Gunasekara
	*	2018-10-03
	*	JB09112017-01659
*/
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.math.*; 
 

public class LAKDL_AF_AD_rebate_configuration extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt =null;
	public ResultSet rs,rs1,rs2;
	String reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();  
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			stmt=conn.createStatement();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>System Administration - Rebate Configuration </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//========================================================================================================================
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" System Administration - Rebate Configuration  - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" System Administration - Rebate Configuration  - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("			window.location.href=window.location.href;"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println(" 	window.location.href=window.location.href;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
			
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				
				out.println("if(m_val==\"NEW\"){");
				out.println(" 	document.Form1.hid_status.value=\"New\";"); 
				out.println(" 	document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("    	disableFields('ENABLE');");
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Edit\";");  
				out.println(" 	document.Form1.hid_save_status.value=\"Modify\";");
				out.println("    	disableFields('ENABLE');");
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"DEACT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Deactivate\";");
				out.println(" 	document.Form1.hid_save_status.value=\"Deactivate\";");
				out.println("    	disableFields('DISABLE');"); 
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"REACT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Reactivate\";");
				out.println(" 	document.Form1.hid_save_status.value=\"Reactivate\";");
				out.println("    	disableFields('DISABLE');"); 
				out.println("    	restFormFields();");
				out.println("}else{");  
				out.println(" 	document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				 
				
				out.println("function help_update_user() {"); 
				out.println("    document.Form1.hid_help_type.value=\"USER_HELP\";"); 				
				out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");				
				out.println("    	m_sql = \"m_help_TXT_ACTIVE_USER_ID_sql\";");
				out.println("    m_criteria = document.Form1.TXT_USER.value+\"@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"DEACT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"REACT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@N@\";"); 
				out.println("    } ");
				
				out.println("    HelpBox('1','10','0');");
				out.println("}"); 
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("    oBj.valout[4]  = \" \";"); 
				out.println("    oBj.valout[5]  = \" \";"); 
				out.println("    oBj.valout[6]  = \" \";"); 
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
				
				out.println("					if(document.Form1.hid_help_type.value==\"USER_HELP\"){"); 
				out.println("						assignData(oBj);"); 
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
				
				
				out.println("function Close(){    ");
				out.println("    clear_data();");
				out.println("}");
				 
				
				out.println("function before_submit(){  ");
				out.println("	if(validateForm()){  ");
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){  ");
				out.println(" 			for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println(" 				document.Form1.elements[i].disabled=false;");
				out.println(" 			}");
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_AD_rebate_configuration?chksql=save_data';  ");
				out.println("			document.Form1.submit();"); 
				out.println("		}  ");
				out.println("	}  ");
				out.println("}  "); 
				
				out.println("function validateData(type,obj){");
				out.println("    ");
				out.println("    if(obj.value ==''){");
				out.println("        obj.value = '';");
				out.println("    }else{");
				out.println("        if(isNaN(obj.value)){");
				out.println("            alert('Please enter valid amount');");
				out.println("            obj.value = '';");
				out.println("        }else{");
				out.println("            if(parseInt(obj.value) > 100 || parseInt(obj.value) < 0){");
				out.println("                alert('Please enter valid amount');");
				out.println("                obj.value = '';");
				out.println("            }else{");
				out.println("                if(type == 'REBATE_FROM'){");
				out.println("                    if(document.Form1.TXT_REBATE_TO.value != ''){");
				out.println("                        if(parseInt(document.Form1.TXT_REBATE_TO.value) < parseInt(obj.value)){");
				out.println("                            alert('Please enter valid range');");
				out.println("                            obj.value = '';");
				out.println("                        }");
				out.println("                    }");
				out.println("                }else if(type == 'REBATE_TO'){");
				out.println("                    if(document.Form1.TXT_REBATE_FROM.value != ''){");
				out.println("                        if(parseInt(document.Form1.TXT_REBATE_FROM.value) > parseInt(obj.value)){");
				out.println("                            alert('Please enter valid range');");
				out.println("                            obj.value = '';");
				out.println("                        }");
				out.println("                    }");
				out.println("                }else if(type == 'ODI_FROM'){");
				out.println("                    if(document.Form1.TXT_ODI_TO.value != ''){");
				out.println("                        if(parseInt(document.Form1.TXT_ODI_TO.value) < parseInt(obj.value)){");
				out.println("                            alert('Please enter valid range');");
				out.println("                            obj.value = '';");
				out.println("                        }");
				out.println("                    }");
				out.println("                }else if(type == 'ODI_TO'){");
				out.println("                    if(document.Form1.TXT_ODI_FROM.value != ''){");
				out.println("                        if(parseInt(document.Form1.TXT_ODI_FROM.value) > parseInt(obj.value)){");
				out.println("                            alert('Please enter valid range');");
				out.println("                            obj.value = '';");
				out.println("                        }");
				out.println("                    }");
				out.println("                }");
				out.println("            }");
				out.println("        }");
				out.println("    }    ");
				out.println("}");
				out.println("");
				out.println("function clear_data(){");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'NEW'){");
				out.println("        document.Form1.TXT_USER.value = '';");
				out.println("    }else{");
				out.println("        document.Form1.TXT_USER.value             = '';");
				out.println("        document.Form1.TXT_REBATE_FROM.value     = '';");
				out.println("        document.Form1.TXT_REBATE_TO.value         = '';");
				out.println("        document.Form1.TXT_ODI_FROM.value         = '';");
				out.println("        document.Form1.TXT_ODI_TO.value         = '';");
				out.println("    }");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'DEACT' || document.Form1.SCREEN_NAME.value == 'REACT'){");
				//out.println("        disableFields('ENABLE');");
				out.println("    }");
				out.println("}");
				out.println("function disableFields(status){");
				out.println("    if(status == 'ENABLE'){");
				out.println("        document.Form1.TXT_REBATE_FROM.disabled = false;");
				out.println("        document.Form1.TXT_REBATE_TO.disabled     = false;");
				out.println("        document.Form1.TXT_ODI_FROM.disabled     = false;");
				out.println("        document.Form1.TXT_ODI_TO.disabled         = false;");
				out.println("    }");
				out.println("    if(status == 'DISABLE'){");
				out.println("        document.Form1.TXT_REBATE_FROM.disabled = true;");
				out.println("        document.Form1.TXT_REBATE_TO.disabled     = true;");
				out.println("        document.Form1.TXT_ODI_FROM.disabled     = true;");
				out.println("        document.Form1.TXT_ODI_TO.disabled         = true;");
				out.println("    }");
				out.println("}");
				out.println("function assignData(oBj){");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'NEW'){");
				out.println("        document.Form1.TXT_USER.value = oBj.valout[2];");
				out.println("    }else{");
				out.println("        document.Form1.TXT_USER.value             = oBj.valout[2];");
				out.println("        document.Form1.TXT_REBATE_FROM.value     = oBj.valout[3];");
				out.println("        document.Form1.TXT_REBATE_TO.value         = oBj.valout[4];");
				out.println("        document.Form1.TXT_ODI_FROM.value         = oBj.valout[5];");
				out.println("        document.Form1.TXT_ODI_TO.value         = oBj.valout[6];");
				
				// added by udara 30-10-2018
				out.println("          document.Form1.txt_charge_applicable.value =oBj.valout[7]; ");
				out.println("        if(oBj.valout[7]=='Y'){");
				out.println("          document.Form1.txt_charge_applicable.checked=true; ");
				out.println("        }");
				// added by udara 30-10-2018
				
				out.println("    }");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'DEACT' || document.Form1.SCREEN_NAME.value == 'REACT'){");
				out.println("        disableFields('DISABLE');");
				out.println("    }");
				out.println("}");
				out.println("function save_window(){    ");
				out.println("    before_submit();");
				out.println("}");
				out.println("");
				out.println("function validateForm(){");
				out.println("    resetDivs();");
				out.println("    var restults = true;");
				out.println("    ");
				out.println("    if(document.Form1.TXT_USER.value ==''){");
				out.println("        DIV_TXT_USER.style.color = 'red';");
				out.println("        restults = false;");
				out.println("    }");
				out.println("    if(document.Form1.TXT_REBATE_FROM.value ==''){");
				out.println("        DIV_TXT_REBATE_FROM.style.color = 'red';");
				out.println("        restults = false;");
				out.println("    }");
				out.println("    if(document.Form1.TXT_REBATE_TO.value ==''){");
				out.println("        DIV_TXT_REBATE_TO.style.color = 'red';");
				out.println("        restults = false;");
				out.println("    }");
				out.println("    if(document.Form1.TXT_ODI_FROM.value ==''){");
				out.println("        DIV_TXT_ODI_FROM.style.color = 'red';");
				out.println("        restults = false;");
				out.println("    }");
				out.println("    if(document.Form1.TXT_ODI_TO.value ==''){");
				out.println("        DIV_TXT_ODI_TO.style.color = 'red';");
				out.println("        restults = false;");
				out.println("    }");
				out.println("    if(!restults){");
				out.println("        alert('Please fill required fields !');");
				out.println("    }");
				out.println("    return restults;");
				out.println("}");
				out.println("function resetDivs(){");
				out.println("    DIV_TXT_USER.style.color='black';");
				out.println("    DIV_TXT_REBATE_FROM.style.color='black';");
				out.println("    DIV_TXT_REBATE_TO.style.color='black';");
				out.println("    DIV_TXT_ODI_FROM.style.color='black';");
				out.println("    DIV_TXT_ODI_TO.style.color='black';");
				out.println("}");
				out.println("function restFormFields(){");
				out.println("    document.Form1.TXT_USER.value             = '';");
				out.println("    document.Form1.TXT_REBATE_FROM.value     = '';");
				out.println("    document.Form1.TXT_REBATE_TO.value         = '';");
				out.println("    document.Form1.TXT_ODI_FROM.value         = '';");
				out.println("    document.Form1.TXT_ODI_TO.value         = '';");
				out.println("}");
		
				//========================================================================================================================
				
				// added by udara 30-10-2018
				out.println("function set_charge_applicable_value(obj){");
				out.println("  if(obj.checked==true){");
				out.println("     obj.value='Y'; ");
				out.println("  }");
				out.println("  else{");
				out.println("     obj.value='N'; ");
				out.println("  }");
				//out.println("  alert(obj.value); ");
				out.println("}");
				// end by udara 30-10-2018
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='' > "); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 			
				
					
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");				
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Rebate Configuration  </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onClick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DEACT\")' value=\"Deactivate\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"REACT\")' value=\"Reactivate\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("</br>"); 
				out.println("</br>"); 
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td width='10%' ><DIV id='DIV_TXT_USER'  class=div_input>User </DIV></td>");
				out.println(" 	<td width='8%'  ><input class='txt_input' type='text' name='TXT_USER' maxlength='20' style='{width=90px}' size='10' onblur='help_update_user()'></td>"); 
				out.println(" 	<td width='10%'  ><input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_update_user()\"></td>"); 
				out.println(" 	<td width='*%'  ></td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td ><DIV id='DIV_TXT_REBATE_FROM'  class=div_input>Rebate % from</DIV></td>");
				out.println(" 	<td ><input class='txt_input' type='text' name='TXT_REBATE_FROM' maxlength='5'  style='{width=80px;text-align=right;}' size='5' onblur='validateData(\"REBATE_FROM\",this)'></td>");
				out.println(" 	<td ><DIV id='DIV_TXT_REBATE_TO'  class=div_input>&nbsp;&nbsp;Rebate % to</DIV></td>");
				out.println(" 	<td ><input class='txt_input' type='text' name='TXT_REBATE_TO' maxlength='5'  style='{width=80px;text-align=right;}' size='5' onblur='validateData(\"REBATE_TO\",this)'></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td ><DIV id='DIV_TXT_ODI_FROM'  class=div_input>ODI % from</DIV></td>");
				out.println(" 	<td ><input class='txt_input' type='text' name='TXT_ODI_FROM' maxlength='5'  style='{width=80px;text-align=right;}' size='5' onblur='validateData(\"ODI_FROM\",this)'></td>");
				out.println(" 	<td ><DIV id='DIV_TXT_ODI_TO'  class=div_input>&nbsp;&nbsp;ODI % to</DIV></td>");
				out.println(" 	<td ><input class='txt_input' type='text' name='TXT_ODI_TO' maxlength='5'  style='{width=80px;text-align=right;}' size='5' onblur='validateData(\"ODI_TO\",this)'></td>");
				out.println("</tr>");
				
				 
				out.println("<tr class=tr_input>");
				out.println(" 	<td > Charge Applicable </td>");
				out.println(" 	<td >  <input type='checkbox' name='txt_charge_applicable' value='N' onclick='set_charge_applicable_value(this);' > </td>");
				out.println(" 	<td ></td>");
				out.println(" 	<td ><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onclick='save_window()' value=\"Save\"></td>");
				out.println("</tr>");
				
				out.println("</table>"); 
				
				out.println("</br>"); 
				out.println("</br>"); 
				out.println("</br>"); 
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				//out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/test.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			else if(m_chksql.equals("save_data")){
				
				try{
					
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_AD_REBATE_CONFIGURATION_SAV(:1,:2,:3,:4,:5,:6,:7,:8); END;");
					callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_USER")).trim());
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_REBATE_FROM"));
					callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_REBATE_TO"));
					callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_ODI_FROM"));
					callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ODI_TO"));
					callstmt.setString(6,m_username);
					callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"txt_charge_applicable")); // added by udara 30-10-2018
					callstmt.execute();
					callstmt.close();
					
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Information saved successfully');");
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_AD_rebate_configuration?chksql=main_page';");
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'></body>");
					out.println("</html>");
					
					out.flush();
				}catch(Exception e){
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error When Saving Record..');");
					out.println("window.history.back();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>"+e.getMessage()+"</body>");
					out.println("</html>");
				}
			} 
		else if(m_chksql.equals("audit_report_main")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				String m_date_dd = "";
				String m_date_mm = "";
				String m_date_yy = "";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>System Administration - Rebate Configuration </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//========================================================================================================================
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" System Administration - Rebate Configuration Audit Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" System Administration - Rebate Configuration Audit Report - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("			window.location.href=window.location.href;"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println(" 	window.location.href=window.location.href;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
			
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				
				out.println("if(m_val==\"NEW\"){");
				out.println(" 	document.Form1.hid_status.value=\"New\";"); 
				out.println(" 	document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("    	disableFields('ENABLE');");
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Edit\";");  
				out.println(" 	document.Form1.hid_save_status.value=\"Modify\";");
				out.println("    	disableFields('ENABLE');");
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"DEACT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Deactivate\";");
				out.println(" 	document.Form1.hid_save_status.value=\"Deactivate\";");
				out.println("    	disableFields('DISABLE');"); 
				out.println("    	restFormFields();");
				out.println("}else if(m_val==\"REACT\"){");  
				out.println(" 	document.Form1.hid_status.value=\"Reactivate\";");
				out.println(" 	document.Form1.hid_save_status.value=\"Reactivate\";");
				out.println("    	disableFields('DISABLE');"); 
				out.println("    	restFormFields();");
				out.println("}else{");  
				out.println(" 	document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				 
				
				out.println("function help_update_user() {"); 
				out.println("    document.Form1.hid_help_type.value=\"USER_HELP\";"); 				
				out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");				
				out.println("    	m_sql = \"m_help_TXT_ACTIVE_USER_ID_sql_2\";");
				out.println("    m_criteria = document.Form1.TXT_USER.value+\"@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"DEACT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@Y@\";"); 
				out.println("	 }else if(document.Form1.SCREEN_NAME.value==\"REACT\"){	");
				out.println("    	m_sql = \"m_help_TXT_REBATE_RATE_sql\";"); 
				out.println("   	m_criteria = document.Form1.TXT_USER.value+\"@N@\";"); 
				out.println("    } ");
				
				out.println("    HelpBox('1','10','0');");
				out.println("}"); 
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("    oBj.valout[4]  = \" \";"); 
				out.println("    oBj.valout[5]  = \" \";"); 
				out.println("    oBj.valout[6]  = \" \";"); 
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
				
				out.println("					if(document.Form1.hid_help_type.value==\"USER_HELP\"){"); 
				out.println("						assignData(oBj);"); 
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
				
				
				out.println("function Close(){    ");
				out.println("    clear_data();");
				out.println("}");
				
				
				out.println("function clear_data(){");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'NEW'){");
				out.println("        document.Form1.TXT_USER.value = '';");				
				out.println("    }");				
				out.println("}");
				
				out.println("function assignData(oBj){");
				out.println("    if(document.Form1.SCREEN_NAME.value == 'NEW'){");
				out.println("        document.Form1.TXT_USER.value = oBj.valout[2];");
				out.println(" 	}  ");
				out.println("}");
				
				
				out.println("function validateFields(){");
				out.println("    if(document.Form1.TXT_FROM_DATE_DD.value == '' ||document.Form1.TXT_FROM_DATE_MM.value == '' ||document.Form1.TXT_FROM_DATE_YY.value == '' ||document.Form1.TXT_TO_DATE_DD.value == '' ||document.Form1.TXT_TO_DATE_MM.value == '' ||document.Form1.TXT_TO_DATE_YY.value == '' ){");
				out.println("         alert('Please enter date range !'); "); 
				out.println("         return false; "); 
				out.println(" 	 }");
				out.println(" 	 var fromD = new Date(document.Form1.TXT_FROM_DATE_YY.value,document.Form1.TXT_FROM_DATE_MM.value,document.Form1.TXT_FROM_DATE_DD.value);");
				out.println("    var toD   = new Date(document.Form1.TXT_TO_DATE_YY.value,document.Form1.TXT_TO_DATE_MM.value,document.Form1.TXT_TO_DATE_DD.value); "); 
				out.println(" 	 if(fromD > toD){");
				out.println("         alert('Please enter valid date range !'); "); 
				out.println("         return false; "); 
				out.println(" 	 }");
				out.println("    if(document.Form1.TXT_USER.value == ''){"); //requested by maduranga
				out.println("         alert('Please enter user !'); "); 
				out.println("         return false; "); 
				out.println(" 	 }");
				out.println("    return true; "); 
				out.println("}");
				
				out.println("function viewReport(){");
				out.println(" 	if(validateFields()){");
				out.println(" 	 	var fromYY = document.Form1.TXT_FROM_DATE_YY.value; ");
				out.println(" 	 	var fromMM = document.Form1.TXT_FROM_DATE_MM.value; ");
				out.println(" 	 	var fromDD = document.Form1.TXT_FROM_DATE_DD.value; ");
				
				out.println(" 		var toYY   = document.Form1.TXT_TO_DATE_YY.value;");
				out.println(" 		var toMM   = document.Form1.TXT_TO_DATE_MM.value;");
				out.println(" 		var toDD   = document.Form1.TXT_TO_DATE_DD.value;");
				out.println(" 		m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 		m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_AD_rebate_configuration?chksql=view_audit_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&userName=\"+document.Form1.TXT_USER.value;");
				out.println("    	popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println(" 	}");
				out.println("}");
				//========================================================================================================================
				
				out.println("function validate_date(){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
				out.println("    if(checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
				out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
				out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
				out.println("      	  return true;"); 
				out.println("  	  	 }");
				out.println("        else{ "); 
				out.println("         return false; "); 
				out.println("     }");
				out.println("     }");
				out.println("			else {");
				out.println("   		alert('To Date cannot be empty ');");
				out.println("   		return false;"); 
				out.println("     }");
				out.println("     }");
				out.println("        else {"); 
				out.println("         return false; "); 
				out.println("     }");
	
				out.println("     }");
				out.println("			else {");
				out.println("   		alert('From Date cannot be empty ');");
				out.println("   		return false;"); 
				out.println("     }");
				out.println("    }");
				
				//===================================================================================================
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println(" 		v_date=val.substr(0,val.indexOf('-'));");
				out.println(" 		if(v_date.length<2)");
				out.println(" 			v_date=0+v_date");
				out.println(" 		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println(" 		v_month=val.substr(0,val.indexOf('-'));");
				out.println(" 		if(v_month.length<2)");
				out.println(" 			v_month=0+v_month");
				out.println(" 		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     	document.Form1.TXT_FROM_DATE_DD.value=v_date;");
				out.println("     	document.Form1.TXT_FROM_DATE_MM.value=v_month;");
				out.println("     	document.Form1.TXT_FROM_DATE_YY.value=val;");
				out.println(" 		date1=v_date+'-'+v_month+'-'+val;");
				out.println(" 		document.Form1.hid_from_date.value=date1");			
				out.println("}");
				
				out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println(" 		v_date=val.substr(0,val.indexOf('-'));");
				out.println(" 		if(v_date.length<2)");
				out.println(" 			v_date=0+v_date");
				out.println(" 		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println(" 		v_month=val.substr(0,val.indexOf('-'));");
				out.println(" 		if(v_month.length<2)");
				out.println(" 			v_month=0+v_month");
				out.println(" 		val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     	document.Form1.TXT_TO_DATE_DD.value=v_date;");
				out.println("     	document.Form1.TXT_TO_DATE_MM.value=v_month;");
				out.println("     	document.Form1.TXT_TO_DATE_YY.value=val;");				
				out.println(" 		date2=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println(" 		document.Form1.hid_to_date.value=date2");
				out.println("     	document.Form1.TXT_TO_DATE_YY.focus();");
				out.println(" 	}");
				out.println("}");
				
				out.println("function get_sys_date(){");
				rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				if(rs.next()){
				m_date_dd = rs.getString(1).substring(0,2);
				m_date_mm = rs.getString(1).substring(3,5);
				m_date_yy = rs.getString(1).substring(6,10);
				}
				out.println("document.Form1.VAL_DAY1.value   =\""+m_date_dd+"\"");
				out.println("document.Form1.VAL_MONTH1.value =\""+m_date_mm+"\"");
				out.println("document.Form1.VAL_YEAR1.value  =\""+m_date_yy+"\"");
				out.println("document.Form1.VAL_DAY2.value   =\""+m_date_dd+"\"");
				out.println("document.Form1.VAL_MONTH2.value =\""+m_date_mm+"\"");
				out.println("document.Form1.VAL_YEAR2.value  =\""+m_date_yy+"\"");
				out.println("}");
				
				
				out.println("function check_date(objdd,objmm,objyy) {");						
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");			
				out.println("}");
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='' > "); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 			
				
				out.println("<input type=hidden name='hid_from_date' value=\"\">");
				out.println("<input type=hidden name='hid_to_date' value=\"\">");
					
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");				
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Rebate Configuration Audit Report  </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Report\");' 		onClick='viewReport()' value=\"View Report\"></td>");  				 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("</br>"); 
				out.println("</br>"); 
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td width='10%' ><DIV id='DIV_TXT_USER'  class=div_input>User </DIV></td>");
				out.println(" 	<td width='15%'  ><input class='txt_input' type='text' name='TXT_USER' maxlength='20' style='{width=90px}' size='10' onblur='help_update_user()'></td>"); 
				out.println(" 	<td width='10%'  ><input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_update_user()\"></td>"); 
				out.println(" 	<td width='*%'  ></td>"); 
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td ><DIV id='DIV_TXT_FROM'  class=div_input>From Date </DIV></td>");
				out.println(" 	<td ><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println(" 		 <input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println(" 		 <input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" onblur='validate_date();' ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");	
				out.println(" 	</td >"); 
				out.println(" 	<td ></td>"); 
				out.println(" 	<td ></td>"); 
				out.println("</tr >"); 
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td ><DIV id='DIV_TXT_TO'  class=div_input>To Date </DIV></td>");
				out.println(" 	<td ><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println(" 		 <input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println(" 		 <input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" onblur='validate_date();' ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a>");	
				out.println(" 	<td >"); 
				out.println(" 	<td ></td>"); 
				out.println(" 	<td ></td>"); 
				out.println("</tr >"); 
				
				out.println("</table>"); 
				
				out.println("</br>"); 
				out.println("</br>"); 
				out.println("</br>"); 
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				//out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/test.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
		else if(m_chksql.equals("view_audit_report")){
			try{
			String m_from_date=req.getParameter("from_date");
			String m_to_date  =req.getParameter("to_date");
			String m_user     =req.getParameter("userName");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Rebate Configuration </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("</script>");
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println(" <tr>"); 
			out.println(" 	<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println(" 	<td class='border_wht' valign='top'> "); 
			out.println(" 		<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println(" 			<tr> "); 
			out.println(" 				<td height='30' class='pdn_mainHD'>Audit Report</td>"); 
			out.println(" 			</tr>"); 
			out.println(" 			<tr> "); 
			out.println("  				<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println(" 			</tr>"); 
			out.println(" 			<tr>"); 
			out.println(" 				<td style='height: 327px'>"); 
			out.println(" 				<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println(" 				<tr>"); 
			out.println(" 					<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println(" 				</tr>"); 
			out.println(" 				<tr>"); 
			out.println(" 					<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Finance - Audit Reports  Rebate Configuration </td>"); 
			out.println(" 				</tr>"); 
			out.println(" 				<tr>"); 
			out.println(" 					<td  height='10px' class='pdn_txtpos'>"); 
			out.println(" 						<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println(" 							<tr><td width='13%' align='center'></td>");  
			out.println(" 							<td width='12%' align='center'></td>");  
			out.println(" 							<td width='12%' align='center'></td>");  			
			out.println(" 							<td width='12%'></td>");  			
			out.println(" 							<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout=''   onclick='close_window()' value=\"Close\"></td>"); //load_roll_out_value()
			out.println(" 							<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println(" 						</table>");  
			out.println(" 				</td></tr>"); 			
			out.println("  		  <tr><td>");  
			 
			out.println("<table class=\"table\"   cellspacing=0 > "); 
			
			out.println("<tr >");	
			out.println(" 	<td width='15%'  align='center' style=\"border: 1px solid black\"><b>User</b></td>");	
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>Rebate % from</b></td>");	
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>Rebate % to</b></td>");	
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>ODI % from</b></td>");	
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>ODI % to</b></td>");				
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>Active Status</b></td>");	
			out.println(" 	<td width='10%'  align='center' style=\"border: 1px solid black\"><b>Enter/Modified User</b></td>");	
			out.println(" 	<td width='15%'  align='center' style=\"border: 1px solid black\"><b>Enter/Modified Date</b></td>");				
			out.println("</tr>");
			
			 String data_q = " SELECT * FROM ( SELECT USER_ID,  REBATE_FROM,  REBATE_TO,  ODI_FROM,  ODI_TO, "+
			 "   DECODE(ACTIVE_STATUS,'Y','Active','Deactive') ACTIVE_STATUS,   "+
			 "   nvl(MOD_USER,ENT_USER) MOD_USER,    "+
			 "   NVL(TO_CHAR(MOD_DATE,'YYYY-MM-DD HH:MI'),TO_CHAR(ENT_DATE,'YYYY-MM-DD HH:MI')) MOD_DATE, "+
			 "   NVL(MOD_DATE,ENT_DATE) MOD_DATE_D "+
			 " FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION  "+
			 " WHERE USER_ID NOT IN (SELECT DISTINCT USER_ID FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION_BK) "+
			 " AND USER_ID LIKE UPPER('%"+m_user+"%') "+
			 " AND TRUNC(NVL(MOD_DATE,ENT_DATE),'DD') BETWEEN TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 " UNION ALL "+
			 " SELECT USER_ID, "+
			 "   P_REBATE_FROM REBATE_FROM,  P_REBATE_TO REBATE_TO,  P_ODI_FROM ODI_FROM,  P_ODI_TO ODI_TO, "+
			 "   DECODE(P_ACTIVE_STATUS,'Y','Active','Deactive') ACTIVE_STATUS,   "+
			 "   nvl(MOD_USER,ENT_USER) MOD_USER, "+
			 "   NVL(TO_CHAR(MOD_DATE,'YYYY-MM-DD HH:MI'),TO_CHAR(ENT_DATE,'YYYY-MM-DD HH:MI')) MOD_DATE,   "+
			 "   NVL(MOD_DATE,ENT_DATE) MOD_DATE_D "+
			 " FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION_BK  "+
			 " WHERE USER_ID LIKE UPPER('%"+m_user+"%') "+
			 " AND TRUNC(NVL(MOD_DATE,ENT_DATE),'DD') BETWEEN TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 " ) ORDER BY MOD_DATE_D ASC ";
			//out.println(data_q);
			rs=stmt.executeQuery(data_q);
			while(rs.next()){
				out.println("<tr >");	
				out.println(" 	<td style=\"border: 1px solid black\" align='center'>"+rs.getString("USER_ID")+"</td>");	//User	
				out.println(" 	<td style=\"border: 1px solid black\" align='right'>"+rs.getString("REBATE_FROM")+"</td>");	//Rebate % from	
				out.println(" 	<td style=\"border: 1px solid black\" align='right'>"+rs.getString("REBATE_TO")+"</td>");	//Rebate % to	
				out.println(" 	<td style=\"border: 1px solid black\" align='right'>"+rs.getString("ODI_FROM")+"</td>");	//ODI % from	
				out.println(" 	<td style=\"border: 1px solid black\" align='right'>"+rs.getString("ODI_TO")+"</td>");	//ODI % to	
				
				out.println(" 	<td style=\"border: 1px solid black\" align='center'>"+rs.getString("ACTIVE_STATUS")+"</td>");	//Active Status	
				out.println(" 	<td style=\"border: 1px solid black\" align='center'>"+rs.getString("MOD_USER")+"</td>");	//Enter/Modified User	
				out.println(" 	<td style=\"border: 1px solid black\" align='center'>"+rs.getString("MOD_DATE")+"</td>");	//Enter/Modified Date				
				out.println("</tr>");
			}
				
			out.println("</table>");			
						
			out.println("</table>");		 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>");
			out.println("</html>");
		}catch(Exception e){
			out.println("Error "+e.getMessage());
		}
		}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
