//--
//SCREEN NAME:CREDIT PROCESS - COLLECTION MISF REPORTS 
//CREATED BY:NUWAN DE SILVA 
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Repossession_Report extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_username = m_sn_methods.username; // added by udara 18-03-2014
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt = conn.createStatement ();
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "REPOSSESSION_NO";	
			String m_order_by_type = "ASC";
			
			if(m_chksql.equals("main_page")){ 
				
				
				
				rs = stmt.executeQuery ("SELECT "+
					" TO_CHAR(SYSDATE,'DD'), "+
					"TO_CHAR(SYSDATE,'MM'), "+
					"TO_CHAR(SYSDATE,'YYYY') "+
					"FROM DUAL ");
				
				if(rs.next()){
					m_date_dd=rs.getString(1);
					m_date_mm=rs.getString(2);
					m_date_yy=rs.getString(3);
				}
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Advertistment Offers Process</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("var b_flag=0;");
				out.println("var count=0;");
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\" ){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				
				
				
				out.println("function before_submit(){ "); 
				out.println("		if(validate_data()){"); 
				out.println("ckeck_data();");
				out.println("if(b_flag==0)");
				
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				
				
				out.println("function assign_system_date(){	"); 
				out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
				m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
				out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
				out.println("}	"); 
				
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
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Start+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_value_assign_yard();"); 
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
				out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
				out.println("	}");
				
				out.println("	}	"); //
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
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"1\"){"); 
				out.println("document.Form1.TXT_REPOSSESSION_CODE.value='';");
				out.println("	}");
				out.println("}");
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Vehicle_In_Hand\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection Process - Repossessed Vehicles - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Repossessed Vehicles - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				out.println("function get_vehicle_in_hand(val){");
				//out.println("assignState('M1');");
				out.println("if(validate_data()){");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report?chksql=view&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"&data_val=\"+val;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report?chksql=view&data_val=\"+val;");
				out.println("window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,addressBar=1,status=0,scrollbars=1,resizable=1');");
				
				out.println("}"); 
				out.println("else{"); 
				out.println("alert('Please select a report field to view report')"); 
				out.println("}"); 
				out.println("}");
				
				
				out.println("function check_date(){ ");
				out.println("var date='' ");
				out.println(" if((document.Form1.VAL_DAY.value !=\"\")&&(document.Form1.VAL_MONTH.value !=\"\")&&(document.Form1.VAL_YEAR.value !=\"\")){");
				out.println("  if(checkMonthLength(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){");
				out.println("date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("document.Form1.hid_bank_date.value=date");
				out.println(" }");
				out.println(" }");
				out.println("}");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date='' ");
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
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
				out.println("date=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_bank_date.value=date");
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				out.println("function help_button_View(val) {");
				out.println("if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("get_vehicle_in_hand(document.Form1.hid_bank_date.value);");
				out.println("}");		
				out.println("else");		
				out.println("{");		
				out.println("  VDATE.style.color='red';");
				out.println("}");		
				out.println("}");
				
				out.println("function change_val_receipt_status(obj){")	;
				out.println("if(obj.checked==true){");
				out.println("obj.value='on'");
				out.println("}else if(obj.checked==false){");
				out.println("obj.value='off'");
				out.println("}");
				out.println("}");			
				
				out.println("function help_button_yard() {"); 
				out.println("    Crit = document.Form1.TXT_YARD_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','6',Crit,'m_help_TXT_YARD_CODE_sql','5');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_yard() {"); 
				out.println("    document.Form1.TXT_YARD_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_YARD_NAME.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
				out.println("function makeRequest(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Vehicle_Inventory_Yard_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length==0 &&  document.Form1.TXT_YARD_CODE.value!=\"\"){");
				out.println("     help_button_yard();");
				out.println("			}");
				out.println("			else if(data_vec.length>0 ){");
				out.println("    document.Form1.TXT_YARD_CODE.value=data_vec[0];"); 
				out.println("    document.Form1.TXT_YARD_NAME.value=data_vec[1];"); 
				out.println("			}");
				
				out.println("			}");
				
				out.println("function Val_Change(obj1){");
				out.println("if(obj1.checked==true ){");
				out.println("obj1.value='on'");
				out.println("obj1.checked=true;");
				out.println("}else if(obj1.checked==false){");
				out.println("obj1.value='off'");
				out.println("obj1.checked=false;");
				out.println("}");	
				out.println("}");	
				
				out.println("function count_selected(){ ");
				out.println("count=0;");
				out.println("j=1;");
				
				out.println("if(document.Form1.CHK_REP_NO.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_INV.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_FIN.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_CLIENT.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_VEH.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_FUEL.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_SEIZER.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_CAP.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_ASS.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_VAL.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				out.println("if(document.Form1.CHK_STATUS.checked==true ){");
				out.println("count=count+j;"); 	 
				out.println("}");	
				
				
				out.println("}");	
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\"> "); //load_lock(), header(),add_row() assign_system_date()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_POST_DATED_RECEIPT_GENERATION\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
				out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Repossessed Vehicles</td>"); 
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
				out.println("<br>");  		
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' ID=VDATE>Date as at *</td>");
				out.println("<td width='18%' ><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='20%' ><input class='but_input' type='button' name='BUT_VIEW' STYLE=\"{width:110px;}\" value=\"View Report\" onClick=\"help_button_View()\"></td>"); 
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				
				out.println("</table>"); 
				
				out.println("<br><br>"); 
				
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			//----------------------------------------------------------------------------------------------------------------------
			else if(m_chksql.equals("view")){		
				String m_date="";
				
				m_sort_column   = "REPOSSESSION_NO";	
				m_order_by_type = "ASC";
				
				
				m_date=req.getParameter("data_val");
				
				if(m_date.equals("")){
					m_date="";
				}
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				
				/*	rs = stmt.executeQuery ("SELECT DISTINCT "+
					"	A.INVENTORY_NO INVENTORY_NO, "+  //1
					"	A.FINANCE_NO FINANCE_NO, "+ //2
					"	B.CLIENT_CODE CLIENT_CODE, "+ //3
					"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+ //4
					"	B.VEHICLE_NO VEHICLE_NO, "+ //5
					"	A.SEIZER_CODE SEIZER_CODE, "+ //6
					"	nvl("+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'-') SEIZER_NAME, "+ //7
					" DECODE(B.COMPLETED_OFFER_NO,'','Entered','Disposed') APP_STATUS , "+
					"	A.REPOSSESSION_NO REPOSSESSION_NO, "+ //9
					"	NVL(TO_CHAR(A.TRN_DATE,'DD-MM-YYYY'),'-') TRN_DATE, "+ //10
					"	NVL(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'-') ENT_DATE, "+ //11
					"	NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-') EFF_VAL_DATE, "+ //12
					"	C.APPLICATION_NO APPLICATION_NO, "+ //13
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',C.APPLICATION_NO),0) ASSET_COUNT,   "+ //14
					"	NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',C.APPLICATION_NO),0) VALUATION_COUNT,  "+ //15
					"	NVL("+m_schema_name+".AF_CO_GET_CAP_OUT_STD_AMT(C.FINANCE_NO),0) CAPITAL_OUTSTANDING  "+ //16
					","+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE "+
					"	FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
					" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
					"	WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND A.FINANCE_NO=C.FINANCE_NO AND  "+
					"	A.ACTIVE_STATUS='Y' AND "+
					" TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
					" AND D.APPLICATION_NO=C.APPLICATION_NO "+
			" AND D.INVOICE_NO=A.PRO_INVOICE_NO "+
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
					*/
				
				
				// commented by udara 11-03-2014
				/*
				
					rs = stmt.executeQuery 
					//out.println
					("SELECT  "+
					" FINANCE_NO, "+ //1
					" REPOSSESSION_NO, "+ //2
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)) CLIENT_CODE, "+ //3
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO))) CLIENT_NAME, "+ //4
					" SEIZER_CODE, "+ //5
					" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(SEIZER_CODE) SEIZER_NAME, "+ //6
					" ENGINE_NO, "+ //7
					" CHASSIS_NO, "+ //8
					" REG_NO , "+ //9
					" LETTER_VALIDITY_PERIOD, "+ //10
					" INVOICE_AMOUNT, "+ //11
					" VEHICLE_INVENTORY_STATUS, "+ //12
					" INVENTORY_NO, "+ //13
					" TRN_DATE, "+ //14
					" TO_CHAR(TRN_DATE,'DD-MM-YYYY') REPOSSESSED_DATE , "+ //15
					" A.ACTIVE_STATUS, "+ //16
					" TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY') EFF_VAL_DATE, "+ //17
					" PRO_INVOICE_NO, "+ //18
					" LETTER_GEN_STATUS, "+ //19
					" LETTER_GEN_DATE, "+ //20
					" LETTER_RENEWAL_COUNT, "+ //21
					" REPOSSESS_OFFICER, "+ //22
					" REPOSSESS_TYPE, "+ //23
					" "+m_schema_name+".AF_CO_GET_CLIENT_ADD1("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO))) ||','|| "+m_schema_name+".AF_CO_GET_CLIENT_ADD2("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO))) ADDRESS, "+//24
					" "+m_schema_name+".AF_CO_GET_REPOS_ARR_COUNT(FINANCE_NO) ARREARS_RENTALS ,"+//25
					" "+m_schema_name+".AF_CO_GET_REPOS_ARR(FINANCE_NO) ARR_AMOUNT "+//26
					
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" WHERE A.PRO_INVOICE_NO=B.INVOICE_NO "+
					" AND A.ACTIVE_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
					
					*/
				
				// added by udara 11-03-2014
				
				rs = stmt.executeQuery 
					//out.println
					("SELECT  "+
					" A.FINANCE_NO, "+ //1
					" A.REPOSSESSION_NO, "+ //2
					" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) CLIENT_CODE, "+ //3
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))) CLIENT_NAME, "+ //4
					" A.SEIZER_CODE, "+ //5
					" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+ //6
					" B.ENGINE_NO, "+ //7
					" B.CHASSIS_NO, "+ //8
					" B.REG_NO , "+ //9
					" A.LETTER_VALIDITY_PERIOD, "+ //10
					" A.INVOICE_AMOUNT, "+ //11
					" A.VEHICLE_INVENTORY_STATUS, "+ //12
					" A.INVENTORY_NO, "+ //13
					" A.TRN_DATE, "+ //14
					" TO_CHAR(A.TRN_DATE,'DD-MM-YYYY') REPOSSESSED_DATE , "+ //15
					" A.ACTIVE_STATUS, "+ //16
					" TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY') EFF_VAL_DATE, "+ //17
					" A.PRO_INVOICE_NO, "+ //18
					" A.LETTER_GEN_STATUS, "+ //19
					" A.LETTER_GEN_DATE, "+ //20
					" A.LETTER_RENEWAL_COUNT, "+ //21
					" A.REPOSSESS_OFFICER, "+ //22
					" A.REPOSSESS_TYPE, "+ //23
					" "+m_schema_name+".AF_CO_GET_CLIENT_ADD1("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))) ||','|| "+m_schema_name+".AF_CO_GET_CLIENT_ADD2("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))) ADDRESS "+//24
					" ,A.ACTIVE_STATUS ACTIVE_STATUS  "+
					//" "+m_schema_name+".AF_CO_GET_REPOS_ARR_COUNT(A.FINANCE_NO) ARREARS_RENTALS ,"+//25//[COMMENTED BY MILINDA ON 10-08-2020]
					//" "+m_schema_name+".AF_CO_GET_REPOS_ARR(A.FINANCE_NO) ARR_AMOUNT "+//26 // commented by udara 18-03-2014
					//" "+m_schema_name+".AF_CO_GET_ARREAS_PERIOD_NEW(A.FINANCE_NO,'"+m_username+"') ARR_AMOUNT "+// commented by udara 27-05-2014 26 added by udara 18-03-2014
					//" "+m_schema_name+".AF_GET_TOTAL_ARREARS(A.FINANCE_NO,'"+m_date+"','"+m_username+"') ARR_AMOUNT "+ // 26 added by udara 27-05-2014//[COMMENTED BY MILINDA ON 10-08-2020]
					" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
					" ,"+m_schema_name+".AF_CO_MAS_MAKE C, "+
					" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+
					" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F, "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL G "+
					
					" WHERE A.PRO_INVOICE_NO=B.INVOICE_NO "+
					//" AND A.ACTIVE_STATUS='Y' "+//[COMMENTED BY MILINDA ON 10-08-2020]
					" AND TO_DATE(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					
					" AND A.FINANCE_NO=E.FINANCE_NO  "+
					" AND B.APPLICATION_NO=E.APPLICATION_NO AND "+ 
					//[COMMENTED ND ADDED BELOW BY MILINDA]
					/*" (C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+
					" (SELECT "+
					" MAKE_CODE ,ITEM_SUB_CAT "+
					" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE "+
					" MODEL_CODE IN ( "+ 
					" SELECT "+
					" MODEL_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+ 
					" WHERE INVOICE_NO=B.INVOICE_NO "+ 
					" AND ACTIVE_STATUS='Y' )) "+*/
					" C.MAKE_CODE = G.MAKE_CODE "+ //[ADDED BY MILINDA ON 10-08-2020]
					" AND F.ITEM_SUB_CAT = G.ITEM_SUB_CAT "+//[ADDED BY MILINDA ON 10-08-2020]
					" AND B.MODEL_CODE = G.MODEL_CODE "+ //[ADDED BY MILINDA ON 10-08-2020]
					" AND B.ACTIVE_STATUS='Y'  "+
					" AND D.SUB_CODE=B.SUB_MODEL_CODE "+
					
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				// end by udara 11-03-2014
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Repossession_Report?chksql=view&data_val="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				out.println("		window.location.href=m_url;");
				out.println("}");
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection Process - Repossessed Vehicles - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection Process - Repossessed Vehicles - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<FORM NAME='Form1'>"); 
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
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Repossessed Vehicles</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td colspan=14 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(\"Top\");'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Reposession No  '   onclick=sort_data('REPOSSESSION_NO') >Reposession No</td>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Finance No  '    	 onclick=sort_data('FINANCE_NO') >Finance No</td>");
				//out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Reposession No  '   onclick=sort_data('REPOSSESSION_NO') >Reposession No</td>");//modified by Sandun on 12-08-2008
				out.println("<td  width='15%'  style= cursor:hand; title='Click here to sort by - Client Name  '     onclick=sort_data('CLIENT_NAME') >Client Name</td>");
				out.println("<td  width='15%'  style= cursor:hand; title='Click here to sort by - Client Address  '     onclick=sort_data('CLIENT_NAME') >Address</td>");//SJ on 11-06-2009
				out.println("<td  width='11%'  style= cursor:hand; title='Click here to sort by - Seizer Name  '      onclick=sort_data('SEIZER_NAME') >Seizer Name</td>");
				out.println("<td  width='8%'  style= cursor:hand; title='Click here to sort by - Engine No  '        onclick=sort_data('ENGINE_NO') >Engine No</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Chassiss No  '      onclick=sort_data('CHASSIS_NO') >Chassiss No</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Registration No  '  onclick=sort_data('REG_NO') >Registration No</td>");
				//out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Period  '           onclick=sort_data('REPOSSESSED_DATE') >Repossessed Date</td>");
				out.println("<td  width='6%' style= cursor:hand; title='Click here to sort by - Status '           onclick=sort_data('VEHICLE_INVENTORY_STATUS') >Vehicle Status</td>");//Added by Sandun on 12-08-2008
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Period  '           onclick=sort_data('EFF_VAL_DATE') >Effective Date</td>");
				//out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Period  '           onclick=sort_data('LETTER_VALIDITY_PERIOD') >Period</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Amount  '           onclick=sort_data('INVOICE_AMOUNT') >Amount</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Period  '           onclick=sort_data('REPOSSESSED_DATE') >Repossessed Date</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Status  '           onclick=sort_data('ACTIVE_STATUS') >Status</td>");
				//out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - No of Arrears Rentals  '           onclick=sort_data('ARREARS_RENTALS') >No of Arrears Rentals</td>");
				//out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Amount  '           onclick=sort_data('ARR_AMOUNT') >Arrears Amount</td>");
				out.println("</tr >"); 
				
				int j=0;
				while(rs.next()){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					out.println("<td align='left'>"+rs.getString(2)+"</td>");
					out.println("<td align='left' style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(1)+"')><u>"+rs.getString(1) +"</u></td>");
					out.println("<td align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(3)+"')><u>"+rs.getString(4) +"</u></td>");
					//out.println("<td align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(5)+"')><u>"+rs.getString(6) +"</u></td>");
					out.println("<td align='left'>   "+rs.getString(24) +"</td>");
					out.println("<td align='left'>   "+rs.getString(6) +"</td>");
					out.println("<td align='left'>   "+rs.getString(7) +"</td>");
					out.println("<td align='left'>   "+rs.getString(8) +"</td>");
					out.println("<td align='left'>   "+rs.getString(9) +"</td>");
					//out.println("<td align='left'>   "+rs.getString(15) +"</td>");
					out.println("<td align='left'>   "+rs.getString(12) +"</td>");//Added by Sandun on 12-08-2008
					out.println("<td align='left'>   "+rs.getString(17) +"</td>");
					//out.println("<td align='left'>   "+rs.getString(10) +"</td>");
					out.println("<td align='right'>  "+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td align='center'>   "+rs.getString(15) +"</td>");
					out.println("<td align='center'>   "+rs.getString(25) +"</td>");
					//out.println("<td align='right'>"+rs.getInt(25)+"</td>");			
					//out.println("<td align='right'>  "+nf.format(rs.getDouble(26))+"</td>");			
					j=j+1;
				}		
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=14><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(\"End\");'></td>");
				out.println("</tr>");
				
				out.println("</table >");
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 		
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
