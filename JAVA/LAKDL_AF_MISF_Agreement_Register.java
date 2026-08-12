// DEVELOP BY : SANDUN FOR OFSCL LEASING-CREDIT  
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Agreement_Register extends javax.servlet.http.HttpServlet { 
	
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
			//String m_sort_column   = "A.FINANCE_NO";	
			//String m_order_by_type = "ASC";
			
			//End by Dineth on 28-04-2009
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			stmt1=conn.createStatement();
			
			if(m_chksql.equals("run_report")){ 
				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");
				String m_branch=req.getParameter("branch");//added milinda 2014-05-02 
				
				if(m_branch.equals("")||m_branch.equals(null)||m_branch.equals(" ")){
					m_branch="";
				}else{ 
					m_branch=m_branch;		
				}
				try{
					//callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_AGR_REGISTER(:1,:2,:3,:4,:5);END;"); // commented by udara 21-08-2018
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_AGR_REGISTER_V2(:1,:2,:3,:4,:5);END;"); // added by udara 21-08-2018
					callstmt1.setString(1,m_from_date);
					callstmt1.setString(2,m_to_date);
					callstmt1.setString(3,m_rpt_type);
					callstmt1.setString(4,m_username);
					callstmt1.setString(5,m_branch);//added milinda 2014-05-02
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Agreement Register</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				out.println("var timerID;");//Added by Jithendra 27-04-2017
				out.println("var durationID=0;");//Added by Jithendra 27-04-2017
				
				/*out.println("   if((document.Form1.TXT_LOCATION_CODE.value==\"\")  || (document.Form1.TXT_LOCATION_CODE.value==null) || (document.Form1.TXT_LOCATION_CODE.value==\" \")){  ");
				out.println("alert('Please enter Branch code');");
				out.println(" document.Form1.TXT_LOCATION_CODE.value=\"\"; ");
				out.println("}");*/
				
				out.println("function run_report() {");
				out.println("	if(validate_date()){");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
				//out.println("   if((document.Form1.TXT_LOCATION_CODE.value==\"\")  || (document.Form1.TXT_LOCATION_CODE.value==null) || (document.Form1.TXT_LOCATION_CODE.value==\" \")){  ");
				//out.println("alert('Please enter Branch code');");
				//out.println(" document.Form1.TXT_LOCATION_CODE.value=\"\"; ");
				//out.println("}else{");
				out.println("       m_branch=document.Form1.TXT_LOCATION_CODE.value;");//added miilnda 2014-05-02 for branch code
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=run_report&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;"); //old
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=run_report&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch=\"+m_branch;"); //new
				out.println("   set_timer_actions();");//Uncommented by Jithendra 27-04-2017
				out.println("		load_interface(m_url,'NORM');");
				//out.println("	}");
				out.println("	}");
				out.println("}");
				
				/****Added by Jithendra 27-04-2017**********/   
				    out.println("function set_timer_actions() {");
					out.println("   durationID=durationID+1;");
					out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
					out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
					out.println("}");
				/****end****/
				
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
				
				
				
				out.println("function view_details() {");
				//out.println("alert('sd');");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
				out.println("       m_branch=document.Form1.TXT_LOCATION_CODE.value;");//added miilnda 2014-05-02 for branch code
				out.println("		clearTimeout(timerID);");//Added by Jithendra 27-04-2017
				out.println("		m_table.innerHTML=\"\";");//Added by Jithendra 27-04-2017
				out.println("		if(validate_date()) {");
				//out.println("   if((document.Form1.TXT_LOCATION_CODE.value==\"\")  || (document.Form1.TXT_LOCATION_CODE.value==null) || (document.Form1.TXT_LOCATION_CODE.value==\" \")){  ");
				//out.println("alert('Please enter Branch code');");
				//out.println(" document.Form1.TXT_LOCATION_CODE.value=\"\"; ");
				//out.println("}else{");
				//Modified by Dineth on 28-04-2009
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009//old
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch=\"+m_branch+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009//new
				out.println("		  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&branch=\"+m_branch+\"&order_by_type=activated_date&sort_column=ASC\";");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&branch="+m_branch+"&rpt_type="+m_rpt_type+"\";");
				//End by Dineth on 28-04-2009
				//out.println(" alert(m_url);");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				//out.println("   }");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Credit Process - Agreement Register - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Credit Process - Agreement Register \";"); 
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
				
				/*out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
				out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
				out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("		if(oBj.valout[1] !=\"Close\"){"); 
				out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("				if(oBj.valout[1]!=\"Next\"){"); 
				out.println("					if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("						help_update_value_assign_2();"); 
				out.println("					}"); 
				out.println("					if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("						help_update_value_assign_3();"); 
				out.println("					}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
				out.println("		}"); 
				out.println("				}"); 
				out.println("				else{"); 
				out.println("					Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("					return false;"); 
				out.println("				} "); 
				out.println("			}"); 
				out.println("			else{	"); 
				out.println("				Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("			}	"); 
				out.println("	 	}"); 
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				*/
				
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
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		help_cr_assign(oBj);"); 
				out.println("		}"); 
				//out.printrln("      if(IfCount==\"5\"){ ");
				//out.println("       help_cr_assign(oBj);");
				//out.println("		}"); 
				
				
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
				
				//-----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println(""); 
				
				out.println("function help_button_finance() {"); 
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
				out.println("}"); 
				
				
				//credit  officer help
				out.println("function mk_officer_help() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    Crit = document.Form1.MKT_OFFICER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_credi_officer','5');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_finance(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
				out.println("}");
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				//added milinda
				out.println("function help_cr_assign() {"); 
				out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				
				out.println("		if(IfCount==\"5\"){"); 
				out.println("document.Form1.MKT_OFFICER.value='';");
				out.println("		}"); 
				
				
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Agreement Register</td>"); 
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
				out.println("<td width='15%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Report Type</DIV></td>"); 
				out.println("<td width='60%' ><SELECT onchange=\"\" name=\"TXT_RPT_TYPE\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"ALL\" SELECTED>All</OPTION>");//Added By Sandun on 23-12-2008
				out.println("<OPTION value=\"ACTIVATED\" >Live</OPTION>");
				out.println("<OPTION value=\"TERMINATED\">Terminated</OPTION>");
				out.println("<OPTION value=\"REJECT\">Reject</OPTION>");			
				out.println("</SELECT></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
				out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
				out.println("</td> ");
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
				out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\"><input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\">");	
				out.println("</td> ");
				//BRANCH ADDED MILINDA 2014-05-02 
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				//END MILINDA
				out.println("</table>");
				
				/**Added by Jithendra 27-04-2017****/
				    out.println("<table align='center' width='100%' class='table'>"); 
					out.println("<tr>");  
					out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
					out.println("</tr>"); 
					out.println("</table>"); 
				/***end****/	
				
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
			
			/*else if(m_chksql.equals("load_agreement_regi")) {
			
			  String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				
				
				rs1= stmt1.executeQuery(" SELECT A.FINANCE_NO,"+//1
				                        " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') LOCATION,"+//2
																" SUM(NVL(B.FINANCED_AMOUNT,0) + NVL(B.CHARGES,0) +NVL(B.MAINTENANCE,0)) FINANCE_AMOUNT,"+//3
															  "	NVL("+m_schema_name+".AF_CO_RETURN_RATE(A.APPLICATION_NO),0) IRR, "+//4
																" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') "+//5
																"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
															  "	WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
															  "	AND A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
															  "	AND A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
															  "	AND A.APPLICATION_STATUS='ACTIVATED' "+
														  	"	GROUP BY A.FINANCE_NO,A.BRANCH_CODE,A.APPLICATION_NO, A.ACTIVATED_DATE "+
															  "	ORDER BY A.ACTIVATED_DATE ");
				
				boolean more = rs1.next();
								
					 out.println("<HTML><HEAD><TITLE>Agreement Register</TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
												
					  out.println("</SCRIPT>");
					  out.println("<table width='100%' class='table' border='0'>");						
						out.println("<tr>");		
						out.println("<td width='*%' align='center' ><DIV class=div_input ><b>Agreement Register From "+m_from_date+" To "+m_to_date+"</b></DIV></td>"); 		
					  out.println("</tr>");		
						out.println("</table>");	
						out.println("<br>");		
						out.println("<table width='100%' class='table' border='1' leftmargine='0'>");		
								
					  out.println("<tr bgcolor='#C0C0C0'>");										
						out.println("<td width='20%' align='left' ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='20%' align='left' ><DIV class=div_input ><b>Location</b></DIV></td>"); 
						out.println("<td width='20%' align='left' ><DIV class=div_input ><b>Activated Date</b></DIV></td>"); 
						out.println("<td width='20%' align='right' ><DIV class=div_input ><b>IIR</b></DIV></td>"); 
						out.println("<td width='20%' align='right' ><DIV class=div_input ><b>Finance Amount</b></DIV></td>"); 
						out.println("</tr>"); 
				  
					int j=0;				
			    while(more){	
					
					if(j==0){
					out.println("<tr bgcolor=\"#FFFFFF\">");
					j=1;
			    	}
				  else{
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					j=0;
				   }
						 
							out.println("<td width='20%' class=div_input align='left' onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input align='left'>"+rs1.getString(2)+"</td>"); 
							out.println("<td width='20%'  class=div_input align='left' >"+rs1.getString(5)+"</td>");
							out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs1.getDouble(4))+"</td>");							
							out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("</tr>"); 
							
							more = rs1.next();
							}
							
			  
				out.println("</table>");
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				
			}
			
			*/
			
			else if(m_chksql.equals("load_agreement_regi")){		
				
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");//ADDED BY LALANKA ON 07-11-2009
				String m_branch =req.getParameter("branch");//added milinda 2014-05-02 for branch
				//Added by Dineth on 28-04-2009
				String m_sort_column = req.getParameter("sort_column");
				String m_order_by_type = req.getParameter("order_by_type");
				//End by Dineth on 28-04-2009
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				
				stmt = conn.createStatement ();
				//stmt2 = conn.createStatement ();
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				//Added by Dineth on 28-04-2009
				out.println("function sort_data(m_order_by_type) {");
				//	out.println("alert(m_sort_col);");
				out.println("	 m_sort_col = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_sort_column+"'=='DESC'){");
				out.println("	      m_sort_col = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_sort_col = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_sort_col = 'ASC'; ");
				out.println("  }");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_legal_provision_report?chksql=run_report&asat_date="+m_asat_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				//if(m_order_by_type.equals("activated_date")){
				// out.println(m_order_by_type);
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&branch="+m_branch+"&rpt_type="+m_rpt_type+"\";");
				//}else{
				//out.println(m_order_by_type);	
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&order_by_type=\"+m_sort_col+\"&sort_column=\"+m_order_by_type+\"&branch="+m_branch+"&rpt_type="+m_rpt_type+"\";");
				// }
				out.println(" window.location.href=m_url;");
				
				out.println("}");
				
				
				//end by Dineth on 28-04-2009	
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");
			
			    if(m_rpt_type.equals("TERMINATED")){
				m_rpt_type="TERM_CHECK";
			    }
				else if(m_rpt_type.equals("Reject")){
				m_rpt_type="REJECT";
			    }
		        else{
				m_rpt_type=m_rpt_type;
				}
				
				
				/*if(m_branch.equals("")){
				m_branch="%";
				}
				else{
				m_branch=m_branch;
				}*/
				
				
				String branch_find = "";
				String branch_find_sql = " ";
				
				rs=stmt.executeQuery(" "+
					" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_branch+"') "+
					" FROM DUAL "+
					" ");
				
				if(rs.next()){
					branch_find = rs.getString(1);
				}
				
				if(!m_branch.equals("") && m_branch != null){
					branch_find_sql = " AND A.LOCATION_CODE = '"+branch_find+"' ";
				}
				
				
				
				String Sql_data="";
				
				if(m_rpt_type.equals("ALL")){
					
					Sql_data=" SELECT  "+
						" DISTINCT a.application_no,  "+//1
						" a.finance_no,  "+//2
						" a.client_code,  "+//3
						" a.client_full_name, "+//4
						" a.client_address,   "+//5
						" a.TERMS_OF_PAYMENTS , "+ //6
						//" to_char(a.activated_date,'dd-mm-yyyy') activated_date,   "+//7
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
						//" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) ,"+ //23
						" A.LOCATION_CODE, "+
						" A.TRANSACTION_TYPE , "+ //24
						" B.vendor, "+ //25 
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME("+m_schema_name+".AF_CO_GET_BUS_SECT_CODE(A.CLIENT_CODE)),'-'), "+//26
						" "+m_schema_name+".AF_CO_GET_AGREEMENT_TYPE(A.APPLICATION_NO), "+ //27 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_VAT_RATE(A.APPLICATION_NO), "+ //28 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_APP_VAT_ON_RENTAL(A.APPLICATION_NO), "+ //29 //Added By Sandun on 11-06-2009
						//" c.first_name||' '||C.last_name "+//30 //Added By Lalanka on 22-06-2009
						" nvl("+m_schema_name+".AF_CO_GET_EMPLOYEE_NAME(A.APPLICATION_NO),'-') "+//Sandun on 14-07-2009
						" ,"+m_schema_name+".AF_CO_GET_CONTRACT_STATUS(a.client_code,'"+m_from_date+"') "+
						" FROM "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REGISTER a  , "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REG_DET B "+ //"+M_SCHEMA_NAME+".CO_CO_MAS_EMPLOYEE C "+
						" WHERE A.application_no=B.application_no "+
						" AND A.ENT_USER='"+m_username+"' "+
						" AND B.ENT_USER='"+m_username+"' "+
						//" AND  UPPER(A.LOCATION_CODE) LIKE '"+m_branch+"%' "+ // added by udara 01-04-2015
						branch_find_sql +
						//"	AND A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//temp comment
						//"	AND A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ //AND A.STATUS LIKE '%"+m_rpt_type+"%'  "+  //AND C.emp_code(+)="+m_schema_name+".AF_GET_COLL_OFFICER(A.finance_no) "+
						//"   AND "+m_schema_name+".GET_LOCATION_FOR_APP_NUM(A.application_no) LIKE '%"+m_branch+"%' "+ //ADDED MIILNDA 2014-05-02 FOR BRANCH
						//" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";//Added by Dineth on 28-04-2009
						" ORDER BY "+m_order_by_type+" "+m_sort_column+" ";
					
				}
				
				else if(m_rpt_type.equals("ACTIVATED")){
					
					Sql_data=" SELECT  "+
						" DISTINCT a.application_no,  "+//1
						" a.finance_no,  "+//2
						" a.client_code,  "+//3
						" a.client_full_name, "+//4
						" a.client_address,   "+//5
						" a.TERMS_OF_PAYMENTS , "+ //6
						//" to_char(a.activated_date,'dd-mm-yyyy') activated_date,   "+//7 //comment by ns on 06-05-2011
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
						//" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) ,"+ //23
						" A.LOCATION_CODE, "+
						" A.TRANSACTION_TYPE , "+ //24
						" B.vendor, "+ //25 
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME("+m_schema_name+".AF_CO_GET_BUS_SECT_CODE(A.CLIENT_CODE)),'-'), "+//26
						" "+m_schema_name+".AF_CO_GET_AGREEMENT_TYPE(A.APPLICATION_NO), "+ //27 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_VAT_RATE(A.APPLICATION_NO), "+ //28 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_APP_VAT_ON_RENTAL(A.APPLICATION_NO), "+ //29 //Added By Sandun on 11-06-2009
						//" c.first_name||' '||C.last_name "+//30 //Added By Lalanka on 22-06-2009
						" nvl("+m_schema_name+".AF_CO_GET_EMPLOYEE_NAME(A.APPLICATION_NO),'-') "+//Sandun on 14-07-2009
						" ,"+m_schema_name+".AF_CO_GET_CONTRACT_STATUS(a.client_code,'"+m_from_date+"') "+
						" FROM "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REGISTER a  , "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REG_DET B "+ //"+M_SCHEMA_NAME+".CO_CO_MAS_EMPLOYEE C "+
						" WHERE A.application_no=B.application_no "+
						" AND A.ENT_USER='"+m_username+"' "+
						" AND B.ENT_USER='"+m_username+"' "+
						//" AND A.STATUS_TERMI=1 "+
						"	AND A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						"	AND A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ //AND A.STATUS LIKE '%"+m_rpt_type+"%'  "+  //AND C.emp_code(+)="+m_schema_name+".AF_GET_COLL_OFFICER(A.finance_no) "+
						" AND A.TERMI_STATUS like '%"+m_rpt_type+"%'  "+
						//" AND  UPPER(A.LOCATION_CODE) LIKE '%"+m_branch+"%' "+ // commented by udara 01-04-2015
						//" AND  UPPER(A.LOCATION_CODE) LIKE '"+m_branch+"%' "+ // added by udara 01-04-2015
						branch_find_sql +
						//"   AND "+m_schema_name+".GET_LOCATION_FOR_APP_NUM(A.application_no)='"+m_branch+"' "+ //ADDED MIILNDA 2014-05-02 FOR BRANCH
						" ORDER BY "+m_order_by_type+" "+m_sort_column+" ";//Added by Dineth on 28-04-2009
					
				}
				else{
					
					Sql_data=" SELECT  "+
						" DISTINCT a.application_no,  "+//1
						" a.finance_no,  "+//2
						" a.client_code,  "+//3
						" a.client_full_name, "+//4
						" a.client_address,   "+//5
						" a.TERMS_OF_PAYMENTS , "+ //6
						//" to_char(a.activated_date,'dd-mm-yyyy') activated_date,   "+//7 //comment by ns on 06-05-2011
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
						//" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) ,"+ //23 // commented by udara 21-08-2018
						" A.LOCATION_CODE,"+ //23 added by udara 21-08-2018
						" A.TRANSACTION_TYPE , "+ //24
						" B.vendor, "+ //25 
						" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME("+m_schema_name+".AF_CO_GET_BUS_SECT_CODE(A.CLIENT_CODE)),'-'), "+//26
						" "+m_schema_name+".AF_CO_GET_AGREEMENT_TYPE(A.APPLICATION_NO), "+ //27 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_VAT_RATE(A.APPLICATION_NO), "+ //28 //Added By Sandun on 11-06-2009
						" "+m_schema_name+".AF_CO_GET_APP_VAT_ON_RENTAL(A.APPLICATION_NO), "+ //29 //Added By Sandun on 11-06-2009
						//" c.first_name||' '||C.last_name "+//30 //Added By Lalanka on 22-06-2009
						" nvl("+m_schema_name+".AF_CO_GET_EMPLOYEE_NAME(A.APPLICATION_NO),'-') "+//Sandun on 14-07-2009
						" ,"+m_schema_name+".AF_CO_GET_CONTRACT_STATUS(a.client_code,'"+m_from_date+"') "+
						" FROM "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REGISTER a  , "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REG_DET B "+ //"+M_SCHEMA_NAME+".CO_CO_MAS_EMPLOYEE C "+
						" WHERE A.application_no=B.application_no "+
						" AND A.ENT_USER='"+m_username+"' "+
						" AND B.ENT_USER='"+m_username+"' "+
						"	AND TRUNC(A.ACTIVATED_DATE,'DD') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						"	AND TRUNC(A.ACTIVATED_DATE,'DD') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
						//" A.STATUS LIKE '%"+m_rpt_type+"%'  "+  //AND C.emp_code(+)="+m_schema_name+".AF_GET_COLL_OFFICER(A.finance_no) "+
						" A.TERMI_STATUS like '%"+m_rpt_type+"%'  "+
						//" AND  UPPER(A.LOCATION_CODE) LIKE '%"+m_branch+"%' "+ // commented by udara 01-04-2015
						//" AND  UPPER(A.LOCATION_CODE) LIKE '"+m_branch+"%' "+ // added by udara 01-04-2015
						branch_find_sql +
						//"   AND "+m_schema_name+".GET_LOCATION_FOR_APP_NUM(A.application_no)='"+m_branch+"' "+ //ADDED MIILNDA 2014-05-02 FOR BRANCH
						" ORDER BY "+m_order_by_type+" "+m_sort_column+" ";//Added by Dineth on 28-04-2009
				}
				
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
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
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Agreement Register</u></td>"); 
				out.println("</tr >");
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
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				if(more){
					
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center;  }'   >Seq No</td>"); //1
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;  }'   >Product</td>");  //2
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;  }'   >Branch</td>");  //3
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'  onclick=sort_data('1') >Agreement No</td>");  //4
					out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; }'   >Lesee's Name</td>");  //5
					out.println("<td width=\"15%\" STYLE='{font: bold 8pt arial; text-align:center; }'   >Address</td>");  //6
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;  }'   >Payment Structure</td>");  //7
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }' onclick=sort_data('7')>Agreement Date</td>");  //8
					//out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Period Structure</td>");  
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;cursor:hand; }'   onclick=sort_data('8')>Period</td>");  //9
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;cursor:hand; }'   onclick=sort_data('9')>Facility Amount</td>");  //10
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;cursor:hand; }'   onclick=sort_data('10')>Rent</td>");  //11
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Frequency</td>");  //12
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Flat Rate</td>");  //13
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >IRR</td>");  //14
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Vat On Purchase</td>");  //14 Sandun on 11-06-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Vat On Finance</td>");  //14 Sandun on 11-06-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center;cursor:hand; }'   onclick=sort_data('14')>Total Receivable</td>");  //14
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'   onclick=sort_data('15')>Status</td>");  //14
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'   onclick=sort_data('16')>Make</td>");  //15
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'   onclick=sort_data('17')>Asset Category</td>");  //16
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Reg No</td>");  //17
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'   onclick=sort_data('19')>Engine No</td>");  //18
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'   onclick=sort_data('20')>Chassis No</td>");  //19
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'  onclick=sort_data('21') >Year Of Manufacture</td>");  //20
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Supplier</td>");  //21
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Business Sector</td>");  //22 Sandun on 18-02-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Agreement Type</td>");  //23 Sandun on 11-06-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Collection Officer</td>");  //24 Lalanka on 22-06-2009
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand;}'  onclick=sort_data('31')  >Status</td>");  //24 Lalanka on 22-06-2009
					
					out.println("</tr >");
					
				}
				int j=1;
				//int count=0;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				String m_application_no="";
				
				while(more){
					//j=1;
					count=0;
					m_application_no=rs.getString(1);
					
					while(m_application_no.equals(rs.getString(1))){
						
						if (count==0){
							out.println("<tr  bgcolor=\"#FCEBC5\"  >");
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+j+"</td>");  //1
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(24)+"</td>");  //2
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(23)+"</td>");  //3
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>");  //4
							out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(4)+"</td>");  //5
							out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(5)+"</td>");  //6
							
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(6)+"</td>");  //7
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(7)+"</td>");  //8
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(8)+"</td>");  //9
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(9))+"</td>");  //10
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(10))+"</td>");  //11
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(11)+"</td>");  //12
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(12))+"</td>");  //13
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(13))+"</td>");  //14
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(28))+"</td>");  //14
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(29))+"</td>");  //14
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+nf.format(rs.getDouble(14))+"</td>");  //14
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(15)+"</td>");  //14
							
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(16)+"</td>");  //15
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>");  //16
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(18)+"</td>");  //17
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(19)+"</td>");  //18
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(20)+"</td>");  //19
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(21)+"</td>");  //20
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(25)+"</td>");  //21
							out.println("<td width=\"12%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(26)+"</td>");  //22 Sandun on 18-02-2009
							//out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(27))+"</td>"); 
							out.println("<td width=\"12%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(27)+"</td>");  //23 Sandun on 11-06-2009
							out.println("<td width=\"12%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(30)+"</td>");  //24 Lalanka on 22-06-2009
							out.println("<td width=\"12%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(31)+"</td>");  //24 Lalanka on 22-06-2009
							
							out.println("</tr >");			
						}
						else{
							
							out.println("<tr  bgcolor=\"#FCEBC5\"  >");
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"5%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >&nbsp;</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(16)+"</td>");  //15
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>");  //16
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(18)+"</td>");  //17
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(19)+"</td>");  //18
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(20)+"</td>");  //19
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(21)+"</td>");  //20
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(25)+"</td>");  //21
							out.println("<td width=\"12%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(26)+"</td>");  //22  Sandun on 18-02-2009
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); //Lalanka on 22-06-2009
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); //Lalanka on 22-06-2009
							out.println("</tr >");		
							
						}
						
						count+=1;
						j+=1;
						more=rs.next();
						if(!more){break;}
					}
					
				}
				
				
				
				out.println("</table>");		 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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
