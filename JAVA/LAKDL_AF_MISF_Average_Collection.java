// DEVELOP BY : SANDUN FOR OFSCL FINANCE - AVERAGE COLLECTION RATIO  
// DATE:23-10-2008

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Average_Collection extends javax.servlet.http.HttpServlet { 
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf1,nf;
	Statement stmt1,stmt2;
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
				
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);			
				
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			
			String m_chksql=req.getParameter("chksql");
								
  	 	if(m_chksql.equals("run_report")){ 
			String m_date=req.getParameter("date");			
			
		try{
			callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_COL_RATIO_RPT(:1,:2);END;");
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
				out.println("<TITLE>Average Collection</TITLE>"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Average_Collection?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Average_Collection?chksql=main_page';"); 
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
				out.println("		help_box.innerHTML=\"Average Collection - \"+m_val;"); 
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

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Average_Collection?chksql=run_report&date=\"+m_date+\" \";"); 
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
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Average_Collection?chksql=print_report&date=\"+m_date ;"); 
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
			
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()'> ");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='' name='SCREEN_NAME'> "); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Average Collection</td>"); 
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
				
				double m_net_amount = 0.0;
				double m_coll =0.0;
				double m_collection_ratio =0.0;				
				double m_net_total = 0.0;
				double m_coll_total = 0.0;
				double m_arr_total = 0.0;
				double m_coll_ratio_tot = 0.0;
				String curr_month = "";
				double curr_net_due = 0.0;
				double curr_arr = 0.0;
				double curr_coll = 0.0;				
				double curr_coll_ratio =0.0;
				
				out.println("<HTML><HEAD><TITLE>Average Collection Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_insurance_details(val,val1){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=SHOW_INSURANCE_BY_CONTRACT&date='+val1+'&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
								
				out.println("</script>");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
        out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='2%' width='2%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Average Collection Ratio Report As At "+mdate+"</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='2%' width='2%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("</table>");				
				
				out.println("<table width='100%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width ='5%'><b>No.</td>");
				out.println("<td width ='10%'><b>Month</td>");
				out.println("<td width='20%' align=right><b>Net Due</td>");
				out.println("<td width='20%' align=right><b>Net Collection</td>");
				out.println("<td width='20%' align=right><b>Arrears</td>");
				out.println("<td width='20%' align=right><b>Collection Ratio</td>");
				out.println("</tr>");
				
				
				rs1= stmt1.executeQuery(" SELECT "+
																" TO_CHAR(TO_DATE(A.MONTH ,'DD-MM-YYYY'),'Month - YYYY'),"+//1
																" A.COLL_RATIO, "+//2
																" A.ENT_USER, "+//3
																" A.ENT_DATE,"+//4
													      " A.TOTAL_AMOUNT, "+//5
																" A.SETTLED_AMOUNT + A.ADJUSTMENTS COLLECTION, "+//6
													      " A.TOTAL_AMOUNT - (A.SETTLED_AMOUNT + A.ADJUSTMENTS) ARREARS ,"+//7
																" TO_DATE(A.MONTH ,'DD-MM-YYYY') "+//8
													      " FROM "+m_schema_name+".AF_TBD_COLLE_AVG_RATIO A	"+
																" WHERE ENT_USER = '"+m_username+"' "+ 
																" ORDER BY 8 ");
																																		
				int j=1;

				while(rs1.next()){
				m_net_amount = rs1.getDouble(5);
				m_coll = rs1.getDouble(6);
				m_collection_ratio = (m_coll/m_net_amount)*100;
					if(j%2==0){
						out.println("<tr bgcolor=\"#C0C0C0\" style='{height:25}'>");					
					}
					else{
					out.println("<tr bgcolor=\"#FFFFFF\" style='{height:25}' >");						
					}				 				
					out.println("<td width='5%' class=factoring-letter-body align='left'>"+j+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs1.getString(1)+"</td>");
					out.println("<td width='20%' class=factoring-letter-body align='right'>"+nf1.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='20%' class=factoring-letter-body align='right'>"+nf1.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='20%' class=factoring-letter-body align='right'>"+nf1.format(rs1.getDouble(7))+"</td>");
					out.println("<td width='20%' class=factoring-letter-body align='right'>"+nf1.format(m_collection_ratio)+"%</td>");
					out.println("</tr>");
					j = j+1;
					m_net_total = m_net_total+rs1.getDouble(5);
					m_coll_total = m_coll_total+rs1.getDouble(6);
					m_arr_total = m_arr_total+rs1.getDouble(7);
					m_coll_ratio_tot =(m_coll_total/m_net_total)*100;
				 }
				out.println("<tr >");
				out.println("<td colspan=2 align='center' ><b>Total</td>");
				out.println("<td width='20%' class=factoring-letter-body align='right'><b>"+nf1.format(m_net_total)+"</td>");
				out.println("<td width='20%' class=factoring-letter-body align='right'><b>"+nf1.format(m_coll_total)+"</td>");
				out.println("<td width='20%' class=factoring-letter-body align='right'><b>"+nf1.format(m_arr_total)+"</td>");
			  out.println("<td width='20%' class=factoring-letter-body align='right'><b>"+nf1.format(m_coll_ratio_tot)+"%</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='5%' width='5%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				rs2= stmt2.executeQuery(" SELECT "+
																" TO_CHAR(TO_DATE(A.MONTH ,'DD-MM-YYYY'),'Month - YYYY'),"+//1
																" A.COLL_RATIO, "+//2
																" A.ENT_USER, "+//3
																" A.ENT_DATE,"+//4
													      " A.TOTAL_AMOUNT, "+//5
																" A.SETTLED_AMOUNT + A.ADJUSTMENTS COLLECTION, "+//6
													      " A.TOTAL_AMOUNT - (A.SETTLED_AMOUNT + A.ADJUSTMENTS) ARREARS "+//7
													      " FROM "+m_schema_name+".AF_TBD_COLLE_AVG_RATIO A	"+
																" WHERE A.MONTH = TO_CHAR(TO_DATE('"+mdate+"','DD-MM-YYYY'),'DD-MM-YYYY')");
				
				if(rs2.next()){
				curr_month = rs2.getString(1);
				curr_net_due = rs2.getDouble(5);				
				curr_coll = rs2.getDouble(6);
				curr_arr = rs2.getDouble(7);
				curr_coll_ratio = (curr_coll/curr_net_due)*100;
				}
				
				
				out.println("<table width='100%' class='table' border='0'  cellspacing='0' cellspacing='1' >");
				out.println("<tr style='{height:25}'>");
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' align='left'><u><b>Current Month Detail</td>");
				out.println("<td width ='10%'>&nbsp;</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr style='{height:25}'>");
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' bgcolor=\"#C0C0CF\">Month</td>");
				out.println("<td width ='10%' bgcolor=\"#C0C0CF\" align='right'><B>"+curr_month+"</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr style='{height:25}'>");
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' bgcolor=\"#C0C0CF\">Net Due</td>");
				out.println("<td width ='10%' bgcolor=\"#C0C0CF\" align='right'><B>"+nf1.format(curr_net_due)+"</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr style='{height:25}' >");	
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' bgcolor=\"#C0C0CF\">Net Collection</td>");
				out.println("<td width ='10%' bgcolor=\"#C0C0CF\" align='right'><B>"+nf1.format(curr_coll)+"</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr style='{height:25}'>");
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' bgcolor=\"#C0C0CF\">Arrears</td>");
				out.println("<td width ='10%' bgcolor=\"#C0C0CF\" align='right'><B>"+nf1.format(curr_arr)+"</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr style='{height:25}'>");
				out.println("<td width= 5%>&nbsp;</td>");
				out.println("<td width ='20%' bgcolor=\"#C0C0CF\">Collection Ratio</td>");
				out.println("<td width ='10%' bgcolor=\"#C0C0CF\" align='right'><B>"+nf1.format(curr_coll_ratio)+"%</td>");
				out.println("<td width = '60%'>&nbsp;</td>");
				out.println("</tr>");				
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
