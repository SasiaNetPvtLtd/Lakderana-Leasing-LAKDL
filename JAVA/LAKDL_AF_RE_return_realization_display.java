
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - RETURN & REALIZATION
//CREATED BY:M.M. Wickramasekara
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_return_realization_display extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	Statement stmt,stmt1;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name;

			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			String m_chksql = req.getParameter("chksql");

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			
			rs=stmt.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");											
			
			boolean more4=rs.next();
			if(more4){
			//out.println(" m_sysdate = '"+rs.getString(1)+"'");			
			
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			
			} 
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					
	    else if(m_chksql.trim().equals("main_page")){

			
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Documents Required</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("var b_flag=0;");
			out.println("var new_data_vec=new Array();");
			
			
			out.println("function get_vector_normal(http_response){ ");
			out.println(" e_deposit_details.innerHTML = ''; ");
			out.println(" e_deposit_details.innerHTML = http_response; ");
			out.println("}");

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_deposit' ){");
			out.println("			display_receipt(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_deposit' ){");
			out.println("     alert('No records available ')");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"DEL\" && document.Form1.hid_help_status.value == 'H_deposit_del' ){");
			out.println("			display_receipt(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"DEL\" && document.Form1.hid_help_status.value == 'H_deposit_del' ){");
			out.println("     alert('No records available ')");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_accno' ){");
			//out.println("				alert('Record already Exists.');");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			
			out.println("		 else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_accno' ){");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			//added by nuwan de silva on 16-08-07-----------------------
			out.println("		 else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_Comment' ){");
			out.println("			help_comment(document.Form1.hid_row_no.value);");
			out.println("			}");
			//added by nuwan de silva on 16-08-07-----------------------
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_Comment' ){");
			out.println("				assign_data_comment(data_vec);");
			out.println("			}");
			
			/*out.println("    else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\" ){");
      out.println("    assign_data(data_vec);");
      out.println("    }");
      out.println("    else if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_CODE.value !=''&& document.Form1.hid_help_status.value == 'H9' ){");
			//out.println("    alert('Selected District code is incorrect,use help...!')");
			out.println("    help_update();");
      out.println("   }");*/
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			out.println("document.Form1.hid_from_date.value=document.Form1.TXT_REAL_DATE_DD.value+'-'+document.Form1.TXT_REAL_DATE_MM.value+'-'+document.Form1.TXT_REAL_DATE_YY.value;");
			out.println("document.Form1.hid_to_date.value=document.Form1.TXT_REAL_DATE_DD2.value+'-'+document.Form1.TXT_REAL_DATE_MM2.value+'-'+document.Form1.TXT_REAL_DATE_YY2.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization&data_val=\"+obj.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value;");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest_new(obj) {");
			out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			out.println("document.Form1.hid_from_date.value=document.Form1.TXT_REAL_DATE_DD.value+'-'+document.Form1.TXT_REAL_DATE_MM.value+'-'+document.Form1.TXT_REAL_DATE_YY.value;");
			out.println("document.Form1.hid_to_date.value=document.Form1.TXT_REAL_DATE_DD2.value+'-'+document.Form1.TXT_REAL_DATE_MM2.value+'-'+document.Form1.TXT_REAL_DATE_YY2.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=get_receipts&data_val=\"+obj.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value;");
			out.println("		load_interface(m_url,'NORM');");
			out.println("}");
			
 		 out.println("function makeRequest_new_chq(obj) {");
			out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=get_receipts_CHQ&data_val=\"+obj.value+\"\";");
			out.println("		load_interface(m_url,'NORM');");
			out.println("}");
		
		//---Added by Prabash on 09-02-2012----------**	
			out.println("function check_number_decimal(obj,size){");
			out.println("var m_length=obj.value.length;");
			out.println("if (obj.value != \"\") {");
			out.println("if(isnumberok(obj)){"); 
			out.println("if(m_length!= size){"); 
			out.println("alert('The length of the Cheque No should be a Six Characters');");
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("alert('please enter a number');"); 
			out.println("obj.value='';"); 
			out.println("obj.focus();"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}");
		//----------------------------------------	
			out.println("function makeRequest_cheque_details(obj) {");
			//out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=get_cheques_details&data_val=\"+obj.value+\"\";");
			//out.println("			window.open(m_url);");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			//out.println("		load_interface(m_url,'NORM');");
			out.println("}");
			

			
			
			out.println("function makeRequest_comment(obj) {");
			//out.println(" document.Form1.hid_help_status.value ='H_Comment'; ");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_cheque_return_narrations&data_val=\"+obj.value+\"&ac_status=Y\";");
			//out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest3(obj) {");
			
			out.println(" real_dd = document.Form1.TXT_REAL_DATE_DD.value ");
			out.println(" real_mm = document.Form1.TXT_REAL_DATE_MM.value ");
			out.println(" real_yy = document.Form1.TXT_REAL_DATE_YY.value ");
			
			out.println(" real_date = real_dd+'-'+real_mm+'-'+real_yy; ");
			
			out.println(" real_dd_to = document.Form1.TXT_REAL_DATE_DD2.value ");
			out.println(" real_mm_to = document.Form1.TXT_REAL_DATE_MM2.value ");
			out.println(" real_yy_to = document.Form1.TXT_REAL_DATE_YY2.value ");
			
			out.println(" real_date_to = real_dd_to+'-'+real_mm_to+'-'+real_yy_to; ");
			
			out.println(" if(validate_date()) { ");
			//out.println(" alert('DAte To @@'+real_date_to ); ");
			out.println(" document.Form1.hid_help_status.value ='H_deposit_del'; ");
			//out.println("    if(document.Form1.hid_help_status.value=='H_deposit' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization_del&data_val=\"+obj.value+\"&data_val2=\"+real_date+\"&data_val3=\"+real_date_to;");
		//	out.println("   window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println(" }");
			out.println("}");
			
			
			
			out.println("function validate_date_2(){");
			out.println(" m_line_no = document.Form1.hid_deposit_lineno.value ; ");
			//out.println(" alert('Line No @'+m_line_no);");
			out.println(" for(var i=0;i<m_line_no;i++){ ");
			
			
			
			out.println(" real_dd =\"TXT_REAL_DATE_DD\"+i ");
			out.println(" real_mm =\"TXT_REAL_DATE_MM\"+i ");
			out.println(" real_yy =\"TXT_REAL_DATE_YY\"+i ");
			
			
		//	out.println("alert('test d'+document.Form1.elements[real_dd].value);");
		//	out.println("alert('test m'+document.Form1.elements[real_mm].value);");
		//	out.println("alert('test y'+document.Form1.elements[real_yy].value);");
			
			out.println(" if( document.Form1.elements[real_dd].value !='' || document.Form1.elements[real_mm].value !='' || document.Form1.elements[real_yy].value !='' ) {");
			out.println("    if(!checkMonthLength(document.Form1.elements[real_dd],document.Form1.elements[real_mm],document.Form1.elements[real_yy])){  "); 
			out.println("     return false ;"); 
			
			out.println("    }");
			out.println("  }");
		//		out.println("alert('test i'+i);");
			out.println(" }");
			out.println("      return true;"); 
			
			out.println("}");
			

			
		  out.println("function validate_date(){");
			
			
			
			out.println(" real_dd = document.Form1.TXT_REAL_DATE_DD.value ");
			out.println(" real_mm = document.Form1.TXT_REAL_DATE_MM.value ");
			out.println(" real_yy = document.Form1.TXT_REAL_DATE_YY.value ");
			
			out.println(" real_dd_to = document.Form1.TXT_REAL_DATE_DD2.value ");
			out.println(" real_mm_to = document.Form1.TXT_REAL_DATE_MM2.value ");
			out.println(" real_yy_to = document.Form1.TXT_REAL_DATE_YY2.value ");
			
			
			out.println(" if(real_dd != '' && real_mm !='' && real_yy !=''  ) { ");
			out.println("  if(checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)){  "); 
		//	out.println("alert('test1');");
			out.println("   return true;"); 
			out.println("  }");
			out.println(" else {");
			
			out.println(" 	 if(real_dd_to != '' && real_mm_to !='' && real_yy_to !=''  ) { ");
			out.println("  		  if(checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2)){  "); 
		//	out.println("alert('test2');");
			out.println("   	  	return true;"); 
			out.println("  	  	}");
			out.println("       else "); 
			out.println("       return false; "); 
			out.println("    }");
			out.println(" 	 else { ");
			out.println("   	 alert('To date cannot be null ')");
			out.println("   	 return false;"); 
			out.println(" 	 }");
			
			out.println("  }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			
		
			
			out.println(" }");
			
			out.println("function makeRequest2(obj) {");
			out.println(" document.Form1.hid_help_status.value ='H_accno'; ");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization2&data_val=\"+obj.value;");
			//out.println("  window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");

      out.println("   function assign_data(data_vec) { ");
			out.println("    document.Form1.TXT_ACC_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_BANK_NAME.value=data_vec[3];"); 
			//out.println("    makeRequest(document.Form1.TXT_ACC_NO);");
			out.println("}");
			
			//added by nuwan de silva on 16-08-07-----------------------
			out.println("   function assign_data_comment(data_vec) { ");
			out.println("    m_comment = \"TXT_COMMENT\"+document.Form1.hid_row_no.value;");
			out.println("    document.Form1.elements[m_comment].value=data_vec[0];"); 
			out.println("}");
			
			
			
			out.println(" function assign_help_status(obj){");
      out.println(" document.Form1.hid_help_status.value =obj; ");
      out.println("}");
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_ACC_NO.value==\"\" ){  "); 
			out.println("DIV_TXT_ACC_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.SCREEN_NAME.value==\"DEL2\" && (document.Form1.TXT_REAL_DATE_DD.value==\"\" || document.Form1.TXT_REAL_DATE_MM.value==\"\" || document.Form1.TXT_REAL_DATE_YY.value==\"\") ){  "); 
			out.println("DIV_TXT_REAL_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		
			out.println("else if(document.Form1.SCREEN_NAME.value==\"DEL2\" && (document.Form1.TXT_REAL_DATE_DD2.value==\"\" || document.Form1.TXT_REAL_DATE_MM2.value==\"\" || document.Form1.TXT_REAL_DATE_YY2.value==\"\") ){  "); 
			//out.println("DIV_TXT_REAL_DATE_TO.style.color='red';");
			out.println("TO_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
		
			
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function ckeck_receipts(){ "); 
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("if(e_deposit_details.innerHTML==\"\"){");
			out.println("alert('No Receipts To Deposit');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_receipts()){"); 
			out.println("alert(\"Please either 'return status' or 'realised status' \");");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			/*---------------------------------------------------------------------
			Purpose : This Function Used TO Count THe Number Of Receipts Selected
			--------------------------------------------------------------------*/
			out.println("function count_receipts(){ ");
			out.println("count=0;");
		//	out.println("alert('arr size'+arr_size);");
				out.println(" m_line_no = document.Form1.hid_deposit_lineno.value ; ");
				//out.println("alert('arr size'+m_line_no);");
			out.println("for(i=0;i<m_line_no;i++){");
			out.println("m_chk_realize=\"CHK_REALIZED_STATUS\"+i;");
			out.println("m_chk_return=\"CHK_RETURNED_STATUS\"+i;");
			
			out.println("if(document.Form1.elements[m_chk_realize].checked==true || document.Form1.elements[m_chk_return].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
			
			//modified by madhawa 2009-10-22
			out.println("function valtwodate(){");
			out.println("if(!checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)){");
			//out.println("alert(\"check From Date\");");
			out.println("return false;");
			out.println("}");
			out.println("else if(!checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2 )){");
			//out.println("alert(\"check To Date\");");
			out.println("return false;");
			out.println("}");
			out.println("else{ return true;");
			out.println("}}");
			//modified by madhawa 2009-10-22
			

			out.println("function before_submit(){ "); 
			//modified by madhawa 2009-10-22
			out.println(" if(valtwodate()){");
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Delete\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("   }"); 
			out.println("		if(validate_data()){");
			out.println("ckeck_receipts();");
			out.println("if(b_flag==0)");
		//	out.println("		if(validate_date_2()){");
			out.println("		if(confirm(m_save_msg)){ ");
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_return_realization';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			//out.println("		}");
     // out.println("else{");
		//	out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
		//	out.println("} "); 
			out.println("		}");
      out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 

			//modified by madhawa 2009-10-22
			out.println("} ");	
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}");

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_return_realization_display\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Return & Realisation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Return & Realisation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){ "); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window(); "); 
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=true;"); 
			out.println("document.Form1.BUT_DETAILS.disabled=true;"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_DD2.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM2.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY2.disabled=false;"); 
			out.println("document.Form1.BUT_DETAILS.disabled=false;");
			out.println("document.Form1.BUT_SHOW_RECEIPT.disabled=true;");
			
			out.println("clear_all(); ");
			//out.println("document.Form1.TXT_DESCRIPTION.disabled=false;"); 
			out.println("document.Form1.hid_status.value=\"Delete\";"); 
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
			
			//added by nuwan de silva 19-07-07-------------------------------------
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\"");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_fields(IfCount);");
			out.println("	}else");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_comment(oBj);"); 
				out.println("}");
				
			
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
			out.println("	clear_fields(IfCount);");//Added To The Clear The Area Code
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
		
		

		/*	out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
		
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	"); 
			//out.println("window.open('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'')"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			out.println("if(oBj.valout[1]=='Next')  {");
			out.println("Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[1]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			/*out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");*/
			/*	out.println("}");
				out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("if(IfCount=='99'){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(oBj);"); 
				out.println("");
				out.println("}");
				out.println("else if(IfCount=='3'){"); 
				out.println("		help_value_assign_3(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='4'){"); 
				out.println("		help_value_assign_4(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='5'){"); 
				out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='6'){"); 
				out.println("		help_value_assign_6(oBj);"); 
				out.println("}");
		
				
					
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
			out.println(""); 

			
			
			out.println("function clear_fields() {");
			out.println("document.Form1.TXT_ACC_NO.value='';"); 
			out.println("document.Form1.TXT_BRANCH_NAME.value='';"); 
			out.println("document.Form1.TXT_BANK_NAME.value='';"); 
			out.println("e_deposit_details.innerHTML='' ");
			//out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("}");
			

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			*/
			
			
			out.println("function clear_fields(IfCount) {");
			
			out.println("if(IfCount=='99'){");
			out.println("document.Form1.TXT_ACC_NO.value='';"); 
			out.println("document.Form1.TXT_BRANCH_NAME.value='';"); 
			out.println("document.Form1.TXT_BANK_NAME.value='';"); 
			out.println("e_deposit_details.innerHTML='' ");
			out.println("}");
			out.println("else if(IfCount=='2'){");
			out.println("    m_comment = \"TXT_COMMENT\"+document.Form1.hid_row_no.value;");
			out.println("    document.Form1.elements[m_comment].value='';"); 
			out.println("}");
			
			out.println("}");
	
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Crit = document.Form1.TXT_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			//out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			//added by nuwan de silva on 16-08-07-----------------------
			out.println("function help_comment(rowNo) {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_comment = \"TXT_COMMENT\"+rowNo;");
			out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("    m_sql = \"m_help_TXT_CHQ_NARRATIONS_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.elements[m_comment].value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'2');"); 
			out.println("}"); 
			
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_DESC_sql_1\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BANK_NAME.value=oBj.valout[5];"); 
			//out.println("    makeRequest(document.Form1.TXT_ACC_NO);");
			out.println("}"); 
			
			out.println("function help_value_assign_comment() {"); 
			out.println("    m_comment = \"TXT_COMMENT\"+document.Form1.hid_row_no.value;");
			out.println("    document.Form1.elements[m_comment].value=oBj.valout[3];"); 
			out.println("}"); 
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}");  
			
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}");
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
									
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_CODE_sql_1\";");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 

			out.println("function change_val_return_status(row_no){")	;
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("m_return_charge=\"TXT_RET_CHARGE\"+row_no;");
			out.println("m_comment=\"TXT_COMMENT\"+row_no;");
			out.println("m_btn=\"BUT_HELP\"+row_no;");
			
			out.println("if(document.Form1.elements[m_chk_return_status].checked==true && document.Form1.elements[m_chk_realize_status].checked==true ){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("document.Form1.elements[m_return_charge].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_return_charge].value=\"500\";"); //added by nuwan de silva on 14-08-07//100->500 BY LALANKA ON 3-11-2009
			out.println("document.Form1.elements[m_chk_realize_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_realize_status].value = 'off'; ");
			out.println("document.Form1.elements[m_btn].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("}else if(document.Form1.elements[m_chk_return_status].checked==true && document.Form1.elements[m_chk_realize_status].checked==false ){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("document.Form1.elements[m_return_charge].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_return_charge].value=\"500\";"); //added by nuwan de silva on 14-08-07//100->500 BY LALANKA ON 3-11-2009
			out.println("document.Form1.elements[m_btn].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].disabled=false;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("}");	
			out.println("else { ");
			out.println("document.Form1.elements[m_chk_return_status].value='off'");
			out.println("document.Form1.elements[m_return_charge].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_return_charge].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_btn].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("}");
			out.println("}");
			
			out.println("function change_val_realize_status(row_no){")	;
			
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("m_return_charge=\"TXT_RET_CHARGE\"+row_no;");
			out.println("m_comment=\"TXT_COMMENT\"+row_no;");
			out.println("m_btn=\"BUT_HELP\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_realize_status].checked==true && document.Form1.elements[m_chk_return_status].checked==true ){");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("document.Form1.elements[m_chk_return_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_return_status].value = 'off'; ");
			out.println("document.Form1.elements[m_return_charge].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_return_charge].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_btn].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].value=\"\";"); //added by nuwan de silva on 14-08-07
			
			out.println("}else if(document.Form1.elements[m_chk_realize_status].checked==true && document.Form1.elements[m_chk_return_status].checked==false ){");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("document.Form1.elements[m_return_charge].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_return_charge].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_btn].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].disabled=true;");//added by nuwan de silva on 14-08-07
			out.println("document.Form1.elements[m_comment].value=\"\";"); //added by nuwan de silva on 14-08-07
			out.println("}");	
			out.println("else { ");
			out.println("document.Form1.elements[m_chk_realize_status].value='off'");
			out.println("}");
			
			
			out.println("}");
			
			out.println("function clear_all() { ");
			out.println("    document.Form1.TXT_ACC_NO.value='';"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value='';"); 
			out.println("    document.Form1.TXT_BANK_NAME.value='';"); 
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("}");
			
			out.println("function load_details(lineno){");
			//out.println("alert('linnoe ** '+lineno)");
			out.println(" m_deposit_no = \"hid_deposit_no\"+lineno;");
			out.println(" m_receipt_no = \"hid_receipt_no\"+lineno;");
			out.println(" m_receipt_no_val = document.Form1.elements[m_receipt_no].value;");
			//out.println("alert('receipt no ** '+m_receipt_no_val)");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+m_receipt_no_val;"); 
			out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 	out.println("}");
				
					

		
				
			
			out.println("function change_status(row_no){")	;
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("flag_return = 0;  ");
			out.println("flag_realize = 0;  ");
			out.println("if(document.Form1.elements[m_chk_realize_status].checked == true   ){");
			//out.println("flag_return = 1 ");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("document.Form1.elements[m_chk_return_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_return_status].value = 'off'; ");
			out.println("}");
			
			out.println("if(document.Form1.elements[m_chk_return_status].checked==true){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("document.Form1.elements[m_chk_realize_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_realize_status].value = 'off'; ");
			out.println("}");	
			out.println("}");
			
			
			out.println("function load_sys_date(count){ ");
			
		//	out.println(" alert('count &&'+count); ");
			
			//rs=stmt.executeQuery(" select to_char(sysdate,'dd-mm-yyyy') from dual ");											
			//more4=rs.next();
			
			//if(more4){
			//out.println(" m_sysdate = '"+rs.getString(1)+"'");			
			
			//} 
		//	out.println(" for(var i=0;i<count;i++ ){ ");
			
			//out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+i;");
			//out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+i;");
			//out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+i;");
			
			out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+count;");
			out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+count;");
			out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+count;");
			
		
			//out.println("document.Form1.elements[m_real_dd].value = m_sysdate.substring(0,2)  ");
			//out.println("document.Form1.elements[m_real_mm].value = m_sysdate.substring(3,5)  ");
			//out.println("document.Form1.elements[m_real_yy].value = m_sysdate.substring(6,10)  ");
			
			out.println("document.Form1.elements[m_real_dd].value ='"+m_date_dd+"'   ");
			out.println("document.Form1.elements[m_real_mm].value ='"+m_date_mm+"'   ");
			out.println("document.Form1.elements[m_real_yy].value ='"+m_date_yy+"'   ");

			//out.println("}");
			
			out.println("}");
			
			
			out.println("function show_rec_details(row_No) {"); 
						
			out.println("show_settle_receipt_drill(new_data_vec[row_No]);"); 
			
			out.println("}"); 
			
			
			out.println("function show_deposit_details(row_No) {"); 
						
			out.println("show_deposit_drill(new_data_vec[row_No]);"); 
			
			out.println("}"); 
	
			
			
			out.println("function date_check(row_no){");
			
		//	out.println(" alert('date val @@'+row_no) ");
				
			out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+row_no;");
			out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+row_no;");
			out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+row_no;");
			
			out.println(" if( document.Form1.elements[m_real_dd].value !='' || document.Form1.elements[m_real_mm].value != '' || document.Form1.elements[m_real_yy].value != '' ) {");
			
			out.println("    checkMonthLength(document.Form1.elements[m_real_dd],document.Form1.elements[m_real_mm],document.Form1.elements[m_real_yy])  "); 
			
      out.println("}");	
			
			out.println("}");	
			

			out.println("function assign_date(count,date_val){ ");
		
		//out.println(" alert('date val @@'+date_val) ");
		//	out.println(" line_no = count-1; ");
			
			
		//	out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+line_no;");
		//	out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+line_no;");
		//	out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+line_no;");
			
			
			out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+count;");
			out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+count;");
			out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+count;");

			
			out.println("document.Form1.elements[m_real_dd].value = date_val.substring(0,2)  ");
			out.println("document.Form1.elements[m_real_mm].value = date_val.substring(3,5)  ");
			out.println("document.Form1.elements[m_real_yy].value = date_val.substring(6,10)  ");
			out.println("}");
			
			
			out.println("function display_receipt(data_vec){");
			
			out.println("				new_data_vec=data_vec;");

			out.println(" var i=0; "); 
			out.println(" var line_no=0; "); 
      out.println("var j=0;");

			
			/*out.println("  e_deposit_details.innerHTML='' ");
			out.println("	 e_deposit_details.innerHTML='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println("		'<TR class=pdn_txtpos2 ><TD width=\"11%\" align=\"left\"><B>Receipt No</B></TD> ' +");
			out.println("   '<TD width=\"11%\" align=\"left\"><B>Deposit No</B></TD> '+ ");
			out.println("   '<TD width=\"8%\" align=\"left\"><B>Transaction Date</B></TD>'+");
			out.println("   '<TD width=\"8%\" align=\"left\"><B>Settlement Mode</B></TD>'+ ");
			out.println("   '<TD width=\"8%\" align=\"center\"><B>Returned Status</B></TD>' +");
			out.println("   '<TD width=\"8%\" align=\"center\"><B>Realised Status</B></TD>' +");
			out.println("   '<TD width=\"12%\" align=\"center\"><B>Return Charge</B></TD>' +");
			out.println("   '<TD width=\"15%\" align=\"center\"><B>Comment</B></TD>' +");
			out.println("   '<TD width=\"12%\" align=\"left\"><B>Realised/Returned date</B></TD>' +");
			out.println("   '<TD width=\"8%\" align=\"left\"><B>Reference No</B></TD>' +");
			out.println("   '<TD width=\"8%\"  align=\"right\"><B>Amount</B></TD>' +");
			out.println("   '<TD width=\"*%\" ></TD></TR>' +");
			out.println(" 	'</table>'");
			*/
			
			out.println("  e_deposit_details.innerHTML='' ");
			out.println("	 e_deposit_details.innerHTML='<table  align=\"center\" border=\"0\" width=\"1400\" class=\"table\">'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println("		'<TR class=pdn_txtpos2 ><TD width=\"110\" align=\"left\"><B>Transaction Date</B></TD> ' +"); 
			out.println("   '<TD width=\"110\" align=\"left\"><B>Deposit No</B></TD> '+ "); 
			out.println("   '<TD width=\"100\" align=\"left\"><B>Reference No</B></TD>'+"); 
			out.println("   '<TD width=\"100\" align=\"center\"><B>Returned Status</B></TD>' +");
			out.println("   '<TD width=\"100\" align=\"center\"><B>Realised Status</B></TD>' +");
			out.println("   '<TD width=\"150\" align=\"right\"><B>Return Charge</B></TD>' +");
			out.println("   '<TD width=\"200\" align=\"left\"><B>Comment</B></TD>' +");
			out.println("   '<TD width=\"120\" align=\"left\"><B>Receipt No</B></TD>' +");
			out.println("   '<TD width=\"100\" align=\"left\"><B>Settlement Mode</B></TD>' +"); 
			out.println("   '<TD width=\"100\" align=\"left\"><B>Realised/Returned date</B></TD>'+ ");
			out.println("   '<TD width=\"150\"  align=\"right\"><B>Amount</B></TD>' +");
			out.println("   '<TD width=\"60\" >&nbsp</TD></TR>' +");
			out.println(" 	'</table>'");
			
			
			out.println("  while(i<data_vec.length ) {  ");	
			
			out.println(" m_realize_date_dd = data_vec[i+5].substring(0,2); ");
			out.println(" m_realize_date_mm = data_vec[i+5].substring(3,5); ");
			out.println(" m_realize_date_yy = data_vec[i+5].substring(6,10); ");
			
			
			out.println(" m_receipt_no = '<td width=\"110\"  align=\"left\" STYLE=\"{cursor:hand;}\"  onclick=\"show_rec_details('+[i]+')\" ><u>'+data_vec[i]+'</u><input type=hidden name=hid_receipt_no'+line_no+' value=\"'+data_vec[i]+'\" ></td>';");
			out.println(" m_deposit_no = '<TD WIDTH=\"110\"  align=\"center\" STYLE=\"{cursor:hand;}\"  onclick=\"show_deposit_details('+[i+1]+')\"><u>'+data_vec[i+1]+'</u><input type=hidden name=hid_deposit_no'+line_no+' value=\"'+data_vec[i+1]+'\" ></td>';");		
			out.println(" m_trans_date = '<TD WIDTH=\"110\"  align=\"center\" >'+data_vec[i+2]+'</td>';");		
			out.println(" m_settle_mode = '<TD WIDTH=\"100\" align=\"left\">'+data_vec[i+3]+'</td>';");	
			
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){ ");

			out.println(" if(data_vec[i+4] == 'Y' ){ ");
		  out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\"  ></td>';");		
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_realize_status('+line_no+')\" checked ></td>';");			
			out.println(" } ");
			out.println(" else if(data_vec[i+4] == 'N' ) {");
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\"  ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_return_status('+line_no+')\" checked ></td>';");		
			out.println(" } ");
			out.println(" else {");
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\" ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\" ></td>';");		
			out.println(" } ");
			
			out.println(" } ");
			
			out.println("else if(document.Form1.SCREEN_NAME.value=='DEL'){ ");

			out.println(" if(data_vec[i+4] == 'Y' ){ ");
		  out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\"  disabled ></td>';");		
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_realize_status('+line_no+')\" disabled checked ></td>';");			
			out.println(" } ");
			out.println(" else if(data_vec[i+4] == 'N' ) {");
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\" disabled ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_return_status('+line_no+')\" disabled checked ></td>';");		
			out.println(" } ");
			out.println(" else {");
			out.println(" m_realized_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\" disabled ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\" disabled ></td>';");		
			out.println(" } ");
			
			out.println(" } ");

			
			
			out.println(" m_return_charge='<TD WIDTH=\"150\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_RET_CHARGE'+line_no+' maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;text-align:right;}\" disabled  ></td>';");
			out.println(" m_comment='<TD WIDTH=\"200\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_COMMENT'+line_no+' maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;}\" onBlur=makeRequest_comment(this) disabled >'+ ");
			out.println("           '<input class=\"but_input\" type=\"button\" name=BUT_HELP'+line_no+' value=\"Help\" onClick=\"help_comment('+line_no+')\" disabled  ></td>';");
			
			out.println(" 		if(document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println(" m_realized_date = '<TD WIDTH=\"120\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD'+line_no+' maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\"  >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM'+line_no+' maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\"  >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY'+line_no+' maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onBlur=\"date_check('+line_no+')\" ></TD>';");	
			out.println(" 		}"); 
			out.println(" 		else {");
			out.println(" m_realized_date = '<TD WIDTH=\"120\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD'+line_no+' maxlength=\"2\" size=\"2\" value=\"'+m_realize_date_dd+'\"  disabled>'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM'+line_no+' maxlength=\"2\" size=\"2\" value=\"'+m_realize_date_mm+'\" disabled >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY'+line_no+' maxlength=\"4\" size=\"4\" value=\"'+m_realize_date_yy+'\" onBlur=\"date_check('+line_no+')\"  disabled></TD>';");	
			out.println(" 		}"); 
			
			out.println(" m_cheq_no = '<TD WIDTH=\"100\" align=\"left\" >'+data_vec[i+6]+'</td>';");	
			out.println(" m_amount = '<TD WIDTH=\"150\" align=\"right\">'+data_vec[i+7]+'</td><input type=hidden name=hid_amount'+line_no+' value=\"'+data_vec[i+7]+'\" >';");	
			out.println(" m_but_detail = '<td width=\"60\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_DETAIL'+line_no+' value=\"View\" onClick=\"load_details('+line_no+')\"  style=\"width: 40px\" ></td>'");
			
			
			
//			out.println(" m_write_data = '<TR>'+m_receipt_no+m_deposit_no+m_trans_date+m_settle_mode+m_returned_status+m_realized_status+m_realized_date+m_cheq_no+m_amount+m_but_detail+'</TR>' ");
			
			
			
			out.println("if(j>0 && j%2==1){");  
				
			//out.println(" m_write_data = '<TR class=\"tr_input1\">'+m_receipt_no+m_deposit_no+m_trans_date+m_settle_mode+m_returned_status+m_realized_status+m_realized_date+m_cheq_no+m_amount+m_but_detail+'</TR>' ");
       out.println(" m_write_data = '<TR class=\"tr_input1\">'+m_trans_date+m_deposit_no+m_cheq_no+m_returned_status+m_realized_status+m_return_charge+m_comment+m_receipt_no+m_settle_mode+m_realized_date+m_amount+m_but_detail+'</TR>' ");
			out.println("	}");
			out.println("	else{");
    		
 			//out.println(" m_write_data = '<TR class=\"tr_input\">'+m_receipt_no+m_deposit_no+m_trans_date+m_settle_mode+m_returned_status+m_realized_status+m_realized_date+m_cheq_no+m_amount+m_but_detail+'</TR>' ");
      out.println(" m_write_data = '<TR class=\"tr_input\">'+m_trans_date+m_deposit_no+m_cheq_no+m_returned_status+m_realized_status+m_return_charge+m_comment+m_receipt_no+m_settle_mode+m_realized_date+m_amount+m_but_detail+'</TR>' ");
			out.println("	}");
			
			out.println(" 		e_deposit_details.innerHTML+='<table  align=\"center\" border=\"0\" width=\"1400\" class=\"table\">'+");
			//out.println(" 		e_deposit_details.innerHTML+=");			
			out.println(" 		m_write_data ");
			out.println(" 		'</table>'");
			
			//out.println(" line_no=line_no+1; ");
		/*	out.println(" 		if(document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println(" 		load_sys_date(line_no); ");
			out.println(" 		}"); 
			
			out.println(" 		else {");
			out.println(" 		assign_date(line_no,m_realize_date); ");
			out.println("     m_realize_date = '' ");
			out.println(" 		}"); 
			
			*/
			
			out.println(" i=i+8; ");
			out.println(" line_no=line_no+1; ");
		  out.println("j=j+1;");

		//	out.println("alert('line number'+line_no);");
			
			out.println("	 }");
			
			/*out.println(" 		if(document.Form1.SCREEN_NAME.value=='NEW'){ ");
			out.println(" 		load_sys_date(line_no); ");
			out.println(" 		}"); 
			
			out.println(" 		else {");
			out.println(" 		assign_date(line_no,m_realize_date); ");
			out.println("     m_realize_date = '' ");
			out.println(" 		}"); 
     */
			out.println(" document.Form1.hid_deposit_lineno.value=line_no ; ");
			out.println("}"); 
			
			out.println("function View_Dishonoured_Cheque(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Dishonoured_Cheque_Letter_View\";");
	    out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
	    out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=main_page';"); 
			out.println("}");
			
			//===Added by Prabash on 08-02-2012==============
			out.println("function load_calendar(num) {");
      		out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=340,height=230\");"); 
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
			out.println("     document.Form1.TXT_REAL_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_REAL_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_REAL_DATE_YY.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
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
			out.println("     document.Form1.TXT_REAL_DATE_DD2.value=v_date;");
			out.println("     document.Form1.TXT_REAL_DATE_MM2.value=v_month;");
			out.println("     document.Form1.TXT_REAL_DATE_YY2.value=val;");
			out.println("date2=document.Form1.TXT_REAL_DATE_DD2.value+'-'+document.Form1.TXT_REAL_DATE_MM2.value+'-'+document.Form1.TXT_REAL_DATE_YY2.value;");
			out.println("}");
			out.println("}");
			
			
			//===============================================


			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_deposit_lineno' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); //added by Prabash on 08-02-2012
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Return & Realisation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			
			out.println("<table class='table' cellpadding='0' cellspacing='0' border='0' width='100%'> "); 
			out.println("<tr><td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='8%' align='center'><input type=\"button\" class='mainbut' style='width:120px';  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Dishonoured Cheque\");'  onclick='View_Dishonoured_Cheque()' value=\"Dishonoured Cheque\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'>&nbsp;</td></tr>");  
			out.println("</table>");  
			
				/*	out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='width:150';  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Pending Receipts\");'  onclick='View_pending_receipts()' value=\"View Pending Receipts\"></td>"); 
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='width:120';  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deposit Slip\");'  onclick='View_Diposit_slip()' value=\"Deposit Slip\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					*/
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' BORDER='0' class='table'>"); 
			out.println("<tr>");
			out.println("<td colspan=4 ></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td  width='10%' ><DIV id='DIV_TXT_ACC_NO'  class=div_input> Account No * </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='20' size='20' onblur=\"makeRequest2(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update('1','10','0','m_help_TXT_ACCOUNT_NO_sql','99')\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input> Branch Name </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' Style=\"{width:200px;}\" maxlength='100' size='100' onblur=\"\"  disabled></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_BANK_NAME'  class=div_input> Bank Name </DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_BANK_NAME' Style=\"{width:200px;}\" maxlength='100' size='100' onblur=\"\" disabled ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' BORDER='0' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input> Date From *</DIV></td>");
			//modified by madhawa add checkMonthLength function call on onblur event 
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD maxlength=\"2\" size=\"2\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)\"  >");
			//modified by madhawa add checkMonthLength function call on onblur event 
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM  maxlength=\"2\" size=\"2\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)\"  >");
			//modified by madhawa add checkMonthLength function call on onblur event 
		//	out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY maxlength=\"4\" size=\"4\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)\"  >");	 //comment by Prabash on 08-02-2012
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY maxlength=\"4\" size=\"4\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)\"  ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>");	 //added by Prabsh on 08-02-2012
			out.println("</td> ");
			out.println("<td width='10%' ID='TO_DATE' ><DIV id='DIV_TXT_REAL_DATE_TO'  class=div_input> Date To * </DIV></td>"); 
			//modified by madhawa add checkMonthLength function call on onblur event 
			out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD2 maxlength=\"2\" size=\"2\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2 )\"  >");
			//modified by madhawa add checkMonthLength function call on onblur event 
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM2  maxlength=\"2\" size=\"2\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2 )\"  >");
			//modified by madhawa add checkMonthLength function call on onchange event 
		//	out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY2 maxlength=\"4\" size=\"4\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2 )\"  >");	//comment by Prabash on 08-02-2012
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY2 maxlength=\"4\" size=\"4\" onchange=\"checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2 )\"  ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a>"); //added by Prabsh on 08-02-2012
			out.println("<input class='but_input' type='button' name='BUT_SHOW_RECEIPT' Style=\"{width:110px;}\" value=\"Show Receipts\" onClick=\"makeRequest_new(document.Form1.TXT_ACC_NO)\"  > ");
			out.println("<input class='but_input' type='button' name='BUT_DETAILS' value=\"Details\" onClick=\"makeRequest3(document.Form1.TXT_ACC_NO)\" disabled ></td> ");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' BORDER='0' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CHQ'  class=div_input>Cheque No *</DIV></td>"); 
		//	out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHQ_NO maxlength=\"10\" size=\"10\"  >"); comment by prabash on 09-02-2012
			//out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHQ_NO maxlength=\"10\" size=\"10\" onClick=\"validate_cheq_Number(this,6)\"  >"); // added by prabash on 09-02-2012
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_CHQ_NO maxlength=\"10\" size=\"10\" onblur=\"check_number_decimal(this,6)\"  >"); // added by prabash on 09-02-2012
			out.println("<input class='but_input' type='button' name='BUT_SHOW_RECEIPT' Style=\"{width:110px;}\" value=\"Show Details\"onClick=\"makeRequest_cheque_details(document.Form1.TXT_CHQ_NO)\"  > </td> ");
			out.println("</td> ");
			out.println("<td width='10%' ></td>"); 
			out.println(" <TD WIDTH=\"*%\">");	
			out.println("<input class='but_input' type='button' name='BUT_SHOW_RECEIPT' Style=\"{width:110px;}\" value=\"Show Receipts\" onClick=\"makeRequest_new_chq(document.Form1.TXT_CHQ_NO)\"  > </td> ");
			//out.println("<input class='but_input' type='button' name='BUT_DETAILS' value=\"Details\" onClick=\"makeRequest3(document.Form1.TXT_ACC_NO)\" disabled >");
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			out.println("<table align='center' width='1400' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"1400\"><DIV ID=e_deposit_details>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			
			

			out.println("</body>"); 
			out.println("</html>"); 
		}	
    
		else if(m_chksql.trim().equals("get_receipts")){
		
				String m_val = req.getParameter("data_val").trim();
				String m_from_date = req.getParameter("from_date").trim();
				String m_to_date = req.getParameter("to_date").trim();

		
								rs= stmt.executeQuery ("SELECT UPPER(REC_NO), "+ //1
								" "+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO), "+ //2
								" TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+ //3
								" SETTLE_MODE, "+ //4
								" RECON_STATUS,  "+ //5
								" REALISED_DATE, "+ //6
								" NVL(UPPER(CHEQUE_NO),'-'), "+ //7
								" REC_AMOUNT_CURR  "+ //8
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  a, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS  b , "+m_schema_name+".AF_CO_PRO_DIPOSIT  c "+
								" where b.diposit_no=c.diposit_no "+
								" and a.rec_no=b.receipt_no "+
								" AND c.DIPOSIT_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')	 "+
								" AND c.DIPOSIT_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY')	   "+
								" and a.status='B' "+
								" AND A.SETTLE_MODE IN('CHEQUE')   "+  //'STD_ORD'
								" AND C.ACC_NO=UPPER('"+m_val+"') "+
								" AND A.REALISED_DATE IS NULL  "+
								" AND A.GROUP_REC_NO IS NULL  "+
								" ORDER BY B.diposit_no ");
			boolean more=rs.next();
      if(more){								
			out.println("	  <table  align=\"center\" border=\"0\" width=\"1400\" class=\"table\">");
			out.println("		<TR class=pdn_txtpos2 ><TD width=\"110\" align=\"left\"><B>Transaction Date</B></TD> "); 
			out.println("   <TD width=\"110\" align=\"left\"><B>Deposit No</B></TD>  "); 
			out.println("   <TD width=\"120\" align=\"left\"><B>Receipt No</B></TD>");
			out.println("   <TD width=\"150\"  align=\"right\"><B>Amount</B></TD>");
			out.println("   <TD width=\"100\" align=\"left\"><B>Reference No</B></TD>"); 
			out.println("   <TD width=\"100\" align=\"center\"><B>Returned Status</B></TD>");
			out.println("   <TD width=\"100\" align=\"center\"><B>Realised Status</B></TD>");
			out.println("   <TD width=\"150\" align=\"right\"><B>Return Charge</B></TD>");
			out.println("   <TD width=\"200\" align=\"left\"><B>Comment</B></TD>");
			out.println("   <TD width=\"100\" align=\"left\"><B>Settlement Mode</B></TD>"); 
			out.println("   <TD width=\"100\" align=\"left\"><B>Realised/Returned date</B></TD> ");
			out.println("   <TD width=\"60\" >&nbsp</TD></TR>");
      }
			int j=0;
			while(more){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
			out.println("		<TD width=\"110\" align=\"left\">"+rs.getString(3)+"</TD> "); 
			out.println("   <TD width=\"110\" align=\"left\">"+rs.getString(2)+"<input type=hidden name=hid_deposit_no"+j+" value=\""+rs.getString(2)+"\" ></TD>  "); 
			out.println("   <TD width=\"100\" align=\"center\" STYLE=\"{cursor:hand;}\"  onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u><input type=hidden name=hid_receipt_no"+j+" value=\""+rs.getString(1)+"\" ></TD>");
			out.println("   <TD WIDTH=\"150\" align=\"right\">"+nf.format(rs.getDouble(8))+"</td><input type=hidden name=hid_amount"+j+" value=\""+rs.getDouble(8)+"\" >");	
			out.println("   <TD width=\"100\" align=\"left\">"+rs.getString(7)+"</TD>"); 
			
			/*if(rs.getString(5).equals("Y")){
		  out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_return_status("+j+")\"  ></td>");		
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS"+j+" VALUE=\"on\" onclick=\"change_val_realize_status("+j+")\" checked ></td>");			
			}
			else if(rs.getString(5).equals("N")){
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS"+j+" VALUE=\"on\" onclick=\"change_val_return_status("+j+")\" checked ></td>");		
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_realize_status("+j+")\"  ></td>");			
			}
			*/
			//else {
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_return_status('"+j+"')\" ></td>");		
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_realize_status('"+j+"')\"  ></td>");			
			//}
			
			out.println("<TD WIDTH=\"150\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_RET_CHARGE"+j+" maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;text-align:right;}\" disabled ></td>");

      out.println("<TD WIDTH=\"200\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_COMMENT"+j+" maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;}\" onBlur=makeRequest_comment(this) disabled >");
			out.println("<input class=\"but_input\" type=\"button\" name=BUT_HELP"+j+" value=\"Help\" onClick=\"help_comment("+j+")\" disabled  ></td>");
  					
			out.println("   <TD width=\"100\" align=\"center\">"+rs.getString(4)+"</TD>");
			
			out.println("<TD WIDTH=\"120\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\"  >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\"  >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onBlur=\"date_check("+j+")\" ></TD>");	
			out.println(" <td width=\"60\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_DETAIL"+j+" value=\"View\" onClick=load_details("+j+")  style=\"width: 40px\" ></td>");
	    //out.println(" 	</tr>'");
			j=j+1;
			more=rs.next();
			}
			
      out.println("<input type=hidden name=hid_deposit_lineno value="+j+">");

			
			out.println(" 	</table>'");


		
		}
		
			  else if(m_chksql.trim().equals("get_receipts_CHQ")){
		
				//String m_val = req.getParameter("data_val").trim();
				String m_CHQ = req.getParameter("data_val").trim();
				
		
								rs= stmt.executeQuery ("SELECT UPPER(REC_NO), "+ //1
								" "+m_schema_name+".AF_CO_GET_DEPOSIT_NO(REC_NO), "+ //2
								" TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+ //3
								" SETTLE_MODE, "+ //4
								" RECON_STATUS,  "+ //5
								" REALISED_DATE, "+ //6
								" NVL(UPPER(CHEQUE_NO),'-'), "+ //7
								" REC_AMOUNT_CURR  "+ //8
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  a, "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS  b , "+m_schema_name+".AF_CO_PRO_DIPOSIT  c "+
								" where b.diposit_no=c.diposit_no "+
								" and a.rec_no=b.receipt_no "+
								//" AND c.DIPOSIT_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY')	 "+
								//" AND c.DIPOSIT_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY')	   "+
								" and a.status='B' "+
								" AND A.SETTLE_MODE IN('CHEQUE')   "+  //'STD_ORD'
								//" AND C.ACC_NO=UPPER('"+m_val+"') "+
								" AND UPPER(CHEQUE_NO)=UPPER('"+m_CHQ+"') "+ 
								" AND A.REALISED_DATE IS NULL  "+
								" AND A.GROUP_REC_NO IS NULL  "+
								" ORDER BY B.diposit_no ");
			boolean more=rs.next();
      if(more){								
			out.println("	  <table  align=\"center\" border=\"0\" width=\"1400\" class=\"table\">");
			out.println("		<TR class=pdn_txtpos2 ><TD width=\"110\" align=\"left\"><B>Transaction Date</B></TD> "); 
			out.println("   <TD width=\"110\" align=\"left\"><B>Deposit No</B></TD>  "); 
			out.println("   <TD width=\"120\" align=\"left\"><B>Receipt No</B></TD>");
			out.println("   <TD width=\"150\"  align=\"right\"><B>Amount</B></TD>");
			out.println("   <TD width=\"100\" align=\"left\"><B>Reference No</B></TD>"); 
			out.println("   <TD width=\"100\" align=\"center\"><B>Returned Status</B></TD>");
			out.println("   <TD width=\"100\" align=\"center\"><B>Realised Status</B></TD>");
			out.println("   <TD width=\"150\" align=\"right\"><B>Return Charge</B></TD>");
			out.println("   <TD width=\"200\" align=\"left\"><B>Comment</B></TD>");
			out.println("   <TD width=\"100\" align=\"left\"><B>Settlement Mode</B></TD>"); 
			out.println("   <TD width=\"100\" align=\"left\"><B>Realised/Returned date</B></TD> ");
			out.println("   <TD width=\"60\" >&nbsp</TD></TR>");
      }
			int j=0;
			while(more){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
			out.println("		<TD width=\"110\" align=\"left\">"+rs.getString(3)+"</TD> "); 
			out.println("   <TD width=\"110\" align=\"left\">"+rs.getString(2)+"<input type=hidden name=hid_deposit_no"+j+" value=\""+rs.getString(2)+"\" ></TD>  "); 
			out.println("   <TD width=\"100\" align=\"center\" STYLE=\"{cursor:hand;}\"  onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u><input type=hidden name=hid_receipt_no"+j+" value=\""+rs.getString(1)+"\" ></TD>");
			out.println("   <TD WIDTH=\"150\" align=\"right\">"+nf.format(rs.getDouble(8))+"</td><input type=hidden name=hid_amount"+j+" value=\""+rs.getDouble(8)+"\" >");	
			out.println("   <TD width=\"100\" align=\"left\">"+rs.getString(7)+"</TD>"); 
			
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_return_status('"+j+"')\" ></td>");		
			out.println(" <TD WIDTH=\"100\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS"+j+" VALUE=\"off\" onclick=\"change_val_realize_status('"+j+"')\"  ></td>");			
			
			out.println("<TD WIDTH=\"150\" align=\"right\"><input class=\"txt_input\" type=\"text\" name=TXT_RET_CHARGE"+j+" maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;text-align:right;}\" disabled ></td>");

      out.println("<TD WIDTH=\"200\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_COMMENT"+j+" maxlength=\"10\" size=\"20\" value=\"\" style=\"{ width:130px;}\" onBlur=makeRequest_comment(this) disabled >");
			out.println("<input class=\"but_input\" type=\"button\" name=BUT_HELP"+j+" value=\"Help\" onClick=\"help_comment("+j+")\" disabled  ></td>");
  					
			out.println("   <TD width=\"100\" align=\"center\">"+rs.getString(4)+"</TD>");
			
			out.println("<TD WIDTH=\"120\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\"  >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\"  >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onBlur=\"date_check("+j+")\" ></TD>");	
			out.println(" <td width=\"60\" align=\"center\" ><input class=\"but_input\" type=\"button\" name=BUT_DETAIL"+j+" value=\"View\" onClick=load_details("+j+")  style=\"width: 40px\" ></td>");
			j=j+1;
			more=rs.next();
			}
			
      out.println("<input type=hidden name=hid_deposit_lineno value="+j+">");
			out.println(" 	</table>'");
		}
		
		
					else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_INFO")){
				
				String m_client_code=req.getParameter("client_code");
				
				
				out.println("<html>");
				out.println("<title>Asset Financing System - Collection - Cheque Details</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">");
				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				
				
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				
				
					String		Sql_company_details=" SELECT "+
					    " COMPANY_NAME "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
				
				
					String		Sql_client_name=" SELECT "+
					      " "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client_code+"') FROM DUAL ";
							
					
					
					String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS "+  //
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					//"  'ACCOUNT RENTAL & VAT RECEIVABLES' DESCRIPTION ,"+
					//"  DECODE(INVOICE_TYPE,'INV_OTHER','Other Invoice','INV_TAX','Tax Invoice','ODI','OD Interest','INV_RESI','Residual Invoice','INV_GENER','RENTAL & VAT') DESCRIPTION, "+ //modified by nuwan de silva on 21-08-07
					//"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),'Other') DESCRIPTION , "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+
					
					"  NULL STATUS "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"  WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					"        ACTIVE_STATUS='Y' "+
					  
					"  UNION "+
					//RECEIPT--------------------------------------------- 
					" SELECT "+
					"     REC_NO REF_NO, "+ //1
					"     EFF_VALDATE DUE_DATE, "+ //2
					"     REC_AMOUNT AMOUNT, "+ //3
					"     'RECEIPT' TYP, "+ //4
					//"     'Cheque - '|| NVL(CHEQUE_NO,'-')  CHEQUE_NO ,"+ //5 //Modified by Chandana on 19/10/2007
					"     DECODE(SETTLE_MODE,'CHEQUE','Cheque - '||CHEQUE_NO,'CASH','Cash','STD_ORD','Standing Order','DIR_DEP','Direct Deposit') CHEQUE_NO, "+ //5

					//"     DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Receipt - Chq Return','Receipt - Cheque'),'CASH','Receipt - Cash') DESCRIPTION, "+ //6
					"     NVL(OTH_COMMENTS,'-') DESCRIPTION , "+ //added by nwuan de silva 17-10-07
          "     STATUS "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					//" AND STATUS <> 'C' "+ //comment by nuwan de silva on 24-03-2008
					"	UNION "+
					
					//ODI---------------------------------------------
					" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07 //modified by nuwan de silva on 17-12-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS "+
					
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE ODI_SETTLED_AMOUNT > 0   "+
					" AND  INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					"          ACTIVE_STATUS='Y' "+
					") "+
			//		"  AND ACTIVE_STATUS='Y' "+
					
					

				
					 //comment by nuwan de silva on 21-12-07
					"	UNION "+
					//CREDIT DEBIT---------------------------------------------
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE CLIENT_CODE='"+m_client_code+"' AND "+
					"    ACTIVE_STATUS='Y' "+
					" ) "+

          "   UNION      "+
					"		SELECT    "+
					"		RETURN_NO REF_NO,   "+
					"		EFF_VALDATE DUE_DATE,  "+
					"		NVL(RETURN_CHARGE,0)  AMOUNT,   "+
					"		'RET_CHARGE' TYP,  "+
					"		'Cheque - '||CHEQUE_NO  CHEQUE_NO ,  "+
					"		'Return Cheque Charges'  DESCRIPTION,    "+
					"		STATUS   "+
					"		FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
					"		WHERE A.REC_NO=B.RECEIPT_NO "+
					"		AND CLIENT_CODE='"+m_client_code+"'   "+
					"		AND STATUS='RET' "+
					
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";


							
				  rs=stmt1.executeQuery(Sql_company_details);
					boolean  more =rs.next();

//out.println(Sql_invoice);
							 											
											if(more)
											{
											m_orient_name=rs.getString(1);
											}
											
				  rs=stmt1.executeQuery(Sql_client_name);
					    more =rs.next();


							 											
											if(more)
											{
											m_name=rs.getString(1);
											}
				
				
				
				  rs=stmt1.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();


        if (!more_inv) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_inv){
					count++;
					
					if(count==1){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='*%'align='center' class=div_input><b>Asset Finance Ledger</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<hr color='black'>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Lessee Name</td>");
					out.println("<td width='50%' class=div_input>"+m_name+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
										

					}
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
					m_debit=rs.getDouble(3);

					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					if(rs.getString(7).equals("RET")){
					m_debit=rs.getDouble(3);
					m_credit=rs.getDouble(3);

          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					
					else if(rs.getString(4).equals("DR/CR")){
					
					if(rs.getString(7).equals("DR")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					
					}
					
					else if(rs.getString(4).equals("ODI")){
					
					m_debit=rs.getDouble(3);
			
					}
					else if(rs.getString(4).equals("OTHER")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					
					}
					
					m_val=m_debit-m_credit;
					
          m_cum_value=m_cum_value+m_val;
          
					
					
					if(rs.getString(4).equals("INVOICE")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					
					if(rs.getString(7).equals("RET") ){
					//m_val=m_debit-m_credit;
					m_val=m_credit;
          m_cum_value=m_cum_value-m_val;

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
				  //m_val=m_debit-m_credit;
					m_val=m_debit;
          m_cum_value=m_cum_value+m_val;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+" - Return </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					//================================================
					
					else if(!rs.getString(7).equals("RET") ){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
          //---------------------------------------------------------------------------------------------------
					if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
					m_debit =0;
					m_credit=0;
					m_debit=rs.getDouble(3);
          				
					m_val=m_debit-m_credit;
          m_cum_value=m_cum_value+m_val;

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
				  //	}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					}
					//---------------------------------------------------------------------------------------------------
					
					
					}

					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
					
												
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					
					if(rs.getString(7).equals("RET") ){
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					
					
					out.println("</tr>");
					
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					else if(rs.getString(4).equals("OTHER")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					
					else if(rs.getString(4).equals("DR/CR")){
					
												
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
				
					
					if(rs.getString(7).equals("DR") ){
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					
															
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					
					
					out.println("</tr>");
					
					}
					
		
					
												
					more_inv = rs.next();
				}
				
				out.println("</table>");
				
				
													String		Sql_Unallocated=" SELECT "+ 
								  "  A.REC_NO, "+
								  "  A.REC_AMOUNT, "+
									"  B.allocated_amount, "+
									"  B.bal_tobe_receive, "+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
								  "  WHERE  A.REC_NO=B.REC_NO /*REC_NO NOT IN */ "+
								  "  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0 AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
									
		
			  rs=stmt.executeQuery(Sql_Unallocated);
				more =rs.next();
				
				if (more) {
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
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");

					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);
					more = rs.next();
				}
				  
					if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
          }
					out.println("</table>");
					
				
				//Added by Chandana on 04/10/2007
				out.println("<br>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Client Special Comments </B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
				
		    rs=stmt1.executeQuery("SELECT TO_CHAR(ENT_DATE,'DD/MM/YYYY'),COMMENTS,ENT_USER "+
				                      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_COMMENT "+
															" WHERE CLIENT_CODE ='"+m_client_code+"'");
					  more4 =rs.next();			
				
				if(more4){
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input valign='top'><B>Date: </B>"+rs.getString(1)+" &nbsp <B>User: </B>"+rs.getString(3)+"</td>");
 				out.println("<td width='2%'>&nbsp</td>");
				out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr></tr>");
				more4 =rs.next();
				}
				out.println("<tr></tr>");
				out.println("</table>");
        }else{
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input >No Special Comments</td>");
				out.println("</tr>");
				out.println("</table>");
				}
				
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input ><B>Comments - Follow Up</B></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<hr color='black'>");
				out.println("<br>");
			
		    rs=stmt1.executeQuery(" (SELECT to_char(ENT_DATE,'DD-MM-YYYY') FROM DUAL ), "+
				         "  NVL(ENT_REMARKS,'-')   , "+
								 "  SELECT "+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER) "+
								 " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
								 " WHERE DIVISION_CODE='AF' AND "+
								 " SUB_DIVISION_CODE='RECOVERY' AND "+
								 " ID_NO IN (SELECT APPLICATION_NO "+
								 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								 " WHERE /*APPLICATION_NO=UPPER('AP20061020-0107')*/ "+
								 " CLIENT_CODE=UPPER('"+m_client_code+"') ) ");
								 
									
									
				boolean  more5 =rs.next();			
				
				if(more5){
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input valign='top'><B>Date: </B>"+rs.getString(1)+" &nbsp <B>User: </B>"+rs.getString(3)+"</td>");
 				out.println("<td width='2%'>&nbsp</td>");
				out.println("<td width='70%' class=div_input>"+rs.getString(2)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr></tr>");
				more5 =rs.next();
				}
				out.println("</table>");
        }else{
				out.println("<table align='center' width='100%' class='table' >");
  			out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' align='center' class=div_input >No Follow Up Comments</td>");
				out.println("</tr>");
				out.println("</table>");
				}			
						
											
				out.println("</body>");
				out.println("</html>");


						
			
		}
		
						else if(m_chksql.trim().equals("get_cheques_details")){
		
				String m_val = req.getParameter("data_val").trim();
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System - Collection - Cheque Details</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				out.println("<script>");
				out.println("	function show_transaction_info(m_client_code){");
				out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_return_realization_display?chksql=SHOW_TRANSACTION_HISTORY_INFO&client_code=\"+m_client_code+\"\";");
				out.println("popupwin=window.open(m_url,'displayWindow4','left=110,top=110,width=650,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("	}");
				out.println("</script>");


				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"\">");
				
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Collection  - Cheque No : "+m_val+" </B></TD></TR>");
					out.println("</TABLE>");


                //out.println(

								rs= stmt.executeQuery (
								"SELECT a.rec_no,  "+  //1
								" a.client_code,  "+ //2
								" "+m_schema_name+".af_co_get_client_name(a.client_code) client_name,"+  //3
								" a.rec_amount, "+ //4
								" a.oth_comments, "+ //5
								" a.status,  "+ //6
								" a.ent_user, "+ //7
								" NVL(TO_CHAR(a.ent_date,'DD-MM-YYYY'),'-'), "+ //8
								" NVL(TO_CHAR( a.eff_valdate,'DD-MM-YYYY'),'-'), "+ //9
								" NVL(TO_CHAR(a.realised_date,'DD-MM-YYYY'),'-'),  "+ //10
								" a.cheque_no, "+ //11
								" NVL(TO_CHAR(a.cheque_date,'DD-MM-YYYY'),'-'),  "+ //12
								" NVL(TO_CHAR(a.bank_date,'DD-MM-YYYY'),'-') "+ //13
								" FROM "+m_schema_name+".af_co_pro_settl_receipt a "+
								" WHERE a.cheque_no='"+m_val+"' ");
								
			 if(rs.next()){
				
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Receipt No</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+rs.getString(1)+"</b></td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Cheque No</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+rs.getString(11)+"</b></td>"); 
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Client Name</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input' style='cursor:hand' onClick=show_transaction_info('"+rs.getString(2)+"')><b><U>"+rs.getString(2)+"</U></b></td>"); 
				out.println("</tr>");

				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Receipt Amount</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+nf.format(rs.getDouble(4))+"</b></td>"); 
				out.println("</tr>");

				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Comments</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+rs.getString(5)+"</b></td>"); 
				out.println("</tr>");

				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Effective Date</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+rs.getString(9)+"</b></td>"); 
				out.println("</tr>");

				out.println("<tr >");
				out.println("<td width=\"20%\" class='tr_input'><b>Realise/Return Date</b></td>"); 
				out.println("<td width=\"5%\" class='tr_input'><b>:</b></td>"); 
				out.println("<td width=\"*%\" class='tr_input'><b>"+rs.getString(10)+"</b></td>"); 
				out.println("</tr>");
        out.println("</table>");
				
				
			}
				
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
								

		}

			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
