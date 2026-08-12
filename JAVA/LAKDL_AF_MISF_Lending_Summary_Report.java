// DEVELOP BY : Ishani 2013.07.11
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Lending_Summary_Report extends javax.servlet.http.HttpServlet { 
	/*
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt;
	CallableStatement callstmt1 =null;
	
	java.text.NumberFormat nf;
	public ResultSet rs1,rs;
	*/
	
	
	//public String m_chksql;
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn = null;
		Statement stmt =null;
		Statement stmt1 =null;
		Statement stmt3=null;
		CallableStatement callstmt1 =null;
		
		java.text.NumberFormat nf= null;
		ResultSet rs1 = null,rs3 = null;
		ResultSet rs = null;
		String m_chksql = null;
		
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
			ResultSet resultSet = null;
			Statement statement = null;
			
			if(m_chksql.equals("run_report")){ 
				
				String m_to_date=req.getParameter("to_date");
				String m_from_date=req.getParameter("from_date");
				
				String m_branch_code=req.getParameter("branch_code");
				
				String m_item_category=req.getParameter("item_category"); // added by udara 17-04-2017
				String m_item_sub_category=req.getParameter("item_sub_category"); // added by udara 17-04-2017
				
				//String m_branch
				
				/*if(req.getParameter("branch_code")!=null ){
					m_branch_code=req.getParameter("branch_code").trim();
				}
				else{
				m_branch_code=" ";
				}*/
				
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_LENDING_SUMMARY(:1,:2,:3,:4);END;"); // commented by udara 17-04-2017
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_LENDING_SUMMARY(:1,:2,:3,:4,:5,:6);END;"); // added by udara 17-04-2017
					callstmt1.setString(1,m_to_date);
					callstmt1.setString(2,m_from_date);
					callstmt1.setString(3,m_branch_code);
					callstmt1.setString(4,m_username);
					
					callstmt1.setString(5,m_item_category); // added by udara 17-04-2017
					callstmt1.setString(6,m_item_sub_category); // added by udara 17-04-2017
					
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("view_report")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Lending Summary</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				
				out.println("function run_report() {");
				//out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.TXT_LOCATION_CODE.value !=\"\" && document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\" ){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				
				out.println("   m_item_category     =  document.Form1.TXT_ITEM_CATEGORY.value;  "); // added by udara 17-04-2017
				out.println("   m_item_sub_category =  document.Form1.TXT_ITEM_SUB_CATEGORY.value;  "); // added by udara 17-04-2017
				
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=run_report&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); // commented by udara 17-04-2017
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=run_report&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&item_category=\"+m_item_category+\"&item_sub_category=\"+m_item_sub_category;"); // added by udara 17-04-2017
				
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
				out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
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
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10(#16240)
				
				out.println("   m_item_cat     = document.Form1.TXT_ITEM_CATEGORY.value; "); // added by udara 05-05-2017
				out.println("   m_item_sub_cat = document.Form1.TXT_ITEM_SUB_CATEGORY.value; "); // added by udara 05-05-2017
				
				//out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Rental_Summary_Report?chksql=view_report_det&location=\"+m_location+\"&date=\"+m_date+\"&from_date=\"+m_from_date;");	 // mod by udara on 18-06-2013
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=view_report_det&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&region=\"+m_region;"); // commented by udara 05-05-2017
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=view_report_det&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&region=\"+m_region+\"&item_cat=\"+m_item_cat+\"&item_sub_cat=\"+m_item_sub_cat;"); // added by udara 05-05-2017
				
				out.println("			window.open(m_url);");
				//out.println("	}");
				out.println("}");
				
				out.println("function view_details() {");
				//out.println("alert('sd');");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				//out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
				out.println("		if(validate_date()) {");
				//Modified by Dineth on 28-04-2009
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=view_report_det&to_date=\"+m_to_date+\"&branch_code=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date;"); 
				//out.println("	    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=view_report_det&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch_code=\"+m_branch_code ;");
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Collection Process - Lending Summary - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Collection Process - Lending Summary \";"); 
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
				
				// added by udara 17-04-2017
				out.println("       if(IfCount==\"66\"){");
				out.println("document.Form1.TXT_ITEM_CATEGORY.value='';");
				out.println("		}");
				out.println("       if(IfCount==\"67\"){");
				out.println("document.Form1.TXT_ITEM_SUB_CATEGORY.value='';");
				out.println("		}");
				// end by udara 17-04-2017
				
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
				
				// added by udara 17-04-2017
				out.println("		if(IfCount==\"66\"){"); 
				out.println("		help_update_value_assign_66(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"67\"){"); 
				out.println("		help_update_value_assign_67(oBj);"); 
				out.println("		}");
				// end by udara 17-04-2017
				
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
				
				
				// added by udara 17-04-2017
				out.println("function item_category_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"66\";"); 
				out.println("    Crit = document.Form1.TXT_ITEM_CATEGORY.value+\"@\"+document.Form1.TXT_ITEM_SUB_CATEGORY.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_item_category_help','66');"); 
				out.println("}"); 
				
				out.println("function item_sub_category_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"67\";"); 
				out.println("    Crit = document.Form1.TXT_ITEM_CATEGORY.value+\"@\"+document.Form1.TXT_ITEM_SUB_CATEGORY.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_item_sub_category_help','67');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_66() {"); 
				out.println("    document.Form1.TXT_ITEM_CATEGORY.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_update_value_assign_67() {"); 
				out.println("    document.Form1.TXT_ITEM_SUB_CATEGORY.value=oBj.valout[2];"); 
				out.println("}");
				// end by udara 17-04-2017
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection Process - Lending Summary </td>"); 
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
				
				
				// Added By: Samith dilshan  On : 2015-06-03
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
				
				
				
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date *</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("</tr>");
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date *</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\">&nbsp;&nbsp;<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"print_report2()\">");	
				out.println("</td> ");
				out.println("</tr>");
				
				
				// added by udara 17-04-2017
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_ITEM_CATEGORY'  class=div_input>Item Category</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='TXT_ITEM_CATEGORY' maxlength='50' size='10' onblur=\"\" >");  //onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\"
				out.println("<input class='but_input' type='button' name='BUT_TXT_ITEM_CATEGORY' value=\"Help\" onClick=\"item_category_help()\"></td>"); 
				out.println("</tr>");	
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_ITEM_SUB_CATEGORY'  class=div_input>Item Sub Category</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='TXT_ITEM_SUB_CATEGORY' maxlength='50' size='10' onblur=\"\" >");  //onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\"
				out.println("<input class='but_input' type='button' name='BUT_TXT_ITEM_SUB_CATEGORY' value=\"Help\" onClick=\"item_sub_category_help()\"></td>"); 
				out.println("</tr>");
				// end by udara 17-04-2017
				
				
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
				
				// added by udara 05-05-2017
				String m_item_cat = "";
				String m_item_sub_cat = "";
				String m_item_cat_sql = "";
				String m_item_sub_cat_sql = "";
				
				if(req.getParameter("item_cat")!=null ){
					m_item_cat=req.getParameter("item_cat").trim();
				}
				
				//if(req.getParameter("item_sub_cat")!=null ){
				if(req.getParameter("sub_item_cat")!=null ){
					//m_item_sub_cat=req.getParameter("item_sub_cat").trim();
					m_item_sub_cat=req.getParameter("sub_item_cat").trim();
				}
				
				if(m_item_cat.equals("")){
					m_item_cat_sql = " ";
				}
				else{
					m_item_cat_sql = " AND A.ITEM_CATEGORY = '"+m_item_cat+"'  ";
				}
				
				if(m_item_sub_cat.equals("")){
					m_item_sub_cat_sql = " ";
				}
				else{
					m_item_sub_cat_sql = " AND A.ITEM_SUB_CATEGORY = '"+m_item_sub_cat+"'  ";
				}
				// end by udara 05-05-2017
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rentals Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url); ");
				out.println(" }");
				
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
				
				// commented by udara 05-05-2017
				/*
				    Sql_data=" SELECT A.FINANCE_NO , "+
							 "  A.TOTAL_FINANCE_AMOUNT,  "+
							 "  NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))),'-') CATEGORY"+	
					         "  ,"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) CLIENT_CODE "+
							 "  FROM "+m_schema_name+".AF_TBD_RPT_LENDN_SUMMARY_MAIN A "+
							 //" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') >= '"+m_from_date+"'   "+
					         // " AND TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') <= '"+m_to_date+"'  AND  "+
							 "  WHERE A.BRANCH_CODE LIKE '"+m_branch_code+"%' "+
							 "  AND   A.ENT_USER = '"+m_username+"' "+
							 "  AND   A.TOTAL_FINANCE_AMOUNT > 0  "+ // added by udara 31-10-2014
							 "  ORDER BY CATEGORY" ;
					*/
				
				// added by udara 05-05-2017
				
				Sql_data=" SELECT A.FINANCE_NO , "+
					"  A.TOTAL_FINANCE_AMOUNT,  "+
					"  NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))),'-') CATEGORY"+	
					"  ,"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) CLIENT_CODE "+
					"  FROM "+m_schema_name+".AF_TBD_RPT_LENDN_SUMMARY_MAIN A "+
					"  WHERE A.BRANCH_CODE LIKE '"+m_branch_code+"%' "+
					"  AND   A.ENT_USER = '"+m_username+"' "+
					"  AND   A.TOTAL_FINANCE_AMOUNT > 0  ";
				
				Sql_data = Sql_data + m_item_cat_sql + m_item_sub_cat_sql;
				
				Sql_data = Sql_data + "  ORDER BY CATEGORY" ;
				
				
				// end by udara 05-05-2017	
				
				//out.println(Sql_data); // test test
				
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
				out.println("<td width='30%' class=div_input align='center' bgcolor='lightblue' ><B> Contract Amount</B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Lead Source Category</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					
					out.println("<tr>");
					out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+count+"</td>"); 
					//out.println("<td class='factoring-letter-body' STYLE='text-align:center; cursor:hand;' bgcolor='"+m_td_color+"' onclick='show_transaction_info('"+rs.getString(4)+"','"+rs.getString(1)+"')' ><u>"+rs.getString(1)+"</u></td>"); 
					
					out.println("<td class=factoring-letter-body align='left' class=div_input style='cursor:hand' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('" + rs.getString(4) + "','" + rs.getString(1) + "')\" ><u> " + rs.getString(1) + " </u></td>");
					
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(2))+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' >"+rs.getString(3)+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					count++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// added by udara 02-01-2016
			else if(m_chksql.equals("branch_drill_total")){		
				
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
				
				// added by udara 05-05-2017
				String m_item_cat = "";
				String m_item_sub_cat = "";
				String m_item_cat_sql = "";
				String m_item_sub_cat_sql = "";
				
				if(req.getParameter("item_cat")!=null ){
					m_item_cat=req.getParameter("item_cat").trim();
				}
				
				//if(req.getParameter("item_sub_cat")!=null ){
				if(req.getParameter("sub_item_cat")!=null ){
					m_item_sub_cat=req.getParameter("sub_item_cat").trim();
				}
				
				if(m_item_cat.equals("")){
					m_item_cat_sql = " ";
				}
				else{
					m_item_cat_sql = " AND A.ITEM_CATEGORY = '"+m_item_cat+"'  ";
				}
				
				if(m_item_sub_cat.equals("")){
					m_item_sub_cat_sql = " ";
				}
				else{
					m_item_sub_cat_sql = " AND A.ITEM_SUB_CATEGORY = '"+m_item_sub_cat+"'  ";
				}
				// end by udara 05-05-2017
				
				
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Rentals Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//add functions here	
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url); ");
				out.println(" }");
				
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
				
				
				// commented by udara 05-05-2017
				/*
				    Sql_data=" SELECT A.FINANCE_NO , "+
							 "  A.TOTAL_FINANCE_AMOUNT,  "+
							 "  NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))),'-') CATEGORY"+	
					         " ,"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) CLIENT_CODE "+
							 "  FROM "+m_schema_name+".AF_TBD_RPT_LENDN_SUMMARY_MAIN A "+
							 "  WHERE "+
							 "  A.ENT_USER = '"+m_username+"' "+
							 "  AND A.TOTAL_FINANCE_AMOUNT > 0  "+ // added by udara 31-10-2014
							 "  ORDER BY CATEGORY" ;
					*/
				
				// added by udara 05-05-2017
				Sql_data=" SELECT A.FINANCE_NO , "+
					"  A.TOTAL_FINANCE_AMOUNT,  "+
					"  NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO))),'-') CATEGORY"+	
					" ,"+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) CLIENT_CODE "+
					"  FROM "+m_schema_name+".AF_TBD_RPT_LENDN_SUMMARY_MAIN A "+
					"  WHERE "+
					"  A.ENT_USER = '"+m_username+"' "+
					"  AND A.TOTAL_FINANCE_AMOUNT > 0  ";
				
				Sql_data = Sql_data + m_item_cat_sql + m_item_sub_cat_sql;
				
				// added by udara 31-10-2014
				Sql_data = Sql_data	+ "  ORDER BY CATEGORY" ;
				// end by udara 05-05-2017
				
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
				out.println("<td width='30%' class=div_input align='center' bgcolor='lightblue' ><B> Contract Amount</B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Lead Source Category</B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					
					out.println("<tr>");
					out.println("<td class='factoring-letter-body' STYLE='text-align:center;' bgcolor='"+m_td_color+"' >"+count+"</td>"); 
					//out.println("<td class='factoring-letter-body' STYLE='text-align:center; cursor:hand;' bgcolor='"+m_td_color+"' onclick='show_transaction_info('"+rs.getString(4)+"','"+rs.getString(1)+"')' ><u>"+rs.getString(1)+"</u></td>"); 
					
					out.println("<td class=factoring-letter-body align='left' class=div_input style='cursor:hand' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('" + rs.getString(4) + "','" + rs.getString(1) + "')\" ><u> " + rs.getString(1) + " </u></td>");
					
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' >"+nf.format(rs.getDouble(2))+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' >"+rs.getString(3)+"</td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					count++;
					more=rs.next();
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' > &nbsp; </td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			// end by udara 02-01-2016
			
			else if(m_chksql.equals("view_report_det")){		
				//out.println("dssfsdfsdfdsf");
				
				String m_from_date="";
				String m_to_date="";
				Double total_lending=0.00;
				int total_no_contracts=0;
				String m_branch_code="";
				
				String m_region="";
				
				double actual_refin_total = 0; // added by udara 05-08-2015
				
				
				// Added By Samith Dilshan on 2015-06-08
				if(req.getParameter("region")!=null ){
					m_region = req.getParameter("region").trim();
				}
				
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
				
				
				// added by udara 05-05-2017
				String m_item_cat = "";
				String m_item_sub_cat = "";
				
				if(req.getParameter("item_cat")!=null ){
					m_item_cat=req.getParameter("item_cat").trim();
				}
				
				if(req.getParameter("item_sub_cat")!=null ){
					m_item_sub_cat=req.getParameter("item_sub_cat").trim();
				}
				// end by udara 05-05-2017
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{m_sort_column = "FINANCE_NO";	 m_order_by_type = "ASC";}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Lending Summary Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function Branch_drill(val1,val2,val3){ ");
				out.println(" m_item_cat = '"+m_item_cat+"'; "); // added by udara 05-05-2017
				out.println(" m_sub_item_cat = '"+m_item_sub_cat+"'; "); // added by udara 05-05-2017
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=branch_drill&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3;"); // commented by udara 05-05-2017
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=branch_drill&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3+\"&item_cat=\"+m_item_cat+\"&sub_item_cat=\"+m_sub_item_cat;"); // added by udara 05-05-2017
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara 02-01-2017
				out.println("function Branch_drill_total(val1,val2,val3){ ");
				out.println(" m_item_cat = '"+m_item_cat+"'; "); // added by udara 05-05-2017
				out.println(" m_sub_item_cat = '"+m_item_sub_cat+"'; "); // added by udara 05-05-2017
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=branch_drill_total&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3;"); // commented by udara 05-05-2017
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Lending_Summary_Report?chksql=branch_drill_total&branch=\"+val1+\"&from_date=\"+val2+\"&to_date=\"+val3+\"&item_cat=\"+m_item_cat+\"&sub_item_cat=\"+m_sub_item_cat;"); // added by udara 05-05-2017
				out.println("window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				// end by udara 02-01-2017
				
				
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
				
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				
				
				
				//End by Dineth on 2008-11-17
				
				
				
				// commented by udara 07-08-2015
				/*
				rs=stmt.executeQuery(" SELECT  A.BRANCH_CODE,  "+
									 " A.COUNT_APPA_NO, "+
					                 " A.SUM_AMOUNT, "+
									 " "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE), "+
									 " NVL(A.RE_FIN_AMOUNT,0)  "+ // 5 added by udara 05-08-2015
					                 " FROM "+m_schema_name+".AF_TBD_RPT_LENDING_SUMMARY A "+
									 " WHERE  "+
									 " A.ENT_USER = '"+m_username+"'"+
										" ORDER BY "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE) "+ // udara 24-06-2015
										" " );
				*/
				
				// added by udara 07-08-2015
				
				String outer_query = "";
				
				outer_query = " SELECT  A.BRANCH_CODE,  "+
					" A.COUNT_APPA_NO, "+
					" A.SUM_AMOUNT, "+
					" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE), "+
					" NVL(A.RE_FIN_AMOUNT,0)  "+ 
					" FROM "+m_schema_name+".AF_TBD_RPT_LENDING_SUMMARY A "+
					" WHERE  "+
					" A.ENT_USER = '"+m_username+"' ";
				
				if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
					outer_query = outer_query +"    AND   "+m_schema_name+".AF_CO_GET_USER_REGION(A.BRANCH_CODE) = '"+m_region+"' ";    
				}		
				
				outer_query = outer_query + " ORDER BY "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE) "; 
				
				
				rs=stmt.executeQuery(outer_query);						
				
				// end ny udara 07-08-2015
				
				
				
				boolean more=rs.next();
				//more=rs.next();
				int count=1;
				
				
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>LAKDERANA INVESTMENTS LTD </u></td>"); 
				out.println("</tr >");
				out.println("<tr>");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>SUMMARY  OF LENDINGS FROM "+m_from_date+" TO "+m_to_date+"</u></td>");   // from 01-01-2012 to 10-07-2013 
				out.println("</tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>"+m_location_desc+"</u></td>"); 
				//out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				stmt3 = conn.createStatement();
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: 9pt arial; text-align:laft;}'   >Region : "+rs3.getString(1)+"</td>"); 
						out.println("</tr >");
						out.println("</table >");
					}
				}
				
				out.println("<br>");
				
				// ADDED BY SAJITH MENDIS ON 03-06-2014
				ArrayList arrayListLocationCodes = new ArrayList();
				
				ArrayList arrayListNum = new ArrayList();
				ArrayList arrayListAmount = new ArrayList();
				
				String sql = " " +
					//"  SELECT DECODE(NAME,'Re-Finance','Actual RF Lending',NAME) NAME FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT WHERE ACTIVE_STATUS ='Y' ORDER BY SOURCE_CODE ASC "+
					"  SELECT NAME FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT WHERE ACTIVE_STATUS ='Y' ORDER BY SOURCE_CODE ASC "+
					" ";
				
				statement = conn.createStatement();
				resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					arrayListLocationCodes.add(resultSet.getString("NAME"));
				}
				
				resultSet =  null;
				statement =  null;
				
				
				
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
				out.println("<td STYLE='{text-align:center;}' ><b>Branch Name</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Total Number of Contracts</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Total Lending</b></td>");
				
				int x = 0;
				String bgcolor1 = "Grey";
				String bgcolor2 = "Blue";
				String color = "";
				
				// ADDED BY SAJITH MENDIS ON 03-06-2014
				for (int index = 0; index < arrayListLocationCodes.size(); index++) {
					if(x == 0){
						color = bgcolor1;
					}
					if(x == 1){
						color = bgcolor2;
					}
					out.println("<td STYLE='{text-align:center; color:"+color+";}' ><b>" + arrayListLocationCodes.get(index) + "</b></td>");
					out.println("<td STYLE='{text-align:center; color:"+color+";}'><b>Lending</b></td>");
					out.println("<td STYLE='{text-align:center; color:"+color+";}'><b> % </b></td>");
					
					
					
					x++;
					if(x==2){
						x=0;
					}
					
				}
				//out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				/*out.println("<td STYLE='{text-align:center;}' ><b>Closed Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Matured Rentals</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>Rentals Total</b></td>"); 	
				out.println("<td STYLE='{text-align:center;}' ><b>No of Cases</b></td>");*/ 
				
				
				// added by udara 10-11-2014
				
				out.println("<td STYLE='{text-align:center; color:"+color+";}' ><b> Actual RF Lending </b></td>");
				/*
				out.println("<td STYLE='{text-align:center; color:"+color+";}'><b>Lending</b></td>");
				out.println("<td STYLE='{text-align:center; color:"+color+";}'><b> % </b></td>");
				*/
				// end by udara 10-11-2014
				
				
				out.println("</tr >");
				
				
				//=================================================
				//}
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				int j=1;
				//out.println("33333333333333333333333334");
				
				// ADDED BY SAJITH MENDIS ON 03-06-2014
				
				double temp_tot_amount = 0;
				
				while(more){
					
					temp_tot_amount = 0;
					temp_tot_amount = rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width=\"12%\" STYLE='{text-align:left;}' > "+count+" </td>"); 			
					out.println("<td STYLE='cursor:hand; {text-align:left;cursor:hand;}' onclick=\"Branch_drill('"+rs.getString(1)+"','"+m_from_date+"','"+m_to_date+"');\" ><u>"+rs.getString(4)+"  </u> </td>"); 
					out.println("<td STYLE='cursor:hand; {text-align:right;cursor:hand;}' onclick=\"Branch_drill('"+rs.getString(1)+"','"+m_from_date+"','"+m_to_date+"');\" ><u> "+rs.getString(2)+" </u></td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(3))+" </td>"); 
					
					String m_inner_query = 	" SELECT SOURCE_CODE,SUM(MCOUNT),SUM(MTOTAL) "+
						" FROM (  SELECT "+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT(A.APPLICATION_NO) SOURCE_CODE,COUNT("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT(A.APPLICATION_NO)) MCOUNT, "+
						"                SUM(A.TOTAL_FINANCE_AMOUNT) MTOTAL "+
						"        FROM "+m_schema_name+".AF_TBD_RPT_LENDN_SUMMARY_MAIN A "+
						"         WHERE BRANCH_CODE = '"+rs.getString(1)+"' "+
						"		  AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI', 'TERMINATED', 'LEGAL','NORM_TERMI') "+
						"		  AND ENT_USER = '"+m_username+"' ";
					
					
					if(!(m_region.equals("NOT_SELECT"))){                 // Added By: Samith Dilshan on 2015-06-08 for Region Code
						m_inner_query = m_inner_query +"    AND   A.REGIONS_CODE = '"+m_region+"' ";    
					}			
					
					
					m_inner_query = m_inner_query +"         AND A.TOTAL_FINANCE_AMOUNT > 0 "+ // added by udara 31-10-2014
						"         GROUP BY "+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT(A.APPLICATION_NO) "+
						"          "+
						"         UNION  "+
						"          "+
						"         SELECT B.SOURCE_CODE SOURCE_CODE,0 MCOUNT,0 MTOTAL "+
						"         FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT B "+
						"         WHERE B.ACTIVE_STATUS ='Y' "+
						" ) "+
						" GROUP BY SOURCE_CODE "+
						" ORDER BY SOURCE_CODE ASC ";
					
					
					//out.println(m_inner_query);
					
					statement = conn.createStatement();
					resultSet = statement.executeQuery(m_inner_query);
					
					
					
					while (resultSet.next()) {
						if(resultSet.getString(1) != null){
							out.println("<td STYLE='{text-align:right;}' >"+ resultSet.getString(2)+"</td>");
							out.println("<td STYLE='{text-align:right;}' >"+ nf.format(resultSet.getInt(3))+"</td>");
							
							if(temp_tot_amount!=0){
								out.println("<td STYLE='{text-align:right;}' > "+ nf.format(  ((resultSet.getDouble(3) / temp_tot_amount)*100)  )+" </td>");
							}
							else{
								out.println("<td STYLE='{text-align:right;}' > "+ nf.format(0)+" </td>");
							}
							
							arrayListNum.add(resultSet.getString(2));
							arrayListAmount.add(resultSet.getString(3));
							
							
							
						}
					}
					
					// added by udara 10-11-2014
					/*
					out.println("<td STYLE='{text-align:right;}' > &nbsp; </td>");
					out.println("<td STYLE='{text-align:right;}' >"+ nf.format(0)+"</td>");
					*/
					out.println("<td STYLE='{text-align:right;}' > "+ nf.format(rs.getDouble(5))+" </td>"); // added by udara 05-08-2015
					
					// end by udara 10-11-2014	
					
					
					/*out.println("<td STYLE='{text-align:right;}' > "+rs.getString(6)+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(7))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+rs.getString(8)+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(9))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+nf.format(rs.getDouble(10))+" </td>"); 
					out.println("<td STYLE='{text-align:right;}' > "+rs.getString(11)+" </td>"); */
					out.println("</tr>");
					
					
					total_no_contracts+=rs.getDouble(2);
					total_lending+=rs.getDouble(3);
					
					actual_refin_total += rs.getDouble(5); // added by udara 05-08-2015
					
					more=rs.next();
					count+=1;
					j+=1;
					
				}
				
				//total============================
				
				if(count>0){
					out.println("<tr>");		
					out.println("<input type='hidden' name=no_of_records value="+j+" >"); 
					
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'    ></td>"); 
					
					//out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'    ><b>Total</td>"); 
					out.println("<td STYLE='cursor:hand; {text-align:left;cursor:hand;}' onclick=\"Branch_drill_total('-','"+m_from_date+"','"+m_to_date+"');\" ><b><u> Total </u></b></td>"); 
					
					//out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+total_no_contracts+"</td>"); // commented by udara 05-01-2017
					out.println("<td STYLE='cursor:hand; {text-align:right;cursor:hand;}' onclick=\"Branch_drill_total('-','"+m_from_date+"','"+m_to_date+"');\" ><b><u> "+total_no_contracts+" </u></b></td>");  // added by udara 05-01-2017
					
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf.format(total_lending)+"</td>"); 
					
					/*
					for (int index = 0; index < arrayListLocationCodes.size(); index++) {  // arrayListNum
						out.println("<td class=factoring-letter-body STYLE='{text-align:center;}'  ><b>"+ arrayListNum.get(index) +"</td>"); 
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+ arrayListAmount.get(index) +"</td>"); 
                		out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+ nf.format(Double.parseDouble(String.valueOf(arrayListAmount.get(index)))) +"</td>"); 
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b> &nbsp; </td>");
					}
					*/
					
					ArrayList arrayListCount1 = new ArrayList();
					ArrayList arrayListSum1 = new ArrayList();
					
					double array_count[][] = new double[(arrayListNum.size() / arrayListLocationCodes.size())][arrayListLocationCodes.size()];
					double array_sum[][]   = new double[(arrayListNum.size() / arrayListLocationCodes.size())][arrayListLocationCodes.size()];
					
					
					int counts = 0;
					int a = 0;
					int b = 0;
					for (int index = 0; index < (arrayListNum.size() / arrayListLocationCodes.size()); index++) { 
						
						
						b=0;
						
						//out.println("<tr>");
						//out.println("<td> "+index+"  </td>");
						for (int index2 = counts; index2 < (counts + arrayListLocationCodes.size()); index2++) { 
							
							
							//out.println("   <td>");
							array_count[a][b] = Double.parseDouble(String.valueOf(arrayListNum.get(index2)));
							array_sum[a][b] = Double.parseDouble(String.valueOf(arrayListAmount.get(index2))); 
							//out.println("     "+arrayListNum.get(index2)+"  ");
							//out.println("   </td>");
							
							b++;
							
							
						}
						
						counts = counts + arrayListLocationCodes.size();
						//out.println("</tr>");
						
						a++;
						
					}
					
					
					double count_n = 0;
					double sum_n = 0;
					for (int index2 = 0; index2 < arrayListLocationCodes.size(); index2++) { 
						//out.println("<tr>");	
						count_n = 0;
						sum_n = 0;
						
						for (int index = 0; index < (arrayListNum.size() / arrayListLocationCodes.size()); index++) { 	
							//out.println("<td> "+array_count[index][index2]+"  </td>");
							count_n = count_n + array_count[index][index2];
							sum_n = sum_n + array_sum[index][index2];
						}
						
						arrayListCount1.add(String.valueOf(count_n));
						arrayListSum1.add(String.valueOf(sum_n));
						//out.println("</tr>");	
					}
					
					for (int index = 0; index < arrayListLocationCodes.size(); index++) {  // arrayListNum
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+ (int)(Double.parseDouble(String.valueOf(arrayListCount1.get(index)))) +"</td>"); 
						//out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+ arrayListAmount.get(index) +"</td>"); 
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+ nf.format(Double.parseDouble(String.valueOf(arrayListSum1.get(index)))) +"</td>"); 
						out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b> &nbsp; </td>");
					}
					
					
					// added by udara 10-11-2014
					/*
					out.println("<td class=factoring-letter-body  STYLE='{text-align:left;}'  ><b> &nbsp; </td>"); 
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b> &nbsp; </td>"); 
					*/
					out.println("<td class=factoring-letter-body STYLE='{text-align:right;}'  ><b>"+nf.format(actual_refin_total)+"</td>"); // added by udara 05-08-2015
					
					// end by udara 10-11-2014
					
					
					out.println("</tr>");		
					
					
					//precentage=================================================================================================
					
				}
				
				
				/*
				//////////////// TEST //////////////////////////////////////////////////////////
				//out.println("<tr>");
				//out.println("<td>");
				//out.println("<table border=1 >");	

				
				ArrayList arrayListCount1 = new ArrayList();
				ArrayList arrayListSum1 = new ArrayList();
				
				double array_count[][] = new double[(arrayListNum.size() / arrayListLocationCodes.size())][arrayListLocationCodes.size()];
				double array_sum[][]   = new double[(arrayListNum.size() / arrayListLocationCodes.size())][arrayListLocationCodes.size()];
				
				
				int counts = 0;
				int a = 0;
				int b = 0;
				for (int index = 0; index < (arrayListNum.size() / arrayListLocationCodes.size()); index++) { 
					
					
					b=0;
					
					//out.println("<tr>");
					//out.println("<td> "+index+"  </td>");
					for (int index2 = counts; index2 < (counts + arrayListLocationCodes.size()); index2++) { 
						
						
						//out.println("   <td>");
						array_count[a][b] = Double.parseDouble(String.valueOf(arrayListNum.get(index2)));
						array_sum[a][b] = Double.parseDouble(String.valueOf(arrayListAmount.get(index2))); 
						//out.println("     "+arrayListNum.get(index2)+"  ");
						//out.println("   </td>");
						
						b++;
						
						
					}
					
					counts = counts + arrayListLocationCodes.size();
					//out.println("</tr>");
					
					a++;
					
				}
				
				
				double count_n = 0;
				double sum_n = 0;
				for (int index2 = 0; index2 < arrayListLocationCodes.size(); index2++) { 
				//out.println("<tr>");	
					count_n = 0;
					sum_n = 0;
					
					for (int index = 0; index < (arrayListNum.size() / arrayListLocationCodes.size()); index++) { 	
						//out.println("<td> "+array_count[index][index2]+"  </td>");
						count_n = count_n + array_count[index][index2];
						sum_n = sum_n + array_sum[index][index2];
					}
					
					arrayListCount1.add(String.valueOf(count_n));
					arrayListSum1.add(String.valueOf(sum_n));
				//out.println("</tr>");	
				}
				
				
				
				//double count_n = 0;
				//for (int index = 0; index < (arrayListNum.size() / arrayListLocationCodes.size()); index++) { 	
				//	out.println("<tr>");
				//	count_n = 0;
				//	out.println("<td> &nbsp;  </td>");
				//	for (int index2 = 0; index2 < arrayListLocationCodes.size(); index2++) { 
				//		out.println("<td> "+array_count[index][index2]+"  </td>");
				//		count_n = count_n + array_count[index][index2];
				//	}
				//	out.println("</tr>");
				//	arrayListCount1.add(String.valueOf(count_n));
				//}
				
				
				
				
				//out.println("<tr>");
				//out.println("<td> "+arrayListCount1.size()+"  </td>");
				//for (int index = 0; index < arrayListCount1.size(); index++) { 
				//	out.println("<td><b> "+arrayListCount1.get(index)+" </b></td>");
				//}
				//out.println("</tr>");
				
				//out.println("<tr>");
				//out.println("<td> "+arrayListSum1.size()+"  </td>");
				//for (int index = 0; index < arrayListSum1.size(); index++) { 
				//	out.println("<td><b> "+arrayListSum1.get(index)+" </b></td>");
				//}
				//out.println("</tr>");
				
				

				
				//out.println("</table>");
				//out.println("</td>");
				//out.println("</tr>");
				//////////////// TEST //////////////////////////////////////////////////////////
				*/
				
				
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
			
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// added by udara 18-05-2017
			
			
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
