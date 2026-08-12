//CREATED BY DINETH ON 24-03-2009
//SCREEN NAME:AF_MISF_OTH_CHG_LIAB
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_MISF_Other_Charge_Liability_Rpt extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt_rental;
		public ResultSet rs,rs1,rs2,rs_rental;
		PreparedStatement pstmt,pstmt1,pstmt2;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 
		
		
		nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();	
			stmt_rental = conn.createStatement();	
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			
			
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");
			
			
			
			if(m_screen_type.trim().equals("main_page")){
			  String m_date_dd = "";
				String m_date_mm = "";
				String m_date_yy = "";
			
			
			  out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");			
			
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Other Charge Liability Report  - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 

				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Other Charge Liability Report  - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_detail(){ ");	
				out.println("as_at_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("charge_type= document.Form1.TXT_CHARGE_TYPE.value;");
				out.println("finance_no = document.Form1.TXT_FINANCE_NO.value;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Other_Charge_Liability_Rpt?chksql=MAIN&charge_type=\"+charge_type+\"&as_at_date=\"+as_at_date+\"&finance_no=\"+finance_no+\" \";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println(" } ");	
			
				
				
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
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){");
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		finance_assign(oBj);"); 
	  		out.println("		}"); 
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
				
				out.println("function help_finance() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				//out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
				out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'FinanceSql2','2');");
				//out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function finance_assign(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("}"); 
			
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Other_Charge_Liability_Rpt?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
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
				out.println("  checkMonthLength(objdd,objmm,objyy);");			
      	out.println("}");
				out.println("}");
			
				
				out.println("</Script>");
				out.println("<body onload=\"get_sys_date()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Other Charge Liability Report</td>");
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
				out.println("<table align='center' width='100%' class='table' border=0>"); 
  			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%' ID=VDATE>As At Date</td>");
				// Commented by Udara Somathilake on 14/10/2009
				//out.println("<td width='30%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				//out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				//out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				//Added by Udara Viruwan Somathilake on 14/10/2009
				out.println("<td width='30%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=checkMonthLength(VAL_DAY1,VAL_MONTH1,VAL_YEAR1); > ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=checkMonthLength(VAL_DAY1,VAL_MONTH1,VAL_YEAR1); > ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=checkMonthLength(VAL_DAY1,VAL_MONTH1,VAL_YEAR1); ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				
				out.println("</td>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("<tr >"); 
			out.println("<td width='10%' >Charge Type </td>"); 
			out.println("<td width='30%' ><SELECT  name=\"TXT_CHARGE_TYPE\" class=\"txt_input\"   style=\"{width:150px;}\"> ");
							
			rs1 = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			" WHERE ACTIVE_STATUS='Y' AND ACCOUNT_TYPE = 'L' ");
        out.println("<OPTION value=\"ALL\" selected >All</OPTION>");
				while(rs1.next()){
				out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
				}
			out.println("</td>");
			//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_INVOICE_TYPE' maxlength='15' size='15'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO'       maxlength='20' size='20' style=\"{width:150px;}\" onblur=\"help_finance()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_finance()\"></td>"); 
			out.println("<td width='10%' align='left'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_detail()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
				out.println("</table>");			
			
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</html>");

			
			
			
			
			}
			if(m_screen_type.trim().equals("MAIN")){
				String m_finance_no=req.getParameter("finance_no");
				String m_as_at_date=req.getParameter("as_at_date");
				String m_charge_type=req.getParameter("charge_type");
				String m_charge_type_desc ="";
				String m_type_code="";//Added by Dineth on 25-06-2009
				
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Other Charge Liability Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println(" function load_voucher(payment_no){ ");
			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+payment_no+\"&print=TRUE\";	");				
			out.println("popupwin =window.open(m_url,'displayWindow3','left=110,top=110,width=750,height=800,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("}");
			
			out.println(" function load_pay_details(invoice_no,finance_no){ ");
			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MISF_Other_Charge_Liability_Rpt?chksql=pay_detail&finance_no=\"+finance_no+\"&invoice_no=\"+invoice_no+\" \";	");				
			out.println(" window.open(m_url,'displayWindow8','left=110,top=110,width=650,height=400,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("}");
			out.println(" function clear_drill(sus_ref_no){");
			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MISF_Other_Charge_Liability_Rpt?sus_ref_no=\"+sus_ref_no+\"&chksql=clear_drill\";	");				
			out.println(" window.open(m_url,'displayWindow8','left=110,top=110,width=650,height=400,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			
			
			out.println("}");
				
				
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 			
			out.println("<BR>");
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='center'  style='height: 18px'><b>Other Charge Liability Report As At "+m_as_at_date+" </td></tr>"); 
			if(!m_finance_no.equals("")){
			out.println("<tr><td align='left'  style='height: 18px;cursor:hand' title=\"Display "+m_finance_no+" Detail\" onclick=\"show_finance_detail_drill('"+m_finance_no+"');\" ><b>Finance No : <u>"+m_finance_no+" </td></tr>"); 
			}
			if(!m_charge_type.equals("ALL")){
			
			rs1 = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION,TYPE_CODE "+//Modified by Dineth on 25-06-2009
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			" WHERE SUB_TYPE_CODE = '"+m_charge_type+"' ");
			
			if(rs1.next()){
			m_charge_type_desc = rs1.getString(2);
			m_type_code        = rs1.getString(3);//Added by Dineth on 25-06-2009
			}
			
			out.println("<tr><td align='left'  style='height: 18px;'><b>Charge Type :"+m_charge_type_desc+" </td></tr>"); 
			}
			out.println("</table>");  
			out.println("</table>");  
			out.println("<BR>");
			out.println("<BR>");
			
			//----------------Commented By Sandun on 02-04-2009----------------------------------
				/*
				if(!m_finance_no.equals("")){
					if(m_charge_type.equals("ALL")){
				rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
				                     " A.INVOICE_NO, "+
														 " TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'), "+
														 " B.TOT_SETTLE_AMOUNT, "+
														 " B.INT_BAL_SETTLE_AMOUNT, "+
														 " B.BAL_TO_BE_PAID, "+
														 " NVL(C.PAYMENT_NO,'-'), "+
													   " DECODE(D.PROCESS_STATUS,'DISBRS','Disbursed','-'), "+
														 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-') "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
														 " WHERE A.INVOICE_NO=B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+ 
														 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO=D.PAYMENT_NO(+) "+
														 " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
                             " AND A.FINANCE_NO='"+m_finance_no+"' ");
					}
					else{
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
				                     " A.INVOICE_NO, "+
														 " TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'), "+
														 " B.TOT_SETTLE_AMOUNT, "+
														 " B.INT_BAL_SETTLE_AMOUNT, "+
														 " B.BAL_TO_BE_PAID, "+
														 " NVL(C.PAYMENT_NO,'-'), "+
                             " DECODE(D.PROCESS_STATUS,'DISBRS','Disbursed','-'), "+
														 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-') "+
														 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
														 " WHERE A.INVOICE_NO=B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+ 
														 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO = D.PAYMENT_NO(+) "+
                             " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
														 " AND A.FINANCE_NO='"+m_finance_no+"' AND B.SUSPENSE_ENTRY_TYPE = '"+m_charge_type+"' ");
				  }
					int j=0;										
					boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b>No Data Found</b></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='5%' align='left'>No</td>");
					out.println("<td width='14%' align='left'>Invoice No</td>"); 
					out.println("<td width='10%' align='left'>Value Date</td>");
					out.println("<td width='15%' align='right'>Amount</td>"); 
					out.println("<td width='15%' align='right'>Requested Amt</td>"); 
					out.println("<td width='15%' align='right'>Balance Amt</td>"); 
					out.println("<td width='15%' align='left'>Payment No</td>");
					out.println("<td width='8%' align='left'>Payment Status</td>");
					out.println("<td width='8%' align='left'>Charge Type</td>");
					out.println("</tr>");
						while(more){
							j=j+1;
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input valign=top>");
							}
							else{
      					out.println("<tr class=tr_input1 valign=top>");
							}
							out.println("<td width='5%' align='left'>"+j+"</td>");
							out.println("<td width='14%' align='left' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(2)+"');\"><u>"+rs.getString(2)+"</u></td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
							out.println("<td width='15%' align='left' STYLE='{cursor:hand;}' onclick=\"load_voucher('"+rs.getString(7)+"');\"><u>"+rs.getString(7)+"</u></td>"); 
							out.println("<td width='12%' align='left'>"+rs.getString(8)+"</td>");
							out.println("<td width='12%' align='left'>"+rs.getString(9)+"</td>");
							out.println("</tr>");
						more=rs.next();
						
						}
						out.println("</table>");
					}
															
				}											
				else if(m_finance_no.equals("")){
				if(m_charge_type.equals("ALL")){
				rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
				                     " A.INVOICE_NO, "+
														 " TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'), "+
														 " B.TOT_SETTLE_AMOUNT, "+
														 " B.INT_BAL_SETTLE_AMOUNT, "+
														 " B.BAL_TO_BE_PAID, "+
														 " NVL(C.PAYMENT_NO,'-'), "+
														 " DECODE(D.PROCESS_STATUS,'DISBRS','Disbursed','-'), "+
														 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-') "+
														 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
														 " WHERE A.INVOICE_NO=B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+ 
														 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO=D.PAYMENT_NO(+) "+
                             " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')");
					}
					else{
					
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+
				                     " A.INVOICE_NO, "+
														 " TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'), "+
														 " B.TOT_SETTLE_AMOUNT, "+
														 " B.INT_BAL_SETTLE_AMOUNT, "+
														 " B.BAL_TO_BE_PAID, "+
														 " NVL(C.PAYMENT_NO,'-'), "+
														 " DECODE(D.PROCESS_STATUS,'DISBRS','Disbursed','-'), "+
														 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-') "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
														 " WHERE A.INVOICE_NO=B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+ 
														 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO=D.PAYMENT_NO(+) "+
                             " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') AND B.SUSPENSE_ENTRY_TYPE ='"+m_charge_type+"'");
					}		
					
					*/
					//-------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!End!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-----------//
					
					if(m_charge_type.equals("ALL")){
					
					//--------Added by Sandun on 02-04-2009-----------------------------
					rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
															 " A.INVOICE_NO,  "+//2
															 " TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//3
															 " B.TOT_SETTLE_AMOUNT,"+//4
															 " B.INT_BAL_SETTLE_AMOUNT, "+//5
															 " B.BAL_TO_BE_PAID,"+//6
															 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-')"+//7
															 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
															 " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
															 " WHERE A.INVOICE_NO = B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+
															 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO=D.PAYMENT_NO(+) "+
															 " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.FINANCE_NO LIKE UPPER('%"+m_finance_no+"%') ");
					
					int j=0;										
					boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' align='center'>No</td>");
					if(m_finance_no.equals("")){
					out.println("<td width='12%' align='left'>Finance No</td>");
					}
					out.println("<td width='12%' align='left'>Invoice No</td>"); 
					out.println("<td width='10%' align='left'>Value Date</td>");
					out.println("<td width='10%' align='right'>Amount</td>"); 
					out.println("<td width='10%' align='right'>Requested Amt</td>"); 
					out.println("<td width='10%' align='right'>Balance Amt</td>"); 
					//out.println("<td width='15%' align='left'>Payment No</td>");
				//	out.println("<td width='8%' align='left'>Payment Status</td>");
				if(m_charge_type.equals("ALL")){
				out.println("<td width='8%' align='left'>Charge Type</td>");
				}
				out.println("<td width='12%' align='center'>Payment Details</td>");
					out.println("</tr>");
						while(more){
							j=j+1;
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
							out.println("<td width='1%' align='center'>"+j+"</td>");
							if(m_finance_no.equals("")){
							out.println("<td width='12%' align='left' STYLE='{cursor:hand;}' title=\"Display "+rs.getString(1)+" Detail\" onclick=\"show_finance_detail_drill('"+rs.getString(1)+"');\"><u>"+rs.getString(1)+"</u></td>");
							}
							out.println("<td width='12%' align='left' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(2)+"');\"><u>"+rs.getString(2)+"</u></td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
							//out.println("<td width='15%' align='left' STYLE='{cursor:hand;}' onclick=\"load_voucher('"+rs.getString(7)+"');\"><u>"+rs.getString(7)+"</u></td>"); 
							//out.println("<td width='8%' align='left'>"+rs.getString(8)+"</td>");
							if(m_charge_type.equals("ALL")){
							out.println("<td width='8%' align='left'>"+rs.getString(7)+"</td>");
							}
							out.println("<td width='12%' align='center'><input type='button' name='BUT_DET' class='but_input' value='View' onclick=\"load_pay_details('"+rs.getString(2)+"','"+rs.getString(1)+"')\"></td>");
							out.println("</tr>");
						more=rs.next();
						
						}
						out.println("</table>");
					
			
					
					}
					}
					else{
					if(!m_type_code.equals("MAINTENANC")){
					rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
															 " A.INVOICE_NO,  "+//2
															 " TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+//3
															 " B.TOT_SETTLE_AMOUNT,"+//4
															 " B.INT_BAL_SETTLE_AMOUNT, "+//5
															 " B.BAL_TO_BE_PAID,"+//6
															 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUSPENSE_ENTRY_TYPE),'-')"+//7
															 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
															 " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D "+
															 " WHERE A.INVOICE_NO = B.REF_NO AND A.INVOICE_TYPE NOT IN ('INV_GENER','INV_RESI') "+
															 " AND B.SUS_REF_NO=C.SUS_REF_NO(+) AND C.PAYMENT_NO=D.PAYMENT_NO(+) "+
															 " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND B.SUSPENSE_ENTRY_TYPE LIKE '%"+m_charge_type+"%' "+
															 " AND A.FINANCE_NO LIKE UPPER('%"+m_finance_no+"%') ");
			int j=0;										
					boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' align='center'>No</td>");
					if(m_finance_no.equals("")){
					out.println("<td width='12%' align='left'>Finance No</td>");
					}
					out.println("<td width='12%' align='left'>Invoice No</td>"); 
					out.println("<td width='10%' align='left'>Value Date</td>");
					out.println("<td width='10%' align='right'>Amount</td>"); 
					out.println("<td width='10%' align='right'>Requested Amt</td>"); 
					out.println("<td width='10%' align='right'>Balance Amt</td>"); 
					//out.println("<td width='15%' align='left'>Payment No</td>");
				//	out.println("<td width='8%' align='left'>Payment Status</td>");
				if(m_charge_type.equals("ALL")){
				out.println("<td width='8%' align='left'>Charge Type</td>");
				}
				out.println("<td width='12%' align='center'>Payment Details</td>");
					out.println("</tr>");
						while(more){
							j=j+1;
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
							out.println("<td width='1%' align='center'>"+j+"</td>");
							if(m_finance_no.equals("")){
							out.println("<td width='12%' align='left' STYLE='{cursor:hand;}' title=\"Display "+rs.getString(1)+" Detail\" onclick=\"show_finance_detail_drill('"+rs.getString(1)+"');\"><u>"+rs.getString(1)+"</u></td>");
							}
							out.println("<td width='12%' align='left' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(2)+"');\"><u>"+rs.getString(2)+"</u></td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
							//out.println("<td width='15%' align='left' STYLE='{cursor:hand;}' onclick=\"load_voucher('"+rs.getString(7)+"');\"><u>"+rs.getString(7)+"</u></td>"); 
							//out.println("<td width='8%' align='left'>"+rs.getString(8)+"</td>");
							if(m_charge_type.equals("ALL")){
							out.println("<td width='8%' align='left'>"+rs.getString(7)+"</td>");
							}
							out.println("<td width='12%' align='center'><input type='button' name='BUT_DET' class='but_input' value='View' onclick=\"load_pay_details('"+rs.getString(2)+"','"+rs.getString(1)+"')\"></td>");
							out.println("</tr>");
						more=rs.next();
						
						}
						out.println("</table>");
					
			
			
			
			
			}
			}
			else{
			double m_maintain_value=0;
			double m_sett_value=0;
			double m_clear_value=0;
			double m_balance_value=0;
			rs = stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO,NVL(B.TOT_SETTLE_AMOUNT,0),NVL(D.SETTELED_AMOUNT,0),NVL(B.BAL_TO_BE_PAID,0),TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),NVL(F.CLEAR_AMOUNT,0),B.SUS_REF_NO "+
                             " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
														 " "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, "+
														 " "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
														 " "+m_schema_name+".AF_CR_PRO_CLEAR_PAYMENT F "+
														 " WHERE A.APPLICATION_NO=B.REF_NO "+
                             " AND B.SUS_REF_NO=D.SUS_REF_NO(+) "+
														 " AND D.PAYMENT_NO=E.PAYMENT_NO(+) "+
														 " AND B.SUS_REF_NO=F.SUS_PAYMENT_NO(+) "+
														 " AND B.SUSPENSE_ENTRY_TYPE=C.SUB_TYPE_CODE "+
														 " AND C.TYPE_CODE='MAINTENANC' "+
														 " AND B.SUSPENSE_ENTRY_TYPE LIKE '%"+m_charge_type+"%' "+
                             " AND B.VALUE_DATE<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
														 " AND A.FINANCE_NO LIKE UPPER('%"+m_finance_no+"%') "+
														 " UNION ALL "+
															" SELECT DISTINCT A.FINANCE_NO,NVL(B.TOT_SETTLE_AMOUNT,0),NVL(D.SETTELED_AMOUNT,0),NVL(B.BAL_TO_BE_PAID,0),TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),NVL(F.CLEAR_AMOUNT,0),B.SUS_REF_NO "+
                             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
														 " "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, "+
														 " "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN D, "+
                             " "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
														 " "+m_schema_name+".AF_CR_PRO_CLEAR_PAYMENT F "+
														 " WHERE A.INVOICE_NO=B.REF_NO "+
                             " AND B.SUS_REF_NO=D.SUS_REF_NO(+) "+
														 " AND D.PAYMENT_NO=E.PAYMENT_NO(+) "+
														 " AND B.SUS_REF_NO=F.SUS_PAYMENT_NO(+) "+
 														 " AND B.SUSPENSE_ENTRY_TYPE=C.SUB_TYPE_CODE "+
														 " AND C.TYPE_CODE='MAINTENANC' "+
														 " AND B.SUSPENSE_ENTRY_TYPE LIKE '%"+m_charge_type+"%' "+
                             " AND B.VALUE_DATE<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
														 " AND A.FINANCE_NO LIKE UPPER('%"+m_finance_no+"%') ");
														 //" GROUP BY 1,6 ");
				int j=0;										
					boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%' align='center'>No</td>");
			    out.println("<td width='12%' align='left'>Finance No</td>");
			    out.println("<td width='10%' align='left'>Value Date</td>");
					out.println("<td width='10%' align='right'>Maintenance Value</td>"); 
					out.println("<td width='10%' align='right'>Settled Amt</td>"); 
					out.println("<td width='10%' align='right'>Cleared Amt</td>"); 
					out.println("<td width='10%' align='right'>Balance Amt</td>");
					out.println("</tr>");
						while(more){
							j=j+1;
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
							m_maintain_value=m_maintain_value+rs.getDouble(2);
							m_sett_value=m_sett_value+rs.getDouble(3);
							m_clear_value=m_clear_value+rs.getDouble(6);
							m_balance_value=m_balance_value+rs.getDouble(4);
							out.println("<td width='1%' align='center'>"+j+"</td>");
			    		out.println("<td width='12%' align='left'>"+rs.getString(1)+"</td>");
			    		out.println("<td width='10%' align='left'>"+rs.getString(5)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(2))+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
							out.println("<td width='10%' align='right' STYLE='{cursor:hand;}' onclick=\"clear_drill('"+rs.getString(7)+"');\"><u>"+nf.format(rs.getDouble(6))+"</u></td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>");
							out.println("</tr>");
							more=rs.next();
						}
						out.println("<tr class=tr_input1 >");
							out.println("<td width='1%' align='center'>&nbsp;</td>");
			    		out.println("<td width='12%' align='left'>&nbsp;</td>");
			    		out.println("<td width='10%' align='left'>&nbsp;</td>");
							out.println("<td width='10%' align='right'><b>"+nf.format(m_maintain_value)+"</b></td>");
							out.println("<td width='10%' align='right'><b>"+nf.format(m_sett_value)+"</b></td>"); 
							out.println("<td width='10%' align='right'><b>"+nf.format(m_clear_value)+"</b></td>"); 
							out.println("<td width='10%' align='right'><b>"+nf.format(m_balance_value)+"</b></td>");
							out.println("</tr>");
							
					}
			
			}
			
			
			}
					
					
							
			
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("</body>"); 
					out.println("</html>"); 
					
			}
			
			else if(m_screen_type.trim().equals("pay_detail")){
			
				String m_invoice_no = req.getParameter("invoice_no");			
				String m_finance_no = req.getParameter("finance_no");			
			  double  m_tot=0;
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Other Charge Liability Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("</SCRIPT>");
			
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='center'  style='height: 18px'><b>Payment Details</td></tr>"); 
			
			out.println("<tr><td align='left'  style='height: 18px'><b>Finance No : "+m_finance_no+"</td></tr>"); 
			out.println("<tr><td align='left'  style='height: 18px'><b>Invoice No : "+m_invoice_no+"</td></tr>"); 
			
			out.println("</table>");
		  out.println("<br>");
			
			rs=stmt.executeQuery( " SELECT D.PAYMENT_NO,"+
														" DECODE(D.PROCESS_STATUS,'APPRO1','Approval-1','APPRO2','Approval-1','AUTHO','Authorized By Division','CANCEL','Cancel','DISBRS','Disbursed','ENTER','Bank Allocation',D.PROCESS_STATUS),"+
														" C.SETTELED_AMOUNT "+
													  " FROM "+
														" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C, "+
														" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D"+ 
														" WHERE B.SUS_REF_NO = C.SUS_REF_NO"+
														" AND D.PAYMENT_NO = C.PAYMENT_NO"+
														" AND B.REF_NO = '"+m_invoice_no+"' ");
																	
			
			
			
			    int k=0;
			    boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					
					out.println("<td width='1%' align='center'>No</td>");
					out.println("<td width='12%' align='left'>Payment No</td>"); 
					out.println("<td width='10%' align='left'>Status</td>"); 
					out.println("<td width='10%' align='right'>Amount</td>"); 
					out.println("</tr>");
						while(more){
							k=k+1;
							if(k>0 && k%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
							out.println("<td width='1%' align='center'>"+k+"</td>");
							out.println("<td width='12%' align='left' >"+rs.getString(1)+"</u></td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>"); 
							out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
							out.println("</tr>");
						  m_tot=m_tot+rs.getDouble(3);
						  more=rs.next();
						
						}
						out.println("<tr class=tr_input1 style='height:15'>");
						out.println("<td width='13%' colspan=3 align='right'><b>Total</td>");
						out.println("<td width='10%' align='right'><b>"+nf.format(m_tot)+"</td></tr>");
						out.println("</table>");
					
			
			out.println("</form>"); 
			out.println("<script language1.2='javascript' src='"+m_html_client_url+"/validate.js'></script>"); 
			out.println("<script language1.2='javascript' src='"+m_html_client_url+"/leasing_drill_down.js'></script>");
			out.println("</body>"); 
			out.println("</html>"); 
			
			}
		}
    else if(m_screen_type.trim().equals("clear_drill")){
		    
				String m_sus_ref_no = req.getParameter("sus_ref_no");			
						
			  double  m_tot=0;
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Other Charge Liability Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("</SCRIPT>");
			
			
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='center'  style='height: 18px'><b>Clear Details</td></tr>"); 
			
			
			out.println("</table>");
		  out.println("<br>");
			rs=stmt.executeQuery( " SELECT FINANCE_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
			                      " TO_CHAR(CLEAR_DATE,'DD-MM-YYYY') "+
                            " FROM "+m_schema_name+".AF_CR_PRO_CLEAR_PAYMENT "+
                            " WHERE SUS_PAYMENT_NO='"+m_sus_ref_no+"'");
		int k=0;
			    boolean more=rs.next();
					if(!more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr><td width=100% align='center'><b><font color='red'>No Data Found..!</b></font></td><tr>");
					out.println("</table>");
					}
					if(more){
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class=pdn_txtpos2>");
					
					out.println("<td width='1%' align='center'>No</td>");
					out.println("<td width='12%' align='left'>Finance No</td>"); 
					out.println("<td width='10%' align='left'>Clear Person</td>"); 
					out.println("<td width='10%' align='left'>Clear Date</td>"); 
					out.println("</tr>");
						while(more){
							k=k+1;
							if(k>0 && k%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
							out.println("<td width='1%' align='center'>"+k+"</td>");
							out.println("<td width='12%' align='left'>"+rs.getString(1)+"</td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>"); 
							out.println("</tr>");
					more=rs.next();
					}
					}

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




