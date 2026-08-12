/*
This File Created By Ns on 25/12/2012 Receipt Collection Module
*/

/*Import Libraries*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CMS_Receipt_Enter extends javax.servlet.http.HttpServlet {
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)
	{
		/*Declareing Variable*/
		/*Database Connection Varible*/
		Connection  conn     = null;
		
		/*Statemets Declaration Goes Here*/
		Statement   stmt     = null;
		Statement   stmt1    = null;
		Statement   stmt2    = null;
		Statement   stmt12   = null;
		Statement   stmt_act = null;
		
		/*Result Set Declaration Goes Here*/
		ResultSet   rs		= null;
		ResultSet	rs1		= null;
		ResultSet	rs2		= null;
		ResultSet	rs12	= null;
		ResultSet	rs_act	= null;
		
		
		String 		m_chksql = null;
		ServletOutputStream out = null;
		java.text.NumberFormat nf,nf1;
		CallableStatement callstmt1 =null;
		/*End section Declareing Variable*/
		
		try {
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn 								= 	con_method.met_user_validate(req); 
			String m_html_client_url   			= 	con_method.html_client_url;
			String m_schema_name 				= 	con_method.schema_name;
			String m_servlet_client_url			= 	con_method.servlet_client_url;
			String m_client_name				= 	con_method.client_name;
			String m_client_t3_port				= 	con_method.client_t3_port;
			String m_username 					= 	con_method.username;
			String header_name    				= 	con_method.header_name;
			String m_rep_cur					= 	""; 
			String m_fschema_name				= 	con_method.client_name.trim();
			String m_class_url					= 	con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			//String m_html_client_url_snet       =   con_method.html_client_url_snet;
			String m_class_url_2                = con_method.servlet_client_url.trim()+":"+"8087/lakdllive/servlet"; // added by udara 22-06-2022
			
			out = res.getOutputStream();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			stmt 		= conn.createStatement ();
			stmt1		= conn.createStatement ();
			stmt2		= conn.createStatement ();
			stmt12		= conn.createStatement ();
			stmt_act	= conn.createStatement ();
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			
			else if(m_chksql.trim().equals("main_page")){
				
				//out.println("<!DOCTYPE html>"); 
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Asset Financing System</TITLE>"); 
				out.println("</HEAD>"); 
				
				
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/CMS_Receipt.css' TYPE=\"text/css\">"); 
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/CMS_Receipt_Enter.js'></SCRIPT>"); //bootstrap
				//=======================================================================================================================================================================================================================================================================================================
				//=======================================================================================================================================================================================================================================================================================================
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/test.js'></SCRIPT>"); //Testing purpose only comment this when you transfer 
				//=======================================================================================================================================================================================================================================================================================================		
				//=======================================================================================================================================================================================================================================================================================================
				
				
				
				
				out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/jquery/fancybox/jquery-1.4.1.min.js'></script>");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.core.min.js' type=\"text/javascript\"></script>");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.datepicker.min.js' type=\"text/javascript\"></script>");
				out.println("       <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href='"+m_html_client_url+"/jquery/grid/themes/redmond/jquery-ui-1.7.1.custom.css' />"); 
				out.println("       <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href='"+m_html_client_url+"/jquery/grid/themes/ui.jqgrid.css\' />");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/js/i18n/grid.locale-en.js' type=\"text/javascript\"></script>");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/js/jquery.jqGrid.min.js' type=\"text/javascript\"></script>");
				out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/jquery/fancybox/jquery.fancybox-1.3.0.pack.js'></script>");
				out.println("       <link rel=\"stylesheet\" href='"+m_html_client_url+"/jquery/fancybox/jquery.fancybox-1.3.0.css'type='text/css' media=\"screen\">"); 
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.draggable.min.js' type=\"text/javascript\"></script>");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.resizable.min.js' type=\"text/javascript\"></script>");
				out.println("       <script src='"+m_html_client_url+"/jquery/grid/ui/minified/ui.dialog.min.js' type=\"text/javascript\"></script>");
				
				/*Java Script section Goes Here */
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println(""); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				//out.println("		clear_data();"); // commented by udara 17-07-2019
				out.println("		clear_data_2();"); // added by udara 17-07-2019
				out.println("		} else "); 
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
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		account_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"5\"){"); 
				out.println("		term_assign(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"7\"){"); 
				out.println("		account_number_assign(oBj);"); 
				out.println("		}");
				
				out.println("		if(IfCount==\"8\"){"); 
				out.println("		branch_assign(oBj);"); 
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
				out.println("	else{");
				//out.println("	clear_data();"); // commented by udara 17-07-2019
				out.println("	clear_data_2();"); // added by udara 24-07-2019
				out.println("	}");
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
				
				//client Help
				out.println("function client_help(){");
				out.println("document.Form1.cli_help.disabled=true;"); // added by udara 30-01-2019
				
				// added by udara 16-05-2019
				out.println(" var m_string = document.Form1.CLIENT_CODE.value; ");
				out.println(" if(m_string!=''){ ");
				//out.println("   m_string = m_string.replace(/\\s+$/,\"\"); ");
				out.println("   m_string = m_string.replace(/^\\s+|\\s+$/g, ''); ");
				out.println(" } ");
				// end by udara 16-05-2019
				
				//out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";"); // commented by udara 16-05-2019
				out.println("Crit=m_string+\"@\";"); // added by udara 16-05-2019
				
				out.println(" document.Form1.hid_help_type.value='1' ");
				//out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt','1');");
				out.println("HelpBox('1','10','0',Crit,'ClientSql_Receipt_client','1');");
				out.println("  			CCODE.style.color='black';"); 
				out.println("}");
				
				out.println("function client_assign(oBj){");
				
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[9]");
				out.println(" document.Form1.CLIENT_NAME.value =oBj.valout[3]");
				//out.println(" display_client_comment(oBj.valout[9]);"); 
				
				
				//out.println(" inv_details();"); /// INV
				
				out.println(" contranct_details();"); /// INV
				//out.println(" makeRequest2(oBj.valout[2]);");
				//out.println("set_address_pay_type();"); //added by nuwan de silva 01-08-07
				//out.println("document.Form1.CLIENT_CODE.disabled =true;"); //added by nuwan de silva
				//out.println("document.Form1.cli_help.disabled    =true;"); //added by nuwan de silva
				out.println("document.Form1.cli_help.disabled=false;"); // added by udara 30-01-2019
				out.println("}");
				
				//account Help
				out.println("function account_help(){");
				out.println("Crit=document.Form1.PAY_ACCOUNT.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("HelpBox('1','10','0',Crit,'AccountSql_receipt','3');");
				
				out.println("}");		
				
				out.println("function account_assign(oBj){");
				out.println(" document.Form1.PAY_ACCOUNT.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[3]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value =oBj.valout[6]+' - '+oBj.valout[4] "); 
				out.println("}");
				
				//Branch Help
				out.println("function branch_help(){");
				out.println("Crit=document.Form1.PAY_BRANCH.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='8' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_BANK_BRANCH_CODE_sql','8');");
				
				out.println("}");		
				
				out.println("function branch_assign(oBj){");
				out.println(" document.Form1.PAY_BRANCH.value =oBj.valout[2]");
				out.println(" document.Form1.PAY_BRANCH_NAME.value = oBj.valout[4] +' - '+ oBj.valout[3] ");
				
				out.println("}");
				
				//account Number
				out.println("function account_number_help(){");
				out.println("Crit=document.Form1.TXT_ACCOUNT_NO.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='7' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_ACCOUNT_NO_sql','7');");
				out.println("}");		
				
				out.println("function account_number_assign(oBj){");
				out.println(" document.Form1.TXT_ACCOUNT_NO.value =oBj.valout[2]");
				out.println(" document.Form1.BRANCH_NAME.value =oBj.valout[4]");
				out.println("      document.Form1.hid_TXT_BRANCH_CODE.value=oBj.valout[3];");
				out.println("      document.Form1.hid_TXT_ACC_REF_NO.value=oBj.valout[6];");
				out.println("}");
				
				/*
				out.println("function Change_Allo_Mode(){");
				out.println("val=document.getElementById('TXT_ALLOCATION_METHOD').value");
				out.println("document.Form1.AMOUNT.disabled=true;"); 
				out.println("if(val==\"FIFO_MANU\"){");
				//out.println("contract.innerHTML=\"\"; ");
				//out.println("fifo_aloc.innerHTML=\"\"; "); 
				//out.println("contract.innerHTML=\"\"; ");
				out.println("load_grid();");
				out.println("}else{");
				///out.println("contract.innerHTML=\"\"; ");
				//out.println("inv.innerHTML=\"\"; ");
				//out.println("get_auto_mode();");			
				out.println("load_grid();");
				out.println("}");
				
				out.println("}");
				*/
				
				
				
				
				///--- Added by DSP Chathuranga 2013-04-22 ---///
				
				out.println("function contract_details_assing_bk(data){ "); //backup on 2017-10-12
				out.println(" m_write_data = ");
				out.println(" '<fieldset>'+ ");
				out.println(" '<legend style=\"margin-bottom: 5px;\" ><strong>Contract Level Allocation:</strong></legend>'+ ");
				out.println(" '<div class=\"contract\" > '+ ");
				out.println(" '<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+ ");
				
				/*out.println(" 	'<tr  >'+");		
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"left\"><strong>Finance No</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"left\"><strong>Vehicle Reg. No</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"right\"><strong>Rental Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"right\"><strong>R.Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"right\"><strong>Insuarance Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"15%\"  align=\"right\"><strong>Ins. Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"10%\"  align=\"center\"><strong>Status</strong></TH>'+");
				out.println(" 	'</tr>';");
				*/
				
				out.println(" 	'<tr  >'+");		
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Finance No</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Status</strong></TH>'+");
				/*out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Rental Due Date</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Rental Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>No.Of.Rentals</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Status</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Trn.History</strong></TH>'+");*/
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Rental Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>R.Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Insuarance Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Ins. Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"5%\"  align=\"center\"><strong>Allocate</strong></TH>'+");
				out.println(" 	'</tr>';");
				
				
				
				//out.println("   var number_of_rows = data.length / 6;");
				out.println("   var number_of_rows = data.length / 10;");
				out.println("       document.Form1.hid_cntract_cnt2.value = number_of_rows; "); 
				out.println("   var j = 0;");
				out.println("   var  m_total_od = 0.00;");
				out.println("   var  m_total_other = 0.00;");
				out.println("   for (var i = 0; i < number_of_rows; i++) {");
				
				
				
				out.println(" m_write_data = m_write_data+ ");
				
				/*out.println(" 	'<tr>'+");		
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[j]+'  <input type=hidden name=\"hid_finance_no_'+i+'\" value=\"'+data_vec[j]+'\">   </TD>'+");   
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[j+1]+' <input type=hidden name=\"hid_vr_no_'+i+'\" value=\"'+data_vec[j+1]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"> '+data_vec[j+5]+' <input type=hidden name=\"Hid_ODI_Rental_'+i+'\" value=\"'+data_vec[j+5]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><input type = \"text\" style=\"{text-align:right}\" name = \"TXT_RENTAL_ODI_' + i + '\"  class=\"txt_input_number\"  ONCHANGE=\"change_rd_amount('+i+');\" value=\"0.00\" /> <input type=hidden name=\"HID_TXT_RENTAL_ODI_'+i+'\" value=\"0.00\" >  </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\">'+data_vec[j+4]+' <input type=hidden name=\"Hid_Other_'+i+'\" value=\"'+data_vec[j+4]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><input type = \"text\" style=\"{text-align:right}\" name = \"TXT_OTHER_' + i + '\" class=\"txt_input_number\" ONCHANGE=\"change_othere_amount('+i+');\" value=\"0.00\" />  <input type=hidden name=\"HID_TXT_OTHER_'+i+'\" value=\"0.00\" >  </TD>'+");
				out.println(" 		'<TD WIDTH=\"10%\"  align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" ></TD>'+");
				
				out.println(" 	'</tr>';");
				
				out.println(" 	m_total_od = Number(parseFloat(m_total_od)+parseFloat(unformat_noobject(data_vec[j+5])));");
				out.println(" 	m_total_other = Number(parseFloat(m_total_other)+parseFloat(unformat_noobject(data_vec[j+4])));");
				*/
				
				
				/******out.println(" 	'<tr>'+");		
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[j]+'  <input type=hidden name=\"hid_finance_no_'+i+'\" value=\"'+data_vec[j]+'\">   </TD>'+");   
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[j+1]+' <input type=hidden name=\"hid_vr_no_'+i+'\" value=\"'+data_vec[j+1]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"> '+data_vec[j+5]+' <input type=hidden name=\"Hid_ODI_Rental_'+i+'\" value=\"'+data_vec[j+5]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><input type = \"text\" style=\"{text-align:right}\" name = \"TXT_RENTAL_ODI_' + i + '\"  class=\"txt_input_number\"  ONCHANGE=\"change_rd_amount('+i+');\"  disabled value=\"0.00\" / > <input type=hidden name=\"HID_TXT_RENTAL_ODI_'+i+'\" value=\"0.00\"  >  </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\">'+data_vec[j+4]+' <input type=hidden name=\"Hid_Other_'+i+'\" value=\"'+data_vec[j+4]+'\"> </TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><input type = \"text\" style=\"{text-align:right}\" name = \"TXT_OTHER_' + i + '\" class=\"txt_input_number\" ONCHANGE=\"change_othere_amount('+i+');\" disabled value=\"0.00\" />  <input type=hidden name=\"HID_TXT_OTHER_'+i+'\" value=\"0.00\"   >  </TD>'+");
				out.println(" 		'<TD WIDTH=\"10%\"  align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" ></TD>'+");
				out.println(" 	'</tr>';");
				*******/
				
				
				out.println(" 	'<tr>'+");		
				out.println(" 		'<TD   align=\"left\">'+data_vec[j]+'  <input type=hidden name=\"hid_finance_no_'+i+'\" value=\"'+data_vec[j]+'\">   </TD>'+");   
				out.println(" 		'<TD   align=\"left\">'+data_vec[j+9]+'<input type=hidden name=\"hid_finance_status_'+i+'\" value=\"'+data_vec[j+9]+'\">  </TD>'+");   
				/*out.println(" 		'<TD   align=\"left\">'+data_vec[j+6]+'  </TD>'+");   
				out.println(" 		'<TD   align=\"left\">'+data_vec[j+7]+'  </TD>'+");   
				out.println(" 		'<TD   align=\"left\">'+data_vec[j+8]+'  </TD>'+");   
				out.println(" 		'<TD   align=\"left\">'+data_vec[j+9]+'  </TD>'+");   
				//out.println(" 		'<TD   align=\"left\" ><INPUT TYPE=\"Button\" name=\"Button_History_'+i+'\"  onclick=\"tranhistory('+i+');\"  value=\"Trn. History\" ></TD>'+");   
				out.println(" 		'<TD   align=\"center\" style= cursor:hand; onclick=\"tranhistory('+i+');\" ><u>Trn. History</u></TD>'+");   */
				out.println(" 		'<TD   align=\"right\"> '+data_vec[j+5]+' <input type=hidden name=\"Hid_ODI_Rental_'+i+'\" value=\"'+data_vec[j+5]+'\"> </TD>'+");
				out.println(" 		'<TD   align=\"right\"><input type = \"text\"  style=\"{text-align:right}\" name = \"TXT_RENTAL_ODI_' + i + '\"  class=\"txt_input_number\"  ONCHANGE=\"change_rd_amount('+i+');\"  disabled value=\"0.00\" / > <input type=hidden name=\"HID_TXT_RENTAL_ODI_'+i+'\" value=\"0.00\"  >  </TD>'+");
				out.println(" 		'<TD   align=\"right\">'+data_vec[j+4]+' <input type=hidden name=\"Hid_Other_'+i+'\" value=\"'+data_vec[j+4]+'\"> </TD>'+");
				//out.println(" 		'<TD   align=\"right\"><input type = \"text\" style=\"{text-align:right; width =\"100%\"}\" name = \"TXT_OTHER_' + i + '\" class=\"txt_input_number\" ONCHANGE=\"change_othere_amount('+i+');\" disabled value=\"0.00\" />  <input type=hidden name=\"HID_TXT_OTHER_'+i+'\" value=\"0.00\"   >  </TD>'+");
				//out.println(" 		'<TD   align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" ></TD>'+");
				
				out.println(" 		'<TD   align=\"right\"><input type = \"text\" style=\"{text-align:right; width =\"100%\"}\" name = \"TXT_OTHER_' + i + '\" class=\"txt_input_number\" ONCHANGE=\"change_othere_amount('+i+');\" disabled value=\"0.00\" />  <input type=hidden name=\"HID_TXT_OTHER_'+i+'\" value=\"0.00\"   >  </TD>';");
				out.println(" 		if(data_vec[j+6]=='Y'){");
				out.println(" 			m_write_data = m_write_data+'<TD   align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" ></TD>';");
				out.println(" 		}else{");
				out.println(" 			m_write_data = m_write_data+'<TD   align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" disabled ></TD>';"); //2017-09-04
				out.println(" 		}");
				
				out.println(" 	'</tr>';");
				
				
				
				out.println(" 	m_total_od = Number(parseFloat(m_total_od)+parseFloat(unformat_noobject(data_vec[j+5])));");
				out.println(" 	m_total_other = Number(parseFloat(m_total_other)+parseFloat(unformat_noobject(data_vec[j+4])));");
				
				
				
				//out.println("   	j = j + 6;");
				out.println("   	j = j + 10;");
				out.println("   }");
				
				
				out.println(" m_write_data = m_write_data+ ");
				out.println(" 	'<tr class=\"alt\" >'+");		
				out.println(" 		'<TD  WIDTH=\"15%\"  align=\"left\"></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>Total</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>'+format_noobject(m_total_od)+'</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong><div id=\"div_m_total_od\"></div></strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>'+format_noobject(m_total_other)+'</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong><div id=\"div_m_total_other\"></div></strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"10%\"  align=\"center\"></TD>'+");
				out.println(" 	'</tr> '+");
				out.println(" '</table> </div> ' + ");
				
				//this section comment by ns on 12/07/2017 
				/*out.println(" '<table align=\"right\" width=\"300px;\" class=\"table\" border=\"0\">'+ "); //remove_inv
				out.println(" 	'<tr  >'+");		
				out.println(" 		'<TD   align=\"right\"> <input type=\"button\" name=\"show_inv\" value=\" Allocate to Invoices \" class=\"but_input\" style=\"width: 140px;\"  onclick=\"validate_back_dating_request();\" >  </TD>'+");
				out.println(" 		'<TD   align=\"right\"> <input type=\"button\" name=\"remove_inv\" value=\" Remove Invoices \" class=\"but_input\" style=\"width: 130px;\"  onclick=\"remv_inv_det();\" > </TD>'+");
				out.println(" 	'</tr>'+");
				out.println(" '</table> </fieldset>'; ");
				*/
				
				out.println(" 	'</fieldset>';");
				out.println(" div_contract_details.innerHTML=m_write_data; ");
				
				out.println("}");
				
				
				//====================================================================================================================================
				out.println("function contract_details_assing(data){ "); //backup on 2017-10-12
				out.println(" m_write_data = ");
				out.println(" '<fieldset>'+ ");
				out.println(" '<legend style=\"margin-bottom: 5px;\" ><strong>Contract Level Allocation:</strong></legend>'+ ");
				out.println(" '<div class=\"contract\" > '+ ");
				out.println(" '<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+ ");
				
				out.println(" 	'<tr  >'+");		
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Finance No</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"left\"><strong>Status</strong></TH>'+");				
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Rental Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>R.Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Insuarance Balance</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"12%\"  align=\"right\"><strong>Ins. Allocation Amount</strong></TH>'+");
				out.println(" 		'<TH WIDTH=\"5%\"  align=\"center\"><strong>Allocate</strong></TH>'+");
				out.println(" 	'</tr>';");
				
				
				
				
				//out.println("   var number_of_rows = data.length / 10;"); // commented by udara 10-10-2018
				//out.println("   var number_of_rows = data.length / 11;"); // added by udara 10-10-2018
				//out.println("   var number_of_rows = data.length / 12;"); // added by udara 14-12-2018
				out.println("   var number_of_rows = data.length / 13;"); // added by udara 07-01-2019
				out.println("       document.Form1.hid_cntract_cnt2.value = number_of_rows; "); 
				out.println("   var j = 0;");
				out.println("   var  m_total_od = 0.00;");
				out.println("   var  m_total_other = 0.00;");
				out.println("   for (var i = 0; i < number_of_rows; i++) {");
				
				
				
				out.println(" m_write_data = m_write_data+ ");
				
				out.println(" 	'<tr>'+");		
				out.println(" 		'<TD   align=\"left\">'+data_vec[j]+'  <input type=hidden name=\"hid_finance_no_'+i+'\" value=\"'+data_vec[j]+'\">   </TD>'+");//Finance No   
				//out.println(" 		'<TD   align=\"left\">'+data_vec[j+9]+'<input type=hidden name=\"hid_finance_status_'+i+'\" value=\"'+data_vec[j+9]+'\">  </TD>'+");//Status   	// commented by udara 10-10-2018
				//out.println(" 		'<TD   align=\"left\">'+data_vec[j+9]+'<input type=hidden name=\"hid_finance_status_'+i+'\" value=\"'+data_vec[j+9]+'\"> <input type=hidden name=\"hid_tran_type_'+i+'\" value=\"'+data_vec[j+10]+'\"> </TD>'+"); // commented by udara 14-12-2018 //Status // added hid_tran_type_ by udara 10-10-2018
				out.println(" 		'<TD   align=\"left\">'+data_vec[j+9]+'<input type=hidden name=\"hid_finance_status_'+i+'\" value=\"'+data_vec[j+9]+'\"> <input type=hidden name=\"hid_tran_type_'+i+'\" value=\"'+data_vec[j+10]+'\"> <input type=hidden name=\"hid_due_amount_'+i+'\" value=\"'+data_vec[j+11]+'\"> <input type=hidden name=\"hid_loan_excess_'+i+'\" value=\"'+data_vec[j+12]+'\"></TD>'+"); // added by udara 14-12-2018
				out.println(" 		'<TD   align=\"right\"> '+data_vec[j+5]+' <input type=hidden name=\"Hid_ODI_Rental_'+i+'\" value=\"'+data_vec[j+5]+'\"> </TD>'+");//Rental Balance
				out.println(" 		'<TD   align=\"right\"><input type = \"text\"  style=\"{text-align:right}\" name = \"TXT_RENTAL_ODI_' + i + '\"  class=\"txt_input_number\"  ONCHANGE=\"change_rental_amount('+i+');\" disabled  value=\"0.00\" / > <input type=hidden name=\"HID_TXT_RENTAL_ODI_'+i+'\" value=\"0.00\"  >  </TD>'+");//R.Allocation Amount  //remove ONCHANGE=\"change_rd_amount('+i+');\"  2017-10-12 
				out.println(" 		'<TD   align=\"right\">'+data_vec[j+4]+' <input type=hidden name=\"Hid_Other_'+i+'\" value=\"'+data_vec[j+4]+'\"> </TD>'+");//Insuarance Balance
				out.println(" 		'<TD   align=\"right\"><input type = \"text\" style=\"{text-align:right; width =\"100%\"}\" name = \"TXT_OTHER_' + i + '\" class=\"txt_input_number\" ONCHANGE=\"change_insurance_amount('+i+');\" disabled value=\"0.00\" />  <input type=hidden name=\"HID_TXT_OTHER_'+i+'\" value=\"0.00\"   >  </TD>';");//Ins. Allocation Amount //remove ONCHANGE=\"change_othere_amount('+i+');\" disabled  2017-10-12
				out.println(" 		if(data_vec[j+6]=='Y'){");
				out.println(" 			m_write_data = m_write_data+'<TD   align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" ></TD>';");//Allocate
				out.println(" 		}else{");
				out.println(" 			m_write_data = m_write_data+'<TD   align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_status_'+i+'\"  onclick=\"check_status('+i+');\"  value=\"NO\" disabled ></TD>';"); //2017-09-04 //Allocate
				out.println(" 		}");				
				out.println(" 	'</tr>';");
				
				out.println(" 	m_total_od = Number(parseFloat(m_total_od)+parseFloat(unformat_noobject(data_vec[j+5])));");
				out.println(" 	m_total_other = Number(parseFloat(m_total_other)+parseFloat(unformat_noobject(data_vec[j+4])));");
				
				//out.println("   	j = j + 10;"); // commented by udara 10-10-2018
				//out.println("   	j = j + 11;"); // added by udara 10-10-2018
				//out.println("   	j = j + 12;"); // added by udara 14-12-2018
				out.println("   	j = j + 13;"); // added by udara 07-01-2019
				out.println("   }");
				
				
				out.println(" m_write_data = m_write_data+ ");
				out.println(" 	'<tr class=\"alt\" >'+");		
				out.println(" 		'<TD  WIDTH=\"15%\"  align=\"left\"></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>Total</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>'+format_noobject(m_total_od)+'</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong><div id=\"div_m_total_od\"></div></strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong>'+format_noobject(m_total_other)+'</strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"15%\"  align=\"right\"><strong><div id=\"div_m_total_other\"></div></strong></TD>'+");
				out.println(" 		'<TD WIDTH=\"10%\"  align=\"center\"></TD>'+");
				out.println(" 	'</tr> '+");
				out.println(" '</table> </div> ' + ");				
				
				out.println(" 	'</fieldset>';");
				out.println(" div_contract_details.innerHTML=m_write_data; ");
				
				out.println(" document.Form1.Allocate.disabled=false; "); // added by udara 30-01-2019
				
				out.println("}");
				
				
				//====================================================================================================================================
				out.println("$(document).ready(function() { ");
				out.println(" 		check_box_enable('','NO');");
				out.println("});");
				
				out.println("function alloc_contract(obj_alloc)");
				out.println("{");
				out.println("    if(obj_alloc.value==\"YES\"){ ");
				out.println("	 	document.Form1.Allocate.disabled=false;   ");
				out.println("	 	check_box_enable('','YES');   ");
				out.println("    }else{ ");	
				out.println("	 	document.Form1.Allocate.disabled=true;   ");
				out.println("       div_contract_details.innerHTML =''; ");
				out.println("       div_allocated_amount_dis.innerHTML =''; ");
				out.println("       div_allocated_amount.innerHTML =''; ");
				out.println("       div_balance_amount_dis.innerHTML =''; ");
				out.println("       div_balance_amount.innerHTML =''; ");
				out.println("       div_invoice_details.innerHTML =''; ");
				out.println("       document.Form1.hid_cntract_cnt2.value ='0'; "); //inesh
				out.println("       document.Form1.hid_rental_allo_amount.value ='0.00'; "); //inesh
				out.println("       document.Form1.hid_ins_allo_amount.value ='0.00'; "); //inesh
				out.println("       document.Form1.hid_allo_fin_no.value =''; "); //inesh
				out.println("	 	check_box_enable('','NO');   ");
				out.println(" 	 } ");
				out.println("}");
				
				
				
				out.println("function check_status(id) { ");
				
				// added by udara 10-10-2018
				
				out.println("   var val_rental = 0; "); 
				out.println("   var val_insurance = 0; ");
				out.println("   var val_loan_arr_total = 0; ");
				out.println("   var val_loan_flag = 'NO'; ");
				
				out.println("   for (var i = 0; i < document.Form1.hid_cntract_cnt2.value; i++) {");
				
				//out.println("        if(document.Form1.elements['hid_tran_type_'+i].value=='LOANS'){"); // commented by udara 03-12-2024
				out.println("        if(document.Form1.elements['hid_tran_type_'+i].value=='LOANS' || document.Form1.elements['hid_tran_type_'+i].value=='HADAGASMA'){"); // added by udara 03-12-2024
				
				out.println("            val_rental     = parseFloat(unformat_number(document.Form1.elements['Hid_ODI_Rental_'+i]));");
				out.println("            val_insurance  = parseFloat(unformat_number(document.Form1.elements['Hid_Other_'+i]));");
				//out.println("            val_loan_arr_total = val_rental + val_insurance; "); // commented by udara 14-12-2018
				out.println("            val_loan_arr_total = parseFloat(unformat_number(document.Form1.elements['hid_due_amount_'+i])); "); // added by udara 14-12-2018
				
				out.println("            loan_excess_amount = parseFloat(unformat_number(document.Form1.elements['hid_loan_excess_'+i])); "); // added by udara 07-01-2019
				//out.println("            alert(loan_excess_amount);   ");
				//out.println("            if((val_loan_arr_total > 0) && (document.Form1.elements['hid_finance_no_'+id].value!=document.Form1.elements['hid_finance_no_'+i].value)){    "); // commented by udara 07-01-2019
				out.println("            if(((val_loan_arr_total - loan_excess_amount) > 0) && (document.Form1.elements['hid_finance_no_'+id].value!=document.Form1.elements['hid_finance_no_'+i].value)){    "); // added by udara 07-01-2019
				//out.println("            if(val_loan_arr_total > 0){    ");
				out.println("                  val_loan_flag = 'YES';   ");
				out.println("            }    ");
				
				out.println("        }    ");
				
				out.println("   }");
				//out.println("   alert(val_loan_flag); ");
				//out.println("   if(val_loan_flag=='YES' && document.Form1.elements['hid_tran_type_'+id].value!='LOANS'){ "); // commented by udara 03-12-2024
				out.println("   if(val_loan_flag=='YES' && (document.Form1.elements['hid_tran_type_'+id].value!='LOANS' || document.Form1.elements['hid_tran_type_'+id].value!='HADAGASMA') ){ ");  // added by udara 03-12-2024
				out.println("     alert('There is a loan contract with arrears. Therefore cannot allocate to this.'); ");
				out.println("     document.Form1.elements['Text_status_'+id].checked=false;");
				out.println("     return false;");
				out.println("   }");
				// end by udara 10-10-2018
				
				
				out.println("   for (var i = 0; i < document.Form1.hid_cntract_cnt2.value; i++) {");
				out.println("   		document.Form1.elements['TXT_OTHER_'+i].value      = \"0.00\"; ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+i].value = \"0.00\"; ");
				out.println("       	document.Form1.elements['Text_status_'+i].checked=false;");		
				out.println("   		document.Form1.elements['TXT_OTHER_'+i].disabled      = true; ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+i].disabled = true; ");
				out.println(" 		if(id != i){");				 
				out.println("       	document.Form1.elements['Text_status_'+i].value=\"NO\";");
				out.println(" 		} ");
				out.println("}");
				
				out.println("       document.Form1.hid_rental_allo_amount.value ='0.00'; "); //inesh
				out.println("       document.Form1.hid_ins_allo_amount.value ='0.00'; "); //inesh
				out.println("       document.Form1.hid_allo_fin_no.value =''; "); //inesh
				
				out.println("   	div_allocated_amount.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"0.00\" DISABLED > ' ; ");
				out.println("   	div_balance_amount.innerHTML =  '<input name=\"tdiv_balance_amount\" id=\"tdiv_balance_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.AMOUNT.value)+'\" DISABLED > ' ; ");
				
				out.println("   	var  m_amount_total = 0.00;");
				out.println("   	var  m_bal_out_rental = 0.00;");
				out.println("   	var  m_bal_out_insurance = 0.00;");
				
				//out.println("		    alert(\"Status\"+document.Form1.elements['Text_status_'+id].value); ");
				out.println(" 	if (document.Form1.elements['Text_status_'+id].value ==\"YES\") { ");
				
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].value      = \"0.00\"; ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].value = \"0.00\"; ");
				out.println("       	document.Form1.elements['Text_status_'+id].checked=false;");
				out.println("       	document.Form1.elements['Text_status_'+id].value=\"NO\";");
				
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].disabled      = true; ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].disabled = true; ");
				
				out.println(" 			check_box_enable('uutick','')");
				out.println("  } else {");
				
				/*
				out.println("   if(document.Form1.elements['TXT_RENTAL_ODI_'+id].value==\"0.00\" && document.Form1.elements['TXT_OTHER_'+id].value==\"0.00\" ){ "); 
				out.println("       	document.Form1.elements['Text_status_'+id].checked=false;");
				out.println("		    alert(\"Please enter Allocation Amount.\"); ");
				out.println(" 	} ");
				*/
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].disabled      = false; ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].disabled = false; ");
				
				out.println(" m_bal_out_rental    =  parseFloat(unformat_number(document.Form1.elements['Hid_ODI_Rental_'+id])); ");
				out.println(" m_bal_out_insurance =  parseFloat(unformat_number(document.Form1.elements['Hid_Other_'+id])); ");
				out.println(" m_amount_total      =  parseFloat(unformat_number(document.Form1.AMOUNT)); ");
				
				
				out.println("if (m_amount_total  > 0 && m_bal_out_insurance > 0 ) { ");
				
				out.println(" 		if ( m_amount_total >= m_bal_out_insurance ) { ");
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].value      = format_noobject(m_bal_out_insurance); ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].value = format_noobject(m_amount_total - m_bal_out_insurance); ");				
				out.println("       	document.Form1.hid_ins_allo_amount.value = m_bal_out_insurance; "); //inesh
				out.println("       	document.Form1.hid_rental_allo_amount.value =  m_amount_total - m_bal_out_insurance; "); //inesh
				out.println("       	document.Form1.hid_allo_fin_no.value = document.Form1.elements['hid_finance_no_'+id].value ; "); //inesh
				out.println(" 		}else { ");
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].value      = format_noobject(m_amount_total); ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].value = \"0.00\"; ");
				out.println("       	document.Form1.hid_ins_allo_amount.value = m_amount_total; "); //inesh
				out.println("       	document.Form1.hid_rental_allo_amount.value =  '0.00'; "); //inesh
				out.println("       	document.Form1.hid_allo_fin_no.value = document.Form1.elements['hid_finance_no_'+id].value ; "); //inesh
				out.println("       }");
				
				out.println(" } else if (m_amount_total  > 0 && m_bal_out_insurance == \"0.00\" ) { ");
				out.println("   		document.Form1.elements['TXT_RENTAL_ODI_'+id].value = format_noobject(m_amount_total); ");
				out.println("   		document.Form1.elements['TXT_OTHER_'+id].value      = \"0.00\"; ");	
				out.println("       	document.Form1.hid_rental_allo_amount.value =  m_amount_total; "); //inesh
				out.println("       	document.Form1.hid_ins_allo_amount.value = \"0.00\"; "); //inesh
				out.println("       	document.Form1.hid_allo_fin_no.value = document.Form1.elements['hid_finance_no_'+id].value ; "); //inesh
				out.println("}");
				
				out.println("   	div_allocated_amount.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(m_amount_total)+'\" DISABLED > ' ; ");
				out.println("   	div_balance_amount.innerHTML =  '<input name=\"tdiv_balance_amount\" id=\"tdiv_balance_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"0.00\" DISABLED > ' ; ");
				
				
				
				out.println("       	document.Form1.elements['Text_status_'+id].value=\"YES\";");
				out.println("       	document.Form1.elements['Text_status_'+id].checked=true;");
				out.println(" 		check_box_enable(document.Form1.elements['hid_finance_status_'+id].value,'')");
				out.println("}"); // 
				
				//out.println("change_rd_amount(id)");
				//out.println("change_othere_amount(id)");
				
				//out.println(" alert(val_loan_arr_total);  ");
				
				out.println("}"); // function
				
				
				
				
				
				out.println(" function change_rd_amount(id) { ");
				//out.println("   alert(format_number(document.Form1.elements['Text_amount_'+id]));");
				out.println("    g_hid_amount = parseFloat(unformat_number(document.Form1.elements['HID_TXT_RENTAL_ODI_'+id])); ");
				out.println("    if(format_number2(document.Form1.elements['TXT_RENTAL_ODI_'+id]) ){ "); 
				out.println("    	g_amount = Math.round((parseFloat(unformat_number(document.Form1.AMOUNT)) - parseFloat(unformat_number(document.Form1.hid_amount2)) ) *100)/100; ");
				out.println("    	tot_g_amount = parseFloat(unformat_number(document.Form1.hid_tot_odi_rental)) ; ");
				out.println("   	g_text_amount = parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+id])); ");
				
				
				out.println("   if(g_text_amount > 0 ){ ");
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("           tot_g_amount=Math.round((tot_g_amount-g_hid_amount)*100)/100; ");
				out.println("           if(g_amount < g_text_amount ){ ");
				out.println("    			alert('You can not enter more than '+g_amount); ");
				out.println("    			g_text_amount='0.00'; ");
				out.println("       	} else{");
				out.println("           	g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("               tot_g_amount=Math.round((tot_g_amount+g_text_amount)*100)/100; ");
				out.println("       	}");
				out.println("       } else{");
				out.println("           if(g_amount < g_text_amount ){ ");
				out.println("    			alert('You can not enter more than '+g_amount); ");
				out.println("    			g_text_amount='0.00'; ");
				out.println("       	} else{");
				out.println("           	g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("               tot_g_amount=Math.round((tot_g_amount+g_text_amount)*100)/100; ");
				out.println("       	}");
				out.println("       }");
				out.println("    } else { ");
				
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("           tot_g_amount=Math.round((tot_g_amount-g_hid_amount)*100)/100; ");
				
				out.println("       } else {} ");
				out.println("		    alert(\"Please enter positive Amount.\"); ");
				out.println("       	g_text_amount=0.00; ");
				out.println("    } ");
				
				out.println("       div_allocated_amount.innerHTML =''; ");
				out.println("       div_balance_amount.innerHTML =''; ");
				out.println("       div_m_total_od.innerHTML =''; ");
				
				out.println("  		document.Form1.hid_amount2.value= Number(parseFloat(unformat_number(document.Form1.AMOUNT)) - g_amount ); ");
				out.println("       document.Form1.elements['HID_TXT_RENTAL_ODI_'+id].value = format_noobject(g_text_amount) ; ");
				out.println("       document.Form1.elements['TXT_RENTAL_ODI_'+id].value =  format_noobject(g_text_amount); "); 
				out.println("       document.Form1.hid_tot_odi_rental.value =  format_noobject(tot_g_amount); "); 
				out.println("   	div_m_total_od.innerHTML = document.Form1.hid_tot_odi_rental.value ; ");
				out.println("   	div_allocated_amount.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.hid_amount2.value)+'\" DISABLED > ' ; ");
				out.println("   	div_balance_amount.innerHTML =  '<input name=\"tdiv_balance_amount\" id=\"tdiv_balance_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(g_amount)+'\" DISABLED > ' ; ");
				//out.println("    	alert('assdfffffffff'+document.Form1.hid_tot_odi_rental.value); ");
				//out.println(" 			document.getElementById('div_invoice_allocation_amount1_'+id).innerHTML= document.Form1.elements['TXT_RENTAL_ODI_'+id].value; ");
				
				out.println("    } ");
				out.println(" } ");
				
				
				out.println(" function change_othere_amount(id) { ");
				//out.println("   alert(format_number(document.Form1.elements['Text_amount_'+id]));");
				out.println("    g_hid_amount = parseFloat(unformat_number(document.Form1.elements['HID_TXT_OTHER_'+id])); ");
				out.println("    if(format_number2(document.Form1.elements['TXT_OTHER_'+id]) ){ "); 
				out.println("    	g_amount = Math.round((parseFloat(unformat_number(document.Form1.AMOUNT)) - parseFloat(unformat_number(document.Form1.hid_amount2)) ) *100)/100; ");
				out.println("    	tot_g_amount = parseFloat(unformat_number(document.Form1.hid_tot_other)) ; ");
				out.println("   	g_text_amount = parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+id])); ");
				
				
				out.println("   if(g_text_amount > 0 ){ ");
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("           tot_g_amount=Math.round((tot_g_amount-g_hid_amount)*100)/100; ");
				out.println("           if(g_amount < g_text_amount ){ ");
				out.println("       alert('bbbb'+ format_noobject(g_text_amount)); "); //kan
				out.println("    			alert('You can not enter more than '+g_amount); ");
				out.println("    			g_text_amount='0.00'; ");
				out.println("       	} else{");
				out.println("           	g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("               tot_g_amount=Math.round((tot_g_amount+g_text_amount)*100)/100; ");
				out.println("       	}");
				out.println("       } else{");
				out.println("           if(g_amount < g_text_amount ){ ");
				out.println("       alert('hid_amt'+g_hid_amount+'g_amnt'+g_amount+'aaaa'+ format_noobject(g_text_amount)); "); //kan  
				out.println("       alert(document.Form1.hid_amount2.value); ");
				out.println("    			alert('You can not enter more than '+g_amount); ");
				out.println("    			g_text_amount='0.00'; ");
				out.println("       	} else{");
				out.println("           	g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("               tot_g_amount=Math.round((tot_g_amount+g_text_amount)*100)/100; ");
				out.println("       	}");
				out.println("       }");
				out.println("    } else { ");
				
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("           tot_g_amount=Math.round((tot_g_amount-g_hid_amount)*100)/100; ");
				
				out.println("       } else {} ");
				out.println("       	g_text_amount=0.00; ");
				out.println("		    alert(\"Please enter positive Amount.\"); ");
				out.println("    } ");
				
				out.println("       div_allocated_amount.innerHTML =''; ");
				out.println("       div_balance_amount.innerHTML =''; ");
				out.println("       div_m_total_other.innerHTML =''; ");
				
				out.println("  		document.Form1.hid_amount2.value= Number(parseFloat(unformat_number(document.Form1.AMOUNT)) - g_amount ); ");
				out.println("       document.Form1.elements['HID_TXT_OTHER_'+id].value = format_noobject(g_text_amount) ; ");
				//out.println("       alert( format_noobject(g_text_amount)); "); //kan 27-06-2016
				out.println("       document.Form1.elements['TXT_OTHER_'+id].value =  format_noobject(g_text_amount); "); 
				out.println("       document.Form1.hid_tot_other.value =  format_noobject(tot_g_amount); "); 
				out.println("   	div_m_total_other.innerHTML = document.Form1.hid_tot_other.value ; ");
				out.println("   	div_allocated_amount.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(document.Form1.hid_amount2.value)+'\" DISABLED > ' ; ");
				out.println("   	div_balance_amount.innerHTML =  '<input name=\"tdiv_balance_amount\" id=\"tdiv_balance_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+format_noobject(g_amount)+'\" DISABLED > ' ; ");
				//out.println("    	alert('assdfffffffff'+document.Form1.hid_tot_other.value); ");
				//out.println(" 			document.getElementById('div_invoice_allocation_amount2_'+id).innerHTML= document.Form1.elements['TXT_OTHER_'+id].value; ");
				
				out.println("    } ");
				out.println(" } ");
				
				
				out.println(" function Change_Allo_Mode(){");
				out.println(" 	if(document.Form1.AMOUNT.value !=\"0.00\" && document.Form1.CLIENT_CODE.value !=\"\" ){");
				out.println(" 		val=document.getElementById('TXT_ALLOCATION_METHOD').value");
				out.println(" 		document.Form1.AMOUNT.disabled=true;"); 
				
				out.println(" 		if(val==\"YES\"){");
				out.println("           document.Form1.Allocate.disabled=true; "); // added by udara 30-01-2019
				out.println(" 			div_allocated_amount_dis.innerHTML='<strong>Allocated Amount</strong>'; ");
				out.println("   		div_allocated_amount.innerHTML = '<input name=\"tdiv_allocated_amount\" id=\"tdiv_allocated_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"0.00\" DISABLED > ' ; ");
				//out.println("     	div_allocated_amount.innerHTML = '0.00'; ");
				out.println(" 			div_balance_amount_dis.innerHTML='<strong>Balance Amount</strong>'; ");
				//out.println("       	div_balance_amount.innerHTML = document.Form1.AMOUNT.value; ");
				out.println("   		div_balance_amount.innerHTML =  '<input name=\"tdiv_balance_amount\" id=\"tdiv_balance_amount\" type=\"text\" maxlength=\"20\" class=\"txt_input_number\"  STYLE=\"{text-align:right;}\" value=\"'+document.Form1.AMOUNT.value+'\" DISABLED > ' ; ");
				out.println(" 			m_client=document.getElementById('CLIENT_CODE').value;");
				out.println(" 			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CMS_Sql_Data?chksql=LOAD_CONTRACT_DETAILS&client=\"+m_client;");				
				//out.println(" 		    window.open(m_url);");		
				out.println(" 			document.Form1.hid_help_status.value='CONTRACT_DETAILS';");
				out.println("   		load_interface(m_url,'XML');");
				out.println(" 		}else {");
				out.println("       	div_contract_details.innerHTML =''; ");
				out.println("       	div_allocated_amount_dis.innerHTML =''; ");
				out.println("       	div_allocated_amount.innerHTML =''; ");
				out.println("       	div_balance_amount_dis.innerHTML =''; ");
				out.println("       	div_balance_amount.innerHTML =''; ");
				out.println("       	div_invoice_details.innerHTML =''; ");
				out.println(" 		    document.Form1.hid_amount2.value=\"0.00\";"); 
				out.println("   	}");
				out.println("   } else { ");
				out.println(" 		if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
				out.println("  			CCODE.style.color='red';"); 
				out.println("   	}"); 
				out.println(" 		if(document.Form1.AMOUNT.value==\"0.00\"){  "); 
				out.println("  			AMOU.style.color='red';");
				out.println(" 		}");
				out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("   }");
				out.println(" }");
				
				
				
				
				
				
				
				out.println(" function befor_submit(){ "); 
				out.println(" 	if(validate_data()){ ");
				out.println(" 		if(allocation_amount_check()){ ");
				out.println(" 			befor_save();");
				out.println(" 		}");
				out.println(" 	} else {");
				out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println(" 	}");
				out.println(" }");
				
				out.println("function befor_save(){ "); 
				out.println(" 	assign_hidden_values();");
				out.println("   m_status = document.Form1.hid_option.value ");
				out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
				out.println("   if(m_status == \"EDIT\"){ ");
				out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
				out.println("   }"); 
				out.println("   else if(m_status == \"DELETE\"){ ");
				//out.println("   m_tot=0;"); //added by nuwan de silva 02-08-07
				//out.println("   m_amount_entered=0;");  //added by nuwan de silva 02-08-07
				//out.println("   m_balance=0;");  //added by nuwan de silva 02-08-07
				out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
				out.println("   }"); 
				//out.println("		if(validate_data()){"); 
				out.println("		document.Form1.b_submit.disabled=true"); 
				
				out.println("		if(confirm(m_save_msg)){ "); 
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		document.Form1.elements[i].disabled=false;");
				out.println("		document.Form1.b_submit.disabled=true"); // added by udara 10-01-2019
				out.println("		}");
				//disable save button added by nuwan de silva
				out.println("		document.Form1.b_submit.disabled=true"); 
				//out.println("		document.Form1.b_submit1.disabled=true");
				
				//out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Save_Receipt';"); // commented by udara 22-06-2020 ///--- Added by DSP Chathuranga 2013-04-23 ---///	
		        
				out.println("       document.Form1.hid_user_name.value =  '"+m_username+"'; ");  // added by udara 22-06-2020 
				//out.println("       alert(document.Form1.hid_user_name); ");
				
				out.println("		document.Form1.action='"+m_class_url_2+"/"+m_fschema_name+"AF_CMS_Save_Receipt';"); // added by udara 22-06-2020 to SMS direct to tomcat 9
				
				out.println("		document.Form1.submit();	"); //xxxxxxxxxxxxxxxxxxxxxxx
				out.println("		}else{");
				out.println("			document.Form1.b_submit.disabled=false"); 
				out.println("		}");								
				//out.println("		}"); 
				//out.println("		else { "); 
				//out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				//out.println("		}");
				
				
				out.println("} "); 
				
				
				
				out.println("function assign_hidden_values(){");
				out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
				out.println("document.Form1.hid_TXT_ACCOUNT_NO.value=document.Form1.TXT_ACCOUNT_NO.value"); 
				out.println("}"); 
				out.println("if(document.Form1.SETT_MODE.value==\"CASH\" ){");
				out.println("document.Form1.hid_TEN_AMOUNT.value=document.Form1.TEN_AMOUNT.value"); 
				out.println("document.Form1.hid_RET_AMOUNT.value=document.Form1.RET_AMOUNT.value"); 
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println(" function validate_data(){"); 
				out.println(" 	m_sub=true;"); 
				out.println(" 	if(document.Form1.hid_option.value==\"NEW\"){"); 
				out.println(" 		if(document.Form1.CLIENT_CODE.value==\"\"){  "); 
				out.println("  			CCODE.style.color='red';");
				out.println("  			m_sub = false;"); 
				out.println("   	}"); 
				out.println(" 		if(document.Form1.AMOUNT.value==\"0.00\"){  "); 
				out.println("  			AMOU.style.color='red';");
				out.println("  			m_sub = false;"); 
				out.println(" 		}");
				out.println(" 		if(document.Form1.SETT_MODE.value==\"CHEQUE\"){  "); 
				out.println(" 			if(document.Form1.CHEQUE_DATE_DD.value==\"\" || document.Form1.CHEQUE_DATE_MM.value==\"\" || document.Form1.CHEQUE_DATE_YY.value==\"\" ){  "); 
				out.println("  				CDATE.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 			if(document.Form1.CHEQUE_NO.value==\"\"){  "); 
				out.println("  				CRNO.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 			if(document.Form1.PAY_BRANCH.value==\"\"){  "); 
				out.println("  				PBRANCH.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 		}else if(document.Form1.SETT_MODE.value==\"STD_ORD\"  ){ ");  ///DIR_DEP
				out.println(" 			if(document.Form1.CHEQUE_DATE_DD.value==\"\" || document.Form1.CHEQUE_DATE_MM.value==\"\" || document.Form1.CHEQUE_DATE_YY.value==\"\" ){  "); 
				out.println("  				CDATE.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 			if(document.Form1.CHEQUE_NO.value==\"\"){  "); 
				out.println("  				CRNO.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 			if(document.Form1.PAY_BRANCH.value==\"\"){  "); 
				out.println("  				PBRANCH.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 
				out.println(" 			if(document.Form1.TXT_ACCOUNT_NO.value==\"\"){  "); 
				out.println("  				ACC.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 				
				out.println(" 		}");
				//[Added milinda for remove mandotory fields direct deopsite level]
				out.println(" 		else if( document.Form1.SETT_MODE.value==\"DIR_DEP\" ){ ");  ///DIR_DEP
				
				out.println(" 			if(document.Form1.TXT_ACCOUNT_NO.value==\"\"){  "); 
				out.println("  				ACC.style.color='red';");
				out.println("  				m_sub = false;"); 
				out.println("   		}"); 				
				out.println(" 		}");
				out.println(" 		if(!validate_allocation_det()){  "); 				
				out.println("  			m_sub = false;");
				out.println("   	}");
				out.println(" 	}"); 
				
				out.println(" 	return m_sub; "); 
				out.println(" }"); 
				
				out.println("function validate_allocation_det() {");//inesh
				out.println("  		if(document.Form1.TXT_ALLOCATION_METHOD.value =='YES'){");
				out.println("   		for (var i = 0; i < document.Form1.hid_cntract_cnt2.value; i++) {"); 
				out.println("       		if(document.Form1.elements['Text_status_'+i].checked==true){");
				//out.println(" 					if((parseFloat(unformat_number(document.Form1.AMOUNT)) == parseFloat(document.Form1.elements['TXT_OTHER_'+i].value)) || (parseFloat(unformat_number(document.Form1.AMOUNT)) == parseFloat(document.Form1.elements['TXT_RENTAL_ODI_'+i].value)) ){");				
				out.println(" 						if( ");				
				out.println(" 							( parseFloat(unformat_number(document.Form1.AMOUNT)) == parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+i])) ) || ");				
				out.println(" 							( parseFloat(unformat_number(document.Form1.AMOUNT)) == parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+i])) ) || ");				
				out.println(" 							( parseFloat(unformat_number(document.Form1.AMOUNT)) == ( parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+i])) + parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+i])) ) ) ");				
				out.println(" 						  ){");				
				out.println(" 						return true; "); 					
				out.println(" 					}");
				out.println(" 				}");
				out.println(" 			}");
				//out.println(" 			alert('Please allocate to contract');"); // commented by udara 28-07-2017
				out.println(" 			alert('Please allocate this receipt to the relevant contract');"); // added by udara 28-07-2017
				out.println(" 			return false; "); 
				out.println(" 		}"); 
				out.println(" 	return true; "); 
				out.println(" }"); 
				
				// added by udara 24-07-2019
				out.println("function clear_data_2() {");
				out.println("  document.Form1.cli_help.disabled=false;");
				out.println("}"); 
				// end by udara 24-07-2019
				
				out.println("function clear_data() {");
				
				out.println(" if(document.Form1.hid_help_type.value == '1') { ");
				out.println(" document.Form1.CLIENT_NAME.value=''; "); 
				out.println(" document.Form1.CLIENT_CODE.value=''; ");
				out.println(" div_outstanding_details.innerHTML = ''; ");
				out.println(" div_contract_details.innerHTML = ''; ");  
				out.println(" div_invoice_details.innerHTML = ''; "); 
				out.println(" div_outstanding_details.innerHTML = ''; "); 
				out.println(" div_contract_details_client.innerHTML = ''; "); 
				out.println("document.Form1.cli_help.disabled=false;"); // added by udara 30-01-2019
				///DSP  div_contract_details div_m_total_od
				out.println(" } else if(document.Form1.hid_help_type.value == '2'){ ");
				out.println(" document.Form1.RECEIPT_NO.value=''; }"); 
				out.println(" else if(document.Form1.hid_help_type.value == '3'){ ");
				out.println(" document.Form1.PAY_ACCOUNT.value='';  "); 
				out.println(" document.Form1.PAY_BRANCH.value='';  ");
				out.println(" document.Form1.PAY_BRANCH_NAME.value=''; ");
				out.println(" document.Form1.hid_amount2.value=\"0.00\";"); 
				out.println(" } "); 
				out.println(" ");
				out.println(" else if(document.Form1.hid_help_type.value == '8'){ ");
				out.println(" document.Form1.PAY_BRANCH.value=''; ");
				out.println(" document.Form1.PAY_BRANCH_NAME.value=''; ");
				out.println(" } ");  
				out.println(" ");
				out.println("}");
				
				
				out.println("function inv_details() {");
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\"){");
				//out.println("alert('inv_details123');");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Settlement?chksql=get_inv_details&Client_Code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'12','client_detail');");
				out.println("  }");
				out.println("  }");
				
				out.println("function view_finance_det(fin_no) {");
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\"){");
				//out.println("alert('inv_details123');");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Settlement?chksql=get_inv_details_fin_wise&Client_Code=\"+document.Form1.CLIENT_CODE.value+\"&fin_no=\"+fin_no;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'12','client_detail');");
				out.println("  }");
				out.println("  }");	
				out.println("function contranct_details() {");
				out.println("  if(document.Form1.CLIENT_CODE.value!=\"\"){");
				//out.println("alert('inv_details123');");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Settlement?chksql=get_contract_details_client&Client_Code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");			
				//out.println(" alert('contract details'); ");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'16','contract_details');");
				out.println("  }");
				out.println("  }");
				
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url); ");
				out.println("	}");
				
				//Check Values Using AJAX
				out.println("function makeRequest(url,opt,type) {");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
				out.println("    http_request = new XMLHttpRequest();");
				out.println("    if (http_request.overrideMimeType) {");
				out.println("        http_request.overrideMimeType('text/xml');");
				out.println("    }");
				out.println("} else if (window.ActiveXObject) { ");// IE
				out.println("    try {");
				out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
				out.println("    } catch (e) {");
				out.println("        try {");
				out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
				out.println("        } catch (e) {}");
				out.println("    }");
				out.println("}");
				out.println("if (!http_request) {");
				out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
				out.println("    return false;");
				out.println("}");
				out.println(" if(opt=='12' || opt=='13'|| opt=='14'||opt=='15'|| opt==16){");
				
				out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt,type); };");
				out.println("  http_request.open('GET',url, true);");
				//out.println("  window.open(url);");
				out.println("  http_request.send(null);");
				out.println(" }");
				
				out.println("}");
				
				out.println("       var m_write_data99 = ''; ");
				
				out.println("function alertGetContents(http_request,opt,type) {");
				//out.println(" alert('test--'+opt);");
				
				
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				
				out.println("       if(opt==\"12\"){");
				//out.println("         alert(http_request.responseText);");
				out.println("       	div_outstanding_details.innerHTML=http_request.responseText; ");
				out.println("    	} else if(opt==\"16\") {");				
				out.println("       	div_contract_details_client.innerHTML=http_request.responseText; ");
				out.println("           update_allocation_button(); ");
				out.println("    	} else if(opt==\"13\") {");
				
				out.println("       var divTag = document.createElement('div'); ");
				out.println("    	divTag.id = 'div_invoice_details_hid_'+type;  ");
				out.println("    	divTag.setAttribute('WIDTH', '100%');  ");
				out.println("    	divTag.innerHTML = http_request.responseText; ");
				out.println("       document.getElementById('div_invoice_details').appendChild(divTag); ");
				out.println("       show_inv_data(type); ");
				
				out.println("    	} else if(opt==\"14\") {");
				
				out.println("       var m_result =  http_request.responseText; ");
				out.println("       validate_back_dating(m_result); ");
				out.println("    	} else if(opt==\"15\") {");
				
				out.println("       var m_result =  http_request.responseText; ");
				out.println("       validate_no_invoices(m_result); ");
				out.println("    	}");
				out.println(" 	}");
				out.println(" }");
				out.println("}");
				
				out.println("function update_allocation_button() {");				
				out.println("   var m_val =0;");
				out.println("   try{");
				out.println(" 		m_val = document.Form1.Hid_no_of_contract.value; ");
				out.println("   }catch(err){");
				out.println("   	m_val =0;");
				out.println("   }"); 
				out.println("   if( parseInt(m_val) == 0){");
				out.println("    	document.Form1.TXT_ALLOCATION_METHOD.selectedIndex=0;");
				out.println("       alloc_contract(document.Form1.TXT_ALLOCATION_METHOD); ");
				out.println("   }else{");
				out.println("    	document.Form1.TXT_ALLOCATION_METHOD.selectedIndex=1;");
				out.println("       alloc_contract(document.Form1.TXT_ALLOCATION_METHOD); ");
				out.println("   }");
				out.println("}");
				
				
				out.println("function remv_inv_det() {");
				//out.println(" alert('test--'+opt);");
				out.println(" div_invoice_details.innerHTML=''; ");
				
				out.println("}");
				
				/*************************Added by Jithendra 19-10-2016***************************************/
				
				out.println(" function validate_back_dating_request() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Settlement?chksql=GET_IF_BACKDATED&m_date=\"+document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'14','Invoice_detail');");
				out.println("}");
				
				
				out.println(" function validate_back_dating(result) {");
				out.println(" if(result=='YES'){ "); 
				out.println(" alert('Invoice Allocation Will Occur at Day End'); "); 
				out.println("}else if(result=='NO'){");
				out.println(" validate_no_invoices_request(); ");
				out.println(" }");
				out.println("}");
				
				
				out.println(" function validate_no_invoices_request() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Settlement?chksql=GET_NO_INVOICES&m_client_code=\"+document.Form1.CLIENT_CODE.value;");
				//out.println("   window.open(m_url);");
				out.println("   makeRequest(m_url,'15','Invoice_count');");
				out.println("}");
				
				
				out.println(" function validate_no_invoices(result) {");
				out.println(" if(parseInt(result)==0){ "); 
				out.println(" alert('No Invoices For Allocation'); "); 
				out.println("}else if(parseInt(result)>0){");
				out.println(" show_inv_det(); ");
				out.println(" }");
				out.println("}");
				
				/********************************END****************************************/
				
				out.println(" function show_inv_det() {");
				out.println(" div_invoice_details.innerHTML= ''; ");
				out.println(" if(document.Form1.tdiv_balance_amount.value == \"0.00\"){ "); //tdiv_balance_amount  TXT_RENTAL_ODI_0
				out.println(" m_write_html =  '<fieldset>'+ ");
				out.println(" '<legend style=\"margin-bottom: 5px;\" ><strong>Invoice Level Allocation:</strong></legend>  '+ ");
				out.println(" 	'<table width=\"100%\" align=\"left\" >'; ");
				
				out.println("    contract_count=document.Form1.hid_cntract_cnt2.value; "); 
				out.println("    for (var z = 0; z < contract_count; z++) { ");
				out.println(" 		if(document.Form1.elements['Text_status_'+z].checked==true && (document.Form1.elements['TXT_RENTAL_ODI_'+z].value != \"0.00\" || document.Form1.elements['TXT_OTHER_'+z].value != \"0.00\") ){ ");
				out.println("       	m_write_html = m_write_html + ");
				//out.println("         '<br/>'+");
				out.println(" 	        '<tr> <td>' + ");
				out.println(" 				'<table width=\"100%\" align=\"left\" >'+");
				out.println(" 					'<tr> <td>'+");	
				out.println(" 						'<div class=\"datagrid\" >'+");
				out.println(" 						'<table     >'+ "); //class="table table-striped" 
				out.println(" 							'<thead>'+");	
				out.println(" 							'<tr  >'+");		
				out.println(" 								'<TH width=\"12%px\"  align=\"left\"   ><strong>Finance No</strong></TH>'+");
				out.println(" 								'<TH width=\"17%\" align=\"left\"   ><strong>Vehicle Reg. No</strong></TH>'+");
				out.println(" 								'<TH width=\"15%\" align=\"right\"  ><strong>Rental + ODI Balance</strong></TH>'+");
				out.println(" 								'<TH width=\"14%\"  align=\"right\" ><strong>R+O Allocated Amount</strong></TH>'+");
				out.println(" 								'<TH width=\"12%\"  align=\"right\"  ><strong>Other Balance</strong></TH>'+");
				out.println(" 								'<TH width=\"13%\"  align=\"right\"  ><strong>Ot. Allocated Amount</strong></TH>'+");
				out.println(" 								'<TH width=\"16% align=\"right\"  ><strong>Total Allocated Amount</strong></TH>'+");
				out.println(" 							'</tr>'+");
				out.println(" 							'</thead>'+");
				out.println(" 							'<tbody>'+");
				out.println(" 							'<tr class=\"alt\" >'+");		
				out.println(" 								'<TD align=\"left\">  <div id=\"div_invoice_finance_no_'+z+'\"> </div> </TD>'+");  
				out.println(" 								'<TD align=\"left\">  <div id=\"div_invoice_vr_no_'+z+'\"> </div> </TD>'+");
				out.println(" 								'<TD align=\"right\"> <div id=\"div_invoice_rd_amount_'+z+'\"> </div> </TD>'+");
				out.println(" 								'<TD align=\"right\"> <div id=\"div_invoice_allocation_amount1_'+z+'\"> </div> <input type=\"hidden\" name=\"Hid_invoice_rd_'+z+'\" /> </TD>'+");
				out.println(" 								'<TD align=\"right\"> <div id=\"div_invoice_other_amount_'+z+'\"> </div> </TD>'+");
				out.println(" 								'<TD align=\"right\"> <div id=\"div_invoice_allocation_amount2_'+z+'\"> </div> <input type=\"hidden\" name=\"Hid_invoice_other_'+z+'\" />  </TD>'+");
				out.println(" 								'<TD align=\"right\"> <div id=\"div_invoice_allocation_total_amount_'+z+'\">  </div>  </TD>'+");
				out.println(" 							'</tr>'+");
				out.println(" 							'</tbody>'+");
				out.println(" 						'</table>'+ ");
				out.println(" 						'</div>'+");
				out.println(" 					'</td> </tr>'+");
				out.println(" 					'<tr> <td style=\"padding-left:8%;\" >'+");	
				//out.println("         '<br/>'+");
				//out.println("         '<br/>'+");
				//out.println("         '<br/>'+");
				//out.println("       '</div>'+");
				out.println("       				'<blockquote><table id=\"table_invoice_details_hid_'+z+'\"    ></table> </blockquote> '+");  //  align=\"right\" width=\"95%\"  class=\"table\" border=\"1\"  height: auto  style=\"height: auto; position: absolute;\" width=\"100%\"
				out.println("       				'<input type=\"hidden\" name=\"HID_INVOICE_COUNT_'+z+'\" >'+");
				out.println("       				'<input type=\"hidden\" name=\"HID_ALLO_NO_'+z+'\" value=\"\" >'+");
				out.println(" 					'</td> </tr>'+");
				out.println(" 				'</table>'+ ");
				//out.println("   	        '<hr/>'+");
				out.println(" 			'</td> </tr>';");
				
				out.println(" 		document.Form1.hid_invoice.value=contract_count; ");//hid_invoice
				
				out.println("    } else { ");
				out.println("    	document.Form1.elements['Text_status_'+z].checked=false;");
				out.println("    } ");
				
				out.println(" 	} ");
				out.println("         m_write_html = m_write_html + ' </table> </fieldset> '; ");
				out.println(" 		div_invoice_details.innerHTML =  m_write_html; ");
				out.println("     show_inv_data(); ");
				out.println(" } else { ");
				out.println("   alert('Please Allocate Full Amount.'); ");
				out.println("  } ");
				out.println("} ");
				
				out.println(" function show_inv_data() {");
				out.println("    for (var z = 0; z < contract_count; z++) { ");
				out.println(" 		if(document.Form1.elements['Text_status_'+z].checked==true ){ ");
				
				out.println(" 			document.getElementById('div_invoice_finance_no_'+z).innerHTML= document.Form1.elements['hid_finance_no_'+z].value; ");           
				out.println(" 			document.getElementById('div_invoice_vr_no_'+z).innerHTML= document.Form1.elements['hid_vr_no_'+z].value; ");
				out.println(" 			document.getElementById('div_invoice_rd_amount_'+z).innerHTML= document.Form1.elements['Hid_ODI_Rental_'+z].value; ");
				out.println(" 			document.getElementById('div_invoice_allocation_amount1_'+z).innerHTML= document.Form1.elements['TXT_RENTAL_ODI_'+z].value; ");
				out.println(" 			document.Form1.elements['Hid_invoice_rd_'+z].value = document.Form1.elements['TXT_RENTAL_ODI_'+z].value; ");
				out.println(" 			document.getElementById('div_invoice_other_amount_'+z).innerHTML= document.Form1.elements['Hid_Other_'+z].value; ");
				out.println(" 			document.getElementById('div_invoice_allocation_amount2_'+z).innerHTML= document.Form1.elements['TXT_OTHER_'+z].value; ");
				out.println(" 			document.Form1.elements['Hid_invoice_other_'+z].value = document.Form1.elements['TXT_OTHER_'+z].value; ");
				out.println(" 			document.getElementById('div_invoice_allocation_total_amount_'+z).innerHTML= format_noobject(Math.round((parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+z]))+parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+z])))*100)/100); ");
				
				//out.println(" 			document.getElementById('div_invoice_allocation_balance_amount1_'+z).innerHTML= format_noobject(document.Form1.elements['Hid_invoice_rd_'+z].value); ");
				//out.println(" 			document.getElementById('div_invoice_allocation_balance_amount2_'+z).innerHTML= format_noobject(document.Form1.elements['Hid_invoice_other_'+z].value); ");
				//out.println(" 			document.getElementById('div_invoice_allocation_balance_total_amount_'+z).innerHTML= format_noobject(Math.round((parseFloat(unformat_number(document.Form1.elements['Hid_invoice_rd_'+z]))+parseFloat(unformat_number(document.Form1.elements['Hid_invoice_other_'+z])))*100)/100); ");
				
				out.println(" 			retel_amount = unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+z]); ");
				out.println(" 			other_amount = unformat_number(document.Form1.elements['TXT_OTHER_'+z]); ");
				//out.println(" 			document.getElementById('Hid_invoice_total_'+z).value= Math.round((parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+z]))+parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+z])))*100)/100 ;  ");
				//out.println(" 			document.getElementById('div_invoice_allocation_total_amount_'+z).innerHTML= format_noobject(document.Form1.elements['Hid_invoice_total_'+z].value); ");
				
				out.println(" 			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CMS_Sql_Data?chksql=LOAD_INVOICE_ALLOCATION&finance_no=\"+document.Form1.elements['hid_finance_no_'+z].value+\"&id=\"+z+\"&rentel=\"+retel_amount+\"&other=\"+other_amount;");
				//out.println("window.open(m_url);");
				out.println(" 			jQuery('#table_invoice_details_hid_'+z).jqGrid(");
				out.println(" 			{");
				out.println(" 				url:m_url,"); 
				out.println(" 				datatype: \"xml\","); 
				out.println("  				colNames:['ID','Invoice No','Invoice Type','Invoice Date',  'Value Date', 'Invoice Amount','Balance Amount','Allocation Amount','Allocate',''],");
				out.println(" 				colModel:[ ");
				out.println(" 				{name:'HID_ID',index:'HID_ID', width:10, hidden:true, align:\"center\" ,formatter:hid_invoice_id_fmatter },");  //,formatter:hid_invoice_id_fmatter
				out.println(" 				{name:'INVOICE_NO',index:'INVOICE_NO', width:100, align:\"center\" ,formatter:invoice_no },");  //,formatter:team_color_fmatter
				out.println(" 				{name:'INVOICE_TYPE',index:'INVOICE_TYPE', width:120, align:\"left\" ,formatter:hid_invoice_type_fmatter },");  //,formatter:hid_batch_facility_no_fmatter
				out.println(" 				{name:'INVOICE_DATE',index:'INVOICE_DATE', width:120, align:\"left\" ,formatter:invoice_date },"); //,formatter:hid_client_no_fmatter
				out.println(" 				{name:'VALUE_DATE',index:'VALUE_DATE', width:120, align:\"left\"},"); 
				out.println(" 				{name:'INVOICE_AMOUNT',index:'INVOICE_AMOUNT', width:120, align:\"right\" ,formatter:invoice_amount },"); 	
				out.println(" 				{name:'BALANCE_AMOUNT',index:'BALANCE_AMOUNT', width:120, align:\"right\" },");   //,summaryType:'sum'
				out.println(" 				{name:'ALLOCATION_INVOICE',index:'ALLOCATION_INVOICE', width:120, align:\"right\",formatter:txt_allocation_contract_fmatter},");  
				out.println(" 				{name:'APPROVE',index:'APPROVE', width:94, align:\"center\",formatter:checkbox_app_fmatter},");
				out.println(" 				{name:'HID_AMOUNT',index:'HID_AMOUNT', hidden:true, width:10, align:\"center\",formatter:set_sum_amount},");
				out.println(" 			],");
				out.println(" 				mtype: \"GET\","); 
				out.println(" 				loadonce: 1,"); 
				out.println(" 				rownumbers:true,");
				out.println(" 				rowNum:-1,"); 
				out.println(" 				gridview: true,");  
				out.println(" 				height: 'auto',");   ///align=\"right\" width=\"95%\"
				//out.println(" 				grouping: true,");
				//out.println(" 				groupingView : { ");
				//out.println(" 				groupField : ['INVOICE_TYPE'],");
				//out.println(" 				groupColumnShow : [false], ");
				//out.println(" 				groupText : ['<b>{0} - <i>{1} Item(s)</i></b>'],"); 
				//out.println(" 				groupCollapse : false, ");
				//out.println(" 				groupOrder: ['asc'],");	
				//out.println(" 				groupSummary : [true], "); 
				//out.println(" 				groupDataSorted : true");
				//out.println(" 			},");
				//out.println(" 				caption:\"Invoice Allocation  \", ");
				out.println("               loadComplete : function(){");
				//out.println(" 			        alert('AAAAAAA');"); 
				//out.println(" 			        alert(jQuery('#table_invoice_details_hid_1').jqGrid('getGridParam', 'records')); ");
				//out.println(" 			        jgrid_load_complete (z); ");
				
				
				// added by udara 20-09-2016
				
				/*out.println("                       var invoices_count = jQuery('#table_invoice_details_hid_0').jqGrid('getGridParam', 'records'); ");  //Commented by Jithendra 20-10-2016
				out.println("                       if(invoices_count==0){    ");
				out.println(" 			               alert('No invoices to allocate'); ");
				out.println(" 			               document.Form1.hid_invoice.value = ''; ");
				out.println("                          div_invoice_details.innerHTML= ''; ");
				
				//out.println("                          contract_count=document.Form1.hid_cntract_cnt2.value; "); 
				//out.println("                          for (var z = 0; z < contract_count; z++) { ");
				//out.println("    	                        document.Form1.elements['Text_status_'+z].checked=false;");
				//out.println("                          } ");
				
				out.println(" 			               remv_inv_det(); ");
				out.println(" 			            }");Commented by Jithendra 20-10-2016 */ 
				
				// end by udara 20-09-2016
				
				
				out.println(" 			    }");
				out.println(" 			}");
				out.println(" 			);");
				out.println(" 		}");
				out.println(" 	} ");
				out.println(" } ");
				
				out.println("function set_sum_amount (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("    var invoice_type = rowObject.getElementsByTagName(\"cell\")[2].firstChild.nodeValue; ");
				out.println("    if(invoice_type==\"OTHER\") {");
				out.println("        document.Form1.elements['Hid_invoice_other_'+contract_id].value =  format_noobject(Math.round( ( parseFloat(unformat_number(document.Form1.elements['Hid_invoice_rd_'+contract_id])) - parseFloat(unformat_noobject(cellvalue)) )*100)/100);  ");
				out.println("    }else {");
				out.println("        document.Form1.elements['Hid_invoice_rd_'+contract_id].value =  format_noobject(Math.round( ( parseFloat(unformat_number(document.Form1.elements['Hid_invoice_rd_'+contract_id])) - parseFloat(unformat_noobject(cellvalue)) )*100)/100);  ");
				out.println("    }");
				
				out.println("    return cellvalue;");		
				out.println("}");
				
				out.println("function checkbox_app_fmatter (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("    var link=''; "); 
				//out.println("     alert(contract_id); ");
				out.println("    if(cellvalue==\"YES\"){ ");
				out.println("	  	link ='<input type=\"checkbox\" id=\"TXT_APPROVE_BATCH_'+contract_id+'_'+rowid+'\" name=\"TXT_APPROVE_BATCH_'+contract_id+'_'+rowid+'\"  value=\"'+cellvalue+'\" onclick=\"check_invoice_status('+rowid+','+contract_id+');\" checked  />'; ");
				out.println("    }else { ");
				out.println("	  	link ='<input type=\"checkbox\" id=\"TXT_APPROVE_BATCH_'+contract_id+'_'+rowid+'\" name=\"TXT_APPROVE_BATCH_'+contract_id+'_'+rowid+'\"  value=\"'+cellvalue+'\" onclick=\"check_invoice_status('+rowid+','+contract_id+');\"  />'; ");
				out.println("    }");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function txt_allocation_contract_fmatter (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("	 var link ='<input type=\"text\" name=\"TXT_ALLOCATION_CONTRACT_'+contract_id+'_'+rowid+'\" class=\"txt_input_number\" style=\"width:100px\" maxlength=\\'25\\' value=\"'+cellvalue+'\" onfocus=\"change_value('+rowid+','+contract_id+')\"  ONBLUR=\"amount_allocation('+rowid+','+contract_id+');\" > <input type=\"hidden\" name=\"HID_ALLOCATION_CONTRACT_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function hid_invoice_type_fmatter (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("	 var link = cellvalue+'<input type=\"hidden\" name=\"HID_INVOICE_TYPE_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function hid_invoice_id_fmatter (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("    document.Form1.elements['HID_INVOICE_COUNT_'+contract_id].value=rowid;");
				out.println("	 var link = cellvalue+'<input type=\"hidden\" name=\"HID_INVOICE_ID_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function invoice_no (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("	 var link = cellvalue+'<input type=\"hidden\" name=\"HID_INVOICE_NO_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function invoice_date (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("	 var link = cellvalue+'<input type=\"hidden\" name=\"HID_INVOICE_DATE_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function invoice_amount (cellvalue, options, rowObject)");
				out.println("{");
				out.println("    var rowid=rowObject.getAttribute('id');");
				out.println("    var contract_id = rowObject.getElementsByTagName(\"cell\")[0].firstChild.nodeValue; ");
				out.println("	 var link = cellvalue+'<input type=\"hidden\" name=\"HID_INVOICE_AMOUNT_'+contract_id+'_'+rowid+'\" value=\"'+cellvalue+'\" >  '; ");
				out.println("    return link;");		
				out.println("}");
				
				out.println("function check_invoice_status(num,contract) {");
				out.println("     m_amount = 0.00; ");
				out.println("     var m_amount_obj ; "); 
				
				
				
				out.println("   if( document.Form1.elements['HID_INVOICE_TYPE_'+contract+'_'+num].value == \"OTHER\" ){");
				out.println("   	m_amount = parseFloat(unformat_number(document.Form1.elements['Hid_invoice_other_'+contract])); ");
				out.println("   	m_amount_obj = document.Form1.elements['Hid_invoice_other_'+contract]; ");
				out.println("  	}else{");
				out.println("   	m_amount = parseFloat(unformat_number(document.Form1.elements['Hid_invoice_rd_'+contract])); ");
				out.println("   	m_amount_obj = document.Form1.elements['Hid_invoice_rd_'+contract]; ");
				out.println(" 	}");//end if
				
				out.println("  if(document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+num].checked){");
				out.println("    if( m_amount > 0 ){");
				out.println("  		document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+num].value=\"YES\";");
				out.println("  		document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+num].focus(); ");
				out.println("  	}else{");
				out.println("        document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+num].checked=false;");
				out.println("    	 alert('Allocation Amount is 0.00 '); ");
				
				out.println(" 	 }");//end if
				out.println("  }else{");
				out.println("   g_amount = parseFloat(unformat_number(document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+num])); ");
				out.println("       if(g_amount > 0 ){ ");
				out.println("           m_amount=Math.round((m_amount+g_amount)*100)/100; ");
				out.println("           m_amount_obj.value = m_amount; ");
				out.println("           document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+num].value = \"0.00\"; ");
				
				out.println("       } ");
				out.println("  document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+num].value=\"NO\";");
				out.println("  }");//end if
				out.println("}");//end function check_status
				
				out.println(" function change_value(id,contract) {"); 
				out.println("   m_text_amount = parseFloat(unformat_number(document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id])); ");
				out.println("   if(!isnumberok(m_text_amount,25)){ ");
				out.println(" 	document.Form1.elements['HID_ALLOCATION_CONTRACT_'+contract+'_'+id].value = document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id].value; ");
				out.println(" 	document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id].value=\"\"; ");
				//out.println("   alert(document.Form1.elements['HID_ALLOCATION_CONTRACT_'+contract+'_'+id].value);");
				out.println("    }");
				out.println(" }");
				
				
				
				
				
				out.println(" function amount_allocation(id,contract) {"); 
				
				out.println("     g_amount = 0.00; ");
				out.println("     var g_amount_obj ; "); 
				
				out.println("   if( document.Form1.elements['HID_INVOICE_TYPE_'+contract+'_'+id].value == \"OTHER\" ){");
				out.println("   	g_amount = parseFloat(unformat_number(document.Form1.elements['Hid_invoice_other_'+contract])); ");
				out.println("   	g_amount_obj = document.Form1.elements['Hid_invoice_other_'+contract]; ");
				
				out.println("  	}else{");
				out.println("   	g_amount = parseFloat(unformat_number(document.Form1.elements['Hid_invoice_rd_'+contract])); ");
				out.println("   	g_amount_obj = document.Form1.elements['Hid_invoice_rd_'+contract]; ");
				
				out.println(" 	}");//end if
				
				//out.println("   alert(format_number(document.Form1.elements['Text_amount_'+id]));");
				out.println("   g_hid_amount = parseFloat(unformat_number(document.Form1.elements['HID_ALLOCATION_CONTRACT_'+contract+'_'+id])); ");
				out.println("   if(format_number2(document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id]) ){ "); 
				
				//out.println("   g_amount = parseFloat(unformat_number(document.Form1.Text_amount)); ");
				out.println("   g_text_amount = parseFloat(unformat_number(document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id])); ");
				
				out.println("   if(g_text_amount > 0 ){ ");
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("           g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("       } else{");
				out.println("           g_amount=Math.round((g_amount-g_text_amount)*100)/100; ");
				out.println("       }");
				out.println("   } else{");
				out.println("       if(g_hid_amount > 0 ){ ");
				out.println("           g_amount=Math.round((g_amount+g_hid_amount)*100)/100; ");
				out.println("       	document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id].value=\"0.00\"; ");
				out.println("       	document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=false;");
				out.println("   		g_amount_obj.value=format_noobject(g_amount); ");
				//out.println("       	alert('Please enter valid value.'); ");
				out.println("       } else{");
				out.println("       	document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id].value=\"0.00\"; ");
				out.println("           document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=false;");
				out.println("   		g_amount_obj.value=format_noobject(g_amount); ");
				out.println("       alert('Please enter valid value.'); ");
				out.println("       }");
				out.println("       document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].value=\"NO\";");
				
				out.println("       document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=false;");
				out.println("   }");
				out.println("   if(g_amount >= 0 ){ ");
				out.println("   	if(g_text_amount == 0 || g_text_amount < 0 ){ ");
				out.println("       	document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=false;");
				out.println("   	} else { ");
				out.println("       	document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=true;");
				out.println("   		g_amount_obj.value=format_noobject(g_amount); ");
				out.println("   	}");
				
				out.println("   } else { ");
				//out.println("       alert('DSP*** '+g_amount); ");
				//out.println("       alert('DSP*** '+g_hid_amount); ");
				//out.println("       alert('DSP*** '+g_text_amount); ");
				out.println("       g_amount=Math.round((g_amount+g_text_amount)*100)/100; ");
				//out.println("       alert('DSP******* '+g_amount); ");
				out.println("   	g_amount_obj.value=format_noobject(g_amount); ");
				out.println("       document.Form1.elements['TXT_ALLOCATION_CONTRACT_'+contract+'_'+id].value=\"0.00\"; ");
				out.println("       alert('You can not enter more than '+g_amount_obj.value); ");
				out.println("       document.Form1.elements['TXT_APPROVE_BATCH_'+contract+'_'+id].checked=false;");
				//out.println("       document.Form1.elements['Text_amount_'+id].focus(); ");
				out.println("   }");
				
				out.println("   }");
				
				out.println(" }");
				
				///-----------------End----------------------///
				
				
				
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("    document.Form1.hid_option.value    =m_val;"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
				out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
				out.println("document.Form1.cli_help.disabled=false;"); 
				out.println("document.Form1.rec_help.disabled=true;"); 
				out.println("document.Form1.RECEIPT_NO.disabled=true;");
				out.println("new_window(); ");
				out.println("}"); 
				
				out.println("}else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
				out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
				out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
				out.println("document.Form1.cli_help.disabled=true;"); 
				out.println("document.Form1.rec_help.disabled=false;"); 
				out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
				out.println("document.Form1.CHEQUE_DATE_DD.disabled=false;");
				out.println("document.Form1.CHEQUE_DATE_MM.disabled=false;");
				out.println("document.Form1.CHEQUE_DATE_YY.disabled=false;");
				out.println("document.Form1.PAY_ACCOUNT.disabled=false;");
				out.println("document.Form1.accno_help.disabled=false;");
				out.println("befor_clear();");
				out.println("}"); 
				out.println("}else if(m_val==\"DELETE\"){"); 
				out.println(" if(confirm(\"Are you sure you want to  Delete a record?\")){  ");
				out.println("document.Form1.RECEIPT_NO.disabled=false;"); 
				out.println("befor_clear();");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.OPTION_DESC.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"DELETE\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");  
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function befor_reset(){");
				out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Receipt_Enter?chksql=main_page'");
				out.println(" }  ");
				out.println("}");
				
				out.println("function befor_back(){");
				out.println("   close_window(); ");
				out.println("}");
				out.println("function load_lock(){	"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are You Sure?\")){ "); 
				out.println("		window.close();");
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CMS_Receipt_Enter?chksql=main_page'");
				out.println("}"); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Settlement\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection - CMS Receipts - Entry - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection - CMS Receipts - Entry - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function befor_clear(){");
				out.println("     document.Form1.RECEIPT_NO.value      ='';"); 
				out.println("     document.Form1.CLIENT_CODE.value     ='';"); 
				out.println("     document.Form1.CLIENT_NAME.value     ='';"); 	
				out.println("     document.Form1.AMOUNT.value          ='';"); 
				out.println("     document.Form1.REP_AMOUNT.value      ='';"); 
				out.println("     document.Form1.PAY_BRANCH.value      ='';");
				out.println("     document.Form1.PAY_BRANCH_NAME.value ='';");
				out.println("     document.Form1.PAY_ACCOUNT.value     ='';");
				out.println("     document.Form1.CHEQUE_NO.value       ='';");
				out.println("     document.Form1.REMARK.value          ='';");
				//out.println(" 		 return_rec.innerHTML = ''; ");	
				out.println("}");
				
				
				out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
				out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
				out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
				out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
				out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
				out.println(" if(((parseInt(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
				out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
				out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
				out.println("}");
				out.println("else if(((parseFloat(FROM_DD.value))>(parseFloat(TO_DD.value)))&&");
				out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
				out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
				out.println("      alert('Value Date should not be greater than System Date');");
				out.println("return false;"); 
				out.println("     } ");
				out.println("}");
				out.println("else{");
				out.println("      alert('Value Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("}");
				out.println(" }");
				out.println(" else{");
				out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
				out.println("      alert('Value Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("   }");
				out.println("   else{");
				out.println("   } ");
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
				out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
				out.println(" }");
				out.println(" else{");
				out.println("      alert('Value Date should be greater than System Date');");
				out.println("return false;"); 
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
				out.println("    }");
				out.println("  else{");
				out.println("      alert('Value Date should be greater than System Date');");
				out.println("return false;"); 
				out.println("  }");
				out.println(" }");
				out.println("}");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				out.println("function chk_validity2(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
				out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
				out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
				out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
				out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
				out.println(" if(((parseInt(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
				out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
				out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
				out.println("}");
				out.println("else if(((parseFloat(FROM_DD.value))>(parseFloat(TO_DD.value)))&&");
				out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
				out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
				out.println("      alert('Value Date should not be less than System Date');");
				out.println("return false;"); 
				out.println("     } ");
				out.println("}");
				out.println("else{");
				out.println("      alert('Value Date should not be less than System Date');");
				out.println("return false;"); 
				out.println("}");
				out.println(" }");
				out.println(" else{");
				out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
				out.println("      alert('Value Date should not be less than System Date');");
				out.println("return false;"); 
				out.println("   }");
				out.println("   else{");
				out.println("   } ");
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
				out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
				out.println(" }");
				out.println(" else{");
				out.println("      alert('Value Date should not be less than System Date');");
				out.println("return false;"); 
				out.println(" }");
				out.println("}");
				out.println("else{");
				out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
				out.println("    }");
				out.println("  else{");
				out.println("      alert('Value Date should not be less than System Date');");
				out.println("return false;"); 
				out.println("  }");
				out.println(" }");
				out.println("}");
				out.println("return true;");
				out.println("}");
				out.println("}");
				
				
				out.println("function validate_date(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){");
				out.println("if(!chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY)){");
				out.println("FROM_DD.value=TO_DD.value;");
				out.println("FROM_MM.value=TO_MM.value;");
				out.println("FROM_YY.value=TO_YY.value;");
				out.println("}");
				//Added By ns on 23-02-2011 for restric the back dated receipt entry
				out.println("if(document.Form1.SETT_MODE.value !='STD_ORD' ) { ");
				out.println("if(!chk_validity2(TO_DD,TO_MM,TO_YY,FROM_DD,FROM_MM,FROM_YY)){");
				out.println("FROM_DD.value=TO_DD.value;");
				out.println("FROM_MM.value=TO_MM.value;");
				out.println("FROM_YY.value=TO_YY.value;");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("function validate_date_future_date(objdd,objmm,objyy) {"); 
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");
				out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("}");
				out.println("}");
				
				out.println("function date_validation(){");
				out.println("if(document.Form1.CHEQUE_DATE_DD.value!='' && document.Form1.CHEQUE_DATE_MM.value!='' && document.Form1.CHEQUE_DATE_YY.value!='')");
				out.println("checkMonthLength(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)");
				out.println("}");	
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println("}");
				
				out.println("function load_c_date(val) {");
				out.println("  if(document.Form1.hid_cal_date.value=='4'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("if(document.Form1.hid_option.value==\"NEW\"){");  //modified by nuwan de silva 02-08-07
				//out.println("     document.Form1.VAL_DAY.value=v_dd;"); //Comment By Chandana for Ref No.873 on 15-10-07
				//out.println("     document.Form1.VAL_MONTH.value=v_mm;");
				//out.println("     document.Form1.VAL_YEAR.value=v_yy;");
				out.println("  }");				
				out.println("  }");				
				out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.CHEQUE_DATE_DD.value=v_dd;");
				out.println("     document.Form1.CHEQUE_DATE_MM.value=v_mm;");
				out.println("     document.Form1.CHEQUE_DATE_YY.value=v_yy;");
				out.println("  }");				
				
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				//out.println("if(document.Form1.SETT_MODE.value==\"STD_ORD\" || document.Form1.SETT_MODE.value==\"DIR_DEP\" ){");
				out.println("     document.Form1.VAL_DAY.value=v_dd;");
				out.println("     document.Form1.VAL_MONTH.value=v_mm;");
				out.println("     document.Form1.VAL_YEAR.value=v_yy;");
				out.println("     validate_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				//out.println("  }");				
				out.println("  }");	
				out.println("}");
				
				out.println("	function chk_comment_length(obj){ ");
				out.println(" var remarks_length=obj.value.toString().length;");
				out.println("if(remarks_length>obj.maxlength) ");
				out.println("		window.event.keyCode=\"\"; ");
				out.println("} ");
				
				out.println("function count_length(obj){ ");
				out.println("var remarks_length=obj.value.toString().length; ");
				out.println("var remarks=obj.value.toString(); ");
				out.println("if(remarks_length>obj.maxlength){ ");
				out.println("obj.value=remarks.substring(0,obj.maxlength); ");
				out.println("} ");
				out.println("} ");
				
				out.println("function calculate_reporting_amount(){ ");
				out.println("  			AMOU.style.color='black';"); 
				out.println("if(document.Form1.AMOUNT.value!='0.00' && isnumberok(document.Form1.AMOUNT,25) ){");
				out.println("   		if(parseFloat(unformat_number(document.Form1.AMOUNT))>=0 ){");
				out.println("   	document.Form1.AMOUNT.value=format_noobject(unformat_noobject(document.Form1.AMOUNT.value));");
				//out.println("   alert(' value '+document.Form1.AMOUNT.value);");
				out.println("  		m_rep =  Number(parseFloat(unformat_noobject(document.Form1.AMOUNT.value)) * parseFloat(unformat_noobject(document.Form1.hid_exchange_rate.value)));");
				out.println("   	document.Form1.REP_AMOUNT.value=format_noobject(m_rep);");
				out.println("     }else{ ");
				out.println("   	alert('Please enter vald amount'); ");
				out.println("       document.Form1.AMOUNT.value='0.00';");
				out.println("     } ");
				out.println("}else { ");
				out.println(" if(document.Form1.AMOUNT.value!='0.00' ){");
				out.println("   	alert('Please Enter a Number'); ");
				out.println(" } ");
				out.println("   document.Form1.AMOUNT.value='0.00';");
				out.println("   document.Form1.REP_AMOUNT.value='0.00';");
				out.println("} ");
				out.println("} ");
				
				out.println("function calculate_other_charges(Obj1,Obj2){ ");
				
				out.println("if(Obj1.value!='' && isnumberok(Obj1,25)){");
				out.println("if (parseFloat(unformat_noobject(document.Form1.AMOUNT.value)) < (parseFloat(unformat_noobject(Obj2.value)) + parseFloat(unformat_noobject(Obj1.value))) ) {");
				out.println("   Obj2.value = parseFloat(unformat_noobject(document.Form1.AMOUNT.value))  - parseFloat(unformat_noobject(Obj1.value))  ");
				out.println("   format_noobject(Obj2.value);");
				out.println("   format_noobject(Obj1.value);");
				out.println("} ");
				out.println("else { ");
				out.println("alert('The Other Charges Amount can not exceed the Receipt amount'); ");
				out.println("   Obj2.value=0.00;");
				out.println("   Obj1.value=0.00;");
				out.println("} ");
				out.println("} ");
				
				out.println("} ");
				
				
				
				/*Call Ajax Data Retive*/
				out.println("function get_sysdate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_sysdate\";");
				out.println(" 	document.Form1.hid_help_status.value='SysDate';");
				out.println(" 	load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function validate_cheque_no() {");
				out.println("if(document.Form1.SETT_MODE.value!='CASH' ){");
				out.println("if(document.Form1.CHEQUE_NO.value!='' && document.Form1.PAY_BRANCH.value!='' ){");
				out.println("document.Form1.hid_help_status.value='Cheque_number_validation'");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_SETT_RECEIPT_CHEQUE_VALIDATE&data_val='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value+'';");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				out.println("}");
				out.println("}");
				
				out.println("function get_excharate() {");
				out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_XMLFile?chksql=get_excharate&CURR_CODE=\"+document.Form1.CURR_CODE.value+\"&VAL_DATE=\"+document.Form1.VAL_DAY.value+\"-\"+document.Form1.VAL_MONTH.value+\"-\"+document.Form1.VAL_YEAR.value;");
				out.println(" 	document.Form1.hid_help_status.value='ExcRate';");
				out.println("   load_interface(m_url,'XML');");
				out.println("}");
				
				/*end section Call Ajax Data Retive*/
				
				
				/*Assign The Values */ 
				out.println("function get_vector(data_vec) {");
				
				out.println("if(data_vec.length >0 && document.Form1.hid_help_status.value=='SysDate'){");
				out.println("assign_SysDate(data_vec);");
				out.println("}");
				
				out.println("else if(data_vec.length >0 && document.Form1.hid_help_status.value=='Cheque_number_validation'){");
				out.println("assign_cheque_number(data_vec);");
				out.println("}");
				
				out.println("else if(data_vec.length >0 && document.Form1.hid_help_status.value=='ExcRate'){");
				out.println("assign_ExcRate(data_vec);");
				out.println("}");
				///--- Added by DSP Chathuranga 2013-04-22 ---///
				out.println("else if(data_vec.length >0 && document.Form1.hid_help_status.value=='CONTRACT_DETAILS'){");
				out.println(" 	contract_details_assing(data_vec);");
				out.println("}");
				///-------------------End--------------------///
				out.println("}");   
				
				/*Assign The Values */
				out.println("function assign_SysDate(data) {");
				out.println("     document.Form1.VAL_DAY.value         			=  data[0];"); 
				out.println("     document.Form1.VAL_MONTH.value       			=  data[1];"); 
				out.println("     document.Form1.VAL_YEAR.value        			=  data[2];"); 
				out.println("     document.Form1.HID_SYS_VAL_DAY.value          =  data[0];"); 
				out.println("     document.Form1.HID_SYS_VAL_MONTH.value        =  data[1];"); 
				out.println("     document.Form1.HID_SYS_VAL_YEAR.value         =  data[2];"); 
				out.println("     get_excharate()");
				out.println("}");
				
				out.println("function assign_cheque_number(data) {");
				out.println("alert('Record already exists');");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Settlement?chksql=view_cheques&cheque_no='+document.Form1.CHEQUE_NO.value+'&branch_code='+document.Form1.PAY_BRANCH.value;");
				out.println("window.open(m_url,'displayWindow2','left=450,top=200,width=600,height=350,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("document.Form1.CHEQUE_NO.value=\"\"");
				out.println("}");
				
				out.println("function assign_ExcRate(data) {");
				out.println("     document.Form1.hid_exchange_rate.value         =  data[0];"); 
				out.println("}");
				
				out.println("function check_box_enable(status,allo) {");//inesh xxx
				//out.println("alert('status'+status+'allo'+allo);");
				out.println("      document.Form1.chk_closing.checked 		= false;  ");
				out.println("      document.Form1.chk_init_charge.checked 	= false;  ");
				out.println("      document.Form1.chk_stamp_duty.checked 	= false;  ");
				out.println("      document.Form1.chk_refinance.checked 	= false;  ");
				out.println("      document.Form1.chk_init_charge.value 	= 'N';  ");
				out.println("      document.Form1.chk_stamp_duty.value 		= 'N';  ");
				out.println("      document.Form1.chk_refinance.value 		= 'N';  ");
				out.println("      document.Form1.chk_closing.value	 		= 'N';  ");
				
				out.println("   if(allo=='NO'&& status=='' ){");
				out.println("      document.Form1.chk_closing.disabled 		= true;  ");
				out.println("      document.Form1.chk_init_charge.disabled 	= true;  ");
				out.println("      document.Form1.chk_stamp_duty.disabled 	= true;  ");
				out.println("      document.Form1.chk_refinance.disabled 	= true;  ");
				out.println(" 	}else if(allo=='YES'&& status==''){");
				out.println("      document.Form1.chk_closing.disabled 		= false;  ");
				out.println("      document.Form1.chk_init_charge.disabled  = false;  ");
				out.println("      document.Form1.chk_stamp_duty.disabled 	= false;  ");
				out.println("      document.Form1.chk_refinance.disabled 	= false;  ");
				out.println(" 	}else if(allo==''&& status!=''){");
				
				out.println("   	if(status =='Activated - Perform' ){");
				
				out.println("      		document.Form1.chk_closing.disabled 		= false;  ");
				out.println("      		document.Form1.chk_init_charge.disabled  	= false;  ");
				out.println("      		document.Form1.chk_stamp_duty.disabled 		= false;  ");
				out.println("      		document.Form1.chk_refinance.disabled 		= false;  ");
			
			    out.println("   	}else if(status =='Activated - Non Perform' ){");				
				out.println("      		document.Form1.chk_closing.disabled 		= false;  ");
				out.println("      		document.Form1.chk_init_charge.disabled  	= true;  ");
				out.println("      		document.Form1.chk_stamp_duty.disabled 		= true;  ");
				out.println("      		document.Form1.chk_refinance.disabled 		= true;  ");
				
				out.println("   	}else if(status =='Yard Vehicle - Perform' || status =='Yard Vehicle - Non Perform' ||  status =='Vehicle In The Yard - Non Perform' || status =='Vehicle In The Yard - Perform'){");//[Added milinda for closing chk box enable #JB23102017-01426]
				
				out.println("      		document.Form1.chk_closing.disabled 		= false;  ");
				out.println("      		document.Form1.chk_init_charge.disabled  	= true;  ");
				out.println("      		document.Form1.chk_stamp_duty.disabled 		= true;  ");
				out.println("      		document.Form1.chk_refinance.disabled 		= true;  ");				
				out.println(" 		} ");
				out.println(" 		else{");
				
				out.println("      		document.Form1.chk_closing.disabled 		= true;  ");
				out.println("      		document.Form1.chk_init_charge.disabled 	= true;  ");
				out.println("      		document.Form1.chk_stamp_duty.disabled 		= true;  ");
				out.println("      		document.Form1.chk_refinance.disabled 		= true;  ");
				out.println(" 		}");
				out.println(" 	}");
				out.println("}");
				
				// added by udara 13-07-2015 
				out.println("function check_chk_boxes(obj){"); 
				out.println("   if(document.Form1.chk_closing.checked==true){");
				out.println("      document.Form1.chk_init_charge.checked = false;  ");
				out.println("      document.Form1.chk_stamp_duty.checked = false;  ");
				out.println("      document.Form1.chk_init_charge.value = 'N';  ");
				out.println("      document.Form1.chk_stamp_duty.value = 'N';  ");
				
				out.println("      document.Form1.chk_closing.value = 'Y';  ");
				
				out.println("      document.Form1.chk_refinance.checked = false;  "); // added by udara 25-08-2016
				out.println("      document.Form1.chk_refinance.value = 'N';  "); // added by udara 25-08-2016
				
				
				//out.println("      check_closing(obj); ");
				
				out.println("   }");
				out.println("   else if(document.Form1.chk_init_charge.checked==true){");
				out.println("      document.Form1.chk_closing.checked = false;  ");
				out.println("      document.Form1.chk_stamp_duty.checked = false;  ");
				out.println("      document.Form1.chk_closing.value = 'N';  ");
				out.println("      document.Form1.chk_stamp_duty.value = 'N';  ");
				out.println("      document.Form1.chk_init_charge.value = 'I';  ");
				
				out.println("      document.Form1.chk_refinance.checked = false;  "); // added by udara 25-08-2016
				out.println("      document.Form1.chk_refinance.value = 'N';  "); // added by udara 25-08-2016
				
				out.println("   }");
				out.println("   else if(document.Form1.chk_stamp_duty.checked==true){");
				out.println("      document.Form1.chk_closing.checked = false;  ");
				out.println("      document.Form1.chk_init_charge.checked = false;  ");
				out.println("      document.Form1.chk_closing.value = 'N';  ");
				out.println("      document.Form1.chk_init_charge.value = 'N';  ");
				out.println("      document.Form1.chk_stamp_duty.value = 'S';  ");
				
				out.println("      document.Form1.chk_refinance.checked = false;  "); // added by udara 25-08-2016
				out.println("      document.Form1.chk_refinance.value = 'N';  "); // added by udara 25-08-2016
				
				out.println("   }");
				
				// added by udara 25-08-2016
				out.println("   else if(document.Form1.chk_refinance.checked==true){");
				out.println("      document.Form1.chk_closing.checked = false;  ");
				out.println("      document.Form1.chk_init_charge.checked = false;  ");
				out.println("      document.Form1.chk_stamp_duty.checked = false;  ");
				out.println("      document.Form1.chk_closing.value = 'N';  ");
				out.println("      document.Form1.chk_init_charge.value = 'N';  ");
				out.println("      document.Form1.chk_stamp_duty.value = 'N';  ");
				out.println("      document.Form1.chk_refinance.value = 'R';  ");
				out.println("   }");
				// end by udara 25-08-2016
				
				
				out.println("}");
				// end by udara 13-07-2015
				
				
				out.println("function create_client_cms(client_code){");//JB15032019-07215 2019-03-19 inesh
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation_cms?client_code=\"+document.Form1.CLIENT_CODE.value+\"&save_close=N&close_status=Y\"+\"&app_screen=APP_R\";"); // added by udara 27-08-2014
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				out.println("}"); 
				
				out.println("function create_client(client_code){");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_type=&inquiry_no=&APP_NO=&save_close=A&no_of_rec=1&close_status=Y&app_screen=Y\";");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?screen=N\";");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_code=\"+document.Form1.CLIENT_CODE.value+\"&save_close=N&close_status=Y\"+\"&app_screen=APP_R\";"); // added by udara 27-08-2014
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1');"); 
				out.println("}"); 
				
				out.println("function allocation_amount_check(){");
				out.println("  		if(document.Form1.TXT_ALLOCATION_METHOD.value =='NO'){");
				out.println("                return true;");
				out.println(" 		}");
				out.println("    var rental_amount      = 0;");
				out.println("    var insurance_amount = 0;");
				out.println("    var allo_amount = parseFloat(unformat_number(document.Form1.AMOUNT));");
				out.println("    for (var i = 0; i < document.Form1.hid_cntract_cnt2.value; i++) {");
				out.println("        if(document.Form1.elements['Text_status_'+i].value=='YES'){");
				out.println("            rental_amount = parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+i]));");
				out.println("            insurance_amount = parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+i]));");
				out.println("            if(allo_amount < (rental_amount+insurance_amount)){");
				out.println("                alert('Rental and Insurance amount can not be greater than allocation amount');");
				out.println("                document.Form1.hid_rental_allo_amount.value                = '0.00';");
				out.println("                document.Form1.elements['TXT_RENTAL_ODI_'+i].value      = '0.00';");
				out.println("                document.Form1.elements['HID_TXT_RENTAL_ODI_'+i].value  = '0.00';");
				out.println("                ");
				out.println("                document.Form1.hid_ins_allo_amount.value                 = '0.00';");
				out.println("                document.Form1.elements['TXT_OTHER_'+i].value      = '0.00';");
				out.println("                document.Form1.elements['HID_TXT_OTHER_'+i].value  = '0.00';");
				out.println("                return false;");
				out.println("            }else{");
				out.println("                document.Form1.hid_rental_allo_amount.value = rental_amount;");
				out.println("                document.Form1.hid_ins_allo_amount.value    = insurance_amount;");
				out.println("                document.Form1.elements['HID_TXT_RENTAL_ODI_'+i].value = rental_amount;");
				out.println("                document.Form1.elements['HID_TXT_OTHER_'+i].value = insurance_amount;");
				out.println("                return true;");
				out.println("            }");
				out.println("        }");
				out.println("    }    ");
				out.println("    return false;");
				out.println("}");
				
				out.println("function change_rental_amount(i){        ");
				out.println("    if(isnumberok(document.Form1.elements['TXT_RENTAL_ODI_'+i],25)){");
				out.println("        var rental_amount = parseFloat(unformat_number(document.Form1.elements['TXT_RENTAL_ODI_'+i]));");
				out.println("        if(rental_amount >= 0){");
				out.println("        	var allo_amount = parseFloat(unformat_number(document.Form1.AMOUNT));        ");
				out.println("        	if(allo_amount >= rental_amount){            ");
				out.println("            	document.Form1.hid_rental_allo_amount.value = rental_amount;");
				out.println("            	document.Form1.elements['TXT_RENTAL_ODI_'+i].value      = format_noobject(rental_amount);");
				out.println("            	document.Form1.elements['HID_TXT_RENTAL_ODI_'+i].value      = format_noobject(rental_amount);");
				out.println("        	}else{");
				out.println("            	alert('R.Allocation Amountcan not be greater than allocation amount');");
				out.println("            	document.Form1.hid_rental_allo_amount.value = 0.00;");
				out.println("            	document.Form1.elements['TXT_RENTAL_ODI_'+i].value      = '0.00';");
				out.println("            	document.Form1.elements['HID_TXT_RENTAL_ODI_'+i].value      = '0.00';");
				out.println("        	}        ");
				out.println("        }else{        ");
				out.println("        	 	alert('Please enter valid amount. ');       ");
				out.println("            	document.Form1.elements['TXT_RENTAL_ODI_'+i].value  = '0.00';");
				out.println("        }        ");
				out.println("    }else{");
				out.println("        alert('Please enter valid amount');");
				out.println("        document.Form1.hid_rental_allo_amount.value = 0.00;");
				out.println("        document.Form1.elements['TXT_RENTAL_ODI_'+i].value      = '0.00';");
				out.println("        document.Form1.elements['HID_TXT_RENTAL_ODI_'+i].value      = '0.00';");
				out.println("    }    ");
				out.println("}");
				
				out.println("function change_insurance_amount(i){");
				out.println("    if(isnumberok(document.Form1.elements['TXT_OTHER_'+i],25)){    ");
				out.println("        var insurance_amount = parseFloat(unformat_number(document.Form1.elements['TXT_OTHER_'+i]));");
				out.println("        if(insurance_amount >= 0){ ");
				out.println("        	var allo_amount = parseFloat(unformat_number(document.Form1.AMOUNT));");
				out.println("        	if(allo_amount >= insurance_amount){");
				out.println("            	document.Form1.hid_ins_allo_amount.value = insurance_amount;");
				out.println("            	document.Form1.elements['TXT_OTHER_'+i].value      = format_noobject(insurance_amount);");
				out.println("            	document.Form1.elements['HID_TXT_OTHER_'+i].value      = format_noobject(insurance_amount);");
				out.println("        	}else{");
				out.println("            	alert('Ins. Allocation Amount can not be greater than allocation amount');");
				out.println("            	document.Form1.hid_ins_allo_amount.value = 0.00;");
				out.println("            	document.Form1.elements['TXT_OTHER_'+i].value      = '0.00';");
				out.println("            	document.Form1.elements['HID_TXT_OTHER_'+i].value      = '0.00';");
				out.println("        	}");
				out.println("        }else{");
				out.println("        	 	alert('Please enter valid amount. ');       ");
				out.println("            	document.Form1.elements['TXT_OTHER_'+i].value  = '0.00';");
				out.println("        }");
				out.println("    }else{");
				out.println("        alert('Please enter valid amount');");
				out.println("        document.Form1.hid_ins_allo_amount.value = 0.00;");
				out.println("        document.Form1.elements['TXT_OTHER_'+i].value      = '0.00';");
				out.println("        document.Form1.elements['HID_TXT_OTHER_'+i].value      = '0.00';");
				out.println("    }");
				out.println("}");
				
				out.println("</script>"); 
				
				/*Java Script End section*/
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad='onLoad_Functions()' >");  
				
				out.println("<FORM NAME='Form1' method='post'>"); 
				/*Form Hidden Variable Declare here*/
				out.println("<INPUT TYPE='Hidden' NAME='hid_user_name' VALUE=\"\"> "); // added by udara 22-06-2020
				out.println("<input type='hidden' name='Hid_scr_name' value='AF_RE_SETTELMENT' > ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\"> ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\"> ");
				out.println("<INPUT TYPE='Hidden' NAME='hid_return_count' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_ACCOUNT_NO\" value=\" \">");
				out.println("<input type=hidden name=\"hid_TEN_AMOUNT\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_RET_AMOUNT\" value=\"0\">");
				out.println("<input type=hidden name=\"TXT_OTHER_INV\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_RENTAL_OTHER_INV\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_INSURANCE_PREMIUM\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_LUX_TAX\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_REVENUE_LICENCY\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_RMV_REG_FEES\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_TXT_BRANCH_CODE\" value=\" \">");
				out.println("<input type=hidden name=\"hid_TXT_ACC_REF_NO\" value=\" \">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_DAY\" value=\"\">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_MONTH\" value=\"\">");
				out.println("<input type=hidden name=\"HID_SYS_VAL_YEAR\" value=\"\">");
				out.println("<input type=hidden name=\"hid_cntract_cnt\" value=\"0\">"); 
				out.println("<input type=hidden name=\"hid_cntract_cnt2\" value=\"0\">");  
				out.println("<input type=hidden name=\"hid_invoice\" value=\"\">");  //hid_invoice
				out.println("<input type=hidden name=\"hid_exchange_rate\" value=\"0\">"); 
				out.println("<input type=hidden name=\"hid_amount2\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_tot_odi_rental\" value=\"0\">");
				out.println("<input type=hidden name=\"hid_tot_other\" value=\"0\">");
				
				out.println("<input type=hidden name=\"hid_allo_fin_no\" value=\"\">");
				out.println("<input type=hidden name=\"hid_rental_allo_amount\" value=\"0.00\">");
				out.println("<input type=hidden name=\"hid_ins_allo_amount\" value=\"0.00\">");
				/*End Section Form Hidden Variable Declare here*/
				
				
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td >"); //style=\"height: 327px\"
				
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box>Collection - CMS Receipts - Entry</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr>");
				out.println("<td><input type=button name=reset value=\"New\" style =\"visibility:hidden\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_out_value();'></td>");//document.Form1.OPTION_DESC.value
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				//out.println("<td><input type=button name=Dele value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_out_value();' disabled></td>");
				out.println("<td width=10%>&nbsp;</td>");
				out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=befor_submit(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");				
				out.println("<td><input type='button' name=help class=mainbut value=\"Help\" onclick=load_screen_status(\"HELP\"); >  </td>");  //Added By Nuwan De Silv 17-05-05
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Reset\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_out_value();'></td>");
				out.println("<td>&nbsp;&nbsp;&nbsp;</td>");			
				out.println("<td><input type=button name=Doc_1 value=\"Document\" class=mainbut onclick=show_document(); onMouseOver='load_roll_value(\"Document\");' style='width: 130px' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr></table>");
				out.println("</td>	");
				out.println("</tr>");
				
				/*out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				*/
				
				
				
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				//==============================================================Client Details===========================================================================
				//=======================================================================================================================================================
				out.println("<fieldset>");
				out.println("<legend><strong>Client Details:</strong></legend>");
				
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class='table'  cellspacing=\"0\" cellpadding=\"1\">"); 
				out.println("<tr class=tr_input>");
				out.println(" 	<td style=\"display:none;\" width=\"9%\"></td>");
				out.println(" 	<td style=\"display:none;\" width=\"21%\"></td>");
				out.println(" 	<td style=\"display:none;\" width=\"9%\"></td>");
				out.println(" 	<td style=\"display:none;\" width=\"31%\"></td>");
				out.println(" 	<td style=\"display:none;\" width=\"9%\"></td>");
				out.println(" 	<td style=\"display:none;\" width=\"21%\"></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td  id='CCODE' >Client Code *</td>");
				//out.println(" 	<td  colspan= '2' ><input name=\"CLIENT_CODE\"  id=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\"  onblur=\"client_help()\"  class=\"txt_input\" > "); //style=\"{background-color:#CCCCFF}\" //onchange=\"check_client(), inv_details()\"");
				//out.println(" 	<td  colspan= '2' ><input name=\"CLIENT_CODE\"  id=\"CLIENT_CODE\" type=\"text\" maxlength=\"20\"  onblur=\"client_help()\"  class=\"txt_input\" > "); // commented by udara 24-04-2018 // increased maxlength by udara 28-07-2017
				out.println(" 	<td  colspan= '2' ><input name=\"CLIENT_CODE\"  id=\"CLIENT_CODE\" type=\"text\" maxlength=\"20\"   class=\"txt_input\" > "); // dded by udara 24-04-2018 removed onblur function
				out.println(" 		  <input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
				out.println(" 		  <input type=button name=client_det value=\"Client Detail\" class=\"but_input\" style=\"width:90px;\" onclick=\"show_client(document.Form1.CLIENT_CODE.value)\">");
			 	//out.println(" 		  <input type=button name=client_create value=\"Create Client\" class=\"but_input\" style=\"width:90px;\" onclick=\"create_client(document.Form1.CLIENT_CODE.value)\">");
				out.println(" 		  <input type=button name=client_create value=\"Create Client\" class=\"but_input\" style=\"width:90px;\" onclick=\"create_client_cms(document.Form1.CLIENT_CODE.value)\">");//2019-03-18 JB15032019-07215
				out.println(" 		  <input style=\"display:none;\" type=button name=charges value=Charges class=\"but_input\" style=\"width:90px;\" onclick=\"display_charges_pending(document.Form1.CLIENT_CODE.value)\"></td>");
				//out.println(" 	<td  ></td>");
				out.println(" 	<td  >Client Name &nbsp;&nbsp;<input name=\"CLIENT_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>");
				out.println(" 	<td  style=\"display:none;\"> Receipt No *</td>");
				out.println(" 	<td  style=\"display:none;\"><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"  onchange=check_client() disabled > ");
				out.println(" 		   <input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\" disabled ></td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td  >Payee Type</td>");
				out.println(" 	<td ><SELECT id=\"PAY_TYPE\" name=\"PAY_TYPE\" class=\"txt_input\" onChange=\" show_payee_type_div()\" > "); //, set_address_pay_type()
				out.println(" 			<OPTION value=\"CLIENT\">Client</OPTION>");
				out.println(" 			<OPTION value=\"THIRD\">Third Party</OPTION>");
				out.println(" 			</SELECT></td>");
				out.println(" 	<td  colspan='4'><div id='div_third_party_details'></td>");				
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println(" 	<td  valign='top'>Remark</td>");
				out.println(" 	<td  ><TEXTAREA class='txt_input' name='REMARK' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>");
				out.println(" 	<td  ></td>");
				out.println(" 	<td  valign='top'> ");
				out.println(" <fieldset>");
				out.println(" 		<table align='center'  width='100%' class='table'>"); 
				out.println(" 			<tr class=tr_input>");
				out.println(" 				<td width=\"40%\" valign='top'>Closing</td>");
				out.println(" 				<td width='10%' ><input type=checkbox  name='chk_closing' value = 'N' onclick='check_chk_boxes(this);' ></td>");  // added by udara 13-07-2015
				out.println(" 				<td width=\"40%\" valign='top'>Documentation Charges</td>"); // out.println("<td width=\"20%\" valign='top'>Initial Charges</td>");
				out.println(" 				<td width='10%' ><input type=checkbox  name='chk_init_charge' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println(" 		</tr>");
				out.println(" 		<tr>"); 
				out.println(" 				<td width=\"40%\" valign='top'>Stamp Duty</td>");
				out.println(" 				<td width='10%' ><input type=checkbox  name='chk_stamp_duty' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println(" 				<td width=\"40%\" valign='top'>Refinance</td>");
				out.println(" 				<td width='10%' ><input type=checkbox  name='chk_refinance' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println(" 		</tr>");				
				out.println("		</table>");
				out.println(" </fieldset>");
				out.println("	</td>");
				out.println(" 	<td  ></td>");
				out.println(" 	<td  ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</fieldset>");
				//=========================================================================================================================================================
				//=========================================================================================================================================================
				/*
				out.println("<fieldset>");				
				out.println("<legend><strong>Client Details:</strong></legend>");
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class='table'  cellspacing=\"0\" cellpadding=\"1\">"); 
								
				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"9%\" > Receipt No *</td>");
				out.println("<td width=\"21%\" ><input name=\"RECEIPT_NO\" type=\"text\" maxlength=\"15\" class=\"txt_input\" onblur=\"makeRequest6(this.value)\"  onchange=check_client() disabled > ");
				out.println(" 	<input type=button name=rec_help value=... class=\"but_input\" onclick=\"receipt_help()\" disabled ></td>");
				out.println("<td id='CCODE' width=\"9%\">Client Code *</td>");
				out.println("<td width=\"31%\"  ><input name=\"CLIENT_CODE\"  id=\"CLIENT_CODE\" type=\"text\" maxlength=\"10\"  onblur=\"client_help()\"  class=\"txt_input\" > "); //style=\"{background-color:#CCCCFF}\" //onchange=\"check_client(), inv_details()\"
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"client_help()\">");
				out.println("<input type=button name=client_det value=\"Client Detail\" class=\"but_input\" style=\"width:90px;\" onclick=\"show_client(document.Form1.CLIENT_CODE.value)\">");
				out.println("<input type=button name=charges value=Charges class=\"but_input\" style=\"width:90px;\" onclick=\"display_charges_pending(document.Form1.CLIENT_CODE.value)\"></td>");
				out.println("<td width=\"9%\" >Client Name</td>");
				out.println("<td width=\"21%\" ><input name=\"CLIENT_NAME\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" disabled></td>");
				out.println("</tr>");//done
				
				out.println("<tr class=tr_input>");
				out.println("<td >Payee Type</td>");
				out.println("<td ><SELECT id=\"PAY_TYPE\" class=\"txt_input\" onChange=\" show_payee_type_div()\" > "); //, set_address_pay_type()
				out.println("<OPTION value=\"CLIENT\">Client</OPTION>");
				out.println("<OPTION value=\"THIRD\">Third Party</OPTION>");
				out.println("</SELECT></TD>");
				out.println("<td colspan='4' ><div id='div_third_party_details'></div></td>");
				out.println("</tr>");//done
				
				
				out.println("<tr class=tr_input>");
				out.println("<td valign='top'  >Settlement Mode</td>");
				out.println("<td valign='top'  > <SELECT name=\"SETT_MODE\"  id=\"SETT_MODE\" class=\"txt_input\" onChange=\"show_settlement_mode_details()\"> ");
				out.println("<OPTION value=\"CASH\" selected >Cash</OPTION>");
				out.println("<OPTION value=\"CHEQUE\"  >Cheque</OPTION>");
				out.println("<OPTION value=\"STD_ORD\">Standing Order</OPTION>");
				//out.println("<OPTION value=\"DIR_DEP\">Direct Deposit</OPTION>");//commented by Jithendra 30-09-2016
				out.println("</SELECT></TD>");//done
				
				out.println("<td  valign='top'>Remark</td>");
				out.println("<td  ><TEXTAREA class='txt_input' name='REMARK' style=\"width:280px; height:50px;\" maxlength='500' size='500' onkeyPress=\"chk_comment_length(this)\"  onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>"); 
				//done
				
				
				//out.println("<td  width=\"*%\">Rec Type</td>");
				out.println("<td  colspan='2'>");
				//out.println("<legend>Rec Type :</legend>");
				//out.println("<fieldset>");
				out.println("<table align='center'  width='100%' class='table'>"); 				
				out.println("<tr>"); 
				out.println("<td width=\"40%\" valign='top'>Closing</td>");
				out.println("<td width='10%' ><input type=checkbox  name='chk_closing' value = 'N' onclick='check_chk_boxes(this);' ></td>");  // added by udara 13-07-2015
				out.println("<td width=\"40%\" valign='top'>Documentation Charges</td>"); // out.println("<td width=\"20%\" valign='top'>Initial Charges</td>");
				out.println("<td width='10%' ><input type=checkbox  name='chk_init_charge' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println("</tr>");
				out.println("<tr>"); 
				out.println("<td width=\"40%\" valign='top'>Stamp Duty</td>");
				out.println("<td width='10%' ><input type=checkbox  name='chk_stamp_duty' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println("<td width=\"40%\" valign='top'>Refinance</td>");
				out.println("<td width='10%' ><input type=checkbox  name='chk_refinance' value = 'N' onclick='check_chk_boxes(this);' ></td>"); // class='txt_input'
				out.println("</tr>");
				out.println("</table>");
				//out.println("</fieldset>");
				out.println("</td>");
				
				out.println("</tr>");
				*/
				//out.println("</table>");
				//out.println("</fieldset>");
				//==============================================================Client Details===========================================================================
				//=======================================================================================================================================================
				
				out.println("<br>");
				/*
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class='table'>"); 
				out.println("<tr > ");  
				out.println("<td width=\"100%\"><DIV ID='div_settlement_details'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				*/
				out.println("<fieldset>");
				out.println("<legend><strong>Settlemt Details:</strong></legend>");
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class='table'  cellspacing=\"0\" cellpadding=\"1\">"); 
				out.println("<tr class=tr_input>");
				out.println(" 	<td width=\"9%\">Settlement Mode</td>");
				out.println(" 	<td width=\"21%\"><SELECT name=\"SETT_MODE\"  id=\"SETT_MODE\" class=\"txt_input\" onChange=\"show_settlement_mode_details()\">");
				out.println(" 			<OPTION value=\"CASH\" selected >Cash</OPTION>");
				out.println(" 			<OPTION value=\"CHEQUE\"  >Cheque</OPTION>");
				out.println(" 			<OPTION value=\"STD_ORD\">Standing Order</OPTION>");
				out.println(" 			<OPTION value=\"DIR_DEP\">Direct Deposit</OPTION>"); 
				out.println(" 		 </SELECT></TD>");
				out.println(" 	<td width=\"9%\">&nbsp;</td>");
				out.println(" 	<td width=\"31%\">&nbsp;</td>");
				out.println(" 	<td width=\"9%\">&nbsp;</td>");
				out.println(" 	<td width=\"21%\">&nbsp;</td>");				
				out.println("</tr>"); 
				out.println("</table>");
				out.println(" <DIV ID='div_settlement_details'></DIV> ");
				out.println("</fieldset>");
				
				out.println("<br>");
				
				
				out.println("<br>");
				
				/*Allocation Details*/
				out.println("<fieldset>");
				out.println("<legend><strong>Allocation Details:</strong></legend>");
				out.println("<table align=\"center\" border=\"0\"  width=\"100%\" class=table>"); 
				
				out.println("<tr class=tr_input>");
				
				out.println("<td width=\"9%\" ID='AMOU' ><strong>Amount *</strong></td>"); 
				out.println("<td width=\"31%\" ><input name=\"AMOUNT\"  type=\"text\" maxlength=\"20\" class=\"txt_input_number\" onblur=calculate_reporting_amount()   STYLE=\"{text-align:right;}\" value=\"0.00\" ></td>");
				
				out.println("<td width=\"9%\" ID=CURR >Currency </td>");
				out.println("<td width=\"31%\" ><SELECT onchange=\"get_excharate();\" name=\"CURR_CODE\" class=\"txt_input\" disabled > ");
				rs = stmt.executeQuery ("SELECT CURR_CODE, CURR_SYMBOL, REP_CURR "+
					"FROM   "+m_schema_name+".AF_CO_MAS_CURRENCY "+
					"WHERE ACTIVE_STATUS='Y' "+ //added by nuwan de silva 23-07-07
					"ORDER  BY DEFAULT_VALUE DESC ");
				while(rs.next()){
					
					/*if(rs.getString(3).equals("Y"))
					{
						out.println("get_excharate()");
						out.println("document.Form1.hid_exchange_rate.value=");
					}
					*/
					if(rs.getString(1).equals("LKR")){
						out.println("<OPTION value=\""+rs.getString(1)+"\" SELECTED >"+rs.getString(2)+"</OPTION>");
					}else {
						out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
					}
				}
				out.println("</td>");
				
				
				out.println("<td width=\"9%\" ID='RAMO'  ><strong>Rep. Curr. Amount *</strong></td>");
				out.println("<td width=\"21%\" > <input name=\"REP_AMOUNT\"   type=\"text\" maxlength=\"25\"  class=\"txt_input_number\" value=\"0.00\" STYLE=\"{text-align:right;}\" disabled ></td> ");
				out.println("</tr>");
				
				
				
				out.println("<tr class=tr_input>");
				out.println("<td width=\"9%\" >Allocation to Contract</td>");
				out.println("<td ><SELECT id=\"TXT_ALLOCATION_METHOD\" class=\"txt_input\" onChange=\" alloc_contract(this)\" > ");
				out.println("<OPTION value=\"NO\" >NO</OPTION>");
				out.println("<OPTION value=\"YES\" selected>YES</OPTION>");
				out.println("</SELECT>");
				out.println("<input type=\"button\" name=\"Allocate\" value=\"Allocate\" class=\"but_input\" style=\"width:90px;\" onclick=\"Change_Allo_Mode()\" ></td>");//DISABLED
				out.println("<td width=\"9%\" > <div id='div_allocated_amount_dis'></div> </td>");
				out.println("<td  ><div id='div_allocated_amount'></div></td>");
				out.println("<td width=\"9%\" > <div id='div_balance_amount_dis'></div></td>");
				out.println("<td  ><div id='div_balance_amount'></div></td>");
				out.println("</tr>");
				
				
				out.println("</table>");
				out.println("</fieldset>");
				rs.close();
				/*End Allocation Details*/
				
				out.println("<table id=\"list2\"></table>");
				out.println("<br/>"); 
				out.println("<div id='div_contract_details'></div>");
				
				out.println("<table width='100%'>");
				out.println(" 	<tr>");
				out.println(" 		<td width ='60%' valign='top'>");
				out.println(" 			<div id='div_contract_details_client' align='left' ></div>");
				out.println(" 		</td>");
				out.println(" 		<td width =40% valign='top'>");
				out.println(" 			<div id='div_outstanding_details' align='left' ></div>");
				out.println(" 		</td>");
				out.println(" 	</tr>");
				out.println("</table>");
				
				
				out.println("");
				out.println("");
				
				//out.println("<div id='div_contract_details_client' align='left' ></div>");
				//out.println("<div id='div_outstanding_details' align='left' ></div>");
				out.println("<br/>"); 
				out.println("<div id='div_invoice_details'    ></div>");
				
				//out.println("<hr/>"); 
				out.println("<br/>"); 
				
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>"); 
				out.println("<td width='100%'><div id = \"DIV_CLIENT_DRILL_DIALOG\" title = \"Dialog\">");
				out.println(" <iframe id = \"IFRAME_CLIENT_DRILL_DIALOG\" width = \"100%\" height = \"100%\" marginWidth = \"0\" marginHeight = \"0\" frameBorder = \"0\" scrolling = \"auto\" title = \"\">");
				out.println(" </iframe>"); 
				out.println(" </div>"); 
				out.println(" </td>"); 
				out.println(" <td width='100%'><div id = \"DIV_FACILITY_DRILL_DIALOG\" title = \"Dialog\">");
				out.println(" <iframe id = \"IFRAME_FACILITY_DRILL_DIALOG\" width = \"100%\" height = \"100%\" marginWidth = \"0\" marginHeight = \"0\" frameBorder = \"0\" scrolling = \"auto\" title = \"\">");
				out.println(" </iframe>"); 
				out.println(" </div>"); 
				out.println(" </td>"); 
				out.println(" </tr>");
				
				out.println("</table>");
				
				
				
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				//	out.println(" <table id=\"table_invoice_details_hid\" > </table> "); 
				
				
				
				
				out.println("</form>"); 
				
				out.println("</body>"); 
				
				
				out.println("</html>"); 
				
			}
			
			
			
			
			
			
			else if(m_chksql.trim().equals("get_inv_details")){
				
				String m_client    = req.getParameter("Client_Code");
				double m_invoice_amt =0.00;
				double m_rental_amt  =0.00;
				double m_rental_amt_future  =0.00;
				double m_odi_amt     =0.00;
				double m_other_amt   =0.00;
				double m_other_amt_future   =0.00;
				double m_total_amt   =0.00;
				double m_total_amt_future =0.00;
				double m_odi_amt_future=0.00;
				String m_sys_date="";
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO ");
				
				if(rs1.next()){
					m_invoice_amt = rs1.getDouble(1);			
					m_sys_date = rs1.getString(2);			
				}
				
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt_future = rs1.getDouble(1);			
				}
				
				
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date >SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt_future = rs1.getDouble(1);			
				}
				
				
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt_future = rs1.getDouble(1);			
				}
				
				//added by sh on 23-11-2010 
				double m_new_odi=0;
				double m_old_odi=0;
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_new_odi = rs1.getDouble(1);
					m_old_odi = m_odi_amt-m_new_odi;
					if (m_old_odi<0){
						m_old_odi=0;
					}	
				}
				//end of addition
				/*out.println("<table border='0'>");
				out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=*%><b>Amount Invoice </td>");
				out.println("  </tr>");
				out.println("</table>");
				out.println("  <hr>");
				out.println("<table border='0'>");
				out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=45%><b>Rental Invoice </td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_rental_amt)+"</td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  </tr>");
				out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=45%><b>OD Invoice </td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_odi_amt)+"</td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  </tr>");
							out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=45%><b>Other Invoice </td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_other_amt)+"</td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  </tr>");
				
				m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt);
				
										out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=45%><b>Total Outstanding</td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  <td WIDTH=25% align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=15% align=right><b></td>");
				out.println("  </tr>");
				out.println("</table>");							
				
				*/
				
				out.println("<table border='0'>");
				out.println("  <tr WIDTH=100%>");
				out.println("  <td WIDTH=*%><b>Amount Invoice </td>");
				out.println("  </tr>");
				out.println("</table>");
				out.println("  <hr>");
				out.println("<table border='0' WIDTH=60% class='table' >");
				out.println("  <tr >");
				out.println("  <td WIDTH=15% ><b>&nbsp;</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Arr as at - "+m_sys_date+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Next Due</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>Total</td>");
				out.println("  </tr>");
				
				out.println("  <tr >");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\" ><b>Rental Invoice </td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_rental_amt)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_rental_amt_future)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_rental_amt+m_rental_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr >");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\"><b>OD Invoice </td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_odi_amt)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_odi_amt_future)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_odi_amt+m_odi_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr >");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFF00\" ><b>Other Invoice </td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC66FF\" align=right><b>"+nf.format(m_other_amt)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CC99FF\" align=right><b>"+nf.format(m_other_amt_future)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCFF\" align=right><b>"+nf.format(m_other_amt+m_other_amt_future)+"</td>");
				out.println("  </tr>");
				
				
				
				
				m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt);
				m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future);
				
				out.println("  <tr >");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" ><b>Total Outstanding</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt_future)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#CCCCCC\" align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
				out.println("  </tr>");
				
				//added by sh on 23-11-2010
				out.println("  <tr >");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" ><b>New ODI</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" align=right><b>"+nf.format(m_new_odi)+"</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" ><b>OLD ODI</td>");
				out.println("  <td WIDTH=15% bgcolor=\"#FFFFFF\" align=right><b>"+nf.format(m_old_odi)+"</td>");
				out.println("  </tr>");
				
				
				out.println("</table>");							
				
				
			}		
			
			else if(m_chksql.trim().equals("get_contract_det")){
				
				String m_client    = req.getParameter("Client_Code");
				double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
				double m_othr_rec_bal   = new Double(req.getParameter("Other_Amount")).doubleValue();
				String m_type      = req.getParameter("Type");
				
				
				
				
				
				double m_rec_tot   = 0;
				double m_rec_tot2   = 0; 
				double m_rec_bal2   = 0;
				double m_tot_amt = 0; //added by nuwan de silva
				
				String m_finance_num ="";
				
				if(m_type.equals("NEW")){
					rs1 = stmt1.executeQuery (" SELECT FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM "+
						" ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
						//" A.ACTIVE_STATUS <> 'C'  "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" AND A.INVOICE_TYPE IN ('INV_GENER','ODI') "+
						" GROUP BY FINANCE_NO ) "+
						
						" UNION ALL "+
						" (SELECT a.FIN_NO FINANCE_NO, "+
						" 0 INV_AMOUNT, "+
						" 0 INV_BALANCE_AMOUNT, "+
						" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
						" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE  CLIENT_CODE='"+m_client+"' AND "+
						" A.INVOICE_NO=B.INVOICE_NO AND "+
						" ODI_BAL_AMOUNT>0 "+
						" GROUP BY a.FIN_NO )) "+
						" GROUP BY  FINANCE_NO ");     
					
					
					boolean more1 = rs1.next();	
					int i = 0;
					int k = 0;
					
					double m_inv_bal = 0;
					double m_odi_bal = 0;
					double m_tot_bal = 0;
					
					double m_inv_bal2 = 0;
					double m_tot_val2 = 0;
					
					double m_inv_val = 0;
					double m_odi_val = 0;
					double m_tot_val = 0;
					//double m_tot_amt = 0;
					
					
					
					
					if(more1) {	
						out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
						out.println("  <td WIDTH=15%>Finance No</td>");
						out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
						out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
						out.println("  <td WIDTH=10% align=center>Status</td></tr>");
						
						/*double m_inv_bal = 0;
						double m_odi_bal = 0;
						double m_tot_bal = 0;
						
						double m_inv_bal2 = 0;
						double m_tot_val2 = 0;
						
						double m_inv_val = 0;
						double m_odi_val = 0;
		double m_tot_val = 0;
						double m_tot_amt = 0;
						*/
						
					}
					while(more1) {
						m_finance_num = rs1.getString(1);
						out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO1_"+i+"\">");
						out.println("  <input type=hidden NAME=\"hid_finance_no_"+i+"\"  value=\""+rs1.getString(1)+"\"> ");
						out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
						out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
						out.println("  <td align=right ><input type=text name=\"ODI_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+i+"\" value="+rs1.getDouble(5)+"></td>");
						out.println("  <td align=right ><input type=text name=\"INV_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+i+"\" value="+rs1.getDouble(3)+">");
						//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
						//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
						//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
						out.println("  <input type=hidden name=\"allo_no1_"+i+"\" value=\"\"><input type=hidden name=\"hid_main_cnt_"+i+"\" value=\""+i+"\"></td>");
						
						m_inv_val=rs1.getDouble(3);
						m_odi_val=rs1.getDouble(5);
						
						
						
						m_inv_bal  = m_inv_bal+rs1.getDouble(3); 
						m_odi_bal  = m_odi_bal+rs1.getDouble(5); //zzzzzzzzz
						m_tot_bal=m_inv_bal+m_odi_bal;
						
						if((m_rec_bal - m_tot_amt) > (m_inv_val + m_odi_val)) {
							out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+m_odi_val+" ,'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"   disabled></td>"); //disabled
							out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(m_inv_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+m_inv_val+" ,'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\"  disabled></td>"); //disabled
							m_tot_val2= (m_inv_val + m_odi_val);
							m_tot_amt = m_tot_amt + (m_inv_val + m_odi_val);	
							
							if((m_rec_bal - m_tot_amt)>0){
								out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
							}else{
								out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
							}
							out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
							
						}else{
							// out.println("2222====>"+(m_rec_bal - m_tot_amt));
							if((m_rec_bal - m_tot_amt)>0){												
								if((m_rec_bal - m_tot_amt)>m_odi_val){
									out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+m_odi_val+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled></td>"); //disabled
									out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(((m_rec_bal - m_tot_amt) - m_odi_val))+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+((m_rec_bal - m_tot_amt) - m_odi_val)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\" disabled ></td>"); //disabled
									m_tot_val2= m_odi_val + ((m_rec_bal - m_tot_amt) - m_odi_val);
									m_tot_amt = m_tot_amt + (m_inv_val + m_odi_val);	
									
									if((m_rec_bal - m_tot_amt)>0){
										out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
									}else{
										out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
									}												
									
									out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\"  onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									
								}else{
									out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+(m_rec_bal - m_tot_amt)+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled></td>"); //disabled
									out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+(0.00)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",'30')\"  disabled ></td>"); //disabled
									m_tot_val2= (m_rec_bal - m_tot_amt);
									m_tot_amt = m_tot_amt + (m_rec_bal - m_tot_amt);
									
									
									if((m_rec_bal - m_tot_amt)>0){
										out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
									}else{
										out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
									}	
									
									out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\"  onclick=check_aloc_status_new(\""+i+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
									
								}					
							}else{
								out.println("<td align=right><input type=text name=\"Text_odi_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_odi_"+i+","+(0.00)+",'ODI',"+i+"),format_number(document.Form1.Text_odi_sett_amount"+i+",30)\"  disabled ></td>"); //disabled
								out.println("<td align=right><input type=text name=\"Text_inv_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_aloc_amt(document.Form1.hid_inv_"+i+","+(0.00)+",'INV',"+i+"),format_number(document.Form1.Text_inv_sett_amount"+i+",30)\"  disabled ></td>"); //disabled
								m_tot_amt = m_tot_amt + 0.00;
								m_tot_val2=0.00;
								
								if((m_rec_bal - m_tot_amt)>0){
									out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}else{
									out.println("<td align=right><input type=text name=\"Text_balance_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}	
								
								out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status_new(\""+i+"\")  value=\"NO\" ></td>"); //check_aloc_status(\""+i+"\")
								
							}
						}
						
						out.println("  </tr>");
						
						
						i = i+1;
						more1 = rs1.next();
						
					}
					
					out.println("<input type=hidden name=hid_cntract_cnt  value="+i+"></table>");
					
					
					
					rs1 = stmt1.executeQuery ("	SELECT FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+ //comment by nuwan de silva 21-05-2008
						//" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) "+
						" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
						" AND "+
						//" A.ACTIVE_STATUS <> 'C' "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" GROUP BY FINANCE_NO ) ) "+
						" GROUP BY  FINANCE_NO ");      
					
					
					boolean more_other = rs1.next();	
					int m = 0;
					// int k = 0;
					
					if(more_other) {	
						out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
						out.println("  <td WIDTH=15%>Finance No</td>");
						out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
						out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
						out.println("  <td WIDTH=10% align=center>Status</td></tr>");
						double m_other_inv_bal = 0;
						double m_other_odi_bal = 0;
						double m_other_tot_bal = 0;
						
						double m_other_inv_bal2 = 0;
						double m_other_tot_val2 = 0;
						
						double m_other_inv_val = 0;
						double m_other_odi_val = 0;
						double m_other_tot_val = 0;
						double m_other_tot_amt = 0;
						
						
						
						while(more_other) {
							
							
							m_finance_num = rs1.getString(1);
							
							out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"OTHR_ALLO_NO1_"+m+"\">");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
							out.println("  <input type=hidden NAME=\"hid_other_finance_no_"+m+"\"  value=\""+rs1.getString(1)+"\"> ");
							out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
							out.println("  <td align=right ><input type=text name=\"OTHR_ODI_VAL_"+m+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+m+"\" value="+rs1.getDouble(5)+" ></td>");
							out.println("  <td align=right ><input type=text name=\"OTHR_INV_VAL_"+m+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+m+"\" value="+rs1.getDouble(3)+">");
							//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
							out.println("  <input type=hidden name=\"other_allo_no1_"+m+"\" value=\"\"><input type=hidden name=\"other_hid_main_cnt_"+m+"\" value=\""+m+"\">"+m+"</td>");
							
							m_other_inv_val=rs1.getDouble(3);
							m_other_odi_val=rs1.getDouble(5);
							
							
							
							m_other_inv_bal  = m_other_inv_bal+rs1.getDouble(3); 
							m_other_odi_bal  = m_other_odi_bal+rs1.getDouble(5); //zzzzzzzzz
							m_other_tot_bal=m_other_inv_bal+m_other_odi_bal;
							
							if((m_othr_rec_bal - m_other_tot_amt) > (m_other_inv_val + m_other_odi_val)) {
								out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" disabled onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+m_other_odi_val+" ,'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  ></td>");
								out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(m_other_inv_val)+"\" class=\"txt_input2\" disabled onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+m_other_inv_val+" ,'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\"  ></td>");
								m_other_tot_val2= (m_other_inv_val + m_other_odi_val);
								m_other_tot_amt = m_other_tot_amt + (m_other_inv_val + m_other_odi_val);	
								
								
								if((m_othr_rec_bal - m_other_tot_amt)>0){
									out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}else{
									out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
								}	
								
								out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\" onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
								
							}else{
								//out.println("2222====>"+(m_othr_rec_bal - m_other_tot_amt));
								if((m_othr_rec_bal - m_other_tot_amt)>0){												
									if((m_othr_rec_bal - m_other_tot_amt)>m_other_odi_val){
										out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+m_other_odi_val+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  disabled ></td>");
										out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val))+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\" disabled ></td>");
										m_other_tot_val2= m_other_odi_val + ((m_othr_rec_bal - m_other_tot_amt) - m_other_odi_val);
										m_other_tot_amt = m_other_tot_amt + (m_other_inv_val + m_other_odi_val);	
										
										
										if((m_othr_rec_bal - m_other_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\"  disabled></td>");
										}else{
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
										}												
										
										out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\"  onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
										
									}else{
										out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+(m_othr_rec_bal - m_other_tot_amt)+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\"  disabled ></td>");
										out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+(0.00)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",'30')\"  disabled ></td>");
										m_other_tot_val2= (m_othr_rec_bal - m_other_tot_amt);
										m_other_tot_amt = m_other_tot_amt + (m_othr_rec_bal - m_other_tot_amt);
										
										
										if((m_othr_rec_bal - m_other_tot_amt)>0){
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\"  disabled></td>");
										}else{
											out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
										}	
										
										out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\"  onclick=check_aloc_status_new_other(\""+m+"\")  value=\"YES\" checked></td>"); //check_aloc_status(\""+i+"\")
										
									}					
								}else{
									out.println("<td align=right><input type=text name=\"other_Text_odi_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_odi_"+m+","+(0.00)+",'ODI',"+m+"),format_number(document.Form1.other_Text_odi_sett_amount"+m+",30)\" disabled></td>");
									out.println("<td align=right><input type=text name=\"other_Text_inv_sett_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onBlur=\"check_other_aloc_amt(document.Form1.hid_inv_"+m+","+(0.00)+",'INV',"+m+"),format_number(document.Form1.other_Text_inv_sett_amount"+m+",30)\" disabled></td>");
									m_other_tot_amt = m_other_tot_amt + 0.00;
									m_other_tot_val2=0.00;
									
									if((m_othr_rec_bal - m_other_tot_amt)>0){
										out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format((m_othr_rec_bal - m_other_tot_amt))+"\" class=\"txt_input2\" disabled></td>");
									}else{
										out.println("<td align=right><input type=text name=\"other_Text_balance_amount"+m+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" disabled></td>");
									}	
									
									out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"other_Text_standard_chk"+m+"\" onclick=check_aloc_status_new_other(\""+m+"\")  value=\"NO\" ></td>"); //check_aloc_status(\""+i+"\")
									
								}
							}
							
							out.println("  </tr>");
							
							
							m = m+1;
							more_other = rs1.next();
							
						}
						out.println("<input type=hidden name=hid_other_cntract_cnt  value="+m+"></table>");
						
						/*out.println(" <br><hr><br>");	
						out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
							out.println("  <td WIDTH=80%>&nbsp</td>");
							out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='show_inv_det();' style='width: 130px'></td>");
							out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
							out.println(" </tr></table>");		
							out.println(" <br><hr>");					
							*/	
						
						
						
					}else{
						
						//out.println("<input type=hidden name=\"hid_cntract_cnt\" value=\"0\">");
						out.println("<input type=hidden name=hid_other_cntract_cnt  value=\"0\">");
						
						/*out.println(" <br><hr><br>");	
					out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
						out.println("  <td WIDTH=80%>&nbsp</td>");
						out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='show_inv_det();' style='width: 130px'></td>");
						out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
						out.println(" </tr></table>");		
						out.println(" <br><hr>");	
				*/
						
					}
					
					
					//===================================================================================
					rs_act = stmt_act.executeQuery ("	SELECT FINANCE_NO, "+
						" 0, "+
						" 0, "+
						" 0, "+
						" 0 ,"+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
						" WHERE APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL') "+
						" AND client_code='"+m_client+"' "+
						" ");
					
					/*" AND FINANCE_NO NOT IN "+
					" (SELECT DISTINCT FINANCE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" where client_code='"+m_client+"' "+
					" )");*/
					
					
					
					
					/*rs_act = stmt_act.executeQuery (" select FINANCE_NO,BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') REG_NO  "+
					" from "+
					" (SELECT FINANCE_NO,  "+
					" 0 BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') REG_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
					" WHERE APPLICATION_STATUS='ACTIVATED'  "+
					" AND client_code='"+m_client+"'  "+
					" AND FINANCE_NO NOT IN  "+
					" (SELECT DISTINCT FINANCE_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" where client_code='"+m_client+"'  "+
					" 	) "+
					" union "+
					" SELECT DISTINCT FINANCE_NO,  "+
					" SUM(BALANCE_TO_BE_RECEIVED) BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')   "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
					" where client_code='"+m_client+"'  "+
					" GROUP BY FINANCE_NO  "+
					
					" UNION  "+
					" SELECT DISTINCT FINANCE_NO,   "+
					" 0 BAL,0,0,0,NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-')    "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					" where client_code='"+m_client+"' "+
					" AND INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
					" GROUP BY FINANCE_NO )"+
					
					" where  BAL = 0 ");
					*/
					
					boolean more_rs_act=rs_act.next();
					int act=0;
					
					if(more_rs_act) {	
						out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
						out.println("  <td WIDTH=15%>Finance No</td>");
						out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
						out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
						out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
						out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
						out.println("  <td WIDTH=10% align=center>Status</td></tr>");
					}
					while(more_rs_act){
						
						
						out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ACTIVATE_OTHR_ALLO_NO1_"+act+"\">");
						out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs_act.getString(1)+"')\" ><u>"+rs_act.getString(1)+"</u></td>"); 
						out.println("  <input type=hidden NAME=\"hid_activate_finance_no_"+act+"\"  value=\""+rs_act.getString(1)+"\"> ");
						out.println("  <td align=left  onClick=\"\" >"+rs_act.getString(6)+"</td>");
						out.println("  <td align=right ><input type=text name=\"activate_ODI_VAL_"+act+"\" disabled value=\""+nf.format(rs_act.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"activate_hid_odi_"+act+"\" value="+rs_act.getDouble(5)+"></td>");
						out.println("  <td align=right ><input type=text name=\"activate_INV_VAL_"+act+"\" disabled value=\""+nf.format(rs_act.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"activate_hid_inv_"+act+"\" value="+rs_act.getDouble(3)+">");
						out.println("  <input type=hidden name=\"activate_allo_no1_"+act+"\" value=\"\"><input type=hidden name=\"activate_hid_main_cnt_"+act+"\" value=\""+act+"\"></td>");
						out.println("<td align=right><input type=text name=\"activate_Text_odi_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_number(document.Form1.activate_Text_odi_sett_amount"+act+",30) \" onblur=\"\" disabled ></td>");
						
						/*if((m_rec_bal - m_tot_amt)>0){
						out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"check_aloc_amt_activate(document.Form1.activate_hid_inv_"+act+","+(0.00)+",'INV',"+act+")\"   disabled></td>");  //
						}else{
						out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
						}	
						*/
						
						m_tot_amt = m_tot_amt + (m_rec_bal - m_tot_amt);
						out.println("<td align=right><input type=text name=\"activate_Text_inv_sett_amount"+act+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\"   onBlur=\"check_aloc_amt_activate(document.Form1.activate_hid_inv_"+act+","+(0.00)+",'INV',"+act+")\"   disabled onBlur=\"format_number(document.Form1.activate_Text_inv_sett_amount"+act+",30)\" ></td>"); //onchange=\"check_act_aloc_amt(document.Form1.activate_hid_inv_"+act+","+(0.00)+" ,'INV',"+act+")
						out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"    value=\""+nf.format(0.00)+"\" class=\"txt_input2\" disabled></td>");
						
						/*if((m_rec_bal - m_tot_amt)>0){
						out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"  value=\""+nf.format((m_rec_bal - m_tot_amt))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
						}else{
						out.println("<td align=right><input type=text name=\"activate_Text_balance_amount"+act+"\"  value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
						}	
		*/
						
						out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"activate_Text_standard_chk"+act+"\"  onclick=check_aloc_status_new_activated(\""+act+"\")  value=\"YES\" checked></td>");  
						act=act+1;
						more_rs_act=rs_act.next();
					}	
					
					out.println("<input type=hidden name=hid_activate_cntract_cnt  value="+act+"></table>");
					
					//===================================================================================
					
					out.println(" <br><hr><br>");	
					out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
					out.println("  <td WIDTH=80%>&nbsp</td>");
					out.println("  <td WIDTH=25%><input type=button name=show_inv value=\"Allocate to Invoices\" class=mainbut onclick='validate_back_dating_request()' style='width: 130px'></td>");
					out.println("  <td WIDTH=25%><input type=button name=remove_inv value=\"Remove Invoices\" class=mainbut onclick='remv_inv_det();' style='width: 130px'</td>");				
					out.println(" </tr></table>");		
					out.println(" <br><hr>");	
					
					
					//} 
					
					
					
					
					
				}
				
				
				
			}else if(m_chksql.trim().equals("get_contract")){
				
				String m_client    = req.getParameter("Client_Code");
				double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
				double m_Other_Amt = new Double(req.getParameter("Other_Amount")).doubleValue();
				String m_type      = req.getParameter("Type");
				
				double m_rec_tot   = 0;
				double m_rec_tot2   = 0; 
				double m_rec_bal2   = 0;
				String m_finance_num ="";
				
				if(m_type.equals("NEW")){
					
					
					rs1 = stmt1.executeQuery (" SELECT FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM "+
						" ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT  "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
						//" A.ACTIVE_STATUS <> 'C' "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" AND A.INVOICE_TYPE IN ('INV_GENER','ODI')  "+ //ADDED BY NUWAN DE SILVA
						" GROUP BY FINANCE_NO ) "+
						" UNION ALL "+
						" (SELECT a.FIN_NO FINANCE_NO, "+
						" 0 INV_AMOUNT, "+
						" 0 INV_BALANCE_AMOUNT, "+
						" SUM(ODI_CAL_AMOUNT) ODI_AMOUNT, "+
						" SUM(ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE  CLIENT_CODE='"+m_client+"' AND "+
						" A.INVOICE_NO=B.INVOICE_NO AND "+
						" B.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+
						" ODI_BAL_AMOUNT>0 "+
						" GROUP BY a.FIN_NO )) "+
						" GROUP BY  FINANCE_NO ");     
					
					
					boolean more1 = rs1.next();	
					int i = 0;
					int k = 0;
					int h = 0;	
					int d = 0;	
					
					double m_inv_bal = 0;
					double m_odi_bal = 0;
					double m_tot_bal = 0;
					
					double m_inv_bal2 = 0;
					double m_tot_val2 = 0;
					
					double m_inv_val = 0;
					double m_odi_val = 0;
					double m_tot_val = 0;
					double m_tot_amt = 0;
					int j=0;
					
					
					if(more1) {	
						/*		double m_inv_bal = 0;
								double m_odi_bal = 0;
								double m_tot_bal = 0;
								
								double m_inv_bal2 = 0;
								double m_tot_val2 = 0;
								
								double m_inv_val = 0;
								double m_odi_val = 0;
				double m_tot_val = 0;
								double m_tot_amt = 0;
								int j=0;
						*/											
						
						while(more1) {
							
							out.println("<table border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
							out.println("  <td WIDTH=15%>Finance No</td>");
							out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
							out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
							out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
							out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
							out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
							out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
							out.println("  <td WIDTH=10% align=center>Status</td></tr>");				
							
							m_finance_num = rs1.getString(1);
							
							out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO1_"+i+"\">");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>"); 
							out.println("  <td align=left  onClick=\"\" >"+rs1.getString(6)+"</td>");
							out.println("  <td align=right ><input type=text name=\"ODI_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_odi_"+i+"\" value="+rs1.getDouble(5)+"></td>");
							out.println("  <td align=right ><input type=text name=\"INV_VAL_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_inv_"+i+"\" value="+rs1.getDouble(3)+">");
							//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
							out.println("  <input type=hidden name=\"allo_no1_"+i+"\" value=\"\"><input type=hidden name=\"hid_main_cnt_"+i+"\" value=\""+i+"\"></td>");
							
							m_inv_val=rs1.getDouble(3);
							m_odi_val=rs1.getDouble(5);
							
							m_inv_bal  = m_inv_bal+rs1.getDouble(3); 
							m_odi_bal  = m_odi_bal+rs1.getDouble(5); 
							m_tot_bal=m_inv_bal+m_odi_bal;
							
							out.println("<td align=right><input type=text name=\"Text_odi_sett_amount2"+i+"\" value=\""+nf.format(m_odi_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
							out.println("<td align=right><input type=text name=\"Text_inv_sett_amount2"+i+"\" value=\""+nf.format(m_inv_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
							out.println("<td align=right><input type=text name=\"Text_balance_amount2"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_standard_chk"+i+"\" onclick=check_aloc_status(\""+i+"\")  value=\"YES\" checked></td>"); //
							out.println("  </tr></table>");
							
							out.println("<table WIDTH=90% border='0'><tr class=pdn_txtpos2 WIDTH=100%>");
							out.println("  <td WIDTH=15% align=center>Invoice No </td>");
							out.println("  <td WIDTH=10% align=center>Invoice Type</td>");
							out.println("  <td WIDTH=15% align=right>Invoiced Date</td>");
							out.println("  <td WIDTH=15% align=right>Due Date</td>");
							out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
							out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
							out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
							//out.println("  <td WIDTH=15% align=center>Balance</td>");
							out.println("  <td WIDTH=10% align=center>Status</td></tr>");
							
							if(m_tot_val2>0){
								m_rec_bal2 = m_tot_val2;
							}else{
								m_rec_bal2 = 0.00;
							}
							
							//out.println("m_rec_bal2"+m_rec_bal2+"m_tot_val2"+m_tot_val2);
							
							rs2 = stmt2.executeQuery ("SELECT INVOICE_NO,VAL_DATE, "+
								" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
								" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
								" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
								" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
								" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
								" INVOICE_TYPE,VALUE_DATE, "+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) INV_DESCR "+
								" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
								" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
								" VAT_AMOUNT,FINANCE_NO, "+
								" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
								" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
								" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
								" INVOICE_TYPE,VALUE_DATE "+
								" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
								" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
								" WHERE CLIENT_CODE='"+m_client+"' AND "+
								" BALANCE_TO_BE_RECEIVED>0 AND "+
								" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
								" A.INVOICE_TYPE IN ('INV_GENER','ODI') AND "+ //ADDED BY NUWAN DE SILVA 
								//" A.ACTIVE_STATUS <> 'C' AND "+
								" A.ACTIVE_STATUS = 'Y'  AND "+
								" FINANCE_NO = '"+m_finance_num+"' "+
								" UNION ALL "+
								" SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
								" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
								" 0,a.FIN_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
								" CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI' INVOICE_TYPE,ODI_DATE "+
								" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
								" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
								" WHERE  CLIENT_CODE='"+m_client+"' AND "+
								" A.INVOICE_NO=B.INVOICE_NO AND "+
								" a.FIN_NO = '"+m_finance_num+"' AND "+
								" ODI_BAL_AMOUNT>0 ) A, "+
								" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
								" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
								" AND A.INVOICE_TYPE IN ('INV_GENER','ODI') "+
								" ORDER BY ORDER_NO,VALUE_DATE ");																	
							
							//int j=0;
							boolean more2 = rs2.next();	
							
							double m_bal_amt = 0;
							
							while(more2) {
								//out.println("m_inv_bal2" +m_rec_bal2);
								out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+k+"\">");
								//out.println("  <td align=left ><input type=text name=\"INV_NO_"+k+"\" disabled value=\""+rs2.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
								out.println("  <td align=center style= cursor:hand;  onClick=\"\" ><u>"+rs2.getString(1)+"</u></td>"); 
								out.println("  <td align=center style= cursor:hand;  onClick=\"\" >"+rs2.getString(19)+"</td>");
								out.println("  <td align=right ><input type=text name=\"V_DATE_"+k+"\" disabled value=\""+rs2.getString(2)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
								out.println("  <td align=right ><input type=text name=\"D_DATE_"+k+"\" disabled value=\""+rs2.getString(8)+"\" class=\"txt_input2\">");
								out.println("  <td align=right ><input type=text name=\"INV_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(3))+"\" class=\"txt_input2\">");
								out.println("  <td align=right ><input type=text name=\"BAL_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(4))+"\" class=\"txt_input2\">");
								out.println("  <input type=hidden name=\"allo_no_"+k+"\" value=\"\">");
								out.println("  <input type=hidden name=\"hid_contract_no_"+k+"\" value=\""+rs2.getString(7)+"\">"); //added by nuwan de silva on 16-06-2008
								out.println("  <input type=hidden name=\"INV_NO_"+k+"\" value=\""+rs2.getString(1)+"\"></td>");
								
								
								m_inv_bal2  = m_inv_bal2+rs2.getDouble(4); 
								
								if(m_rec_tot2<m_inv_bal2){
									if(m_rec_bal2>= (m_inv_bal2-m_rec_tot2)){
										//out.println("m_inv_bal" +m_rec_bal2);
										out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format((m_inv_bal2-m_rec_tot2))+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled ></td>");  //chk_bal('"+k+"')
										out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
										//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>"); 
										out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
										
										m_rec_bal2 = m_rec_bal2-(m_inv_bal2-m_rec_tot2);
										m_rec_tot2 = m_rec_tot2 +(m_inv_bal2-m_rec_tot2);
									}else{
										if(m_rec_bal2>0){ 
											//out.println("m_inv_bal" +m_rec_bal2);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\"  disabled></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
											
											m_rec_tot2 = m_rec_tot2+m_rec_bal2;
											m_rec_bal2 = 0;
										}else{
											//out.println("m_inv_bal" +m_rec_bal2);
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\" >");
											
										}	
									}
								}else{
									//out.println("m_inv_bal2" +m_rec_bal2);
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_batch_val("+i+","+k+")\" disabled></td>"); //chk_bal('"+i+"')
									out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
									//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\">");
								}
								out.println("<input type=hidden name=hid_othr_invoice_no_"+k+"  value="+rs2.getString(17)+"></tr>");
								
								//----- END INNER LOOP --------------------------------------------//
								
								j = j+1;
								k = k+1; 
								more2 = rs2.next();
							}
							
							
							
							out.println("<tr></tr>");
							// out.println("j ==== value"+j+"k ==== value"+k);	
							
							out.println(" <input type=hidden name=hid_invoice_cnt_"+i+"  value="+j+"><input type=hidden name=hid_invoice_tot_cnt_"+i+"  value="+k+"></table>");
							
							//added by nuwan de silva on 21-05-2008 --------
							more1 = rs1.next();
							i=i+1;
						}
						
					}
					//added by nuwan de silva on 21-05-2008 --------
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/* OTHER INVOICE ALLOCATION */
					
					
					rs12 = stmt12.executeQuery (" SELECT FINANCE_NO, "+
						" SUM(INV_AMOUNT), "+
						" SUM(INV_BALANCE_AMOUNT), "+
						" SUM(ODI_AMOUNT), "+
						" SUM(ODI_BAL_AMOUNT), "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						" FROM ((SELECT FINANCE_NO, "+
						" SUM(TOTAL_AMOUNT_CURR) INV_AMOUNT, "+
						" SUM(BALANCE_TO_BE_RECEIVED)  INV_BALANCE_AMOUNT, "+
						" 0 ODI_AMOUNT, "+
						" 0 ODI_BAL_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						//	" FINANCE_NO = '"+m_finance_num+"' AND "+ //comment by nuwan de silva on 21-05-2008
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						//" A.INVOICE_TYPE NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) "+
						" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') "+
						" AND "+
						//" A.ACTIVE_STATUS <> 'C' "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" GROUP BY FINANCE_NO ) ) "+
						" GROUP BY  FINANCE_NO ");
					
					
					boolean more_othr_det = rs12.next();
					
					double m_other_inv_val =0.00;
					double m_other_odi_val =0.00;
					
					double m_other_inv_bal =0.00;
					double m_other_odi_bal =0.00; 
					double m_other_tot_bal =0.00;
					
					if(more_othr_det){
						
						while(more_othr_det){		
							
							out.println("<table border='0'><tr class=tr_input1 WIDTH=100%>");
							out.println("  <td WIDTH=15%>Finance No</td>");
							out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
							out.println("  <td WIDTH=12% align=right>ODI Balance Amount</td>");
							out.println("  <td WIDTH=12% align=right>Invoice Balance Amount</td>");
							out.println("  <td WIDTH=12% align=right>ODI Allocated Amount</td>");
							out.println("  <td WIDTH=12% align=right>Invoice Allocated Amount</td>");
							out.println("  <td WIDTH=12% align=right>Balance Receipt Amount</td>");
							out.println("  <td WIDTH=10% align=center>Status</td></tr>");		
							
							out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"OTHR_ALLO_NO1_"+h+"\">");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs12.getString(1)+"')\" ><u>"+rs12.getString(1)+"</u></td>"); 
							out.println("  <td align=left  onClick=\"\" >"+rs12.getString(6)+"</td>");
							out.println("  <td align=right ><input type=text name=\"OTHR_ODI_VAL_"+h+"\" disabled value=\""+nf.format(rs12.getDouble(5))+"\" class=\"txt_input2\" style=\"width: 100px\"><input type=hidden name=\"hid_othr_odi_"+h+"\" value="+rs12.getDouble(5)+"></td>");
							out.println("  <td align=right ><input type=text name=\"OTHR_INV_VAL_"+h+"\" disabled value=\""+nf.format(rs12.getDouble(3))+"\" class=\"txt_input2\"><input type=hidden name=\"hid_othr_inv_"+h+"\" value="+rs12.getDouble(3)+">");
							//out.println("  <td align=right ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
							//out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\"-\" class=\"txt_input2\">");
							out.println("  <input type=hidden name=\"othr_allo_no1_"+h+"\" value=\"\"><input type=hidden name=\"hid_othr_main_cnt_"+h+"\" value=\""+h+"\"></td>");
							
							m_other_inv_val=rs12.getDouble(3);
							m_other_odi_val=rs12.getDouble(5);
							
							
							
							m_other_inv_bal  = m_other_inv_bal+rs12.getDouble(3); 
							m_other_odi_bal  = m_other_odi_bal+rs12.getDouble(5); 
							m_other_tot_bal = m_other_inv_bal+m_other_odi_bal;
							
							
							out.println("<td align=right><input type=text name=\"Text_othr_odi_sett_amount2"+h+"\" value=\""+nf.format(m_other_odi_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
							out.println("<td align=right><input type=text name=\"Text_othr_inv_sett_amount2"+h+"\" value=\""+nf.format(m_other_inv_val)+"\" class=\"txt_input2\" onchange=\"\" onblur=\"\" disabled></td>");
							
							out.println("<td align=right><input type=text name=\"Text_othr_balance_amount2"+h+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");
							
							out.println("<td align=center><INPUT TYPE=\"checkbox\" name=\"Text_othr_standard_chk"+h+"\" onclick=check_aloc_status(\""+h+"\")  value=\"YES\" checked></td>"); //
							
							out.println("</tr></table>");
							
							//out.println("---h--++"+h); 
							h=h+1;
							m_finance_num = rs12.getString(1);
							
							//	}
							
							rs2 = stmt2.executeQuery ("	SELECT INVOICE_NO,VAL_DATE, "+
								" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
								" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
								" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
								" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
								" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
								" INVOICE_TYPE,VALUE_DATE, "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE) "+
								" FROM "+
								" (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
								" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
								" VAT_AMOUNT,FINANCE_NO, "+
								" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
								" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
								" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
								" INVOICE_TYPE,VALUE_DATE "+
								" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
								" WHERE CLIENT_CODE='"+m_client+"' AND "+
								" BALANCE_TO_BE_RECEIVED>0 AND "+
								//" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD) AND "+
								" A.INVOICE_TYPE NOT IN ('INV_GENER','ODI') AND "+
								//" A.ACTIVE_STATUS <> 'C' AND "+
								" A.ACTIVE_STATUS = 'Y'  AND "+
								" FINANCE_NO = '"+m_finance_num+"' "+
								" ORDER BY VALUE_DATE) ");
							
							
							j=0;
							boolean more_othr = rs2.next();	
							
							
							
							if(more_othr){
								out.println("<table WIDTH=90% border='0'><tr class=tr_input1 WIDTH=100%>");
								out.println("  <td WIDTH=15% align=center>Invoice No </td>");
								out.println("  <td WIDTH=10% align=center>Invoice Type</td>");
								out.println("  <td WIDTH=15% align=right>Invoiced Date</td>");
								out.println("  <td WIDTH=15% align=right>Due Date</td>");
								out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
								out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
								out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
								//out.println("  <td WIDTH=15% align=center>Balance</td>");
								out.println("  <td WIDTH=10% align=center>Status</td></tr>");
								
							}
							
							
							if(m_tot_val2>0){
								m_rec_bal2 = m_tot_val2;
							}else{
								m_rec_bal2 = 0.00;
							}										
							
							
							int t = k;
							int n = 0;
							
							double m_othr_bal_amt = 0;
							
							while(more_othr) {
								out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+k+"\">");
								//out.println("  <td align=left ><input type=text name=\"INV_NO_"+k+"\" disabled value=\""+rs2.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
								out.println("  <td align=center style= cursor:hand;  onClick=\"\" ><u>"+rs2.getString(1)+"</u></td>"); 
								out.println("  <td align=center style= cursor:hand;  onClick=\"\" >"+rs2.getString(19)+"</td>");
								out.println("  <td align=right ><input type=text name=\"V_DATE_"+k+"\" disabled value=\""+rs2.getString(2)+"\" class=\"txt_input2\" style=\"width: 100px\"></td>");
								out.println("  <td align=right ><input type=text name=\"D_DATE_"+k+"\" disabled value=\""+rs2.getString(8)+"\" class=\"txt_input2\">");
								out.println("  <td align=right ><input type=text name=\"INV_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(3))+"\" class=\"txt_input2\">");
								out.println("  <td align=right ><input type=text name=\"BAL_AM_"+k+"\" disabled value=\""+nf.format(rs2.getDouble(4))+"\" class=\"txt_input2\">");
								out.println("  <input type=hidden name=\"allo_no_"+k+"\" value=\"\">");
								out.println("  <input type=hidden name=\"hid_contract_no_"+k+"\" value=\""+rs2.getString(7)+"\">"); //added by nuwan de silva on 16-06-2008
								if(n==0){
									out.println(" <input type=hidden name=\"start_val_"+h+"\" value=\""+k+"\">");
								}else{
									//out.println(" "+h+" <input type=hidden name=\"start_val_"+h+"\" value=\"0\">");
								}
								
								out.println("  <input type=hidden name=\"INV_NO_"+k+"\" value=\""+rs2.getString(1)+"\"></td>");
								
								
								m_inv_bal2  = m_inv_bal2+rs2.getDouble(4); 
								
								if(m_rec_tot2<m_inv_bal2){
									if(m_rec_bal2>= (m_inv_bal2-m_rec_tot2)){
										out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format((m_inv_bal2-m_rec_tot2))+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");  //chk_bal('"+k+"') //uuuuuuuuu
										out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
										//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>"); 
										out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
										
										m_rec_bal2 = m_rec_bal2-(m_inv_bal2-m_rec_tot2);
										m_rec_tot2 = m_rec_tot2 +(m_inv_bal2-m_rec_tot2);
									}else{
										if(m_rec_bal2>0){ 
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"YES\" checked>");
											
											m_rec_tot2 = m_rec_tot2+m_rec_bal2;
											m_rec_bal2 = 0;
										}else{
											out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(m_rec_bal2)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled ></td>");//chk_bal('"+i+"')
											out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
											//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
											out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\" >");
											
										}	
									}
								}else{
									
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_othr_batch_val("+h+","+k+")\" disabled></td>"); //chk_bal('"+i+"')
									out.println("  <input type=hidden name=\"inv_alocate_amt_"+k+"\" value=\"\">");
									//out.println("<td align=right><input type=text name=\"Text_bal_amount"+k+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"\" disabled ></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+k+"\" onclick=check_status_inv(\""+k+"\") value=\"NO\">");
								}
								out.println("<input type=hidden name=hid_othr_invoice_no_"+k+"  value="+rs2.getString(17)+"></tr>");
								
								//----- END INNER LOOP --------------------------------------------//
								
								
								
								j = j+1;
								k = k+1; 
								n = n+1;
								more_othr = rs2.next();
							}	                        //
							
							if(d!=h){ /*ADDED BY CHANDANA ON 23/04/2008*/
								//out.println("Test ok ------"+h+"---"+j); 
								out.println("<input type=hidden name=hid_other_inv_cnt_"+h+"  value="+j+">");
								d=h;
							}else{
								//out.println("Test no ------"+h+"---"+j); 
								
							}	
							
							
							
							
							//out.println("<input type=hidden name=hid_other_inv_cnt_"+h+"  value="+j+">"); /*COMMENT BY CHANDANA ON 23/04/2008*/
							out.println("<input type=hidden name=hid_other_invoice_tot_cnt_"+i+"  value="+k+">");
							out.println("</table>");
							
							out.println("<hr>");
							//////////////////////////////////////////////////////////////////////////////
							//comment by nuwan de silva on 21-05-2008
							// i = i+1;
							//	more1 = rs1.next();
							// }
							
							
							//added by nuwan de silva on 21-05-2008 -----
							more_othr_det = rs12.next();
						}
						
						
					}
					//-------------------------------------------
					
					out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
					
					out.println("  <tr><input type=hidden name=hid_invoice_count  value="+k+"><input type=hidden name=hid_othr_contract_cnt  value="+h+"></tr>");	//<input type=hidden name=hid_cntract_cnt  value="+i+">
					out.println("</table>");		
					
					
				}
				//		 }
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 						
				
			}
			else if(m_chksql.trim().equals("get_invoice")){
				
				String m_client    = req.getParameter("Client_Code");
				double m_rec_bal   = new Double(req.getParameter("Amount")).doubleValue();
				String m_type      = req.getParameter("Type");
				String m_rec_no    = req.getParameter("Rec_No");
				
				double m_rec_tot   = 0;//m_rec_bal;
				
				if(m_type.equals("NEW")){
					/*
							rs1 = stmt1.executeQuery ("SELECT INVOICE_NO,VAL_DATE,"+
														"       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																				"       VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
																				"       NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																				"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																				"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																				"       INVOICE_TYPE,VALUE_DATE, "+
																				"       NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
																				" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE,"+
														"       TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																				"       VAT_AMOUNT,FINANCE_NO, "+
																				"       DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																				"			  CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																				"       SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																				"       INVOICE_TYPE,VALUE_DATE "+
																				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+
																				"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																				" WHERE CLIENT_CODE='"+m_client+"' AND "+
																				"       BALANCE_TO_BE_RECEIVED>0 AND "+
																				"       A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
												"	      A.ACTIVE_STATUS <> 'C' "+//AND DUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																				" UNION ALL  "+
																				"	SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
																				"	       ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
																				"	       0,FINANCE_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
																				"	       CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE "+
																				" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
																				"	WHERE  CLIENT_CODE='"+m_client+"' AND "+
																				"        A.INVOICE_NO=B.INVOICE_NO AND "+//ODI_DATE<=SYSDATE AND "+
																				"	       ODI_BAL_AMOUNT>0  "+
																				" UNION ALL  "+
																				" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
																				" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
																				" VAT_AMOUNT,FINANCE_NO, "+
																				" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
																				" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
																				" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
																				" INVOICE_TYPE,VALUE_DATE, "+
																				" SFLOANS.AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE) "+
																				" FROM  SFLOANS.AF_CO_PRO_INVOICE A "+
																				" WHERE CLIENT_CODE='0000000475' AND "+
																				" BALANCE_TO_BE_RECEIVED>0 AND "+
																				" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM SFLOANS.AF_CO_MAS_INVOICE_RECEIPT_ORD ) "+
																				" AND A.ACTIVE_STATUS <> 'C' "+						
																				
																				
																				
																				
																				") A, "+
																				"       "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
																				" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
																				"	ORDER BY ORDER_NO,VALUE_DATE	"); */
					
					
					
					
					rs1 = stmt1.executeQuery ("	SELECT INVOICE_NO,VAL_DATE, "+
						"  TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
						" VAT_AMOUNT,FINANCE_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
						" NET_AMOUNT,CLIENT_CODE,REMARKS, "+
						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
						" INVOICE_TYPE,VALUE_DATE, "+
						" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-'), DESCR "+
						" FROM  (SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
						" VAT_AMOUNT,FINANCE_NO, "+
						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
						" INVOICE_TYPE,VALUE_DATE,"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(INVOICE_TYPE) DESCR "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A, "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND "+
						//" A.ACTIVE_STATUS <> 'C' "+
						" A.ACTIVE_STATUS = 'Y'  "+
						" UNION ALL "+
						" SELECT ODI_REF_NO,TO_CHAR(ODI_DATE,'DD-MM-YYYY') VAL_DATE, "+
						" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT, "+
						" 0,A.FIN_NO NO,ODI_DATE,0,CLIENT_CODE,'', "+
						" CURRENCY_CODE, EXCHANGE_RATE,0,0,0,'ODI',ODI_DATE, "+
						" "+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI') DESCR "+ //B.INVOICE_TYPE
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
						" WHERE  CLIENT_CODE='"+m_client+"' AND "+
						" A.INVOICE_NO=B.INVOICE_NO AND "+
						" ODI_BAL_AMOUNT>0 "+
						" UNION ALL "+
						" SELECT INVOICE_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT, "+
						" VAT_AMOUNT,FINANCE_NO, "+
						" DUE_DATE,NET_AMOUNT,CLIENT_CODE,REMARKS, "+
						" CURRENCY_CODE,EXCHANGE_RATE,TOTAL_AMOUNT_CURR, "+
						" SETTEL_AMOUNT_CURR,BALANCE_TO_BE_RECEIVED_CURR, "+
						" 'INV_OTHER' INVOICE_TYPE,VALUE_DATE, "+
						" "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE) DESCR "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE CLIENT_CODE='"+m_client+"' AND "+
						" BALANCE_TO_BE_RECEIVED>0 AND "+
						" A.INVOICE_TYPE  NOT IN (SELECT INVOICE_TYPE_CODE FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD ) "+
						//" AND A.ACTIVE_STATUS <> 'C') A, "+
						" AND A.ACTIVE_STATUS = 'Y') A, "+ //modified nuwan de silva
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B "+
						" WHERE A.INVOICE_TYPE = B.INVOICE_TYPE_CODE "+
						" ORDER BY ORDER_NO,VALUE_DATE ");	
					
					
					
					
					
					
					
					
					
					boolean more1 = rs1.next();	
					int i = 0;
					if(more1){	
						out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
						out.println("  <td WIDTH=15%>Finance No</td>");
						out.println("  <td WIDTH=10%>Vehicle Reg. No</td>");
						out.println("  <td WIDTH=10%>	Invoice Type</td>");
						out.println("  <td WIDTH=10%>Invoice No</td>");
						out.println("  <td WIDTH=10%>Invoiced Date</td>");
						out.println("  <td WIDTH=10%>Due Date</td>");
						out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
						out.println("  <td WIDTH=15% align=right>Balance Amount</td>");
						out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
						out.println("  <td WIDTH=10% align=center>Status</td></tr>");
						double m_inv_bal = 0;
						
						while(more1){	
							out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+i+"\">");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(7)+"')\" ><u>"+rs1.getString(7)+"</u></td>"); //added by nuwan de silva 26-07-07
							out.println("  <td align=left  >"+rs1.getString(19)+"</td>");
							out.println("  <td align=left  >"+rs1.getString(20)+"</td>");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_invoice_drill('"+rs1.getString(1)+"')\" ><input type=text name=\"INV_NO_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\" style=\"width: 100px\" ></td>");
							out.println("  <td align=left ><input type=text name=\"V_DATE_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
							out.println("  <td align=left ><input type=text name=\"D_DATE_"+i+"\" disabled value=\""+rs1.getString(8)+"\" class=\"txt_input2\"></td>");
							out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
							out.println("  <td align=right><input type=text name=\"BAL_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\">");
							out.println("  <input type=hidden name=\"HID_FIN_NO_"+i+"\" value="+rs1.getString(7)+">");
							out.println("  <input type=hidden name=\"allo_no_"+i+"\" value=\"\"></td>");
							m_inv_bal  = m_inv_bal+rs1.getDouble(4); 
							
							//out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
							
							if(m_rec_tot<m_inv_bal){
								if(m_rec_bal>= (m_inv_bal-m_rec_tot)){
									//out.println("111m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
									out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");
									out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked>");
									//out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
									m_rec_bal = m_rec_bal-(m_inv_bal-m_rec_tot);
									m_rec_tot = m_rec_tot +(m_inv_bal-m_rec_tot);
								}else{
									if(m_rec_bal>0){ 
										//out.println("222m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");//chk_bal('"+i+"')
										//out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\" disabled></td>");//chk_bal('"+i+"')
										out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked>");
										//out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
										m_rec_tot = m_rec_tot+m_rec_bal;
										m_rec_bal = 0;
									}else{
										//out.println("444m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
										out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\"></td>");//chk_bal('"+i+"')
										out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"NO\" >");
										//out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"YES\" checked></td>"); 
										
									}	
								}
							}else{
								//out.println("333m_inv_bal=");
								out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_manu_bal('"+i+"'),format_number(document.Form1.Text_sett_amount"+i+",30)\"></td>"); //chk_bal('"+i+"')
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"NO\">");
								//out.println("  <td align=right><input type=\"checkbox\" name=\"Chk_status"+i+"\" onclick=\"Status_Change(document.Form1.Chk_status"+i+",'"+i+"')\" value=\"NO\" ></td>"); 
							}
							
							
							out.println("  </tr>");
							i = i+1;
							more1 = rs1.next();	
							
						}
						out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></table></div>");
						
					}else{
						out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></div>");
						
					}
					//m_rec_tot = m_rec_tot +rs.getDouble(4);
				}else{
					
					//modified by nuwan de silva 26-07-07----------------------------------------------------------
					rs1 = stmt1.executeQuery ("SELECT B.INVOICE_NO, TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'), "+
						"       INVOICED_AMOUNT,SETTELED_AMOUNT,ALLOCATION_NO,FINANCE_NO, "+
						"       NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') "+
						"FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE  B "+
						"WHERE  A.INVOICE_NO=B.INVOICE_NO AND RECEIPT_NO = '"+m_rec_no+"' ");
					
					
					boolean more1 = rs1.next();	
					int i = 0;
					if(more1){	
						out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
						out.println("  <td WIDTH=15%>Finance No</td>");
						out.println("  <td WIDTH=15%>Vehicle Reg. No</td>");
						out.println("  <td WIDTH=20%>Invoice No</td>");
						out.println("  <td WIDTH=15%>Allocated Date</td>");
						out.println("  <td WIDTH=15% align=right>Invoice Amount</td>");
						out.println("  <td WIDTH=15% align=right>Allocated Amount</td>");
						out.println("  <td WIDTH=15% align=center>Status</td></tr>");
						double m_inv_bal = 0;
						while(more1){	
							out.println("  <tr><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_"+i+"\">");
							out.println("  <td align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs1.getString(6)+"')\" ><u>"+rs1.getString(6)+"</u></td>"); //added by nuwan de silva 26-07-07
							out.println("  <td align=left style= cursor:hand; onClick=\"\" >"+rs1.getString(7)+"</td>");
							out.println("  <td align=left ><input type=text name=\"INV_NO_"+i+"\" disabled value=\""+rs1.getString(1)+"\" class=\"txt_input2\"></td>");
							out.println("  <td align=left ><input type=text name=\"V_DATE_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\"></td>");
							out.println("  <td align=right><input type=text name=\"INV_AM_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\"></td>");
							out.println("<td align=right><input type=text name=\"Text_sett_amount"+i+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" onchange=\"\" disabled></td>");//chk_bal('"+i+"')
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+i+"\" onclick=check_status_inv(\""+i+"\") value=\"YES\" checked disabled >"); //modified by nuwan de silva 25-07-07
							out.println("  <input type=hidden name=\"HID_FIN_NO_"+i+"\" value="+rs1.getString(7)+">");
							out.println("  <input type=hidden name=\"allo_no_"+i+"\" value=\""+(rs1.getString(5))+"\"></td>");
							
							out.println("  </tr>");
							//out.println("m_inv_bal="+m_inv_bal+"--m_rec_bal="+m_rec_bal+"--m_rec_tot="+m_rec_tot);
							i = i+1;
							more1 = rs1.next();	
							
						}
						out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></table></div>");
						
					}else{
						out.println("  <input type=hidden name=hid_invoice_count  value="+i+"></div>");
						
					}
					
					
				}
				
				
				
			}else if(m_chksql.trim().equals("get_return_receipt")){
				
				String m_client      = req.getParameter("client");
				
				rs = stmt.executeQuery ("SELECT  b.RETURN_NO,b.DIPOSIT_NO,b.RECEIPT_NO,NVL(a.CHEQUE_NO,'-'), "+
					"				NVL(a.PAYER_ACC_NO,'-'),b.AMOUNT,b.ALLOCATED_AMOUNT,b.BAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT a, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS b "+
					" where a.REC_NO = b.RECEIPT_NO  and client_code=UPPER('"+m_client+"')  and  b.BAL_AMOUNT > 0 "+
					" order by  b.RETURN_NO	");
				
				
				
				out.println("<table class=table border='0' width='100%' >");
				
				out.println("<tr >");
				out.println("<td  width='12%' ><b>Return No </b></td>");
				out.println("<td  width='12%' ><b>Deposit No </b></td>");
				out.println("<td  width='12%' ><b>Receipt No </b></td>");
				out.println("<td  width='8%' ><b>Cheque No </b></td>");
				out.println("<td  width='12%' ><b>Payer Account No</b></td>");
				out.println("<td  width='8%' align='right' ><b>Amount</b></td>");
				out.println("<td  width='12%' align='right' ><b>Allocated Amount</b></td>");
				out.println("<td  width='12%' align='right' ><b>Balance Amount</b></td>");
				out.println("<td  width='15%' align='right' ><b>Returned Amount</b></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input  >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"RETURN_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
					out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"DIPOSIT_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"RECEIPT_NO_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
					out.println("<td >"+rs.getString(4) +"</td>");
					out.println("<td >"+rs.getString(5) +"</td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(6)) +"<input type=hidden name=\"HID_AMOUNT_"+j+"\" value=\""+rs.getDouble(6)+"\">  </td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(7)) +"<input type=hidden name=\"ALLOCATED_AMOUNT_"+j+"\" value=\""+rs.getDouble(7)+"\">  </td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(8)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getDouble(8)+"\"> </td>");
					out.println("<td align='right' ><input type=text name=\"AMOUNT_"+j+"\" value=\"0\" onblur=\"check_rep_amount(this,'"+j+"')\" class=\"txt_input2\"> <input type=hidden name=\"RET_AMOUNT_"+j+"\" value=\"\">  </td>");
					out.println("</tr>");
					
					j=j+1;
					
				}
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
			}
			
			else if(m_chksql.trim().equals("get_return_receipt2")){
				
				String m_client      = req.getParameter("client");
				String m_rec_no      = req.getParameter("rec_no");
				
				rs = stmt.executeQuery(" SELECT B.RETURN_NO,B.DIPOSIT_NO,B.RECEIPT_NO,NVL(A.CHEQUE_NO,'-'), "+
					" A.PAYER_ACC_NO ,B.AMOUNT ,B.ALLOCATED_AMOUNT,B.BAL_AMOUNT,C.AMOUNT RETURNED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B, "+
					" "+m_schema_name+".AF_CO_PRO_RETURN_REC_ALL_DET C "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND A.REC_NO = C.RETURN_REC_NO "+
					" AND B.RETURN_NO = C.RETURN_NO AND B.DIPOSIT_NO = C.DIPOSIT_NO "+
					" AND B.RECEIPT_NO = C.RETURN_REC_NO AND CLIENT_CODE = UPPER('"+m_client+"') "+
					" AND B.BAL_AMOUNT > 0 AND ALLO_RECEIPT_NO = '"+m_rec_no+"' "+
					" UNION ALL "+
					" SELECT B.RETURN_NO,B.DIPOSIT_NO,B.RECEIPT_NO,A.CHEQUE_NO, "+
					" A.PAYER_ACC_NO,B.AMOUNT,B.ALLOCATED_AMOUNT,B.BAL_AMOUNT,0 "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND CLIENT_CODE=UPPER('"+m_client+"') AND B.BAL_AMOUNT > 0 "+
					" AND (B.RETURN_NO,B.DIPOSIT_NO) NOT IN (SELECT RETURN_NO,DIPOSIT_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_RETURN_REC_ALL_DET "+
					" WHERE ALLO_RECEIPT_NO='"+m_rec_no+"') order by RETURN_NO ");
				
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr >");
				out.println("<td  width='12%' ><b>Return No </b></td>");
				out.println("<td  width='12%' ><b>Deposit No </b></td>");
				out.println("<td  width='12%' ><b>Receipt No </b></td>");
				out.println("<td  width='8%' ><b>Cheque No </b></td>");
				out.println("<td  width='12%' ><b>Payer Account No</b></td>");
				out.println("<td  width='8%' align='right' ><b>Amount</b></td>");
				out.println("<td  width='12%' align='right' ><b>Allocated Amount</b></td>");
				out.println("<td  width='12%' align='right' ><b>Balance Amount</b></td>");
				out.println("<td  width='15%' align='right' ><b> Returned Amount</b></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"RETURN_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
					out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"DIPOSIT_NO_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"RECEIPT_NO_"+j+"\" value=\""+rs.getString(3)+"\"></td>");
					out.println("<td >"+rs.getString(4) +"</td>");
					out.println("<td >"+rs.getString(5) +"</td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(6)) +"<input type=hidden name=\"HID_AMOUNT_"+j+"\" value=\""+rs.getDouble(6)+"\">  </td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(7)) +"<input type=hidden name=\"ALLOCATED_AMOUNT_"+j+"\" value=\""+rs.getDouble(7)+"\">  </td>");
					out.println("<td align='right' >"+nf.format(rs.getDouble(8)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getDouble(8)+"\"> </td>");
					out.println("<td align='right' ><input type=text name=\"AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(9))+"\" onblur=\"check_rep_amount(this,'"+j+"')\"  class=\"txt_input2\" > <input type=hidden name=\"RET_AMOUNT_"+j+"\" value=\""+rs.getDouble(9)+"\" ></td>");
					out.println("</tr>");
					
					j=j+1;
					
				}
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");		
				
			} else if(m_chksql.trim().equals("get_Termi_Det")){
				
				String m_Termination_no    = req.getParameter("Termination_no");
				
				
				rs = stmt.executeQuery ("SELECT RENTAL_DATE, PERCENTAGE, RENTAL_AMOUNT,  "+
					"       RENTAL_PV,TERM_AMOUNT, TERM_PV "+
					"FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION_DETAILS "+
					"WHERE  TERMINATION_NO='"+m_Termination_no+"'"); 
				
				out.println("<table class=table border='0' width='100%' >");
				
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Installment Date</td>");
				out.println("<td  width='5%'  >Percentage</td>");
				out.println("<td  width='20%' >Rental</td>");
				out.println("<td  width='20%' >P.V.</td>");
				out.println("<td  width='20%' >Termination Amount</td>");
				out.println("<td  width='20%' >P.V.</td>");
				out.println("</tr>");
				
				int j = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td></td>");
				out.println("<td ></td>");
				out.println("<td align=right id=rent></td>");
				out.println("<td align=right id=rpv ></td>");
				out.println("<td align=right id=term></td>");
				out.println("<td align=right id=tpv ></td>");
				out.println("</tr>");
				
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
					out.println("");
					out.println("</tr>");
					rent = rent  + rs.getDouble(3);
					rpv  = rpv   + rs.getDouble(4);
					term = term  + rs.getDouble(5);
					tpv  = tpv   + rs.getDouble(6);
					j=j+1;
					
					if(rs.next()){
						out.println("<tr class=tr_input1 >");
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
						out.println("");
						out.println("</tr>");
						rent = rent  + rs.getDouble(3);
						rpv  = rpv   + rs.getDouble(4);
						term = term  + rs.getDouble(5);
						tpv  = tpv   + rs.getDouble(6);
						j=j+1;
					}
					
					
				}
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
				out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
				out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
				out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
			}
			
			else if(m_chksql.trim().equals("get_Termi_Char")){
				
				String m_Lease_no     = req.getParameter("Lease_no");
				String m_Vehicle_no   = req.getParameter("Vehicle_no");
				String m_Disco_rate   = req.getParameter("Disco_rate");
				String m_App_date     = req.getParameter("App_Date");
				String m_client       = req.getParameter("Client");
				
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_CR_TEMP_TERMINATION_SAVE(:1,:2,:3,:4,:5,:6);END;");
				callstmt1.setString(1 ,m_Disco_rate);
				callstmt1.setString(2 ,m_Vehicle_no);
				callstmt1.setString(3 ,m_Lease_no);
				callstmt1.setString(4 ,m_username);
				callstmt1.setString(5 ,m_App_date);
				callstmt1.setString(6 ,m_client);
				
				//out.println("t5");
				callstmt1.execute();
				//out.println("t6");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(INSTALLMENT_DATE,'DD-MM-YYYY'),PERCENTAGE,  "+
					"       SUM(RENTAL_AMOUNT), SUM(PV),SUM(TERMINATION_AMOUNT),   "+
					"       SUM(TERMINATION_PV),INSTALLMENT_NO "+
					"FROM   "+m_schema_name+".AF_CR_TBD_TERMINATION "+
					"WHERE  ENT_USER='"+m_username+"' "+
					"GROUP  BY INSTALLMENT_NO, "+
					"       INSTALLMENT_DATE, PERCENTAGE ");
				
				out.println("<table class=table border='0' width='100%' >");
				
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Installment Date</td>");
				out.println("<td  width='5%'  >Percentage</td>");
				out.println("<td  width='20%' >Rental</td>");
				out.println("<td  width='20%' >P.V.</td>");
				out.println("<td  width='20%' >Termination Amount</td>");
				out.println("<td  width='20%' >P.V.</td>");
				out.println("</tr>");
				
				int j = 0;
				double rent=0;
				double rpv =0;
				double term=0;
				double tpv =0;
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td></td>");
				out.println("<td ></td>");
				out.println("<td align=right id=rent></td>");
				out.println("<td align=right id=rpv ></td>");
				out.println("<td align=right id=term></td>");
				out.println("<td align=right id=tpv ></td>");
				out.println("</tr>");
				
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input >");
					out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
					out.println("");
					out.println("</tr>");
					rent = rent  + rs.getDouble(3);
					rpv  = rpv   + rs.getDouble(4);
					term = term  + rs.getDouble(5);
					tpv  = tpv   + rs.getDouble(6);
					j=j+1;
					
					if(rs.next()){
						out.println("<tr class=tr_input1 >");
						out.println("<td >           "+rs.getString(1)           +"<input type=hidden name=\"INSTALL_DATE_"+j+"\"  value=\""+rs.getString(1)+"\"></td>");
						out.println("<td align=right>"+nf1.format(rs.getDouble(2))+"<input type=hidden name=\"PERCENTAGE_"+j+"\"    value=\""+rs.getString(2)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(3))+"<input type=hidden name=\"RENTAL_"+j+"\"        value=\""+rs.getString(3)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"PV_"+j+"\"            value=\""+rs.getString(4)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"TERMINATION_A_"+j+"\" value=\""+rs.getString(5)+"\"></td>");
						out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"TER_PV_AM_"+j+"\"     value=\""+rs.getString(6)+"\"></td>");
						out.println("");
						out.println("</tr>");
						rent = rent  + rs.getDouble(3);
						rpv  = rpv   + rs.getDouble(4);
						term = term  + rs.getDouble(5);
						tpv  = tpv   + rs.getDouble(6);
						j=j+1;
					}
					
					
				}
				
				out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
				out.println("<td><input type=hidden name=hid_count value="+j+"></td>");
				out.println("<td ></td>");
				out.println("<td align=right>"+nf.format(rent)+"<input type=hidden name=h_rent value="+nf.format(rent)+"></td>");
				out.println("<td align=right>"+nf.format(rpv) +"<input type=hidden name=h_rpv  value="+nf.format(rpv)+"></td>");
				out.println("<td align=right>"+nf.format(term)+"<input type=hidden name=h_term value="+nf.format(term)+"></td>");
				out.println("<td align=right>"+nf.format(tpv) +"<input type=hidden name=h_tpv  value="+nf.format(tpv)+"></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
			}else if(m_chksql.trim().equals("get_Receipt")){
				
				String m_client      = req.getParameter("client");
				
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
					"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
					"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
					"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
					"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
					"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
					"	       BAL_TOBE_RECEIVE>0 AND CLIENT_CODE = '"+m_client+"' ");
				
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Receipt No</td>");
				out.println("<td  width='15%' align=right>Receipt Amount</td>");
				out.println("<td  width='15%' align=right>Allocated Amount</td>");
				out.println("<td  width='20%' align=right>Balance Amount</td>");
				out.println("<td  width='35%' align=right>Amount</td>");
				//out.println("<td  width='10%'  ></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");//out.pr	
					out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled>");
					out.println("<input type=button name=inv_h_"+j+" value=\"Invoice Detail\" class=mainbut1 onclick=inv_help('"+j+"'); style=\"width: 90px\"></td>");
					//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
					//out.println("     </td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'><input type=hidden name=hid_invoice_count_"+j+" value=0></div>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td>&nbsp;</TD>");
					out.println("<td colspan=5 >");
					out.println("</td>");
					out.println("</tr>");
					j=j+1;
					
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
				
			} 	
			
			
			else if(m_chksql.trim().equals("get_Receipt_del")){
				
				String m_rec_no      = req.getParameter("rec_no");
				
				rs = stmt.executeQuery (" SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT, "+
					"	       BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE, "+
					"	       A.REC_AMOUNT_CURR,EXCHANGE_RATE_BANK, "+
					"	       EXCHANGE_RATE_REP_CURR,REC_AMMOUNT_REP_CURR, "+
					"	       EXCHANGE_GAIN_LOSS,SUS_REF_NO,CHEQUE_NO "+
					"	FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+ 
					"	       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
					"	WHERE  A.REC_NO = B.REC_NO AND STATUS='P' AND "+
					"	       ALLOCATED_AMOUNT>0 AND A.REC_NO = '"+m_rec_no+"' ");
				
				
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr class=tr_input>");
				//out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("<td colspan=8 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				out.println("</tr>");
				
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td  width='15%' >Receipt No</td>");
				out.println("<td  width='15%' align=right>Receipt Amount</td>");
				out.println("<td  width='15%' align=right>Allocated Amount</td>");
				out.println("<td  width='20%' align=right>Balance Amount</td>");
				out.println("<td  width='25%' align=right>Amount</td>");
				out.println("<td  width='10%'  ></td>");
				out.println("</tr>");
				
				int j = 0;      					
				
				while(rs.next()){
					//out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
					out.println("<td >"+rs.getString(1) +"<input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\" ></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(2)) +"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+rs.getString(2)+"\"></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(3)) +"<input type=hidden name=\"ALLO_AMOUN_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
					out.println("<td align=right>"+nf.format(rs.getDouble(4)) +"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"></td>");
					out.println("<td align=right><input type=text name=\"SETT_AMOUN_"+j+"\" value=\"0\" class=\"txt_input2\" disabled></td>");
					//out.println("<input type=button name=inv_h_"+j+" value=\"Help\" class=mainbut1 onclick=inv_help('"+j+"');></td>");
					//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\">");
					//out.println("     </td>");
					out.println("</tr>");
					
					
					
					rs1 = stmt1.executeQuery ("SELECT A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), A.INVOICED_AMOUNT, "+
						"       A.SETTELED_AMOUNT,ALLOCATION_NO, A.REMARKS, A.SETTELED_AMOUNT_CURR "+
						"FROM   "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A "+
						"WHERE  RECEIPT_NO = '"+rs.getString(1)+"'");
					
					
					
					out.println("<tr class=tr_input>");
					out.println("<td></TD>");
					out.println("<td colspan=5 ><div id='inv_"+j+"'>");
					
					out.println("<table><tr class=pdn_txtpos2 WIDTH=100%>");
					out.println("      <td WIDTH=20%>Invoice No</td>");
					out.println("      <td WIDTH=15%>Date</td>");
					out.println("      <td WIDTH=15% align=right >Invoice Amount</td>");
					out.println("      <td WIDTH=15% align=right >Allocated Amount</td>");
					out.println("      <td WIDTH=15% align=CENTER>Status</td></tr>");
					int x=0;
					
					while(rs1.next()){
						out.println(" <tr>");
						out.println("  <td align=left ><input type=text name=\"INV_NO_0_"+x+"\" value=\""+rs1.getString(1)+"\" class=\"txt_input2\" disabled><INPUT TYPE=HIDDEN NAME=\"ALLO_NO_0_"+x+"\" VALUE=\""+rs1.getString(5)+"\"></td>");
						out.println("  <td align=left ><input type=text name=\"V_DATE_0_"+x+"\" value=\""+rs1.getString(2)+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"INV_AM_0_"+x+"\" value=\""+nf.format(rs1.getDouble(3))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=right><input type=text name=\"Text_sett_amount0_"+x+"\" value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\" disabled></td>");
						out.println("  <td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard_0_"+x+"\" onclick=check_status_del(\""+x+"\") value=\"NO\">");
						out.println(" </tr>");
						x=x+1;
					}
					//out.println(" }");
					out.println("<input type=hidden name=hid_invoice_count_0  value="+x+"></table>");
					out.println("</div>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr class=tr_input>");
					out.println("<td>&nbsp;</TD>");
					out.println("<td colspan=5 >");
					out.println("</td>");
					out.println("</tr>");
					j=j+1;
				}
				
				
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=8><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
				
				out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");
				
				
			} 	
			
			else if(m_chksql.equals("view_cheques")){		
				int count=0;		
				
				String m_cheque_no = req.getParameter("cheque_no");	
				String m_branch_code= req.getParameter("branch_code");	
				
				rs = stmt.executeQuery (" SELECT CLIENT_CODE,NVL(REC_NO,'-'),NVL(CHEQUE_NO,'-'),NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'),NVL(PAYER_ACC_NO,'-'), "+
					" PAYER_BRANCH_CODE,REC_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE UPPER(PAYER_BRANCH_CODE)=UPPER('"+m_branch_code+"') AND UPPER(CHEQUE_NO)=UPPER('"+m_cheque_no+"') ");
				
				
				out.println("<HTML><HEAD><TITLE>Receipt Details</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Receipt Details</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				boolean more = rs.next();
				
				if (!more) {
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if (more) {
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>The cheque number that has been entered already exists </u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					more = rs.next();
				}
				out.println("</table>");
				
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' class=div_input><b>In the event this is the genuine cheque number please add a suffix to the actual number </b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' class=div_input><b>Example :</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input><b>Actual Number</b></td>");
				out.println("<td width='*%' class=div_input><b>123456</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='*%' class=div_input><b>Please enter additional records as </b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input><b>Example :</b></td>");
				out.println("<td width='*%' class=div_input><b>123456<font color='red'>A</font></b></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='15%' class=div_input><b>&nbsp;</b></td>");
				out.println("<td width='*%' class=div_input><b>123456<font color='red'>B</font>&nbsp;&nbsp;etc.</b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
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











