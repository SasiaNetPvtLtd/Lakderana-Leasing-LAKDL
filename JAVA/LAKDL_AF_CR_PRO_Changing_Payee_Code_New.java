//CREATED BY DINETH ON 12-06-2009

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Changing_Payee_Code_New extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	CallableStatement callstmt;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs3;
	public String m_chksql,m_no_of_due_days,m_sys_date,m_ac_status;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
	  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
		//	LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
   //   String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			m_chksql         = req.getParameter("chksql");
			if(m_chksql.equals("main_page")){
							out.println("<html>");
							out.println("<head>");
							out.println("<title>Changing Payee Code</title>    ");
							out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
							out.println("</head>");
							out.println("<Script>");
							out.println("var arr_size=0");
							out.println("var arr_check=0");
							out.println("var b_check_help=0;");
							out.println("var chk_no=0;");
							out.println("function befor_end(m_obj) {");
							//out.println("alert('value'+m_obj);");
							out.println("if(m_obj==\"GO_TOP\"){");
							out.println("m_go_top=\"top_b\";");
							out.println("document.Form1.elements[m_go_top].focus();}");
							out.println("else if(m_obj==\"GO_END\"){");
							out.println("m_go_end=\"end_b\";");
							out.println("document.Form1.elements[m_go_end].focus();}");

    					//    out.println("   m_obj.focus();");
        			out.println("}");
				
							out.println("function get_vector_normal(http_response) {");
							out.println(" m_table.innerHTML = ''; ");
							out.println(" m_table.innerHTML = http_response; ");
							out.println(" if(document.Form1.hid_chk_status.value=='BULK'){ ");
							out.println(" if(document.Form1.hid_count_bulk.value>0){");
							out.println("    document.Form1.TXT_NEW_PAYEE.disabled=false;"); 
							out.println("    document.Form1.BUT_HELP_NEW_PAYEE.disabled=false;"); 
							out.println("    document.Form1.BUT_APPLY_ALL.disabled=false;"); 
			
							out.println("  }");
							out.println(" }");
							out.println("} ");
				      //Added by Dineth on 23-06-2009
				      out.println(" function view(){ ");
							out.println(" if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
							out.println("assignState('SINGLE');");
							out.println(" makeRequest();");
							out.println(" }");
							out.println(" else if(document.Form1.TXT_ASSIGN_TYPE.value==\"B\"){");
							out.println("assignState('BULK');");
							out.println(" makeRequest();");
							out.println(" }");
							out.println("} ");
							//End by Dineth on 23-06-2009
							out.println("function makeRequest() {");
							out.println(" 	m_as_at_dd = document.Form1.TXT_AS_AT_DATE_DD.value ");
							out.println(" 	m_as_at_mm = document.Form1.TXT_AS_AT_DATE_MM.value ");
							out.println("		m_as_at_yy = document.Form1.TXT_AS_AT_DATE_YY.value ");
							out.println(" 	m_as_at_date = m_as_at_dd+\"-\"+m_as_at_mm+\"-\"+m_as_at_yy; ");
					
							out.println("     if(document.Form1.hid_chk_status.value=='SINGLE'){ ");
					
							out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Changing_Payee_Code_New?chksql=request_details_single&&fin_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&as_at_date=\"+m_as_at_date+\"\";");
							out.println("load_interface(m_url,'NORM');");
							out.println("}");
							out.println(" else if(document.Form1.hid_chk_status.value=='BULK'){ ");
							out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Changing_Payee_Code_New?chksql=request_details_bulk&payee=\"+document.Form1.TXT_PAYEE.value+\"&as_at_date=\"+m_as_at_date+\"\";");
							out.println("load_interface(m_url,'NORM');");
							out.println("}");
				
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
		
		  				//out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
							out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
							out.println("	if(oBj.valout[1] ==\" \"){"); 
							out.println("	clear_data();");
							out.println("	}else");
			
				
							out.println("	"); 
							out.println("	if(oBj.valout[1] !=\" \"){"); 
							out.println("	if(oBj.valout[1] !=\"Close\"){"); 
							out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
							out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
							out.println("		if(IfCount==\"1\"){"); 
							out.println("		help_value_assign_payee_n(document.Form1.hid_row_no.value);"); 
	  					out.println("		}"); 
			
							out.println("		if(IfCount==\"7\"){"); 
							out.println("		help_value_assign_payee_no(document.Form1.hid_row_no.value);"); 
	  					out.println("		}"); 
			
			
							out.println("		if(IfCount==\"2\"){"); 
							out.println("		finance_assign(oBj);"); 
	  					out.println("		}"); 
			
							out.println("		if(IfCount==\"3\"){"); 
							out.println("		assign_help_button_payee();"); 
	  					out.println("		}"); 
			
							out.println("		if(IfCount==\"5\"){"); 
							out.println("		assign_help_button_payee_new();"); 
	  					out.println("		}"); 
			
			

							out.println("		if(IfCount==\"6\"){"); 
							out.println("		help_value_assign_6(oBj);"); 
	  					out.println("		}"); 
							out.println("		if(IfCount==\"4\"){"); 
							out.println("		help_value_assign_4(oBj);"); 
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
				
				
							//end Help box
				
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
							out.println("    b_check_help=5;");
							//out.println("    m_sql = \"m_help_TXT_INVOICE_NO_sql\";"); 
							out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
							out.println("    HelpBox('1','10','0',Crit,'FinanceSql','2');");
					 
							out.println("}"); 
					
					
							out.println("function finance_assign() {"); 
							out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
							
							out.println(" }");
						
						
							out.println("function help_button_payee() {");
							out.println("    Crit = document.Form1.TXT_PAYEE.value+\"@Y@\";"); 
							out.println("    b_check_help=3;");
							//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','3');"); 
							out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_PAYEE_CODE_sql','3');"); 
							out.println("}"); 
							out.println(""); 
	
							out.println("function assign_help_button_payee() {"); 
							out.println("    document.Form1.TXT_PAYEE.value=oBj.valout[2];");
							out.println("    document.Form1.hid_TXT_PAYMENT_CATEGORY.value=oBj.valout[3];");
							out.println("}"); 
					
					
							out.println("function assignState(val){");
							out.println("document.Form1.hid_chk_status.value=val");
							out.println("}");
	
							//help button assign payee_n
							out.println("function help_button_payee_n(row_No,chk_no) {"); 
							out.println("document.Form1.hid_row_no.value=row_No;");
							out.println(" if(chk_no==2){");
							out.println("b_check_help=2;");
							out.println("m_payee_n=\"TXT_PAYEE_N\"+row_No");
							out.println("m_payee_cat_n=\"hid_TXT_PAYMENT_CAT_B\"+row_No");
							out.println("    Crit = document.Form1.elements[m_payee_n].value+\"@\"+document.Form1.elements[m_payee_cat_n].value+\"@Y@\";"); 
							out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_PAYEE_CODE_sql1','1');"); 
							out.println(" }else if(chk_no==1){");
							out.println("b_check_help=1;");
							out.println("m_payee_no=\"TXT_PAYEE_NO\"+row_No");
							out.println("m_payee_cat_no=\"hid_TXT_PAYMENT_CAT_I\"+row_No");
							out.println("    Crit = document.Form1.elements[m_payee_no].value+\"@\"+document.Form1.elements[m_payee_cat_no].value+\"@Y@\";");
							out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_PAYEE_CODE_sql1','7');"); 
							out.println(" }");
							//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','1');"); 
					
							out.println("}"); 
							out.println(""); 

							out.println("function help_value_assign_payee_n(row_No) {");
							out.println("m_payee_n_assign=\"TXT_PAYEE_N\"+row_No");
							out.println("document.Form1.elements[m_payee_n_assign].value=oBj.valout[2];"); 
							out.println("}"); 
			
							out.println("function help_value_assign_payee_no(row_No) {");
							out.println("m_payee_n_assign=\"TXT_PAYEE_NO\"+row_No");
							out.println("document.Form1.elements[m_payee_n_assign].value=oBj.valout[2];"); 
							out.println("}"); 

					
							out.println("function clear_data() {");
							out.println("if(b_check_help==3){");
							out.println("document.Form1.TXT_PAYEE.value=\"\";");
							out.println(" m_table.innerHTML = ''; ");
							out.println("    document.Form1.TXT_NEW_PAYEE.disabled=true;"); 
							out.println("    document.Form1.BUT_HELP_NEW_PAYEE.disabled=true;"); 
							out.println("    document.Form1.BUT_APPLY_ALL.disabled=true;"); 
			
							out.println("document.Form1.TXT_PAYEE.focus();");
							out.println("}");
							out.println("if(b_check_help==4){");
							out.println("document.Form1.TXT_NEW_PAYEE.value=\"\";");
							out.println("document.Form1.TXT_NEW_PAYEE.focus();");
							out.println(" } ");
							out.println("if(b_check_help==5){");
							out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
							out.println(" m_table.innerHTML = ''; ");
							out.println("document.Form1.TXT_FINANCE_NO.focus();");
							out.println(" } ");
		  				out.println("if(b_check_help==2){");
							out.println("m_user_clear=\"TXT_PAYEE_N\"+document.Form1.hid_row_no.value;");
							out.println("document.Form1.elements[m_user_clear].value=\"\";");
							out.println("document.Form1.elements[m_user_clear].focus();");
							out.println("}");
			
							out.println("if(b_check_help==1){");
							out.println("m_user_clear=\"TXT_PAYEE_NO\"+document.Form1.hid_row_no.value;");
							out.println("document.Form1.elements[m_user_clear].value=\"\";");
							out.println("document.Form1.elements[m_user_clear].focus();");
      				out.println("}");
							out.println("}");
			
			
			//function to apply comments to all the fields
								out.println("function but_app_all(){");					
								out.println("for(i=0;i<document.Form1.hid_count_bulk.value;i++){"); 
								out.println("txt_remark=\"TXT_REMARK\"+i");
								out.println("document.Form1.elements[txt_remark].value=document.Form1.TXT_REMARK_NEW.value;"); 
								out.println("}"); 
			
								out.println("}");
			
			
			//end function applyall
					
					out.println("function change_val_req(row_no){")	;
					out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");
					out.println("if(document.Form1.elements[m_chk_required].checked==true){");
					out.println("document.Form1.elements[m_chk_required].value='on'");
					out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
					out.println("document.Form1.elements[m_chk_required].value='off'");
					out.println("}");	
					out.println("}");	
						
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Changing Payee Code - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Changing Payee Code - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 

					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();}");
					out.println("else if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}"); 
					out.println("else if(m_val!=\"EDIT\"){"); 
		
   
					out.println("}"); 
					out.println("else{}");
			 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_save_status.value=\"Save\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
					out.println("}else if(m_val==\"DACT\"){");  
					out.println("document.Form1.hid_status.value=\"Deactivate\";");  
					out.println("document.Form1.hid_save_status.value=\"Deactive\";"); 
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
			
			    out.println("function  change_type(){");
								out.println("m_table.innerHTML=\"\" ");
								out.println("m_table_main.innerHTML=\"\" ");
			  				out.println("m_table3.innerHTML=\"\" ");
								out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
			  				out.println("m_table_top.innerHTML=\"\" ");
								out.println("m_table_end.innerHTML=\"\" ");
								out.println("m_write='<tr >'+"); 
								out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_FINANCE  class=div_input>Finance No</DIV></td>'+"); 
								out.println("        '<td width=\"20%\" ><input class=txt_input type=text name=TXT_FINANCE_NO maxlength=15 size=15>&nbsp;&nbsp;'+"); 
								out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_FINANCE value=\" ... \" onClick=\"help_finance()\"></td>'+"); 
								out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
								out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
								out.println("        '</tr>';"); 
					
								out.println("m_table_main.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
								out.println("m_write+'</table>';");
								out.println("arr_check=0;");
								out.println("}");
								out.println("else if(document.Form1.TXT_ASSIGN_TYPE.value==\"B\"){");
			  				out.println("m_table_top_2.innerHTML=\"\";");
								out.println("m_table_end_2.innerHTML=\"\";");
								out.println("m_write='<tr >'+"); 
								out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_PAYEE  class=div_input>Payee</DIV></td>'+"); 
								out.println("        '<td width=\"20%\" ><input class=txt_input type=text name=TXT_PAYEE maxlength=15 size=15  >&nbsp;&nbsp;'+"); 
								out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_PAYEE value=\" Help \" onClick=\"help_button_payee()\"></td>'+"); 
								out.println("        '<td width=\"10%\"><input class=but_input type=button name=BUT_VIEW value=\"View\" onClick=\"view()\"></td>'+"); 
								out.println("        '<td width=\"*%\">&nbsp;</td>'+"); 
								out.println("        '</tr>';"); 
			
								out.println("m_write1='<tr >'+"); 
								out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_NEW_PAYEE  class=div_input>New Payee</DIV></td>'+"); 
								out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_NEW_PAYEE maxlength=15 size=15 disabled>&nbsp;&nbsp;'+"); 
								out.println("        '<input class=but_input type=button text-align=center name=BUT_HELP_NEW_PAYEE value=\" Help \" onClick=\"help_button_payee_new()\" disabled></td>'+"); 
								out.println("      '<td width=\"*%\">&nbsp;</td>'+"); 
								out.println("        '</tr>';"); 
			
								out.println("m_write2='<tr >'+"); 		
								out.println("        '<td width=\"20%\" ><DIV id=DIV_TXT_REMARK_NEW class=div_input>Remark</DIV></td>'+"); 
								//out.println("        '<td width=\"30%\" ><input class=txt_input type=text name=TXT_REMARK_NEW maxlength=500 size=15>&nbsp;&nbsp;'+"); 
								out.println("        '<td width=\"*%\"><textarea class=txt_input style=\"width:300px; height:50px;\" name=TXT_REMARK_NEW maxlength=500 size=500></textarea>&nbsp;&nbsp;'+");
								out.println("        '<input class=but_input type=button text-align=center name=BUT_APPLY_ALL value=\" Apply All\" onClick=\"but_app_all()\" disabled></td>'+"); 
								out.println("        '<td width=\"*%\">&nbsp;</td>'+");
								out.println("       '<br>'+");
								out.println("        '</tr>';"); 
			
			
								//out.println("m_table_main.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
								//out.println("m_write+m_write1+'</table>';");
								out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
								out.println("m_write+'</table><br>';");
			
								out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
								out.println("m_write1+'</table>';");
			
								out.println("m_table_main.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >'+");
								out.println("m_write2+'</table>';");
								out.println("arr_check=0;");
								out.println("}");	
			
			
								out.println("}");
				
			
								out.println("function load_sys_date(){ ");
			 					rs3 = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
				                        "FROM  DUAL ");
								//out.println(" m_sysdate = ''   ");													
								while(rs3.next()){
								out.println(" m_sysdate = '"+rs3.getString(1)+"'");			
								}
								out.println(" document.Form1.TXT_AS_AT_DATE_DD.value = m_sysdate.substring(0,2)");
								out.println(" document.Form1.TXT_AS_AT_DATE_MM.value = m_sysdate.substring(3,5)");
								out.println(" document.Form1.TXT_AS_AT_DATE_YY.value = m_sysdate.substring(6,10)");
								out.println("}");
			
								out.println("function load_calendar(num) {");
      					out.println(" document.Form1.hid_cal_date.value=num;"); 
								out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
								out.println("}");
							
			
								out.println("function load_c_date(val) {");
								out.println(" if(document.Form1.hid_cal_date.value=='1'){"); 	
								out.println("  v_dd = val.substr(0,val.indexOf('-'))");
								out.println("   if(v_dd.length <2) ");
								out.println("   v_dd = 0+v_dd ");
								out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
								out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
								out.println("   if(v_mm.length <2) ");
								out.println("   v_mm = 0+v_mm ");
								out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
								out.println("     document.Form1.TXT_AS_AT_DATE_DD.value=v_dd;");
								out.println("     document.Form1.TXT_AS_AT_DATE_MM.value=v_mm;");
								out.println("     document.Form1.TXT_AS_AT_DATE_YY.value=v_yy;");
								//out.println("  checkMonthLength(document.Form1.TXT_DUE_DATE_DD,document.Form1.TXT_DUE_DATE_MM,document.Form1.TXT_DUE_DATE_YY)");
								out.println("  }");		
								out.println("}");	
					
					
								out.println("function check_date_value(objDD,objMM,objYY){");
								out.println("if(objDD.value!='' && objMM.value!='' && objYY.value!='')");
								out.println("checkMonthLength(objDD,objMM,objYY)");
								out.println("}");				
			
			
			
								out.println("function validate_data(){"); 
								out.println("return true;"); 
								out.println("}");
					
					
								out.println("function save_window(){	"); 
								out.println("before_submit();"); 
								out.println("}"); 
								out.println(""); 
								
								out.println("function validate_data1(){");
								out.println("var count=0;");
								out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"B\"){");
								out.println("if(document.Form1.hid_count_bulk.value >0){");
								out.println("for(j=0;j<document.Form1.hid_count_bulk.value;j++){");
								out.println(" if(document.Form1.elements[\"CHK_REQUIRED\"+j].checked==true && document.Form1.elements[\"TXT_PAYEE_N\"+j].value!=\"\"){");
								out.println(" count++;");
								out.println(" }");
								out.println(" }");
								out.println("if(count>0){");
								out.println("return true;");
								out.println("}else{");
								out.println("alert('Please fill the fields and check the checkboxes')");
								out.println("}");
								out.println("}");
								out.println("}else if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
								out.println("if(document.Form1.hid_count_single.value>0){");
								out.println(" arr_size=document.Form1.hid_count_single.value;");
								out.println("for(j=0;j<document.Form1.hid_count_single.value;j++){");
								out.println(" if(document.Form1.elements[\"CHK_REQUIRED\"+j].checked==true && document.Form1.elements[\"TXT_PAYEE_NO\"+j].value!=\"\"){");
								out.println(" count++;");
								out.println(" }");
								out.println(" }");
								out.println("if(count>0){");
								out.println("return true;");
								out.println("}else{");
								out.println("alert('Please fill the fields and check the checkboxes')");
								out.println("}");
								out.println("}");
								out.println("}");
								out.println("}");
								
								out.println("function before_submit(){ "); 
								out.println("   if(validate_data1()){");
								out.println("		if(validate_data()){"); 
								out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
								out.println("if(document.Form1.TXT_ASSIGN_TYPE.value==\"B\"){");
								out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_count_bulk.value;");//Added By Nuwan De Silva
								out.println("}else if(document.Form1.TXT_ASSIGN_TYPE.value==\"I\"){");
								out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_count_single.value;");//Added By Nuwan De Silva
								out.println("} ");
								out.println("		if(validate_data()){"); 
								out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
								out.println("document.Form1.elements[i].disabled=false;");
								out.println("}");
								out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Payee_Code';");  
								out.println("		document.Form1.submit();	"); 
								out.println("		}"); 
								out.println("		}"); 
								out.println("		}"); 
								out.println("else{");
								out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
								out.println("} "); 
								out.println("}");
								out.println("} "); 

								//end saving process
								//clear window
								out.println("function clear_window(){	"); 
								out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
								out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Changing_Payee_Code_New?chksql=main_page';"); 
								out.println("		}"); 
								out.println("}"); 
			
					
					
								//end clear window
								//new window
								out.println("function new_window(){	"); 
								out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Changing_Payee_Code_New?chksql=main_page';"); 
								out.println("}"); 
								out.println(""); 
								out.println(""); 
					
					
								//end new window
								//help button new_payee
					
					
					
								out.println("function help_button_payee_new() {");
								out.println("    Crit = document.Form1.TXT_NEW_PAYEE.value+\"@\"+document.Form1.hid_TXT_PAYMENT_CATEGORY.value+\"@Y@\";");
								out.println("    b_check_help=4; ");
								//out.println("    Crit =document.Form1.TXT_NEW_PAYEE.value+\"@\"+document.Form1.TXT_COLL_OFFICER.value+\"@Y@\";"); 
								//out.println("    Crit = document.Form1.TXT_NEW_COLL_OFFICER.value+\"@Y@\";"); 
								//out.println("    HelpBox('1','10','0',Crit,'m_help_collection_officer_new','5');"); 
								out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_PAYEE_CODE_sql1','5');"); 
					
								out.println("}"); 
								out.println(""); 
			
					
								out.println("function assign_help_button_payee_new() {"); 
								out.println("    document.Form1.TXT_NEW_PAYEE.value=oBj.valout[2];"); 
								out.println("    assign_payee_bulk();"); 
								out.println("}"); 
								//bulk
								out.println("function assign_payee_bulk(){"); 
								out.println("for(i=0;i<document.Form1.hid_count_bulk.value;i++){"); 
								out.println("m_new_payee=\"TXT_PAYEE_N\"+i");
								out.println("m_chk_req=\"CHK_REQUIRED\"+i");
								out.println("document.Form1.elements[m_new_payee].value=document.Form1.TXT_NEW_PAYEE.value;"); 
								out.println("document.Form1.elements[m_chk_req].checked=true;"); 
								out.println("document.Form1.elements[m_chk_req].value='on';"); 
								out.println("}"); 
								out.println("}"); 

					
								//help button new payee
								out.println("function val_user(row_No){");
								out.println("assignState('M2')");
								out.println("document.Form1.hid_row_no.value=row_No;");
			
								out.println("}");
					
								out.println("function val_user1(row_No){");
								out.println("assignState('M5')");
								out.println("document.Form1.hid_row_no.value=row_No;");
			
								out.println("}");
					
			
					out.println("</Script>");
								out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"change_type(),load_sys_date()\">"); //load_lock()
								out.println("<FORM NAME='Form1' method='post'>"); 
				  				
								out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
								out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
								out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
								out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
								out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
								out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			    			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
								out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_CHANGING_PAYEE_CODE\">"); 
								out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
								out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
								out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_PAYMENT_CATEGORY' VALUE=\"\">");
								
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
								out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Changing Payee Code - New</td>"); 
								out.println("</tr>"); 
								out.println("<tr>"); 
								out.println("<td  height='10px' class='pdn_txtpos'>"); 
								out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
								out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
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
								out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
								out.println("<tr class='tr_input'>");  
								out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
								out.println("</tr>");  
								out.println("</table>"); 
								out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0>"); 
								out.println("<tr >"); 
								out.println("<td width='20%' ><DIV id='DIV_TXT_AS_AT_DATE'  class=div_input>Date As At</DIV></td>"); 
								out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD maxlength=\"2\" size=\"2\" onBlur=check_date_value(document.Form1.TXT_AS_AT_DATE_DD,document.Form1.TXT_AS_AT_DATE_MM,document.Form1.TXT_AS_AT_DATE_YY) >");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM  maxlength=\"2\" size=\"2\"  onBlur=check_date_value(document.Form1.TXT_AS_AT_DATE_DD,document.Form1.TXT_AS_AT_DATE_MM,document.Form1.TXT_AS_AT_DATE_YY)>");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY maxlength=\"4\" size=\"4\"  onBlur=check_date_value(document.Form1.TXT_AS_AT_DATE_DD,document.Form1.TXT_AS_AT_DATE_MM,document.Form1.TXT_AS_AT_DATE_YY)> <a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
								out.println("</td> ");
								//out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_AS_AT_DATE' maxlength='7' size='7'></td>"); 
								out.println("<td width='*%'>&nbsp;</td>");
								out.println("</tr>"); 
								out.println("</table>");
								out.println("<br>");
								out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0>"); 

								out.println("<tr >"); 
								out.println("<td width='20%' >Assign Type</td>"); 
								out.println("<td width='30%' ><select class='txt_input' type='text' name='TXT_ASSIGN_TYPE' onChange=\"change_type()\" maxlength='1' size='1'>");  
								out.println("<option value='I' selected>Individual</option>");			
								out.println("<option value='B' >Bulk</option>");		
								out.println("</select>");
								out.println("</td>");
								out.println("<td width='*%'>&nbsp;</td>"); 
								out.println("</tr>"); 
								out.println("</table>");  
					
								out.println("<br>"); 
								out.println("<table align='center' width='100%' class='table' border=0 cellspacing=0 cellpadding=0 >"); 
								out.println("<tr>");  
			   			 	out.println("<td ><DIV ID='m_table_main'></DIV></td>"); //width=\"100%\"
		      			out.println("</tr>"); 
					
								out.println("</table>");  
								//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         				out.println("<table align='center' width='100%' class='table'>"); 
			   			   
									
								out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table_top'></DIV></td>");
		     				out.println("</tr>"); 

				 				out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     				out.println("</tr>"); 
					
								out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table_end'></DIV></td>");
		     				out.println("</tr>"); 

						  					
				 				out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table2'></DIV></td>");
		     				out.println("</tr>"); 
					
				 				out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table_top_2'></DIV></td>");
		     				out.println("</tr>"); 
					
				 				out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table3'></DIV></td>");
		     				out.println("</tr>"); 
					
								out.println("<tr>");  
			   				out.println("<td width=\"100%\"><DIV ID='m_table_end_2'></DIV></td>");
		     				out.println("</tr>"); 	
	
			
			   				out.println("</table>");
					
					
								out.println("<tr>"); 
								out.println("<td  height='10px' class='pdn_txtpos'>"); 
								out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
								out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		
		
								out.println("<td width='6%'></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
								out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
								out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
								out.println("</table>");  
					
								out.println("</td></tr>");  
					
				
		
								out.println("</form>");
								out.println("</body>");
				
								out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
								out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
								out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
								out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 

								out.println("</html>");
					
		
					
			

			
			
			
			
			
			}
			if(m_chksql.equals("request_details_bulk")){
			String m_payee=req.getParameter("payee");
			String m_as_at_date=req.getParameter("as_at_date");
			int lineno=0;
			int j=0;
			int i=0;
			/*rs = stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+
					" B.SUB_TYPE_CODE, "+
					" A.TOTAL_AMOUNT, "+
					" B.PAYEE_NAME "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B"+
          " WHERE B.PAYEE_CODE LIKE ('"+m_payee+"%') "+
					" AND B.SUB_TYPE_CODE=A.INVOICE_TYPE "+
					" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')"+
					" ORDER BY A.CLIENT_CODE ASC ");*/
					rs=stmt.executeQuery(" SELECT "+ 
                               " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.PAYER),' ') ,"+//"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))
                               " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
                               " NVL(A.BAL_TO_BE_PAID,0), "+ 
                               " NVL(B.PAYEE_NAME,' '), "+
															 " B.PAYEE_CODE, "+
															 " A.REF_NO "+
															 " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
															 " WHERE "+ 
	                             " A.RECEIVER=B.PAYEE_CODE "+
	                             " AND B.PAYEE_CODE LIKE ('"+m_payee+"%') "+
	                             " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.SUSPENSE_ENTRY_TYPE NOT IN ('V','L') "+	
															 " AND A.BAL_TO_BE_PAID>0 "+
															 " AND A.INT_BAL_SETTLE_AMOUNT=0 ");
	  
					
				boolean more=rs.next();
				if(!more){
								out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
								out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
								out.println("<input type=\"hidden\" name=\"hid_count_bulk\" value=\"0\">");
          			out.println("</TR></table>");	
				}
				else{
								out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
								out.println("<td  width=\"15%\" align=\"left\"  ><B>Client Name</td>");
          			out.println("<td  width=\"15%\" align=\"left\"  ><B>Payment Category</td>");
				  			out.println("<td  width=\"15%\" align=\"right\"  ><B>Amount</td>");
          			out.println("<td  width=\"15%\" align=\"left\"   ><B>Current Payee</td>");
				  			out.println("<td  width=\"15%\" align=\"center\" ><B>New Payee</td>");
					
								out.println("<td  width=\"15%\" align=\"center\" ><B>Remark</td>");
					
					
								out.println("<td  width=\"10%\" align=\"left\"  ><B>Approve</td>");
				while(more){
				
					
						    if(j>0 && j%2==1){
								out.println("<TR class=\"tr_input1\">"); 
									}
									else{
								out.println("<TR class=\"tr_input\">"); 
									}
			
								out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(1)+"</TD>");
								out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(2)+"</TD>");
								out.println("<TD WIDTH=\"15%\" align=\"right\">"+nf.format(rs.getDouble(3))+"</TD>");
								out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(4)+"</TD>");
								out.println("<td width=\"15%\"   align=\"left\" ><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_N"+lineno+"  maxlength=\"15\" size=\"15\" onblur=\"val_user("+lineno+")\" style={width:70px;}>&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_PAYEE_N"+lineno+" value=\"Help\" onClick=\"help_button_payee_n("+lineno+",2)\"></TD>") ;			
								out.println("<td width=\"15%\"   align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK"+lineno+" maxlength=\"500\" size=\"15\"></TD>") ;
								out.println("<TD WIDTH=\"10%\"    align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+lineno+" VALUE=\"off\" onclick=\"change_val_req("+lineno+")\"></td>");			
								out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PAY_CODE_B"+lineno+"	VALUE="+rs.getString(5)+">");
								out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_REF_NO_B"+lineno+"	VALUE="+rs.getString(6)+">");
								out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PAYMENT_CAT_B"+lineno+" VALUE="+rs.getString(2)+">");
								out.println("</TR>");	
			
			
								
			    j++;
					lineno++;
					more=rs.next();
				}
				out.println("<input type=\"hidden\" name=\"hid_count_bulk\" value="+j+">");
				out.println("</table>");
				}
			
			
			
			
			}
			if(m_chksql.equals("request_details_single")){
			String m_finance_no=req.getParameter("fin_no");
					String m_as_at_date=req.getParameter("as_at_date");
					int j=0;
					int i=0;
					int lineno=0;
					
					
					/*rs = stmt.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+
					" B.SUB_TYPE_CODE, "+
					" A.TOTAL_AMOUNT, "+
					" B.PAYEE_NAME "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B"+
          " WHERE A.FINANCE_NO LIKE ('"+m_finance_no+"%') "+
					" AND B.SUB_TYPE_CODE=A.INVOICE_TYPE "+
					" AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY')"+
					" ORDER BY A.CLIENT_CODE ASC ");*/
					
					/*rs=stmt.executeQuery(" SELECT "+ 
                               " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.PAYER),' ') ,"+//"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))
                               " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
                               " NVL(A.BAL_TO_BE_PAID,0), "+ 
                               " NVL(B.PAYEE_NAME,' '), "+
															 " B.PAYEE_CODE, "+
															 " A.REF_NO "+
															 " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
															 " WHERE "+ 
	                             " A.RECEIVER=B.PAYEE_CODE "+
	                             " AND "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)) LIKE ('"+m_finance_no+"%') "+
	                             " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.SUSPENSE_ENTRY_TYPE NOT IN('V','L')");
	      */
				rs=stmt.executeQuery(" SELECT "+ 
                               " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.PAYER),' ') ,"+//"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))
                               " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
                               " NVL(A.BAL_TO_BE_PAID,0), "+ 
                               " NVL(B.PAYEE_NAME,' '), "+
															 " B.PAYEE_CODE, "+
															 " A.REF_NO, "+
															 " B.SUB_TYPE_CODE "+
															 " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
															 " WHERE "+ 
	                             " A.RECEIVER=B.PAYEE_CODE "+
															 " AND REF_NO IN (SELECT APPLICATION_NO "+
                               " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                               " WHERE UPPER(FINANCE_NO) LIKE UPPER('"+m_finance_no+"%')) "+
	                             " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.SUSPENSE_ENTRY_TYPE NOT IN('V','L')"+
															 " AND SUBSTR(REF_NO,1,2)='AP' "+
															 " AND A.BAL_TO_BE_PAID>0 "+
															 " AND A.INT_BAL_SETTLE_AMOUNT=0 "+
															 " UNION ALL "+
															 " SELECT "+ 
                               " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.PAYER),' ') ,"+//"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))
                               " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
                               " NVL(A.BAL_TO_BE_PAID,0), "+ 
                               " NVL(B.PAYEE_NAME,' '), "+
															 " B.PAYEE_CODE, "+
															 " A.REF_NO, "+
															 " B.SUB_TYPE_CODE "+
															 " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
															 " WHERE "+ 
	                             " A.RECEIVER=B.PAYEE_CODE "+
															 " AND REF_NO IN (SELECT INVOICE_NO "+
                               " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
                               " WHERE UPPER(FINANCE_NO) LIKE UPPER('"+m_finance_no+"%')) "+
	                             " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.SUSPENSE_ENTRY_TYPE NOT IN('V','L')"+
															 " AND SUBSTR(REF_NO,1,2)='IN' "+
															 " AND A.BAL_TO_BE_PAID>0 "+
															 " AND A.INT_BAL_SETTLE_AMOUNT=0 "+
															 " UNION ALL "+
															 " SELECT "+ 
                               " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.PAYER),' ') ,"+//"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO))
                               " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(B.SUB_TYPE_CODE),' '), "+
                               " NVL(A.BAL_TO_BE_PAID,0), "+ 
                               " NVL(B.PAYEE_NAME,' '), "+
															 " B.PAYEE_CODE, "+
															 " A.REF_NO, "+
															 " B.SUB_TYPE_CODE "+
															 " FROM  "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
															 " WHERE "+ 
	                             " A.RECEIVER=B.PAYEE_CODE "+
															 " AND REF_NO IN (SELECT B.INVOICE_NO "+
                               " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
                               " WHERE A.application_no=B.application_no "+
                               " AND UPPER(FINANCE_NO) LIKE UPPER('"+m_finance_no+"%')) "+
	                             " AND TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_as_at_date+"','DD-MM-YYYY') "+
															 " AND A.SUSPENSE_ENTRY_TYPE NOT IN('V','L')"+
															 " AND SUBSTR(REF_NO,1,2)='PI' "+
															 " AND A.BAL_TO_BE_PAID>0 "+
															 " AND A.INT_BAL_SETTLE_AMOUNT=0 ");
	      
	                                   
				
				
				boolean more=rs.next();
				if(!more){
								out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
								out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
          			out.println("</TR></table>");	
				}
				else{
					
								out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><TR class=\"pdn_txtpos2\" align=\"center\">");
								out.println("<td  width=\"15%\" align=\"left\"  ><B>Client Name</td>");
          			out.println("<td  width=\"15%\" align=\"left\"  ><B>Payment Category</td>");
				  			out.println("<td  width=\"15%\" align=\"right\"  ><B>Amount</td>");
          			out.println("<td  width=\"15%\" align=\"left\"   ><B>Current Payee</td>");
				  			out.println("<td  width=\"15%\" align=\"left\"   ><B>New Payee</td>");
								out.println("<td  width=\"15%\" align=\"center\" ><B>Remark</td>");
								out.println("<td  width=\"10%\" align=\"left\" ><B>Approve</td>");
			    				
					
				 while(more){
				  if(j>0 && j%2==1){
			        out.println("<TR class=\"tr_input1\">");
					}else{
							out.println("<TR class=\"tr_input\">");
							}
					
					out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(1)+"</TD>");
					out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(2)+"</TD>");
					out.println("<TD WIDTH=\"15%\" align=\"right\">"+nf.format(rs.getDouble(3))+"</TD>");
					out.println("<TD WIDTH=\"15%\" align=\"left\">"+rs.getString(4)+"</TD>");
					out.println("<td width=\"15%\" align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_PAYEE_NO"+lineno+" maxlength=\"15\" size=\"15\" onblur=\"val_user1("+lineno+")\"style={width:70px;}>&nbsp;<input class=\"but_input\" type=\"button\" name=BUT_TXT_PAYEE_NO"+lineno+" value=\"Help\" onClick=\"help_button_payee_n("+lineno+",1)\"></TD>") ;
					out.println("<td width=\"15%\"   align=\"center\" ><input class=\"txt_input\" type=\"text\" name=TXT_REMARK"+lineno+" style={width:120px;} maxlength=\"500\" size=\"15\"></TD>") ;		
          out.println("<TD WIDTH=\"10%\"    align=\"center\" STYLE={width:10px}><INPUT TYPE=\"checkbox\" NAME=CHK_REQUIRED"+lineno+" VALUE=\"off\" onclick=\"change_val_req("+lineno+")\"></td>");			
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PAY_CODE_I"+lineno+"	VALUE="+rs.getString(5)+">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_REF_NO_I"+lineno+"	VALUE="+rs.getString(6)+">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_PAYMENT_CAT_I"+lineno+" VALUE="+rs.getString(7)+">");
					out.println("</TR>");
					
					j++;
				  lineno++;
			    more=rs.next();
	         }
					out.println("<input type=\"hidden\" name=\"hid_count_single\" value="+j+">");
					out.println("</table>");
				}
			
			
			}
			
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
			
