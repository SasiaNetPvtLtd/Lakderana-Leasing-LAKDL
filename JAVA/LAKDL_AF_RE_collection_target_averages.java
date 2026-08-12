//Sandun Jayathilake on 26-06-2009

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_collection_target_averages extends javax.servlet.http.HttpServlet { 

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
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_COLL_TARGET_AVARAGES(:1,:2);END;");
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
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_RE_collection_target_averages?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_RE_collection_target_averages?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\" Collection Process - Collection Target Averages - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Target Averages  \"+document.Form1.hid_status.value;"); 
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
			rs=stmt.executeQuery("SELECT TO_CHAR(LAST_DAY(SYSDATE),'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
			
			if(rs.next()){
			out.println("document.Form1.TXT_EFF_VAL_DD.value= '"+rs.getString(1)+"' ;");
			out.println("document.Form1.TXT_EFF_VAL_MM.value= '"+rs.getString(2)+"' ;");
			out.println("document.Form1.TXT_EFF_VAL_YY.value= '"+rs.getString(3)+"' ;");
			}
			
      out.println("} ");
			
			out.println("function run_report() {");
			out.println("	if(document.Form1.TXT_EFF_VAL_DD.value!=\"\" && document.Form1.TXT_EFF_VAL_MM.value!=\"\" && document.Form1.TXT_EFF_VAL_YY.value!=\"\"){");
			out.println("		m_date=document.Form1.TXT_EFF_VAL_DD.value+'-'+document.Form1.TXT_EFF_VAL_MM.value+'-'+document.Form1.TXT_EFF_VAL_YY.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_collection_target_averages?chksql=run_report&date=\"+m_date;"); 
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
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_collection_target_averages?chksql=print_report&date=\"+m_date;"); 
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
			
			out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			out.println("}");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Target Averages</td>"); 
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
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_DD maxlength=\"2\" size=\"2\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_MM  maxlength=\"2\" size=\"2\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_YY maxlength=\"4\" size=\"4\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY) ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
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
			 int j=0;
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
			 String m_month_12="";
			 double m_tot_ach=0,m_tot_month_0=0,m_tot_month_1=0,m_tot_month_2=0,m_tot_month_3=0,m_tot_month_4=0,m_tot_month_5=0,m_tot_month_6=0,m_tot_month_7=0,m_tot_month_8=0,m_tot_month_9=0,m_tot_month_10=0,m_tot_month_11=0;
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Target Average</TITLE>"); 
			out.println("<script>"); 
			
			out.println("function load_data(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_collection_target_averages?chksql=print_report_team_wise&date=\"+date+\"&value=\"+value+\"\";");
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
			out.println("<tr width='100%'>");
			out.println("<td align='center'><b><u>Collection Target Average As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			  rs=stmt.executeQuery("SELECT  TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0),'Mon-YYYY'), "+//1
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon-YYYY'), "+//2
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'Mon-YYYY'), "+//3
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'Mon-YYYY'), "+//4
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'Mon-YYYY'), "+//5
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'Mon-YYYY'), "+//6
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'Mon-YYYY'), "+//7
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'Mon-YYYY'), "+//8
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'Mon-YYYY'), "+//9
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'Mon-YYYY'), "+//10
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'Mon-YYYY'), "+//11
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'Mon-YYYY') "+//12
        " FROM DUAL ");
				
				out.println("<table  width='150%'  class='table' border=1 cellspacing=0 cellpadding=0>");
			
			if(rs.next()){
			m_month_1  = rs.getString(1);
			m_month_2  = rs.getString(2);
			m_month_3  = rs.getString(3);
			m_month_4  = rs.getString(4);
			m_month_5  = rs.getString(5);
			m_month_6  = rs.getString(6);
			m_month_7  = rs.getString(7);
			m_month_8  = rs.getString(8);
			m_month_9  = rs.getString(9);
			m_month_10 = rs.getString(10);
			m_month_11 = rs.getString(11);
			m_month_12 = rs.getString(12);
			}
			
			out.println("<tr class=pdn_txtpos2  >");
			out.println("<td width='10%' align ='left' ><b>Branch</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_1+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_2+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_3+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_4+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_5+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_6+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_7+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_8+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_9+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_10+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_11+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_12+"</td>");
			out.println("<td width='8%' align ='center'><b>Total</td>");
			out.println("</tr >"); 
			
			
			rs=stmt.executeQuery(" SELECT A.BRANCH_CODE,  "+
			                     " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-'), "+
													 " SUM(A.MONTH_0_ACHVEMENT), "+
													 " SUM(A.MONTH_1_ACHVEMENT), "+
													 " SUM(A.MONTH_2_ACHVEMENT), "+
													 " SUM(A.MONTH_3_ACHVEMENT), "+
													 " SUM(A.MONTH_4_ACHVEMENT), "+
													 " SUM(A.MONTH_5_ACHVEMENT), "+
													 " SUM(A.MONTH_6_ACHVEMENT), "+
													 " SUM(A.MONTH_7_ACHVEMENT), "+
													 " SUM(A.MONTH_8_ACHVEMENT), "+ 
													 " SUM(A.MONTH_9_ACHVEMENT), "+
													 " SUM(A.MONTH_10_ACHVEMENT),"+ 
													 " SUM(A.MONTH_11_ACHVEMENT), "+
													 " SUM(TOT_MONTH_ACHVEMENT) "+	
													 " FROM "+m_schema_name+".AF_RE_TBD_COL_TARGET_AVERAGE A "+
													 " WHERE A.ENT_USER = '"+m_username+"' "+
													 " AND  TOT_MONTH_ACHVEMENT >0 "+													
													 " GROUP BY A.BRANCH_CODE ");
			
			boolean more = rs.next();
			while(more){
			j=j+1;
			
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='10%' align ='left' style='cursor:hand' id=branch_"+j+" onmouseout=\"mouse_out('branch_"+j+"')\" onmouseover=\"mouse_over('branch_"+j+"')\" onclick=\"load_data('"+rs.getString(1)+"')\">"+rs.getString(2)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(8))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(9))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(10))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(11))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(12))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(13))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(15))+"</td>");
			out.println("</tr>");
			
			
			m_tot_month_0  = m_tot_month_0+rs.getDouble(3);
			m_tot_month_1  = m_tot_month_1+rs.getDouble(4);
			m_tot_month_2  = m_tot_month_2+rs.getDouble(5);
			m_tot_month_3  = m_tot_month_3+rs.getDouble(6);
			m_tot_month_4  = m_tot_month_4+rs.getDouble(7);
			m_tot_month_5  = m_tot_month_5+rs.getDouble(8);
			m_tot_month_6  = m_tot_month_6+rs.getDouble(9);
			m_tot_month_7  = m_tot_month_7+rs.getDouble(10);
			m_tot_month_8  = m_tot_month_8+rs.getDouble(11);
			m_tot_month_9  = m_tot_month_9+rs.getDouble(12);
			m_tot_month_10 = m_tot_month_10+rs.getDouble(13);
			m_tot_month_11 = m_tot_month_11+rs.getDouble(14);
			m_tot_ach      = m_tot_ach +rs.getDouble(15);
			
			more = rs.next();
			}
			
			out.println("<tr  bgcolor='FFFF99' style='height:25'>");
			out.println("<td width='10%' align ='right' ><b>Total</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_0/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_1/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_2/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_3/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_4/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_5/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_6/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_7/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_8/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_9/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_10/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_11/j)+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_ach/j)+"</td>");
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
			  int j=0;
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
			 String m_month_12="";
			 double  m_tot_ach=0,m_tot_month_0=0,m_tot_month_1=0,m_tot_month_2=0,m_tot_month_3=0,m_tot_month_4=0,m_tot_month_5=0,m_tot_month_6=0,m_tot_month_7=0,m_tot_month_8=0,m_tot_month_9=0,m_tot_month_10=0,m_tot_month_11=0;
			
					
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Target Average</TITLE>"); 
			out.println("<script>"); 
      
					
			out.println("function load_data(value){");
			out.println(" date     = '"+m_date+"' ; ");
			out.println(" branch   = '"+m_branch+"' ; ");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_collection_target_averages?chksql=print_report_collector_wise&branch=\"+branch+\"&date=\"+date+\"&value=\"+value+\"\";");
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
			out.println("<table border=0 class='table' width='100%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u> Collection Target Average As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			rs=stmt.executeQuery("SELECT  TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0),'Mon-YYYY'), "+//1
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon-YYYY'), "+//2
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'Mon-YYYY'), "+//3
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'Mon-YYYY'), "+//4
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'Mon-YYYY'), "+//5
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'Mon-YYYY'), "+//6
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'Mon-YYYY'), "+//7
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'Mon-YYYY'), "+//8
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'Mon-YYYY'), "+//9
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'Mon-YYYY'), "+//10
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'Mon-YYYY'), "+//11
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'Mon-YYYY') "+//12
        " FROM DUAL ");
				
				out.println("<table  width='150%'  class='table' border=1 cellspacing=0 cellpadding=0>");
			
			if(rs.next()){
			m_month_1  = rs.getString(1);
			m_month_2  = rs.getString(2);
			m_month_3  = rs.getString(3);
			m_month_4  = rs.getString(4);
			m_month_5  = rs.getString(5);
			m_month_6  = rs.getString(6);
			m_month_7  = rs.getString(7);
			m_month_8  = rs.getString(8);
			m_month_9  = rs.getString(9);
			m_month_10 = rs.getString(10);
			m_month_11 = rs.getString(11);
			m_month_12 = rs.getString(12);
			}
			
			out.println("<tr class=pdn_txtpos2  >");
			out.println("<td width='10%' align ='left' ><b>Team</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_1+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_2+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_3+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_4+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_5+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_6+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_7+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_8+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_9+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_10+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_11+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_12+"</td>");
			out.println("<td width='8%' align ='center'><b>Total</td>");
			
			out.println("</tr >"); 
			
			
			rs=stmt.executeQuery(" SELECT A.TEAM_ID,  "+
			                     " NVL("+m_schema_name+".AF_CO_GET_TEAM_DESC(A.TEAM_ID),'-'), "+
													 " SUM(A.MONTH_0_ACHVEMENT), "+
													 " SUM(A.MONTH_1_ACHVEMENT), "+
													 " SUM(A.MONTH_2_ACHVEMENT), "+
													 " SUM(A.MONTH_3_ACHVEMENT), "+
													 " SUM(A.MONTH_4_ACHVEMENT), "+
													 " SUM(A.MONTH_5_ACHVEMENT), "+
													 " SUM(A.MONTH_6_ACHVEMENT), "+
													 " SUM(A.MONTH_7_ACHVEMENT), "+
													 " SUM(A.MONTH_8_ACHVEMENT), "+ 
													 " SUM(A.MONTH_9_ACHVEMENT), "+
													 " SUM(A.MONTH_10_ACHVEMENT),"+ 
													 " SUM(A.MONTH_11_ACHVEMENT), "+
													 " SUM(TOT_MONTH_ACHVEMENT) "+	
													 " FROM "+m_schema_name+".AF_RE_TBD_COL_TARGET_AVERAGE A "+
													 " WHERE A.BRANCH_CODE = '"+m_branch+"' "+	
													 " AND A.ENT_USER = '"+m_username+"' "+
													 " AND  TOT_MONTH_ACHVEMENT >0 "+					
													 " GROUP BY A.TEAM_ID ");
			
			boolean more = rs.next();
			while(more){
			j=j+1;
			
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='10%' align ='left' style='cursor:hand' id=team_"+j+" onmouseout=\"mouse_out('team_"+j+"')\" onmouseover=\"mouse_over('team_"+j+"')\" onclick=\"load_data('"+rs.getString(1)+"')\">"+rs.getString(2)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(8))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(9))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(10))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(11))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(12))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(13))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(15))+"</td>");
			out.println("</tr>");
			
			
			m_tot_month_0  = m_tot_month_0+rs.getDouble(3);
			m_tot_month_1  = m_tot_month_1+rs.getDouble(4);
			m_tot_month_2  = m_tot_month_2+rs.getDouble(5);
			m_tot_month_3  = m_tot_month_3+rs.getDouble(6);
			m_tot_month_4  = m_tot_month_4+rs.getDouble(7);
			m_tot_month_5  = m_tot_month_5+rs.getDouble(8);
			m_tot_month_6  = m_tot_month_6+rs.getDouble(9);
			m_tot_month_7  = m_tot_month_7+rs.getDouble(10);
			m_tot_month_8  = m_tot_month_8+rs.getDouble(11);
			m_tot_month_9  = m_tot_month_9+rs.getDouble(12);
			m_tot_month_10 = m_tot_month_10+rs.getDouble(13);
			m_tot_month_11 = m_tot_month_11+rs.getDouble(14);
			m_tot_ach      = m_tot_ach +rs.getDouble(15);
			
			more = rs.next();
			}
			
			out.println("<tr  bgcolor='FFFF99' style='height:25'>");
			out.println("<td width='10%' align ='right' ><b>Total</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_0/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_1/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_2/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_3/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_4/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_5/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_6/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_7/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_8/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_9/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_10/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_11/j)+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_ach/j)+"</td>");
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
			String m_team     = req.getParameter("value");
			String m_branch     = req.getParameter("branch");
			int j=0;
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
			 String m_month_12="";
			 double  m_tot_ach=0,m_tot_month_0=0,m_tot_month_1=0,m_tot_month_2=0,m_tot_month_3=0,m_tot_month_4=0,m_tot_month_5=0,m_tot_month_6=0,m_tot_month_7=0,m_tot_month_8=0,m_tot_month_9=0,m_tot_month_10=0,m_tot_month_11=0;
					
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Target Average</TITLE>"); 
			out.println("<script>"); 
						
      out.println("</script>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='100%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u>Collection Target Average As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			
			rs=stmt.executeQuery("SELECT  TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0),'Mon-YYYY'), "+//1
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'Mon-YYYY'), "+//2
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'Mon-YYYY'), "+//3
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'Mon-YYYY'), "+//4
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'Mon-YYYY'), "+//5
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'Mon-YYYY'), "+//6
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'Mon-YYYY'), "+//7
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'Mon-YYYY'), "+//8
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'Mon-YYYY'), "+//9
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'Mon-YYYY'), "+//10
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'Mon-YYYY'), "+//11
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'Mon-YYYY') "+//12
        " FROM DUAL ");
				
				out.println("<table  width='150%'  class='table' border=1 cellspacing=0 cellpadding=0>");
			
			if(rs.next()){
			m_month_1  = rs.getString(1);
			m_month_2  = rs.getString(2);
			m_month_3  = rs.getString(3);
			m_month_4  = rs.getString(4);
			m_month_5  = rs.getString(5);
			m_month_6  = rs.getString(6);
			m_month_7  = rs.getString(7);
			m_month_8  = rs.getString(8);
			m_month_9  = rs.getString(9);
			m_month_10 = rs.getString(10);
			m_month_11 = rs.getString(11);
			m_month_12 = rs.getString(12);
			}
			
			out.println("<tr class=pdn_txtpos2  >");
			out.println("<td width='10%' align ='left' ><b>Collector</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_1+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_2+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_3+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_4+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_5+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_6+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_7+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_8+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_9+" </td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_10+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_11+"</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_12+"</td>");
			out.println("<td width='8%' align ='center'><b>Total</td>");
			out.println("</tr >"); 
			
			
			rs=stmt.executeQuery(" SELECT A.COLLECTOR_ID,  "+
			                     " NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(A.COLLECTOR_ID),'-'),  "+
													 " SUM(A.MONTH_0_ACHVEMENT), "+
													 " SUM(A.MONTH_1_ACHVEMENT), "+
													 " SUM(A.MONTH_2_ACHVEMENT), "+
													 " SUM(A.MONTH_3_ACHVEMENT), "+
													 " SUM(A.MONTH_4_ACHVEMENT), "+
													 " SUM(A.MONTH_5_ACHVEMENT), "+
													 " SUM(A.MONTH_6_ACHVEMENT), "+
													 " SUM(A.MONTH_7_ACHVEMENT), "+
													 " SUM(A.MONTH_8_ACHVEMENT), "+ 
													 " SUM(A.MONTH_9_ACHVEMENT), "+
													 " SUM(A.MONTH_10_ACHVEMENT),"+ 
													 " SUM(A.MONTH_11_ACHVEMENT), "+
													 " SUM(TOT_MONTH_ACHVEMENT) "+	
													 " FROM "+m_schema_name+".AF_RE_TBD_COL_TARGET_AVERAGE A "+
													 " WHERE A.BRANCH_CODE = '"+m_branch+"' "+	
													 " AND A.TEAM_ID = '"+m_team+"' "+
													 " AND A.ENT_USER = '"+m_username+"' "+
													 " AND  TOT_MONTH_ACHVEMENT >0 "+					
													 " GROUP BY A.COLLECTOR_ID ");
			
			boolean more = rs.next();
			while(more){
			j=j+1;
			
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:25' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:25' >");
			}
			
			out.println("<td width='10%' align ='left'>"+rs.getString(2)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(8))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(9))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(10))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(11))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(12))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(13))+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(14))+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(rs.getDouble(15))+"</td>");
			out.println("</tr>");
			
			
			m_tot_month_0  = m_tot_month_0+rs.getDouble(3);
			m_tot_month_1  = m_tot_month_1+rs.getDouble(4);
			m_tot_month_2  = m_tot_month_2+rs.getDouble(5);
			m_tot_month_3  = m_tot_month_3+rs.getDouble(6);
			m_tot_month_4  = m_tot_month_4+rs.getDouble(7);
			m_tot_month_5  = m_tot_month_5+rs.getDouble(8);
			m_tot_month_6  = m_tot_month_6+rs.getDouble(9);
			m_tot_month_7  = m_tot_month_7+rs.getDouble(10);
			m_tot_month_8  = m_tot_month_8+rs.getDouble(11);
			m_tot_month_9  = m_tot_month_9+rs.getDouble(12);
			m_tot_month_10 = m_tot_month_10+rs.getDouble(13);
			m_tot_month_11 = m_tot_month_11+rs.getDouble(14);
			m_tot_ach      = m_tot_ach +rs.getDouble(15);
			
			more = rs.next();
			}
			
			out.println("<tr  bgcolor='FFFF99' style='height:25'>");
			out.println("<td width='10%' align ='right' ><b>Total</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_0/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_1/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_2/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_3/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_4/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_5/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_6/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_7/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_8/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_9/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_10/j)+"</td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_month_11/j)+"</td>");
			out.println("<td width='8%' align ='right'>"+nf.format(m_tot_ach/j)+"</td>");
			out.println("</tr>");	
			
			out.println("</table>");	
			
			out.println("<body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
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
