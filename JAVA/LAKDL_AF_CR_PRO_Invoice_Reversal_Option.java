//CREATED BY DINETH MEEMANAGE
//CREATED DATE 2008-10-01

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_CR_PRO_Invoice_Reversal_Option extends javax.servlet.http.HttpServlet { 


	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
		  String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Invoice Reversal Option</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					out.println("var m_flag=0");
					out.println("function get_vector(data_vec) {");
					out.println("			if(data_vec.length>0 && document.Form1.hid_text.value==\"J6\"){");
					
					out.println("document.Form1.TXT_VALUE_DATE_DD.value=data_vec[0]");
					out.println("document.Form1.TXT_VALUE_DATE_MM.value=data_vec[1]");
					out.println("document.Form1.TXT_VALUE_DATE_YY.value=data_vec[2]");
					out.println("document.Form1.HID_VAL_DD.value=data_vec[0]");
					out.println("document.Form1.HID_VAL_MM.value=data_vec[1]");
					out.println("document.Form1.HID_VAL_YY.value=data_vec[2]");
					out.println("			}");
					out.println("			if(data_vec.length>0 && document.Form1.hid_text.value==\"J7\"){");
					out.println("m_count = data_vec[0];");
					out.println("m = data_vec[1];");
					out.println("if(m_count==0){");
				  out.println("alert('Reverse date cannot be less than the value date..!');");	
				  out.println("document.Form1.elements[\"CHK_\"+m].checked=false;");
				  out.println("document.Form1.elements[\"CHK_\"+m].disabled=false;");
					out.println("}");	
					out.println("}");
					out.println("}");
					
					 
			
					out.println("function get_vector_normal(http_response) {");
					out.println(" details.innerHTML = ''; ");
					out.println(" details.innerHTML = http_response; ");
					out.println(" ");
					out.println("}");
					
					out.println("function load_details() {");
					out.println(" 	m_value_dd = document.Form1.TXT_VALUE_DATE_DD.value ");
					out.println(" 	m_value_mm = document.Form1.TXT_VALUE_DATE_MM.value ");
					out.println("		m_value_yy = document.Form1.TXT_VALUE_DATE_YY.value ");
					out.println(" 	m_value_date = m_value_dd+\"-\"+m_value_mm+\"-\"+m_value_yy; ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Invoice_Reversal_Option?chksql=details&status=\"+document.Form1.hid_status.value+\"&FINANCE_NO=\"+document.Form1.TXT_FINANCE_NO.value+\"&VALUE_DATE=\"+m_value_date+\"\";"); 
					
					out.println("load_interface(m_url,'NORM');");
					out.println("}");
					
					//Added by Dineth on 2008-10-03
					/*out.println("function validate_date(){");
					out.println(" 	m_value_dd = document.Form1.TXT_VALUE_DATE_DD.value ");
					out.println(" 	m_value_mm = document.Form1.TXT_VALUE_DATE_MM.value ");
					out.println("		m_value_yy = document.Form1.TXT_VALUE_DATE_YY.value ");
					out.println("   m_hid_dd = document.Form1.HID_VAL_DD.value ");
					out.println("   m_hid_mm = document.Form1.HID_VAL_MM.value ");
					out.println("   m_hid_yy = document.Form1.HID_VAL_YY.value ");
					out.println("   m_val_dd=parseInt(m_value_dd)");
					out.println("   m_val_mm=parseInt(m_value_mm)");
					
					out.println("   m_val_yy=parseInt(m_value_yy)");
					out.println("   m_cur_dd=parseInt(m_hid_dd)");
					out.println("   m_cur_mm=parseInt(m_hid_mm)");
					out.println("   m_cur_yy=parseInt(m_hid_yy)");
					out.println("   alert('m_val_dd'+m_val_dd+'m_val_mm'+m_val_mm+'m_val_yy'+m_val_yy);");
					out.println("   alert('m_val_dd'+document.Form1.TXT_VALUE_DATE_DD.value+'m_val_mm'+document.Form1.TXT_VALUE_DATE_MM.value+'m_val_yy'+document.Form1.TXT_VALUE_DATE_YY.value);");
					out.println("   if(m_val_yy < m_cur_yy - 1){");
					out.println("      alert('Value Date cannot be older than one month1'); ");
					out.println("      return false; ");
					out.println("   }                ");
					out.println("   else if(m_val_yy == m_cur_yy-1 && m_cur_mm != 1){");
					out.println("      alert('Value Date cannot be older than one month2'); ");
					out.println("      return false; ");
					out.println("   }                ");
					out.println("   else if(m_val_yy < m_cur_yy && m_val_mm%12>m_cur_mm-1){");
					out.println("      alert('Value Date cannot be older than one month3'); ");
					out.println("      return false; ");
					out.println("   }                ");
					out.println("   else if(m_val_yy==m_cur_yy && m_val_mm<m_cur_mm-1){");
					out.println("      alert('m_val_mm='+m_val_mm+'m_cur_mm='+m_cur_mm);");
					out.println("      alert('Value Date cannot be older than one month4'); ");
					out.println("      return false; ");
					out.println("   }                ");
					out.println("   else {           ");
					out.println("      return true;  ");
					out.println("   }                ");
					out.println("}                   ");*/
					// End by Dineth on 2008-10-03
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Invoice_Reversal_Option?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Invoice_Reversal_Option?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Invoice Reversal Option - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Invoice Reversal Option - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
					
					
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					//out.println("load_help_msg();"); 
					out.println("}");
					 
					out.println("else{");
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					//out.println("edit_window();"); 

					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_delete.value=\"Delete\";"); 
					out.println("document.Form1.hid_save.value=\"reverse\";");
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("	if(confirm(\"Are you sure you want to modify records?\")){ "); 
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("document.Form1.hid_delete.value=\"Modify\";");
					out.println("document.Form1.hid_save.value=\"generate\";");
					out.println("chng_butt();");					
					out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
					out.println("details.innerHTML=\"\";");
					out.println("document.Form1.TXT_VALUE_DATE_DD.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_MM.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_YY.disabled=true;");
					out.println("}"); 
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


					out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	");
					
					out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					out.println("	if(oBj.valout[4] ==\" \"){"); 
					out.println(" clear(); ");
					out.println(" }");
					
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					out.println("if(document.Form1.hid_help_type.value=='1'){"); 
					out.println("		help_value_assign_1(oBj);"); 
					out.println("}");
					
					out.println("	}"); 
					out.println("	else{"); 
					out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("		return false;"); 
					out.println("	} "); 
					out.println("	}"); 
					out.println("	else{	"); 
					out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
					out.println("	}	"); 
					out.println("	}		"); 
					out.println("	else{");
					out.println("clear()");
					out.println("	}");
					out.println("}");
					out.println("if(oBj.valout[2]==' '){");
					out.println("clear()");
					out.println("	}	"); 
					out.println("}"); 
			
					out.println("function Prev(Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 

					out.println("function Next (Start,End,Hid_No){"); 
					out.println("    HelpBox(Start,End,Hid_No);"); 
					out.println("}"); 
					out.println(""); 
					
					
					out.println("function clear(){");
					out.println("   if(document.Form1.hid_help_type.value==\"1\"){;"); 
					out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
					
					out.println(" details.innerHTML = ''; ");
					out.println("}");
					out.println("}");
					
					out.println("function help_button_1() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    m_sql = \"FinanceSql\";"); 
					out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\";");
					out.println("    HelpBox('1','10','0');"); 
					out.println("}");
					
					out.println(" function help_value_assign_1(oBj) {"); 
					out.println(" document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
					out.println("load_details()");
					out.println("}"); 
					
					out.println("function check_date_start(row){ ");
					out.println("  checkMonthLength(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);");
					out.println("}");

					out.println("function load_calendar(num) {");
      		out.println(" document.Form1.hid_cal_date.value=num;"); 
					out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
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
					out.println("     document.Form1.TXT_VALUE_DATE_DD.value=v_dd;");
					out.println("     document.Form1.TXT_VALUE_DATE_MM.value=v_mm;");
					out.println("     document.Form1.TXT_VALUE_DATE_YY.value=v_yy;");
					out.println("  }");	
				  	
					//out.println(" if( (document.Form1.TXT_START_DATE_DD.value !=\"\")&&(document.Form1.TXT_START_DATE_MM.value !=\"\")&&(document.Form1.TXT_START_DATE_YY.value !=\"\")){");
					//out.println("chk_validity()");
					//out.println("}");
					
					out.println("}");	
					out.println("function sysdate() {");
					out.println("document.Form1.hid_text.value=\"J6\"");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate\";");
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					//---------------------------Added by Sandun on 11-03-2009----------------------------
				
					
					out.println("function validate_data(){"); 
					out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
					out.println("DIV_TXT_FINANCE_NO.style.color='red';");
					out.println("return false;"); 
					out.println("}else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 
					 
					out.println("function disabled_all(){"); 
					out.println("for(var y=0;y<parseInt(document.Form1.INV_COUNT.value);y++){");
					out.println("document.Form1.elements[\"CHK_\"+y].disabled      =false;");	
					out.println("}");
					out.println("document.Form1.TXT_VALUE_DATE_DD.disabled=false;");
					out.println("document.Form1.TXT_VALUE_DATE_MM.disabled=false;");
					out.println("document.Form1.TXT_VALUE_DATE_YY.disabled=false;");
					out.println("}");
			
					out.println("function count_receipts(){ ");			
					out.println("count=0;");		
					out.println("for(i=0;i<parseInt(document.Form1.INV_COUNT.value);i++){");	
					out.println("m_chk=\"CHK_\"+i;");
					out.println("if(document.Form1.elements[m_chk].checked==true){");
				  out.println("count=count+1;");
					out.println("}");		
					out.println("}");				
					out.println("if(count>0)");
					out.println("return true;");
					out.println("else");
					out.println("return false;");		
					out.println("}"); 
			
					out.println("function ckeck_data(){ "); 			
					out.println("b_flag=0;");						
					out.println("if(details.innerHTML==\"\"){");
					out.println("alert('No data to delete..!');");
					out.println("b_flag=1;");
					out.println("}"); 
					out.println("else if(!count_receipts()){"); 
					out.println("alert('No invoice selected...!');");
					out.println("b_flag=1;");
					out.println("}"); 						
					out.println("else{");
					out.println("b_flag=0;");
					out.println("}"); 			
		      out.println("}"); 
			    
					out.println("function before_submit(){ "); 
					out.println("if(validate_data()){"); 
					//out.println("if(chk_rev_date()){");
					out.println("ckeck_data();");
					out.println("if(b_flag==0){");
					out.println("disabled_all();");
					out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
					out.println("		if(validate_data()){");	
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Invoice_Reversal';");  
					out.println("		document.Form1.submit();	"); 
					out.println("		}"); 
					out.println("		}"); 
					out.println("		}"); 
					//out.println("		}"); 
					out.println("		}"); 
					out.println("else{");
					out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
					out.println("} "); 			
					out.println("} "); 
										
				out.println("function check_status_inv(num2) {");				
				out.println("if(document.Form1.elements[\"CHK_\"+num2].checked){");
				out.println("validate_check_status_inv(num2);"); 
				out.println("if(b_flag_inv==0){");
				out.println("  document.Form1.elements[\"CHK_\"+num2].value       =\"on\";");
				out.println("  document.Form1.elements[\"CHK_\"+num2].disabled    =true;");
				out.println("}");
				out.println("else{");
				out.println("alert('Please select the invoces by order');");
				out.println("  document.Form1.elements[\"CHK_\"+num2].checked       = false;");
				out.println("  document.Form1.elements[\"CHK_\"+num2].value        =\"off\";");
				out.println("  document.Form1.elements[\"CHK_\"+num2].disabled      =false;");				
				out.println("}");				
				out.println("}else{");				
				out.println("  document.Form1.elements[\"CHK_\"+num2].value       =\"off\";");
				out.println("}");
				out.println("}");
				
				out.println("function validate_check_status_inv(num2) {");
				out.println("b_flag_inv=0;");
				out.println("var m_count_inv=num2;");
				out.println("if(parseInt(m_count_inv)!=0) {");
				out.println("if(document.Form1.hid_status.value==\"New\"){");
				out.println("for(var m_inv=0;m_inv<parseInt(m_count_inv);m_inv++){");
				out.println("if(!document.Form1.elements[\"CHK_\"+m_inv].checked){");
				out.println("b_flag_inv=1;");
				out.println("break;");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("function chk_rev_date(){");
				out.println("rev_date = document.Form1.TXT_VALUE_DATE_DD.value+'-'+document.Form1.TXT_VALUE_DATE_MM.value+'-'+document.Form1.TXT_VALUE_DATE_YY.value");
				out.println("sys_date = document.Form1.HID_VAL_DD.value+'-'+document.Form1.HID_VAL_MM.value+'-'+document.Form1.HID_VAL_YY.value;");
				out.println("var date1 = new Date(rev_date);");
				out.println("var date2 = new Date(sys_date);");
				out.println("if(date1 > date2){");
				out.println("alert(\"Value date should not be greater than system date..! \");");
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				out.println("function chng_butt(){");
				out.println("if(document.Form1.hid_status.value==\"Edit\"){");
				out.println("document.Form1.MAIN_BUT_3.value=\"Generate\"; ");	
				out.println("}");	
				out.println("}");	
				
				
				out.println("function check_value_date(m,m_invoice_no){");
				out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
				out.println("document.Form1.hid_text.value=\"J7\"");
			  out.println("m_dd=document.Form1.TXT_VALUE_DATE_DD.value;");
				out.println("m_mm=document.Form1.TXT_VALUE_DATE_MM.value;");
				out.println("m_yy=document.Form1.TXT_VALUE_DATE_YY.value;");
				out.println(" m_date = m_dd+'-'+m_mm+'-'+m_yy; ");				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_value_date&m=\"+m+\"&date=\"+m_date+\"&m_inv_no=\"+m_invoice_no+\" \";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");	
				out.println("}");	
				
				
				
				
					//-------------------------------end---------------------------------------------------------------------
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"sysdate()\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"reverse\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_delete' VALUE=\"Delete\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_text' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_SCREEN' VALUE=\"AF_CR_PRO_INVOICE_REVERSAL_OPTION\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_OPTION' VALUE=\"NEW\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_DD' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_MM' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_YY' VALUE=\"\">");
					
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Invoice Reversal Option</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  //modified by nuwan  de silva 20-08-07
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");'  onClick='before_submit()' name=\"MAIN_BUT_3\" value=\"Reverse\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  

					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


					out.println("<table align='center' width='100%' class='table'>");
					
					
					out.println("<tr >"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_VALUE_DATE' class=div_input>Value Date</div></td>"); 
					out.println("<td width=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_start()\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_start()\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_VALUE_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_start()\" ><a href style='{cursor:hand; }' onclick=load_calendar('1') >   Calendar</a>");	//
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 


					
					
					
					out.println("<tr >"); 
					out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO' class=div_input>Finance No*</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
			
					
					out.println("</table>");
					
					out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
					out.println("<td ><div id=details></div></td></tr></table>");
		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();
			

						
						
						
						
						
						
						
			}
			
			else if(m_chksql.equals("details")){
			String	m_fin_no     = req.getParameter("FINANCE_NO");
			String	m_value_date = req.getParameter("VALUE_DATE");
			String	m_option     = req.getParameter("status");
			String  m_status = "";
			String  m_inv_no = "";
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			
			if(m_option.equals("New")){
			m_status = "Y";
			}
			else if(m_option.equals("Edit")){
			m_status = "C";
			}
			
																	
														
					rs=stmt.executeQuery(" SELECT INVOICE_NO,VALUE_DATE,AMOUNT,TYP,STATUS,M_DATE,BAL,CAN_DATE "+
					                     " FROM( "+
															 " SELECT A.INVOICE_NO INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE ,TOTAL_AMOUNT AMOUNT,'CR' TYP ,B.ACTIVE_STATUS STATUS , A.VALUE_DATE M_DATE,A.BALANCE_TO_BE_RECEIVED BAL,NVL(TO_CHAR(A.INV_REV_DATE,'DD-MM-YYYY'),TO_CHAR(A.MOD_DATE,'DD-MM-YYYY')) CAN_DATE "+
													     " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS B"+
													     " WHERE  A.INVOICE_NO = B.INVOICE_NO "+
															 " AND A.FINANCE_NO  = '"+m_fin_no+"' "+
													     " AND A.ACTIVE_STATUS = '"+m_status+"' "+
													     " AND A.INVOICE_TYPE IN ('INV_GENER','INV_RESI') "+
														   " AND A.ADJUSTED_DATE IS NOT NULL "+
													     " AND B.ACTIVE_STATUS <> 'A' "+
															 
																
												       " UNION ALL "+
													
													     " SELECT A.INVOICE_NO INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,TOTAL_AMOUNT AMOUNT,'DR' TYP,'' STATUS,A.VALUE_DATE M_DATE,BALANCE_TO_BE_RECEIVED BAL,NVL(TO_CHAR(A.INV_REV_DATE,'DD-MM-YYYY'),TO_CHAR(A.MOD_DATE,'DD-MM-YYYY')) CAN_DATE"+
															 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
 													     " WHERE A.FINANCE_NO  = '"+m_fin_no+"' "+
													     " AND A.ACTIVE_STATUS = '"+m_status+"' "+
													     " AND A.INVOICE_TYPE IN ('INV_GENER','INV_RESI') "+
													     " AND A.ADJUSTED_DATE IS NULL "+
														 //" AND A.MOD_DATE IN (SELECT MAX(MOD_DATE) FROM "+m_schema_name+".AF_CO_PRO_INVOICE WHERE FINANCE_NO='"+m_fin_no+"' AND ACTIVE_STATUS = '"+m_status+"' )"+ 	--comment by ns on 01-03-2013
															 " ) "+
															 " ORDER BY M_DATE DESC ");
			int j=0;
	    boolean more = rs.next();
			if(!more){
					out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
					out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
          out.println("</TR></table>");
			}
			if(more){		
			
			out.println("<br>");	
			out.println("<b><HR>");	
			out.println("<br>");	
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
      out.println("<tr class=pdn_txtpos2 align='left'>");
			out.println("<td  width='25%'>Invoice No</td>");
      out.println("<td  width='25%'>Value Date</td>");
			if(m_option.equals("Edit")){
			out.println("<td  width='10%'>Cancel Date</td>");
			}
			out.println("<td  width='25%' align=right>Total Amount</td>");
			out.println("<td  width='25%' align=left>&nbsp;Remark</td>");
			out.println("<td  width='5%' align=right>&nbsp;</td>");
			while(more){
			
			 rs1=stmt1.executeQuery(" SELECT  NVL(A.INVOICE_NO,'N/A') "+
														  " FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
														  " WHERE TO_DATE(TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') = TO_DATE('"+rs.getString(2)+"','DD-MM-YYYY') "+
															" AND     A.APPLICATION_NO IN (SELECT B.APPLICATION_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B WHERE B.FINANCE_NO='"+m_fin_no+"')");
																	
			
			
			if(rs1.next()){
			m_inv_no = rs1.getString(1);
			}
					
			
			if(j>0 && j%2==1){
              out.println("<tr class=tr_input1 >");
					}
					else{
									
               out.println("<tr class=tr_input >");
					}
				if(m_option.equals("Edit") && m_inv_no.equals("N/A")){					
			
			out.println("<td width='25%' align='left' >"+rs.getString(1)+"<input type='hidden' name='HID_INV_NO_"+j+"' value="+rs.getString(1)+"></td>");
			out.println("<td width='25%' align='left' >"+rs.getString(2)+"</td>");
			out.println("<td width='10%' align='left' >"+rs.getString(8)+"</td>");
			out.println("<td width='25%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			if(rs.getString(4).equals("CR") && !rs.getString(5).equals("C") && rs.getDouble(7)!=0){
			out.println("<td width='25%'>&nbsp;Credit Note/Adjustment</td>");
			out.println("<td  width='5%' align=center><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_inv("+j+");check_value_date("+j+",'"+rs.getString(1)+"')\" disabled></td>");
			
			}else{
			out.println("<td width='25%'>&nbsp;-</td>");
			out.println("<td  width='5%' align=center><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_inv("+j+");check_value_date("+j+",'"+rs.getString(1)+"')\" ></td>");
			}
			
			}
			else if(m_option.equals("New")){//option NEW
			
			out.println("<td width='25%' align='left' >"+rs.getString(1)+"<input type='hidden' name='HID_INV_NO_"+j+"' value="+rs.getString(1)+"></td>");
			out.println("<td width='25%' align='left' >"+rs.getString(2)+"</td>");
			
			out.println("<td width='25%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			if(rs.getString(4).equals("CR") && !rs.getString(5).equals("C") && rs.getDouble(7)!=0){
			out.println("<td width='25%'>&nbsp;Credit Note/Adjustment</td>");
			out.println("<td  width='5%' align=center><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_inv("+j+");check_value_date("+j+",'"+rs.getString(1)+"')\" disabled></td>");
			
			}else{
			out.println("<td width='25%'>&nbsp;-</td>");
			out.println("<td  width='5%' align=center><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_inv("+j+");check_value_date("+j+",'"+rs.getString(1)+"')\" ></td>");
			}
			
			}
			
			more=rs.next();
			j=j+1;
			}
			out.println("<input type='hidden' name='INV_COUNT' value="+j+">");
			}
			
			}
			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
	
