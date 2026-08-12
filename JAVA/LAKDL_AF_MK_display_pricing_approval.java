
//--
//SCREEN NAME:APPROVAL OF PRICING
//CREATED BY:YOHAN GUNARATHNA
//DATE/TIME:OCTOBER 2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
 

public class LAKDL_AF_MK_display_pricing_approval extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt, stmt1;
	public ResultSet rs, rs1;
	public String m_chksql;
	java.text.NumberFormat nf;
	
	String m_first_price_no = "";
	double m_first_rate = 0.00;
	double m_first_period = 0.00;
	double m_first_interval = 0.00;
	String m_first_pay_mode = "";
	String m_first_currency = "";
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Connection conn = null;
	Statement stmt= null, stmt1= null;
	 ResultSet rs= null, rs1= null;
	 String m_chksql= null;
	java.text.NumberFormat nf= null;
	
	String m_first_price_no = "";
	double m_first_rate = 0.00;
	double m_first_period = 0.00;
	double m_first_interval = 0.00;
	String m_first_pay_mode = "";
	String m_first_currency = "";
		
		try { 
			 
			///SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			// String m_schema_name = m_sn_methods.schema_name.trim();
//			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
	//		String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			m_chksql=req.getParameter("chksql");
			String m_app_no = "";
			String m_inq_no = "";
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			
			if(m_chksql.equals("main_page")){
			
			m_app_no = req.getParameter("APP_NO");
			m_inq_no = req.getParameter("INQ_NO");
			
			String m_TER_NO  = req.getParameter("TER_NO");
			String m_TER_AMT = req.getParameter("TER_AMT");
			String m_TER_TYPE = req.getParameter("TER_TYPE");
			
			String m_brk_status = req.getParameter("brk_status"); // added by udara 23-07-2014
			
			String m_direct_status = req.getParameter("direct_status"); // added by udara 10-10-2014
			
			if(m_inq_no.equals("-")){
				m_inq_no = "";
			}
			
			//String m_transaction_type = req.getParameter("transaction_type"); // added by udara 30-10-2018

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("	<TITLE>Application Process - Approval of Pricing</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("	<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("	var m_app_no='"+m_app_no+"' ;");
			out.println("	var m_inq_no='"+m_inq_no+"' ;");
			out.println("	var m_rate=0;"); 
			out.println("	var m_period=0;"); 
			out.println("	var m_interval=0;"); 
			out.println("	var m_pay_mode='' ;"); 
			out.println("	var m_currency='' ;"); 
			
			out.println("	var b_flag_on=0;"); 
			
			
	
			
			
			out.println("function makeRequest() {");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("    try {");
			out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("    } catch (e) {");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("        } catch (e) {}");
			out.println("    }");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("    return false;");
			out.println("}");
			out.println("url=\""+m_class_url+"/"+m_schema_name+"_AF_MK_display_pricing_approval?chksql=pricing_approval&APP_NO=\"+m_app_no+'&INQ_NO='+m_inq_no ;");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
			out.println("function alertContents(http_request,count) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					pricing_details.innerHTML=m_data;");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			out.println(" }");
			out.println("}");
			
			out.println("function before_submit(){ "); 
			out.println("        var chk_sav_count=0; ");
			out.println("        b_flag_on=0; ");
			
			//ADDED BY NUWAN DE SILVA 06-06-07-----------------------------------
			out.println("        for(j=0;j<document.Form1.NUM_CHKS.value;j++) { ");
			out.println("          if(document.Form1.elements['CHK_'+j].checked==true) {  ");
			//out.println("           chk_sav_count=chk_sav_count+1 ; ");
			//out.println("          }"); 
			
			out.println("  check_pricing(j,document.Form1.NUM_CHKS.value);"); 
			
			out.println("  if(b_flag_on==1){ "); 
			out.println("      break;  "); 
			out.println("        }"); 
			
			out.println("        }"); 
			
			out.println("        }"); 
			
			
			/*out.println("          if(chk_sav_count==0) {  ");
			out.println("            alert('Please select a Pricing No before saving.') ; ");
			out.println("          }"); 
			out.println("          else {"); */
			
			out.println("  if(b_flag_on==0){ "); 
			
			out.println("		         if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("		          document.Form1.action='"+m_class_url+"/"+m_schema_name+"_AF_MK_save_pricing_approval?APP_NO='+m_app_no+'&INQ_NO='+m_inq_no;");  
			out.println("		          document.Form1.submit();	"); 
			out.println("		         }"); 
			out.println("} "); 
			
			//out.println("		       }"); 
			out.println("} "); 
			
			
			
			out.println("function clear_check(rownum){	"); 
			out.println(" alert('Interest Rate, Re-payment Period, Payment Interval, Re-payment Mode and Currency Type should be equal for the selected Pricing Numbers.') ;"); 
			out.println("  document.Form1.elements['CHK_'+rownum].checked=false; ");
			out.println("b_flag_on=1;	");  //added by nuwan de silva 06-06-07
			out.println("}	"); 
			

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+m_app_no+'&INQ_NO='+m_inq_no+'' ;"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function close_screen() {");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("		window.close();"); 
			out.println("		}"); 
			out.println("}");

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+m_app_no+'INQ_NO='+m_inq_no+'' ;"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message =\"m_help_msg_LAKDL_AF_MK_display_pricing_approvals\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Application Process - Approval of Pricing - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Application Process - Approval of Pricing  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

						
			out.println("function display_data(pric_no){");
			//out.println(" var m_transaction_type = '"+m_transaction_type+"'; ");
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&pricing_no='+pric_no+'&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_TYPE="+m_TER_TYPE+"&TER_AMT="+m_TER_AMT+"&TER_NO="+m_TER_NO+"';"); // commented by udara 23-07-2014
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&pricing_no='+pric_no+'&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_TYPE="+m_TER_TYPE+"&TER_AMT="+m_TER_AMT+"&TER_NO="+m_TER_NO+"'+'&brk_status='+'"+m_brk_status+"';");  // added by udara 23-07-2014
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&pricing_no='+pric_no+'&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_TYPE="+m_TER_TYPE+"&TER_AMT="+m_TER_AMT+"&TER_NO="+m_TER_NO+"'+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"';"); // commented by udara 27-01-2017 // added by udara 10-10-2014
			out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&pricing_no='+pric_no+'&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_TYPE="+m_TER_TYPE+"&TER_AMT="+m_TER_AMT+"&TER_NO="+m_TER_NO+"'+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"'+'&approval_view=Y';"); // commented by udara 30-10-2018 // added by udara 27-01-2017
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&pricing_no='+pric_no+'&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_TYPE="+m_TER_TYPE+"&TER_AMT="+m_TER_AMT+"&TER_NO="+m_TER_NO+"'+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"'+'&approval_view=Y&transaction_type='+m_transaction_type;"); // added by udara 30-10-2018
			out.println("window.open(m_url,'displayWindow4','left=20,top=13,width=950,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			
			out.println("function display_new_pricing(){");
			//out.println("window.close()");
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&inquiry_no='+m_inq_no;"); 
			//out.println("window.open(m_url,'displayWindow4','left=20,top=13,width=950,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			
			
			//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no;"); 
			//out.println("window.open(m_url,'displayWindow4','left=20,top=13,width=950,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 

			//out.println(" var m_transaction_type = '"+m_transaction_type+"'; ");
			
			//out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no;"); // commented by udara 23-07-2014
			//out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&brk_status='+'"+m_brk_status+"';");  // added by udara 23-07-2014
			//out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"';"); // commented by udara 13-02-2017 // added by udara 10-10-2014
			out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"'+'&approval_view=Y';"); // commented by udara 30-10-2018 // added by udara 13-02-2017
			//out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&brk_status='+'"+m_brk_status+"'+'&direct_status='+'"+m_direct_status+"'+'&approval_view=Y&transaction_type='+m_transaction_type;"); // added by udara 30-10-2018
			out.println("}"); 
			
			
			out.println("function check_pricing(rownum,row_count){");
			out.println(" var chk_count=0; ");
			out.println(" var m_first_check=''; ");
			//out.println("   alert('m_first_rate:"+m_first_rate+"'); ");
			/*out.println("   m_rate="+m_first_rate+";"); 
			out.println("   m_period="+m_first_period+";"); 
			//out.println("   alert('m_period--:'+parseFloat(m_period)); ");
			out.println("   m_interval="+m_first_interval+";"); 
			out.println("   m_pay_mode='"+m_first_pay_mode+"';"); */
			
			//out.println("alert(rownum+'-'+rate+'-'+period+'-'+interval+'-'+pay_mode+'-'+row_count);");
			out.println(" for(i=0;i<row_count;i++) { ");
			out.println("   if(document.Form1.elements['CHK_'+i].checked==true){ ");
			//out.println("   if(document.Form1.elements['CHK_'+i].value==\"YES\"){ ");
			out.println("     chk_count=chk_count+1; ");
			out.println("       if (chk_count==1) {");
			out.println("          m_first_check=i;"); 
			out.println("       }"); 
			out.println("   }"); 
			out.println(" }"); 
			//out.println("    alert('m_rate:'+m_rate+'-'+'chk_count:'+chk_count+'m_first_check'+m_first_check);");
			//out.println("   alert(m_rate+'-'+m_period+'-'+m_interval+'-'+m_pay_mode);");
			out.println(" if (chk_count==0) {");
			out.println("   m_rate=0;"); 
			out.println("   m_period=0;"); 
			out.println("   m_interval=0;"); 
			out.println("   m_pay_mode='';"); 
			out.println("   m_currency='';"); 
			out.println("  }"); 
			out.println(" else if (chk_count==1 && m_rate==0 && document.Form1.elements['CHK_'+m_first_check].checked==true) {");
			//out.println("   alert('1');");
			out.println("   m_rate=document.Form1.elements['HID_TXT_RATE_'+rownum].value;"); 
			out.println("   m_period=document.Form1.elements['HID_TXT_PERIOD_'+rownum].value;"); 
			out.println("   m_interval=document.Form1.elements['HID_TXT_INTERVAL_'+rownum].value;"); 
			out.println("   m_pay_mode=document.Form1.elements['HID_TXT_MODE_'+rownum].value;"); 
			out.println("   m_currency=document.Form1.elements['HID_TXT_CURR_'+rownum].value;"); 
			out.println("  }"); 
			out.println(" else if (chk_count==1 && m_rate!=0 && document.Form1.elements['CHK_'+m_first_check].checked==true) {");
			//out.println("   alert('2');");
			out.println("   m_rate=document.Form1.elements['HID_TXT_RATE_'+m_first_check].value;"); 
			out.println("   m_period=document.Form1.elements['HID_TXT_PERIOD_'+m_first_check].value;"); 
			out.println("   m_interval=document.Form1.elements['HID_TXT_INTERVAL_'+m_first_check].value;"); 
			out.println("   m_pay_mode=document.Form1.elements['HID_TXT_MODE_'+m_first_check].value;"); 
			out.println("   m_currency=document.Form1.elements['HID_TXT_CURR_'+m_first_check].value;"); 
			out.println("  }"); 
			
			out.println(" else if (chk_count>1) {");      
			/*out.println("   alert(parseFloat(m_period)+'-'+ parseFloat(document.Form1.elements['HID_TXT_PERIOD_'+rownum].value) );");
			out.println("   alert(parseFloat(m_rate)  +'-'+ parseFloat(document.Form1.elements['HID_TXT_RATE_'+rownum].value) )");
			out.println("   alert(parseFloat(m_interval)+'-'+ parseFloat(document.Form1.elements['HID_TXT_INTERVAL_'+rownum].value) )");
			out.println("   alert(m_pay_mode+'-'+ document.Form1.elements['HID_TXT_MODE_'+rownum].value )");
			out.println("   alert(m_currency+'-'+ document.Form1.elements['HID_TXT_CURR_'+rownum].value )");*/
			
			out.println("       if ( (parseFloat(m_rate)!= parseFloat(document.Form1.elements['HID_TXT_RATE_'+rownum].value) ) || (parseFloat(m_period)!= parseFloat(document.Form1.elements['HID_TXT_PERIOD_'+rownum].value) ) || (parseFloat(m_interval)!= parseFloat(document.Form1.elements['HID_TXT_INTERVAL_'+rownum].value) ) || (m_pay_mode!= document.Form1.elements['HID_TXT_MODE_'+rownum].value) || (m_currency!= document.Form1.elements['HID_TXT_CURR_'+rownum].value) ) {");
			out.println("        clear_check(rownum);"); 
			out.println("      }"); 
			out.println("  }"); 
			//out.println(" alert(m_rate+'-'+m_period+'-'+m_interval+'-'+m_pay_mode); ");
			
			out.println("}"); 
			
			
			
			
			
			out.println("</SCRIPT>"); 
			
			
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); //onload=\"makeRequest();\"
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Application Process - Approval of Pricing </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' style='{width:100};' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New Pricing\");' onClick='display_new_pricing()' value=\"New Pricing\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_help_msg()' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
   		out.println("<td width='10%' align='center'>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>");  
			out.println("<br>"); 
			

			
			out.println("<DIV id='pricing_details'  class=div_input>");
			
			///
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr><td align='left' class='pdn_txtpos2' >APPROVAL OF PRICING FOR APPLICATION NO : "+m_app_no+"</td></tr>"); 
				out.println("</table>"); 
				out.println("<br><br><br>"); 
				
				out.println("<table align='center' width='100%' class='table' border=\"1\">");
				out.println("<tr >");
				out.println("<th width='1%'></th>"); 
				out.println("<th width='14%' align='center'><DIV class=div_input>Pricing No</DIV></th>");
				out.println("<th width='6%' align='center'><DIV class=div_input>Interest Rate</DIV></th>");
				out.println("<th width='6%' align='center'><DIV class=div_input>Repayment Period</DIV></th>"); 
				out.println("<th width='6%' align='center'><DIV class=div_input>Payment Interval</DIV></th>"); 
				out.println("<th width='10%' align='center'><DIV class=div_input>Interest Type</DIV></th>"); 
				out.println("<th width='10%' align='center'><DIV class=div_input>Repayment Mode</DIV></th>"); 
				out.println("<th width='14%' align='center'><DIV class=div_input>Gross Amount</DIV></th>"); 
				out.println("<th width='10%' align='center'><DIV class=div_input>VAT</DIV></th>"); 
				out.println("<th width='14%' align='center'><DIV class=div_input>Net Amount</DIV></th>"); 
				out.println("<th width='5%' align='center'><DIV class=div_input>Currency</DIV></th>"); 
				out.println("<th width='5%' align='center'><DIV class=div_input></DIV></th>"); 
				out.println("<th width='%' align='center'><DIV class=div_input></DIV></th>"); 
				out.println("</tr>");
				
					
				rs= stmt.executeQuery (" SELECT PRICING_NO, NVL(RATE,0), NVL(PERIOD,0), NVL(PAYMENT_INTERVAL,0),"+
								" NVL(INTEREST_TYPE,'-'), NVL(PAYMENT_MODE,'N/A'), NVL(GROSS_AMOUNT,0), NVL(VAT_AMOUNT,0),"+
								" NVL(NET_AMOUNT,0), NVL(INQUIRY_NO,'-'),APP_NO,NVL(CURRENCY_CODE,'-') "+ // do not apply nvl to APP_NO
								" FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
								" WHERE INQUIRY_NO ='"+m_inq_no+"' ORDER BY PRICING_NO ");				
								//" WHERE APP_NO ='"+m_app_no+"' ORDER BY PRICING_NO ");				//MODIFIED BY NUWAN DE SILVA 10-05-07
				int i=0;
				int m_checked_count=0;
				
				while(rs.next()){
				  String m_checked="";
					
				  if(rs.getString(11)!=null){
						  m_checked="checked";
							m_checked_count+=1;
							
						  if(m_checked_count==1){
								m_first_price_no=rs.getString(1);
								m_first_rate=rs.getDouble(2);
								m_first_period=rs.getDouble(3);
								m_first_interval=rs.getDouble(4);
								m_first_pay_mode=rs.getString(6);
								m_first_currency=rs.getString(12);
						  }
							
							//out.println(m_first_price_no+"----"+m_first_rate+"---"+m_first_period+"----"+m_first_interval+"---"+m_first_pay_mode+"---"+m_first_currency);
						
					}
					
					
					out.println("<tr >");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='14%' align='left'><DIV class=div_input>"+rs.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_PRICING_"+i+"\" VALUE=\""+rs.getString(1)+"\"></td>");
					out.println("<td width='6%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(2))+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_RATE_"+i+"\" VALUE=\""+rs.getDouble(2)+"\"></td>");
					out.println("<td width='6%' align='center'><DIV class=div_input>"+rs.getString(3)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_PERIOD_"+i+"\" VALUE=\""+rs.getDouble(3)+"\"></td>"); 
					out.println("<td width='6%' align='center'><DIV class=div_input>"+rs.getString(4)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_INTERVAL_"+i+"\" VALUE=\""+rs.getDouble(4)+"\"></td>"); 
					out.println("<td width='10%' align='center'><DIV class=div_input>"+rs.getString(5)+"</DIV></td>"); 
					out.println("<td width='10%' align='center'><DIV class=div_input>"+rs.getString(6)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_MODE_"+i+"\" VALUE=\""+rs.getString(6)+"\"></td>"); 
					out.println("<td width='14%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(7))+"</DIV></td>");
					out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(8))+"</DIV></td>"); 
					out.println("<td width='14%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(9))+"</DIV></td>"); 
					out.println("<td width='5%' align='center'><DIV class=div_input>"+rs.getString(12)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_CURR_"+i+"\" VALUE=\""+rs.getString(12)+"\"></td>"); 
					out.println("<td width='5%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"')\"></DIV></td>"); 
					if(m_checked.equals("checked")){
					  out.println("<td width='4%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\" onclick=\"check_pricing('"+i+"',document.Form1.NUM_CHKS.value)\" checked></DIV></td>"); 
					}else{
					  out.println("<td width='4%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\" onclick=\"check_pricing('"+i+"',document.Form1.NUM_CHKS.value)\" ></DIV></td>"); 
					}
					out.println("</tr>");
					i++;
				}
				out.println("<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">");
				out.println("</table>");
			/////
			out.println("</DIV>");			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT>"); 
			out.println("function assign_first(){");
			out.println("   m_rate="+m_first_rate+";"); 
			out.println("   m_period="+m_first_period+";"); 
			out.println("   m_interval="+m_first_interval+";"); 
			out.println("   m_pay_mode='"+m_first_pay_mode+"';"); 
			out.println("   m_currency='"+m_first_currency+"';"); 
			
			//out.println("alert('m_period is TEST ******'+m_period);");
			out.println("}"); 
			out.println("assign_first();"); 
			out.println("</SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>");
			
			}
			
			//========================================================================================================================================================================================================================================================================================
			
			else if(m_chksql.equals("pricing_approval")){
			
				String m_app_no1 = req.getParameter("APP_NO");
				String m_inq_no1 = req.getParameter("INQ_NO");
			
				
				String m_string="";
				
				m_string=m_string+"<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >APPROVAL OF PRICING FOR APPLICATION NO : "+m_app_no1+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br><br><br>"; 
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr >";
				m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='14%' align='center'><DIV class=div_input>Pricing No</DIV></th>";
				m_string=m_string+"<th width='6%' align='center'><DIV class=div_input>Interest Rate</DIV></th>";
				m_string=m_string+"<th width='6%' align='center'><DIV class=div_input>Repayment Period</DIV></th>"; 
				m_string=m_string+"<th width='6%' align='center'><DIV class=div_input>Payment Interval</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>Interest Type</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>Repayment Mode</DIV></th>"; 
				m_string=m_string+"<th width='14%' align='center'><DIV class=div_input>Gross Amount</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='center'><DIV class=div_input>VAT</DIV></th>"; 
				m_string=m_string+"<th width='14%' align='center'><DIV class=div_input>Net Amount</DIV></th>"; 
				m_string=m_string+"<th width='5%' align='center'><DIV class=div_input>Currency</DIV></th>"; 
				m_string=m_string+"<th width='5%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"<th width='%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"</tr>";
				
					
				rs= stmt.executeQuery (" SELECT PRICING_NO, NVL(RATE,0), NVL(PERIOD,0), NVL(PAYMENT_INTERVAL,0),"+
								" NVL(INTEREST_TYPE,'-'), NVL(PAYMENT_MODE,'N/A'), NVL(GROSS_AMOUNT,0), NVL(VAT_AMOUNT,0),"+
								" NVL(NET_AMOUNT,0), NVL(INQUIRY_NO,'-'),APP_NO,NVL(CURRENCY_CODE,'-') "+ // do not apply nvl to APP_NO
								" FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
								" WHERE INQUIRY_NO ='"+m_inq_no1+"' ORDER BY PRICING_NO ");				
				int i=0;
				int m_checked_count=0;
				
				while(rs.next()){
				  String m_checked="";
					
				  if(rs.getString(11)!=null){
						  m_checked="checked";
							m_checked_count+=1;
							
						  if(m_checked_count==1){
								m_first_price_no=rs.getString(1);
								m_first_rate=rs.getDouble(2);
								m_first_period=rs.getDouble(3);
								m_first_interval=rs.getDouble(4);
								m_first_pay_mode=rs.getString(6);
								m_first_currency=rs.getString(12);
						  }
							
							//out.println(m_first_price_no+"----"+m_first_rate+"---"+m_first_period+"----"+m_first_interval+"---"+m_first_pay_mode+"---"+m_first_currency);
						
					}
					
					
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='14%' align='left'><DIV class=div_input>"+rs.getString(1)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_PRICING_"+i+"\" VALUE=\""+rs.getString(1)+"\"></td>";
					m_string=m_string+"<td width='6%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(2))+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_RATE_"+i+"\" VALUE=\""+rs.getDouble(2)+"\"></td>";
					m_string=m_string+"<td width='6%' align='center'><DIV class=div_input>"+rs.getString(3)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_PERIOD_"+i+"\" VALUE=\""+rs.getDouble(3)+"\"></td>"; 
					m_string=m_string+"<td width='6%' align='center'><DIV class=div_input>"+rs.getString(4)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_INTERVAL_"+i+"\" VALUE=\""+rs.getDouble(4)+"\"></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input>"+rs.getString(5)+"</DIV></td>"; 
					m_string=m_string+"<td width='10%' align='center'><DIV class=div_input>"+rs.getString(6)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_MODE_"+i+"\" VALUE=\""+rs.getString(6)+"\"></td>"; 
					m_string=m_string+"<td width='14%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(7))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(8))+"</DIV></td>"; 
					m_string=m_string+"<td width='14%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(9))+"</DIV></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input>"+rs.getString(12)+"</DIV><INPUT TYPE=\"HIDDEN\" NAME=\"HID_TXT_CURR_"+i+"\" VALUE=\""+rs.getString(12)+"\"></td>"; 
					m_string=m_string+"<td width='5%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"')\"></DIV></td>"; 
					if(m_checked.equals("checked")){
					  m_string=m_string+"<td width='4%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\" onclick=\"check_pricing('"+i+"',document.Form1.NUM_CHKS.value)\" checked></DIV></td>"; 
					}else{
					  m_string=m_string+"<td width='4%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"CHECKBOX\" NAME=\"CHK_"+i+"\" onclick=\"check_pricing('"+i+"',document.Form1.NUM_CHKS.value)\" ></DIV></td>"; 
					}
					m_string=m_string+"</tr>";
					i++;
				}
				m_string=m_string+"<INPUT TYPE='text' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
 
			out.flush();
			out.close();
			conn.close();
			this.destroy();
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


