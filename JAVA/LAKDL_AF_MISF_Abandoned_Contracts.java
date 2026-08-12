// DEVELOP BY : Ishani 2013.07.11
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Abandoned_Contracts extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt;
	CallableStatement callstmt1 =null;
	
	java.text.NumberFormat nf;
	public ResultSet rs1,rs;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			//******************************************************************************** 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			//**********************************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			//Added by Dineth on 28-04-2009
			String m_sort_column   = "A.FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			//End by Dineth on 28-04-2009
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			
			if(m_chksql.equals("run_report")){ 
				
			    //String m_to_date=req.getParameter("to_date");
				//String m_from_date=req.getParameter("from_date");
				
				String m_branch_code=req.getParameter("branch_code");
				
				//String m_branch
				
				/*if(req.getParameter("branch_code")!=null ){
					m_branch_code=req.getParameter("branch_code").trim();
				}
				else{
				m_branch_code=" ";
				}*/
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_ABANDOND_CONTRACT_PRO(:1,:2);END;");
					//callstmt1.setString(1,m_to_date);
					//callstmt1.setString(2,m_from_date);
					callstmt1.setString(1,m_branch_code);
					callstmt1.setString(2,m_username);
					callstmt1.execute();
					out.print("OK"); 
					
					/*callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_ABANDOND_CONTRACT_PRO(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_to_date);
					callstmt1.setString(2,m_from_date);
					callstmt1.setString(3,m_branch_code);
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					out.print("OK");*/ 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("main_page")){
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Abandoned Contracts</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				
				out.println("function run_report() {");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				//	out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			//	out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				//out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				//out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				//out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				//out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				//out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				//out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=run_report&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=run_report&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value;"); 
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=run_report&to_date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				//out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			view_details();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				//out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				//out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				//out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				//out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				//out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				//out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
				out.println("		}");
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
				
				
				out.println("function validate_date(){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
				out.println("    if(checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
				out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
				out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
				out.println("      	  return true;"); 
				out.println("  	  	 }");
				out.println("        else{ "); 
				out.println("         return false; "); 
				out.println("     }");
				out.println("     }");
				out.println("			else {");
				out.println("   		alert('To Date cannot be empty ');");
				out.println("   		return false;"); 
				out.println("     }");
				out.println("     }");
				out.println("        else {"); 
				out.println("         return false; "); 
				out.println("     }");
				
				out.println("     }");
				out.println("			else {");
				out.println("   		alert('From Date cannot be empty ');");
				out.println("   		return false;"); 
				out.println("     }");
				out.println("    }");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				//out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				//out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				//out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				//out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				//out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				//out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				//out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				//out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=view_report_det&location=\"+m_location+\"&date=\"+m_date+\"&from_date=\"+m_from_date;");	 // mod by udara on 18-06-2013
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=view_report_det&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value;"); 
				
				out.println("			window.open(m_url);");
				//out.println("	}");
				out.println("}");
				
				out.println("function view_details() {");
				//out.println("alert('sd');");
				//out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				//out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				//out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				//out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				//out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				//out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				//out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				//out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				//out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
				out.println("		if(validate_date()) {");
				//Modified by Dineth on 28-04-2009
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=view_report_det&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=view_report_det&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
				
				//out.println("	    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=view_report_det&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch_code=\"+m_branch_code ;");
				
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
				//End by Dineth on 28-04-2009
				//out.println(" alert(m_url);");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
				out.println("   }");
				
				
				/*out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				*/
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=main_page';"); 
				out.println("}"); 
				
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" Collection Process - Abandoned Contracts - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Collection Process - Abandoned Contracts \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				out.println("}");
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");//m_help_TXT_LOCATION_CODE_sql
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		team_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_user(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
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
				out.println("	clear_data(IfCount);");//Added To The Clear 
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
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection Process - Abandoned Contracts </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'></td>");
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
				
				
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				/*out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date *</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("</tr>");*/
				
				
				/*out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date *</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\">&nbsp;&nbsp;<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"print_report2()\">");	
				out.println("</td> ");
				out.println("</tr>");*/
				
				
				out.println("<tr></tr>");
				
				out.println("<tr >"); 
				out.println("<td width='10%' ></td>"); 
				out.println(" <td>");
				//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\">&nbsp;&nbsp;<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"print_report2()\">");	
				out.println("</td> ");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>"); 
				
				out.println("<br>"); 
				out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			
			
			else if(m_chksql.equals("branch_drill")){		
				
				String m_branch_code="";
				String m_to_date="";
				String m_from_date="";
				String m_report_type="";
				
				
				if(req.getParameter("branch")!=null ){
					m_branch_code=req.getParameter("branch").trim();
				}
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				
				
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rentals Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u>Branch Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				String Sql_data="";
				boolean more;

				//out.println(m_branch_code+"m_branch_code");
				    Sql_data=" SELECT A.APPLICATION_NO , "+
							 "  A.TOTAL_FINANCE_AMOUNT  "+
					         "  FROM "+m_schema_name+".AF_RPT_ABANDOND_CONTRACT_MAIN A "+
							 "  WHERE "+
							 //" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') >= '"+m_from_date+"'   "+
					         // " AND TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') <= '"+m_to_date+"'  AND  "+
							 " A.BRANCH_CODE LIKE '"+m_branch_code+"%'  " ;
					
					
				
					
				
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
				int count=1;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr> ");
				out.println("<td width='10%' class=div_input align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='40%' class=div_input align='center' bgcolor='lightblue' ><B> Contract </B></td>");
				out.println("<td width='50%' class=div_input align='center' bgcolor='lightblue' ><B> Contract Amount</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					
					out.println("<tr>");
				    out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+count+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+rs.getString(1)+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(2))+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					count++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			else if(m_chksql.equals("view_report_det")){		
				//out.println("dssfsdfsdfdsf");
				
				String m_from_date="";
				String m_to_date="";
				Double total_lending=0.00;
				int total_no_contracts=0;
				String m_branch_code="";
				
				
				if(req.getParameter("branch_code")!=null ){
					m_branch_code=req.getParameter("branch_code").trim();
				}
				
				
				if(req.getParameter("from_date")!=null ){
					
					
					m_from_date=req.getParameter("from_date").trim();
					//out.println(m_from_date+"m_from_date");
				}
				
				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{m_sort_column = "FINANCE_NO";	 m_order_by_type = "ASC";}
				
			
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Abandoned Contracts Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function Branch_drill(val1,val2,val3){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Abandoned_Contracts?chksql=branch_drill&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3;");
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_application_drill(m_application_no){");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_five?chksql=SHOW_APPLICATION_DETAIL_DRILL&application_no='+m_application_no+'';"); 
				out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
				out.println("}");
				
				
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
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
			    out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			    out.println("    window.open(m_url); ");
			    out.println(" }");
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				
				
				
				//End by Dineth on 2008-11-17
				
				
				
			
				
				rs=stmt.executeQuery(" SELECT "+
					//" A.APPLICATION_NO,  "+ // commented by udara 26-08-2014
					" A.FINANCE_NO, "+ // added by udara 26-08-2014
									 " NVL(A.ENT_USER,'-'), "+
					                 " NVL(A.REMARKS,'-'), "+
										" A.CLIENT_CODE, "+ 
										" TO_CHAR(A.ABANDON_DATE,'DD-MM-YYYY') "+
					                 " FROM "+m_schema_name+".AF_RPT_ABANDOND_CONTRACT A "+
									 " WHERE A.FINANCE_NO IS NOT NULL   "+
									 //" WHERE  "+
									 //" A.ENT_USER = '"+m_username+"'  " );
										"  ORDER BY A.ABANDON_DATE "+
				                     " " );
				
				boolean more=rs.next();
				//more=rs.next();
				int count=1;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD </u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>ABANDOND CONTRACTS </u></td>");   // from 01-01-2012 to 10-07-2013 
				out.println("</tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				//out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				//else{
				
				out.println("<table id=mytable align=\"left\" width=\"95%\" border=\"1\" class=\"table\"  cellspacing=0 > "); //bordercolor='black' -1220
				///out.println("4444444444444444444444444444444444");
				/*out.println("<tr bgcolor=\"#CCCCCC\"  >");
				out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Seq No</td>"); //1
				out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Branch name</td>");  //2
				out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Total number of contracts</td>");  //3
				out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Total lending</td>");  //4*/
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\" height=30px  >");
				out.println("<td width=\"12%\" STYLE='{text-align:center;}' ><b>Seq No</b></td>"); 			
				out.println("<td STYLE='{text-align:center;}' ><b>Contract</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Abandonded User</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Abandonded Comment</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Abandonded Date</b></td>"); 
				//out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				/*out.println("<td STYLE='{text-align:center;}' ><b>Closed Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Matured Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Rentals Total</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>");*/ 	
				out.println("</tr >");
				
				
				//=================================================
				//}
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				int j=1;
				//out.println("33333333333333333333333334");
				
				while(more){
					
					out.println("<tr>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+count+" </td>"); 			
					//out.println("<td STYLE='cursor:hand; {text-align:left;}' onclick=\"show_application_drill('"+rs.getString(1)+"');\" >"+rs.getString(1)+"   </td>"); 
					out.println("<td STYLE='cursor:hand; {text-align:left;}' onclick=\"show_transaction_info('"+rs.getString(4)+"','"+rs.getString(1)+"');\" >"+rs.getString(1)+"   </td>");  
					out.println("<td STYLE='{text-align:left;}' > "+rs.getString(2)+" </td>"); 
					//out.println("<td STYLE='{text-align:right;}' > "+rs.getString(1)+" </td>"); 
					//out.println("<td STYLE='{text-align:right;}' > "+rs.getString(2)+" </td>"); 
					out.println("<td STYLE='{text-align:left;}' > "+rs.getString(3)+" </td>"); 
					out.println("<td STYLE='{text-align:left;}' > "+rs.getString(5)+" </td>"); 
					/*out.println("<td STYLE='{text-align:right;}' > "+rs.getString(6)+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(7))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+rs.getString(8)+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(9))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(10))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+rs.getString(11)+" </td>"); */
					out.println("</tr>");
					
					
					//total_no_contracts+=rs.getDouble(2);
					//total_lending+=rs.getDouble(3);
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					//out.println("<tr>");		
					//out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'    ></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'    ><b>Total</td>"); 
					
					//out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+total_no_contracts+"</td>"); 
					//out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf.format(total_lending)+"</td>"); 
					
					
					//out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
				out.println("</table>");		
				out.println("</td>"); 
				out.println("</tr>");		
				out.println("</table>");		 
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}	
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
			if(out!=null){
				try{out.close();  
				}catch(Exception e){}
			}
		}
	}
}