// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Portfolio_Quality_Statement_Account extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
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
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			stmt1 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				//String m_client_code=req.getParameter("client_code");
				//String m_coll_officer=req.getParameter("coll_officer");
			  String m_division=req.getParameter("division_code");
				
				try{
				
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_PORTFOLIO_ACC(:1,:2,:3);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_division);
				callstmt1.setString(3,m_username);
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
				out.println("<TITLE>Finance - PortFolio Quality Statement Account </TITLE>"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=view_screen';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=view_screen';"); 
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
				out.println("		help_box.innerHTML=\" Finance - PortFolio Quality Statement Account - \"+m_val;"); 
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
			
				out.println("function help_value_assign_collection(oBj) {"); 
				out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");

				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=run_report&division_code=\"+document.Form1.TXT_DIVISION_CODE.value+\"&date=\"+m_date;"); 
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
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=print_report&date=\"+m_date+\"&report_type=\"+document.Form1.TXT_DIVISION_CODE.value;"); 
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
			
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MISF_PORTFOLIO_QUALITY_ACCOUNT\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - PortFolio Quality Statement Account </td>"); 
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
				out.println("<td width='20%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Division Code</DIV></td>"); 
				out.println("<td width='15%' ><SELECT onchange=\"\" name=\"TXT_DIVISION_CODE\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"AF\" SELECTED>Other</OPTION>");
				out.println("<OPTION value=\"BD\">Bike</OPTION>");
				out.println("</SELECT></td>");
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
		else if(m_chksql.equals("print_report")){
				
				String m_report_type=req.getParameter("report_type");
				
				
			 String m_date=req.getParameter("date");
				
				String m_report_date="";
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'dd-Month-YYYY') FROM DUAL");
				if(rs1.next()){
				m_report_date=rs1.getString(1);
				}


				//if(m_report_type.equals("AF")){
				out.println("<HTML><HEAD><TITLE>Finance - Portfolio Quality Statment</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
		
				
				out.println("function show_detail_view(m_age_from,m_age_to){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=drill_down_transaction&age_from='+m_age_from+'&age_to='+m_age_to;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 fullscreen=1');"); 
				out.println("}");
				
				out.println("</script>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							

				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Finance - Portfolio Quality Statment Account as at  : "+m_report_date+" </B></TD></TR>");
				out.println("</TABLE>");
				
				String m_report_desc="";
				if(m_report_type.equals("AF")){
				m_report_desc="Asset Finance";
				}
				else if(m_report_type.equals("BD")){
				m_report_desc="Bike ";
				}
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>"+m_report_desc+"</B></TD></TR>");
				out.println("</TABLE>");

			
			
      out.println("<br>");
			//out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			out.println("<table width='70%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");

			//-------report header -------------------------------------------------------
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" ><td width='7%' class=factoring-letter-body align='center'><b>Contracts</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Cum Contracts</td>");
			out.println("<td width='11%' class=factoring-letter-body align='center'><b>Category</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Nil</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Arrears</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>%</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>0-3 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>3-6 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>6-12 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>12-18 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>>18 above</td>");
			out.println("</tr>");

				
			String sql_query="SELECT a.application_no, "+ //1
			//" NVL(a.total_rentals,0) - NVL(a.rentals_paid,0) age, "+ //2
			" ROUND(a.age) ,"+ //2
			//" NVL(a.rentals_paid,0) age, "+ //2
			" ROUND(NVL(a.total_amount,0),2) - (ROUND(NVL(a.settled_amount,0),2) + ROUND(NVL(a.ADJUSTED_AMOUNT,0),2) ) arrears, "+ //3
			" NVL(a.income,0), "+ //4
			" ROUND(NVL(a.arr_capital_portion,0)+ NVL(a.future_receivable,0) ,2) capital ,"+  //5
			" NVL(a.arreas_age_0_3,0), "+ //6
			" NVL(a.arreas_age_3_6,0), "+ //7
      " NVL(a.arreas_age_6_12,0),  "+ //8
			" NVL(a.arreas_age_12_18,0), "+ //9
			" NVL(a.arreas_age_19,0) ,"+ //10
			" NVL(a.interest,0) "+ //11
			" FROM "+m_schema_name+".af_re_tbd_portfolio_acc a "+
			" where a.ent_user='"+m_username+"' ";

		  
			rs1 = stmt1.executeQuery(sql_query);
		  boolean more2=rs1.next();
			double m_age=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0;
			double m_amount_cap=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0;
			double m_age_0_3=0;
			double m_age_3_6_0=0,m_age_3_6_1=0;
			double m_age_6_12_0=0,m_age_6_12_1=0,m_age_6_12_2=0;
			double m_age_12_18_0=0,m_age_12_18_1=0,m_age_12_18_2=0,m_age_12_18_3=0;
			double m_age_19_0=0,m_age_19_1=0,m_age_19_2=0,m_age_19_3=0,m_age_19_4=0;
			double m_tot_0_3=0,m_tot_3_6=0,m_tot_6_12=0,m_tot_12_18=0,m_tot_19=0;
			double m_int_0_3=0,m_int_3_6=0,m_int_6_12=0,m_int_12_18=0,m_int_19=0,m_tot_int=0;
			
			while(more2){
			m_age=rs1.getDouble(2);
			m_amount=rs1.getDouble(3);
			m_amount_cap=rs1.getDouble(5);
			
			//if ( m_age < 0  || (m_age >=0 && m_age <3) ){
			if ( m_age <=3 ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			m_age_0_3+=rs1.getDouble(6)+rs1.getDouble(7)+rs1.getDouble(8)+rs1.getDouble(9)+rs1.getDouble(10);
			m_int_0_3+=rs1.getDouble(11);
			}
			else if (m_age >3 && m_age <=6){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			//out.println("m_age_1_m_amount"+m_age_1_m_amount);
		  m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			m_age_3_6_0+=rs1.getDouble(6);
			m_age_3_6_1+=rs1.getDouble(7)+rs1.getDouble(8)+rs1.getDouble(9)+rs1.getDouble(10);
			m_int_3_6+=rs1.getDouble(11);
			
			}
			else if (m_age >6 && m_age <=12){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			//out.println("m_age_2_m_amount"+m_age_2_m_amount);
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			m_age_6_12_0+=rs1.getDouble(6);
			m_age_6_12_1+=rs1.getDouble(7);
			m_age_6_12_2+=rs1.getDouble(8)+rs1.getDouble(9)+rs1.getDouble(10);
			m_int_6_12+=rs1.getDouble(11);

			}
			else if (m_age >12 && m_age <=18){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			m_age_12_18_0+=rs1.getDouble(6);
			m_age_12_18_1+=rs1.getDouble(7);
			m_age_12_18_2+=rs1.getDouble(8);
			m_age_12_18_3+=rs1.getDouble(9)+rs1.getDouble(10);
			m_int_12_18+=rs1.getDouble(11);


			}
			else if(m_age>18 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			//out.println("m_age_6_m_amount"+m_age_6_m_amount);
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			m_age_19_0+=rs1.getDouble(6);
			m_age_19_1+=rs1.getDouble(7);
			m_age_19_2+=rs1.getDouble(8);
			m_age_19_3+=rs1.getDouble(9);
			m_age_19_4+=rs1.getDouble(10);
			m_int_19+=rs1.getDouble(11);


			}
		  more2=rs1.next();
			}
			
			double m_tot_cap=0,m_tot_amount=0,arr_precentage=0;
     
			m_tot_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_6_m_amount_cap;
			m_tot_amount=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_6_m_amount;
			m_tot_0_3=m_age_0_3+m_age_3_6_0+m_age_6_12_0+m_age_12_18_0+m_age_19_0;
			m_tot_3_6=m_age_3_6_1+m_age_6_12_1+m_age_12_18_1+m_age_19_1;
			m_tot_6_12=m_age_6_12_2+m_age_12_18_2+m_age_19_2;
			m_tot_12_18=m_age_12_18_3+m_age_19_3;
			m_tot_19=m_age_19_4;
			m_tot_int=m_int_0_3+m_int_3_6+m_int_6_12+m_int_12_18+m_int_19;


			m_cum_value=m_age_0_count;
			arr_precentage=(m_age_0_m_amount/m_tot_amount)*100;
			out.println("<tr>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('0','3') STYLE='{font:  8pt arial; text-align:right;}'><u>"+m_age_0_count+"</u></td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_age_0_count+"</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>0 -3 contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_0_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");
			
			m_cum_value+=m_age_1_count;
			arr_precentage=(m_age_1_m_amount/m_tot_amount)*100;
			out.println("<tr>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('3','6') STYLE='{font:  8pt arial; text-align:right;}'><u>"+m_age_1_count+"</u></td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>3 -6 contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_1_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_6_0)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_6_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("</tr>");			//two month----------------------------
			
			m_cum_value+=m_age_2_count;

			arr_precentage=(m_age_2_m_amount/m_tot_amount)*100;

			out.println("<tr>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('6','12') STYLE='{font:  8pt arial; text-align:right;}'><u>"+m_age_2_count+"</u></td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>6 -12 contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_2_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_12_0)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_12_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_12_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");

			out.println("</tr>");
			
			m_cum_value+=m_age_3_count;
			arr_precentage=(m_age_3_m_amount/m_tot_amount)*100;

			
			out.println("<tr>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('12','18') STYLE='{font:  8pt arial; text-align:right;}'><u>"+m_age_3_count+"</u></td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>12 -18 contracts</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_3_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_12_18_0)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_12_18_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_12_18_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_12_18_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");

			out.println("</tr>");
			
			m_cum_value+=m_age_6_count;

			arr_precentage=(m_age_6_m_amount/m_tot_amount)*100;

			out.println("<tr>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('18','18') STYLE='{font:  8pt arial; text-align:right;}'><u>"+m_age_6_count+"</u></td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+m_cum_value+"</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>18 above</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_6_m_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_19_0)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_19_1)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_19_2)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_19_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>"+nf.format(m_age_19_4)+"</td>");

			out.println("</tr>");
			arr_precentage=(m_tot_amount/m_tot_amount)*100;

			//total  -----------------------------
			out.println("<tr bgcolor='lightgrey' >");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><B>Total</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_cap)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_amount)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(arr_precentage)+"%</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_0_3)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_3_6)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_6_12)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_12_18)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_19)+"</td>");

			out.println("</tr>");
			
			
			out.println("<tr bgcolor='#FFFFCC'>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><B>Income</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='11%' STYLE='{font:  8pt arial; text-align:left;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'><b>"+nf.format(m_tot_int)+"</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' STYLE='{font:  8pt arial; text-align:right;}'>&nbsp;</td>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('0','3') STYLE='{font:  8pt arial; text-align:right;}'><u>"+nf.format(m_int_0_3)+"</u></td>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('3','6') STYLE='{font:  8pt arial; text-align:right;}'><u>"+nf.format(m_int_3_6)+"</u></td>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('6','12') STYLE='{font:  8pt arial; text-align:right;}'><u>"+nf.format(m_int_6_12)+"</u></td>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('12','18') STYLE='{font:  8pt arial; text-align:right;}'><u>"+nf.format(m_int_12_18)+"</u></td>");
			out.println("<td width='7%' style= cursor:hand; class=div_input onClick=show_detail_view('18','18') STYLE='{font:  8pt arial; text-align:right;}'><u>"+nf.format(m_int_19)+"</u></td>");

			out.println("</tr>");

			
			out.println("</table>");
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");

				
			
		}
		
		
				/*else if(m_chksql.equals("print_report_test")){
				
				String m_report_type=req.getParameter("report_type");
				
				
			 String m_date=req.getParameter("date");
				
				String m_report_date="";
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'dd-Month-YYYY') FROM DUAL");
				if(rs1.next()){
				m_report_date=rs1.getString(1);
				}


				//if(m_report_type.equals("AF")){
				out.println("<HTML><HEAD><TITLE>Finance -Capital OutStanding Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
		
				
				out.println("function show_detail_view(m_age_from,m_age_to){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Quality_Statement_Account?chksql=drill_down_transaction&age_from='+m_age_from+'&age_to='+m_age_to;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 fullscreen=1');"); 
				out.println("}");
				
				out.println("</script>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							

				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Finance - Capital OutStanding Report As At : "+m_report_date+" </B></TD></TR>");
				out.println("</TABLE>");
				
				String m_report_desc="";
				if(m_report_type.equals("AF")){
				m_report_desc="Asset Finance";
				}
				else if(m_report_type.equals("BD")){
				m_report_desc="Bike ";
				}
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>"+m_report_desc+"</B></TD></TR>");
				out.println("</TABLE>");

			
			
      out.println("<br>");
			//out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			out.println("<table width='70%' class='table' border='1'  align='center' cellspacing='0' cellspacing='1' >");

			//-------report header -------------------------------------------------------
			out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" ><td width='7%' class=factoring-letter-body align='center'><b>Contracts</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Cum Contracts</td>");
			out.println("<td width='11%' class=factoring-letter-body align='center'><b>Category</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Nil</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>Arrears</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>%</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>0-3 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>3-6 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>6-12 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>12-18 month</td>");
			out.println("<td width='7%' class=factoring-letter-body align='center'><b>>18 above</td>");
			out.println("</tr>");

				
			String sql_query="SELECT a.application_no, "+ //1
			" NVL(a.total_rentals,0) - NVL(a.rentals_paid,0) age, "+ //2
			" ROUND(NVL(a.total_amount,0),2) - (ROUND(NVL(a.settled_amount,0),2) + ROUND(NVL(a.ADJUSTED_AMOUNT,0),2) ) arrears, "+ //3
			" NVL(a.income,0), "+ //4
			" ROUND(NVL(a.arr_capital_portion,0)+ NVL(a.future_receivable,0) ,2) capital ,"+  //5
			" NVL(a.arreas_age_0_3,0), "+ //6
			" NVL(a.arreas_age_3_6,0), "+ //7
      " NVL(a.arreas_age_6_12,0),  "+ //8
			" NVL(a.arreas_age_12_18,0), "+ //9
			" NVL(a.arreas_age_19,0) ,"+ //10
			" NVL(a.interest,0) "+ //11
			" FROM af_re_tbd_portfolio_acc a "+
			" where a.ent_user='"+m_username+"' ";

		  
			rs1 = stmt1.executeQuery(sql_query);
		  boolean more2=rs1.next();
			double m_age=0;
			int m_cum_value=0;
			int m_age_0_count=0,m_age_1_count=0,m_age_2_count=0,m_age_3_count=0,m_age_4_count=0,m_age_6_count=0;
			double m_amount=0,m_age_0_m_amount=0,m_age_1_m_amount=0,m_age_2_m_amount=0,m_age_3_m_amount=0,m_age_6_m_amount=0;
			double m_amount_cap=0;
			double m_age_0_m_amount_cap=0,m_age_1_m_amount_cap=0,m_age_2_m_amount_cap=0,m_age_3_m_amount_cap=0,m_age_6_m_amount_cap=0;
			double m_age_0_3=0;
			double m_age_3_6_0=0,m_age_3_6_1=0;
			double m_age_6_12_0=0,m_age_6_12_1=0,m_age_6_12_2=0;
			double m_age_12_18_0=0,m_age_12_18_1=0,m_age_12_18_2=0,m_age_12_18_3=0;
			double m_age_19_0=0,m_age_19_1=0,m_age_19_2=0,m_age_19_3=0,m_age_19_4=0;
			double m_tot_0_3=0,m_tot_3_6=0,m_tot_6_12=0,m_tot_12_18=0,m_tot_19=0;
			double m_int_0_3=0,m_int_3_6=0,m_int_6_12=0,m_int_12_18=0,m_int_19=0,m_tot_int=0;
			
			while(more2){
			m_age=rs1.getDouble(2);
			m_amount=rs1.getDouble(3);
			m_amount_cap=rs1.getDouble(5);
			
			if ( m_age < 0  || (m_age >=0 && m_age <=3) ){
			m_age_0_count=m_age_0_count+1;
			m_age_0_m_amount+=m_amount;
			m_age_0_m_amount_cap=m_age_0_m_amount_cap+m_amount_cap;
			m_age_0_3+=rs1.getDouble(6);
			m_int_0_3+=rs1.getDouble(11);
			}
			else if (m_age >3 && m_age <=6){
			m_age_1_count=m_age_1_count+1;
			m_age_1_m_amount+=m_amount;
			//out.println("m_age_1_m_amount"+m_age_1_m_amount);
		  m_age_1_m_amount_cap=m_age_1_m_amount_cap+m_amount_cap;
			m_age_3_6_0+=rs1.getDouble(6);
			m_age_3_6_1+=rs1.getDouble(7);
			m_int_3_6+=rs1.getDouble(11);



			}
			else if (m_age >6 && m_age <=12){
			m_age_2_count=m_age_2_count+1;
			m_age_2_m_amount+=m_amount;
			//out.println("m_age_2_m_amount"+m_age_2_m_amount);
			m_age_2_m_amount_cap=m_age_2_m_amount_cap+m_amount_cap;
			m_age_6_12_0+=rs1.getDouble(6);
			m_age_6_12_1+=rs1.getDouble(7);
			m_age_6_12_2+=rs1.getDouble(8);
			m_int_6_12+=rs1.getDouble(11);

			}
			else if (m_age >12 && m_age <=18){
			m_age_3_count=m_age_3_count+1;
			m_age_3_m_amount+=m_amount;
			m_age_3_m_amount_cap=m_age_3_m_amount_cap+m_amount_cap;
			m_age_12_18_0+=rs1.getDouble(6);
			m_age_12_18_1+=rs1.getDouble(7);
			m_age_12_18_2+=rs1.getDouble(8);
			m_age_12_18_3+=rs1.getDouble(9);
			m_int_12_18+=rs1.getDouble(11);


			}
			else if(m_age>18 ){
		  m_age_6_count=m_age_6_count+1;
			m_age_6_m_amount+=m_amount;
			//out.println("m_age_6_m_amount"+m_age_6_m_amount);
			m_age_6_m_amount_cap=m_age_6_m_amount_cap+m_amount_cap;
			m_age_19_0+=rs1.getDouble(6);
			m_age_19_1+=rs1.getDouble(7);
			m_age_19_2+=rs1.getDouble(8);
			m_age_19_3+=rs1.getDouble(9);
			m_age_19_4+=rs1.getDouble(10);
			m_int_19+=rs1.getDouble(11);


			}
		  more2=rs1.next();
			}
			
			double m_tot_cap=0,m_tot_amount=0,arr_precentage=0;
     
			m_tot_cap=m_age_0_m_amount_cap+m_age_1_m_amount_cap+m_age_2_m_amount_cap+m_age_3_m_amount_cap+m_age_6_m_amount_cap;
			m_tot_amount=m_age_0_m_amount+m_age_1_m_amount+m_age_2_m_amount+m_age_3_m_amount+m_age_6_m_amount;
			m_tot_0_3=m_age_0_3+m_age_3_6_0+m_age_6_12_0+m_age_12_18_0+m_age_19_0;
			m_tot_3_6=m_age_3_6_1+m_age_6_12_1+m_age_12_18_1+m_age_19_1;
			m_tot_6_12=m_age_6_12_2+m_age_12_18_2+m_age_19_2;
			m_tot_12_18=m_age_12_18_3+m_age_19_3;
			m_tot_19=m_age_19_4;
			m_tot_int=m_int_0_3+m_int_3_6+m_int_6_12+m_int_12_18+m_int_19;


			/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
						out.println("<table border='1' width='100%' class='table' cellspacing='0' >"); 		
			//-------report header ------------------------------------------------------- <tr>
			out.println("</tr>");
      out.println("<td colspan='9' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");//<b> Figures As @  "+m_Letter_date+"
      out.println("</tr>");
			out.println("<tr class=pdn_txtpos2 ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>Collector</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total Arr. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			
			out.println("<tr bgcolor='lightgrey' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' style=\"{cursor:hand;}\" onClick=\"show_employee_drill('"+rs.getString(1)+"')\" class='rep-body' align='left'><u>"+rs.getString(4)+"</u></td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>"+rs.getString(27)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(11))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(12))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(13))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(15))+"</td>");
		  out.println("</tr>");
			//End Previos Month ---------------------------------------------------------------------------
			total=rs.getDouble(22)+rs.getDouble(23)+rs.getDouble(24)+rs.getDouble(25)+rs.getDouble(26);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			
			//Current Month -------------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(22))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(23))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(24))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(25))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' >"+nf.format(rs.getDouble(26))+"</td>");
		  out.println("</tr>");
			//End Current Month ---------------------------------------------------------------------------
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			//Capital Out Standing --------------------------------------------------------------------------------------------------
			out.println("<tr ><td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' >&nbsp;</td>");//class=pdn_txtpos2
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' ><b>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>No Arrears</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>Total C/O. Without Zero</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>1 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>2 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>3 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>4-5 Month</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>6-Above</td>");
			out.println("</tr>");
			out.println("<tr bgcolor='lightgrey'>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			total=rs.getDouble(28)+rs.getDouble(29)+rs.getDouble(30)+rs.getDouble(31)+rs.getDouble(32);
			total_count=rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)+rs.getInt(10);
			tot_without_zero=total_count-rs.getInt(5);
			//Current Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_prev_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(5)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(6)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(7)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(8)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(9)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(10)+"</td>");
		  out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(28))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(29))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(30))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(31))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC' align='center'>"+nf.format(rs.getDouble(32))+"</td>");
		  out.println("</tr>");
			//-------------------------------------------------------------------------------------------
			
			total=rs.getDouble(33)+rs.getDouble(34)+rs.getDouble(35)+rs.getDouble(36)+rs.getDouble(37);
			total_count=rs.getInt(16)+rs.getInt(17)+rs.getInt(18)+rs.getInt(19)+rs.getInt(20)+rs.getInt(21);
			tot_without_zero=total_count-rs.getInt(16);
			//Previous Month ---------------------------------------------------------------------------
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:left;}' class='rep-body' align='left'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'><b>"+m_curr_month+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(16)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+tot_without_zero+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(17)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(18)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(19)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(20)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >"+rs.getInt(21)+"</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' >&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(total)+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(33))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(34))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(35))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(36))+"</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' bgcolor='#FFFFCC'>"+nf.format(rs.getDouble(37))+"</td>");
		  out.println("</tr>");

			
			//End Capital Out Standing --------------------------------------------------------------------------------------------------

			out.println("<tr bgcolor='#993300' >");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt bold arial; text-align:center;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
			out.println("<td width='10%' STYLE='{font:  8pt  arial; text-align:right;}' class='rep-body' align='center'>&nbsp;</td>");
		  out.println("</tr>");
			
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");

				
			
		}*/
		
			else if(m_chksql.equals("drill_down_transaction")){
			int m_age_from=Integer.parseInt(req.getParameter("age_from"));		
			int m_age_to=Integer.parseInt(req.getParameter("age_to"));		
			out.println("<HTML><HEAD><TITLE> Portfolio Quality Statement Account </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' STYLE='{ color: black; font: 20pt arial;}'>");
			out.println("<TR><TD class=factoring-letter-body ><CENTER><B>Portfolio Quality Statement Account </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");

		
			String Sql_invoice="";
			if(m_age_from==0){		
			
			Sql_invoice="SELECT "+
			" a.application_no, "+ //1
			" a.finance_no, "+ //2
			" a.client_code,"+ //3
			" a.client_full_name, "+ //4
			" a.ent_user, "+ //5
			" a.division_code, "+ //6
			//" a.total_rentals - a.rentals_paid, "+ //7
			" NVL(a.age,0) age, "+ //7
			" a.total_amount-	a.settled_amount, "+ //8
			" a.income,  "+ //9
			" a.arr_capital_portion ,"+ //10
			" a.future_receivable, "+ //11
			" a.arr_capital_portion +	a.future_receivable, "+ //12
			" a.arreas_age_0_3, "+ //13
			" a.arreas_age_3_6, "+ //14
			" a.arreas_age_6_12, "+ //15
			" a.arreas_age_12_18, "+  //16
			" a.arreas_age_19, "+ //17
			" a.interest "+ //18
			" FROM af_re_tbd_portfolio_acc a "+
			" WHERE a.ent_user='"+m_username+"'  "+
			//" and NVL(a.age,0) >="+m_age_from+" "+
			" and NVL(a.age,0) <= "+m_age_to+" ";
			
     }
			else
			if(m_age_from==18){	
			
			 Sql_invoice="SELECT "+
			" a.application_no, "+ //1
			" a.finance_no, "+ //2
			" a.client_code,"+ //3
			" a.client_full_name, "+ //4
			" a.ent_user, "+ //5
			" a.division_code, "+ //6
			//" a.total_rentals - a.rentals_paid, "+ //7
			" NVL(a.age,0) age, "+ //7
			" a.total_amount-	a.settled_amount, "+ //8
			" a.income,  "+ //9
			" a.arr_capital_portion ,"+ //10
			" a.future_receivable, "+ //11
			" a.arr_capital_portion +	a.future_receivable, "+ //12
			" a.arreas_age_0_3, "+ //13
			" a.arreas_age_3_6, "+ //14
			" a.arreas_age_6_12, "+ //15
			" a.arreas_age_12_18, "+  //16
			" a.arreas_age_19, "+ //17
			" a.interest "+ //18
			" FROM af_re_tbd_portfolio_acc a "+
			" WHERE a.ent_user='"+m_username+"'  "+
			" and NVL(a.age,0) >"+m_age_from+" ";

			}
			else{
			Sql_invoice="SELECT "+
			" a.application_no, "+ //1
			" a.finance_no, "+ //2
			" a.client_code,"+ //3
			" a.client_full_name, "+ //4
			" a.ent_user, "+ //5
			" a.division_code, "+ //6
			//" a.total_rentals - a.rentals_paid, "+ //7
			" NVL(a.age,0) age, "+ //7
			" a.total_amount-	a.settled_amount, "+ //8
			" a.income,  "+ //9
			" a.arr_capital_portion ,"+ //10
			" a.future_receivable, "+ //11
			" a.arr_capital_portion +	a.future_receivable, "+ //12
			" a.arreas_age_0_3, "+ //13
			" a.arreas_age_3_6, "+ //14
			" a.arreas_age_6_12, "+ //15
			" a.arreas_age_12_18, "+  //16
			" a.arreas_age_19, "+ //17
			" a.interest "+ //18
			" FROM af_re_tbd_portfolio_acc a "+
			" WHERE a.ent_user='"+m_username+"'  "+
			" and NVL(a.age,0) >"+m_age_from+" "+
			" and NVL(a.age,0) <= "+m_age_to+" ";
			}
			rs=stmt1.executeQuery(Sql_invoice);
			boolean  more_inv =rs.next();
			double sum=0,sum_2=0,sum_3=0;
			int i=1;
					out.println("<table align='center' width='70%' class='table' border='1'  cellspacing='0' >");
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					out.println("<td width='1%' class=factoring-letter-body ><B>No</td>");
					out.println("<td width='10%'class=factoring-letter-body ><B>Finance No</td>");
					out.println("<td width='35%' class=factoring-letter-body  ><B>Client Name</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>Age</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>Arrears Amount</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>arreas_age_0_3</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>arreas_age_3_6</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>arreas_age_6_12</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>arreas_age_12_18</td>");
					out.println("<td width='7%' class=factoring-letter-body ><B>arreas_age_19t</td>");
					out.println("<td width='7%' class=factoring-letter-body align='right' ><B>Arrears Capital</td>");
					out.println("<td width='7%' class=factoring-letter-body align='right' ><B>Future Capital</td>");
					out.println("<td width='7%' class=factoring-letter-body align='right' ><B>Capital OutStanding</td>");
					out.println("<td width='7%' class=factoring-letter-body align='right' ><B>Income</td>");

					out.println("</tr>");
					
					while(more_inv){
					out.println("<tr >");
					out.println("<td width='1%' class=div_input>"+i+"</td>");
					out.println("<td width='10%' style= cursor:hand; class=div_input onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='35%' class=div_input style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='7%' bgcolor='#FFFFCC'align='right' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(16))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(17))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='7%' align='right' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='7%' bgcolor='lightgrey' align='right' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='7%' bgcolor='#FFFFCC' align='right' class=div_input>"+nf.format(rs.getDouble(18))+"</td>");

					out.println("</tr>");
					sum+=rs.getDouble(12);
					//sum_2+=rs.getDouble(11);
					sum_3+=rs.getDouble(18);
					more_inv =rs.next();
					i=i+1;
					}
					
					out.println("<tr >");
					out.println("<td width='1%' class=div_input>"+i+"</td>");
					out.println("<td width='10%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='35%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='7%' align='right' class=div_input>Total</td>");
					out.println("<td width='7%' bgcolor='lightgrey' align='right' class=div_input>"+nf.format(sum)+"</td>");
					out.println("<td width='7%' bgcolor='#FFFFCC' align='right' class=div_input>"+nf.format(sum_3)+"</td>");
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
