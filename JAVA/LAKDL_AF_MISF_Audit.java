// DEVELOP BY : SANDUN FOR OFSCL LEASING-CREDIT  
// DATE:25-08-2008

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
public class LAKDL_AF_MISF_Audit extends javax.servlet.http.HttpServlet { 

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
			
			stmt=conn.createStatement();
			
				if(m_chksql.equals("run_report")){ 
			
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_rpt_type=req.getParameter("rpt_type");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_AGR_REGISTER(:1,:2,:3,:4);END;");
				callstmt1.setString(1,m_from_date);
				callstmt1.setString(2,m_to_date);
				callstmt1.setString(3,m_rpt_type);
				callstmt1.setString(4,m_username);
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
			out.println("<TITLE>Audit Reports</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			
			out.println("function run_report() {");
  		out.println("if (validate_date()) {"); // added by Chatura Jayawardena
			out.println("	if(document.Form1.TXT_RPT_TYPE.value!=\"ALL\"){");
			out.println("	 if(document.Form1.TXT_USER.value!=\"\" && (document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\")){");
			out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
			out.println("		m_user_type=document.Form1.TXT_USER.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=run_report&rpt_type=\"+m_rpt_type+\"&user=\"+m_user_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&rpt_name=\"+document.Form1.TXT_RPT_TYPE.options[document.Form1.TXT_RPT_TYPE.selectedIndex].text;"); 
			//out.println("   set_timer_actions();");
			out.println("		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=490,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			//out.println("		load_interface(m_url,'NORM');");
			out.println("	 }else if (document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\") {");
			out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
			out.println("		m_user_type=document.Form1.TXT_USER.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=run_report&rpt_type=\"+m_rpt_type+\"&user=\"+m_user_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&rpt_name=\"+document.Form1.TXT_RPT_TYPE.options[document.Form1.TXT_RPT_TYPE.selectedIndex].text;"); 
			//out.println("   set_timer_actions();");
			out.println("		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=490,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			
			out.println("	 }else if (document.Form1.TXT_USER.value!=\"\") {");
			out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
			out.println("		m_user_type=document.Form1.TXT_USER.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=run_report&rpt_type=\"+m_rpt_type+\"&user=\"+m_user_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&rpt_name=\"+document.Form1.TXT_RPT_TYPE.options[document.Form1.TXT_RPT_TYPE.selectedIndex].text;"); 
			//out.println("   set_timer_actions();");
			out.println("		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=490,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			
			out.println("	 }else{");
			out.println("		m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value;");
			out.println("		m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("		m_rpt_type=document.Form1.TXT_RPT_TYPE.value;");
			out.println("		m_user_type=document.Form1.TXT_USER.value;");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit_Date?chksql=run_report&rpt_type=\"+m_rpt_type+\"&user=\"+m_user_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&rpt_name=\"+document.Form1.TXT_RPT_TYPE.options[document.Form1.TXT_RPT_TYPE.selectedIndex].text;"); 
			//out.println("   set_timer_actions();");
			out.println("		window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=490,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			
			out.println("	 }");
			out.println("	}");
			out.println("}");
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
						
			out.println("function validate_date(){"); // added by Chatura Jayawardena
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");
		      
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
			out.println("		if(validate_date()) {");
			//Modified by Dineth on 28-04-2009
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"\";");
			//End by Dineth on 28-04-2009
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Audit?chksql=main_page';"); 
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
		  
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Finance - Audit Reports</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='2%' align='center'></td>");
			//out.println("<td width='6%'></td>");  
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
			out.println("<td width='20%' ><SELECT onchange=\"\" name=\"TXT_RPT_TYPE\" class=\"txt_input\" style=\"width:250px;\" > ");
			//out.println("<OPTION value=\"ALL\" SELECTED>All</OPTION>");//Added By Sandun on 23-12-2008
			
		/*	rs=stmt.executeQuery(" SELECT INITCAP(DISPLAY_NAME),UPPER(SCREEN_NAME) SCREEN_NAME "+
			                     " FROM   "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
													 " WHERE  DIVISION_CODE='AD' AND "+
													 "        DISPLAY_STATUS='Y' AND "+
													 "        SCREEN_NAME <>'AF_AD_USER_CHANGE_PASSWORD' "+	
													 " ORDER  BY OPTION_NAME,ROW_ID "+ */
													 /*" UNION ALL "+
													 " SELECT 'Login Details','LOGIN_DETAILS' "+
													 " FROM   DUAL "*/ //"");     //commented by Kanchana on 2016-08-29
			
			//Modified by Kanchana on 2016-08-29
			rs=stmt.executeQuery(" SELECT INITCAP(DISPLAY_NAME),UPPER(SCREEN_NAME) SCREEN_NAME "+
			                     " FROM   "+m_schema_name+".CO_CO_MAS_USER_SCREEN  		"+
													 " WHERE  DIVISION_CODE='AD' AND 	"+
													 "        DISPLAY_STATUS='Y' AND 	"+
													// "        SCREEN_NAME <>'AF_AD_USER_CHANGE_PASSWORD' "+	
													 " UPPER(SCREEN_NAME) IN ('AF_AD_CUSTOMER_CATEGORY','AF_AD_ITEM_CATEGORY','AF_AD_ITEM_SUB_CATEGORY','AF_AD_MODEL_CREATION','AF_AD_YARD_CREATION','AF_AD_LAWYER','AF_AD_SEIZER','AF_AD_MISSING_VEHICLE', 	"+  //,'AF_AD_CLIENT_CLASSIFICATION'
													 " 'FA_CO_MAS_PRODUCT','FA_CO_MAS_FEES','FA_CO_MAS_PRODUCT_CATEGORY','AF_AD_DISCOUNT_RATE','AF_AD_TERMINATION_RATE','FA_CO_MAS_PROD_FEATURES','FA_CO_MAS_COLLECTION_ROUTES','FA_CO_MAS_COLLECTION_ROUTES_ASSIGN', 				"+
													 " 'AF_AD_SUB_DIVISION','AF_AD_LOCATION','AF_AD_MAKE_CREATION','AF_AD_ENGINE_CAPACITY','AF_CO_MAS_REFERNCE_CONDITIONS','AF_AD_PHONE_AREA_CODE','AF_AD_BRANCH','AF_AD_USER_LIST','AF_AD_DIVISION','AF_AD_LEAD_SOURCE_CATEGORY', 	"+
													 " 'AF_AD_ENGINE_CAPACITY','AF_AD_APPLICABLE_CHARGES','AF_AD_CLIENT_GROUP','AF_AD_DOCUMENT_APPLICABLE','AF_AD_SCORE_CATEGORY','AF_AD_RMV_AGENTS','AF_AD_LOAN_FACILITIES','AF_AD_FOLLOW_UP_ACTION','AF_AD_ASSIGN_MEMBERS', 		"+
													 " 'AF_AD_CLIENT_CREATION','AF_CO_MAS_EXPOSURE_CODE','AF_AD_CURRENCY','AF_AD_DISTRICT','AF_AD_BANK','AF_AD_PROVINCE','AF_AD_SUB_BUSINESS_SECTORS','AF_AD_EMPLOYEE','AF_AD_ASSIGN_SUB_TEAMS','AF_AD_REPAYMENT_INERVAL', 			"+
													 " 'AF_AD_SUB_CHARGE','AF_AD_TRANSACTION','AF_AD_ASSET_USAGE_TYPE','AF_AD_GARAGE','AF_AD_INCOME_EXPENCE_TYPE','AF_AD_LICENCEE_SETTLEMENT','FA_CO_MAS_CLIENT_GROUPS','AF_CO_MAS_APPLICATION_STATUS','AF_AD_SCORE_RATING', 		"+
													 " 'AF_AD_ODI_ALTERATION','AF_AD_USER','AF_AD_NATIONALITY','AF_AD_BUSINESS_SECTOR','AF_AD_CITY','AF_AD_LOCATION','AD_AD_SUB_TEAMS','AF_AD_PRICE_DEFAULT_VALUE','AF_AD_MAINTANANCE','AF_AD_CONDITION_OF_ASSET',					"+
													 " 'AF_AD_VENDOR_CREATION','AF_AD_VALUER','AF_AD_SCORE_SUB_CATEGORY','AF_AD_ACCOUNT_CODE','AF_AD_LOAN_FACILITIES_ASSIGN','FA_CO_MAS_FEE_STRUCT','AF_AD_CLIENT_COMMENT_ADDITION','AF_CO_MAS_TARGET_MONTHS',						"+
													 " 'AF_AD_USER_APPROVAL2','AF_AD_HOLIDAY','AF_AD_POSTAL_CODE','AF_AD_COUNTRY','AF_AD_EMPLOYEE_STAGE2','AF_AD_BROKER','AF_AD_MODEL_CREATION','AF_AD_MILEAGE','AF_AD_APPLICABLE_FIELDS','AF_AD_BROKER_COMMENTS',					"+
													 " 'AF_AD_AUTHORIZATION_LIMITS','AF_AD_EAR_TEM_CHARGE','AF_AD_VARIABLE_INTEREST_BASE','AF_AD_VAR_BASE_RATE_NEW','AF_AD_SCREEN_ORDER','AF_AD_VAR_BASE_RATE','AF_CO_MAS_RENTAL_TARGET_SETUP', 									"+
													 " 'AF_AD_LEGAL_ENTITY','AF_AD_GUARANTOR_CREATION','AF_AD_AREA','AF_AD_FUEL_TYPE','AF_AD_DESIGNATION','AF_AD_TEAM','AF_AD_INITIATION_TYPE','AF_CO_PRO_VAT_ON_RENTAL','AF_AD_MAKE_CREATION',										"+
													 " 'AF_AD_DOCUMENTS_REQUIRED','AF_CO_MAS_CHEQ_RET_NARRATIONS','AF_AD_LEASE_PROCESS_STAGE','AF_CO_MAS_ODI_TARGET_SETUP','AF_AD_EMPLOYEE_STAGE1','AF_AD_CHARGES','AF_AD_TRANSACTION_SUB_TYPE',									"+
													 " 'AF_AD_OPTION','AF_CO_MAS_CR_DB_NARRATIONS','AF_AD_CR_SCORE_MODEL','AF_AD_VENDOR_BACKLIST','FA_CO_MAS_FACTOR_DEFAULT_VALUES','AF_AD_CLIENT_CRIB','AF_CO_MAS_BUSINESS_VOL_SETUP','AF_AD_INQUIRY_STATUS','AF_AD_FIELDS',		"+
													 " 'AF_AD_ASSIGN_PAYEE_SUB_CHARGES','AF_AD_USER_APPROVAL1','AF_AD_LEASE_PROCESS_STAGE','AF_AD_GROUP_USER','AF_AD_CLIENT_BACKLIST','AF_CO_MAS_REFERENCE_ADMIN','LOGIN_DETAILS','AF_AD_PASSWORD_POLICY' ,'AF_AD_SCHEDULE_DETAILS' 													"+
													 " ) 							"+
													 " ORDER  BY OPTION_NAME,ROW_ID "+ 
													 /*" UNION ALL "+
													 " SELECT 'Login Details','LOGIN_DETAILS' "+
													 " FROM   DUAL " */ "");
														
														
														
			while(rs.next()){											
			  out.println("<OPTION value=\""+rs.getString(2)+"\" >"+rs.getString(1)+"</OPTION>");
			}
			out.println("<OPTION value=\"LOGIN_DETAILS\" >Login Details</OPTION>");
			out.println("</SELECT></td>");
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			
			// Commented by Udara Somathilake on 22-01-2010
			/*
			out.println("<td width='15%' ><DIV id='DIV_TXT_DIVISION_CODE'  class=div_input>Transaction Report Type</DIV></td>"); 
			out.println("<td width='20%' ><SELECT onchange=\"\" name=\"TXT_RPT_TYPE_TRN\" class=\"txt_input\" > ");
			//out.println("<OPTION value=\"ALL\" SELECTED>All</OPTION>");//Added By Sandun on 23-12-2008
			
			rs=stmt.executeQuery(" SELECT INITCAP(DISPLAY_NAME),UPPER(SCREEN_NAME) SCREEN_NAME "+
			                     " FROM   "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
													 " WHERE  DIVISION_CODE='AF' AND "+
													 "        DISPLAY_STATUS='Y' AND "+
													 "        SCREEN_NAME <>'AF_AD_USER_CHANGE_PASSWORD' "+	
													 " ORDER  BY OPTION_NAME,ROW_ID");
														
			while(rs.next()){											
			  out.println("<OPTION value=\""+rs.getString(2)+"\" >"+rs.getString(1)+"</OPTION>");
			}
			out.println("</SELECT></td>");
			*/
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			//out.println("</table>");
			out.println("<tr >"); 
			out.println("<td width='15%' >User</td>"); 
			//out.println("<td width='20%' ><input class=\"txt_input\" type=\"text\" name=TXT_USER maxlength=\"2\" size=\"2\" value=\"\" ></td>"); // commented by udara 05-12-2025
			out.println("<td width='20%' ><input class=\"txt_input\" type=\"text\" name=TXT_USER maxlength=\"10\" size=\"10\" value=\"\" ></td>"); // added by udara 05-12-2025
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			//out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" onblur='validate_date();' >");	
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
			out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" onblur='validate_date();'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\"><!--input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\"-->");	
			out.println("</td> ");
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
		//Added by Dineth on 28-04-2009
		m_sort_column = req.getParameter("sort_column");
		m_order_by_type = req.getParameter("order_by_type");
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
			  out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Agreement_Register?chksql=load_agreement_regi&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" \";");
				out.println(" window.location.href=m_url;");
				
				out.println("}");
			
			
			//end by Dineth on 28-04-2009	
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	

			String Sql_data="";

				Sql_data=" SELECT  "+
				" DISTINCT a.application_no,  "+//1
				" a.finance_no,  "+//2
				" a.client_code,  "+//3
				" a.client_full_name, "+//4
				" a.client_address,   "+//5
				" a.TERMS_OF_PAYMENTS , "+ //6
				" to_char(a.activated_date,'dd-mm-yyyy') activated_date,   "+//7
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
				" "+m_schema_name+".AF_CO_GET_AGREEMENT_TYPE(A.APPLICATION_NO), "+ //27 //Added By Sandun on 11-06-2009
				" "+m_schema_name+".AF_CO_GET_VAT_RATE(A.APPLICATION_NO), "+ //28 //Added By Sandun on 11-06-2009
				" "+m_schema_name+".AF_CO_GET_APP_VAT_ON_RENTAL(A.APPLICATION_NO), "+ //29 //Added By Sandun on 11-06-2009
				//" c.first_name||' '||C.last_name "+//30 //Added By Lalanka on 22-06-2009
				" nvl("+m_schema_name+".AF_CO_GET_EMPLOYEE_NAME(A.APPLICATION_NO),'-') "+//Sandun on 14-07-2009
				" FROM "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REGISTER a  , "+m_schema_name+".AF_MISF_TBD_AGREEMENT_REG_DET B "+ //"+M_SCHEMA_NAME+".CO_CO_MAS_EMPLOYEE C "+
				" WHERE A.application_no=B.application_no "+
			  " AND A.ENT_USER='"+m_username+"' "+
				" AND B.ENT_USER='"+m_username+"' "+
				"	AND A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				"	AND A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+  //AND C.emp_code(+)="+m_schema_name+".AF_GET_COLL_OFFICER(A.finance_no) "+
				" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";//Added by Dineth on 28-04-2009
			
			
			rs=stmt.executeQuery(Sql_data);
			
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
			out.println("<td width=\"5%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Seq No</td>"); //1
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Product</td>");  //2
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Branch</td>");  //3
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement No</td>");  //4
			out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Lesee's Name</td>");  //5
			out.println("<td width=\"15%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Address</td>");  //6
			
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Payment Structure</td>");  //7
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }' onclick=\"sort_data('activated_date');\"  >Agreement Date</td>");  //8
			//out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period Structure</td>");  
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period</td>");  //9
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Facility Amount</td>");  //10
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Rent</td>");  //11
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Frequency</td>");  //12
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Flat Rate</td>");  //13
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >IRR</td>");  //14
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Vat On Purchase</td>");  //14 Sandun on 11-06-2009
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Vat On Finance</td>");  //14 Sandun on 11-06-2009
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Total Receivable</td>");  //14
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Status</td>");  //14
			
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Make</td>");  //15
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Category</td>");  //16
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Reg No</td>");  //17
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Engine No</td>");  //18
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Chassis No</td>");  //19
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Year Of Manufacture</td>");  //20
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Supplier</td>");  //21
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Business Sector</td>");  //22 Sandun on 18-02-2009
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement Type</td>");  //23 Sandun on 11-06-2009
			out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Collection Officer</td>");  //24 Lalanka on 22-06-2009
			
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
