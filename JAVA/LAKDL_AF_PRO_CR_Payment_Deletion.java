//DEVELOED BY : SANDUN   ON 19 01 2009


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_PRO_CR_Payment_Deletion extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			int m_count_payment_no=0;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if(m_chksql.trim().equals("payment_detals")){
				
				String  m_type    = req.getParameter("type").trim();
				String  from_date = req.getParameter("from"); 
				String  to_date   = req.getParameter("to"); 
				String m_payee   = req.getParameter("payee");		//added by Prabash on 17-02-2012
				String m_pamt_no   = req.getParameter("pamt_no");	//added by Prabash on 17-02-2012
				
				out.println("<br>");
				out.println("<br>");
				out.println("<table class=table border='0' width='100%' >");					
				
				int j = 0;   
				
				
				
				rs = stmt.executeQuery (" SELECT A.PAYMENT_NO, "+//1
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//2
					" NVL(A.FINANCE_NO,'-'), "+//3
					" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'), "+//4
					" NVL(A.PAY_AMOUNT,0), "+//5
					" NVL(A.NET_AMOUNT,0), "+//6
					" NVL(A.PAYEE_NAME,'-') "+//7
					" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					" WHERE A.PROCESS_STATUS = '"+m_type+"' "+
					//" AND   A.EFF_VALDATE     >= TO_DATE('"+from_date+"','DD-MM-YYYY') "+ // commented by udara 09-06-2015
					//" AND   A.EFF_VALDATE     <= TO_DATE('"+to_date+"','DD-MM-YYYY') "+ // commented by udara 09-06-2015
					" AND   TRUNC(A.EFF_VALDATE)     >= TO_DATE('"+from_date+"','DD-MM-YYYY') "+ // added by udara 09-06-2015
					" AND   TRUNC(A.EFF_VALDATE)     <= TO_DATE('"+to_date+"','DD-MM-YYYY') "+ // added by udara 09-06-2015
					" AND   UPPER( A.PAYMENT_NO) LIKE UPPER('%"+m_pamt_no+"%') "+ //added by Prabash on 17-02-2012
					" AND   UPPER( A.PAYEE_NAME) LIKE UPPER('%"+m_payee+"%') "); //added by Prabash on 17-02-2012
				
				
				
				boolean more = rs.next();
				
				if(!more){
					out.println("<tr><td width='*%' align='center'>");
					out.println("<font color=red>No Data Found..!</font>");
					out.println("</td></tr>");
				}
				else{
					out.println("<tr class=tr_input>");
					out.println("<td colspan=10 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					out.println("</tr>");	
					out.println("<tr class=pdn_txtpos2 >");					
					out.println("<td  width='15%' align='left'>Payment No</td>");
					out.println("<td  width='15%' align='left'>Contract No</td>");
					out.println("<td  width='25%' align='left'>Payee Name</td>");
					out.println("<td  width='15%' align='left'>Effective Date</td>");
					out.println("<td  width='15%' align='right'>Net Amount</td>");
					out.println("<td  width='5%'  align='center'>Select<INPUT TYPE=\"checkbox\" NAME=\"CHECK_ALL\" VALUE=\"on\" onclick=\"check_all_chkbox()\"  ></td>");
					out.println("</tr>");							
					
					while(more){
						if(j>0 && j%2==1){
							out.println("<tr class=tr_input1 >");
						}
						else{
							out.println("<tr class=tr_input >");
						}
						out.println("<td width='15%' align='left' >"+rs.getString(1) +"</td>");									
						out.println("<td width='15%' align='left' >"+rs.getString(3) +"</td>");
						out.println("<td width='25%' align='left' >"+rs.getString(7) +"</td>");
						out.println("<td width='15%' align='left' >"+rs.getString(4) +"</td>");
						out.println("<td width='25%' align='right' >"+nf.format(rs.getDouble(6))+"</td>");
						out.println("<TD WIDTH='5%' align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+j+" VALUE=\"\" onclick=\"change_val_req("+j+")\"  ></td>");			
						out.println("<input type='hidden' name=HID_PAY_NO_"+j+" value=\""+rs.getString(1)+"\">");
						out.println("</tr>");
						j=j+1;
						more = rs.next();
					}
					out.println("<tr class=tr_input>");				 
					out.println("<td align=right colspan=10><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					out.println("</tr>");
				}
				out.println("<input type=hidden name=hid_no_rec_count value="+j+">");
				out.println("</table>");
				
				
				
				
				
			}
			else if(m_chksql.trim().equals("main_page")){
				
				String m_date_dd = "";
				String m_date_mm = "";
				String m_date_yy = "";
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Finance</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
				
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Finance - Payment Deletion\";"); 
				out.println("}else{");
				out.println("help_box.innerHTML=\"Finance - Payment Deletion - \"+m_val;"); 
				out.println("}");
				out.println("}");
				
				out.println("function change_val_req(row_no){")	;
				out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");	
				out.println("}");	
				
				
				out.println("function validate_data(){"); 
				out.println("return true;"); 
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ ");			
				out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_no_rec_count.value;");
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_PRO_Save_Payment_Deletion';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 				
				out.println("} "); 
				out.println("} "); 
				
				
				
				
				
				out.println("function get_payments(){");			
				out.println("m_type= document.Form1.TXT_TYPE.value;");
				out.println("from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("payee = document.Form1.CLIENT_CODE.value;");//Added by prabash on17-02-2012
				out.println("pamt_no = Form1.TXT_PAYMENT_NO.value;");//Added by prabash on17-02-2012
				
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Payment_Deletion?chksql=payment_detals&payee=\"+payee+\"&pamt_no=\"+pamt_no+\"&from=\"+from_date+\"&to=\"+to_date+\"&type=\"+m_type+\"\";");
				out.println("load_interface(m_url,'NORM');");
				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" m_table.innerHTML = ''; ");
				out.println(" m_table.innerHTML = http_response; ");
				out.println("}");
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance - Payment Deletion - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"\";");  
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 					
				out.println("if(m_val==\"HELP\"){"); 
				out.println(" load_help_msg();");
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  					
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				//out.println("       window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"LAKDL_AF_PRO_CR_Payment_Deletion?chksql=main_page');");
				out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Payment_Deletion?chksql=main_page';"); 
				out.println("		}"); 
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
				rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
				if(rs.next()){
					m_date_dd = rs.getString(1).substring(0,2);
					m_date_mm = rs.getString(1).substring(3,5);
					m_date_yy = rs.getString(1).substring(6,10);
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
				
				
				//=====Added by Prabash on 17-02-2012 ==========================================
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee4','1');");
				out.println("}");
				
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'cre_payee4','2');");
				out.println("}");
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");  
				out.println("}");
				out.println("function payment_no_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[3]");  
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
				out.println("		payment_no_assign(oBj);"); 
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
				
				
				
				out.println("function check_all_chkbox()");
				out.println("{ ");
				out.println("   check_true=false;    ");
				out.println("       if(document.Form1.CHECK_ALL.checked==true)");
				out.println("       { ");
				out.println("          check_true=true;");
				out.println("       }else  ");
				out.println("       { ");
				out.println("    	    check_true=false;");
				out.println("       } ");
				out.println("   for(m=0;m<parseInt(document.Form1.hid_no_rec_count.value);m++) ");
				out.println("   { ");
				out.println("        document.Form1.elements[\"CHK_REQUIRED\"+m].checked=check_true; ");
				out.println("        change_val_req(m); ");	
				//out.println("        alert(document.Form1.elements[\"CHK_REQUIRED\"+m].value); ");
				out.println("   } ");	
				out.println("} ");
				
				out.println("</Script>");
				
				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_sys_date()\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PAYMENT_DELETION\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Deletion </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>"); 
				out.println("<td width='10%'></td>");
				//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");
				out.println("<td width='6%'></td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
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
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>");
				out.println("<td width='10%' ID=VDATE>From</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				
				out.println("<td width='3%' ID=VDATE>To</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");	
				
				
				out.println("</table>");
				
				//== Added by prabash on 15-02-2012====
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 			
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'&gt;</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Payee Name</td>");
				out.println("<td width='30%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='15' style=\"{width:250px;}\" size='15'onBlur='client_help()' >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%'>Payment No</td>");
				out.println("<td><input type=text name='TXT_PAYMENT_NO' class='txt_input'  style=width:150px onBlur='payment_no_help()'>");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\"></td>");
				out.println("</td>");
				out.println("</tr>");
				
				//=====================================		
				
				out.println("<tr class=tr_input>"); 								
				out.println("<td width='10%' ><DIV id='DIV_TXT_TYPE' class=div_input>Type</DIV></td>"); 
				
				out.println("<td width='20%'><select name='TXT_TYPE' class=div_input style='width:150' >");
				out.println("<option value='DISBRS' >Cheque Disbursment</option>");
				out.println("<option value='PRINT'  >Cheque Printing</option>");
				out.println("</select></td>");
				
				out.println("<td width='10%'><input type='button' class='but_input' name='MAIN_BBT' value= 'Search' OnClick ='get_payments()'></td>");
				out.println("<td width='50%'>&nbsp;</td>");
				out.println("<td width='*%'>&nbsp;</td>");
				out.println("</tr>");										
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>");
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  	
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				
				out.println("</td></tr>");  
				out.println("</table>");  	
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
				
				
			}
			
			//=========================================================================================================================			
			/*else {
				out.println("Undefined");
			}
			*/
			//out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
