//DEVELOPED BY : SANDUN JAYATHILAKE 
//DATE         : 15-01-2009

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Branch_Wise_Income_Report extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	Statement stmt,stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs1;
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
			//m_username="OFSCLALL";
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			stmt1 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
						
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_APP_CAP_OS_RPT(:1,:2);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_username);
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			else if(m_chksql.equals("main_page")){ 
			
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Brance Wise Income Report</TITLE>"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=main_page';"); 
				out.println("		}"); 
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
				out.println("		help_box.innerHTML=\" Brance Wise Income Report - \"+m_val;"); 
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
 
							
				out.println("function help_button_collection_officer() {"); 
			  out.println(" document.Form1.hid_help_type.value='3';");
			  out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
			  out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
			  out.println("}"); 
			
				out.println("function help_value_assign_collection(oBj) {"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");
				//added function by madhawa 2009-09-14
				out.println("function validate_date(){");
   			out.println("  m_from_dd = document.Form1.VAL_DAY.value ");
   			out.println("  m_from_mm = document.Form1.VAL_MONTH.value ");
  		 	out.println("  m_from_yy = document.Form1.VAL_YEAR.value ");
   
   			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
   			out.println("    if(!checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){  "); 
   			out.println("       return false;"); 
   			out.println("    }");
   			out.println("    else {");
   			out.println("         return true; "); 
   			out.println("    }");
   			out.println(" }");
   			out.println(" else {");
   			out.println("     alert(' Date cannot be Empty ')");
   			out.println(" }");
   			out.println("}");
				
				//added by madhawa 2009-09-14

				out.println("function run_report() {");
				//modified by madhawa added else part 2009-09-14
				out.println("if(validate_date()){");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=run_report&date=\"+m_date;"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				//modified by madhawa added else part 2009-09-14
				out.println("}");
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
				//modified by madhawa added else part 2009-09-14
				out.println("if(validate_date()){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=print_report&date=\"+m_date+\"\";"); 
				//out.println("			window.open(m_url);");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=8,top=100,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("	}");
				out.println("}");
				//modified by madhawa added else part 2009-09-14
				out.println("}");
			
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MISF_CAPITAL_OS_REPORT\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Brance Wise Income Report</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='' value=\"Help\"></td>");  
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
				out.println("<td width='10%'ID=VDATE>Date As At *</td>");
				out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" >");
				out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" >");
				out.println("</td>");
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report()\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
				out.println("</td>"); 
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
				
				
				String m_report_date="";
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'dd-Month-YYYY') FROM DUAL");
				
				if(rs1.next()){
				m_report_date=rs1.getString(1);				
				}

      
				out.println("<HTML><HEAD><TITLE>Branch Wise Income Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=transaction_history&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");				
				
				out.println("function show_drill(m_branch,txt_type,m_division_code){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=drill_down_transaction&division_code='+m_division_code+'&transaction_type='+txt_type+'&branch_code='+m_branch;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_drill_1(m_branch,txt_type,m_division_code){ ");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=drill_down_transaction_mk&division_code='+m_division_code+'&transaction_type='+txt_type+'&branch_code='+m_branch;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");				
				
				out.println("</script>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							

				out.println("<TABLE  WIDTH='100%' >");
				out.println("<TR><TD align='Center'><B>Branch Wise Income Report As At : "+m_report_date+"</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<TABLE  WIDTH='100%' >");
				out.println("<TR><TD align='Center'><B>Asset Finance</B></TD></TR>");
				out.println("</TABLE>");

				out.println("<br>");
				out.println("<table width='70%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");
				out.println("<tr  bgcolor=\"#C0C0C0\">");
				out.println("<td width='20%' align='left'><DIV ><b>Branch<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Leasing Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Lease Purchase Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Hiring Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Total Income<DIV></td>");
				out.println("</tr>");

								
				rs1= stmt1.executeQuery(" SELECT DISTINCT NVL(a.branch_code,'undefined') branch_code,"+ //1
				" a.transaction_type, "+ //2
				" SUM(NVL(a.future_receivable,0) +   NVL(a.arr_capital_portion,0)) ,"+ //3
				" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(a.branch_code),'undefined') "+
				" ,SUM(NVL(A.interest,0))"+
				" FROM "+m_schema_name+".af_re_tbd_app_capital_os a  "+
				" where a.ent_user='"+m_username+"' "+
				" AND  a.DIVISION_CODE='AF' "+
				" GROUP BY A.branch_code,a.transaction_type "+
				" ORDER BY branch_code,a.transaction_type  ");
				
				String m_branch_code="",m_branch_desc="";
				int j=1;
				double m_lease=0,m_lease_purchase=0,m_lease_hiring=0,m_total=0;
				double m_lease_int=0,m_lease_purchase_int=0,m_lease_hiring_int=0,m_total_int=0;
				double m_lease_total=0,m_lease_purchase_total=0,m_lease_hiring_total=0;
				double m_lease_total_int=0,m_lease_purchase_total_int=0,m_lease_hiring_total_int=0;
				double m_sub_tot=0,m_sub_tot_int=0;

				boolean more1=rs1.next();
				
				while(more1){
				m_total=0;
				m_lease=0;
				m_lease_purchase=0;
				m_lease_hiring=0;
				m_total_int=0;
				m_lease_int=0;
				m_lease_purchase_int=0;
				m_lease_hiring_int=0;

				m_branch_code=rs1.getString(1);
				m_branch_desc=rs1.getString(4);

				
				if(j==0){
				out.println("<tr bgcolor=\"#FFFFFF\">");
				j=1;
				}
				else{
				out.println("<tr bgcolor=\"#C0C0C0\" >");
				j=0;
				}

				while(m_branch_code.trim().equals(rs1.getString(1))){
				if(rs1.getString(2).equals("FINLEASE")){
				m_lease=rs1.getDouble(3);
				m_lease_int=rs1.getDouble(5);
				m_lease_total=m_lease_total+m_lease;
				m_lease_total_int=m_lease_total_int+m_lease_int;

				}
				else if(rs1.getString(2).equals("HIREPURCH")){
				m_lease_purchase=rs1.getDouble(3);
				m_lease_purchase_int=rs1.getDouble(5);
				m_lease_purchase_total=m_lease_purchase_total+m_lease_purchase;
				m_lease_purchase_total_int=m_lease_purchase_total_int+m_lease_purchase_int;
				}
				else if(rs1.getString(2).equals("HIRING")){
				m_lease_hiring_int=rs1.getDouble(5);
				m_lease_hiring=rs1.getDouble(3);
				m_lease_hiring_total=m_lease_hiring_total+m_lease_hiring;
				m_lease_hiring_total_int=m_lease_hiring_total_int+m_lease_hiring_int;


				}
			  m_total=m_lease+m_lease_purchase+m_lease_hiring;
				m_total_int=m_lease_int+m_lease_purchase_int+m_lease_hiring_int;
				more1=rs1.next();
				if(!more1){
				break;
				}
				}
				
				out.println("<td width='20%' align='left' ><DIV >"+m_branch_desc+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','FINLEASE','AF')\" ><DIV ><u>"+nf.format(m_lease_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','HIREPURCH','AF')\" ><DIV ><u>"+nf.format(m_lease_purchase_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','HIRING','AF')\" ><DIV ><u>"+nf.format(m_lease_hiring_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV >"+nf.format(m_total_int)+"<DIV></td>");
				out.println("</tr>");
				}
				
				m_sub_tot=m_lease_total+m_lease_purchase_total+m_lease_hiring_total;
				m_sub_tot_int=m_lease_total_int+m_lease_purchase_total_int+m_lease_hiring_total_int;
				
				out.println("<tr>");
				out.println("<td width='20%' align='left'><DIV ><b>Total<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_purchase_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_hiring_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_total_int+m_lease_purchase_total_int+m_lease_hiring_total_int)+"<DIV></td>");
				out.println("</tr>");

				
				out.println("</table>");
				
				out.println("<br><br>");
				out.println("<TABLE  WIDTH='100%' >");
				out.println("<TR><TD align='Center' ><B>Bike Lease</B></TD></TR>");
				out.println("</TABLE>");

				out.println("<table width='70%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");
				out.println("<tr  bgcolor=\"#C0C0C0\">");
				out.println("<td width='20%' align='left'><DIV ><b>Branch<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Leasing Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Lease Purchase Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Hiring Income<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>Total Income<DIV></td>");
				out.println("</tr>");

				
								
				rs1= stmt1.executeQuery(" SELECT DISTINCT NVL(A.BRANCH_CODE,'undefined') BRANCH_CODE,"+ //1
				" A.TRANSACTION_TYPE, "+ //2
				" SUM(NVL(A.FUTURE_RECEIVABLE,0) +   NVL(A.ARR_CAPITAL_PORTION,0)) ,"+ //3
				" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'undefined') "+
				" ,SUM(NVL(A.INTEREST,0))"+
				" FROM "+m_schema_name+".AF_RE_TBD_APP_CAPITAL_OS A "+
				" WHERE A.ENT_USER='"+m_username+"' "+
				" AND A.DIVISION_CODE='BD' "+
				" GROUP BY A.BRANCH_CODE,A.TRANSACTION_TYPE "+
				" ORDER BY BRANCH_CODE,A.TRANSACTION_TYPE  ");
				
				
				m_branch_code="";
				m_branch_desc="";
				j=1;
				more1=rs1.next();
				m_lease_total=0;
				m_lease_purchase_total=0;
				m_lease_hiring_total=0;
				m_lease_total_int=0;
				m_lease_purchase_total_int=0;
				m_lease_hiring_total_int=0;


				
				while(more1){
				m_total=0;
				m_lease=0;
				m_lease_purchase=0;
				m_lease_hiring=0;
				m_total_int=0;
				m_lease_int=0;
				m_lease_purchase_int=0;
				m_lease_hiring_int=0;

				m_branch_code=rs1.getString(1);
				m_branch_desc=rs1.getString(4);

				
				if(j==0){
				out.println("<tr bgcolor=\"#FFFFFF\">");
				j=1;
				}
				else{
				out.println("<tr bgcolor=\"#C0C0C0\" >");
				j=0;
				}

				while(m_branch_code.trim().equals(rs1.getString(1))){
				
				if(rs1.getString(2).equals("FINLEASE")){
				m_lease=rs1.getDouble(3);
				m_lease_int=rs1.getDouble(5);
				m_lease_total=m_lease_total+m_lease;
				m_lease_total_int=m_lease_total_int+m_lease_int;
				}
				else if(rs1.getString(2).equals("HIREPURCH")){
				m_lease_purchase=rs1.getDouble(3);
			  m_lease_purchase_int=rs1.getDouble(5);
				m_lease_purchase_total=m_lease_purchase_total+m_lease_purchase;
				m_lease_purchase_total_int=m_lease_purchase_total_int+m_lease_purchase_int;
				}
				else if(rs1.getString(2).equals("HIRING")){
				m_lease_hiring_int=rs1.getDouble(5);
				m_lease_hiring=rs1.getDouble(3);
				m_lease_hiring_total=m_lease_hiring_total+m_lease_hiring;
				m_lease_hiring_total_int=m_lease_hiring_total_int+m_lease_hiring_int;


				}
			  m_total=m_lease+m_lease_purchase+m_lease_hiring;
				m_total_int=m_lease_int+m_lease_purchase_int+m_lease_hiring_int;
				more1=rs1.next();
				if(!more1){
				break;
				}
				}
				
				out.println("<td width='20%' align='left' ><DIV >"+m_branch_desc+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','FINLEASE','BD')\" ><DIV ><u>"+nf.format(m_lease_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','HIREPURCH','BD')\" ><DIV ><u>"+nf.format(m_lease_purchase_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right' style= cursor:hand; onclick=\"show_drill_1('"+m_branch_code+"','HIRING','BD')\" ><DIV ><u>"+nf.format(m_lease_hiring_int)+"</u><DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV >"+nf.format(m_total_int)+"<DIV></td>");
				out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='20%' align='left'><DIV ><b>Total<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_purchase_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_hiring_total_int)+"<DIV></td>");
				out.println("<td width='10%' bgcolor='#FFFFCC' align='right'><DIV ><b>"+nf.format(m_lease_total_int+m_lease_purchase_total_int+m_lease_hiring_total_int)+"<DIV></td>");
				out.println("</tr>");
				
				out.println("</table>");
				m_sub_tot+=m_lease_total+m_lease_purchase_total+m_lease_hiring_total;
				m_sub_tot_int+=m_lease_total_int+m_lease_purchase_total_int+m_lease_hiring_total_int;

				
				out.println("<br>");
				out.println("<table width='70%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");
				out.println("<tr bgcolor='lightgrey'>");
				out.println("<td width='20%' colspan='8' align='left'><DIV ><b>Total Income<DIV></td>");
				out.println("<td width='10%' align='right'><DIV ><b>"+nf.format(m_sub_tot_int)+"<DIV></td>");
				out.println("</tr>");
				out.println("</table>");

				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
		
			
		}	
			
			else if(m_chksql.equals("drill_down_transaction_mk")){
			
			String m_branch_code=req.getParameter("branch_code");		
			String m_transaction_type=req.getParameter("transaction_type");		
			String m_division_code=req.getParameter("division_code");		
			int i =1;
			double sum     = 0.0;
			double sum_2   = 0.0;
			double sum_3   = 0.0;
			double sum_int = 0.0;
			
			out.println("<HTML><HEAD><TITLE>Branch Wise Income</TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function show_drill(m_branch,txt_type,m_division_code,m_coll_officer){ "); //
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Branch_Wise_Income_Report?chksql=drill_down_transaction&coll_officer='+m_coll_officer+'&division_code='+m_division_code+'&transaction_type='+txt_type+'&branch_code='+m_branch;"); 
			out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("</SCRIPT>"); 
			
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B> Branch Wise Income </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
			
			rs=stmt1.executeQuery("SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(B.COLLECTION_OFFICER), "+//1
			" SUM(A.CAPITAL_OUTSTANDING), "+//2
			" SUM(A.FUTURE_RECEIVABLE), "+//3
			" SUM(A.ARR_CAPITAL_PORTION) ,"+//4
			" NVL(SUM(A.FUTURE_RECEIVABLE),0)+NVL(SUM(A.ARR_CAPITAL_PORTION),0) ,"+//5
			" NVL(SUM(A.INTEREST),0),	"+//6
			" B.COLLECTION_OFFICER "+//7
			" FROM "+m_schema_name+".AF_RE_TBD_APP_CAPITAL_OS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
      " AND A.BRANCH_CODE      = '"+m_branch_code+"'  "+
			" AND A.DIVISION_CODE    = '"+m_division_code+"'  "+
			" AND A.ENT_USER         = '"+m_username+"'  "+
		  " AND A.TRANSACTION_TYPE = '"+m_transaction_type+"' "+
		  " GROUP BY B.COLLECTION_OFFICER ");
			
			
			boolean more = rs.next();
			
			out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' class=div_input>No</td>");
					out.println("<td width='25%' class=div_input>Collection Officer</td>");					
					out.println("<td width='10%' align='right' class=div_input>Arrears Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
					out.println("<td width='10%' align='right' class=div_input>Income</td>");

					out.println("</tr>");
					
					while(more){
					out.println("<tr bgcolor=\"#FCEBC5\" >");
					out.println("<td width='1%' class=div_input>"+i+"</td>");
					out.println("<td width='25%' class=div_input >"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' align='right' style='cursor:hand' class=div_input onClick=\"show_drill('"+m_branch_code+"','"+m_transaction_type+"','"+m_division_code+"','"+rs.getString(7)+"')\"><u>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='10%' align='right' style='cursor:hand' class=div_input onClick=\"show_drill('"+m_branch_code+"','"+m_transaction_type+"','"+m_division_code+"','"+rs.getString(7)+"')\"><u>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' align='right' style='cursor:hand' class=div_input onClick=\"show_drill('"+m_branch_code+"','"+m_transaction_type+"','"+m_division_code+"','"+rs.getString(7)+"')\"><u>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='10%' align='right' style='cursor:hand' class=div_input onClick=\"show_drill('"+m_branch_code+"','"+m_transaction_type+"','"+m_division_code+"','"+rs.getString(7)+"')\"><u>"+nf.format(rs.getDouble(6))+"</td>");


					out.println("</tr>");
					sum+=rs.getDouble(4);
					sum_2+=rs.getDouble(3);
					sum_3+=rs.getDouble(5);
					sum_int+=rs.getDouble(6);

					more =rs.next();
					i=i+1;
					}
					
					out.println("<tr >");
					out.println("<td width='1%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input><b>Total</td>");					
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_3)+"</td>");
					out.println("<td width='10%' align='right' class=div_input ><b>"+nf.format(sum_int)+"</td>");

					out.println("</tr>");

					

					out.println("</table>");
					out.println("</body>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</html>");

			
			}
		
			else if(m_chksql.equals("drill_down_transaction")){
			
			String m_branch_code      = req.getParameter("branch_code");		
			String m_transaction_type = req.getParameter("transaction_type");		
			String m_division_code    = req.getParameter("division_code");	
			String m_coll_officer     = req.getParameter("coll_officer");	
			
			out.println("<HTML><HEAD><TITLE>Branch Wise Income</TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B> Branch Wise Income </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");		

		
			String Sql_invoice=" SELECT A.APPLICATION_NO,"+ //1
			" A.FINANCE_NO, "+ //2
			" A.CLIENT_CODE, "+ //3
			" A.CLIENT_FULL_NAME, "+ //4
			" A.CAPITAL_OUTSTANDING, "+ //5
			" A.ENT_USER, "+ //6
			" A.ENT_DATE,  "+ //7
			" A.DIVISION_CODE,  "+ //8
			" A.BRANCH_CODE,  "+ //9
			" A.FUTURE_RECEIVABLE, "+ //10
			" A.ARR_CAPITAL_PORTION ,"+ //11
			" NVL(A.FUTURE_RECEIVABLE,0) +   NVL(A.ARR_CAPITAL_PORTION,0) ,"+ //12
			" NVL(A.INTEREST,0)"+ //13
			" FROM "+m_schema_name+".AF_RE_TBD_APP_CAPITAL_OS A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
			" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
			" AND A.BRANCH_CODE        = '"+m_branch_code+"' "+
			" AND A.DIVISION_CODE      = '"+m_division_code+"' "+
			" AND A.ENT_USER           = '"+m_username+"' "+
			" AND A.TRANSACTION_TYPE   = '"+m_transaction_type+"' "+
			" AND B.COLLECTION_OFFICER = '"+m_coll_officer+"' ";
			
			rs=stmt1.executeQuery(Sql_invoice);
			boolean  more_inv =rs.next();
			double sum=0,sum_2=0,sum_3=0,sum_int=0;
			int i=1;
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' class=div_input>No</td>");
					out.println("<td width='10%' class=div_input>Finance No</td>");
					out.println("<td width='25%' class=div_input>Client Name</td>");
					out.println("<td width='10%' align='right' class=div_input>Arrears Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Future Capital</td>");
					out.println("<td width='10%' align='right' class=div_input>Capital OutStanding</td>");
					out.println("<td width='10%' align='right' class=div_input>Income</td>");

					out.println("</tr>");
					
					while(more_inv){
					out.println("<tr bgcolor=\"#FCEBC5\" >");
					out.println("<td width='1%' class=div_input>"+i+"</td>");
					out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");


					out.println("</tr>");
					sum+=rs.getDouble(12);
					sum_2+=rs.getDouble(11);
					sum_3+=rs.getDouble(10);
					sum_int+=rs.getDouble(13);

					more_inv =rs.next();
					i=i+1;
					}
					
					out.println("<tr >");
					out.println("<td width='1%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input><b>Total</td>");
					out.println("<td width='15%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_2)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_3)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum)+"</td>");
					out.println("<td width='10%' align='right' class=div_input><b>"+nf.format(sum_int)+"</td>");

					out.println("</tr>");

					

					out.println("</table>");
					out.println("</body>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</html>");


      }


	  }
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
