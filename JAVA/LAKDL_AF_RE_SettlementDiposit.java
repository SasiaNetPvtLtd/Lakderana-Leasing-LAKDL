//Option Id is 1.65 
//This File was created by Nuwan De Silva



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_RE_SettlementDiposit extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public void service(HttpServletRequest req, HttpServletResponse res) // added by udara 30-05-2017
	{
		
		Connection conn =null;
		Statement stmt=null,stmt1=null;
		java.text.NumberFormat nf=null,nf1=null;
		ResultSet rs=null,rs1=null;
		String m_chksql=null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			
			//		String m_html_client_url = m_sn_methods.html_client_url;
			String fschema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_schema_name = m_sn_methods.schema_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			String m_username 						= m_sn_methods.username;
			//		String header_name    = m_sn_methods.header_name;
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			//nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if(m_chksql.trim().equals("main_page")){
				
				
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
					"TO_CHAR(SYSDATE,'MM'), "+
					"TO_CHAR(SYSDATE,'YYYY') "+
					"FROM DUAL ");
				
				if(rs.next()){
					m_date_dd=rs.getString(1);
					m_date_mm=rs.getString(2);
					m_date_yy=rs.getString(3);
				}
				
				
				String m_Followu_no   = "";//req.getParameter("Followu_no");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//Global Varailbe To Hold The Values
				out.println("var lineno=0;");
				out.println("var arr_size=0;");
				out.println("var sum=0;");
				out.println("var count=0;");
				out.println("var b_flag=0;");
				
				
				out.println("var new_data_vec=new Array();");
				
				out.println("function get_vector_normal(http_response){ ");
				out.println(" m_table.innerHTML = ''; ");
				out.println(" m_table.innerHTML = http_response; ");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M1'){");
				//out.println("				alert('Record already exists');");
				//out.println("				new_window();");
				out.println("				new_data_vec=data_vec;");
				
				out.println("				display_receipts(data_vec);");
				out.println("			}");
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.ACCOUNT_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M3' ){");
				out.println("     account_help();");
				out.println("			}");
				
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.ACCOUNT_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M3' ){");
				out.println("     Assign_account_values(data_vec);");
				out.println("			}");
				
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.DEPOSIT_CODE.value!=\"\"){");
				out.println("    display_data(data_vec);"); 
				out.println("			}");
				
				
				out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M2' && document.Form1.DEPOSIT_CODE.value!=\"\"){");
				out.println("     help_update();");
				out.println("			}");
				
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M4' && document.Form1.DEPOSIT_CODE.value!=\"\"){");
				out.println("				new_data_vec=data_vec;");
				
				out.println("     display_receipts(data_vec);");
				out.println("			}");
				
				
				
				
				
				
				out.println("}");
				
				out.println("function makeRequest(obj) {");
				
				
				out.println("if(document.Form1.hid_chk_status.value=='M2')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_val_deposit_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_city&data_val=\"+obj.value;");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M3')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_val_account_code&data_val=\"+obj.value+\"&ac_status=Y\";");	
				
				out.println("else if(document.Form1.hid_chk_status.value=='M4')");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_deposit_receipts&data_val=\"+obj.value+\"&ac_status=Y\";");	
				out.println("load_interface(m_url,'XML');");
				//		out.println("window.open(m_url)");
				
				out.println("}"); 
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				out.println("function getDateValues(dval){");
				out.println("document.Form1.VAL_DAY.value=dval.substring(0,2)");
				out.println("document.Form1.VAL_MONTH.value=dval.substring(3,5)");
				out.println("document.Form1.VAL_YEAR.value=dval.substring(6,10)");
				out.println("}");
				
				/*--------------------------------------------------------------------------------------------------
				Purpose : Diplay the Deposit Number Data
				--------------------------------------------------------------------------------------------------*/
				out.println("function display_data(data_vec){");
				
				out.println("     document.Form1.DEPOSIT_CODE.value    =data_vec[0];"); 
				out.println("    getDateValues(data_vec[1]);");
				out.println("     document.Form1.SETT_MODE.value      =data_vec[2];"); 
				out.println("     document.Form1.ACCOUNT_NO.value      =data_vec[3];"); 
				out.println("     document.Form1.BRANCH_CODE.value     =data_vec[4];"); 
				out.println("     document.Form1.BRANCH_NAME.value     =data_vec[5];"); 
				out.println("    document.Form1.REFERENCE.value       =data_vec[6];"); 
				
				out.println("}");
				
				
				
				out.println("function Assign_account_values(data_vec){");
				
				out.println("     document.Form1.ACCOUNT_NO.value      =data_vec[0];"); 
				out.println("     document.Form1.BRANCH_CODE.value     =data_vec[1];"); 
				out.println("     document.Form1.BRANCH_NAME.value     =data_vec[2];"); 
				out.println("    document.Form1.REFERENCE.value        =data_vec[3];"); 
				
				out.println("get_Deposit_data()");
				
				
				out.println("}");
				
				
				
				
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\" || document.Form1.VAL_YEAR.value==\"\"){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				/*out.println("if(document.Form1.VAL_DAY1.value==\"\" || document.Form1.VAL_MONTH1.value==\"\" || document.Form1.VAL_YEAR1.value==\"\"){  "); 
				out.println("VDATE1.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				
				out.println("if(document.Form1.VAL_DAY2.value==\"\" || document.Form1.VAL_MONTH2.value==\"\" || document.Form1.VAL_YEAR2.value==\"\"){  "); 
				out.println("VDATE2.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
			*/
				out.println("else if(document.Form1.ACCOUNT_NO.value==\"\"){  "); 
				out.println("ANO.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				
				
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				
				out.println("function ckeck_receipts(){ "); 
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No Receipts To Deposit');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_receipts() ){"); 
				out.println("alert('Please Select Receipt To Be Deposit');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else {");
				out.println("b_flag=0;");
				out.println("}"); 
				out.println("}"); 
				
				out.println("}"); 
				
				/*---------------------------------------------------------------------
				Purpose : This Function Used TO Count THe Number Of Receipts Selected
				--------------------------------------------------------------------*/
				out.println("function count_receipts(){ ");
				out.println("count=0;");
				//	out.println("alert('arr size'+arr_size);");
				
				out.println(" arr_size= document.Form1.hid_count_receipts.value; ");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_chk_deposit_tmp=\"CHK_DEPOSIT\"+i;");
				
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 
				
				
				out.println("function before_submit(){ "); 
				//	out.println("alert('count'+count);");
				
				out.println("		if(validate_data()){");
				out.println("document.Form1.save_but.disabled=true;");
				out.println("ckeck_receipts();");
				out.println("if(b_flag==0  )");
				out.println("if(parseInt(count)<=32 || document.Form1.SETT_MODE.value=='CASH'  || document.Form1.SCREEN_NAME.value==\"DEL\"  ){"); //
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				out.println("		if(validate_data()){"); 
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_SettlementDiposit';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}else{");
				out.println("document.Form1.save_but.disabled=false;");
				out.println("		}"); 
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Receipt Selected Should Be Less Than or Equal To 32 Receipts\");");
				out.println("} "); 
				
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("} "); 
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				
				/* out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
					out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
					out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
					m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
					out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
					
					out.println("document.Form1.hid_SYS_DATE_DD.value='"+m_date_dd+"'");
					out.println("document.Form1.hid_SYS_DATE_MM.value='"+m_date_mm+"'");
					out.println("document.Form1.hid_SYS_DATE_YY.value='"+m_date_yy+"'");
			   */
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	");			
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_bank_settlement\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection - Receipts - Deposit - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection - Receipts - Deposit - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
				out.println("new_window();"); 
				//out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=false;");
				out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.DEPOSIT_CODE.disabled=false;"); 
				
				out.println("}"); 
				out.println("else{");
				//out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
				out.println("document.Form1.DEPOSIT_CODE.disabled=false;"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println(" if(confirm(\"Are you sure you want to Delete a record?\")){  ");
				out.println("m_table.innerHTML=\"\"");
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				
				
				out.println("document.Form1.VAL_DAY.disabled=true;");
				out.println("document.Form1.VAL_MONTH.disabled=true;");
				out.println("document.Form1.VAL_YEAR.disabled=true;");
				
				out.println("     document.Form1.SETT_MODE.disabled=true;"); 
				out.println("     document.Form1.ACCOUNT_NO.disabled=true;"); 
				out.println("     document.Form1.acc_help.disabled=true;"); 
				
				out.println("     document.Form1.BRANCH_CODE.disabled=true;"); 
				out.println("     document.Form1.BRANCH_NAME.disabled=true;"); 
				out.println("    document.Form1.REFERENCE.disabled=true;"); 
				out.println("clear_values();");
				
				out.println("}}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("document.Form1.hid_save_status.value=\"Reactive\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				
				
				out.println("function clear_values(){");
				
				
				out.println("document.Form1.VAL_DAY.value='';");
				out.println("document.Form1.VAL_MONTH.value='';");
				out.println("document.Form1.VAL_YEAR.value='';");
				
				out.println("     document.Form1.SETT_MODE.value='';");
				out.println("     document.Form1.ACCOUNT_NO.value='';");
				//	out.println("     document.Form1.acc_help.value='';");
				
				out.println("     document.Form1.BRANCH_CODE.value='';");
				out.println("     document.Form1.BRANCH_NAME.value='';");
				out.println("    document.Form1.REFERENCE.value='';");
				out.println("    document.Form1.AMOUNT.value='';");
				out.println("}");
				
				
				
				
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
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
				
				//  out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CO_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign(oBj);"); 
				out.println("		}"); 
				
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		account_help_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_location(oBj);"); 
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
				
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.DEPOSIT_CODE.value='';");
				out.println("document.Form1.DEPOSIT_CODE.focus();");
				out.println("	}");
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("document.Form1.ACCOUNT_NO.value='';"); 
				out.println("document.Form1.ACCOUNT_NO.focus();"); 
				out.println("}");
				
				out.println("		if(IfCount==\"3\"){"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value='';"); 
				out.println("    document.Form1.TXT_LOCATION_DESC.value='';"); 
				out.println("}");
				
				out.println("}");
				
				
				
				out.println("function load_data(num) {");
				out.println("	popupwin = window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Documents?chksql=get_documents&deal_no=\"+num+\"\", \"oBj\",\"left=150,top=280,width=520,height=290\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				
				
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("   checkMonthLength(objDD,objMM,objYY);");
				//	out.println("validate_date();");//comment by nuwan on 02-04-2008 temp
				//out.println("get_Deposit_data()");
				out.println("");
				out.println("}");
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("var date='' ");
				//	out.println("alert('date valaue'+val);");
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
				
				//out.println("validate_date();");//comment on 02-04-2008 nuwan temp
				
				
				// out.println("get_Deposit_data()");
				
				
				//out.println("     document.Form1.VAL_DAY.value=val.substr(0,2);");
				//out.println("     document.Form1.VAL_MONTH.value=val.substr(3,2);");
				//out.println("     document.Form1.VAL_YEAR.value=val.substr(6,4);");
				
				
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function validate_date(){ ");	//TEMP COMMENT FOR 21-12-2007
				//out.println("if(chk_validity(document.Form1.hid_SYS_DATE_DD,document.Form1.hid_SYS_DATE_MM,document.Form1.hid_SYS_DATE_YY,document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)){");-
				out.println("document.Form1.hid_bank_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				//out.println(" if(confirm(\"Are you sure you want change the Banking date\")){  ");
				out.println("get_Deposit_data('DATE_CHANGE')");	
				/*out.println("}");
				out.println("else {");
				out.println("     document.Form1.VAL_DAY.value='"+m_date_dd+"';");
				out.println("     document.Form1.VAL_MONTH.value='"+m_date_mm+"';");
				out.println("     document.Form1.VAL_YEAR.value='"+m_date_yy+"';");
				out.println("}");			
				out.println("}");
				out.println("else {");			
				out.println("     document.Form1.VAL_DAY.value='"+m_date_dd+"';");
				out.println("     document.Form1.VAL_MONTH.value='"+m_date_mm+"';");
				out.println("     document.Form1.VAL_YEAR.value='"+m_date_yy+"';");
				out.println("}");*/
				out.println("}");
				
				
				
				out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
				out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
				out.println("if((parseInt(FROM_DD.value))>=(parseInt(TO_DD.value))){");
				out.println("if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
				out.println("if((parseInt(FROM_YY.value))<=(parseInt(TO_YY.value))){");
				out.println(" if(((parseInt(FROM_DD.value))==(parseInt(TO_DD.value)))&&");
				out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
				out.println("((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
				out.println("}");
				out.println("else if(((parseInt(FROM_DD.value))>(parseInt(TO_DD.value)))&&");
				out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
				out.println(" ((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
				out.println("      alert('Banking Date should be greater than System Date');");
				out.println("     } ");
				out.println("}");
				out.println("else{");
				out.println("      alert('Banking Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("}");
				out.println(" }");
				out.println(" else{");
				out.println("   if((parseInt(FROM_YY.value))>=(parseInt(TO_YY.value))){");
				out.println("      alert('Banking Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("   }");
				out.println("   else{");
				out.println("   } ");
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println(" if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
				out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
				out.println(" }");
				out.println(" else{");
				out.println("      alert('Banking Date should be greater than System Date');");
				out.println("return false;"); 
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println("   if((parseInt(FROM_YY.value))<(parseInt(TO_YY.value))){ ");
				out.println("    }");
				out.println("  else{");
				out.println("      alert('Banking Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("  }");
				out.println(" }");
				out.println("}");
				//	out.println("FROM_DD.focus();");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				
				//added by nuwan de silva 25-06-07------------------------------------------------
				out.println("function help_button_location() {"); 
				out.println("    Crit = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_LOCATION_CODE_sql','3');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_location() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_DESC.value=oBj.valout[3];"); 
				out.println("}"); 
				//---------------------------------------------------------------------------------
				
				
				out.println("function account_help() {"); 
				
				
				out.println("    Crit = document.Form1.ACCOUNT_NO.value+\"@Y@\";"); 
				//out.println("    Crit = document.Form1.TXT_AREA_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_Collection_process_Account_code','2');"); 
				
				out.println("}"); 
				
				out.println("function account_help_assign(oBj) {"); 
				out.println("    document.Form1.ACCOUNT_NO.value=oBj.valout[2];"); 
				out.println("    document.Form1.BRANCH_CODE.value=oBj.valout[3];"); 
				out.println("    document.Form1.BRANCH_NAME.value=oBj.valout[4];"); 
				out.println("    document.Form1.REFERENCE.value=oBj.valout[6];"); 
				
				//  out.println("get_Deposit_data();");	//Call To DeposiT Function To Retive The Records
				out.println("}"); 
				
				/*---------------------------------------------------------------------------------
				Purpose : To Get The Deposit Numbers.
				---------------------------------------------------------------------------------*/
				
				out.println("function help_update() {"); 
				
				
				out.println("    Crit = document.Form1.DEPOSIT_CODE.value+\"@Y@\";"); 
				//out.println("    Crit = document.Form1.DEPOSIT_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_Collection_process_Deposit_code','99');"); 
				
				out.println("}"); 
				
				out.println("function help_update_value_assign(oBj) {"); 
				
				
				out.println("     document.Form1.DEPOSIT_CODE.value    =oBj.valout[2];"); 
				out.println("    getDateValues(oBj.valout[3]);");
				
				out.println("     document.Form1.SETT_MODE.value      =oBj.valout[4];"); 
				out.println("     document.Form1.ACCOUNT_NO.value      =oBj.valout[5];"); 
				out.println("     document.Form1.BRANCH_CODE.value     =oBj.valout[6];"); 
				out.println("     document.Form1.BRANCH_NAME.value     =oBj.valout[7];"); 
				out.println("    document.Form1.REFERENCE.value       =oBj.valout[8];"); 
				
				//out.println("if(document.Form1.SCREEN_NAME.value==\"DEL\"){");	
				
				out.println("assignState('M4');");
				out.println("makeRequest(document.Form1.DEPOSIT_CODE);");
				
				//out.println("}"); 				
				// out.println("else");
				//	out.println("get_Deposit_data();");	//Call To DeposiT Function To Retive The Records
				
				out.println("}"); 
				
				
				/*-----------------------------------------------------------------
				Purpose    : This Function Get The Deposit Data
				------------------------------------------------------------------*/
				out.println("function get_Deposit_data(val){");
				out.println("document.Form1.AMOUNT.value=\"0.00\"");
				out.println("document.Form1.RECEIPT_COUNT.value=count");
				//MODIFIED BY NUWAN DE SILVA 26-07-07---------------------------
				out.println("if(val=='SYS_DATE'){"); 
				
				out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
				m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
				out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
				out.println("document.Form1.hid_bank_date1.value='"+m_val_date+"'");
				out.println("}"); 
				
				out.println("else {"); 
				out.println("document.Form1.hid_bank_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("document.Form1.hid_bank_date1.value=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("document.Form1.hid_bank_date2.value=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				
				
				out.println("}"); 
				//---------------------------------------------------------------
				out.println("document.Form1.hid_SYS_DATE_DD.value='"+m_date_dd+"'");
				out.println("document.Form1.hid_SYS_DATE_MM.value='"+m_date_mm+"'");
				out.println("document.Form1.hid_SYS_DATE_YY.value='"+m_date_yy+"'");
				
				out.println("m_table.innerHTML=\"\";");
				//out.println("assignState('M1');");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_COLLECTION_PROCES_get_receipt&data_val=\"+document.Form1.ACCOUNT_NO.value+\"&data_va2=\"+document.Form1.SETT_MODE.value+\"&ac_status=E\";");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_COLLECTION_PROCES_get_receipt&data_val=\"+document.Form1.SETT_MODE.value+\"&bank_date=\"+document.Form1.hid_bank_date1.value+\"&ac_status=E\";");
				
				//comment by nuwan de silva on 17-04-2008---------------
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_COLLECTION_PROCES_get_receipt&user_location=\"+document.Form1.TXT_LOCATION_CODE.value+\"&data_val=\"+document.Form1.SETT_MODE.value+\"&bank_date=\"+document.Form1.hid_bank_date1.value+\"&bank_date2=\"+document.Form1.hid_bank_date2.value+\"&ac_status=E\";");
				//out.println("load_interface(m_url,'XML');");
				
				out.println("  var m_acc_no = document.Form1.BRANCH_CODE.value;   "); // added by udara 11-03-2014
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit?chksql=receipts_details&user_location=\"+document.Form1.TXT_LOCATION_CODE.value+\"&data_val=\"+document.Form1.SETT_MODE.value+\"&bank_date=\"+document.Form1.hid_bank_date1.value+\"&bank_date2=\"+document.Form1.hid_bank_date2.value+\"&ac_status=E\";"); // commented by udara 11-03-2014
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit?chksql=receipts_details&user_location=\"+document.Form1.TXT_LOCATION_CODE.value+\"&data_val=\"+document.Form1.SETT_MODE.value+\"&bank_date=\"+document.Form1.hid_bank_date1.value+\"&bank_date2=\"+document.Form1.hid_bank_date2.value+\"&ac_status=E\"+\"&acc_no=\"+m_acc_no;"); // added by udara 11-03-2014
				
				//out.println("  alert(m_url); "); // added by udara 11-03-2014

				out.println("load_interface(m_url,'NORM');");
				//out.println("window.open(m_url);");
				out.println("}"); 
				
				out.println("function show_rec_details(row_No) {"); 
				out.println("show_settle_receipt_drill(new_data_vec[row_No]);"); 
				out.println("}"); 
				
				out.println("function show_bank_details(row_No) {"); //added by nuwan de silva 17-07-07
				out.println("show_bank_drill(new_data_vec[row_No]);"); 
				out.println("}"); 
				
				/*--------------------------------------------------------------------
				Purpose  :This Function Display The Receipts
				------------------------------------------------------------------*/
				out.println("function display_receipts(data_vec){");
				out.println("var i=0;");
				out.println("var j=0;");
				out.println("sum=0;");	
				out.println("count=0;");		
				out.println("var c_client=0;");
				out.println("header();	");		
				
				out.println("while(i<data_vec.length){");
				out.println("m_receipt_no='<TD WIDTH=\"15%\" STYLE=\"{cursor:hand;}\"  onclick=\"show_rec_details('+[c_client]+')\"  ><u>'+data_vec[i]+'</u></TD>';");
				out.println("m_old_rec_no='<TD WIDTH=\"15%\">'+data_vec[i+7]+'</TD>';");		
				out.println("m_receipt_date='<TD WIDTH=\"10%\">'+data_vec[i+5]+'</TD>';");		
				out.println("m_cheque_no='<TD WIDTH=\"10%\">'+data_vec[i+1]+'</TD>';");		
				out.println("m_bank_account='<TD WIDTH=\"10%\">'+data_vec[i+2]+'</TD>';");	
				out.println("m_bank_name='<TD WIDTH=\"15%\" STYLE=\"{cursor:hand;}\" onclick=\"show_bank_details('+[c_client+6]+')\" ><u>'+data_vec[i+3]+'</u></TD>';");	 //modified by nuwan de silva 17-07-07
				out.println("m_amount='<TD WIDTH=\"10%\" STYLE=\"{text-align:right;}\">'+data_vec[i+4]+'</TD>';");	
				out.println("m_user_location='<TD WIDTH=\"10%\">'+data_vec[i+8]+'</TD>';");	 // added by nuwan de silva on 09-04-2008
				
				out.println("if(document.Form1.hid_chk_status.value=='M4'){");
				out.println("m_deposit='<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_DEPOSIT'+lineno+' VALUE=\"on\" checked onclick=\"change_val_deposit_status('+lineno+')\" disabled></td>';");			
				//out.println("sum=sum+parseFloat(document.Form1.elements[m_amount].value);");
				out.println("}");
				out.println("else{");
				out.println("m_deposit='<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_DEPOSIT'+lineno+' VALUE=\"off\" onclick=\"change_val_deposit_status('+lineno+')\"></td>';");			
				out.println("}");
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_RECEIPT_NO'+lineno+'	VALUE='+data_vec[i]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CHEQUE_NO'+lineno+'	VALUE='+data_vec[i+1]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_ACCOUNT'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_NAME'+lineno+'	VALUE='+data_vec[i+3]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_AMOUNT'+lineno+'	VALUE='+data_vec[i+4]+'>';");
				// out.println("m_writedata='<TR>'+m_receipt_no+m_cheque_no+m_bank_account+m_bank_name+m_amount+m_deposit+'</TR>'+m_hid_input;"); 
				out.println("if(j>0 && j%2==1){");
				out.println("m_writedata='<TR class=\"tr_input1\">'+m_receipt_no+m_old_rec_no+m_receipt_date+m_cheque_no+m_bank_account+m_bank_name+m_amount+m_user_location+m_deposit+'</TR>'+m_hid_input;"); 
				out.println("	}");
				out.println("	else{");
				out.println("m_writedata='<TR class=\"tr_input\">'+m_receipt_no+m_old_rec_no+m_receipt_date+m_cheque_no+m_bank_account+m_bank_name+m_amount+m_user_location+m_deposit+'</TR>'+m_hid_input;"); 
				out.println("	}");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("j=j+1;");
				out.println("i=i+9;");
				out.println("c_client=c_client+9;");
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");		
				out.println("}"); //End while loop
				//	out.println("for(i=0;i<arr_size;i++){");
				//	out.println("m_amount=\"hid_TXT_AMOUNT\"+i;");
				//	out.println("}");			
				out.println("if(document.Form1.hid_chk_status.value=='M4'){");
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_amount=\"hid_TXT_AMOUNT\"+i;");
				out.println("sum=sum+parseFloat(document.Form1.elements[m_amount].value);");
				out.println("count=count+1;");
				out.println("}");			
				out.println("document.Form1.AMOUNT.value=sum");
				out.println("document.Form1.RECEIPT_COUNT.value=count;");
				out.println("format_noobject(document.Form1.AMOUNT.value)");	
				out.println("}");
				out.println("}"); 
				
				
				out.println("function check_all(){");
				
				out.println("if(m_table.innerHTML!=\"\") {");
				
				out.println("sum=0;");
				out.println("count=0;");
				out.println(" arr_size= document.Form1.hid_count_receipts.value; ");
				out.println("if(document.Form1.CHK_ALL.checked==true){");
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_amount=\"hid_TXT_AMOUNT\"+i;");
				out.println("m_chk_deposit=\"CHK_DEPOSIT\"+i;");
				out.println("document.Form1.elements[m_chk_deposit].checked=true");
				out.println("document.Form1.elements[m_chk_deposit].value='on'");//added by nuwan de silva on 05-10-07
				
				out.println("if(document.Form1.elements[m_chk_deposit].checked==true){");
				out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("count=count+1;");
				out.println("}");			
				
				out.println("}"); 
				
				out.println("}"); 
				
				out.println("else if(document.Form1.CHK_ALL.checked==false){");
				
				out.println("for(i=0;i<arr_size;i++){");
				
				out.println("m_amount=\"hid_TXT_AMOUNT\"+i;");
				out.println("m_chk_deposit=\"CHK_DEPOSIT\"+i;");
				
				out.println("document.Form1.elements[m_chk_deposit].checked=false");
				
				out.println("}"); 
				
				out.println("}"); 
				
				
				out.println("document.Form1.AMOUNT.value=format_noobject(sum)");
				out.println("document.Form1.RECEIPT_COUNT.value=count");
				
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				
				
				out.println("function change_val_deposit_status(row_no){")	;
				
				
				out.println("m_chk_deposit=\"CHK_DEPOSIT\"+row_no;");
				out.println("sum=0.00;");
				out.println("count=0;");
				out.println("if(document.Form1.elements[m_chk_deposit].checked==true){");
				out.println("document.Form1.elements[m_chk_deposit].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_deposit].checked==false){");
				out.println("document.Form1.elements[m_chk_deposit].value='off'");
				out.println("}");
				
				out.println(" arr_size= document.Form1.hid_count_receipts.value; ");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_amount=\"hid_TXT_AMOUNT\"+i;");
				out.println("m_chk_deposit_tmp=\"CHK_DEPOSIT\"+i;");
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				//out.println("alert('value'+document.Form1.elements[m_amount].value);");
				out.println("sum=sum+parseFloat(unformat_noobject(document.Form1.elements[m_amount].value));");
				out.println("count=count+1;");
				
				out.println("}");			
				
				out.println("document.Form1.AMOUNT.value=format_noobject(sum)");
				out.println("document.Form1.RECEIPT_COUNT.value=count");
				//out.println("alert('value'+document.Form1.elements[m_amount].value);");
				//out.println("format_noobject(document.Form1.AMOUNT.value)");								
				
				out.println("}");			
				
				
				
				
				out.println("}");			
				
				/*--------------------------------------------------------------------
				Purpose  :This Function Display The Header
				------------------------------------------------------------------*/
				out.println("function header(){");
				
				out.println("m_table.innerHTML=\"\"");
				out.println("lineno=0;");
				out.println("arr_size=0;");		
				
				
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><TR class=\"pdn_txtpos2\" align=\"center\" >'+");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Receipt No</B></TD>'+");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Old Receipt No</B></TD>'+");
				out.println("'<TD WIDTH=\"10%\"     align=\"left\"><B>Receipt Date</B></TD>'+");
				out.println("'<TD WIDTH=\"10%\"     align=\"left\"><B>Cheque Number</B></TD>'+");
				out.println("'<TD WIDTH=\"10%\"     align=\"left\"><B>Bank Account No</B></TD>' +");
				out.println("'<TD WIDTH=\"15%\"     align=\"left\"><B>Bank Name</B></TD>' +");
				out.println("'<TD WIDTH=\"10%\"     align=\"right\"><B>Amount</B></TD>' +");
				out.println("'<TD WIDTH=\"10%\"     align=\"left\"><B>Location</B></TD>' +");
				out.println("'<TD WIDTH=\"5%\"     align=\"center\"><B>Deposit</B></TD>' +");
				
				out.println("'</TR></table>';");
				
				out.println("}");
				
				out.println("function View_pending_receipts(){");
				// out.println("window.location.href='"+m_url+"/LAKDL_AF_RE_SettlementDiposit_Pending_Report?chksql=main_page';");
				//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit_Pending_Report?chksql=main_page';"); 
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit_Pending_Report?chksql=main_page\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=110,width=900,height=600,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				
				out.println("}");		
				
				out.println("function View_Diposit_slip(){");
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Deposit_Slip_View\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_SettlementDiposit?chksql=main_page';"); 
				
				out.println("}");
				
				
				
				//added by nuwan de silva 21-12-07-----------------
				out.println("function show_receipts_to_be_diposit(){");
				out.println("get_Deposit_data();");
				out.println("}");
				
				
				out.println("function load_System_date(){");
				out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
				
				out.println("document.Form1.VAL_DAY1.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH1.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR1.value='"+m_date_yy+"'");
				
				out.println("document.Form1.VAL_DAY2.value='"+m_date_dd+"'");
				out.println("document.Form1.VAL_MONTH2.value='"+m_date_mm+"'");
				out.println("document.Form1.VAL_YEAR2.value='"+m_date_yy+"'");
				
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
				out.println("     document.Form1.VAL_DAY1.value=v_date;");
				out.println("     document.Form1.VAL_MONTH1.value=v_month;");
				out.println("     document.Form1.VAL_YEAR1.value=val;");
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
				out.println("     document.Form1.VAL_DAY2.value=v_date;");
				out.println("     document.Form1.VAL_MONTH2.value=v_month;");
				out.println("     document.Form1.VAL_YEAR2.value=val;");
				out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("}");
				out.println("}");
				
				
				//===============================================
				
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New'),load_System_date()\">");   //,get_Deposit_data('SYS_DATE')
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_SETTELMENT_BANK' > ");
				//out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_CAL' > ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
				out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date1' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date2' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_DD' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_MM' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_SYS_DATE_YY' VALUE=\"\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Receipts - Deposit</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//		out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name =\"save_but\" onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='width:150';  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View Pending Receipts\");'  onclick='View_pending_receipts()' value=\"View Pending Receipts\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='width:120';  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deposit Slip\");'  onclick='View_Diposit_slip()' value=\"Deposit Slip\"></td>"); 
				//out.println("<td width='15%' align='left'><input class='but_input' style='width:100'; font-size: 20px;  type='button' name='INVOICE_LINK_BUT' value=\"Pro Forma Invoice\" onClick=\"load_invoice()\" disabled></td>"); 
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
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				out.println("<tr >"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch Code</DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' onblur=\"help_button_location()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LOCATION_CODE' value=\"...\" onClick=\"help_button_location()\"></td>"); 
				out.println("<td >Branch Name </td>"); 
				out.println("<td ><input class='txt_input' type='text' name='TXT_LOCATION_DESC' style=\"width:250px;\" maxlength='250' size='22' disabled></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				out.println("<tr class=tr_input>"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_DEPOSIT_CODE'  class=div_input>Deposit Code *</DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='DEPOSIT_CODE' maxlength='15' size='10' disabled onblur=\"assignState('M2'),makeRequest(document.Form1.DEPOSIT_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"...\" onClick=\"help_update()\" disabled></td>"); 
				out.println("<td ></td>");
				out.println("<td></TD>");
				out.println("</tr>"); 
				
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=VDATE>Banking Deposit Date</td>");
				out.println("<td><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" disabled> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" disabled> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" disabled onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><!--a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a--> ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td></TD>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=VDATE1>From Banking Date</td>");
				out.println("<td><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				//	out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)>"); //comment by Prabash on 08-02-2012
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>"); //added by prabash on 08-02-2012
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td></TD>");
				//out.println("<input name=\"LEAD_SOURCE_CATEGORY\" type=\"text\" maxlength=\"10\" id=\"txtAddress1\" class=\"txt_input\" style=\"width:150px;\" >");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td ID=VDATE2>To Banking Date</td>");
				out.println("<td><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				//out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");//comment by Prabash on 08-02-2012
				out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");//Added by Prabash on 08-02-2012
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td></TD>");
				out.println("</tr>");
				
				
				out.println("<tr class=tr_input>");
				out.println("<td >Settlement Mode</td>");
				out.println("<td><SELECT name=\"SETT_MODE\" class=\"txt_input\" onChange=\"\"> "); //get_Deposit_data() // comment by nuwan de silva on 21-12-07
				out.println("<OPTION value=\"CHEQUE\" >Cheque  </OPTION>");
				out.println("<OPTION value=\"CASH\"   >Cash    </OPTION>");
				
				out.println("</SELECT></TD>");
				out.println("<td ></td>");
				out.println("<td>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td id=ANO>Account No *</td>");
				out.println("<td><input name=\"ACCOUNT_NO\" type=\"text\" maxlength=\"20\" class=\"txt_input\" onblur=\"assignState('M3'),makeRequest(document.Form1.ACCOUNT_NO)\"> ");
				out.println("<input type=button name=acc_help value=... class=\"but_input\" onclick=\"account_help()\"></td>");
				out.println("</td>");
				out.println("<td width='10%'>Branch Code</td>");
				out.println("<td><input name=\"BRANCH_CODE\" type=\"text\" maxlength=\"15\" class=\"txt_input\" disabled> ");
				out.println("</td>");
				
				out.println("</tr>");		
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td id=CCODE>Reference</td>");
				out.println("<td><input name=\"REFERENCE\" type=\"text\" maxlength=\"10\" STYLE=\"{width :200px;}\" class=\"txt_input\"disabled> ");
				out.println("</td>");
				out.println("<td >Branch Name</td>");
				out.println("<td><input name=\"BRANCH_NAME\" type=\"text\" maxlength=\"15\" STYLE=\"{width :250px;}\" class=\"txt_input\" disabled> ");
				out.println("</td>");
				out.println("<td ></td>");
				out.println("<td></TD>");
				out.println("</tr>");		
				
				out.println("</table>");
				
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				
				out.println("<tr class=tr_input>");
				out.println("<td width='30%' >Total </td>");
				out.println("<td width='30%' ><input name=\"AMOUNT\" type=\"text\" maxlength=\"25\" class=\"txt_input\" disabled STYLE=\"{text-align:right;}\">");
				out.println("<input type=button name=btn_receipt_display value=\"Show Receipts\"  class=\"but_input\" style=\"{width :80px;}\" onclick=\"show_receipts_to_be_diposit()\">");
				out.println("</td>");
				out.println("<td  width='10%' >Receipt Selected</td>");
				out.println("<td width='10%' ><input name=\"RECEIPT_COUNT\" type=\"text\" maxlength=\"2\" STYLE=\"{width :75px;}\"  class=\"txt_input\" disabled> ");
				out.println("</td>");
				out.println("<td width='10%' >Select All </td>");
				out.println("<td width='10%' align='center'><INPUT TYPE=\"checkbox\" NAME=CHK_ALL  VALUE=\"off\" onclick=\"check_all()\"></td>");			
				
				out.println("</tr>");
				
				
				out.println("</table>");
				
				
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>");
				
				
				
				
				out.println("</form>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				
				
				out.println("</body>"); 
				out.println("</html>"); 
				
			} 
			else if(m_chksql.trim().equals("receipts_details")){
				
				
				String m_val = req.getParameter("data_val").trim();
				String m_bank_date = req.getParameter("bank_date").trim();
				String m_bank_date2 = req.getParameter("bank_date2").trim();
				String m_status = req.getParameter("ac_status").trim();
				String m_user_location =req.getParameter("user_location").trim();
				
				String m_acc_no =req.getParameter("acc_no").trim(); // added by udara 11-03-2014
				
				// added by udara 11-03-2014
				if(m_acc_no==null)
					m_acc_no="";
				
				if(m_val.trim().equals("CHEQUE")){
					
					rs= stmt.executeQuery (" SELECT "+
						" REC_NO, "+//1 // mod by udara on 09-08-2013
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(CHEQUE_NO,'-'),'CASH','-'), "+ //2
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(PAYER_ACC_NO,' ' ),'CASH','-'), "+ //3
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)), ' '),'CASH','-'), "+ //4
						" NVL(REC_AMOUNT,0.00), "+ //5
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') ,"+ //6
						" NVL(B.BANK_CODE,'-'), "+  //modified by nuwan de silva 17-07-07 //7
						" NVL(SUBSTR(OTH_COMMENTS,INSTR(OTH_COMMENTS,'@')+1,LENGTH(OTH_COMMENTS)),' ') "+  //8
						","+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)), "+ //9
						" NVL(A.SUB_REC_NO,A.REC_NO) "+ // 10 added by udara on 09-08-2013
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
						" WHERE SETTLE_MODE=UPPER('"+m_val+"')  "+
						//" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) LIKE UPPER('"+m_user_location+"%') "+ // commented by udara on 30-07-2013
						//" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"')) "+ // commented by udara 17-02-2014 // added by udara on 30-07-2013
						" AND (  (UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'))) OR  (UPPER(A.REC_LOC) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"')))   ) "+ // added by udara 17-02-2014
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_bank_date+"','DD-MM-YYYY')   "+
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_bank_date2+"','DD-MM-YYYY')   "+
						" AND GROUP_REC_NO IS NULL "+//MODIFIED BY DELANJALI
						" AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE(+) "+
						" AND STATUS=('"+m_status+"') "+ // ORDER BY  EFF_VALDATE,REC_NO "+
						" AND STATUS NOT IN ('B') "+ // added by udara on 30-09-2013
						//" AND NVL(B.BRANCH_CODE,' ') LIKE '%"+m_acc_no+"%'    "+ // added by udara 11-03-2014
						" ORDER BY A.ENT_DATE,A.EFF_VALDATE,A.REC_NO "+ // added by udara on 09-08-2013
						" ");
					
				}
				else
				{
					
					rs= stmt.executeQuery (" SELECT "+
						" REC_NO, "+//1 // mod by udara on 09-08-2013
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(CHEQUE_NO,'-'),'CASH','-'), "+ //2
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(PAYER_ACC_NO,' '),'CASH','-'), "+ //3
						" DECODE(SETTLE_MODE,'CHEQUE',NVL(INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(PAYER_BRANCH_CODE)), ' '),'CASH','-'), "+ //4
						" NVL(REC_AMOUNT,0.00), "+ //5
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') ,"+ //6
						" '-' BANK_CODE, "+  //modified by nuwan de silva 17-07-07 //7
						" NVL(SUBSTR(OTH_COMMENTS,INSTR(OTH_COMMENTS,'@')+1,LENGTH(OTH_COMMENTS)),' ') "+  //8
						","+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)), "+ //9
						" NVL(SUB_REC_NO,REC_NO)  "+ // 10 added by udara on 09-08-2013
						" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						" WHERE SETTLE_MODE=UPPER('"+m_val+"') "+
						//" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)) LIKE UPPER('"+m_user_location+"%') "+ 
						//" AND UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"')) "+ // commented by udara 17-02-2014 // added by udara on 30-07-2013
						" AND (  (UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION(ENT_USER)) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"'))) OR  (UPPER(REC_LOC) = UPPER("+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"')))   ) "+ // added by udara 17-02-2014
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_bank_date+"','DD-MM-YYYY')  "+ //modified 21-12-07
						" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_bank_date2+"','DD-MM-YYYY')   "+
						" AND GROUP_REC_NO IS NULL "+//MODIFIED BY DELANJALI
						" AND STATUS=('"+m_status+"') "+ // ORDER BY  EFF_VALDATE,REC_NO  ");
						" AND STATUS NOT IN ('B') "+ // added by udara on 30-09-2013
						//" AND NVL(BRANCH_CODE,' ') LIKE '%"+m_acc_no+"%'    "+ // added by udara 11-03-2014
						" ORDER BY ENT_DATE,EFF_VALDATE,REC_NO "+
						" ");
				}
				
				boolean more=rs.next();
				if(more){		
					out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><TR class=\"pdn_txtpos2\" align=\"center\" >");
					out.println("<TD WIDTH=\"15%\"     align=\"left\"><B>Receipt No</B></TD>");
					out.println("<TD WIDTH=\"15%\"     align=\"left\"><B>Old Receipt No</B></TD>");
					out.println("<TD WIDTH=\"10%\"     align=\"left\"><B>Receipt Date</B></TD>");
					out.println("<TD WIDTH=\"10%\"     align=\"left\"><B>Cheque Number</B></TD>");
					out.println("<TD WIDTH=\"10%\"     align=\"left\"><B>Bank Account No</B></TD>");
					out.println("<TD WIDTH=\"15%\"     align=\"left\"><B>Bank Name</B></TD>");
					out.println("<TD WIDTH=\"10%\"     align=\"right\"><B>Amount</B></TD>");
					out.println("<TD WIDTH=\"10%\"     align=\"left\"><B>Location</B></TD>");
					out.println("<TD WIDTH=\"5%\"      align=\"center\"><B>Deposit</B></TD></TR>");
				}
				//out.println("</TR></table>");
				int j=0;
				while(more){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					//out.println("<TD WIDTH=\"15%\" STYLE=\"{cursor:hand;}\"  onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></TD>"); // commented by udara on 09-08-2013
					out.println("<TD WIDTH=\"15%\" STYLE=\"{cursor:hand;}\"  onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(10)+"</u></TD>"); // added by udara on 09-08-2013
					out.println("<TD WIDTH=\"15%\">"+rs.getString(8)+"</TD>");		
					out.println("<TD WIDTH=\"10%\">"+rs.getString(6)+"</TD>");		
					out.println("<TD WIDTH=\"10%\">"+rs.getString(2)+"</TD>");		
					out.println("<TD WIDTH=\"10%\">"+rs.getString(3)+"</TD>");	
					out.println("<TD WIDTH=\"15%\" STYLE=\"{cursor:hand;}\" onclick=\"show_bank_drill('"+rs.getString(7)+"')\" ><u>"+rs.getString(4)+"</u></TD>");
					out.println("<TD WIDTH=\"10%\" STYLE=\"{text-align:right;}\">"+nf.format(rs.getDouble(5))+"</TD>"); // out.println("<TD WIDTH=\"10%\" STYLE=\"{text-align:right;}\">"+rs.getString(5)+"</TD>");	
					out.println("<TD WIDTH=\"10%\">"+rs.getString(9)+"</TD>");	
					out.println("<TD WIDTH=\"5%\" align=\"center\"><INPUT TYPE=\"checkbox\" NAME=CHK_DEPOSIT"+j+" VALUE=\"off\" onclick=\"change_val_deposit_status('"+j+"')\"></td>");			
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_RECEIPT_NO"+j+"	 VALUE=\""+rs.getString(1)+"\">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_CHEQUE_NO"+j+"	   VALUE=\""+rs.getString(2)+"\">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_ACCOUNT"+j+"VALUE=\""+rs.getString(3)+"\">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_BANK_NAME"+j+"	VALUE=\""+rs.getString(4)+"\">");
					out.println("<INPUT TYPE=\"Hidden\" NAME=hid_TXT_AMOUNT"+j+"	VALUE=\""+rs.getDouble(5)+"\" >");
					more=rs.next();			
					j=j+1;
					out.println("</TR>");
				}
				out.println("<input type=hidden name=hid_count_receipts value="+j+">");
				out.println("</table>");
				
				
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
