// DEVELOP BY : UDARA ON 10-10-2013

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_rental_slab_vs_no_of_case extends javax.servlet.http.HttpServlet { 
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn= null;
		Statement stmt= null,stmt1= null,stmt2= null,stmt3= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null,rs3= null;
		String m_chksql= null;
		
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//String m_username=m_sn_methods.username;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2); 
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement();
			CallableStatement callstmt1 =null;
			String m_username 	= m_sn_methods.username;
			//new report
			if(m_chksql.equals("run_report")){ 
				
				String m_branch = req.getParameter("branch");
				String m_date=req.getParameter("date");//added milinda
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".SAVE_RENTAL_SLAB_RPT(:1,:2,:3);END;"); 
					callstmt1.setString(1,m_branch);
					callstmt1.setString(2,m_username);	
					callstmt1.setString(3,m_date);	//added milinda 2013-11-11
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
				
			}
			
			
			if(m_chksql.equals("main_page")){
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Rental Slab Vs. No. Of Cases Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("var m_sav_msg='';");
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
				out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				//To validate from date & to date
				out.println("function validate_date(){");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println("   if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
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
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				
				out.println("function validate_data(){"); 
				out.println("	return true;"); 
				out.println("}"); 			
				
				out.println("function before_submit(){ "); 
				out.println("} "); 
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case=main_page';"); 
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
				out.println("	help_box.innerHTML=\" Rental Slab Vs. No. Of Cases Report - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" Rental Slab Vs. No. Of Cases Report \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"NEW\"){"); 
				out.println("			new_window();"); 
				out.println("		}"); 
				out.println("		else if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("		if(m_val==\"NEW\"){");
				out.println("			document.Form1.hid_status.value=\"New\";"); 
				out.println("		}");
				out.println("		else if(m_val==\"EDIT\"){");  
				out.println("			document.Form1.hid_status.value=\"Edit\";");  
				out.println("		}");
				out.println("		else if(m_val==\"DACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("		}");
				out.println("		else if(m_val==\"RACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("		}");
				out.println("		else{");  
				out.println("			document.Form1.hid_status.value=\"\";");  
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function get_display_msg(){"); 
				out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("		m_sav_msg=\"Save\";"); 
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
				out.println("		m_sav_msg=\"Modify\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
				out.println("		m_sav_msg=\"Deactivate\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
				out.println("		m_sav_msg=\"Reactivate\";");  
				out.println("	}");
				out.println("	else{");  
				out.println("		m_sav_msg=\"\";");  
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				
				out.println("function help_update() {"); 
				out.println(" document.Form1.hid_help_type.value=\"1\";"); 
				out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql\";"); 
				out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
				out.println(" HelpBox('1','10','0');"); 
				out.println("}"); 
				
				
				out.println("function help_update_value_assign_1() {"); 
				out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("}");
				
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("		if(oBj.valout[1] !=\"Close\"){"); 
				out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("				if(oBj.valout[1]!=\"Next\"){"); 
				out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("						help_value_assign_branch_id();"); 
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
				
				out.println("		else{	"); 
				out.println("			if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("				clear_branch_id();"); 
				out.println("			}"); 
				out.println("		}	"); 
				
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("		HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				
				out.println("function help_button_branch_id() {"); 
				out.println(" document.Form1.hid_help_type.value='1';");
				out.println(" m_criteria = document.Form1.TXT_BRANCH_ID.value+\"@\"+\"\"+\"@\";");
				out.println(" m_sql = 'm_help_TXT_BRANCH_sql';"); 
				out.println(" HelpBox('1','10','0');");
				out.println("}"); 
				
				out.println("function help_value_assign_branch_id() {"); 
				out.println(" 	document.Form1.TXT_BRANCH_ID.value=oBj.valout[2];"); 
				out.println("}");
				
				out.println("function clear_branch_id() {"); 
				out.println(" 	document.Form1.TXT_BRANCH_ID.value='';"); 
				out.println("}");
				
				
				// end by udara on 27-02-2013
				//report section 
				
				out.println("function run_report() {");
				out.println("   m_branch = document.Form1.TXT_BRANCH_ID.value; ");
				out.println("	m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");//added milinda 2013-11-11
				
				//out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case?chksql=run_report&branch=\"+m_branch;"); // added by udara on 27-02-2013
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case?chksql=run_report&branch=\"+m_branch+\"&date=\"+m_date;"); // added by udara on 27-02-2013-mod by milinda
				
				out.println("   set_timer_actions();");
				out.println("	load_interface(m_url,'NORM');");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("	clearTimeout(timerID);");
				out.println("	m_table.innerHTML=\"\";");
				out.println("   m_branch = document.Form1.TXT_BRANCH_ID.value; ");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");//added milinda 201-11-11
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case?chksql=print_report2&branch=\"+m_branch+\"&date=\"+m_date;"); 
				out.println("	window.open(m_url);");
				out.println("}");
				
				//ADDED MILINDA 2013-11-11
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				
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
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_lock();load_sysdate();'>"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<input type=hidden name='hid_date' value=\"\">");
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Rental Slab Vs. No. Of Cases Report </td>"); 
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
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
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
				
				/*
				out.println("<tr>"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
				out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\"  >"); //value=\"01\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >"); //value=\"04\"
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"   >");	 //value=\"2007\"
				out.println("</td> ");
				out.println("</tr>");
				
				out.println("<tr>");				
				out.println(" <td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
				out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
				out.println("</td> ");
				out.println("</tr>");
				*/
				
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_BRANCH_ID'  class=div_input>Location Code </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_ID' maxlength='15' style='{width=150px}' size='10' onblur=\"help_button_branch_id()\"> &nbsp; <input class='but_input' type='button' name='BUT_BRANCH_ID' value=\"Help\" onClick=\"help_button_branch_id()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				//added milinda 2013-11-11
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</tr>");
				//end
				out.println("<tr>");
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input> Generate Report </DIV></td>"); 
				out.println("<td width='40%' > ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</td> ");
				out.println("</tr>");
				
				
				out.println("<tr>");//Added By Sandun on 16-09-2009
				out.println("<td colspan=4>&nbsp;</td>");
				out.println("<td>");
				out.println("</td>");
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
				out.println("<br>"); 
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}			
			
			else if(m_chksql.equals("print_report2")){
				
				String m_branch_id = req.getParameter("branch_id");
				String m_user=req.getParameter("user");
				String m_date="";//added milinda 2013-11-11
				
				out.println("<HTML><HEAD><TITLE> Rental Slab vs No of cases </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function print_report2_drill(m_slab_no){");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_rental_slab_vs_no_of_case?chksql=print_report2_drill&slab_no=\"+m_slab_no;"); 
				out.println("	window.open(m_url);");
				out.println("}");
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				
				out.println("<TABLE  WIDTH='100%'  align='Center'>");
				out.println("<TR><TD align='Center' ><B>  Rental Slab vs No of Cases. </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				
				out.println("<table width='100%' class='table' border=1 >");	
				
				out.println("<tr>");
				out.println("<td width='1%' align=right ><b> No. </b></td>");
				out.println("<td width='5%'  ><b> Monthly Rental Slab (Rs.) </b></td>"); 			
				out.println("<td width='5%' align=right ><b> Number of Cases </b></td>"); 
				out.println("<td width='5%' align=right ><b> Total Rental Value (Rs) </b></td>"); 
				out.println("</tr>"); 
				
				int count = 0;
				double total_amount = 0;
				int total_no_of_cas=0;
				
				rs1= stmt1.executeQuery(" "+	
					" SELECT "+ 						
					" DECODE(A.SLAB_NO, "+
					" '1','0 - 5000', "+
					" '2','5001  - 10000', "+
					" '3','10001 - 15000', "+
					" '4','15001 - 20000', "+
					" '5','20001 - 25000', "+
					" '6','25001 - 30000', "+
					" 'More Than 30000'    "+
					" ), "+ // 1							
					" COUNT(SLAB_NO),     "+ // 2
					" SUM(RENTAL_AMOUNT), "+ // 3
					" A.SLAB_NO "+ // 4
					" FROM "+m_schema_name+".RENTAL_SLAB_RPT A  "+
					" WHERE   A.ENT_USER = '"+m_username+"' "+
					//" AND A.ACTIVATED_DATE < TRUNC(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MM') "+//added milinda
					"  GROUP BY SLAB_NO	 "+	
					"  ORDER BY A.SLAB_NO "+ 
					" ");
				
				
				while(rs1.next()){
					
					count = count + 1;
					total_no_of_cas=total_no_of_cas + rs1.getInt(2);//ADDEDE MILINDA 2013-11-11
					total_amount = total_amount + rs1.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='1%' align=right > "+count+" </td>");
					out.println("<td width='5%'  > "+rs1.getString(1)+" </td>"); 
					out.println("<td width='5%' align=right style='cursor:hand' onclick=\"print_report2_drill('"+rs1.getInt(4)+"')\"  ><u> "+rs1.getInt(2)+" </u></td>"); 
					out.println("<td width='5%' align=right > "+nf.format(rs1.getDouble(3))+" </td>"); 
					out.println("</tr>"); 
					
				}
				
				out.println("<tr>");
				out.println("<td width='1%' > &nbsp; </td>");
				out.println("<td width='5%' > &nbsp; </td>"); 			
				out.println("<td width='5%' align=right ><b>"+total_no_of_cas+"</b></td>"); 
				out.println("<td width='5%' align=right ><b> "+nf.format(total_amount)+" </b></td>"); 
				out.println("</tr>"); 
				
				
				out.println("</table>"); 
				
			}
			
			
			else if(m_chksql.equals("print_report2_drill")){
				
				String m_slab_no = req.getParameter("slab_no");
				
				out.println("<HTML><HEAD><TITLE> Rental Slab vs No of Cases Drill </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>");
				
				out.println("<TABLE  WIDTH='100%'  align='Center'>");
				out.println("<TR><TD align='Center' ><B>  Rental Slab vs No of Cases Drill. </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				
				out.println("<table width='100%' class='table' border=1 >");	
				
				out.println("<tr>");
				out.println("<td width='1%' align=right ><b> No. </b></td>");
				out.println("<td width='5%' align=right ><b> Monthly Rental Slab (Rs.) </b></td>"); 			
				out.println("<td width='5%' ><b> Finance No. </b></td>"); 
				out.println("<td width='5%' align=right ><b> Rental Value (Rs) </b></td>"); 
				out.println("</tr>"); 
				
				int count = 0;
				double total_amount = 0;
				
				
				rs1= stmt1.executeQuery(" "+	
					" SELECT "+ 						
					" DECODE(A.SLAB_NO, "+
					" '1','0 - 5000', "+
					" '2','5001  - 10000', "+
					" '3','10001 - 15000', "+
					" '4','15001 - 20000', "+
					" '5','20001 - 25000', "+
					" '6','25001 - 30000', "+
					" 'More Than 30000'    "+
					" ), "+ // 1							
					" A.FINANCE_NO,    "+ // 2
					" A.RENTAL_AMOUNT    "+ // 3
					" FROM "+m_schema_name+".RENTAL_SLAB_RPT A  "+
					" WHERE   A.ENT_USER = '"+m_username+"' "+
					" AND 	A.SLAB_NO     = '"+m_slab_no+"' "+
					" ORDER BY A.FINANCE_NO "+ 
					" ");
				
				
				while(rs1.next()){
					
					count = count + 1;
					total_amount = total_amount + rs1.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='1%' align=right > "+count+" </td>"); 
					out.println("<td width='5%' align=right > "+rs1.getString(1)+" </td>"); 
					out.println("<td width='5%'  > "+rs1.getString(2)+" </td>"); 
					out.println("<td width='5%' align=right > "+nf.format(rs1.getDouble(3))+" </td>"); 
					out.println("</tr>"); 
					
				}
				
				out.println("<tr>");
				out.println("<td width='1%' > &nbsp; </td>");
				out.println("<td width='5%' > &nbsp; </td>"); 			
				out.println("<td width='5%' > &nbsp; </td>"); 
				out.println("<td width='5%' align=right ><b> "+nf.format(total_amount)+" </b></td>"); 
				out.println("</tr>"); 
				
				
				out.println("</table>"); 
				
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