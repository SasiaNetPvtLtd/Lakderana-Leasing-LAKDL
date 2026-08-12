// DEVELOP BY : SANDUN FOR OFSCL  LEASING AND LOANS    
// DATE:22-08-2008

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Insurance_Ledger extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf1,nf;
	Statement stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1;
	
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
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			stmt1 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			String m_finance_no=req.getParameter("finance_no");
			String Insurance_Sql="";
			
			int count = 1;
			double m_tot_invoice=0.0;
			double m_tot_settled=0.0;
			double m_tot_balanced=0.0;
			double m_tot_amount=0.0;
			
			
			
			if(m_chksql.equals("run_report")){ 
				String m_date=req.getParameter("date");	
				String m_client_code=req.getParameter("client_code").trim();
				String m_coll_officer=req.getParameter("coll_officer").trim();
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_INSURANCE_LEDGER(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_client_code);
					callstmt1.setString(3,m_coll_officer);
					callstmt1.setString(4,m_username);
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
				out.println("<TITLE>Collection - Insurance Ledger</TITLE>"); 
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
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=view_screen';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=view_screen';"); 
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
				out.println("		help_box.innerHTML=\" Collection Process - Insurance Ledger - \"+m_val;"); 
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
				
				out.println("function help_button_collection_officer() {"); 
				out.println(" document.Form1.hid_help_type.value='3';");
				out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
				out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
				out.println("}"); 
				
				out.println("function help_value_assign_collection(oBj) {"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=run_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value;"); 
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
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=print_report&date=\"+m_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value ;"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Insurance Ledger</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' value=\"Help\"></td>");  
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
				out.println("<tr>");
				out.println("<td  width='10%' >Insurance Done By</td>"); 
				out.println("<td ='15%' ><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:100px;\" >");
				//out.println("<option value=\"LICENSEE\" >Licensee</option>");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"LICENSEE\" >Company</option>");
				out.println("<option value=\"BROKER\" >Broker</option>");
				out.println("<option value=\"CLIENT\" >Lessee</option>");
				
				out.println("</select>");
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
				
				String mdate = req.getParameter("date"); 
				
				String m_insurence_done    = "";
				
				if(req.getParameter("insurance_done").equals("ALL") ){
					m_insurence_done   = ""; 
				}else{
					m_insurence_done   = req.getParameter("insurance_done"); 
				}
				
				
				out.println("<HTML><HEAD><TITLE>Collection Process - Insurance Legder </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_insurance_details(val,val1){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=SHOW_INSURANCE_BY_CONTRACT&date='+val1+'&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function finance_drill(finance,client){ ");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+client+\"&finance_no=\"+finance+\"\";");
                out.println("window.open(m_url,'displayWindow12','left=110,top=90,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
                out.println("}");
				
				out.println("</script>");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Collection Process - Insurance Ledger As At "+mdate+"</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table width='*%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Finance No<DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Client Name</b></DIV></td>");
				//out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Insurance Officer</b></DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Pending Receipts Client </b></DIV></td>");
				out.println("<td width='12%' align=center><DIV class=factoring-letter-body><b>Pending Receipts Contract</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Total Insurance</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Settled Amount</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Outstanding Amount</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>No.Outstanding Invoices</b></DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 0 or Less<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 1<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 2<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 3<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 4<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 5<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 6<DIV></td>");
				//out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>>Age 6<DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 0-3<DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 3-6<DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 6-9<DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>Age 9-12<DIV></td>");
				out.println("<td width='8%' align=center><DIV class=factoring-letter-body><b>>Age 12<DIV></td>");
				
				out.println("</tr>");
				
				rs1= stmt1.executeQuery(" SELECT APPLICATION_NO, "+//1
					" FINANCE_NO, "+//2
					" CLIENT_CODE, "+//3
					" CLIENT_FULL_NAME, "+//4
					" CLIENT_ADDRESS, "+//5
					" CLIENT_COLLECTOR, "+//6
					" TOTAL_AMOUNT, "+//7
					" SETTLED_AMOUNT, "+//8
					" UNALLO_RECEIPTS_CLIENT, "+//9
					" UNALLO_RECEIPTS, "+//10
					" ENT_USER, "+//11
					" ENT_DATE, "+//12
					" INVOICE_COUNT, "+//13
					" ARREAS_AGE_0, "+//14
					" ARREAS_AGE_1, "+//15
					" ARREAS_AGE_2, "+//16
					" ARREAS_AGE_3, "+//17
					" ARREAS_AGE_4, "+//18
					" ARREAS_AGE_5, "+//19
					" ARREAS_AGE_6, "+//20
					" ARREAS_AGE_7 "+//21
					" FROM "+m_schema_name+".AF_RE_TBD_INSURANCE_LEDGER "+
					" WHERE ENT_USER='"+m_username+"'  "+
					" AND UPPER("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY(APPLICATION_NO)) LIKE TRIM(UPPER('%"+m_insurence_done+"%')) "); 
				
				
				
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#FFFFFF\">");
						
						j=0;
					}
					
					out.println("<td width='10%' class=factoring-letter-body align='left' style='cursor:hand' onclick=\"finance_drill('"+rs1.getString(2)+"','"+rs1.getString(3)+"')\">"+rs1.getString(2)+"</td>");
					out.println("<td width='12.5%' class=factoring-letter-body align='left' style='cursor:hand' onclick=\"show_client('"+rs1.getString(3)+"')\">"+rs1.getString(4)+"</td>");
					//out.println("<td width='12.5%' class=factoring-letter-body align='left'>"+rs1.getString(4)+"</td>");   //commented By Sandun on 26-08-2008
					//out.println("<td width='10%' class=factoring-letter-body align='left'>-</td>");
					out.println("<td width='10%' class=factoring-letter-body align='right'>"+nf1.format(rs1.getDouble(9))+"</td>");
					out.println("<td width='12%' class=factoring-letter-body align='right'>"+nf1.format(rs1.getDouble(10))+"</td>");
					out.println("<td width='12.5%' class=factoring-letter-body align='right' style='{cursor:hand}' onClick=\"show_insurance_details('"+rs1.getString(2)+"','"+mdate+"')\">"+nf1.format(rs1.getDouble(7))+"</td>");
					out.println("<td width='12.5%' class=factoring-letter-body align='right' style='{cursor:hand}' onClick=\"show_insurance_details('"+rs1.getString(2)+"','"+mdate+"')\">"+nf1.format(rs1.getDouble(8))+"</td>");
					out.println("<td width='12.5%' class=factoring-letter-body align='right' style='{cursor:hand}' onClick=\"show_insurance_details('"+rs1.getString(2)+"','"+mdate+"')\">"+nf1.format(rs1.getDouble(7)-rs1.getDouble(8))+"</td>");
					out.println("<td width='12.5%' class=factoring-letter-body align='right' style='{cursor:hand}' onClick=\"show_insurance_details('"+rs1.getString(2)+"','"+mdate+"')\">"+rs1.getString(13)+"</td>");//Added By Sandun on 26-08-2008
					out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
					out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
					out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(16))+"</td>");
					out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
					out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
					//out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(19))+"</td>");
					//out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
					//out.println("<td width='8%' class=factoring-letter-body align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}	
			
			else 
				if(m_chksql.equals("SHOW_INSURANCE_BY_CONTRACT")){				 		
					
					out.println("<HTML><HEAD><TITLE> Insurance Details </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Insurance Details - Finance No: "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
					String mdate = req.getParameter("date"); 	
					
					
					Insurance_Sql = "	SELECT "+
						" A.RECEIPT_NO, "+ //1
						" NVL(A.INVOICE_NO,'-'), "+ //2
						" A.ALLOCATION_NO, "+ //3 
						" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+ //4
						" A.INVOICED_AMOUNT, "+ //5 
						" A.SETTELED_AMOUNT, "+//6  
						" B.SETTELE_AMOUNT, "+//7
						" B.BALANCE_TO_BE_RECEIVED, "+ //8 
						" B.FINANCE_NO ,"+//9
						" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'), "+//10
						" B.TOTAL_AMOUNT, "+//11
						" B.ENT_USER "+//12
						"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
						"	WHERE A.INVOICE_NO(+) = B.INVOICE_NO AND "+
						" B.INVOICE_TYPE='INSURANCE' "+//INSURANCE
						"	AND UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
						" AND B.VALUE_DATE <= TO_DATE('"+mdate+"','DD-MM-YYYY') "+
						" ORDER BY B.VALUE_DATE ";
					/*
					" UNION "+
					" SELECT "+
					" A.RECEIPT_NO,  "+
					" A.INVOICE_NO,  "+
					" A.ALLOCATION_NO,  "+
					" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),  "+
					" A.INVOICED_AMOUNT,  "+ 
					" A.SETTELED_AMOUNT, "+
					" C.SETTELE_AMOUNT, "+
					" C.BALANCE_TO_BE_RECEIVED, "+
					" C.FINANCE_NO, "+
					" TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY'), "+
					" C.TOTAL_AMOUNT, "+
					" C.ENT_USER "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY B ,"+m_schema_name+".AF_CO_PRO_INVOICE C  "+
					" WHERE A.INVOICE_NO(+)=B.ODI_REF_NO "+
					" AND A.INVOICE_NO=C.INVOICE_NO "+
					" AND C.INVOICE_TYPE='INSURANCE' "+
					" AND UPPER(C.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
					" AND C.VALUE_DATE <= TO_DATE('"+mdate+"','DD-MM-YYYY') "+
					" ORDER BY VALUE_DATE " ;	
					*/
					rs = stmt1.executeQuery(Insurance_Sql);
					boolean more =rs.next();	
					
					if (!more) {
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='80%' class=div_input><b>No Insurance Details For Finance No: "+m_finance_no+"</b></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					if (more) {
						out.println("<table width='90%' border='0' class='table' >");
						out.println("<tr>");
						out.println("<td width='5%' class=div_input align='left'><b>No.</b></td>");
						out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
						out.println("<td width='10%' class=div_input align='left' ><b>Value Date </b></td>");
						out.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right' ><b>Settled Amount</b></td>");
						out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>");
						out.println("<td width='3%' class=div_input align='left' ><b>&nbsp;</b></td>");
						out.println("<td width='18%' class=div_input align='left' ><b>Insurance Officer</b></td>");
						out.println("</tr>");
						
						while(more){				
							out.println("<tr>");
							out.println("<td width='5%'  class=div_input align='left' >"+count+++"</td>");  
							out.println("<td width='15%' class=div_input align='left'  >"+rs.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input align='left' >"+rs.getString(10)+"</td>");
							out.println("<td width='15%' class=div_input align='right' >"+nf1.format(rs.getDouble(11))+"&nbsp;</td>");
							out.println("<td width='15%' class=div_input align='right' >"+nf1.format(rs.getDouble(7))+"&nbsp;</td>");
							out.println("<td width='15%' class=div_input align='right' >"+nf1.format(rs.getDouble(8))+"&nbsp;</td>");
							out.println("<td width='3%' class=div_input align='left' ><b>&nbsp;</b></td>");
							out.println("<td width='18%' class=div_input align='left' >"+rs.getString(12)+"</td>");
							out.println("</tr>");
							m_tot_invoice+=rs.getDouble(11);
							m_tot_settled+=rs.getDouble(7);
							m_tot_balanced+=rs.getDouble(8);
							more = rs.next();
						}
						
						out.println("<tr>");
						out.println("<td width='5%' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%' align='left'><B>Total</td>"); 
						out.println("<td width='15%' align='right'><b>"+nf1.format(m_tot_invoice)+"&nbsp;</td>"); 
						out.println("<td width='15%' align='right'><b>"+nf1.format(m_tot_settled)+"&nbsp;</td>"); 
						out.println("<td width='15%' align='right'><b>"+nf1.format(m_tot_balanced)+"&nbsp;</td>");
						out.println("<td width='3%'  align='left' ><b>&nbsp;</b></td>");
						out.println("<td width='18%' align='left'>&nbsp;</td>"); 
						out.println("</tr>");
					}
					
					out.println("</table>");
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
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
