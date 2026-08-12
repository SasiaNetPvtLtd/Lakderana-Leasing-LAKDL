// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Report_With_Age extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2,rs3,rs4,rs5;
  java.lang.Math a;

	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
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
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=print_report\";"); 
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
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(12))+"</td>");
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
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Receipt - Chq Return','Receipt - Cheque'),'CASH','Receipt - Cash') DESCRIPTION, "+ //6
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
													" WHERE A.INVOICE_TYPE=B.INVOICE_TYPE_CODE(+) "+
													" AND  BALANCE_TO_BE_RECEIVED > 0 "+
													" AND INVOICE_TYPE NOT IN('INV_GENER','LEGAL_CAP','LEGAL_ODI','LEGAL_ARR') "+//Modified by Dineth on 19-06-2009
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
				
					out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Transaction History - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

					
					String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					"  NULL STATUS ,"+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"        ACTIVE_STATUS='Y'  "+
					"        ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+
					"   AND INVOICE_TYPE NOT IN ('LEGAL_ODI') "+
					" AND VALUE_DATE <=SYSDATE "+  
					
					"  UNION "+
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') DESCRIPTION,  "+
					" STATUS,  "+
					" '' NO "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+//,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO "+
					//" A.INVOICE_NO=C.INVOICE_NO AND "+
					//" C.FINANCE_NO='"+m_finance_no+"' "+
					
					" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' "+
					
          "                   UNION ALL "+
          "                   SELECT ODI_REF_NO "+
          "                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
          "                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
          "                   WHERE FINANCE_NO='"+m_finance_no+"' )) "+
					
					" AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					
					
					
					"	UNION "+
					/*" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+*/
					
					
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" C.ALLOCATED_DATE DUE_DATE, "+//A.DUE_DATE  DUE_DATE, "+
					" C.SETTELED_AMOUNT AMOUNT, "+//A.ODI_SETTLED_AMOUNT AMOUNT, "+
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'Over Due Interst' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+//m_schema_name+".AF_CO_PRO_INVOICE B, "+
					"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE "+//A.ODI_SETTLED_AMOUNT > 0 "+
					//"     A.INVOICE_NO=B.INVOICE_NO "+
					"     C.INVOICE_NO=A.ODI_REF_NO "+
					//" AND B.CLIENT_CODE='"+m_client_code+"'  "+
					" AND ODI_SETTLED_AMOUNT > 0 "+
					" AND A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"          ACTIVE_STATUS='Y' "+
					"            ACTIVE_STATUS IN ('Y','DB_CAN')  "+
					") "+
					" AND DUE_DATE <=SYSDATE "+  

					
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE NOT IN ('LEGAL_AD') "+
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					//--------------------------Sandun on 18-03-2009---------------------------------
					
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note - Legal Termination') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
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
					
					
					" UNION"+
					
					" SELECT B.INVOICE_NO REF_NO, "+
				  " NVL(B.INV_REV_DATE,B.VALUE_DATE) DUE_DATE,  "+
				  " B.TOTAL_AMOUNT AMOUNT,  "+
				  " 'INV_REV' TYP, "+
				  " '-'  CHEQUE_NO ,   "+
				  " 'Invoice Cancel '  DESCRIPTION,     "+
				  " '-'  STATUS,   "+ 
				  " 'CR' STYPE "+
				  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE B "+
				  " WHERE "+				  
				  " B.FINANCE_NO = '"+m_finance_no+"' "+
				  " AND B.ACTIVE_STATUS = 'C' "+
					" AND VALUE_DATE <= SYSDATE "+
					
					//------------------------------------------------------------
					
					" UNION "+
					
					" SELECT a.rec_no REF_NO , "+
					" b.eff_valdate  DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO   "+ //7
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					
					" UNION "+
					
					" SELECT a.rec_no REF_NO , "+
					" NVL(B.rec_cancel_date,b.eff_valdate)  DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'CAD_BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO   "+ //7
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and b.status ='CAD' "+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
          					
					" UNION "+
					
					"	SELECT    "+
					"	A.REC_NO REF_NO,   "+
					//"	C.ENT_DATE DUE_DATE,  "+ //ADDDE BY NUWAN DE SILVA 03-11-2008
					" B.REALISED_DATE DUE_DATE,"+
					"	NVL(A.BAL_TOBE_RECEIVE,0)  AMOUNT,   "+
					"	'RETURN_DEBIT' TYP,  "+
					"	B.CHEQUE_NO  CHEQUE_NO ,  "+
					"	'Cheque Return '  DESCRIPTION,    "+
					"	B.STATUS STATUS,   "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" ,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS C "+
					" WHERE A.REC_NO=B.REC_NO "+
					"	AND   A.REC_NO=C.RECEIPT_NO(+) "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='RET' "+
						
					" UNION "+
						
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	NVL(AMOUNT,0)  AMOUNT,   "+
					"	'DEBIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Debit Note Cancelation '  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					
					" AND   TRN_DATE <=SYSDATE "+  
					
					
					" UNION "+
					
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	NVL(ADJUSTED_AMOUNT,0)  AMOUNT,   "+
					"	'CREDIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Credit Note Cancelation '  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+					
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_CR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					" AND   TRN_DATE <=SYSDATE "+  
					
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";

				  rs=stmt1.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();
					String m_application_no="";
					while(more_inv){
					count++;
					if(count==1){
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
																	 " ,APPLICATION_NO,"+m_schema_name+".af_co_get_client_name('"+m_client_code+"') "+
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
					
					out.println("<hr color='black'>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
										

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
					else if(rs.getString(4).equals("CAD_BAL")){ //added for receipt cancelation entry
					m_debit=rs.getDouble(3);
					}
					
					m_val=m_debit-m_credit;
					
					if(!rs.getString(4).equals("CAD_BAL")){ //added by nuwan de silva 21-04-2009
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 
					m_cum_value=m_cum_value-m_credit;
					}
					else{
					m_cum_value=m_cum_value+m_val;
					}
					}

					//m_cum_value=m_cum_value+m_val;
          					
					if(rs.getString(4).equals("INVOICE")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					//if(rs.getString(7).equals("RET") ){
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
          else if(!rs.getString(7).equals("RET") ){
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
					
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					
					if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>");
					
					/*if(rs.getString(7).equals("CAD")){
					
					rs5 = stmt4.executeQuery(" SELECT TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY') "+
												           " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
												           " WHERE B.REC_NO = '"+rs.getString(1)+"' ");				
					if(rs5.next()){
					out.println("<td width='10%' class=div_input>"+rs5.getString(1)+"</td>");
					}
					}else{
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					}
					*/
					
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>Receipt</td>"); //"+rs.getString(6)+"
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					
					/*______________________________06-04-2009 COMMENT FOR REC CANCEL_____________________________
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
					____________________________06-04-2009 COMMENT FOR REC CANCEL___________________________________*/
					
          /***********************************************************************************************    
     			out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
          **********************************/		
					
          }
					else{
					
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
					}
					
					}
					
					else if(rs.getString(4).equals("CAD_BAL")){					
					
					if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					
					m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
					
					/*______________________________06-04-2009 COMMENT FOR REC CANCEL_____________________________*/
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
						//m_cum_value=m_cum_value-m_val;
						m_cum_value=m_cum_value+m_val;//21-04-2009
						}
						else if (m_cum_value>=0 && m_val<0)
						{
						m_cum_value=m_cum_value-m_val;
						}
						else{
						m_cum_value=m_cum_value+m_val;
						}
					
					}//added by SH on 15-01-2009 ***********
					
					/*____________________________06-04-2009 COMMENT FOR REC CANCEL___________________________________*/
					
          /***********************************************************************************************    */
     			out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
          /***********************************/		
					
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
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					else if (rs.getString(4).equals("INV_REV")){
														
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					
					else if (rs.getString(4).equals("CREDIT_CANCEL")){
															
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
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
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					more_inv = rs.next();
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
				out.println("<hr color='black'>");
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
				out.println("<hr color='black'>");
				out.println("<br>");



				
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
