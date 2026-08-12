// DEVELOP BY : SANDUN FOR OFSCL LEASING-CREDIT  
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Confirm_Case_Summary extends javax.servlet.http.HttpServlet { 
	/*
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt;
	CallableStatement callstmt1 =null;

	java.text.NumberFormat nf;
	public ResultSet rs1,rs,rs2,rs3,rs4;

	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn;
		Statement stmt1,stmt2,stmt3,stmt4,stmt;
		stmt1=stmt2=stmt3=stmt4=stmt=null;
		CallableStatement callstmt1 =null;
		
		java.text.NumberFormat nf,nf2; // nf2 added by udara 05-05-2017
		nf=null;
		nf2=null; // added by udara 05-05-2017
		ResultSet rs1,rs,rs2,rs3,rs4;
		rs1=rs=rs2=rs3=rs4=null;
		
		String m_chksql;
		
		
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
			nf2 = java.text.NumberFormat.getInstance(Locale.US); // added by udara 05-05-2017
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);    
			nf2.setMaximumFractionDigits(2); // added by udara 05-05-2017
			res.setStatus(HttpServletResponse.SC_OK); 
			
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			//Added by Dineth on 28-04-2009
			//	String m_sort_column   = "A.FINANCE_NO";
			String m_sort_column   = "FINANCE_NO_SORT";//added by Prabash on 13-06-2012 Support #5336
			
			String m_order_by_type = "ASC";
			
			//End by Dineth on 28-04-2009
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			
			
			if(m_chksql.equals("run_report")){ 
				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_location_id=req.getParameter("location_id"); //added by Prabash on 09-05-2012
				String m_rpt_type=req.getParameter("rpt_type");
				String m_cr_office = req.getParameter("cr_off");//.ADDED MILINDA
				
				String m_region = req.getParameter("region");   // Added By Samith Dilshan on 2015-06-10  
				
				try{
					
					synchronized(this){	
						// commented by udara on 18-07-2013
						/*
						callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY(:1,:2,:3);END;");
						//callstmt1.setString(1,m_from_date);
						callstmt1.setString(1,m_to_date);
						//callstmt1.setString(3,m_rpt_type);
						callstmt1.setString(2,m_username);
						callstmt1.setString(3,m_location_id); //added by prabash on 09-05-2012
						callstmt1.execute();
						out.print("OK"); 
						*/
						
						//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY(:1,:2,:3,:4);END;");//commented by milinda 2013-10-21
						callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY(:1,:2,:3,:4,:5);END;");//added milinda 
						callstmt1.setString(1,m_from_date);
						callstmt1.setString(2,m_to_date);
						callstmt1.setString(3,m_username);
						callstmt1.setString(4,m_location_id); 
						callstmt1.setString(5,m_cr_office);//ADDED MILINDA
						callstmt1.execute();
						out.print("OK"); 
						
						
					}
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Confirm Case Summary</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				//Added by Kanchana for issue 19307
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				//pra-----------------**
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				// added by udara 12-04-2017
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
				// end by udara 12-04-2017
				
				
				//credit  officer help
				out.println("function mk_officer_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				//out.println("   Sql = \"MKOfficerSqlNew\";");//MKOfficerSqlNew Modified By Sandun 25-08-2008
				//out.println("   Crit=document.Form1.MKT_OFFICER.value+\"@AF@\";");
				out.println("    Crit = document.Form1.MKT_OFFICER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_credi_officer','5');"); 
				out.println("}"); 
				//--------------------**

				out.println("function run_report() {");				
				out.println("	if(validate_date()){");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("		m_rpt_type='';");
				out.println("cr_off = document.Form1.MKT_OFFICER.value;");//added milinda
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10 (#16240)
				
				//	out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=run_report&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");  //comment by Prabash on 09-05-2012---
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=run_report&rpt_type=\"+m_rpt_type+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");  // added by Prabash on 09-05-2012--- insert location \ //commented by milinda
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=run_report&rpt_type=\"+m_rpt_type+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&region=\"+m_region+\"&cr_off=\"+cr_off;");  // added by milinda 2013-10-+21
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");				
				out.println("	}");
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
				
				
				/*out.println("		 if(data_vec.length==0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("        mk_officer_help(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.MKT_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_MKT_OFFC' ){");
				out.println("			document.Form1.MKT_OFFICER.value=data_vec[0]");
				out.println("			}");*/
				
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
				
				
				
				out.println("function makeRequest(obj) {");
				
						
				out.println("if(document.Form1.hid_chk_status.value=='M_MKT_OFFC' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_credit_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.MKT_OFFICER.value+\"&ac_status=Y\";");	
				
				//out.println("window.open(m_url);");
				
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function view_details() {");
				out.println("		clearTimeout(timerID);");
			//	out.println("		m_table.innerHTML=\"\";");				
				//out.println("alert('sd');");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		m_rpt_type='ALL';");
				out.println("		m_croff=document.Form1.MKT_OFFICER.value ");//added milinda
				
				out.println("   m_region   = document.Form1.TXT_REGION.value; "); // Samith Dilshan on 2015-06-10 (#16240)
				
				out.println(" 	m_lead_source = document.Form1.LEAD_SOURCE_CATEGORY.value ");
				
				out.println(" 	m_item_cat = document.Form1.TXT_ITEM_CATEGORY.value "); // added by udara 12-04-2017
				out.println(" 	m_item_sub_cat = document.Form1.TXT_ITEM_SUB_CATEGORY.value "); // added by udara 12-04-2017
				
				out.println("		if(validate_date()) {");				
				//Modified by Dineth on 28-04-2009
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009//commentd by milinda 2013-10-21
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&cr_off=\"+m_croff+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
				
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&cr_off=\"+m_croff+\"&lead_source=\"+m_lead_source+\"&region=\"+m_region+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";"); // udara 10-11-2014
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&cr_off=\"+m_croff+\"&lead_source=\"+m_lead_source+\"&region=\"+m_region+\"&item_cat=\"+m_item_cat+\"&item_sub_cat=\"+m_item_sub_cat+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";"); // added by udara 12-04-2017
				
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Credit Process - Confirm Case Summary - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Credit Process - Confirm Case Summary \";"); 
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
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				
				
				
				//----Added by Prabash on 09-05-2012------**
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("		}"); 
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_update_value_assign_5(oBj);"); 
				out.println("		}"); 
				
				// added by udara 12-04-2017
				out.println("		if(IfCount==\"66\"){"); 
				out.println("		help_update_value_assign_66(oBj);"); 
				out.println("		}");
			
			    out.println("		if(IfCount==\"67\"){"); 
				out.println("		help_update_value_assign_67(oBj);"); 
				out.println("		}");
			    // end by udara 12-04-2017
				
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
				
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				//ADDED MILINDA
				out.println("function help_update_value_assign_5() {"); 
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
				out.println("}");
		
				// added by udara 12-04-2017
				out.println("function help_update_value_assign_66() {"); 
				out.println("    document.Form1.TXT_ITEM_CATEGORY.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function help_update_value_assign_67() {"); 
				out.println("    document.Form1.TXT_ITEM_SUB_CATEGORY.value=oBj.valout[2];"); 
				out.println("}");
				// end by udara 12-04-2017
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("       if(IfCount==\"5\"){");
				out.println("document.Form1.MKT_OFFICER.value='';");
				out.println("		}");
				// added by udara 12-04-2017
				out.println("       if(IfCount==\"66\"){");
				out.println("document.Form1.TXT_ITEM_CATEGORY.value='';");
				out.println("		}");
				out.println("       if(IfCount==\"67\"){");
				out.println("document.Form1.TXT_ITEM_SUB_CATEGORY.value='';");
				out.println("		}");
				// end by udara 12-04-2017
				out.println("}");
				
				//---Pra-----------------------------------**
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Confirm Case Summary</td>"); 
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
				
				// commented below by udara on 14-11-2012
				/*
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr display:'none'>"); 
				out.println("<td width='15%' ></td>"); //<DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV>
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"hidden\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("<td width='*%'></td>");  
				*/
				
				
				// added by udara on 14-11-2012
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\"  name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\"  name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("<td width='*%'></td>");  
				
				out.println("</tr >"); 
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"View Report\" onClick=\"run_report()\">"); //<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\">	
				out.println("</td> ");
				out.println("<td width='*%'></td>"); 
				// added by prabash on09-05-2012----**
				out.println("</tr >");
				
				// commented by udara 08-05-2017
				
				/*
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				*/
				
				
				
				
				//----------------------------------**
				out.println("<tr >"); 
				
				out.println("<td width='20%' ><DIV id='DIV_MKT_OFFICER'  class=div_input>Credit Officer *</DIV></td>"); 
				out.println("<td width='*%%' ><input class='txt_input' type='text' name='MKT_OFFICER' maxlength='50' size='10' onblur=\"mk_officer_help()\" >");  //onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\"
				out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"mk_officer_help()\"></td>"); 
				
				//out.println("<td ></td>"); 
				out.println("</tr>"); //added milinda
				
				
				// added by udara 12-04-2017
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
				// end by udara 12-04-2017
				
				// added by udara 10-11-2014

				out.println("<tr >"); 
				out.println("<td width='20%' >Lead Source Category</td>");
				out.println("<td width='*%' >");
				out.println("<select name=\"LEAD_SOURCE_CATEGORY\" class=\"txt_input\" style=\"width:175px;\" onChange='' >");
				rs1 = stmt1.executeQuery(" "+
							" SELECT SOURCE_CODE Source,NAME \"Source Name\" "+
                           	" FROM   "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
							" WHERE  ACTIVE_STATUS ='Y'  "+
							" AND SOURCE_CODE LIKE '%%' "+
							" ORDER BY	DEFAULT_VALUE DESC "+
							" ");
				
				boolean more=rs1.next();
				
				out.println("<OPTION value=\"ALL\" > All </option>");
				while(more){
					out.println("<OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</option>");
					more = rs1.next();	
				}	
				out.println("</SELECT>");
				out.println("</td>");
				out.println("</tr>");
				
				// end by udara 10-11-2014
				
				
				
				// Added By: Samith dilshan  On : 2015-06-03
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_REGION'  class=div_input>Region </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_REGION'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
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
				
				
				// added by udara 12-05-2017
				String m_location = "";
				
				rs1 = stmt1.executeQuery(" SELECT "+
							" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
							" FROM DUAL ");
				
				if(rs1.next()){
					m_location = rs1.getString(1);
				}
				
				if(m_location.equals("HO")){
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
					out.println("</tr>"); 
				}
				else{
					out.println("<tr style={visibility:hidden;} >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
					out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" value=\""+m_location+"\" >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
					out.println("</tr>"); 
				}
				
				
				// end by udara 12-05-2017
				
				// added by udara 11-10-2018
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PRODUCT'  class=div_input> Product Name </DIV></td>"); 
			    out.println("<td width='*%' ><select class='txt_input' name='TXT_PRODUCT'>");  
	            out.println("      <OPTION value='NOT_SELECT' >--- Please Select ---</OPTION>");
				
				rs1=stmt1.executeQuery(" SELECT TRAN_CODE,DESCRIPTION,DEFAULT_VALUE "+
					" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
					" WHERE  ACTIVE_STATUS='Y' ");
				
				while(rs1.next()){
					out.println("  <OPTION value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</OPTION>");
				}
				
				out.println(" 	   </select>");
				out.println(" </td>"); 
				out.println("</tr>");
				// end by udara 11-10-2018
				
				out.println("</table>");
				//Added by Kanchana.
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
			
			else if(m_chksql.equals("load_agreement_regi")){		
				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");
				m_sort_column = req.getParameter("sort_column");
				m_order_by_type = req.getParameter("order_by_type");
				String m_cr_officer=req.getParameter("cr_off");//added milinda
				String m_lead_source=req.getParameter("lead_source"); // added by udara 10-11-2014
		
				String m_region = req.getParameter("region"); // Added By Samith Dilshan On 2015-06-10
				
				
				// added by udara 12-04-2017
				String m_item_cat = req.getParameter("item_cat");
				String m_item_sub_cat = req.getParameter("item_sub_cat");
				
				String m_item_cat_sql = "";
				String m_item_sub_cat_sql = "";
				
				if(!m_item_cat.equals(""))
					m_item_cat_sql = " AND A.ITEM_CATEGORY  = '"+m_item_cat+"' "; // m_item_cat_sql = " AND A.ITEM_CATEGORY  LIKE '"+m_item_cat+"%' ";
				
				
				if(!m_item_sub_cat.equals(""))
					m_item_sub_cat_sql = " AND A.ITEM_SUB_CATEGORY  = '"+m_item_sub_cat+"' "; // m_item_sub_cat_sql = " AND A.ITEM_SUB_CATEGORY  LIKE '"+m_item_sub_cat+"%' ";
				// end by udara 12-04-2017
				
				
				String m_lead_source_1=""; //Added by Kanchana on 2016-08-09
				// added by udara 10-11-2014
				if(m_lead_source.equals("ALL"))
					m_lead_source_1 = "";
				else
					m_lead_source_1 = " AND NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT(A.APPLICATION_NO),' ') LIKE '"+m_lead_source+"%' ";
				// end by udara 10-11-2014
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_application_no="";
				//String m_cr_officer = ""; //added by milinda
				
				String q_part = "";
				
				if(!m_cr_officer.equals(""))
					q_part = " AND "+m_schema_name+".AF_CO_GET_CR_OFFICER("+m_schema_name+".AF_CO_GET_APPLICAT_NO(A.APPLICATION_NO)) = UPPER('"+m_cr_officer+"')";
				else
					q_part = "";
				
				stmt = conn.createStatement ();
				//stmt2 = conn.createStatement ();
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				//Added by Dineth on 28-04-2009
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
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirm_Case_Summary?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&rpt_type="+m_rpt_type+"&cr_off="+m_cr_officer+"&lead_source="+m_lead_source+"&region="+m_region+" \";");
				out.println(" window.location.href=m_url;");
				
				out.println("}");
				
				// added by udara 03-11-2014
				
				out.println(" function show_transaction_info(m_client_code,m_finance_no){");
			    out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
			    out.println("    window.open(m_url); ");
			    out.println(" }");
				
				// end by udara 03-11-2014
				
				
				//Added by Kanchana on 2016-08-09
				// commented by udara 13-03-2018
				/*
				out.println("function load_details_report(val){"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_View?chksql=main_page&finance_no=\"+val; ");
				out.println("window.open(m_url,'popupwin_conf_rpt_app_4','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1')");
				out.println("} "); 
				*/
				
				// added by udara 13-03-2018
				out.println("function load_details_report(val){"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no=\"+val; ");
				out.println("window.open(m_url,'popupwin_conf_rpt_app_case_sum','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1')");
				out.println("} ");
				// end by udara 13-03-2018	
					
				out.println("function view_documents(val){");
				out.println("	if(val!=\"\"){");				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_upload?chksql=view_documents&finance_no=\"+val;"); // commented by udara 13-03-2018
				out.println("   m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_View?chksql=viewAllDocuments&finance_no=\"+val; "); // added by udara 13-03-2018
				out.println("			window.open(m_url);");
				out.println("	}else{");
				out.println(" alert('Finance number not available.');");
				out.println("}");
				out.println("}");
					
				//End ---------------------------
				
				
				
				//end by Dineth on 28-04-2009	
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");
				//Added by Kanchana on 2016-08-09
				if(m_sort_column.equals("activated_date")){
					
					m_sort_column ="TO_date(activated_date,'DD-MM-YYYY') ";				
				}
				//End----------------------------
				String Sql_data="";
				
				if(m_rpt_type.equals("ALL")){
					
					Sql_data=" SELECT  "+
						" DISTINCT a.application_no,  "+//1
						" a.finance_no,  "+//2
						" a.client_code,  "+//3
						" a.client_full_name, "+//4
						" a.client_address,   "+//5
						" a.TERMS_OF_PAYMENTS , "+ //6
						" to_char(a.activated_date,'DD/MM/YYYY') activated_date,   "+//7
						" NO_OF_FUTURE_INS ,"+  //8
						" NVL(a.facility_amount,0), "+  //9
						" NVL(a.rental_amount,0),  "+//10
						" a.frequency ,"+ //11
						" NVL(a.flat_rate,0),   "+//12
						" NVL(a.irr,0),  "+//13
						" NVL(a.facility_amount,0) + NVL(a.INTEREST_AMOUNT,0) ,  "+//14
						" STATUS  ,  "+//15
						" b.make, "+ //16
						" ITEM_SUB_CAT ,"+ //17
						" nvl(B.reg_no,'-') reg_no, "+ //18
						" nvl(B.engine_no,'-') engine_no,"+ //19
						" nvl(B.chassis_no,'-') chassis_no,"+  //20
						" nvl(B.year_of_manufacture,0) year_of_manufacture,"+ //21
						" B.asset_status ,"+ //22
						" A.LOCATION_CODE ,"+ //23
						" A.TRANSACTION_TYPE , "+ //24
						" B.vendor, "+ //25 
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME("+m_schema_name+".AF_CO_GET_BUS_SECT_CODE(A.CLIENT_CODE)),'-'), "+//26
						" "+m_schema_name+".AF_CO_GET_AGREEMENT_TYPE(A.APPLICATION_NO), "+ //27 
						" "+m_schema_name+".AF_CO_GET_VAT_RATE(A.APPLICATION_NO), "+ //28 
						" "+m_schema_name+".AF_CO_GET_APP_VAT_ON_RENTAL(A.APPLICATION_NO), "+ //29 
						" nvl("+m_schema_name+".AF_CO_GET_EMPLOYEE_NAME(A.APPLICATION_NO),'-') "+ //30
						" ,"+m_schema_name+".AF_CO_GET_CONTRACT_STATUS(a.client_code,'"+m_to_date+"'), "+ //31
						" "+m_schema_name+".AF_CO_GET_RENTAL_TOTAL(A.APPLICATION_NO), "+ //32
						" NVL(a.INTEREST_AMOUNT,0), "+ //33
						" TO_CHAR(TO_DATE ("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'),'DD')RENTAL_DATE "+ //34
						" ,SUBSTR(A.FINANCE_NO,-4) FINANCE_NO_SORT, "+ // 35
						
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'PAYEE_NAME'),'-') PAYEE_NAME, "+ // 36 added by udara on 11-07-2013
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'INS_AMOUNT'),0) INS_AMOUNT, "+ // 37 added by udara on 11-07-2013
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'BROKER_CHARGE'),0) BROKER_CHARGE, "+ // 38 added by udara on 11-07-2013
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'RMV_CHARGE'),0) RMV_CHARGE, "+ // 39 added by udara on 11-07-2013
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'CASH_PRICE'),0) CASH_PRICE, "+ // 40 added by udara on 11-07-2013
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'MARKET_PRICE'),0) MARKET_PRICE, "+ // 41 added by udara on 11-07-2013
						" DECODE(B.CR_BOOK_RECEIVED,'Y','Yes','N','No',NULL,'-'), "+ //42 // thamali 2013.08.12		
						" NVL("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_DESC("+m_schema_name+".AF_CO_GET_LEAD_SOURCE_CAT(A.APPLICATION_NO)),'-'), "+ //43 // ADDED BY SAJITH MENDIS ON 03-06-2014
						" NVL("+m_schema_name+".GET_CONF_SUMMARY_IMP_DETAILS(A.FINANCE_NO,A.APPLICATION_NO,'DOCUMENT_CHARGE'),0) DOCUMENT_CHARGE, "+ // 44 added by udara 12-11-2015
						" NVL(DOWN_PAYMENT,0), "+ // 45 added by udara 12-04-2017
						" NVL(CAPITALIZED_AMOUNT,0), "+ // 46 added by udara 28-06-2017
						" NVL(ITEM_CAT_DESC,'-'), "+ // 47 added by udara 15-08-2017
						" NVL(ITEM_SUB_CAT_DESC,'-'), "+ // 48 added by udara 15-08-2017
						" NVL(LEAD_SOURCE_NAME,'-')   "+ // 49 added by udara 06-09-2017
						" FROM "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY a  , "+m_schema_name+".AF_MISF_TBD_CASE_SUMMARY_DET B "+ 
						" WHERE A.application_no=B.application_no ";
						
					if(!(m_region.equals("NOT_SELECT"))){    // Added By: Samith Dilshan on 2015-06-08 for Region Code
						Sql_data = Sql_data +"  AND  a.REGION_CODE = '"+m_region+"' ";    
					}
					
					
						Sql_data = Sql_data +" AND A.ENT_USER='"+m_username+"' "+
						" AND B.ENT_USER='"+m_username+"' "+
						
						"   "+m_lead_source_1+"   "+ // added by udara 10-11-2014
						
						"   "+m_item_cat_sql+"  "+ // added by udara 12-04-2017
						"   "+m_item_sub_cat_sql+"  "+ // added by udara 12-04-2017
						
						//"	AND A.ACTIVATED_DATE >= TRUNC(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MM') "+ // commented by udara on 14-11-2012
						//"	AND A.ACTIVATED_DATE <= LAST_DAY(TO_DATE('"+m_to_date+"','DD-MM-YYYY')) "+ // commented by udara on 14-11-2012
						
						"	AND TRUNC(A.ACTIVATED_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara on 14-11-2012
						"	AND TRUNC(A.ACTIVATED_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+  // added by udara on 14-11-2012
						
						//" AND "+m_schema_name+".AF_CO_GET_CR_OFFICER("+m_schema_name+".AF_CO_GET_APPLICAT_NO(A.APPLICATION_NO)) = UPPER('"+m_cr_officer+"')  "+ //ADDED MILINDA 2013-10-21
						q_part + 
						
						//" AND TO_DATE(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+  
						//" AND TO_DATE(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						
						" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
					
				}
				
				//out.println(Sql_data);
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
				stmt3 = conn.createStatement ();
				
				boolean more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				double tot1=0;
				double tot2=0;
				double tot3=0;
				double tot4=0;
				double tot5=0;
				double tot6=0;
				double tot7=0;
				double tot8=0;
				double tot9=0;
				
				double tot_document_charge = 0; // added by udara 12-11-2015
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><B>LAKDERANA INVESTMENTS LIMITED</B></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				//out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   >Confirm Case Summary of "+m_to_date+"</td>"); // commented by udara on 14-1-2012
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   >Confirmed Case Summary from "+m_from_date+" to "+m_to_date+" </td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				
				// added by udara 17-08-2015
				if(!(m_cr_officer.equals(""))){ 
					
					String qry = " SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_cr_officer+"') FROM DUAL  ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Credit Officer : "+rs3.getString(1)+"</td>");  
						out.println("</tr >");
						out.println("</table >");
					}
				}
				// end by udara 17-08-2015
				
				
				
				if(!(m_region.equals("NOT_SELECT"))){ 
					
					String qry = " SELECT R.REGIONS_DESC FROM "+m_schema_name+".AF_CO_MAS_REGIONS R WHERE R.REGIONS_CODE = '"+m_region+"' ";
					
					rs3=stmt3.executeQuery(qry);
					
					boolean more_1=rs3.next();
					if(more_1){
						out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
						out.println("<tr >");
						out.println("<td width=\"*\" STYLE='{font: bold 8pt arial; text-align:left;}'   >Region : "+rs3.getString(1)+"</td>");  
						out.println("</tr >");
						out.println("</table >");
					}
				}
				
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				if(more){
					
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"3%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >No</td>");  //4
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Contract No</td>");  //4
					out.println("<td width=\"9%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Vehicle No</td>");  //17
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   onclick=\"sort_data('activated_date');\"  >Commence Date</td>");  //8
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Credit Value</td>");  //10
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Cash Price</td>");  //10
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Market Value</td>");  //10
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Rental</td>");  //11
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Period</td>");  //9
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Rental Date</td>");  //34
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Future Rental</td>");  //12
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Interest Income</td>");  //13
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >RMV</td>");  //14
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Document Charges/PC Charges</td>"); // added by udara 12-11-2015//[PC Charges Added by milinda 16-11-2017]
					
					out.println("<td width=\"12%\"  STYLE='{font: bold 8pt arial; text-align:center; }'  >Insurance Type</td>");  //14 Sandun on 11-06-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Insurance</td>");  //14 Sandun on 11-06-2009
					out.println("<td width=\"4%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >BR</td>");  //14
					out.println("<td width=\"4%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >CR Book</td>");  //15
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Lead Source Category</td>");
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >IRR</td>"); // added by udara 12-04-2017
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Down Payment</td>"); // added by udara 12-04-2017
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Capitalized amount</td>"); // added by udara 28-06-2017
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Item Category</td>"); // added by udara 15-08-2017
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Item Sub Category</td>"); // added by udara 15-08-2017
					out.println("<td width=\"6%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Lead Source Name</td>"); // added by udara 06-09-2017
					out.println("</tr >");
					
				}
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				
				// added by udara on 11-07-2013
				String payee_name = "";
				double insu_amt = 0;
				double broker_charge = 0;
				double rmv_charge = 0;
				double cash_price = 0;
				double market_price = 0;
				
				while(more){
					
					payee_name = rs.getString(36);
					insu_amt = rs.getDouble(37);
					broker_charge = rs.getDouble(38);
					rmv_charge = rs.getDouble(39);
					cash_price = rs.getDouble(40);
					market_price = rs.getDouble(41);
					
					// commented by udara on 11-07-2013	
					/*	
					rs1=stmt1.executeQuery(""+					
							"				SELECT PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,AMOUNT ,PRO_INVOICE_NO,CHARGES,PAYEE_CODE  "+
							"				FROM( "+
							"				SELECT  "+
							"				B.PRICING_NO PRICING_NO,   "+
							"				B.SUB_CHAGE_CODE SUB_CHAGE_CODE,   "+
							"				C.DESCRIPTION DESCRIPTION,   "+
							"				SUM(B.AMOUNT) AMOUNT , "+
							"				B.PRO_INVOICE_NO PRO_INVOICE_NO, "+
							"				'CHARGES' CHARGES, "+
							"               NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(B.PAYEE_CODE),'-') PAYEE_CODE "+
							"				FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B , "+
							"				"+m_schema_name+".AF_CO_MAS_SUB_CHARGES C  "+
							"				WHERE A.APPLICATION_NO=B.APPLICATION_NO AND   "+
							"	            A.FINANCE_NO=UPPER('"+rs.getString(2)+"') AND "+
							"               B.SUB_CHAGE_CODE = 'INSURANCE' AND "+
							"				B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
							//"				AND NVL(B.AMOUNT,0) <> 0  "+
							"				GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO,B.PAYEE_CODE ORDER BY  C.DESCRIPTION 			 "+
							"				) "+
							"				ORDER BY CHARGES,DESCRIPTION ");
							
							
						more =rs1.next();
						double insu_amt=0;	
						String payee_name= "";
						if(more){
						insu_amt=rs1.getDouble(4);
						payee_name=rs1.getString(7);
						}
						
						
						
						rs2=stmt2.executeQuery(""+					
							"				SELECT PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,AMOUNT ,PRO_INVOICE_NO,CHARGES  "+
							"				FROM( "+
							"				SELECT  "+
							"				B.PRICING_NO PRICING_NO,   "+
							"				B.SUB_CHAGE_CODE SUB_CHAGE_CODE,   "+
							"				C.DESCRIPTION DESCRIPTION,   "+
							"				SUM(B.AMOUNT) AMOUNT , "+
							"				B.PRO_INVOICE_NO PRO_INVOICE_NO, "+
							"				'CHARGES' CHARGES "+
							"				FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B , "+
							"				"+m_schema_name+".AF_CO_MAS_SUB_CHARGES C  "+
							"				WHERE A.APPLICATION_NO=B.APPLICATION_NO AND   "+
							"	            A.FINANCE_NO=UPPER('"+rs.getString(2)+"') AND "+
							"               B.SUB_CHAGE_CODE = 'BROKERCOMM' AND "+
							"				B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE AND  "+
							"				NVL(B.AMOUNT,0) <> 0  "+
							"				GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION 			 "+
							"				) "+
							"				ORDER BY CHARGES,DESCRIPTION ");
							
							
						more =rs2.next();
						double broker_charge=0;				
						if(more){
						broker_charge=rs2.getDouble(4);
						}
						
					rs3=stmt3.executeQuery(""+					
							"				SELECT PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,AMOUNT ,PRO_INVOICE_NO,CHARGES  "+
							"				FROM( "+
							"				SELECT  "+
							"				B.PRICING_NO PRICING_NO,   "+
							"				B.SUB_CHAGE_CODE SUB_CHAGE_CODE,   "+
							"				C.DESCRIPTION DESCRIPTION,   "+
							"				SUM(B.AMOUNT) AMOUNT , "+
							"				B.PRO_INVOICE_NO PRO_INVOICE_NO, "+
							"				'CHARGES' CHARGES "+
							"				FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B , "+
							"				"+m_schema_name+".AF_CO_MAS_SUB_CHARGES C  "+
							"				WHERE A.APPLICATION_NO=B.APPLICATION_NO AND   "+
							"	            A.FINANCE_NO=UPPER('"+rs.getString(2)+"') AND "+
							"               B.SUB_CHAGE_CODE = 'RMV' AND "+
							"				B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE AND  "+
							"				NVL(B.AMOUNT,0) <> 0  "+
							"				GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION 			 "+
							"				) "+
							"				ORDER BY CHARGES,DESCRIPTION ");
							
							
						more =rs3.next();
						double rmv_charge=0;				
						if(more){
						rmv_charge=rs3.getDouble(4);
						}
						
						double cash_price = 0;
						double market_price = 0;
						
						rs3=stmt3.executeQuery(""+					
							"				SELECT  "+
							"				NVL(FORCED_SALES_VALUE,0), NVL(VALUE,0)  "+
							"				FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
							"				WHERE    "+
							"	            A.APPLICATION_NO=UPPER('"+rs.getString(1)+"')  ");
							
						more =rs3.next();
						if(more){
						
						cash_price=rs3.getDouble(1);
						market_price=rs3.getDouble(2);
						}
						*/
					
					out.println("<tr  bgcolor=\"#FCEBC5\"  >");
					out.println("<td width=\"3%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+j+"</td>");  //4
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left; cursor:hand;  }' onclick=\"show_transaction_info('"+rs.getString(3)+"','"+rs.getString(2)+"');\"  ><u>"+rs.getString(2)+"</u></td>");  //4
					out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left; cursor:hand; }' onClick=\"view_documents('"+rs.getString(2)+"');\"  >"+rs.getString(18)+"</td>");  //17
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left; cursor:hand; }' onClick=\"load_details_report('"+rs.getString(2)+"');\" >"+rs.getString(7)+"</td>");  //8 onclick added by Kanchana on 2016-08-09
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(9))+"</td>");  //10
					
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(cash_price)+"</td>");  //10
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(market_price)+"</td>");  //11
					
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(10))+"</td>");  //11
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center;}'   >"+rs.getString(8)+"</td>");  //9
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center;}'   >"+rs.getString(34)+"</td>");  //34 added by Prabash on 13-06-2012
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(32))+"</td>");  //12  rs.getDouble(14)
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(33))+"</td>");  //13
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rmv_charge)+"</td>");  //14
					
					out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+nf.format(rs.getDouble(44))+"</td>"); // added by udara 12-11-2015
					
					out.println("<td width=\"12%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+payee_name+"</td>");  //14
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(insu_amt)+"</td>");  //14
					out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(broker_charge)+"</td>");  //14
					out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+rs.getString(42)+"</td>");  //15
					out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+rs.getString(43)+"</td>");  
					out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf2.format(rs.getDouble(13))+"</td>"); // mod by udara 05-05-2017 // added by udara 12-04-2017
					out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(45))+"</td>"); // added by udara 12-04-2017
					out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+nf.format(rs.getDouble(46))+"</td>"); // added by udara 28-06-2017
					out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+rs.getString(47)+"</td>"); // added by udara 15-08-2017
					out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left; }'   >"+rs.getString(48)+"</td>"); // added by udara 15-08-2017
					out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left; }'   > "+rs.getString(49)+" </td>"); // added by udara 06-09-2017
					out.println("</tr >");			
					
					tot1=tot1+rs.getDouble(9);
					tot2=tot2+rs.getDouble(10);
					tot3=tot3+rs.getDouble(32);
					tot4=tot4+rs.getDouble(33);
					tot5=tot5+rmv_charge;
					tot6=tot6+insu_amt;
					tot7=tot7+broker_charge;
					tot8=tot8+cash_price;
					tot9=tot9+market_price;
					
					tot_document_charge = tot_document_charge + rs.getDouble(44); // added by udara 12-11-2015
					
					count = count+ 1;
					j = j+ 1;
					more=rs.next();
					if(!more){break;}
				}
				
				
				out.println("<tr  bgcolor=\"#FCEBC5\"  >");
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //4
				out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //17
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot1)+"</b></td>");  //10
				
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot8)+"</b></td>");  //10
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot9)+"</b></td>");  //11
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot2)+"</b></td>");  //11
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //9
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //34
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot3)+"</b></td>");  //12  rs.getDouble(14)
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot4)+"</b></td>");  //13
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot5)+"</b></td>");  //14
				
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ><b>"+tot_document_charge+"</b></td>"); // added by udara 12-11-2015
				
				out.println("<td width=\"12%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>");  //14
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot6)+"</b></td>");  //14
				out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot7)+"</b></td>");  //14
				out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>");  //14
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>");
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 12-04-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 12-04-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 28-06-2017

				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 15-08-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 15-08-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 06-09-2017

				out.println("</tr >");			
				//out.println("</table>");	
				
				//out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				out.println("<tr >");
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ><b>Case Average:</b></td>");  //4
				out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //17
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				if (count!=0)
				{
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot1/count)+"</b></td>");  //10
				}
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //11
				
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //9
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //12  rs.getDouble(14)
				
				
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //9
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //12  rs.getDouble(14)
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //13
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //14
				out.println("<td width=\"12%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>");  //14
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //14
				out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //14
				out.println("<td width=\"4%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>");  //14
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:right; }'   ></td>"); 
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 12-04-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 12-04-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 28-06-2017

				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 15-08-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 15-08-2017
				out.println("<td width=\"6%\"  STYLE='{font: 8pt arial; text-align:left;  }'  ></td>"); // added by udara 06-09-2017

				out.println("</tr >");			
				out.println("</table>");	
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			}
			

			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(stmt3!=null){try{stmt3.close();  }catch(Exception e){}}
			if(stmt4!=null){try{stmt4.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
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
