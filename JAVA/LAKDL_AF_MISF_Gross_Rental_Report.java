//Created by Minal on 29-12-2014 for #14293
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.math.*; 


public class LAKDL_AF_MISF_Gross_Rental_Report extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a= null;
		Statement stmt=null,stmt2=null,stmt3=null;
		CallableStatement callstmt1 =null;
		ResultSet rs= null,rs1=null,rs2=null,rs_drill_new=null,rs3=null;
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_perform_status=req.getParameter("perform_status");
				String m_location_id=req.getParameter("location_id");
				
				
				try{
					
					//if(m_location_id.equals("ALL")){
					//	m_location_id="";
					//}
					
					
					// added by udara on 23-10-2013
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT_SAVE(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_location_id);
					callstmt1.setString(3,m_perform_status);
					callstmt1.setString(4,m_username);
					
					callstmt1.execute();
					out.print("OK"); 
					
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			if(m_chksql.equals("main_page")){ 
				
				
				stmt2 = conn.createStatement ();
				
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Gross Rental Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				out.println("function run_report() {");
				
				out.println("   m_perform_status = document.Form1.TXT_PERFORM_STATUS.value; ");
				out.println("   m_location_id = document.Form1.LOCATION_CODE.value; ");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=run_report&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status;"); 
				
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report2();");  // commented by udara 25-03-2014
				out.println("			alert('Gross Rental Report is generated. Use View Report button to get the view.');"); // out.println("			alert('The report is generated. Use View Report button to get the view.');"); // added by udara 25-03-2014
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_location_id=document.Form1.LOCATION_CODE.value;");
				out.println("   m_perform_status = document.Form1.TXT_PERFORM_STATUS.value; ");
				
				out.println("       m_yard_vehicles =  document.Form1.TXT_ACTIVE_STATUS.value;     "); // added by udara 27-07-2015
				out.println("       m_region_wise   =  document.Form1.TXT_REGION.value;     "); // added by udara 27-07-2015
				out.println("   m_product_name   = document.Form1.TXT_PRODUCT.value; "); // added by udara 11-10-2018
				
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=print_report_new_1&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status;"); 
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=print_report_new_1&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&yard_vehicles=\"+m_yard_vehicles+\"&region_wise=\"+m_region_wise;");  // added by udara 27-07-2015
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=print_report_new_1&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&yard_vehicles=\"+m_yard_vehicles+\"&region_wise=\"+m_region_wise+\"&product_name=\"+m_product_name;"); 
				out.println("		window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				
				out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("     help_button_user(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				//out.println("			document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			document.Form1.LOCATION_CODE.value=data_vec[2]");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     help_update(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("			document.Form1.LOCATION_CODE.value=data_vec[0]");
				out.println("			}");
				
				out.println("}");
				
				
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");	
				
				
				
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				
				out.println("function makeRequest(obj) {");
				
				out.println("load_interface(m_url,'XML');");
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
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
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
				out.println("help_box.innerHTML=\" Gross Rental Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Gross Rental Report - \"+document.Form1.hid_status.value;"); 
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
				
				
				//----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("}");	
				
				out.println("function ckeck_new_date(){ "); 
				out.println("b_flag=0;");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_date_selected()){"); 
				out.println("alert('Please enter new date');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println("function count_date_selected(){ ");
				out.println("count=0;");
				out.println("var arr_size=document.Form1.hid_no_rec.value;");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
				out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
				out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
				
				out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()' > "); //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Gross Rental Report </td>"); 
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
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Perform Status </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_PERFORM_STATUS'>"); 
				out.println("<option value='ALL' SELECTED> All </option>");
				out.println("<option value='PERFORM'  > Perform </option>");
				out.println("<option value='NPERFORM' > Non Perform </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch</DIV></td>"); 
				out.println("<td width='*%' >");
				out.println("<select name=\"LOCATION_CODE\" class=\"txt_input\" >");
				
				rs = stmt2.executeQuery(//Minal
					" SELECT LOCATION_CODE, "+
					"        LOCATION_DESC "+
					" FROM "+m_schema_name+".AF_CO_MAS_LOCATION  WHERE ACTIVE_STATUS='Y' ORDER BY LOCATION_DESC ");
				
				out.println("<OPTION value=\"ALL\" SELECTED >ALL</option>");
				while(rs.next()){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</option>");
				}	
				out.println("</SELECT></TD>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				
				// added by udara 27-07-2015
				
				out.println("<tr>"); 
				out.println("<td width='20%' > Active/Yard Vehicles </td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_ACTIVE_STATUS'>"); 
				out.println("<option value='A' > All </option>");
				out.println("<option value='Y' > Active </option>");
				out.println("<option value='N' > Yard Vehicles </option>");
				out.println("</select>");
				out.println("</td>"); 
				out.println("</tr>");
				
				
				/*out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				out.println("      <OPTION value='SAB' >Sabaragamuwa</OPTION>");
				out.println("      <OPTION value='WP'  >Western Province</OPTION>");
				out.println("      <OPTION value='CP'  >Central Province</OPTION>");
				out.println("      <OPTION value='CPSP'>Southern Province</OPTION>");

				out.println(" 	</select>");
				out.println("</td>"); 
				out.println("</tr>");*/
				
				// end by udara 27-07-2015
				
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
				out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				stmt3 = conn.createStatement();
				
				rs3 = stmt3.executeQuery (" SELECT REGIONS_CODE, REGIONS_DESC  "+
					" FROM "+m_schema_name+".AF_CO_MAS_REGIONS "+
					" WHERE ACTIVE_STATUS='Y' "+
					" ORDER BY REGIONS_DESC ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				
				
				// added by udara 11-10-2018
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT'  class=div_input> Product Name </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_PRODUCT'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				rs3=stmt3.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE  ACTIVE_STATUS='Y' ");
				
				while(rs3.next()){
					out.println("  <OPTION value=\""+rs3.getString(1)+"\">"+rs3.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				// end by udara 11-10-2018
				
				
				
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			else if(m_chksql.equals("print_report_new_1")){		
				
				String m_date=req.getParameter("date");
				String m_perform_status=req.getParameter("perform_status");
				String m_location_id=req.getParameter("location_id");
				
				
				
				// added by udara 27-07-2015
				String m_yard_vehicles = "";
				String m_region_wise   = "";
				String  m_active_status_string = ""; 
				String  m_region_string = ""; 
				
				
				
				m_yard_vehicles=req.getParameter("yard_vehicles");
				m_region_wise=req.getParameter("region_wise");
				
				if(!m_yard_vehicles.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_yard_vehicles+"' "; 
				}
				
				if(!m_region_wise.equals("NOT_SELECT")){
					m_region_string = " AND REGION_CODE = '"+m_region_wise+"' ";
				}
				
				// end by udara 27-07-2015
				
				// added by udara 11-10-2018
				String m_product_name=req.getParameter("product_name"); 
				String  m_product_string = ""; 
				if(!m_product_name.equals("NOT_SELECT")){
					m_product_string = " AND TRANSACTION_TYPE = '"+m_product_name+"' ";
				}
				// end by udara 11-10-2018
				
				
				if(m_perform_status.equals("ALL")){
					m_perform_status="";
				}
				
				if(m_location_id.equals("ALL")){
					m_location_id="";
				}
				
				stmt = conn.createStatement ();
				stmt2 = conn.createStatement ();
				
				String pre_month = "";
				String curr_month = "";
				String pre_month_year  = "";
				String curr_month_year = "";
				String location_desc = "";
				String region_desc = "";
				
				rs2= stmt2.executeQuery("  "+
					" SELECT TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),0),'MONTH'), "+
					"        TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),1),'MONTH'), "+
					"        TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),0),'Month/YYYY'), "+
					"        TO_CHAR(ADD_MONTHS(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),1),'Month/YYYY'), "+
					"        "+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location_id+"'), "+
					"        NVL("+m_schema_name+".AF_CO_GET_REG_DESC('"+m_region_wise+"'),'-')  "+ // added by udara 25-08-2015
					" FROM DUAL "+
					" ");
				
				if(rs2.next()){
					pre_month  = rs2.getString(1);
					curr_month = rs2.getString(2);
					pre_month_year = rs2.getString(3);
					curr_month_year = rs2.getString(4);
					location_desc = rs2.getString(5);
					region_desc = rs2.getString(6);
				}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>GROSS RENTAL REPORT</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("function unselect_select_row(id){ ");
				out.println("count=document.Form1.no_of_records.value;");
				out.println("for(i=1; i<count; i++){");
				out.println(" document.getElementById(\"tr_id\"+i).style.backgroundColor ='#FFFFFF' ;");
				out.println("}");
				out.println("select_row(id);");
				out.println("}");
				
				out.println("function select_row(id){ ");
				out.println(" document.getElementById(\"tr_id\"+id).style.backgroundColor ='yellow' ;");
				out.println("");
				out.println("}");
				
				out.println("function show_drill_gross_rental_prev(mm_location_id,mm_type){");
				out.println("		m_date = '"+m_date+"'; ");
				out.println("		m_location_id = mm_location_id; ");
				out.println("       m_perform_status = '"+m_perform_status+"'; ");
				out.println("       m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=show_drill_gross_rental_prev&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&type=\"+mm_type;"); 
				out.println("       window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_drill_gross_rental_prev_2(mm_location_id,mm_type){");
				out.println("		m_date = '"+m_date+"'; ");
				out.println("		m_location_id = mm_location_id; ");
				out.println("       m_perform_status = '"+m_perform_status+"'; ");
				
				out.println("       m_yard_vehicles =  '"+m_yard_vehicles+"';     "); // added by udara 27-07-2015
				out.println("       m_region_wise   =  '"+m_region_wise+"';     "); // added by udara 27-07-2015
				
				//out.println("       m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=show_drill_gross_rental_prev_2&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&type=\"+mm_type;"); // commented by udara 27-07-2015
				out.println("       m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=show_drill_gross_rental_prev_2&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&type=\"+mm_type+\"&yard_vehicles=\"+m_yard_vehicles+\"&region_wise=\"+m_region_wise;");  // added by udara 27-07-2015
				out.println("       window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>GROSS RENTAL REPORT AS AT "+m_date+" </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				// added by udara 18-08-2015
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform Status :- </td>"); 
				
				if(m_perform_status.equals(""))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_perform_status.equals("PERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Perform</td>");
				else if(m_perform_status.equals("NPERFORM"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Non Perform</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				
				// added by udara 25-08-2015
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active/Yard Vehicles :- </td>"); 
				
				if(m_yard_vehicles.equals("A"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >All</td>"); 
				else if(m_yard_vehicles.equals("Y"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Active</td>");
				else if(m_yard_vehicles.equals("N"))
					out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Yard Vehicles</td>");
				
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				out.println("<tr >");
				out.println("<td width='10%' STYLE='{font: bold 8pt arial; text-align:left;}'   >Region :- </td>"); 
				out.println("<td width='50%' STYLE='{font: bold 8pt arial; text-align:left;}'   >"+region_desc+"</td>"); 
				out.println("<td width='*%' > &nbsp; </td>"); 
				out.println("</tr >");
				
				// end by udara 25-08-2015
				
				
				
				out.println("</table >");
				// end by udara 18-08-2015
				
				
				if(!m_location_id.equals("")){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr >");
					out.println("<td width=\"*\" STYLE='{font: bold 8pt arial; text-align:left;}'   > Branch :- "+location_desc+" </td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				
				
				
				out.println("<table  cellspacing=0 > "); 
				out.println("<tr> "); 
				out.println("<td width='20'> "); 
				out.println("</td> "); 
				out.println("<td> "); 
				out.println("<table id=mytable align=\"left\" width=\"1500px\" border=\"1\" class=\"table\" cellspacing=0 > "); 
				//=================HEADER ================================
				out.println("<tr border=\"2\">");
				out.println("<td width=\"100px\"  align='center' COLSPAN='9' ><b> "+pre_month+" </b></td>");
				out.println("<td width=\"100px\"  align='center' COLSPAN='4' ><b> "+curr_month+" </b></td>");
				out.println("<td width=\"100px\"  align='center' COLSPAN='2' ><b>INCREASE/ DECREASE</b></td>");
				out.println("</tr> "); 
				out.println("<tr border=\"2\"> "); 
				out.println("<td width='10px' class=div_input   align='center' > &nbsp; </td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>BRANCH</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>GROSS RENTAL-"+pre_month_year+"</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NO OF CASES</B></td>"); 
				
				out.println("<td width='100px' class=div_input  align='center' ><B>NON PERFORM</B></td>"); // added by udara 30-07-2015
				out.println("<td width='100px' class=div_input  align='center' ><B>NO OF CASES</B></td>"); // added by udara 30-07-2015
				
				
				out.println("<td width='100px' class=div_input  align='center' ><B>CLOSED</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NO OF CASES</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>MATURE</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NEW CASE</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NO OF CASES</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>GROSS RENTAL-"+curr_month_year+"</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>TOTAL NO OF CASES</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NET RENTAL</B></td>"); 
				out.println("<td width='100px' class=div_input  align='center' ><B>NET CASES</B></td>"); 
				out.println("</tr> "); 
				
				/*
				
				rs2= stmt2.executeQuery("  "+
					      
					" SELECT "+
						" BRANCH_CODE, "+ // 1
						" SUM(PREV_MONTH_GROSS), "+ // 2
						" SUM(PREV_MONTH_GROSS_COUNT), "+ // 3
						" SUM(PREV_MONTH_CLOSED), "+ // 4
						" SUM(PREV_MONTH_CLOSED_COUNT), "+ // 5
						" SUM(PREV_MONTH_MATURE), "+ // 6
						" SUM(PREV_MONTH_MATURE_COUNT), "+ // 7
						" SUM(CURR_MONTH_NEW), "+ // 8
						" SUM(CURR_MONTH_NEW_COUNT), "+ // 9
						" SUM(CURR_MONTH_GROSS), "+ // 10
						" SUM(CURR_MONTH_GROSS_COUNT)  "+ // 11
						" FROM ( "+
						
							" SELECT BRANCH_CODE BRANCH_CODE, "+
							" SUM(PREV_MONTH_GROSS) PREV_MONTH_GROSS, "+
							" COUNT(PREV_MONTH_GROSS) PREV_MONTH_GROSS_COUNT, "+
							" 0 PREV_MONTH_CLOSED, "+
							" 0 PREV_MONTH_CLOSED_COUNT, "+
							" 0 PREV_MONTH_MATURE, "+
							" 0 PREV_MONTH_MATURE_COUNT, "+
							" 0 CURR_MONTH_NEW, "+
							" 0 CURR_MONTH_NEW_COUNT, "+
							" 0 CURR_MONTH_GROSS, "+
							" 0 CURR_MONTH_GROSS_COUNT "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_GROSS > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY BRANCH_CODE "+
							
							" UNION "+
							
							" SELECT BRANCH_CODE BRANCH_CODE, "+
							" 0 PREV_MONTH_GROSS, "+
							" 0 PREV_MONTH_GROSS_COUNT, "+
							" SUM(PREV_MONTH_CLOSED) PREV_MONTH_CLOSED, "+
							" COUNT(PREV_MONTH_CLOSED) PREV_MONTH_CLOSED_COUNT, "+
							" 0 PREV_MONTH_MATURE, "+ 
							" 0 PREV_MONTH_MATURE_COUNT, "+
							" 0 CURR_MONTH_NEW, "+
							" 0 CURR_MONTH_NEW_COUNT, "+
							" 0 CURR_MONTH_GROSS, "+
							" 0 CURR_MONTH_GROSS_COUNT "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_CLOSED > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY BRANCH_CODE "+
							
							" UNION "+
							
							" SELECT BRANCH_CODE BRANCH_CODE, "+
							" 0 PREV_MONTH_GROSS, "+
							" 0 PREV_MONTH_GROSS_COUNT, "+
							" 0 PREV_MONTH_CLOSED, "+ 
							" 0 PREV_MONTH_CLOSED_COUNT, "+
							" SUM(PREV_MONTH_MATURE) PREV_MONTH_MATURE, "+
							" COUNT(PREV_MONTH_MATURE) PREV_MONTH_MATURE_COUNT, "+
							" 0 CURR_MONTH_NEW, "+
							" 0 CURR_MONTH_NEW_COUNT, "+
							" 0 CURR_MONTH_GROSS, "+
							" 0 CURR_MONTH_GROSS_COUNT "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_MATURE > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY BRANCH_CODE "+
							
							" UNION "+
							
							" SELECT BRANCH_CODE BRANCH_CODE, "+
							" 0 PREV_MONTH_GROSS, "+
							" 0 PREV_MONTH_GROSS_COUNT, "+
							" 0 PREV_MONTH_CLOSED, "+
							" 0 PREV_MONTH_CLOSED_COUNT, "+
							" 0 PREV_MONTH_MATURE, "+
							" 0 PREV_MONTH_MATURE_COUNT, "+
							" SUM(CURR_MONTH_NEW) CURR_MONTH_NEW, "+
							" COUNT(CURR_MONTH_NEW) CURR_MONTH_NEW_COUNT, "+
							" 0 CURR_MONTH_GROSS, "+
							" 0 CURR_MONTH_GROSS_COUNT "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE CURR_MONTH_NEW > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY BRANCH_CODE "+
							
							" UNION "+
							
							" SELECT BRANCH_CODE BRANCH_CODE, "+
							" 0 PREV_MONTH_GROSS, "+
							" 0 PREV_MONTH_GROSS_COUNT, "+
							" 0 PREV_MONTH_CLOSED, "+
							" 0 PREV_MONTH_CLOSED_COUNT, "+
							" 0 PREV_MONTH_MATURE, "+
							" 0 PREV_MONTH_MATURE_COUNT, "+
							" 0 CURR_MONTH_NEW, "+
							" 0 CURR_MONTH_NEW_COUNT, "+
							" SUM(CURR_MONTH_GROSS) CURR_MONTH_GROSS, "+
							" COUNT(CURR_MONTH_GROSS) CURR_MONTH_GROSS_COUNT "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE CURR_MONTH_GROSS > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY BRANCH_CODE "+
						
						" ) "+
						" GROUP BY BRANCH_CODE "+
					
					" ");
				
				*/
				
				rs2= stmt2.executeQuery("  "+
					
					" SELECT "+
					" BRANCH_CODE, "+ // 1
					" SUM(PREV_MONTH_GROSS), "+ // 2
					" SUM(PREV_MONTH_GROSS_COUNT), "+ // 3
					" SUM(PREV_MONTH_CLOSED), "+ // 4
					" SUM(PREV_MONTH_CLOSED_COUNT), "+ // 5
					" SUM(PREV_MONTH_MATURE), "+ // 6
					" SUM(PREV_MONTH_MATURE_COUNT), "+ // 7
					" SUM(CURR_MONTH_NEW), "+ // 8
					" SUM(CURR_MONTH_NEW_COUNT), "+ // 9
					" SUM(CURR_MONTH_GROSS), "+ // 10
					" SUM(CURR_MONTH_GROSS_COUNT),  "+ // 11
					" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),BRANCH_CODE), "+ // 12
					" SUM(NON_PERFORM_VALUE), "+ // 13 // added by udara 30-07-2015
					" SUM(NON_PERFORM_VALUE_COUNT) "+ // 14 // added by udara 30-07-2015
					" FROM ( "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					//" PREV_MONTH_GROSS PREV_MONTH_GROSS, "+
					" DECODE(GROSS_FLAG,'Y',PREV_MONTH_GROSS,0) PREV_MONTH_GROSS,  "+
					" 1 PREV_MONTH_GROSS_COUNT, "+
					" 0 PREV_MONTH_CLOSED, "+
					" 0 PREV_MONTH_CLOSED_COUNT, "+
					" 0 PREV_MONTH_MATURE, "+
					" 0 PREV_MONTH_MATURE_COUNT, "+
					" 0 CURR_MONTH_NEW, "+
					" 0 CURR_MONTH_NEW_COUNT, "+
					" 0 CURR_MONTH_GROSS, "+
					" 0 CURR_MONTH_GROSS_COUNT, "+
					" 0 NON_PERFORM_VALUE, "+ // added by udara 30-07-2015
					" 0 NON_PERFORM_VALUE_COUNT "+ // added by udara 30-07-2015
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE PREV_MONTH_GROSS > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					" GROUP BY BRANCH_CODE, FINANCE_NO, PREV_MONTH_GROSS, GROSS_FLAG "+
					
					" UNION "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					" 0 PREV_MONTH_GROSS, "+
					" 0 PREV_MONTH_GROSS_COUNT, "+
					//" PREV_MONTH_CLOSED PREV_MONTH_CLOSED, "+
					" DECODE(CLOSE_FLAG,'Y',PREV_MONTH_CLOSED,0) PREV_MONTH_CLOSED, "+
					" 1 PREV_MONTH_CLOSED_COUNT, "+
					" 0 PREV_MONTH_MATURE, "+ 
					" 0 PREV_MONTH_MATURE_COUNT, "+
					" 0 CURR_MONTH_NEW, "+
					" 0 CURR_MONTH_NEW_COUNT, "+
					" 0 CURR_MONTH_GROSS, "+
					" 0 CURR_MONTH_GROSS_COUNT, "+
					" 0 NON_PERFORM_VALUE, "+ // added by udara 30-07-2015
					" 0 NON_PERFORM_VALUE_COUNT "+ // added by udara 30-07-2015
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE PREV_MONTH_CLOSED > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					" GROUP BY BRANCH_CODE, FINANCE_NO, PREV_MONTH_CLOSED, CLOSE_FLAG "+
					
					" UNION "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					" 0 PREV_MONTH_GROSS, "+
					" 0 PREV_MONTH_GROSS_COUNT, "+
					" 0 PREV_MONTH_CLOSED, "+ 
					" 0 PREV_MONTH_CLOSED_COUNT, "+
					//" PREV_MONTH_MATURE PREV_MONTH_MATURE, "+
					" DECODE(MATURE_FLAG,'Y',PREV_MONTH_MATURE,0) PREV_MONTH_MATURE, "+
					" 1 PREV_MONTH_MATURE_COUNT, "+
					" 0 CURR_MONTH_NEW, "+
					" 0 CURR_MONTH_NEW_COUNT, "+
					" 0 CURR_MONTH_GROSS, "+
					" 0 CURR_MONTH_GROSS_COUNT, "+
					" 0 NON_PERFORM_VALUE, "+ // added by udara 30-07-2015
					" 0 NON_PERFORM_VALUE_COUNT "+ // added by udara 30-07-2015
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE PREV_MONTH_MATURE > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					" GROUP BY BRANCH_CODE, FINANCE_NO, PREV_MONTH_MATURE, MATURE_FLAG "+
					
					" UNION "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					" 0 PREV_MONTH_GROSS, "+
					" 0 PREV_MONTH_GROSS_COUNT, "+
					" 0 PREV_MONTH_CLOSED, "+
					" 0 PREV_MONTH_CLOSED_COUNT, "+
					" 0 PREV_MONTH_MATURE, "+
					" 0 PREV_MONTH_MATURE_COUNT, "+
					//" CURR_MONTH_NEW CURR_MONTH_NEW, "+
					" DECODE(CURR_FLAG,'Y',CURR_MONTH_NEW,0) CURR_MONTH_NEW, "+
					" 1 CURR_MONTH_NEW_COUNT, "+
					" 0 CURR_MONTH_GROSS, "+
					" 0 CURR_MONTH_GROSS_COUNT, "+
					" 0 NON_PERFORM_VALUE, "+ // added by udara 30-07-2015
					" 0 NON_PERFORM_VALUE_COUNT "+ // added by udara 30-07-2015
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE CURR_MONTH_NEW > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					" GROUP BY BRANCH_CODE, FINANCE_NO, CURR_MONTH_NEW, CURR_FLAG "+
					
					" UNION "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					" 0 PREV_MONTH_GROSS, "+
					" 0 PREV_MONTH_GROSS_COUNT, "+
					" 0 PREV_MONTH_CLOSED, "+
					" 0 PREV_MONTH_CLOSED_COUNT, "+
					" 0 PREV_MONTH_MATURE, "+
					" 0 PREV_MONTH_MATURE_COUNT, "+
					" 0 CURR_MONTH_NEW, "+
					" 0 CURR_MONTH_NEW_COUNT, "+
					" CURR_MONTH_GROSS CURR_MONTH_GROSS, "+
					" 1 CURR_MONTH_GROSS_COUNT, "+
					" 0 NON_PERFORM_VALUE, "+ // added by udara 30-07-2015
					" 0 NON_PERFORM_VALUE_COUNT "+ // added by udara 30-07-2015
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE CURR_MONTH_GROSS > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					" GROUP BY BRANCH_CODE, FINANCE_NO, CURR_MONTH_GROSS "+
					
					// added by udara 30-07-2015
					" UNION "+
					
					" SELECT FINANCE_NO FINANCE_NO, "+
					" BRANCH_CODE BRANCH_CODE, "+
					" 0 PREV_MONTH_GROSS,  "+
					" 0 PREV_MONTH_GROSS_COUNT, "+
					" 0 PREV_MONTH_CLOSED, "+
					" 0 PREV_MONTH_CLOSED_COUNT, "+
					" 0 PREV_MONTH_MATURE, "+
					" 0 PREV_MONTH_MATURE_COUNT, "+
					" 0 CURR_MONTH_NEW, "+
					" 0 CURR_MONTH_NEW_COUNT, "+
					" 0 CURR_MONTH_GROSS, "+
					" 0 CURR_MONTH_GROSS_COUNT, "+
					" DECODE(GROSS_FLAG,'Y',NON_PERFORM_VALUE,0) NON_PERFORM_VALUE,  "+ // added by udara 30-07-2015
					
					//" 1 NON_PERFORM_VALUE_COUNT "+ // commented by udara 14-09-2015 added by udara 30-07-2015
					
					// added by udara 14-09-2015
					" CASE "+
					//" WHEN DECODE(GROSS_FLAG,'Y',NON_PERFORM_VALUE,0) < 0 THEN -1 "+
					//" WHEN DECODE(GROSS_FLAG,'Y',NON_PERFORM_VALUE,0) > 0 THEN 1 "+
					//" ELSE 1 "+
					" WHEN NON_PERFORM_STATUS = 'Y' THEN -1 "+ // added by udara 15-09-2015
					" ELSE 1 "+
					
					" END NON_PERFORM_VALUE_COUNT "+
					// end by udara 14-09-2015
					
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					//" WHERE NON_PERFORM_VALUE > 0 "+ // commented by udara 14-09-2015
					" WHERE ((NON_PERFORM_VALUE > 0) OR (NON_PERFORM_VALUE < 0)) "+ // added by udara 14-09-2015
					" AND ENT_USER = '"+m_username+"' "+
					//" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ // commented by udara 05-08-2015
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ 
					" "+m_region_string+"  "+ 
					" "+m_product_string+" "+ // added by udara 11-10-2018
					//" GROUP BY BRANCH_CODE, FINANCE_NO, NON_PERFORM_VALUE, GROSS_FLAG "+ // commented by udara 15-09-2015
					" GROUP BY BRANCH_CODE, FINANCE_NO, NON_PERFORM_VALUE, GROSS_FLAG, NON_PERFORM_STATUS "+ // added by udara 15-09-2015
					// end by udara 30-07-2015
					
					
					" ) "+
					//" ORDER BY "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)  "+
					" GROUP BY BRANCH_CODE "+
					" ORDER BY "+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE)  "+
					
					" ");
				
				double non_perform_tot = 0; // added by udara 30-07-2015
				int non_perform_count = 0;  // added by udara 30-07-2015
				
				double prev_month_gross_tot = 0;
				int prev_month_gross_count = 0;
				
				double prev_month_closed_tot = 0;
				int prev_month_closed_count = 0;
				
				double prev_month_mature_tot = 0;
				int prev_month_mature_count = 0;
				
				double curr_month_new_tot = 0;
				int curr_month_new_count = 0;
				
				double gross_rent_total = 0;
				int total_cases_count = 0;
				double net_rental = 0;
				int net_cases_count = 0;
				
				int count = 0;
				
				while(rs2.next()){
					
					count = count + 1;
					
					/*
					out.println("<tr border=\"0\"> "); 
					out.println("<td width='10px' class=div_input  align='left'    > "+count+" </td>");
					out.println("<td width='100px' class=div_input  align='left'   > "+rs2.getString(1)+" </td>"); // branch
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+nf.format(rs2.getDouble(2)+rs2.getDouble(4))+" </u></td>"); // prev month gross  // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+nf.format(rs2.getDouble(2))+" </u></td>"); // prev month gross
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+(rs2.getInt(3)+rs2.getInt(5))+" </u></td>"); // prev month gross count // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+rs2.getInt(3)+" </u></td>"); 
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_closed');\" ><u> "+nf.format(rs2.getDouble(4))+" </u></td>"); // prev month closed
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_closed');\" ><u> "+rs2.getInt(5)+" </u></td>"); // prev month closed count
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_mature');\" ><u> "+nf.format(rs2.getDouble(6))+" </u></td>"); // prev month mature
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','curr_month_new');\"    ><u> "+nf.format(rs2.getDouble(8))+" </u></td>"); // curr month new
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','curr_month_new');\"    ><u> "+rs2.getInt(9)+" </u></td>");  // curr month new count
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','gross_rent');\"        ><u> "+nf.format(rs2.getDouble(2) + rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </u></td>"); // gross_rent_total
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','gross_rent');\"        ><u> "+(rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</u></td>"); // total cases count // out.println("<td width='100px' class=div_input  align='right'  > "+(rs2.getInt(3)-rs2.getInt(5)+rs2.getInt(9))+"</td>"); // total cases count
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','net_rent');\"          ><u> "+nf.format(rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </u></td>"); // net rental
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','net_rent');\"          ><u> "+(rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</u></td>"); // out.println("<td width='100px' class=div_input  align='right'  > "+(rs2.getInt(9)-rs2.getInt(5)+rs2.getInt(9))+"</td>"); // net cases count
					out.println("</tr> ");
					*/
					
					out.println("<tr border=\"0\"> "); 
					out.println("<td width='10px' class=div_input  align='left'    > "+count+" </td>");
					//out.println("<td width='100px' class=div_input  align='left'   > "+rs2.getString(1)+" </td>"); // branch
					out.println("<td width='100px' class=div_input  align='left'   > "+rs2.getString(12)+" </td>"); // branch
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+nf.format(rs2.getDouble(2))+" </u></td>"); // commented by udara 07-10-2015 // prev month gross  // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+nf.format(rs2.getDouble(2))+" </u></td>"); // prev month gross
					
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+nf.format(rs2.getDouble(2)+rs2.getDouble(13))+" </u></td>"); // added by udara 07-10-2015
					
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+(rs2.getInt(3))+" </u></td>"); // commented by udara 07-10-2015 // prev month gross count // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+rs2.getInt(3)+" </u></td>"); 
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_gross');\"  ><u> "+(rs2.getInt(3)+rs2.getInt(14))+" </u></td>"); // added by udara 07-10-2015
					
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','non_perform');\" ><u> "+nf.format(rs2.getDouble(13))+" </u></td>"); // non perform // added by udara 30-07-2015
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','non_perform');\" ><u> "+(rs2.getInt(14))+" </u></td>"); // non perform count // added by udara 30-07-2015
					
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_closed');\" ><u> "+nf.format(rs2.getDouble(4))+" </u></td>"); // prev month closed
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_closed');\" ><u> "+rs2.getInt(5)+" </u></td>"); // prev month closed count
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','prev_month_mature');\" ><u> "+nf.format(rs2.getDouble(6))+" </u></td>"); // prev month mature
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','curr_month_new');\"    ><u> "+nf.format(rs2.getDouble(8))+" </u></td>"); // curr month new
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','curr_month_new');\"    ><u> "+rs2.getInt(9)+" </u></td>");  // curr month new count
					
					
					
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','gross_rent');\"        ><u> "+nf.format(rs2.getDouble(2) + rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </u></td>"); // gross_rent_total
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','gross_rent');\"        ><u> "+(rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</u></td>"); // total cases count // out.println("<td width='100px' class=div_input  align='right'  > "+(rs2.getInt(3)-rs2.getInt(5)+rs2.getInt(9))+"</td>"); // total cases count
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','net_rent');\"          ><u> "+nf.format(rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </u></td>"); // net rental
					//out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; cursor:hand;' onclick=\"show_drill_gross_rental_prev_2('"+rs2.getString(1)+"','net_rent');\"          ><u> "+(rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</u></td>"); // out.println("<td width='100px' class=div_input  align='right'  > "+(rs2.getInt(9)-rs2.getInt(5)+rs2.getInt(9))+"</td>"); // net cases count
					
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+nf.format(rs2.getDouble(2) + rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </td>"); // gross_rent_total
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+(rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5))+"</td>");  // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+(rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</td>"); 
					
					// commented by udara 29-10-2015
					/*
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+nf.format(rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6))+" </td>"); // net rental
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+(rs2.getInt(9)-rs2.getInt(5))+"</td>");  // out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+(rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7))+"</td>"); 
					*/
					
					// added by udara 29-10-2015
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+nf.format(rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6) - rs2.getDouble(13))+" </td>"); // net rental
					out.println("<td width='100px' class=div_input  align='right'  STYLE='text-align:right; ' > "+(rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(14))+"</td>"); 
					// end by udara 29-10-2015
					
					out.println("</tr> ");
					
					//prev_month_gross_tot = prev_month_gross_tot + rs2.getDouble(2) + rs2.getDouble(4); //prev_month_gross_tot = prev_month_gross_tot + rs2.getDouble(2);
					//prev_month_gross_count = prev_month_gross_count + rs2.getInt(3) + rs2.getInt(5); //prev_month_gross_count = prev_month_gross_count + rs2.getInt(3);
					
					prev_month_gross_tot = prev_month_gross_tot + rs2.getDouble(2);
					prev_month_gross_count = prev_month_gross_count + rs2.getInt(3);
					
					prev_month_closed_tot = prev_month_closed_tot + rs2.getDouble(4);
					prev_month_closed_count = prev_month_closed_count + rs2.getInt(5);
					
					prev_month_mature_tot = prev_month_mature_tot + rs2.getDouble(6);
					curr_month_new_tot = curr_month_new_tot + rs2.getDouble(8);
					
					curr_month_new_count = curr_month_new_count + rs2.getInt(9);
					
					gross_rent_total = gross_rent_total + (rs2.getDouble(2) + rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6));
					//total_cases_count = total_cases_count + (rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7));
					total_cases_count = total_cases_count + (rs2.getInt(3)+rs2.getInt(9)-rs2.getInt(5));
					
					//net_rental = net_rental + (rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6)); // commented by udara 29-10-2015
					net_rental = net_rental + (rs2.getDouble(8) - rs2.getDouble(4) - rs2.getDouble(6) - rs2.getDouble(13)); // added by udara 29-10-2015
					
					//net_cases_count = net_cases_count + (rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(7));
					//net_cases_count = net_cases_count + (rs2.getInt(9)-rs2.getInt(5)); // commented by udara 29-10-2015
					net_cases_count = net_cases_count + (rs2.getInt(9)-rs2.getInt(5)-rs2.getInt(14));
					
					non_perform_tot = non_perform_tot + rs2.getDouble(13); // added by udara 30-07-2015
					non_perform_count = non_perform_count + rs2.getInt(14); // added by udara 30-07-2015
					
				}
				
				out.println("<tr border=\"0\"> "); 
				out.println("<td width='10px' class=div_input   align='left'   > &nbsp; </td>");
				out.println("<td width='100px' class=div_input  align='left'   > &nbsp; </td>"); // branch
				//out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(prev_month_gross_tot)+" </b></td>"); // prev month gross
				
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(prev_month_gross_tot+non_perform_tot)+" </b></td>"); // added by udara 07-10-2015
				
				//out.println("<td width='100px' class=div_input  align='right'  ><b> "+prev_month_gross_count+" </b></td>"); // commented by udara 07-10-2015 // prev month gross count
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+(prev_month_gross_count+non_perform_count)+" </b></td>"); // added by udara 07-10-2015
				
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(non_perform_tot)+" </b></td>"); // non perform // added by udara 30-07-2015
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+non_perform_count+" </b></td>"); // non perform count // added by udara 30-07-2015
				
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(prev_month_closed_tot)+" </b></td>"); // prev month closed
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+prev_month_closed_count+" </b></td>"); // prev month closed count
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(prev_month_mature_tot)+"</b> </td>"); // prev month mature
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(curr_month_new_tot)+" </b></td>"); // curr month new
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+curr_month_new_count+" </b></td>");  // curr month new count
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(gross_rent_total)+" </b></td>"); // gross_rent_total
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+total_cases_count+" </b></td>"); // total cases count
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+nf.format(net_rental)+" </b></td>"); // net rental
				out.println("<td width='100px' class=div_input  align='right'  ><b> "+net_cases_count+" </b></td>"); // net cases count
				out.println("</tr> ");
				
				
				out.println("</table> "); 
				out.println("</table> "); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			// added by udara 14-01-2015
			
			else if(m_chksql.equals("show_drill_gross_rental_prev")){		
				
				String m_date=req.getParameter("date");
				String m_perform_status=req.getParameter("perform_status");
				String m_location_id=req.getParameter("location_id");
				String m_type = req.getParameter("type"); 
				String m_fin_no = req.getParameter("fin_no"); 
				
				if(m_perform_status.equals("ALL")){
					m_perform_status="";
				}
				
				if(m_location_id.equals("ALL")){
					m_location_id="";
				}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Gross Rental Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Gross Rental Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;
				
				
				Sql_data = " "+
					" SELECT "+
					" FINANCE_NO, "+
					" INVOICE_NO, "+
					" PREV_MONTH_GROSS "+
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE PREV_MONTH_GROSS > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
					" ORDER BY FINANCE_NO,INVOICE_NO  "+
					" ";
				
				//out.println(Sql_data);
				
				
				if(m_type.equals("prev_month_gross")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" PREV_MONTH_GROSS "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_GROSS > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				else if(m_type.equals("prev_month_closed")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" PREV_MONTH_CLOSED "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_CLOSED > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				else if(m_type.equals("prev_month_mature")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" PREV_MONTH_MATURE "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_MATURE > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				else if(m_type.equals("curr_month_new")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" CURR_MONTH_NEW "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE CURR_MONTH_NEW > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				
				else if(m_type.equals("gross_rent")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" (PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE)  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE (PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE) <> 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				
				else if(m_type.equals("net_rent")){
					Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" INVOICE_NO, "+
						" (CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE)  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE (CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE) <> 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND FINANCE_NO = '"+m_fin_no+"'  "+ 
						" ORDER BY FINANCE_NO,INVOICE_NO  "+
						" ";
				}
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='5%' class=div_input  align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Invoice No </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Amount </B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rec_count+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\" ><u>"+rs.getString(1)+"</u></td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_invoice_info_2('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(3))+" </td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(3);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// end by udara 14-01-2015
			
			
			// added by udara 26-05-2015
			
			else if(m_chksql.equals("show_drill_gross_rental_prev_2")){		
				
				String m_date=req.getParameter("date");
				String m_perform_status=req.getParameter("perform_status");
				String m_location_id=req.getParameter("location_id");
				String m_type = req.getParameter("type"); 
				
				if(m_perform_status.equals("ALL")){
					m_perform_status="";
				}
				
				if(m_location_id.equals("ALL")){
					m_location_id="";
				}
				
				
				// added by udara 27-07-2015
				String m_yard_vehicles = "";
				String m_region_wise   = "";
				String  m_active_status_string = ""; 
				String  m_region_string = ""; 
				
				m_yard_vehicles=req.getParameter("yard_vehicles");
				m_region_wise=req.getParameter("region_wise");
				
				if(!m_yard_vehicles.equals("A")){
					m_active_status_string = " AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2(FINANCE_NO,'"+m_date+"') = '"+m_yard_vehicles+"' "; 
				}
				
				if(!m_region_wise.equals("NOT_SELECT")){
					m_region_string = " AND REGION_CODE = '"+m_region_wise+"' ";
				}
				// end by udara 27-07-2015
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Gross Rental Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("function show_drill_gross_rental_prev(mm_location_id,mm_type,mm_fin_no){");
				out.println("		m_date = '"+m_date+"'; ");
				out.println("		m_location_id = mm_location_id; ");
				out.println("       m_perform_status = '"+m_perform_status+"'; ");
				out.println("       m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MISF_Gross_Rental_Report?chksql=show_drill_gross_rental_prev&date=\"+m_date+\"&location_id=\"+m_location_id+\"&perform_status=\"+m_perform_status+\"&type=\"+mm_type+\"&fin_no=\"+mm_fin_no;"); 
				out.println("       window.open(m_url,'slab_2','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Gross Rental Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;
				
				
				Sql_data = " "+
					" SELECT "+
					" FINANCE_NO, "+
					" SUM(PREV_MONTH_GROSS) "+
					" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
					" WHERE PREV_MONTH_GROSS > 0 "+
					" AND ENT_USER = '"+m_username+"' "+
					" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
					" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
					" "+m_active_status_string+"  "+ // added by udara 27-07-2015
					" "+m_region_string+"  "+ // added by udara 27-07-2015 
					" GROUP BY FINANCE_NO  "+
					" ORDER BY FINANCE_NO  "+
					" ";
				
				//out.println(Sql_data);
				
				/*
				if(m_type.equals("prev_month_gross")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM(PREV_MONTH_GROSS) "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_GROSS > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				else if(m_type.equals("prev_month_closed")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM(PREV_MONTH_CLOSED) "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_CLOSED > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				else if(m_type.equals("prev_month_mature")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM(PREV_MONTH_MATURE) "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_MATURE > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				else if(m_type.equals("curr_month_new")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM(CURR_MONTH_NEW) "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE CURR_MONTH_NEW > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				
				else if(m_type.equals("gross_rent")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM((PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE))  "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE (PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE) <> 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				
				else if(m_type.equals("net_rent")){
					Sql_data = " "+
						" SELECT "+
						    " FINANCE_NO, "+
							" SUM((CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE))  "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE (CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE) <> 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
				}
				*/
				
				
				if(m_type.equals("prev_month_gross")){
					/*
					Sql_data = " "+
						" SELECT DISTINCT "+
						    " FINANCE_NO, "+
							" PREV_MONTH_GROSS "+
							" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
							" WHERE PREV_MONTH_GROSS > 0 "+
							" AND ENT_USER = '"+m_username+"' "+
							" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
							" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
							//" GROUP BY FINANCE_NO  "+
							" ORDER BY FINANCE_NO  "+
					" ";
					*/
					
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						" DECODE(GROSS_FLAG,'Y',PREV_MONTH_GROSS,0) PREV_MONTH_GROSS  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_GROSS > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
					
					
					
				}
				else if(m_type.equals("prev_month_closed")){
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						//" PREV_MONTH_CLOSED "+
						" DECODE(CLOSE_FLAG,'Y',PREV_MONTH_CLOSED,0) PREV_MONTH_CLOSED  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_CLOSED > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
				}
				else if(m_type.equals("prev_month_mature")){
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						//" PREV_MONTH_MATURE "+
						" DECODE(MATURE_FLAG,'Y',PREV_MONTH_MATURE,0) PREV_MONTH_MATURE  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE PREV_MONTH_MATURE > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" AND MATURE_FLAG = 'Y'  "+ // udara 22-07-2015
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
				}
				else if(m_type.equals("curr_month_new")){
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						//" CURR_MONTH_NEW "+
						" DECODE(CURR_FLAG,'Y',CURR_MONTH_NEW,0) CURR_MONTH_NEW  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE CURR_MONTH_NEW > 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
				}
				
				else if(m_type.equals("gross_rent")){
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						//" (PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE)  "+
						" (DECODE(GROSS_FLAG,'Y',PREV_MONTH_GROSS,0) + DECODE(CURR_FLAG,'Y',CURR_MONTH_NEW,0) - DECODE(CLOSE_FLAG,'Y',PREV_MONTH_CLOSED,0) - DECODE(MATURE_FLAG,'Y',PREV_MONTH_MATURE,0))  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE (DECODE(GROSS_FLAG,'Y',PREV_MONTH_GROSS,0) + DECODE(CURR_FLAG,'Y',CURR_MONTH_NEW,0) - DECODE(CLOSE_FLAG,'Y',PREV_MONTH_CLOSED,0) - DECODE(MATURE_FLAG,'Y',PREV_MONTH_MATURE,0)) <> 0 "+
						//" WHERE (PREV_MONTH_GROSS + CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE) <> 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
				}
				
				else if(m_type.equals("net_rent")){
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						" (CURR_MONTH_NEW - PREV_MONTH_CLOSED - PREV_MONTH_MATURE)  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						" WHERE (DECODE(CURR_FLAG,'Y',CURR_MONTH_NEW,0) - DECODE(CLOSE_FLAG,'Y',PREV_MONTH_CLOSED,0) - DECODE(MATURE_FLAG,'Y',PREV_MONTH_MATURE,0)) <> 0 "+
						" AND ENT_USER = '"+m_username+"' "+
						" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ // added by udara 27-07-2015
						" "+m_region_string+"  "+ // added by udara 27-07-2015 
						//" GROUP BY FINANCE_NO  "+
						" ORDER BY FINANCE_NO  "+
						" ";
				}
				
				// added by udara 30-07-2015
				else if(m_type.equals("non_perform")){
					
					Sql_data = " "+
						" SELECT DISTINCT "+
						" FINANCE_NO, "+
						" DECODE(GROSS_FLAG,'Y',NON_PERFORM_VALUE,0) NON_PERFORM_VALUE  "+
						" FROM "+m_schema_name+".AF_TBD_GROSS_RENTAL_RPT "+
						//" WHERE NON_PERFORM_VALUE > 0 "+ // commented by udara 14-09-2015
						" WHERE ((NON_PERFORM_VALUE > 0) OR (NON_PERFORM_VALUE < 0)) "+ // added by udara 14-09-2015
						" AND ENT_USER = '"+m_username+"' "+
						//" AND PERFORM_STATUS LIKE '"+m_perform_status+"%' "+ // commented by udara 05-08-2015
						" AND BRANCH_CODE LIKE '"+m_location_id+"%'  "+
						" "+m_active_status_string+"  "+ 
						" "+m_region_string+"  "+ 
						" ORDER BY FINANCE_NO  "+
						" ";
					
					
					
				}
				// end by udara 30-07-2015
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='5%' class=div_input  align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Amount </B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rec_count+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\" ><u>"+rs.getString(1)+"</u></td>"); 
					//out.println("<td class='factoring-letter-body' STYLE='text-align:right; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_drill_gross_rental_prev('"+m_location_id+"','"+m_type+"','"+rs.getString(1)+"');\"  ><u> "+nf.format(rs.getDouble(2))+" </u></td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right; ' bgcolor='"+m_td_color+"'   > "+nf.format(rs.getDouble(2))+" </td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// end by udara 26-05-2015
			
			
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs_drill_new!=null){try{rs_drill_new.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
		}
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			
			if(out!=null){try{out.flush();out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}