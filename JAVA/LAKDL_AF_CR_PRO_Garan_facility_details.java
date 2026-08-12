//--
//SCREEN NAME:
//CREATED BY:SANDUN JAYATHILAKE
//DATE/TIME:12-08-2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_CR_PRO_Garan_facility_details extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url   = m_sn_methods.html_client_url.trim(); 
			String m_class_url         = m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name      = m_sn_methods.client_name.trim();
			String m_schema_name       = m_sn_methods.schema_name;			
			String m_servlet_client_url= m_sn_methods.servlet_client_url;
			String m_client_name       = m_sn_methods.client_name;
			String m_client_t3_port    = m_sn_methods.client_t3_port;
			String m_username 				 = m_sn_methods.username;
			String header_name         = m_sn_methods.header_name;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt = conn.createStatement ();
			String m_chksql=req.getParameter("chksql");	
			String m_sort_column="",m_order_by_type="";
			
			if(m_chksql.equals("main_page")){ 
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Temporary Receipt Approval</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");	
				
				out.println("function before_submit(){ "); 	
				
				out.println("ckeck_data();");
				out.println("if(b_flag==0)");
				
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Garan_facility_details';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("} "); 
				
				
				
				out.println("function load_lock(){	"); 						
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 	
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Garan_facility_details?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Garan_facility_details?chksql=main_page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Post_Dated_Cheque_Deposit\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection - Guarantor Details - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection - Guarantor Details - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				
				out.println("function load_temp_rec(){");
				out.println("if(document.Form1.TXT_USER.value==\"\"){");
				out.println("  alert('Please Select Guranter Code');"); 
				out.println("}else{"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Garan_facility_details?chksql=view&user=\"+document.Form1.TXT_USER.value+\" \";");
				out.println("load_interface(m_url,'NORM');");
				out.println("}"); 
				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" m_table.innerHTML = ''; ");
				out.println(" m_table.innerHTML = http_response; ");
				out.println("}");		
				
				
				out.println("function ckeck_data(){ "); 
				
				out.println("b_flag=0;");
				
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_receipts()){"); 
				out.println("alert('No cheques selected');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 
				
				
				
				out.println("function count_receipts(){ ");			
				out.println("count=0;");		
				out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");			
				out.println("m_chk_deposit_tmp=\"CHK_RECEIPT\"+i;");
				out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
				out.println("count=count+1;");
				out.println("}");								
				out.println("}");					
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");			
				out.println("}"); 				
				
				out.println("function change_val_receipt_status(r){");
				out.println("if(document.Form1.elements[\"CHK_RECEIPT\"+r].checked==true){");
				out.println("document.Form1.elements[\"CHK_RECEIPT\"+r].value=\"on\";");
				out.println("}");
				out.println("else if(document.Form1.elements[\"CHK_RECEIPT\"+r].checked==false){");
				out.println("document.Form1.elements[\"CHK_RECEIPT\"+r].value=\"off\";");
				out.println("}");
				out.println("}");									
				
				out.println("function mouse_over(id){");
				out.println("document.getElementById(id).style.textDecoration='underline';");
				out.println("}");
				
				out.println("function mouse_out(id){");
				out.println("document.getElementById(id).style.textDecoration='none';");
				out.println("}");	
				
				
				out.println("function contract_allo_details(temp_id){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Temp_Settlement_Approval?chksql=allo_details&rec_no=\"+temp_id+\" \";");
				out.println("window.open(m_url,'win98','top=200,left=200,width=600,height=400')");	
				out.println("}");	
				
				out.println("function MyDialog(){"); 
				out.println("this.valout   = new Array(10);"); 
				out.println("}"); 
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] ==\" \"){");  //m_help_TXT_CLIENT_CODE
				out.println("		clear_data();");
				out.println("		} else "); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		enter_user_assign(oBj);"); 
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
				out.println("	clear_data();");
				out.println("	}");
				out.println("	}	"); 
				out.println("}"); 
				
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				
				/*out.println("function enter_user_help(){");
				out.println("Crit=document.Form1.TXT_USER.value+\"@\"+\"Y@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				out.println("HelpBox('1','10','0',Crit,'m_help_TXT_USER_ID_sql','1');"); 
				out.println("}");*/
				
				out.println("function enter_user_help(rowNo) {"); 
					out.println("		 m_gur_code=\"TXT_USER\"+rowNo;");
					out.println("    document.Form1.hid_help_type.value=\"5\";"); 
					out.println("    document.Form1.hid_row_no.value=rowNo;"); 
					out.println("    Sql = \"GuarantorSql_add_gua\";"); 
				  out.println("    Crit = document.Form1.TXT_USER.value+\"@Y@\";"); 
					//out.println("    Crit =document.Form1.elements[m_gur_code].value+\"@\"+document.Form1.TXT_APPLICANT_CODE.value+\"@\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','2',Crit,Sql,'5');"); 
					out.println("}"); 
					out.println(""); 
				
				out.println("function enter_user_help() {"); 
				out.println("Crit=document.Form1.TXT_USER.value+\"@\"+\"@Y@\";");
				//out.println("    document.Form1.hid_help_type.value=\"80\";"); 
				//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
				//out.println("   Sql = \"m_help_TXT_CLIENT_CODE\";"); 
				//out.println("    Crit =document.Form1.elements[m_gur_code].value+\"@\"+\"@Y@\";"); 
				//out.println("    HelpBox('1','10','2',Crit,Sql,'5','0');"); 
				out.println("HelpBox('1','10','0',Crit,'GuarantorSql_add_gua','1');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function enter_user_assign(oBj){");
				out.println(" document.Form1.TXT_USER.value =oBj.valout[2];");
				out.println("}"); 
				
				out.println("function clear_data(){");
				out.println(" document.Form1.TXT_USER.value =\"\";");
				out.println("}"); 
				
				out.println("var m_prv_col=''; ");
				out.println("var m_prv_order_by_type=''; ");
				out.println("function sort_data(m_sort_col) {");	
				out.println("m_order_by_type = 'ASC' ");			
				//out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				//out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	 if(m_sort_col==m_prv_col){");
				out.println("	   if(m_prv_order_by_type=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
				out.println("       m_order_by_type = 'DESC'; ");
				out.println("    }");
				out.println("  }else{");
				out.println("    m_order_by_type = 'ASC'; ");
				out.println("  }");
				out.println("m_prv_col =m_sort_col; ");
				out.println("m_prv_order_by_type =m_order_by_type; ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Temp_Settlement_Approval?chksql=view&user=\"+document.Form1.TXT_USER.value+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");	
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_temp_rec()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_POST_DATED_RECEIPT_GENERATION\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
				out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 			
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Guarantor Details</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos'  valign='top'>");  
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%' >Guarantor Code *</td>");
				out.println("<td width='20%' ><input type='text' name='TXT_USER' value=\"\" class=\"txt_input\" onblur=\"enter_user_help()\" >");
				out.println("&nbsp;");
				out.println("<input class='but_input' type='button' name='BUT_HELP' value=\"Help\" onClick=\"enter_user_help()\"><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_temp_rec()\"> </td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				/*out.println("<tr class=tr_input>");
				out.println("<td width=\"20%\" >Settlement Mode</td>");
				out.println("<td width=\"20%\"> <SELECT name=\"SETT_MODE\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"\" selected>All</OPTION>");
				out.println("<OPTION value=\"CHEQUE\">Cheque</OPTION>");
				out.println("<OPTION value=\"CASH\">Cash</OPTION>");
				out.println("<OPTION value=\"STD_ORD\">Standing Order</OPTION>");
				out.println("<OPTION value=\"DIR_DEP\">Direct Deposit</OPTION>");
				out.println("</SELECT>");
				out.println("&nbsp;");
				out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_temp_rec()\"></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); */
				
				
				out.println("</table>"); 
				
				out.println("<br>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			else if(m_chksql.equals("view")){		
				String m_set_mode = req.getParameter("setle_mode");
				String m_user     = req.getParameter("user");
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");				
					
				}else{
					m_sort_column   = "EFF_VALDATE";
					m_order_by_type = "ASC";
					
				}
				
			/*	rs=stmt.executeQuery( " SELECT DISTINCT A.TEMP_REC_NO, "+//1
					" "+m_schema_name+".AF_CO_GET_TEM_REC_ALLO_FIN_NO(A.TEMP_REC_NO),  "+//2
					" DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','STD_ORD','Standing Order','CASH','Cash','DIR_DEP','Direct Deposit',A.SETTLE_MODE) SETTLE_MODE,  "+//3
					" NVL(DECODE(A.SETTLE_MODE,'STD_ORD',A.BRANCH_CODE,'DIR_DEP',A.BRANCH_CODE,A.PAYER_BRANCH_CODE),'-') PAYER_BRANCH_CODE, "+//4
					" NVL(DECODE(A.SETTLE_MODE,'STD_ORD',A.ACC_NO,'DIR_DEP',A.ACC_NO,A.PAYER_ACC_NO),'-') PAYER_ACC_NO, "+//5
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,  "+//6
					" NVL(A.REC_AMOUNT,0) REC_AMOUNT, "+//7
					" NVL(A.BRANCH_CODE,'-') BRANCH_CODE, "+//8
					" NVL(A.ACC_NO,'-') ACC_NO, "+//9
					" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,        "+//10
					" NVL(A.CHEQUE_NO,'-') CHEQUE_NO, "+//11
					" NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+//12
					" A.CLIENT_CODE "+//13
					" FROM "+m_schema_name+".AF_CO_PRO_SET_TMRECEIPT A,"+m_schema_name+".AF_CO_PRO_SET_TMREC_APP_BAL B "+
					" WHERE A.TEMP_REC_NO = B.TEMP_REC_NO(+) "+
					" AND A.STATUS IN ('E','B') "+
					" AND UPPER(A.ENT_USER) LIKE  UPPER('%"+m_user+"%') "+
					" AND A.SETTLE_MODE LIKE  '%"+m_set_mode+"%' "+
					" ORDER BY "+m_sort_column+"  "+m_order_by_type+" ");	*/
				
				 rs= stmt.executeQuery(" SELECT "+m_schema_name+".GET_AUTO_GEN_FINANCE_NO(A.APPLICATION_NO),A.GUARANTOR_CODE,"+//1,2
  						               "  "+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE(A.APPLICATION_NO)) "+//4		
 									   " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
 									   " WHERE A.GUARANTOR_CODE='"+m_user+"' ");
					
					
				
				/* rs= stmt.executeQuery(" SELECT   A.GUARANTOR_CODE,"+//1
  						"  NVL(A.RELATIONSHIP,'-'), "+//2
  						"  NVL(A.PERIOD,0), "+//3
  						"  NVL(A.TEL_NO,'-') "+//4
 				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
 				" WHERE A.GUARANTOR_CODE='"+m_user+"'   ");
				*/
				
				boolean more  = rs.next();
				
				if(!more){
					out.println("<br>");			
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr><td width=\"100%\" align=\"center\"><font color='red'>No Data Found...!</font></td></tr>");
					out.println("</table>");
				}
				
				if(more){
					out.println("<br>");			
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width=\"3%\" align=left  title='Click here to sort by - Receipt No  '         STYLE='{cursor:hand}'>No.</td>"); 
				    out.println("<td width=\"10%\" align=left   title='Click here to sort by - Receipt No  '         STYLE='{cursor:hand}'> Finance No</td>");
					out.println("<td width=\"10%\" align=left  title='Click here to sort by - Client Name  '           STYLE='{cursor:hand}'>Client Name</td>"); 
					 
					out.println("</tr >"); 
					
					
					int j=1;
					
					while(more){			
						
						//if(j>1 && j%3==2){
						//	out.println("<tr class=tr_input1 >");
						////}
						//else{
							out.println("<tr class=tr_input >");
						//}
						out.println("<td width=\"3%\" align=left >"+j+"<input type='hidden' name=hid_rec_no_"+j+" value=\""+rs.getString(1)+"\"></td>"); 
						out.println("<td width=\"10%\" align=left  >"+rs.getString(1)+"</td>"); 
						out.println("<td width=\"10%\" align=left  >"+rs.getString(3)+"</td>"); 
						
						out.println("</tr >"); 
						more  = rs.next();
						j=j+1;
						
					}
					
					out.println("<input type=hidden name=hid_no_rec value="+j+">");
					out.println("</table >"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					
				}
				
				
				
			}
			else if(m_chksql.equals("allo_details")){
				
				String m_rec_no = req.getParameter("rec_no");
				int j=1;
				double  m_tot=0;
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Temporary Receipt Approval - Allocation Details</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<body>"); 
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				out.println("<tr>");
				out.println("<td width='100%' align='center'><b><u>Temporary Receipt - Allocation Details</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\">");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"10%\"  align=center>No</td>"); 
				out.println("<td width=\"50%\" align=left>Finance No</td>"); 
				out.println("<td width=\"40%\" align=right>Receipt Amount</td>"); 			
				out.println("</tr >"); 
				
				rs=stmt.executeQuery( " SELECT FINANCE_NO, nvl(APP_REC_AMOUNT,0) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SET_TMREC_APP_BAL "+
					" WHERE  TEMP_REC_NO = '"+m_rec_no+"' ");
				
				
				boolean more = rs.next();
				while(more){
					out.println("<tr>");
					out.println("<td width=\"10%\"  align=center>"+j+"</td>"); 
					out.println("<td width=\"50%\" align=left>"+rs.getString(1)+"</td>"); 
					out.println("<td width=\"40%\" align=right>"+nf.format(rs.getDouble(2))+"</td>"); 			
					out.println("</tr >"); 
					m_tot = m_tot+rs.getDouble(2);
					more = rs.next();
					j++;
					
				}
				
				
				out.println("<tr>");
				out.println("<td width=\"10%\"   align=center>&nbsp;</td>"); 
				out.println("<td width=\"50%\"  align=right><b>Total</td>"); 
				out.println("<td width=\"40%\"  align=right><b>"+nf.format(m_tot)+"</td>"); 			
				out.println("</tr >"); 
				
				
				out.println("</table >"); 
				
				
				out.println("</body>"); 
				out.println("</HTML>"); 
				
			}
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
