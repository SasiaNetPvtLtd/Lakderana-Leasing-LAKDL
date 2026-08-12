// DEVELOP BY : SANDUN FOR OFSCL LEASING-CREDIT  
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Broker_Report extends javax.servlet.http.HttpServlet { 
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
		
		java.text.NumberFormat nf;
		nf=null;
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
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
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
						
						callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_CASE_SUMMARY(:1,:2,:3,:4);END;");
						callstmt1.setString(1,m_from_date);
						callstmt1.setString(2,m_to_date);
						callstmt1.setString(3,m_username);
						callstmt1.setString(4,m_location_id); 
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
				out.println("<TITLE>Broker Detail Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				//pra-----------------**
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_BROKER_DETAILS_sql\";");  // thamali
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				//--------------------**
				
				
				
				
				
				out.println("function run_report() {");
				out.println("	if(validate_date()){");
				out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
				out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
				out.println("		m_rpt_type='';");
				//	out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=run_report&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");  //comment by Prabash on 09-05-2012---
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=run_report&rpt_type=\"+m_rpt_type+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");  // added by Prabash on 09-05-2012--- insert location 
				//out.println("   set_timer_actions();");
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
				
				out.println("	if(document.Form1.TXT_LOCATION_CODE.value!=\"\") {"); // thamali
				out.println("		m_rpt_type=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("   }");
				out.println("   else{");
				out.println("		m_rpt_type='ALL';");
				out.println("   }");
				
				out.println("		if(validate_date()) {");
				//Modified by Dineth on 28-04-2009
				out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=load_agreement_regi&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");//MOD BY LALANKA ON 07-11-2009
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Credit Process - Broker Detail Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Credit Process - Broker Detail Report \";"); 
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
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Broker Detail Report</td>"); 
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
				out.println("<table class='table' width='100%' style='display:none'  >"); 
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
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >"); //<input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\">	
				out.println("</td> ");
				out.println("<td width='*%'></td>"); 
				
				out.println("</table>");
				
				out.println("<table class='table' width='100%'>"); 
				// added by prabash on09-05-2012----**
				out.println("</tr >");
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Broker Code *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"View Report\" onClick=\"view_details()\"></td>"); 
				out.println("</tr>"); 
				//----------------------------------**
				
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
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				String m_application_no="";
				
				
				stmt = conn.createStatement ();
				//stmt2 = conn.createStatement ();
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Broker Report </TITLE>"); 
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
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Broker_Report?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				out.println(" window.location.href=m_url;");
				
				out.println("}");
				
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				
				//end by Dineth on 28-04-2009	
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				if(m_rpt_type.equals("ALL")){
					
					Sql_data=" SELECT  "+
						" B.FINANCE_NO,  "+//1
						" A.BROKER_CODE, "+
						" A.FIRST_NAME || ' ' || A.LAST_NAME, "+
						" A.ID_NO, "+
						" A.ADDRESS1, "+
						" A.ADDRESS2, "+
						" A.LOCATION_CODE, "+
						" "+m_schema_name+".AF_CO_GET_APP_CHARGES(B.APPLICATION_NO,'BROKERCOMM') "+
						
						" FROM "+m_schema_name+".AF_CO_MAS_BROKER a  , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						" WHERE A.BROKER_CODE=B.LEAD_SOURCE_NAME "+
						" AND B.LEAD_SOURCE_CATEGORY = 'BROKER' "+
						"	AND TRUNC(B.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara on 14-11-2012
						"	AND TRUNC(B.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+  // added by udara on 14-11-2012
						"  "; //ORDER BY "+m_sort_column+" "+m_order_by_type+"
					
				}
				else{
					Sql_data=" SELECT  "+
						" B.FINANCE_NO,  "+//1
						" A.BROKER_CODE, "+ //2
						" A.FIRST_NAME || ' ' || A.LAST_NAME, "+//3
						" A.ID_NO, "+ //4
						" A.ADDRESS1, "+//5
						" A.ADDRESS2, "+ //6
						" A.LOCATION_CODE, "+ //7
						" "+m_schema_name+".AF_CO_GET_APP_CHARGES(B.APPLICATION_NO,'BROKERCOMM'), "+ //8
						" A.MOBILE_NO "+ //9
						" FROM "+m_schema_name+".AF_CO_MAS_BROKER a  , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						" WHERE A.BROKER_CODE=B.LEAD_SOURCE_NAME  AND  A.BROKER_CODE = '"+m_rpt_type+"' "+
						" AND B.LEAD_SOURCE_CATEGORY = 'BROKER' "+
					//	"	AND TRUNC(B.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara on 14-11-2012
					//	"	AND TRUNC(B.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+  // added by udara on 14-11-2012
						"  "; //ORDER BY "+m_sort_column+" "+m_order_by_type+"
					
				}
				
				//	out.println(Sql_data);
				rs=stmt.executeQuery(Sql_data);
				//out.println(Sql_data);
				
				boolean more=rs.next();
				int count=0;
				
				double tot1=0;
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><B>LAKDERANA INVESTMENTS LIMITED</B></td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   >Broker Detail Report - from "+m_from_date+" to "+m_to_date+" </td>"); 
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
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); 
				if(more){
					
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"3%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >No</td>");  //1
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Broker Code</td>");  //3
					out.println("<td width=\"9%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Broker Name</td>");  //4
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >NIC</td>");  //5
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Address </td>");  //6
					//out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Address 2</td>");  //7
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Location</td>");  //8
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >Telephone</td>");  //9
					out.println("<td width=\"10%\"  STYLE='{font: bold 8pt arial; text-align:center; }'   >No of Contracts</td>");  //2
					out.println("</tr >");
					
				}
				int j=1;
				
				while(more){
					
					
					out.println("<tr  bgcolor=\"#FCEBC5\"  >");
					out.println("<td width=\"3%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+j+"</td>");  //1
					//out.println("<td width=\"10%\"  STYLE='{font: 8pt arial; text-align:left; cursor:hand;  }'  onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>");  //4
					
					out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(2)+"</td>");  //2
					out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(3)+"</td>");  //3
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(4)+"</td>");  //4
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(5)+" "+rs.getString(6)+"</td>");  //5
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(7)+"</td>");  //6
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   >"+rs.getDouble(9)+"</td>");  //7
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   >"+rs.getString(1)+"</td>");  //8
					out.println("</tr >");			
					
					tot1=tot1+rs.getDouble(8);
					
					count = count+ 1;
					j = j+ 1;
					more=rs.next();
					if(!more){break;}
				}
				
				
				out.println("<tr  bgcolor=\"#FCEBC5\"  >");
				out.println("<td width=\"3%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //4
				out.println("<td width=\"10%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //17
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"9%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //4
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //17
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
				out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:left;  }'   ></td>");  //8
			//	out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:right; }'   ><b>"+nf.format(tot1)+"</b></td>");  //14
				out.println("</tr >");			
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
