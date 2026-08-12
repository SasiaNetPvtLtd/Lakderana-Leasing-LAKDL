//SCREEN NAME	:Security_and_Marketting_file Report
//CREATED BY	:DELANJALI
//DATE/TIME		:24-09-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Security_and_Marketting_file_1 extends javax.servlet.http.HttpServlet { 

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
			//stmt2 = conn.createStatement();
			
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
			out.println("			if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\" ){");
			out.println("help_update();");
			out.println("			}");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_no&data_val=\"+obj.value+\"&ac_status=ACTIVATED\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			

			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"FinanceSql_finance\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("    document.Form1.hid_app_no.value=oBj.valout[3];");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_1?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_1?chksql=main_page1';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_payment_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Security Documents Issued and Pending - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Security Documents Issued and Pending\";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
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
			out.println("  if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("    document.Form1.hid_app_no.value=\"\";");
			out.println("    document.Form1.hid_client.value=\"\";");
			out.println("    document.Form1.hid_client_name.value=\"\";");
			out.println("}");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_1?chksql=SEC_REPORT&CLIENT_CODE=\"+document.Form1.hid_client.value+\"&CLIENT_NAME=\"+document.Form1.hid_client_name.value+\"&APP_NO=\"+document.Form1.hid_app_no.value+\"&FIN_NO=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
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
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\"\">");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Security Documents Issued and Pending</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='20' onblur=\"makeRequest(document.Form1.TXT_FINANCE_NO)\" style='{width:230px}'>"); 
			out.println("<input class='but_input' type='button' name='BUT_FIN_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view_report()\"></td>"); 
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
			String m_req_no="";
		

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Security Documents Issued and Pending</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
				
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_1?chksql=SEC_REPORT&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr>");
			out.println("<td align=\"right\"  style=\"height: 18px\" ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" style=\"height: 18px\" ></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Security Documents Issued and Pending</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");


		
			rs1 = stmt1.executeQuery  ("SELECT "+
		   " DISTINCT A.APPLICATION_NO, "+
			 " A.CLIENT_CODE, "+
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
			 " A.FINANCE_NO,REQ_NO "+				
			 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT B,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL C "+
			 " WHERE UPPER(A.FINANCE_NO) LIKE UPPER('"+m_fin_no+"%') "+
			 " AND B.FINANCE_NO=C.FINANCE_NO "+
			 " AND C.DOCUMENT_STATUS='REQ-APP' "+	
			 " AND A.FINANCE_NO=B.FINANCE_NO "+
			 " ORDER BY A.FINANCE_NO ASC ");

				
			out.println("<br>");
			
			boolean more1=rs1.next();

			out.println("<table align='center' width='100%' class='table' border=0>"); 

			while(more1){
			out.println("<HR>"); 			

			m_fin_no=rs1.getString(4);
			m_app_no =rs1.getString(1);
			m_client =rs1.getString(2);
			m_name =rs1.getString(3);
			m_req_no=rs1.getString(5);
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
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>REQUEST NO</td><TD width='25%' align='left' class='txt_report_data' >:<B> "+rs1.getString(5)+"</TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	

	
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("</table >"); 
			//-----ASSET DOCUMENTS---------------------------------------------------------------------------------------------------------------------------------
		
		rs2 = stmt.executeQuery 
		(	" SELECT "+
			" REQ_NO, "+
			" NVL(PRO_FORMA_INVOICE_NO,'-') INVOICE_NO, "+
			" NVL(DOCUMENT_CODE,'-'), "+
			" NVL(DOCUMENT_STATUS,'-'), "+
			" NVL(REASON,'-') REASON, "+
			" NVL(REQUESTED_USER,'-') REQ_USER, "+
			" TO_CHAR(REQUESTED_DATE,'DD-MON-YYYY') REQ_DATE, "+
			" NVL(APPRO_USER,'-') APP_USER, "+
			" NVL(TO_CHAR(APPRO_DATE,'DD-MON-YYYY'),'-') APP_DATE, "+ 
			" "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_CODE) DOC_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL "+
			" WHERE FINANCE_NO='"+m_fin_no+"' "+
			" AND DOCUMENT_STATUS='REQ-APP' "+
			" AND REQ_NO='"+m_req_no+"' "+
			" AND PRO_FORMA_INVOICE_NO IS NOT NULL "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

			
			
			
			
	int j=0;
			
			boolean more2=rs2.next();
			out.println("<table align='center' width='100%' class='table' border=0>"); 

			while(more2){
			
			if(j==0){
			out.println("<tr>");
			out.println("<td width='30%' class=tr_input align=left><I><B>ASSET DOCUMENTS</td>");
			out.println("</tr >");
			
			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='30%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Name  ' onclick=sort_data('DOC_NAME') >DOCUMENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Proforma Invoice  ' onclick=sort_data('INVOICE_NO') >PRO FORMA INVOICE NO</td>"); 
			out.println("<td width='15%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Reason  ' onclick=sort_data('REASON') >REASON</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Requested User  ' onclick=sort_data('REQ_USER') >REQUESTED USER</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Requested Date  ' onclick=sort_data('REQ_DATE') >REQUESTED DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approved User  ' onclick=sort_data('APP_USER') >APPRO USER</td>"); 
			out.println("<td width='5%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approved Date  ' onclick=sort_data('APP_DATE') >APPRO DATE</td>");			
			out.println("</tr >");


			}
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			
			out.println("<tr >"); 
			out.println("<TD class='txt_report_data' align='left' STYLE='{cursor:hand;}' onclick=show_document_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(10)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{cursor:hand;}' onclick=show_proforma_invoice_drill('"+rs2.getString(2)+"') ><u>"+rs2.getString(2)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(5)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(8)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(9)+"</TD>");
			out.println("</tr >"); 

		 

			out.println("<tr >"); 
			out.println("</tr >"); 

			more2=rs2.next(); 
			j=j+1;
			
			if(!more2){
			break;
			}
	
			}	
			
		
			out.println("</table>"); 
		
			rs2.close();
			stmt.close();
			stmt = conn.createStatement();

			//--CLIENT DOCUMENTS------------------------------------------------------------------------------------------------------------------------------------
			//--------------------------------------------------------------------------------------------------------------------------------------
			rs2 = stmt.executeQuery 
		(	" SELECT "+
			" REQ_NO, "+
			" NVL(PRO_FORMA_INVOICE_NO,'-') INVOICE_NO, "+
			" NVL(DOCUMENT_CODE,'-'), "+
			" NVL(DOCUMENT_STATUS,'-'), "+
			" NVL(REASON,'-') REASON, "+
			" NVL(REQUESTED_USER,'-') REQ_USER, "+
			" TO_CHAR(REQUESTED_DATE,'DD-MON-YYYY') REQ_DATE, "+
			" NVL(APPRO_USER,'-') APP_USER, "+
			" NVL(TO_CHAR(APPRO_DATE,'DD-MON-YYYY'),'-') APP_DATE, "+ 
			" "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_CODE) DOC_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL "+
			" WHERE FINANCE_NO='"+m_fin_no+"' "+
			" AND DOCUMENT_STATUS='REQ-APP' "+
			" AND REQ_NO='"+m_req_no+"' "+
		  " AND PRO_FORMA_INVOICE_NO IS NULL "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");


			int d=0;
			
			more2=rs2.next();
			out.println("<table align='center' width='100%' class='table' border=0>"); 

			while(more2){
			if(d==0){
			out.println("<tr>");
			out.println("<td width='30%' class=tr_input align=left><I><B>CLIENT DOCUMENTS</td>");
			out.println("</tr >");
			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='30%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Name  ' onclick=sort_data('DOC_NAME') >DOCUMENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Proforma Invoice  ' onclick=sort_data('INVOICE_NO') >PRO FORMA INVOICE NO</td>"); 
			out.println("<td width='15%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Reason  ' onclick=sort_data('REASON') >REASON</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Requested User  ' onclick=sort_data('REQ_USER') >REQUESTED USER</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Requested Date  ' onclick=sort_data('REQ_DATE') >REQUESTED DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approved User  ' onclick=sort_data('APP_USER') >APPRO USER</td>"); 
			out.println("<td width='5%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approved Date  ' onclick=sort_data('APP_DATE') >APPRO DATE</td>");			
			out.println("</tr >");
			}
				
			if(d>0 && d%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<tr >"); 
			out.println("<TD class='txt_report_data' align='left' STYLE='{cursor:hand;}' onclick=show_document_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(10)+"</u></TD>");
		  out.println("<TD class='txt_report_data' align='left' >-</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(5)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(8)+"</TD>");
			out.println("<TD class='txt_report_data' align='left'>"+rs2.getString(9)+"</TD>");
			out.println("</tr >"); 


			out.println("<tr >"); 
			out.println("</tr >"); 

			more2=rs2.next(); 
			d=d+1;
			
			if(!more2){
			break;
			}
	
			}	
			
			//--------------------------------------------------------------------------------------------------------------------------------------


			out.println("</table>"); 

			//--------------------------------------------------------------------------------------------------------------------------------------

			if(more1){
			m_app_no=rs1.getString(1);
			}
      more1=rs1.next();
			rs2.close();
		//	stmt1.close();

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
		//	rs2.close();
			stmt.close();
			//stmt1.close();
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


