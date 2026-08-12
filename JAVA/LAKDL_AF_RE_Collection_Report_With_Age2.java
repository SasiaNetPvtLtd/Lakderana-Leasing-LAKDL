// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Collection_Report_With_Age2 extends javax.servlet.http.HttpServlet { 
	
	/*
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2,rs3,rs4;
	java.lang.Math a;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		Statement stmt=null,stmt1=null,stmt2=null,stmt3=null,stmt4=null,stmt_rental=null;
		CallableStatement callstmt1 =null;
		ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs_rental=null;
		java.lang.Math a=null;
		
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
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt_rental = conn.createStatement();
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_client_code=req.getParameter("client_code");
				String m_coll_officer=req.getParameter("coll_officer");
				String m_guarant_code=req.getParameter("guarant_code");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_COLLECTION_RPT_AGE(:1,:2,:3,:4,:5);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_client_code);
					callstmt1.setString(3,m_coll_officer);
					callstmt1.setString(4,m_guarant_code);
					callstmt1.setString(5,m_username);
					callstmt1.execute();
					
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			else if(m_chksql.equals("view_screen")){ 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report With Age</TITLE>"); 
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
				
				out.println("function load_lock(){	"); 
				//out.println("		document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=view_screen';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=view_screen';"); 
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
				out.println("		help_box.innerHTML=\" Collection Process - Collection Report with Age - \"+m_val;"); 
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
				out.println("			if(IfCount==\"3\"){"); 
				out.println("			help_value_assign_collection(oBj);"); 
				out.println("			}"); 
				out.println("    if(IfCount==\"4\"){"); 
				out.println("			help_value_assign_guarator(oBj);"); 
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
				out.println("	HelpBox('1','10','0',Crit,'ClientSql_Collection_Age','1');"); // ClientSql_Collection_Age -Added by Sandun on 17-11-2008 Instead of ClientSql
				out.println("}");
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2];");
				out.println(" document.Form1.TXT_GUARANTOR_CODE.disabled=true;");//
				out.println(" document.Form1.BUT_GUARANTOR_CODE.disabled=true;");// Added By Sandun on 08-10-2008
				out.println("}");
				
				/*out.println("function help_button_collection_officer() {"); 
				out.println(" document.Form1.hid_help_type.value='3';");
				out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.hid_save_status.value+\"@Y@\";"); 
				out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
				out.println("}"); 
				*/
				
				out.println("function help_button_collection_officer() {"); 
				out.println(" document.Form1.hid_help_type.value='3';");
				out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
				out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
				out.println("}"); 
				
				//=======================================================================================
				out.println("function help_button_guarator_code() {"); 
				out.println(" document.Form1.hid_help_type.value='4';");
				out.println(" Crit = document.Form1.TXT_GUARANTOR_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
				out.println(" HelpBox('1','10','0',Crit,'m_help_guarator_code_colection','4');");
				out.println("}");
				
				//Added By Sandun on 30-09-2008 For Guarantor Help
				
				out.println("function help_value_assign_guarator(oBj) {"); 
				out.println(" document.Form1.TXT_GUARANTOR_CODE.value=oBj.valout[2];"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.disabled=true;");//
				out.println(" document.Form1.BUT_COLLECTION_OFFICER.disabled=true;");//
				out.println(" document.Form1.CLIENT_CODE.disabled=true;");//
				out.println(" document.Form1.BUT_TXT_CLIENT_CODE.disabled=true;");//Added By Sandun on 08-10-2008
				out.println("}"); 			
				//=========================================================================================
				
				out.println("function help_value_assign_collection(oBj) {"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
				out.println(" document.Form1.TXT_GUARANTOR_CODE.disabled=true;");//
				out.println(" document.Form1.BUT_GUARANTOR_CODE.disabled=true;");//Added By Sandun on 08-10-2008
				out.println("}"); 
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=run_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&guarant_code=\"+document.Form1.TXT_GUARANTOR_CODE.value+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value;"); 
				//out.println("alert(m_url);");
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
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=print_report&date=\"+m_date+\" \";"); 
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Report with Age</td>"); 
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
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
				out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
				out.println("</td>");
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
				out.println("</td>"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='50' style='{width=150px}' size='50' onblur=\"client_help()\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_COLLECTION_OFFICER'  class=div_input>Collection Officer </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_COLLECTION_OFFICER' maxlength='10' style='{width=150px}' size='10' onblur=\"help_button_collection_officer()\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_COLLECTION_OFFICER' value=\"Help\" onClick=\"help_button_collection_officer()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr >"); //Added By Sandun on 30-09-2008
				out.println("<td width='20%' ><DIV id='DIV_TXT_GUARANTOR_CODE'  class=div_input>Guarantor Code </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_GUARANTOR_CODE' maxlength='10' style='{width=150px}' size='10' onblur=\"help_button_guarator_code()\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_GUARANTOR_CODE' value=\"Help\" onClick=\"help_button_guarator_code()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
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
			}
			else if(m_chksql.equals("print_report")){
				
				String m_date=req.getParameter("date");
				
				out.println("<HTML><HEAD><TITLE>Collection Process - Collection Report with Age</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=transaction_history&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_other_charges_detail(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=OTHER_CHARGES_DETAIL&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_vehile_detail(val_1){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=all_vehicles&finance_no='+val_1;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_all_vehicle_no(val1,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=vehicle_no&application_no='+val1+'&finance_no='+val2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function update_contract_detail(val_1,val_2){ ");  //Added By Sandun on 19-11-2008
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=update_contact_detail&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Collection Process - Collection Report with Age</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table width='70%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Finance No<DIV></td>");//2
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Client Name</b></DIV></td>");//3
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Collector Name</b></DIV></td>");//7
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Insurance Officer</b></DIV></td>");// added by nuwan de silva 11-03-2009
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Contract Status</b></DIV></td>");//7
				//out.println("<td width='10%' ><DIV class=factoring-letter-body><b></b></DIV></td>");//7  // Commented By Sandun on 17-11-2008 (Shift as end columns)
				//out.println("<td width='10%' ><DIV class=factoring-letter-body><b></b></DIV></td>");//7
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Total Outstanding</b></DIV></td>"); //added by nuwan 
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Pending Receipts Client </b></DIV></td>"); //added by nuwan 
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Pending Receipts Contract</b></DIV></td>");//36
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Net Balance</b></DIV></td>"); //added by nuwan 
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Arreas Amt</b></DIV></td>");//11
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Arreas Age</b></DIV></td>");//10
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Total Rental</b></DIV></td>");//31
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Settl Rental</b></DIV></td>");//32
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>ODI Amt</b></DIV></td>");//12
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>ODI Settle Amt</b></DIV></td>");//30
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Other Charges Amt</b></DIV></td>");//15
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Future Receivable</b></DIV></td>");//14
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 0 or Less</b></DIV></td>");//19
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 1</b></DIV></td>");//20
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 2</b></DIV></td>");//21
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 3</b></DIV></td>");//22
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 4</b></DIV></td>");//23
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 5</b></DIV></td>");//24
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 6</b></DIV></td>");//25
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 7-9</b></DIV></td>");//26
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 10-13</b></DIV></td>");//27
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 14-18</b></DIV></td>");//28
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Age 18></b></DIV></td>");//30
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>NIBSM</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Chq. Returns</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Vehicle No.</b></DIV></td>");//added by Sandun on 23-10-2008
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Last Pay Details</b></DIV></td>");				
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>ML Number</b></DIV></td>");//add by malik on 25-8-2008
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Asset Details<DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Address<DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Tele. No<DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Con Person<DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b></b></DIV></td>");//7
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b></b></DIV></td>");//7 
				out.println("</tr>");
				
				rs1= stmt1.executeQuery(" SELECT APPLICATION_NO,  "+//1
					" FINANCE_NO,  "+//2
					" CLIENT_CODE, "+//3
					" CLIENT_FULL_NAME, "+//4
					" CLIENT_ADDRESS,  "+//5
					" CLIENT_TEL_NO, "+//6
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(CLIENT_COLLECTOR),'-'), "+//7
					" NVL(CLIENT_CONT_PERSON,'-'),  "+//8
					" NVL(CLIENT_ASSET,'-'), "+//9
					" AGE_ARREAS,  "+//10
					" AMOUNT_ARREAS, "+//11
					" ODI_ARREAS_AMT, "+//12
					" CHEQ_RETURN_AMT,  "+//13
					" FUTURE_RECEIVABLES,  "+//14
					" OTHER_CHARGES, "+//15
					" PDC_AMT,  "+//16
					" NIBSM_AMT,  "+//17
					" NVL(LAST_PAY_DETAILS,'-'),  "+//18
					" ARREAS_AGE_0, "+//19
					" ARREAS_AGE_1, "+//20
					" ARREAS_AGE_2, "+//21
					" ARREAS_AGE_3, "+//22
					" ARREAS_AGE_4, "+//23
					" ARREAS_AGE_5, "+//24
					" ARREAS_AGE_6, "+//25
					" ARREAS_AGE_7, "+//26
					" ARREAS_AGE_8, "+//27
					" ARREAS_AGE_9, "+//28
					" ARREAS_AGE_99, "+//29
					" UNALLO_RECEIPTS, "+//30
					" ODI_SETTLED,"+//31
					" RENTAL_TOTAL,"+//32
					" RENTAL_SETTLE, "+//33
					" (NVL(AMOUNT_ARREAS,0)+NVL(OTHER_CHARGES,0)) , "+ //34 //+NVL(ARREAS_AGE_0,0)
					" ((NVL(AMOUNT_ARREAS,0)+NVL(OTHER_CHARGES,0)) - NVL(UNALLO_RECEIPTS,0) ) ,"+ //35 //+NVL(ARREAS_AGE_0,0)
					" NVL(UNALLO_REC_CONTRACT,0) "+ //36
					" ,NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(INSURANCE_OFFICER),'-') "+//37
					" FROM "+m_schema_name+".AF_RE_TBD_COLLECTION_RPT_AGE "+
					" WHERE UPPER(ENT_USER)=UPPER('"+m_username+"') "+
					" ORDER BY CLIENT_CODE,FINANCE_NO ");
				
				
				
				
				rs2=stmt2.executeQuery("SELECT AGREEMENT_NO FROM "+m_schema_name+".af_co_mas_master_agreement_det a where UPPER(ENT_USER)=UPPER('"+m_username+"')");
				int cnt = 0;
				int j=1;
				String vehicle_no = "";
				String m_con_status = "";
				while(rs1.next()){
					rs4 = stmt4.executeQuery(" SELECT COUNT(A.REG_NO),A.APPLICATION_NO "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
						" WHERE A.APPLICATION_NO = '"+rs1.getString(1)+"' "+
						" GROUP BY A.APPLICATION_NO ");
					
					
					if(rs4.next()){
						cnt = rs4.getInt(1);
					}
					
					
					
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					rs3=stmt3.executeQuery(" SELECT A.INVOICE_NO, A.APPLICATION_NO, "+
						" NVL(A.REG_NO,'-') "+//3
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+
						"    	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
						" AND B.APPLICATION_NO = '"+rs1.getString(1)+"' ");
					while(rs3.next()){
						vehicle_no = rs3.getString(3);
					}	
					
					
					rs4=stmt4.executeQuery(" SELECT "+
						//" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-') "+//Added by Dineth on 29-01-2009
						" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
						//" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED','Activated','LEGAL','Legal','TERMI','Terminated','APPLICATION_STATUS'),'-') "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
						" WHERE FINANCE_NO = '"+rs1.getString(2)+"' ");
					
					if(rs4.next()){
						m_con_status = rs4.getString(1);
					}
					
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"show_transaction_history_new('"+rs1.getString(3)+"','"+rs1.getString(2)+"')\">"+rs1.getString(2)+"</td>");// show_finance_detail_drill('"+rs1.getString(2)+"') //Modified By Sandun 0n 29.09.2008
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"show_client('"+rs1.getString(3)+"')\">"+rs1.getString(4)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body >"+rs1.getString(7)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body >"+rs1.getString(37)+"</td>"); // added by nuwan de silva 11-03-2009
					out.println("<td width='20%' class=factoring-letter-body >"+m_con_status+"</td>");
					
					//out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"add_client_comments('"+rs1.getString(3)+"','"+rs1.getString(1)+"')\"><u>Remarks</u></td>"); // Commented By Sandun on 17-11-2008 (Shift as end columns)
					//out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"show_followup('"+rs1.getString(2)+"')\"><u>Follow up</u></td>");
					//out.println("<td width='10%' class=factoring-letter-body align=right style=cursor:hand onClick=\"show_transaction_history('"+rs1.getString(3)+"')\">"+nf.format(rs1.getDouble(11))+"</td>");
					//out.println("<td width='10%' class=factoring-letter-body align=right style=cursor:hand onClick=\"show_transaction_history('"+rs1.getString(3)+"')\">"+nf1.format(rs1.getDouble(10))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(34))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(30))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(36))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(35))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right style=cursor:hand onClick=\"show_transaction_history_new('"+rs1.getString(3)+"','"+rs1.getString(2)+"')\">"+nf.format(rs1.getDouble(11))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right style=cursor:hand onClick=\"show_transaction_history_new('"+rs1.getString(3)+"','"+rs1.getString(2)+"')\">"+nf1.format(rs1.getDouble(10))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(32))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(33))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(12))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf1.format(rs1.getDouble(31))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right style=cursor:hand onClick=\"show_other_charges_detail('"+rs1.getString(3)+"','"+rs1.getString(2)+"')\" >"+nf.format(rs1.getDouble(15))+"</td>"); //other cnarges
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(14))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(19))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(20))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(21))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(22))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(23))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(24))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(25))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(26))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(27))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(28))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(29))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(17))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(13))+"</td>");
					if(cnt > 1){
						out.println("<td width='20%' class=factoring-letter-body style=cursor:hand  title ='"+cnt+" Vehicles' onClick=\"show_all_vehicle_no('"+rs1.getString(1)+"','"+rs1.getString(2)+"')\">"+vehicle_no+"</td>");
					}
					else{
						out.println("<td width='20%' class=factoring-letter-body style=cursor:hand  title ='"+cnt+" Vehicle' >"+vehicle_no+"</td>");
					}
					out.println("<td width='20%' class=factoring-letter-body >"+rs1.getString(18)+"</td>");
					///*			
					if(rs2.next())
					{
						out.println("<td width='10%' class=factoring-letter-body align=right>"+rs2.getString(1)+"</td>");
						
					}
					else
					{
						out.println("<td width='10%' class=factoring-letter-body align=right>&nbsp;</td>");
						
					}
					//	*/ add by malik on 22-8-2008
					
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"show_asset('"+rs1.getString(1)+"')\">"+rs1.getString(9)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body >"+rs1.getString(5)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"update_contract_detail('"+rs1.getString(3)+"','"+rs1.getString(1)+"')\">"+rs1.getString(6)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body >"+rs1.getString(8)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"add_client_comments('"+rs1.getString(3)+"','"+rs1.getString(1)+"')\"><u>Remarks</u></td>");
					out.println("<td width='20%' class=factoring-letter-body style=cursor:hand onClick=\"show_followup('"+rs1.getString(2)+"')\"><u>Follow up</u></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}	
			else if(m_chksql.equals("transaction_history")){
				
				String m_client_code=req.getParameter("client_code");
				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Transaction History</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				rs=stmt1.executeQuery(" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,STYPE "+
					" FROM "+
					" ( "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" VALUE_DATE   DUE_DATE, "+
					" TOTAL_AMOUNT AMOUNT, "+
					" 'INVOICE' TYP, "+
					" NVL(NULL,'-') CHEQUE_NO, "+
					" DECODE(INVOICE_TYPE,'INV_OTHER','Other Invoice','INV_TAX','Tax Invoice','ODI','OD Interest','TERM_ODI','OD Interest','INV_RESI','Residual Invoice','INV_GENER','RENTAL & VAT') DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					//" FINANCE_NO='"+m_finance_no+"' AND "+
					" ACTIVE_STATUS='Y' "+
					" UNION "+
					
					" SELECT "+
					" REC_NO REF_NO, "+ //1
					" EFF_VALDATE DUE_DATE, "+ //2
					" REC_AMOUNT AMOUNT, "+ //3
					" 'RECEIPT' TYP, "+ //4
					" NVL(CHEQUE_NO,'-')  CHEQUE_NO ,"+ //5
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Receipt - Chq Return','Receipt - Cheque'),'CASH','Receipt - Cash') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION, "+ //6 //ADDED BY NS 01-10-2009
					" STATUS, "+ //7
					" 'CR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND STATUS <> 'C' "+
					
					
					
					"	UNION "+
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					//" C.ALLOCATED_DATE DUE_DATE, "+
					" A.DUE_DATE  DUE_DATE, "+
					//" C.SETTELED_AMOUNT AMOUNT, "+
					" A.ODI_SETTLED_AMOUNT AMOUNT, "+
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'Over Due Interst' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE A.ODI_SETTLED_AMOUNT > 0 "+
					" AND A.INVOICE_NO=B.INVOICE_NO "+
					//" AND A.INVOICE_NO=C.ODI_REF_NO "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" UNION "+
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" A.ADJUSTED_DATE DUE_DATE, "+
					" A.ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(A.CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" A.CREDIT_TYPE STATUS, "+
					" A.CREDIT_TYPE STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO=B.INVOICE_NO "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" UNION "+
					"	SELECT    "+
					"	RETURN_NO REF_NO,   "+
					"	EFF_VALDATE DUE_DATE,  "+
					"	NVL(RETURN_CHARGE,0)  AMOUNT,   "+
					"	'RET_CHARGE' TYP,  "+
					"	CHEQUE_NO  CHEQUE_NO ,  "+
					"	'Return Cheque Charges'  DESCRIPTION,    "+
					"	STATUS,   "+
					" 'DR' STYPE "+
					"	FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
					"	WHERE A.REC_NO=B.RECEIPT_NO "+
					"	AND CLIENT_CODE='"+m_client_code+"'   "+
					"	AND STATUS='RET' "+
					" ) "+ 
					" ORDER BY DUE_DATE ");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='*%'align='center' class=div_input><b>Asset Finance Ledger</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='10%' class=div_input>Date</td>");
				out.println("<td width='15%' class=div_input>Doc Ref</td>");
				out.println("<td width='30%' class=div_input>Narration</td>");
				out.println("<td width='10%' class=div_input>Cheque No.</td>");
				out.println("<td width='10%' class=div_input align='right'>Debit</td>");
				out.println("<td width='10%' class=div_input align='right'>Credit</td>");
				out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
				out.println("<td width='4%' class=div_input></td>");
				out.println("</tr>");
				
				while(rs.next()){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					if(rs.getString(4).equals("INVOICE")){
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					}
					else if(rs.getString(4).equals("RECEIPT")){;
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					}
					else if(rs.getString(4).equals("RET_CHARGE")){
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					}
					else{
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					}
					
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					
					if(rs.getString(8).equals("CR")){
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						m_cum_value=m_cum_value+rs.getDouble(3);
					}
					else{
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						m_cum_value=m_cum_value-rs.getDouble(3);
					}
					
					if(m_cum_value>0){
						out.println("<td width='10%' class=div_input align='right'>"+nf.format((m_cum_value))+"</td>");
						out.println("<td width='4%' class=div_input align='right'>CR</td>");
					}
					else if(m_cum_value==0){
						out.println("<td width='10%' class=div_input align='right'>"+nf.format((m_cum_value))+"</td>");
						out.println("<td width='4%' class=div_input align='right'></td>");
					}
					else{
						out.println("<td width='10%' class=div_input align='right'>"+nf.format((m_cum_value*-1))+"</td>");
						out.println("<td width='4%' class=div_input align='right'></td>");
					}
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
				rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY'),COMMENTS,ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
					" WHERE CLIENT_CODE ='"+m_client_code+"'");
				
				
				boolean  more4 =rs.next();			
				
				if(more4){
					out.println("<table align='center' width='100%' class='table' >");
					while(more4){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input valign='top'><B>Date: </B>"+rs.getString(1)+" &nbsp <B>User: </B>"+rs.getString(3)+"</td>");
						out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more4 =rs.next();
					}
					out.println("</table>");
				}
				else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<br>");
				out.println("<br>");
				out.println("<table></table>");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Collection</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
			}
			else if(m_chksql.equals("OTHER_CHARGES_DETAIL")){
				String m_finance_no=req.getParameter("finance_no");		
				
				out.println("<HTML><HEAD><TITLE> Other Charges - Finance No : "+m_finance_no+" </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Other Charges - Finance No : "+m_finance_no+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				String Sql_invoice= " SELECT A.INVOICE_NO , "+
					" A.INVOICE_TYPE, "+
					" A.BALANCE_TO_BE_RECEIVED, "+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),B.INVOICE_DESC) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+
					"      "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
					" WHERE A.INVOICE_TYPE=B.INVOICE_TYPE_CODE "+
					" AND  BALANCE_TO_BE_RECEIVED > 0 "+
					" AND INVOICE_TYPE <>'INV_GENER' "+
					" AND FINANCE_NO = '"+m_finance_no+"' ";
				
				rs=stmt1.executeQuery(Sql_invoice);
				boolean  more_inv =rs.next();
				double sum=0;
				out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
				out.println("<tr class=pdn_txtpos2>");
				//out.println("<td width='1%'></td>"); 
				out.println("<td width='10%' class=div_input>Invoice No</td>");
				out.println("<td width='15%' class=div_input>Invoice Type</td>");
				out.println("<td width='30%' align='right' class=div_input>Amount</td>");
				out.println("</tr>");
				
				while(more_inv){
					out.println("<tr >");
					//out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='30%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("</tr>");
					sum+=rs.getDouble(3);
					more_inv =rs.next();
				}
				
				out.println("<tr >");
				//out.println("<td width='1%'></td>"); 
				out.println("<td width='10%' class=div_input>&nbsp;</td>");
				out.println("<td width='15%' class=div_input><b>Total</td>");
				out.println("<td width='30%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				
			}
			
			else if(m_chksql.equals("vehicle_no")){ // Added by Sandun on 03-10-2008
				
				String m_finance_no=req.getParameter("finance_no");	
				String m_application_no=req.getParameter("application_no");
				
				
				out.println("<HTML><HEAD><TITLE>Vehicle No</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Vehicle No</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				String Sql_Vehicle= " SELECT DISTINCT A.APPLICATION_NO ,A.REG_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A  ,"+
					" "+m_schema_name+".AF_RE_TBD_COLLECTION_RPT_AGE B		"+											
					"	WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
					" AND B.FINANCE_NO ='"+m_finance_no+"' "+
					" AND B.ENT_USER ='"+m_username+"'	";								
				
				rs=stmt1.executeQuery(Sql_Vehicle);
				boolean  more =rs.next();
				int count=1;
				out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='1%'>No.</td>");					
				out.println("<td width='10%' class=div_input>Finance No</td>");
				out.println("<td width='15%' class=div_input>Registration No</td>");					
				out.println("</tr>");
				
				while(more){
					out.println("<tr >");
					out.println("<td width='1%'>"+count+"</td>"); 
					out.println("<td width='15%' class=div_input>"+m_finance_no+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("</tr>");
					more =rs.next();
					count = count+1;
				}
				
				out.println("</table>");
				
				
			}
			else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_BY_CONTRACT")){
				
				
				String m_finance_no=req.getParameter("finance_no");		
				String m_client_code=req.getParameter("client_code");		
				
				
				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				double m_cummulative_ins=0;
				double m_cum_value_ren =0;
				boolean mm_flag_insurance=false;
				String mm_insurance_drcr="";
				String mm_rental_drcr ="";
				String mm_row_color ="#000000";
				
				boolean mm_flag_ins_rental=false; //#23772 Inesh 2017-08-01
				boolean mm_flag_ins_rental_cancel=false; //#23772 Inesh 2017-08-03
				double mm_rec_rental =0;
				double mm_rec_insurence =0;
				
				String m_app_status = "";
				String m_trn_dec    = "";
				String m_client_name= "";
				String m_cli_add    = "";
				String m_app_no     = "";
				String m_trn_type   = "";
				String m_agr_date   = "";
				String m_ter_type   = "";
				String m_application_no="";
				String m_perform_status = ""; // added by udara 28-07-2015
				
				int m_termi_count=0;
				int m_rental_count=0;
				
				double m_tot_agree=0;
				double m_capitl=0;
				double m_interest=0;
				double m_vat=0;
				
				double m_int_tate=0;
				double mm_NET_RENTAL_AMOUNT=0;
				String mm_RENTAL_DATE="";
				String app_status="";
				String mm_VEHICLE_NO="";
				String mm_MATURITY_DATE="";
				
				String mm_SCORE_MODEL_CODE="";
				String mm_FINAL_APP_SCORE="";
				String mm_MODEL_SCORE="";
				String mm_COMMENTS="";
				
				
				
				rs1=stmt1.executeQuery(
					//out.println(
					" SELECT DISTINCT A.CLIENT_CODE, "+//1
					//" "+m_schema_name+".AF_CO_GET_APP_STATUS(A.APPLICATION_NO), "+//2
					" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO)), "+ /*'Normal Termination Pending' added By Chandana on 19/10/2009 For SR/20091014/069 */	
					" C.DESCRIPTION, "+//3
					" B.FULL_NAME, "+//4
					//	" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||B.TEL_NO ||'/'||B.MOBILE_NO,  "+//5 // Comment Amila 2016-07-05 
					" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'-')||','||B.TEL_NO ||'/'||B.MOBILE_NO,  "+//5 // Add by Amila 2016-07-20  Addres load to city name
					//    " B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name
					//" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_CODE(B.CITY_CODE)),'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name  
					//	" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(B.CITY_CODE,'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name 
					" A.APPLICATION_NO, "+//6
					" A.TRANSACTION_TYPE, "+//7
					" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//8
					" "+m_schema_name+".AF_CO_GET_CONTRCT_INTERST_RATE(A.FINANCE_NO) FINANCE_NO, "+  //9
					" DECODE(NVL(STATUS,'PERFORM'),'PERFORM','Perform','NPERFORM','Non Perform',NVL(STATUS,'PERFORM')) "+ // added by udara 28-07-2015
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE C "+
					" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
					" AND   A.TRANSACTION_TYPE=C.TRAN_CODE "+
					" AND A.FINANCE_NO = '"+m_finance_no+"' ");
				
				
				if(rs1.next()){
					m_app_status   = rs1.getString(2);
					m_trn_dec      = rs1.getString(3);
					m_client_name  = rs1.getString(4);
					m_cli_add      = rs1.getString(5);
					m_app_no       = rs1.getString(6);
					m_trn_type     = rs1.getString(7);
					m_agr_date     = rs1.getString(8);
					m_int_tate     = rs1.getDouble(9);
					m_application_no=rs1.getString("APPLICATION_NO");
					m_perform_status = rs1.getString(10); // added by udara 28-07-2015
					//out.println("m_cli_add ="+m_cli_add);
				}
				
				
				
				
				
				rs2=stmt2.executeQuery(
					//out.println(
					" SELECT SUM(B.NET_PRICE), "+//1
					" SUM(A.INTEREST_AMOUNT), "+//2
					" 0 , "+//3
					" COUNT(*) "+	//4
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
					" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
					" AND A.PRICING_NO       = B.PRICING_NO "+
					" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
					" AND B.ACTIVE_STATUS    IN ('T','Y')");		
				
				
				if(rs2.next()){
					m_rental_count= rs2.getInt(4);
					m_capitl      = rs2.getDouble(1)/m_rental_count;
					m_interest    = rs2.getDouble(2);
					m_vat         = rs2.getDouble(3);
					m_tot_agree   = m_capitl+m_interest+m_vat;
					
				}
				
				// added by udara 28-11-2013
				int app_count = 0;
				
				rs2=stmt2.executeQuery(
					" SELECT COUNT(APPLICATION_NO) FROM(	"+
					" SELECT A.APPLICATION_NO APPLICATION_NO"+//1
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
					" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
					" AND A.PRICING_NO       = B.PRICING_NO "+
					" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
					" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
					" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
					" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
					" )");
				
				if(rs2.next()){
					app_count = rs2.getInt(1);
				}
				
				//out.println("aaaaaaaaaaaaaaa" + app_count);
				
				
				// end by udara 28-11-2013
				
				// commented by udara 28-11-2013
				/*
				rs2=stmt2.executeQuery(
				//out.println(
				" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
				" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
				" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
				" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
				" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
				" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
				" AND A.PRICING_NO       = B.PRICING_NO "+
				" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
				" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
				" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
				" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
				" ");
				*/
				
				// added by udara 28-11-2013
				if(app_count>0){
					rs2=stmt2.executeQuery(
						//out.println(
						" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
						" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
						" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
						" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
						" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
						" AND A.PRICING_NO       = B.PRICING_NO "+
						" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
						" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
						" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
						" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
						" ");
				}
				else{
					rs2=stmt2.executeQuery(
						//out.println(
						" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
						" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
						" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
						" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
						" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
						" AND A.PRICING_NO       = B.PRICING_NO "+
						" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
						" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
						" AND ROWNUM= 1 "+
						" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
						" ");
				}
				
				
				// end by udara 28-11-2013
				
				if(rs2.next()){
					mm_NET_RENTAL_AMOUNT      = rs2.getDouble("NET_RENTAL_AMOUNT");
					//mm_RENTAL_DATE            = rs2.getString("RENTAL_DATE");
					mm_VEHICLE_NO             = rs2.getString("VEHICLE_NO");
					mm_MATURITY_DATE          = rs2.getString("MATURITY_DATE");
					
					
				}
				
				// commented by udara on 08-08-2013
				/*
				rs2=stmt2.executeQuery(
				
				" SELECT   APPLICATION_NO,TO_CHAR(RENTAL_DATE,'DD') DUE_DATE "+
				"            FROM     "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
				"            WHERE    (APPLICATION_NO,RENTAL_DATE) IN  "+
				"            ( "+
				"            SELECT   APPLICATION_NO,MAX(RENTAL_DATE) RENTAL_DATE "+
				"            FROM     "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
				"            WHERE   (PRO_INVOICE_NO,APPLICATION_NO,PRICING_NO) IN ( SELECT   INVOICE_NO, APPLICATION_NO, PRICING_NO  "+
				"                                                                    FROM     "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				//"                                                                    WHERE    ACTIVE_STATUS IN ('Y') "+ // commented by udara on 04-07-2013
				"                                                                    WHERE    ACTIVE_STATUS IN ('Y','T') "+ // added by udara on 04-07-2013
				" 																	  AND      APPLICATION_NO='"+m_app_no+"' "+
				"                                                                    GROUP BY INVOICE_NO, APPLICATION_NO, PRICING_NO  "+
				"                                                                  ) "+
				"                                                                  "+
				"            AND       RENTAL_DATE < TRUNC(SYSDATE,'DD') +1 "+
				"            GROUP BY APPLICATION_NO       "+
				"            ) "+
				" 			  AND      APPLICATION_NO ='"+m_app_no+"' "+
				"            GROUP BY APPLICATION_NO ,RENTAL_DATE  "+
				" ");
				
				if(rs2.next()){
				mm_RENTAL_DATE            = rs2.getString("DUE_DATE");
				}
				*/
				
				// added by udara on 08-08-2013
				//Added by Kanchana on 2016-07-21
				rs3=stmt3.executeQuery("SELECT APPLICATION_STATUS FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO = '"+m_app_no+"' AND APPLICATION_STATUS IN ('ENTERED','VERIFY1','V-APP','V-RECOM','VERIFY-M','VERIFY2','VERIFY-M','VERIFY2','ENT_CON') ");
				
				
				
				if(rs3.next()){//Added by Kanchana on 2016-07-21
					rs2=stmt2.executeQuery( " "+
						" SELECT  NVL(TO_CHAR("+m_schema_name+".AF_CR_GET_NEXT_PAYMENT_DATE(ADD_MONTHS(SYSDATE,1)),'DD'),'') NEXT_DATE_DD "+
						" FROM DUAL "+
						" ");
					
					if(rs2.next()){
						mm_RENTAL_DATE            = rs2.getString("NEXT_DATE_DD");
					}
					
				}else{//Added by Kanchana on 2016-07-21
					
					rs2=stmt2.executeQuery( " "+
						" SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
						" ");
					
					if(rs2.next()){
						mm_RENTAL_DATE            = rs2.getString("DUE_DATE");
					}
					// added by udara on 08-08-2013
					
				}//Added by Kanchana on 2016-07-21
				
				rs2=stmt2.executeQuery( " "+
					" SELECT APPLICATION_CODE, "+
					" SCORE_MODEL_CODE, "+
					" FINAL_APP_SCORE, "+
					" MODEL_SCORE, "+
					" COMMENTS "+
					" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE  "+
					" WHERE APPLICATION_CODE = '"+m_app_no+"' "+
					" ");
				
				if(rs2.next()){
					mm_SCORE_MODEL_CODE            = rs2.getString(2);
					mm_FINAL_APP_SCORE             = rs2.getString(3);
					mm_MODEL_SCORE                 = rs2.getString(4);
					mm_COMMENTS                    = rs2.getString(5);
				}
				
				/*	out.println("mm_SCORE_MODEL_CODE "+mm_SCORE_MODEL_CODE);
				out.println("mm_FINAL_APP_SCORE  "+mm_FINAL_APP_SCORE);
				out.println("mm_MODEL_SCORE "+mm_MODEL_SCORE);
				out.println("mm_COMMENTS " +mm_COMMENTS); */
				
				
				out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : "+m_finance_no+" </TITLE>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<script type='text/javascript'>");
				out.println("function view_odi(odj){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&url=&application_no='+odj;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				
				out.println("}");
				out.println("</script>");
				out.println("</HEAD><BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				out.println("<br><br>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Transaction History - Finance No : "+m_finance_no+" </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<table class='table' width='100%'  border='0' bordercolor='grey' cellspacing='2' cellpadding='2' >");
				out.println("<tr>");
				out.println("<td class=div_input colspan='2'><b>Client Details </td>");
				out.println("<td class=div_input colspan='2'><b>Facility Details </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=div_input width='10%'><b>Name</td>");
				out.println("<td class=div_input width='40%'><b>:&nbsp;&nbsp;"+m_client_name+"</td>");
				out.println("<td class=div_input width='10%'><b>Capital</td>");
				out.println("<td class=div_input width='40%'><b>:&nbsp;&nbsp;"+nf.format(m_capitl)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=div_input ><b>Address</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_cli_add+"</td>");
				out.println("<td class=div_input ><b>No of Rental </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_rental_count+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Contract Status</td>");
				//out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_app_status+"</td>"); // commented by udara 28-07-2015
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_app_status+" - "+m_perform_status+"</td>"); 
				out.println("<td class=div_input ><b>Rental </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+nf.format(mm_NET_RENTAL_AMOUNT)+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Type of facility</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_trn_dec+"</td>");
				out.println("<td class=div_input ><b>Rental Date </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_RENTAL_DATE+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Vehicle No</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_VEHICLE_NO+"</td>");
				out.println("<td class=div_input ><b>Maturity Date </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_MATURITY_DATE+"</td>");
				out.println("</tr>");
				
				// added by udara 21-10-2014
				out.println("<tr>");
				out.println("<td class=div_input ><b>Security Details</td>");
				out.println("<td class=div_input style={cursor:hand;} onClick=\"show_run_con_det('"+m_app_no+"');\" >:&nbsp;&nbsp;<u>view</u></td>"); //comment AS
				out.println("<td class=div_input ><b>Credit Score Details</b></td>"); // mod by udara 21-08-2015
				out.println("<td class=div_input style={cursor:hand;} onClick=\"show_crdit_score_det('"+m_app_no+"','"+m_finance_no+"','"+mm_SCORE_MODEL_CODE+"','"+mm_FINAL_APP_SCORE+"','"+mm_MODEL_SCORE+"','"+mm_COMMENTS+"');\" >:&nbsp;&nbsp;<u>view</u></td>"); // added by udara 21-08-2015 // out.println("<td class=div_input > &nbsp; </td>"); ,'"+mm_SCORE_MODEL_CODE+"','"+mm_FINAL_APP_SCORE+"','"+mm_MODEL_SCORE+"','"+mm_COMMENTS+"'
				out.println("</tr>");
				
				// end by udara 21-10-2014
				
				out.println("</table>");
				
				
				
				
				
				
				String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO,ORDER_NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					//"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  DECODE(INVOICE_TYPE,'LEGAL_CAP',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),'LEGAL_ARR',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),VALUE_DATE) DUE_DATE ,"+
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					//"  NVL(NULL,'-') CHEQUE_NO, "+
					
					//" CASE  "+
					//" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN '-'   "+
					//" END CHEQUE_NO , "+
					
					/*
					// added by udara on 14-11-2012
					 " CASE "+ 
					 " WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO=INVOICE_NO) "+   
					 " WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN '-' "+   
					 " END CHEQUE_NO , "+
					// end by udara on 14-11-2012
					*/
					
					// added by udara on 26-11-2012
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN ( "+
					" NVL( "+
					" (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO=INVOICE_NO AND SUSPENSE_ENTRY_TYPE='INSURANCE' ), "+
					" (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO='"+m_application_no+"' AND SUSPENSE_ENTRY_TYPE='INSURANCE') "+ // commented by udara on 21-02-2014 // released by udara 18-03-2014
					
					// commented by udara 18-03-2014
					/*
					// added by udara on 21-02-2014
					" ( "+                            
						" SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(D.PAYEE_CODE) "+
						" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E,  "+m_schema_name+".AF_CO_PRO_INVOICE B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES D "+
						" WHERE B.INVOICE_NO LIKE A.INVOICE_NO "+
						" AND E.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
						" AND E.PRO_INVOICE_NO = C.INVOICE_NO "+
						" AND C.PRICING_NO = D.PRICING_NO "+
						" AND D.CHARGE_TYPE = 'INV'  "+
					" ) "+
					*/
					
					" ) "+
					"  )  "+  
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN "+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO)  "+ // commented by udara 17-05-2017  // modified by udara on 31-12-2012
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN "+m_schema_name+".AF_CO_GET_INST_NO_TRAN('"+m_application_no+"',INVOICE_NO)  "+ // added by udara 17-05-2017 [Commented by milinda on 09-10-2017  #JB16082017-00437]
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN "+m_schema_name+".AF_CO_GET_INST_NO_TRAN_LEDGER('"+m_application_no+"',INVOICE_NO)  "+//[Added by milinda for  #JB16082017-00437 on 09-10-2017 ]
					" END CHEQUE_NO , "+
					// end by udara on 26-11-2012
					
					//"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					
					// commented by udara 27-07-2015
					/*
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'CAN_INC')  THEN 'Abandon Fee'   "+ // added by udara 22-05-2015
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium'   "+
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE))   "+
					" END DESCRIPTION , "+
					*/
					
					// added by udara 27-07-2015
					" CASE "+  
					" WHEN (INVOICE_TYPE = 'CAN_INC')  THEN 'Abandon Fee' "+   
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium' "+  
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) "+  //[Commented by milinda on 09-10-2017  #JB16082017-00437]
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' AND "+m_schema_name+".AF_CO_GET_INST_NO_TRAN_LEDGER('"+m_application_no+"',INVOICE_NO)<> 0)  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) "+ //[Added by milinda for  #JB16082017-00437 on 09-10-2017 ]
					" WHEN (INVOICE_TYPE='INV_GENER' AND "+m_schema_name+".AF_CO_GET_INST_NO_TRAN_LEDGER('"+m_application_no+"',INVOICE_NO)=0) THEN 'Down payment' "+ //[Added by milinda for Down payment part #JB16082017-00437 on 09-10-2017 ]
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)))  "+ 
					" END DESCRIPTION, "+
					// end by udara 27-07-2015
					
					
					" NULL STATUS ,"+
					" ''   NO "+ //NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ') 
					" , '1'  ORDER_NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE A   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"        ACTIVE_STATUS='Y'  "+
					"  TOTAL_AMOUNT <> 0 AND "+
					"  ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+
					"  AND INVOICE_TYPE NOT IN ('LEGAL_ODI') "+
					" AND VALUE_DATE <=SYSDATE "+  
					
					
					//Comment by ns on 23-05-2012
					/*"  UNION ALL "+
					" SELECT "+
					" REC_NO REF_NO,  "+
					" EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') || ' Value Date ' || "+m_schema_name+".AF_CO_GET_REC_VAL_DATE(REC_NO) DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+//,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO "+
					//" A.INVOICE_NO=C.INVOICE_NO AND "+
					//" C.FINANCE_NO='"+m_finance_no+"' "+
					"	AND B.STATUS NOT IN ('RET','C','CAD') "+
					
					
					" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' "+
					"                   AND   ACTIVE_STATUS='Y' "+
					"                   UNION ALL "+
					"                   SELECT ODI_REF_NO "+
					"                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					"                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"'    )) "+ //comment by ns on 23-12-2011
					
					" AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
				    */
					
					
					"	UNION ALL "+
					
					" SELECT "+
					" REF_NO, "+
					" DUE_DATE, "+
					" AMOUNT, "+
					" TYP, "+
					" CHEQUE_NO, "+
					" DESCRIPTION, "+
					" STATUS, "+
					" STYPE, "+
					" ORDER_NO "+
					" FROM  ( "+
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" C.ALLOCATED_DATE DUE_DATE, "+//A.DUE_DATE  DUE_DATE, "+
					" SUM(C.SETTELED_AMOUNT) AMOUNT, "+//A.ODI_SETTLED_AMOUNT AMOUNT, "+ //SUM(C.SETTELED_AMOUNT)
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'Over Due Interst' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" , '2'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+//m_schema_name+".AF_CO_PRO_INVOICE B, "+
					"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE "+//A.ODI_SETTLED_AMOUNT > 0 "+
					//"     A.INVOICE_NO=B.INVOICE_NO "+
					"       C.INVOICE_NO=A.ODI_REF_NO "+
					//" AND B.CLIENT_CODE='"+m_client_code+"'  "+
					" AND SETTELED_AMOUNT <> 0 "+
					" AND A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' "+//AND "+ //commented AND  by SH on 13-12-2010
					//"          ACTIVE_STATUS='Y' "+
					//"            ACTIVE_STATUS IN ('Y','DB_CAN')  "+//commented by SH on 13-12-2010
					") "+
					" AND C.ALLOCATED_DATE <=SYSDATE "+ //changed by SH on 29-09-2009 DUE_DATE <=SYSDATE "+   
					" GROUP BY A.INVOICE_NO,C.ALLOCATED_DATE "+
					" ) "+
					" WHERE AMOUNT <> 0 "+
					
					
					" UNION ALL "+
					
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					//" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					
					" CASE  "+
					" WHEN (REMARKS = 'CREDIT NOTE FOR - INSURANCE' )  THEN 'Credit Note - Insurance Premeium'   "+
					//" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note')   "+ // commented by udara on 06-09-2013
					//" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR','Credit Note of Invoice No ' || INVOICE_NO || ' - ' || ("+m_schema_name+".AF_CO_GET_INVOICE_TYPE(INVOICE_NO,'DESCRIPT')) || ' - ' || 'Reverse','Debit Note')  "+ // added by udara on 06-09-2013 // commented by udara on 01-10-2013
					" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR',NVL('Credit Note - ' || REMARKS,'Credit Note') ,'Debit Note')   "+ // added by udara on 01-10-2013
					" END DESCRIPTION , "+
					
					" CREDIT_TYPE STATUS, "+
					" '' NO "+ //7
					" ,'3'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+	//'C' added by ns on 04/09/2015
					"      AND   INVOICE_TYPE<>'LEGAL_ODI'"+
					" ) "+
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE NOT IN ('LEGAL_AD','LEGAL_ODI','TERM_ODI','ODI') "+ //'LEGAL_ODI','TERM_ODI','ODI' added by ns on 31-05-2011
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					
					//--------------------------Sandun on 18-03-2009---------------------------------
					
					" UNION ALL"+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note - Legal Termination') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" , '4'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( "+
					" SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+					
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE = 'LEGAL_AD' "+
					" AND ADJUSTED_DATE <=SYSDATE "+ 
					
					
					" UNION ALL"+
					
					" SELECT B.INVOICE_NO REF_NO, "+
					" NVL(B.INV_REV_DATE,B.VALUE_DATE) DUE_DATE,  "+
					" B.TOTAL_AMOUNT AMOUNT,  "+
					" 'INV_REV' TYP, "+
					" '-'  CHEQUE_NO ,   "+
					//" 'Invoice Cancel '  DESCRIPTION,     "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					
					" END DESCRIPTION , "+
					
					" '-'  STATUS,   "+ 
					" 'CR' STYPE  "+
					" , '5'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE "+				  
					" B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND B.ACTIVE_STATUS = 'C' "+
					" AND (SELECT COUNT(INVOICE_NO) FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE WHERE INVOICE_NO = B.INVOICE_NO ) = 0 "+ // added by udara on 04-12-2012
					" AND VALUE_DATE <= SYSDATE "+
					
					//------------------------------------------------------------
					
					" UNION ALL "+
					
					// commented by udara 18-11-2013
					/*
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ //DISNAKA
					" b.eff_valdate   DUE_DATE ,"+ //				
					" a.APP_REC_AMOUNT ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					//Added by ns on 23-05-2012
					" CASE  "+
					" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment'   "+
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment'   "+
					" END DESCRIPTION , "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '6'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					//" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+
					*/
					
					// added by udara 18-11-2013
					" SELECT  "+
					" REF_NO, "+
					" DUE_DATE, "+
					" APP_REC_AMOUNT, "+
					" TYP, "+
					" CHEQUE_NO, "+
					" DESCRIPTION, "+
					" STATUS, "+
					" NO, "+
					" ORDER_NO  "+
					" FROM( "+
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ //DISNAKA
					" b.eff_valdate   DUE_DATE ,"+ //				
					" a.APP_REC_AMOUNT ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					//Added by ns on 23-05-2012
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment'   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					" WHEN B.RENTAL_OTER_INVOICE >0 AND B.INSURANCE >0 THEN 'Rental / Insurance Payment' "+//#23772 Inesh 2017-08-01
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges'  "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment' "+
					// end by udara 14-07-2015
					
					
					" END DESCRIPTION , "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '6'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					//" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+ 
					" ORDER BY B.ENT_DATE ASC "+
					
					" ) "+
					
					// end by udara 18-11-2013
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	NVL(B.SUB_REC_NO,B.REC_NO) REF_NO,   "+ //DISNAKA
					//"	C.ENT_DATE DUE_DATE,  "+ //ADDDE BY NUWAN DE SILVA 03-11-2008
					" B.REALISED_DATE DUE_DATE,"+
					"	NVL(A.BAL_TOBE_RECEIVE,0)  AMOUNT,   "+
					"	'RETURN_DEBIT' TYP,  "+
					"	B.CHEQUE_NO  CHEQUE_NO ,  "+
					//"	'Cheque Return '  DESCRIPTION,    "+
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment - Cheque Return '   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cheque Return'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges - Cheque Return'  "+  
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance - Cheque Return'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment' "+
					// end by udara 14-07-2015
					
					" END DESCRIPTION , "+
					
					"	B.STATUS STATUS,   "+
					" 'DR' STYPE "+
					" , '7'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" ,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS C "+
					" WHERE A.REC_NO=B.REC_NO "+
					"	AND   A.REC_NO=C.RECEIPT_NO(+) "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='RET' "+
					
					
					//ADDED BY NUWAN DE SILVA 10-08-2009
					" UNION ALL "+
					
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ // ADDED BY DISNAKA 
					" NVL(b.rec_cancel_date,b.eff_valdate)   DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'REC_CAN' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" 'Receipt Cancelation' DESCRIPTION , "+
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment - Cancelation'   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cancelation'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					//" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')  ='N' AND  B.RENTAL_OTER_INVOICE >0 AND B.INSURANCE >0 THEN 'Rental / Insurance Payment - Cancelation' "+//#23772 Inesh 2017-08-03
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges - Cancelation'  "+  
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance - Cancelation'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cancelation' "+
					// end by udara 14-07-2015
					
					" END DESCRIPTION , "+
					
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '8'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='CAD' "+
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	A.INVOICE_NO REF_NO,   "+
					"	A.TRN_DATE DUE_DATE,  "+ 
					"	NVL(A.AMOUNT,0)  AMOUNT,   "+
					"	'DEBIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					//"	'Debit Note Cancelation'  DESCRIPTION,    "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) ||'- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					" END DESCRIPTION , "+
					
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" , '9'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE A ,  "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO = B.INVOICE_NO "+
					" AND   A.FINANCE_NO='"+m_finance_no+"' "+
					
					" AND   A.TRN_DATE <=SYSDATE "+  
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	A.INVOICE_NO REF_NO,   "+
					"	A.TRN_DATE DUE_DATE,  "+ 
					"	NVL(A.ADJUSTED_AMOUNT,0)  AMOUNT,   "+
					"	'CREDIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					//"	'Credit Note Cancelation '  DESCRIPTION,    "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Credit Note - Insurance Premeium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) ||'- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  // commented by udara 02-03-2017
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN 'Credit Note - ' || NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  // added by udara 02-03-2017
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					" END DESCRIPTION , "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" , '10'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_CR_NOTE A,  "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO = B.INVOICE_NO "+
					" AND   A.FINANCE_NO=B.FINANCE_NO  "+
					" AND   A.FINANCE_NO='"+m_finance_no+"' "+
					" AND   A.TRN_DATE <=SYSDATE "+  
					
					//added by ns 10-08-2009 ------------------------
					" UNION  ALL "+
					" SELECT  "+
					" A.INVOICE_NO REF_NO , "+
					" A.ALLOCATED_DATE DUE_DATE, "+
					" SUM(A.SETTELED_AMOUNT) AMOUNT , "+
					" 'LEGAL_ODI' TYP, "+
					" '-' CHEQUE_NO, "+
					" 'Legal ODI Allocation' DESCRIPTION, "+ 
					" '' STATUS, "+
					" 'DR' STYPE "+
					" , '11'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO=B.INVOICE_NO "+
					" AND   B.FINANCE_NO='"+m_finance_no+"' "+
					" AND   B.INVOICE_TYPE='LEGAL_ODI' "+
					" AND   B.ACTIVE_STATUS='Y' "+
					" AND   A.ALLOCATED_DATE <=SYSDATE "+
					" GROUP BY A.INVOICE_NO, A.ALLOCATED_DATE "+
					//-----------------------------------------------------
					
					
					" ) "+ 
					
					//" ORDER BY DUE_DATE,ORDER_NO "; // commented by udara 21-08-2015
					" ORDER BY DUE_DATE,ORDER_NO,REF_NO "; // added by udara 21-08-2015
				
				rs=stmt1.executeQuery(Sql_invoice);
				
				//out.println(Sql_invoice);
				out.println("<!--"+Sql_invoice+"-->");
				boolean  more_inv =rs.next();
				
				while(more_inv){
					count++;
					if(count==1){
						
						/*
						out.println("<table align='center' width='100%' class='table'>");
						out.println("<tr>");
						out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
						out.println("</tr>");
						// ------ Modified by Dineth on 29-07-2008
						String sql_col_status  = " SELECT "+ m_schema_name + ".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),"+
							//" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-') "+//2
							//" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected','NORM_TERMI','Normal Termination'),'-') "+//2
							" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
							//" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No'),'-')  "+
							" ,APPLICATION_NO "+
							","+m_schema_name+".af_co_get_client_name('"+m_client_code+"') "+
							" FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
							" WHERE FINANCE_NO = '"+m_finance_no +"'";
						rs2 = stmt2.executeQuery(sql_col_status);
						boolean more2 = rs2.next();
						if(more2){
							//Added By Lalanka on 25-06-2009
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Client Name</td><td>: "+rs2.getString(4)+"</td>");
							out.println("</tr>");
							//End by Lalanka
							
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Collection Officer</td><td>: "+rs2.getString(1)+"</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Application Status</td><td>: "+rs2.getString(2)+"</td>");
							out.println("</tr>");
							m_application_no=rs2.getString(3);
						}
						// ------ End by Dineth on 29-07-2008
						out.println("<tr>");
						out.println("<td width='100%'align='center' colspan='2' class=div_input><b>Asset Finance Ledger</b></td>");
						out.println("</tr>");
						out.println("</table>");
						
						*/
						
						//out.println("<hr color='#2F4F4F'>");
						
						
						//out.println("<br>");
						
						/*out.println("<table align='center' width='100%' class='table'  >");
						out.println("</table>");
						*/
						
						
						
						//out.println("<br>");
						
						//out.println("<table align='center' class='table' width='100%'  border='1' bordercolor='#2F4F4F' cellspacing='0'  >"); 
						out.println("<table align='center' class='table' width='100%' border='1' bordercolor=\"#C0C0C0\" cellspacing='1' cellpadding='2'  >"); 
						
						
						out.println("<tr bgcolor=\"#C0C0C0\" >"); //class=pdn_txtpos2
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>Date</td>");
						out.println("<td width='15%' class=div_input>Doc Ref</td>");
						out.println("<td width='30%' class=div_input>Narration</td>");
						out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
						out.println("<td width='10%' class=div_input align='right'>Debit</td>");
						out.println("<td width='10%' class=div_input align='right'>Credit</td>");
						//out.println("<td width='10%' class=div_input align='right'>Balance</td>");
						//out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("<td width='10%' class=div_input align='right'>R/Balance</td>");
						out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("<td width='10%' class=div_input align='right'>Ins/Balance</td>");
						out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("</tr>");
						
					}
					
					//m_cummulative_ins = 0;
					/* added by ns on 24-05-2012 */
					//mm_row_color="black";
					mm_row_color ="style='color:#000000'";
					mm_flag_ins_rental =  false;
					mm_flag_ins_rental_cancel =  false;
					mm_rec_rental =0;
					mm_rec_insurence =0;
					if(rs.getString("DESCRIPTION")==null){
						mm_flag_insurance  =  false;
					}
					
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Premium")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins +rs.getDouble("AMOUNT");
						
					}
					//----------added by ishani 2013.09.12---------//
					else if(rs.getString("DESCRIPTION").equals("Insurance Refund Back")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins +rs.getDouble("AMOUNT");
					}
					//added end by ishani---------------------------//
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");					
						mm_row_color ="style='color:#FF0000'";//added by Prabash on 16-07-2012
						
					}
					else if(rs.getString("DESCRIPTION").equals("Rental / Insurance Payment")){//#23772 Inesh 2017-08-01
						mm_flag_ins_rental  =  true;	
						mm_flag_insurance   =  false;
						rs_rental = stmt_rental.executeQuery(" SELECT NVL(SUM(A.RENTAL_OTER_INVOICE),0) RENTAL_OTER_INVOICE,NVL(SUM(A.INSURANCE),0) INSURANCE FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A WHERE A.SUB_REC_NO = '"+rs.getString(1)+"' GROUP BY A.SUB_REC_NO ");
						out.println(" 1 >>>  mm_rec_rental "+mm_rec_rental+" mm_rec_insurence "+mm_rec_insurence+" m_cummulative_ins "+m_cummulative_ins+"<br>");
						if(rs_rental.next()){
							mm_rec_rental 	 = rs_rental.getDouble("RENTAL_OTER_INVOICE");
							mm_rec_insurence = rs_rental.getDouble("INSURANCE");	
							
							m_cummulative_ins  =  m_cummulative_ins - rs_rental.getDouble("INSURANCE");
							//m_cum_value 	   =  m_cum_value - rs_rental.getDouble("RENTAL_OTER_INVOICE");
						}						
						rs_rental.close();
						out.println(" 2 >>>  mm_rec_rental "+mm_rec_rental+" mm_rec_insurence "+mm_rec_insurence+" m_cummulative_ins "+m_cummulative_ins+"<br>");
						mm_row_color ="style='color:#FF0000'";//added by Prabash on 16-07-2012
						
					}
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment - Cheque Return")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");					
					}
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#A020F0'";
					}
					else if(rs.getString("DESCRIPTION").equals("Credit Note - Insurance Premeium - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#FF0000'";
					}
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Premium - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");							
						mm_row_color ="style='color:#0BA015'";//Mod BY Kanishka On 09-07-2015
						
					}
					else if(rs.getString("DESCRIPTION").equals("Insurance Refund Back- Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins  - rs.getDouble("AMOUNT");
					}
					else if(rs.getString("DESCRIPTION").equals("Credit Note - Insurance Premeium")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Rental Payment - Cancelation")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#A020F0'";
					}
					else if(rs.getString("DESCRIPTION").equals("Rental / Insurance Payment - Cancelation")){ //#23772 Inesh 2017-08-01
						mm_flag_insurance  =  false;
						mm_flag_ins_rental_cancel = true;
						mm_row_color ="style='color:#A020F0'";
						
						rs_rental = stmt_rental.executeQuery(" SELECT NVL(SUM(A.RENTAL_OTER_INVOICE),0) RENTAL_OTER_INVOICE,NVL(SUM(A.INSURANCE),0) INSURANCE FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A WHERE A.SUB_REC_NO = '"+rs.getString(1)+"' GROUP BY A.SUB_REC_NO ");
						
						if(rs_rental.next()){
							mm_rec_rental 	 = rs_rental.getDouble("RENTAL_OTER_INVOICE");
							mm_rec_insurence = rs_rental.getDouble("INSURANCE");	
							
							m_cummulative_ins  =  m_cummulative_ins + rs_rental.getDouble("INSURANCE");
							//m_cum_value 	   =  m_cum_value - rs_rental.getDouble("RENTAL_OTER_INVOICE");
						}						
						rs_rental.close();
						
					}
					else if(rs.getString("DESCRIPTION").equals("Rental Payment")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Documentation Charges")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Refinance")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					
					else if(rs.getString("DESCRIPTION").equals("Stamp Duty Charges")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Closing Payment")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Closing Payment - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					else if(rs.getString("DESCRIPTION").equals("Documentation Charges - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					
					// added by udara 18-10-2016
					else if(rs.getString("DESCRIPTION").equals("Refinance - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					// end by udara 18-10-2016
					
					else if(rs.getString("DESCRIPTION").equals("Stamp Duty Charges - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					// end by udara 05-11-2015
					
					
					else{ 
						mm_flag_insurance  =  false;
					}
					if (m_cummulative_ins > 0) {
						mm_insurance_drcr="Dr";
					}else{
						mm_insurance_drcr="Cr";
					}
					out.println(" 3 >>>  mm_insurance_drcr "+mm_insurance_drcr+"<br>");
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
						m_debit=rs.getDouble(3);
						
					}else if(rs.getString(4).equals("RECEIPT")){
						if(rs.getString(7).equals("RET")){
							m_debit=rs.getDouble(3);
						}else
						{
							m_credit=rs.getDouble(3);
						}
					}else if(rs.getString(4).equals("DR/CR")){
						
						if(rs.getString(7).equals("DR")){
							m_debit=rs.getDouble(3);
						}else
						{
							m_credit=rs.getDouble(3);
						}
						
					}else if(rs.getString(4).equals("ODI")){
						m_debit=rs.getDouble(3);
					}else if(rs.getString(4).equals("OTHER")){
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}else if(rs.getString(4).equals("RETURN_DEBIT")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("DEBIT_CANCEL")){ //added by nuwan de silva on 14-08-07
						m_credit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("CREDIT_CANCEL")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("INV_REV")){ 
						m_credit=rs.getDouble(3);
					}					
					else if(rs.getString(4).equals("LEGAL_ODI")){ 
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("REC_CAN")){ 
						if(mm_flag_ins_rental_cancel){
							m_debit=mm_rec_rental;
						}else{
							m_debit=rs.getDouble(3);
						}						
					}
					
					
					/*else if(rs.getString(4).equals("BAL")){
					m_credit=rs.getDouble(3);
					}*/
					
					else if(rs.getString(4).equals("BAL")){
						/*if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
							m_debit=rs.getDouble(3);
						}else{
							m_credit=rs.getDouble(3);
						}
						*/
						if(!mm_flag_ins_rental){ //#23772 Inesh 2017-08-01
							m_credit = rs.getDouble(3);	
						}else{
							m_credit = mm_rec_rental;							
						}
						
					}
					out.println(" 4 >>>  m_debit "+m_debit+" m_credit "+m_credit+"<br>");
					m_val=m_debit-m_credit;
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 						
						m_cum_value=m_cum_value-m_credit;							
					}
					else{						
						m_cum_value=m_cum_value+m_val;								
					}
					out.println(" 5 >>>  m_cum_value "+m_cum_value+" m_cummulative_ins "+m_cummulative_ins+"<br>");
					/*Added by ns on 28-05-2012*/					
					m_cum_value_ren = m_cum_value - m_cummulative_ins;
					
					if(mm_flag_ins_rental){//2017-08-22
						if ( (m_cum_value_ren-mm_rec_insurence) > 0) {
							mm_rental_drcr="Dr";
						}else{
							mm_rental_drcr="Cr";
						}
					}else{
						if (m_cum_value_ren > 0) {
							mm_rental_drcr="Dr";
						}else{
							mm_rental_drcr="Cr";
						}
					}
					out.println(" 6 >>>  m_cum_value "+m_cum_value+" m_cummulative_ins "+m_cummulative_ins+" m_cum_value_ren "+m_cum_value_ren+" mm_rental_drcr "+mm_rental_drcr+"<br>");
					
					if(rs.getString(4).equals("INVOICE")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>"); // commented by udara on 22-11-2012
						
						/*if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
							out.println("<td width='10%' class=div_input> &nbsp; </td>");  // added by udara on 01-01-2013
						else
						
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 22-11-2012
						*/
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 22-11-2012
						
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}*/
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						
						
						out.println("</tr>");
						
					}
					else if(rs.getString(4).equals("LEGAL_ODI")){ //added by ns 10-08-2009
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("REC_CAN")){ //added by ns 10-08-2009
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>");
						
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else
						{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
							    out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");	
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						
						
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
						}
						*/
						
						if(mm_flag_ins_rental_cancel){//#23772 Inesh 2017-08-01
							
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
							
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");	
						}else{
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
						}
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
						
						if(rs.getString(7).equals("RET") ){
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								   out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							
							//if(rs.getString(7).equals("RET") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
							
							m_val=m_debit-m_credit;
							m_cum_value=m_cum_value-m_val;
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
									out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							//if(rs.getString(7).equals("RET") ){
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							//}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
						}
						else if(!rs.getString(7).equals("RET") ){
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=z'"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								    out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							
							if(rs.getString(7).equals("RET") ){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							
							out.println("</tr>");
							
							//---------------------------------------------------------------------------------------------------
							if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
								m_debit =0;
								m_credit=0;
								m_debit=rs.getDouble(3);
								
								m_val=m_debit-m_credit;
								if(m_cum_value>0){  
									m_cum_value=m_cum_value+m_val;
								}else if (m_cum_value<0 ){
									m_cum_value=m_cum_value-m_val;
								}
								
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
								out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
								out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
								if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
									out.println("<td width='10%' class=div_input>&nbsp;</td>");
								}
								else
								{
									/*
									if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
									   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
									else
										out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
									*/
									
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
									
								}
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								
								/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
								if(m_cum_value>0){
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								else
								{
									out.println("<td width='4%' class=div_input>Cr</td>");
								}*/
								
								
								
								
								if (!mm_flag_insurance){
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
								}else{
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								if (mm_flag_insurance){
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
								}else{
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								
								out.println("</tr>");
							}
						}
						//---------------------------------------------------------------------------------------------------
						
						
					}
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else
						{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								out.println("<td width='10%' class=div_input> &nbsp; </td>");
							else
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							*/
							out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							
							
						}
						if(rs.getString(7).equals("RET") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						}
						
						/*
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					else if(rs.getString(4).equals("OTHER")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					/*else if(rs.getString(4).equals("BAL")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}*/
					
					else if(rs.getString(4).equals("BAL")){
						
						//if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){ //comment by nuwan de silva 10-08-2009
						if(rs.getString(7).equals("RET")  || rs.getString(7).equals("C") ){
							out.println("<tr >");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");//Date
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");//Doc Ref
							out.println("<td width='30%' class=div_input>Receipt</td>"); //"+rs.getString(6)+" //Narration
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>"); //Reference No.
							}
							else{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");//Reference No.
							}
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>"); //Debit
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");//Credit
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							*/
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");//R/Balance
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");//R/Balance
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");//Ins/Balance
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
							
							//m_val=m_debit;
							//m_cum_value=m_cum_value-m_val;
							m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
							/*if (m_cum_value<0 && m_val<0)
							{
							m_cum_value=m_cum_value-m_val;
							}
							else if (m_cum_value>=0 && m_val<0)
							{
							m_cum_value=m_cum_value-m_val;
							}
							else{
							m_cum_value=m_cum_value+m_val;
							}*/
							//added by SH on 15-01-2009 ***********
							if(m_val>0){ 
								if (m_cum_value>0)
								{
									m_cum_value=m_cum_value-m_val;
								}					
								else{
									m_cum_value=m_cum_value+m_val;
								}
							}else{
								//end of addition ******************
								if (m_cum_value<0 && m_val<0)
								{
									m_cum_value=m_cum_value-m_val;
								}
								else if (m_cum_value>=0 && m_val<0)
								{
									m_cum_value=m_cum_value-m_val;
								}
								else{
									m_cum_value=m_cum_value+m_val;
								}
								
							}//added by SH on 15-01-2009 ***********
							
							out.println("<tr >");
							out.println("<td width='1%'><span "+mm_row_color+"></span></td>"); 
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");//Date
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");//Doc Ref
							out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"  </span></td>");//Narration
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">&nbsp;</span></td>"); //Reference No.
							}
							else{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>"); // added by udara on 01-01-2012
								else
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
								*/
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");//Reference No.
							}
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");//Debit
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");//Credit
							
							/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							*/
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"XX</span></td>");//R/Balance
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");//Ins/Balance
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
						}
						else{
							
							out.println("<tr >");
							out.println("<td width='1%'><span "+mm_row_color+"></span></td>"); 
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");//Date
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");//Doc Ref
							out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");//Narration
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");//Reference No.
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
								else
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
								*/
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");//Reference No.
							}
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
							
							
							/*
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
							}
							*/
							
							out.println(" 7 >>>  mm_flag_ins_rental "+mm_flag_ins_rental+"<br>");
							if(mm_flag_ins_rental){ //#23772 Inesh 2017-08-01
								out.println(" 8 >>>  m_cum_value "+m_cum_value+" mm_rec_insurence "+mm_rec_insurence+" m_cummulative_ins "+m_cummulative_ins+" mm_insurance_drcr "+mm_insurance_drcr+"<br>");
								//out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-mm_rec_insurence))+"</span></td>");//R/Balance
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-mm_rec_insurence-m_cummulative_ins))+"</span></td>");//R/Balance
								
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
								
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");//Ins/Balance
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
								m_cum_value=m_cum_value-mm_rec_insurence;
								out.println(" 9 >>>  m_cum_value "+m_cum_value+" mm_rec_insurence "+mm_rec_insurence+" m_cummulative_ins "+m_cummulative_ins+" mm_insurance_drcr "+mm_insurance_drcr+"<br>");
							} 
							
							else {
								
								
								if (!mm_flag_insurance){
									out.println(" 10 >>>  mm_flag_ins_rental "+mm_flag_ins_rental+" m_cum_value "+m_cum_value+" m_cummulative_ins"+m_cummulative_ins+" mm_rental_drcr "+mm_rental_drcr+"<br>");
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");//R/Balance
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
								}else{
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								if (mm_flag_insurance){
									out.println(" 11 >>>  mm_flag_insurance "+mm_flag_insurance+" mm_insurance_drcr "+mm_insurance_drcr+"<br>");
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");//Ins/Balance
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
								}/*else if(mm_flag_ins_rental){ //#23772 Inesh 2017-08-01
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");//Ins/Balance
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
							}*/else {
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								
								
							}
							
							
							out.println("</tr>");
						}
						
					}
					
					else if (rs.getString(4).equals("RETURN_DEBIT")){
						
						/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
						
						if (m_cum_value >0 ){
						m_cum_value=m_cum_value+m_val;
						}
						else{
						m_cum_value=m_cum_value-m_val;
						}
						*/
						
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input> &nbsp; </td>");
							else
								 out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							*/
							
							out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						}
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						out.println("</tr>");
						
					}
					
					else if (rs.getString(4).equals("DEBIT_CANCEL")){
						
						/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
						
						if (m_cum_value >0 ){
						m_cum_value=m_cum_value+m_val;
						}
						else{
						m_cum_value=m_cum_value-m_val;
						}
				*/
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span>  </td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					else if (rs.getString(4).equals("INV_REV")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						} 
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
						}*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					else if (rs.getString(4).equals("CREDIT_CANCEL")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					
					else if(rs.getString(4).equals("DR/CR")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						if(rs.getString(7).equals("DR") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						}
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					
					more_inv = rs.next();
					
					
					if(!more_inv){
						out.println("<tr>");
						out.println("<td colspan='13'>");
						out.println("<br>");
						out.println("<input type='button' value='Cumulative ODI' name='VIEW_ODI' class='but_input' style='width:150px' onclick=\"view_odi('"+m_application_no+"')\">");
						out.println("<br>");
						out.println("</td>");
						out.println("</tr>");
					}
				}
				out.println("</table>");
				
				
				
				
				
				String		Sql_Unallocated=" SELECT "+ 
					"  A.REC_NO, "+
					"  A.REC_AMOUNT, "+
					"  B.allocated_amount, "+
					"  B.bal_tobe_receive, "+
					"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"  WHERE  A.REC_NO=B.REC_NO  "+
					"  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
				
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				boolean  more =rs.next();
				
				if (more) {
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);
					
					more = rs.next();
				}
				
				if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				//receipt contracl level unallocated receipt details .......................................................
				
				/*String		Sql_Unallocated=" SELECT "+ 
				"  A.REC_NO, "+
				"  A.REC_AMOUNT, "+
				"  B.allocated_amount, "+
				"  B.bal_tobe_receive, "+
				"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
				"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
				"  WHERE  A.REC_NO=B.REC_NO  "+
				"  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
				*/
				
				Sql_Unallocated=" SELECT "+ 
					" a.rec_no ,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') , "+ 
					" a.finance_no,"+
					" a.rec_amount, "+
					" a.app_rec_amount, "+
					" a.allocated_amount, "+
					" a.bal_tobe_receive "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a ,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')  "+
					" AND  A.BAL_TOBE_RECEIVE > 0   "+
					" AND B.STATUS NOT IN ('C','CAD','RET')  "+
					" ORDER BY  EFF_VALDATE  ";
				
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				more =rs.next();
				
				if (more) {
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details Contract Level</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='15%' class=div_input ><b>Contract No</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount To This Contract</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(7);
					
					more = rs.next();
				}
				
				if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				
				//================Added by Sandun on 30-07-2008======================================================================
				
				
				
				String		Sql_Pod_Cheque_Hand= " SELECT "+ 
					"  NVL(POD_REF_NO,'-'), "+//1
					"  NVL(FINANCE_NO,'-'), "+//2
					"  NVL(CHEQUE_NO,'-'), "+//3
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
					"  NVL(PAYER_ACC_NO,'-'), "+//5
					"  NVL(PAYER_BRANCH_CODE,'-'), "+//6
					"  NVL(CHEQUE_AMOUNT,0), "+//7
					"  DECODE(STATUS,'APP','Approved','CAN','Dis Approved','HOL','Hold','INV','Entered','REC','Receipt Generated','WIT','Withdraw',STATUS) "+
					"  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
					"  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  AND STATUS IN ('INV' ,'APP') " ;
				
				
				
				rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
				
				
				boolean  more1=rs.next();
				if(more1){
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='100%' class=div_input><u><b>Post Dated Cheque Details - Finance No: "+m_finance_no+"</b></u></td>");					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					/*
				if (!more1) {
							out.println("<table align='center' width='100%' class='table' >");
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							out.println("</table>");
						}
						*/
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='10%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
				//out.println("<table align='center' width='100%' class='table' >");
				while(more1){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input align='left'>"+rs.getString(8)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more1 = rs.next();
				}
				
				
				out.println("</table>");
				
				
				
				//==============================End on 30-07-2008========================================================
				
				//..........................................................................................................
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				
				rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS,ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
					" WHERE CLIENT_CODE ='"+m_client_code+"'  "+
					" AND ( APPLICATION_NO ='"+m_finance_no+"' "+
					" OR    APPLICATION_NO ='"+m_application_no+"' )"+
					" ORDER BY ENT_DATE DESC ");
				
				
				/*rs=stmt1.executeQuery("SELECT TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),A.COMMENTS,A.ENT_USER "+
											" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
																" WHERE A.CLIENT_CODE ='"+m_client_code+"' "+
																" AND A.APPLICATION_NO=B.APPLICATION_NO "+
																" AND (B.FINANCE_NO='"+m_finance_no+"' "+
																" OR  B.FINANCE_NO='"+m_finance_no+"' )"+
																" ORDER BY A.ENT_DATE DESC ");
																
																*/
				
				boolean  more4 =rs.next();			
				
				if(more4){
					out.println("<table align='center' width='100%' class='table' >");
					while(more4){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input valign='top'><p><B>Date: </B>"+rs.getString(1)+" &nbsp <br><B>User: </B>"+rs.getString(3)+"</p></td>");
						out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more4 =rs.next();
					}
					out.println("</table>");
				}
				else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<br>");
				out.println("<br>");
				out.println("<table></table>");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Collection</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				
				// added by udara 06-04-2015
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Contract Status</B></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='20%'><b> Status </b></td>"); 
				out.println("<td width='10%'><b> Entered User </b></td>"); 
				out.println("<td width='20%'><b> Entered Date </b></td>"); 
				out.println("<td width='*%'><b> &nbsp; </b></td>"); 
				out.println("</tr>");
				
				rs=stmt1.executeQuery(" "+
					" select  "+
					" FINANCE_NO, "+
					" STATUS, "+
					" ENT_USER, "+
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS') "+
					" from ( "+
					
					" select FINANCE_NO, "+
					" DECODE(STATUS,'PERFORM','Perform','NPERFORM','Non Perform',STATUS) STATUS, "+
					" ENT_USER,  "+
					" NVL(MOD_DATE,ENT_DATE) ENT_DATE  "+ // " ENT_DATE "+
					" from "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					
					" UNION all "+
					
					" select FINANCE_NO, "+
					" DECODE(STATUS,'PERFORM','Perform','NPERFORM','Non Perform',STATUS) STATUS, "+
					" ENT_USER, "+
					" NVL(MOD_DATE,ENT_DATE) ENT_DATE "+
					" from "+m_schema_name+".AF_CO_PERFORMING_CONTACTS_BK "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					
					" union all "+
					
					" select a.FINANCE_NO FINANCE_NO,  "+
					" B.STATUS STATUS,  "+
					" B.ENTUSER ENT_USER,  "+
					" B.ENTDATE ENT_DATE "+
					" from "+m_schema_name+".AF_RE_PRO_REPOSSESSION a, "+m_schema_name+".AF_RE_REPOSSESSION_STATUS B "+
					" where a.REPOSSESSION_NO = B.ref "+
					" AND A.FINANCE_NO = '"+m_finance_no+"' "+
					
					" ORDER BY ENT_DATE DESC "+
					" ) "+
					" ");
				
				while(rs.next()){
					out.println("<tr>");
					out.println("<td width='20%'> "+rs.getString(2)+" </td>"); 
					out.println("<td width='10%'> "+rs.getString(3)+" </td>"); 
					out.println("<td width='20%'> "+rs.getString(4)+" </td>"); 
					out.println("<td width='*%'> &nbsp; </td>"); 
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_BY_CONTRACT_prev")){ // BACKUP ONE BEFORE APPLY THE RENTAL/INSURENCE
				
				
				String m_finance_no=req.getParameter("finance_no");		
				String m_client_code=req.getParameter("client_code");		
				
				
				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				double m_cummulative_ins=0;
				double m_cum_value_ren =0;
				boolean mm_flag_insurance=false;
				String mm_insurance_drcr="";
				String mm_rental_drcr ="";
				String mm_row_color ="#000000";
				
				String m_app_status = "";
				String m_trn_dec    = "";
				String m_client_name= "";
				String m_cli_add    = "";
				String m_app_no     = "";
				String m_trn_type   = "";
				String m_agr_date   = "";
				String m_ter_type   = "";
				String m_application_no="";
				String m_perform_status = ""; // added by udara 28-07-2015
				
				int m_termi_count=0;
				int m_rental_count=0;
				
				double m_tot_agree=0;
				double m_capitl=0;
				double m_interest=0;
				double m_vat=0;
				
				double m_int_tate=0;
				double mm_NET_RENTAL_AMOUNT=0;
				String mm_RENTAL_DATE="";
				String app_status="";
				String mm_VEHICLE_NO="";
				String mm_MATURITY_DATE="";
				
				String mm_SCORE_MODEL_CODE="";
				String mm_FINAL_APP_SCORE="";
				String mm_MODEL_SCORE="";
				String mm_COMMENTS="";
				
				
				
				rs1=stmt1.executeQuery(
					//out.println(
					" SELECT DISTINCT A.CLIENT_CODE, "+//1
					//" "+m_schema_name+".AF_CO_GET_APP_STATUS(A.APPLICATION_NO), "+//2
					" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO)), "+ /*'Normal Termination Pending' added By Chandana on 19/10/2009 For SR/20091014/069 */	
					" C.DESCRIPTION, "+//3
					" B.FULL_NAME, "+//4
					//	" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||B.TEL_NO ||'/'||B.MOBILE_NO,  "+//5 // Comment Amila 2016-07-05 
					" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'-')||','||B.TEL_NO ||'/'||B.MOBILE_NO,  "+//5 // Add by Amila 2016-07-20  Addres load to city name
					//    " B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE)),'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name
					//" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_CODE(B.CITY_CODE)),'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name  
					//	" B.ADDRESS1|| ' ' ||B.ADDRESS2||','||NVL(B.CITY_CODE,'-'),  "+//5 // Add by Amila 2016-07-05 Addres load to city name 
					" A.APPLICATION_NO, "+//6
					" A.TRANSACTION_TYPE, "+//7
					" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//8
					" "+m_schema_name+".AF_CO_GET_CONTRCT_INTERST_RATE(A.FINANCE_NO) FINANCE_NO, "+  //9
					" DECODE(NVL(STATUS,'PERFORM'),'PERFORM','Perform','NPERFORM','Non Perform',NVL(STATUS,'PERFORM')) "+ // added by udara 28-07-2015
					" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE C "+
					" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
					" AND   A.TRANSACTION_TYPE=C.TRAN_CODE "+
					" AND A.FINANCE_NO = '"+m_finance_no+"' ");
				
				
				if(rs1.next()){
					m_client_code  = rs1.getString(1);
					m_app_status   = rs1.getString(2);
					m_trn_dec      = rs1.getString(3);
					m_client_name  = rs1.getString(4);
					m_cli_add      = rs1.getString(5);
					m_app_no       = rs1.getString(6);
					m_trn_type     = rs1.getString(7);
					m_agr_date     = rs1.getString(8);
					m_int_tate     = rs1.getDouble(9);
					m_application_no=rs1.getString("APPLICATION_NO");
					m_perform_status = rs1.getString(10); // added by udara 28-07-2015
					//out.println("m_cli_add ="+m_cli_add);
				}
				
				
				
				
				
				rs2=stmt2.executeQuery(
					//out.println(
					" SELECT SUM(B.NET_PRICE), "+//1
					" SUM(A.INTEREST_AMOUNT), "+//2
					" 0 , "+//3
					" COUNT(*) "+	//4
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
					" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
					" AND A.PRICING_NO       = B.PRICING_NO "+
					" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
					" AND B.ACTIVE_STATUS    IN ('T','Y')");		
				
				
				if(rs2.next()){
					m_rental_count= rs2.getInt(4);
					m_capitl      = rs2.getDouble(1)/m_rental_count;
					m_interest    = rs2.getDouble(2);
					m_vat         = rs2.getDouble(3);
					m_tot_agree   = m_capitl+m_interest+m_vat;
					
				}
				
				// added by udara 28-11-2013
				int app_count = 0;
				
				rs2=stmt2.executeQuery(
					" SELECT COUNT(APPLICATION_NO) FROM(	"+
					" SELECT A.APPLICATION_NO APPLICATION_NO"+//1
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
					" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
					" AND A.PRICING_NO       = B.PRICING_NO "+
					" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
					" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
					" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
					" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
					" )");
				
				if(rs2.next()){
					app_count = rs2.getInt(1);
				}
				
				//out.println("aaaaaaaaaaaaaaa" + app_count);
				
				
				// end by udara 28-11-2013
				
				// commented by udara 28-11-2013
				/*
				rs2=stmt2.executeQuery(
				//out.println(
				" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
				" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
				" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
				" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
				" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
				" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
				" AND A.PRICING_NO       = B.PRICING_NO "+
				" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
				" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
				" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
				" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
				" ");
				*/
				
				// added by udara 28-11-2013
				if(app_count>0){
					rs2=stmt2.executeQuery(
						//out.println(
						" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
						" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
						" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
						" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
						" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
						" AND A.PRICING_NO       = B.PRICING_NO "+
						" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
						" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
						" AND TO_NUMBER(INSTALLMENT_NO)= 1 "+
						" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
						" ");
				}
				else{
					rs2=stmt2.executeQuery(
						//out.println(
						" SELECT SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT, "+//1
						" TO_CHAR(RENTAL_DATE,'DD') RENTAL_DATE, "+
						" NVL(REG_NO,VEHICLE_NO)  VEHICLE_NO, "+
						" "+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO) MATURITY_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"'  "+
						" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
						" AND A.PRICING_NO       = B.PRICING_NO "+
						" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
						" AND B.ACTIVE_STATUS    IN ('T','Y')	"+
						" AND ROWNUM= 1 "+
						" GROUP BY A.APPLICATION_NO,RENTAL_DATE,VEHICLE_NO,REG_NO "+
						" ");
				}
				
				
				// end by udara 28-11-2013
				
				if(rs2.next()){
					mm_NET_RENTAL_AMOUNT      = rs2.getDouble("NET_RENTAL_AMOUNT");
					//mm_RENTAL_DATE            = rs2.getString("RENTAL_DATE");
					mm_VEHICLE_NO             = rs2.getString("VEHICLE_NO");
					mm_MATURITY_DATE          = rs2.getString("MATURITY_DATE");
					
					
				}
				
				// commented by udara on 08-08-2013
				/*
				rs2=stmt2.executeQuery(
				
				" SELECT   APPLICATION_NO,TO_CHAR(RENTAL_DATE,'DD') DUE_DATE "+
				"            FROM     "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
				"            WHERE    (APPLICATION_NO,RENTAL_DATE) IN  "+
				"            ( "+
				"            SELECT   APPLICATION_NO,MAX(RENTAL_DATE) RENTAL_DATE "+
				"            FROM     "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
				"            WHERE   (PRO_INVOICE_NO,APPLICATION_NO,PRICING_NO) IN ( SELECT   INVOICE_NO, APPLICATION_NO, PRICING_NO  "+
				"                                                                    FROM     "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				//"                                                                    WHERE    ACTIVE_STATUS IN ('Y') "+ // commented by udara on 04-07-2013
				"                                                                    WHERE    ACTIVE_STATUS IN ('Y','T') "+ // added by udara on 04-07-2013
				" 																	  AND      APPLICATION_NO='"+m_app_no+"' "+
				"                                                                    GROUP BY INVOICE_NO, APPLICATION_NO, PRICING_NO  "+
				"                                                                  ) "+
				"                                                                  "+
				"            AND       RENTAL_DATE < TRUNC(SYSDATE,'DD') +1 "+
				"            GROUP BY APPLICATION_NO       "+
				"            ) "+
				" 			  AND      APPLICATION_NO ='"+m_app_no+"' "+
				"            GROUP BY APPLICATION_NO ,RENTAL_DATE  "+
				" ");
				
				if(rs2.next()){
				mm_RENTAL_DATE            = rs2.getString("DUE_DATE");
				}
				*/
				
				// added by udara on 08-08-2013
				//Added by Kanchana on 2016-07-21
				rs3=stmt3.executeQuery("SELECT APPLICATION_STATUS FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO = '"+m_app_no+"' AND APPLICATION_STATUS IN ('ENTERED','VERIFY1','V-APP','V-RECOM','VERIFY-M','VERIFY2','VERIFY-M','VERIFY2','ENT_CON') ");
				
				
				
				if(rs3.next()){//Added by Kanchana on 2016-07-21
					rs2=stmt2.executeQuery( " "+
						" SELECT  NVL(TO_CHAR("+m_schema_name+".AF_CR_GET_NEXT_PAYMENT_DATE(ADD_MONTHS(SYSDATE,1)),'DD'),'') NEXT_DATE_DD "+
						" FROM DUAL "+
						" ");
					
					if(rs2.next()){
						mm_RENTAL_DATE            = rs2.getString("NEXT_DATE_DD");
					}
					
				}else{//Added by Kanchana on 2016-07-21
					
					rs2=stmt2.executeQuery( " "+
						" SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
						" WHERE A.APPLICATION_NO = '"+m_app_no+"' "+
						" ");
					
					if(rs2.next()){
						mm_RENTAL_DATE            = rs2.getString("DUE_DATE");
					}
					// added by udara on 08-08-2013
					
				}//Added by Kanchana on 2016-07-21
				
				rs2=stmt2.executeQuery( " "+
					" SELECT APPLICATION_CODE, "+
					" SCORE_MODEL_CODE, "+
					" FINAL_APP_SCORE, "+
					" MODEL_SCORE, "+
					" COMMENTS "+
					" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE  "+
					" WHERE APPLICATION_CODE = '"+m_app_no+"' "+
					" ");
				
				if(rs2.next()){
					mm_SCORE_MODEL_CODE            = rs2.getString(2);
					mm_FINAL_APP_SCORE             = rs2.getString(3);
					mm_MODEL_SCORE                 = rs2.getString(4);
					mm_COMMENTS                    = rs2.getString(5);
				}
				
				/*	out.println("mm_SCORE_MODEL_CODE "+mm_SCORE_MODEL_CODE);
				out.println("mm_FINAL_APP_SCORE  "+mm_FINAL_APP_SCORE);
				out.println("mm_MODEL_SCORE "+mm_MODEL_SCORE);
				out.println("mm_COMMENTS " +mm_COMMENTS); */
				
				
				out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : "+m_finance_no+" </TITLE>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<script type='text/javascript'>");
				out.println("function view_odi(odj){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL&url=&application_no='+odj;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				
				out.println("}");
				out.println("</script>");
				out.println("</HEAD><BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				out.println("<br><br>");
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B> Transaction History - Finance No : "+m_finance_no+" </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<table class='table' width='100%'  border='0' bordercolor='grey' cellspacing='2' cellpadding='2' >");
				out.println("<tr>");
				out.println("<td class=div_input colspan='2'><b>Client Details </td>");
				out.println("<td class=div_input colspan='2'><b>Facility Details </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=div_input width='10%'><b>Name</td>");
				out.println("<td class=div_input width='40%'><b>:&nbsp;&nbsp;"+m_client_name+"</td>");
				out.println("<td class=div_input width='10%'><b>Capital</td>");
				out.println("<td class=div_input width='40%'><b>:&nbsp;&nbsp;"+nf.format(m_capitl)+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=div_input ><b>Address</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_cli_add+"</td>");
				out.println("<td class=div_input ><b>No of Rental </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_rental_count+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Contract Status</td>");
				//out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_app_status+"</td>"); // commented by udara 28-07-2015
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_app_status+" - "+m_perform_status+"</td>"); 
				out.println("<td class=div_input ><b>Rental </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+nf.format(mm_NET_RENTAL_AMOUNT)+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Type of facility</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+m_trn_dec+"</td>");
				out.println("<td class=div_input ><b>Rental Date </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_RENTAL_DATE+"</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=div_input ><b>Vehicle No</td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_VEHICLE_NO+"</td>");
				out.println("<td class=div_input ><b>Maturity Date </td>");
				out.println("<td class=div_input ><b>:&nbsp;&nbsp;"+mm_MATURITY_DATE+"</td>");
				out.println("</tr>");
				
				// added by udara 21-10-2014
				out.println("<tr>");
				out.println("<td class=div_input ><b>Security Details</td>");
				out.println("<td class=div_input style={cursor:hand;} onClick=\"show_run_con_det('"+m_app_no+"');\" >:&nbsp;&nbsp;<u>view</u></td>"); //comment AS
				out.println("<td class=div_input ><b>Credit Score Details</b></td>"); // mod by udara 21-08-2015
				out.println("<td class=div_input style={cursor:hand;} onClick=\"show_crdit_score_det('"+m_app_no+"','"+m_finance_no+"','"+mm_SCORE_MODEL_CODE+"','"+mm_FINAL_APP_SCORE+"','"+mm_MODEL_SCORE+"','"+mm_COMMENTS+"');\" >:&nbsp;&nbsp;<u>view</u></td>"); // added by udara 21-08-2015 // out.println("<td class=div_input > &nbsp; </td>"); ,'"+mm_SCORE_MODEL_CODE+"','"+mm_FINAL_APP_SCORE+"','"+mm_MODEL_SCORE+"','"+mm_COMMENTS+"'
				out.println("</tr>");
				
				// end by udara 21-10-2014
				
				out.println("</table>");
				
				
				
				
				
				
				String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO,ORDER_NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					//"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  DECODE(INVOICE_TYPE,'LEGAL_CAP',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),'LEGAL_ARR',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),VALUE_DATE) DUE_DATE ,"+
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					//"  NVL(NULL,'-') CHEQUE_NO, "+
					
					//" CASE  "+
					//" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN '-'   "+
					//" END CHEQUE_NO , "+
					
					/*
					// added by udara on 14-11-2012
					 " CASE "+ 
					 " WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO=INVOICE_NO) "+   
					 " WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN '-' "+   
					 " END CHEQUE_NO , "+
					// end by udara on 14-11-2012
					*/
					
					// added by udara on 26-11-2012
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN ( "+
					" NVL( "+
					" (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO=INVOICE_NO AND SUSPENSE_ENTRY_TYPE='INSURANCE' ), "+
					" (SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER) FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE REF_NO='"+m_application_no+"' AND SUSPENSE_ENTRY_TYPE='INSURANCE') "+ // commented by udara on 21-02-2014 // released by udara 18-03-2014
					
					// commented by udara 18-03-2014
					/*
					// added by udara on 21-02-2014
					" ( "+                            
						" SELECT "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(D.PAYEE_CODE) "+
						" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E,  "+m_schema_name+".AF_CO_PRO_INVOICE B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES D "+
						" WHERE B.INVOICE_NO LIKE A.INVOICE_NO "+
						" AND E.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
						" AND E.PRO_INVOICE_NO = C.INVOICE_NO "+
						" AND C.PRICING_NO = D.PRICING_NO "+
						" AND D.CHARGE_TYPE = 'INV'  "+
					" ) "+
					*/
					
					" ) "+
					"  )  "+  
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN "+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO)  "+ // commented by udara 17-05-2017  // modified by udara on 31-12-2012
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN "+m_schema_name+".AF_CO_GET_INST_NO_TRAN('"+m_application_no+"',INVOICE_NO)  "+ // added by udara 17-05-2017
					" END CHEQUE_NO , "+
					// end by udara on 26-11-2012
					
					//"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					
					// commented by udara 27-07-2015
					/*
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'CAN_INC')  THEN 'Abandon Fee'   "+ // added by udara 22-05-2015
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium'   "+
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE))   "+
					" END DESCRIPTION , "+
					*/
					
					// added by udara 27-07-2015
					" CASE "+  
					" WHEN (INVOICE_TYPE = 'CAN_INC')  THEN 'Abandon Fee' "+   
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium' "+  
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) "+  
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)))  "+ 
					" END DESCRIPTION, "+
					// end by udara 27-07-2015
					
					
					" NULL STATUS ,"+
					" ''   NO "+ //NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ') 
					" , '1'  ORDER_NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE A   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"        ACTIVE_STATUS='Y'  "+
					"  TOTAL_AMOUNT <> 0 AND "+
					"  ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+
					"  AND INVOICE_TYPE NOT IN ('LEGAL_ODI') "+
					" AND VALUE_DATE <=SYSDATE "+  
					
					
					//Comment by ns on 23-05-2012
					/*"  UNION ALL "+
					" SELECT "+
					" REC_NO REF_NO,  "+
					" EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') || ' Value Date ' || "+m_schema_name+".AF_CO_GET_REC_VAL_DATE(REC_NO) DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+//,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO "+
					//" A.INVOICE_NO=C.INVOICE_NO AND "+
					//" C.FINANCE_NO='"+m_finance_no+"' "+
					"	AND B.STATUS NOT IN ('RET','C','CAD') "+
					
					
					" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' "+
					"                   AND   ACTIVE_STATUS='Y' "+
					"                   UNION ALL "+
					"                   SELECT ODI_REF_NO "+
					"                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					"                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"'    )) "+ //comment by ns on 23-12-2011
					
					" AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
				    */
					
					
					"	UNION ALL "+
					
					" SELECT "+
					" REF_NO, "+
					" DUE_DATE, "+
					" AMOUNT, "+
					" TYP, "+
					" CHEQUE_NO, "+
					" DESCRIPTION, "+
					" STATUS, "+
					" STYPE, "+
					" ORDER_NO "+
					" FROM  ( "+
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" C.ALLOCATED_DATE DUE_DATE, "+//A.DUE_DATE  DUE_DATE, "+
					" SUM(C.SETTELED_AMOUNT) AMOUNT, "+//A.ODI_SETTLED_AMOUNT AMOUNT, "+ //SUM(C.SETTELED_AMOUNT)
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'Over Due Interst' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" , '2'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+//m_schema_name+".AF_CO_PRO_INVOICE B, "+
					"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE "+//A.ODI_SETTLED_AMOUNT > 0 "+
					//"     A.INVOICE_NO=B.INVOICE_NO "+
					"       C.INVOICE_NO=A.ODI_REF_NO "+
					//" AND B.CLIENT_CODE='"+m_client_code+"'  "+
					" AND SETTELED_AMOUNT <> 0 "+
					" AND A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' "+//AND "+ //commented AND  by SH on 13-12-2010
					//"          ACTIVE_STATUS='Y' "+
					//"            ACTIVE_STATUS IN ('Y','DB_CAN')  "+//commented by SH on 13-12-2010
					") "+
					" AND C.ALLOCATED_DATE <=SYSDATE "+ //changed by SH on 29-09-2009 DUE_DATE <=SYSDATE "+   
					" GROUP BY A.INVOICE_NO,C.ALLOCATED_DATE "+
					" ) "+
					" WHERE AMOUNT <> 0 "+
					
					
					" UNION ALL "+
					
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					//" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					
					" CASE  "+
					" WHEN (REMARKS = 'CREDIT NOTE FOR - INSURANCE' )  THEN 'Credit Note - Insurance Premeium'   "+
					//" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note')   "+ // commented by udara on 06-09-2013
					//" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR','Credit Note of Invoice No ' || INVOICE_NO || ' - ' || ("+m_schema_name+".AF_CO_GET_INVOICE_TYPE(INVOICE_NO,'DESCRIPT')) || ' - ' || 'Reverse','Debit Note')  "+ // added by udara on 06-09-2013 // commented by udara on 01-10-2013
					" WHEN (REMARKS <> 'CREDIT NOTE FOR - INSURANCE' ) THEN DECODE(CREDIT_TYPE,'CR',NVL('Credit Note - ' || REMARKS,'Credit Note') ,'Debit Note')   "+ // added by udara on 01-10-2013
					" END DESCRIPTION , "+
					
					" CREDIT_TYPE STATUS, "+
					" '' NO "+ //7
					" ,'3'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+	//'C' added by ns on 04/09/2015
					"      AND   INVOICE_TYPE<>'LEGAL_ODI'"+
					" ) "+
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE NOT IN ('LEGAL_AD','LEGAL_ODI','TERM_ODI','ODI') "+ //'LEGAL_ODI','TERM_ODI','ODI' added by ns on 31-05-2011
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					
					//--------------------------Sandun on 18-03-2009---------------------------------
					
					" UNION ALL"+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note - Legal Termination') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" , '4'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( "+
					" SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+					
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE = 'LEGAL_AD' "+
					" AND ADJUSTED_DATE <=SYSDATE "+ 
					
					
					" UNION ALL"+
					
					" SELECT B.INVOICE_NO REF_NO, "+
					" NVL(B.INV_REV_DATE,B.VALUE_DATE) DUE_DATE,  "+
					" B.TOTAL_AMOUNT AMOUNT,  "+
					" 'INV_REV' TYP, "+
					" '-'  CHEQUE_NO ,   "+
					//" 'Invoice Cancel '  DESCRIPTION,     "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					
					" END DESCRIPTION , "+
					
					" '-'  STATUS,   "+ 
					" 'CR' STYPE  "+
					" , '5'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE "+				  
					" B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND B.ACTIVE_STATUS = 'C' "+
					" AND (SELECT COUNT(INVOICE_NO) FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE WHERE INVOICE_NO = B.INVOICE_NO ) = 0 "+ // added by udara on 04-12-2012
					" AND VALUE_DATE <= SYSDATE "+
					
					//------------------------------------------------------------
					
					" UNION ALL "+
					
					// commented by udara 18-11-2013
					/*
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ //DISNAKA
					" b.eff_valdate   DUE_DATE ,"+ //				
					" a.APP_REC_AMOUNT ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					//Added by ns on 23-05-2012
					" CASE  "+
					" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment'   "+
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment'   "+
					" END DESCRIPTION , "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '6'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					//" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+
					*/
					
					// added by udara 18-11-2013
					" SELECT  "+
					" REF_NO, "+
					" DUE_DATE, "+
					" APP_REC_AMOUNT, "+
					" TYP, "+
					" CHEQUE_NO, "+
					" DESCRIPTION, "+
					" STATUS, "+
					" NO, "+
					" ORDER_NO  "+
					" FROM( "+
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ //DISNAKA
					" b.eff_valdate   DUE_DATE ,"+ //				
					" a.APP_REC_AMOUNT ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					//Added by ns on 23-05-2012
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment'   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges'  "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment' "+
					// end by udara 14-07-2015
					
					
					" END DESCRIPTION , "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '6'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					//" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+ 
					" ORDER BY B.ENT_DATE ASC "+
					
					" ) "+
					
					// end by udara 18-11-2013
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	NVL(B.SUB_REC_NO,B.REC_NO) REF_NO,   "+ //DISNAKA
					//"	C.ENT_DATE DUE_DATE,  "+ //ADDDE BY NUWAN DE SILVA 03-11-2008
					" B.REALISED_DATE DUE_DATE,"+
					"	NVL(A.BAL_TOBE_RECEIVE,0)  AMOUNT,   "+
					"	'RETURN_DEBIT' TYP,  "+
					"	B.CHEQUE_NO  CHEQUE_NO ,  "+
					//"	'Cheque Return '  DESCRIPTION,    "+
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment - Cheque Return '   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cheque Return'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges - Cheque Return'  "+  
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance - Cheque Return'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment' "+
					// end by udara 14-07-2015
					
					" END DESCRIPTION , "+
					
					"	B.STATUS STATUS,   "+
					" 'DR' STYPE "+
					" , '7'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" ,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS C "+
					" WHERE A.REC_NO=B.REC_NO "+
					"	AND   A.REC_NO=C.RECEIPT_NO(+) "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='RET' "+
					
					
					//ADDED BY NUWAN DE SILVA 10-08-2009
					" UNION ALL "+
					
					" SELECT NVL(B.SUB_REC_NO,B.REC_NO) REF_NO , "+ // ADDED BY DISNAKA 
					" NVL(b.rec_cancel_date,b.eff_valdate)   DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'REC_CAN' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" 'Receipt Cancelation' DESCRIPTION , "+
					" CASE  "+
					
					//" WHEN B.RENTAL_OTER_INVOICE >0  THEN 'Rental Payment - Cancelation'   "+ // commented by udara 14-07-2015
					//" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cancelation'   "+ // commented by udara 14-07-2015
					
					// added by udara 14-07-2015
					//" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cheque Return' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'N')='N' THEN 'Rental Payment - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'Y')='Y' THEN 'Closing Payment - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='I' THEN 'Documentation Charges - Cancelation' "+
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='S' THEN 'Stamp Duty Charges - Cancelation'  "+  
					" WHEN B.RENTAL_OTER_INVOICE >0 AND NVL(CLOSING_FLAG,'I')='R' THEN 'Refinance - Cancelation'  "+ // added by udara 26-08-2016
					" WHEN B.INSURANCE >0  THEN 'Insurance Payment - Cancelation' "+
					// end by udara 14-07-2015
					
					" END DESCRIPTION , "+
					
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" , '8'  ORDER_NO "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='CAD' "+
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	A.INVOICE_NO REF_NO,   "+
					"	A.TRN_DATE DUE_DATE,  "+ 
					"	NVL(A.AMOUNT,0)  AMOUNT,   "+
					"	'DEBIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					//"	'Debit Note Cancelation'  DESCRIPTION,    "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Insurance Premium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) ||'- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					" END DESCRIPTION , "+
					
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" , '9'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE A ,  "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO = B.INVOICE_NO "+
					" AND   A.FINANCE_NO='"+m_finance_no+"' "+
					
					" AND   A.TRN_DATE <=SYSDATE "+  
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	A.INVOICE_NO REF_NO,   "+
					"	A.TRN_DATE DUE_DATE,  "+ 
					"	NVL(A.ADJUSTED_AMOUNT,0)  AMOUNT,   "+
					"	'CREDIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					//"	'Credit Note Cancelation '  DESCRIPTION,    "+
					" CASE  "+
					" WHEN (INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE' )  THEN 'Credit Note - Insurance Premeium - Cancelation'   "+
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) ||'- Cancelation'    "+ // commented by udara 27-07-2015
					
					// added by udara 27-07-2015
					//" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  // commented by udara 02-03-2017
					" WHEN (INVOICE_TYPE <> 'INSURANCE' AND REMARKS <> 'CHARGES - INSURANCE' AND INVOICE_TYPE<>'INV_OTHER' )  THEN 'Credit Note - ' || NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) || '- Cancelation' "+  // added by udara 02-03-2017
					" WHEN (INVOICE_TYPE = 'INV_OTHER')  THEN "+
					" NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2)),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(REMARKS,INSTR(REMARKS,'-',-1)+2))) || '- Cancelation' "+ 
					// end by udara 27-07-2015
					
					" END DESCRIPTION , "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" , '10'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_CR_NOTE A,  "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO = B.INVOICE_NO "+
					" AND   A.FINANCE_NO=B.FINANCE_NO  "+
					" AND   A.FINANCE_NO='"+m_finance_no+"' "+
					" AND   A.TRN_DATE <=SYSDATE "+  
					
					//added by ns 10-08-2009 ------------------------
					" UNION  ALL "+
					" SELECT  "+
					" A.INVOICE_NO REF_NO , "+
					" A.ALLOCATED_DATE DUE_DATE, "+
					" SUM(A.SETTELED_AMOUNT) AMOUNT , "+
					" 'LEGAL_ODI' TYP, "+
					" '-' CHEQUE_NO, "+
					" 'Legal ODI Allocation' DESCRIPTION, "+ 
					" '' STATUS, "+
					" 'DR' STYPE "+
					" , '11'  ORDER_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO=B.INVOICE_NO "+
					" AND   B.FINANCE_NO='"+m_finance_no+"' "+
					" AND   B.INVOICE_TYPE='LEGAL_ODI' "+
					" AND   B.ACTIVE_STATUS='Y' "+
					" AND   A.ALLOCATED_DATE <=SYSDATE "+
					" GROUP BY A.INVOICE_NO, A.ALLOCATED_DATE "+
					//-----------------------------------------------------
					
					
					" ) "+ 
					
					//" ORDER BY DUE_DATE,ORDER_NO "; // commented by udara 21-08-2015
					" ORDER BY DUE_DATE,ORDER_NO,REF_NO "; // added by udara 21-08-2015
				
				rs=stmt1.executeQuery(Sql_invoice);
				
				//out.println(Sql_invoice);
				boolean  more_inv =rs.next();
				
				while(more_inv){
					count++;
					if(count==1){
						
						/*
						out.println("<table align='center' width='100%' class='table'>");
						out.println("<tr>");
						out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
						out.println("</tr>");
						// ------ Modified by Dineth on 29-07-2008
						String sql_col_status  = " SELECT "+ m_schema_name + ".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),"+
							//" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-') "+//2
							//" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected','NORM_TERMI','Normal Termination'),'-') "+//2
							" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
							//" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No'),'-')  "+
							" ,APPLICATION_NO "+
							","+m_schema_name+".af_co_get_client_name('"+m_client_code+"') "+
							" FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
							" WHERE FINANCE_NO = '"+m_finance_no +"'";
						rs2 = stmt2.executeQuery(sql_col_status);
						boolean more2 = rs2.next();
						if(more2){
							//Added By Lalanka on 25-06-2009
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Client Name</td><td>: "+rs2.getString(4)+"</td>");
							out.println("</tr>");
							//End by Lalanka
							
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Collection Officer</td><td>: "+rs2.getString(1)+"</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td width='12%' align='left' class=div_input>Application Status</td><td>: "+rs2.getString(2)+"</td>");
							out.println("</tr>");
							m_application_no=rs2.getString(3);
						}
						// ------ End by Dineth on 29-07-2008
						out.println("<tr>");
						out.println("<td width='100%'align='center' colspan='2' class=div_input><b>Asset Finance Ledger</b></td>");
						out.println("</tr>");
						out.println("</table>");
						
						*/
						
						//out.println("<hr color='#2F4F4F'>");
						
						
						//out.println("<br>");
						
						/*out.println("<table align='center' width='100%' class='table'  >");
						out.println("</table>");
						*/
						
						
						
						//out.println("<br>");
						
						//out.println("<table align='center' class='table' width='100%'  border='1' bordercolor='#2F4F4F' cellspacing='0'  >"); 
						out.println("<table align='center' class='table' width='100%' border='1' bordercolor=\"#C0C0C0\" cellspacing='1' cellpadding='2'  >"); 
						
						
						out.println("<tr bgcolor=\"#C0C0C0\" >"); //class=pdn_txtpos2
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>Date</td>");
						out.println("<td width='15%' class=div_input>Doc Ref</td>");
						out.println("<td width='30%' class=div_input>Narration</td>");
						out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
						out.println("<td width='10%' class=div_input align='right'>Debit</td>");
						out.println("<td width='10%' class=div_input align='right'>Credit</td>");
						//out.println("<td width='10%' class=div_input align='right'>Balance</td>");
						//out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("<td width='10%' class=div_input align='right'>R/Balance</td>");
						out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("<td width='10%' class=div_input align='right'>Ins/Balance</td>");
						out.println("<td width='4%' class=div_input>&nbsp;</td>");
						
						out.println("</tr>");
						
					}
					
					//m_cummulative_ins = 0;
					/* added by ns on 24-05-2012 */
					//mm_row_color="black";
					mm_row_color ="style='color:#000000'";
					if(rs.getString("DESCRIPTION")==null){
						mm_flag_insurance  =  false;
					}
					
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Premium")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins +rs.getDouble("AMOUNT");
						
					}
					//----------added by ishani 2013.09.12---------//
					else if(rs.getString("DESCRIPTION").equals("Insurance Refund Back")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins +rs.getDouble("AMOUNT");
					}
					//added end by ishani---------------------------//
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");					
						mm_row_color ="style='color:#FF0000'";//added by Prabash on 16-07-2012
						
					}
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment - Cheque Return")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");					
					}
					else if(rs.getString("DESCRIPTION").equals("Insurance Payment - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#A020F0'";
					}
					else if(rs.getString("DESCRIPTION").equals("Credit Note - Insurance Premeium - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins + rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#FF0000'";
					}
					
					else if(rs.getString("DESCRIPTION").equals("Insurance Premium - Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");	
						//	mm_row_color ="style='color:#A020F0'";
						mm_row_color ="style='color:#0BA015'";//Mod BY Kanishka On 09-07-2015
						
					}
					
					// added by udara 01-09-2015
					else if(rs.getString("DESCRIPTION").equals("Insurance Refund Back- Cancelation")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins  - rs.getDouble("AMOUNT");
					}
					// end by udara 01-09-2015
					
					
					else if(rs.getString("DESCRIPTION").equals("Credit Note - Insurance Premeium")){
						mm_flag_insurance  =  true;
						m_cummulative_ins  =  m_cummulative_ins - rs.getDouble("AMOUNT");	
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Rental Payment - Cancelation")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#A020F0'";
					}
					
					else if(rs.getString("DESCRIPTION").equals("Rental Payment")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					
					// added by udara 14-07-2015
					else if(rs.getString("DESCRIPTION").equals("Documentation Charges")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					
					// added by udara 18-10-2016
					else if(rs.getString("DESCRIPTION").equals("Refinance")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					// end by udara 18-10-2016
					
					else if(rs.getString("DESCRIPTION").equals("Stamp Duty Charges")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					else if(rs.getString("DESCRIPTION").equals("Closing Payment")){
						mm_flag_insurance  =  false;
						mm_row_color ="style='color:#FF0000'";
					}
					// end by udara 14-07-2015
					
					// added by udara 05-11-2015
					else if(rs.getString("DESCRIPTION").equals("Closing Payment - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					else if(rs.getString("DESCRIPTION").equals("Documentation Charges - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					
					// added by udara 18-10-2016
					else if(rs.getString("DESCRIPTION").equals("Refinance - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					// end by udara 18-10-2016
					
					else if(rs.getString("DESCRIPTION").equals("Stamp Duty Charges - Cancelation")){
						mm_flag_insurance  =  false; // added by udara 13-03-2017
						mm_row_color ="style='color:#A020F0'";
					}
					// end by udara 05-11-2015
					
					
					else{ 
						mm_flag_insurance  =  false;
						
					}
					
					
					if (m_cummulative_ins > 0) {
						mm_insurance_drcr="Dr";
					}else{
						mm_insurance_drcr="Cr";
					}
					
					
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
						m_debit=rs.getDouble(3);
						
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
						if(rs.getString(7).equals("RET")){
							m_debit=rs.getDouble(3);
						}
						else
						{
							m_credit=rs.getDouble(3);
						}
					}
					
					else if(rs.getString(4).equals("DR/CR")){
						
						if(rs.getString(7).equals("DR")){
							m_debit=rs.getDouble(3);
						}
						else
						{
							m_credit=rs.getDouble(3);
						}
						
					}
					
					else if(rs.getString(4).equals("ODI")){
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("OTHER")){
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RETURN_DEBIT")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("DEBIT_CANCEL")){ //added by nuwan de silva on 14-08-07
						m_credit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("CREDIT_CANCEL")){ //added by nuwan de silva on 14-08-07
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("INV_REV")){ 
						m_credit=rs.getDouble(3);
					}
					//added by ns
					else if(rs.getString(4).equals("LEGAL_ODI")){ 
						m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("REC_CAN")){ 
						m_debit=rs.getDouble(3);
					}
					
					
					/*else if(rs.getString(4).equals("BAL")){
					m_credit=rs.getDouble(3);
					}*/
					
					else if(rs.getString(4).equals("BAL")){
						/*if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
						m_debit=rs.getDouble(3);
						}else{
						m_credit=rs.getDouble(3);
						}
						*/
						m_credit=rs.getDouble(3);
					}
					
					m_val=m_debit-m_credit;
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 
						m_cum_value=m_cum_value-m_credit;
					}
					else{
						m_cum_value=m_cum_value+m_val;
					}
					
					/*Added by ns on 28-05-2012*/
					m_cum_value_ren = m_cum_value - m_cummulative_ins;
					if (m_cum_value_ren > 0) {
						mm_rental_drcr="Dr";
					}else{
						mm_rental_drcr="Cr";
					}
					
					//m_cum_value=m_cum_value+m_val;
					
					if(rs.getString(4).equals("INVOICE")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>"); // commented by udara on 22-11-2012
						
						/*if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
							out.println("<td width='10%' class=div_input> &nbsp; </td>");  // added by udara on 01-01-2013
						else
						
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 22-11-2012
						*/
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>"); // added by udara on 22-11-2012
						
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}*/
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						
						
						out.println("</tr>");
						
					}
					else if(rs.getString(4).equals("LEGAL_ODI")){ //added by ns 10-08-2009
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("REC_CAN")){ //added by ns 10-08-2009
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						//out.println("<td width='10%' class=div_input>&nbsp;</td>");
						
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else
						{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
							    out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");	
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						
						
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
						
						if(rs.getString(7).equals("RET") ){
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								   out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							
							//if(rs.getString(7).equals("RET") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
							
							m_val=m_debit-m_credit;
							m_cum_value=m_cum_value-m_val;
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
									out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							//if(rs.getString(7).equals("RET") ){
							//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							//}
							//else
							//{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							//}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
						}
						else if(!rs.getString(7).equals("RET") ){
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=z'"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								    out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								
							}
							
							if(rs.getString(7).equals("RET") ){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							}
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}*/
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							
							out.println("</tr>");
							
							//---------------------------------------------------------------------------------------------------
							if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
								m_debit =0;
								m_credit=0;
								m_debit=rs.getDouble(3);
								
								m_val=m_debit-m_credit;
								if(m_cum_value>0){  
									m_cum_value=m_cum_value+m_val;
								}else if (m_cum_value<0 ){
									m_cum_value=m_cum_value-m_val;
								}
								
								out.println("<tr>");
								out.println("<td width='1%'></td>"); 
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
								out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
								out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
								if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
									out.println("<td width='10%' class=div_input>&nbsp;</td>");
								}
								else
								{
									/*
									if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
									   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
									else
										out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
									*/
									
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
									
								}
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								
								/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
								if(m_cum_value>0){
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								else
								{
									out.println("<td width='4%' class=div_input>Cr</td>");
								}*/
								
								
								
								
								if (!mm_flag_insurance){
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
								}else{
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								if (mm_flag_insurance){
									out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
									out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
								}else{
									out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
									out.println("<td width='4%' class=div_input>&nbsp;</td>");
								}
								
								
								out.println("</tr>");
							}
						}
						//---------------------------------------------------------------------------------------------------
						
						
					}
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else
						{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) // added by udara on 01-01-2013
								out.println("<td width='10%' class=div_input> &nbsp; </td>");
							else
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							*/
							out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							
							
						}
						if(rs.getString(7).equals("RET") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						}
						
						/*
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					else if(rs.getString(4).equals("OTHER")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					/*else if(rs.getString(4).equals("BAL")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}*/
					
					else if(rs.getString(4).equals("BAL")){
						
						//if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){ //comment by nuwan de silva 10-08-2009
						if(rs.getString(7).equals("RET")  || rs.getString(7).equals("C") ){
							out.println("<tr >");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
							out.println("<td width='30%' class=div_input>Receipt</td>"); //"+rs.getString(6)+"
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input> &nbsp; </td>");
								else
									out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
								*/
								out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							}
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							
							/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							*/
							
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
								out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
							
							//m_val=m_debit;
							//m_cum_value=m_cum_value-m_val;
							m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
							/*if (m_cum_value<0 && m_val<0)
							{
							m_cum_value=m_cum_value-m_val;
							}
							else if (m_cum_value>=0 && m_val<0)
							{
							m_cum_value=m_cum_value-m_val;
							}
							else{
							m_cum_value=m_cum_value+m_val;
							}*/
							//added by SH on 15-01-2009 ***********
							if(m_val>0){ 
								if (m_cum_value>0)
								{
									m_cum_value=m_cum_value-m_val;
								}					
								else{
									m_cum_value=m_cum_value+m_val;
								}
							}else{
								//end of addition ******************
								if (m_cum_value<0 && m_val<0)
								{
									m_cum_value=m_cum_value-m_val;
								}
								else if (m_cum_value>=0 && m_val<0)
								{
									m_cum_value=m_cum_value-m_val;
								}
								else{
									m_cum_value=m_cum_value+m_val;
								}
								
							}//added by SH on 15-01-2009 ***********
							
							out.println("<tr >");
							out.println("<td width='1%'><span "+mm_row_color+"></span></td>"); 
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
							out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"  </span></td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">&nbsp;</span></td>");
							}
							else{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>"); // added by udara on 01-01-2012
								else
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
								*/
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							}
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							
							/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}else{
								out.println("<td width='4%' class=div_input>Cr</td>");
							}
							*/
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
						}
						else{
							
							out.println("<tr >");
							out.println("<td width='1%'><span "+mm_row_color+"></span></td>"); 
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
							out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
							out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
							if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
								out.println("<td width='10%' class=div_input>&nbsp;</td>");
							}
							else
							{
								/*
								if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
								   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
								else
									out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
								*/
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							}
							
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
							
							/*
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
							if(m_cum_value>0){
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							else
							{
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
							}
							*/
							
							
							if (!mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							if (mm_flag_insurance){
								out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
								out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
							}else{
								out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
								out.println("<td width='4%' class=div_input>&nbsp;</td>");
							}
							
							out.println("</tr>");
						}
						
					}
					
					else if (rs.getString(4).equals("RETURN_DEBIT")){
						
						/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
						
						if (m_cum_value >0 ){
						m_cum_value=m_cum_value+m_val;
						}
						else{
						m_cum_value=m_cum_value-m_val;
						}
						*/
						
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input> &nbsp; </td>");
							else
								 out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
							*/
							
							out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						}
						out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						out.println("</tr>");
						
					}
					
					else if (rs.getString(4).equals("DEBIT_CANCEL")){
						
						/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
						
						if (m_cum_value >0 ){
						m_cum_value=m_cum_value+m_val;
						}
						else{
						m_cum_value=m_cum_value-m_val;
						}
				*/
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span>  </td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					else if (rs.getString(4).equals("INV_REV")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						} 
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">Cr</span></td>");
						}*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					else if (rs.getString(4).equals("CREDIT_CANCEL")){
						
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(2)+"</span></td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><span "+mm_row_color+"><u>"+rs.getString(1)+"</u></span></td>");
						out.println("<td width='30%' class=div_input><span "+mm_row_color+">"+rs.getString(6)+"</span></td>");
						if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
							out.println("<td width='10%' class=div_input>&nbsp;</td>");
						}
						else{
							/*
							if(rs.getString(6).equals("Rental Payment") || rs.getString(6).equals("Insurance Payment")) 
							   out.println("<td width='10%' class=div_input><span "+mm_row_color+"> &nbsp; </span></td>");
							else
								out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
							*/
							out.println("<td width='10%' class=div_input><span "+mm_row_color+">"+rs.getString(5)+"</span></td>");
						}
						
						out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(rs.getDouble(3))+"</span></td>");
						out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						
						/*out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value))+"</span></td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}else{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_rental_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'><span "+mm_row_color+">"+nf1.format(a.abs(m_cummulative_ins))+"</span></td>");
							out.println("<td width='4%' class=div_input><span "+mm_row_color+">"+mm_insurance_drcr+"</span></td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
						
					}
					
					
					else if(rs.getString(4).equals("DR/CR")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						if(rs.getString(7).equals("DR") ){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(rs.getDouble(3))+"</td>");
						}
						
						/*out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value))+"</td>");
						if(m_cum_value>0){
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						else
						{
							out.println("<td width='4%' class=div_input>Cr</td>");
						}
						*/
						
						
						if (!mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cum_value-m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_rental_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						if (mm_flag_insurance){
							out.println("<td width='10%' class=div_input align='right'>"+nf1.format(a.abs(m_cummulative_ins))+"</td>");
							out.println("<td width='4%' class=div_input>"+mm_insurance_drcr+"</td>");
						}else{
							out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
							out.println("<td width='4%' class=div_input>&nbsp;</td>");
						}
						
						out.println("</tr>");
					}
					
					
					more_inv = rs.next();
					
					
					if(!more_inv){
						out.println("<tr>");
						out.println("<td colspan='13'>");
						out.println("<br>");
						out.println("<input type='button' value='Cumulative ODI' name='VIEW_ODI' class='but_input' style='width:150px' onclick=\"view_odi('"+m_application_no+"')\">");
						out.println("<br>");
						out.println("</td>");
						out.println("</tr>");
					}
				}
				out.println("</table>");
				
				
				
				
				
				String		Sql_Unallocated=" SELECT "+ 
					"  A.REC_NO, "+
					"  A.REC_AMOUNT, "+
					"  B.allocated_amount, "+
					"  B.bal_tobe_receive, "+
					"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"  WHERE  A.REC_NO=B.REC_NO  "+
					"  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
				
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				boolean  more =rs.next();
				
				if (more) {
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);
					
					more = rs.next();
				}
				
				if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				//receipt contracl level unallocated receipt details .......................................................
				
				/*String		Sql_Unallocated=" SELECT "+ 
				"  A.REC_NO, "+
				"  A.REC_AMOUNT, "+
				"  B.allocated_amount, "+
				"  B.bal_tobe_receive, "+
				"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
				"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
				"  WHERE  A.REC_NO=B.REC_NO  "+
				"  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
				*/
				
				Sql_Unallocated=" SELECT "+ 
					" a.rec_no ,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') , "+ 
					" a.finance_no,"+
					" a.rec_amount, "+
					" a.app_rec_amount, "+
					" a.allocated_amount, "+
					" a.bal_tobe_receive "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a ,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')  "+
					" AND  A.BAL_TOBE_RECEIVE > 0   "+
					" AND B.STATUS NOT IN ('C','CAD','RET')  "+
					" ORDER BY  EFF_VALDATE  ";
				
				
				rs=stmt1.executeQuery(Sql_Unallocated);
				more =rs.next();
				
				if (more) {
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details Contract Level</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='15%' class=div_input ><b>Contract No</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount To This Contract</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='15%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(7);
					
					more = rs.next();
				}
				
				if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				
				//================Added by Sandun on 30-07-2008======================================================================
				
				
				
				String		Sql_Pod_Cheque_Hand= " SELECT "+ 
					"  NVL(POD_REF_NO,'-'), "+//1
					"  NVL(FINANCE_NO,'-'), "+//2
					"  NVL(CHEQUE_NO,'-'), "+//3
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
					"  NVL(PAYER_ACC_NO,'-'), "+//5
					"  NVL(PAYER_BRANCH_CODE,'-'), "+//6
					"  NVL(CHEQUE_AMOUNT,0), "+//7
					"  DECODE(STATUS,'APP','Approved','CAN','Dis Approved','HOL','Hold','INV','Entered','REC','Receipt Generated','WIT','Withdraw',STATUS) "+
					"  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
					"  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  AND STATUS IN ('INV' ,'APP') " ;
				
				
				
				rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
				
				
				boolean  more1=rs.next();
				if(more1){
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='100%' class=div_input><u><b>Post Dated Cheque Details - Finance No: "+m_finance_no+"</b></u></td>");					
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					/*
				if (!more1) {
							out.println("<table align='center' width='100%' class='table' >");
							out.println("<tr>");
							out.println("<td width='1%'></td>"); 
							out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							out.println("</table>");
						}
						*/
					
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='10%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
				//out.println("<table align='center' width='100%' class='table' >");
				while(more1){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input align='left'>"+rs.getString(8)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more1 = rs.next();
				}
				
				
				out.println("</table>");
				
				
				
				//==============================End on 30-07-2008========================================================
				
				//..........................................................................................................
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				
				rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS,ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
					" WHERE CLIENT_CODE ='"+m_client_code+"'  "+
					" AND ( APPLICATION_NO ='"+m_finance_no+"' "+
					" OR    APPLICATION_NO ='"+m_application_no+"' )"+
					" ORDER BY ENT_DATE DESC ");
				
				
				/*rs=stmt1.executeQuery("SELECT TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),A.COMMENTS,A.ENT_USER "+
											" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
																" WHERE A.CLIENT_CODE ='"+m_client_code+"' "+
																" AND A.APPLICATION_NO=B.APPLICATION_NO "+
																" AND (B.FINANCE_NO='"+m_finance_no+"' "+
																" OR  B.FINANCE_NO='"+m_finance_no+"' )"+
																" ORDER BY A.ENT_DATE DESC ");
																
																*/
				
				boolean  more4 =rs.next();			
				
				if(more4){
					out.println("<table align='center' width='100%' class='table' >");
					while(more4){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input valign='top'><p><B>Date: </B>"+rs.getString(1)+" &nbsp <br><B>User: </B>"+rs.getString(3)+"</p></td>");
						out.println("<td width='2%'>&nbsp</td>");
						out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr></tr>");
						more4 =rs.next();
					}
					out.println("</table>");
				}
				else{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<br>");
				out.println("<br>");
				out.println("<table></table>");
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Collection</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				
				// added by udara 06-04-2015
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Contract Status</B></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='20%'><b> Status </b></td>"); 
				out.println("<td width='10%'><b> Entered User </b></td>"); 
				out.println("<td width='20%'><b> Entered Date </b></td>"); 
				out.println("<td width='*%'><b> &nbsp; </b></td>"); 
				out.println("</tr>");
				
				rs=stmt1.executeQuery(" "+
					" select  "+
					" FINANCE_NO, "+
					" STATUS, "+
					" ENT_USER, "+
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS') "+
					" from ( "+
					
					" select FINANCE_NO, "+
					" DECODE(STATUS,'PERFORM','Perform','NPERFORM','Non Perform',STATUS) STATUS, "+
					" ENT_USER,  "+
					" NVL(MOD_DATE,ENT_DATE) ENT_DATE  "+ // " ENT_DATE "+
					" from "+m_schema_name+".AF_CO_PERFORMING_CONTACTS "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					
					" UNION all "+
					
					" select FINANCE_NO, "+
					" DECODE(STATUS,'PERFORM','Perform','NPERFORM','Non Perform',STATUS) STATUS, "+
					" ENT_USER, "+
					" NVL(MOD_DATE,ENT_DATE) ENT_DATE "+
					" from "+m_schema_name+".AF_CO_PERFORMING_CONTACTS_BK "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					
					" union all "+
					
					" select a.FINANCE_NO FINANCE_NO,  "+
					" B.STATUS STATUS,  "+
					" B.ENTUSER ENT_USER,  "+
					" B.ENTDATE ENT_DATE "+
					" from "+m_schema_name+".AF_RE_PRO_REPOSSESSION a, "+m_schema_name+".AF_RE_REPOSSESSION_STATUS B "+
					" where a.REPOSSESSION_NO = B.ref "+
					" AND A.FINANCE_NO = '"+m_finance_no+"' "+
					
					" ORDER BY ENT_DATE DESC "+
					" ) "+
					" ");
				
				while(rs.next()){
					out.println("<tr>");
					out.println("<td width='20%'> "+rs.getString(2)+" </td>"); 
					out.println("<td width='10%'> "+rs.getString(3)+" </td>"); 
					out.println("<td width='20%'> "+rs.getString(4)+" </td>"); 
					out.println("<td width='*%'> &nbsp; </td>"); 
					out.println("</tr>");
				}
				
				out.println("</table>");
				
				
				out.println("<hr color='#2F4F4F'>");
				out.println("<br>");
				// end by udara 06-04-2015
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			else if(m_chksql.equals("enter_comments")){
				String m_client_code=req.getParameter("client_code");
				String m_application_no=req.getParameter("application_no");
				String m_finance_no ="";
				
				String sql_col_status  = " SELECT FINANCE_NO "+
					" FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
					" WHERE APPLICATION_NO = '"+m_application_no +"'";
				rs2 = stmt2.executeQuery(sql_col_status);
				if(rs2.next()){
					m_finance_no=rs2.getString(1);
				}
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System - Collection Follow up Comments</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Followup_comments';");  
				out.println("			document.Form1.submit();"); 
				out.println("		}"); 
				out.println("} "); 
				
				out.println("</Script>");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<INPUT TYPE='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='hidden' value='"+m_application_no+"' name='APPLICATION_NO'> "); 
				out.println("<INPUT TYPE='hidden' value='"+m_client_code+"' name='CLIENT_CODE'> "); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'></td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Follow up Comments</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  			
				out.println("</table>");  
				out.println("</td></tr><tr>");   
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos'  valign='top'>");    
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='60%'>"); 
				out.println("<tr >");
				out.println("<td width=\"30%\" class='tr_input'><b>Remarks</b></td>"); 
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='30%' ><TEXTAREA class='txt_input' name='TXT_COMMENT' style=\"width:400px; height:150px;\" maxlength=\"2000\" size=\"2000\"></TEXTAREA></td>");  //modified by nuwan de silva on 14-09-07
				out.println("</tr>");
				out.println("</table>");    
				out.println("<hr>");
				
				/*rs=stmt1.executeQuery(" SELECT NVL(COMMENTS,'-'), "+
				" ENT_USER, "+
				" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+
				" NVL(COMMENTS,'-') "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
				" WHERE CLIENT_CODE='"+m_client_code+"' "+
				" AND APPLICATION_NO='"+m_application_no+"' "+
				" ORDER BY ENT_DATE DESC ");
				*/
				
				rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS,ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
					" WHERE CLIENT_CODE ='"+m_client_code+"'  "+
					" AND ( APPLICATION_NO ='"+m_finance_no+"' "+
					" OR    APPLICATION_NO ='"+m_application_no+"' )"+
					" ORDER BY ENT_DATE DESC ");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Enter User</b></td>"); 
				out.println("<td width=\"25%\" class='tr_input'><b>Enter Date/Time</b></td>"); 
				out.println("<td width=\"60%\" class='tr_input'><b>Comments</b></td>"); 
				out.println("</tr>");
				
				while(rs.next()){
					out.println("<tr >");
					out.println("<td width=\"20%\" class='tr_input'>"+rs.getString(3)+"</td>"); 
					out.println("<td width=\"25%\" class='tr_input'>"+rs.getString(1)+"</td>"); 
					out.println("<td width=\"60%\" class='tr_input'>"+rs.getString(2)+"</td>"); 
					out.println("</tr>");
				}
				out.println("</table>"); 
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</html>");
			}
			
			else if(m_chksql.equals("update_contact_detail")){ //Added By Sandun on 19-11-2008
				
				String m_client_code=req.getParameter("client_code");
				String m_application_no=req.getParameter("application_no");
				String m_client_name   = "";
				String m_client_moble  = "";
				String m_client_tel    = "";
				String m_client_fax    = "";
				String m_client_person = "";
				String m_client_off_tel="";
				
				rs2 = stmt2.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" A.FULL_NAME, "+//2
					" NVL(A.TEL_NO,'-'), "+//3       
					" NVL(A.MOBILE_NO,'-'), "+//4
					" NVL(A.FAX_NO,'-'), "+//5
					" NVL(CONTACT_FOR_PAYMENT,'-'), "+//6
					" NVL(OFFICE_TEL_NO,'-') "+//7
					" FROM  "+m_schema_name+".AF_CO_MAS_CLIENT A "+
					" WHERE A.CLIENT_CODE ='"+m_client_code+"' ");	
				
				if(rs2.next()){
					m_client_name      = rs2.getString(2);
					m_client_moble     = rs2.getString(4);
					m_client_tel       = rs2.getString(3);
					m_client_fax       = rs2.getString(5);
					m_client_person    = rs2.getString(6);
					m_client_off_tel   = rs2.getString(7);
				}
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System - Contact Details Updation</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("		help_box.innerHTML=\" Contact Details Updation - \"+m_val;"); 
				out.println("}"); 
				
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				
				out.println("function close_window(){	"); 
				out.println("window.close();"); 
				out.println("}"); 
				
				out.println("function before_submit(){ "); 			
				out.println("		if(confirm(\"Are you sure you want to Modify Record?\")){ "); 
				out.println("document.Form1.TXT_CLIENT_CODE.disabled=false;");
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_client_contact_updation';");  
				out.println("			document.Form1.submit();"); 
				out.println("		}"); 
				out.println("} "); 
				
				out.println("</Script>");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<INPUT TYPE='hidden' value='CLIENT_CONTACT_UPDATE' name='hid_scr_name'> "); 			 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'></td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Contact Details Updation</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='10%'></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_value(\"Save\")' onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_value(\"Close\")' onClick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  			
				out.println("</table>");  
				out.println("</td></tr><tr>");   
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos'  valign='top'>");
				out.println("<hr><br><br>");
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='60%'>"); 
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Client Code</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_CODE' value=\""+m_client_code+"\" disabled></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Client Name</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_NAME' style='width:300'  value=\""+m_client_name+"\" disabled></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Contact Person</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_CON_PERSON'  value=\""+m_client_person+"\"></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Mobile No</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_MOBIL'  value=\""+m_client_moble+"\"></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Tel No</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_TEL'  value=\""+m_client_tel+"\"></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Office Tel No</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_TEL_OFFICE'  value=\""+m_client_off_tel+"\"></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Fax No</b></td>");
				out.println("<td width=\"30%\" class='tr_input'><input type='text' class='txt_input' name='TXT_CLIENT_FAX'  value=\""+m_client_fax+"\"></td>");
				out.println("</tr>");
				out.println("</table>");    
				
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("</html>");
			}
			
			
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