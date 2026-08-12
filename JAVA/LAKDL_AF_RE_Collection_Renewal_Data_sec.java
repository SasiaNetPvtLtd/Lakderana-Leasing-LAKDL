
//SCREEN NAME:ASSET INSURANCE DETAIL FOR MANAGEMENT INFORMATION COLLECTION 
//CREATED BY:SANDUN
//DATE/TIME:02/10/2008
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import java.math.BigDecimal;

public class LAKDL_AF_RE_Collection_Renewal_Data_sec extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
	public ResultSet rs,rs1,rs2;
	PreparedStatement pstmt;
	java.text.NumberFormat nf;
	
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
			
			//prabash----------------TAX selecter--------------01-06-2011--------**	
			rs2=stmt.executeQuery(" SELECT A.tax_code,A.TAX_DESC "+ 
				" FROM "+m_schema_name+".AF_CO_MAS_INS_TAX A "+		
				" WHERE A.active_status= 'Y' ");
			
			//-------------------------------------------------------------------**
			
			if(m_screen_type.trim().equals("main_page")){	
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				//		//prabash----------------TAX selecter--------------01-06-2011--------**	
				//	  			rs2=stmt.executeQuery(" SELECT A.tax_code "+ 
				//			 		" FROM "+m_schema_name+".AF_CO_MAS_INS_TAX A "+		
				//					" WHERE A.active_status= 'Y' ");
				
				//		//-------------------------------------------------------------------**
				
				out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
					out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+document.Form1.hid_status.value;"); 
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
				out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='4'){"); 
				out.println("		finance_assign(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='99'){"); 
				out.println("		company_assign_99(oBj);"); 
				out.println("	}");
				out.println("	}"); 
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
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}");  
				
				out.println("function clear_fields(){ ");
				out.println("if(document.Form1.hid_help_type.value==\"4\"){");
				out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"99\"){");
				out.println("document.Form1.TXT_INSU_COM.value=\"\";");
				out.println("}");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function clear_window_2(){	"); 
				//out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=main_page';"); 
				//out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=main_page';"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println(" if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
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
				
				out.println("function check_date(objdd,objmm,objyy) {");						
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");
				//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("}");
				out.println("}");
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				//	out.println("alert(document.Form1.hid_from_date.value);");
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
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" request_details.innerHTML = ''; ");
				out.println(" request_details.innerHTML = http_response; ");
				out.println("}");
				
				out.println("function main_date_chk(){");
				out.println("if(document.Form1.VAL_DAY1.value==\"\" || document.Form1.VAL_MONTH1.value==\"\" || document.Form1.VAL_YEAR1.value==\"\"){;");
				out.println("alert('Enter valid From Date...!');");
				out.println("return false;");
				out.println("}else");	
				out.println("if(document.Form1.VAL_DAY2.value==\"\" || document.Form1.VAL_MONTH2.value==\"\" || document.Form1.VAL_YEAR2.value==\"\"){;");
				out.println("alert('Enter valid To Date...!');");	
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");	
				out.println("}");
				
				
				out.println("function makeRequest(){");
				out.println("if(main_date_chk()){");
				out.println("m_scr_name=document.Form1.hid_status.value");
				out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				//out.println("m_opt = document.Form1.TXT_INSURANCE_DONE.value");
				//out.println("m_opt = document.Form1.TXT_DEVISION.value");//Commented By Kanishka On 03-07-2014
				out.println("m_opt = \"%\"; ");//Added by kd
				out.println("finance_no = document.Form1.TXT_FINANCE_NO.value;");
				out.println("m_done_by = document.Form1.TXT_INSURANCE_DONE.value;");
				out.println("m_insu_com = document.Form1.TXT_INSU_COM.value;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=load_details&sr_name=\"+m_scr_name+\"&finance_no=\"+finance_no+\"&devision_type=\"+m_opt+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&done_by=\"+m_done_by+\"&insu_com=\"+m_insu_com+\"\";");
				//out.println("window.open(m_url);");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				out.println("}");
				
				
				out.println("function load_scr(val1,val2,val3,val4,val5,val6){");
				out.println("ast_des=document.Form1.elements['TXT_ASSET_DETA_'+val3].value;");	
				//out.println("alert(ast_des)");
				out.println("foundAtStartPos = ast_des.indexOf('@');");
				out.println("foundAtEndPos = ast_des.lastIndexOf('@');");
				out.println("description = ast_des.substring(0,foundAtStartPos);");
				//out.println("alert(description)");
				out.println("invoice_no= ast_des.substring(foundAtStartPos+1,foundAtEndPos);");
				//out.println("alert(invo_no)");
				out.println("m_scr_name=document.Form1.hid_status.value;");
				out.println("m_business_type='NEW';");
				out.println("m_price_no=document.Form1.hid_pricing_no.value;"); // added by udara 13-02-2014
				out.println("m_app_no=val6;"); // added by udara 17-02-2014
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=load_details_btt&finance_no=\"+val1+\"&clint_name=\"+val2+\"&asst_deta=\"+val3+\"\";");//
				
				out.println(" clear_window_2(); "); // added by udara 19-05-2014
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=load_details_btt&sr_name=\"+m_scr_name+\"&invo_no=\"+invoice_no+\"&finance_no=\"+val1+\"&clint_name=\"+val2+\"&asst_deta=\"+description+\"&business_type=\"+m_business_type+\"&policy_no=\"+val4+\"&debit_note_no=\"+val5+\"&price_no=\"+m_price_no+\"&app_no=\"+m_app_no;");
				out.println("window.open(m_url,'displayWindow2','left=175,top=60,width=900,height=600,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function help_finance() {"); 
				out.println("document.Form1.hid_help_type.value=\"4\";"); 
				out.println("Crit = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','4');");
				out.println("}");
				
				out.println("function finance_assign(oBj) {");				
				out.println("document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_insu_com() {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_insurance_company_code\";"); 
				out.println("    m_criteria = document.Form1.TXT_INSU_COM.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function company_assign_99() {");
				out.println("    document.Form1.TXT_INSU_COM.value=oBj.valout[2];"); 
				//out.println("    document.Form1.txt_in_company.value=oBj.valout[3];"); 
				out.println("}"); 
				
				// added by udara 13-02-2014
				out.println("function select_assets(app_no,div_type,obj,sel_type) {"); 
				out.println(" set_pricing_no(app_no,div_type,obj.value,sel_type);");
				out.println("}");
				
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
			    out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			    out.println("    window.open(m_url); ");
			    out.println(" }");
				
				out.println("function set_pricing_no(m_app_no,m_devision_type,m_check_value,m_sel_type){"); 
				
				out.println("   if(m_sel_type=='new') {");
				
				out.println("	    document.Form1.hid_chk_status.value='set_pricing_no'; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=set_pricing_no&app_no=\"+m_app_no+\"&devision_type=\"+m_devision_type+\"&check_value=\"+m_check_value;");
				out.println("		load_interface(m_url,'XML');");
				
				out.println("   }");
				
				out.println("   else if(m_sel_type=='edit') {");
				
				out.println("	    document.Form1.hid_chk_status.value='set_pricing_no'; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=set_pricing_no_edit&app_no=\"+m_app_no+\"&devision_type=\"+m_devision_type+\"&check_value=\"+m_check_value;");
				//out.println("    window.open(m_url); ");
				out.println("		load_interface(m_url,'XML');");
				
				out.println("   }");
				
				out.println("}"); 
				
				out.println("function get_vector(data_vec) {");
				out.println("	if(data_vec.length>0  && document.Form1.hid_chk_status.value=='set_pricing_no' ){");
				//out.println("			alert(data_vec[0]);");
				out.println("           document.Form1.hid_pricing_no.value = data_vec[0]; ");
				out.println("	}");
				out.println("}");
				// end by udara 13-02-2014
				
				out.println("</Script>");
				
				out.println("<body onload=\"load_sysdate();load_screen_status('EDIT');\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">"); // added by udara 13-02-2014
                out.println("<INPUT TYPE='Hidden' NAME='hid_pricing_no' VALUE=\"\">"); // added by udara 13-02-2014
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' id='hid_start_date_diff' NAME='hid_start_date_diff' VALUE=\"\">"); //Added by Kanchana.
				//out.println("<input type='hidden' name='hid_invo_no_0' value=\"\">");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Prossess - Asset Insurance Details </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				//	out.println("<tr>");
				//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"EDIT\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\" style='display:none' ></td>"); // commented by udara 20-03-2014
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"  ></td>");
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
				out.println("<table align='center' width='100%' class='table' border='0'>"); 			
				
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=VDATE>From</td>");
				out.println("<td width='8%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='2%'></td>"); 
				out.println("<td width='2%' ID=VDATE>To</td>");
				out.println("<td width='15%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='10%'></td>"); 
				out.println("</tr>");	
				//out.println("<tr width=100%><td>&nbsp;</td>"); 
				//out.println("</tr >"); 
				/*out.println("<tr >");   // Remove by kanishka on 3-07-2014
				
				
				out.println("<td width='9%'>Division</td>"); //Mod By SJ on 08-12-2008
				out.println("<td width='15%'><select name='TXT_DEVISION' class='txt_input' style=\"width:100px;\" onchange=\"\">");
				out.println("<option value=\"BIKE\" >Bike</option>");
				out.println("<option value=\"LEASE\" selected>Leasing</option>");			
				out.println("</select>");
				out.println("</td>");
				
				out.println("</tr>"); */
				out.println("<tr class=tr_input>"); 
				
				out.println("<td  width='9%' ><DIV id='DIV_TXT_INSU_COM'  class=div_input>Insurance Company</DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_INSU_COM' maxlength='20' style='width:100'  OnBlur=\"help_insu_com()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_INSU_COM' value=\"...\" onClick=\"help_insu_com()\" > </td>"); 
				out.println("<td width='5%'></td>");

				
				out.println("<td width='9%'>Insurance Done By</td>");  //Commented By SJ on 08-12-2008
				out.println("<td width='15%'><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:100px;\" onchange=\"\">");
				out.println("<option value=\"LICENSEE\" >Licensee</option>"); // Company
				//out.println("<option value=\"BROKER\" >Broker</option>");
				out.println("<option value=\"CLIENT\" >Lessee</option>"); // out.println("<option value=\"CLIENT\" >Company</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr >"); 
				
				out.println("<tr class=tr_input>"); 
				out.println("<td  width='9%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' style='width:100'  OnBlur=\"help_finance()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_finance()\" > </td>"); 
				out.println("<td width='5%'></td>");
				
				//out.println("<td width='1%'></td>"); 
				//out.println("<td width='2%'></td>");
				out.println("<td width='8%' align='left'><input type=\"button\" class='mainbut'onClick='makeRequest()' value=\"Go\"></td>"); 
				out.println("<td width='20%'></td>"); 
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");
				
				
			}else	if(m_screen_type.equals("load_details")){
				
				//String m_insuarane_type= req.getParameter("insuarane_type"); 
				String m_devision_type= req.getParameter("devision_type"); //Added By Sanudn on 08-12-2008
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date");
				String m_scr_name=req.getParameter("sr_name");
				String m_finance_no=req.getParameter("finance_no");
				
				
				if(m_scr_name.equals("New")){
					int j=1;
					
					if(m_devision_type == null){
						m_devision_type = "";
					}
					
					if(m_devision_type.equals("BIKE")){ //Added By Sanudn on 08-12-2008
						m_devision_type = "BD";
					}else if(m_devision_type.equals("LEASE")){
						m_devision_type = "AF";
					}
					
					if(m_finance_no.equals("")){
						
						//out.println(" "+
						rs=stmt.executeQuery(" "+
							" SELECT DISTINCT A.FINANCE_NO, "+//1
							" A.CLIENT_CODE, "+//2
							" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//3
							" DECODE(A.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//4
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//5
							" A.INSURANCE_DONE_BY, "+//6
							" A.APPLICATION_NO "+//7
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
							"      "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE B "+                                                     
							" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
							" AND (A.FINANCE_NO ,B.SECURITY_ID) NOT IN( "+
							" SELECT C.FINANCE_NO ,C.SECURITY_ID "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC C "+
							" ) "+
							
							//" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA WHERE FINANCE_NO = A.FINANCE_NO AND ACTIVE_STATUS = 'N' )  "+ // added by udara 20-07-2018
							
							// " AND A.INSURANCE_DONE_BY ='"+m_insuarane_type+"' "+ 
						//	" AND A.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008  //Commetned By KD
							
							//" AND A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara20-02-2014
							//" AND A.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // commented by udara20-02-2014
							" AND TRUNC(A.ENT_DATE) <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara20-02-2014
							" AND TRUNC(A.ENT_DATE) >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara20-02-2014
							" AND (A.FINANCE_NO )  IN( "+
							" SELECT C.FINANCE_NO  "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC C "+
							" ) "+
							//" AND A.APPLICATION_STATUS='ACTIVATED' "); // commented by udara 24-11-2015 // released by udara 09-07-2014   
							" AND A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "); // added by udara 24-11-2015
							//" AND A.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI') "); // commented by udara 09-07-2014
					}
					else{
						rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
							" A.CLIENT_CODE, "+//2
							" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//3
							" DECODE(A.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//4
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//5
							" A.INSURANCE_DONE_BY, "+//6
							" A.APPLICATION_NO "+//7
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
							"      "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE B "+                                                     
							" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
							" AND (A.FINANCE_NO ,B.SECURITY_ID) NOT IN( "+
							"                    SELECT C.FINANCE_NO ,C.SECURITY_ID "+
							"                    FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC C "+
							"                    ) "+
							
						//	" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA WHERE FINANCE_NO = A.FINANCE_NO AND ACTIVE_STATUS = 'N' )  "+ // added by udara 20-07-2018
							
							// " AND A.INSURANCE_DONE_BY ='"+m_insuarane_type+"' "+ 
							//" AND A.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008  Commented By KD
							" AND A.FINANCE_NO = '"+m_finance_no+"' "+
							
							
							//" AND A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							//" AND A.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
							
							" AND TRUNC(A.ENT_DATE) <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara20-02-2014
							" AND TRUNC(A.ENT_DATE) >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara20-02-2014
							//" AND A.APPLICATION_STATUS='ACTIVATED' "); // commented by udara 24-11-2015 // released by udara 09-07-2014
							" AND A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "); // added by udara 24-11-2015
							//" AND A.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI') "); // commented by udara 09-07-2014
					}
					
					
					
					boolean more = rs.next();
					if(!more){
						out.println("<table align='center' width='100%' class='table' border=0>");
						out.println("<tr>"); 
						out.println("<td width='20%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
						out.println("</tr>"); 
						out.println("<table>");
					}
					if(more){
						out.println("<table align='center' width='100%' class='table' border=0>"); 
						out.println("<tr class=pdn_txtpos2>"); 
						out.println("<td width='20%' align ='left'>Finance No.</td>"); 
						out.println("<td width='25%' align ='left'>Client Name</td>"); 
						out.println("<td width='10%' align ='left'>Status</td>"); 
						out.println("<td width='20%' align ='center'>Asset Description</td>"); 
						out.println("<td width='10%' align ='center'>Details</td>"); 
						out.println("</tr >"); 
					}
					while(more){
						
						if(j%2==1){
							out.println("<tr class=tr_input>"); 
						}
						else{
							out.println("<tr class=tr_input1>"); 
						}
						out.println("<input type=hidden name=\"app_no\" value=\""+rs.getString(7)+"\"></td>");
						out.println("<td width='20%' align ='left'>"+rs.getString(1)+"</td>"); 
						out.println("<td width='25%' align ='left'>"+rs.getString(5)+"</td>"); 
						out.println("<td width='10%' align ='left'>"+rs.getString(4)+"</td>");		
						out.println("<td width='20%' align ='center'>"); 
						//out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\">"); // commented by udara 13-02-2014
						out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\" onblur=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',this,'new'); \" >"); // added by udara 13-02-2014
															
						
						rs1=stmt1.executeQuery("SELECT  A.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.VEHICLE_NO, "+//A.REG_NO Added By SJ on 28-11-2008
							"  A.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.VEHICLE_NO || '@' || A.SECURITY_ID || '@' "+
							" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE A,"+
							"	   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D"+
							" WHERE A.APPLICATION_NO=D.APPLICATION_NO "+
							" AND A.APPLICATION_NO='"+rs.getString(7)+"' "+				
							" AND (D.FINANCE_NO ,A.SECURITY_ID) NOT IN( "+
							" SELECT E.FINANCE_NO ,E.SECURITY_ID "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC E )");	
						
						boolean more1 = rs1.next();
						while(more1){			
							out.println("<option value=\""+rs1.getString(2)+"\" >"+rs1.getString(1)+"</option>");	
							more1 = rs1.next();
							
						}
						out.println("</select></td>");			
						//out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"');\"></td>");  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value--onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"',"+j+"')\"
						
						//out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',document.Form1.TXT_ASSET_DETA_"+j+",'new'); setTimeout(function(){load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"')},2000);\"></td>"); // udara 19-03-2014
						out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',document.Form1.TXT_ASSET_DETA_"+j+",'new'); setTimeout(function(){load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"','','','"+rs.getString(7)+"')},2000);\"></td>"); 
						
						//out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',document.Form1.TXT_ASSET_DETA_"+j+",'new'); setTimeout(function(){load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"','','','"+rs.getString(7)+"')},2000);\"></td>");
						
						out.println("</tr>");
						
						more = rs.next();
						j=j+1;
					}
					
					out.println("</table >"); 	
					
					
				}
				
				// added by udara 21-05-2014
				else if(m_scr_name.equals("Edit")){			
					int j=1;
					
					if(m_devision_type == null){
					m_devision_type="";
					}
					
					if(m_devision_type.equals("BIKE")){ 
						m_devision_type = "BD";
					}else if(m_devision_type.equals("LEASE")){
						m_devision_type = "AF";
					}
					
				String m_done_by=req.getParameter("done_by");
				String m_insu_com=req.getParameter("insu_com");
				
				if(m_insu_com.equals("")){
						m_insu_com = "%";
				}
					
					
					if(m_finance_no.equals("")){	
						rs=stmt.executeQuery(" "+
						//out.println(" "+
							" SELECT DISTINCT A.FINANCE_NO, "+//1 // " SELECT A.FINANCE_NO, "+//1
							" A.ASSET_DESCRIPTION, "+//2
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
							" B.ACTIVATED_DATE, "+ //4
							" DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated','REPOSSESS','Repossess'), "+//5 // modified by udara 08-12-2015
							" A.SECURITY_ID, "+//6
							" B.APPLICATION_NO, "+//7
							" A.POLICY_NO, "+ //8
							" A.DEBIT_NOTE_NO, "+ //9
							" B.CLIENT_CODE, "+//10
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'),  "+ // 11 udara 21-10-2014
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM) "+ // 12 udara 21-10-2014
							" ,"+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO) "+ // 13 Jithendra 06-10-2016
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
							//" AND B.APPLICATION_STATUS='ACTIVATED' "+ // commented by udara 08-12-2015	 // commented by udara 20-02-2014 // released by udara 01-08-2014
							" AND B.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "+ // commented by udara 08-12-2015
							//" AND B.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI','CANCEL') "+ // " AND B.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI') "+ // added by udara 20-02-2014
							//" AND UPPER(A.BUSINESS_TYPE) = 'NEW' "+//COMMENTED BY MILINDA 2014-06-
							//" AND UPPER(A.BUSINESS_TYPE) IN('NEW','REBEWAL') "+ // commented by udara 12-09-2014 //ADDED MIILNDA FOR TEST ENVIRONMENT VIEW UPLOADED DATA
							" AND UPPER(A.BUSINESS_TYPE) IN('NEW','RENEWAL') "+ // added by udara 12-09-2014
							
							//AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA WHERE FINANCE_NO = A.FINANCE_NO AND ACTIVE_STATUS = 'N' )  "+ // added by udara 20-07-2018
							
							" AND A.INSUR_COM LIKE '"+m_insu_com+"' "+ // thamali 2014.08.21
							" AND B.INSURANCE_DONE_BY = '"+m_done_by+"' "+ // thamali 2014.08.21
							
							//" AND B.DIVISION_CODE  = '"+m_devision_type+"'  "+ // cpommented by udara 21-07-2014  //Added By Sanudn on 08-12-2008
							//" AND B.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara 22-04-2014
							//" AND B.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "); // commented by udara 22-04-2014
							" AND A.END_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara 22-04-2014
							" AND A.END_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+ // added by udara 22-04-2014
							" AND B.TRANSACTION_TYPE <> 'LOANS'  "+ // added by udara 10-10-2018
							" and a.ENT_DATE in (select max(ent_date) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC where FINANCE_NO = a.FINANCE_NO ) "+ // added by udara on 20-10-2014
							
						//	" and a.ENT_DATE in (select max(TO_DATE(ent_date,'DD-MM-YYYY')) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where FINANCE_NO = a.FINANCE_NO ) ");//tempp commented milinda
						// " AND A.INSURED_BY ='"+m_insuarane_type+"' ");
						" ");
						
					}
					else{
						rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
							" A.ASSET_DESCRIPTION, "+//2
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
							" B.ACTIVATED_DATE, "+ //4
							" DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated','REPOSSESS','Repossess'), "+//5 // modified by udara 08-12-2015 // " DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//5
							" A.SECURITY_ID, "+//6
							" B.APPLICATION_NO, "+//7
							" A.POLICY_NO, "+ //8
							" A.DEBIT_NOTE_NO, "+ //9
							" B.CLIENT_CODE, "+//10
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'),  "+ // 11 udara 21-10-2014
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM) "+ // 12 udara 21-10-2014
							" ,"+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO) "+ // 13 Jithendra 06-10-2016
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
							//" AND B.APPLICATION_STATUS ='ACTIVATED' "+ // added by udara 08-12-2015 // commented by udara 20-02-2014 // released by udara 01-08-2014
							" AND B.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "+ // commented by udara 08-12-2015
							//" AND B.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI','CANCEL') "+ // " AND B.APPLICATION_STATUS NOT IN ('TERM','TERMINATED','NORM_TERMI') "+ // added by udara 20-02-2014
							//" AND A.BUSINESS_TYPE  = 'NEW' "+ // commented by udara 28-01-2015
							" AND UPPER(A.BUSINESS_TYPE) IN ('NEW','RENEWAL') "+ // added by udara 28-01-2015
							
							//" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_INIT_INSURANCE_DATA WHERE FINANCE_NO = A.FINANCE_NO AND ACTIVE_STATUS = 'N' )  "+ // added by udara 20-07-2018
							
							" AND A.FINANCE_NO = '"+m_finance_no+"' "+
							" AND A.INSUR_COM LIKE '"+m_insu_com+"' "+ // thamali 2014.08.21
							" AND B.INSURANCE_DONE_BY = '"+m_done_by+"' "+ // thamali 2014.08.21

							//" AND B.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008
							//" AND B.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // commented by udara 22-04-2014
							//" AND B.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "); // commented by udara 22-04-2014
							" AND A.END_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara 22-04-2014
							" AND A.END_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara 22-04-2014
							" AND B.TRANSACTION_TYPE <> 'LOANS'  "+ // added by udara 10-10-2018
							" and a.ENT_DATE in (select max(ent_date) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC where FINANCE_NO = a.FINANCE_NO ) ");
						// " AND A.INSURED_BY ='"+m_insuarane_type+"' ");
						
					}
					
					boolean more = rs.next();
					
					if(!more){
						out.println("<table align='center' width='100%' class='table' border=0>");
						out.println("<tr>"); 
						out.println("<td width='20%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
						out.println("</tr>"); 
						out.println("<table>");
					}
					if(more){
						out.println("<table align='center' width='100%' class='table' border=0>"); 
						out.println("<tr class=pdn_txtpos2>"); 
						out.println("<td width='20%' align ='left'>Finance No.</td>"); 
						out.println("<td width='20%' align ='left'>Client Name</td>"); 
						out.println("<td width='10%' align ='left'>Status</td>"); 
						out.println("<td width='20%' align ='center'>Asset Description</td>"); 
						
						out.println("<td width='10%' align ='left'>Vehicle No</td>"); // added by Jithendra 06-10-2016
						
						out.println("<td width='10%' align ='left'>Renewal Date</td>"); // added by udara 21-10-2014
						out.println("<td width='20%' align ='left'>Insurance Company</td>"); // added by udara 21-10-2014
						
						out.println("<td width='10%' align ='center'>Details</td>"); 
						out.println("</tr >"); 
					}
					while(more){
						
						if(j%2==1){
							out.println("<tr class=tr_input>"); 
						}
						else{
							out.println("<tr class=tr_input1>"); 
						}
						out.println("<input type=hidden name=\"app_no\" value=\""+rs.getString(7)+"\"   ></td>");
						out.println("<td width='20%' align ='left' style='{cursor:hand; }' onclick = \"show_transaction_info('"+rs.getString(10)+"','"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>"); 
						out.println("<td width='20%' align ='left'>"+rs.getString(3)+"</td>"); 
						out.println("<td width='10%' align ='left'>"+rs.getString(5)+"</td>");		
						out.println("<td width='20%' align ='center'>"); 
						
						//out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\">"); // commented by udara 20-02-2014
						out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\" onblur=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',this,'edit'); \" >"); // added by udara 20-02-2014
						
						rs1=stmt1.executeQuery(" SELECT A.ASSET_DESCRIPTION , "+
							" A.ASSET_DESCRIPTION || '@' || A.SECURITY_ID ||'@'"+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A "+
							" WHERE A.FINANCE_NO = '"+rs.getString(1)+"'  GROUP BY A.ASSET_DESCRIPTION,A.SECURITY_ID ");
						//" AND A.INSURED_BY ='"+m_insuarane_type+"'");
						
						boolean more1 = rs1.next();
						while(more1){			
							out.println("<option value=\""+rs1.getString(2)+"\" >"+rs1.getString(1)+"</option>");
							more1 = rs1.next();
						}
						out.println("</select></td>");
						//out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(3)+"',"+j+",'"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(7)+"')\"></td>"); // commented by udara 20-02-2014  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value
						//out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',document.Form1.TXT_ASSET_DETA_"+j+"); setTimeout(function(){load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"')},2000);\"></td>"); // added by udara 20-02-2014
						out.println("<td width='10%' align ='left'> "+rs.getString(13)+" </td>"); // added by Jithendra 06-10-2016
						
						out.println("<td width='10%' align ='left'> "+rs.getString(11)+" </td>"); // added by udara 21-10-2014
						out.println("<td width='20%' align ='left'> "+rs.getString(12)+" </td>"); // added by udara 21-10-2014
						
						out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"select_assets('"+rs.getString(7)+"','"+m_devision_type+"',document.Form1.TXT_ASSET_DETA_"+j+",'edit'); setTimeout(function(){load_scr('"+rs.getString(1)+"','"+rs.getString(3)+"','"+j+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(7)+"')},2000);\"></td>"); // added by udara 20-02-2014
						
						out.println("</tr>"); 			
						more = rs.next();
						j=j+1;
					}
					
					out.println("</table >"); 			
					
					
				}
			}
			
			else	if(m_screen_type.equals("load_details_btt")){
				
				String m_finance_no=req.getParameter("finance_no");
				String m_cli_name=req.getParameter("clint_name");
				String m_asset_deta=req.getParameter("asst_deta");
				String m_invoice_no=req.getParameter("invo_no");
				String m_scr_name=req.getParameter("sr_name");
				String m_business_type=req.getParameter("business_type");
				String mm_policy_no=req.getParameter("policy_no");
				String mm_debit_note_no=req.getParameter("debit_note_no");
				String m_price_no = req.getParameter("price_no"); // added by udara 13-02-2014
				String m_app_no = req.getParameter("app_no");  
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");
				
				
				// commented by udara 05-12-2014
				/*
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println(" document.Form2.txt_policy_no.value=\"\";");
				out.println(" document.Form2.start_dd.value=\"\";");
				out.println(" document.Form2.start_mm.value=\"\";");
				out.println(" document.Form2.start_yy.value=\"\";");
				out.println(" document.Form2.end_dd.value=\"\";");
				out.println(" document.Form2.end_mm.value=\"\";");
				out.println(" document.Form2.end_yy.value=\"\";");
				out.println(" document.Form2.txt_sum_in.value=\"\";");
				out.println(" document.Form2.txt_premium.value=\"\";");
				out.println(" document.Form2.txt_in_company.value=\"\";");
				//out.println(" document.Form2.txt_insurance_done.value=\"\";");
				out.println(" document.Form2.txt_Debit_Note_no.value=\"\";");
				out.println(" document.Form2.TXT_REMARK.value=\"\";");
				out.println(" document.Form2.txt_rcc.value=\"\";");
				out.println(" document.Form2.txt_tc.value=\"\";");
				out.println(" document.Form2.txt_rcc_tc.value=\"\";");
				out.println(" document.Form2.txt_tax_total.value=\"\";");
				out.println(" document.Form2.txt_bas_pre.value=\"\";");
				out.println(" document.Form2.txt_comm_rate.value=\"\";");
				out.println(" document.Form2.txt_bas_com.value=\"\";");
				out.println(" document.Form2.txt_rcc_comm_rate.value=\"\";");
				out.println(" document.Form2.txt_rcc_comm.value=\"\";");
				out.println(" document.Form2.txt_tot_com.value=\"\";");
				out.println(" document.Form2.txt_vat_total_com.value=\"\";");
				out.println(" m_table_tax_charges.innerHTML=\"\" ");
				out.println(" document.Form2.txt_tax.value=\"NO\";");			
				out.println("		}"); 
				out.println("}"); 
				*/
				
				// added by udara 05-2014
				out.println("function clear_window(){	"); 
				
				out.println("           var m_chksql = 'load_details_btt'; ");	
			    out.println("           var m_sr_name = '"+m_scr_name+"'; "); 
				out.println("           var m_finance_no = '"+m_finance_no+"'; "); 
				out.println("           var m_clint_name = '"+m_cli_name+"'; "); 
				out.println("           var m_asst_deta = '"+m_asset_deta+"'; "); 
				out.println("           var m_business_type = '"+m_business_type+"'; "); 
				out.println("           var m_policy_no = '"+mm_policy_no+"'; "); 
				out.println("           var m_debit_note_no = '"+mm_debit_note_no+"'; "); 
				out.println("           var m_price_no = '"+m_price_no+"'; "); 
				out.println("           var m_app_no = '"+m_app_no+"'; "); 
				out.println("           var m_invoice_no = '"+m_invoice_no+"'; ");
				
				
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("          m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Renewal_Data_sec?chksql=load_details_btt&sr_name=\"+m_sr_name+\"&invo_no=\"+m_invoice_no+\"&finance_no=\"+m_finance_no+\"&clint_name=\"+m_clint_name+\"&asst_deta=\"+m_asst_deta+\"&business_type=\"+m_business_type+\"&policy_no=\"+m_policy_no+\"&debit_note_no=\"+m_debit_note_no+\"&price_no=\"+m_price_no+\"&app_no=\"+m_app_no;");
				out.println("		   window.location.href=m_url;"); 
				out.println("		}"); 

				out.println("}"); 
				// end by udara 05-12-2014
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form2.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println(" load_c_date(document.Form2.hid_cal_date.value);");
				out.println("}");
				
				
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
				out.println("  if(document.Form2.hid_cal_date.value=='2'){"); 
				out.println("  document.Form2.hid_chk_status.value='start_date_check';");				
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form2.start_dd.value=v_date;");
				out.println("     document.Form2.start_mm.value=v_month;");
				out.println("     document.Form2.start_yy.value=val;");
				//Added by kanchana to change end date according to start date via calender-- on 2016-01-14
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=start_date_check_sec&hidden_policy_no=\"+document.Form2.hid_policy_no.value+\"&hidden_debit_note_no=\"+document.Form2.hid_debit_note_no.value+\"&start_date=\"+v_date+\"-\"+v_month+\"-\"+val;");
				out.println("		load_interface(m_url,'XML');");
				out.println(" function get_vector(data_vec) {");
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='start_date_check' ){");				
				out.println("     document.Form2.end_dd.value=data_vec[3];");
				out.println("     document.Form2.end_mm.value=data_vec[4]");
				out.println("     document.Form2.end_yy.value=data_vec[5];");	
				out.println("}");
				//endded by kanchana.
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form2.hid_from_date.value=date1");
				out.println("     document.Form2.start_yy.focus();"); // added by udara 11-02-2014				
				out.println("}");
				out.println("}");
				
				out.println("  if(document.Form2.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form2.end_dd.value=v_date;");
				out.println("     document.Form2.end_mm.value=v_month;");
				out.println("     document.Form2.end_yy.value=val;");
				out.println("date2=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
				out.println("document.Form2.hid_to_date.value=date2");
				
				out.println("}");
				
				out.println("}");
				
				
				
				
				
				
				
				
				//Added by Kanchana.
				out.println("function make_request_date() {");
				//out.println("			alert('AAAAAAAAAA');");
				out.println("	document.Form2.hid_chk_status.value='check_date_range'; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=check_date_range_sec&hidden_fin_no=\"+document.Form2.hid_fi_no_val.value+\"&start_date=\"+document.Form2.start_dd.value+\"-\"+document.Form2.start_mm.value+\"-\"+document.Form2.start_yy.value;");
			//	out.println("			alert(''+document.Form2.hid_chk_status.value);");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function start_date_check() {");
			//	out.println("			alert('CCCCCCCCC');");
				out.println("	document.Form2.hid_chk_status.value='start_date_check'; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=start_date_check_sec&hidden_policy_no=\"+document.Form2.hid_policy_no.value+\"&hidden_debit_note_no=\"+document.Form2.hid_debit_note_no.value+\"&start_date=\"+document.Form2.start_dd.value+\"-\"+document.Form2.start_mm.value+\"-\"+document.Form2.start_yy.value;");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");

				//Ended by Kanchana.=============================================
				
								// added by udara 11-02-2014
				out.println("function set_end_date(){"); 
				out.println("	document.Form2.hid_chk_status.value='set_end_date'; ");
				
				out.println("   if( (document.Form2.start_dd.value != '') && (document.Form2.start_mm.value != '') && (document.Form2.start_yy.value != '') ) {   ");
			//	out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=set_end_date&start_date=\"+document.Form2.start_dd.value+\"-\"+document.Form2.start_mm.value+\"-\"+document.Form2.start_yy.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=set_end_date&start_date=\"+document.Form2.start_dd.value+\"-\"+document.Form2.start_mm.value+\"-\"+document.Form2.start_yy.value;");
				out.println("		load_interface(m_url,'XML');");
				
			//	out.println("   alert(data_vec[0]+ data_vec[1]+data_vec[2]);"); 
				out.println("   }"); 
				
				out.println("}"); 
				// end by udara 11-02-2014
				
		
				
				// added by udara 21-05-2014
				out.println("function submit_data(){");
				
				//out.println(" get_flag_status(); "); // added by udara 20-07-2018
				
				out.println("if(validate_data()){");
				
				out.println("    document.Form2.txt_policy_no.disabled=false;");
			
				
				out.println("    if(document.Form2.txt_hid_Debit_Note_no.value ==  document.Form2.txt_Debit_Note_no.value){ ");
				out.println("		alert('Debit note number cannot be the same');  ");
				out.println("       document.Form2.txt_Debit_Note_no.value = ''; ");
				out.println("       document.Form2.txt_Debit_Note_no.focus(); ");
				out.println("    } ");
				out.println("    else{ ");
				
			
				
				out.println("    if(document.Form2.hid_ins_done_by.value=='LICENSEE'){    ");
				out.println("	 	if(confirm(\"Are you sure, you want to save data?\")){ ");
	
					out.println("       	document.Form2.txt_sum_in.value  = unformat_noobject(document.Form2.txt_sum_in.value); ");
					out.println("       	document.Form2.txt_premium.value = unformat_noobject(document.Form2.txt_premium.value); ");
					out.println("       	document.Form2.txt_rcc.value     = unformat_noobject(document.Form2.txt_rcc.value); ");
					out.println("       	document.Form2.txt_tc.value      = unformat_noobject(document.Form2.txt_tc.value); ");
					
					out.println("       	document.Form2.txt_rcc_tc.value           = unformat_noobject(document.Form2.txt_rcc_tc.value); ");
					out.println("       	document.Form2.txt_tax_total.value        = unformat_noobject(document.Form2.txt_tax_total.value); ");
					out.println("       	document.Form2.txt_payable_premium.value  = unformat_noobject(document.Form2.txt_payable_premium.value); ");
					out.println("       	document.Form2.txt_bas_pre.value          = unformat_noobject(document.Form2.txt_bas_pre.value); ");
					
					out.println("       	document.Form2.txt_comm_rate.value      = unformat_noobject(document.Form2.txt_comm_rate.value); ");
					out.println("       	document.Form2.txt_bas_com.value        = unformat_noobject(document.Form2.txt_bas_com.value); ");
					out.println("       	document.Form2.txt_rcc_comm_rate.value  = unformat_noobject(document.Form2.txt_rcc_comm_rate.value); ");
					out.println("       	document.Form2.txt_rcc_comm.value       = unformat_noobject(document.Form2.txt_rcc_comm.value); ");
					
					out.println("       	document.Form2.txt_tot_com.value        = unformat_noobject(document.Form2.txt_tot_com.value); ");
					out.println("       	document.Form2.txt_vat_total_com.value  = unformat_noobject(document.Form2.txt_vat_total_com.value); ");
	
					// added by udara 11-11-2014
					out.println("    for (var i=0; i < document.Form2.elements.length; i++ ) {");
					out.println("       document.Form2.elements[i].disabled=false;");
					out.println("    }");
					// end by udara 11-11-2014
					
					//out.println("			document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail';"); // commented by udara 19-09-2014
					out.println("			document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail_renewal_sec';"); // added by udara 19-09-2014
					out.println("			document.Form2.submit();	");
					
					
					out.println("      }"); // are you
				
				
				out.println("    }");
				
				out.println("    else{");
				out.println("	 	if(confirm(\"Are you sure, you want to save data?\")){ ");

				out.println("       	document.Form2.txt_sum_in.value  = unformat_noobject(document.Form2.txt_sum_in.value); ");
				out.println("       	document.Form2.txt_premium.value = unformat_noobject(document.Form2.txt_premium.value); ");
				out.println("       	document.Form2.txt_rcc.value     = unformat_noobject(document.Form2.txt_rcc.value); ");
				out.println("       	document.Form2.txt_tc.value      = unformat_noobject(document.Form2.txt_tc.value); ");
				
				out.println("       	document.Form2.txt_rcc_tc.value           = unformat_noobject(document.Form2.txt_rcc_tc.value); ");
				out.println("       	document.Form2.txt_tax_total.value        = unformat_noobject(document.Form2.txt_tax_total.value); ");
				out.println("       	document.Form2.txt_payable_premium.value  = unformat_noobject(document.Form2.txt_payable_premium.value); ");
				out.println("       	document.Form2.txt_bas_pre.value          = unformat_noobject(document.Form2.txt_bas_pre.value); ");
				
				out.println("       	document.Form2.txt_comm_rate.value      = unformat_noobject(document.Form2.txt_comm_rate.value); ");
				out.println("       	document.Form2.txt_bas_com.value        = unformat_noobject(document.Form2.txt_bas_com.value); ");
				out.println("       	document.Form2.txt_rcc_comm_rate.value  = unformat_noobject(document.Form2.txt_rcc_comm_rate.value); ");
				out.println("       	document.Form2.txt_rcc_comm.value       = unformat_noobject(document.Form2.txt_rcc_comm.value); ");
				
				out.println("       	document.Form2.txt_tot_com.value        = unformat_noobject(document.Form2.txt_tot_com.value); ");
				out.println("       	document.Form2.txt_vat_total_com.value  = unformat_noobject(document.Form2.txt_vat_total_com.value); ");

				// added by udara 11-11-2014
				out.println("    for (var i=0; i < document.Form2.elements.length; i++ ) {");
				out.println("       document.Form2.elements[i].disabled=false;");
				out.println("    }");
				// end by udara 11-11-2014

				//out.println("			document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail';");  // commented by udara 09-10-2014 by udara 09-10-2014
				out.println("			document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail_renewal_sec';"); // added by udara 09-10-2014
				out.println("			document.Form2.submit();	");
				
				
				out.println("      }"); // are you
				out.println("    }");
				
				
				out.println("   } ");
				
				
				out.println("}"); // validate data
				
				
				out.println("}"); // end function

				// end by udara 21-05-2014


				
				out.println("function validate_data(){");
				
				out.println(" var letterNumber = /^[0-9\\/a-zA-Z_-]+$/;  "); // added by udara 10-01-2018
				
				out.println("if(document.Form2.txt_policy_no.value==\"\"){;");
				out.println("   alert('Please Enter Policy No..!');");
				out.println("   document.Form2.txt_policy_no.focus();");
				out.println("   return false;");
				out.println("}");
				
				// added by udara 10-01-2018
				out.println(" else if(!document.Form2.txt_policy_no.value.match(letterNumber)){   ");
				out.println("    alert( \"Invalid characters are included in Policy No\" ); ");
				out.println(" 	 document.Form2.txt_policy_no.focus(); ");
				out.println("    return false;");
				out.println(" } "); 
				// end by udara 10-01-2018
				
				out.println("else if(document.Form2.txt_Debit_Note_no.value==\"\"){");
				out.println("   alert('Please Enter Debit Note No..!');");
				out.println("   document.Form2.txt_Debit_Note_no.focus();");
				out.println("   return false;");
				out.println("}");
				
				// added by udara 10-01-2018
				out.println(" else if(!document.Form2.txt_Debit_Note_no.value.match(letterNumber)){   ");
				out.println("    alert( \"Invalid characters are included in Debit Note No\" ); ");
				out.println(" 	 document.Form2.txt_Debit_Note_no.focus(); ");
				out.println("    return false;");
				out.println(" } "); 
				// end by udara 10-01-2018
				
				out.println("else if(document.Form2.start_dd.value==\"\" || document.Form2.start_mm.value==\"\" || document.Form2.start_yy.value==\"\"){");
				out.println("alert('Please Enter Start Date..!');");			
				out.println("return false;");
				out.println("}else if(document.Form2.end_dd.value==\"\" || document.Form2.end_mm.value==\"\" || document.Form2.end_yy.value==\"\"){");
				out.println("alert('Please Enter End Date..!');");			
				out.println("return false;");			
				out.println("}else if(document.Form2.txt_sum_in.value==\"\"){;");
				out.println("alert('Please Enter Inssured Sum..!');");
				out.println("document.Form2.txt_sum_in.focus();");
				out.println("return false;");
				out.println("}else if(document.Form2.txt_premium.value==\"\"){;");
				out.println("alert('Please Enter Premium Value..!');");
				out.println("document.Form2.txt_premium.focus();");
				out.println("return false;");
				out.println("}else if(document.Form2.txt_in_company.value==\"\"){;");
				out.println("alert('Please Enter Insurance Company Name..!');");
				out.println("document.Form2.txt_in_company.focus();");
				out.println("return false;");
				out.println("}else if(!chk_date()){;");//Added By Sandun on 01-01-2009
				out.println("alert('End date must be greater than Start Date..!');");
				out.println("return false;");
				out.println("}");
				
				// added by udara 11-11-2014
				out.println(" else if( unformat_noobject(document.Form2.txt_sum_in.value)<=0 ){ ");
				out.println("   alert('Sum insured should be greater than 0'); ");
				out.println(" } ");
				
				
				out.println("else{");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				out.println("function chk_date(){");//Added By Sandun on 01-01-2009
				out.println("end_date=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
				out.println("start_date=document.Form2.start_dd.value+'-'+document.Form2.start_mm.value+'-'+document.Form2.start_yy.value;");
				out.println("var d1 = new Date(start_date);");
				out.println("var d2 = new Date(end_date);");		
				out.println("if(d2>d1){");		
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+document.Form2.hid_status.value;"); 
				out.println("}"); 
				
				//added by prabash--------------------------------------(1)**	
				out.println("function check_amt2(){");
				out.println("var bas_co=0, rcc2=0,tot_com=0;");	
				out.println("document.Form2.txt_rcc_tc.value=parseFloat(unformat_noobject(document.Form2.txt_rcc.value))+parseFloat(unformat_noobject(document.Form2.txt_tc.value))"); 
				//out.println("document.Form2.txt_bas_pre.value=parseFloat(unformat_noobject(document.Form2.txt_premium.value))-(parseFloat(unformat_noobject(document.Form2.txt_rcc_tc.value))+parseFloat(unformat_noobject(document.Form2.txt_tax_total.value)))"); // commented by udara 12-03-2014
				out.println("document.Form2.txt_bas_com.value=(parseFloat(unformat_noobject(document.Form2.txt_bas_pre.value))*parseFloat(unformat_noobject(document.Form2.txt_comm_rate.value))/100)"); 
				out.println("document.Form2.txt_rcc_comm.value=(parseFloat(unformat_noobject(document.Form2.txt_rcc_tc.value))*parseFloat(unformat_noobject(document.Form2.txt_rcc_comm_rate.value))/100)"); 
				out.println("document.Form2.txt_tot_com.value=parseFloat(unformat_noobject(document.Form2.txt_bas_com.value))+parseFloat(unformat_noobject(document.Form2.txt_rcc_comm.value))"); 
				//out.println("document.Form2.txt_tot_com.value=(parseFloat(unformat_noobject(document.Form2.txt_bas_pre.value))*(parseFloat(unformat_noobject(document.Form2.txt_comm_rate.value)))/100)+(parseFloat(unformat_noobject(document.Form2.txt_rcc.value))*(parseFloat(unformat_noobject(document.Form2.txt_rcc_comm_rate.value)))/100)"); 
				out.println("document.Form2.txt_vat_total_com.value=(document.Form2.txt_tot_com.value)*0.12"); 
				out.println("}");
				//------------------------------------------------------**
				
				
				
				//-- for change tax ---added by prabash------------------(2)**	
				out.println("function dis_tax(){");
				out.println("var sum=0,j=1,m_amount=0,m_amount_val=0;");
				out.println("for(varj=1; j<document.Form2.hid_tax_num.value;j++ )");
				out.println("{");
				out.println(" m_amount =\"txt_tax_mut\"+j");
				out.println(" m_amount_val=document.Form2.elements[m_amount].value");
				out.println("sum+=(parseFloat(unformat_noobject( m_amount_val)));");
				out.println("}");
				out.println("document.Form2.txt_tax_total.value=sum;"); 
				out.println("}");   
				
				//--------------------------------------------------------**
				
				
				//prabash-------------------------------01-06-2011---------(3)**
				out.println("function header(){");
				out.println("m_table_tax_charges.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr ID=T_ID class=tr_input>'+");		
				out.println("'<TD WIDTH=\"20%\"  align=\"left\"><b><u>Taxes</u></TD>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td>'+");
				out.println("'<td></td></tr>'+");
				out.println("'</table>';");
				out.println("}");		
				
				
				
				//prabash------------------------------01-06-2011---------(4)**
				out.println("function get_tax_charges(){");
				out.println("m_table_tax_charges.innerHTML=\"\" ");
				out.println("if(document.Form2.txt_tax.value==\"YES\"){;");
				out.println("header();");
				out.println("m_table_tax_charges.innerHTML+='<table width = \"100%\" ><tr>'+");	
				out.println("'<TD WIDTH=\"20%\" ><B><FONT COLOR=\"black\">Tax Description</TD>'+");
				out.println("'<TD WIDTH=\"*%\" ><B><FONT COLOR=\"black\">Amount</TD>'+");	
				
				out.println("'</tr>'+");
				
				boolean more1 = rs2.next();
				int i=1;
				
				while(more1){
					out.println("'<tr>'+");	
					out.println("'<TD WIDTH=\"20%\" ><FONT COLOR=\"black\">"+rs2.getString(2)+"</TD>'+");	
					out.println("'<td width=\"*%\" ><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right;\" name=\"txt_tax_mut"+i+"\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);dis_tax()\"><input type=\"hidden\"  name=\"txt_tax_code"+i+"\" value=\""+rs2.getString(1)+"\" maxlength=\"18\"></td>'+"); 
					out.println("'</tr>'+");	
					i++;
					
					more1 = rs2.next();
				}
				out.println("'</table>';");
				out.println("document.Form2.hid_tax_num.value="+i+";");
				out.println("}");
				out.println(" else ");
				out.println("document.Form2.txt_tax_total.value=\"\" ");
				out.println("}");
				
				//prabash--------------------------------------------------------**
				
				
				
				out.println("function check_amt(obj,size){");
				out.println("if(obj.value!=''){"); 
				out.println("format_number(obj,18)");
				out.println("}"); 
				out.println("}"); 
				
				out.println("function window_close(){");
				out.println("	if(confirm(\"Are you sure you want to close the screen?\")){ ");
				out.println("window.close()");
				out.println("}");
				out.println("}");
				
				out.println("function check_date(objdd,objmm,objyy) {"); 						
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");
				//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("}");
				out.println("}");
				
				out.println("function load_tax(){");
				if(m_scr_name.equals("Edit") || m_scr_name.equals("Renewal_New") || m_scr_name.equals("Renewal_Edit")){
					out.println("if(document.Form2.txt_tax.value==\"YES\"){"); 
					out.println("get_tax_charges();");
					out.println("edit_tax();");
					out.println("}");
				}
				out.println("}");
				
				
				out.println("function load_status(){");
				if(m_business_type.equals("RENEWAL")){
					out.println("document.Form2.hid_business_type.value=\"RENEWAL\";");
				}
				else{
					out.println("document.Form2.hid_business_type.value=\"NEW\";");
				}
				out.println("}");
				
				
				out.println("function edit_tax(){"); 
				out.println("	document.Form2.hid_chk_status.value='edit_tax'; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=edit_tax_asset_insurance&policy_no=\"+document.Form2.txt_policy_no.value+\"&debit_note_no=\"+document.Form2.hid_debit_note_no.value;");
				
				out.println("		load_interface(m_url,'XML');");
				out.println("}"); 

				
				out.println("function get_vector(data_vec) {");
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='edit_tax' ){");
				out.println("			m_act_len=parseInt(data_vec.length)/3;");
				out.println("			var m_count=0;");
				out.println("			for(var i=1;i<=parseInt(m_act_len);i++){");
				out.println("    		document.Form2.elements[\"txt_tax_code\"+i].value=data_vec[m_count];"); 
				out.println("    		document.Form2.elements[\"txt_tax_mut\"+i].value=data_vec[m_count+2];"); 
				out.println("   		m_count=m_count+3;");
				out.println("		}");
				out.println("	}");
				
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='set_end_date' ){");
				out.println("			document.Form2.end_dd.value = data_vec[0];");
				out.println("			document.Form2.end_mm.value = data_vec[1];");
				out.println("			document.Form2.end_yy.value = data_vec[2];");
			//	out.println("			alert('stat_date '+data_vec[0]+' '+data_vec[1]+' '+ data_vec[2]);");
			//	out.println("start_date_check(); ");
				out.println("	}");
				
				// added by udara 13-02-2014
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='set_pricing_no' ){");
				out.println("			alert(data_vec[0]);");
				out.println("	}");
				
				//Added by Kanchana.-----------------------------------------------------
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='check_date_range' && data_vec[0]>0  ){"); 
			//	out.println("			alert(data_vec[0]);");
				out.println("			alert('You already have a insurance in this date range.Your insurance end date is '+data_vec[1]);");
			//	out.println("start_date_check(); ");
				out.println("	}");
				
				out.println("	if(data_vec.length>0  && document.Form2.hid_chk_status.value=='start_date_check' ){");
			// 	out.println("			alert('stat_date '+data_vec[0]+' '+data_vec[1]+' '+ data_vec[2]);");
				out.println(" 			 if((Number(data_vec[2])>Number(0))&&(Number(data_vec[2]>Number(30))) ){ ");
				out.println("					alert('Insurance Start date is less than 30 days.');");
				out.println("	}");	
					
				out.println("			if(((parseInt(data_vec[2]))*(-1))>Number(60)) { ");
				out.println("					alert('Insurance Start date is greater than 60 days.');");
			//	out.println(" make_request_date(); ");
				out.println("								}");
				//Set end date
				out.println("			document.Form2.end_dd.value = data_vec[3];");
				out.println("			document.Form2.end_mm.value = data_vec[4];");
				out.println("			document.Form2.end_yy.value = data_vec[5];");
				out.println("	document.Form2.hid_start_date_diff.value=data_vec[2];");
				
				out.println("			alert('End date is set as '+data_vec[3]+' '+data_vec[4]+' '+ data_vec[5]);"); // added by udara 15-03-2018
				
				out.println(" make_request_date(); ");
			//	out.println("					alert(document.Form2.hid_start_date_diff.value);");
				out.println("	}");
				//Ended by Kanchana.-----------------------------------------
				
				// added by udara 10-03-2014
				out.println("	if( document.Form2.hid_chk_status.value=='get_basic_rcc_comm_rate' ){");
				out.println("	     if(data_vec.length>0  && document.Form2.hid_chk_status.value=='get_basic_rcc_comm_rate' ){");
				out.println("	        document.Form2.txt_comm_rate.value =  data_vec[0]; ");
				out.println("	        document.Form2.txt_rcc_comm_rate.value =  data_vec[1]; "); 
				// commented below by udara 04-11-2014
				/*
				out.println("	        document.Form2.txt_bas_pre.value =  data_vec[2]; "); 
				//out.println("	        document.Form2.txt_premium.value =  data_vec[3]; ");  // commented by udara 06-05-2014 // added by udara 16-04-2014 
				out.println("	        document.Form2.txt_payable_premium.value =  data_vec[3]; "); // added by udara 06-05-2014 
				out.println("	        document.Form2.txt_premium.value =  data_vec[4]; "); // added by udara 03-11-2014
				*/
				out.println("           check_amt2(); ");
				out.println("           get_flag_status(); "); // added by udara 20-07-2018
				
				out.println("	     }");
				out.println("	     else{");
				out.println("	        document.Form2.txt_comm_rate.value =  0; ");
				out.println("	        document.Form2.txt_rcc_comm_rate.value =  0; "); 
				// commented by udara 04-11-2014
				/*
				out.println("	        document.Form2.txt_bas_pre.value =  0; "); 
				//out.println("	        document.Form2.txt_premium.value =  0; "); // commented by udara 06-05-2014 // added by udara 16-04-2014
				out.println("	        document.Form2.txt_payable_premium.value =  data_vec[3]; "); // added by udara 06-05-2014 
				out.println("	        document.Form2.txt_premium.value =  data_vec[4]; "); // added by udara 03-11-2014
				*/
				out.println("	     }");
				out.println("	 }");
				// end by udara 10-03-2014
				
				// added by udara 20-07-2018
				out.println("	else if( document.Form2.hid_chk_status.value=='get_flag_status' ){");
				out.println("	     if(data_vec.length>0  && document.Form2.hid_chk_status.value=='get_flag_status' ){");
				out.println("	         document.Form2.hid_get_flag_status.value = data_vec[0]; ");
				//out.println("	         alert('Flag Yes - ' + document.Form2.hid_get_flag_status.value ); ");
				out.println("	     }");
				out.println("	}");
				// added by udara 20-07-2018
				
				// added by udara 20-07-2018
				out.println("	else if( document.Form2.hid_chk_status.value=='get_flag_status_before_save' ){");
				out.println("	     if(data_vec.length>0  && document.Form2.hid_chk_status.value=='get_flag_status_before_save' ){");
				out.println("	         document.Form2.hid_get_flag_status.value = data_vec[0]; ");
				//out.println("	         alert('Flag Yes - ' + document.Form2.hid_get_flag_status.value ); ");
				out.println("            submit_data(); ");
				out.println("	     }");
				out.println("	}");
				// added by udara 20-07-2018
				
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
				out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='99'){"); 
				out.println("		help_company_assign_99(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='2'){"); 
				out.println("		help_update_value_assign_2(oBj);"); 
				out.println("	}");
				out.println("	}"); 
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
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}");  
				
				out.println("function clear_fields(){ ");
				out.println("if(document.Form2.hid_help_type.value==\"99\"){");
				out.println("    document.Form2.txt_in_company.value=\"\";"); 
				out.println("    document.Form2.txt_in_company_code.value=\"\";"); 
				out.println("}");
				out.println("if(document.Form2.hid_help_type.value==\"2\"){");
				out.println("    document.Form2.TXT_ITEM_SUB_CAT.value=\"\";"); 
				out.println("    DIV_TXT_ITEM_SUB_CAT_DESC_2.innerHTML = \"\";");
				out.println("}");
				
				
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}");
				
				out.println("function help_company() {"); 
				out.println("    document.Form2.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_insurance_company_code\";"); 
				out.println("    m_criteria = document.Form2.txt_in_company.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_company_assign_99() {");
				out.println("    document.Form2.txt_in_company_code.value=oBj.valout[2];"); 
				out.println("    document.Form2.txt_in_company.value=oBj.valout[3];"); 
				out.println("}"); 
				
				
				// added by udara 12-03-2014

				
				out.println("function help_update_item_sub_cat() {"); 
				out.println("    document.Form2.hid_help_type.value=\"2\";"); 
				//out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_sql\";"); 
				out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_DESC_NEW_sql\";"); 
				//out.println("    m_criteria = document.Form2.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
				out.println("    m_criteria = document.Form2.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\"+document.Form2.txt_in_company_code.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
				out.println("}"); 
      
      
	 			out.println("function help_update_value_assign_2() {"); 
				out.println("    document.Form2.TXT_ITEM_SUB_CAT.value=oBj.valout[2];");
				out.println("    DIV_TXT_ITEM_SUB_CAT_DESC_2.innerHTML = oBj.valout[3];");
				out.println("}"); 
				
				// end by udara 12-03-2014
				
				
				// added by udara 13-02-2014
				out.println(" function set_rcc_value(obj){ ");  
				// commented by udara 04-11-2014
				
				// released by udara 08-12-2014
				out.println("    var sum_insured = unformat_noobject(obj.value);");
				//out.println("    var rcc_value   = sum_insured*0.2;");
				out.println("    var rcc_value   = sum_insured*0.0025;");
				out.println("    document.Form2.txt_rcc.value = rcc_value; ");
				
				out.println(" }"); 
				
				
				// added by udara 10-03-2014
				
				out.println(" function get_basic_rcc_comm_rate(){ ");  
				//out.println("    alert('Test onblur'); ");
				//out.println(" 	   var m_insurance_agent = document.Form2.txt_in_company_code.value; "); 
				
				//out.println(" 	   var m_insurance_agent = document.Form2.TXT_ITEM_SUB_CAT.value; ");
				out.println(" 	   var m_insurance_agent = document.Form2.txt_in_company_code.value; "); 
				
				out.println(" 	   var m_sum_insured     = unformat_noobject(document.Form2.txt_sum_in.value); "); 
				//out.println("      alert(m_insurance_agent + ' ' + m_sum_insured); ");
				out.println(" 	   var m_sub_cat     = document.Form2.TXT_ITEM_SUB_CAT.value; ");  // udara 05-09-2014
				out.println("	   document.Form2.hid_chk_status.value='get_basic_rcc_comm_rate'; ");
				//out.println("	   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_basic_rcc_comm_rate&insurance_agent=\"+m_insurance_agent+\"&sum_insured=\"+m_sum_insured;");
				out.println("	   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_basic_rcc_comm_rate&insurance_agent=\"+m_insurance_agent+\"&sum_insured=\"+m_sum_insured+\"&sub_cat=\"+m_sub_cat;"); // udara 05-09-2014
				out.println("	   load_interface(m_url,'XML');");
				//out.println("      window.open(m_url);  ");
				
				
				out.println(" }"); 
				
				// end by udara 10-03-2014
				
				// added by udara 05-11-2014
				out.println(" function ceil_total_premium(){ ");  
				out.println("  var ceil_value = Math.ceil(unformat_noobject(document.Form2.txt_payable_premium.value)); ");
				out.println("  document.Form2.txt_premium.value =  ceil_value;  ");
				out.println("  format_number(document.Form2.txt_premium,18);  ");
				out.println(" }");
				// end by udara 05-11-2014

				
				// added by udara 04-01-2018
				out.println(" function validate_policy(obj) { ");
				
				out.println("    var policy_num = obj; ");
				out.println("    var letterNumber = /^[0-9\\/a-zA-Z_-]+$/;  ");

				out.println("    if (policy_num.value == \"\") { ");
				out.println("        alert('Policy number cannot be blank'); ");
				out.println("    } ");
				out.println("    else if(!obj.value.match(letterNumber)){   ");
				//out.println("        alert( \"Invalid characters are included\" ); "); 
				//out.println(" 	     obj.value=''; ");
				//out.println(" 	     obj.focus(); ");
				out.println("    } ");
				
				out.println("} ");
				// end by udara 04-01-2018
				
				// added by udara 20-07-2018
				out.println(" function get_flag_status(){ "); 
				out.println(" 	   var m_insurance_agent = document.Form2.txt_in_company_code.value; "); 
				out.println(" 	   var m_sum_insured     = unformat_noobject(document.Form2.txt_sum_in.value); "); 
				out.println(" 	   var m_sub_cat         = document.Form2.TXT_ITEM_SUB_CAT.value; "); 
				out.println("	   document.Form2.hid_chk_status.value='get_flag_status'; ");
				out.println("	   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_flag_status&insurance_agent=\"+m_insurance_agent+\"&sum_insured=\"+m_sum_insured+\"&sub_cat=\"+m_sub_cat;");
				out.println("	   load_interface(m_url,'XML');");
				out.println(" }");
				
				out.println(" function get_flag_status_before_save(){ "); 
				out.println(" 	   var m_insurance_agent = document.Form2.txt_in_company_code.value; "); 
				out.println(" 	   var m_sum_insured     = unformat_noobject(document.Form2.txt_sum_in.value); "); 
				out.println(" 	   var m_sub_cat         = document.Form2.TXT_ITEM_SUB_CAT.value; "); 
				out.println("	   document.Form2.hid_chk_status.value='get_flag_status_before_save'; ");
				out.println("	   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_flag_status&insurance_agent=\"+m_insurance_agent+\"&sum_insured=\"+m_sum_insured+\"&sub_cat=\"+m_sub_cat;");
				out.println("	   load_interface(m_url,'XML');");
				out.println(" }");
				// end by udara 20-07-2018
				
				// end by udara 19-11-2014
				//Added by Kanchana Karunarathna on 2016-01-06 for issue no 19134
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
			    out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			    out.println("    window.open(m_url); ");
			    out.println(" }");
				
				// added by udara 04-08-2017
				out.println(" function debit_note_plus(obj){");

				out.println(" var letterNumber = /^[0-9\\/a-zA-Z_-]+$/;  ");
				
				out.println(" if(obj.value == \"\"){   ");
				out.println("        alert('Debit Note Number cannot be blank'); ");			
				out.println(" } ");
				
				out.println(" else if(!obj.value.match(letterNumber)){   ");
				//out.println("    alert( \"Invalid characters are included\" ); ");
				//out.println(" 	     obj.value=''; ");
				//out.println(" 	 obj.focus(); ");
				out.println(" } "); 
				// end by udara 28-12-2017
				
				out.println(" }");
				// end by udara 04-08-2017
				
				
				out.println("</script>");
				out.println("</head>");
				out.println("<body onload=\"load_tax();load_status()\">");
				
				out.println("<form name=\"Form2\" method=post>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_tax_num'ID='hid_tax_num' VALUE=\"0\">"); //added by prabash--**
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_insurance_done' VALUE=\""+m_cli_name+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE=\""+m_finance_no+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_asset_deta' VALUE=\""+m_asset_deta+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_invo_no' VALUE=\""+m_invoice_no+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_business_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_policy_no' VALUE=\""+mm_policy_no+"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_debit_note_no' VALUE=\""+mm_debit_note_no+"\">");
				//Added by Kanchana.
				out.println("<INPUT TYPE='Hidden' id='hid_start_date_diff' NAME='hid_start_date_diff' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_pricing_no' VALUE=\""+m_price_no+"\">"); // added by udara 17-02-2014
				out.println("<INPUT TYPE='Hidden' NAME='hid_app_no' VALUE=\""+m_app_no+"\">"); // added by udara 17-02-2014
				out.println("<INPUT TYPE='Hidden' NAME='hid_screen' VALUE=\""+m_scr_name+"\">");  // added by udara 21-02-2014
				out.println("<INPUT TYPE='Hidden' NAME='hid_get_flag_status' VALUE=\"\" >"); // added by udara 20-07-2018
				
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Prossess - Asset Insurance Details </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");			
				//out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='submit_data()' value=\"Save\"></td>");  // commented by udara 20-07-2018
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='get_flag_status_before_save()' value=\"Save\"></td>");  // added by udara 20-07-2018
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");' onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='window_close()' value=\"Close\"></td>");  
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
				
				// added by udara 20-02-2014
				rs=stmt.executeQuery(" SELECT DECODE (A.DIVISION_CODE, 'AF', 'Hire Purchase', 'BD', 'Bike', '-'), "+ // added by udara 11-02-2014
					" TO_CHAR(A.ENT_DATE,'DD'),TO_CHAR(A.ENT_DATE,'MM'),TO_CHAR(A.ENT_DATE,'YYYY'), "+
					" TO_CHAR(ADD_MONTHS (A.ENT_DATE, 12)-1,'DD'),TO_CHAR(ADD_MONTHS (A.ENT_DATE, 12)-1,'MM'),TO_CHAR(ADD_MONTHS (A.ENT_DATE, 12)-1,'YYYY'), "+
					" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT (A.APPLICATION_NO), NVL(B.AMOUNT,0.00), "+
					" NVL(B.PAYEE_CODE,' '), NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME (B.PAYEE_CODE),' '), NVL(A.INSURANCE_DONE_BY,'-'), "+
					" A.CLIENT_CODE  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, ( SELECT * FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN  WHERE YEAR_NO = 0) B "+
					" WHERE A.FINANCE_NO = '"+m_finance_no+"' AND A.APPLICATION_NO = B.APPLICATION_NO (+) ");
				
				String m_division="";
				String m_start_dd="";
				String m_start_mm="";
				String m_start_yy="";
				String m_end_dd  ="";
				String m_end_mm  ="";
				String m_end_yy  ="";
				BigDecimal m_sum_inssured  = new BigDecimal("0.00");
				BigDecimal m_total_premium = new BigDecimal("0.00");
				String m_payee_code="";
				String m_payee_name="";
				String m_insurence_done_by ="";
				String m_client_code="";
				boolean more2 = rs.next();
				
				if(more2){
					m_division     = rs.getString(1);
					m_start_dd = rs.getString(2);
					m_start_mm = rs.getString(3);
					m_start_yy = rs.getString(4);
					m_end_dd   = rs.getString(5);
					m_end_mm   = rs.getString(6);
					m_end_yy   = rs.getString(7);
					m_sum_inssured  = rs.getBigDecimal(8);
					m_total_premium = rs.getBigDecimal(9);
					m_payee_code = rs.getString(10);
					m_payee_name = rs.getString(11);
					m_insurence_done_by = rs.getString(12);
					m_client_code = rs.getString(13);
				}
				
				m_payee_name = m_payee_name.trim(); // udara 20-03-2014
				
				
				out.println("<table width='100%' class='table' border='0'>"); 	
				out.println("<tr>");
				out.println("<td width='20%' align ='left'>Finance No</td>"); 
				out.println("<td width='30%' align ='left' style='{cursor:hand; }' onclick = \"show_transaction_info('"+m_client_code+"','"+m_finance_no+"')\"><b><u>"+m_finance_no+"</u></b><input type='hidden'  name='hid_fi_no_val' value='"+m_finance_no+"' > </td>");  // function show_transaction_info added by Kanchana Karunarathna for issue no 19134
				out.println("<td width='20%' align ='left'>Client Name</td>");
				out.println("<td width='30%' align ='left'><b>"+m_cli_name+"</b></td>");
				//out.println("<td width='10%' colspan=0.5 align ='left'>Client Name</td>");
				//out.println("<td width='10%' align ='left'>"+m_cli_name+"</td>"); 
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' align ='left'>Asset Description</td>");
				out.println("<td width='30%' align ='left'><b>"+m_asset_deta+"</b></td>");
				out.println("<td width='20%' align ='left'>Division</td>");
				out.println("<td width='30%' align ='left'><b>"+m_division+"</b></td>");
				
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%' align ='left'>Insurance Done By</td>");
				//out.println("<td width='30%' align ='left'><b>"+m_insurence_done_by+"</b></td>"); // commented by udara 20-06-2014
				out.println("<td width='30%' align ='left'><b>"+m_insurence_done_by+"</b> <input type='hidden'  name='hid_ins_done_by' value='"+m_insurence_done_by+"' >  </td>"); // added by udara 20-06-2014
				out.println("<td width='20%' align ='left'></td>");
				out.println("<td width='30%' align ='left'></td>");
				
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				out.println("</table>");
				
				if(m_scr_name.equals("New")){
					
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Insured Details</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					//out.println("<td width='20%' align ='left'>Policy No</td>"); // commented by udara 11-02-2014
					out.println("<td width='20%' align ='left'>Policy/Cover Note No</td>"); // added by udara 11-02-2014
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:150px;\" maxlength=20 value=\"\"  onblur=\"validate_policy(this);\" ></td>"); 			
					
					
					out.println("<td width='20%' align ='left'>Debit Note No</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_Debit_Note_no\" style=\"width:150px;\" maxlength=20 value=\"\" onblur=\"debit_note_plus(this);\" ></td>"); 			
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Start Date</td>"); 
					out.println("<td width='30%' ><input name=\"start_dd\" value=\""+m_start_dd+"\"  type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);set_end_date();\" > ");
					out.println("    <input name=\"start_mm\" type=\"text\" value=\""+m_start_mm+"\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  onblur=\"check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy); set_end_date();\"  > ");
					out.println("    <input name=\"start_yy\"  type=\"text\" value=\""+m_start_yy+"\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\" check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy); set_end_date();\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> "); // check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);
					out.println("</td>");
					
					
					out.println("<td width='20%' align ='left'>End Date</td>"); 
					out.println("<td width='30%' ><input name=\"end_dd\"  value=\""+m_end_dd+"\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > ");
					out.println("    <input name=\"end_mm\" type=\"text\" value=\""+m_end_mm+"\"  maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > ");
					out.println("    <input name=\"end_yy\"  type=\"text\" value=\""+m_end_yy+"\"  maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > "); // <a href style='{cursor:hand; }' onclick=load_calendar('3')  >   Calendar</a> 
					out.println("</td>");
					out.println("</tr>");
					
			
					out.println("<tr>");
					out.println("<td width='20%' align ='left'  valign=\"top\">Insurance Company</td>"); 
					out.println("<td width='30%' align ='left'  valign=\"top\"><input type=\"text\" value=\""+m_payee_name+"\" class=\"txt_input\" style=\"width:150px;\" name=\"txt_in_company\" value=\"\" maxlength=18 ><input type=\"hidden\" value=\""+m_payee_code+"\" class=\"txt_input\" name=\"txt_in_company_code\"  >"); 			
					out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" ></td>"); 
					out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
					out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px'></TEXTAREA></td>"); 			
					out.println("</tr>");
					
					
					// added by udara 12-03-2014
					
					out.println("<tr>");
					out.println("<td width='20%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Vehicle Type</DIV></td>"); 
					out.println("<td width='30%' >");
				    out.println("  <input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"\">"); 
					out.println("  <input class='but_input' type='button' name='BUT_HELP_ITEM_SUB_CAT' value=\"Help\" onClick=\"help_update_item_sub_cat()\" > ");
					out.println("</td>"); 
					out.println("<td width='20%' align ='left' valign=top> &nbsp; </td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					// end by udara 12-03-2014
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Premium Breakdown</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\"  class=\"txt_input\"style=\"width:150px;text-align:right\" name=\"txt_sum_in\" value=\"0.00\" maxlength=20 onblur=\"set_rcc_value(this); check_amt(this,18); get_basic_rcc_comm_rate(); \"></td>"); 
					//out.println("<td width='30%' align ='left'><input type=\"text\"  class=\"txt_input\"style=\"width:150px;text-align:right\" name=\"txt_sum_in\" value=\""+nf.format(m_sum_inssured)+"\" maxlength=20 onblur=\"check_amt(this,18) \"></td>"); 			
					
					out.println("<td width='20%' align ='left'>Total Premium</td>"); 
					//out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_premium\" value=\""+nf.format(m_total_premium)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"></td>"); 			
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_premium\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);\" disabled ></td>"); // mod udara 03-11-2014 	disabled
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);\" disabled ></td>"); 
					//out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"></td>"); 			
					out.println("<td width='20%' align ='left'>TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tc\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%'>TAX</td>"); 
					out.println("<td width='30%'><select name='txt_tax' class='txt_input' style=\"width:50px;\" onchange=\"get_tax_charges()\">");
					out.println("<option value=\"NO\" selected>NO</option>");
					out.println("<option value=\"YES\" >YES</option>");
					out.println("</select>");
					out.println("</td>");
					out.println("<td width='20%' align ='left'>RCC/TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_tc\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"disabled > </td >"); 			
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align=\"left\" width=\"100%\" class='table'border='0'>"); 
					out.println("<tr > ");  
					out.println("<td width=\"100%\"><DIV ID='m_table_tax_charges'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" valign=\"top\">");
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>TAX Total</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tax_total\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					
					// added by udara 06-05-2014
					out.println("<td width='20%' align ='left'>Payable Premium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_payable_premium\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18); ceil_total_premium();\" >"); 	// mod by udara 03-11-2014 removed disabled		
					out.println("</td>");
					// end by udara 06-05-2014
					
					//out.println("<td width='*%'></td>");	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Commission Receiveble</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Permium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_pre\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" ></td>"); // mod by udara 03-11-2014 removed disabled			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_comm_rate\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 	
					out.println("<td width='20%' align ='left'>Basic Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_com\" value = \"0.00\"  maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm_rate\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>");
					out.println("<td width='20%' align ='left'>RCC Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Total Commission </td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tot_com\" value=\"0.00\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='20%' align ='left'>VAT on Total Commission</td>"); 
					out.println("<td>");
					out.println("<input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_vat_total_com\" value=\"0.00\" maxlength=100 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='*%'></td>");	
					out.println("</tr>");
					
					out.println("</table>");
					
				}	
				
				
				else if(m_scr_name.equals("Edit")){
					
					String m_start_date_dd="";
					String m_start_date_mm="";
					String m_start_date_yy="";
					String m_end_date_dd="";
					String m_end_date_mm="";
					String m_end_date_yy="";
					String m_policy_no="";
					String m_debit_note_no = "";
					String m_tax = "";
					String m_start_date="";
					String m_end_date="";
					String m_insur_company="";
					String m_insur_remarks="";
					String m_insur_company_code = "";
					String m_vehi_type = "";
					String m_vehi_type_desc = ""; // added by udara 22-04-2014
					
					BigDecimal m_tot_commission = new BigDecimal("0.00");
					BigDecimal m_rcc = new BigDecimal("0.00");
					BigDecimal m_tc = new BigDecimal("0.00");
					BigDecimal m_rcc_tc = new BigDecimal("0.00");
					BigDecimal m_basic_premi = new BigDecimal("0.00");
					BigDecimal m_comm_rate = new BigDecimal("0.00");
					BigDecimal m_bas_com = new BigDecimal("0.00");
					BigDecimal m_rcc_comm_rate = new BigDecimal("0.00");
					BigDecimal m_rcc_comm = new BigDecimal("0.00");
					BigDecimal m_vat_total_com = new BigDecimal("0.00");
					BigDecimal m_premium = new BigDecimal("0.00");
					BigDecimal m_sum_insur = new BigDecimal("0.00");
					BigDecimal m_total_tax = new BigDecimal("0.00");
					BigDecimal m_payable_premium = new BigDecimal("0.00"); // added by udara 06-05-2014
					
					// commented by udara 21-05-2014
					
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
						" A.SECURITY_ID,"+ //2
						" A.POLICY_NO, "+ //3
						" A.ASSET_DESCRIPTION, "+ //4
						" TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //5
						" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //6
						" NVL(A.SUM_INSSURED,0.00), "+ //7
						" NVL(A.PREMIUM,0.00), "+ //8
						" NVL( A.RCC,0.00), "+   //9
						" NVL(A.TC,0.00), "+  //10
						" NVL(A.RCC_TC_TOTAL,0.00), "+ //11
						" NVL(A.BASIC_PREMIUM,0.00), "+ //12
						" NVL(A.BASIC_PREMIUM_COMM_RATE,0.00), "+  //13
						" NVL(A.BASIC_PREMIUM_COMMISION,0.00), "+  //14
						" NVL(A.RCC_TC_COMM_RATE,0.00), "+ //15
						" NVL(A.RCC_TC_COMMISION,0.00),"+ //16
						" NVL(A.VAT_ON_TOTAL_COMMISION,0.00), "+  //17
						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+  //18
						" NVL(A.REMARKS,'-'), "+//19
						" NVL(A.TAX_DUE,0.00), "+ //20
						" NVL(A.DEBIT_NOTE_NO,'-'), "+ //21
						" TAX_APPLICABILITY, "+ //22
						" NVL(A.INSUR_COM,'-'), "+  //23
						//" NVL(A.VEHICLE_TYPE,'-') "+ // 24
						" A.VEHICLE_TYPE,  "+ // 24
						" "+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC_2(A.VEHICLE_TYPE), "+ // 25 added by udara 22-04-2014
						" NVL(A.PAYABLE_PREMIUM,0.00) "+ // 26 added by udara 06-05-2014
						" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A "+		
						" WHERE A.POLICY_NO = '"+mm_policy_no+"'"+
						" AND A.DEBIT_NOTE_NO='"+mm_debit_note_no+"' ");
					
					
					
					
					boolean more = rs.next();
					if(more){
						m_policy_no     = rs.getString(3);
						m_start_date    = rs.getString(5);
						m_end_date      = rs.getString(6);
						m_premium       = rs.getBigDecimal(8).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_sum_insur     = rs.getBigDecimal(7).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc			= rs.getBigDecimal(9).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_tc			= rs.getBigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_tc		= rs.getBigDecimal(11).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_basic_premi	= rs.getBigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_comm_rate		= rs.getBigDecimal(13).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_bas_com		= rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_comm_rate = rs.getBigDecimal(15).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_comm		= rs.getBigDecimal(16).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_vat_total_com = rs.getBigDecimal(17).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_insur_company = rs.getString(18);
						m_insur_remarks = rs.getString(19);
						m_total_tax     = rs.getBigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_debit_note_no = rs.getString(21);
						m_tax           = rs.getString(22);
						m_insur_company_code = rs.getString(23);
						
						m_vehi_type = rs.getString(24);
						
						m_vehi_type_desc = rs.getString(25); // added by udara 22-04-2014
						
						
						m_tot_commission = m_bas_com.add(m_rcc_comm).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						
						m_payable_premium = rs.getBigDecimal(26).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						
					}
					m_start_date_dd = m_start_date.substring(0,2);
					m_start_date_mm = m_start_date.substring(3,5);
					m_start_date_yy = m_start_date.substring(6,10);
					m_end_date_dd   = m_end_date.substring(0,2);
					m_end_date_mm   = m_end_date.substring(3,5);
					m_end_date_yy   = m_end_date.substring(6,10);
					
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Insured Details</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					//out.println("<td width='20%' align ='left'>Policy No</td>"); 
					out.println("<td width='20%' align ='left'>Policy/Cover Note No</td>"); // added by udara 20-02-2014
					//out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:150px;\" maxlength=20 value=\""+m_policy_no+"\" disabled ></td>"); 	// commented by udara 09-07-2014
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:150px;\" maxlength=20 value=\""+m_policy_no+"\"  onblur=\"validate_policy(this);\" ></td>"); // added by udara 09-07-2014
					
					
					out.println("<td width='20%' align ='left'>Debit Note No</td>"); 
					//out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_Debit_Note_no\" style=\"width:150px;\" maxlength=20 value=\""+m_debit_note_no+"\"></td>"); // commented by udara 21-05-2014
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_Debit_Note_no\" style=\"width:150px;\" maxlength=20 value=\"\"  onblur=\"debit_note_plus(this);\" > ");
		            out.println(" <INPUT TYPE='Hidden' NAME='txt_hid_Debit_Note_no' value=\""+m_debit_note_no+"\" >   ");
		 			out.println(" </td>"); 	// added by udara 21-05-2014
					out.println("</tr>");
					
					// commented by udara 20-02-2014
					/*
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Start Date</td>"); 
					out.println("<td width='30%' ><input name=\"start_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_start_date_dd+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> "); 
					out.println("    <input name=\"start_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_mm+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
					out.println("    <input name=\"start_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_start_date_yy+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> ");
					out.println("</td>");
					
					
					out.println("<td width='20%' align ='left'>End Date</td>"); 
					out.println("<td width='30%' ><input name=\"end_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_end_date_dd+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
					out.println("    <input name=\"end_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_end_date_mm+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
					out.println("    <input name=\"end_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_end_date_yy+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("</tr>");
					*/
					
					// added by udara 20-02-2014
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Start Date</td>"); 
					out.println("<td width='30%' ><input name=\"start_dd\" value=\""+m_start_date_dd+"\"  type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);\" onblur=\"start_date_check()\"> "); //added onchange function by Kanchana.
					out.println("    <input name=\"start_mm\" type=\"text\" value=\""+m_start_date_mm+"\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);\" onblur=\"start_date_check()\"> ");
				//	out.println("    <input name=\"start_yy\"  type=\"text\" value=\""+m_start_date_yy+"\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"set_end_date();make_request_date()\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> "); // check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);
					out.println("    <input name=\"start_yy\"  type=\"text\" value=\""+m_start_date_yy+"\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"start_date_check()\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> "); // check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy);
					out.println("</td>");
					
					
					out.println("<td width='20%' align ='left'>End Date</td>"); 
					out.println("<td width='30%' ><input name=\"end_dd\"  value=\""+m_end_date_dd+"\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > ");
					out.println("    <input name=\"end_mm\" type=\"text\" value=\""+m_end_date_mm+"\"  maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > ");
					out.println("    <input name=\"end_yy\"  type=\"text\" value=\""+m_end_date_yy+"\"  maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy) disabled > "); // <a href style='{cursor:hand; }' onclick=load_calendar('3')  >   Calendar</a> 
					out.println("</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'  valign=\"top\">Insurance Company</td>"); 
					out.println("<td width='30%' align ='left'  valign=\"top\"><input type=\"text\" class=\"txt_input\" style=\"width:150px;\" name=\"txt_in_company\" value=\""+m_insur_company+"\" maxlength=18 ><input type=\"hidden\" class=\"txt_input\" name=\"txt_in_company_code\" value=\""+m_insur_company_code+"\"  >"); 			
					out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" ></td>"); 
					out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
					out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px' >"+m_insur_remarks+"</TEXTAREA></td>"); 			
					out.println("</tr>");
					
					// added by udara 12-03-2014
					
					out.println("<tr>");
					out.println("<td width='20%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Vehicle Type</DIV></td>");  
					out.println("<td width='30%' >");
				    out.println("  <input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"\" value=\""+m_vehi_type+"\" >");  
					out.println("  <input class='but_input' type='button' name='BUT_HELP_ITEM_SUB_CAT' value=\"Help\" onClick=\"help_update_item_sub_cat()\" > ");
					out.println("</td>"); 
					out.println("<td width='20%' align ='left' valign=top> &nbsp; </td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					// end by udara 12-03-2014
					
					
					// added by udara 22-04-2014
					out.println("<tr>");
					out.println("<td width='20%' ><DIV id='DIV_TXT_ITEM_SUB_CAT_DESC_1'  class=div_input>Vehicle Type Description</DIV></td>"); 
					out.println("<td width='30%' >");
				    out.println("   <DIV id='DIV_TXT_ITEM_SUB_CAT_DESC_2'  class=div_input> "+m_vehi_type_desc+" </DIV> ");
					out.println("</td>"); 
					out.println("<td width='20%' align ='left' valign=top> &nbsp; </td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					// end by udara 22-04-2014
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Premium Breakdown</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
					//out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\"style=\"width:150px;text-align:right\" name=\"txt_sum_in\"value=\""+nf.format(m_sum_insur)+"\" maxlength=20 onblur=\"set_rcc_value(this);check_amt(this,18); \"></td>"); // commented by udara 20-05-2014
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\"style=\"width:150px;text-align:right\" name=\"txt_sum_in\"value=\""+nf.format(m_sum_insur)+"\" maxlength=20 onblur=\"set_rcc_value(this); check_amt(this,18); get_basic_rcc_comm_rate();\"></td>"); 	// added by udara 20-05-2014
					
					out.println("<td width='20%' align ='left'>Total Premium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_premium\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); // mod udara 11-11-2014	// mod udara 03-11-2014 disabled	 value=\""+nf.format(m_premium)+"\"	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); // modified by udara 08-12-2014out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc\" value=\""+nf.format(m_rcc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 			
					out.println("<td width='20%' align ='left'>TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tc\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); // modified by udara 08-12-2014  // out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tc\" value=\""+nf.format(m_tc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%'>TAX</td>"); 
					out.println("<td width='30%'><select name='txt_tax' class='txt_input' style=\"width:50px;\" onchange=\"get_tax_charges()\">");
					if(m_tax.equals("YES")){
						out.println("<option  value=\"NO\" >NO</option>");
						out.println("<option  value=\"YES\" SELECTED >YES</option>");
					}
					else{
						out.println("<option  value=\"NO\"  SELECTED>NO</option>");
						out.println("<option  value=\"YES\"  >YES</option>");
					}
					out.println("</select>");
					
					out.println("</td>");
					out.println("<td width='20%' align ='left'>RCC/TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_tc\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"disabled > </td >"); // modified by udara 08-12-2014 // out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_tc\" value=\""+nf.format(m_rcc_tc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"disabled > </td >"); 			
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align=\"left\" width=\"100%\" class='table'border='0'>"); 
					out.println("<tr > ");  
					out.println("<td width=\"100%\"><DIV ID='m_table_tax_charges'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" valign=\"top\">");
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>TAX Total</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tax_total\" value=\""+nf.format(m_total_tax)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					//out.println("<td width='*%'></td>");	
					
					// added by udara 06-05-2014
					out.println("<td width='20%' align ='left'>Payable Premium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_payable_premium\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);ceil_total_premium();\" >");  // modified by udara 08-12-2014 out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_payable_premium\" value=\""+nf.format(m_payable_premium)+"\" maxlength=18 onblur=\"check_amt(this,18);ceil_total_premium();\" >"); 	// mod by udara 03-11-2014 removed disabled		
					out.println("</td>");
					// end by udara 06-05-2014
					
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Commission Receiveble</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Permium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_pre\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" ></td>"); // mod udara 11-11-2014 mod by udara 03-11-2014 removed disabled	// value=\""+nf.format(m_basic_premi)+"\"		
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_comm_rate\" value=\""+nf.format(m_comm_rate)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 	
					out.println("<td width='20%' align ='left'>Basic Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_com\" value = \""+nf.format(m_bas_com)+"\"  maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm_rate\" value=\""+nf.format(m_rcc_comm_rate)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>");
					out.println("<td width='20%' align ='left'>RCC Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>");  // modified by udara 08-12-2014 out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm\" value=\""+nf.format(m_rcc_comm)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Total Commission </td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tot_com\" value=\""+nf.format(0)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>");  // modified by udara 08-12-2014 out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tot_com\" value=\""+nf.format(m_tot_commission)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='20%' align ='left'>VAT on Total Commission</td>"); 
					out.println("<td>");
					out.println("<input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_vat_total_com\"  value=\""+nf.format(0)+"\" maxlength=100 onblur=\"check_amt(this,18)\"disabled>");  // modifed by udara 08-12-2014 out.println("<input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_vat_total_com\"  value=\""+nf.format(m_vat_total_com)+"\" maxlength=100 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='*%'></td>");	
					out.println("</tr>");
					
				}
				else if(m_scr_name.equals("Renewal_New") || m_scr_name.equals("Renewal_Edit")){
					
					String m_start_date_dd="";
					String m_start_date_mm="";
					String m_start_date_yy="";
					String m_end_date_dd="";
					String m_end_date_mm="";
					String m_end_date_yy="";
					String m_policy_no="";
					String m_debit_note_no = "";
					String m_tax = "";
					String m_start_date="";
					String m_end_date="";
					String m_insur_company="";
					String m_insur_remarks="";
					String m_insur_company_code="";
					
					String m_vehi_type="";
					
					BigDecimal m_tot_commission = new BigDecimal("0.00");
					BigDecimal m_rcc = new BigDecimal("0.00");
					BigDecimal m_tc = new BigDecimal("0.00");
					BigDecimal m_rcc_tc = new BigDecimal("0.00");
					BigDecimal m_basic_premi = new BigDecimal("0.00");
					BigDecimal m_comm_rate = new BigDecimal("0.00");
					BigDecimal m_bas_com = new BigDecimal("0.00");
					BigDecimal m_rcc_comm_rate = new BigDecimal("0.00");
					BigDecimal m_rcc_comm = new BigDecimal("0.00");
					BigDecimal m_vat_total_com = new BigDecimal("0.00");
					BigDecimal m_premium = new BigDecimal("0.00");
					BigDecimal m_sum_insur = new BigDecimal("0.00");
					BigDecimal m_total_tax = new BigDecimal("0.00");

					
					
					rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
						" A.PRO_INVOICE_NO,"+ //2
						" A.POLICY_NO, "+ //3
						" A.ASSET_DESCRIPTION, "+ //4
						" TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //5
						" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //6
						" NVL(A.SUM_INSSURED,0.00), "+ //7
						" NVL(A.PREMIUM,0.00), "+ //8
						" NVL( A.RCC,0.00), "+   //9
						" NVL(A.TC,0.00), "+  //10
						" NVL(A.RCC_TC_TOTAL,0.00), "+ //11
						" NVL(A.BASIC_PREMIUM,0.00), "+ //12
						" NVL(A.BASIC_PREMIUM_COMM_RATE,0.00), "+  //13
						" NVL(A.BASIC_PREMIUM_COMMISION,0.00), "+  //14
						" NVL(A.RCC_TC_COMM_RATE,0.00), "+ //15
						" NVL(A.RCC_TC_COMMISION,0.00),"+ //16
						" NVL(A.VAT_ON_TOTAL_COMMISION,0.00), "+  //17
						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+  //18
						" NVL(A.REMARKS,'-'), "+//19
						" NVL(A.TAX_DUE,0.00), "+ //20
						" NVL(A.DEBIT_NOTE_NO,'-'), "+ //21
						" TAX_APPLICABILITY, "+ //22
						" NVL(A.INSUR_COM,'-'), "+  //23
						" A.VEHICLE_TYPE  "+ // 24
						" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A "+		
						" WHERE A.POLICY_NO = '"+mm_policy_no+"'"+
						" AND A.DEBIT_NOTE_NO='"+mm_debit_note_no+"'");
					
					boolean more = rs.next();
					if(more){
						m_policy_no     = rs.getString(3);
						m_start_date    = rs.getString(5);
						m_end_date      = rs.getString(6);
						m_premium       = rs.getBigDecimal(8).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_sum_insur     = rs.getBigDecimal(7).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc			= rs.getBigDecimal(9).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_tc			= rs.getBigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_tc		= rs.getBigDecimal(11).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_basic_premi	= rs.getBigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_comm_rate		= rs.getBigDecimal(13).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_bas_com		= rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_comm_rate = rs.getBigDecimal(15).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_rcc_comm		= rs.getBigDecimal(16).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_vat_total_com = rs.getBigDecimal(17).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_insur_company = rs.getString(18);
						m_insur_remarks = rs.getString(19);
						m_total_tax     = rs.getBigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						m_debit_note_no = rs.getString(21);
						m_tax           = rs.getString(22);
						m_insur_company_code = rs.getString(23);
						
						m_vehi_type = rs.getString(24);
						
						m_tot_commission = m_bas_com.add(m_rcc_comm).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						
					}
					m_start_date_dd = m_start_date.substring(0,2);
					m_start_date_mm = m_start_date.substring(3,5);
					m_start_date_yy = m_start_date.substring(6,10);
					m_end_date_dd   = m_end_date.substring(0,2);
					m_end_date_mm   = m_end_date.substring(3,5);
					m_end_date_yy   = m_end_date.substring(6,10);
					
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Insured Details</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Policy No</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:150px;\" maxlength=20 value=\""+m_policy_no+"\" disabled ></td>"); 			
					
					out.println("<td width='20%' align ='left'>Debit Note No</td>"); 
					if(m_scr_name.equals("Renewal_Edit"))
						out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_Debit_Note_no\" style=\"width:150px;\" maxlength=20 value=\""+m_debit_note_no+"\" onblur=\"debit_note_plus(this);\" ></td>"); 			
					else
						out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_Debit_Note_no\" style=\"width:150px;\" maxlength=20 value=\"\"  onblur=\"debit_note_plus(this);\"  ></td>"); 	
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Start Date</td>"); 
					out.println("<td width='30%' ><input name=\"start_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_start_date_dd+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
					out.println("    <input name=\"start_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_mm+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
					out.println("    <input name=\"start_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_start_date_yy+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> ");
					out.println("</td>");
					
					
					out.println("<td width='20%' align ='left'>End Date</td>"); 
					out.println("<td width='30%' ><input name=\"end_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_end_date_dd+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
					out.println("    <input name=\"end_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_end_date_mm+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
					out.println("    <input name=\"end_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_end_date_yy+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'  valign=\"top\">Insurance Company</td>"); 
					out.println("<td width='30%' align ='left'  valign=\"top\"><input type=\"text\" class=\"txt_input\" style=\"width:150px;\" name=\"txt_in_company\" value=\""+m_insur_company+"\" maxlength=18 ><input type=\"hidden\" class=\"txt_input\" name=\"txt_in_company_code\" value=\""+m_insur_company_code+"\" >"); 			
					out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" ></td>"); 
					out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
					out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px' >"+m_insur_remarks+"</TEXTAREA></td>"); 			
					out.println("</tr>");
					
					/*
					// added by udara 12-03-2014
					
					out.println("<tr>");
					out.println("<td width='20%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Vehicle Type</DIV></td>");  
					out.println("<td width='30%' >");
				    out.println("  <input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"\" value=\""+m_vehi_type+"\" >");  
					out.println("  <input class='but_input' type='button' name='BUT_HELP_ITEM_SUB_CAT' value=\"Help\" onClick=\"help_update_item_sub_cat()\" > ");
					out.println("</td>"); 
					out.println("<td width='20%' align ='left' valign=top> &nbsp; </td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					// end by udara 12-03-2014
					*/
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Premium Breakdown</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\"style=\"width:150px;text-align:right\" name=\"txt_sum_in\"value=\""+nf.format(m_sum_insur)+"\" maxlength=20 onblur=\"check_amt(this,18) \"></td>"); 			
					
					out.println("<td width='20%' align ='left'>Total Premium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_premium\" value=\""+nf.format(m_premium)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); // mod udara 03-11-2014 disabled			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc\" value=\""+nf.format(m_rcc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 			
					out.println("<td width='20%' align ='left'>TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tc\" value=\""+nf.format(m_tc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%'>TAX</td>"); 
					out.println("<td width='30%'><select name='txt_tax' class='txt_input' style=\"width:50px;\" onchange=\"get_tax_charges()\">");
					if(m_tax.equals("YES")){
						out.println("<option  value=\"NO\" >NO</option>");
						out.println("<option  value=\"YES\" SELECTED >YES</option>");
					}
					else{
						out.println("<option  value=\"NO\"  SELECTED>NO</option>");
						out.println("<option  value=\"YES\"  >YES</option>");
					}
					out.println("</select>");
					
					out.println("</td>");
					out.println("<td width='20%' align ='left'>RCC/TC</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_tc\" value=\""+nf.format(m_rcc_tc)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\"disabled > </td >"); 			
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align=\"left\" width=\"100%\" class='table'border='0'>"); 
					out.println("<tr > ");  
					out.println("<td width=\"100%\"><DIV ID='m_table_tax_charges'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" valign=\"top\">");
					
					out.println("<table width='100%' class='table' border='0'>"); 	
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>TAX Total</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tax_total\" value=\""+nf.format(m_total_tax)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='*%'></td>");	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'><b><u>Commission Receiveble</u></b></td>"); 
					out.println("<td width='30%' align ='left'></td>"); 			
					out.println("<td width='20%' align ='left'></td>"); 			
					out.println("<td width='30%' align ='left'></td>"); 	
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Permium</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_pre\" value=\""+nf.format(m_basic_premi)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" ></td>"); // mod by udara 03-11-2014 removed disabled			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Basic Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_comm_rate\" value=\""+nf.format(m_comm_rate)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>"); 	
					out.println("<td width='20%' align ='left'>Basic Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_bas_com\" value = \""+nf.format(m_bas_com)+"\"  maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>RCC Commission Rate</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm_rate\" value=\""+nf.format(m_rcc_comm_rate)+"\" maxlength=18 onblur=\"check_amt(this,18);check_amt2()\" disabled ></td>");
					out.println("<td width='20%' align ='left'>RCC Commission</td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_rcc_comm\" value=\""+nf.format(m_rcc_comm)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='20%' align ='left'>Total Commission </td>"); 
					out.println("<td width='30%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_tot_com\" value=\""+nf.format(m_tot_commission)+"\" maxlength=18 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='20%' align ='left'>VAT on Total Commission</td>"); 
					out.println("<td>");
					out.println("<input type=\"text\" class=\"txt_input\" style=\"width:150px;text-align:right\" name=\"txt_vat_total_com\"  value=\""+nf.format(m_vat_total_com)+"\" maxlength=100 onblur=\"check_amt(this,18)\"disabled>"); 			
					out.println("</td>");
					out.println("<td width='*%'></td>");	
					out.println("</tr>");
					
				}
				
				
				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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



