// DEVELOPED BY : SANDUN FOR OFSCL MANAGEMENT INFORMATION - CREDIT   
// DATE:24-02-2009
//**********************************************************


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Exposure_Arrears_Statement extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf1;
	Statement stmt1,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10;
	
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
			
			
			stmt1 = conn.createStatement();	
			stmt2 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
			String m_client_code=req.getParameter("client_code");
			String m_date=req.getParameter("date");
			//out.println(m_schema_name);
		  if(m_chksql.equals("run_report")){ 				
			 try{	
			  callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_EXPOSURE_ARR_STMT(:1,:2);END;");
				
				callstmt1.setString(1,m_username); 
			  callstmt1.setString(2,m_date);
			  
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
			out.println("<TITLE>Exposure & Arrears Statement</TITLE>"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Exposure_Arrears_Statement?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
				
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Exposure_Arrears_Statement?chksql=main_page';"); 
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
			out.println("		help_box.innerHTML=\" Credit Process - Exposure & Arrears Statement - \"+m_val;"); 
			out.println("}"); 
			
			out.println("function load_roll_out_value(){");
			out.println("}");
			
		  out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			out.println("if(checkMonthLength(objDD,objMM,objYY)){");
			out.println("}");
			out.println("}");
			
      out.println("function run_report() {");
			out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Exposure_Arrears_Statement?chksql=run_report&date=\"+m_date+\"\";"); 
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
			out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Exposure_Arrears_Statement?chksql=print_report&date=\"+m_date+\"\";"); 
			out.println("window.open(m_url,'displayWindow3','left=75,top=130,width=1000,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=0');"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Exposure & Arrears Statement</td>"); 
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
			out.println("<td width='10%'ID=VDATE>As At Date*</td>");
			out.println("<td width='25%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("<input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>");
			out.println("<input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");
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
			
			}else	
			if(m_chksql.equals("print_report")){		
			
			out.println("<HTML><HEAD><TITLE>Exposure & Arrears Statement</TITLE></HEAD>");
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("   function load_contract_exe_detail(val){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Exposure_Arrears_Statement?chksql=show_contract_break_up&date="+m_date+"&val=\"+val+\" \";");
	    //out.println("alert(m_url)");
			out.println("   window.open(m_url,\"popupwin1\",\"status=0,menubar=0,left=50,top=100,scrollbars=1,height=650,width=900,resizable=0\");");
      out.println("   }");
			
			out.println("   function load_contract_age_detail(val,age_upper,age_lower){");
	    out.println("   m_url=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MISF_Exposure_Arrears_Statement?chksql=show_contract_break_up_by_age&val=\"+val+\"&age_upper=\"+age_upper+\"&age_lower=\"+age_lower+\" \";");
	    out.println("   window.open(m_url,\"popupwin1\",\"status=0,menubar=0,left=50,top=100,scrollbars=1,height=650,width=900,resizable=0\");");
      out.println("   }");
			
      out.println("</SCRIPT>");
			
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 							

			out.println("<TABLE  WIDTH='100%' >");
			out.println("<TR><TD align='Center' ><B>Exposure & Arrears Statement As At "+m_date+"</B></TD></TR>");
			out.println("</TABLE>");
			/*
			out.println("<br>");
			out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='0' bordercolor=black>");
			out.println("<tr  bgcolor=\"#87CEFA\">");
			out.println("<td width='10%' align=left><b>Exposure Range</td>");
			out.println("<td width='15%' align=right><b>Total Exposure</b></td>");
			out.println("<td width='10%' align=right><b>No of Contracts</b></td>");
			out.println("<td width='5%' align=right><b>Age 0 or Less</b></td>");
			out.println("<td width='5%' align=right><b>Age 1</b></td>");
			out.println("<td width='5%' align=right><b>Age 2</b></td>");
			out.println("<td width='5%' align=right><b>Age 3</b></td>");
			out.println("<td width='5%' align=right><b>Age 4</b></td>");
			out.println("<td width='5%' align=right><b>Age 5</b></td>");
			out.println("<td width='5%' align=right><b>Age 6</b></td>");
			out.println("<td width='5%' align=right><b>Age 7-9</b></td>");
			out.println("<td width='5%' align=right><b>Age 10-13</b></td>");
			out.println("<td width='5%' align=right><b>Age 14-18</b></td>");
			out.println("<td width='5%' align=right><b>Age 18> </b></td>");
			out.println("<td width='15%' align=right><b>Total Arrears</b></td><tr>");
			
				
				double m_exp_val_20MN     = 0.0;				
				int fin_count_20MN        = 0;
				double fin_arr_20MN       = 0.0;
				int m_exp_age_20MN_0       = 0;
				int m_exp_age_20MN_1       = 0;
			  int m_exp_age_20MN_2       = 0;
			  int m_exp_age_20MN_3       = 0;
			  int m_exp_age_20MN_4       = 0;
			  int m_exp_age_20MN_5       = 0;
			  int m_exp_age_20MN_6       = 0;
				int m_exp_age_20MN_7_9    = 0;
			  int m_exp_age_20MN_10_13  = 0;
			  int m_exp_age_20MN_14_17  = 0;
				int m_exp_age_20MN_18       = 0;
				double m_exp_val_10MN     = 0.0;				
				int fin_count_10MN        = 0;
				double fin_arr_10MN       = 0.0;
				int m_exp_age_10MN_0       = 0;
				int m_exp_age_10MN_1       = 0;
			  int m_exp_age_10MN_2       = 0;
			  int m_exp_age_10MN_3       = 0;
			  int m_exp_age_10MN_4       = 0;
			  int m_exp_age_10MN_5       = 0;
			  int m_exp_age_10MN_6       = 0;
				int m_exp_age_10MN_7_9   = 0;
			  int m_exp_age_10MN_10_13 = 0;
			  int m_exp_age_10MN_14_17 = 0;
				int m_exp_age_10MN_18       = 0;
				double m_exp_val_5MN      = 0.0;				
				int fin_count_5MN         = 0;
				double fin_arr_5MN        = 0.0;
				int m_exp_age_5MN_0       = 0;
				int m_exp_age_5MN_1       = 0;
			  int m_exp_age_5MN_2       = 0;
			  int m_exp_age_5MN_3       = 0;
			  int m_exp_age_5MN_4       = 0;
			  int m_exp_age_5MN_5       = 0;
			  int m_exp_age_5MN_6       = 0;
				int m_exp_age_5MN_7_9    = 0;
			  int m_exp_age_5MN_10_13  = 0;
			  int m_exp_age_5MN_14_17  = 0;
				int m_exp_age_5MN_18       = 0;
				double m_exp_val_1MN      = 0.0;				
				int fin_count_1MN         = 0;
				double fin_arr_1MN        = 0.0;
				int m_exp_age_1MN_0       = 0;
				int m_exp_age_1MN_1       = 0;
			  int m_exp_age_1MN_2       = 0;
			  int m_exp_age_1MN_3       = 0;
			  int m_exp_age_1MN_4       = 0;
			  int m_exp_age_1MN_5       = 0;
			  int m_exp_age_1MN_6       = 0;
				int m_exp_age_1MN_7_9    = 0;
			  int m_exp_age_1MN_10_13  = 0;
			  int m_exp_age_1MN_14_17  = 0;
				int m_exp_age_1MN_18       = 0;
				double m_exp_val_0MN      = 0.0;				
				int fin_count_0MN         = 0;
				double fin_arr_0MN        = 0.0;
				int m_exp_age_0MN_0       = 0;
				int m_exp_age_0MN_1       = 0;
			  int m_exp_age_0MN_2       = 0;
			  int m_exp_age_0MN_3       = 0;
			  int m_exp_age_0MN_4       = 0;
			  int m_exp_age_0MN_5       = 0;
			  int m_exp_age_0MN_6       = 0;
				int m_exp_age_0MN_7_9    = 0;
			  int m_exp_age_0MN_10_13  = 0;
			  int m_exp_age_0MN_14_17  = 0;
				int m_exp_age_0MN_18       = 0;
				
				rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS), "+
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','','0'),   "+//4
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','1',''),   "+//5
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','2',''),   "+//6
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','3',''),   "+//7
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','4',''),   "+//8
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','5',''),   "+//9
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','6',''),   "+//10
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','9','7'),  "+//11
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','13','10'),"+//12
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','18','14'),"+//13
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','','','18') "+//14
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI > 20000000 ");
			
			
			if(rs1.next()){
			m_exp_val_20MN = rs1.getDouble(1);
			fin_count_20MN = rs1.getInt(2);
			fin_arr_20MN   = rs1.getDouble(3);
			m_exp_age_20MN_0      = rs1.getInt(4);
			m_exp_age_20MN_1      = rs1.getInt(5);
			m_exp_age_20MN_2      = rs1.getInt(6);
			m_exp_age_20MN_3      = rs1.getInt(7);
			m_exp_age_20MN_4      = rs1.getInt(8);
			m_exp_age_20MN_5      = rs1.getInt(9);
			m_exp_age_20MN_6      = rs1.getInt(10);
			m_exp_age_20MN_7_9    = rs1.getInt(11);
			m_exp_age_20MN_10_13  = rs1.getInt(12);
			m_exp_age_20MN_14_17  = rs1.getInt(13);
			m_exp_age_20MN_18     = rs1.getInt(14);
			}	
			
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left >> 20 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(m_exp_val_20MN)+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"1\")'><b>"+fin_count_20MN+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"0\")'><b>"+m_exp_age_20MN_0+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"1\")'><b>"+m_exp_age_20MN_1+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"2\")'><b>"+m_exp_age_20MN_2+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"3\")'><b>"+m_exp_age_20MN_3+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"4\")'><b>"+m_exp_age_20MN_4+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"5\")'><b>"+m_exp_age_20MN_5+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"\",\"6\")'><b>"+m_exp_age_20MN_6+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"9\",\"7\")'><b>"+m_exp_age_20MN_7_9+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"13\",\"10\")'><b>"+m_exp_age_20MN_10_13+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"17\",\"14\")'><b>"+m_exp_age_20MN_14_17+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"1\",\"18\",\"\")'><b>"+m_exp_age_20MN_18+"</td>");
			out.println("<td width='15%'  align=right>"+nf1.format(fin_arr_20MN)+"</b></td>");
			out.println("</tr>"); 
			
			stmt1.close(); 
      stmt1 = conn.createStatement();
			
			rs3= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS),     "+
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','','0'),   "+//4
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','1',''),   "+//5
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','2',''),   "+//6
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','3',''),   "+//7
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','4',''),   "+//8
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','5',''),   "+//9
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','6',''),   "+//10
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','9','7'),  "+//11
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','13','10'),"+//12
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','18','14'),"+//13
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('20000000','10000000','','18')   "+//14
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 20000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  10000000 ");
			
			
			if(rs3.next()){
			m_exp_val_10MN       = rs3.getDouble(1);
			fin_count_10MN       = rs3.getInt(2);
			fin_arr_10MN         = rs3.getDouble(3);
			m_exp_age_10MN_0     = rs3.getInt(4);
			m_exp_age_10MN_1     = rs3.getInt(5);
			m_exp_age_10MN_2     = rs3.getInt(6);
			m_exp_age_10MN_3     = rs3.getInt(7);
			m_exp_age_10MN_4     = rs3.getInt(8);
			m_exp_age_10MN_5     = rs3.getInt(9);
			m_exp_age_10MN_6     = rs3.getInt(10);
			m_exp_age_10MN_7_9   = rs3.getInt(11);
			m_exp_age_10MN_10_13 = rs3.getInt(12);
			m_exp_age_10MN_14_17 = rs3.getInt(13);
			m_exp_age_10MN_18     = rs3.getInt(14);
			
			}	
			
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left > 10 MN to 20 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(m_exp_val_10MN)+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"2\")'><b>"+fin_count_10MN+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"0\")'><b>"+m_exp_age_10MN_0+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"1\")'><b>"+m_exp_age_10MN_1+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"2\")'><b>"+m_exp_age_10MN_2+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"3\")'><b>"+m_exp_age_10MN_3+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"4\")'><b>"+m_exp_age_10MN_4+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"5\")'><b>"+m_exp_age_10MN_5+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"\",\"6\")'><b>"+m_exp_age_10MN_6+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"9\",\"7\")'><b>"+m_exp_age_10MN_7_9+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"13\",\"10\")'><b>"+m_exp_age_10MN_10_13+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"17\",\"14\")'><b>"+m_exp_age_10MN_14_17+"</td>");
			out.println("<td width='5%'   align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_age_detail(\"2\",\"18\",\"\")'><b>"+m_exp_age_10MN_18+"</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(fin_arr_10MN)+"</b></td>");
			out.println("</tr>"); 
			
			stmt1.close(); 
      stmt1 = conn.createStatement();
			
			rs5= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS),  "+
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','','0'),  "+//4
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','1',''),  "+//5
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','2',''),  "+//6
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','3',''),  "+//7
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','4',''),  "+//8
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','5',''),  "+//9
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','6',''),  "+//10
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','9','7'),  "+//11
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','13','10'),"+//12
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','18','14'), "+//13
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('10000000','5000000','','18')  "+//14
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 10000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  5000000 ");
			
			
			if(rs5.next()){
			m_exp_val_5MN = rs5.getDouble(1);
			fin_count_5MN = rs5.getInt(2);
			fin_arr_5MN   = rs5.getDouble(3);
			m_exp_age_5MN_0     = rs5.getInt(4);
			m_exp_age_5MN_1     = rs5.getInt(5);
			m_exp_age_5MN_2     = rs5.getInt(6);
			m_exp_age_5MN_3     = rs5.getInt(7);
			m_exp_age_5MN_4     = rs5.getInt(8);
			m_exp_age_5MN_5     = rs5.getInt(9);
			m_exp_age_5MN_6     = rs5.getInt(10);
			m_exp_age_5MN_7_9   = rs5.getInt(11);
			m_exp_age_5MN_10_13 = rs5.getInt(12);
			m_exp_age_5MN_14_17 = rs5.getInt(13);
			m_exp_age_5MN_18     = rs5.getInt(14);
			}			
			
			out.println("<tr style='height:25' >");    
			out.println("<td width='10%'  align=left >5MN to 10MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(m_exp_val_5MN)+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"3\")'><b>"+fin_count_5MN+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_0+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_1+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_2+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_3+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_4+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_5+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_6+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_7_9+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_10_13+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_14_17+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_5MN_18+"</td>");
			out.println("<td width='15%' align=right>"+nf1.format(fin_arr_5MN)+"</b></td>");
			out.println("</tr>"); 
			
			stmt1.close(); 
      stmt1 = conn.createStatement();
			
			rs5= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS),   "+
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','','0'),   "+//4
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','1',''),   "+//5
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','2',''),   "+//6
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','3',''),   "+//7
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','4',''),   "+//8
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','5',''),   "+//9
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','6',''),   "+//10
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','9','7'),  "+//11
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','13','10'),"+//12
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','18','14'),"+//13
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('5000000','1000000','','18')   "+//14
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 5000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  1000000 ");
			
			
			if(rs5.next()){
			m_exp_val_1MN        = rs5.getDouble(1);
			fin_count_1MN        = rs5.getInt(2);
			fin_arr_1MN          = rs5.getDouble(3);
			m_exp_age_1MN_0      = rs5.getInt(4);
			m_exp_age_1MN_1      = rs5.getInt(5);
			m_exp_age_1MN_2      = rs5.getInt(6);
			m_exp_age_1MN_3      = rs5.getInt(7);
			m_exp_age_1MN_4      = rs5.getInt(8);
			m_exp_age_1MN_5      = rs5.getInt(9);
			m_exp_age_1MN_6      = rs5.getInt(10);
			m_exp_age_1MN_7_9    = rs5.getInt(11);
			m_exp_age_1MN_10_13  = rs5.getInt(12);
			m_exp_age_1MN_14_17  = rs5.getInt(13);
			m_exp_age_1MN_18     = rs5.getInt(14);
			}				
			
			out.println("<tr style='height:25' >");    
			out.println("<td width='10%'  align=left >1 MN to 5 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(m_exp_val_1MN)+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"4\")'><b>"+fin_count_1MN+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_0+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_1+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_2+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_3+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_4+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_5+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_6+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_7_9+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_10_13+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_14_17+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_1MN_18+"</td>");
			out.println("<td width='15%' align=right>"+nf1.format(fin_arr_1MN)+"</b></td>");
			out.println("</tr>"); 
			
			stmt1.close(); 
      stmt1 = conn.createStatement();
			
			rs7= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS),  "+
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','','0'),   "+//4
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','1',''),   "+//5
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','2',''),   "+//6
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','3',''),   "+//7
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','4',''),   "+//8
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','5',''),   "+//9
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','6',''),   "+//10
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','9','7'),  "+//11
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','13','10'),"+//12
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','18','14'),"+//13
																" "+m_schema_name+".AF_CO_GET_EXPOURSE_AGE('','1000000','','18')   "+//14
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 1000000 ");
																
			
			
			if(rs7.next()){
			m_exp_val_0MN = rs7.getDouble(1);
			fin_count_0MN = rs7.getInt(2);
			fin_arr_0MN   = rs7.getDouble(3);
			m_exp_age_0MN_0      = rs7.getInt(4);
			m_exp_age_0MN_1      = rs7.getInt(5);
			m_exp_age_0MN_2      = rs7.getInt(6);
			m_exp_age_0MN_3      = rs7.getInt(7);
			m_exp_age_0MN_4      = rs7.getInt(8);
			m_exp_age_0MN_5      = rs7.getInt(9);
			m_exp_age_0MN_6      = rs7.getInt(10);
			m_exp_age_0MN_7_9    = rs7.getInt(11);
			m_exp_age_0MN_10_13  = rs7.getInt(12);
			m_exp_age_0MN_14_17  = rs7.getInt(13);
			m_exp_age_0MN_18     = rs7.getInt(14);
			}	
			
			
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >< 1 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(m_exp_val_0MN)+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"5\")'><b>"+fin_count_0MN+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_0+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_1+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_2+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_3+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_4+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_5+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_6+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_7_9+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_10_13+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_14_17+"</td>");
			out.println("<td width='5%' align=right><b>"+m_exp_age_0MN_18+"</td>");
			out.println("<td width='15%' align=right>"+nf1.format(fin_arr_0MN)+"</b></td>");
			out.println("</tr>"); 			
			
			
			out.println("</table>");
			out.println("</form>"); 
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
			*/
			
			out.println("<br>");
			out.println("<table width='50%' class='table' border='1'  align='center' cellspacing='0' cellspacing='0' bordercolor=black>");
			out.println("<tr  bgcolor=\"#87CEFA\">");
			out.println("<td width='10%' align=left><b>Exposure Range</td>");
			out.println("<td width='15%' align=right><b>Total Exposure</b></td>");
			out.println("<td width='10%' align=right><b>No of Contracts</b></td>");
			//out.println("<td width='15%' align=right><b>Total Arrears</b></td><tr>");
			
			    rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS) "+
															  " FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI > 20000000 "+
																" AND A.ENT_USER='"+m_username+"' "+
																" AND NVL(TERMI_STATUS,0) = 1 ");
																//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																//"      OR A.TERMI_DATE IS NULL ) ");
				if(rs1.next()){	
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >> 20 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(rs1.getDouble(1))+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"1\")'><b>"+rs1.getInt(2)+"</td>");
			//out.println("<td width='15%' align=right>"+nf1.format(rs1.getDouble(3))+"</b></td>");
			out.println("</tr>"); 		
					}
					
			     rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS) "+
															  " FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 20000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  10000000 "+
																" AND A.ENT_USER='"+m_username+"' "+
																//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																//"      OR A.TERMI_DATE IS NULL )");
																" AND NVL(TERMI_STATUS,0) = 1 ");
			if(rs1.next()){	
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >10 MN to 20 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(rs1.getDouble(1))+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"2\")'><b>"+rs1.getInt(2)+"</td>");
			//out.println("<td width='15%' align=right>"+nf1.format(rs1.getDouble(3))+"</b></td>");
			out.println("</tr>"); 													
				}
				   rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS) "+
															  " FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 10000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  5000000 "+
																" AND A.ENT_USER='"+m_username+"' "+
																//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																//"      OR A.TERMI_DATE IS NULL )");
																" AND NVL(TERMI_STATUS,0) = 1 ");
				
				if(rs1.next()){	
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >5MN to 10MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(rs1.getDouble(1))+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"3\")'><b>"+rs1.getInt(2)+"</td>");
			//out.println("<td width='15%' align=right>"+nf1.format(rs1.getDouble(3))+"</b></td>");
			out.println("</tr>"); 		
					}
					  			
						 rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS) "+
															  " FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 5000000 "+
																" AND   A.ARREARS+A.NIL+A.ODI >  1000000 "+
																" AND A.ENT_USER='"+m_username+"' "+
																//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																//"      OR A.TERMI_DATE IS NULL )");
																" AND NVL(TERMI_STATUS,0) = 1 ");
						if(rs1.next()){											
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >1 MN to 5 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(rs1.getDouble(1))+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"4\")'><b>"+rs1.getInt(2)+"</td>");
			//out.println("<td width='15%' align=right>"+nf1.format(rs1.getDouble(3))+"</b></td>");
			out.println("</tr>"); 		
			}
			       rs1= stmt1.executeQuery(" SELECT "+
																" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0),COUNT(A.FINANCE_NO),SUM(A.ARREARS)  "+
																" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A "+
																" WHERE A.ARREARS+A.NIL+A.ODI <= 1000000 "+
																" AND A.ENT_USER='"+m_username+"' "+
																//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																//"      OR A.TERMI_DATE IS NULL )");
																" AND NVL(TERMI_STATUS,0) = 1 ");
			
			if(rs1.next()){	
			out.println("<tr style='height:25'>");    
			out.println("<td width='10%'  align=left  >< 1 MN</td>");
			out.println("<td width='15%'  align=right >"+nf1.format(rs1.getDouble(1))+"</td>");
			out.println("<td width='10%'  align=right STYLE='cursor:hand' title = 'Break Up Details' onclick='load_contract_exe_detail(\"5\")'><b>"+rs1.getInt(2)+"</td>");
			//out.println("<td width='15%' align=right>"+nf1.format(rs1.getDouble(3))+"</b></td>");
			out.println("</tr>"); 			
			}
			
			out.println("</table>");
			out.println("</form>"); 
		  out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</BODY></HTML>");
			
			
			
			
			}
			
			else	
			if(m_chksql.equals("show_contract_break_up")){	
					String value   = req.getParameter("val");
					String m_range = "";
					m_date = req.getParameter("date");
					
					if(value.equals("1")){
				rs1= stmt1.executeQuery(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE),TERMI_DATE "+//,AGE "+
					//out.println(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE) "+//,AGE "+
					                        " FROM ( "+
					                        " SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
																	" B.CLIENT_CODE CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE, "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE "+
																	//" NVL(AGE,0) AGE "+
																	" ,NVL(TO_CHAR(A.TERMI_DATE,'DD-MM-YYYY'),'-') TERMI_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.ARREARS+A.NIL+A.ODI > 20000000  "+
																	" AND  A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ENT_USER='"+m_username+"' "+
																  //" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																  //"      OR A.TERMI_DATE IS NULL ) "+
																	" AND NVL(TERMI_STATUS,0) = 1 "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE,TERMI_DATE "+
																	" ) "+
																	" GROUP BY FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,TERMI_DATE "+
																	" ORDER BY ACTIVATED_DATE DESC");
			
					m_range =" >20MN ";
					}
					else if(value.equals("2")){
					
																				
        rs1= stmt1.executeQuery(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE),TERMI_DATE "+//,AGE "+
				//out.println(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE) "+//,AGE "+
					                        " FROM ( "+
					                        " SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
																	" B.CLIENT_CODE CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE, "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE "+
																	//" NVL(AGE,0) AGE "+
																	" ,NVL(TO_CHAR(A.TERMI_DATE,'DD-MM-YYYY'),'-') TERMI_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.ARREARS+A.NIL+A.ODI <= 20000000 "+
																  " AND   A.ARREARS+A.NIL+A.ODI >  10000000  "+
																	" AND  A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ENT_USER='"+m_username+"' "+
																	//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																  // "      OR A.TERMI_DATE IS NULL ) "+
																	" AND NVL(TERMI_STATUS,0) = 1 "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE,TERMI_DATE "+
																	" ) "+
																	" GROUP BY FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,TERMI_DATE "+
																	" ORDER BY ACTIVATED_DATE DESC");
			
			
					m_range =" 10MN to 20MN ";
					
					}
					else if(value.equals("3")){
					
					rs1= stmt1.executeQuery(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE),TERMI_DATE "+//,AGE "+
					                        " FROM ( "+
					                        " SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
																	" B.CLIENT_CODE CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE, "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE "+
																	//" NVL(AGE,0) AGE "+
																	" ,NVL(TO_CHAR(A.TERMI_DATE,'DD-MM-YYYY'),'-') TERMI_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.ARREARS+A.NIL+A.ODI <= 10000000 "+
														    	" AND   A.ARREARS+A.NIL+A.ODI >  5000000 "+
																	" AND  A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ENT_USER='"+m_username+"' "+
																	//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																  //"      OR A.TERMI_DATE IS NULL ) "+
																	" AND NVL(TERMI_STATUS,0) = 1 "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE,TERMI_DATE "+
																	" ) "+
																	" GROUP BY FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,TERMI_DATE "+
																	" ORDER BY ACTIVATED_DATE DESC");
																	
							
					m_range =" 5MN to 10MN ";
					
					}
					else if(value.equals("4")){
					
					rs1= stmt1.executeQuery(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE),TERMI_DATE "+//,AGE "+
					                        " FROM ( "+
					                        " SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
																	" B.CLIENT_CODE CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE, "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE "+
																	//" NVL(AGE,0) AGE "+
																	" ,NVL(TO_CHAR(A.TERMI_DATE,'DD-MM-YYYY'),'-') TERMI_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.ARREARS+A.NIL+A.ODI <= 5000000 "+
														    	" AND   A.ARREARS+A.NIL+A.ODI >  1000000 "+
																	" AND  A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ENT_USER='"+m_username+"' "+
																	//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																  //"      OR A.TERMI_DATE IS NULL ) "+
																	" AND NVL(TERMI_STATUS,0) = 1 "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE ,TERMI_DATE "+
																	" ) "+
																	" GROUP BY FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE ,TERMI_DATE "+
																	" ORDER BY ACTIVATED_DATE DESC");
																	
									
					m_range =" 1MN to 5MN ";
					
					}
					else if(value.equals("5")){
					
					rs1= stmt1.executeQuery(" SELECT FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE,SUM(EXEPOURSE),TERMI_DATE "+//,AGE "+
					                        " FROM ( "+
					                        " SELECT DISTINCT A.FINANCE_NO FINANCE_NO, "+
																	" B.CLIENT_CODE CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY') ACTIVATED_DATE, "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE "+
																	//" NVL(AGE,0) AGE "+
																	" ,NVL(TO_CHAR(A.TERMI_DATE,'DD-MM-YYYY'),'-') TERMI_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.ARREARS+A.NIL+A.ODI <= 1000000 "+
																	" AND  A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ENT_USER='"+m_username+"' "+
																	//" AND (A.TERMI_DATE > TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																  //"      OR A.TERMI_DATE IS NULL ) "+
																	" AND NVL(TERMI_STATUS,0) = 1 "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE ,TERMI_DATE "+
																	" ) "+
																	" GROUP BY FINANCE_NO,CLIENT_CODE,CLIENT_NAME,ACTIVATED_DATE ,TERMI_DATE  "+
																	" ORDER BY ACTIVATED_DATE DESC");
					
									
			
					m_range =" < 1MN ";
					
					}
					
					
					int j = 0;
					double m_tot_exe=0.0;
			    out.println("<HTML><HEAD><TITLE>Exposure & Arrears Statement</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			    out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			    out.println("<FORM NAME='Form1' method='post'>"); 							

			    out.println("<TABLE  WIDTH='100%' class='table'>");
			    out.println("<TR><TD align='Center' class=table><b>Break Up Detail For ("+m_range+") Range</B></TD></TR>");
			    out.println("</TABLE>");
			
			    out.println("<br>");
         
																	
				  boolean more1=rs1.next();
					if(!more1){
					out.println("<TABLE  WIDTH='100%' class='table'>");
			    out.println("<TR><TD align='Center' class=table><B>No Data Found..!</B></TD></TR>");
			    out.println("</TABLE>");
					}
					else{
					out.println("<TABLE  WIDTH='100%' class='table' align='center' border=1 cellpadding=0 cellspacing=0 bordercolor='black'>");
			    out.println("<TR bgcolor=\"#87CEFA\"><TD WIDTH='5%' style='text-align:center'><b>No</TD>");
					out.println("<TD WIDTH='10%' ><b>&nbsp;Finance No</TD>");
					out.println("<TD WIDTH='20%' ><b>&nbsp;Client Code</TD>");
					out.println("<TD WIDTH='30%' ><b>&nbsp;Client Name</TD>");
					out.println("<TD WIDTH='10%' ><b>&nbsp;Activated Date</TD>");
					out.println("<TD WIDTH='10%' ><b>&nbsp;Termination Date</TD>");
					//out.println("<TD WIDTH='5%'  style='text-align:right'><b>Age</TD>");
					out.println("<TD WIDTH='20%' style='text-align:right'><b>Total Exposure</TD>");
					out.println("</TR>");
					
					   while(more1){
						j++;	
					out.println("<TR style='height=30'><TD WIDTH='5%' style='text-align:center'>"+j+"</TD>");
					out.println("<TD WIDTH='10%' >&nbsp;"+rs1.getString(1)+"</TD>");
					out.println("<TD WIDTH='20%' >&nbsp;"+rs1.getString(2)+"</TD>");
					out.println("<TD WIDTH='30%' >&nbsp;"+rs1.getString(3)+"</TD>");
					out.println("<TD WIDTH='10%' >&nbsp;"+rs1.getString(4)+"</TD>");
					out.println("<TD WIDTH='10%' >&nbsp;"+rs1.getString(6)+"</TD>");
					//out.println("<TD WIDTH='5%'  style='text-align:right'>"+rs1.getInt(6)+"</TD>");
					out.println("<TD WIDTH='20%' style='text-align:right'>"+nf1.format(rs1.getDouble(5))+"</TD>");
					out.println("</TR>");
					m_tot_exe = m_tot_exe+rs1.getDouble(5);
					
					more1=rs1.next();
					   }
					out.println("<TR>");		
					out.println("<TD WIDTH='80%' colspan=5 align=right><b>Total</TD>");
					out.println("<TD WIDTH='20%' align=right ><b>"+nf1.format(m_tot_exe)+"</TD>");
					out.println("</TR>");	
			    out.println("</TABLE>");
					}
					
			 
				
				
		  
				
				
				
				
				
			}
			if(m_chksql.equals("show_contract_break_up_by_age")){	
			    
					String value =req.getParameter("val");
					String m_age_upper =req.getParameter("age_upper");
					String m_age_lower =req.getParameter("age_lower");
					String m_range = "";
					String m_age = "";
					String m_sql_age = "";
					
					 if(m_age_upper.equals("")){
					    m_sql_age = " < "+m_age_lower;
							if(m_age_lower.equals("0")){
							   m_age = "Age "+m_age_lower+ " or Less";
							   }
							else{
							   m_age = "Age "+m_age_lower;
							  }
					}else if(m_age_lower.equals("")){
					    m_sql_age = " > "+m_age_upper;
							m_age = "Age "+m_age_upper+" or Upper";
					}else{
					    m_sql_age = " > "+m_age_lower+" AND AGE < "+m_age_upper;
							m_age = "Age "+m_age_lower+" - "+m_age_upper;
					}
					
					
					if(value.equals("1")){
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+
					  											" B.CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE, "+
																	" NVL(AGE,0), B.ACTIVATED_DATE"+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ARREARS+A.NIL+A.ODI > 20000000  "+
																	" AND AGE "+m_sql_age+" "+																	
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE "+
																	" ORDER BY B.ACTIVATED_DATE DESC");
			
					m_range =" >20MN ";
					}
					else if(value.equals("2")){
					
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+
																	" B.CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE, "+
																	" NVL(AGE,0),B.ACTIVATED_DATE "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.FINANCE_NO= B.FINANCE_NO "+ 
																	" AND A.ARREARS+A.NIL+A.ODI <= 20000000 "+
																  " AND   A.ARREARS+A.NIL+A.ODI >  10000000  "+
																	" AND AGE "+m_sql_age+" "+																	
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE "+
																	" ORDER BY B.ACTIVATED_DATE DESC");
			
					m_range =" 10MN to 20MN ";
					
					}
					else if(value.equals("3")){
					
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+
																	" B.CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE, "+
																	" NVL(AGE,0),B.ACTIVATED_DATE "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.FINANCE_NO= B.FINANCE_NO "+
																	" AND   A.ARREARS+A.NIL+A.ODI <= 10000000 "+
														    	" AND   A.ARREARS+A.NIL+A.ODI >  5000000 "+
																	" AND   AGE "+m_sql_age+" "+																	
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE "+
																	" ORDER BY B.ACTIVATED_DATE DESC");
			
					m_range =" 5MN to 10MN ";
					
					}
					else if(value.equals("4")){
					
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+
																	" B.CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE, "+
																	" NVL(AGE,0) ,B.ACTIVATED_DATE "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE  A.FINANCE_NO= B.FINANCE_NO  "+
																	" AND   A.ARREARS+A.NIL+A.ODI <= 5000000 "+
														    	" AND   A.ARREARS+A.NIL+A.ODI >  1000000 "+
																	" AND AGE "+m_sql_age+" "+
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE "+
																	" ORDER BY B.ACTIVATED_DATE DESC");
					m_range =" 1MN to 5MN ";
					
					}
					else if(value.equals("5")){
					
					rs1= stmt1.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+
																	" B.CLIENT_CODE, "+
																	" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+
																	" TO_CHAR(B.ACTIVATED_DATE,'DD-MM-YYYY'), "+
																	" NVL(SUM(A.ARREARS+A.NIL+A.ODI),0) EXEPOURSE, "+
																	" NVL(AGE,0),B.ACTIVATED_DATE  "+
																	" FROM "+m_schema_name+".AF_CO_TBD_EXPOSURE_ARR_STMT A , "+
																	"      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	" WHERE A.FINANCE_NO= B.FINANCE_NO "+
																	" AND A.ARREARS+A.NIL+A.ODI <= 1000000 "+
																	" AND AGE "+m_sql_age+" "+														    	
																	" GROUP BY A.FINANCE_NO,B.CLIENT_CODE,B.ACTIVATED_DATE,AGE "+
																	" ORDER BY B.ACTIVATED_DATE DESC");
			
					m_range =" < 1MN ";
					
					}
					
					
					int j = 0;
					double m_tot_exe=0.0;
			    out.println("<HTML><HEAD><TITLE>Exposure & Arrears Statement</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			    out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			    out.println("<FORM NAME='Form1' method='post'>"); 							

			    out.println("<TABLE  WIDTH='100%' class='table'>");
			    out.println("<TR><TD align='Center' class=table><b>Break Up Detail For ("+m_range+") Range - "+m_age+"</B></TD></TR>");
			    out.println("</TABLE>");
			
			    out.println("<br>");
         
																	
				  boolean more1=rs1.next();
					if(!more1){
					out.println("<br>");
					out.println("<TABLE  WIDTH='100%' class='table'>");
			    out.println("<TR><TD align='Center' class=table><font color='red' >No Data Found..!</TD></TR>");
			    out.println("</TABLE>");
					}
					else{
					out.println("<TABLE  WIDTH='100%' class='table' align='center' border=1 cellpadding=0 cellspacing=0 bordercolor='black'>");
			    out.println("<TR bgcolor=\"#87CEFA\"><TD WIDTH='5%' style='text-align:center'><b>No</TD>");
					out.println("<TD WIDTH='10%' ><b>&nbsp;Finance No</TD>");
					out.println("<TD WIDTH='20%' ><b>&nbsp;Client Code</TD>");
					out.println("<TD WIDTH='30%' ><b>&nbsp;Client Name</TD>");
					out.println("<TD WIDTH='10%' ><b>&nbsp;Activated Date</TD>");
					out.println("<TD WIDTH='5%'  style='text-align:right'><b>Age</TD>");
					out.println("<TD WIDTH='20%' style='text-align:right'><b>Total Exposure</TD>");
					out.println("</TR>");
					
					   while(more1){
						j++;	
					out.println("<TR style='height=30'><TD WIDTH='5%' style='text-align:center'>"+j+"</TD>");
					out.println("<TD WIDTH='10%' >&nbsp;"+rs1.getString(1)+"</TD>");
					out.println("<TD WIDTH='20%' >&nbsp;"+rs1.getString(2)+"</TD>");
					out.println("<TD WIDTH='30%' >&nbsp;"+rs1.getString(3)+"</TD>");
					out.println("<TD WIDTH='10%' >&nbsp;"+rs1.getString(4)+"</TD>");
					out.println("<TD WIDTH='5%'  style='text-align:right'>"+rs1.getInt(6)+"</TD>");
					out.println("<TD WIDTH='20%' style='text-align:right'>"+nf1.format(rs1.getDouble(5))+"</TD>");
					out.println("</TR>");
					m_tot_exe = m_tot_exe+rs1.getDouble(5);
					
					more1=rs1.next();
					   }
					out.println("<TR>");		
					out.println("<TD WIDTH='80%' colspan=6 align=right><b>Total</TD>");
					out.println("<TD WIDTH='20%' align=right ><b>"+nf1.format(m_tot_exe)+"</TD>");
					out.println("</TR>");	
			    out.println("</TABLE>");
					}
					
			
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
