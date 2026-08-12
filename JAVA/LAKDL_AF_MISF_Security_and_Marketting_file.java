//SCREEN NAME	:Security_and_Marketting_file Report
//CREATED BY	:DELANJALI
//DATE/TIME		:24-09-2007
//NOTES				:
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Security_and_Marketting_file extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
	  ServletOutputStream out = null;
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");
					   
		  String m_sort_column   = "DOC_NAME";	
			String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
          m_sort_column = req.getParameter("sort_column");
          m_order_by_type = req.getParameter("order_by_type");
				}
			if(m_screen_type.trim().equals("main_page1")){	
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" ){");
			out.println("help_update();");
			out.println("			}");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_application&data_val=\"+obj.value+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"FinanceSql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ACTIVATED@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];");
			out.println("    document.Form1.hid_fin_no.value=oBj.valout[3];");
			out.println("    document.Form1.hid_client.value=oBj.valout[4];");
			out.println("    document.Form1.hid_client_name.value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function help_update_finance() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			out.println("    m_sql = \"FinanceSql\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ACTIVATED@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
			out.println("}");
			
			
			out.println("function help_value_assign_3() {");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];");
			out.println("    document.Form1.hid_fin_no.value=oBj.valout[3];");
			out.println("    document.Form1.hid_client.value=oBj.valout[4];");
			out.println("    document.Form1.hid_client_name.value=oBj.valout[5];");
			out.println("}"); 
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
      out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("	}");
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file?chksql=main_page1';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_doc_requied_for_appro_app_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Documents Required for Approved Applications - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Documents Required for Approved Applications\";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
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
			out.println("}"); 
			out.println("}");
			
			out.println("function view_report(){ ");
			out.println("  if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
			out.println("    document.Form1.hid_fin_no.value=\"\";");
			out.println("    document.Form1.hid_client.value=\"\";");
			out.println("    document.Form1.hid_client_name.value=\"\";");
			out.println("}");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file?chksql=SEC_REPORT&CLIENT_CODE=\"+document.Form1.hid_client.value+\"&CLIENT_NAME=\"+document.Form1.hid_client_name.value+\"&FIN_NO=\"+document.Form1.hid_fin_no.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
			
				
			out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_name' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_fin_no' VALUE=\"\">");
			out.println("<input  type='hidden' value='REPORT' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Report\">"); 
			
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");
			
			out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
			out.println("<td class=\"border_wht\" valign=\"top\"> ");
			out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
			out.println("<tr> ");
			out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
			out.println("</tr>");
			out.println("<tr> ");
			out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style=\"height: 327px\">");
						
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Documents Required for Approved Applications</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='window.close()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='20' size='20' onblur=\"makeRequest(document.Form1.TXT_APPLICATION_NO)\" style='{width:150px}'>"); 
			out.println("<input class='but_input' type='button' name='BUT_APP_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view_report()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='20' size='20' onblur=\"\" style='{width:300px}' disabled>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("SEC_REPORT")){
			
			String m_app_no = req.getParameter("APP_NO");
			String m_fin_no = req.getParameter("FIN_NO");
			String m_client = req.getParameter("CLIENT_CODE");
			String m_name = req.getParameter("CLIENT_NAME");
			

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Documents Required for Approved Applications which have not being received</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
				
			out.println("var b_flag=0;");
			out.println("var chk_chng=0;");
			
			
		  out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
		  out.println("       m_order_by_type = 'DESC'; ");
		  out.println("    }");
		  out.println("  }else{");
		  out.println("    m_order_by_type = 'ASC'; ");
		  out.println("  }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file?chksql=SEC_REPORT&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("function count_select_app(){ ");
			
			out.println("for(i=1;i<parseInt(document.Form1.aset_hid_count.value);i++){");		  
			out.println("if(document.Form1.elements[\"STATUS_CHKBX_\"+i].checked==true){");
			out.println("b_flag=1;");	
			out.println("break;");	
			out.println("}else{");
			out.println("b_flag=2;}");
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_change(){"); 
			out.println("for(i=1;i<parseInt(document.Form1.as_hid_count.value);i++){");		
			out.println("  chk=\"AS_STATUS_CHKBX_\"+i;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"off\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"on\";"); 
			out.println("chk_chng=2");
			out.println("}");
			out.println("}");
			out.println("for(i=1;i<parseInt(document.Form1.cl_hid_count.value);i++){");		
			out.println("  chk=\"CL_STATUS_CHKBX_\"+i;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"off\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"on\";"); 
			out.println("chk_chng=2");
			out.println("}");
			out.println("}");
			out.println("}"); 
			
			out.println("function save_window(){ ");
			//out.println("check_change()");
			//out.println("if(chk_chng==2){");
			out.println("if(confirm(\"Are you sure you want to save ? \")){ "); 
			out.println("num1 = document.Form1.as_hid_count.value;");
			out.println("num2 = document.Form1.cl_hid_count.value;");
			out.println("document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Security_Marketting?as_number='+num1+'&cl_number='+num2+'';");  
			//out.println("alert(document.Form1.cl_hid_count.value);");
			out.println("document.Form1.submit();");
			//out.println("}");
			out.println("}");
			out.println("}");
   	  
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("for(i=1;i<parseInt(document.Form1.as_hid_count.value);i++){");		
			out.println("  chk=\"AS_STATUS_CHKBX_\"+i;");
			out.println("document.Form1.elements[chk].checked=false;"); 
			out.println("}");
			out.println("for(i=1;i<parseInt(document.Form1.cl_hid_count.value);i++){");		
			out.println("  chk=\"CL_STATUS_CHKBX_\"+i;");
			out.println("document.Form1.elements[chk].checked=false;"); 
			out.println("}");
			out.println("}"); 
			out.println("}"); 
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>");
			out.println("<input type='hidden' name='hid_chk_count' value='' >");
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");			
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onclick='window.close()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");
			
			out.println("<table class='table' align=\"center\" width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr>");
			out.println("<td align=\"center\" style=\"height: 18px\" ></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Documents Required for Approved Applications which have not being received</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");			
	
			rs1 = stmt1.executeQuery 
			("SELECT "+
		   " DISTINCT A.APPLICATION_NO, "+//1
			 " B.CLIENT_CODE, "+//2
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
			 " B.FINANCE_NO "+//4				
			 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			 " WHERE UPPER(A.APPLICATION_NO) LIKE UPPER('"+m_app_no+"%') "+
			 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
			 " AND B.APPLICATION_STATUS='ACTIVATED' "+
			 " AND A.STATUS='N' "+
			 " ORDER BY APPLICATION_NO ASC ");
				
				
			out.println("<br>");
			
			boolean more1=rs1.next();

			out.println("<table align='center' width='100%' class='table' border=0>"); 

			while(more1){
			out.println("<HR>"); 			

			m_app_no=rs1.getString(1);
 			m_fin_no =rs1.getString(4);
 			m_client =rs1.getString(2);
 			m_name =rs1.getString(3);
			out.println("<table align='center' width='100%' class='table' border=0>"); 

			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>APPLICATION NO</td><TD width='25%' align='left'  class='txt_report_data' STYLE='{text-align:left; cursor:hand;}' onclick=show_application_detail_drill('"+m_app_no+"')>:<U><B> "+m_app_no+" </U></TD>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 

			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>FINANCE NO</td><TD width='25%' align='left' class='txt_report_data' STYLE='{text-align:left; cursor:hand;}' onclick=show_finance_detail_drill('"+m_fin_no+"') >:<U><B> "+m_fin_no+"</U></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	
			
			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>CLIENT CODE</td><TD width='25%' align='left' class='txt_report_data' style='{text-align:left; cursor:hand;}' onclick=\"show_client('"+m_client+"')\">:<U><B> "+m_client+"</U></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	

			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>CLIENT NAME</td><TD width='25%' align='left' class='txt_report_data' style='{text-align:left; cursor:hand;}' onclick=\"show_client('"+m_client+"')\">:<U><B> "+m_name+"</U></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	

	
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("</table >"); 
			
	
			out.println("<input type=hidden name='hid_app_no' value="+m_app_no+">");
			//-----ASSET DOCUMENTS---------------------------------------------------------------------------------------------------------------------------------
			rs2 = stmt.executeQuery 
			("SELECT "+
		   " DISTINCT A.APPLICATION_NO APPLICATION_NO, "+//1
		   " A.DOCUMENT_TYPE, "+//2
			 " "+m_schema_name+".AF_CO_GET_DOC_DESC(A.DOCUMENT_TYPE) DOC_NAME, "+//3
			 " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+//4
			 " NVL(A.REF_NO,'-') FOLL_NO, "+ //5
			 " NVL(A.PRO_INVOICE_NO,'-') INVOICE_NO, "+//6
			 " B.CLIENT_CODE, "+//7
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//8
			 " B.FINANCE_NO ,"+//9
			 " A.ENT_USER ,"+ //10
			 " DECODE(A.STATUS,'Y','Yes','N','No'), "+//11
			 " NVL(A.FOLLOWUP_REMARKS,'-') "+ //12
			 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			 " WHERE UPPER(A.APPLICATION_NO) LIKE UPPER('"+rs1.getString(1)+"%') "+
			 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
			 " AND B.APPLICATION_STATUS='ACTIVATED' "+
			 " AND A.PRO_INVOICE_NO IS NOT NULL "+
			 " AND A.STATUS='N' "+
			 " ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
	     int j=0;
			
			boolean more2=rs2.next();
			out.println("<table align='center' width='100%' class='table' border=0>"); 
       
			while(more2){
			
			if(j==0){
			out.println("<tr>");
			out.println("<td width='10%' class=tr_input align=left><I><B>ASSET DOCUMENTS</td>");
			out.println("</tr >");
			out.println("<tr class='pdn_txtpos2'>");

      out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Follow up No  ' onclick=sort_data('FOLL_NO') >FOLLOW-UP NO</td>"); 
			out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Name  ' onclick=sort_data('DOC_NAME') >DOCUMENT NAME</td>"); 
			//out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Proforma Invoice No  ' onclick=sort_data('INVOICE_NO') >PRO FORMA INVOICE NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand;  >STATUS</td>");
			out.println("<td width='10%' class='txt_report_column'   >REMARK</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand;  >ENTER USER</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Enter Date  ' onclick=sort_data('ENT_DATE') >ENTER DATE</td>"); 
			out.println("<td width='5%' class='txt_report_column'   >UPDATE</td>"); 
			out.println("</tr >");
			}
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
			if(!rs2.getString(5).equals("-")){
			out.println("<TD class='txt_report_data' align='left' >"+rs2.getString(5)+"</TD>");
			//STYLE='{text-align:left; cursor:hand;}' onclick=show_followup_category_drill('"+rs2.getString(5)+"')
			}
			else{
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(5)+"</TD>");
			}

			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left; cursor:hand;}' onclick=show_document_drill('"+rs2.getString(2)+"')><u>"+rs2.getString(3)+"</u></TD>");
			/*
			if(!rs2.getString(6).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left; cursor:hand;}' onclick=show_proforma_invoice_drill('"+rs2.getString(6)+"') ><u>"+rs2.getString(6)+"</u></TD>");
			}
			
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(6)+"</TD>");
			}
		*/
		 if(rs2.getString(5).equals("-")){
		  out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(11)+"</TD>");
		  }
			else{
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>Follow Up</TD>");
			}
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(12)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(10)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='center'  STYLE='{text-align:center;}'><input  type='checkbox' name=AS_STATUS_CHKBX_"+j+" onclick=\"check_change()\"></TD>");
			out.println("</tr >"); 

			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<input type=hidden name=as_follo_up_"+j+" value="+rs2.getString(5)+">");
			out.println("<input type=hidden name=as_doc_type_"+j+" value="+rs2.getString(2)+">");
      out.println("<input type=hidden name=as_hid_inv_no_"+j+" value="+rs2.getString(6)+">");
			more2=rs2.next(); 
			j=j+1;
						
			
			if(!more2){
			break;
			}
	    
			}	
			out.println("<input type=hidden name=as_hid_count value="+j+">");	
		
			out.println("</table>"); 
		
			rs2.close();
			stmt.close();
			stmt = conn.createStatement();

			//--CLIENT DOCUMENTS------------------------------------------------------------------------------------------------------------------------------------
			//--------------------------------------------------------------------------------------------------------------------------------------
			rs2 = stmt.executeQuery 
			("SELECT "+
		   " DISTINCT A.APPLICATION_NO, "+//1
		   " A.DOCUMENT_TYPE, "+//2
			 " "+m_schema_name+".AF_CO_GET_DOC_DESC(A.DOCUMENT_TYPE) DOC_NAME, "+//3
			 " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+//4
			 " NVL(A.REF_NO,'-') FOLL_NO, "+ //5
			 " NVL(A.PRO_INVOICE_NO,'-') INVOICE_NO, "+//6
			 " B.CLIENT_CODE, "+//7
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//8
			 " B.FINANCE_NO, "+//9
			 " A.ENT_USER ,"+//10
			 " DECODE(A.STATUS,'Y','Yes','N','No'), "+//11
			 " NVL(A.FOLLOWUP_REMARKS,'-') "+ //12
			 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			 " WHERE UPPER(A.APPLICATION_NO) LIKE UPPER('"+rs1.getString(1)+"%') "+
			 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
			 " AND B.APPLICATION_STATUS='ACTIVATED' "+
			 " AND A.PRO_INVOICE_NO IS NULL "+
			 " AND A.STATUS='N' "+
			 " ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

			int d=0;
			
			more2=rs2.next();
			out.println("<table align='center' width='100%' class='table' border=0>"); 
     
			while(more2){
			if(d==0){
			out.println("<tr>");
			out.println("<td width='10%' class=tr_input align=left><I><B>CLIENT DOCUMENTS</td>");
			out.println("</tr >");
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Follow up No  ' onclick=sort_data('FOLL_NO') >FOLLOW-UP NO</td>"); 
			out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Name  ' onclick=sort_data('DOC_NAME') >DOCUMENT NAME</td>"); 
			//out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Proforma Invoice No  ' onclick=sort_data('INVOICE_NO') >PRO FORMA INVOICE NO</td>");
			out.println("<td width='10%' class='txt_report_column'   >STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'   >REMARK</td>");
			out.println("<td width='10%' class='txt_report_column'   >ENTER USER</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Enter Date  ' onclick=sort_data('ENT_DATE') >ENTER DATE</td>"); 
			out.println("<td width='5%' class='txt_report_column'   >UPDATE</td>"); 
			out.println("</tr >");
			}
				
			if(d>0 && d%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
      if(!rs2.getString(5).equals("-")){
			out.println("<TD class='txt_report_data' align='left' >"+rs2.getString(5)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(5)+"</TD>");
			}
			
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left; cursor:hand;}' onclick=show_document_drill('"+rs2.getString(2)+"')><u>"+rs2.getString(3)+"</u></TD>");
			
			/*
			if(!rs2.getString(6).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left; cursor:hand;}' onclick=show_proforma_invoice_drill('"+rs2.getString(6)+"') ><u>"+rs2.getString(6)+"</u></TD>");
			}
			
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(6)+"</TD>");
			}
			*/			
		 if(rs2.getString(5).equals("-")){
		  out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(11)+"</TD>");
		  }
			else{
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>Follow Up</TD>");
			}
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(12)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(10)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'  STYLE='{text-align:left;}'>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='center'  STYLE='{text-align:center;}'><input  type='checkbox' name=CL_STATUS_CHKBX_"+d+" onclick=\"check_change()\" ></TD>");
			out.println("</tr >"); 

			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<input type=hidden name=cl_follo_up_"+d+" value="+rs2.getString(5)+">");
      out.println("<input type=hidden name=cl_hid_inv_no_"+d+" value="+rs2.getString(6)+">");
			out.println("<input type=hidden name=cl_doc_type_"+d+" value="+rs2.getString(2)+">");
			more2=rs2.next(); 
			d=d+1;
			
			
			
			if(!more2){
			break;
			}
	    
			}	
			
		out.println("<input type=hidden name=cl_hid_count value="+d+">");
			out.println("</table>"); 
			//out.println("<HR>"); 




			//--------------------------------------------------------------------------------------------------------------------------------------

			if(more1){
			m_app_no=rs1.getString(1);
			}
      more1=rs1.next();

			}

			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			rs1.close();
			rs2.close();
			stmt.close();
			stmt1.close();
			conn.close();
			out.flush();
			out.close();
			}


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


