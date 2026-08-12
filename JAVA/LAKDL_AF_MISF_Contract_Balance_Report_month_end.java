// DEVELOPED BY : SANDUN FOR OFSCL MANAGEMENT INFORMATION   
// DATE:08-08-2008
//**********************************************************


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Contract_Balance_Report_month_end extends javax.servlet.http.HttpServlet { 
	
	/*ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf1;
	Statement stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs1;*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
	ServletOutputStream out = null;
	Connection conn= null;
	java.text.NumberFormat nf1= null;
	Statement stmt1= null;
	CallableStatement callstmt1 =null;
	 ResultSet rs1= null;
		
		try { 			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			String m_username=m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream();
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);			
			
			double oustanding_bal = 0.0;
			double future_receivble = 0.0;
			double unearned_income= 0.0;
			double tot_oustanding_bal = 0.0;
			double tot_future_receivble = 0.0;
			double tot_unearned_income = 0.0;			
			
			double nibsm = 0.0;			
			double ami = 0.0;			
			double maintaince = 0.0;			
			double tot_nibsm = 0.0;			
			double tot_ami = 0.0;			
			double tot_maintaince = 0.0;
			double tot_RENTAL=0.0;
			int count=0;
			
			double tot_future_debits = 0.0; // added by udara 07-01-2021
			
			stmt1 = conn.createStatement();			
			String m_chksql=req.getParameter("chksql");
			String m_client_code=req.getParameter("client_code");
			String m_date=req.getParameter("date");
			String m_branch_code=req.getParameter("branch_code"); //  Added by: Samith dilshan on 2015-05-05 , To get brach code for 'Contract Balance Report'
			 
			// added by udara 10-07-2017
			String m_branch_sql = ""; 
			if(m_branch_code!=null){
			
				if (!m_branch_code.equals("")){
					m_branch_sql = " AND X.BRANCH_CODE = '"+m_branch_code+"'  ";
				}
				
			}
			
			// added by udara 11-10-2018
				String m_product_name = "";
				
				if(req.getParameter("product_name") != null ){
					m_product_name = req.getParameter("product_name").trim();
				}
				// end by udara 11-10-2018
			
			if(m_chksql.equals("run_report")){ 				
				try{	
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_TBD_CONTRACT_STATUS_MON(:1,:2,:3,:4);END;");
					
					callstmt1.setString(1,m_client_code);	
					callstmt1.setString(2,m_date);
					callstmt1.setString(3,m_username);
					callstmt1.setString(4,m_branch_code); //  Added by: Samith dilshan on 2015-05-05
					callstmt1.execute();
					
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}else							
				if(m_chksql.equals("main_page")){ 
					
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Contract Balance Report</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("var timerID;");
					out.println("var durationID=0;");
					
					out.println("function set_timer_actions() {");
					out.println("   durationID=durationID+1;");
					out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
					out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
					out.println("}");
					
					out.println("function load_sysdate(){	"); 
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
					
					if(rs1.next()){
						out.println("document.Form1.VAL_DAY.value='"+rs1.getString(1)+"';");
						out.println("document.Form1.VAL_MONTH.value='"+rs1.getString(2)+"';");
						out.println("document.Form1.VAL_YEAR.value='"+rs1.getString(3)+"';");
					}
					out.println("}"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Balance_Report_month_end?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
					
					out.println("function new_window(){	"); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Balance_Report_month_end?chksql=main_page';"); 
					out.println("}"); 
					
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
					out.println("    HelpBox_msg(m_help_message);"); 
					out.println("}"); 	
					
					out.println("function HelpBox_msg(m_help_message) {"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
					out.println("  \"&help_message_in=\"+m_help_message);"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){"); 
					out.println("		help_box.innerHTML=\" Credit Process -Contract Balance Report - \"+m_val;"); 
					out.println("}"); 
					
					out.println("function load_roll_out_value(){");
					out.println("}");
					
					out.println("function MyDialog(){"); 
					out.println("    this.valout=new Array(10);"); 
					out.println("}");
					
					out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
					out.println("	oBj = new MyDialog();"); 
					out.println(" oBj.valout[1]=\" \";"); 
					out.println(" oBj.valout[2]=\" \";"); 
					out.println(" oBj.valout[3]=\" \";"); 
					out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("		if(oBj.valout[1]!=\"Next\"){"); 
					out.println("			if(IfCount==\"1\"){"); 
					out.println("			client_assign(oBj);"); 
					out.println("			}");
					out.println("			if(IfCount==\"2\"){");   // Samith : for brach
					out.println("			client_assign2(oBj);"); 
					out.println("			}");
					out.println("			if(IfCount==\"3\"){"); 
					out.println("			help_value_assign_collection(oBj);"); 
					out.println("			}"); 
					out.println("		}");
					out.println("		else{"); 
					out.println("			Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
					out.println("			return false;"); 
					out.println("		}"); 
					out.println("	}");
					out.println("	else{"); 
					out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
					out.println("	}"); 
					out.println("	}");
					out.println("	}	");
					out.println("}"); 
					
					out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					
					out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					
					out.println("function client_help(){");
					out.println("	Crit=document.Form1.CLIENT_CODE.value+\"@\";");
					out.println(" document.Form1.hid_help_type.value='1';");
					out.println("	HelpBox('1','10','0',Crit,'ClientSql','1');");
					out.println("}");		
					
					
					out.println("function client_assign(oBj){");
					out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
					out.println("}");
					
					
					out.println("function check_Date(objDD,objMM,objYY) {");
					out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
					out.println("if(checkMonthLength(objDD,objMM,objYY)){");
					out.println("}");
					out.println("}");
					
					out.println("function run_report() {");
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
					out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Balance_Report_month_end?chksql=run_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value;");  // Samith :  brach:TXT_LOCATION_CODE
					out.println("   set_timer_actions();");
					out.println("		load_interface(m_url,'NORM');");
					out.println("	}");
					out.println("}");
					
					out.println("function get_vector_normal(m_data){");
					out.println("		if(m_data==\"OK\"){");
					out.println("			print_report();"); 
					out.println("		}");
					out.println("		else{");
					out.println("			alert('Error when generating Report...'+m_data);");
					out.println("		}");
					out.println("}");
					
					out.println("function print_report(){");
					out.println("		clearTimeout(timerID);");
					out.println("		m_table.innerHTML=\"\";");
					out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
					out.println("   m_product_name   = document.Form1.TXT_PRODUCT.value; "); // added by udara 11-10-2018
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
					//out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Balance_Report_month_end?chksql=print_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value;");   // Samith :  brach:TXT_LOCATION_CODE
					out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Balance_Report_month_end?chksql=print_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&product_name=\"+m_product_name;"); 
					out.println("			window.open(m_url);");
					out.println("	}");
					out.println("}");
					
					out.println("function load_calendar(num) {");
					out.println(" document.Form1.hid_cal_date.value=num;"); 
					out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
					out.println("}");
					
					out.println("function load_c_date(val) {");		
					out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
					out.println("if(document.Form1.hid_cal_date.value=='2'){"); 
					out.println("v_date=val.substr(0,val.indexOf('-'));");
					out.println("if(v_date.length<2)");
					out.println("v_date=0+v_date");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("v_month=val.substr(0,val.indexOf('-'));");
					out.println("if(v_month.length<2)");
					out.println("v_month=0+v_month");
					
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");			
					out.println("     document.Form1.VAL_DAY.value=v_date;");
					out.println("     document.Form1.VAL_MONTH.value=v_month;");
					out.println("     document.Form1.VAL_YEAR.value=val;");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
					out.println("  }");				
					out.println("}");
					out.println("}");
					
					
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}");
					out.println("}"); 
			
			
					// Samith Dilshan : Select branch
					out.println("function help_branch() {"); 
					out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
					out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
					out.println("}");
					
					out.println("function client_assign2(oBj){");
					out.println(" document.Form1.TXT_LOCATION_CODE.value =oBj.valout[2]");
					out.println("}");
					
					
					
					out.println("</script>"); 
					
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
					out.println("<input type=hidden name='hid_cal_date' value=\"\">");
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Contract Balance Report Month End</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");
					out.println("<td width='10%'></td>");
					out.println("<td width='10%'></td>");
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
					out.println("<table align='center' width='100%' class='table' border='0'>"); 
					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>Date </td>");
					out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
					out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");
					out.println("</td>");
					out.println("<td width='*%'>");
					out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
					//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
					out.println("</td>"); 
					out.println("</td>");
					out.println("</tr>");
					
					//Aded By : Samith Dilshan on 2015-04-30
					/*out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\" ></td>"); 
					out.println("</tr>"); */
					
					//Aded By : Samith Dilshan on 2015-04-30
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch</DIV></td>"); 
					out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' value=\"\" maxlength='50' style='{width=150px}' size='50' onblur=\"help_branch()\">"); 
					out.println("</td>");
					out.println("<td width='*%'><input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\">"); 
					out.println("</td>");
					out.println("</tr>"); 
					
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
					out.println("<td width='15%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='50' style='{width=150px}' size='50' onblur=\"client_help()\">"); 
					out.println("</td>");
					out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
					out.println("</td>");
					out.println("</tr>"); 
					
					// added by udara 11-10-2018
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT'  class=div_input> Product Name </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_PRODUCT'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				rs1=stmt1.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE  ACTIVE_STATUS='Y' ");
				
				while(rs1.next()){
					out.println("  <OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				// end by udara 11-10-2018
		
		
		
					out.println("</table>");
					
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
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();			
					
				}else	
					if(m_chksql.equals("print_report")){		
						
						out.println("<HTML><HEAD><TITLE>Contract Balance Report</TITLE></HEAD>");
						out.println("<SCRIPT language=\"JavaScript\">"); //Added by Dineth on 2008-12-31
						out.println("   function show_maintenance_drill(m_app_no,m_date){");
						out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Contract_Balance_Report_month_end?chksql=SHOW_MAINTENANCE_DRILL&date=\"+m_date+\"&app_no=\"+m_app_no;");
						out.println("   window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1\");");
						out.println("   }");
						
						// added by udara 02-09-2015
						
						out.println("	function show_transaction_info(m_client_code,m_finance_no){");
						out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
						out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
						out.println("	}");
						
						// end by udara 02-09-2015
						

						out.println("</SCRIPT>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 							
						
						out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
						out.println("<TR><TD align='Center' class=factoring-letter-body><B>Contract Balance Report As At "+m_date+"</B></TD></TR>");
						out.println("</TABLE>");
						
						out.println("<br>");
						out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
						out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
						out.println("<td width='5%' align=left><DIV class=factoring-letter-body><b>Serial No<DIV></td>");//ADD BY MALIK ON 21-8-2008
						
						out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Finance No<DIV></td>");
						out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Status<DIV></td>");			
						out.println("<td width='20%' align=left><DIV class=factoring-letter-body><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Application No</b></DIV></td>");
						out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Activation Date</b></DIV></td>");
						out.println("<td width='10%' align=left><DIV class=factoring-letter-body><b>Termination Date</b></DIV></td>");
						out.println("<td width='15%' align=right><DIV class=factoring-letter-body><b>Balance Outstanding</b></DIV></td>"); 	
						out.println("<td width='20%' align=right><DIV class=factoring-letter-body><b>Future Receivable</b></DIV></td>");
						out.println("<td width='20%' align=right><DIV class=factoring-letter-body><b>Unearned Income</b></DIV></td>"); 
						out.println("<td width='15%' align=right><DIV class=factoring-letter-body><b>NIBSM</b></DIV></td>"); 
						out.println("<td width='15%' align=right><DIV class=factoring-letter-body><b>AMI</b></DIV></td>"); 
						out.println("<td width='15%' align=right><DIV class=factoring-letter-body><b>Maintance</b></DIV></td>"); 
						out.println("<td width='15%' align=right><DIV class=factoring-letter-body><b>Rental Amount</b></DIV></td>"); 
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>IRR</b></DIV></td>"); //added by Jithendra 28-04-2017
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Branch</b></DIV></td>"); // added by udara 10-07-2017
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Item Category</b></DIV></td>"); // added by udara 10-07-2017
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Item Sub Category</b></DIV></td>"); // added by udara 10-07-2017
						
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>CR Book Loan Id</b></DIV></td>"); // added by Jithendra 24-04-2019 JB25092018-05225
						//out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>CR Book Bank</b></DIV></td>"); // added by Jithendra 24-04-2019 JB25092018-05225
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>CR Book Bank/Branch</b></DIV></td>"); // added by Jithendra 24-04-2019 JB25092018-05225
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Moratorium</b></DIV></td>"); // added by udara 13-08-2020
						out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Future Debits</b></DIV>"); // added by udara 07-01-2021
						
						String m_sql_string = "";
						
						if (m_client_code.equals("")){
							
							/*
							rs1= stmt1.executeQuery(" SELECT X.CLIENT_CODE,"+
								" X.FINANCE_NO,X.APPLICATION_NO,"+
								" X.ENT_DATE,"+
								" NVL(FUTURE_RECEIVABLE,0),"+
								" NA_AMOUNT,"+
								" NVL(UNEARNED_INCOME,0),"+
								" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(X.CLIENT_CODE),'-') ,"+
								" NVL(NIBSM,0),"+ //added by nuwan de silva on 16-12-2008
								" NVL(AMI,0),"+  //added by nuwan de silva on 16-12-2008
								" NVL(MAINTAINCE,0),"+  //added by nuwan de silva on 16-12-2008
								" "+m_schema_name+".AF_CO_GET_APP_STATUS(X.APPLICATION_NO), "+//added by sandun on 14-05-2009
								" NVL(TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'),'-') "+ //Added By Sandun on 21-05-2009
								" ,"+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(Y.TRANSACTION_TYPE) TRANSACTION_TYPE,   "+ //added by ns on 29-06-2011
								" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(X.APPLICATION_NO),0) "+//ADDED MIILNDA 2014-07-18
								" ,IRR "+//16//ADDED JITHENDRA 28-04-2017
								" ,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(X.BRANCH_CODE),'-')  "+ // 17 added by udara 10-07-2017
								" FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_MON X , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
								" WHERE X.FINANCE_NO=Y.FINANCE_NO "+
								" AND REPORT_DATE=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))"+//Added by Jithendra 03-07-2017
								//" AND   USER_NAME='"+m_username+"' ORDER BY X.FINANCE_NO");
								"  ORDER BY X.FINANCE_NO");
								*/
							
							m_sql_string = m_sql_string + " SELECT X.CLIENT_CODE,"+
								" X.FINANCE_NO,X.APPLICATION_NO,"+
								" X.ENT_DATE,"+
								" NVL(FUTURE_RECEIVABLE,0),"+
								" NA_AMOUNT,"+
								" NVL(UNEARNED_INCOME,0),"+
								" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(X.CLIENT_CODE),'-') ,"+
								" NVL(NIBSM,0),"+ //added by nuwan de silva on 16-12-2008
								" NVL(AMI,0),"+  //added by nuwan de silva on 16-12-2008
								" NVL(MAINTAINCE,0),"+  //added by nuwan de silva on 16-12-2008
								" "+m_schema_name+".AF_CO_GET_APP_STATUS(X.APPLICATION_NO), "+//added by sandun on 14-05-2009
								" NVL(TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'),'-') "+ //Added By Sandun on 21-05-2009
								" ,"+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(Y.TRANSACTION_TYPE) TRANSACTION_TYPE,   "+ //added by ns on 29-06-2011
								//" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(X.APPLICATION_NO),0) "+//ADDED MIILNDA 2014-07-18
								" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(NVL(Y.pre_application_no,Y.APPLICATION_NO)),0) "+//ADDED UDARA 10-07-2018
								" ,IRR "+//16//ADDED JITHENDRA 28-04-2017
								" ,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(X.BRANCH_CODE),'-')  "+ // 17 added by udara 10-07-2017
								" ,TO_CHAR(Y.ACTIVATED_DATE,'DD-Mon-YYYY') ACTIVATION_DATE "+
								" ,NVL(X.ITEM_CATEGORY,'-')  "+     // 19 added by udara 14-08-2017
								" ,NVL(X.ITEM_SUB_CATEGORY,'-')  "+ // 20 added by udara 14-08-2017
								
								" ,NVL(CR_BOOK_BANK,'-') CR_BOOK_BANK "+// added by Jithendra 24-04-2019 JB25092018-05225
                                " ,NVL(CR_BOOK_BRANCH,'-') CR_BOOK_BRANCH  "+// added by Jithendra 24-04-2019 JB25092018-05225
                                " ,NVL(CR_BOOK_LOAN,'-') CR_BOOK_LOAN  "+// added by Jithendra 24-04-2019 JB25092018-05225
								" ,NVL("+m_schema_name+".AF_CO_GET_MORA_FLAG_STATUS(X.FINANCE_NO),'-') MORATORIUM_STATUS "+ // added by udara 13-08-2020
								" ,NVL(X.FUTURE_DEBITS,0) FUTURE_DEBITS "+ // added by udara 07-01-2021
								" FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_MON X , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
								" WHERE X.FINANCE_NO=Y.FINANCE_NO "+
								//" AND REPORT_DATE=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))";// commented by udara 21-06-2019 //Added by Jithendra 03-07-2017
								" AND REPORT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY')"; // added by udara 21-06-2019
								//" AND   USER_NAME='"+m_username+"' ORDER BY X.FINANCE_NO");
								
								m_sql_string = m_sql_string + m_branch_sql;
								// added by udara 11-10-2018
								if(!(m_product_name.equals("NOT_SELECT"))){    
									m_sql_string = m_sql_string +"  AND  X.TRANSACTION_TYPE = '"+m_product_name+"' ";    
								}
								// end by udara 11-10-2018
								m_sql_string = m_sql_string  + " ORDER BY X.FINANCE_NO ";

							
						}
						else{
							/*
							rs1= stmt1.executeQuery(" SELECT X.CLIENT_CODE,"+
								" X.FINANCE_NO,X.APPLICATION_NO,"+
								" X.ENT_DATE,"+
								" NVL(FUTURE_RECEIVABLE,0),"+
								" NA_AMOUNT,"+
								" NVL(UNEARNED_INCOME,0),"+
								" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(X.CLIENT_CODE),'-') ,"+
								" NVL(NIBSM,0),"+ //added by nuwan de silva on 16-12-2008
								" NVL(AMI,0) ,"+  //added by nuwan de silva on 16-12-2008
								" NVL(MAINTAINCE,0),"+  //added by nuwan de silva on 16-12-2008
								" "+m_schema_name+".AF_CO_GET_APP_STATUS(X.APPLICATION_NO), "+//added by sandun on 14-05-2009
								" NVL(TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'),'-') "+ //Added By Sandun on 21-05-2009
								" ,"+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(Y.TRANSACTION_TYPE) TRANSACTION_TYPE,   "+ //added by ns on 29-06-2011
								" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(X.APPLICATION_NO),0) "+//ADDED MIILNDA 2014-07-18
								" ,IRR "+//16//ADDED JITHENDRA 28-04-2017
								" ,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(X.BRANCH_CODE),'-')  "+ // 17 added by udara 10-07-2017
								" FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_MON X , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
								" WHERE X.FINANCE_NO=Y.FINANCE_NO "+
								" AND REPORT_DATE=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))"+//Added by Jithendra 03-07-2017
								//" AND  USER_NAME='"+m_username+"'  AND X.CLIENT_CODE='"+m_client_code+"' ORDER BY X.FINANCE_NO  ");
								" AND X.CLIENT_CODE='"+m_client_code+"' ORDER BY X.FINANCE_NO  ");
							*/
							
							
							m_sql_string = m_sql_string + " SELECT X.CLIENT_CODE,"+
								" X.FINANCE_NO,X.APPLICATION_NO,"+
								" X.ENT_DATE,"+
								" NVL(FUTURE_RECEIVABLE,0),"+
								" NA_AMOUNT,"+
								" NVL(UNEARNED_INCOME,0),"+
								" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(X.CLIENT_CODE),'-') ,"+
								" NVL(NIBSM,0),"+ //added by nuwan de silva on 16-12-2008
								" NVL(AMI,0) ,"+  //added by nuwan de silva on 16-12-2008
								" NVL(MAINTAINCE,0),"+  //added by nuwan de silva on 16-12-2008
								" "+m_schema_name+".AF_CO_GET_APP_STATUS(X.APPLICATION_NO), "+//added by sandun on 14-05-2009
								" NVL(TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'),'-') "+ //Added By Sandun on 21-05-2009
								" ,"+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(Y.TRANSACTION_TYPE) TRANSACTION_TYPE,   "+ //added by ns on 29-06-2011
								//" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(X.APPLICATION_NO),0) "+//ADDED MIILNDA 2014-07-18
								" NVL("+m_schema_name+".CO_GET_RENTAL_CONTRACT(NVL(Y.pre_application_no,Y.APPLICATION_NO)),0) "+//ADDED UDARA 10-07-2018
								" ,IRR "+//16//ADDED JITHENDRA 28-04-2017
								" ,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(X.BRANCH_CODE),'-')  "+ // 17 added by udara 10-07-2017
								" ,TO_CHAR(Y.ACTIVATED_DATE,'DD-Mon-YYYY') ACTIVATION_DATE "+
								" ,NVL(X.ITEM_CATEGORY,'-')  "+     // 19 added by udara 14-08-2017
								" ,NVL(X.ITEM_SUB_CATEGORY,'-')  "+ // 20 added by udara 14-08-2017
								
								" ,NVL(CR_BOOK_BANK,'-') CR_BOOK_BANK "+// added by Jithendra 24-04-2019 JB25092018-05225
                                " ,NVL(CR_BOOK_BRANCH,'-') CR_BOOK_BRANCH  "+// added by Jithendra 24-04-2019 JB25092018-05225
                                " ,NVL(CR_BOOK_LOAN,'-') CR_BOOK_LOAN  "+// added by Jithendra 24-04-2019 JB25092018-05225
								" ,NVL("+m_schema_name+".AF_CO_GET_MORA_FLAG_STATUS(X.FINANCE_NO),'-') MORATORIUM_STATUS "+ // added by udara 13-08-2020
								" ,NVL(X.FUTURE_DEBITS,0) FUTURE_DEBITS "+ // added by udara 07-01-2021
								" FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL_MON X , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
								" WHERE X.FINANCE_NO=Y.FINANCE_NO "+
								//" AND REPORT_DATE=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))"+// commented by udara 21-06-2019 //Added by Jithendra 03-07-2017
								" AND REPORT_DATE=TO_DATE('"+m_date+"','DD-MM-YYYY') "+ // added by udara 21-06-2019
								//" AND  USER_NAME='"+m_username+"'  AND X.CLIENT_CODE='"+m_client_code+"' ORDER BY X.FINANCE_NO  ");
								" AND X.CLIENT_CODE='"+m_client_code+"' ";
							
								m_sql_string = m_sql_string + m_branch_sql;
								// added by udara 11-10-2018
								if(!(m_product_name.equals("NOT_SELECT"))){    
									m_sql_string = m_sql_string +"  AND  X.TRANSACTION_TYPE = '"+m_product_name+"' ";    
								}
								// end by udara 11-10-2018
								m_sql_string = m_sql_string  + " ORDER BY X.FINANCE_NO ";

							
							
						}	
						
						rs1= stmt1.executeQuery(m_sql_string);
						
						//out.println(m_sql_string);
						
						int j=0;
						
						while(rs1.next()){
							if(j==0){
								out.println("<tr bgcolor=\"#FFFFFF\">");
								j=1;
							}
							else{
								out.println("<tr bgcolor=\"#C0C0C0\" >");
								j=0;
							}
							count++;	
							oustanding_bal = rs1.getDouble(6);
							future_receivble = rs1.getDouble(5);
							unearned_income= rs1.getDouble(7);
							
							nibsm= rs1.getDouble(9);
							ami= rs1.getDouble(10);
							maintaince= rs1.getDouble(11);
							
							tot_oustanding_bal = tot_oustanding_bal+oustanding_bal;
							tot_future_receivble = tot_future_receivble+future_receivble;
							tot_unearned_income = tot_unearned_income+unearned_income;
							
							tot_nibsm      = tot_nibsm+nibsm;
							tot_ami        = tot_ami+ami;
							tot_maintaince = tot_maintaince+maintaince;
							tot_RENTAL += rs1.getDouble(15);
							
							tot_future_debits += rs1.getDouble("FUTURE_DEBITS"); // added by udara 07-01-2021
							
							out.println("<td width='5%' class=factoring-letter-body align=left>"+count+"</td>");//ADD BY MALIK ON 21-8-2008
							
							//out.println("<td width='10%' class=factoring-letter-body align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs1.getString(2)+"')\"><U>"+rs1.getString(2)+"</U></td>");//Modified by Dineth on 2008-12-31 // commented by udara 02-09-2015
							out.println("<td width='10%' class=factoring-letter-body align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_transaction_info('','"+rs1.getString(2)+"')\"><U>"+rs1.getString(2)+"</U></td>"); // added by udara 02-09-2015
							out.println("<td width='10%' class=factoring-letter-body align=left>"+rs1.getString(12)+"</td>");
							out.println("<td width='20%' class=factoring-letter-body align=left style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+m_client_code+"')\"><U>"+rs1.getString(8)+"</U></td>");//Modified by Dineth on 2008-12-31
							out.println("<td width='10%' class=factoring-letter-body align=left>"+rs1.getString(3)+"</td>");
							out.println("<td width='10%' class=factoring-letter-body align=left>"+rs1.getString("ACTIVATION_DATE")+"</td>"); //added by ns on 21-07-2017
							out.println("<td width='10%' class=factoring-letter-body align=left>"+rs1.getString(13)+"</td>");
							out.println("<td width='15%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='20%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='20%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='15%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(9))+"</td>");  //Added by nuwan De Silva on 16-12-2008
							out.println("<td width='15%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(10))+"</td>"); //Added by nuwan De Silva on 16-12-2008
							out.println("<td width='15%' class=factoring-letter-body align=right style= \"cursor:hand;cursor-color:blue\" onclick=\"show_maintenance_drill('"+rs1.getString(3)+"','"+m_date+"')\"><U>"+nf1.format(rs1.getDouble(11))+"</U></td>"); //Added by nuwan De Silva on 16-12-2008 Modified by Dineth on 2008-12-31
							out.println("<td width='15%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(15))+"</td>");
							out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(16))+"</td>");//ADDED JITHENDRA 28-04-2017
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(17)+"</td>"); // added by udara 10-07-2017
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(19)+"</td>"); // added by udara 14-08-2017
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(20)+"</td>"); // added by udara 14-08-2017
							
							//out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(21)+"</td>"); // added by Jithendra 24-04-2019 JB25092018-05225
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(23)+"</td>"); // added by Jithendra 24-04-2019 JB25092018-05225
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(22)+"</td>"); // added by Jithendra 24-04-2019 JB25092018-05225
							out.println("<td width='10%' class=factoring-letter-body align=right>"+rs1.getString(24)+"</td>"); // added by udara 13-08-2020
							out.println("<td width='10%' class=factoring-letter-body align=right> "+nf1.format(rs1.getDouble("FUTURE_DEBITS"))+" </td>"); // added by udara 07-01-2021
							out.println("</tr>");	
							
						}
						out.println("<tr>");
						out.println("<td width='45%' align=center colspan=7><DIV class=factoring-letter-body><b>Total<DIV></td>");
						out.println("<td width='15%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_oustanding_bal)+"<DIV></td>");
						out.println("<td width='20%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_future_receivble)+"<DIV></td>");
						out.println("<td width='20%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_unearned_income)+"<DIV></td>");
						
						out.println("<td width='15%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_nibsm)+"<DIV></td>"); //Added by nuwan De Silva on 16-12-2008
						out.println("<td width='15%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_ami)+"<DIV></td>"); //Added by nuwan De Silva on 16-12-2008
						out.println("<td width='15%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_maintaince)+"<DIV></td>"); //Added by nuwan De Silva on 16-12-2008
						out.println("<td width='15%' align=right ><DIV class=factoring-letter-body><b>"+nf1.format(tot_RENTAL)+"<DIV></td>");
						//out.println("<td width='20%' align=center colspan=2> &nbsp; </td>"); // added by udara 10-07-2017
						out.println("<td width='20%' align=center colspan=7> &nbsp; </td>"); // increased 6 to 7 by udara 13-08-2020
						out.println("<td width='20%' align=center colspan=3><b> "+nf1.format(tot_future_debits)+" </b></td>"); // added by udara 07-01-2021
						out.println("</tr>");
						
						out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
					}//Added by Dineth on 2008-12-31	
			else	
				if(m_chksql.equals("SHOW_MAINTENANCE_DRILL")){	
					String m_app_no_1=req.getParameter("app_no");
					//out.println(m_date);
					//out.println(m_app_no_1);	
					out.println("<HTML><HEAD><TITLE>Contract Balance Report</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 							
					
					out.println("<TABLE  WIDTH='100%' class='table'>");
					out.println("<TR><TD align='Center' class=table><B>Maintenance Drill Down For App No:"+m_app_no_1+"</B></TD></TR>");
					out.println("</TABLE>");
					
					out.println("<br>");
					rs1=stmt1.executeQuery(" SELECT TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),SUM(A.BAL_TO_BE_PAID) "+ 
						" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A "+
						" WHERE A.REF_NO='"+m_app_no_1+"' "+
						" AND  A.SUSPENSE_ENTRY_TYPE='INS' "+
						" AND  A.VALUE_DATE >TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" GROUP BY A.VALUE_DATE ");
					
					boolean more1=rs1.next();
					if(!more1){
						out.println("<TABLE  WIDTH='100%' class='table'>");
						out.println("<TR><TD align='Center' class=table><B>No Records</B></TD></TR>");
						out.println("</TABLE>");
					}
					else{
						out.println("<TABLE  WIDTH='40%' class='table' align='center' border=0 cellpadding=0 cellspacing=0>");
						out.println("<TR><TD WIDTH='20%' style='text-align:center'>Value Date</TD>");
						out.println("<TD WIDTH='20%' style='text-align:center'>Amount</TD>");
						out.println("</TR>");
						while(more1){
							out.println("<TR>");
							out.println("<TD>"+rs1.getString(1)+"</TD>");
							out.println("<TD style='text-align:center'>"+nf1.format(rs1.getDouble(2))+"</TD>");
							out.println("</TR>");
						}
						out.println("</TABLE>");
					}
					
					
				}//End by Dineth on 2008-12-31
			
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			/*rs1.close();
			stmt1.close();
			out.close();	
			conn.close();*/
		}	
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{		
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
