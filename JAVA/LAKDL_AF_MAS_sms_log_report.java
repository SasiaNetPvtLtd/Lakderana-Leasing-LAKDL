// DEVELOP BY : Jithendra 05.05.2016
// DATE:05.05.2016

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_sms_log_report extends javax.servlet.http.HttpServlet { 
	
	
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn = null;
		Statement stmt =null;
		Statement stmt1 =null;
		Statement stmt3=null;
		CallableStatement callstmt1 =null;
		
		java.text.NumberFormat nf= null;
		ResultSet rs1 = null,rs3 = null;
		ResultSet rs = null;
		String m_chksql;
		
		try { 
			
			//******************************************************************************** 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			//**********************************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			
			
			//End by Dineth on 28-04-2009
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			
			Statement statement = null;
			
			if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>SMS Log Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				//To validate from date & to date
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
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println("	return true;"); 
				out.println("}"); 			
				
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Overall System References - SMS Log Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Overall System References - SMS Log Report \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_sms_log_report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				
				out.println("function print_report2(){");
				//out.println("		clearTimeout(timerID);");
				//out.println("		m_table.innerHTML=\"\";");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_sms_type = document.Form1.TXT_SMS_TYPE.value ");
				out.println(" 	m_sms_status = document.Form1.TXT_SMS_STATUS.value ");
				//out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				
				//out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=view_report_det&location=\"+m_location+\"&date=\"+m_date+\"&from_date=\"+m_from_date;");	 // mod by udara on 18-06-2013
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sms_log_report?chksql=view_report_det&to_date=\"+m_to_date+\"&from_date=\"+m_from_date+\"&sms_type=\"+m_sms_type+\"&sms_status=\"+m_sms_status;"); 
				
				out.println("			window.open(m_url);");
				//out.println("	}");
				out.println("}");
				
				
				
				out.println("</SCRIPT>");
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Overall System References - SMS Log Report </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table class='table' width='100%'  >");
				
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date *</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onblur=\"validate_date()\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"validate_date()\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("</tr>");
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date *</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" onblur=\"validate_date()\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"validate_date()\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");
				out.println("</td> ");
				out.println("</tr>");
				
				out.println("<tr >"); 
				
				
				out.println("<td width='20%' ><DIV id='DIV_TXT_SMS_TYPE'  class=div_input>SMS Type </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_SMS_TYPE'>");  
				out.println("      <OPTION value='ALL' >All</OPTION>");
				out.println("      <OPTION value='REC' >Receipts</OPTION>");
				out.println("      <OPTION value='RENT' >Rental Reminders</OPTION>");
				out.println("      <OPTION value='BDAY' >Birthday Wishes</OPTION>");
				out.println("      <OPTION value='ACTIVATION' >Contract Activation</OPTION>");
				out.println(" 	   </select>");
				out.println("</td> ");
				out.println("</tr>");
				
				
				out.println("<td width='20%' ><DIV id='DIV_TXT_SMS_STATUS'  class=div_input>SMS Sent Status </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_SMS_STATUS'>");  
				out.println("      <OPTION value='ALL' >All</OPTION>");
				out.println("      <OPTION value='SUC' >Successful</OPTION>");
				out.println("      <OPTION value='FAL' >Failed</OPTION>");
				
				out.println(" 	   </select>");
				
				
				
				out.println("&nbsp;&nbsp;<input class='but_input' type='button' style='{width:150;}'  name='BUT_VIEW' value=\"View Report\" onClick=\"print_report2();validate_date()\">");	
				out.println("</td> ");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("</FORM>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY>");
				out.println("</HTML>");}
			
			else if(m_chksql.equals("view_report_det")){
				
				String m_from_date="";
				String m_to_date="";
				String m_sms_type="";
				String m_sms_status="";
				
				if(req.getParameter("from_date")!=null ){
					
					m_from_date=req.getParameter("from_date").trim();	
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				if(req.getParameter("sms_type")!=null ){
					m_sms_type=req.getParameter("sms_type").trim();
				}
				
				if(req.getParameter("sms_status")!=null ){
					m_sms_status=req.getParameter("sms_status").trim();
				}
				
				/**Set Up Variable msg Status*/	
				if(m_sms_status.equals("ALL")){
					m_sms_status="%";
				}else if(m_sms_status.equals("SUC")){
					m_sms_status="SUCCESS";
				}else if(m_sms_status.equals("FAL")){
					m_sms_status="ERROR";
				}
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>SMS Log Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("</SCRIPT>");
				
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");
				//out.println("<B align='center'>Report Generated<BR>"+m_from_date+"<BR><BR>"+m_to_date+"<BR><BR>"+m_sms_type+"<BR><BR>"+m_sms_status+"<BR>");
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD </u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>SMS LOG REPORT FROM "+m_from_date+" TO "+m_to_date+"</u></td>");   // from 01-01-2012 to 10-07-2013 
				out.println("</tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				//out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				
				
				out.println("<table id=mytable align=\"left\" width=\"100%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td width=\"5%\" STYLE='{text-align:center;}' ><b>Seq No</b></td>"); 			
				out.println("<td width=\"12%\" STYLE='{text-align:center;}' ><b>Contract No</b></td>"); 	
				out.println("<td width=\"30%\" STYLE='{text-align:center;}' ><b>Client Name</b></td>"); 	
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b>Client Phone No</b></td>");
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b> SMS Type</b></td>");
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b> SMS Status</b></td>");
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b> SMS Sent Date</b></td>");
				out.println("</tr>");
				
				/****************Test Code******************************/
				/*out.println("<tr class=factoring-letter-body  height=20px  >");
				out.println("<td width=\"12%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>"); 			
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>"); 	
				out.println("<td width=\"30%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>"); 	
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>");
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>");
				out.println("<td width=\"15%\" STYLE='{text-align:center;}' ><b>Test 1</b></td>");
			    out.println("</tr>");
				/****************Test Code******************************/
				String m_sql_query="";
				String  m_sql_table="";
				String m_sms_type_decode="";
				
				if(m_sms_type.equals("ALL")){

					m_sql_query = "  "+
						" SELECT NVL(A.CONTRACT_NO,'-') "+
						" ,C.FULL_NAME  CLIENT_NAME  "+
						" ,C.MOBILE_NO  CONTACT_NO  "+
						" ,'Receipts'  "+
						" ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						" ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".SMS_LOG A,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						" WHERE A.STATUS LIKE '"+m_sms_status+"' "+
						" AND A.CONTRACT_NO = B.FINANCE_NO "+
						" AND B.CLIENT_CODE = C.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						
						
						" UNION ALL  "+
						
						" SELECT NVL(A.CONTRACT_NO,'-')  "+
						" ,C.FULL_NAME  CLIENT_NAME "+
						" ,C.MOBILE_NO  CONTACT_NO  "+
						" ,'Rental Reminders'  "+
						" ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						" ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".SMS_LOG_RENTAL_REMINDER A ,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						" WHERE A.STATUS LIKE '"+m_sms_status+"'  "+
						" AND A.CONTRACT_NO = B.FINANCE_NO "+
						" AND B.CLIENT_CODE = C.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						//" ORDER BY SMS_DATE ASC "+
						//[Added milinda for load only client receipts ]
						" UNION ALL "+
						
						" SELECT NVL(A.CONTRACT_NO,'-') "+
						" ,C.FULL_NAME  CLIENT_NAME  "+
						" ,C.MOBILE_NO  CONTACT_NO  "+
						" ,'Receipts'  "+
						" ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						" ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".SMS_LOG A, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						" WHERE A.STATUS LIKE '"+m_sms_status+"'			"+			
						" AND A.CONTRACT_NO = C.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						
						// added by udara 23-07-2018
						
						" UNION ALL "+
						
						" SELECT A.CLIENT_CODE, "+
						" B.FULL_NAME, "+
						" B.MOBILE_NO, "+
						" 'Birthday Wishes', "+
						" DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS , "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".AF_TBL_BIRTHDAY_REMINDER_LOG A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
						" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						" UNION ALL "+
						
						" SELECT NVL(B.FINANCE_NO,'-')  "+ 
						 " ,C.FULL_NAME  CLIENT_NAME "+
						 " ,C.MOBILE_NO  CONTACT_NO  "+
						 " ,'Contract Activation'  "+
						 " ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						 " ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						 " FROM "+m_schema_name+".CONTRACT_ACTIVATION_SMS_LOG A ,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						 " WHERE A.STATUS LIKE '"+m_sms_status+"' "+ 
						 " AND A.APPLICATION_NO = B.APPLICATION_NO "+
						 " AND B.CLIENT_CODE = C.CLIENT_CODE "+
						 " AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						 " AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						
						
						" ";	
					
						// end by udara 23-07-2018
					
					
					
					
				}
				
				
				// added by udara 23-07-2018
				else if(m_sms_type.equals("BDAY")){
					
					m_sql_query = "  "+
						" SELECT A.CLIENT_CODE, "+
						" B.FULL_NAME, "+
						" B.MOBILE_NO, "+
						" 'Birthday Wishes', "+
						" DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS , "+
						" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".AF_TBL_BIRTHDAY_REMINDER_LOG A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
						" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')   "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
					
				}
				// end by udara 23-07-2018
				
				// added by udara 07-08-2018
				else if(m_sms_type.equals("ACTIVATION")){
					
					m_sql_query = "  "+
					" SELECT NVL(B.FINANCE_NO,'-')  "+ 
						 " ,C.FULL_NAME  CLIENT_NAME "+
						 " ,C.MOBILE_NO  CONTACT_NO  "+
						 " ,'Contract Activation'  "+
						 " ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						 " ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						 " FROM "+m_schema_name+".CONTRACT_ACTIVATION_SMS_LOG A ,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						 " WHERE A.STATUS LIKE '"+m_sms_status+"' "+ 
						 " AND A.APPLICATION_NO = B.APPLICATION_NO "+
						 " AND B.CLIENT_CODE = C.CLIENT_CODE "+
						 " AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						 " AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						 " ORDER BY	A.ENT_DATE "+
						 " ";
					
					
				}
				// end by udara 07-08-2018
				
				else {
					
					if(m_sms_type.equals("REC")){
						
						m_sql_table="SMS_LOG";
						m_sms_type_decode="Receipts";
					}else if (m_sms_type.equals("RENT")){
						
						m_sql_table="SMS_LOG_RENTAL_REMINDER";
						m_sms_type_decode="Rental Reminders";
					}

					// added by udara on 26-09-2017
					m_sql_query = " "+
						" SELECT NVL(A.CONTRACT_NO,'-')  "+
						" ,C.FULL_NAME  CLIENT_NAME "+
						" ,C.MOBILE_NO  CONTACT_NO  "+
						//" ,'Rental Reminders'  "+
						",'"+m_sms_type_decode+"' "+
						
						" ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						" ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+"."+m_sql_table+" A ,  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						" WHERE A.STATUS LIKE '"+m_sms_status+"'  "+
						" AND A.CONTRACT_NO = B.FINANCE_NO(+) "+
						" AND B.CLIENT_CODE = C.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
						//[Added milinda for load only client receipts ]
						" UNION ALL "+
						
						" SELECT NVL(A.CONTRACT_NO,'-') "+
						" ,C.FULL_NAME  CLIENT_NAME  "+
						" ,C.MOBILE_NO  CONTACT_NO  "+
						//" ,'Receipts'  "+
						",'"+m_sms_type_decode+"' "+
						" ,DECODE(A.STATUS,'SUCCESS ','Successful','ERROR ','Failed') SMS_STATUS  "+
						" ,TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') SMS_DATE "+
						" FROM "+m_schema_name+".SMS_LOG A, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						" WHERE A.STATUS LIKE '"+m_sms_status+"'			"+			
						" AND A.CONTRACT_NO = C.CLIENT_CODE "+
						" AND TRUNC(A.ENT_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
						" AND TRUNC(A.ENT_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  ";
						//" ORDER BY SMS_DATE ASC ";
					
					
					
				}
				statement = conn.createStatement();
				rs = statement.executeQuery(m_sql_query);
				int i=1;
				String bgColour="";	
				String fontColor="";
				
				//out.println(m_sql_query); 		
				
				while (rs.next()){
					
					if(rs.getString(5).equals("Failed")&& m_sms_status.equals("%")){bgColour="red";fontColor="white";}
					else{bgColour="white";fontColor="black";}
					
					out.println("<tr class=factoring-letter-body bgcolor='"+bgColour+"'  height=20px  >");
					out.println("<td width=\"5%\" STYLE='{text-align:center; color:"+fontColor+";}' >"+i+"</td>");
					out.println("<td width=\"12%\" STYLE='{text-align:left; color:"+fontColor+";}' >"+rs.getString(1)+"</td>"); 			
					out.println("<td width=\"30%\" STYLE='{text-align:left;color:"+fontColor+";}' >"+rs.getString(2)+"</td>"); 	
					out.println("<td width=\"12%\" STYLE='{text-align:left;color:"+fontColor+";}' >"+rs.getString(3)+"</td>"); 	
					out.println("<td width=\"15%\" STYLE='{text-align:left;color:"+fontColor+";}' >"+rs.getString(4)+"</td>");
					out.println("<td width=\"15%\" STYLE='{text-align:left;color:"+fontColor+";}' >"+rs.getString(5)+"</td>");
					out.println("<td width=\"15%\" STYLE='{text-align:left;color:"+fontColor+";}' >"+rs.getString(6)+"</td>");
					
					out.println("</tr>");
					
					i++;	
				}
				//out.println("<B align='center'>Report Generated<BR>"+m_from_date+"<BR><BR>"+m_to_date+"<BR><BR>"+m_sms_type+"<BR><BR>"+m_sms_status+"<BR>");
				out.println("</FORM>");
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</BODY>");
				out.println("</HTML>");}
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
			if(out!=null){
				try{out.close();  
				}catch(Exception e){}
			}
		}
		
		
		
	}
	
	
}