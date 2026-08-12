
//SCREEN NAME:VIEW OTHER PAYMENT VOUCHER
//CREATED BY:SANDUN
//DATE/TIME:03/12/2008
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_CR_PRO_Display_Other_Payment_Voucher extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt,pstmt1,pstmt2;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			
			
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_chksql = req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){
			String m_date_dd = "";
		  String m_date_mm = "";
		  String m_date_yy = "";
   
      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
							
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
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		assign_payment_no();"); 
	  	out.println("		}"); 
		
		
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data();");//Added To The Clear The Area Code
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println(" HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_data() {");
			out.println("}");
			*/
		
					
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Display_Other_Payment_Voucher?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Display_Other_Payment_Voucher?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 			

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Lease_Assign\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance Process - View Payment Voucher - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance Process - View Payment Voucher - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function make_request(){"); 
			out.println("from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("payee = document.Form1.CLIENT_CODE.value;");//Added by prabash on17-02-2012
			out.println("pamt_no = Form1.TXT_PAYMENT_NO.value;");//Added by prabash on17-02-2012

			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Display_Other_Payment_Voucher?chksql=load_voucher&payee=\"+payee+\"&pamt_no=\"+pamt_no+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\" \";");				
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			
			out.println("function close_screen(){	"); 
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("window.close();");
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
				
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY2.value=v_date;");
			out.println("     document.Form1.VAL_MONTH2.value=v_month;");
			out.println("     document.Form1.VAL_YEAR2.value=val;");
			out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("document.Form1.hid_to_date.value=date2");
			out.println("}");
			out.println("}");
			
			out.println("function get_sys_date(){");
			rs1 = stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			if(rs1.next()){
			m_date_dd = rs1.getString(1).substring(0,2);
			m_date_mm = rs1.getString(1).substring(3,5);
			m_date_yy = rs1.getString(1).substring(6,10);
			}
			out.println("document.Form1.VAL_DAY1.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH1.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR1.value  =\""+m_date_yy+"\"");
			out.println("document.Form1.VAL_DAY2.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH2.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR2.value  =\""+m_date_yy+"\"");
			out.println("}");
			
			
      out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
      out.println("}");
			out.println("}");	
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println("}");
			
			out.print("function load_pay_voucher(val){");
			out.println(" m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+val+\"&print=TRUE \";");				
			out.println("popupwin =window.open(m_url,'displayWindow3','left=110,top=110,width=750,height=800,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("}");
			
			
			//=====Added by Prabash on 16-02-2012 ==========================================
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee2','1');");
				out.println("}");
				
				out.println("function debit_note_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee2','2');");
				out.println("}");
		
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[3]");  
				out.println("}");
				out.println("function debit_note_no_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[4]");  
				out.println("}");
				
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

			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  		out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		debit_note_no_assign(oBj);"); 
	  		out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
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
			
			out.println("function check_client(val) {");
			out.println(" document.Form1.TXT_PAYMENT_NO.value = ''  ");
			 out.println("}");	
			//=======================================================================	
			
			
      out.println("</Script>");
			
			
				
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_sys_date()\">"); //load_lock()get_sys_date()
					out.println("<FORM NAME='Form1' method='post'>"); 
				  					
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Cancel\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			    out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
								
										
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>"); 
					out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance Process - View Payment Vouchers</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  				
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_screen()' value=\"Close\"></td>"); 
								
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  				
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					
					out.println("</table>");  
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
					
					out.println("<tr class=tr_input>");
					out.println("<td width='10%' ><b>From</td>");
					out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='10%'><b>To</td>");
					out.println("<td width='20%'><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='10%'><input class='but_input' type='button' name='BUT_BCODE' value=\"View Vouchers\" onClick=\"make_request()\" style=\"{width:110px;}\" style='{cursor:hand;}'></td>"); 
					out.println("<td width='*%'>&nbsp;</td>");
				  out.println("</tr>");
			
			//== Added by prabash on 15-02-2012====
			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'&gt;</td>");
				out.println("</tr>");
			
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Payee Name</td>");
				out.println("<td width='40%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='15' style=\"{width:250px;}\" size='15'onBlur='client_help()' >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
					out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Payment No</td>");
				out.println("<td><input type=text name='TXT_PAYMENT_NO' class='txt_input'  style=width:150px onBlur='debit_note_help()'>");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"debit_note_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");

				//=====================================
			
			
			
          out.println("</table>"); 
					out.println("<br><br>");
					out.println("<table align='center' width='100%' class='table'>"); 
			    out.println("<tr>");  
			    out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
			    out.println("</tr>"); 
			    out.println("</table>");
					
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
  
			}
			else if(m_chksql.equals("load_voucher")){
			String m_from_date = req.getParameter("from_date");
			String m_to_date   = req.getParameter("to_date");
			String m_payee   = req.getParameter("payee");		//added by Prabash on 17-02-2012
			String m_pamt_no   = req.getParameter("pamt_no");	//added by Prabash on 17-02-2012
			
			
			
				rs = stmt.executeQuery (" SELECT  "+
																" PAYMENT_NO, "+
																" NVL(PAYEE_NAME,'-'), "+
																" TO_CHAR(ENTDATE,'DD-MM-YYYY') "+
																" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
													      " WHERE TO_DATE(TO_CHAR(ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
 																" AND TO_DATE(TO_CHAR(ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																" AND   UPPER( PAYMENT_NO)  LIKE UPPER('%"+m_pamt_no+"%') "+
																" AND   UPPER( PAYEE_NAME)  LIKE UPPER('%"+m_payee+"%') "+
																" ORDER BY ENTDATE DESC ");
 
			
			int j=1;
			boolean more = rs.next();
			
			if(!more){			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width='*%' align='center'><font color='red'>No Data Found....!!</td></tr>");
			out.println("</table>");
			}else{	
			out.println("<hr>");
			out.println("<table align='center' width='100%' class='table' border=0>");
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='5%'>No</td>");
			out.println("<td width='20%'>Payment No</td>");
			out.println("<td width='20%'>Payee Name</td>");
			out.println("<td width='20%'>Entered Date</td>");
			out.println("<td width='20%'align='center'>Payment Voucher</td>");
			out.println("</tr>");	
			}
			
			while(more){
			
			if(j%2==1){
			out.println("<tr class='tr_input'>");
			}else{
			out.println("<tr class='tr_input1'>");
			}
			out.println("<td width='5%'>"+j+"</td>");
			out.println("<td width='20%'>"+rs.getString(1)+"</td>");
			out.println("<td width='20%'>"+rs.getString(2)+"</td>");
			out.println("<td width='20%'>"+rs.getString(3)+"</td>");
			out.println("<td width='20%' align='center'><input type='button' value=\"View\" Style='width:100' name=BTT_"+j+" class='but_input' onclick=\"load_pay_voucher('"+rs.getString(1)+"')\"></td>");
			out.println("</tr>");	
			more = rs.next();
			j=j+1;
			}
				out.println("</table>");
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
