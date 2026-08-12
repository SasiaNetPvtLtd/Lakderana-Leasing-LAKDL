// DEVELOP BY : DINETH 
// FOR        : OFSCL - INSURANCE    
// DATE       : 13-05-2009

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Insurance_Arrears_Age_Report extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	Statement stmt,stmt1,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1,rs2;
	
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
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);			
			
			stmt1 = conn.createStatement();
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");			
			String Insurance_Sql="";
			String m_finance_no ="";
			
			int count = 1;
			double m_tot_invoice=0.0;
			double m_tot_settled=0.0;
			double m_tot_balanced=0.0;
			double m_tot_amount=0.0;
			
			
			
			if(m_chksql.equals("run_report")){ 
				String m_date=req.getParameter("date");
				String m_ins_officer = req.getParameter("officer");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_INSURANCE_ARR_AGE(:1,:2,:3);END;");
					callstmt1.setString(1,m_date);			
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_ins_officer);
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
				out.println("<TITLE>Assert Finance</TITLE>"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=view_screen';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=view_screen';"); 
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
				out.println("		help_box.innerHTML=\" Insurance Arrears Age Movement Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("}");
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=180,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");				  
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
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
				out.println("  }");				
				out.println("}");
				
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date    = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("   m_officer = document.Form1.TXT_INSUR_CODE.value; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=run_report&officer=\"+m_officer+\"&date=\"+m_date+\"\";"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("else{");
				out.println("VDATE.style.color='red';");
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
				out.println("    m_officer = document.Form1.TXT_INSUR_CODE.value; ");
				out.println("		 	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=print_report_new&date=\"+m_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value;"); 
				out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=950,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				out.println("else{");
				out.println("VDATE.style.color='red';");
				out.println("	}");
				out.println("}");
				
				out.println("function MyDialog(){"); 
				out.println(" this.valout   = new Array(10);"); 
				out.println("}"); 
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data();");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 				
				
				
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		assign_insurance_officer(oBj);"); 
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
				out.println("	clear_data();");//Added To The Clear The Area Code
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
				
				
				out.println("function help_ins_officer(){");
				out.println("    Crit = document.Form1.TXT_INSUR_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','1');"); 
				out.println("}");
				
				out.println("function assign_insurance_officer(){"); 
				out.println("document.Form1.TXT_INSUR_CODE.value=oBj.valout[2];");
				out.println("}");
				
				out.println("function clear_data(){");
				out.println("document.Form1.TXT_INSUR_CODE.value=\"\";");
				out.println("}");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Arrears Age Movement Report</td>"); 
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
				out.println("<td width='15%'ID=VDATE>Date As At *</td>");
				out.println("<td width='20%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
				out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>");
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
				out.println("<tr>");
				out.println("<td width='15%'>Insuarance Officer</td>"); 
				out.println("<td width='20%'><input type='text' name='TXT_INSUR_CODE' class='txt_input' style='width:100px' onblur='help_ins_officer()'>&nbsp;<input type='button' value='Help' name='BUT_INSURANCE_OFF' class='but_input' onclick='help_ins_officer();'></td>"); 
				out.println("<td width='*%'></td>"); 
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
			
			else if(m_chksql.equals("print_report_new")){
				
				String mdate               = ""; 
				String m_sort_by  		   = ""; 
				String m_order_by 		   = ""; 
				String m_order             = "";
				String m_insurence_done    = "";
				
				if(req.getParameter("insurance_done").equals("ALL") ){
					m_insurence_done   = ""; 
				}else{
					m_insurence_done   = req.getParameter("insurance_done"); 
				}
				
				
				if(req.getParameter("date")!=null ){
					mdate      = req.getParameter("date"); 
				}
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_by = req.getParameter("sort_column");
					m_order_by = req.getParameter("order_by_type");
					m_order   = "ORDER BY";
				}
				else{
					m_sort_by  = "1"; 
					m_order_by = "ASC";
				}		
				
				String m_first_date    = "";
				String m_last_date     = "";
				double m_tot_open      = 0.0;
				double m_tot_close     = 0.0;
				double m_tot_month     = 0.0;
				double m_tot_balance 	 = 0.0;
				double m_cls_balance   = 0.0;
				double m_tot_cur_month = 0.0;
				double m_tot_bal       = 0.0;
				double m_curr_rental   = 0.0;
				int m_age = 0;
				
				rs = stmt.executeQuery("SELECT TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+mdate+"','DD-MM-YYYY'))+1,-1),'DD-MM-YYYY'),TO_CHAR(LAST_DAY(TO_DATE('"+mdate+"','DD-MM-YYYY')),'DD-MM-YYYY') FROM DUAL ");
				
				if(rs.next()){
					m_first_date = rs.getString(1);
					m_last_date = rs.getString(2);
				}
				rs1= stmt1.executeQuery(" SELECT "+
					" A.FINANCE_NO, "+ //1
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_CODE,  "+//2
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TEL(A.CLIENT_CODE),'-') TEL,"+//3
					" TO_CHAR(MIN(A.RENEWAL_DATE),'DD-MM-YYYY') RENEWAL_DATE,"+//4
					" SUM(NVL(A.TOTAL_AMOUNT,0) - (NVL(A.SETTLED_AMOUNT,0)+NVL(A.ADJUSTED_AMOUNT,0)+NVL(A.CUR_RENTAL_AMOUNT,0))) VALUE, "+//5
					" SUM(A.MONTH_COLLECTION) MONTH_COLLECTION , "+//6
					" '' ,"+//7
					" INITCAP(A.INSURANCE_DONE_BY) INSURANCE_DONE_BY, "+//8
					" A.CLIENT_CODE ,"+//9
					" A.APPLICATION_NO, "+//10
					" SUM(A.CUR_RENTAL_AMOUNT) RENTAL , "+//11
					" SUM(NVL(SETTLED_AMOUNT_CUR_MON,0)) SETTLED_AMOUNT, "+//12
					" "+m_schema_name+".AF_CO_GET_AGE_INSURANCE_NEW(A.FINANCE_NO), "+//13
					" "+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(A.FINANCE_NO,'"+mdate+"'), "+//14
					" (SUM(NVL(A.TOTAL_AMOUNT,0) - (NVL(A.SETTLED_AMOUNT,0)+NVL(A.ADJUSTED_AMOUNT,0)+NVL(A.CUR_RENTAL_AMOUNT,0))) + "+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(A.FINANCE_NO,'"+mdate+"')-SUM(SETTLED_AMOUNT_CUR_MON) ) TOT_BALANCE, "+//15
					" (SUM(NVL(A.TOTAL_AMOUNT,0) - (NVL(A.SETTLED_AMOUNT,0)+NVL(A.ADJUSTED_AMOUNT,0)+NVL(A.CUR_RENTAL_AMOUNT,0))) + "+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(A.FINANCE_NO,'"+mdate+"')-SUM(A.MONTH_COLLECTION) ) TOT_CLOSE_BALANCE, "+//16
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.INSURANCE_OFFICER),'-'), "+//17
					" A.CLIENT_CODE, "+//18
					" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(A.APPLICATION_NO) ASSET_SESC ,"+//19
					" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER),'-'), "+//20
					" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO_NEW(A.FINANCE_NO,'"+mdate+"',A.CLIENT_CODE),0), "+//21
					" NVL(SUM(ARREARS_AGE_0),0)+NVL("+m_schema_name+".AF_CO_GET_RENTAL_INSURANCE(A.FINANCE_NO,'"+mdate+"'),0), "+//22
					" NVL(SUM(ARREARS_AGE_1),0), "+//23
					" NVL(SUM(ARREARS_AGE_2),0), "+//24
					" NVL(SUM(ARREARS_AGE_3),0), "+//25
					" NVL(SUM(ARREARS_AGE_4),0), "+//26
					" NVL(SUM(ARREARS_AGE_5),0) "+//27
					" FROM "+m_schema_name+".AF_RE_TBD_INSUR_ARR_AGE  A "+																
					" WHERE "+
					" A.ENT_USER='"+m_username+"' AND A.INSURANCE_DONE_BY LIKE '%"+m_insurence_done+"%' "+
					" GROUP BY A.FINANCE_NO,A.CLIENT_CODE,A.INSURANCE_DONE_BY,A.APPLICATION_NO,A.AGE,A.INSURANCE_OFFICER,A.COLLECTION_OFFICER "+
					" ORDER BY  "+m_sort_by+" "+m_order_by+" "); 
				
				
				boolean more = rs1.next();
				out.println("<HTML><HEAD><TITLE>Insurance Arrears Age Movement Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">");
				
				
				out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_by+"'){");
				out.println("	   if('"+m_order_by+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=print_report_new&date="+mdate+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function onload_remark_screen(client,application_no){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code=\"+client+\"&application_no=\"+application_no+\"\";"); 
				out.println("window.open(m_url,'displayWindow7','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0');"); 
				out.println("}");
				
				
				out.println("function onload_follow_screen(finance){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no=\"+finance+\"\";"); 
				out.println("window.open(m_url,'displayWindow8','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0');"); 
				out.println("}");
				
				out.println("function close_break_up_drill(finance,client){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=close_break_up&date="+mdate+"&client=\"+client+\"&finance_no=\"+finance+\"\";"); 
				//out.println("alert(m_url);");
				out.println("window.open(m_url,'displayWindow9','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function open_break_up_drill(finance,client){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=open_break_up&date="+mdate+"&client=\"+client+\"&finance_no=\"+finance+\"\";"); 
				//out.println("alert(m_url);");
				out.println("window.open(m_url,'displayWindow10','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function curr_break_up_drill(finance,client){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Arrears_Age_Report?chksql=curr_break_up&date="+mdate+"&client=\"+client+\"&finance_no=\"+finance+\"\";"); 
				//out.println("alert(m_url);");
				out.println("window.open(m_url,'displayWindow11','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");				
				
				out.println("function finance_drill(finance,client){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+client+\"&finance_no=\"+finance+\"\";"); 
				out.println("window.open(m_url,'displayWindow12','left=110,top=90,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(var i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				out.println("</script>");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body ><TD align='Center' ><B>Insurance Arrears Age Movement Report</B></TD></TR>");
				out.println("<TR><td width='5%'><B>Generated Date : "+mdate+" </td></TR>");
				out.println("<TR><td width='5%'><B>Collection Report - From : "+m_first_date+"  To : "+m_last_date+"</td></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				if(!more){
					out.println("<table border=0 width=100% align='center'>");
					out.println("<tr>");
					out.println("<td width='100%' align=center ><font color='red'>No Data Found..!</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				else{			 	
					out.println("<table width='115%' class='table' border='1' cellspacing=0 cellpadding=0 >");
					out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" style='height=40'>");
					out.println("<td width='1%'  align=left><b>No</td>");
					out.println("<td width='10%' align=left style='cursor:hand' onclick=sort_data('1') ><b>Finance No</td>");
					
					out.println("<td width='25%' align=left style='cursor:hand' onclick=sort_data('2') ><b>Client Name</b></td>");
					
					out.println("<td width='10%' align=left style='cursor:hand' onclick=sort_data('4') ><b>Renewal Date</b></td>");
					out.println("<td width='10%' align=left style='cursor:hand' onclick=sort_data('8') ><b>Insurance Done By</b></td>");
					out.println("<td width='5%'  align=right style='cursor:hand' onclick=sort_data('21') ><b>Rental Arr. Age</b>(Months)</td>");
					out.println("<td width='5%'  align=right style='cursor:hand' onclick=sort_data('13') ><b>Ins. Age</b>(Months)</td>");
					out.println("<td width='10%' align=right style='cursor:hand' onclick=sort_data('5') ><b>Total Opening Balance</b></td>");
					out.println("<td width='10%' align=right style='cursor:hand' onclick=sort_data('14') ><b>Current Month Due</b></td>");
					out.println("<td width='10%' align=right style='cursor:hand' onclick=sort_data('15') ><b>Total Balance</b></td>");
					out.println("<td width='10%' align=right style='cursor:hand' onclick=sort_data('6') ><b>Months Collection</b></td>");
					out.println("<td width='10%' align=right style='cursor:hand' onclick=sort_data('16') ><b>Closing Balance</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 0</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 1</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 2</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 3</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 4</b></td>");
					out.println("<td width='8%' align=right><b>Arr Age 5<</b></td>");
					out.println("<td width='15%' align=left style='cursor:hand' onclick=sort_data('17') ><b>Insurance Officer</b></td>");
					out.println("<td width='15%' align=left style='cursor:hand' onclick=sort_data('20') ><b>Debt Collector</b></td>");
					out.println("</tr>");					
					
					
					int j=1;
					double m_curr_month_coll=0;
					
					while(more){
						
						rs2= stmt2.executeQuery(" SELECT NVL(SUM(A.SETTLED_AMOUNT),0) "+
							" FROM "+m_schema_name+".AF_RE_TBD_INSUR_THS_MONTH A "+
							" WHERE A.FINANCE_NO    = '"+rs1.getString(1)+"' "+
							" AND UPPER(A.ENT_USER) = UPPER('"+m_username+"')");
						
						
						if(rs2.next()){
							m_curr_month_coll = rs2.getDouble(1);
						}
						
						
						out.println("<tr style = 'height:30' class=factoring-letter-body  id=tr_id"+j+" onClick=\"unselect_select_row('"+j+"')\" >");										
						
						m_tot_balance = rs1.getDouble(5)+ rs1.getDouble(14)-rs1.getDouble(12);
						m_cls_balance = rs1.getDouble(5)+ rs1.getDouble(14)-rs1.getDouble(6)-m_curr_month_coll; 
						out.println("<td width='1%'  align=center bgcolor='lightblue'>"+j+"</td>");
						out.println("<td width='10%' align=left  bgcolor='lightblue' style='cursor:hand' onclick=\"finance_drill('"+rs1.getString(1)+"','"+rs1.getString(9)+"')\"><u>"+rs1.getString(1)+"</td>");
						
						out.println("<td width='15%' align=left  bgcolor='lightblue' style='cursor:hand' onclick=\"show_client('"+rs1.getString(18)+"')\">"+rs1.getString(2)+"</td>");
						
						out.println("<td width='10%' align=left  bgcolor='lightblue' >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' align=left  bgcolor='lightblue' >"+rs1.getString(8)+"</td>");
						
						if(rs1.getDouble(21) >= 0 && rs1.getDouble(21) < 2 ){
							out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=green; }' ><b>"+nf.format(rs1.getDouble(21))+"</b></td>");
						}
						else if(rs1.getDouble(21) >= 2 && rs1.getDouble(21) < 3 ){
							out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=orange; }' ><b>"+nf.format(rs1.getDouble(21))+"</b></td>");
						}
						else if(rs1.getDouble(21) >= 3 ){
							out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=red; }' ><b>"+nf.format(rs1.getDouble(21))+"</b></td>");
						}else{
							out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=green; }' ><b>"+nf.format(rs1.getDouble(21))+"</b></td>");
						}
						
						if((rs1.getDouble(5)-rs1.getDouble(6))!=0){
							if(rs1.getInt(13) >= 0 && rs1.getInt(13) < 60 ){
								out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=green; }' ><b>"+rs1.getInt(13)+"</b></td>");
							}
							else if(rs1.getInt(13) >= 60 && rs1.getInt(13) < 90 ){
								out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=orange; }' ><b>"+rs1.getInt(13)+"</b></td>");
							}
							else if(rs1.getInt(13) >=90){
								out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=red; }' ><b>"+rs1.getInt(13)+"</b></td>");
							}
						}
						else{
							out.println("<td width='5%'  bgcolor='lightblue' align=right  STYLE='{color=green; }' ><b>0</b></td>");
						}
						
						out.println("<td width='10%' align=right title='Break Up - Opening Balance' style='cursor:hand' onclick=\"open_break_up_drill('"+rs1.getString(1)+"','"+rs1.getString(2).trim()+"')\" >"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' align=right title='Break Up - Current Month Due' style='cursor:hand' onclick=\"curr_break_up_drill('"+rs1.getString(1)+"','"+rs1.getString(2).trim()+"')\" >"+nf.format(rs1.getDouble(14))+"</td>");
						out.println("<td width='10%' align=right >"+nf.format(m_tot_balance)+"</td>");
						out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(6)+m_curr_month_coll)+"</td>");
						out.println("<td width='10%' align=right title='Break Up - Closing Balance' style='cursor:hand' onclick=\"close_break_up_drill('"+rs1.getString(1)+"','"+rs1.getString(2).trim()+"')\" >"+nf.format(m_cls_balance)+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(22))+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(23))+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(24))+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(25))+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(26))+"</td>");
						out.println("<td width='8%' align=right>"+nf.format(rs1.getDouble(27))+"</td>");
						
						out.println("<td width='15%' align=left  bgcolor='lightblue' >"+rs1.getString(17)+"</td>");
						out.println("<td width='15%' align=left  bgcolor='lightblue' >"+rs1.getString(20)+"</td>");
						out.println("</tr>"); 
						j++;
						
						m_tot_open       = m_tot_open  + rs1.getDouble(5);
						m_tot_close      = m_tot_close + m_cls_balance;
						m_tot_month      = m_tot_month + rs1.getDouble(6)+m_curr_month_coll; 
						m_tot_cur_month = m_tot_cur_month + rs1.getDouble(14);
						m_tot_bal        = m_tot_bal + m_tot_balance;
						
						more = rs1.next();
					}
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					out.println("<tr style='height:30' class=factoring-letter-body>");
					out.println("<td width='60%' align=right colspan=7 ><b>Total</td>");
					out.println("<td width='10%' align=right ><b>"+nf.format(m_tot_open)+"</td>");
					out.println("<td width='10%' align=right ><b>"+nf.format(m_tot_cur_month)+"</td>");
					out.println("<td width='10%' align=right ><b>"+nf.format(m_tot_bal)+"</td>");				
					out.println("<td width='10%' align=right ><b>"+nf.format(m_tot_month)+"</td>");
					out.println("<td width='10%' align=right ><b>"+nf.format(m_tot_close)+"</td>");
					out.println("<td width='20%' align=right  colspan=6>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}	
			else if(m_chksql.equals("close_break_up")){
				String m_finance = req.getParameter("finance_no");
				String m_name    = req.getParameter("client");
				String m_date    = req.getParameter("date");
				
				double m_tot_open  = 0.0;
				double m_tot_close = 0.0;
				double m_tot_month = 0.0;
				
				out.println("<HTML><HEAD><TITLE>Insurance Arrears Age Movement Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<script>");
				
				out.println("function invoice_drill(inv){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&url=&invoice_no=\"+inv+\"\";"); 
				out.println("window.open(m_url,'displayWindow2','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");			
				out.println("</script>");
				
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='5%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr></table>"); 
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body ><TD align='Center' ><B>Insurance Collection Break Up Details - Closing Balance</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body ><TD width='10%'><B>FInance No</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_finance+"</td></TR>");
				out.println("<TR class=factoring-letter-body ><TD width='10%'><B>Client Name</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_name+"</td></TR>");
				out.println("</TABLE>");
				
				
				rs1= stmt1.executeQuery(" SELECT A.INVOICE_NO, "+//1
					" TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//2
					" NVL(A.TOTAL_AMOUNT,0), "+//3
					" NVL(A.SETTELE_AMOUNT,0) + NVL(A.ADJUSTED_AMOUNT,0) , "+//4
					" NVL(A.BALANCE_TO_BE_RECEIVED,0), "+  //5   
					" A.ENT_USER,"+//6
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),"+//7
					" ROUND(SYSDATE-A.VALUE_DATE)-1 "+//8
					"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
					"	WHERE A.FINANCE_NO = '"+m_finance+"' "+
					"	AND  VALUE_DATE <= LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					"	AND A.INVOICE_TYPE='INSURANCE' "+
					"	AND A.ACTIVE_STATUS='Y' "+
					" ORDER BY A.VALUE_DATE");	
				
				out.println("<table width='*%' class='table' border='1' cellspacing=0 cellpadding=0>");
				out.println("<tr style='height:30' class=factoring-letter-body bgcolor=\"#C0C0C0\" >");
				out.println("<td width='5%'  align=left><b>No</td>");
				out.println("<td width='10%' align=left><b>Invoice No</td>");
				out.println("<td width='10%' align=left><b>Renewal Date</b></td>");
				out.println("<td width='10%' align=right><b>Invoice Amount</b></td>");
				out.println("<td width='10%' align=right><b>Settled Amount</b></td>");
				out.println("<td width='10%' align=right><b>Balance Amount</b></td>");
				out.println("<td width='8%'  align=right><b>Age</b>(Days)</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. user</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. Date</td>");
				out.println("</tr>");
				
				
				
				
				
				int j=1;
				boolean more = rs1.next();
				
				while(more){
					
					out.println("<tr style='height:30' class=factoring-letter-body>");											
					
					out.println("<td width='5%'  align=left  >"+j+"</td>");
					out.println("<td width='10%' align=left  style='cursor:hand' onclick=\"invoice_drill('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(5))+"</td>");
					if((rs1.getDouble(3)-rs1.getDouble(4))!=0){
						out.println("<td width='8%'  align=right  >"+rs1.getInt(8)+"</b></td>");
					}
					else{
						out.println("<td width='8%'  align=right  >0</b></td>");
					}
					out.println("<td width='10%' align=left  >"+rs1.getString(6)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(7)+"</td>");
					out.println("</tr>");
					j++;
					
					m_tot_open  = m_tot_open  + rs1.getDouble(3);
					m_tot_close = m_tot_close + (rs1.getDouble(5));
					m_tot_month = m_tot_month + rs1.getDouble(4); 
					
					more = rs1.next();
				}
				
				out.println("<tr bgcolor='lightblue' class=factoring-letter-body >");
				out.println("<td width='15%' align=right colspan=3><b>Total</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_open)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_month)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_close)+"</td>");
				out.println("<td width='28%' align=right colspan=3>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");	
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			else if(m_chksql.equals("open_break_up")){
				String m_finance = req.getParameter("finance_no");
				String m_name    = req.getParameter("client");
				String m_date    = req.getParameter("date");
				
				double m_tot_open  = 0.0;
				double m_tot_close = 0.0;
				double m_tot_month = 0.0;
				
				out.println("<HTML><HEAD><TITLE>Insurance Arrears Age Movement Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<script>");
				
				out.println("function invoice_drill(inv){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&url=&invoice_no=\"+inv+\"\";"); 
				//out.println("alert(m_url);");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='5%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr></table>"); 
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body ><TD align='Center' ><B>Insurance Collection Break Up Details - Opening Balance</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body ><TD width='10%'><B>FInance No</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_finance+"</td></TR>");
				out.println("<TR class=factoring-letter-body ><TD width='10%'><B>Client Name</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_name+"</td></TR>");
				out.println("</TABLE>");
				
				
				rs1= stmt1.executeQuery(  " SELECT "+
					" A.FINANCE_NO, "+ //1
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),  "+//2
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TEL(A.CLIENT_CODE),'-'),"+//3
					" TO_CHAR(A.RENEWAL_DATE,'DD-MM-YYYY'),"+//4
					" NVL(A.TOTAL_AMOUNT,0), "+//5
					" NVL(A.SETTLED_AMOUNT,0)+NVL(A.ADJUSTED_AMOUNT,0)+NVL(MONTH_COLLECTION,0), "+//6
					" ROUND(SYSDATE-A.RENEWAL_DATE)-1 ,"+//7
					" A.INVOICE_NO,"+//8
					" A.INV_GEN_USER,"+//9
					" TO_CHAR(A.INV_GEN_DATE,'DD-MM-YYYY'), "+//10
					" A.CUR_RENTAL_AMOUNT "+//11
					" FROM "+m_schema_name+".AF_RE_TBD_INSUR_ARR_AGE  A"+
					" WHERE  "+
					" A.ENT_USER   = '"+m_username+"'"+
					" AND A.FINANCE_NO = '"+m_finance+"' "+
					" ORDER BY A.RENEWAL_DATE ");
				
				
				
				
				out.println("<table width='*%' class='table' border='1' cellspacing=0 cellpading=0>");
				out.println("<tr style='height:30' class=factoring-letter-body bgcolor=\"#C0C0C0\" >");
				out.println("<td width='5%'  align=left><b>No</td>");
				out.println("<td width='10%' align=left><b>Invoice No</td>");
				out.println("<td width='10%' align=left><b>Renewal Date</b></td>");
				out.println("<td width='10%' align=right><b>Invoice Amount</b></td>");
				out.println("<td width='10%' align=right><b>Settled Amount</b></td>");
				out.println("<td width='10%' align=right><b>Balance Amount</b></td>");
				out.println("<td width='8%'  align=right><b>Age</b>(Days)</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. user</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. Date</td>");
				out.println("</tr>");
				
				
				
				
				
				int j=1;
				boolean more = rs1.next();
				
				while(more){					
					out.println("<tr style='height:30' class=factoring-letter-body>");				 				
					out.println("<td width='5%'  align=left  >"+j+"</td>");
					out.println("<td width='10%' align=left  style='cursor:hand' onclick=\"invoice_drill('"+rs1.getString(8)+"')\"><u>"+rs1.getString(8)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(5)-rs1.getDouble(6))+"</td>");
					if((rs1.getDouble(5)-rs1.getDouble(6))!=0){
						out.println("<td width='8%'  align=right  >"+rs1.getInt(7)+"</b></td>");
					}
					else{
						out.println("<td width='8%'  align=right  >0</b></td>");
					}
					out.println("<td width='10%' align=left  >"+rs1.getString(9)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(10)+"</td>");
					out.println("</tr>");
					j++;
					
					m_tot_open  = m_tot_open  + rs1.getDouble(5);
					m_tot_close = m_tot_close + (rs1.getDouble(5)-rs1.getDouble(6));
					m_tot_month = m_tot_month + rs1.getDouble(6); 
					
					more = rs1.next();
				}
				
				out.println("<tr bgcolor='lightblue' class=factoring-letter-body>");
				out.println("<td width='15%' align=right colspan=3><b>Total</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_open)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_month)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_close)+"</td>");
				out.println("<td width='28%' align=right colspan=3>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");	
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			else if(m_chksql.equals("curr_break_up")){
				String m_finance = req.getParameter("finance_no");
				String m_name    = req.getParameter("client");
				String m_date    = req.getParameter("date");
				
				double m_tot_open  = 0.0;
				double m_tot_close = 0.0;
				double m_tot_month = 0.0;
				
				out.println("<HTML><HEAD><TITLE>Insurance Arrears Age Movement Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<script>");
				
				out.println("function invoice_drill(inv){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&url=&invoice_no=\"+inv+\"\";"); 
				out.println("window.open(m_url,'displayWindow1','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>");
				
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='5%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr></table>"); 
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body><TD align='Center' ><B>Insurance Collection Break Up Details - Current Month Due</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%' class='table'>");
				out.println("<TR class=factoring-letter-body><TD width='10%'><B>FInance No</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_finance+"</td></TR>");
				out.println("<TR class=factoring-letter-body><TD width='10%'><B>Client Name</B></TD><td width='2%'><b>:</td><td width='*%'><b>"+m_name+"</td></TR>");
				out.println("</TABLE>");
				
				
				rs1= stmt1.executeQuery(" SELECT A.INVOICE_NO, "+//1
					" TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//2
					" NVL(A.TOTAL_AMOUNT,0), "+//3
					" NVL(A.SETTELE_AMOUNT,0) + NVL(A.ADJUSTED_AMOUNT,0) , "+//4
					" NVL(A.BALANCE_TO_BE_RECEIVED,0), "+  //5   
					" A.ENT_USER,"+//6
					" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),"+//7
					" ROUND(SYSDATE-A.VALUE_DATE)-1 "+//8
					"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
					"	WHERE A.FINANCE_NO = '"+m_finance+"' "+
					"	AND  VALUE_DATE <= LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					"	AND  VALUE_DATE >= ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))+1,-1) "+
					"	AND A.INVOICE_TYPE='INSURANCE' "+
					"	AND A.ACTIVE_STATUS='Y' "+
					" ORDER BY A.VALUE_DATE  ");		
				
				
				out.println("<table width='*%'  class='table' border='1' cellpadding=0 cellspacing=0 >");
				out.println("<tr style='height:30' class=factoring-letter-body bgcolor=\"#C0C0C0\" >");
				out.println("<td width='5%'  align=left><b>No</td>");
				out.println("<td width='10%' align=left><b>Invoice No</td>");
				out.println("<td width='10%' align=left><b>Renewal Date</b></td>");
				out.println("<td width='10%' align=right><b>Invoice Amount</b></td>");
				out.println("<td width='10%' align=right><b>Settled Amount</b></td>");
				out.println("<td width='10%' align=right><b>Balance Amount</b></td>");
				out.println("<td width='8%'  align=right><b>Age</b>(Days)</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. user</td>");
				out.println("<td width='10%' align=left><b>Invoice Gen. Date</td>");
				out.println("</tr>");
				
				
				
				
				
				int j=1;
				boolean more = rs1.next();
				
				while(more){
					
					out.println("<tr style='height:30' class=factoring-letter-body>");			
					
					out.println("<td width='5%'  align=left  >"+j+"</td>");
					out.println("<td width='10%' align=left  style='cursor:hand' onclick=\"invoice_drill('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' align=right >"+nf.format(rs1.getDouble(5))+"</td>");
					if((rs1.getDouble(3)-rs1.getDouble(4))!=0){
						out.println("<td width='8%'  align=right  >"+rs1.getInt(8)+"</b></td>");
					}
					else{
						out.println("<td width='8%'  align=right  >0</b></td>");
					}
					out.println("<td width='10%' align=left  >"+rs1.getString(6)+"</td>");
					out.println("<td width='10%' align=left  >"+rs1.getString(7)+"</td>");
					out.println("</tr>");
					j++;
					
					m_tot_open  = m_tot_open  + rs1.getDouble(3);
					m_tot_close = m_tot_close + (rs1.getDouble(5));
					m_tot_month = m_tot_month + rs1.getDouble(4); 
					
					more = rs1.next();
				}
				
				out.println("<tr bgcolor='lightblue' class=factoring-letter-body >");
				out.println("<td width='15%' align=right colspan=3><b>Total</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_open)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_month)+"</td>");
				out.println("<td width='10%' align=right><b>"+nf.format(m_tot_close)+"</td>");
				out.println("<td width='28%' align=right colspan=3>&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");	
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
