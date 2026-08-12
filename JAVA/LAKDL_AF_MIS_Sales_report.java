// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:21-07-2007
              
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MIS_Sales_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
				
				
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Sales Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
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
			 
			//To validate from date & to date
			out.println("function validate_date(){");
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
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_TEAM_ID.value==\"\"){  "); 
			out.println("DIV_TXT_TEAM_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

      
			out.println("function makeRequest_detail_sales() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		if(validate_data()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_Sales_report?chksql=load_sales_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&team_id=\"+document.Form1.TXT_TEAM_ID.value+\"\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=10,top=110,width=975,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("   }");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
		

			out.println("function before_submit(){ "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MIS_Sales_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MIS_Sales_report?chksql=main_page';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
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
			out.println("	help_box.innerHTML=\" Sales Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Sales Report \";"); 
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
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_REPORT_sql\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_3() {"); 
			out.println("		document.Form1.TXT_DEBTOR_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_TEAM_ID_team_sql\";"); 			
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','3');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_TEAM_ID.value=oBj.valout[2];"); 
			/*out.println("    document.Form1.TXT_TEAM_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TEAM_HEAD.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_SUB_DIVISION_CODE.value=oBj.valout[6];");*/ 
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
			out.println("					if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("						help_update_value_assign_99();"); 
	  	out.println("					}"); 
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
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Sales Report </td>"); 
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
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Sales Report\" onClick=\"makeRequest_detail_sales()\">");	
			out.println("</td> ");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='11%' ><DIV id='DIV_TXT_TEAM_ID'  class=div_input><b>Team Id *</b></DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEAM_ID' maxlength='10' size='10' onblur=\"\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
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
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			
			else if(m_chksql.equals("load_third_level")){
			
			  String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_user=req.getParameter("user");
				
				rs1= stmt1.executeQuery(" SELECT INITCAP("+m_schema_name+".AF_CO_GET_USER_NAME('"+m_user+"')) FROM DUAL ");
				boolean more_user=rs1.next();
				String m_user_name="";
				if(more_user){
				 m_user_name=rs1.getString(1);
				}
				
				//--------------   Definite Business  -------------------------------------------------------------------------------------
				
				rs1= stmt1.executeQuery("SELECT COUNT(A.APPLICATION_NO) "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS='ENTERED' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "); 
				boolean more_def=rs1.next();
				int def_count=0;
				if(more_def){
				 def_count=rs1.getInt(1);
				}
				
				rs1= stmt1.executeQuery("SELECT A.APPLICATION_NO, "+
 				" DECODE(A.TRANSACTION_TYPE,'FINLEASE','Finance Lease','HIREPURCH','Hire Purchase'), "+//2
 				" A.CLIENT_CODE, "+//3
 				" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//4
 				" NVL((SELECT TEL_NO FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE=A.CLIENT_CODE),'-') CONTACT, "+//5
  			" NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//6
				" NVL(A.FINANCE_NO,'-') "+//7
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS='ENTERED' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "+
				" ORDER BY A.TRANSACTION_TYPE ");

				  boolean mflag=true;							
					boolean more = rs1.next();
					double m_cum_amount=0;
					
					 out.println("<HTML><HEAD><TITLE>Sales Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_asset_drill(m_pur_order_no){");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_third_level_report?chksql=load_asset_detail&pur_order_no=\"+m_pur_order_no+\"\";");	
					 out.println("    popupwin=window.open(m_url,'displayWindow2','left=90,top=110,width=800,height=175,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					 out.println("	}");			
					 out.println("	function hide_count(){");
					 out.println("   ent_inquiries.innerHTML=\" Entered Inquiries \";"); 
					 out.println("	}");			
							
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 out.println("	function show_contract_report(m_finance_no){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Contract_Report?chksql=main_page&print=FALSE&finance_no=\"+m_finance_no+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=800');	");
					 out.println("	}");	
						
						
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<hr>");				
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 //out.println("<TR><TD align='Center' ><B><u> Weekly Sales Performance Report </u></B></TD></TR>"); //comment by nuwan de silva on 04-09-07
						out.println("<TR><TD align='Center' ><B><u> Sales Performance Report </u></B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Period - From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
	         out.println("<hr>");			
						
 					 out.println("<table align='center' width='100%' class='table' border='1' >");						
					 out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
					 out.println("<td colspan=10 bgcolor='#FFCCFF' width='100%' style= cursor:hand;  style='{font-color:white;}' align=center id='ent_inquiries' ><DIV class=div_input ><b>Marketing Officer - "+m_user_name+" </b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
					 out.println("</tr>"); 
						
				  if(more){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=10 bgcolor='#CCFF66' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Definite Business - "+def_count+"</b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCCC66' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</b></DIV></td>"); 
						out.println("<td width='20%' bgcolor='#CCCC66' colspan=3><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' ><DIV class=div_input ><b>Contact No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' colspan=2 align=right><DIV class=div_input ><b>Net Cost</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' colspan=2 align=right><DIV class=div_input ><b>Cumulative</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more){
					 m_cum_amount=m_cum_amount+rs1.getDouble(6);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input colspan=3 onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							//out.println("<td width='12%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_contract_report('"+rs1.getString(7)+"')\" style='cursor:hand' ><u>"+rs1.getString(7)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(m_cum_amount)+"</td>");
							out.println("</tr>");
							more = rs1.next();
							
					}	

						
				
			//---------------------   Completed Business  ------------------------------------------------------------------------------	
			 
			 rs1= stmt1.executeQuery("SELECT COUNT(A.APPLICATION_NO) "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS IN ('ACTIVATED','COMPLETED','ENT','ENT-CON','ENT_CON','REPOSSESS','V-APP','VERIFY','VERIFY-M','VERIFY1','VERIFY2','VERIFYL') AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "); 	
				boolean more_com=rs1.next();
				int com_count=0;
				if(more_com){
				 com_count=rs1.getInt(1);
				}
				
       rs1= stmt1.executeQuery("SELECT A.APPLICATION_NO, "+
 				" DECODE(A.TRANSACTION_TYPE,'FINLEASE','Finance Lease','HIREPURCH','Hire Purchase'), "+//2
 				" A.CLIENT_CODE, "+//3
 				" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//4
 				" NVL((SELECT TEL_NO FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE=A.CLIENT_CODE),'-') CONTACT, "+//5
  			" NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//6
				" NVL((SELECT LEAD_SOURCE_NAME FROM "+m_schema_name+".AF_MK_PRO_INQUIRY WHERE INQUIRY_CODE=A.INQUARY_NO),'-') LEAD , "+//7
				" NVL(A.FINANCE_NO,'-') "+//8
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS IN ('ACTIVATED','COMPLETED','ENT','ENT-CON','ENT_CON','REPOSSESS','V-APP','VERIFY','VERIFY-M','VERIFY1','VERIFY2','VERIFYL') AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "+ 
				" ORDER BY A.TRANSACTION_TYPE ");
				//A.APPLICATION_STATUS='ENT_CON'
					boolean more_pro = rs1.next();
					double m_cum_amount2=0;

				  if(more_pro){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=10 bgcolor='#FFFF99' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Completed Business - "+com_count+" </b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCCC66' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</b></DIV></td>"); 
						out.println("<td width='15%' bgcolor='#CCCC66' ><DIV class=div_input ><b>Lead</b></DIV></td>"); 
						out.println("<td width='20%' bgcolor='#CCCC66' colspan=2><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' ><DIV class=div_input ><b>Contact No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' ><DIV class=div_input  ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' colspan=2 align=right><DIV class=div_input ><b>Net Cost</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCC66' colspan=2 align=right><DIV class=div_input ><b>Cumulative</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_pro){
				
							m_cum_amount2=m_cum_amount2+rs1.getDouble(6);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='20%' class=div_input colspan=2 onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_contract_report('"+rs1.getString(8)+"')\" style='cursor:hand' ><u>"+rs1.getString(8)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(m_cum_amount2)+"</td>");
							out.println("</tr>");
							more_pro = rs1.next();
							
					}	
			
			//---------------------   Rejected Business  ------------------------------------------------------------------------------	
   		 
			 rs1= stmt1.executeQuery("SELECT COUNT(A.APPLICATION_NO) "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS='REJECT' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "); 	
				boolean more_rej=rs1.next();
				int rej_count=0;
				if(more_rej){
				 rej_count=rs1.getInt(1);
				}
				
       rs1= stmt1.executeQuery("SELECT A.APPLICATION_NO, "+
 				" DECODE(A.TRANSACTION_TYPE,'FINLEASE','Finance Lease','HIREPURCH','Hire Purchase'), "+//2
 				" A.CLIENT_CODE, "+//3
 				" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//4
 				" NVL((SELECT TEL_NO FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE=A.CLIENT_CODE),'-') CONTACT, "+//5
  			" NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//6
				" NVL((SELECT LEAD_SOURCE_NAME FROM "+m_schema_name+".AF_MK_PRO_INQUIRY WHERE INQUIRY_CODE=A.INQUARY_NO),'-') LEAD, "+//7
				" (SELECT DISTINCT "+
  		  " NVL(REMARK,'-') "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
 				" WHERE APPLICATION_NO=A.APPLICATION_NO AND STATUS='REJECT') ,"+ //8
				" NVL(A.FINANCE_NO,'-') "+//9
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS='REJECT' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "+ 
				" ORDER BY A.TRANSACTION_TYPE ");
				
					boolean more_reject = rs1.next();
					double m_cum_amount3=0;
					
				  if(more_reject){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=10 bgcolor='#CC99FF' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Business Rejected by Credit Committee - "+rej_count+" </b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCCCFF' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</b></DIV></td>"); 
						out.println("<td width='15%' bgcolor='#CCCCFF' ><DIV class=div_input ><b>Lead</b></DIV></td>"); 
						out.println("<td width='20%' bgcolor='#CCCCFF' colspan=2><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCCFF' ><DIV class=div_input ><b>Contact No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCCFF' ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCCFF' colspan=2 align=right><DIV class=div_input ><b>Net Cost</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCCCFF' colspan=2 align=right><DIV class=div_input ><b>Reason</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_reject){
				
							m_cum_amount3=m_cum_amount3+rs1.getDouble(6);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='20%' class=div_input colspan=2 onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_contract_report('"+rs1.getString(9)+"')\" style='cursor:hand' ><u>"+rs1.getString(9)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+rs1.getString(8)+"</td>");
							out.println("</tr>");
							more_reject = rs1.next();
							
					}	
			 
			//---------------------   Other Business  ------------------------------------------------------------------------------	
				
				rs1= stmt1.executeQuery("SELECT COUNT(A.APPLICATION_NO) "+
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS NOT IN ('REJECT','ENTERED','ACTIVATED','COMPLETED','ENT','ENT-CON','ENT_CON','REPOSSESS','V-APP','VERIFY','VERIFY-M','VERIFY1','VERIFY2','VERIFYL') AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "); 
				boolean more_other1=rs1.next();
				int other_count=0;
				if(more_other1){
				 other_count=rs1.getInt(1);
				}
				
        rs1= stmt1.executeQuery("SELECT A.APPLICATION_NO, "+
 				" DECODE(A.TRANSACTION_TYPE,'FINLEASE','Finance Lease','HIREPURCH','Hire Purchase'), "+//2
 				" A.CLIENT_CODE, "+//3
 				" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//4
 				" NVL((SELECT TEL_NO FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE=A.CLIENT_CODE),'-') CONTACT, "+//5
  			" NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//6
				" NVL((SELECT LEAD_SOURCE_NAME FROM "+m_schema_name+".AF_MK_PRO_INQUIRY WHERE INQUIRY_CODE=A.INQUARY_NO),'-') LEAD, "+//7
				" (SELECT "+
  		  " NVL(REMARK,'-') "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
 				" WHERE APPLICATION_NO=A.APPLICATION_NO AND STATUS='REJECT') ,"+ //8
				" NVL(A.FINANCE_NO,'-') "+//9
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
        " "+m_schema_name+".AF_MK_PRO_INQUIRY B "+
        " WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
        " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
        " AND A.INQUARY_NO=B.INQUIRY_CODE AND B.MK_OFFICER='"+m_user+"' "+
        " AND A.APPLICATION_STATUS NOT IN ('REJECT','ENTERED','ACTIVATED','COMPLETED','ENT','ENT-CON','ENT_CON','REPOSSESS','V-APP','VERIFY','VERIFY-M','VERIFY1','VERIFY2','VERIFYL') AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') "+ 
				" ORDER BY A.TRANSACTION_TYPE ");
				
					boolean more_other = rs1.next();
					double m_cum_amount4=0;
					
				  if(more_other){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=10 bgcolor='#CC66FF' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Terminated Business - "+other_count+" </b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CC99FF' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type</b></DIV></td>"); 
						out.println("<td width='15%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Lead</b></DIV></td>"); 
						out.println("<td width='20%' bgcolor='#CC99FF' colspan=2><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Contact No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Finance No</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CC99FF' colspan=2 align=right><DIV class=div_input ><b>Net Cost</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CC99FF' colspan=2 align=right><DIV class=div_input ><b>Cumulative</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_other){
				
							m_cum_amount4=m_cum_amount4+rs1.getDouble(6);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(2)+"</td>");
							out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='20%' class=div_input colspan=2 onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_contract_report('"+rs1.getString(9)+"')\" style='cursor:hand' ><u>"+rs1.getString(9)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(m_cum_amount4)+"</td>");
							out.println("</tr>");
							more_other = rs1.next();
							
					}	
					
			//Collections ====================added by nuwan de silva on 04-09-07======================================
					
					
			 rs1.close();	
			 rs1= stmt1.executeQuery(" SELECT "+
			 " COUNT(DISTINCT REC_NO)"+
			 " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
			 " WHERE B.INQUIRY_CODE=A.INQUARY_NO "+
			 " AND   UPPER(B.MK_OFFICER)=UPPER('"+m_user+"') "+
			 " AND   A.CLIENT_CODE=C.CLIENT_CODE "+
			 " AND   A.APPLICATION_STATUS='ACTIVATED' "+
			 " AND   C.STATUS<>'C' "+
			 " AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 " AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  ");
				
		 		boolean more_coll_1=rs1.next();
				int col_count=0;
				if(more_coll_1){
				 col_count=rs1.getInt(1);
				}
										
			 rs1.close();	
			 /*rs1= stmt1.executeQuery(" SELECT "+
			 " A.CLIENT_CODE,"+
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),"+
			 " NVL(SUM(REC_AMOUNT),0) COL_AMOUNT "+
			 " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
			 " WHERE B.INQUIRY_CODE=A.INQUARY_NO "+
			 " AND   UPPER(B.MK_OFFICER)=UPPER('"+m_user+"') "+
			 " AND   A.CLIENT_CODE=C.CLIENT_CODE "+
			 " AND   A.APPLICATION_STATUS='ACTIVATED' "+
			 " AND   C.STATUS<>'RET' "+
			 " AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 " AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
			 " GROUP BY A.CLIENT_CODE "+
			 " ORDER BY CLIENT_CODE ");
				*/
				
			  rs1= stmt1.executeQuery(" SELECT "+
				" A.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),NVL(A.COL_AMOUNT,0) TOTAL,NVL(B.REALISE,0) REALISE,NVL(C.RETURN_AMOUNT,0) TOT_RETIRN ,"+
				" (SELECT NVL(SUM(CAPITAL_AMOUNT),0)  "+
        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
        " WHERE APPLICATION_NO IN "+
        " (SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE CLIENT_CODE=A.CLIENT_CODE "+
        " AND APPLICATION_STATUS='ACTIVATED' "+
        " )   "+         
        " AND INVOICE_NO IS NULL ) FUTURE "+
				
        " FROM "+
        " (SELECT  "+
			  " A.CLIENT_CODE CLIENT_CODE, "+
			  " NVL(SUM(REC_AMOUNT),0) COL_AMOUNT  "+
			  " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C  "+
			  " WHERE B.INQUIRY_CODE=A.INQUARY_NO  "+
			  " AND   UPPER(B.MK_OFFICER)=UPPER('"+m_user+"')  "+
			  " AND   A.CLIENT_CODE=C.CLIENT_CODE  "+
			  " AND   A.APPLICATION_STATUS='ACTIVATED'  "+
			  "  AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
			  " AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
			  " GROUP BY A.CLIENT_CODE  "+
			  " ORDER BY CLIENT_CODE  ) A, "+

              
        " (SELECT  "+
			  " A.CLIENT_CODE CLIENT_CODE, "+
			  " NVL(SUM(REC_AMOUNT),0) REALISE "+
			  " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C  "+
			  " WHERE B.INQUIRY_CODE=A.INQUARY_NO  "+
			  " AND   UPPER(B.MK_OFFICER)=UPPER('"+m_user+"')  "+
			  " AND   A.CLIENT_CODE=C.CLIENT_CODE  "+
			  " AND   A.APPLICATION_STATUS='ACTIVATED'  "+
			  " AND   C.STATUS IN ('B','REC')  "+
			  " AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
			  " AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
			  " GROUP BY A.CLIENT_CODE  "+
			  " ORDER BY CLIENT_CODE )B, "+

        "      (SELECT   "+
			  " A.CLIENT_CODE, "+
			  " NVL(SUM(REC_AMOUNT),0) RETURN_AMOUNT "+
			  " FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C  "+
			  " WHERE B.INQUIRY_CODE=A.INQUARY_NO  "+
			  " AND   UPPER(B.MK_OFFICER)=UPPER('"+m_user+"')  "+
			  " AND   A.CLIENT_CODE=C.CLIENT_CODE  "+
			  " AND   A.APPLICATION_STATUS='ACTIVATED'  "+
			  " AND   C.STATUS=('RET')  "+
			  " AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
			  " AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
			  " GROUP BY A.CLIENT_CODE  "+
			  " ORDER BY CLIENT_CODE )C "+
        "       WHERE A.CLIENT_CODE=B.CLIENT_CODE(+) "+
        "       AND   A.CLIENT_CODE=C.CLIENT_CODE(+) ");
				
				
				
					boolean collect = rs1.next();
					double m_collect=0;
					
				  if(collect){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=10 bgcolor='#CCFF99' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Collections - Receipts Count - "+col_count+" </b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCFFFF' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client Code</b></DIV></td>"); 
						//out.println("<td width='15%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						out.println("<td width='20%' bgcolor='#CCFFFF' colspan=3><DIV class=div_input ><b>Client Name</b></DIV></td>"); 
						//out.println("<td width='12%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Realise Amount </b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCFFFF'  align=right><DIV class=div_input ><b>Total Amount</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCFFFF'   align=right><DIV class=div_input ><b>Realise Amount</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCFFFF'  colspan=2 align=right><DIV class=div_input ><b>Return Amount</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CCFFFF'  colspan=2 align=right><DIV class=div_input ><b>Future Receivalbe</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(collect){
				
							//m_cum_amount4=m_cum_amount4+rs1.getDouble(6);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							//out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input colspan=3 onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='12%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
							//out.println("<td width='12%' class=div_input onClick=\"show_contract_report('"+rs1.getString(9)+"')\" style='cursor:hand' ><u>"+rs1.getString(9)+"</u></td>");
							out.println("<td width='12%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 align=right>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("</tr>");
							collect = rs1.next();
							
					}	
				
				
				
	
	      //===================================================================================================
				out.println("</table>");
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
			}
			else if(m_chksql.equals("load_sales_report")) {
			
			  String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_team_id=req.getParameter("team_id");
				
				
				rs1= stmt1.executeQuery(" SELECT "+
				   " INITCAP(TEAM_DESC) "+
				" FROM "+m_schema_name+".AF_CO_MAS_TEAMS "+
				" WHERE TEAM_ID='"+m_team_id+"' ");
				
				boolean more_team=rs1.next();
				String m_team_desc="";
				if(more_team){
				 m_team_desc=rs1.getString(1);
				}
				
				rs1= stmt1.executeQuery(" SELECT "+
  		  " A.USER_ID, "+
				" INITCAP("+m_schema_name+".AF_CO_GET_USER_NAME(A.USER_ID)), "+
				" ( SELECT SUM(TARGET_AMT) "+
				" FROM "+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET "+
				" WHERE USER_ID=A.USER_ID "+
				" AND TO_DATE(TO_CHAR(TARGET_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
				" AND TO_DATE(TO_CHAR(TARGET_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
				" )AMOUNT "+

 				" FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A"+
 				" WHERE TEAM_ID='"+m_team_id+"' ");
				
				boolean more = rs1.next();
				int tot_hp=0;
				int tot_fin=0;
				double tot_target=0;
				double tot_amount_hp=0;
				double tot_amount_fin=0;
				double tot_tot_amount=0;
				double tot_def_amount=0;
				double tot_exp_amount=0;
				double tot_var_amount=0;
				double tot_rej_amount=0;
				double sub_tot_collect=0; //added by nuwan de silva on 04-09-07
				double sub_tot_return=0;  //added by nuwan de silva on 04-09-07
				
					 out.println("<HTML><HEAD><TITLE>Sales Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("	function show_asset_drill(m_pur_order_no){");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_third_level_report?chksql=load_asset_detail&pur_order_no=\"+m_pur_order_no+\"\";");	
					 out.println("    popupwin=window.open(m_url,'displayWindow2','left=90,top=110,width=800,height=175,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					 out.println("	}");			
					 out.println("	function hide_count(){");
					 out.println("   ent_inquiries.innerHTML=\" Entered Inquiries \";"); 
					 out.println("	}");			
							
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
						
					 
					 out.println("function makeRequest_detail(m_user) {");
					 out.println(" 	m_from_date = '"+m_from_date+"' ");
					 out.println(" 	m_to_date = '"+m_to_date+"' ");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_Sales_report?chksql=load_third_level&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&user=\"+m_user+\"\";");
					 //out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
					 out.println("    popupwin=window.open(m_url,'displayWindow2','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					 out.println("}");	
						
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<hr>");				
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B><u> Sales Report </u></B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Period - From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
	         out.println("<hr>");			
						
						
 					 out.println("<table align='center' width='100%' class='table' border='1' >");						
					 out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
					 out.println("<td colspan=13 bgcolor='#CCFF66' width='100%' style= cursor:hand;  style='{font-color:white;}' align=center id='ent_inquiries' onClick=show_team_member_drill('"+m_team_id+"') ><DIV class=div_input ><b><u> Team - "+m_team_desc+" </u></b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
					 out.println("</tr>"); 
				
				if(more) {
				  
						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CC99FF' ><DIV class=div_input ><b>Marketing Executive</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CC99FF' align=right><DIV class=div_input ><b>Target</b></DIV></td>"); 
						out.println("<td width='15%' bgcolor='#CC99FF' colspan=2 align=right ><DIV class=div_input ><b>Achievement to date</b></DIV></td>"); 
						out.println("<td width='12%' bgcolor='#CC99FF' colspan=2 align=right><DIV class=div_input ><b>No. of Trans.</b></DIV></td>"); 
						out.println("<td width='48%' bgcolor='#CC99FF' colspan=7 align=right><DIV class=div_input ><b></b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCCCFF' ><DIV class=div_input ><b></b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right><DIV class=div_input ><b></b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>Leasing</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>Hire Purchase</b></DIV></td>"); 
						out.println("<td width='5%'  bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>L</b></DIV></td>"); 
						out.println("<td width='5%'  bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>HP</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>Total</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>Def. Business</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>Total Expected</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF'  align=right ><DIV class=div_input ><b>Variation</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF'  align=right ><DIV class=div_input ><b>Rejected</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF'  align=right ><DIV class=div_input ><b>Collection</b></DIV></td>"); //added by ns
						out.println("<td width='5%' bgcolor='#CCCCFF'  align=right ><DIV class=div_input ><b>Returns</b></DIV></td>");  //added by ns
						out.println("</tr>"); 
			
				
					
				}
				
				while (more){
						
				    boolean mflag=true; 
						rs2= stmt2.executeQuery("SELECT "+
						   " SUM(TOTAL_FINANCE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.TRANSACTION_TYPE='FINLEASE' ");
						
						boolean more_fin = rs2.next();
						double m_tot_fin=0;
						if(more_fin){
						 m_tot_fin=rs2.getDouble(1);
						}
           
						rs2= stmt2.executeQuery("SELECT "+
						   " SUM(TOTAL_FINANCE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.TRANSACTION_TYPE='HIREPURCH' ");
						
						boolean more_hp = rs2.next();
						double m_tot_hp=0;
						if(more_hp){
						 m_tot_hp=rs2.getDouble(1);
						}
						
						rs2= stmt2.executeQuery("SELECT "+
						   " COUNT(APPLICATION_NO) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.TRANSACTION_TYPE='FINLEASE' ");
						
						boolean more_cnt_fin = rs2.next();
						int m_count_fin=0;
						if(more_cnt_fin){
						 m_count_fin=rs2.getInt(1);
						}
						
						rs2= stmt2.executeQuery("SELECT "+
						   " COUNT(APPLICATION_NO) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.TRANSACTION_TYPE='HIREPURCH' ");
						
						boolean more_cnt_hp = rs2.next();
						int m_count_hp=0;
						if(more_cnt_hp){
						 m_count_hp=rs2.getInt(1);
						}
						
						
						rs2= stmt2.executeQuery("SELECT "+
						   " SUM(TOTAL_FINANCE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.APPLICATION_STATUS='ENTERED' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') ");
						
						boolean more_busi = rs2.next();
						double m_tot_busi=0;
						if(more_busi){
						 m_tot_busi=rs2.getDouble(1);
						}
						
						rs2= stmt2.executeQuery("SELECT "+
						   " SUM(TOTAL_FINANCE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
						" WHERE A.INQUARY_NO=B.INQUIRY_CODE  AND B.MK_OFFICER='"+rs1.getString(1)+"' "+
						" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    	 		  " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND  A.APPLICATION_STATUS='REJECT' AND A.TRANSACTION_TYPE IN ('FINLEASE','HIREPURCH') ");
						
						boolean more_reject = rs2.next();
						double m_tot_reject=0;
						if(more_reject){
						 m_tot_reject=rs2.getDouble(1);
						}
						
						
						//added by nuwan de silva on 04-09-07 ========================
						rs2.close();
						rs2= stmt2.executeQuery("SELECT "+
						" A.COL_AMOUNT,B.REJ_AMOUNT "+
						" FROM "+
						" (SELECT NVL(SUM(REC_AMOUNT),0) COL_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
						" WHERE B.INQUIRY_CODE=A.INQUARY_NO "+
						" AND   UPPER(B.MK_OFFICER)=UPPER('"+rs1.getString(1)+"') "+
						" AND   A.CLIENT_CODE=C.CLIENT_CODE "+
						" AND   A.APPLICATION_STATUS='ACTIVATED' "+
						" AND   C.STATUS<>'RET' "+
						" AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') )A, "+
						
						" (SELECT NVL(SUM(REC_AMOUNT),0) REJ_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
						" WHERE B.INQUIRY_CODE=A.INQUARY_NO "+
						" AND   UPPER(B.MK_OFFICER)=UPPER('"+rs1.getString(1)+"') "+
						" AND   A.CLIENT_CODE=C.CLIENT_CODE "+
						" AND   A.APPLICATION_STATUS='ACTIVATED' "+
						" AND   C.STATUS='RET' "+
						" AND   C.EFF_VALDATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND   C.EFF_VALDATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') )B ");
						
						boolean more_collect = rs2.next();
						double m_tot_collect=0;
						double m_tot_return=0;
						if(more_reject){
						 m_tot_collect=rs2.getDouble(1);
							m_tot_return=rs2.getDouble(2);
						}
						//==================================================
						
						  if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							
							if(m_tot_return>0){
							out.println("<td width='15%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>"); //8000,000.00
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_fin)+"</td>");
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_hp)+"</td>");
							out.println("<td width='5%'  class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+m_count_fin+"</td>");
							out.println("<td width='5%'  class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+m_count_hp+"</td>");
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_hp+m_tot_fin)+"</td>");
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_busi)+"</td>");
							out.println("<td width='10%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>"); //8000,000.00 //comment by nuwan de silva on 26-10-07
							out.println("<td width='5%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>");//8000,000.00 //comment by nuwan de silva on 26-10-07
							out.println("<td width='5%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_reject)+"</td>");
							out.println("<td width='5%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_collect)+"</td>");
							out.println("<td width='5%' class=div_input bgcolor='#FF0000' onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_return)+"</td>");
							out.println("</tr>");
							}
							else{
							out.println("<td width='15%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>"); //8000,000.00 //comment by nuwan de silva on 26-10-07
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_fin)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_hp)+"</td>");
							out.println("<td width='5%'  class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+m_count_fin+"</td>");
							out.println("<td width='5%'  class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+m_count_hp+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_hp+m_tot_fin)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_busi)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>"); //8000,000.00 //comment by nuwan de silva on 26-10-07
							out.println("<td width='5%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(rs1.getDouble(3))+"</td>"); //8000,000.00 //comment by nuwan de silva on 26-10-07
							out.println("<td width='5%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_reject)+"</td>");
							out.println("<td width='5%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_collect)+"</td>");
							out.println("<td width='5%' class=div_input onClick=\"makeRequest_detail('"+rs1.getString(1)+"')\" style='cursor:hand' align=right >"+nf.format(m_tot_return)+"</td>");
							out.println("</tr>");
				      }
							
					tot_target=0;
				  tot_hp=tot_hp+m_count_hp;
				  tot_fin=tot_fin+m_count_fin;
				  tot_amount_hp=tot_amount_hp+m_tot_hp;
				  tot_amount_fin=tot_amount_fin+m_tot_fin;
				  tot_tot_amount=tot_tot_amount+(m_tot_hp+m_tot_fin);
				  tot_def_amount=tot_def_amount+m_tot_busi;
				  tot_exp_amount=0;
				  tot_var_amount=0;
				  tot_rej_amount=tot_rej_amount+m_tot_reject;	
					sub_tot_collect=sub_tot_collect+m_tot_collect;	
					sub_tot_return=sub_tot_return+m_tot_return;	
				 more = rs1.next();
				}
				
				    out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' bgcolor='#CCCCFF' ><DIV class=div_input ><b>Total</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right><DIV class=div_input ><b>"+nf.format(tot_target)+"</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_amount_fin)+"</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_amount_hp)+"</b></DIV></td>"); 
						out.println("<td width='5%'  bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+tot_fin+"</b></DIV></td>"); 
						out.println("<td width='5%'  bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+tot_hp+"</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_tot_amount)+"</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_def_amount)+"</b></DIV></td>"); 
						out.println("<td width='10%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_exp_amount)+"</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_var_amount)+"</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(tot_rej_amount)+"</b></DIV></td>"); 
						out.println("<td width='5%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(sub_tot_collect)+"</b></DIV></td>"); //added by ns
						out.println("<td width='5%' bgcolor='#CCCCFF' align=right ><DIV class=div_input ><b>"+nf.format(sub_tot_return)+"</b></DIV></td>"); //added by ns 
						out.println("</tr>"); 
			  
				out.println("</table>");
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
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
