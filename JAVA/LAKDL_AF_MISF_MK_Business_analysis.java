
//SCREEN NAME: BUSSINESS VOLUME ANALYSIS REPORT
//CREATED BY : SANDUN
//DATE/TIME  : 13/11/2008
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_MISF_MK_Business_analysis extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt2,stmt3,stmt4;
		public ResultSet rs,rs1,rs2,rs3,rs4;		
		java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username=con_method.username;		
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_chksql= req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_BUSSINESS_ANALYSIS(:1,:2);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_username);
				callstmt1.execute();
				
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}			
			
			else if(m_chksql.trim().equals("main_page")){	
			
			String m_date_dd = "";
			String m_date_mm = "";
			String m_date_yy = "";
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");	
			out.println("var timerID;");
			out.println("var durationID=0;");
				
			out.println("function set_timer_actions() {");
			out.println("   durationID=durationID+1;");
			out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
			out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
			out.println("}");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Bussiness Volume Analysis Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Bussiness Volume Analysis Report  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			/*
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
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
			*/
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_MK_Business_analysis?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_MK_Business_analysis?chksql=main_page';"); 
			out.println("}"); 			
			
			out.println("function view_report(){ ");
			out.println(" if(check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){ ");	
			out.println("		clearTimeout(timerID);");
			out.println("		m_table.innerHTML=\"\";");
			out.println("from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_MK_Business_analysis?chksql=BUSINESS_ANALYSIS&from_date=\"+from_date+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
			out.println(" } ");	
			
			out.println("function load_screen_status(m_val){"); 			
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 			
			out.println("}"); 			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY1.value=v_date;");
			out.println("     document.Form1.VAL_MONTH1.value=v_month;");
			out.println("     document.Form1.VAL_YEAR1.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_from_date.value=date1");			
			out.println("}");			
			out.println("}");

		  out.println("function get_sys_date(){");
			rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			if(rs.next()){
			m_date_dd = rs.getString(1).substring(0,2);
			m_date_mm = rs.getString(1).substring(3,5);
			m_date_yy = rs.getString(1).substring(6,10);
			}
			out.println("document.Form1.VAL_DAY1.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH1.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR1.value  =\""+m_date_yy+"\"");			
			out.println("}");
			
			
      out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("    if(checkMonthLength(objdd,objmm,objyy)){");	
			out.println("    return true;");
			out.println("    }");
			out.println("    else{");
			out.println("    return false;");
			out.println("    }");
      out.println("   }else{");
			out.println("       alert('Date field cannot be empty');");
			out.println("        return false;");
			out.println("   }");
			out.println("}");
			
			out.println("function run_report() {");
			out.println("	if(check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){");
			out.println("		m_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_MK_Business_analysis?chksql=run_report&date=\"+m_date+\" \";"); 
			out.println("   set_timer_actions();");
			out.println("		load_interface(m_url,'NORM');");
			out.println("	}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		if(m_data==\"OK\"){");
			out.println("			view_report();"); 
			out.println("		}");
			out.println("		else{");
			out.println("			alert('Error when generating Report...'+m_data);");
			out.println("		}");
			out.println("}");
				
			out.println("</Script>");
			
			out.println("<body onload=\"get_sys_date()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");
			
			out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
			out.println("<td class=\"border_wht\" valign=\"top\"> ");
			out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
			out.println("<tr> ");
			out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
			out.println("</tr>");
			out.println("<tr> ");
			out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style=\"height: 327px\">");
						
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Bussiness Volume Analysis Report</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<td width='6%'></td>"); 
			out.println("<td width='6%'></td>"); 
			out.println("<td width='6%'></td>"); 
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table'>"); 
  			
			out.println("<tr class=tr_input>");
			out.println("<td width='10%' >&nbsp;</td>");
			out.println("<td width='5%' ID=VDATE><b>Date</b></td>");
			out.println("<td width='5%' ></td>");			
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");			
			
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View Report\" onClick=\"view_report()\" style='width:150'></td>"); 
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"Run Report\" onClick=\"run_report()\" style='width:150'></td>"); 
		  out.println("<td width='*%'></td>");
			out.println("</tr>");	
			out.println("</table>");	
			out.println("<br><br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</html>");

					
			}else
			if(m_chksql.equals("BUSINESS_ANALYSIS")){  
			int k=0;
			String m_from_date = req.getParameter("from_date");
			String m_prv_month_0 = "";
			String m_prv_month_1 = "";
		  String m_prv_month_2 = "";												
			String m_curr_month = "";
			String m_curr_year = "";
		  int m_next_year =0;
			rs2=stmt2.executeQuery(" SELECT TO_CHAR(ADD_MONTHS((TO_DATE('"+m_from_date+"','DD-MM-YYYY')),0),'MON-YYYY'), "+
													   "        TO_CHAR(ADD_MONTHS((TO_DATE('"+m_from_date+"','DD-MM-YYYY')),-1),'MON-YYYY'), "+
													   "        TO_CHAR(ADD_MONTHS((TO_DATE('"+m_from_date+"','DD-MM-YYYY')),-2),'MON-YYYY'), "+
														 "        TO_CHAR(SYSDATE,'fmddth Month YYYY'), "+
														 "        TO_CHAR(SYSDATE,'YYYY') "+
													   " FROM DUAL ");
			
			if(rs2.next()){
			m_prv_month_0 = rs2.getString(1);
			m_prv_month_1 = rs2.getString(2);
			m_prv_month_2 = rs2.getString(3);
			m_curr_month  = rs2.getString(4);
			m_curr_year   = rs2.getString(5);
			}
		  m_next_year = Integer.parseInt(m_curr_year)+1;
			
			rs=stmt.executeQuery(" SELECT A.ENTITY_CODE, "+//1
													 " A.DESCRIPTION, "+//2
													 " ROUND(NVL(B.MONTH_3_VALUE,0)/1000000,2), "+//3
													 " ROUND(NVL(B.MONTH_2_VALUE,0)/1000000,2), "+//4
													 " ROUND(NVL(B.MONTH_1_VALUE,0)/1000000,2), "+//5
													 " ROUND((NVL(B.MONTH_1_VALUE,0)+NVL(B.MONTH_2_VALUE,0)+NVL(B.MONTH_3_VALUE,0))/1000000,2), "+ //6
													 " ORDER_ID "+ //7
													 " ,ROUND(NVL(B.YEAR_TO_DATE,0)/1000000,2) "+//8
													 " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY A, "+
													 "      "+m_schema_name+".AF_TBD_BUSINESS_VOLUME B "+
													 " WHERE A.ENTITY_CODE = B.BUSINESS_TYPE  "+
													 " AND   A.ACTIVE_STATUS='Y' "+
													 " AND   B.BUS_CATEGORY  ='SEGMENT' "+
													 " AND   B.ENT_USER = '"+m_username+"' ORDER BY ORDER_ID ");
			
	
/*			rs1=stmt1.executeQuery(" SELECT  "+
														 " B.BUSINESS_TYPE,  "+//1
														 " NVL(A.DESCRIPTION,'Not Assign'), "+//2
														 " NVL(B.MONTH_3_VALUE,0), "+//3
														 " NVL(B.MONTH_2_VALUE,0), "+//4
														 " NVL(B.MONTH_1_VALUE,0) "+//5
														 " FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR A,"+m_schema_name+".AF_TBD_BUSINESS_VOLUME B "+
														 " WHERE A.SECTOR_CODE(+) = B.BUSINESS_TYPE  "+
														 " AND A.ACTIVE_STATUS = 'Y' "+
														 " AND B.BUS_CATEGORY  ='INDUSTRY' "+
														 " AND B.ENT_USER      = '"+m_username+"' " );
	*/

			rs1=stmt1.executeQuery(" SELECT  "+
														 " B.BUSINESS_TYPE,  "+//1
														 " NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(B.BUSINESS_TYPE),'Not Assign') ,"+ //2
														 " ROUND(SUM(NVL(B.MONTH_3_VALUE,0))/1000000,2), "+//3
													     " ROUND(SUM(NVL(B.MONTH_2_VALUE,0))/1000000,2), "+//4
													     " ROUND(SUM(NVL(B.MONTH_1_VALUE,0))/1000000,2), "+//5
													     " ROUND((SUM(NVL(B.MONTH_1_VALUE,0))+SUM(NVL(B.MONTH_2_VALUE,0))+SUM(NVL(B.MONTH_3_VALUE,0)))/1000000,2),"+ //6
														 " ORDER_ID "+ //7
														 " ,ROUND(SUM(NVL(B.YEAR_TO_DATE,0))/1000000,2) "+//8
														 " FROM "+m_schema_name+".AF_TBD_BUSINESS_VOLUME B "+
														 " WHERE B.BUS_CATEGORY  ='INDUSTRY' "+
														 " AND   B.ENT_USER      = '"+m_username+"' "+
														 " GROUP BY B.BUSINESS_TYPE,NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(B.BUSINESS_TYPE),'Not Assign'),ORDER_ID "+
														 " ORDER BY ORDER_ID " );
														 
			rs3 = stmt3.executeQuery(" SELECT DECODE(A.BUSINESS_TYPE,'BUDGET','Budget','APPROVAL','Approval','DISBURSE','Disbursements','REJECT','Rejection','LOST','Lost to Competition'), "+//1
													     " A.BUS_CATEGORY, "+//2
													     " NVL(A.MONTH_1_HO,0),"+//3 
													     " NVL(A.MONTH_1_BR,0),"+//4
													     " NVL(A.MONTH_1_BK,0), "+//5
													     " NVL(A.MONTH_2_HO,0), "+//6
													     " NVL(A.MONTH_2_BR,0), "+//7
													     " NVL(A.MONTH_2_BK,0), "+//8
													     " NVL(A.MONTH_3_HO,0), "+//9
													     " NVL(A.MONTH_3_BR,0), "+//10
													     " NVL(A.MONTH_3_BK,0) "+ //11    
														 " ,ORDER_ID "+ //12
														 " ,BUSINESS_TYPE "+ //13
														 " ,NVL(A.YEAR_TO_DATE,0) "+ //14    
													  	 " FROM "+m_schema_name+".AF_TBD_BUSINESS_VOLUME A "+
													  	 " WHERE A.BUS_CATEGORY = 'BASIC' "+
														 " AND A.ENT_USER   = '"+m_username+"' ORDER BY ORDER_ID "); //added by ns on 08-10-2010 ORDER_ID
																
            
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Bussiness Volume Analysis Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function mouse_over(id){");
			out.println("document.getElementById(id).style.textDecoration='underline';");
			out.println("}");
								
			out.println("function mouse_out(id){");
			out.println("document.getElementById(id).style.textDecoration='none';");
			out.println("}");								
									
									
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
			out.println("<FORM NAME='Form1' method='post'>"); 			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='center'  style='height: 18px'><b>Business Volumes- Industry & Market Segment Analysis As at "+m_from_date+"</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("<BR>");
			out.println("<BR>");
			
			out.println("<table width=96%  border=0 cellspacing=0  align=center cellpadding=0 >");
			out.println("<tr>"); 
			out.println("<td width='96%' >"); 
			out.println("<table width=96%  border=0 cellspacing=0  align=center cellpadding=0 >");
			out.println("<tr class=tr_input ><td width='50%' colspan=3><b>LAKDERANA INVESTMENTS LIMITED</td><td width='*%' >&nbsp;</td></tr>");
	    out.println("<tr class=tr_input ><td width='10%'>To</td><td width='5%' align='center'>-</td><td width='35%' >The Board of Directors</td><td width='*%' >&nbsp;</td></tr>");
			out.println("<tr class=tr_input ><td width='10%'>Through</td><td width='5%' align='center'>-</td><td width='35%' >Director/Chief Executive Officer</td><td width='*%' >&nbsp;</td></tr>");
			out.println("<tr class=tr_input ><td width='10%'>From</td><td width='5%' align='center'>-</td><td width='35%' >Assistant General Manager - Business Development</td><td width='*%' align='right'><b>Board Paper No.<input type='text'  style='{text-align:right;border:none;font-weight:bold;width:120px}' value='64/06/10/2008'></td></tr>");
			out.println("<tr class=tr_input ><td width='10%'>Subject</td><td width='5%' align='center'>-</td><td width='35%'>Business Volumes - Industry & Market Segment Analysis</td><td width='*%' align='right'><b>Date - "+m_curr_month+"</td></tr>");
			out.println("</table>");
			out.println("</td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<BR>");
			out.println("<BR>");
			out.println("<table width=96%  border=0 cellspacing=0  align=center cellpadding=0 >");
			out.println("<tr><td width='50%'><b>FINANCIAL YEAR "+m_curr_year+"/"+m_next_year+"</td><td width='50%' align='right'><b>Rs.Mn</td></tr>");
			out.println("</table>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >"); 	
		  out.println("<tr class=tr_input ><td>");
			
			out.println("<table width=96%  border=1 cellspacing=0  align=center cellpadding=0  >");
	    out.println("<tr class=tr_input>");
	    out.println("<td width=20% bgcolor='lime'>&nbsp;</td>");
	    out.println("<td colspan=3 bgcolor='#CC99FF' align='center'><b>"+m_prv_month_2+"</td>");
	    out.println("<td colspan=3 bgcolor='#CC99FF' align='center'><b>"+m_prv_month_1+"</td>");
	    out.println("<td colspan=3 bgcolor='#CC99FF' align='center'><b>"+m_prv_month_0+"</td>");
	    out.println("<td colspan=3 bgcolor='#CC99FF' align='center'><b>Sub Total</td>");
			out.println("<td  bgcolor='#CC99FF' align='center'><b>Year to date Total</td>");
	    out.println("</tr>");
	    out.println("<tr class=tr_input >");
			out.println("<td width=5% align='right'>&nbsp;</td>");
	    out.println("<td width=5% align='right'>Head Office</td>");
	    out.println("<td width=5% align='right'>Branches</td>");
	    out.println("<td width=5% align='right'>Bike Division</td>");
	    out.println("<td width=5% align='right'>Head Office</td>");
	    out.println("<td width=5% align='right'>Branches</td>");
	    out.println("<td width=5% align='right'>Bike Division</td>");
	    out.println("<td width=5% align='right'>Head Office</td>");
	    out.println("<td width=5% align='right'>Branches</td>");
	    out.println("<td width=5% align='right'>Bike Division</td>");
	    out.println("<td width=5% align='right'>Head Office</td>");
	    out.println("<td width=5% align='right'>Branches</td>");
	    out.println("<td width=5% align='right'>Bike Division</td>");
			out.println("<td width=5% align='right'>&nbsp;</td>");
	    out.println("</tr>");			
			
		double m_open_balance=0;
		double m_sub_total=0;

			
		while(rs3.next()){
		k++;
		
		/*
		//added by ns on 08-10-2010 to get the opening balance
		rs4 = stmt4.executeQuery(
		" SELECT SUM(A.BALANCE) "+
        " FROM   "+m_schema_name+".AF_PRO_BUS_VOLUME_BAL A "+
		" WHERE  A.BUSINESS_TYPE='"+rs3.getString(13)+"' ");
		double m_open_balance=0;
		double m_sub_total=0;
		
		if(rs4.next()){
		m_open_balance=rs4.getDouble(1);
		}
		*/
		m_sub_total=(rs3.getDouble(9)+rs3.getDouble(6)+rs3.getDouble(3)+rs3.getDouble(10)+rs3.getDouble(7)+rs3.getDouble(4)+rs3.getDouble(11)+rs3.getDouble(8)+rs3.getDouble(5))/1000000;
  
		out.println("<tr class=tr_input >");
		out.println("<td width=5%>"+rs3.getString(1)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='ho_mn_1' ><b>"+nf.format(rs3.getDouble(9)/1000000)+"</td>");//onmouseover=\"mouse_over('ho_mn_1')\" onmouseout=\"mouse_out('ho_mn_1')\" 
	    out.println("<td width=5% align='right' style='cursor:hand' id='br_mn_1' ><b>"+nf.format(rs3.getDouble(10)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='bk_mn_1' ><b>"+nf.format(rs3.getDouble(11)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='ho_mn_2' ><b>"+nf.format(rs3.getDouble(6)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='br_mn_2' ><b>"+nf.format(rs3.getDouble(7)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='bk_mn_2' ><b>"+nf.format(rs3.getDouble(8)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='ho_mn_3' ><b>"+nf.format(rs3.getDouble(3)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='br_mn_3' ><b>"+nf.format(rs3.getDouble(4)/1000000)+"</td>");
	    out.println("<td width=5% align='right' style='cursor:hand' id='bk_mn_3' ><b>"+nf.format(rs3.getDouble(5)/1000000)+"</td>");	    
	    out.println("<td width=5% align='right'><b>"+nf.format((rs3.getDouble(9)+rs3.getDouble(6)+rs3.getDouble(3))/1000000)+"</td>");
	    out.println("<td width=5% align='right'><b>"+nf.format((rs3.getDouble(10)+rs3.getDouble(7)+rs3.getDouble(4))/1000000)+"</td>");
	    out.println("<td width=5% align='right'><b>"+nf.format((rs3.getDouble(11)+rs3.getDouble(8)+rs3.getDouble(5))/1000000)+"</td>");	   
	    //out.println("<td width=5% align='right'><b>"+nf.format((rs3.getDouble(9)+rs3.getDouble(6)+rs3.getDouble(3)+rs3.getDouble(10)+rs3.getDouble(7)+rs3.getDouble(4)+rs3.getDouble(11)+rs3.getDouble(8)+rs3.getDouble(5))/1000000)+"</td>");
		//out.println("<td width=5% align='right' style='cursor:hand' id='bk_mn_3' ><b>"+nf.format(m_sub_total+m_open_balance)+"</td>");	    
		out.println("<td width=5% align='right' style='cursor:hand' id='bk_mn_3' ><b>"+nf.format(rs3.getDouble(14)/1000000)+"</td>");	    
		out.println("</tr>");			
			}						
	    out.println("</table>");
			
			out.println("<br>");
			
		  out.println("<table class='table' border='1' cellpadding='0' cellspacing='0' width='96%' align='center'  bgcolor='#FAFAD2'>"); 	
			out.println("<tr class=tr_input>");
			out.println("<td width=20% align='left'   bgcolor='lime'><b>Market Segments</td>");//#CCFF66
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_2+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_1+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_0+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>Sub Total</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>Year To Date</td>");
			out.println("</tr>");	
		
			while(rs.next()){
			out.println("<tr class=tr_input>");
			out.println("<td width=20% align='left'>"+rs.getString(2)+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs.getDouble(4))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs.getDouble(5))+"</td>");
			//out.println("<td width=20% align='right'><b>"+nf.format(rs.getDouble(3)/1000000 + rs.getDouble(4)/1000000 + rs.getDouble(5)/1000000)+"</td>");
			//out.println("<td width=20% align='right'><b>"+nf.format((rs.getDouble(3)+rs.getDouble(4)+rs.getDouble(5))/1000000)+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs.getDouble(6))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs.getDouble(8))+"</td>");
			out.println("</tr>");
			}
					
			out.println("</td>");	
			out.println("</tr>");
			out.println("</table>");	
			out.println("</table>");
			
			out.println("<br>");
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'  bgcolor='#FAFAD2'>"); 	
		  out.println("<tr class=tr_input ><td>");
			out.println("<table class='table' border='1' cellpadding='0' cellspacing='0' width='96%' align='center'>"); 	
			out.println("<tr class=tr_input>");
			out.println("<td width=20% align='left' bgcolor='lime'><b>Year To Date Industry Analysis</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_2+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_1+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>"+m_prv_month_0+"</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>Sub Total</td>");
			out.println("<td width=15% align='center' bgcolor='#CC99FF'><b>Year To Date</td>");
			out.println("</tr>");
			
			while(rs1.next()){
			out.println("<tr class=tr_input>");
			out.println("<td width=20%>"+rs1.getString(2)+"</td>");	
			out.println("<td width=15% align='right'><b>"+nf.format(rs1.getDouble(3))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs1.getDouble(4))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs1.getDouble(5))+"</td>");
			//out.println("<td width=20% align='right'><b>"+nf.format(rs1.getDouble(3)/1000000 + rs1.getDouble(4)/1000000 + rs1.getDouble(5)/1000000)+"</td>");
			//out.println("<td width=20% align='right'><b>"+nf.format((rs1.getDouble(3)+rs1.getDouble(4)+rs1.getDouble(5))/1000000)+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs1.getDouble(6))+"</td>");
			out.println("<td width=15% align='right'><b>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("</tr>");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</table>");
			out.println("<BR>");
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			
			
			}


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


