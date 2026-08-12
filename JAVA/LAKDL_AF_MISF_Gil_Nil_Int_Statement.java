//CREATED BY DINETH
//ON 2009-01-13
//SCREEN NAME:AF_MISF_GIL_NIL_STATEMENT

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Gil_Nil_Int_Statement extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt1,stmt;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;
	  

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt1 = conn.createStatement();
			stmt  = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username=con_method.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");
			
			if(m_screen_type.trim().equals("run_report")){
								  
			  String m_date            = req.getParameter("upto_date");
				String m_client_code     = req.getParameter("client_code");
				String m_div_code        = req.getParameter("m_div_code");
				String m_finance_no      = req.getParameter("finance_no");
				String m_tran_type       = req.getParameter("transaction_type");
				String m_location_code   = req.getParameter("location_code");
				String m_sel_crit        = req.getParameter("sel_crit");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_GIL_NIL_STMT(:1,:2,:3,:4,:5,:6,:7);END;");
				callstmt1.setString(1,m_finance_no );
				callstmt1.setString(2,m_location_code);
				callstmt1.setString(3,m_div_code);
				callstmt1.setString(4,m_location_code);
				callstmt1.setString(5,m_sel_crit);
				callstmt1.setString(6,m_date);
				callstmt1.setString(7,m_username);
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			
			}
			
			else if(m_screen_type.trim().equals("main_page")){
			     String m_date_dd = "";
			     String m_date_mm = "";
			     String m_date_yy = "";
			     out.println("<html>");
					 out.println("<head>");
					 out.println("<title>GIL NIL Interest Statement -Asset Financing System</title>    ");
					 out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					 out.println("</head>");
					 out.println("<Script>");
					 
					 out.println("var timerID;");
				   out.println("var durationID=0;");
				
				   out.println("function set_timer_actions() {");
				   out.println("   durationID=durationID+1;");
				   out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				   out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				   out.println("}");
				
					 out.println("function load_roll_value(m_val){"); 
			     out.println("help_box.innerHTML=\"  GIL NIL Interest Statement  - \"+m_val;"); 
			     out.println("}"); 
			     out.println(""); 

			     out.println("function load_roll_out_value(){");
			     out.println("help_box.innerHTML=\"  GIL NIL Interest Statement  - \"+document.Form1.hid_status.value;"); 
			     out.println("}"); 
			     
						
					 out.println("function help_update_location_code() {"); 
					 out.println(" 	document.Form1.hid_help_type.value=\"1\";"); 
					 out.println(" 	m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
					 out.println(" 	m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";");
					 out.println(" 	HelpBox('1','10','0');"); 
					 out.println("}");
			
					 out.println("function help_update_finance_no() {"); 
					 out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
					 out.println(" 	m_sql = \"m_help_TXT_FinanceSql_new4\";"); 
					 out.println(" 	m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@\"+document.Form1.DRP_TRANSACTION_CODE.value+\"@ACTIVATED@\";");
					 out.println(" 	HelpBox('1','10','0');"); 
					 out.println("}"); 
			     
					 out.println("function help_location_code_assign() {"); 
					 out.println("		document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
					 out.println("}");
			
					 out.println("function help_finance_no_assign() {"); 
					 out.println("		document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					 out.println("		document.Form1.TXT_LOCATION_CODE.value=oBj.valout[3];");
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
			     out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			     out.println("	"); 
			     out.println("	if(oBj.valout[1] !=\" \"){"); 
			     out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			     out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			     out.println("	if(oBj.valout[1]!=\"Next\"){");
					 out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
					 out.println("						help_location_code_assign();"); 
	  			 out.println("					}"); 
					 out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
					 out.println("						help_finance_no_assign();"); 
	  			 out.println("					}");
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
			
			     out.println("function load_help_msg() {"); 
			     out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			     out.println("    HelpBox_msg(m_help_message);"); 
			     out.println("}");
			
			     out.println("function HelpBox_msg(m_help_message) {"); 
			     out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			     out.println("  \"&help_message_in=\"+m_help_message);"); 
			     out.println("}");
			
			     out.println("function clear_window(){	"); 
			     out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			     out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Gil_Nil_Int_Statement?chksql=main_page';"); 
			     out.println("		}"); 
			     out.println("}"); 
			

					 out.println("function check_date(objdd,objmm,objyy) {");						
					 out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
					 out.println("  checkMonthLength(objdd,objmm,objyy);");			
      		 out.println("}");
					 out.println("}");
			     out.println("function load_calendar(num) {");
      		 out.println(" document.Form1.hid_cal_date.value=num;"); 
			     out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			     out.println("}");
						
					 out.println("function clear_fin(){");
					 out.println("   document.Form1.TXT_FINANCE_NO.value='';");
					 out.println("}");
						
					 out.println("function load_c_date(val) {");
					 out.println("var date1='' ");
					 out.println("var date2='' ");
		  		 out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
					 out.println("v_date=val.substr(0,val.indexOf('-'));");
					 out.println("if(v_date.length<2)");
					 out.println("v_date=0+v_date");
					 out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					 out.println("v_month=val.substr(0,val.indexOf('-'));");
					 out.println("if(v_month.length<2)");
					 out.println("v_month=0+v_month");
					 out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					 out.println("     document.Form1.UPTO_DAY.value=v_date;");
					 out.println("     document.Form1.UPTO_MONTH.value=v_month;");
					 out.println("     document.Form1.UPTO_YEAR.value=val;");
					 out.println("date1=v_date+'-'+v_month+'-'+val;");
					 out.println("document.Form1.hid_upto_date.value=date1");			
					 out.println("}");
				   out.println("}");
						
					 out.println("function get_sys_date(){");
					 rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			     if(rs.next()){
			     m_date_dd = rs.getString(1).substring(0,2);
			     m_date_mm = rs.getString(1).substring(3,5);
			     m_date_yy = rs.getString(1).substring(6,10);
			     }
			     out.println("document.Form1.UPTO_DAY.value   =\""+m_date_dd+"\"");
			     out.println("document.Form1.UPTO_MONTH.value =\""+m_date_mm+"\"");
			     out.println("document.Form1.UPTO_YEAR.value  =\""+m_date_yy+"\"");
					 out.println("}");
						
					 out.println("function load_details(){ ");	
					 out.println("upto_date = document.Form1.UPTO_DAY.value+'-'+document.Form1.UPTO_MONTH.value+'-'+document.Form1.UPTO_YEAR.value;");
					 out.println("m_div_code   = document.Form1.DIV_CODE.value;");//added by nuwan de silva

					 out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gil_Nil_Int_Statement?chksql=MAIN&location_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&div_code=\"+m_div_code+\"&transaction_type=\"+document.Form1.DRP_TRANSACTION_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sel_crit=\"+document.Form1.DRP_SEL_CRIT.value+\"&upto_date=\"+upto_date+\" \";");
					 out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					 out.println(" } ");	
						//-------------------Added By Sandun on 11-11-2009----------------

						out.println("function run_report() {");
						out.println("upto_date = document.Form1.UPTO_DAY.value+'-'+document.Form1.UPTO_MONTH.value+'-'+document.Form1.UPTO_YEAR.value;");
					  out.println("m_div_code   = document.Form1.DIV_CODE.value;");
					  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gil_Nil_Int_Statement?chksql=run_report&location_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&div_code=\"+m_div_code+\"&transaction_type=\"+document.Form1.DRP_TRANSACTION_CODE.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&sel_crit=\"+document.Form1.DRP_SEL_CRIT.value+\"&upto_date=\"+upto_date+\" \";");
						out.println("   set_timer_actions();");
						out.println("		load_interface(m_url,'NORM');");
						out.println("	}");
						
						
						out.println("function get_vector_normal(m_data){");
						out.println("		if(m_data==\"OK\"){");
						out.println("			print_report();"); 
						out.println("		}");
						out.println("		else{");
						out.println("			alert('Error when generating Report...'+m_data);");
						out.println("		}");
						out.println("}");
						
						out.println("function print_report(){");
						out.println("   upto_date = document.Form1.UPTO_DAY.value+'-'+document.Form1.UPTO_MONTH.value+'-'+document.Form1.UPTO_YEAR.value;");
						out.println("		clearTimeout(timerID);");
						out.println("		m_table.innerHTML=\"\";");
						out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gil_Nil_Int_Statement?chksql=MAIN&date=\"+upto_date+\" \";");
						out.println("		window.open(m_url);");
						out.println("}");
								
						//-----------------------------------------------------------------------------------------------
						
					 out.println("</Script>");
			
					out.println("<body class=\"body & txt-body\" onload=\"get_sys_date()\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
					out.println("<form name=\"Form1\" method=post>");
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
					out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_upto_date' VALUE=\"\">");
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
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>GIL NIL Interest Statement</td>");
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
					
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_DIVISION'  class=div_input>Division </DIV></td>"); 
					out.println("<td width='20%' ><select class='txt_input' name='DIV_CODE' style='width:120'>"); 
					out.println("<option value='BD' selected>Bike</option>");
					out.println("<option value='ALL'>All</option>");
					out.println("</td>");
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>"); 


					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch Code</DIV></td>"); 
					out.println("<td width='20%' align='left'><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_LOCATION_CODE' value=\"Help\" onClick=\"help_update_location_code()\" ></td>"); 
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>");
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_TRANSACTION_TYPE'  class=div_input>Transaction Type</DIV></td>"); 
					out.println("<td width='20%' align='left'><select class='txt_input' name='DRP_TRANSACTION_CODE' onchange='clear_fin()'>");
					out.println("<option value=''>All</option>");
					rs2=stmt.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
										   " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
											 " WHERE  ACTIVE_STATUS='Y' ");

					boolean more=rs2.next();
					while(more){
				
					out.println("<option value=\""+rs2.getString(1)+"\" >"+rs2.getString(2)+"</option>");		
							
					more=rs2.next();
					} 
					
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>");
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
					out.println("<td width='20%' align='left'><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_update_finance_no()\" ></td>"); 
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>");
					out.println("<tr>"); 
					out.println("<td width='15%' ><DIV id='DIV_TXT_SEL_CRIT'  class=div_input>Selection Criteria</DIV></td>"); 
					out.println("<td width='20%' align='left'><select class='txt_input' name='DRP_SEL_CRIT'>");
					out.println("<option value='GIL'>GIL</option>");
					out.println("<option value='NIL'>NIL</option>");
					out.println("<option value='INT'>INTEREST</option>");
					out.println("</select></td>");
					out.println("<td width='*%'>&nbsp;</td>"); 
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='15%' ><DIV id='DIV_TXT_UPTO_DATE'  class=div_input>Date Up To</DIV></td>");
					out.println("<td width='20%' ><input name=\"UPTO_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.UPTO_DAY,document.Form1.UPTO_MONTH,document.Form1.UPTO_YEAR)> ");
					out.println("    <input name=\"UPTO_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.UPTO_DAY,document.Form1.UPTO_MONTH,document.Form1.UPTO_YEAR)> ");
					out.println("    <input name=\"UPTO_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.UPTO_DAY,document.Form1.UPTO_MONTH,document.Form1.UPTO_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					//out.println("<td width='*%' align='left'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_details()\" ></td>"); 
					out.println("<td width='*%' align='left'><input class='but_input' type='button' name='BUT_RUN' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>&nbsp;<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'></td>"); //MOd By Sandun on 11-11-2009
					out.println("</tr>");
					out.println("</table>");
					//---Added By Sandun on 11-11-2009=-------------
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
					//------------------------------------------------
				  out.println("</form>"); 
					out.println("</body>");
					out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
					//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 

					out.println("</html>");
			
			
			
			}
			else if(m_screen_type.trim().equals("MAIN")){//Mod By Sandun on 11-11-2009
			    
					String m_upto_date = req.getParameter("date");
					int j=1;
					String m_month_0="";
					String m_month_1="";
					String m_month_2="";
					String m_month_3="";
					String m_month_4="";
					String m_month_5="";
					String m_month_6="";
					String m_month_7="";
					String m_month_8="";
				  String m_month_9="";
					String m_month_10="";
					String m_month_11="";
					
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>GIL NIL Interest Report</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("function mouse_over(id){");
			    out.println("document.getElementById(id).style.textDecoration='underline';");
			    out.println("}");
								
			    out.println("function mouse_out(id){");
			    out.println("document.getElementById(id).style.textDecoration='none';");
			    out.println("}");	
			
					
					out.println("</script>");
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='4'>"); 
					out.println("<br>");
					out.println("<tr>");
					out.println("<td align='center'  ><b>GIL NIL Interest Statement</b></td>");//<DIV class=div_input style='font-size:9pt'>
					out.println("</tr>");
					//out.println("</table>");
					//out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>");
					out.println("<td align='center' ><b>From -"+m_upto_date+"</b></td>");//<DIV class=div_input>
					out.println("</tr>");//bgcolor=\"#CCCCCC\"
					out.println("</table>"); 
			    out.println("<br>");
					
					rs1= stmt1.executeQuery("SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'MON-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),6),'MON-YYYY'),"+//7
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),7),'MON-YYYY'),"+//8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),8),'MON-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),9),'MON-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),10),'MON-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),11),'MON-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),12),'MON-YYYY'),"+//13
					" TO_CHAR(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY'),"+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),1),'DD-MM-YYYY'),"+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),2),'DD-MM-YYYY'),"+//16
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),3),'DD-MM-YYYY'),"+//17
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),4),'DD-MM-YYYY'),"+//18
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),5),'DD-MM-YYYY'),"+//19
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),6),'DD-MM-YYYY'), "+//20
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),7),'DD-MM-YYYY'), "+//21
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),8),'DD-MM-YYYY'), "+//22
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),9),'DD-MM-YYYY'),"+//23
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),10),'DD-MM-YYYY'),"+//24
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),11),'DD-MM-YYYY'),"+//25
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),12),'DD-MM-YYYY')"+//26
					" FROM DUAL ");
				
					
					if(rs1.next()){
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2 >");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>No</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>Finance No</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>Client Code</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(7)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(8)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(9)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(10)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(11)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' ><DIV class=div_input><b>"+rs1.getString(12)+"</b></DIV></td>");
			
				
								
				rs2=stmt1.executeQuery(" SELECT A.FINANCE_NO, "+
				                       " A.CLIENT_CODE,  "+
												       " NVL(A.MONTH_1,0), "+
															 " NVL(A.MONTH_2,0), "+
															 " NVL(A.MONTH_3,0), "+
															 " NVL(A.MONTH_4,0), "+
												       " NVL(A.MONTH_5,0), "+
															 " NVL(A.MONTH_6,0), "+
															 " NVL(A.MONTH_7,0), "+
															 " NVL(A.MONTH_8,0), "+
															 " NVL(A.MONTH_9,0), "+
												       " NVL(A.MONTH_10,0),"+
															 " NVL(A.MONTH_11,0),"+
															 " NVL(A.MONTH_12,0) "+
															 " FROM "+m_schema_name+".AF_CO_TBD_GIL_NIL_STATEMENT A "+
															 " WHERE A.ENT_USER = '"+m_username+"' "+	
															 " AND  ( A.TERMI_DATE > TO_DATE('"+m_upto_date+"','DD-MM-YYYY') "+
															 "         OR A.TERMI_DATE IS NULL ) "+
															 " AND A.APPLICATION_STATUS = 'ACTIVATED' ");
				
				
				
				boolean more=rs2.next();
				
				while(more){
				
				//out.println("<tr style='height=30'>");//bgcolor=\"#CC66FF\" bgcolor=\"#FFFF00\" bgcolor=\"#FFFFCC\" 
				if(j>0 && j%2==1){
			   out.println("<tr class=tr_input style='height=30'>");
			    }
			   else{
			   out.println("<tr class=tr_input1 style='height=30'>");
			  }
				out.println("<td width='5%' ><DIV class=div_input align='center'>"+j+"</DIV></td>");
				out.println("<td width='10%'  style='cursor:hand' onclick=\"show_finance_detail_drill('"+rs2.getString(1)+"')\" id=finance_no_"+j+" onmouseover=\"mouse_over('finance_no_"+j+"')\" onmouseout=\"mouse_out('finance_no_"+j+"')\"    ><DIV class=div_input>"+rs2.getString(1)+"</DIV></td>");
				out.println("<td width='10%' style='cursor:hand' onclick=\"show_client('"+rs2.getString(2)+"')\" id=client_no_"+j+" onmouseover=\"mouse_over('client_no_"+j+"')\" onmouseout=\"mouse_out('client_no_"+j+"')\"><DIV class=div_input>"+rs2.getString(2)+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(3))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(4))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(5))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(6))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(7))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(8))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(9))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(10))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(11))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(12))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(13))+"</DIV></td>");
				out.println("<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs2.getDouble(14))+"</DIV></td>");
        out.println("</tr>");
				j++;
				more=rs2.next();
				
				}
				}
				

				out.println("</table>");
				out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 

					
			}
			
			else if(m_screen_type.trim().equals("MAIN_old")){
			    String m_finance_no      = req.getParameter("finance_no");
					String m_location_code   = req.getParameter("location_code");
					String m_transaction_type= req.getParameter("transaction_type");
					String m_sel_crit        = req.getParameter("sel_crit");
					String m_upto_date       = req.getParameter("upto_date");
					String m_div_code        = req.getParameter("div_code");
					
					String m_month_0="";
					String m_month_1="";
					String m_month_2="";
					String m_month_3="";
					String m_month_4="";
					String m_month_5="";
					String m_month_6="";
					String m_month_7="";
					String m_month_8="";
				  String m_month_9="";
					String m_month_10="";
					String m_month_11="";
					
					/*out.println(m_finance_no);
					out.println(m_location_code);
					out.println(m_transaction_type);
					out.println(m_sel_crit);
					out.println(m_upto_date);*/
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>GIL NIL Interest Report</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("</script>");
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='4'>"); 
					out.println("<br>");
					out.println("<tr>");
					out.println("<td align='center'  ><b>GIL NIL Interest Statement</b></td>");//<DIV class=div_input style='font-size:9pt'>
					out.println("</tr>");
					//out.println("</table>");
					//out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>");
					out.println("<td align='center' ><b>From -"+m_upto_date+"</b></td>");//<DIV class=div_input>
					out.println("</tr>");//bgcolor=\"#CCCCCC\"
					out.println("</table>"); 
			    out.println("<br>");
					
					rs1= stmt1.executeQuery("SELECT TO_CHAR(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'MON-YYYY'),"+//1
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),1),'MON-YYYY'),"+//2
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),2),'MON-YYYY'),"+//3
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),3),'MON-YYYY'),"+//4
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),4),'MON-YYYY'),"+//5
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),5),'MON-YYYY'),"+//6
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),6),'MON-YYYY'),"+//7
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),7),'MON-YYYY'),"+//8
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),8),'MON-YYYY'),"+//9
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),9),'MON-YYYY'),"+//10
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),10),'MON-YYYY'),"+//11
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),11),'MON-YYYY'),"+//12
					" TO_CHAR(ADD_MONTHS(TO_DATE('01-' || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),12),'MON-YYYY'),"+//13
					" TO_CHAR(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),'DD-MM-YYYY'),"+//14
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),1),'DD-MM-YYYY'),"+//15
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),2),'DD-MM-YYYY'),"+//16
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),3),'DD-MM-YYYY'),"+//17
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),4),'DD-MM-YYYY'),"+//18
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),5),'DD-MM-YYYY'),"+//19
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),6),'DD-MM-YYYY'), "+//20
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),7),'DD-MM-YYYY'), "+//21
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),8),'DD-MM-YYYY'), "+//22
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),9),'DD-MM-YYYY'),"+//23
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),10),'DD-MM-YYYY'),"+//24
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),11),'DD-MM-YYYY'),"+//25
					" TO_CHAR(ADD_MONTHS(TO_DATE(TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'DD') || TO_CHAR(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),'MM-YYYY'),'DD-MM-YYYY'),12),'DD-MM-YYYY')"+//26
					" FROM DUAL ");
				
					
					while(rs1.next()){
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Finance No</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>Client Code</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(3)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(4)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(5)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(6)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(7)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(8)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(9)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(10)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(11)+"</b></DIV></td>");
				out.println("<td width='10%' align='center' bgcolor=\"#CCCCCC\"><DIV class=div_input><b>"+rs1.getString(12)+"</b></DIV></td>");
				
				
				
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				//out.println("</table>");
				m_month_0=rs1.getString(14);
				m_month_1=rs1.getString(15);
				m_month_2=rs1.getString(16);
				m_month_3=rs1.getString(17);
				m_month_4=rs1.getString(18);
				m_month_5=rs1.getString(19);
				m_month_6=rs1.getString(20);
				m_month_7=rs1.getString(21);
				m_month_8=rs1.getString(22);
				m_month_9=rs1.getString(23);
				m_month_10=rs1.getString(24);
				m_month_11=rs1.getString(25);
				}
				//out.println("<table align='center' width='200%' class='table'>");
				if(!m_finance_no.equals("") && !m_location_code.equals("") ){
				rs1=stmt1.executeQuery("SELECT FINANCE_NO,CLIENT_CODE, "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_0+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_1+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_2+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_3+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_4+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_5+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_6+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_7+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_8+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_9+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_10+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_11+"',BRANCH_CODE,'"+m_sel_crit+"'),0) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE FINANCE_NO='"+m_finance_no+"' AND BRANCH_CODE='"+m_location_code+"' AND TRANSACTION_TYPE LIKE '%"+m_transaction_type+"%' AND APPLICATION_STATUS='ACTIVATED'");
				}
				else if(!m_finance_no.equals("") && m_location_code.equals("") ){
				rs1=stmt1.executeQuery("SELECT FINANCE_NO,CLIENT_CODE, "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_0+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_1+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_2+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_3+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_4+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_5+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_6+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_7+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_8+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_9+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_10+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_11+"',BRANCH_CODE,'"+m_sel_crit+"'),0) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE FINANCE_NO='"+m_finance_no+"' AND APPLICATION_STATUS='ACTIVATED' AND TRANSACTION_TYPE LIKE '%"+m_transaction_type+"%'");
				}
				else if(m_finance_no.equals("") && !m_location_code.equals("") ){
				rs1=stmt1.executeQuery("SELECT DISTINCT FINANCE_NO,CLIENT_CODE, "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_0+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_1+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_2+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_3+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_4+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_5+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_6+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_7+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_8+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_9+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_10+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_11+"',BRANCH_CODE,'"+m_sel_crit+"'),0) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B WHERE A.APPLICATION_NO = B.APPLICATION_NO AND "+
				" A.BRANCH_CODE='"+m_location_code+"' "+
				" AND A.APPLICATION_STATUS='ACTIVATED' "+
				" AND A.TRANSACTION_TYPE LIKE '%"+m_transaction_type+"%' "+
				" AND B.RENTAL_DATE >= TO_DATE('"+m_upto_date+"','DD-MM-YYYY') "+
        " AND B.RENTAL_DATE <= ADD_MONTHS(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),11) ");
				}
				else{
				rs1=stmt1.executeQuery("SELECT DISTINCT FINANCE_NO,CLIENT_CODE,NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,'"+m_transaction_type+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'),BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_0+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_1+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_2+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_3+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_4+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_5+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_6+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_7+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_8+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_9+"',BRANCH_CODE,'"+m_sel_crit+"'),0), "+
				" NVL("+m_schema_name+".AF_MISF_GET_GIL_NIL_INT_AMT(CLIENT_CODE,FINANCE_NO,TRANSACTION_TYPE,'"+m_month_11+"',BRANCH_CODE,'"+m_sel_crit+"'),0) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B  "+
				" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
				" AND APPLICATION_STATUS = 'ACTIVATED' "+
				" AND TRANSACTION_TYPE LIKE '%"+m_transaction_type+"%' "+
				" AND B.RENTAL_DATE >= TO_DATE('"+m_upto_date+"','DD-MM-YYYY') "+
        " AND B.RENTAL_DATE <= ADD_MONTHS(TO_DATE('"+m_upto_date+"','DD-MM-YYYY'),11) ");
				
				
				}
				boolean more=rs1.next();
				if(more){
				while(more){
				out.println("<tr style='height=25'>");//bgcolor=\"#CC66FF\"
				out.println("<td width='10%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>"+rs1.getString(1)+"</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFF00\"><DIV class=div_input><b>"+rs1.getString(2)+"</b></DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(6))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(7))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(8))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(9))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(10))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(11))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(12))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(13))+"</DIV></td>");
				out.println("<td width='10%' bgcolor=\"#FFFFCC\" align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(14))+"</DIV></td>");
        out.println("</tr>");
				more=rs1.next();
				}
				}
				
				out.println("</table>");
				out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 

					
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


			
