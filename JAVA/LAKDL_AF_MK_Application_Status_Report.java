//Created by Yohan Gunarathna on 24-10-2006 at  11.40 A.M.
//Application Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MK_Application_Status_Report extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
 // public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		Connection conn = null;
		Statement stmt= null;
		java.text.NumberFormat nf= null,nf1= null;
		 ResultSet rs= null;
		 String m_chksql= null;
		ServletOutputStream out = null;
		
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			String m_header_name=con_method.header_name.trim();
			
			
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
			
			else if(m_chksql.equals("main_page")){
				
				String m_sort_column = "";
			    String m_order_by_type = "";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Application Status Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				//pra-----------------**
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";");  // thamali
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				//--------------------**
				
				
				
				
				
				out.println("function run_report() {");
				//out.println("	if(document.Form1.TXT_LOCATION_CODE.value==\"\"){");
				//out.println("	alert('Enter Branch Code');");
				//out.println("	}");
				//out.println("	else{");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=run_report&location_id=\"+document.Form1.TXT_LOCATION_CODE.value;");   
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Application_Status_Report?chksql=run_report&location_id=\"+document.Form1.TXT_LOCATION_CODE.value;");   
				//out.println("		load_interface(m_url,'NORM');");
				//out.println("	}");
				out.println("       window.open(m_url); ");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			view_details();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
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
				
				
				
				out.println("function view_details() {");
				//out.println("alert('sd');");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				
				out.println("	if(document.Form1.TXT_LOCATION_CODE.value!=\"\") {"); // thamali
				out.println("		m_rpt_type=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("   }");
				out.println("   else{");
				out.println("		m_rpt_type='ALL';");
				out.println("   }");
				
				out.println("		if(validate_date()) {");
				//Modified by Dineth on 28-04-2009
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
				//End by Dineth on 28-04-2009
				//out.println(" alert(m_url);");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
				out.println("   }");
				
				
				/*out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				*/
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=main_page';"); 
				out.println("}"); 
				
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Credit Process - Application Status Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Credit Process - Application Status Report \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				
				
				
				//----Added by Prabash on 09-05-2012------**
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
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
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear 
				out.println("	}");
				
				
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
				
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("}");
				
				//---Pra-----------------------------------**
				
				
				out.println("</script>"); 
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Application Status Report</td>"); 
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
				
				// commented below by udara on 14-11-2012
				/*
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr display:'none'>"); 
				out.println("<td width='15%' ></td>"); //<DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV>
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("<td width='*%'></td>");  
				*/
				
				
				// added by udara on 14-11-2012
				out.println("<table class='table' width='100%' style='display:none'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\"  name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("<td width='*%'></td>");  
				
				out.println("</tr >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >"); //<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\">	
				out.println("</td> ");
				out.println("<td width='*%'></td>"); 
				
				out.println("</table>");
				
				out.println("<table class='table' width='100%'>"); 
				
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"View Report\" onClick=\"run_report()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				//out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Broker Code *</DIV></td>"); 
				//out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				//----------------------------------**
				
				out.println("</table>");
				
				
				out.println("<br>"); 
				out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
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
			
			
			
			
			
			
			
			else if(m_chksql.trim().equals("run_report")){
					   
						  String m_sort_column   = "PRIORITY";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
							}
							
							String m_location_id = req.getParameter("location_id");
					
			
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing - Application Status Report \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing - Application Status Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				

			  out.println("function load_data(m_app_no,m_app_sts) {");
				out.println(" if(m_app_sts=='IP') { ");
	      out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&CLOSE=Y&application_no=\"+m_app_no;"); 
			  out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("  }");
				out.println(" else { ");
			  out.println("   alert(m_app_no+'  is complete.');"); 
				out.println("  }");
				out.println("}");
			
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
				//out.println("alert(m_sort_col);");
	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Status_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
			
        out.println("</Script>");
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				
                  
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing - Application Status Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

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
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  out.println("<td colspan=12 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td colspan=14 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2 align='center'>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
          out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Finance No  '       onclick=sort_data('FINANCE_NO') >Finance No</td>");
          out.println("<td  width='10%'  style= cursor:hand; title='Click here to sort by - Entered Date  '      onclick=sort_data('ENT_DATE') >Entered Date</td>");
          out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Period  '            onclick=sort_data('PERIOD') >Period(Days)</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Priority  '            onclick=sort_data('PRIORITY') >Priority</td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Marketing Officer</td>");
					out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
					//out.println("<td  width='10%' >Pro Forma Total Amount</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Status  '            onclick=sort_data('APP_STS') >Status</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Asset  '             onclick=sort_data('APP_STS') >Asset</td>");
					out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Amount  '            onclick=sort_data('APP_STS') >Total net receivables</td>");
					out.println("</tr>");
					 
           int j = 0;   
						
					 rs = stmt.executeQuery (" SELECT "+
							                        " A.APPLICATION_NO,"+ //1
																			" NVL(A.FINANCE_NO,'-') FINANCE_NO , "+ //2
																			"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
																			" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
																			"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
																			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //6
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+ //7
																			" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
																			" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
																			//"	NVL( (SELECT SUM(C.TOTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C WHERE C.APPLICATION_NO=A.APPLICATION_NO),0) PROFORMA_TOTAL, "+ //
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
																			" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
																			//" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+ //12
																			
																			//" DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No') APP_STS , "+//comment by malik on 26-8-2008
																			//"DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No') APP_STS , "+ //add by malik on 26-8-2008
																			" DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO),'ACTIVATED','ACTIVATED',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'ENTERED','ENTERED','ENT_CON','COMPLETED','VERIFY1','CREDIT VERIFICATION','V-APP','CREDIT SCORE APPROVAL','VERIFY-M','CREDIT APPROVAL 1','VERIFY2','CREDIT APPROVAL 2','VERIFYL','ENTERED LEASING NO') APP_STS , "+//add by malik on 7-10-2008
																			// Modified by Thamali Jayatunga on 2009.10.19, changed '-' to 'SLR'.
																			" NVL(CURRENCY_CODE,'SLR'), "+ //13
																			" NVL(PRIORITY,'-'), "+ //14
																			" A.CLIENT_CODE, "+ //15
																			//comment by nuwan de silva on 29-11-2007--------------------------------------------
																			/*" NVL( (SELECT NVL(STATUS,'N')  FROM "+ //added by nuwan de silva on 17-10-07--------
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																			" WHERE   ENT_DATE =( "+
																			" SELECT  MAX(ENT_DATE) "+
																			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																			" WHERE APPLICATION_NO=A.APPLICATION_NO)),'N' ) STATUS "+ //16
																			*/
																			" NVL("+m_schema_name+".AF_CO_GET_MAX_STATUS(A.APPLICATION_NO),'N') STATUS, "+
																			" DECODE(TER_TYPE,NULL,'-',"+m_schema_name+".AF_CO_GET_TERMINATION_DESC(TER_TYPE)) TERM_TYPE, "+
																			" "+m_schema_name+".AF_CO_GET_ASSET_DESC(A.APPLICATION_NO), "+
																			" "+m_schema_name+".AF_CO_GET_NET_INSTALL_AMT(A.APPLICATION_NO)  "+
																			"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																			
																			//" WHERE APPLICATION_STATUS NOT IN ('CANCEL') "+
																			//" WHERE APPLICATION_STATUS IN ('ENTERED') "+
																			" WHERE APPLICATION_STATUS  IN ('ENTERED','ENT_CON','VERIFY1','V-APP','VERIFY-M','VERIFY2','VERIFYL') "+
																			" AND A.BRANCH_CODE  LIKE '"+m_location_id+"%'   "+ 
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
       
				String m_return_status="";
              while(rs.next()){
							    m_return_status=rs.getString(16);
							
									if(m_return_status.equals("RET-VERY-M") || m_return_status.equals("RET-VE-APP") || m_return_status.equals("RET-VERY-1") || m_return_status.equals("RET-EN_CON") ){ 
									out.println("<tr bgcolor='#CCCC99' >");
									}
									
									else{
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									    	out.println("<tr class=tr_input >");
									    }
									}		
									
							    
									/*if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}*/
									
									
									//out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') >"+rs.getString(1) +"</td>");
									out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1) +"</u></td>");
                  out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(2)+"')><u>"+rs.getString(2) +"</u></td>");
                  out.println("<td width='10%' align='left'>"+rs.getString(3) +"</td>");
                  out.println("<td width='5%' align='center'>"+rs.getString(4) +"</td>");
									out.println("<td width='5%' align='center'>"+rs.getString(14) +"</td>");
                  out.println("<td width='15%' align='left'>"+rs.getString(5) +"</td>");
                  out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(15)+"')><U>"+rs.getString(6) +"</U></td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(7) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(8) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(9) +"</td>");
                  //out.println("<td width='10%' align='center'>"+nf.format(rs.getDouble(8))+"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(10) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getInt(11) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(12) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(13) +"</td>");
									out.println("<td width='5%'  align='center'>"+rs.getString(18) +"</td>");
                  out.println("<td width='5%'  align='center'>"+rs.getString(19) +"</td>");
         
                  //out.println("<td><input type=button name=\"Edit_"+j+"\" value=\"Edit\"   class=mainbut1 onclick=load_edit_window(\""+j+"\",\""+rs.getString(1)+"\",\"Edit\"); >");
									//out.println("     </td>");
									out.println("</tr>");
                	j=j+1;
              }
          

					out.println("<tr class=tr_input>");
				  out.println("<td colspan=12 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right colspan=14><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 
          out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("</html>");
      }
			
			//=========================================================================================================================			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
