//Created by Nuwan De Silva on 16/1/2007 at 3.41 pm.
//Purchase Order Main Screen

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Purchase_Order_Main_Screen extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("main_page")){
				
				String m_sort_column   = "PRIORITY";	
				String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Credit - Purchase Order Generation \";"); 
				out.println("}else{");
				out.println("help_box.innerHTML=\"Credit - Purchase Order Generation - \"+m_val;"); 
				out.println("}");
				out.println("}");
				
				
				
				//out.println("function load_roll_value(m_val){"); 
				//out.println("help_box.innerHTML=\" Credit - Purchase Order Approval - \"+m_val;"); 
				//out.println("}"); 
				//out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit - Purchase Order - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_af_cro_pro_purchase_order_main\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				
				out.println("}"); 
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				
				
				out.println("}"); 
				out.println("else{}");
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function load_data(m_app_no,m_app_sts) {");
				out.println(" if(m_app_sts=='IP') { ");
				
				out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order?CLOSE=Y&application_no=\"+m_app_no;"); 
				
				//comment by nuwan de silva on 29-10-07-------------
				out.println("   window.open(m_url,'displayWindowap','left=50,top=60,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				//added by nuwan de silva on 29-10-07-------------
				//out.println("window.location.href=m_url;"); 
				out.println("  }");
				out.println(" else { ");
				out.println("   alert(m_app_no+'  is complete.');"); 
				out.println("  }");
				out.println("}");
				
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function delete_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=delete_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				
				//Added by Mahela on 26-07-2007
				out.println("function View_Letter(){");	                            
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_View_Letter\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1,fullscreen=1');");
				//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order?application_no="+m_application_no+"';"); 
				out.println("}");
				
				
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
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_sort_column=m_sort_col;");
				out.println("m_order_by_type=m_order_by_type;");
				out.println("get_purchase_orders(m_sort_col,m_order_by_type);");
				out.println("}");
				
				//thamali 2012.02.15
				
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'PO_GENERATIONSql','1');");
				out.println("}");
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.TXT_CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println("}");
				
				out.println("function app_help(){");
				out.println("	 if(document.Form1.CLIENT_CODE.value==\"\"){");
				out.println("		Crit=document.Form1.APP_CODE.value+\"@\";");
				out.println("       HelpBox('1','10','0',Crit,'PO_GENERATIONSql2','5');");
				out.println("  }else{");
				out.println("       Crit=document.Form1.APP_CODE.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\";");
				out.println("       HelpBox('1','10','0',Crit,'PO_GENERATIONSql3','5');");
				out.println("  }");
				out.println("}");
				
				out.println("function app_assign(oBj){");
				out.println(" document.Form1.TXT_CLIENT_NAME.value =oBj.valout[3]");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println(" document.Form1.APP_CODE.value =oBj.valout[4]");
				out.println(" document.Form1.TXT_CONTRACT_NUMBER.value =oBj.valout[6]");
				
				out.println("}");
				
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_Help_Servlet?class_in="+m_client_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		vehicle_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		app_assign(oBj);"); 
				out.println("		}");
				
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	}	"); 
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
				
				out.println("function check_client(val) {");
				out.println(" document.Form1.TXT_CLIENT_NAME.value = ''  ");
				out.println("}");	
				
				out.println("function get_purchase_orders(m_sort_column,m_order_by_type){");
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Purchase_Order_approval&sort_column=\"+m_sort_column+\"&client=\"+document.Form1.CLIENT_CODE.value+\"&order_by_type=\"+m_order_by_type+\"&ac_status=ENT&ac_status2=VERIFY\";");//Added by prabash on 10-02-2012
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=m_prime_chk_load_data&sort_column=\"+m_sort_column+\"&client=\"+document.Form1.CLIENT_CODE.value+\"&order_by_type=\"+m_order_by_type+\";");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=m_prime_chk_load_data&sort_column=\"+m_sort_column+\"&client=\"+document.Form1.CLIENT_CODE.value+\"&app=\"+document.Form1.APP_CODE.value+\"&order_by_type=\"+m_order_by_type+\"\";");//Added by prabash on 10-02-2012
				out.println("load_interface(m_url,'normal');");
				out.println("}");	
				
				out.println("function get_vector_normal(m_data){");
				out.println("		validation_nromal_data.innerHTML=m_data;");
				out.println("}");
				
				
				out.println("</Script>");
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Purchase Order Generation</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DELETE\"),delete_window()' value=\"Delete\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_letter'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
				out.println("<tr class='tr_input'>");  
				out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
				out.println("</tr>");  
				out.println("</table>");  
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				
				//======== Added by thamali on 2012.02.15========================================
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>Client Code</td>");
				out.println("<td><input name=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" onchange=check_client() onblur=\"client_help()\"> ");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\"></td>");
				out.println("</tr>");
				out.println("<td id=tod>Client Name</td>");
				out.println("<td> <input name=\"TXT_CLIENT_NAME\" type=\"text\" maxlength=\"10\" class=\"txt_input\" disabled style=\"width:300px;\">");
				out.println("</td>");
				out.println("</tr>");	
				out.println("<tr class=tr_input>");
				out.println("<td id=fod>Application No</td>");
				out.println("<td><input name=\"APP_CODE\" type=\"text\" maxlength=\"10\" class=\"txt_input\"  onblur=\"app_help()\"> ");
				out.println("<input type=button name=ap_help value=... class=\"but_input\" onclick=\"app_help()\">");
				out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"get_purchase_orders('APPLICATION_NO','ASC')\"></td>");
				out.println("</tr>");
				
				
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_USER'  class=div_input>Contract Number</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTRACT_NUMBER'  maxlength='10' size='10'disabled>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				
				out.println("</tr>");		
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" colspan=4>");
				out.println("<div id=inv>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");  
				//=========================================================================================
				out.println("<table align='center' width='100%'border='0' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='validation_nromal_data'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");	
				
				
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				
				out.println("</html>");
			}
			
			
			else if(m_chksql.trim().equals("delete_page")){
				
				String m_sort_column   = "PURCHASE_ORDER_NO";	
				String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Credit - Purchase Order Generation \";"); 
				out.println("}else{");
				out.println("help_box.innerHTML=\"Credit - Purchase Order Generation - \"+m_val;"); 
				out.println("}");
				out.println("}");
				
				
				
				//out.println("function load_roll_value(m_val){"); 
				//out.println("help_box.innerHTML=\" Credit - Purchase Order Approval - \"+m_val;"); 
				//out.println("}"); 
				//out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit - Purchase Order  - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_af_cro_pro_purchase_order_main\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				
				out.println("}"); 
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				
				
				out.println("}"); 
				out.println("else{}");
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function load_data(m_pur_ord_no,m_app_no) {");
				//out.println(" if(m_app_sts=='IP') { ");
				out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order?CLOSE=Y&pur_ord_no=\"+m_pur_ord_no+\"&application_no=\"+m_app_no;"); 
				out.println("   window.open(m_url,'displayWindowap','left=50,top=60, width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1');"); 
				
				//added by nuwan de silva on 29-10-07-----------------------------------
				//out.println("   window.open(m_url,'displayWindowap','left=0,top=0, maximize=1,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				//out.println("window.location.href=m_url;"); 
				
				//out.println("  }");
				//out.println(" else { ");
				//out.println("   alert(m_app_no+'  is complete.');"); 
				//out.println("  }");
				out.println("}");
				
				//Added by Mahela on 26-07-2007
				out.println("function View_Letter(){");	                            
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order_View_Letter\";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1 ,fullscreen=1');");
				//out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Purchase_Order?application_no="+m_application_no+"';"); 
				out.println("}");
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				
				
				
				
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
				//out.println("alert(m_sort_col);");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Main_Screen?chksql=delete_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("</Script>");
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Purchase Order Generation</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DELETE\")' value=\"Delete\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name='btn_letter'  onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
				out.println("<tr class='tr_input'>");  
				out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
				out.println("</tr>");  
				out.println("</table>");  
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				// out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				out.println("<td  width='12%' align='left' style= cursor:hand; title='Click here to sort by - Purchase Order No  '    onclick=sort_data('PURCHASE_ORDER_NO') >Purchase Order No</td>");
				out.println("<td  width='12%' align='left' style= cursor:hand; title='Click here to sort by - Application No  '       onclick=sort_data('APPLICATION_NO') >Application No</td>");
				out.println("<td  width='26%' align='left' style= cursor:hand; title='Click here to sort by - Client Name  '      onclick=sort_data('FULL_NAME') >Client Name</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Vendor Code  '            onclick=sort_data('VENDER_CODE') >Vendor Code</td>");
				out.println("<td  width='15%' align='right' style= cursor:hand; title='Click here to sort by -  Tottal Net  ' onclick=sort_data('TOTAL_NET') >Total Net</td>");
				out.println("<td  width='15%' align='right' style= cursor:hand; title='Click here to sort by - Total Vat  '            onclick=sort_data('TOTAL_VAT') >Total Vat</td>");
				out.println("<td  width='10%' align='left' style= cursor:hand; title='Click here to sort by - Purchase Order Date  '      onclick=sort_data('PURCHASE_ORDER_DATE') >Purchase Order Date</td>");
				out.println("<td  width='7%'  align='center' >&nbsp;</td>");
				out.println("</tr>");
				
				int j = 0;   																																							
				
				rs = stmt.executeQuery (" SELECT "+
					" PURCHASE_ORDER_NO, "+ //1
					" A.APPLICATION_NO APPLICATION_NO, "+ //2
					" B.CLIENT_CODE CLIENT_CODE , "+ //3
					" C.FULL_NAME FULL_NAME , "+ //4
					" VENDER_CODE VENDER_CODE, "+ //5
					" TOTAL_NET TOTAL_NET, "+ //6 
					" TOTAL_VAT TOTAL_VAT, "+//7
					" TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY') PURCHASE_ORDER_DATE "+ //8
					" ,"+m_schema_name+".AF_CO_CHK_APP_DETAILS_ACT_DATE(A.APPLICATION_NO) "+//9 //add by waruna 2011-04-28 to restict saving of finacial year finished data
					
					"  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
					"       "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B, "+
					"       "+m_schema_name+".AF_CO_MAS_CLIENT C "+
					
					" WHERE  A.APPLICATION_NO=B.APPLICATION_NO AND "+
					"        B.CLIENT_CODE=C.CLIENT_CODE AND "+
					"        A.ACTIVE_STATUS IN('VERIFY','ENT','ENT_N','ENT_D') AND "+ //modified by nuwan de silva on 13-11-07
					"        PURCHASE_ORDER_NO IN ( "+
					" SELECT "+
					" DISTINCT PURCHASE_ORDER_NO "+
					" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
					" WHERE   ACTIVE_STATUS='Y' AND PRO_INVOICE_NO IN "+
					" (SELECT "+
					" REF_NO "+
					" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
					" WHERE INT_BAL_SETTLE_AMOUNT=0 /*AND */ "+
					/*" 	SUS_REF_NO NOT IN ( "+
					" 	SELECT SUS_REF_NO  "+ //modified by nuwan de silva 18-07-07
					" 	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
					" 	)  "+*/
					" )) "+
					
					" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				
				
				
				
				while(rs.next()){
					
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='12%' align='left' style= cursor:hand;cursor-color:blue onclick=show_purchase_order_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1) +"</u></td>");
					out.println("<td width='12%' align='left' style= cursor:hand; onClick=\"show_application_detail_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2) +"</u></td>");
					out.println("<td width='26%' align='left' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><u>"+rs.getString(4) +"</u></td>");
					out.println("<td width='10%' align='left' style= cursor:hand; onClick=\"show_vendor_drill('"+rs.getString(5)+"')\" ><u>"+rs.getString(5) +"</u></td>");
					out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6)) +"</td>");
					out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(7)) +"</td>");
					out.println("<td width='10%' align='left'>"+rs.getString(8) +"</td>");
					out.println("<td width='7%'  align='center'><input class='mainbut1'  style=width:75; font-size: 20px; type='button' name=\"BUTTON_PO\" value=\"Delete PO\"  "+rs.getString(9)+"  onclick=load_data('"+rs.getString(1)+"','"+rs.getString(2)+"')></td>");
					out.println("</tr>");
					j=j+1;
				}
				// 
				
				out.println("<tr class=tr_input>");
				// out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</html>");
			}
			
			//thamali 2012.02.15
			else if(m_chksql.equals("m_prime_chk_load_data"))
			{
				String m_sort_column = req.getParameter("sort_column").trim();
				String m_order_by_type = req.getParameter("order_by_type").trim();
				String m_client      = req.getParameter("client"); 
				String m_app      = req.getParameter("app"); 
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				// out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td colspan=14 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2 align='center'>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
				out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Facility No  '       onclick=sort_data('FINANCE_NO') >Agreement No</td>");
				out.println("<td  width='8%'  style= cursor:hand; title='Click here to sort by - Entered Date  '      onclick=sort_data('ENT_DATE') >Entered Date</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Period  '            onclick=sort_data('PERIOD') >Period(Days)</td>");
				out.println("<td  width='8%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Marketing Officer</td>");
				out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Status  '            onclick=sort_data('APP_STS') >Status</td>");
				out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
				out.println("<td  width='7%'  >&nbsp;</td>");
				out.println("</tr>");
				
				
				int j = 0;   
				
				if (m_app.equals("")){
					rs = stmt.executeQuery (" "+
					//out.println(" "+
						" SELECT "+
						" A.APPLICATION_NO,"+ //1
						" NVL(A.FACILITY_NO,'-'), "+ //2
						"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
						" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
						"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
						"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+ //7
						" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
						" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
						//"	NVL( (SELECT SUM(C.TOTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C WHERE C.APPLICATION_NO=A.APPLICATION_NO),0) PROFORMA_TOTAL, "+ //
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
						" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+ //12
						" NVL(CURRENCY_CODE,'-'), "+ //13
						" NVL(A.CLIENT_CODE,'-'), "+ //14
						" NVL(A.FINANCE_NO,'-'), "+ //15 -- ADDED BY NUWAN DE SILVA ON 29-02-2008
						" "+m_schema_name+".AF_CO_GET_STIPULATED_COUNT(FINANCE_NO) "+  //ADDED BY NS ON 21-04-2011
						"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						//" WHERE APPLICATION_STATUS NOT IN ('CANCEL') "+
						" WHERE  A.CLIENT_CODE LIKE '%"+m_client+"%' AND APPLICATION_STATUS IN ('VERIFYL') "+
						" AND "+m_schema_name+".AF_CO_PUR_ORDER_GEN_COUNT(A.APPLICATION_NO) > 0 "+ //Added by Sandun on 15-06-2009
						" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
					
					
				}
				else{
					rs = stmt.executeQuery (" SELECT "+
						" A.APPLICATION_NO,"+ //1
						" NVL(A.FACILITY_NO,'-'), "+ //2
						"	TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+ //3
						" TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') PERIOD, "+ //4
						"	NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') 	MK_NAME, "+ //5
						"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //6
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('AS',A.APPLICATION_NO),0) ASSET_COUNT,  "+ //7
						" DECODE( NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) ,0,'N','Y') PRICING_STS, "+ //8
						" NVL( (SELECT COUNT(B.APP_NO) FROM "+m_schema_name+".AF_MK_PRO_PRICING B WHERE B.APP_NO=A.APPLICATION_NO),0) PRICING_COUNT, "+ //9
						//"	NVL( (SELECT SUM(C.TOTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C WHERE C.APPLICATION_NO=A.APPLICATION_NO),0) PROFORMA_TOTAL, "+ //
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('PI',A.APPLICATION_NO),0) PROFORMA_COUNT, "+ //10
						" NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL',A.APPLICATION_NO),0) VALUATION_COUNT, "+ //11
						" DECODE(APPLICATION_STATUS,'ENT_CON','OK','IP') APP_STS, "+ //12
						" NVL(CURRENCY_CODE,'-'), "+ //13
						" NVL(A.CLIENT_CODE,'-'), "+ //14
						" NVL(A.FINANCE_NO,'-'), "+ //15 -- ADDED BY NUWAN DE SILVA ON 29-02-2008
						" "+m_schema_name+".AF_CO_GET_STIPULATED_COUNT(FINANCE_NO) "+  //ADDED BY NS ON 21-04-2011
						"	FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						//" WHERE APPLICATION_STATUS NOT IN ('CANCEL') "+
						" WHERE  A.CLIENT_CODE = '"+m_client+"' AND A.APPLICATION_NO = '"+m_app+"' AND APPLICATION_STATUS IN ('VERIFYL') "+
						" AND "+m_schema_name+".AF_CO_PUR_ORDER_GEN_COUNT(A.APPLICATION_NO) > 0 "+ //Added by Sandun on 15-06-2009
						" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
					
				}
				
				
				
				int m_stipulated_count=0;
				while(rs.next()){
					
					m_stipulated_count=rs.getInt(16);
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					
					out.println("<td width='12%' align='center' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1) +"</u></td>");
					//out.println("<td width='10%' align='center'>"+rs.getString(2) +"</td>");
					out.println("<td width='10%' align='center'>"+rs.getString(15) +"</td>");
					out.println("<td width='8%' align='center'>"+rs.getString(3) +"</td>");
					out.println("<td width='5%' align='center'>"+rs.getString(4) +"</td>");
					out.println("<td width='8%' align='left'>"+rs.getString(5) +"</td>");
					out.println("<td width='15%' style= cursor:hand; onClick=\"show_client('"+rs.getString(14)+"')\" ><u>"+rs.getString(6) +"</u></td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(7) +"    </td>");
					out.println("<td width='5%'  align='center'>"+rs.getString(8)+"  </td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(9) +"    </td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(10) +"   </td>");
					out.println("<td width='5%'  align='center'>"+rs.getInt(11) +"   </td>");
					out.println("<td width='5%'  align='center'>"+rs.getString(12) +"</td>");
					out.println("<td width='5%'  align='center'>"+rs.getString(13) +"</td>");
					
					if (m_stipulated_count > 0) {				  
						out.println("<td width='7%'  align='center'><input class='mainbut1'  style=width:75; font-size: 20px; type='button' name=\"BUTTON_PO\" value=\"Generate PO\" onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"')></td>");
					}
					else {
						out.println("<td width='7%'  align='center'><input class='mainbut1'  style=width:75; font-size: 20px; type='button' name=\"BUTTON_PO\" value=\"Generate PO\" onclick=load_data('"+rs.getString(1)+"','"+rs.getString(12)+"') disabled ></td>");
					}				  
					
					out.println("</tr>");
					j=j+1;
				}
				// 
				
				out.println("<tr class=tr_input>");
				// out.println("<td colspan=11 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
				out.println("<td align=right colspan=14><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("</tr></table>");
				
				
			}		
			//=========================================================================================================================			
			/*else {
				out.println("Undefined");
			}
			*/
			//out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
