
//--
//SCREEN NAME:TEMPRERY RECIEPT DETAILS
//CREATED BY:CHANDANA
//DATE/TIME:29/10/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Temprery_Reciept_Details?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RPT_Temprery_Reciept_Daily_Collection extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			
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
			
			
			if(m_screen_type.trim().equals("main_page1")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			//out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			//out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			//out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_TEMP_REC_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_RECIEPT_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_RECIEPT_NO.value=oBj.valout[2];");
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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

			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	}	"); //
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Daily_Collection?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Daily_Collection?chksql=main_page1';"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_application_process_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Daily Collection - Temporary Reciept Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Daily Collection - Temporary Reciept Report \";"); 
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
			
			out.println("function load_data_frame(){ ");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			//out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			//out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			//out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			//out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Details?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_RECIEPT_NO.value+\" \");  ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Daily_Collection?chksql=MAIN&date_from=\"+m_from_date+\"&sort_column=A.TEMP_REC_NO&order_by_type=DESC \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
				
				
			out.println("</Script>");
			
			out.println("<body onload=\"get_system_date();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>"); 
			out.println("<input type=hidden name=\"SCREEN_NAME\" value=\"\"></td>");
			out.println("<input type=hidden name=\"hid_status\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Daily Collection - Temporary Reciept Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
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
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>Enter Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" >");	
			out.println("</td> ");
			out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");

			/*
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
			out.println(" <TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
			out.println("</td> "); */
			//out.println("<td width='*%' ></td>");
			//out.println("</table>");  
			/*
			out.println("<table align='center' width='100%' class='table'>"); 
  		out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_RECIEPT_NO'  class=div_input> Reciept No </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_RECIEPT_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_RECIEPT_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
				*/
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){
			
			//String m_reciept_no = req.getParameter("PURCH_ORD_NO");
      String m_date_from=req.getParameter("date_from").trim();
			//String m_date_to=req.getParameter("date_to").trim();
			String m_sort_column = req.getParameter("sort_column");
			String m_order_by_type = req.getParameter("order_by_type");
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Daily Collection - Temporary Reciept Report</TITLE>"); 
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
     // out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Details?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_RECIEPT_NO.value+\"&date_from=\"+m_from_date+\"&date_to=\"+m_to_date+\"&sort_column=A.TEMP_REC_NO&order_by_type=DESC \";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Temprery_Reciept_Daily_Collection?chksql=MAIN&date_from="+m_date_from+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			out.println(" window.location.href=m_url;");			
			out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

			
			
			
			pstmt = conn.prepareStatement("SELECT  A.TEMP_REC_NO,A.FINANCE_NO,A.CLIENT_CODE, "+
			                              " B.FULL_NAME, TO_CHAR(A.TRN_DATE,'DD-MON-YYYY') TRN_DATE, "+
																		" NVL(A.AMOUNT,0), DECODE(A.SETTELMENT_MODE,'CASH','Cash','CHEQUE','Cheque'), NVL(A.BANK_CODE,'-'), NVL(A.BRANCH_CODE,'-'), "+
																		" NVL(A.ACCOUNT_NO,'-'), NVL(A.CURR_CODE,'-'), NVL(A.EXCHANGE_RATE,0), NVL(A.TRN_AMOUNT_CURR,0), "+
																		" NVL(A.COLLECTION_OFFICER,'-'), NVL(A.RECEIPT_NO,'-'), NVL(A.REC_BOOK_NO,'-'), NVL(A.CHEQUE_NO,'-') CHEQUE_NO, "+
																		" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-') "+		//Added by Chandana on 21/05/2007 For Ref No.45																
																		" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A, (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME, "+
																		" V.TEL_NO,V.NIC_NO FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT X, "+
																		" "+m_schema_name+".AF_CO_MAS_CLIENT V WHERE V.CLIENT_CODE=X.CLIENT_CODE) B "+
																		" WHERE A.FINANCE_NO=B.FINANCE_NO "+
																		" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') = TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
																		//" TO_DATE(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
																		" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
												

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			out.println("<DIV STYLE='{position:absolute; top:40; left:0 cursor: hand;}'>");
			out.println("<br>");
			out.println("<table align='center' width='1425' class='table' border=1>"); 
			while(more){
						out.println("<tr >"); 
						out.println("<TD width='130' class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD width='130' class='txt_report_data' align='right'  style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></TD>");
						out.println("<TD width='130' class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(3)+"')><u>"+rs.getString(4)+"</u></TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(18)+"</TD>");//Added by Chandana on 21/05/2007 For Ref No.45
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(12)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(13)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(14)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(15)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(17)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>"); 
			out.println("</DIV>");
					
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   		out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1425; height: 15'>");
			
			out.println("<table align='center' width='1425' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='100' ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>"); 
			out.println("<td width='100' ></td>");
			out.println("</tr >");
			out.println("</table>"); 
			
			out.println("<table align='center' width='1425' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='150' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.TEMP_REC_NO') >TEMP REC NO</td>"); 
			out.println("<td width='150' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.FINANCE_NO')>FINANCE NO</td>"); 
			out.println("<td width='160' class='txt_report_column' style= cursor:hand; onclick=sort_data('B.FULL_NAME')>FULL NAME</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.TRN_DATE')>TRN DATE</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.AMOUNT')>AMOUNT</td>"); 
			out.println("<td width='100' class='txt_report_column'>SETTELMENT MODE</td>"); 
			out.println("<td width='100' class='txt_report_column'>BANK CODE</td>"); 
			out.println("<td width='100' class='txt_report_column'>BRANCH CODE</td>"); 
			out.println("<td width='100' class='txt_report_column'>BRANCH NAME</td>");  //Added by Chandana on 21/05/2007 For Ref No.45
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.ACCOUNT_NO')>ACCOUNT NO</td>"); 
			out.println("<td width='100' class='txt_report_column'>EXCHANGE RATE</td>"); 
			out.println("<td width='100' class='txt_report_column'>TRN AMOUNT CURR</td>"); 
			out.println("<td width='100' class='txt_report_column'>COLLECTION OFFICER</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.RECEIPT_NO') >RECEIPT NO</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.CHEQUE_NO')>CHEQUE NO</td>"); 
			out.println("</tr >");
			out.println("</table>");  
			out.println("</div>"); 
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
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


