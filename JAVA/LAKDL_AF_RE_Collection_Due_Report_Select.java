//Created by Yohan Gunarathna on 24-10-2006 at  11.40 A.M.
//Application Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Collection_Due_Report_Select extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
		//	LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			
	//		m_chksql         = req.getParameter("chksql");
	//		m_ac_status = req.getParameter("ac_status");
		//	stmt = conn.createStatement ();
		///	stmt1 = conn.createStatement ();
			
 // 	 if(m_chksql.trim().equals("main_page")){
			
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				
			
			
			
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report_Select';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report_Select';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Due_select\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Collection Due Reports  - \"+m_val;"); 
			//out.println("if(m_val==\"New\")");
			//out.println("document.Form1.hid_status.value=\"Save\";"); //Added By Nuwan De Silva
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Due Reports  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();}"); 
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
		//	out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
		
   
			out.println("}"); 
			out.println("else{}");
			//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
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
			/*out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_CO_Help_Servlet?class_in=\"+client_name+\"AF_CO_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			*/
			//out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			
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
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
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
			
			
				out.println("function clear_data(IfCount) {");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
		//	out.println("document.Form1.TXT_TEMP_REC_NO.focus();");
			out.println("	}");
				out.println("	}");
			
			
				out.println("function help_button_2() {"); 
		//	out.println("    document.Form1.hid_help_type.value=\"2\";"); 
		//	out.println("    m_sql = \"TXT_CLIENT_CODE\";"); 
		//	out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			//out.println("    HelpBox('1','10','0');"); 
			
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				
			out.println("    HelpBox('1','10','2',Crit,'m_help_TXT_CLIENT_CODE','2');"); 
			
			
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("get_due_letters(document.Form1.TXT_CLIENT_CODE.value,'ENT_DATE','ASC');");
			
			out.println("}"); 
			
			
      out.println("function View_3days() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=3\";");
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=950,height=550,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			
			out.println("function View_15days() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=15\";");
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=950,height=550,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}"); 


			out.println("function View_30days() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=30\";");
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=950,height=550,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}"); 

			out.println("function View_All() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=ALL\";");
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=950,height=550,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}"); 


			out.println("function View_select() {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=TODAY\";");
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=950,height=550,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			
			out.println("function set_value(val1) {"); 
			out.println("    document.Form1.hid_select.value=val1.value");
			out.println("}"); 
			
			out.println("function show_report() {"); 
			out.println("  var  val_1=document.Form1.hid_select.value"); 
			out.println("if(val_1=='3'){"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=3';");

			out.println("}"); 
			
			out.println("else if(val_1=='15'){"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=15';");

      out.println("}"); 			
			
			out.println("else if(val_1=='30'){"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=30';");

      out.println("}"); 			
			
			out.println("else if(val_1=='ALL'){"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=ALL';");

      out.println("}"); 			
			
			out.println("else if(val_1=='TODAY'){"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=TODAY';");

   		 out.println("}"); 			

      out.println("}"); 
			
			

						
				out.println("function check_number(obj,size){");
				out.println("if(obj.value!=''){"); 
				out.println("if(isnumberok(obj,size)){"); 
				out.println("format_noobject_nodecimal1(obj)"); 
				out.println("View_receipts(obj.value);");
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value='';"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("DAYS.style.color='red';");
				out.println("}"); 
				out.println("}"); 
	
	
				
				out.println("function View_receipts(m_val) {");
				out.println("if(m_val!=\"\"){");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=TODAY\";"); 
			  out.println(" window.location.href=m_url;"); 
        out.println("}");	
				out.println("}");	
			
        out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  				
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Cancel\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
					//out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_DUE_STATUS\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
					
							out.println("<INPUT TYPE='Hidden' NAME='hid_select' VALUE=\"3\">");			
										
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
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
					out.println("<tr>"); 
					out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Due Reports</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
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
					
					
				  out.println("<table align='center' width='100%' border=0 class='table' cellspacing='1' >"); 
					
					
					
					out.println("<tr>"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_REPORT_TYPE'  class=div_input><b>Report Type</DIV></td>"); 
					out.println("<td width=\"40%\" align=\"left\"><select name=TXT_REPORT class=\"txt_input\" onChange=\"set_value(document.Form1.TXT_REPORT)\">");
					out.println("<OPTION value=\"3\" selected >3 Days Due</option>");
					out.println("<OPTION value=\"15\">15 Days Due</option>");
					out.println("<OPTION value=\"ALL\">All Days Due</option>");		
					out.println("</SELECT>");
					//out.println("<td width='2%' >&nbsp</td>"); 
					
					out.println("<input class='but_input' type='button' style='{width:50px}' name='BUT_TXT_REPORT' value=\"Report\" onClick=\"show_report()\"></td>"); 
				  out.println("<td width='*%' >&nbsp</td>"); 	
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 

					/*
					out.println("<tr>");      //Mod By Sandun on 06-10-2008
					out.println("<td width='30%' ><DIV id='DIV_TXT_COLL'  class=div_input><b>Collection Due Report</DIV></td>"); 
					out.println("</tr>");
					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("</tr>"); 

					
					out.println("<tr >"); 
					out.println("<td width='30%' ID=DAYS >No of Due Days </td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NO_OF_DUE_DATES' maxlength='3' size='3' onBlur=\"check_number(document.Form1.TXT_NO_OF_DUE_DATES,5)\">"); 
					out.println("<input class='but_input' type='button' style='{width:50px}' name='BUT_TXT_VIEW' value=\"Report\" onClick=\"check_number(document.Form1.TXT_NO_OF_DUE_DATES,5)\"></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					*/

					//out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('TODAY')\" ><u>Collection Due Report</u></td>"); 
			    //out.println("<td width='*%'></td>"); 
			   // out.println("</tr>"); 


				/*	out.println("<tr>"); 
			    out.println("<td width='30%' ><b>Select a option to view the report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
             
					out.println("<br>"); 
					out.println("<tr>"); 
					//out.println("<td width='2%'>&nbsp;</td>"); 
			    out.println("<td width='30%' ><input type=\"radio\" name=\"3days\" value=\"pos\" onClick=\"View_3days()\">3 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 

					out.println("<tr>"); 
			    out.println("<td width='30%' ><input type=\"radio\" name=\"3days\" value=\"pos\" onClick=\"View_15days()\">15 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 

					out.println("<tr>"); 
			    out.println("<td width='30%' ><input type=\"radio\" name=\"3days\" value=\"pos\" onClick=\"View_30days()\">30 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
			    out.println("<td width='30%' ><input type=\"radio\" name=\"3days\" value=\"pos\" onClick=\"View_All()\">All Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
			    out.println("<td width='30%' ><input type=\"radio\" name=\"3days\" value=\"pos\" onClick=\"View_select()\">Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					
					*/
					
					/*out.println("<tr>"); 
					out.println("<td width='30%' ><br><a href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=3'>3 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='30%' ><br><a href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=15'>15 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='30%' ><br><a href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=30'>30 Days Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='30%' ><br><a href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=ALL'>All Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='30%' ><br><a href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Due_Report?chksql=main_page&no_of_days=TODAY'>Collection Due Report</td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					*/
					
					
					//commented by delanjali on 2007-08-31
					/*out.println("<tr>"); 
					out.println("<td width='2%'><li></td>"); 
					out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('3')\" ><u>3 Days Collection Due Report</u></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='2%'><li></td>"); 
					out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('15')\" ><u>15 Days Collection Due Report</u></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 

					out.println("<tr>"); 
					out.println("<td width='2%'><li></td>"); 
					out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('30')\" ><u>30 Days Collection Due Report</u></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					
					out.println("<tr>"); 
					out.println("<td width='2%'><li></td>"); 
					out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('ALL')\" ><u>All Collection Due Report</u></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
					
					out.println("<tr>"); 
					out.println("<td width='2%'><li></td>"); 
					out.println("<td width='30%' style= cursor:hand; onClick=\"show_report('TODAY')\" ><u>Collection Due Report</u></td>"); 
			    out.println("<td width='*%'></td>"); 
			    out.println("</tr>"); 
*/






					
					out.println("</table>");
                  
				
								
				 
				
		
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
    //  }
			
			//=========================================================================================================================			
  	
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
