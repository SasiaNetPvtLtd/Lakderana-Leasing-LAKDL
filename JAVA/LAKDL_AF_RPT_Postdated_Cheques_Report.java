
//--
//SCREEN NAME:POST DATED CHEQUES
//CREATED BY:CHANDANA
//DATE/TIME:30/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Postdated_Cheques_Report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Postdated_Cheques_Report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs2;
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
			out.println("<title>Post Dated Cheque Report-Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");
			
			
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			
			//====================Added by Dineth on 2008-12-30===================
				out.println("function help_button_location() {"); 
				out.println("    Crit = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_LOCATION_CODE_sql','3');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_location() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				 
				out.println("}");
			
			
			//===================End by Dineth on 2008-12-30======================
			
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_POD_NO_sql_report\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			//============================Added by Dineth on 2008-08-12==============================
			out.println("function help_cheque_no() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"39\";");
			out.println("    m_sql = \"m_help_TXT_CHK_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CHEQUE_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'39');"); 
			out.println("}");
			
			//============================End by Dineth on 2008-08-12================================
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];");
			out.println("}"); 
			//============================Added by Dineth on 2008-08-12==============================
			out.println("function help_value_assign_cheque() {");
			out.println("    document.Form1.TXT_CHEQUE_NO.value=oBj.valout[3];");
			out.println("}"); 
			
			
			//============================End by Dineth on 2008-08-12================================
			
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
			//===============================Added by Dineth on 2008-08-12
			out.println("		if(IfCount==\"39\"){"); 
			out.println("		help_value_assign_cheque();"); 
	  	out.println("		}"); 
      //===============================End by Dineth on 2008-08-12
			//===============================Added by Dineth on 2008-12-30
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_location();"); 
	  	out.println("		}"); 
			
			
			//==============================End by Dineth on 2008-12-30
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Postdated_Cheques_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Postdated_Cheques_Report?chksql=main_page1';"); 
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
			out.println("help_box.innerHTML=\"  Postdated Cheques Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Postdated Cheques Report \";"); 
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
			
			out.println("function validate_date(){");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");	
			
			
			out.println("function load_data_frame(){ ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Postdated_Cheques_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\" \");  ");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Postdated_Cheques_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&CHEQUE_STATUS=\"+document.Form1.DRP_CHEQUE_STATUS.value+\"&BRANCH_CODE=\"+document.Form1.TXT_LOCATION_CODE.value+\"&CHEQUE_NO=\"+document.Form1.TXT_CHEQUE_NO.value+\"&FROM_DATE=\"+m_from_date+\"&TO_DATE=\"+m_to_date+\" \";");
			//out.println(" alert(m_url);");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("  } ");
			out.println(" } ");
				
				
			out.println("</Script>");
			
			out.println("<body ONLOAD=\"get_system_date();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Postdated Cheques Report</td>");
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
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"4\" width=\"100%\" >");
			out.println("<tr >"); 
			out.println("<td width=\"15%\" ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" >");	
			out.println("</td> ");
			out.println("<td width=\"15%\" ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
			out.println(" <TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			
			out.println("</tr>");
			//out.println("</table>");
			//Added by Dineth on 2008-12-30
			out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch Code</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_LOCATION_CODE' value=\"Help\" onClick=\"help_button_location()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			//End by Dineth on 2008-12-30
			
			//out.println("<table align='center' width='100%' class='table' border='0'>"); 
  		out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_CHEUQE_STATUS'  class=div_input>Cheque Status</DIV></td>"); 
			out.println("<td width='20%' ><select class='txt_input' name='DRP_CHEQUE_STATUS'>"); 
			/*out.println("<option value='ALL'>All</option>");
			out.println("<option value='ENTERED'>Entered</option>");
			out.println("<option value='APPROVED'>Approved</option>");*/
			//out.println("<option value='ALL'>All</option>");
			out.println("<option value='ALL'>All</option>");
			out.println("<option value='INV'>Entered</option>");//Added by Dineth on 2008-12-16
			out.println("<option value='APP'>Approved</option>");
			out.println("<option value='HOL'>Hold</option>");
			out.println("<option value='REC'>Receipt Generated</option>");
			out.println("<option value='CAN'>Cancel</option>");//
			out.println("<option value='WIT'>Withdraw</option>");//Added BY Sandun on 05-03-2009
			
			out.println("</select>");
			out.println("</td>");
			
			//out.println("<td width='10%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			//out.println("</table>");
			
			
			//out.println("<BR>");
			//out.println("<table align='center' width='100%' class='table' border='0'>"); 
  		out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_PURCHASE_ORDER_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			//out.println("<td width='10%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			//out.println("</table>");
			
			//out.println("<table align='center' width='100%' class='table' border='0'>"); 
  		out.println("<tr>"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_CHEQUE_NO'  class=div_input>Cheque No</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_CHEQUE_NO' value=\"Help\" onClick=\"help_cheque_no()\" ></td>"); 
			out.println("<td width='10%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			//=================================Added by Dineth on 12-08-2008
			//out.println("<td width='20%' ><DIV id='DIV_TXT_CHEQUE_NO'  class=div_input>Cheque No </DIV></td>"); 
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CHEQUE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			//out.println("<input class='but_input' type='button' name='BUT_CHEQUE_NO' value=\"Help\" onClick=\"help_cheque_no()\" ></td>"); 
			//out.println("<td width='10%'><input class='but_input' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			//out.println("<td width='*%'></td>"); 
			//out.println("</tr>");
			//================================End by Dineth on 12-08-2008
			//out.println("</table>");
				
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 

			out.println("</html>");

					
			}else	if(m_screen_type.equals("MAIN")){ 
			
			String m_finance_no = req.getParameter("PURCH_ORD_NO");
			
			String m_cheque_status = req.getParameter("CHEQUE_STATUS");
			String m_cheque_no = req.getParameter("CHEQUE_NO");
			String m_from_date = req.getParameter("FROM_DATE");
			String m_to_date = req.getParameter("TO_DATE");
			String m_branch_code = req.getParameter("BRANCH_CODE");//Added by Dineth on 2008-12-30
			//m_chksql = req.getParameter(\"chksql\");
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Post dated Cheques</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

			
			

			/*out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>FULL NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>FINANCE NO</td>"); 
			out.println("</tr >"); */
			String sql_fin_no="";
			String sql_cheque_status="";
			String sql_cheque_no="";
			String sql_branch_code="";
			//Commented by Dineth on 2008-12-16
			/*if(m_cheque_status.equals("ENTERED")){
				sql_cheque_status=" AND STATUS='INV'";
			}
			else if(m_cheque_status.equals("APPROVED")){
				sql_cheque_status=" AND STATUS='APP'";
			}
			else if(m_cheque_status.equals("ALL")){
				sql_cheque_status=" AND STATUS IN('APP','INV')";
			}*/
			
			if(!m_cheque_no.equals("")){
				sql_cheque_no=" AND CHEQUE_NO='"+m_cheque_no+"'";
			}
			
			if(!m_finance_no.equals("")){
				sql_fin_no=" AND FINANCE_NO='"+m_finance_no+"'";
			}
			
			if(!m_branch_code.equals("")){//Added by Dineth on 2008-12-30
			  sql_branch_code=" AND "+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)='"+m_branch_code+"'";
			}
			/*
			if(m_finance_no.equals("")){
			
			pstmt = conn.prepareStatement("  SELECT  A.APPLICATION_NO, A.CLIENT_CODE, B.FULL_NAME, "+
			                              " A.FINANCE_NO, A.APPLICATION_STATUS "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
																		" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																		" WHERE APPLICATION_STATUS='ACTIVATED' AND "+
																		" A.CLIENT_CODE=B.CLIENT_CODE "); */
																		
			//Modified by Mahela on 26-07-2007															
			/*pstmt = conn.prepareStatement(" SELECT DISTINCT C.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME, A.FINANCE_NO "+
																		" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
																		" WHERE A.FINANCE_NO IS NOT NULL AND "+
																		" A.FINANCE_NO=C.FINANCE_NO AND "+
																		" A.CLIENT_CODE=B.CLIENT_CODE ");
																		
																		
																		
																		
																		
																		
			}else{
			
		/*	pstmt = conn.prepareStatement("  SELECT  A.APPLICATION_NO, A.CLIENT_CODE, B.FULL_NAME, "+
			                              " A.FINANCE_NO, A.APPLICATION_STATUS "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
																		" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																		" WHERE APPLICATION_STATUS='ACTIVATED' AND "+
																		" A.CLIENT_CODE=B.CLIENT_CODE AND "+
																		" A.FINANCE_NO ='"+m_finance_no+"' "); */
			
			
			//Modified by Mahela on 26-07-2007 -- app no
			/*pstmt = conn.prepareStatement(" SELECT DISTINCT C.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME, A.FINANCE_NO "+
																		" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
																		" WHERE A.FINANCE_NO IS NOT NULL AND "+
																		" A.CLIENT_CODE=B.CLIENT_CODE AND "+
																		" A.FINANCE_NO=C.FINANCE_NO AND "+
			                              " A.FINANCE_NO ='"+m_finance_no+"' ");
																		
			
			
			
			
			}*/
			

			//rs=pstmt.executeQuery(); 

			//boolean more=rs.next();
			/*while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} */
			String m_quotation;
			
			//while(more){
			//==================Commented and Added by Dineth on 06-08-2008
			//m_quotation=rs.getString(1);
			//m_quotation=rs.getString(4);
			
			/*
			out.println("<br>");*/
			
	/*
			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>APPLICATION NO</td><TD width='15%' align='left' align='right' STYLE='{text-align:left; cursor:hand;}' onclick=show_application_detail_drill('"+rs.getString(1)+"')>:<U> "+rs.getString(1)+"</U></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	
			
			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>FINANCE NO</td><TD width='15%' align='left'   align='right' STYLE='{text-align:left; cursor:hand;}' onclick=show_finance_detail_drill('"+rs.getString(4)+"')>:<U> "+rs.getString(4)+"</U></TD>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>CLIENT NAME</td><TD width='15%' align='left'   align='right' style=\"{text-align:left; cursor:hand; }\" onclick=show_client('"+rs.getString(2)+"') >: <U>"+rs.getString(3)+"</U></TD>"); 
			
			//out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=load_data('"+rs.getString(1)+"')>"+rs.getString(2)+"</TD>");
			
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
	
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
			*/

			
			
			/*pstmt = conn.prepareStatement("  SELECT "+
			" POD_REF_NO, "+
			" CHEQUE_NO, NVL(TO_CHAR(CHEQUE_DATE,'DD-MON-YYYY'),'-'), "+
			" PAYER_BRANCH_CODE, PAYER_ACC_NO, "+ //AF_CO_GET_BANK_NAME() Added by Chandana on 16/05/2007
			" CLIENT_CODE, "+
			" CURR_CODE, "+
			" CHEQUE_AMOUNT, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE) "+
			" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
			" WHERE FINANCE_NO='"+m_quotation+"' AND STATUS='INV' " ); //MODIFIED BY NUWAN DE SILVA 18-JUN-07
			*/
			
			if(m_cheque_status.equals("ALL")){
			pstmt = conn.prepareStatement("  SELECT "+
			" POD_REF_NO, "+
			" CHEQUE_NO, NVL(TO_CHAR(CHEQUE_DATE,'DD-MON-YYYY'),'-'), "+
			" PAYER_BRANCH_CODE, NVL(PAYER_ACC_NO,'-'), "+ //AF_CO_GET_BANK_NAME() Added by Chandana on 16/05/2007
			" CLIENT_CODE, "+
			" CURR_CODE, "+
			" CHEQUE_AMOUNT, "+
			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
			" "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER), "+
			" NVL(FINANCE_NO,'-'), "+
			" NVL(DECODE(STATUS,'INV','Entered','APP','Approved','HOL','Hold','REC','Receipt Generated','CAN','Cancel','WIT','Withdraw'),'-') "+
			" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
			" WHERE "+//AND STATUS IN ('REC','APP','ENT','INV') "+ MODIFIED BY NUWAN DE SILVA 18-JUN-07
			" TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+sql_cheque_no+sql_fin_no+sql_branch_code+" ");
			}
			else{
			pstmt = conn.prepareStatement("  SELECT "+
			" POD_REF_NO, "+
			" CHEQUE_NO, NVL(TO_CHAR(CHEQUE_DATE,'DD-MON-YYYY'),'-'), "+
			" PAYER_BRANCH_CODE, NVL(PAYER_ACC_NO,'-'), "+ //AF_CO_GET_BANK_NAME() Added by Chandana on 16/05/2007
			" CLIENT_CODE, "+
			" CURR_CODE, "+
			" CHEQUE_AMOUNT, "+
			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
			" "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER), "+
			" NVL(FINANCE_NO,'-'), "+
			" NVL(DECODE(STATUS,'INV','Entered','APP','Approved','HOL','Hold','REC','Receipt Generated','CAN','Cancel','WIT','Withdraw'),'-') "+
			" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
			" WHERE "+//AND STATUS IN ('REC','APP','ENT','INV') "+ MODIFIED BY NUWAN DE SILVA 18-JUN-07
			" TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND STATUS='"+m_cheque_status+"' "+sql_cheque_no+sql_fin_no+sql_branch_code+" ");
			}
			
			
			rs2=pstmt.executeQuery(); 

			boolean more2=rs2.next();
			int row = 0;
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			if(!more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Postdated Cheques Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			}
			if(more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Postdated Cheques Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='10%' class='txt_report_column'>FINANCE NO</td>");  
			out.println("<td width='10%' class='txt_report_column'>POD REF NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CHEQUE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CHEQUE DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PAYER BRANCH CODE</td>");
			out.println("<td width='10%' class='txt_report_column'>PAYER BRANCH NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PAYER ACC NO</td>"); 
			
			if(m_cheque_status.equals("ALL")){//Added BY SANDUN on 05-03-2009
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			}
			out.println("<td width='10%' class='txt_report_column'>CHEQUE AMOUNT</td>");
			out.println("<td width='10%' class='txt_report_column'>CURR CODE</td>");
			out.println("<td width='10%' class='txt_report_column'>ENT USER</td>");
			//out.println("<td width='10%' class='txt_report_column'>STATUS</td>");
			out.println("</tr >");
			
			while(more2){
      if(row>0 && row%2==1){
			out.println("<tr class = 'tr_input' bgcolor=silver >");
			}else{
			out.println("<tr class = 'tr_input' >");
			}
			out.println("<TD  align='center'>"+rs2.getString(11)+"</TD>");
			out.println("<TD  align='center' style=cursor:hand;cursor-color:blue onclick=show_pod_cheque_drill('"+rs2.getString(1)+"') ><u> "+rs2.getString(1)+"</u></TD>");
			out.println("<TD  align='center'>"+rs2.getString(2)+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(3)+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(4)+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(9)+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(5)+"</TD>");
			
			if(m_cheque_status.equals("ALL")){//Added BY SANDUN on 05-03-2009
			out.println("<TD  align='center'>"+rs2.getString(12)+"</TD>");
			}
			
			out.println("<TD  align='right'>"+nf.format(rs2.getDouble(8))+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(7)+"</TD>");
			out.println("<TD  align='center'>"+rs2.getString(10)+"</TD>");
			//out.println("<TD  align='center'>"+rs2.getString(12)+"</TD>");
			out.println("</tr >"); 
			
			
			
			
			
			
			
			
			

			more2=rs2.next(); 
			 row++;
			/*if(!more2){
			break;
			}*/
	
			}	
			
			/*
			if(more){
			m_quotation=rs.getString(1);
			}
      more=rs.next();*/
			
			out.println("</table>"); 
			//out.println("<BR >"); 
			 }

		//}
			
			
			
			
			
			
			
			
			

			//out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs2.close();
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


