//--
//SCREEN NAME	:POST DATED  CHEQUES WITHDRAWAL
//CREATED BY	:SANDUN JAYATHILAKE
//DATE/TIME		:
//NOTES			
//MODIFIED NUWAN DE SILVA:
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_App_Post_Dated_Cheque_Withrawal extends javax.servlet.http.HttpServlet { 


	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			String fschema_name = m_sn_methods.schema_name;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
      String m_username 						= m_sn_methods.username;
			String m_value="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
						
			out = res.getOutputStream();
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){
								
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Post Dated Cheques Withdrawal</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
						
			out.println("function get_vector_normal(http_response) {");		
			out.println(" withdrawal_details.innerHTML = ''; ");
			out.println(" withdrawal_details.innerHTML = http_response; ");
			out.println("}");
			
			
			out.println("function makeRequest(val) {");
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_App_Post_Dated_Cheque_Withrawal?chksql=cheque_details&m_val=\"+document.Form1.hid_status.value+\"&m_client_code=\"+val+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			
			out.println("function count_receipts(){ ");			
			out.println("count=0;");		
			out.println("for(i=0;i<parseInt(document.Form1.hid_count.value);i++){");			
			out.println("m_chk_deposit=\"select_\"+i;");
			out.println("if(document.Form1.elements[m_chk_deposit].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");				
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");		
			out.println("}"); 
			
			out.println("function ckeck_data(){ "); 			
			out.println("b_flag=0;");						
			out.println("if(withdrawal_details.innerHTML==\"\"){");
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
			
			out.println("function before_submit(){ "); 
			out.println("if(validate_data()){"); 
			out.println("ckeck_data();");
			out.println("if(b_flag==0)");						
			out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
			out.println("		if(validate_data()){");			
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_save_pod_cheques_withdrawal';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 			
			out.println("} "); 
							
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_App_Post_Dated_Cheque_Withrawal?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 			

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_App_Post_Dated_Cheque_Withrawal?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_pod_cheques\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Collection - Post Dated Cheques Withdrawal - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Post Dated Cheques Withdrawal - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){");
			
			out.println("if(m_val==\"NEW\"){"); 
			out.println(" if(confirm(\"Are you sure you want to enter New record?\")){  ");
			out.println("new_window();");
			out.println("}"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 			
			out.println("else{");			
			out.println("}"); 
			
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println(" if(confirm(\"Are you sure you want to Modify a record?\")){  ");
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function clear_data(IfCount) {");			
			out.println("		if(IfCount==\"1\"){"); 
			out.println(" document.Form1.TXT_CLIENT_CODE.value = \"\" ");
			out.println(" document.Form1.TXT_CLIENT_NAME.value = \"\" ");
			out.println(" withdrawal_details.innerHTML = ''; ");
			out.println("}");			
			out.println("}");


			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Help_Servlet?class_in="+m_client_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_help_assign(oBj);"); 
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
			out.println("	clear_data(IfCount);");
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
			
			out.println("function help_button_client() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'ClientSql_POD_With','1');"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@ACTIVATED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'ClientSql_POD_With_Edit','1');"); 
			out.println("}"); 			
			out.println("}"); 
			
			out.println("function client_help_assign(oBj) {");			
			out.println(" document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println(" document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
			out.println(" }");
			
			out.println("function chk_req(row){");
			out.println("if(document.Form1.elements[\"select_\"+row].checked){");
			out.println("document.Form1.elements[\"select_\"+row].value='on';");
			out.println("}else{");
			out.println("document.Form1.elements[\"select_\"+row].value='off';");
			out.println("}");
			out.println("}");								
				
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"PDC_WITHDRAWAL\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Post Dated Cheques Entry</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
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
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>");  


			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			out.println("<tr>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code*</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='10' onblur=\"help_button_client()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NO' value=\"...\" onClick=\"help_button_client()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client Name</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' style='{width:240px}' type='text' name='TXT_CLIENT_NAME' maxlength='10' size='40' disabled>&nbsp;&nbsp;&nbsp;<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"makeRequest(document.Form1.TXT_CLIENT_CODE.value)\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
					
			out.println("<table align='center' width='100%' class='table' border=\"0\" bgcolor=\"#FFFACD\">"); 
			out.println("<tr>");  
			out.println("<td width='*%' ><div id=withdrawal_details></div></td>");
	 		out.println("<tr></table>"); 
			
					
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 

			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		else if(m_chksql.equals("cheque_details")){		
			int i=0;		
			int x=0;	
			String m_client_code = req.getParameter("m_client_code");	
			String m_screen_name= req.getParameter("m_val");	
			String m_status = "";
			
				
				if(m_screen_name.equals("New")){
				//m_status = "APP";
				m_status = "INV";
				}
				else{
				m_status = "WIT";
				}
			rs1 = stmt1.executeQuery ("SELECT COUNT(POD_REF_NO) "+
			"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
		 	"WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ");
			boolean more1 = rs1.next();

			rs = stmt.executeQuery ("SELECT POD_REF_NO,NVL(CHEQUE_NO,'-'),to_char(CHEQUE_DATE,'dd-mm-yyyy'),SETTLE_MODE, "+
			"PAYER_BRANCH_CODE,PAYER_ACC_NO, "+
			"CLIENT_CODE,'X','X',NVL(CURR_CODE,'-'),to_char(nvl(CHEQUE_AMOUNT,0),'999,999,999,999.99') ,FINANCE_NO "+
			"FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
		  "WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')   AND STATUS = '"+m_status+"'  "+
			"ORDER BY POD_REF_NO ASC ");
			boolean more = rs.next();
				
				
			if (!more)
			{
			out.println("<br><table border=0 class='table' width=100% ><tr class='txt_input'><td width=100% align='center'><font color='red'>No Data Found..!</font></td></tr></table>");
			}else{
				

			if(rs1.getInt(1)!=0){	
			
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"15%\" ><b>Contract No</td>"); 
			out.println("<td width=\"15%\" ><b>POD No </td>"); 
			out.println("<td width=\"15%\" ><b>Cheque No</td>");
			out.println("<td width=\"15%\" ><b>Cheque Date </td>"); 
			out.println("<td width=\"15%\" align=right><b>Allocated Amount </td>"); 
			out.println("<td width=\"15%\" ><b>Account No </td>"); 
			out.println("<td width=\"10%\" ><b>Branch  </td>"); 
			out.println("<td width=\"15%\" ><b>Currency </td>");
			out.println("<td width=\"5%\" align=center><b>Select </td>");
			out.println("</tr >"); 

			
	    int j = 0; 
			while(more){

			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(12)+"<input type=hidden name=HID_FIN_"+j+" value="+rs.getString(12)+"></td>"); 
			out.println("<td width='15%' style='{width:120px;text-align:left;cursor:hand;}' onClick=\"show_pod_cheque_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input type=hidden name=HID_POD_"+j+" value="+rs.getString(1)+"></td>");
			out.println("<td width='15' style='{width:120px;text-align:left;}'>"+rs.getString(2)+"<input type=hidden name=HID_CHQUE_NO_"+j+" value="+rs.getString(2)+"></td>");

			if(rs.getString(3)!=null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(3)+"</td>");
			}
			else if(rs.getString(3)==null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'></td>");
			}
			out.println("<td width='15%' style='{width:120px;text-align:right;}'>"+rs.getString(11)+"</td>"); 
			if(rs.getString(6)!=null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(6)+"</td>"); 
			}
				if(rs.getString(6)==null){
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>-</td>"); 
			}
			
			if(rs.getString(5)!=null){
			out.println("<td width='10%' style='{width:120px;text-align:left; cursor:hand;}' onClick=\"show_branch_drill('"+rs.getString(5)+"')\" ><u>"+rs.getString(5)+"</u></td>"); 
			}
			if(rs.getString(5)==null){
			out.println("<td width='10%' style='{width:120px;text-align:left;}'>-</td>"); 
			}
			
			out.println("<td width='15%' style='{width:120px;text-align:left;}'>"+rs.getString(10)+"</td>");
			out.println("<td width='5%' align=center><input type=checkbox name=select_"+j+" value=off onclick=chk_req("+j+")></td>");
			out.println("</tr>");
			more=rs.next();
			j=j+1;
		
			/*if (!more)
			{
			break;
			}
			*/
			}
				
		
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
			out.println("</table>");
		}
			}		
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<tr><td>");
			out.println("<td ><input type=hidden name=hid_no_val value=\""+rs1.getInt(1)+"\"></td>");
			out.println("</tr>");
			out.println("</table>");
			
			
	}
			
	
//--------------------------------------------------------------------------------------------------------------------			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null){try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
