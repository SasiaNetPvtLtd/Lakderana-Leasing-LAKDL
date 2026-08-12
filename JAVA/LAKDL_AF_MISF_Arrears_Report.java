

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Arrears_Report extends javax.servlet.http.HttpServlet { 

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
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_ARREARS_REPORT(:1,:2,:3);END;");
				callstmt1.setString(1,m_username);
				callstmt1.setString(2,m_date);
				callstmt1.setString(3,m_due_date);
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
			out.println("<TITLE>Arrears Report</TITLE>"); 
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
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_MISF_Arrears_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_MISF_Arrears_Report?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\" Arrears Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Arrears Report  \"+document.Form1.hid_status.value;"); 
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
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Arrears_Report?chksql=run_report&date=\"+m_date+\"&due_date=\"+document.Form1.DUE_DATE.value;"); 
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
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Arrears_Report?chksql=print_report&date=\"+m_date+\"&due_date=\"+document.Form1.DUE_DATE.value;"); 
			out.println("	 window.open(m_url,'displayWindow29','left=50,top=100,width=930,height=380,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
			out.println("	}");
			out.println("}");
     
			out.println("function set_timer_actions() {");
		  out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
				
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date()\">");  //load_lock()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_Branch' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Arrears Report</td>"); 
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


		
		  out.println("<table class='table' border='0'> ");
			out.println("<tr>");
		  out.println("<td class=div_input>As At Date*</td>");
			out.println("<td ><input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_DD maxlength=\"2\" size=\"2\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_MM  maxlength=\"2\" size=\"2\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY)>");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_EFF_VAL_YY maxlength=\"4\" size=\"4\" value=\"\" onchange=check_Date(document.Form1.TXT_EFF_VAL_DD,document.Form1.TXT_EFF_VAL_MM,document.Form1.TXT_EFF_VAL_YY) ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("</tr>"); 
			
			out.println("<tr >");
			
	    out.println("<td width='10%' >Due Date</td>"); 
			out.println("<td width='20%'>");
			out.println("<select name='DUE_DATE' class='txt_input' style='width:50px'>");
			out.println("<option value='10' >10</option>");
			out.println("<option value='20' >20</option>");
			out.println("<option value='30' >30</option>");
			out.println("</td>"); 
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
			
			String m_date   = req.getParameter("date");
			String m_due_date = req.getParameter("due_date");
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Arrears Report</TITLE>"); 
			out.println("</HEAD>"); 			 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr width='110%'>");
			out.println("<td align='center'><b><u>Arrears Report As At "+m_date+"</td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='110%'>");
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='2%' align='center' >No</td>");
			out.println("<td width='10%' align='center' >Contract Date</td>");
			out.println("<td width='10%' align='center' >Due Date</td>");
			out.println("<td width='10%' >Contract No</td>");
			out.println("<td width='10%' >Customer Name</td>");
			out.println("<td width='10%' align='right' >Rentals Due In Amount</td>");
			out.println("<td width='10%' align='right' >Rentals Due</td>");
			out.println("<td width='10%' align='right' >Recovery Percentage</td>");
			out.println("<td width='10%' align='right' >Total Outstanding</td>");
			out.println("<td width='10%' align='right'>Total Recovered</td>");
			out.println("<td width='10%' >Status</td>");
			out.println("</tr>");
			
			String m_tran_type = "";
			int k=1;
			rs2=stmt2.executeQuery( " SELECT DISTINCT A.TRAN_CODE,A.DESCRIPTION "+
												      " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE A ,"+m_schema_name+".AF_CO_TBD_ARREARS_RPT B "+
												      " WHERE  A.TRAN_CODE = B.TRAN_TYPE "+
															" AND A.ACTIVE_STATUS = 'Y' "+
															" AND B. ENT_USER='"+m_username+"'" );
				
				
				while(rs2.next()){
				
				m_tran_type = rs2.getString(1);
				
				out.println("<tr width='110%' style='height:30' ><td align=center colspan=11 width='110%'><B><font color='green'>"+rs2.getString(2)+"</font></td></tr>");
				
				
			rs1=stmt1.executeQuery( " SELECT  "+
														  " NVL(FINANCE_NO,'-'), "+ //1
														  " NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-'),  "+//2
														  " NVL(DUE_DATE,'-'), "+//3
														  " NVL(CLIENT_NAME,'-'), "+//4
														  " NVL(RENTAL_DUE_AMT,0), "+ //5
														  " NVL(RENTALS_DUE,0),  "+//6
														  " NVL(RECOVERY_PERCENTAGE,0), "+//7
														  " NVL(TOTAL_OUTSTANDING,0), "+//8
														  " NVL(TOTAL_RECOVERED,0),  "+//9
														  " NVL(STATUS,'-') "+//10
														  " FROM "+m_schema_name+".AF_CO_TBD_ARREARS_RPT "+ 
															" WHERE ENT_USER='"+m_username+"'"+
															" AND TRAN_TYPE = '"+m_tran_type+"' "+
															" ORDER BY TO_NUMBER(DUE_DATE) ");
				
			boolean more  = rs1.next();	
		 	int j=0	;
				
			while(more){			
			
			if(j%2==0){
			out.println("<tr bgcolor=\"#C0C0C0\" style='height:30' >");
			}else{
			out.println("<tr bgcolor=\"#FFFFFF\" style='height:30' >");
			}
			out.println("<td width='2%' align='center' >("+k+")</td>");
			out.println("<td width='10%' align='center' >"+rs1.getString(2)+"</td>");
			out.println("<td width='10%' align='center' >"+rs1.getString(3)+"</td>");
			out.println("<td width='10%' >"+rs1.getString(1)+"</td>");
			out.println("<td width='10%' >"+rs1.getString(4)+"</td>");
			out.println("<td width='10%' align='right' >"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("<td width='10%' align='right' >"+nf.format(rs1.getDouble(6))+"</td>");
			out.println("<td width='10%' align='right' >"+nf.format(rs1.getDouble(7))+"%</td>");
			out.println("<td width='10%' align='right' >"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			out.println("<td width='10%' >&nbsp;"+rs1.getString(10)+"</td>");
			out.println("</tr>");
			j++;
			k++;
			more  = rs1.next();	
			}
			k=1;
			out.println("<tr><td align=center colspan=11 width='110%'><hr color='black'></td></tr>");
			}
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
