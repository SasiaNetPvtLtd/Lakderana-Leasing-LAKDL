//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_PRO_Monthly_Statement
//CREATED BY: nuwan de silva 
//DATE/TIME:29-11-2007
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_PRO_Monthly_Statement extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt,stmt1,stmt3,stmt2,stmt_sys;
	public ResultSet rs,rs2,rs3;
	
	

	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			String m_orient_name="",m_orient_add1="",m_orient_add2="",m_orient_city_name="",m_orient_tel_no="",m_orient_fax_no="",m_orient_vat_rate="",m_LAKDL_vat_no="",m_contact_person="";
			String m_date="",m_vat_precentage="";
			String m_c_code="",m_add1="",m_add2="",m_name="",m_city_desc="",m_vat_reg_no="",m_Letter_date="",m_Letter_date_month="",m_Letter_date_1="",_m_Previous_day="",_m_end_date="",_m_month="" ,_m_date="";
			String m_client_type="";
			
			//Added by Dineth on 28-04-2009
			String m_finance_no_1="";
			String m_application_no_1="";
			double m_tot_no_rentals=0;
			double m_paid_rentals=0;
			double m_fut_rentals=0;
			double m_fut_rec=0;
			double m_total_1=0;
			double m_total_2=0;
			double m_total_3=0;
			//End by Dineth on 28-04-2009
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			String m_sys_date_dd="",m_sys_date_mm="",m_sys_date_yy="";
			
			stmt1= conn.createStatement ();//Added by Dineth on 28-04-2009
			stmt3= conn.createStatement ();//Added by Dineth on 28-04-2009
			if(m_chksql.equals("main_page")){ 
			stmt_sys = conn.createStatement ();
			rs = stmt_sys.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL "); 
			if(rs.next()){
			m_sys_date_dd=rs.getString(1);m_sys_date_mm=rs.getString(2);m_sys_date_yy=rs.getString(3);
			}
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Application Status Change</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_client_code();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("			document.Form1.TXT_CLIENT_CODE.value=data_vec[0];");
			out.println("			document.Form1.TXT_CLIENT_NAME.value=data_vec[1];");
			out.println("			}");
			
			out.println("}");
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_client_code&data_val=\"+obj.value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\" ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Save_Application_Status_Change';");  
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
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_APP_Status_change\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Monthly Statment Generation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Monthly Statment Generation - \"+document.Form1.hid_status.value;"); 
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

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(document.Form1.hid_help_type.value);");
			out.println("	}else");
							
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
		  out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_app_no(oBj);"); 
			out.println("}");
											
			out.println("	}"); //end next
			
			out.println("	else{"); 
						out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
						out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 

			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			//-----------------------------------------------------------------------------------------------------------------------------------------
			out.println(""); 
			
			out.println("function clear_data(IfCount) {");
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
			out.println("}");
				  
					out.println("function help_client_code() {"); 
					out.println("document.Form1.hid_help_type.value=\"1\" ");
					//out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
					//out.println("    Sql  = \"m_help_client_code_help\";");//Commented by Dineth on 02-06-2009
					out.println("Crit=document.Form1.TXT_CLIENT_CODE.value+\"@\";");//Added by Dineth on 02-06-2009
					out.println("      Sql  = \"ClientSql_Receipt\";");//Added by Dineth on 02-06-2009
					out.println("    m_sql = Sql;"); 
				  out.println("    m_criteria = Crit"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
		
					out.println("function help_value_assign_app_no() {"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[9];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
					out.println("}"); 
     
			out.println("function get_rental_dates(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Tax_Invoice?chksql=view&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_app_details(val,m_date){");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=tax_invoices&client_code=\"+val;");
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=report_data&m_date=\"+m_date+\"&client_code=\"+val;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			
			
			/*out.println("function Generate_Letter(m_invoice_no,m_date,m_client_code,m_letter_date) {");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=tax_invoices&m_date=\"+m_date+\"&letter_date=\"+m_letter_date+\"&client_code=\"+m_client_code+\"&invoice_no=\"+m_invoice_no;"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=700 ,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
		  out.println("}");	
			*/
			
			out.println("function Generate_Letter(m_client_code,m_letter_date) {");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Monthly_Statement?chksql=tax_invoices&letter_date=\"+m_letter_date+\"&client_code=\"+m_client_code;"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=700 ,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
		  out.println("}");	
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function ckeck_data(){ "); 
			out.println("b_flag=0;");
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_receipts()){"); 
			out.println("alert('No cheques selected');");
			out.println("b_flag=1;");
			out.println("}"); 
						
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
      out.println("}"); 

			out.println("function count_receipts(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			out.println("m_chk_deposit_tmp=\"CHK_RECEIPT\"+i;");
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
					
						
			
			
			out.println("function help_button_View() {");
			
			out.println("if(validate_data()){");
			//out.println("get_app_details(document.Form1.TXT_CLIENT_CODE.value,document.Form1.hid_date.value);");
			out.println("Generate_Letter(document.Form1.TXT_CLIENT_CODE.value,document.Form1.hid_date.value);");
			
		  out.println("}");		
			//out.println("else");		
			//out.println("{");		
			//out.println("  DIV_TXT_CLIENT_CODE.style.color='red';");
			//out.println("}");		

			out.println("}");
			
			out.println("function change_val_receipt_status(obj){")	;
			out.println("if(obj.checked==true){");
			out.println("obj.value='on'");
			out.println("}else if(obj.checked==false){");
			out.println("obj.value='off'");
			out.println("}");
			out.println("}");			
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			out.println("if(checkMonthLength(objDD,objMM,objYY))");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("}");
			
			
						out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
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
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			out.println("function assign_system_date(){");
			out.println("     document.Form1.VAL_DAY.value='"+m_sys_date_dd+"';");
			out.println("     document.Form1.VAL_MONTH.value='"+m_sys_date_mm+"';");
			out.println("     document.Form1.VAL_YEAR.value='"+m_sys_date_yy+"';");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
      out.println("}");


			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\"> "); //load_lock(), header(),add_row() //assign_system_date()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_PRO_COLLECTION_MONTHLY_STATEMENT\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=0>"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Monthly Statment Generation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='20' style={width:150px;} size='20' onblur=\"assignState('M2'),makeRequest(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"...\" onClick=\"help_client_code()\"></td>"); //m_help_TXT_APPLICATION_NO
			//out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='20' style={width:350px;} size='20' onblur=\"\" disabled ></TD>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>");
			out.println("<td width='30%'ID=VDATE>Date As At *</td>");
			out.println("<td width='40%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
			out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("<input class='but_input' type='button' name='BUT_VIEW' style=\"width: 110px\" value=\"Monthly Statement\" onClick=\"help_button_View()\"></td>"); 
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_report'></DIV></td>");
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
			
		
		else if(m_chksql.equals("tax_invoices")){		
		
			String m_client_code=req.getParameter("client_code").trim();
			String m_value_date="";
			//String m_value_date=req.getParameter("m_date").trim();
			String m_invoice_no="";
			//String m_invoice_no=req.getParameter("invoice_no").trim();
			
			String _m_letter_date=req.getParameter("letter_date").trim();
			m_value_date=_m_letter_date;
			
			//Vector _m_vector_finance_no =new Vector();
			
			
			
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			double    m_amt_rps=0;//Added by Dineth on 07-05-2009			
			double		_m_tot_net_rental=0;
			double		_m_tot_vat_rental=0;
			double		_m_tot_gross_rental=0;
			int m_count=0;
					
			  rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY'),TO_CHAR(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1)),'DD/MM/YY') ,TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month YYYY') , TO_CHAR(LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')),'DD/MM/YY') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'Month') , TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL "); //,TO_CHAR(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),'DD-MM-YYYY')
				boolean more = rs.next();
				if(more){
				m_Letter_date=rs.getString(1);
				_m_Previous_day=rs.getString(2);
				m_Letter_date_month=rs.getString(3);
				_m_end_date=rs.getString(4);
				_m_month=rs.getString(5);
				_m_date=rs.getString(6);
				}
				
				rs = stmt.executeQuery(" SELECT "+
				" COMPANY_NAME, "+
				" ADDRESS1, "+
				" ADDRESS2, "+
				" CITY, "+
				" TEL_NO, "+
				" FAX_NO,  "+
				" VAT_RATE, "+
				" VAT_REG_NO "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				more = rs.next();		
				
				if(more)
				{
				m_orient_name=rs.getString(1);
				m_orient_add1=rs.getString(2);
				m_orient_add2=rs.getString(3);
				m_orient_city_name=rs.getString(4);
				m_orient_tel_no=rs.getString(5);
				m_orient_fax_no=rs.getString(6);
				m_vat_precentage=rs.getString(7);			
				m_LAKDL_vat_no=rs.getString(8);			
				}
        rs.close(); 
					
				rs = stmt.executeQuery (	" SELECT "+
				" CLIENT_CODE, "+
				" NVL(FULL_NAME,' ' ), "+
				" NVL(ADDRESS1,' '), "+
				" NVL(ADDRESS2,'-' ), "+
				" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-') CITY_NAME ,"+
				" NVL(VAT_REG_NO,'-') VAT_REG_NO, "+
				" NVL(CONTACT_FOR_PAYMENT,' ' ), "+
				" NVL(CLIENT_TYPE,'-') "+//Added by Dineth on 06-05-2009
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
				" WHERE CLIENT_CODE='"+m_client_code+"' ");
											
				more = rs.next();
				if(more){
				m_c_code=rs.getString(1);
				m_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_desc=rs.getString(5);
				m_vat_reg_no=rs.getString(6);
				//m_contact_person=rs.getString(7);//Commented by Dineth on 06-05-2009
				m_client_type=rs.getString(8);
				}
				rs.close(); 
				//Added by Dineth on 06-05-2009
				if(m_client_type.trim().equals("I")){
				rs = stmt.executeQuery(" SELECT "+
															 " NVL(FIRST_NAME,' ') FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
															 " WHERE CLIENT_CODE='"+m_client_code+"' ");
					if(rs.next()){
						m_contact_person=rs.getString(1);
					}
				}
				else if(m_client_type.trim().equals("C")){
				rs = stmt.executeQuery(" SELECT "+
															 " NVL(CONTACT_FOR_PAYMENT,' ' ) FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
															 " WHERE CLIENT_CODE='"+m_client_code+"' ");
					if(rs.next()){
						m_contact_person=rs.getString(1);
					}
				
				}
				rs = stmt.executeQuery (	" SELECT "+
				" COUNT(VAT_REG_NO) "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
				" WHERE CLIENT_CODE='"+m_client_code+"' AND TO_DATE((TO_CHAR(VAT_REG_DATE,'DD-MM-YYYY')),'DD-MM-YYYY')<=TO_DATE('"+m_value_date+"','DD-MM-YYYY')  ");
				
				more = rs.next();
				
				if(more){
				m_count=rs.getInt(1);
				}

				
				  String Sql_invoice=" SELECT "+
					" A.FINANCE_NO,TO_CHAR(A.DUE_DATE,'DD/MM/YYYY'),NVL(SUM(NET_AMOUNT),0),NVL(SUM(VAT_AMOUNT),0),NVL(SUM(TOTAL_AMOUNT),0),B.APPLICATION_NO,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO),'-') ,"+m_schema_name+".AF_CO_GET_VAT_RATE(B.APPLICATION_NO) ,"+
					" NVL("+m_schema_name+".AF_CO_GET_MAKE(B.APPLICATION_NO),'-') ASSET_DESC "+
					" ,B.CLIENT_CODE,NVL("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(B.TRANSACTION_TYPE,TO_DATE('"+_m_letter_date+"','DD-MM-YYYY')),0) "+//Modified by Dineth on 06-05-2009
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
					" WHERE A.FINANCE_NO=B.FINANCE_NO"+
					" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'),-1))+1)  "+
					" AND   A.DUE_DATE<=LAST_DAY(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'))"+
					//" AND   UPPER(GROUP_INV_NO)=UPPER('"+m_invoice_no+"')"+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND   B.APPLICATION_STATUS='ACTIVATED'"+
					" AND   A.ACTIVE_STATUS='Y' "+
					" AND   A.INVOICE_TYPE='INV_GENER'  "+
					" GROUP BY A.FINANCE_NO,B.APPLICATION_NO,A.DUE_DATE,B.CLIENT_CODE,B.TRANSACTION_TYPE ";//Modified by Dineth on 06-05-2009
				
					String Sql_invoice1=" SELECT DISTINCT FINANCE_NO,APPLICATION_NO,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') FROM "+
															" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE CLIENT_CODE='"+m_client_code+"' "+
															" AND APPLICATION_STATUS='ACTIVATED'";
				
					/*String Sql_invoice1=" SELECT "+
					" A.FINANCE_NO,TO_CHAR(A.DUE_DATE,'DD/MM/YYYY'),NVL(SUM(NET_AMOUNT),0),NVL(SUM(VAT_AMOUNT),0),NVL(SUM(TOTAL_AMOUNT),0),B.APPLICATION_NO,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO),'-') ,"+m_schema_name+".AF_CO_GET_VAT_RATE(B.APPLICATION_NO) ,"+
					" NVL("+m_schema_name+".AF_CO_GET_MAKE(B.APPLICATION_NO),'-') ASSET_DESC "+
					" ,B.CLIENT_CODE,NVL("+m_schema_name+".AF_CO_GET_VAT_ON_RENTAL_PRO(B.TRANSACTION_TYPE,TO_DATE('"+_m_letter_date+"','DD-MM-YYYY')),0) "+//Modified by Dineth on 06-05-2009
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
					" WHERE A.FINANCE_NO=B.FINANCE_NO"+
					//" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'),-1))+1)  "+
					" AND   A.DUE_DATE<=LAST_DAY(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'))"+
					//" AND   UPPER(GROUP_INV_NO)=UPPER('"+m_invoice_no+"')"+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND   B.APPLICATION_STATUS='ACTIVATED'"+
					" AND   A.ACTIVE_STATUS='Y' "+
					" AND   A.INVOICE_TYPE='INV_GENER'  "+
					" GROUP BY A.FINANCE_NO,B.APPLICATION_NO,A.DUE_DATE,B.CLIENT_CODE,B.TRANSACTION_TYPE ";//Modified by Dineth on 06-05-2009
				*/
				//" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
					//_________________________________________________________________________________________________________________
					
					//Added by Dineth on 07-05-2009
					
					rs=stmt.executeQuery(" SELECT NVL(SUM(TOTAL_AMOUNT),0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
					" WHERE A.FINANCE_NO=B.FINANCE_NO"+
					" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'),-1))+1)  "+
					" AND   A.DUE_DATE<=LAST_DAY(TO_DATE('"+_m_letter_date+"','DD-MM-YYYY'))"+
					//" AND   UPPER(GROUP_INV_NO)=UPPER('"+m_invoice_no+"')"+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND   B.APPLICATION_STATUS='ACTIVATED'"+
					" AND   A.ACTIVE_STATUS='Y' "+
					" AND   A.INVOICE_TYPE='INV_GENER' ");
					
					if(rs.next()){
					 m_amt_rps=rs.getDouble(1);
					}
					
					//End by Dineth on 07-05-2009
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					
					if(m_count==1){
					out.println("<tr>");
					out.println("<td><div align=\"center\"><strong>Tax Invoice </strong></div></td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr>");
					out.println("<td><div align=\"center\"><strong>Invoice </strong></div></td>");
					out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<br><br><br><br><br><br><br><br><br><br>"); //SPACE 
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"62%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_name+" </td>");
					out.println("<td width=\"18%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_schema_name+" Vat No </td>");
					out.println("<td width=\"4%\"  class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_LAKDL_vat_no+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">"+m_add1+" </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">Invoice No </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >:</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">"+m_invoice_no+"</td>");
					out.println("</tr>");
					if(!m_add2.equals("-")){
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">"+m_add2+" </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("</tr>");
					}
					if(!m_city_desc.equals("-")){
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">"+m_city_desc+" </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("</tr>");
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					if(m_count==1){
					out.println("<tr>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">Customer VAT No </td>");
					out.println("<td width=\"2%\"  class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"44%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_vat_reg_no+"</td>");
					out.println("<td width=\"18%\" class='rep-body1' style=\"{font-size: 9px;}\">Atten</td>");
					out.println("<td width=\"4%\"  class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_contact_person+" </td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td width=\"2%\"  class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td width=\"44%\" class='rep-body1' style=\"{font-size: 9px;}\">&nbsp;</td>");
					out.println("<td width=\"18%\" class='rep-body1' style=\"{font-size: 9px;}\">Atten</td>");
					out.println("<td width=\"4%\"  class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">"+m_contact_person+" </td>");
					out.println("</tr>");
					}
					
					out.println("<tr>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">Rental due for </td>");
					out.println("<td width=\"2%\" class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"44%\" class='rep-body1'style=\"{font-size: 9px;}\" >"+m_Letter_date_month+" </td>");
					out.println("<td width=\"18%\" class='rep-body1' style=\"{font-size: 9px;}\">Amount in Rupees</td>");
					out.println("<td width=\"4%\" class='rep-body1' style=\"{font-size: 9px;}\">:</td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\">"+nf.format(m_amt_rps)+"</td>");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("<br><br><br><br><br><br>");
					
					
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"15%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>Agreement No </strong></td>");
					out.println("<td width=\"10%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>Due Date </strong></td>");
					out.println("<td width=\"11%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>VAT % </strong></td>");
					out.println("<td width=\"11%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>Net Rental </strong></td>");
					out.println("<td width=\"11%\"  class='rep-body1' style=\"{font-size: 9px;}\"><strong>VAT</strong></td>");
					out.println("<td width=\"11%\"  class='rep-body1' style=\"{font-size: 9px;}\"><strong>Total</strong></td>");
					out.println("<td width=\"15%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>Equipment No </strong></td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\"><strong>Make</strong></td>");
					out.println("</tr>");
					
					rs= stmt.executeQuery (Sql_invoice);
									
					while(rs.next()){
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(1)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(2)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs.getDouble(11))+"%</td>");//Modified by Dineth on 06-05-2009
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					if(rs.getString(7).equals("-")){
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");}
					else{out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+rs.getString(7)+"</td>");}
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='left'>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					_m_tot_net_rental+=rs.getDouble(3);
					_m_tot_vat_rental+=rs.getDouble(4);
					_m_tot_gross_rental+=rs.getDouble(5);
					
					}
					rs.close();
					
					out.println("<tr>");
					out.println("<td class='rep-body1'>&nbsp;</td>");
					out.println("<td class='rep-body1' align='right'>&nbsp;</td>");
					out.println("<td class='rep-body1' align='right'>&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_tot_net_rental)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_tot_vat_rental)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_tot_gross_rental)+"</td>");
					out.println("<td class='rep-body1' align='right'>&nbsp;</td>");
					out.println("<td class='rep-body1' align='right'>&nbsp;</td>");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\">Timely payment would be appriciated.</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >");
					out.println("<tr>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\">This computer generated invoice requires no signatures.</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
										
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"><div align=\"center\"><strong>Statement of Account </strong></div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><div align=\"left\">Gross Rental=G.ren</div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"12%\" class='rep-body1'>&nbsp;</td>");
					out.println("<td width=\"88%\" class='rep-body1'><table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"9%\"  class='rep-body1' style=\"{font-size: 9px;}\"><b>Ref</td>");
					out.println("<td width=\"17%\" class='rep-body1' style=\"{font-size: 9px;}\"><b>G.Ren</td>");
					out.println("<td width=\"8%\"  class='rep-body1' style=\"{font-size: 9px;}\"><b>ODI</td>");
					out.println("<td width=\"14%\" class='rep-body1' style=\"{font-size: 9px;}\"><b>Insurance</td>");
					out.println("<td width=\"15%\" class='rep-body1' style=\"{font-size: 9px;}\"><b>Othe chg </td>");
					out.println("<td width=\"9%\"  class='rep-body1' style=\"{font-size: 9px;}\"><b>Total</td>");
					out.println("<td width=\"16%\" class='rep-body1' style=\"{font-size: 9px;}\"><b>Total Over Due </td>");
					out.println("<td width=\"12%\" class='rep-body1' style=\"{font-size: 9px;}\"><b>No of months arrears </td>");
					out.println("</tr>");
					out.println("</table></td>");
					out.println("</tr>");
					out.println("</table>");
					
					
					rs= stmt.executeQuery (Sql_invoice1);
					String _m_finance_no ="";
					String _m_application_no ="";
					double _m_total_last_month=0;
					double _m_total_receipts=0;
					double _m_bal_g_ren=0;
					double _m_bal_total_charges=0;
					double _m_bal_other_charges=0;
					double _m_bal_insuarance=0;
					double _m_bal_odi_ren=0;
					double _m_total_charges_mont=0;
					double _m_total_charges_month=0;
					double _m_total_over_due=0;
					double _m_total_rental=0;
					double _m_total_payable=0;
					while(rs.next()){
					_m_finance_no     = rs.getString(1);
					_m_application_no = rs.getString(2);
					
					
					rs2= stmt2.executeQuery (" SELECT "+
						" NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) G_REN, "+//1
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) ODI, "+ //2
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','INSURA'),0) INSU, "+ //3
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','OTHER'),0) OTHR, "+ //4
						" NVL("+m_schema_name+".AF_CO_GET_INVOICE_THS_MONTH('"+_m_finance_no+"','"+m_value_date+"'),0) G_THS, "+ //5
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS_THS_MNTH('"+_m_finance_no+"','"+m_value_date+"'),0) ODI_THS, "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','RENT'),0) RENT, "+ //7
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','INSUARANCE'),0) INSUARANCE, "+ //8
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','OTHER'),0) OTHER ,"+ //9
						" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO('"+_m_finance_no+"','"+m_value_date+"'),0) AGE ,"+ //10
						" NVL("+m_schema_name+".AF_CO_MAS_ASSET_DESC('"+_m_application_no+"'),'-') ASSET_DESC ,"+ //11
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','ODI'),0) RENT, "+ //12
						" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT('"+_m_finance_no+"','"+m_value_date+"','PREV'),0), "+//13 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','INSURA','PREV'),0), "+//14 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','ODI','PREV'),0), "+//15 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','OTHER','PREV'),0), "+//16 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','INSURA','THIS'),0), "+//17 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','ODI','THIS'),0), "+//18 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','OTHER','THIS'),0), "+//19 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT('"+_m_finance_no+"','"+m_value_date+"','THIS'),0) "+//20 Added by Dineth on 06-05-2009
						" FROM DUAL ");
						
					rs2.next();
					
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td  class='rep-body1' style=\"{font-size: 9px;}\" ><b>"+_m_finance_no+"&nbsp;&nbsp;&nbsp;"+rs2.getString(11)+"&nbsp;&nbsp;&nbsp;"+rs.getString(3)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >");
					
					//________ Begining Balance _____________________________________________
					out.println("<tr>");
					out.println("<td width=\"12%\" class='rep-body1' style=\"{font-size: 9px;}\">Balance "+_m_Previous_day+" </td>");
					out.println("<td width=\"8%\"  class='rep-body1' style=\"{font-size: 9px;}\">-</td>");
					out.println("<td width=\"15%\" class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(13))+"</td>");
					out.println("<td width=\"7%\"  class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(2))+"</td>");
					out.println("<td width=\"12%\" class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(14))+"</td>");//Modified by Dineth on 06-05-2009
					out.println("<td width=\"14%\" class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(16))+"</td>");//Modified by Dineth on 06-05-2009
					_m_total_last_month=rs2.getDouble(13)+rs2.getDouble(2)+rs2.getDouble(14)+rs2.getDouble(16);
					out.println("<td width=\"7%\"  class='rep-body1' style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_total_last_month)+"</td>");
					out.println("<td width=\"14%\" class='rep-body1' style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("<td width=\"11%\" class='rep-body1' style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("</tr>");
					//_______________________________________________________________________
					
					//__________ Charges ____________________________________________________
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+m_Letter_date_month+"-Charges </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >-</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(20))+"</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(18))+"</td>");
					//_m_total_charges_month=rs2.getDouble(5)+rs2.getDouble(6);
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(17))+"</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(19))+"</td>");
					_m_total_charges_month=rs2.getDouble(20)+rs2.getDouble(18)+rs2.getDouble(17)+rs2.getDouble(19);
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_total_charges_month)+"</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("</tr>");
					//_____________________________________________________________________
					
					//______________Receipts ______________________________________________
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >Receipts-"+m_Letter_date_month+"</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" >&nbsp;</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>("+nf.format(rs2.getDouble(7))+")</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>("+nf.format(rs2.getDouble(12))+")</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>("+nf.format(rs2.getDouble(8))+")</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>("+nf.format(rs2.getDouble(9))+")</td>");
					_m_total_receipts=rs2.getDouble(7)+rs2.getDouble(8)+rs2.getDouble(9);
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>("+nf.format(_m_total_receipts)+")</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("</tr>");
					//______________________________________________________________________
					
					//______________End Blalance _______________________________________________
					
					_m_bal_g_ren=(rs2.getDouble(13)+rs2.getDouble(20) ) - rs2.getDouble(7);//+rs2.getDouble(5)
					_m_bal_odi_ren=rs2.getDouble(2)+rs2.getDouble(6)+rs2.getDouble(18)-rs2.getDouble(12);
					_m_bal_insuarance=rs2.getDouble(14)+rs2.getDouble(17) - rs2.getDouble(8);//Lalanka on 06-04-2009
					_m_bal_other_charges=rs2.getDouble(16)+rs2.getDouble(19) - rs2.getDouble(9);//Lalanka on 06-04-2009
					_m_bal_total_charges=(_m_total_last_month + _m_total_charges_month ) -  _m_total_receipts ;
					
					out.println("<tr>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" >Balance "+_m_end_date+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" >-</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_g_ren)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_odi_ren)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_insuarance)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_other_charges)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_total_charges)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(_m_bal_total_charges)+"</td>");
					out.println("<td  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs2.getDouble(10))+"</td>");
					out.println("</tr>");
					//_______________________________________________________________________
					out.println("</table>");
					
					out.println("<br><br>");
					//_m_total_over_due+=_m_bal_total_charges;
					_m_total_over_due+=rs2.getDouble(13);
					_m_total_rental+=rs2.getDouble(20);
					rs2.close();
					}
					rs.close();
					
				String sql_return=" SELECT "+
				" REC_NO, "+//1
				" PAYER_BRANCH_CODE, "+ //2
				" PAYER_ACC_NO, "+ //3
				" REC_AMOUNT, "+ //4
				" BRANCH_CODE, "+ //5
				" ACC_NO, "+ //6
				" EFF_VALDATE, "+ //7
				" CHEQUE_NO, "+ //8
				" REC_AMOUNT_REP_CURR, "+ //9
				" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') "+ //10
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				" WHERE EFF_VALDATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)  "+
				" AND   EFF_VALDATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY'))"+
				" AND   STATUS='RET' "+
				" AND   UPPER(CLIENT_CODE)=UPPER('"+m_c_code+"') ";
				
					rs= stmt.executeQuery(sql_return);
					boolean more_return=rs.next();
					if(more_return){
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >");
					while(more_return){
						
					out.println("<tr>");
					out.println("<td width=\"12%\" class='rep-body1' style=\"{font-size: 9px;}\" ><B>Cheques Returns </td>");
					out.println("<td width=\"8%\"  class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(3)+"</td>");
					out.println("<td width=\"15%\" class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width=\"7%\"  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+rs.getString(5)+"</td>");
					out.println("<td width=\"12%\" class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>"+rs.getString(10)+"</td>");
					out.println("<td width=\"14%\" class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>-</td>");
					out.println("<td width=\"7%\"  class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>-</td>");
					out.println("<td width=\"14%\" class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("<td width=\"11%\" class='rep-body1'  style=\"{font-size: 9px;}\" align='right'>&nbsp;</td>");
					out.println("</tr>");

					more_return=rs.next();
					}
					out.println("</table>");
          }

					
					out.println("<br><br>");

					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"75%\" style=\"{font-size: 9px;}\" class='rep-body1'  >Total Over Due as at "+_m_Previous_day+"</td>");
					out.println("<td align='right' style=\"{font-size: 9px;}\" width=\"15%\" class='rep-body1'  >"+nf.format(_m_total_over_due)+"</td>");
					out.println("<td width=\"10%\" class='rep-body1'>&nbsp;</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >Gross rentals Due in "+m_Letter_date_month+" for all contracts</td>");
					out.println("<td align='right' style=\"{font-size: 9px;}\" class='rep-body1'  ><u>"+nf.format(_m_total_rental)+"<u></td>");
					out.println("<td width=\"10%\" class='rep-body1'  >&nbsp;</td>");
					out.println("</tr>");
					_m_total_payable=_m_total_over_due+_m_total_rental;
					out.println("<tr>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" >Total payable in "+_m_month+" without charges due in "+_m_month+"</td>");
					out.println("<td align='right' style=\"{font-size: 9px;}\" class='rep-body1'  ><u>"+nf.format(_m_total_payable)+"</u></td>");
					out.println("<td width=\"10%\" class='rep-body1'  >&nbsp;</td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
					//Added by Dineth on 28-04-2009
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
					" NVL("+m_schema_name+".AF_CO_GET_TOT_NO_RENTALS(A.FINANCE_NO,'"+_m_letter_date+"'),0), "+//2
					" NVL(CEIL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO(A.FINANCE_NO,'"+_m_letter_date+"')),0), "+//3
					" NVL("+m_schema_name+".AF_CO_GET_INVOICE_THS_MONTH(A.FINANCE_NO,'"+_m_letter_date+"'),0), "+//4
					" NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS(A.FINANCE_NO,'"+_m_letter_date+"'),0), "+//5
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS(A.FINANCE_NO,'"+_m_letter_date+"','RENT'),0), "+//6
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES(A.APPLICATION_NO,'INSURA'),0) INSU, "+ //7
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES(A.APPLICATION_NO,'OTHER'),0) OTHR, "+ //8
					" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS_THS_MNTH(A.APPLICATION_NO,'"+_m_letter_date+"'),0) ODI_THS, "+ //9
					" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS(A.FINANCE_NO,'"+_m_letter_date+"'),0) ODI, "+ //10
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS(A.FINANCE_NO,'"+_m_letter_date+"','INSUARANCE'),0) INSUARANCE, "+ //11
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS(A.FINANCE_NO,'"+_m_letter_date+"','OTHER'),0) OTHER,"+ //12
					" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS(A.FINANCE_NO,'"+_m_letter_date+"','ODI'),0) RENT, "+ //13
					" NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID(A.FINANCE_NO,'"+_m_letter_date+"'),0), "+//14
					" NVL("+m_schema_name+".AF_CO_GET_CNT_RENT_PAID(A.FINANCE_NO,'"+_m_letter_date+"'),0), "+//15
					" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT(A.FINANCE_NO,'"+_m_letter_date+"','PREV'),0), "+//16 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','INSURA','PREV'),0), "+//17 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','ODI','PREV'),0), "+//18 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','OTHER','PREV'),0), "+//19 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','INSURA','THIS'),0), "+//20 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','ODI','THIS'),0), "+//21 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT(A.FINANCE_NO,'"+_m_letter_date+"','OTHER','THIS'),0), "+//22 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT(A.FINANCE_NO,'"+_m_letter_date+"','THIS'),0), "+//23 Added by Dineth on 06-05-2009
					" NVL("+m_schema_name+".AF_CO_GET_FUTURE_REC(A.APPLICATION_NO),0) "+//24 Added by Dineth on 14-05-2009
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
          " WHERE A.CLIENT_CODE='"+m_client_code+"' "+ 
          " AND   A.APPLICATION_STATUS='ACTIVATED' "+
          " GROUP BY A.FINANCE_NO,A.APPLICATION_NO ");
					
							boolean more3=rs.next();
					if(more3){
					
					out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td width=\"40%\" style=\"{font-size: 9px;}\" class='rep-body1'  >&nbsp</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  ><b>Total</b></td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  ><b>Paid</b></td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  ><b>Future</b></td>");
					out.println("<td width=\"30%\" style=\"{font-size: 9px;}\" class='rep-body1'  ><b>Total Receivables & Including Charges</b></td>");
					out.println("</tr>");
					while(more3){
					m_tot_no_rentals=0;
					m_total_1=0;
					m_total_2=0;
					m_fut_rentals=0;
					m_finance_no_1=rs.getString(1);
					
					m_tot_no_rentals=rs.getDouble(2);
					//m_fut_rentals=rs.getDouble(3);
					//m_total_2=rs.getDouble(4)+rs.getDouble(5)-rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(8)+rs.getDouble(9)+rs.getDouble(10)-rs.getDouble(11)-rs.getDouble(12)-rs.getDouble(13);
					//m_total_2=rs.getDouble(16)+rs.getDouble(17)+rs.getDouble(18)+rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22)+rs.getDouble(23)-rs.getDouble(6)-rs.getDouble(11)-rs.getDouble(12)-rs.getDouble(13);
					
					m_total_2=rs.getDouble(16)+rs.getDouble(23)+rs.getDouble(24)+rs.getDouble(10)+rs.getDouble(17)+rs.getDouble(18)+rs.getDouble(19)+rs.getDouble(20)+rs.getDouble(21)+rs.getDouble(22);
					m_total_3=m_total_3+m_total_2;
					//m_paid_rentals=m_tot_no_rentals-m_fut_rentals;
					m_paid_rentals=rs.getDouble(15);
					m_fut_rentals=m_tot_no_rentals-m_paid_rentals;
					//m_tot_no_rentals=m_fut_rentals+m_paid_rentals;
					out.println("<tr>");
					out.println("<td width=\"40%\" style=\"{font-size: 9px;}\" class='rep-body1'  >No of Rentals- <b>"+m_finance_no_1+"</b></td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >"+nf.format(m_tot_no_rentals)+"</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >"+nf.format(m_paid_rentals)+"</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >"+nf.format(m_fut_rentals)+"</td>");
					out.println("<td width=\"30%\" style=\"{font-size: 9px;text-align:right;}\" class='rep-body1'  >"+nf.format(m_total_2)+"</td>");
					out.println("</tr>");
						more3=rs.next();
					}
					out.println("<tr>");
					out.println("<td width=\"40%\" style=\"{font-size: 9px;}\" class='rep-body1'  >&nbsp;</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >&nbsp;</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >&nbsp;</td>");
					out.println("<td width=\"10%\" style=\"{font-size: 9px;}\" class='rep-body1'  >&nbsp</td>");
					out.println("<td width=\"30%\" style=\"{font-size: 9px;text-align:right;}\" class='rep-body1'  >"+nf.format(m_total_3)+"</td>");
					out.println("</tr>");
						
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					}
					//End by Dineth on 28-04-2009
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><div align=\"center\"><strong>Payment Slip </strong></div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1'  >Credit to the "+m_orient_name+" AC</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Agreement No </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Vehicle No </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Due Including "+m_Letter_date_month+" </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Cheque No </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Bank Branch </td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Amount Rs. </td>");
					out.println("</tr>");
					
					rs= stmt.executeQuery (Sql_invoice);
					
					while(rs.next()){
					_m_finance_no     = rs.getString(1);
					_m_application_no = rs.getString(6);
					
						rs2= stmt2.executeQuery (" SELECT "+
						" NVL("+m_schema_name+".AF_CO_GET_RENTAL_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) G_REN, "+//1
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS('"+_m_finance_no+"','"+m_value_date+"'),0) ODI, "+ //2
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','INSURA'),0) INSU, "+ //3
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHARGES('"+_m_application_no+"','OTHER'),0) OTHR, "+ //4
						" NVL("+m_schema_name+".AF_CO_GET_INVOICE_THS_MONTH('"+_m_finance_no+"','"+m_value_date+"'),0) G_THS, "+ //5
						" NVL("+m_schema_name+".AF_CO_GET_ODI_ARREARS_THS_MNTH('"+_m_finance_no+"','"+m_value_date+"'),0) ODI_THS, "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','RENT'),0) RENT, "+ //7
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','INSUARANCE'),0) INSUARANCE, "+ //8
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','OTHER'),0) OTHER ,"+ //9
						" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO('"+_m_finance_no+"','"+m_value_date+"'),0) AGE ,"+ //10
						" NVL("+m_schema_name+".AF_CO_MAS_ASSET_DESC('"+_m_application_no+"'),'-') ASSET_DESC ,"+ //11
						" NVL("+m_schema_name+".AF_CO_GET_RECEIPTS('"+_m_finance_no+"','"+m_value_date+"','ODI'),0) RENT, "+ //12
						" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT('"+_m_finance_no+"','"+m_value_date+"','PREV'),0), "+//13 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','INSURA','PREV'),0), "+//14 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','ODI','PREV'),0), "+//15 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','OTHER','PREV'),0), "+//16 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','INSURA','THIS'),0), "+//17 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','ODI','THIS'),0), "+//18 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_OTHER_CHA_AMT('"+_m_finance_no+"','"+m_value_date+"','OTHER','THIS'),0), "+//19 Added by Dineth on 06-05-2009
						" NVL("+m_schema_name+".AF_CO_GET_GROSS_RENTAL_AMT('"+_m_finance_no+"','"+m_value_date+"','THIS'),0) "+//20 Added by Dineth on 06-05-2009
						" FROM DUAL ");
						rs2.next();
					//_m_bal_g_ren=(rs2.getDouble(1)+rs2.getDouble(5) ) - rs2.getDouble(7);
					//_m_bal_g_ren=rs2.getDouble(13)+rs2.getDouble(14)+rs2.getDouble(15)+rs2.getDouble(16)+rs2.getDouble(17)+rs2.getDouble(18)+rs2.getDouble(19)+rs2.getDouble(20)-(rs2.getDouble(7)+rs2.getDouble(8)+rs2.getDouble(9));
					_m_bal_g_ren=rs2.getDouble(13)+rs2.getDouble(20);
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(1)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(7)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" align='right' >"+nf.format(_m_bal_g_ren)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >&nbsp;</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right' >&nbsp;</td>");
					out.println("</tr>");
					
					}
															
					out.println("</table>");
					out.println("<br>");
					
					out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					//out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\"><b>Please quote agreement no in the bank deposit slip attached this slip with bank deposit slip.</b></div></td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\"><b>Please quote agreement no in the bank deposit slip & attached this slip with bank deposit slip</b></div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\">Com bank</div></td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\">HNB</div></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" ><div align=\"left\">Sampath</div></td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" ><div align=\"left\">BOC</div></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\">Peoples</div></td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\"><div align=\"left\">Union</div></td>");
					out.println("</tr>");
					out.println("</table>");
					
					//out.println("   <p style=\"page-break-after:always\"></p>");		
					
					/*out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
					out.println("<tr>");
					out.println("<td class='rep-body1'><div align=\"center\"><b>Official Receipt</b></div></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class='rep-body1'><div align=\"center\">if the contract terminated payment without prejudice</div></td>");
					out.println("</tr>");
					out.println("</table>");
					*/
					
				String Sql_receipt=" SELECT A.REC_NO,C.FINANCE_NO, NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges ' DESCREPTION,SUM(SETTELED_AMOUNT)  "+
				" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_INVOICE C  "+
				" WHERE A.REC_NO = B.RECEIPT_NO  "+
				" AND   B.INVOICE_NO = C.INVOICE_NO  "+
				" AND   A.EFF_VALDATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   A.EFF_VALDATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND   UPPER(C.GROUP_INV_NO)=UPPER('"+m_invoice_no+"')"+
				" GROUP BY C.FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE ,A.REC_NO "+
				" ORDER BY A.REC_NO ";

     rs= stmt.executeQuery (Sql_receipt);
		  more=rs.next();
			String _m_rec_no="";
			double _m_receipt_total=0;
			double _m_over_due=0;
		 while(more){
     _m_rec_no=rs.getString(1);
		  out.println("<br><br>");
			out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><div align=\"center\"><b>Official Receipt</b></div></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><div align=\"center\">if the contract terminated payment without prejudice</div></td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br>");
			out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1'  >Date</td>");
			out.println("<td class='rep-body1'  >"+m_value_date+"</td>");
			out.println("<td class='rep-body1'  >Receipt No </td>");
			out.println("<td class='rep-body1'  >"+rs.getString(1)+"</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1'   >Received with thanks from </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"17%\"><b>Agreement No </td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"14%\"><b>Chq No/Cash </td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"19%\"><b>Bank/Branch</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"15%\"><b>Description</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"15%\">&nbsp;</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"7%\">&nbsp;</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" width=\"13%\"><div align=\"right\"><b>Amount</div></td>");
			out.println("</tr>");

			
						
			while(_m_rec_no.equals(rs.getString(1))){
			
					out.println("<tr>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(2)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(3)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(4)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >"+rs.getString(5)+"</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >&nbsp;</td>");
					out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >&nbsp;</td>");
					out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right' >"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");			
			    _m_receipt_total=rs.getDouble(6);
			   
				
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" colspan=\"6\"><div align=\"center\">Total Amount </div></td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   ><div align=\"right\">"+nf.format(_m_receipt_total)+"</div></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1'   style=\"{font-size: 9px;}\" >Receipts is valid subject to realisation of the cheque. </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" >Amount in words RUPEES : "+m_sn_methods.numbersToChar(Double.toString(_m_receipt_total)).toUpperCase()+" ONLY </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			
			String sql_overdue=" SELECT NVL("+m_schema_name+".AF_CO_GET_ARREARS_AGR('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //1
			" NVL("+m_schema_name+".AF_CO_GET_AGE_END_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //2
			" NVL("+m_schema_name+".AF_CO_GET_FUTURE_RENTALS('"+rs.getString(2)+"'),0), "+ //3
			" NVL("+m_schema_name+".AF_CO_GET_FUTURE_RENTALS_AMT('"+rs.getString(2)+"'),0), "+ //4
			" NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //5
			" NVL("+m_schema_name+".AF_CO_GET_RENTALS_PAID_AMT('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //6
			" NVL("+m_schema_name+".AF_CO_GET_ODI_DUE('"+rs.getString(2)+"'),0), "+ //7
			" NVL("+m_schema_name+".AF_CO_GET_ODI_DUE_MONTH('"+rs.getString(2)+"'),0) ,"+ //8
			" NVL("+m_schema_name+".AF_CO_GET_INSU_DUE_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //9
			" NVL("+m_schema_name+".AF_CO_GET_AGE_INSU_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //10
			" NVL("+m_schema_name+".AF_CO_GET_OTHER_DUE_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0), "+ //11
		  " NVL("+m_schema_name+".AF_CO_GET_AGE_OTHER_AGR_NO('"+rs.getString(2)+"','"+_m_letter_date+"'),0) "+ //12
			
			" FROM DUAL ";
			//out.println("m_value_date"+m_value_date);
			rs2= stmt2.executeQuery (sql_overdue);
			boolean more2=rs2.next();
			
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1' width=\"51%\"><table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1' width=\"39%\">&nbsp;</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right' width=\"30%\"><b>Amount&nbsp;</td>");
			out.println("<td class='rep-body1'  style=\"{font-size: 9px;}\" align='right' width=\"31%\"><b>Months&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Rentals</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"  align='right' >"+nf.format(rs2.getDouble(1))+"&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>"+nf.format(rs2.getDouble(2))+"&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Interest</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right' >"+nf.format(rs2.getDouble(7))+"&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>"+nf.format(rs2.getDouble(8))+"&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>insuarance</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"  align='right'>"+nf.format(rs2.getDouble(9))+"&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"  align='right'>"+nf.format(rs2.getDouble(10))+"&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Other dues </td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>"+nf.format(rs2.getDouble(11))+"&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>"+nf.format(rs2.getDouble(12))+"&nbsp;</td>");
			out.println("</tr>");
			_m_over_due=rs2.getDouble(1)+rs2.getDouble(7)+rs2.getDouble(9)+rs2.getDouble(11);
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Total overdue </td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>"+nf.format(_m_over_due)+"&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'>&nbsp;</td>");
			out.println("</tr>");
			out.println("</table></td>");
			out.println("<td class='rep-body1'   width=\"16%\">&nbsp;</td>");
			out.println("<td class='rep-body1'   width=\"33%\"><table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" >&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right'><b>No&nbsp;</td>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\"   align='right' ><b>Value&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Future rentals </td>");
			out.println("<td class='rep-body1'   align='right'>"+nf.format(rs2.getDouble(3))+"&nbsp;</td>");
			out.println("<td class='rep-body1'   align='right' >"+nf.format(rs2.getDouble(4))+"&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class='rep-body1' style=\"{font-size: 9px;}\" ><b>Total rentals paid </td>");
			out.println("<td class='rep-body1'   align='right'>"+nf.format(rs2.getDouble(5))+"&nbsp;</td>");
			out.println("<td class='rep-body1'  align='right'>"+nf.format(rs2.getDouble(6))+"&nbsp;</td>");
			out.println("</tr>");
			out.println("</table></td>");
			out.println("</tr>");
			out.println("</table>");
			
			more=rs.next();
			if(!more){
			break;
			}	
			
						
			}


			
		 }
    
				
				
					
			/*out.println("<br>");
			out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td >Date</td>");
			out.println("<td >&nbsp;</td>");
			out.println("<td >Receipt No </td>");
			out.println("<td >&nbsp;</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td>Received with thanks from </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td width=\"17%\">Agreement No </td>");
			out.println("<td width=\"14%\">Chq No/Cash </td>");
			out.println("<td width=\"19%\">Bank/Branch</td>");
			out.println("<td width=\"15%\">Description</td>");
			out.println("<td width=\"15%\">&nbsp;</td>");
			out.println("<td width=\"7%\">&nbsp;</td>");
			out.println("<td width=\"13%\"><div align=\"right\">Amount</div></td>");
			out.println("</tr>");
			*/
			
			/*out.println("<tr>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");*/
			
			
		/*	out.println("<tr>");
			out.println("<td colspan=\"6\"><div align=\"center\">Total Amount </div></td>");
			out.println("<td><div align=\"right\"></div></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td>Receipts is valid subject to realisation of the cheque. </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td>Amount in words </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td width=\"51%\"><table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td width=\"39%\">&nbsp;</td>");
			out.println("<td width=\"30%\">Amount</td>");
			out.println("<td width=\"31%\">Months</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Rentals</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Interest</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>insuarance</td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Other dues </td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Total overdue </td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("</table></td>");
			out.println("<td width=\"16%\">&nbsp;</td>");
			out.println("<td width=\"33%\"><table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>No</td>");
			out.println("<td>Value</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Future rentals </td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Total rentals paid </td>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
			out.println("</table></td>");
			out.println("</tr>");
			
			out.println("</table>");
			*/
			
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td style=\"{font-size: 9px;}\" >News</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			//out.println("<td style=\"{font-size: 9px;}\" >Stamp duty has been calculated in terms of section 7 of the Stamp Duty (Special Provisions) Act No 12 of 2006.</td>"); //comment By Sandun on 14-07-2009
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("<tr>");
			out.println("<td style=\"{font-size: 9px;}\" >Please inform any discrepancies within 10 days.</td>");
			out.println("</tr>");
			out.println("</table>");


					//_________________________________________________________________________________________________________________
    }
				
				else if(m_chksql.equals("report_data")){		
		
				String m_client_code=req.getParameter("client_code").trim();
			  String m_value_date=req.getParameter("m_date").trim();
				stmt = conn.createStatement ();

				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='25%' align='left' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('GROUP_INV_NO') >Group Invoice No</td>");
				out.println("<td  width='20%' align='right'style= cursor:hand; title='Click here to sort by - Total'  onclick=sort_data('TOTAL_AMOUNT') >Total</td>");
				out.println("<td  width='20%' align='left' style= cursor:hand; title='Click here to sort by - Total'  onclick=sort_data('TOTAL_AMOUNT') >Due Date</td>");
				out.println("<td  width='5%' ></td>");
				out.println("</tr>");
 				
				
				rs= stmt.executeQuery (" SELECT "+
				" DISTINCT GROUP_INV_NO,  "+//1
				" SUM(TOTAL_AMOUNT) , "+ //2
				" NVL((TO_CHAR(DUE_DATE,'DD-MM-YYYY')),'-') DUE_DATE ,  "+ //3
				" B.CLIENT_CODE "+//4
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND  INVOICE_TYPE='INV_GENER'  "+
				" AND  UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
				" AND  DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_value_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND  DUE_DATE<=LAST_DAY(TO_DATE('"+m_value_date+"','DD-MM-YYYY')) "+
				" AND  B.APPLICATION_STATUS='ACTIVATED' "+
				" AND  PRINTED_STATUS IS NULL"+
				" GROUP BY GROUP_INV_NO,DUE_DATE,B.CLIENT_CODE ");
				
				  int j = 0;   
				  while(rs.next()){
											if(j>0 && j%2==1){
				              	out.println("<tr class=tr_input1 >");
											}
											else{
				              	out.println("<tr class=tr_input >");
											}
				              out.println("<td width='25%' align='left'>"+rs.getString(1) +"</td> ");
											out.println("<td width='20%' align='right'>"+nf.format(rs.getDouble(2))+"</td>");
											out.println("<td width='20%' align='left'>"+rs.getString(3) +"</td> ");
											out.println("<td  width='5%' align='center' ><input class=\"but_input\"  type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1) +"','"+rs.getString(3) +"','"+rs.getString(4) +"','"+m_value_date+"')\" name=BUT_LETTER value=\"Print\" ></td>"); 
				              out.println("</tr>");
				            	j=j+1;
				          }
						   out.println("</table>");			
											

    }
		
		
		
			
		}
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(out!=null){try{out.close();  }catch(Exception e){}}
		    if(rs!=null){try{rs.close();  }catch(Exception e){}}
		    if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
		    if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
		    if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
		    if(conn!=null){try{conn.close();  }catch(Exception e){}}
				
				
		}
	}
}
