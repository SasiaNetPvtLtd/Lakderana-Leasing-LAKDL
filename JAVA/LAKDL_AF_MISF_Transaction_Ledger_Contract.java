

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Transaction_Ledger_Contract extends javax.servlet.http.HttpServlet { 

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
			else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_BY_CONTRACT")){
			
            String s9 = req.getParameter("finance_no");
            String s13 = req.getParameter("client_code");
            int i = 0;
            String s18 = "";
            String s20 = "";
            String s22 = "";
            String s23 = "";
            double d2 = 0.0D;
            double d4 = 0.0D;
            double d10 = 0.0D;
            double d13 = 0.0D;
						
						String m_finance_no=req.getParameter("finance_no");		
						String m_client_code=req.getParameter("client_code");		
						
						int count = 0;
						String m_string="";		
						String m_orient_name="";
						String m_name="";
						String m_cheque_no="";
						double m_cum_value=0;
						double m_val=0;
						double m_debit=0;
						double m_credit=0;
				
            out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : " + m_finance_no + " </TITLE></HEAD>");
            out.println("<link REL='STYLESHEET' HREF='" + s + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
            out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
            out.println("<FORM NAME='Form1' method='post'>");
            out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
            out.println("<TR><TD><CENTER><B> Transaction History - Finance No : " + m_finance_no + " </B></TD></TR>");
            out.println("</TABLE>");
            out.println("<BR><BR>");
            String s24 = " SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO  FROM  (   SELECT   INVOICE_NO REF_NO,   VALUE_DATE   DUE_DATE,   TOTAL_AMOUNT AMOUNT,   'INVOICE' TYP,   NVL(NULL,'-') CHEQUE_NO,   NVL((SELECT INVOICE_DESC FROM " + s3 + ".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE)," + s3 + ".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , " + "  NULL STATUS ," + "  NVL(" + s3 + ".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO " + "  FROM " + s3 + ".AF_CO_PRO_INVOICE   " + "  WHERE FINANCE_NO='" + m_finance_no + "' AND " + "        ACTIVE_STATUS='Y'  " + " AND VALUE_DATE <=SYSDATE " + "  UNION " + " SELECT " + " RECEIPT_NO REF_NO,  " + " NVL(EFF_VALDATE,ALLOCATED_DATE) DUE_DATE,  " + " SUM(SETTELED_AMOUNT) AMOUNT,  " + " 'RECEIPT' TYP,  " + " NVL(CHEQUE_NO,'-')  CHEQUE_NO , " + " DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','-') DESCRIPTION,  " + " NVL(STATUS,'E'),  " + " '' NO  " + " FROM  " + s3 + ".AF_CO_PRO_INVOICE_DETAILS A," + s3 + ".AF_CO_PRO_SETTL_RECEIPT B " + " WHERE RECEIPT_NO=REC_NO(+) AND " + " A.INVOICE_NO IN (SELECT INVOICE_NO FROM " + s3 + ".AF_CO_PRO_INVOICE " + "                  WHERE FINANCE_NO='" + m_finance_no + "' " + "                   UNION ALL " + "                   SELECT ODI_REF_NO " + "                   FROM  " + s3 + ".AF_CO_PRO_OD_INTEREST_MONTHLY " + "                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM " + s3 + ".AF_CO_PRO_INVOICE  " + "                   WHERE FINANCE_NO='" + m_finance_no + "' )) " + " GROUP BY RECEIPT_NO,EFF_VALDATE,ALLOCATED_DATE,CHEQUE_NO,SETTLE_MODE,STATUS " + "\tUNION " + " SELECT " + "    INVOICE_NO REF_NO, " + "    DUE_DATE  DUE_DATE, " + "    ODI_SETTLED_AMOUNT AMOUNT, " + "    'ODI' TYP, " + "    NULL CHEQUE_NO, " + "    'Over Due Interst' DESCRIPTION, " + "    NULL STATUS, " + "     '' NO " + " FROM " + s3 + ".AF_CO_PRO_OD_INTEREST_MONTHLY " + " WHERE ODI_SETTLED_AMOUNT > 0 " + " AND INVOICE_NO IN " + " ( SELECT INVOICE_NO " + "    FROM " + s3 + ".AF_CO_PRO_INVOICE " + "    WHERE FINANCE_NO='" + m_finance_no + "' AND " + "          ACTIVE_STATUS='Y' " + ") " + " AND DUE_DATE <=SYSDATE " + " UNION " + " SELECT " + " INVOICE_NO REF_NO, " + " ADJUSTED_DATE DUE_DATE, " + " ADJUSTED_AMOUNT AMOUNT, " + " 'DR/CR' TYP, " + " NULL CHEQUE_NO, " + " DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, " + " CREDIT_TYPE STATUS, " + "     '' NO " + " FROM " + s3 + ".AF_CO_PRO_CREDIT_DETAILS " + " WHERE INVOICE_NO IN " + " ( SELECT INVOICE_NO " + "    FROM " + s3 + ".AF_CO_PRO_INVOICE " + "    WHERE FINANCE_NO='" + m_finance_no + "' AND " + "    ACTIVE_STATUS='Y' " + " ) " + " AND ADJUSTED_DATE <=SYSDATE " + " UNION " + " SELECT a.rec_no REF_NO , " + " b.eff_valdate   DUE_DATE ," + " a.bal_tobe_receive ," + "'BAL' TYP, " + " CHEQUE_NO CHEQUE_NO ," + " NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc'),'-') DESCRIPTION,  " + " STATUS ," + " '' NO " + " FROM " + s3 + ".af_co_pro_settl_rec_app_bal a , " + s3 + ".AF_CO_PRO_SETTL_RECEIPT b " + " where a.rec_no=b.rec_no " + " and a.finance_no='" + m_finance_no + "'" + " and a.bal_tobe_receive <>0 " + " AND B.eff_valdate <=SYSDATE " + " ) " + " ORDER BY DUE_DATE ";
            rs = stmt1.executeQuery(s24);
            for(boolean flag4 = rs.next(); flag4; flag4 = rs.next())
            {
                if(++i == 1)
                {
                    out.println("<table align='center' width='100%' class='table' >");
                    out.println("<tr>");
                    out.println("<td width='*%'align='center' class=div_input><b>" + s20.toUpperCase() + "</b></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td width='*%'align='center' class=div_input><b>Asset Finance Ledger</b></td>");
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("<hr color='black'>");
                    out.println("<br>");
                    out.println("<table align='center' width='100%' class='table' >");
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
                    out.println("<tr class=pdn_txtpos2>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input>Date</td>");
                    out.println("<td width='15%' class=div_input>Doc Ref</td>");
                    out.println("<td width='30%' class=div_input>Narration</td>");
                    out.println("<td width='10%' class=div_input>Reference No.</td>");
                    out.println("<td width='10%' class=div_input align='right'>Debit</td>");
                    out.println("<td width='10%' class=div_input align='right'>Credit</td>");
                    out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
                    out.println("<td width='4%' class=div_input></td>");
                    out.println("</tr>");
                }
                double d11 = 0.0D;
                double d14 = 0.0D;
                if(rs.getString(4).equals("INVOICE"))
                    d11 = rs.getDouble(3);
                else
                if(rs.getString(4).equals("RECEIPT"))
                {
                    if(rs.getString(7).equals("RET"))
                        d11 = rs.getDouble(3);
                    else
                        d14 = rs.getDouble(3);
                } else
                if(rs.getString(4).equals("DR/CR"))
                {
                    if(rs.getString(7).equals("DR"))
                        d11 = rs.getDouble(3);
                    else
                        d14 = rs.getDouble(3);
                } else
                if(rs.getString(4).equals("ODI"))
                    d11 = rs.getDouble(3);
                else
                if(rs.getString(4).equals("OTHER"))
                    d11 = rs.getDouble(3);
                else
                if(rs.getString(4).equals("RET_CHARGE"))
                    d11 = rs.getDouble(3);
                else
                if(rs.getString(4).equals("BAL"))
                    if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C"))
                        d11 = rs.getDouble(3);
                    else
                        d14 = rs.getDouble(3);
                double d5 = d11 - d14;
                d2 += d5;
                if(rs.getString(4).equals("INVOICE"))
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                    out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                    out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                    out.println("<td width='10%' class=div_input>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                    out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                    if(d2 > 0.0D)
                        out.println("<td width='4%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='4%' class=div_input>Cr</td>");
                    out.println("</tr>");
                } else
                if(rs.getString(4).equals("RECEIPT"))
                {
                    if(rs.getString(7).equals("RET"))
                    {
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                        double d6 = d11 - d14;
                        d2 -= d6;
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                    } else
                    if(!rs.getString(7).equals("RET"))
                    {
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        if(rs.getString(7).equals("RET"))
                        {
                            out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                            out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        } else
                        {
                            out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                            out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        }
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                        if(rs.getString(7).equals("CAD") || rs.getString(7).equals("C"))
                        {
                            d11 = 0.0D;
                            double d15 = 0.0D;
                            d11 = rs.getDouble(3);
                            double d7 = d11 - d15;
                            d2 += d7;
                            out.println("<tr>");
                            out.println("<td width='1%'></td>");
                            out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                            out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                            out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                            if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                                out.println("<td width='10%' class=div_input>&nbsp;</td>");
                            else
                                out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                            out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                            out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                            out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                            if(d2 > 0.0D)
                                out.println("<td width='4%' class=div_input>&nbsp;</td>");
                            else
                                out.println("<td width='4%' class=div_input>Cr</td>");
                            out.println("</tr>");
                        }
                    }
                } else
                if(rs.getString(4).equals("RET_CHARGE"))
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                    out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                    out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                    if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                        out.println("<td width='10%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                    if(rs.getString(7).equals("RET"))
                    {
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                    } else
                    {
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                    }
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                    if(d2 > 0.0D)
                        out.println("<td width='4%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='4%' class=div_input>Cr</td>");
                    out.println("</tr>");
                } else
                if(rs.getString(4).equals("ODI"))
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                    out.println("<td width='15%' class=div_input>" + rs.getString(1) + "</td>");
                    out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                    out.println("<td width='10%' class=div_input>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                    out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                    if(d2 > 0.0D)
                        out.println("<td width='4%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='4%' class=div_input>Cr</td>");
                    out.println("</tr>");
                } else
                if(rs.getString(4).equals("OTHER"))
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input >" + rs.getString(2) + "</td>");
                    out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('" + rs.getString(1) + "')><u>" + rs.getString(1) + "</u></td>");
                    out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                    out.println("<td width='10%' class=div_input>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                    out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                    if(d2 > 0.0D)
                        out.println("<td width='4%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='4%' class=div_input>Cr</td>");
                    out.println("</tr>");
                } else
                if(rs.getString(4).equals("BAL"))
                {
                    if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C"))
                    {
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                        double d8 = d11;
                        d2 -= d8;
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "  </td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                    } else
                    {
                        out.println("<tr>");
                        out.println("<td width='1%'></td>");
                        out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                        out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                        out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                        if(rs.getString(5).equals("-") || rs.getString(5).equals("null"))
                            out.println("<td width='10%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                        if(d2 > 0.0D)
                            out.println("<td width='4%' class=div_input>&nbsp;</td>");
                        else
                            out.println("<td width='4%' class=div_input>Cr</td>");
                        out.println("</tr>");
                    }
                } else
                if(rs.getString(4).equals("DR/CR"))
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='10%' class=div_input>" + rs.getString(2) + "</td>");
                    out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                    out.println("<td width='30%' class=div_input>" + rs.getString(6) + "</td>");
                    out.println("<td width='10%' class=div_input>" + rs.getString(5) + "</td>");
                    if(rs.getString(7).equals("DR"))
                    {
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                    } else
                    {
                        out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
                        out.println("<td width='10%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "</td>");
                    }
                    out.println("<td width='10%' class=div_input align='right'>" + nf.format(a.abs(d2)) + "</td>");
                    if(d2 > 0.0D)
                        out.println("<td width='4%' class=div_input>&nbsp;</td>");
                    else
                        out.println("<td width='4%' class=div_input>Cr</td>");
                    out.println("</tr>");
                }
            }

            out.println("</table>");
            String s25 = " SELECT   A.REC_NO,   A.REC_AMOUNT,   B.allocated_amount,   B.bal_tobe_receive,   TO_CHAR(EFF_VALDATE,'DD-MM-YYYY')   FROM " + s3 + ".AF_CO_PRO_SETTL_RECEIPT A, " + s3 + ".AF_CO_PRO_SETTL_RECEIPT_BAL B " + "  WHERE  A.REC_NO=B.REC_NO  " + "  AND UPPER(A.CLIENT_CODE)=UPPER('" + m_client_code + "') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
            rs = stmt1.executeQuery(s25);
            boolean flag5 = rs.next();
            if(flag5)
            {
                out.println("<br>");
                out.println("<table align='center' width='100%' class='table' >");
                out.println("<tr>");
                out.println("<td width='1%'></td>");
                out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
                out.println("<td width='*%'></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
                out.println("<table align='center' width='100%' class='table' >");
                out.println("<tr>");
                out.println("<td width='1%'></td>");
                out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
                out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
                out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
                out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
                out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }
            out.println("<table align='center' width='100%' class='table' >");
            double d16 = 0.0D;
            for(; flag5; flag5 = rs.next())
            {
                i++;
                out.println("<tr>");
                out.println("<td width='1%'></td>");
                out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('" + rs.getString(1) + "') ><u>" + rs.getString(1) + "</u></td>");
                out.println("<td width='10%' class=div_input >" + rs.getString(5) + "</td>");
                out.println("<td width='20%' class=div_input align='right'>" + nf.format(rs.getDouble(2)) + "&nbsp;&nbsp;</td>");
                out.println("<td width='20%' class=div_input align='right'>" + nf.format(rs.getDouble(3)) + "&nbsp;&nbsp;</td>");
                out.println("<td width='20%' class=div_input align='right'>" + nf.format(rs.getDouble(4)) + "&nbsp;&nbsp;</td>");
                out.println("</tr>");
                d16 += rs.getDouble(4);
            }

            if(d16 > 0.0D)
            {
                out.println("<tr>");
                out.println("<td width='1%'></td>");
                out.println("<td width='15%' class=div_input >&nbsp;</td>");
                out.println("<td width='10%' class=div_input >&nbsp;</td>");
                out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
                out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
                out.println("<td width='20%' class=div_input align='right'><b>" + nf.format(d16) + "&nbsp;&nbsp;</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br>");
            out.println("<br>");
            out.println("<table align='center' width='100%' class='table' >");
            out.println("<tr>");
            out.println("<td width='1%'></td>");
            out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments</B></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr color='black'>");
            out.println("<br>");
            rs = stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'),COMMENTS,ENT_USER  FROM " + s3 + ".AF_CO_MAS_CLIENT_COMMENT " + " WHERE CLIENT_CODE ='" + m_client_code + "' ORDER BY ENT_DATE DESC ");
            boolean flag6 = rs.next();
            if(flag6)
            {
                out.println("<table align='center' width='100%' class='table' >");
                for(; flag6; flag6 = rs.next())
                {
                    out.println("<tr>");
                    out.println("<td width='1%'></td>");
                    out.println("<td width='20%' class=div_input valign='top'><p><B>Date: </B>" + rs.getString(1) + " &nbsp <br><B>User: </B>" + rs.getString(3) + "</p></td>");
                    out.println("<td width='2%'>&nbsp</td>");
                    out.println("<td width='70%' class=div_input>" + rs.getString(2) + "</td>");
                    out.println("<td width='*%'></td>");
                    out.println("</tr>");
                    out.println("<tr></tr>");
                }

                out.println("</table>");
            } else
            {
                out.println("<table align='center' width='100%' class='table' >");
                out.println("<tr>");
                out.println("<td width='1%'></td>");
                out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
                out.println("</tr>");
                out.println("</table>");
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<table></table>");
            out.println("<br>");
            out.println("<br>");
            out.println("<table align='center' width='100%' class='table' >");
            out.println("<tr>");
            out.println("<td width='1%'></td>");
            out.println("<td width='*%' align='center' class=div_input ><B>Comments - Collection</B></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr color='black'>");
            out.println("<br>");
            out.println("</form>");
            out.println("<SCRIPT language1.2='JavaScript' src='" + s + "/leasing_drill_down.js'></SCRIPT>");
            out.println("</BODY></HTML>");
        
			
			
			
			

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
