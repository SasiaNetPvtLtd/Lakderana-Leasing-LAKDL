// DEVELOP BY : MILINDA   
// DATE:30-05-2014

import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_MK_MAS_Printer_find extends javax.servlet.http.HttpServlet { 
	
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
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
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
			
			
			
			
			if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Agreement Register</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var m_sav_msg='';");
				
				
				
				//mili
				out.println("function get_vector_normal(m_data){");
				out.println("		credit_approval_data.innerHTML=m_data;");
				out.println("}");
				//mili
				out.println("function validate_data(){"); 
				out.println("	return true;"); 
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
				
				
				
				
				
				/*out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				*/
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Printer_find?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Printer_find?chksql=main_page';"); 
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
				
				out.println("function display_data(val){"); 
				out.println("	if(val==3){"); 
				
				//out.println("		document.Form1.action=servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"sav_print_find\";"); 
				//out.println("		document.Form1.submit();"); 
				out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"sav_print_find\";");  
				out.println("		document.Form1.submit();	"); 		
				out.println("	}else{"); 
				out.println("		\"no data\" "); 
				out.println("	} "); 
				out.println("}	"); 
				
				//added mili
				out.println("function makeRequest_detail() {");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"ac_getdata?chksql=LOAD_USER_APPROVAL_2\";");
				//out.println("window.open(m_url);");
				out.println("		load_interface(m_url,'NO');");
				out.println("}");
				
				out.println("function add_data(){");
				out.println("	var num;");
				//out.println("	m_url=servlet_client_url+":"+client_t3_port+"/"+client_name+"ac_getdata?chksql=printuser";");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"ac_getdata?chksql=printuser\";"); 
				//out.println("window.open(m_url);");
				out.println("	top.frames[0].document.applets[0].getdata(m_url);");
				out.println("	m_size=top.frames[0].document.applets[0].outvec_value.size();");
				out.println("	vec_pos=0;");
				out.println("	lineno=parseInt(document.Form1.Hid_rows.value);");
				//lineno=1;
				out.println("	m_row=\"\"; ");
				out.println("	if (m_size==0) {");
				//alert('No Pending Agreements for Verification');
				out.println("		document.Form1.Hid_rows.value=lineno;");
				out.println("	}");
				out.println("	else{");
				out.println("		while (vec_pos<m_size) {");
				out.println("			m_dealnum=top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos);");
				//m_dealnum_type=top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+1);
				out.println("			m_row=m_row+'<TR  >';");
				out.println("			m_row=m_row+'<TD ALIGN=\"left\" >'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos)+'<INPUT TYPE=\"Hidden\" NAME=\"HID_PRINTER'+lineno+'\" VALUE='+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos)+'></TD>';");
				out.println("			m_row=m_row+'<TD ALIGN=\"left\" >'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+1)+'<INPUT TYPE=\"Hidden\" NAME=\"HID_PINTPATH'+lineno+'\" VALUE='+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+1)+'></TD>';");
				out.println("			m_row=m_row+'<TD ALIGN=\"left\" >'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+2)+'<INPUT TYPE=\"Hidden\" NAME=\"HID_SHARE'+lineno+'\" VALUE='+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+2)+'></TD>';");
				//m_row=m_row+'<TD ALIGN="Center" >'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+3)+'</TD>';
				//m_row=m_row+'<TD ALIGN="Center" >'+format_noobject(top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+4))+'</TD>';
				//m_row=m_row+'<TD ALIGN="Center" >'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+5)+'<INPUT TYPE="Hidden" NAME="HID_PROD_TYPE'+lineno+'" VALUE="'+top.frames[0].document.applets[0].outvec_value.elementAt(vec_pos+7)+'"></TD>';
				//m_row=m_row+'<TD ALIGN="Center"><INPUT TYPE="button" class="mainbut4" VALUE="Details" NAME=DETAIL'+lineno+'  ONCLICK=show_info_1("'+m_dealnum+'")></TD>';
				//m_row=m_row+'<TD ALIGN="Center"><INPUT TYPE="checkbox" NAME=SEL_'+lineno+' value="off"  onchange="val(this);"></TD></TR>';
				out.println("			vec_pos=vec_pos+4;");
				out.println("			lineno=lineno+1;");
				out.println("		}");
				out.println("		document.Form1.Hid_rows.value=lineno;");
				out.println("		m_writedata ='<TABLE WIDTH=\"100%\"  STYLE=\"{color: black; font: 9pt arial;}\">'+");
				out.println("			'<TR >'+");
				out.println("			'</TR></TABLE>'+");
				out.println("			'<TABLE WIDTH=\"100%\"  border=\"\1\" style=\"border:1px solid black;border-collapse:collapse;\">'+");
				out.println("			'<TR BGCOLOR=\"darkblue\">'+");
				out.println("			'<TD WIDTH=\"15%\" ALIGN=\"Center\"><B><font color=\"white\">Printer Name</font></B></TD>' +");
				out.println("		'<TD WIDTH=\"18%\" ALIGN=\"Center\"><B><font color=\"white\">Printer Path</font></B></TD>'+");
				out.println("			'<TD WIDTH=\"10%\" ALIGN=\"Center\"><B><font color=\"white\">Server Name</font></B></TD>'+");
				//'<TD WIDTH="15%" ALIGN="Center"><B>PrinterType</B></TD>'+
				//'<TD WIDTH="15%" ALIGN="Center"><B><font color="white">Select</font></B></TD>'+
				
				out.println("			'</TR>' + m_row+'</TABLE><br><br>'; ");
				//clientmgr.innerHTML=m_writedata;
				out.println("		clientmgr.innerHTML=clientmgr.innerHTML+m_writedata; ");
				out.println("	}");
				out.println("}");
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest_detail();\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Credit Process - Printer Find</td>"); 
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
				out.println("<td width='15%' >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"RUN\" onClick=\"display_data(3)\" ></td>"); 
				out.println("</table>");
				
				out.println("<br>"); 
				out.println("<DIV id='credit_approval_data'  class=div_input></DIV>");
				out.println("<br>"); 
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