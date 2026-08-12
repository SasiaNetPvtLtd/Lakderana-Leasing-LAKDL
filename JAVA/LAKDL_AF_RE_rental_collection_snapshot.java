//Sandun Jayathilake on 10-06-2009

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_rental_collection_snapshot extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Statement stmt,stmt1,stmt2;	
	public ResultSet rs,rs1,rs2;
	Connection conn;
	CallableStatement callstmt1 =null;
	java.text.NumberFormat nf;
	
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
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
			String m_chksql = req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				String m_due_date=req.getParameter("due_date");
				
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_RENTAL_COL_SNAPSHOT(:1,:2);END;");
				callstmt1.setString(1,m_username);
				callstmt1.setString(2,m_date);
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
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
      
			out.println("var timerID;");
			out.println("var durationID=0;");
				

				


				
				
				
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

				
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_RE_rental_collection_snapshot?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_RE_rental_collection_snapshot?chksql=main_page';"); 
			out.println("}"); 
				

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_follow_up\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			//out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			//out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Rental Collection Snapshot - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Rental Collection Snapshot  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 			
			out.println("}"); 
					
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=340,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_EFF_VAL_DD.value=v_dd;");
			out.println("     document.Form1.TXT_EFF_VAL_MM.value=v_mm;");
			out.println("     document.Form1.TXT_EFF_VAL_YY.value=v_yy;");
			out.println("  }");			
			out.println("}");		
			
			out.println("function load_sys_date(){");
			//rs=stmt.executeQuery("SELECT TO_CHAR(LAST_DAY(SYSDATE),'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
			
			if(rs.next()){
			out.println("document.Form1.TXT_EFF_VAL_DD.value= '"+rs.getString(1)+"' ;");
			out.println("document.Form1.TXT_EFF_VAL_MM.value= '"+rs.getString(2)+"' ;");
			out.println("document.Form1.TXT_EFF_VAL_YY.value= '"+rs.getString(3)+"' ;");
			}
			
      out.println("} ");
			
			out.println("function run_report() {");
			out.println("	if(document.Form1.TXT_EFF_VAL_DD.value!=\"\" && document.Form1.TXT_EFF_VAL_MM.value!=\"\" && document.Form1.TXT_EFF_VAL_YY.value!=\"\"){");
			out.println("		m_date=document.Form1.TXT_EFF_VAL_DD.value+'-'+document.Form1.TXT_EFF_VAL_MM.value+'-'+document.Form1.TXT_EFF_VAL_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=run_report&date=\"+m_date;"); 
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
			out.println("	 if(document.Form1.TXT_EFF_VAL_DD.value!=\"\" && document.Form1.TXT_EFF_VAL_MM.value!=\"\" && document.Form1.TXT_EFF_VAL_YY.value!=\"\"){");
			out.println("		m_date=document.Form1.TXT_EFF_VAL_DD.value+'-'+document.Form1.TXT_EFF_VAL_MM.value+'-'+document.Form1.TXT_EFF_VAL_YY.value;");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report&date=\"+m_date;"); 
			out.println("	 window.open(m_url,'displayWindow29','left=50,top=100,width=930,height=380,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
			out.println("	}");
			out.println("}");
     
			out.println("function set_timer_actions() {");
		  out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
			
			out.println("function change_action(){");
			out.println("var actionlist = document.getElementById('report_list');");
      out.println("document.Form1.hid_report_type.value = actionlist.options[actionlist.selectedIndex].text;");
			out.println("}");	
			
				
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date()\">");  //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_Branch' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_report_type' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Rental Collection Snapshot</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>");
			
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onClick='close_window()' value=\"Close\"></td>");
			
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


		  out.println("<br>");  
		  out.println("<table class='table' border='0'> ");
			out.println("<tr>");
		  out.println("<td class=div_input>As At Date*</td>");
		  // Added By Samitha Kulatilaka On 2009-10-14 (Function Call On onBlur() Event)
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_DD maxlength=\"2\" size=\"2\" value=\"\" onBlur=checkMonthLength(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_MM  maxlength=\"2\" size=\"2\" value=\"\" onBlur=checkMonthLength(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_YY maxlength=\"4\" size=\"4\" value=\"\" onBlur=checkMonthLength(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY) ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("<td width='40%' align='center'><input type='button' value='View Report' name='BUT_VIEW' class='but_input' style='width:150px' onclick='print_report()'>");
			out.println("&nbsp;<input type='button' value='Run Report' name='BUT_RUN' class='but_input' style='width:150px' onclick='run_report()'></td>");
			out.println("<td width='20%'></td>");
			out.println("</tr>"); 			
		   
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			}
			else if(m_chksql.equals("print_report")){
			
			String m_date     = req.getParameter("date");
			
			rs=stmt.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-'),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8  
													 " A.BRANCH_CODE, "+//9
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER = '"+m_username+"' "+
													 " GROUP BY A.BRANCH_CODE ");
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("<script>"); 
			
			out.println("function  load_finance_no(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_finance_no_wise&mode=BR&date=\"+date+\"&value3=\"+value+\"\";");
			out.println("	 window.open(m_url,'displayWindow241','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function load_data(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_team_wise&date=\"+date+\"&value=\"+value+\"\";");
			out.println("	 window.open(m_url,'displayWindow291','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
			
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
			
			
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u> Rental Collection Snapshot - Branch Wise As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border=1 class='table' width='150%'  cellpadding=0 cellspacing=0 >");
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >No</td>");
			out.println("<td width='10%' align='center' >Branch</td>");
			out.println("<td width='10%' align='center' >No Of Contracts</td>");
			out.println("<td width='10%' align='center' colspan=2 >Suspend</td>");
			out.println("<td width='10%' align='center' colspan=2 >Provision</td>");
			out.println("<td width='10%' align='center'>Opening Balance</td>");
			out.println("<td width='10%' align='center'>Monthly Billed</td>");
			out.println("<td width='10%' align='center'>Monthly Target</td>");
			out.println("<td width='10%' align='center'>Realized Payments</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("<td width='10%' align='center'>Closing Balance</td>");
			out.println("<td width='10%' align='center' colspan=2>Returned Cheques</td>");
			out.println("<td width='10%' align='center'>Total Collection</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("</tr>");
			
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='5%' align='center' >No</td>");
			out.println("<td width='5%' align='center' >Amount</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("</tr>");
						
			int j=0;
			boolean more  = rs.next();	
		 	double m_tot_open_bal=0,m_tot_close_bal=0,m_monthly_billed=0,m_relize_payment=0,m_tot_collection=0,m_ret_cheque_amt=0;
			int m_no_ret_cheque =0,m_contract=0,m_susp_count=0,m_pro_count=0, m_tot_susp_count=0, m_tot_pro_count=0;
			double 	m_susp_amount=0,m_pro_amount=0,m_tot_pro_amount=0,m_tot_susp_amount=0,m_tot_month_target=0;
			while(more){	
			
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER =  '"+m_username+"' "+
													 " AND   A.branch_code = '"+rs.getString(9)+"' ");
			
			
			if(rs1.next()){
			m_susp_count = rs1.getInt(1);
			m_susp_amount= rs1.getDouble(2);
			}
			
			rs2=stmt2.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER =  '"+m_username+"' "+
													 " AND   A.branch_code = '"+rs.getString(9)+"' ");
			
			
			if(rs2.next()){
			m_pro_count = rs2.getInt(1);
			m_pro_amount= rs2.getDouble(2);
			}
			
			
			
			j++;
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='2%' align='center' >"+j+"</td>");
			if(rs.getString(1).equals("-")){
			out.println("<td width='10%' align='left' style='cursor:hand' onclick=\"load_data('"+rs.getString(9)+"')\" id=branch_"+j+" onmouseout=\"mouse_out('branch_"+j+"')\" onmouseover=\"mouse_over('branch_"+j+"')\">Bike Devision</td>");
			}else{
			out.println("<td width='10%' align='left' style='cursor:hand' onclick=\"load_data('"+rs.getString(9)+"')\" id=branch_"+j+" onmouseout=\"mouse_out('branch_"+j+"')\" onmouseover=\"mouse_over('branch_"+j+"')\">"+rs.getString(1)+" </td>");
			}
			out.println("<td width='10%' align='right' style='cursor:hand' onclick=\"load_finance_no('"+rs.getString(9)+"')\" id=finance_"+j+" onmouseout=\"mouse_out('finance_"+j+"')\" onmouseover=\"mouse_over('finance_"+j+"')\">"+rs.getInt(2)+"</td>");
			out.println("<td width='5%' align='right' >"+m_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)/rs.getDouble(10)*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
			out.println("<td width='5%'  align='right' >"+rs.getInt(7)+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)+rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format((rs.getDouble(5)+rs.getDouble(8))/rs.getDouble(10)*100)+"</td>");
			out.println("</tr>");
			
			m_tot_open_bal     = m_tot_open_bal  + rs.getDouble(3);
			m_tot_close_bal    = m_tot_close_bal + rs.getDouble(6);
			m_contract         = m_contract + rs.getInt(2);
			m_monthly_billed   = m_monthly_billed + rs.getDouble(4) ;
			m_relize_payment   = m_relize_payment + rs.getDouble(5) ;
			m_tot_collection   = m_tot_collection + rs.getDouble(5)+rs.getDouble(8) ;
			m_no_ret_cheque    = m_no_ret_cheque + rs.getInt(7);
			m_ret_cheque_amt   = m_ret_cheque_amt + rs.getDouble(8) ;
			m_tot_susp_count   = m_tot_susp_count  + m_susp_count;
		  m_tot_pro_count    = m_tot_pro_count  + m_pro_count;
			m_tot_susp_amount  = m_tot_susp_amount+m_susp_amount;
			m_tot_pro_amount   = m_tot_pro_amount+m_pro_amount;
			m_tot_month_target = m_tot_month_target+rs.getDouble(10) ;	
			more  = rs.next();	
			}
			
			if((j+1)%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			out.println("<td width='2%' align='center' >"+(j+1)+"</td>");
			out.println("<td width='12%' align='left'  style='cursor:hand' onclick=\"load_data('LAKDL')\" id=branch_"+(j+1)+" onmouseout=\"mouse_out('branch_"+(j+1)+"')\" onmouseover=\"mouse_over('branch_"+(j+1)+"')\" >LAKDL</td>");
			out.println("<td width='10%' align='right' style='cursor:hand' onclick=\"load_finance_no('LAKDL')\" id=finance_"+(j+1)+" onmouseout=\"mouse_out('finance_"+(j+1)+"')\" onmouseover=\"mouse_over('finance_"+(j+1)+"')\" >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
						
			
			out.println("<tr style='font:bold' style='height:25' bgcolor='FFFF99'>");
			out.println("<td width='12%' align='center' colspan=2>Total</td>");
			out.println("<td width='10%' align='right' >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
			
			
			
			out.println("</table>");
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			
			
			}
			else if(m_chksql.equals("print_report_team_wise")){
			
			String m_date     = req.getParameter("date");
			String m_branch     = req.getParameter("value");
			
		 if(m_branch.equals("LAKDL")){
			rs=stmt.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(A.TEAM_ID),'-'),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8  
													 "  A.TEAM_ID, "+//9
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " GROUP BY A.TEAM_ID ");
			}
			else{
			rs=stmt.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(A.TEAM_ID),'-'),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8  
													 "  A.TEAM_ID, "+//9
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.TEAM_ID ");
			
			}
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("<script>"); 
      
			out.println("function  load_finance_no(value2,value3){");
			out.println(" date     = '"+m_date+"' ; ");			
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_finance_no_wise&mode=TM&date=\"+date+\"&value2=\"+value2+\"&value3=\"+value3+\"\";");
			out.println("	 window.open(m_url,'displayWindow251','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function load_data(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" branch   = '"+m_branch+"' ; ");
			out.println("if(value==\"HOFFICEALL\"){");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_collector_wise&branch=\"+branch+\"&date=\"+date+\"&value=\"+value+\"\";");
			out.println("}else{");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_sub_team_wise&branch=\"+branch+\"&date=\"+date+\"&value=\"+value+\"\";");
			out.println("}");
			out.println("	 window.open(m_url,'displayWindow771','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
			
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
			
			
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u> Rental Collection Snapshot - Team Wise As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border=1 class='table' width='150%'  cellpadding=0 cellspacing=0 >");
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >No</td>");
			out.println("<td width='10%' align='center' >Team</td>");
			out.println("<td width='10%' align='center' >No Of Contracts</td>");
			out.println("<td width='10%' align='center' colspan=2 >Suspend</td>");
			out.println("<td width='10%' align='center' colspan=2 >Provision</td>");
			out.println("<td width='10%' align='center'>Opening Balance</td>");
			out.println("<td width='10%' align='center'>Monthly Billed</td>");
			out.println("<td width='10%' align='center'>Monthly Target</td>");
			out.println("<td width='10%' align='center'>Realized Payments</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("<td width='10%' align='center'>Closing Balance</td>");
			out.println("<td width='10%' align='center' colspan=2>Returned Cheques</td>");
			out.println("<td width='10%' align='center'>Total Collection</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("</tr>");
			
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='5%' align='center' >No</td>");
			out.println("<td width='5%' align='center' >Amount</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("</tr>");
						
					
			int j=0;
			boolean more  = rs.next();	
		 	double m_tot_open_bal=0,m_tot_close_bal=0,m_monthly_billed=0,m_relize_payment=0,m_tot_collection=0,m_ret_cheque_amt=0;
			int m_no_ret_cheque =0,m_contract=0,m_susp_count=0,m_pro_count=0, m_tot_susp_count=0, m_tot_pro_count=0;
			double 	m_susp_amount=0,m_pro_amount=0,m_tot_pro_amount=0,m_tot_susp_amount=0,m_tot_month_target=0;
			
			while(more){	
			if(m_branch.equals("LAKDL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+rs.getString(9)+"' ");
													 
			}else{
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+rs.getString(9)+"' "+
													 " AND   A.BRANCH_CODE = '"+m_branch+"'  ");
			}
			
			if(rs1.next()){
			m_susp_count = rs1.getInt(1);
			m_susp_amount= rs1.getDouble(2);
			}
			if(m_branch.equals("LAKDL")){
			rs2=stmt2.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER =  '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+rs.getString(9)+"' ");
													 
			}else{
			
			rs2=stmt2.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER =  '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+rs.getString(9)+"' "+
													 " AND  A.BRANCH_CODE = '"+m_branch+"'  ");
			}
			
			if(rs2.next()){
			m_pro_count = rs2.getInt(1);
			m_pro_amount= rs2.getDouble(2);
			}
			
			
			
			j++;
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='2%' align='center' >"+j+"</td>");
			
			out.println("<td width='10%' align='left' style='cursor:hand' onclick=\"load_data('"+rs.getString(9)+"')\" id=team_"+j+" onmouseout=\"mouse_out('team_"+j+"')\" onmouseover=\"mouse_over('team_"+j+"')\">"+rs.getString(1)+" </td>");
			out.println("<td width='10%' align='right' style='cursor:hand' onclick=\"load_finance_no('"+rs.getString(9)+"','"+m_branch+"')\" id=finance_"+j+" onmouseout=\"mouse_out('finance_"+j+"')\" onmouseover=\"mouse_over('finance_"+j+"')\">"+rs.getInt(2)+"</td>");
			out.println("<td width='5%' align='right' >"+m_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)/rs.getDouble(10)*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
			out.println("<td width='5%'  align='right' >"+rs.getInt(7)+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)+rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format((rs.getDouble(5)+rs.getDouble(8))/rs.getDouble(10)*100)+"</td>");
			out.println("</tr>");
			
			m_tot_open_bal     = m_tot_open_bal  + rs.getDouble(3);
			m_tot_close_bal    = m_tot_close_bal + rs.getDouble(6);
			m_contract         = m_contract + rs.getInt(2);
			m_monthly_billed   = m_monthly_billed + rs.getDouble(4) ;
			m_relize_payment   = m_relize_payment + rs.getDouble(5) ;
			m_tot_collection   = m_tot_collection + rs.getDouble(5)+rs.getDouble(8) ;
			m_no_ret_cheque    = m_no_ret_cheque + rs.getInt(7);
			m_ret_cheque_amt   = m_ret_cheque_amt + rs.getDouble(8) ;
			m_tot_susp_count   = m_tot_susp_count  + m_susp_count;
		  m_tot_pro_count    = m_tot_pro_count  + m_pro_count;
			m_tot_susp_amount  = m_tot_susp_amount+m_susp_amount;
			m_tot_pro_amount   = m_tot_pro_amount+m_pro_amount;
			m_tot_month_target = m_tot_month_target+rs.getDouble(10) ;			
			
			more  = rs.next();	
			}
			
			if(m_branch.equals("HEADOFFICE")){
			if((j+1)%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}		
			out.println("<td width='2%' align='center' >"+(j+1)+"</td>");
			out.println("<td width='12%' align='center' style='cursor:hand' onclick=\"load_data('HOFFICEALL')\" id=all_"+(j+1)+" onmouseout=\"mouse_out('all_"+(j+1)+"')\" onmouseover=\"mouse_over('all_"+(j+1)+"')\" >Headoffice All</td>");
			out.println("<td width='10%' align='right'  style='cursor:hand' onclick=\"load_finance_no('HOFFICEALL','"+m_branch+"')\" id=finance_"+(j+1)+" onmouseout=\"mouse_out('finance_"+(j+1)+"')\" onmouseover=\"mouse_over('finance_"+(j+1)+"')\" >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
			
			}
			
			
			
			out.println("<tr style='font:bold' style='height:25' bgcolor='FFFF99'>");
			out.println("<td width='12%' align='center' colspan=2>Total</td>");
			out.println("<td width='10%' align='right' >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
			
			
			
			out.println("</table>");
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			}
			
			else if(m_chksql.equals("print_report_sub_team_wise")){
			
			String m_date       = req.getParameter("date");
			String m_branch     = req.getParameter("branch");
			String m_team       = req.getParameter("value");
			
			
		 if(m_branch.equals("LAKDL")){
			rs=stmt.executeQuery(" SELECT "+
													 " '' ,"+
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8  
													 " A.SUB_TEAM_ID, "+//9
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'  "+	
													 " GROUP BY A.SUB_TEAM_ID ");
			}else{
			
			rs=stmt.executeQuery(" SELECT "+
													 " '' ,"+
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8  
													 " A.SUB_TEAM_ID, "+//9
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'  "+	
													 " GROUP BY A.SUB_TEAM_ID ");
			
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("<script>"); 
      
			out.println("function  load_finance_no(value2,value3){");
			out.println(" date     = '"+m_date+"' ; ");			
			out.println(" team     = '"+m_team+"' ; ");			
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_finance_no_wise&mode=ST&date=\"+date+\"&value2=\"+team+\"&value4=\"+value2+\"&value3=\"+value3+\"\";");
			out.println("	 window.open(m_url,'displayWindow251','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function load_data(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" branch   = '"+m_branch+"' ; ");
			out.println(" team     = '"+m_team+"' ; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_collector_wise&branch=\"+branch+\"&team=\"+team+\"&date=\"+date+\"&value=\"+value+\"\";");
			out.println("	 window.open(m_url,'displayWindow779','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
			
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
			
			
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u> Rental Collection Snapshot - Sub Team Wise As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border=1 class='table' width='150%'  cellpadding=0 cellspacing=0 >");
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >No</td>");
			out.println("<td width='10%' align='center' >Sub Team</td>");
			out.println("<td width='10%' align='center' >No Of Contracts</td>");
			out.println("<td width='10%' align='center' colspan=2 >Suspend</td>");
			out.println("<td width='10%' align='center' colspan=2 >Provision</td>");
			out.println("<td width='10%' align='center'>Opening Balance</td>");
			out.println("<td width='10%' align='center'>Monthly Billed</td>");
			out.println("<td width='10%' align='center'>Monthly Target</td>");
			out.println("<td width='10%' align='center'>Realized Payments</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("<td width='10%' align='center'>Closing Balance</td>");
			out.println("<td width='10%' align='center' colspan=2>Returned Cheques</td>");
			out.println("<td width='10%' align='center'>Total Collection</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("</tr>");
			
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='5%' align='center' >No</td>");
			out.println("<td width='5%' align='center' >Amount</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("</tr>");
						
					
			int j=0;
			boolean more  = rs.next();	
		 	double m_tot_open_bal=0,m_tot_close_bal=0,m_monthly_billed=0,m_relize_payment=0,m_tot_collection=0,m_ret_cheque_amt=0;
			int m_no_ret_cheque =0,m_contract=0,m_susp_count=0,m_pro_count=0, m_tot_susp_count=0, m_tot_pro_count=0;
			double 	m_susp_amount=0,m_pro_amount=0,m_tot_pro_amount=0,m_tot_susp_amount=0,m_tot_month_target=0;
			String m_sub_team_desc="";
			while(more){	
			
			rs1=stmt1.executeQuery("SELECT SUB_TEAM_DESC FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS 	WHERE  SUB_TEAM_ID='"+rs.getString(9)+"' ");
			if(rs1.next()){
			m_sub_team_desc = rs1.getString(1);
			}
			 if(m_branch.equals("LAKDL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+m_team+"' "+
													 " AND   A.SUB_TEAM_ID  = '"+rs.getString(9)+"' ");
													 
			}else{
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+m_team+"' "+
													 " AND   A.SUB_TEAM_ID  = '"+rs.getString(9)+"' "+
													 " AND   A.BRANCH_CODE = '"+m_branch+"'  ");
			}
			
			if(rs1.next()){
			m_susp_count = rs1.getInt(1);
			m_susp_amount= rs1.getDouble(2);
			}
			
			if(m_branch.equals("LAKDL")){
			rs2=stmt2.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+m_team+"' "+
													 " AND   A.SUB_TEAM_ID  = '"+rs.getString(9)+"' ");
													 
				}else{
				rs2=stmt2.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER = '"+m_username+"' "+
													 " AND   A.TEAM_ID  = '"+m_team+"' "+
													 " AND   A.SUB_TEAM_ID  = '"+rs.getString(9)+"' "+
													 " AND   A.BRANCH_CODE = '"+m_branch+"'  ");
				
				}
			
			
			if(rs2.next()){
			m_pro_count = rs2.getInt(1);
			m_pro_amount= rs2.getDouble(2);
			}
			
			
			
			j++;
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='2%' align='center' >"+j+"</td>");
			
			out.println("<td width='10%' align='left' style='cursor:hand' onclick=\"load_data('"+rs.getString(9)+"')\" id=team_"+j+" onmouseout=\"mouse_out('team_"+j+"')\" onmouseover=\"mouse_over('team_"+j+"')\">"+m_sub_team_desc+" </td>");
			out.println("<td width='10%' align='right' style='cursor:hand' onclick=\"load_finance_no('"+rs.getString(9)+"','"+m_branch+"')\" id=finance_"+j+" onmouseout=\"mouse_out('finance_"+j+"')\" onmouseover=\"mouse_over('finance_"+j+"')\">"+rs.getInt(2)+"</td>");
			out.println("<td width='5%' align='right' >"+m_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)/rs.getDouble(10)*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
			out.println("<td width='5%'  align='right' >"+rs.getInt(7)+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)+rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format((rs.getDouble(5)+rs.getDouble(8))/rs.getDouble(10)*100)+"</td>");
			out.println("</tr>");
			
			m_tot_open_bal     = m_tot_open_bal  + rs.getDouble(3);
			m_tot_close_bal    = m_tot_close_bal + rs.getDouble(6);
			m_contract         = m_contract + rs.getInt(2);
			m_monthly_billed   = m_monthly_billed + rs.getDouble(4) ;
			m_relize_payment   = m_relize_payment + rs.getDouble(5) ;
			m_tot_collection   = m_tot_collection + rs.getDouble(5)+rs.getDouble(8) ;
			m_no_ret_cheque    = m_no_ret_cheque + rs.getInt(7);
			m_ret_cheque_amt   = m_ret_cheque_amt + rs.getDouble(8) ;
			m_tot_susp_count   = m_tot_susp_count  + m_susp_count;
		  m_tot_pro_count    = m_tot_pro_count  + m_pro_count;
			m_tot_susp_amount  = m_tot_susp_amount+m_susp_amount;
			m_tot_pro_amount   = m_tot_pro_amount+m_pro_amount;
			m_tot_month_target = m_tot_month_target+rs.getDouble(10) ;	
			more  = rs.next();	
			}
			out.println("<tr style='font:bold' style='height:25' bgcolor='FFFF99'>");
			out.println("<td width='12%' align='center' colspan=2>Total</td>");
			out.println("<td width='10%' align='right' >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
			
			
			
			out.println("</table>");
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			}			
			
			
			
			
			
			else if(m_chksql.equals("print_report_collector_wise")){
			
			String m_date     = req.getParameter("date");
			String m_sub_team = req.getParameter("value");
			String m_branch   = req.getParameter("branch");
			String m_team     = req.getParameter("team");
			
			if(m_sub_team.equals("HOFFICEALL")){
			rs=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTOR_ID),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8 
													 " A.COLLECTOR_ID, "+														
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"'   "+
													 " GROUP BY A.COLLECTOR_ID ");
			
			}else if(m_branch.equals("LAKDL")){						
			rs=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTOR_ID),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8 
													 " A.COLLECTOR_ID, "+														
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     "+
													 " AND A.SUB_TEAM_ID = '"+m_sub_team+"' "+
													 " GROUP BY A.COLLECTOR_ID ");
			}
			else{
			rs=stmt.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTOR_ID),  "+//1
									         " COUNT(A.FINANCE_NO), "+//2
									         " SUM(A.OPENING_BALANCE), "+//3
									         " SUM(A.MONTHLY_BILLED),  "+//4
									         " SUM(A.REALIZED_PAYMENT),  "+//5
									         " SUM(A.CLOSING_BALANCE), "+//6
									         " SUM(A.RETURN_CHQ_COUNT),  "+//7
									         " SUM(A.RETURN_CHQ_AMOUNT), "+    //8 
													 " A.COLLECTOR_ID, "+														
													 " NVL(SUM("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT(FINANCE_NO,TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'Mon-YYYY'))),0)+SUM(RENTAL_AMOUNT) "+//10
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     "+
													 " AND A.SUB_TEAM_ID = '"+m_sub_team+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"'   "+
													 " GROUP BY A.COLLECTOR_ID ");
			
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("<script>"); 
			
			out.println("function  load_finance_no(value1,value2,value3){");
			out.println(" date     = '"+m_date+"' ; ");
			if(m_sub_team.equals("HOFFICEALL")){
			out.println(" value2   = '"+m_sub_team+"' ; ");
			}
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_collection_snapshot?chksql=print_report_finance_no_wise&mode=CO&date=\"+date+\"&value3=\"+value3+\"&value2=\"+value2+\"&value1=\"+value1+\"\";");
			out.println("	 window.open(m_url,'displayWindow261','left=150,top=100,width=830,height=480,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
			out.println("}");
			
     	out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
			
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
		
			
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u> Rental Collection Snapshot - Collector wise As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border=1 class='table' width='150%'  cellpadding=0 cellspacing=0 >");
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >No</td>");
			out.println("<td width='10%' align='center' >Collector</td>");
			out.println("<td width='10%' align='center' >No Of Contracts</td>");
			out.println("<td width='10%' align='center' colspan=2 >Suspend</td>");
			out.println("<td width='10%' align='center' colspan=2 >Provision</td>");
			out.println("<td width='10%' align='center'>Opening Balance</td>");
			out.println("<td width='10%' align='center'>Monthly Billed</td>");
			out.println("<td width='10%' align='center'>Monthly Target</td>");
			out.println("<td width='10%' align='center'>Realized Payments</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("<td width='10%' align='center'>Closing Balance</td>");
			out.println("<td width='10%' align='center' colspan=2>Returned Cheques</td>");
			out.println("<td width='10%' align='center'>Total Collection</td>");
			out.println("<td width='10%' align='center'>%</td>");
			out.println("</tr>");
			
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='10%' align='center' >&nbsp;</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='5%' align='center' >Contracts</td>");
			out.println("<td width='5%' align='center' >Income</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='5%' align='center' >No</td>");
			out.println("<td width='5%' align='center' >Amount</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("<td width='10%' align='center'>&nbsp;</td>");
			out.println("</tr>");
						
						
			int j=0;
			boolean more  = rs.next();	
		 	double m_tot_open_bal=0,m_tot_close_bal=0,m_monthly_billed=0,m_relize_payment=0,m_tot_collection=0,m_ret_cheque_amt=0;
			int m_no_ret_cheque =0,m_contract=0,m_susp_count=0,m_pro_count=0, m_tot_susp_count=0, m_tot_pro_count=0;
			double 	m_susp_amount=0,m_pro_amount=0,m_tot_pro_amount=0,m_tot_susp_amount=0,m_tot_month_target=0;
			
			while(more){	
			if(m_sub_team.equals("HOFFICEALL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND A.BRANCH_CODE    = '"+m_branch+"'   ");
			}else if(m_branch.equals("LAKDL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND   A.COLLECTOR_ID = '"+rs.getString(9)+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     ");
													 
			
			}else{
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW > 3 "+
													 " AND   A.AGE_NEW < 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND   A.COLLECTOR_ID = '"+rs.getString(9)+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     "+
													  " AND A.BRANCH_CODE = '"+m_branch+"'   ");
			
			
			}
			
			if(rs1.next()){
			m_susp_count = rs1.getInt(1);
			m_susp_amount= rs1.getDouble(2);
			}
						
			if(m_sub_team.equals("HOFFICEALL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													  " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND A.BRANCH_CODE    = '"+m_branch+"'   ");
														
			}else if(m_branch.equals("LAKDL")){
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													  " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND   A.COLLECTOR_ID = '"+rs.getString(9)+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     ");
													 
			
			}else{
			rs1=stmt1.executeQuery(" SELECT COUNT(*),NVL(SUM(A.INCOME),0) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE A.AGE_NEW >= 6 "+
													 " AND   A.ENT_USER     =  '"+m_username+"' "+
													 " AND   A.COLLECTOR_ID = '"+rs.getString(9)+"' "+
													 " AND A.TEAM_ID     = '"+m_team+"'     "+
													  " AND A.BRANCH_CODE = '"+m_branch+"'   ");
			
			
			}
			if(rs2.next()){
			m_pro_count = rs2.getInt(1);
			m_pro_amount= rs2.getDouble(2);
			}
			
			
			
			j++;
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='2%' align='center' >"+j+"</td>");
			
			out.println("<td width='10%' align='left' >"+rs.getString(1)+" </td>");
			out.println("<td width='10%' align='right' style='cursor:hand' onclick=\"load_finance_no('"+rs.getString(9)+"','"+m_team+"','"+m_branch+"')\" id=finance_"+j+" onmouseout=\"mouse_out('finance_"+j+"')\" onmouseover=\"mouse_over('finance_"+j+"')\">"+rs.getInt(2)+"</td>");
			out.println("<td width='5%' align='right' >"+m_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(10))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)/rs.getDouble(10)*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
			out.println("<td width='5%'  align='right' >"+rs.getInt(7)+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)+rs.getDouble(8))+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format((rs.getDouble(5)+rs.getDouble(8))/rs.getDouble(10)*100)+"</td>");
			out.println("</tr>");
			
			m_tot_open_bal     = m_tot_open_bal  + rs.getDouble(3);
			m_tot_close_bal    = m_tot_close_bal + rs.getDouble(6);
			m_contract         = m_contract + rs.getInt(2);
			m_monthly_billed   = m_monthly_billed + rs.getDouble(4) ;
			m_relize_payment   = m_relize_payment + rs.getDouble(5) ;
			m_tot_collection   = m_tot_collection + rs.getDouble(5)+rs.getDouble(8) ;
			m_no_ret_cheque    = m_no_ret_cheque + rs.getInt(7);
			m_ret_cheque_amt   = m_ret_cheque_amt + rs.getDouble(8) ;
			m_tot_susp_count   = m_tot_susp_count  + m_susp_count;
		  m_tot_pro_count    = m_tot_pro_count  + m_pro_count;
			m_tot_susp_amount  = m_tot_susp_amount+m_susp_amount;
			m_tot_pro_amount   = m_tot_pro_amount+m_pro_amount;
			m_tot_month_target = m_tot_month_target+rs.getDouble(10) ;	
			more  = rs.next();	
			}
			out.println("<tr style='font:bold' style='height:25' bgcolor='FFFF99'>");
			out.println("<td width='12%' align='center' colspan=2>Total</td>");
			out.println("<td width='10%' align='right' >"+m_contract+"</td>");
			out.println("<td width='5%' align='right' >"+m_tot_susp_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_susp_amount)+"
			out.println("<td width='5%' align='right' >"+m_tot_pro_count+"</td>");
			out.println("<td width='5%' align='right' >&nbsp;</td>");//"+nf.format(m_tot_pro_amount)+"
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_open_bal)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_monthly_billed)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_month_target)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_relize_payment/m_tot_month_target*100)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_close_bal)+"</td>");
			out.println("<td width='5%' align='right' >"+m_no_ret_cheque+"</td>");
			out.println("<td width='5%' align='right' >"+nf.format(m_ret_cheque_amt)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(m_tot_collection)+"</td>");
			out.println("<td width='10%' align='center'>"+nf.format(m_tot_collection/m_tot_month_target*100)+"</td>");
			out.println("</tr>");
			
			
			
			out.println("</table>");
			
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			}
			
			else if(m_chksql.equals("print_report_finance_no_wise")){
			
			String m_date     = req.getParameter("date");
			String m_branch   = req.getParameter("value3");
			String m_team     = req.getParameter("value2");
			String m_collector= req.getParameter("value1");
			String m_mode     = req.getParameter("mode");
			String m_sub_team = req.getParameter("value4");
		
			 
			if(m_mode.equals("BR")){
					if(m_branch.equals("LAKDL")){
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+
													 " SUM(A.MONTHLY_BILLED), "+
													 " SUM(A.REALIZED_PAYMENT), "+
													 " SUM(A.CLOSING_BALANCE), "+
													 " SUM(A.RETURN_CHQ_COUNT), "+
													 " SUM(A.RETURN_CHQ_AMOUNT) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER = '"+m_username+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
						}else{
					    rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+
													 " SUM(A.MONTHLY_BILLED), "+
													 " SUM(A.REALIZED_PAYMENT), "+
													 " SUM(A.CLOSING_BALANCE), "+
													 " SUM(A.RETURN_CHQ_COUNT), "+
													 " SUM(A.RETURN_CHQ_AMOUNT) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER = '"+m_username+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");	
						
						
						}
			
		
			}else if(m_mode.equals("TM")){
			  if(m_team.equals("HOFFICEALL")){
			   rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+//2
													 " SUM(A.MONTHLY_BILLED), "+//3
													 " SUM(A.REALIZED_PAYMENT), "+//4
													 " SUM(A.CLOSING_BALANCE), "+//5
													 " SUM(A.RETURN_CHQ_COUNT), "+//6
													 " SUM(A.RETURN_CHQ_AMOUNT) "+//7
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
					}else if(m_branch.equals("LAKDL")){
					 rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+//2
													 " SUM(A.MONTHLY_BILLED), "+//3
													 " SUM(A.REALIZED_PAYMENT), "+//4
													 " SUM(A.CLOSING_BALANCE), "+//5
													 " SUM(A.RETURN_CHQ_COUNT), "+//6
													 " SUM(A.RETURN_CHQ_AMOUNT) "+//7
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
					}else{
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+//2
													 " SUM(A.MONTHLY_BILLED), "+//3
													 " SUM(A.REALIZED_PAYMENT), "+//4
													 " SUM(A.CLOSING_BALANCE), "+//5
													 " SUM(A.RETURN_CHQ_COUNT), "+//6
													 " SUM(A.RETURN_CHQ_AMOUNT) "+//7
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
					}									
			
			}else if(m_mode.equals("CO")){
			if(m_branch.equals("LAKDL")){
			rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+
													 " SUM(A.MONTHLY_BILLED), "+
													 " SUM(A.REALIZED_PAYMENT), "+
													 " SUM(A.CLOSING_BALANCE), "+
													 " SUM(A.RETURN_CHQ_COUNT), "+
													 " SUM(A.RETURN_CHQ_AMOUNT) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.COLLECTOR_ID = '"+m_collector+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
														
					}else if(m_team.equals("HOFFICEALL")){
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+
													 " SUM(A.MONTHLY_BILLED), "+
													 " SUM(A.REALIZED_PAYMENT), "+
													 " SUM(A.CLOSING_BALANCE), "+
													 " SUM(A.RETURN_CHQ_COUNT), "+
													 " SUM(A.RETURN_CHQ_AMOUNT) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.COLLECTOR_ID = '"+m_collector+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
					
					
					}else{
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+
													 " SUM(A.MONTHLY_BILLED), "+
													 " SUM(A.REALIZED_PAYMENT), "+
													 " SUM(A.CLOSING_BALANCE), "+
													 " SUM(A.RETURN_CHQ_COUNT), "+
													 " SUM(A.RETURN_CHQ_AMOUNT) "+
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.COLLECTOR_ID = '"+m_collector+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " AND A.BRANCH_CODE = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
					
					}
			}
			else if(m_mode.equals("ST")){
			if(m_branch.equals("LAKDL")){
			rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+//2
													 " SUM(A.MONTHLY_BILLED), "+//3
													 " SUM(A.REALIZED_PAYMENT), "+//4
													 " SUM(A.CLOSING_BALANCE), "+//5
													 " SUM(A.RETURN_CHQ_COUNT), "+//6
													 " SUM(A.RETURN_CHQ_AMOUNT) "+//7
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " AND A.SUB_TEAM_ID  = '"+m_sub_team+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
			}
			else{
			rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
			                     " A.APPLICATION_NO,"+
													 " "+m_schema_name+".AF_CO_GET_CLI_NAME(A.APPLICATION_NO),"+
													 " SUM(A.OPENING_BALANCE), "+//2
													 " SUM(A.MONTHLY_BILLED), "+//3
													 " SUM(A.REALIZED_PAYMENT), "+//4
													 " SUM(A.CLOSING_BALANCE), "+//5
													 " SUM(A.RETURN_CHQ_COUNT), "+//6
													 " SUM(A.RETURN_CHQ_AMOUNT) "+//7
													 " FROM "+m_schema_name+".AF_RE_TBD_RENTAL_COL_SNAPSHOT A "+
													 " WHERE  A.ENT_USER  = '"+m_username+"' "+
													 " AND A.TEAM_ID      = '"+m_team+"' "+
													 " AND A.SUB_TEAM_ID  = '"+m_sub_team+"' "+
													 " AND A.BRANCH_CODE  = '"+m_branch+"' "+
													 " GROUP BY A.FINANCE_NO,A.APPLICATION_NO,A.BRANCH_CODE,A.TEAM_ID ");
			}
			}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Rental Collection Snapshot</TITLE>"); 
			out.println("<script>"); 
						      
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
			
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");
		
			
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='100%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u>Rental Collection Snapshot - Contract Details As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
				
						
			out.println("<table border=1 class='table' width='100%'  cellpadding=0 cellspacing=0 >");
			out.println("<tr style='font:bold' class='pdn_txtpos2'>");
			out.println("<td width='2%'  align='center' >No</td>");
			out.println("<td width='15%' align='left' >Finance No</td>");
			out.println("<td width='15%' align='left' >Application No</td>");
			out.println("<td width='30%' align='left' >Client Name</td>");
			out.println("<td width='15%' align='right' >Opening Balance</td>");
			out.println("</tr>");
				int j=0;
				
			boolean more  = rs.next();	
			
			while(more){	
			
			j++;
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			out.println("<td width='2%'  align='center' >"+j+"</td>");
			out.println("<td width='10%' align='left' style='cursor:hand' onclick=\"show_finance_detail_drill('"+rs.getString(1)+"')\" id=fin_"+j+" onmouseout=\"mouse_out('fin_"+j+"')\" onmouseover=\"mouse_over('fin_"+j+"')\">"+rs.getString(1)+" </td>");
			out.println("<td width='15%' align='left' >"+rs.getString(2)+"</td>");
			out.println("<td width='30%' align='left' >"+rs.getString(3)+"</td>");
			out.println("<td width='15%' align='right' >"+nf.format(rs.getDouble(4))+"</td>");
			out.println("</tr>");
			more  = rs.next();
			}
			
			out.println("</table>");
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
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
