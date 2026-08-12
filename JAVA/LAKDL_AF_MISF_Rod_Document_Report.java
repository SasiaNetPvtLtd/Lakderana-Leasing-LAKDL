//Created by Minal on 31-12-2014 for #14902

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Rod_Document_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	java.text.NumberFormat nf,nf1;
	 String m_chksql=null;
	

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();

     		m_chksql=req.getParameter("chksql");
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			CallableStatement callstmt =null;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (m_chksql.equals("run_report")) {
                
			
                String m_contract_no        = req.getParameter("contract_no");
              
                
                try {
                   // callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_ROD_DOCUMENT_RUN(:1,:2); END;");
					  callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_ROD_DOCUMENT_RUN_2(:1,:2); END;");
                    
                    callstmt.setString(1, m_contract_no);
                    callstmt.setString(2, m_username);
                    callstmt.execute();
                    out.print("OK");
                }
                catch(Exception ex) {
                    out.println("ERROR : " + ex.toString());
					conn.close();
                } 
                
            }
			
			// added by udara 05-12-2018
			else if (m_chksql.equals("run_report_guar")) {
                
			
                String m_client_no        = req.getParameter("client_no");
              
                
                try {
					  callstmt = conn.prepareCall("BEGIN " + m_schema_name + ".AF_ROD_DOCUMENT_RUN_GUAR(:1,:2); END;");
                    
                    callstmt.setString(1, m_client_no);
                    callstmt.setString(2, m_username);
                    callstmt.execute();
                    out.print("GUAR");
                }
                catch(Exception ex) {
                    out.println("ERROR : " + ex.toString());
					conn.close();
                } 
				
				
				//out.print("GUAR");
                
            }
			// end by udara 05-12-2018

			else if(m_chksql.equals("main_page")){
				
				
			String m_user_branch = "";
			
			rs=stmt.executeQuery(" "+
				" SELECT "+ 
					" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
						" FROM DUAL ");
			
			if(rs.next()){
				m_user_branch = rs.getString(1);
			}
			
			if(m_user_branch.equals("HO")){
				m_user_branch = "";
			}
				

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>ROD Document</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			
			out.println("var timerID;");
			out.println("var durationID=0;");
			
			out.println("function set_timer_actions() {");
			out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("help_button_finance();");
			out.println("			}");
			out.println("}");




			out.println("function load_lock(){	"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MISF_display_contract_details\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"ROD Document - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"ROD Document - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			/*out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); */
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
		/*	out.println("else if(m_val!=\"EDIT\"){"); 
			//out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			//out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			//out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			//out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
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
			out.println("}"); */
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function makeRequest1(obj) {");
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_ROD_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("	help_value_assign_1()");
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
			out.println("   document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("   document.Form1.TXT_CLIENT_CODE.value=\"\";  ");
			out.println("   DIV_TXT_CLIENT_CODE.innerHTML=\"\";  ");
			
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=\"\";  ");
			out.println("   DIV_TXT_CLIENT_CODE.innerHTML=\"\";  ");
			out.println("	}	"); 
			out.println("}"); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 


			out.println("function assig(val) {"); 
			out.println("document.Form1.hid_assig.value=val");
			out.println("}");
			

		    out.println("function run_report() {");
			//out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.TXT_FINANCE_NO.value!=\"-\" ){");
			out.println(" 	m_contract_no = document.Form1.TXT_FINANCE_NO.value ");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report?chksql=run_report&contract_no=\"+m_contract_no;");  
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("	}");
			out.println("else{");
			out.println("alert('Please enter Finance No')");
			out.println("}");
			out.println("}");
			
			// added by udara 05-12-2018
			out.println("function run_report_guar() {");
			//out.println("if(document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.TXT_CLIENT_CODE.value!=\"-\" ){");
			out.println(" 	m_client_no = document.Form1.TXT_CLIENT_CODE.value ");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report?chksql=run_report_guar&client_no=\"+m_client_no;");  
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("	}");
			out.println("else{");
			out.println("alert('Please select a client code')");
			out.println("}");
			out.println("}");
			// end by udara 05-12-2018

       		out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			out.println("			view();"); 
			out.println("		}");
			out.println("		else if(m_data==\"GUAR\"){");
			out.println("			view_guar();"); 
			//out.println("			alert('Ready');");
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
			
			out.println("function view(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println(" 	m_contract_no = document.Form1.TXT_FINANCE_NO.value ");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report_Detail?chksql=main_page&contract_no=\"+m_contract_no;"); 
		    out.println("			window.open(m_url);");
			out.println("	}");
			out.println("}");
			
			out.println("function view_guar(){");
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("if(document.Form1.TXT_CLIENT_CODE.value!=\"\"){");
			out.println(" 	m_client_no = document.Form1.TXT_CLIENT_CODE.value ");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rod_Document_Report_Detail_Guar?chksql=main_page&client_no=\"+m_client_no;"); 
		    out.println("			window.open(m_url);");
			out.println("	}");
			out.println("}");
			
			

			out.println("function help_button_finance() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			
			//out.println("    m_sql = \"m_help_TXT_confirmation_rpt_gen_sql\";"); // commented by udara 19-05-2015
			
			out.println(" if(document.Form1.generate_status.value=='N'){ ");
			out.println("    m_sql = \"m_help_TXT_ROD_document_sql\";");
			out.println(" }");
			out.println(" else if(document.Form1.generate_status.value=='R'){");
			out.println("    m_sql = \"m_help_TXT_confirmation_rpt_regenerate_sql\";");
			out.println(" }");
			
			//out.println("    alert(m_sql); ");
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql2\";"); 
			//out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\""+m_username+"\"+\"@\"+\""+m_user_branch+"\"+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			
			out.println("function help_value_assign_1() {"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("   document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];  ");
			out.println("   DIV_TXT_CLIENT_CODE.innerHTML=oBj.valout[4];  ");
			//out.println("   alert(oBj.valout[7]); ");
			out.println("}");
			
			// added by udara 19-05-2015
			
			out.println("function re_generate_report(gen_status) {"); 
			
			out.println("  if(gen_status=='new') { ");
			out.println("    document.Form1.generate_status.value='N';");
			out.println("    document.Form1.hid_status.value='New';"); 
			out.println("  }");
			out.println("  else if (gen_status=='re_gen'){");
			out.println("    document.Form1.generate_status.value='R';");
			out.println("    document.Form1.hid_status.value='Re-Generate';"); 
			out.println("  }");
			
			//out.println("  alert(document.Form1.generate_status.value);  ");

			out.println("}");
			
			// end by udara 19-05-2015
			
			out.println(" function clearBox(){ ");
			out.println("    document.getElementById('DIV_TXT_CLIENT_CODE').innerHTML = \"\"; ");
			out.println(" } ");

//-----------------------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='generate_status' VALUE=\"N\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>ROD Document</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='6%'></td>");  
			
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");'          onclick='re_generate_report(\"new\")' value=\"New\" ></td>");  // added by udara 19-05-2015
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Re-Generate\");'  onclick='re_generate_report(\"re_gen\")' value=\"Re-Generate\" disabled ></td>");  // added by udara 19-05-2015
			
			
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>");

			out.println("<table align='center' width='100%' class='table'>"); 
		
			// commented by udara 05-12-2018
			/*
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			//out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_FINANCE_NO)\" >"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='200' size='10' onblur=\"\" >"); 
			//out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance()\" onblur=\"help_button_finance()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance()\"  >"); 
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\"  Style=\"{width:150px;}\"  onClick=\"run_report()\"></td>"); //view

			out.println("</tr>"); 
			*/
			
			// added by udara 05-12-2018
			
			out.println("<tr>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			out.println("<td width='20%' > ");
			out.println(" <input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='200' size='10' onblur=\"\" > ");
		    out.println(" <input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance()\"  >"); 
			out.println("</td>"); 
			out.println("<td width='*%' >"); 
			out.println(" <input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\"  Style=\"{width:150px;}\"  onClick=\"run_report()\">"); 
			out.println("</td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Client Code </DIV></td>"); 
			out.println("<td width='20%' > "); 
			out.println("  <DIV id='DIV_TXT_CLIENT_CODE' name='DIV_TXT_CLIENT_CODE' > &nbsp; </DIV> <input type='hidden' name='TXT_CLIENT_CODE' > "); 
			out.println("</td>"); 
			out.println("<td width='*%' >"); 
			out.println("<input class='but_input' type='button' name='BUT_VIEW_GUARANTOR' value=\"View Guarantor Details\"  Style=\"{width:150px;}\"  onClick=\"run_report_guar()\">"); 
			out.println("</td>"); 
			out.println("</tr>"); 
			// end by udara 05-12-2018

	
			out.println("</table>"); 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			

			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}