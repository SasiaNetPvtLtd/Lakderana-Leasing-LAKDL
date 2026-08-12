//--
//SCREEN NAME:Credit Process - Cheque Printing/Disburse
//CREATED BY :DELANJALI	
//DATE/TIME  :25-01-2007
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_cheque_printing_details_dis_new extends javax.servlet.http.HttpServlet { 
	Connection conn;
	ServletOutputStream out = null;
	Statement stmt;//,stmt1,stmt2;
	public ResultSet rs;//,rs1,rs2;
	java.text.NumberFormat nf,nf1;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
      String m_username = m_sn_methods.username;
			String m_chksql = req.getParameter("chksql");
			String m_chksql1 = req.getParameter("chksql2");
			String m_head="";
			String m_date = "";
		
 			String  _m_sys_date_dd ="",_m_sys_date_mm ="",_m_sys_date_yy ="";

			stmt=conn.createStatement();
			//stmt1=conn.createStatement();

			String fschema_name = m_sn_methods.schema_name;

			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);

			if (m_chksql1.equals("DISBURSE")){
			m_head="Disbursement";
			}
			
			String m_sort_column   = "PAYMENT_NO";	
			String m_order_by_type = "DESC";
			
		
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
		

			if(m_sort_column==null){
			m_sort_column="PAYMENT_NO";	
			}

			if(m_order_by_type==null){
			m_order_by_type = "DESC";
			}
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			boolean more_sys = rs.next();
			if(more_sys){
			_m_sys_date_dd = rs.getString(1);
			_m_sys_date_mm = rs.getString(2);
			_m_sys_date_yy = rs.getString(3);
			m_date         = rs.getString(4);
			}


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Cheque "+m_head+" - New</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var st_val='"+m_chksql+"'");
			out.println("var st_val1='"+m_chksql1+"'");
			out.println("var chk_chng=0");
			out.println("var chk_stat=0");
			out.println("var m_order_by_type='DESC'");
			
			// CHATURA

			out.println("function before_submit(){ "); 
			out.println("alert(TXT_PAYMENT_NO_0)");
			out.println("alert(TXT_DIS_BY_0)");
			out.println("alert(TXT_DIS_TO_0)");
			out.println("alert(TXT_ID_NO_0)");
			out.println("alert(TXT_CHEQUE_NO_0)");
			out.println("alert(TXT_REF_NO_0)");
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("if(document.Form1.elements[chk].checked==false){"); 
			out.println("chk_chng=0");
			out.println("} ");
			out.println("else if (document.Form1.elements[chk].checked==true){");
			out.println("chk_chng=1");
		//=============Added by Chandana on 04/06/2007=================
		  
		  out.println("  txt_cheque=\"TXT_CHEQUE_NO_\"+d;"); 
      out.println("  txt_dis_to=\"TXT_DIS_TO_\"+d;"); 
			out.println("  txt_id_no=\"TXT_ID_NO_\"+d;"); 
			out.println("  txt_dis_by=\"TXT_DIS_BY_\"+d;");
			
			out.println("  txt_dis_dd=\"TXT_DIS_DATE_DD_\"+d;");
			out.println("  txt_dis_mm=\"TXT_DIS_DATE_MM_\"+d;");
			out.println("  txt_dis_yy=\"TXT_DIS_DATE_YY_\"+d;");
			
			out.println("if( (document.Form1.elements[txt_dis_yy].value==\"\") || (document.Form1.elements[txt_dis_mm].value==\"\") || (document.Form1.elements[txt_dis_dd].value==\"\")|| (document.Form1.elements[txt_cheque].value==\"\")||(document.Form1.elements[txt_dis_to].value==\"\")||(document.Form1.elements[txt_id_no].value==\"\")||(document.Form1.elements[txt_dis_by].value==\"\")){"); 
     	out.println("chk_stat=2");
			out.println("}else{");
		  out.println("chk_stat=1");
			out.println("}");
		//=================End on 04/06/2007==================	
			out.println("break");
			out.println("		}");		
			out.println("		}");
			out.println("		if(chk_chng==1){"); 
			out.println("		if(chk_stat==1){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_cheque_printing_details_new?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}else{");
			out.println("	alert('Please enter all required fields marked with a * on screen');"); 
			out.println("		}"); 
			
			out.println("} "); 
			out.println("else{");
			out.println("alert('Please select a payment to disburse')");
			out.println("}");
			out.println("} "); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_dis_new?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_dis_new?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("if(st_val1=='PRINT'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque\";"); 
			out.println("}");
			out.println("else if(st_val1=='CANCEL'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_can\";"); 
			out.println("}");
			out.println("else if(st_val1=='DISBURSE'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_dis\";"); 
			out.println("}");
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			


			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance - Cheque "+m_head+" - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance - Cheque "+m_head+" - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.hid_save.value=\"Delete\";");
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
		

			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_dis_new?chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println(" window.location.href=m_url;"); 
			out.println("}");

		
			out.println("function check_change(row) {"); 
			out.println("  chk=\"CHK_APP_\"+row;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"N\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"Y\";"); 
			out.println("chk_chng=0");
			out.println("}");
			out.println("}"); 
			
			
			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");
		  out.println("   if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_DD_\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_MM_\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_YY_\"+m_row].value=v_yy;");
			out.println("  }");	
		  out.println("   if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_DIS_DATE_DD_\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_DIS_DATE_MM_\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_DIS_DATE_YY_\"+m_row].value=v_yy;");
			out.println("  }");	
	    out.println("check_date(m_row);");			
			out.println("}");				

			
			out.println("function check_date_from(ln){ ");
			out.println("ind_dd='TXT_POST_DATE_DD_'+ln;");
			out.println("ind_mm='TXT_POST_DATE_MM_'+ln;");
			out.println("ind_yy='TXT_POST_DATE_YY_'+ln;");
			out.println("  if(document.Form1.elements[ind_dd].value!=\"\" && document.Form1.elements[ind_mm].value!=\"\" && document.Form1.elements[ind_yy].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println(" }");

			out.println("function check_date_dis_from(ln){ ");
			out.println("ind_dd1='TXT_DIS_DATE_DD_'+ln;");
			out.println("ind_mm1='TXT_DIS_DATE_MM_'+ln;");
			out.println("ind_yy1='TXT_DIS_DATE_YY_'+ln;");
			out.println("  if(document.Form1.elements[ind_dd1].value!=\"\" && document.Form1.elements[ind_mm1].value!=\"\" && document.Form1.elements[ind_yy1].value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd1],document.Form1.elements[ind_mm1],document.Form1.elements[ind_yy1]);");
			out.println(" }");
			out.println(" }");
			
			out.println("function check_date(line){");
			out.println("m_sys_date =\""+m_date+"\"; ");
			out.println("dis_dd='TXT_DIS_DATE_DD_'+line;");
			out.println("dis_mm='TXT_DIS_DATE_MM_'+line;");
			out.println("dis_yy='TXT_DIS_DATE_YY_'+line;");
			out.println("m_dis_date = document.Form1.elements[dis_dd].value+'-'+document.Form1.elements[dis_mm].value+'-'+document.Form1.elements[dis_yy].value");
			out.println("var dis_date = new Date(m_dis_date);");
			out.println("var sys_date = new Date(m_sys_date);");
			out.println("if(dis_date>sys_date){");
			out.println("alert('Disbursement Date cannot be greater than System Date..!');");
			out.println("document.Form1.elements[dis_dd].value=\""+_m_sys_date_dd+"\";");
			out.println("document.Form1.elements[dis_mm].value=\""+_m_sys_date_mm+"\";");
			out.println("document.Form1.elements[dis_yy].value=\""+_m_sys_date_yy+"\";");
			out.println(" }");
			out.println(" }");
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Cheque "+m_head+" - New </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\" ></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
		
			out.println("<table align='center' width='100%' class='table' borer=\"1\">"); 
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<br>");
			int i=0;		
			int x=0;	
			
		/*rs= stmt.executeQuery
		//out.println
		("SELECT C.APPLICATION_NO APPLICATION_NO, "+
															"A.PAYMENT_NO PAYMENT_NO, "+
															"A.SUS_REF_NO SUS_REF_NO, "+
															"C.CLIENT_CODE CLIENT_CODE, "+
															""+fschema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
															"B.VENDOR_CODE VENDOR_CODE, "+
															""+fschema_name+".af_co_get_vendor_name(B.VENDOR_CODE) VENDOR_NAME, "+
															"A.SETTLE_MODE SETTLE_MODE, "+
															"A.PAY_AMOUNT PAY_AMOUNT, "+
															"TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+
															"TO_CHAR(A.MOD_DATE,'dd-mm-yyyy') PRINT_DATE, "+
															"D.REF_NO REF_NO "+
															"FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
															""+fschema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
															""+fschema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
															""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D "+
															"WHERE A.SUS_REF_NO=D.SUS_REF_NO "+
															"AND D.REF_NO=B.INVOICE_NO "+
															"AND B.APPLICATION_NO=C.APPLICATION_NO "+
														//	"AND C.APPLICATION_STATUS='ACTIVATED' "+
															"AND A.PROCESS_STATUS='PRINT' "+
															"AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') < 7 "+
														
															"UNION "+
															
															"SELECT DD.APP APPLICATION_NO, "+
															"DD.PAYMENT_NO PAYMENT_NO, "+
															"DD.SUS_REF_NO SUS_REF_NO, "+
                              ""+fschema_name+".af_co_get_client_CODE(DD.APP) CLIENT_CODE, "+  
														  ""+fschema_name+".AF_CO_GET_APP_NAME(DD.APP) NAME, "+ 
															"'', "+
                              "DD.receiver VENDOR_NAME,  "+
															"DD.SETTLE_MODE SETTLE_MODE,  "+
															"DD.PAY_AMOUNT PAY_AMOUNT,  "+
															"DD.EFF_DATE EFF_DATE,  "+
															"DD.PRINT_DATE PRINT_DATE, "+
											//				"DD.LIC_ACC_NO LIC_ACC_NO, "+
															"DD.REF_NO REF_NO "+
															"FROM (SELECT DISTINCT "+fschema_name+".AF_CO_GET_FIN_NO(D.REF_NO) APP, "+
															"A.PAYMENT_NO PAYMENT_NO, "+
															"A.SUS_REF_NO SUS_REF_NO, "+
															"D.receiver receiver,  "+
															"A.SETTLE_MODE SETTLE_MODE,  "+
															"A.PAY_AMOUNT PAY_AMOUNT,  "+
															"TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+ 
															"TO_CHAR(A.MOD_DATE,'dd-mm-yyyy') PRINT_DATE, "+
															"A.LIC_ACC_NO LIC_ACC_NO ,D.REF_NO REF_NO "+
															"FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,  "+
															""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D  "+
															"WHERE A.SUS_REF_NO=D.SUS_REF_NO   "+
															//"AND A.LIC_ACC_NO like ('"+m_account+"%')   "+
															"AND A.PROCESS_STATUS='PRINT'  "+
															"AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') < 7 "+
	                            "AND D.SUSPENSE_ENTRY_TYPE in ('E','L','A'))DD  "+
														  //"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
														" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
														
				


			int j=0;


			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		

			while(rs.next()){
			if(j==0){

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Payment No' onclick=sort_data(\"PAYMENT_NO\")>Payment No</td>"); 
			out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Sus Ref No' onclick=sort_data(\"SUS_REF_NO\")>Sus Ref No</td>"); 
			out.println("<td width=\"6%\" align=\"right\" style=cursor:hand;title='Click here to sort by - Paid Amount' onclick=sort_data(\"PAY_AMOUNT\") >Amount</td>"); 
			out.println("<td width=\"8%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Ref No  '    onclick=sort_data('REF_NO')>Ref No</td>"); 
			out.println("<td width=\"6%\" align=\"left\"  >Cheque*</td>");					
			out.println("<td width=\"8%\" align=\"left\" >Disbursed To*</td>");
			out.println("<td width=\"6%\" align=\"left\">ID No*</td>");
			out.println("<td width=\"6%\" align=\"left\" >By*</td>"); 
			out.println("<td width=\"11%\" align=\"left\"  >Disburse Date*</td>"); 
 
			out.println("<td width=\"11%\" align=\"left\"  >Posted Date</td>"); 
			out.println("<td width=\"1%\" align=\"center\" >&nbsp</td>"); 
			out.println("</tr>");		

			}
			
						
			if(j>0 && j%2==1){
    	out.println("<tr class=tr_input1 >");
			}
			else{
			
    	out.println("<tr class=tr_input >");
			}

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(2)+"\" ></td>"); 
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs.getDouble(9))+"</td>"); 
			if(rs.getString(12).substring(0,2).equals("PI")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"<input type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(12)+"\" ></td>"); 
			}
			if(rs.getString(12).substring(0,2).equals("AD")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_advertistment_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"<input type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(12)+"\" ></td>"); 
			}


			if(rs.getString(12).substring(0,2).equals("LN")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_legal_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"<input type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(12)+"\" ></td>"); 
			}

			if(rs.getString(12).substring(0,2).equals("RP")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_repossession_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"<input type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(12)+"\" ></td>"); 
			}

			out.println("<td align=\"left\"><input class=\"txt_input2\" style=\"{width:70px}\" type=\"text\" maxlength='10' size='20' name=TXT_CHEQUE_NO_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input2\" type=\"text\" maxlength='250' size='20' name=TXT_DIS_TO_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input style=\"{text-align:left}\" class=\"txt_input2\" style=\"{width:70px}\" type=\"text\" maxlength='10' size='20' name=TXT_ID_NO_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input2\" style=\"{width:70px}\" type=\"text\" maxlength='10' size='20' name=TXT_DIS_BY_"+j+" value=\"\" ></td>"); 
			
			out.println("<td align=\"left\"><input class=\"txt_input5\" style=\"{width:20px}\" type=\"text\" name=TXT_DIS_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_dis_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:20px}\" name=TXT_DIS_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_dis_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:30px}\" name=TXT_DIS_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_dis_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")><U>   Calendar</a> ");	

			out.println("<td align=\"left\"><input class=\"txt_input5\" style=\"{width:20px}\" type=\"text\" name=TXT_POST_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:20px}\" name=TXT_POST_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:30px}\" name=TXT_POST_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")><U>   Calendar</a> ");	
			

			out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\" ></td>");
			out.println("</tr>");		
			j=j+1;
			
			}
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
			
			
			*/
			
		rs= stmt.executeQuery
		(" SELECT A.PAYMENT_NO PAYMENT_NO "+//1
		" , A.SETTLE_MODE SETTLE_MODE "+ //2007
		//" , A.PAY_AMOUNT PAY_AMOUNT "+ //3
		" , A.net_amount net_amount "+ //3
		" , TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE "+ //4
		" , TO_CHAR(A.MOD_DATE,'dd-mm-yyyy') PRINT_DATE "+ //5
		" , NVL(A.PAYEE_NAME,'-') "+ //6
		" ,NVL(A.CHEQUE_NO,'-') "+   //7
		" ,NVL(A.FINANCE_NO,'-') "+  //8
		" FROM LAKDL.AF_RE_PRO_SETTLMENT_PAYMENT A "+
		" WHERE A.PROCESS_STATUS='PRINT' ");
	//	" AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') < 117 ");
		
		
					int j=0;


			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		

			while(rs.next()){
			if(j==0){

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"10%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Payment No' onclick=sort_data(\"PAYMENT_NO\")>Payment No</td>"); 
			out.println("<td width=\"5%\" align=\"left\" >Finance no</td>");
			//out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Sus Ref No' onclick=sort_data(\"SUS_REF_NO\")>Sus Ref No</td>"); 
			out.println("<td width=\"7%\" align=\"right\" style=cursor:hand;title='Click here to sort by - Paid Amount' onclick=sort_data(\"PAY_AMOUNT\") >Amount</td>"); 
			//out.println("<td width=\"8%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Ref No  '    onclick=sort_data('REF_NO')>Ref No</td>"); 
			out.println("<td width=\"10%\" align=\"left\" >Payee Name</td>");
			out.println("<td width=\"8%\" align=\"left\"  >Cheque No*</td>");					
			out.println("<td width=\"15%\" align=\"left\" >Disbursed To*</td>");
			out.println("<td width=\"9%\" align=\"left\">ID No*</td>");
			out.println("<td width=\"9%\" align=\"left\" >By*</td>"); 
			out.println("<td width=\"15%\" align=\"left\"  >Disburse Date*</td>"); 
 			out.println("<td width=\"15%\" align=\"left\"  >Posted Date</td>"); 
			out.println("<td width=\"1%\" align=\"center\" >&nbsp</td>"); 
			out.println("</tr>");		

			}
			
						
			if(j>0 && j%2==1){
    	out.println("<tr class=tr_input1 >");
			}
			else{
			
    	out.println("<tr class=tr_input >");
			}

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			out.println("<td align=\"left\">"+rs.getString(8)+"</td>"); 
			out.println("<td align=\"right\">"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td align=\"right\">"+rs.getString(6)+"</td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input2\" style=\"{width:80px}\" type=\"text\" maxlength='10' size='20' name=TXT_CHEQUE_NO_"+j+" value="+rs.getString(7)+" ></td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input2\" style=\"{width:130px}\" type=\"text\" maxlength='250' size='20' name=TXT_DIS_TO_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input style=\"{text-align:left}\" class=\"txt_input2\" style=\"{width:70px}\" type=\"text\" maxlength='10' size='20' name=TXT_ID_NO_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input2\" style=\"{width:80px}\" type=\"text\" maxlength='10' size='20' name=TXT_DIS_BY_"+j+" value=\"\" ></td>"); 
			out.println("<td align=\"left\"><input class=\"txt_input5\" style=\"{width:20px}\" type=\"text\" name=TXT_DIS_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value='"+_m_sys_date_dd+"' onblur=\"check_date_dis_from("+j+"),check_date("+j+")\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:20px}\" name=TXT_DIS_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value='"+_m_sys_date_mm+"' onblur=\"check_date_dis_from("+j+"),check_date("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:30px}\" name=TXT_DIS_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value='"+_m_sys_date_yy+"' onblur=\"check_date_dis_from("+j+"),check_date("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")><U>   Calendar</a> ");	
			out.println("<td align=\"left\"><input class=\"txt_input5\" style=\"{width:20px}\" type=\"text\" name=TXT_POST_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:20px}\" name=TXT_POST_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" style=\"{width:30px}\" name=TXT_POST_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")><U>   Calendar</a> ");	
			out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\" ></td>");
			out.println("</tr>");		
			j=j+1;
			
			}
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");

			
			
			
		
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		
				
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
				if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
				
		    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
