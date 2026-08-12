import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
/* Created by Chandana on 08/01/2008 */

public class LAKDL_AF_RE_Invoice_Printing_Normal extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3,rs5,rs6,rs7,rs8,rs9;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
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
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoicing - Invoice Print </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[3];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[4];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[5];");
			out.println("		}");
			out.println("}");
			 
			//To validate from date & to date
			out.println("function validate_date(){");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			
      out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
      out.println("			else {");
			out.println("   		alert(' Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			

			
			
			
			
			out.println("}");
			
	
			
			out.println("function makeRequest_detail() {");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println(" 	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			
			out.println(" 	m_to_date   = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println("		if(validate_date()) {");
			out.println("   if(document.Form1.CLIENT_CODE.value!='') {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_statment_of_accomadatons_Report?chksql=invoice_analysis&m_from_date=\"+m_from_date+\"&m_to_date=\"+m_to_date+\"&order_by=ENT_DATE&sort_by=ASC\";");
			//out.println("		window.open(m_url);");
			//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=40,top=200,width=1000,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("   }");
			out.println("   } else {");
			out.println("   DIV_TXT_CLIENT_CODE.color='red' "); 
			out.println("  	  	 }");

			out.println("}");

			
			out.println("function get_vector_normal(m_data){");
			out.println("		m_invoice_details.innerHTML=m_data;");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Invoice_Printing_Normal?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Invoice_Printing_Normal?chksql=main_page';"); 
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
			out.println("	help_box.innerHTML=\" Invoicing - Invoice Print - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Invoicing - Invoice Print \";"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=get_quart_date\";");
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
				out.println("    this.valout=new Array(10);"); 
				out.println("}");
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("	oBj = new MyDialog();"); 
				out.println(" oBj.valout[1]=\" \";"); 
				out.println(" oBj.valout[2]=\" \";"); 
				out.println(" oBj.valout[3]=\" \";"); 
				out.println("	popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("		if(oBj.valout[1]!=\"Next\"){"); 
				out.println("			if(IfCount==\"1\"){"); 
				out.println("			client_assign(oBj);"); 
				out.println("			}"); 
				out.println("			if(IfCount==\"3\"){"); 
				out.println("			help_value_assign_collection(oBj);"); 
				out.println("			}"); 
				out.println("		}");
				out.println("		else{"); 
				out.println("			Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("			return false;"); 
				out.println("		}"); 
				out.println("	}");
				out.println("	else{"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}"); 
				out.println("	}");
				out.println("	}	");
				out.println("}"); 

				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 

				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 

				out.println("function client_help(){");
				out.println(" document.Form1.hid_help_type.value='1';");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";"); 
				out.println("	HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");		
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
				out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
				out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
				out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
				out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
				out.println(" 	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
				out.println(" 	m_to_date   = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
				out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
				//call to a function 
				out.println("print_report3(document.Form1.CLIENT_CODE.value,m_from_date,m_to_date);");
				out.println("}");
				// ======================== Added by Dineth on 24-07-2008 ========================
				out.println("function print_report3(val,from_date1,to_date1){");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Invoice_Printing_Normal?chksql=get_invoice_details&client_code=\"+val+\"&from_date=\"+from_date1+\"&to_date=\"+to_date1;");	
				out.println("		load_interface(m_url,'NORM');");
				out.println("}");
				// ======================== End by Dineth on 24-07-2008 ==========================
			
			
			
			out.println("function bulk_print(val){"); 
			//out.println("alert('val--'+val);		"); 
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println(" 	m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_client_code = document.Form1.CLIENT_CODE.value ");

			
			out.println(" 	m_to_date   = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println("		if(validate_date()) {");		
			out.println("   if(document.Form1.CLIENT_CODE.value!='') {");
		  out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Invoice_Printing?chksql=PRINT&client_code=\"+m_client_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&print_type=\"+val+\"\";");
		  out.println("document.Form1.submit();");	
			out.println("popupwin = window.open(m_url,'displayWindow45','left=0,top=200,width=790,height=310,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");					
			out.println("   } else {");
			out.println("   DIV_TXT_CLIENT_CODE.style.color='red' "); 
			out.println("  	  	 }");

			out.println("}");
			out.println("}");
			// =====================  Added by Dineth on 24-07-2008 ======================
			out.println("function print_details(fin_val,cli_code,inv_no){  ");
			out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Invoice_Printing_Normal?chksql=print_det&finance_no=\"+fin_val+\"&client_code=\"+cli_code+\"&invoice_no=\"+inv_no+\"\";");
			out.println("popupwin = window.open(m_url,'displayWindow45','left=0,top=200,width=790,height=310,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");			
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
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=val;");
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
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=val;");
			out.println("date2=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value;");
			out.println("}");
			out.println("}");
			
			
			//===============================================
			
			// =====================  End by Dineth on 24-07-2008   ======================
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); //added by Prabash on 08-02-2012
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Invoicing - Invoice Print </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");
			
			out.println("<table class='table' width='100%'  >"); 
			out.println("</table>");
			out.println("<BR><BR>");			
			//================ Commented by Dineth on 24-07-2008 ===========================
			/*out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='50' style='{width=150px}' size='50' onblur=\"client_help()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
			out.println("</td>");
			out.println("<td width='*%' ></td>");

			out.println("</tr>"); 
			out.println("</table>");*/
			//================ Comment end =================================================
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			//out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>Date As At*</b></DIV></td>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FROM_DATE'  class=div_input>From Date</DIV></td>"); 
			//modified by madhawa add checkMonthLength on onblur event
			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)\" >");
			//modified by madhawa add checkMonthLength on onblur event
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)\" >");
			//modified by madhawa add checkMonthLength on onblur event
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)\"  > "); // comment  by Prabash on 08-02-2012	
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)\"  > <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	 // added by Prabash on 08-02-2012
			out.println("</td> "); 
			out.println("<td width='5%' ></td>");
			out.println("<td width='10%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>To Date</DIV></td>"); 
			//modified by madhawa add checkMonthLength on onblur event
			out.println(" <TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" >");
			//modified by madhawa add checkMonthLength on onblur event
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" >");
			//modified by madhawa add checkMonthLength on onblur event
			//out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" >");	 //comment by Prabash on 08-02-2012
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)\" ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a>");	 //Added by Prabash on 08-02-2012
			out.println("</td> ");
			out.println("<td width='*%' ></td>");
			out.println("</table>");  
			
			
			//===================== Added by Dineth on 24-07-2008 ===============================
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='50' style='{width=150px}' size='50' onblur=\"client_help()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"...\" onClick=\"client_help()\">"); 
			out.println("</td>");
			out.println("<td width='*%' ></td>");

			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br><br><br><br>");
			
			//===================== End by Dineth on 24-07-2008 =================================
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'><DIV ID=m_invoice_details></DIV></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 

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
			// ===================== Added by Dineth on 23-07-2008 ======================================
			else if (m_chksql.trim().equals("get_invoice_details")) {
			
			String m_client_code     = req.getParameter("client_code");
			String m_from_date      = req.getParameter("from_date");
			String m_to_date        = req.getParameter("to_date");
			
			String sql1="SELECT FINANCE_NO, "+
			            " INVOICE_NO, "+
									" TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
									" TOTAL_AMOUNT  "+
			            " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+ 
									" WHERE CLIENT_CODE='"+m_client_code+"' "+
									" AND INVOICE_TYPE='INV_GENER'  "+
									" AND ACTIVE_STATUS='Y' "+
									" AND TOTAL_AMOUNT <> 0 "+
									" AND TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
									" AND TO_DATE(TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')" ;
									
			rs5 = stmt4.executeQuery(sql1);
			boolean more = rs5.next();
						
			if(!more){
			  out.println("<table border='0' width='100%' class='table' align='center'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' align='center'><b>No Records</b></td></tr>");
				out.println("</table>");
			}
			  String finance_no="",invoice_no="";
			
			if(more){
			  out.println("<table border='0' width='100%' class='table' align='center'>"); 		
				out.println("</tr>");
				out.println("<td width='20%' class='rep-body1' align='left'><b>Finance No</b></td>");
				out.println("<td width='20%' class='rep-body1' align='left'><b>Invoice No</b></td>");
				out.println("<td width='10%' class='rep-body1' align='left'><b>Value Date</b></td>");
				out.println("<td width='10%' class='rep-body1' align='right'><b>Total Amount&nbsp;</b></td>");
				out.println("<td width='10%' class='rep-body1' align='center'>&nbsp;</td>");
				out.println("<td width='*%' class='rep-body1' align='center'>&nbsp;</td>");
				out.println("</tr>");
				
			
			
			while(more){
			  finance_no     = rs5.getString(1);
				invoice_no     = rs5.getString(2);
			  out.println("</tr>");
				out.println("<td width='20%' class='rep-body1' align='left'>"+finance_no+"</td>");
				out.println("<td width='20%' class='rep-body1' align='left'>"+invoice_no+"</b></td>");
				out.println("<td width='10%' class='rep-body1' align='left'>"+rs5.getString(3)+"</b></td>");
				out.println("<td width='10%' class='rep-body1' align='right'>"+nf.format(rs5.getDouble(4))+"&nbsp;</b></td>");
				out.println("<td width='10%' class='rep-body1'  align='center'><input class='but_input' type='button' name='BUT_PRINT' value=\" Print \" onClick=\"print_details('"+finance_no+"','"+m_client_code+"','"+invoice_no+"')\"></td>");
				out.println("<td width='*%' class='rep-body1'  align='center'>&nbsp;</td>");
			  out.println("</tr>");
				more=rs5.next();
			}
			out.println("</table>");			
			
			}
			
			
			
			/*out.println("<TABLE BORDER='0' WIDTH='100%' >");
			out.println("<TR><TD>Finance No</TD><TD>Invoice No</TD><TD>Value Date</TD><TD>Total Amount</TD><TD>&nbsp;</TD></TR>");
			
			while(more){
			  String finance_no     = rs5.getString(1);
				String invoice_no     = rs5.getString(2);
				out.println("<TR><TD>"+rs5.getString(1)+"</TD><TD>"+rs5.getString(2)+"</TD><TD>"+rs5.getString(3)+"</TD><TD>"+nf.format(rs5.getDouble(4))+"</TD><TD><input class='but_input' type='button' name='BUT_PRINT' value=\" Print \" onClick=\"print_details('"+finance_no+"','"+m_client_code+"','"+invoice_no+"')\"></TD></TR>");
				more=rs5.next();
				}
			out.println("</TABLE>");
			*/
			
			
			}
			
			// ============================== End by Dineth on 23-07-2008 ================================
			// ============================== Added by Dineth on 24-07-2008 ==============================
			
			else if (m_chksql.trim().equals("print_det")){
			
			
			String m_finance_no    = req.getParameter("finance_no");
			String m_cli_code      = req.getParameter("client_code");
			String m_inv_no        = req.getParameter("invoice_no");
			String m_orient_name   = "";
			String m_orient_add1   = "";
			String m_orient_add2   = "";
			String m_orient_city_name = "";
			String m_orient_tel_no    = "";
			String m_orient_fax_no    = "";
			String m_LAKDL_vat_no     = "";
			String m_client_name   = "";
			String m_add1          = "";
			String m_add2          = "";
			String m_city					 = "";
			String m_date					 = "";
			String m_tax_no				 = "";
			String m_date_sysdate  = "";
			String m_vat_precentage= "";
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoicing - Invoice Print </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</SCRIPT>");
			// ============================= Retrieve Company Details ====================================
			 rs = stmt.executeQuery(" SELECT "+
		    " COMPANY_NAME, "+
		    " ADDRESS1, "+
		    " ADDRESS2, "+
		    " CITY, "+
		    " TEL_NO, "+
		    " FAX_NO,  "+
				" VAT_RATE, "+
				" VAT_REG_NO "+
				" FROM LAKDL.AF_CO_MAS_COMPANY_DETAILS ");

		boolean  more = rs.next();		
						
						if(more)
						{
						m_orient_name=rs.getString(1);
						m_orient_add1=rs.getString(2);
						m_orient_add2=rs.getString(3);
						m_orient_city_name=rs.getString(4);
						m_orient_tel_no=rs.getString(5);
						m_orient_fax_no=rs.getString(6);
						//m_vat_precentage=rs.getString(7);			
						m_LAKDL_vat_no=rs.getString(8);			
						}
						
					rs6 = stmt5.executeQuery (	" SELECT "+
					" CLIENT_CODE, "+
					" UPPER(FULL_NAME), "+
					" UPPER(ADDRESS1), "+
					" UPPER(NVL(ADDRESS2,' ')), "+
					" UPPER(NVL(LAKDL.AF_CO_GET_CITY_NAME(CITY_CODE),' ')) CITY_NAME, "+
					" TO_CHAR(SYSDATE,'DD-MON-YY'), "+
					" VAT_REG_NO "+
					" FROM LAKDL.AF_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE='"+m_cli_code+"' ");
					

				boolean more6 = rs6.next();	
				
				if(more6){
				m_client_name=rs6.getString(2);
				m_add1=rs6.getString(3);
				m_add2=rs6.getString(4);
				m_city=rs6.getString(5);
			  m_date="Date "+rs6.getString(6);
				m_tax_no=rs6.getString(7);
				m_date_sysdate=rs6.getString(6);
				}
				
				// ========================== End Client Details ==========================================
				
				// ========================== Retrieve Vat Percentage =====================================
				
				

					
					out.println("<br><br><br><br><br><br>");
										
					out.println("<table border='0' width='100%' class='table' align='center'>"); 		
					out.println("<TR>");
					if(m_tax_no==null){
					out.println("<TD WIDTH='100%' STYLE='{font:10pt Times New Roman;text-align:center;}' >Invoice</TD>");
					}
					else {
					out.println("<TD WIDTH='100%' STYLE='{font:10pt Times New Roman;text-align:center;}' >Tax Invoice</TD>");
					}
										
					out.println("</TR>");
          out.println("</TABLE>");		
										
					out.println("<br>");
					out.println("<br>");
					
					out.println("<table border='0' width='100%' class='table' align='center'>"); 		
					out.println("<TR>");
					out.println("<TD WIDTH='60%' STYLE='{font:10pt Times New Roman;}'  align='left'>"+m_date+"</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}'  align='left'>&nbsp;</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}'  align='left'>&nbsp;</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD WIDTH='60%' STYLE='{font:10pt Times New Roman;}' align='left'>"+m_client_name+"</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}' align='left'>LAKDL VAT No</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'>"+m_LAKDL_vat_no+"</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD WIDTH='60%' STYLE='{font:10pt Times New Roman;}' align='left'>"+m_add1+"</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}' align='left'>Invoice No</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'>"+m_inv_no+"</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD WIDTH='60%' STYLE='{font:10pt Times New Roman;}' align='left'>"+m_add2+"</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'><B>&nbsp;</TD>");
					out.println("</TR>");
									
					out.println("<TR>");
					out.println("<TD WIDTH='60%' STYLE='{font:10pt Times New Roman;}' align='left'>"+m_city+"</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'><B>&nbsp;</TD>");
					out.println("</TR>");
					
					
					out.println("</table>");
					
					out.println("<br><br>");
					
				rs9 = stmt8.executeQuery ("SELECT "+
				" INVOICE_NO, "+
				" TO_CHAR(VALUE_DATE,'DD/MM/YYYY'), "+
				" NET_AMOUNT, "+
				" VAT_AMOUNT , "+
				" TOTAL_AMOUNT, "+
				" TO_CHAR(VALUE_DATE,'MON YYYY'), "+
				" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
				" FINANCE_NO "+
				//" ,TO_CHAR(VALUE_DATE,'DD-MON-YY') "+
				" ,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
				" FROM LAKDL.AF_CO_PRO_INVOICE "+
				" WHERE INVOICE_NO=UPPER('"+m_inv_no+"')");  

				
				boolean more10  = rs9.next();
				if(more10){
					
				rs7 = stmt6.executeQuery (" SELECT LAKDL.AF_CO_GET_VAT_ON_RENTAL_PRO(transaction_type,'"+rs9.getString(9)+"')  "+
				" FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE  FINANCE_NO='"+m_finance_no+"' ");
				
				if(rs7.next()){
				m_vat_precentage=rs7.getString(1)+" %";
				}
					
					out.println("<table border='0' width='100%' class='table' align='center'>"); 		
					
					if(m_tax_no!=null){
					out.println("<TR>");
					out.println("<TD WIDTH='50%' STYLE='{font:10pt Times New Roman;}' align='left'>Cutomer VAT No</TD>");
					out.println("<TD WIDTH='2%' STYLE='{font:10pt Times New Roman;}' align='left'>-</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'>"+m_tax_no+"</TD>");
					out.println("</TR>");
					}
					
					out.println("<TR>");
					out.println("<TD WIDTH='50%' STYLE='{font:10pt Times New Roman;}' align='left'>Rental Due For the Month</TD>");
					out.println("<TD WIDTH='2%'  STYLE='{font:10pt Times New Roman;}' align='left'>-</TD>");
					out.println("<TD WIDTH='*%'  STYLE='{font:10pt Times New Roman;}' align='left'>"+rs9.getString(6)+"</TD>");
					out.println("</TR>");
					out.println("</Table>");
					
					out.println("<table border='0' width='100%' class='table' align='center'>"); 		
					out.println("<TR>");
					out.println("<TD colspan='8' WIDTH='100%' STYLE='{font:10pt Times New Roman;}' align='right'>(Amount In Rupees)</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD colspan='8' WIDTH='100%' STYLE='{font:10pt Times New Roman;}' align='right'>&nbsp;</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD colspan='8' WIDTH='100%' STYLE='{font:10pt Times New Roman;}' align='left'>-----------------------------------------------------------------------------------------------------------------------------------------------------------</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD WIDTH='5%'  STYLE='{font:10pt Times New Roman;}' align='left'>SIno</TD>");
					out.println("<TD WIDTH='20%' STYLE='{font:10pt Times New Roman;}' align='left'>Finance No</TD>");
					out.println("<TD WIDTH='20%' STYLE='{font:10pt Times New Roman;}' align='left'>Invoice No</TD>");
					out.println("<TD WIDTH='10%' STYLE='{font:10pt Times New Roman;}' align='left'>Due Date</TD>");
					out.println("<TD WIDTH='10%' STYLE='{font:10pt Times New Roman;}' align='left'>Net Rent</TD>");
					out.println("<TD WIDTH='10%' STYLE='{font:10pt Times New Roman;}' align='left'>VAT %</TD>");
					out.println("<TD WIDTH='10%' STYLE='{font:10pt Times New Roman;}' align='left'>VAT</TD>");
					out.println("<TD WIDTH='15%' STYLE='{font:10pt Times New Roman;}' align='left'>Gross Rent</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD colspan='8' WIDTH='100%' STYLE='{font:10pt Times New Roman;}' align='left'>-----------------------------------------------------------------------------------------------------------------------------------------------------------</TD>");
					out.println("</TR>");
					
					
					
					out.println("<TR>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>1</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+rs9.getString(8)+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+rs9.getString(1)+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+rs9.getString(2)+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(3))+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+m_vat_precentage+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(4))+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(5))+"</TD>");
					out.println("</TR>");
					
					
					out.println("<TR>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD colspan='5'  STYLE='{font:10pt Times New Roman;}' align='left'>-------------------------------------------------------------------------------------</TD>");
					out.println("</TR>");
					
					out.println("<TR>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>Total</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(3))+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(4))+"</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>"+nf.format(rs9.getDouble(5))+"</TD>");

		
					
					out.println("<TR>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  STYLE='{font:10pt Times New Roman;}' align='left'>&nbsp;</TD>");
					out.println("<TD  colspan='5'  STYLE='{font:10pt Times New Roman;}' align='left'>-------------------------------------------------------------------------------------</TD>");
					out.println("</TR>");
					}
					out.println("</table>");
					
					
					out.println("<br><br>");
					
					out.println("<table border='0' width='100%' class='table' align='left'>"); 		
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;text-align:center}' align='left'>TIMELY PAYMENT WOULD BE APPRECIATED</TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>* Cheque payments should be made only in favour of Lakderana Investments Limited - Crossed Account</TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>payee only.</TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>* If the payment is made by a third party cheque, it will be accepted by us only if it is endorsed by the lessee.</TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>* In the event you are settling the payment in cash, please ensure that you obtain receipt immediately from our officer.</TD>");
					out.println("</TR>");
					out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>* Official receipts for cheque/cash payments will be posted within 7 days. If not received, please bring this to the notice</TD>");
					out.println("</TR>");
					/*out.println("<TR>");
					out.println("<TD WIDTH='*%' STYLE='{font:10pt Times New Roman;}' align='left'>* Official receipts for cheque/cash payments will be posted within 7 days. If not received, please bring this to the notice</TD>");
					out.println("</TR>");
					*/
					out.println("</table>");
				

					
					
					/*out.println("<table border='0' width='100%' class='table' align='center'>"); 		
					out.println("<TR>");
					out.println("<TD WIDTH='30%' class='rep-body1' align='left'>Cutomer VAT No</TD>");
					out.println("<TD WIDTH='2%' class='rep-body1' align='left'>-</TD>");
					out.println("<TD WIDTH='*%' class='rep-body1' align='left'>"+m_tax_no+"</TD>");
					out.println("</TR>");
					out.println("</table>");

					out.println("<TABLE BORDER='0' WIDTH='100%' ><B>");
					out.println("<TR>");
					out.println("<TD STYLE='{text-align:left}' WIDTH='60%'>");
					out.println(m_client_name+"<br>");
					out.println(m_add1+"<br>");
					out.println(m_add2+"<br>");
					out.println(m_city+"<br>");
					out.println("</TD>");
					out.println("<TD WIDTH='40%' STYLE='{valign:top;}'>OFCSL VAT No .&nbsp;&nbsp;"+m_LAKDL_vat_no);
					out.println("</TR>");
					out.println("</TABLE>");
					
					
					
			
			boolean more10 = rs9.next();
		  out.println("<TABLE BORDER='0' WIDTH='100%' >");
		  out.println("<TR>");
			out.println("<TD>S1&nbsp;No</TD><TD>Finance No</TD><TD>Due Date</TD><TD>Net Rent</TD><TD>VAT %</TD><TD>VAT</TD><TD>Total Rent</TD></TR>");
			
			if(more10){
			out.println("<TR>");
			out.println("<TD>1</TD>");
			out.println("<TD>"+rs9.getString(8)+"</TD>");
			out.println("<TD>"+rs9.getString(7)+"</TD>");
			out.println("<TD>"+nf.format(rs9.getDouble(3))+"</TD>");
			out.println("<TD>"+nf.format(rs9.getDouble(4))+"</TD>");
			out.println("<TD>"+nf.format(rs9.getDouble(4))+"</TD>");
			out.println("<TD>"+nf.format(rs9.getDouble(5))+"</TD>");
			out.println("</TR>");
			
				}
			
			out.println("</TABLE>");	
			out.println("<TR>");
			out.println("<TD>&nbsp;</TD>");
			*/
			
				
				// ========================= End Vat Percentage ===========================================
				//out.println(m_client_name+"<br>");
				//out.println(m_add1+"<br>");
				//out.println(m_add2+"<br>");
				//out.println(m_city+"<br>");
				//out.println(m_date+"<br>");
				//out.println(m_tax_no+"<br>");
				//out.println(m_vat_precentage+"<br>");
				
				
			out.println("</form>"); 
			out.println("</body>"); 
			out.println("</html>"); 
				
			}
			
			// ============================== End by Dineth on 24-07-2008 ================================	
			else if (m_chksql.trim().equals("invoice_analysis")) {
			
			String m_from_date     = req.getParameter("m_from_date");
			String m_to_date       = req.getParameter("m_to_date");
			String m_ended_date    = "";
			int    m_quart_tot_cont=0;
			double m_quart_tot_amnt=0.00;
			int    m_tot_cont      =0;
			double m_tot_amnt      =0.00;

			
			String Sql=" SELECT "+
			           " "+m_schema_name+".AF_CO_GET_QUATER_CONTRACT_CNT('"+m_from_date+"','"+m_to_date+"','FINLEASE'), "+ 
								 " "+m_schema_name+".AF_CO_GET_QUARTER_FIN_TOT_AMT('"+m_from_date+"','"+m_to_date+"','FINLEASE'), "+
			           " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MIN','FINLEASE'), "+
	  						 " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MAX','FINLEASE'), "+
								 " "+m_schema_name+".AF_CO_GET_TOT_CONTRACT_COUNT('"+m_to_date+"','FINLEASE'), "+
			           " "+m_schema_name+".AF_CO_GET_APP_FINANCE_TOT_AMT('"+m_to_date+"','FINLEASE'), "+
								 			           
								 " "+m_schema_name+".AF_CO_GET_QUATER_CONTRACT_CNT('"+m_from_date+"','"+m_to_date+"','OPELEASE'), "+ 
								 " "+m_schema_name+".AF_CO_GET_QUARTER_FIN_TOT_AMT('"+m_from_date+"','"+m_to_date+"','OPELEASE'), "+
								 " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MIN','OPELEASE'), "+
	  						 " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MAX','OPELEASE'), "+
								 " "+m_schema_name+".AF_CO_GET_TOT_CONTRACT_COUNT('"+m_to_date+"','OPELEASE'), "+
			           " "+m_schema_name+".AF_CO_GET_APP_FINANCE_TOT_AMT('"+m_to_date+"','OPELEASE'), "+
                 
 								 " "+m_schema_name+".AF_CO_GET_QUATER_CONTRACT_CNT('"+m_from_date+"','"+m_to_date+"','HIREPURCH'), "+ 
								 " "+m_schema_name+".AF_CO_GET_QUARTER_FIN_TOT_AMT('"+m_from_date+"','"+m_to_date+"','HIREPURCH'), "+
			           " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MIN','HIREPURCH'), "+
	  						 " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MAX','HIREPURCH'), "+
  							 " "+m_schema_name+".AF_CO_GET_TOT_CONTRACT_COUNT('"+m_to_date+"','HIREPURCH'), "+
			           " "+m_schema_name+".AF_CO_GET_APP_FINANCE_TOT_AMT('"+m_to_date+"','HIREPURCH'), "+	 
 
								 " "+m_schema_name+".AF_CO_GET_QUATER_CONTRACT_CNT('"+m_from_date+"','"+m_to_date+"','LOANS'), "+ 
								 " "+m_schema_name+".AF_CO_GET_QUARTER_FIN_TOT_AMT('"+m_from_date+"','"+m_to_date+"','LOANS'), "+
			           " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MIN','LOANS'), "+
	  						 " "+m_schema_name+".AF_CO_GET_INTEREST_RATE('"+m_from_date+"','"+m_to_date+"','MAX','LOANS'), "+
								 " "+m_schema_name+".AF_CO_GET_TOT_CONTRACT_COUNT('"+m_to_date+"','LOANS'), "+ 
			           " "+m_schema_name+".AF_CO_GET_APP_FINANCE_TOT_AMT('"+m_to_date+"','LOANS') "+	
	
	               " FROM DUAL ";
			  
														
			rs = stmt.executeQuery(Sql);
			
			boolean more = rs.next();
			
			
			
			 
				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
      rs1 = stmt1.executeQuery(Comp_Name);
			
			boolean more1 = rs1.next();			
			
				m_ended_date = rs1.getString(2);	
				
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			
			  out.println("function get_detail(val){");
				//out.println("alert('qqqqqqqqq=='+val);"); 
				//out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_statment_of_accomadatons_Report?chksql=Load_Details&State=\"+val+\"&m_date="+m_date+" \";");
			  //out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_report?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
		  	out.println("    popupwin=window.open(m_url,'displayWindow2','left=40,top=300,width=1000,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				
				
				out.println("}"); 
							
			
			
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (PROVISION FOR BAD DOUBTFUL ACCOMMODATIONS) DIRECTION NO 2 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>STATEMENT OF ACCOMMADATIONS GRANTED AND TOTAL OUTSTANDING ACCOMMODATIONS</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/02/06</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs1.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Quarter Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs1.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				
			  out.println("<br>");
				
        if(!more){
				out.println("<table class='table' align='center' border='0' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td align='center' width='*%' ><b>No Records.</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				} else{
				
				
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor='black'>");
				out.println("<tr>");
				out.println("<td rowspan=\"2\" width=\"35%\">&nbsp;</td>");
				out.println("<td colspan=\"3\" width=\"40%\">Accommodation Granted During the Quarter ended "+m_ended_date+"</td>");
				out.println("<td colspan=\"2\" width=\"25%\">Outstanding Accommodation As at "+m_ended_date+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width=\"10%\">No of contract  </td>");
				out.println("<td width=\"15%\">Total amount granted/financed </td>");
				out.println("<td width=\"15%\">Range of rates of interest(%) </td>");
				out.println("<td width=\"10%\">No of Contracts </td>");
				out.println("<td width=\"15%\">Total Outstanding </td>");
				out.println("</tr>");
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Finance Leases &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(1)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(2))+" &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(3)+"% - "+rs.getString(4)+"% &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(5)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(6))+" &nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Operating Leases &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(7)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(8))+" &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(9)+"% - "+rs.getString(10)+"% &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(11)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(12))+" &nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Hire Purchase &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(13)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(14))+" &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(15)+"% - "+rs.getString(16)+"% &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(17)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(18))+" &nbsp;</td>");
				out.println("</tr>");
        				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Loans & Overdraft Facilities &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(19)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(20))+" &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(21)+"% - "+rs.getString(22)+"% &nbsp;</td>");
				out.println("<td align=\"right\">"+rs.getString(23)+" &nbsp;</td>");
				out.println("<td align=\"right\">"+nf.format(rs.getDouble(24))+" &nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Redeemable cumulative Preference shares </td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Bonds</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Debentures</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");

       	out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Asset back securities</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");

				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Commercial Papers</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Promissory notes</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");

        out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Securitisation</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");
   
        out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; Intercompany credit</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");

        out.println("<tr height=\"30px\">");
				out.println("<td>&nbsp; All Other Accommodations</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">0.00%&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("<td align=\"right\">-&nbsp;</td>");
				out.println("</tr>");

        
				m_quart_tot_cont = rs.getInt(1) + rs.getInt(7) + rs.getInt(13) + rs.getInt(19);
				m_quart_tot_amnt = rs.getDouble(2) + rs.getDouble(8) + rs.getDouble(14) + rs.getDouble(20);
				m_tot_cont  = rs.getInt(5) + rs.getInt(11) + rs.getInt(17) + rs.getInt(23);
        m_tot_amnt  = rs.getDouble(6) + rs.getDouble(12) + rs.getDouble(18) + rs.getDouble(24);
				
				out.println("<tr height=\"30px\">");
				out.println("<td><B>&nbsp; Total &nbsp;</B></td>");
				out.println("<td align=\"right\"><B>"+m_quart_tot_cont+"&nbsp;</td>");
				out.println("<td align=\"right\"><B>"+nf.format(m_quart_tot_amnt)+"&nbsp;</td>");
				out.println("<td align=\"right\"><B>&nbsp;</td>");
				out.println("<td align=\"right\"><B>"+m_tot_cont+"&nbsp;</td>");
				out.println("<td align=\"right\"><B>"+nf.format(m_tot_amnt)+"&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");
				
				
  			}
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");

				
					
			}

			
			else if(m_chksql.equals("Load_Details")){
			
			
			String m_State = req.getParameter("State");
			String m_date = req.getParameter("m_date");
			String contract_det   = ""; 
			double m_equipmnt_val =0;
			double m_rent_arears  =0;
			double m_rent_suspens =0;
			double m_valuation_amt=0;
			
			if(m_State.equals("1")){
			         
             contract_det = " SELECT X.FINANCE_NO, "+ //1
			                      " "+m_schema_name+".af_co_get_client_name(X.CLIENT_CODE), "+ //2
														" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(X.APPLICATION_NO)||' / '||NVL(LAKDL.AF_CO_GET_ALL_REG_NUMBERS(X.FINANCE_NO),' '), "+ //3
														" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO), "+ //4
														" NVL("+m_schema_name+".AF_CO_GET_APPROVED_DATE(X.APPLICATION_NO,'ACTIVATED'),' ')||' to '||LAKDL.AF_CO_GET_LAST_RENTAL_DATE(X.APPLICATION_NO), "+ //5
														" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)||' x '||"+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO), "+ //6
														" NVL("+m_schema_name+".AF_CO_GET_MORE_3MON_RENTS_CNT(X.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0), "+ //7
														" "+m_schema_name+".AF_CO_GET_ARREAS_RENT_CHARGS(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //8 
														" '' WDV, "+ //9
														" "+m_schema_name+".AF_CO_GET_RENT_INCOME_SUSPENSE(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //10
														" '' DEPOSIT, "+ //11
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_DATE(X.APPLICATION_NO),'-'), "+ // 12
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_VAL(X.APPLICATION_NO),0), "+ //13
														" '-' ACTION "+  //14
														
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X "+
														" WHERE X.FINANCE_NO IN "+
														" (SELECT DISTINCT A.FINANCE_NO "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
														" AND  B.APPLICATION_STATUS='ACTIVATED' "+
														" AND  B.TRANSACTION_TYPE IN('FINLEASE','HIREPURCH') "+
														" AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
														" AND  A.ACTIVE_STATUS='Y' "+
														" AND  A.INVOICE_TYPE='INV_GENER' "+
														" AND  ADD_MONTHS(DUE_DATE,3) <=  LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))) ";
														
			} else if(m_State.equals("2")){
			
			contract_det = " SELECT X.FINANCE_NO, "+ //1
			                      " "+m_schema_name+".af_co_get_client_name(X.CLIENT_CODE), "+ //2
														" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(X.APPLICATION_NO)||' / '||NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(X.FINANCE_NO),' '), "+ //3
														" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(X.APPLICATION_NO), "+ //4
														" NVL("+m_schema_name+".AF_CO_GET_APPROVED_DATE(X.APPLICATION_NO,'ACTIVATED'),' ')||' to '||"+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(X.APPLICATION_NO), "+ //5
														" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(X.APPLICATION_NO)||' x '||"+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(X.APPLICATION_NO), "+ //6
														" NVL("+m_schema_name+".AF_CO_GET_MORE_3MON_RENTS_CNT(X.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0), "+ //7
														" "+m_schema_name+".AF_CO_GET_ARREAS_RENT_CHARGS(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //8 
														" '' WDV, "+ //9
														" "+m_schema_name+".AF_CO_GET_RENT_INCOME_SUSPENSE(X.APPLICATION_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+ //10
														" '' DEPOSIT, "+ //11
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_DATE(X.APPLICATION_NO),'-'), "+ // 12
														" NVL("+m_schema_name+".AF_CO_GET_REPO_VALUATION_VAL(X.APPLICATION_NO),0), "+ //13
														" '-' ACTION "+  //14
														
														" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X "+
														" WHERE X.FINANCE_NO IN "+
														" (SELECT DISTINCT A.FINANCE_NO "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
														" AND  B.APPLICATION_STATUS='ACTIVATED' "+
														" AND  B.TRANSACTION_TYPE IN('OPELEASE') "+
														" AND  DUE_DATE  <=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
														" AND  A.ACTIVE_STATUS='Y' "+
														" AND  A.INVOICE_TYPE='INV_GENER' "+
														" AND  ADD_MONTHS(DUE_DATE,3) <=  LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))) ";
			
			        }
			
			
			
			      rs = stmt.executeQuery(contract_det);
			
			      boolean more = rs.next();	
			
			
			
			
			
			
			
			
			
			  				String Comp_Name = " SELECT COMPANY_NAME, "+
				       " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD')||' '|| "+
							 " INITCAP(TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MONTH'))||' '|| "+
							 " TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'YYYY') "+
							 " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
			
      rs1 = stmt1.executeQuery(Comp_Name);
			
			boolean more1 = rs1.next();	 
			
			  
			
			
			  out.println("<HTML>"); 
			  out.println("<HEAD>"); 
			  out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			  out.println("</HEAD>"); 
			  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
			  			
			  out.println("</script>"); 
			  out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table  width=\"1550\" class='table' align='center' border='1' width='100%' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASING ACT NO 56 OF 2000");
				out.println("<BR><b>FINANCE LEASING (PROVISION FOR BAD DOUBTFUL ACCOMMODATIONS) DIRECTION NO 2 OF 2006</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
		  
		
		    out.println("<table  width=\"1550\" class='table' align='center' border='0' width='100%' cellspacing=\"0\" cellpadding=\"0\" >");
				
				if(m_State.equals("2")){
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>OPERATING LEASES IN ARREARS FOR THREE MONTHS OR MORE</b></td>");
				out.println("</tr>");
				}else if(m_State.equals("1")){ 
				out.println("<tr>");
				out.println("<td align='center' width='*%' ><b>FINANCE LEASES & HP IN ARREARS FOR THREE MONTHS OR MORE</b></td>");
				out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("<br>");

	     
				
				out.println("<table class='table' align='center' border='0' width='100%' cellspacing=\"0\" cellpadding=\"0\">");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Format No</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >SNBFI/FL/02/03</td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >Name of RFLE</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' ><b>"+rs1.getString(1)+"</b></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td align='left' width='20%' >As at Quarter Ended</td>");
				out.println("<td align='center' width='5%' >:</td>");
				out.println("<td align='left' width='*%' >"+rs1.getString(2)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				
			  out.println("<br>");
				
				
				
				
				
				out.println(" <table width=\"1550\"  border=\"1\"  bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
        out.println(" <tr>"); 
			  out.println(" <td width=\"100\" rowspan=\"2\">Contract </td>");
			  out.println(" <td width=\"200\" rowspan=\"2\">Name of the Borrower</td>");
			  out.println(" <td width=\"100\" rowspan=\"2\">Type of Equipment</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Value of the equipment at the commencement</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Date of inception & expiry of the contract  5</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Terms of Contract   6</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">No of rental in arrears   7</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Amount of rentals in arrears & othercharges   8</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">WDV of the equipment    9</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Rental income in suspense   10</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Deposit/ Prepaid rentals   11</td>");
			   out.println(" <td colspan=\"2\">Equipment Returned repossessd </td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Net Exposure   14</td>");
			   out.println(" <td width=\"100\" rowspan=\"2\">Action taken on return/ reposs: equipment  15</td>");
			   out.println(" <td width=\"50\" rowspan=\"2\">Age  16</td>");
			 out.println(" </tr>");
			 out.println(" <tr>");
			   out.println(" <td width=\"100\">Date  12</td>");
			   out.println(" <td width=\"100\">Valuation  13</td>");
			 out.println(" </tr>");
			 
				m_equipmnt_val=0;
				m_rent_arears =0;
				m_rent_suspens=0;
				
				while(more){
				out.println(" <tr>");
			   out.println(" <td align=\"center\">&nbsp;"+rs.getString(1)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(2)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(3)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(5)+"</td>");
			   out.println(" <td align=\"left\"  >&nbsp;"+rs.getString(6)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+rs.getString(7)+"&nbsp;&nbsp;&nbsp;</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(8))+"&nbsp;</td>");
			   out.println(" <td>&nbsp;9</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(10))+"&nbsp;</td>");
			   out.println(" <td>&nbsp;11</td>");
			   out.println(" <td align=\"center\">&nbsp;"+rs.getString(12)+"</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+nf.format(rs.getDouble(13))+"&nbsp;</td>");
			   out.println(" <td>&nbsp;14</td>");
			   out.println(" <td>&nbsp;15</td>");
			   out.println(" <td align=\"right\" >&nbsp;"+rs.getString(7)+"&nbsp;&nbsp;&nbsp;</td>");
			 out.println(" </tr>");
			 				
				m_equipmnt_val = m_equipmnt_val + rs.getDouble(4);
				m_rent_arears  = m_rent_arears  + rs.getDouble(8);
				m_rent_suspens = m_rent_suspens + rs.getDouble(10);
				m_valuation_amt= m_valuation_amt+ rs.getDouble(13);
				
				more = rs.next();
				}
				
				 out.println(" <tr>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_equipmnt_val)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_rent_arears)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_rent_suspens)+"&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td align=\"right\">"+nf.format(m_valuation_amt)+"&nbsp;</td>"); 
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			   out.println(" <td>&nbsp;</td>");
			 out.println(" </tr>");
			out.println(" </table>");
							
				
				
				
				
				
				
		
		
		
		
			}
			
			else if(m_chksql.equals("load_receipts")){
			
			  String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
			
							
			if(m_from_date==null){
			m_from_date="";
			}
			if(m_to_date==null){
			m_to_date="";
			}

			if(!m_from_date.equals("") && !m_to_date.equals("")){	
			
			
			/*
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,OTH_COMMENTS,CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,A.ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+ 
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			     " ORDER BY "+m_sort_by+"  "+m_order_by+" ");	 */
			
			
			
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					 " AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");	 
						
					 //" TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE('01-01-2007','DD-MM-YYYY') "+ 
					 //" AND TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('01-01-2009','DD-MM-YYYY') ");
			
			
			
			
			
			
			



			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	

						
			rs = stmt.executeQuery ("SELECT A.REC_NO,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_CODE, "+
			     " DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Chaeque','STD_ORD','Standing Order','DIR_DEP','Direct Deposit',SETTLE_MODE) SETTLE_MODE, "+
					 " A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,TO_CHAR(EFF_VALDATE,'DD-MM-YY') EFF_VALDATE,NVL(OTH_COMMENTS,'-'),CURR_CODE, "+
					 " A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR,REC_AMOUNT_REP_CURR, "+
					 " EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO,A.ENT_DATE "+
					 " FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					 " "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					 " WHERE  A.REC_NO = B.REC_NO AND STATUS<>'C' AND "+  
					 " BAL_TOBE_RECEIVE>0 AND "+
					 " ORDER BY "+m_order_by+"  "+m_sort_by+" ");		
						
						
		
			}		
						
						
						
				
					boolean mflag=true;							
					boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Arrears For Three Months Or More Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("function sort_data(m_sort_col) {");
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
					 out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_receipt_unalocated_det_rept?chksql=load_receipts&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type;");
					 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");		
					 out.println(" window.location.href=m_url;"); 
					 out.println("}");
 	
					 out.println("	function show_account_type(m_acc_code){");
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_trail_balance_report?chksql=LOAD_ACC_TYPE_CODE_DRILL&acc_type_code=\"+m_acc_code+\"\";");
					 out.println("   window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=200,width=500');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Receipt Unallocated Report From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
					
					out.println("<table align='center' border=\"0\" width='100%' class='table'>");  //colspan=13
					
					out.println("<tr></tr>");
		
				  out.println("<tr class=pdn_txtpos2 >");
		
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Receipt  No  '    onclick=sort_data('REC_NO') >Receipt  No</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_CODE') >Client Code</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('SETTLE_MODE') >Settlement Mode</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by -  Receipt Amount '    onclick=sort_data('REC_AMOUNT') >Receipt Amount</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Allocated Amount '    onclick=sort_data('ALLOCATED_AMOUNT') >Allocated Amount</td>"); 
					out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Balance Amount '    onclick=sort_data('BAL_TOBE_RECEIVE') >Balance Amount</td>"); 
				  out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Effective Value Date '    onclick=sort_data('EFF_VALDATE') >Effective Value Date</td>"); 
					out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Comments  '    onclick=sort_data('EFF_VALDATE') >Comments</td>"); 
					out.println("</tr>");
					  

					}
					
					int j=0;
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_std_order_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
			      out.println("<td width='15%' align='left' style= cursor:hand;  ><u>"+rs.getString(2)+"</u></td>");
			      out.println("<td width='10%' align='left'>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_START_DATE_"+j+" value=\""+rs.getString(3)+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"<input class='txt_input' type='hidden' name=TXT_END_DATE_"+j+" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5))+"<input class='txt_input' type='hidden' name=TXT_ACC_NO_"+j+" value=\""+nf.format(rs.getDouble(5))+"\"></td>");
			      out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(6))+"<input class='txt_input' type='hidden' name=TXT_BRANCH_CODE_"+j+" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
			      out.println("<td width='10%' align='center'>"+rs.getString(7)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
						out.println("<td width='15%' align='left'>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_AMOUNT_"+j+" value=\""+rs.getString(8)+"\"></td>");
			      out.println("</tr>");
							more = rs.next();
						j = j+1;	
							
					}	
					     
      	 		out.println("</table>");
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
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
