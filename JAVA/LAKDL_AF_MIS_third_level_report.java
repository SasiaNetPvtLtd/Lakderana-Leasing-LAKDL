// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:21-09-2006
              
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MIS_third_level_report extends javax.servlet.http.HttpServlet { 

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
			out.println("<TITLE>Third Level Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
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
			
			out.println("function makeRequest_detail() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_third_level_report?chksql=load_third_level&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"\";");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=900,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("}");

			
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_RE_debtor_detail_report';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_RE_debtor_detail_report';"); 
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
			out.println("	help_box.innerHTML=\" Third Level Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Third Level Report \";"); 
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
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"FA_MAS_Help_Servlet?class_in=\"+client_name+\"FA_OP_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("		if(oBj.valout[1] !=\"Close\"){"); 
			out.println("			if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("				if(oBj.valout[1]!=\"Next\"){"); 
			out.println("					if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("						help_update_value_assign_1();"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Third Level Report </td>"); 
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
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"01\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"04\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"2007\" >");	
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Third Level Report\" onClick=\"makeRequest_detail()\">");	
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
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
				
				
				//--------------   Entered Inquiries -------------------------------------------------------------------------------------
				
				rs1= stmt1.executeQuery(" SELECT "+
				  "  A.INQUIRY_CODE,"+
				  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,"+
				  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUIRY_CODE),'-')MK_OFFICER,"+
				  "  NVL(A.LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,"+
				  "  NVL(B.DESCRIPTION,'-')PRODUCT,"+
				  //"  --STATUS,"+
				  "  A.INQUIRY_STATUS "+
			 "	FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
			 "	WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 "  AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 //"  A.INQUIRY_CODE IN (SELECT INQUARY_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS) "+
			 "	AND A.ENT_USER='"+m_username+"' AND A.SUB_PRODUCT_CODE=B.TRAN_CODE ");
				
				  boolean mflag=true;							
					boolean more = rs1.next();
				
				rs2= stmt2.executeQuery(" SELECT "+
				  "  COUNT(A.INQUIRY_CODE)"+
			 "	FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
			 "	WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 "  AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 "	AND A.ENT_USER='"+m_username+"' AND A.SUB_PRODUCT_CODE=B.TRAN_CODE ");
					
					boolean more_cou =rs2.next();
					int m_count=0;
					
					if(more_cou){
					 m_count=rs2.getInt(1);
					}
					
					 out.println("<HTML><HEAD><TITLE>Third Level Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
						
					 /*out.println("function sort_data(m_sort_col) {");
					 out.println(" m_from_date ='"+m_from_date+"';");	
					 out.println(" m_to_date='"+m_to_date+"';"); 	
		  		 out.println("	 m_order_by_type = 'ASC'; ");  
					 out.println("	 if(m_sort_col=='"+m_order_by+"'){");
					 out.println("	   if('"+m_sort_by+"'=='DESC'){");
					 out.println("	      m_order_by_type = 'ASC'; ");  
					 out.println("    }else{");
					 out.println("       m_order_by_type = 'DESC'; ");
					 out.println("    }");
					 out.println("  }else{");
					 out.println("    m_order_by_type = 'ASC'; ");
					 out.println("  }");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 out.println(" window.location.href=m_url;"); 
					 out.println("}");*/
 					 
					 out.println("	function show_asset_drill(m_pur_order_no){");
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MIS_third_level_report?chksql=load_asset_detail&pur_order_no=\"+m_pur_order_no+\"\";");	
					 out.println("    popupwin=window.open(m_url,'displayWindow2','left=90,top=110,width=800,height=175,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					 out.println("	}");			
						
					 out.println("	function show_count(){");
					 out.println("   ent_inquiries.innerHTML=\" Entered Inquiries - "+m_count+"\";"); 
					 out.println("	}");		
						
					 out.println("	function hide_count(){");
					 out.println("   ent_inquiries.innerHTML=\" Entered Inquiries \";"); 
					 out.println("	}");			
							
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 			 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<hr>");				
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Third Level Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
	         out.println("<hr>");				
 					 out.println("<table align='center' width='100%' class='table' border='1' >");						
						
				  if(more){
						out.println("<tr class=pdn_txtpos2 bgcolor='lightblue' >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand;  style='{font-color:white;}' id='ent_inquiries' ><DIV class=div_input ><b>Entered Inquiries -  "+m_count+"</b></DIV></td>"); //onMouseout='hide_count();' onMouseOver='show_count();'
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Inquiry No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2><DIV class=div_input ><b>Broker</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Product</b></DIV></td>"); 
						out.println("<td width='12%' colspan=3 ><DIV class=div_input ><b>Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more){
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_inquiry_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input colspan=3 >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more = rs1.next();
							
					}	

						
				
			//---------------------   Processed Inquiries  ------------------------------------------------------------------------------	

				rs1= stmt1.executeQuery(" SELECT "+
				  "  A.INQUIRY_CODE,"+
				  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE,"+
				  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUIRY_CODE),'-')MK_OFFICER,"+
				  "  NVL(A.LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,"+
				  "  NVL(B.DESCRIPTION,'-')PRODUCT,"+
				  //"  --STATUS,"+
				  "  A.INQUIRY_STATUS "+
			 "	FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
			 "	WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 "  AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			 "  A.INQUIRY_CODE IN (SELECT INQUARY_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS) "+
			 "	AND A.ENT_USER='"+m_username+"' AND A.SUB_PRODUCT_CODE=B.TRAN_CODE ");
						
					boolean more_pro = rs1.next();
					
					rs2= stmt2.executeQuery(" SELECT "+
				  "  COUNT(A.INQUIRY_CODE)"+
					"	FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
					"	WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					"  AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
					"  A.INQUIRY_CODE IN (SELECT INQUARY_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS) "+
					"	AND A.ENT_USER='"+m_username+"' AND A.SUB_PRODUCT_CODE=B.TRAN_CODE ");
					
					boolean more_count=rs2.next();
					int m_count1=0;
					if(more_count){
					 m_count1=rs2.getInt(1);
					}

				  if(more_pro){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Processed Inquiries  - "+m_count1+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Inquiry No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input ><b>Broker</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Product</b></DIV></td>"); 
						out.println("<td width='12%' colspan=3 ><DIV class=div_input ><b>Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_pro){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_inquiry_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input colspan=2 >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input colspan=3 >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more_pro = rs1.next();
							
					}	
			
			//------------------------   Pending Applications -------------------------------------------------------------------
			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  A.APPLICATION_NO, "+//1
			  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//2
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-'), "+//3
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//4
			  "  NVL(ROUND(A.MOD_DATE - A.ENT_DATE,2),0) TAKEN_TIME, "+//5   
			  "  A.TOTAL_FINANCE_AMOUNT, "+//6
				"  NVL(FLOOR((A.MOD_DATE - A.ENT_DATE)),0) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.ENT_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENTERED' ");
			
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.ENT_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENTERED' ");
					
					boolean more_count2=rs2.next();
					int m_count2=0;
					if(more_count2){
					 m_count2=rs2.getInt(1);
					}
					
					boolean more_app_ent = rs1.next();

				  if(more_app_ent){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Pending Applications  - "+m_count2+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input align='right' ><b>Taken Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' colspan=3 align='right' ><DIV class=div_input ><b>V/T ratio</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_ent){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+rs1.getString(7)+"/"+rs1.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input colspan=3 align='right' >"+nf.format(rs1.getDouble(4)/(rs1.getInt(7)+rs1.getInt(9)))+"</td>");
							out.println("</tr>");
							more_app_ent = rs1.next();
							
					}	

			//----------------   Completed Applications  -------------------------------------------------------------------------------------------------
			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  A.APPLICATION_NO, "+//1
			  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//2
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-'), "+//3
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//4
			  "  NVL(ROUND(A.MOD_DATE - A.ENT_DATE,2),0) TAKEN_TIME, "+//5   
			  "  A.TOTAL_FINANCE_AMOUNT, "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENT_CON' ");
			
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENT_CON' ");
			   
					boolean more_count3=rs2.next();
					int m_count3=0;
					if(more_count3){
					m_count3=rs2.getInt(1);
					}
			
					boolean more_app_pen = rs1.next();

				  if(more_app_pen){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Completed Applications  - "+m_count3+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input align='right' ><b>Taken Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' colspan=3 align='right' ><DIV class=div_input ><b>V/T ratio</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_pen){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+rs1.getString(7)+"/"+rs1.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input colspan=3 align='right' >"+nf.format(rs1.getDouble(4)/(rs1.getInt(7)+rs1.getInt(9)))+"</td>");
							out.println("</tr>");
							more_app_pen = rs1.next();
							
					}	
			
			//----------------   Rejected Applications  -------------------------------------------------------------------------------------------------
			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  A.APPLICATION_NO, "+//1
			  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//2
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-'), "+//3
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//4
			  "  NVL(ROUND(A.MOD_DATE - A.ENT_DATE,2),0) TAKEN_TIME, "+//5   
			  "  A.TOTAL_FINANCE_AMOUNT, "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='REJECT' ");
			
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='REJECT' ");
					
					boolean more_count4=rs2.next();
					int m_count4=0;
					if(more_count4){
					 m_count4=rs2.getInt(1);
					}
						
					boolean more_app_rej = rs1.next();

				  if(more_app_rej){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Rejected Applications  - "+m_count4+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input align='right' ><b>Taken Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' colspan=3 align='right' ><DIV class=div_input ><b>V/T ratio</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_rej){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+rs1.getString(7)+"/"+rs1.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input colspan=3 align='right' >"+nf.format(rs1.getDouble(4)/(rs1.getInt(7)+rs1.getInt(9)))+"</td>");
							out.println("</tr>");
							more_app_rej = rs1.next();
							
					}	

			
			//--------------------------------------   Pending  Verification ---------------------------------------------------------------
			
				rs1= stmt1.executeQuery(" SELECT "+
			  "  A.APPLICATION_NO, "+//1
			  "  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+//2
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-'), "+//3
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//4
			  "  NVL(ROUND(A.MOD_DATE - A.ENT_DATE,2),0) TAKEN_TIME, "+//5   
			  "  A.TOTAL_FINANCE_AMOUNT , "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENT_CON' ");
			
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='ENT_CON' ");
					
					boolean more_count5=rs2.next();
					int m_count5=0;
					if(more_count5){
					 m_count5=rs2.getInt(1);
					}
					
					boolean more_app_com = rs1.next();

				  if(more_app_com){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Pending Verification  - "+m_count5+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Entered Date</b></DIV></td>"); 
						out.println("<td width='20%' colspan=2 ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Taken Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' colspan=4 align='right' ><DIV class=div_input ><b>V/T ratio</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_com){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='20%' colspan=2 class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='12%' colspan=2 class=div_input align='right' >"+nf.format(rs1.getDouble(4))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs1.getString(7)+"/"+rs1.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input colspan=4 align='right' >"+nf.format(rs1.getDouble(4)/(rs1.getInt(7)+rs1.getInt(9)))+"</td>");
							out.println("</tr>");
							more_app_com = rs1.next();
							
					}	
			
			//---------------------------   Verified Applications ---------------------------------------------------------------------------------------------------

			 rs1= stmt1.executeQuery("	SELECT DISTINCT "+
			  "  A.APPLICATION_NO, "+//1
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER),'-'), "+//2
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//3
        "  NVL((SELECT SUM(CAPITAL_AMOUNT+INTEREST_AMOUNT) FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT WHERE PRICING_NO=B.PRICING_NO ),0),"+//4
				"  "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(A.CLIENT_CODE), "+//5
				//"  NVL(ROUND(A.MOD_DATE - A.ENT_DATE,2),0) TAKEN_TIME "+//6   
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//6
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//8
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY1'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
			
			rs2= stmt2.executeQuery("	SELECT  "+
			  "  COUNT(DISTINCT A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY1'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
					
					boolean more_count6=rs2.next();
					int m_count6=0;
					if(more_count6){
					 m_count6=rs2.getInt(1);
					}
					
					boolean more_app_very = rs1.next();
					
				  if(more_app_very){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Verified Applications  - "+m_count6+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Credit Officer</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input align='right' ><b>Cur. Action Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input align='right' ><b>Cur. R/T ratio</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input align='right' ><b>Total Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total R/T ratio  </b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input align='right' ><b>Exposure</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_very){
					
					
					rs2= stmt2.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
					  " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+
						" TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+
					  " FROM(SELECT ENT_DATE "+
					  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					  " WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
					  " STATUS = 'ENTERED' ) A, "+
					  " (SELECT ENT_DATE "+
				 "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				 "	WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
				 "	STATUS = 'VERIFY1' ) B ");
					
				 boolean more_time = rs2.next();
					   
						if(more_time){	
						
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input align='right'  >"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+rs2.getString(1)+" /  "+rs2.getString(2)+" </td>");
							out.println("<td width='6%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs2.getInt(1)*24)+rs2.getInt(3) ))+"</td>");
							out.println("<td width='6%' class=div_input align='right' >"+rs1.getString(6)+" /  "+rs1.getString(7)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs1.getInt(6)*24)+rs1.getInt(8) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("</tr>");
							
						}	
							more_app_very = rs1.next();
							
					}	

				//-----------------  Credit Score --------------------------------------------------------------------------------------------------------

				rs1= stmt1.executeQuery("	SELECT DISTINCT "+
			  "  A.APPLICATION_NO, "+//1
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER),'-'), "+//2
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//3
        "  NVL((SELECT SUM(CAPITAL_AMOUNT+INTEREST_AMOUNT) FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT WHERE PRICING_NO=B.PRICING_NO ),0),"+//4
				"  ( SELECT    FINAL_APP_SCORE  FROM "+m_schema_name+".AF_CR_PRO_CRSCORE WHERE APPLICATION_CODE=A.APPLICATION_NO ), "+//5
				"  "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(A.CLIENT_CODE), "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='V-APP'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
			
			rs2= stmt2.executeQuery("	SELECT  "+
			  "  COUNT(DISTINCT A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='V-APP'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
					
					boolean more_count7=rs2.next();
					int m_count7=0;
					if(more_count7){
					 m_count7=rs2.getInt(1);
					}
					
					boolean more_app_score = rs1.next();
					
				  if(more_app_score){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Credit Score  - "+m_count7+" </b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Credit Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. Action Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total Time (DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Credit Score</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Exposure</b></DIV></td>"); 						
						out.println("</tr>"); 
					}
					
					while(more_app_score){
					
					
					rs2= stmt2.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
					  " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+
						" TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+
					  " FROM(SELECT ENT_DATE "+
					  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					  " WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
					  " STATUS = 'VERIFY1' ) A, "+
					  " (SELECT ENT_DATE "+
				 "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				 "	WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
				 "	STATUS = 'V-APP' ) B ");
					
				 boolean more_time = rs2.next();
					   
						if(more_time){	
						
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs2.getString(1)+" /  "+rs2.getString(2)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs2.getInt(1)*24)+rs2.getInt(3) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs1.getString(7)+" /  "+rs1.getString(8)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs1.getInt(7)*24)+rs1.getInt(9) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("</tr>");
							
						}	
							more_app_score = rs1.next();
							
					}
				
			//---------------------------------  Credit Approval 1 ----------------------------------------------------------------------------------------------------
			
			rs1= stmt1.executeQuery("	SELECT DISTINCT "+
			  "  A.APPLICATION_NO, "+//1
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER),'-'), "+//2
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//3
        "  NVL((SELECT SUM(CAPITAL_AMOUNT+INTEREST_AMOUNT) FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT WHERE PRICING_NO=B.PRICING_NO ),0),"+//4
				"  ( SELECT    FINAL_APP_SCORE  FROM "+m_schema_name+".AF_CR_PRO_CRSCORE WHERE APPLICATION_CODE=A.APPLICATION_NO ), "+//5
				"  "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(A.CLIENT_CODE), "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY-M'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
			
			rs2= stmt2.executeQuery("	SELECT  "+
			  "  COUNT(DISTINCT A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY-M'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
					
					boolean more_count8=rs2.next();
					int m_count8=0;
					if(more_count8){
					 m_count8=rs2.getInt(1);
					}
					
					boolean more_app_app1 = rs1.next();
					
				  if(more_app_app1){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Credit Approval 1  - "+m_count8+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Credit Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. Action Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total Time (DD/Time) </b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Credit Score</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Exposure</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_app1){

					rs2= stmt2.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
					  " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+
						" TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+
					  " FROM(SELECT ENT_DATE "+
					  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					  " WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
					  " STATUS = 'V-APP' ) A, "+
					  " (SELECT ENT_DATE "+
				 "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				 "	WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
				 "	STATUS = 'VERIFY-M' ) B ");
					
				 boolean more_time = rs2.next();
					   
						if(more_time){	
						
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs2.getString(1)+" /  "+rs2.getString(2)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs2.getInt(1)*24)+rs2.getInt(3) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs1.getString(7)+" /  "+rs1.getString(8)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs1.getInt(7)*24)+rs1.getInt(9) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("</tr>");
							
						}	
							more_app_app1 = rs1.next();
							
					}
			
			//-------------------------------   Credit Approval 2 --------------------------------------------------------------------------------------------------
			
				rs1= stmt1.executeQuery("	SELECT DISTINCT "+
			  "  A.APPLICATION_NO, "+//1
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER),'-'), "+//2
			  "  NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//3
        "  NVL((SELECT SUM(CAPITAL_AMOUNT+INTEREST_AMOUNT) FROM "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT WHERE PRICING_NO=B.PRICING_NO ),0),"+//4
				"  ( SELECT    FINAL_APP_SCORE  FROM "+m_schema_name+".AF_CR_PRO_CRSCORE WHERE APPLICATION_CODE=A.APPLICATION_NO ), "+//5
				"  "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(A.CLIENT_CODE), "+//6
				"  FLOOR((A.MOD_DATE - A.ENT_DATE)) STAGE1_DD, "+//7
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((A.MOD_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+//8
				"  TO_CHAR(MOD(FLOOR((A.MOD_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+//9
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY2'  AND A.APPLICATION_NO=B.APPLICATION_NO ");
			
			rs2= stmt2.executeQuery("	SELECT  "+
			  "  COUNT(DISTINCT A.APPLICATION_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
			" WHERE TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	A.MOD_USER='"+m_username+"' AND A.APPLICATION_STATUS='VERIFY2'  AND A.APPLICATION_NO=B.APPLICATION_NO ");

					boolean more_count9=rs2.next();
					int m_count9=0;
					if(more_count9){
					 m_count9=rs2.getInt(1);
					}
					
					boolean more_app_app2 = rs1.next();
					
				  if(more_app_app2){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Credit Approval 2  - "+m_count9+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Application No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Credit Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Value</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. Action Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Cur. R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total Time(DD/Time)</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Total R/T ratio</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Credit Score</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input align='right' ><b>Exposure</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_app_app2){

					rs2= stmt2.executeQuery(" SELECT FLOOR((B.ENT_DATE - A.ENT_DATE)) STAGE1_DD, "+
					  " TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 ))||':'||TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60),60))||':'||TO_CHAR(MOD( FLOOR((B.ENT_DATE - A.ENT_DATE)*24*60*60),60 )) STAGE1_TIME, "+
						" TO_CHAR(MOD(FLOOR((B.ENT_DATE - A.ENT_DATE)*24),24 )) STAGE1_TIME_HH "+
					  " FROM(SELECT ENT_DATE "+
					  " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					  " WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
					  " STATUS = 'VERIFY-M' ) A, "+
					  " (SELECT ENT_DATE "+
				 "	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				 "	WHERE APPLICATION_NO='"+rs1.getString(1)+"' AND "+
				 "	STATUS = 'VERIFY2' ) B ");
					
				 boolean more_time = rs2.next();
					   
						if(more_time){	
						
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(3))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs2.getString(1)+" /  "+rs2.getString(2)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs2.getInt(1)*24)+rs2.getInt(3) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+rs1.getString(7)+" /  "+rs1.getString(8)+" </td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(4)/ ((rs1.getInt(7)*24)+rs1.getInt(9) ))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right'  >"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("</tr>");
							
						}	
							more_app_app2 = rs1.next();
							
					}
			
						
			//----------------   Pending Purchase Orders  -------------------------------------------------------------------------------------------------

						rs1= stmt1.executeQuery("	SELECT "+
						  "  X.PURCHASE_ORDER_NO, "+//1
						  "  APPLICATION_NO, "+//2
						  "  VENDER_CODE, "+//3
						  "  NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(VENDER_CODE),'-'), "+//4
						  "  NVL(TOTAL_NET,0), "+//5
							"  NVL((SELECT  "+  //mod by mahela on 14-08-2007
              "  COUNT(DISTINCT E.DESCRIPTION) "+//6  
              "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,  "+
              "  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C, "+m_schema_name+".AF_CO_MAS_MODEL D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+ 
              "  "+m_schema_name+".AF_CO_MAS_MAKE F ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS G,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER H "+
              "  WHERE A.ASSET_ID=B.ASSET_ID AND  "+ 
              "  B.SUB_MODEL_CODE=C.SUB_CODE AND "+  
              "  D.ITEM_SUB_CAT=E.ITEM_SUB_CAT AND "+  
              "  B.MODEL_CODE=D.MODEL_CODE AND  "+
              "  D.MAKE_CODE=F.MAKE_CODE AND  "+ 
              "  A.PRO_INVOICE_NO=G.INVOICE_NO AND  "+ 
							"  A.PURCHASE_ORDER_NO=H.PURCHASE_ORDER_NO AND "+
              "  UPPER(H.PURCHASE_ORDER_NO)=UPPER(X.PURCHASE_ORDER_NO)),0), "+ 
						  //"  NVL(TOTAL_VAT,0), "+//6
						  "  ACTIVE_STATUS "+//7
						 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER X "+
						 " WHERE ACTIVE_STATUS='VERIFY' AND "+ 
						 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+ 
						 " ENT_USER='"+m_username+"' AND  "+
			       "  PURCHASE_ORDER_NO NOT IN ( "+
			 						"	SELECT DISTINCT "+
			    				"	D.PURCHASE_ORDER_NO "+
			 						"	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
			 						"	"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER D "+
			 						"	WHERE A.PROCESS_STATUS='DISBRS' AND A.SUS_REF_NO=B.SUS_REF_NO AND C.PRO_INVOICE_NO=B.REF_NO "+
			 						"	AND D.PURCHASE_ORDER_NO=C.PURCHASE_ORDER_NO) ");
				
			 rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(PURCHASE_ORDER_NO) "+//1
			 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
			 " WHERE ACTIVE_STATUS='VERIFY' AND "+
			 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			 " ENT_USER='"+m_username+"' AND "+
       "   PURCHASE_ORDER_NO NOT IN ( "+
 						"	SELECT DISTINCT "+
    				"	D.PURCHASE_ORDER_NO "+
 						"	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
 						"	"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER D "+
 						"	WHERE A.PROCESS_STATUS='DISBRS' AND A.SUS_REF_NO=B.SUS_REF_NO AND C.PRO_INVOICE_NO=B.REF_NO "+
 						"	AND D.PURCHASE_ORDER_NO=C.PURCHASE_ORDER_NO) ");
					
					boolean more_count101=rs2.next();
					int m_count101=0;
					if(more_count101){
					 m_count101=rs2.getInt(1);
					}
					
					boolean more_pur1 = rs1.next();
					
				  if(more_pur1){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Pending Purchase Orders  - "+m_count101+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pur. Order No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Application No</b></DIV></td>"); 
						out.println("<td width='20%' colspan=2 ><DIV class=div_input ><b>Vendor</b></DIV></td>"); 
						out.println("<td width='12%' align='right' colspan=2><DIV class=div_input ><b>Total Net Amount</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input ><b>Asset Count</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input  ><b>Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_pur1){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='20%' colspan=2 class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right' style='cursor:hand' onClick=\"show_asset_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(6)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 >"+rs1.getString(7)+"</td>");
							out.println("</tr>");
							more_pur1 = rs1.next();
							
					}	
			
			//----------------   Issued Purchase Orders  -------------------------------------------------------------------------------------------------


			
			rs1= stmt1.executeQuery("	 SELECT DISTINCT "+
				  "  X.PURCHASE_ORDER_NO, "+//1
				  "  X.APPLICATION_NO, "+//2
				  "  X.VENDER_CODE, "+//3
				  "  NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(X.VENDER_CODE),'-'), "+//4
				  "  NVL(X.TOTAL_NET,0), "+//5
					"  NVL((SELECT  "+ //MOD by mahela on 14-08-2007
              "  COUNT(DISTINCT E.DESCRIPTION) "+//6  
              "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,  "+
              "  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C, "+m_schema_name+".AF_CO_MAS_MODEL D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+ 
              "  "+m_schema_name+".AF_CO_MAS_MAKE F ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS G,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER H "+
              "  WHERE A.ASSET_ID=B.ASSET_ID AND  "+ 
              "  B.SUB_MODEL_CODE=C.SUB_CODE AND "+  
              "  D.ITEM_SUB_CAT=E.ITEM_SUB_CAT AND "+  
              "  B.MODEL_CODE=D.MODEL_CODE AND  "+
              "  D.MAKE_CODE=F.MAKE_CODE AND  "+ 
              "  A.PRO_INVOICE_NO=G.INVOICE_NO AND  "+ 
							"  A.PURCHASE_ORDER_NO=H.PURCHASE_ORDER_NO AND "+
              "  UPPER(H.PURCHASE_ORDER_NO)=UPPER(X.PURCHASE_ORDER_NO)),0), "+ 
				  //"  NVL(D.TOTAL_VAT,0), "+//6
				  "  X.ACTIVE_STATUS "+//7
				" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
				" "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER X "+
				" WHERE A.PROCESS_STATUS='DISBRS' AND A.SUS_REF_NO=B.SUS_REF_NO AND C.PRO_INVOICE_NO=B.REF_NO "+
				" AND X.PURCHASE_ORDER_NO=C.PURCHASE_ORDER_NO	 AND "+
				" TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			  " AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			  " A.MOD_USER='"+m_username+"' ");
				
			 rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(DISTINCT D.PURCHASE_ORDER_NO) "+//1
			  " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
				" "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET C,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER D "+
				" WHERE A.PROCESS_STATUS='DISBRS' AND A.SUS_REF_NO=B.SUS_REF_NO AND C.PRO_INVOICE_NO=B.REF_NO "+
				" AND D.PURCHASE_ORDER_NO=C.PURCHASE_ORDER_NO	 AND "+
				" TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			  " AND TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			  " A.MOD_USER='"+m_username+"' ");
					
					boolean more_count10=rs2.next();
					int m_count10=0;
					if(more_count10){
					 m_count10=rs2.getInt(1);
					}
					
					boolean more_pur = rs1.next();
					
				  if(more_pur){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Issued Purchase Orders  - "+m_count10+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pur. Order No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Application No</b></DIV></td>"); 
						out.println("<td width='20%' colspan=2 ><DIV class=div_input ><b>Vendor</b></DIV></td>"); 
						out.println("<td width='12%' align='right' colspan=2><DIV class=div_input ><b>Total Net Amount</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input ><b>Asset Count</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input  ><b>Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_pur){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='20%' colspan=2 class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right' style='cursor:hand' onClick=\"show_asset_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(6)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 >"+rs1.getString(7)+"</td>");
							out.println("</tr>");
							more_pur = rs1.next();
							
					}	

			
			//----------------   Deleted Purchase Orders  -------------------------------------------------------------------------------------------------

			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  X.PURCHASE_ORDER_NO, "+//1
			  "  X.APPLICATION_NO, "+//2
			  "  X.VENDER_CODE, "+//3
			  "  NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(X.VENDER_CODE),'-'), "+//4
			  "  NVL(X.TOTAL_NET,0), "+//5
				"  NVL((SELECT "+ //mod by mahela on 14-08-2007
              "  COUNT(DISTINCT E.DESCRIPTION) "+//6  
              "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,  "+
              "  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C, "+m_schema_name+".AF_CO_MAS_MODEL D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+ 
              "  "+m_schema_name+".AF_CO_MAS_MAKE F ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS G,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER H "+
              "  WHERE A.ASSET_ID=B.ASSET_ID AND  "+ 
              "  B.SUB_MODEL_CODE=C.SUB_CODE AND "+  
              "  D.ITEM_SUB_CAT=E.ITEM_SUB_CAT AND "+  
              "  B.MODEL_CODE=D.MODEL_CODE AND  "+
              "  D.MAKE_CODE=F.MAKE_CODE AND  "+ 
              "  A.PRO_INVOICE_NO=G.INVOICE_NO AND  "+ 
							"  A.PURCHASE_ORDER_NO=H.PURCHASE_ORDER_NO AND "+
              "  UPPER(H.PURCHASE_ORDER_NO)=UPPER(X.PURCHASE_ORDER_NO)),0), "+ 
			  //"  NVL(TOTAL_VAT,0), "+//6
			  "  X.ACTIVE_STATUS "+//7
			 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER X"+
			 " WHERE X.ACTIVE_STATUS='CANCEL' AND "+
			 " TO_DATE(TO_CHAR(X.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 " AND TO_DATE(TO_CHAR(X.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			 " X.MOD_USER='"+m_username+"' ");
				
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(PURCHASE_ORDER_NO) "+//1
			 " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
			 " WHERE ACTIVE_STATUS='CANCEL' AND "+
			 " TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			 " AND TO_DATE(TO_CHAR(MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			 " MOD_USER='"+m_username+"' ");	
					
					boolean more_count11=rs2.next();
					int m_count11=0;
					if(more_count11){
					 m_count11=rs2.getInt(1);
					}
					
					boolean more_pur_d = rs1.next();
					
				  if(more_pur_d){
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Deleted Purchase Orders  - "+m_count11+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pur. Order No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Application No</b></DIV></td>"); 
						out.println("<td width='20%' colspan=2 ><DIV class=div_input ><b>Vendor</b></DIV></td>"); 
						out.println("<td width='12%' align='right' colspan=2 ><DIV class=div_input ><b>Total Net Amount</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input ><b>Asset Count</b></DIV></td>"); 
						out.println("<td width='12%' colspan=2 ><DIV class=div_input  ><b>Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_pur_d){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_purchase_order_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_application_detail_drill('"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='20%' colspan=2 class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=2 >"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='12%' class=div_input align='right' style='cursor:hand' onClick=\"show_asset_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(6)+"</u></td>");
							out.println("<td width='12%' class=div_input colspan=2 >"+rs1.getString(7)+"</td>");
							out.println("</tr>");
							more_pur_d = rs1.next();
							
					}	
			
			//----------------   Invoicing  -------------------------------------------------------------------------------------------------

			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  A.FINANCE_NO, "+//1
			  "  A.INVOICE_NO, "+//2
			  "  C.MK_OFFICER, "+//3
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(B.INQUARY_NO),'-'), "+//4
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(B.COLLECTION_OFFICER),'-'), "+//5
			  "  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+//6
			  "  A.TOTAL_AMOUNT, "+//7
			  //  --A.SETTELE_AMOUNT,
			  "  A.TOTAL_ODI_AMOUNT "+//8
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_MK_PRO_INQUIRY C "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND B.INQUARY_NO=C.INQUIRY_CODE AND "+
      " TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	(C.MK_OFFICER='"+m_username+"' OR B.COLLECTION_OFFICER='"+m_username+"') ");
			
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.FINANCE_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_MK_PRO_INQUIRY C "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND B.INQUARY_NO=C.INQUIRY_CODE AND "+
      " TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	(C.MK_OFFICER='"+m_username+"' OR B.COLLECTION_OFFICER='"+m_username+"') ");
					
					boolean more_count12=rs2.next();
					int m_count12=0;
					if(more_count12){
					 m_count12=rs2.getInt(1);
					}
					
					boolean more_inv = rs1.next();
					boolean tot_flag =false;
					double tot_inv_amount=0;
					double tot_odi_amount=0;

				  if(more_inv){
					  tot_flag=true;
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Invoicing  - "+m_count12+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Finance No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Collection Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Due Date</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input ><b>Invoice Amount</b></DIV></td>"); 
						out.println("<td width='12%' align='right' colspan=3 ><DIV class=div_input  ><b>ODI Amount</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_inv){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_invoice_drill('"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=3 >"+nf.format(rs1.getDouble(8))+"</td>");
							out.println("</tr>");
							tot_inv_amount=tot_inv_amount+rs1.getDouble(7);
							tot_odi_amount=tot_odi_amount+rs1.getDouble(8);
							more_inv = rs1.next();
							
					}	
							if(tot_flag){				     
							out.println("<tr class=pdn_txtpos2>");
						  out.println("<td width='15%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='20%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' align='right'><DIV class=div_input ><b>Total</b></DIV></td>"); 
						  out.println("<td width='12%' align='right'><DIV class=div_input ><b>"+nf.format(tot_inv_amount)+"</b></DIV></td>"); 
						  out.println("<td width='12%' align='right' colspan=3 ><DIV class=div_input  ><b>"+nf.format(tot_odi_amount)+"</b></DIV></td>"); 
						  out.println("</tr>"); 

							}
			
			
			//----------------   collection  -------------------------------------------------------------------------------------------------

			
			
			rs1= stmt1.executeQuery(" SELECT "+
			  "  A.FINANCE_NO, "+//1
			  //"  A.INVOICE_NO, "+
			  "  D.RECEIPT_NO, "+//2
			  "  C.MK_OFFICER, "+//3
			  "  NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(B.INQUARY_NO),'-'), "+//4
			  "  NVL("+m_schema_name+".AF_CO_GET_USER_NAME(B.COLLECTION_OFFICER),'-'), "+//5
			  "  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+//6
			  "  NVL(D.RECEIPT_AMOUNT,0), "+//7
			  //  --A.SETTELE_AMOUNT,
			  "  NVL(A.TOTAL_ODI_AMOUNT,0) "+//8
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_MK_PRO_INQUIRY C,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS D "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND B.INQUARY_NO=C.INQUIRY_CODE AND "+
			" A.INVOICE_NO=D.INVOICE_NO AND "+   
			" TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	(C.MK_OFFICER='"+m_username+"' OR B.COLLECTION_OFFICER='"+m_username+"') ");
      
			rs2= stmt2.executeQuery(" SELECT "+
			  "  COUNT(A.FINANCE_NO) "+//1
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_MK_PRO_INQUIRY C,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS D "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND B.INQUARY_NO=C.INQUIRY_CODE AND "+
			" A.INVOICE_NO=D.INVOICE_NO AND "+   
			" TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
			"	(C.MK_OFFICER='"+m_username+"' OR B.COLLECTION_OFFICER='"+m_username+"') ");
			
					boolean more_count13=rs2.next();
					int m_count13=0;
					if(more_count13){
					 m_count13=rs2.getInt(1);
					}
					
					boolean more_rec = rs1.next();
					boolean tot_flag1 =false;
					double tot_rec_amount=0;
					double tot_odi_amount1=0;

				  if(more_rec){
					  tot_flag1=true;
						out.println("<tr class=pdn_txtpos2  >");
						out.println("<td colspan=9 bgcolor='lightblue' width='100%' style= cursor:hand; ><DIV class=div_input ><b>Collection  - "+m_count13+"</b></DIV></td>"); 
						out.println("</tr>"); 
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Finance No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input ><b>Mk. Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Collection Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input ><b>Due Date</b></DIV></td>"); 
						out.println("<td width='12%' align='right'><DIV class=div_input ><b>Receipt Amount</b></DIV></td>"); 
						out.println("<td width='12%' align='right' colspan=3 ><DIV class=div_input  ><b>ODI Amount</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					
					while(more_rec){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							} 
							out.println("<td width='15%' class=div_input onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" style='cursor:hand'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_settle_receipt_drill('"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='12%' class=div_input align='right' >"+nf.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='12%' class=div_input align='right' colspan=3 >"+nf.format(rs1.getDouble(8))+"</td>");
							out.println("</tr>");
							tot_rec_amount=tot_rec_amount+rs1.getDouble(7);
							tot_odi_amount1=tot_odi_amount1+rs1.getDouble(8);
							more_rec = rs1.next();
							
					}	
							if(tot_flag1){				     
							out.println("<tr class=pdn_txtpos2>");
						  out.println("<td width='15%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='20%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' ><DIV class=div_input ></DIV></td>"); 
						  out.println("<td width='12%' align='right'><DIV class=div_input ><b>Total</b></DIV></td>"); 
						  out.println("<td width='12%' align='right'><DIV class=div_input ><b>"+nf.format(tot_rec_amount)+"</b></DIV></td>"); 
						  out.println("<td width='12%' align='right' colspan=3 ><DIV class=div_input  ><b>"+nf.format(tot_odi_amount1)+"</b></DIV></td>"); 
						  out.println("</tr>"); 
							}
			
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
			}
			else if(m_chksql.equals("load_asset_detail")){
				
				int count = 0;
				String m_string="";								
				String m_pur_order_no=req.getParameter("pur_order_no");
				
				 rs= stmt1.executeQuery("	SELECT   "+
              "  DISTINCT A.ASSET_ID, "+//1
              "  F.MAKE_DESC, "+//2
							"  D.DESCRIPTION, "+//3
              "  C.DESCRIPTION, "+//4
              "  E.DESCRIPTION "+//5  
              "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+  
              "  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C, "+m_schema_name+".AF_CO_MAS_MODEL D,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E, "+  
              "  "+m_schema_name+".AF_CO_MAS_MAKE F ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS G,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER H  "+
              "  WHERE A.ASSET_ID=B.ASSET_ID AND "+   
              "  B.SUB_MODEL_CODE=C.SUB_CODE AND  "+ 
              "  D.ITEM_SUB_CAT=E.ITEM_SUB_CAT AND  "+ 
              "  B.MODEL_CODE=D.MODEL_CODE AND  "+ 
              "  D.MAKE_CODE=F.MAKE_CODE AND  "+ 
              "  A.PRO_INVOICE_NO=G.INVOICE_NO AND  "+ 
							"  A.PURCHASE_ORDER_NO=H.PURCHASE_ORDER_NO AND "+
              "  UPPER(H.PURCHASE_ORDER_NO)=UPPER('"+m_pur_order_no+"') ");
							
				boolean more_dir = rs.next();
				
				  out.println("<HTML><HEAD><TITLE>Asset Details - Purchase Order No - "+m_pur_order_no+"</TITLE></HEAD>");
				  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='*%'><b>Asset Details - Purchase Order No - "+m_pur_order_no+" </b></td>"); 
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
				
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Purchase Order No "+m_pur_order_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%'><b>Asset ID</b></td>"); 
					out.println("<td width='10%' class=div_input><b>Make </b></td>");
					out.println("<td width='10%' class=div_input><b>Model</b></td>");
					out.println("<td width='15%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='20%' class=div_input><b>Item Sub Category</b></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='10%' onClick=show_asset_detail_drill('"+rs.getString(1)+"') style='cursor:hand' ><u>"+rs.getString(1)+"</u></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+" </td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
					more_dir = rs.next();
				}
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
