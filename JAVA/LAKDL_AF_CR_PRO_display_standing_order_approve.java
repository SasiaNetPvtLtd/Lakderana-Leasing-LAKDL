//--
//SCREEN NAME:STANDING ORDER APPROVE
//CREATED BY :DELANJALI
//DATE/TIME  :19-02-2007
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_display_standing_order_approve extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;


	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_order="SO_NO";
			String m_type="DESC";



			if (req.getParameter("ORDER")!=null && req.getParameter("TYPE")!=null){
			m_order=req.getParameter("ORDER");
			m_type=req.getParameter("TYPE");


			}
		
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - Standing Order Approval</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("b_flag=0");
			out.println("m_order_by='"+m_type+"'");

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("}");
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_standing_order_approve&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("		if(b_flag==1){"); 
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_standing_order_approve';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please select a document\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order_approve?order="+m_order+"&type="+m_type+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order_approve?order="+m_order+"&type="+m_type+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_standing_order_approve\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Credit - Standing Order Approval - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Credit - Standing Order Approval - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_SO_NO.disabled=true;"); 
			out.println("document.Form1.TXT_START_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_END_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_BANK_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_ACC_NO.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
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

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");

			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");

			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("}");	

			
	 		out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
			out.println("if(document.Form1.SCREEN_NAME.value=='NEW'){");
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"REQUESTED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'FinanceSql_return','2');");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value=='EDIT'){");
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"RETURNED@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'FinanceSql_return','2');");
			out.println("}");
			out.println("}");

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SO_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_START_DATE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_END_DATE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[9];"); 

			out.println("}"); 

			out.println("function change(row) {"); 
			out.println(" b_flag=0");
			out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
			out.println(" b_flag=0");
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
			out.println(" b_flag=1");
			out.println("}");
			out.println("}"); 

			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_order+"'){");
			out.println("	   if('"+m_type+"'=='DESC'){");
			out.println("	      m_order_by = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by = 'DESC'; ");
			out.println("    }");
			out.println("  }");
	    out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_standing_order_approve?ORDER=\"+m_sort_col+\"&TYPE=\"+m_order_by"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			

///------------------------------------------------------------------------------------------------------------------------------
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Standing Order Approval</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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

			//----------------------------------------------------------------------------------------------------------------------------
			int j=0;
			
			
			rs = stmt.executeQuery ("SELECT SO_NO,FINANCE_NO,TO_CHAR(START_DATE,'DD-MM-YYYY'),TO_CHAR(END_DATE,'DD-MM-YYYY'),ACC_NO,BANK_CODE,TO_CHAR(AMOUNT,'99,999,999,999,999,999,999,999.99'),STATUS, "+
			" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BANK_CODE),'-')"+
			"FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS "+
			"WHERE STATUS='CANCEL' "+	 
			"ORDER BY "+m_order+"  "+m_type+"");

			out.println("<br>"); 
			out.println("<br>"); 

			out.println("<table align='center' border=\"0\" width='100%' class='table'>"); 
		  out.println("<tr class=pdn_txtpos2 >");


			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Standing Order No  '    onclick=sort_data('SO_NO') >Standing Order No</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('FINANCE_NO') >Finance No</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Start Date  '    onclick=sort_data('START_DATE') >Start Date</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - End Date  '    onclick=sort_data('END_DATE') >End Date</td>"); 
			
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Account No  '    onclick=sort_data('ACC_NO') >Account No</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Branch Code  '    onclick=sort_data('BANK_CODE') >Branch Code</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Branch Code  '    onclick=sort_data('BANK_CODE') >Branch Name</td>");  //
		  out.println("<td width='10%' align=right style= cursor:hand; title='Click here to sort by - Amount  '    onclick=sort_data('AMOUNT') >Amount</td>"); 
			out.println("<td width='10%' align=center>Status</td>"); 
			out.println("<td width='10%' align=center>Approve</td></tr>"); 
				
            while(rs.next()){
							
							if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									
                  	out.println("<tr class=tr_input >");
									}
							
							
									
			out.println("<td width='10%' align='left' >"+rs.getString(1) +"<input class='txt_input' type='hidden' name=TXT_SO_NO_"+j+" value=\""+rs.getString(1)+"\"></td>");
      out.println("<td width='10%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2) +"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\"></td>");
      out.println("<td width='10%' align='left'>"+rs.getString(3) +"<input class='txt_input' type='hidden' name=TXT_START_DATE_"+j+" value=\""+rs.getString(3)+"\"></td>");
      out.println("<td width='10%' align='left'>"+rs.getString(4) +"<input class='txt_input' type='hidden' name=TXT_END_DATE_"+j+" value=\""+rs.getString(4)+"\"></td>");
      out.println("<td width='10%' align='left' >"+rs.getString(5) +"<input class='txt_input' type='hidden' name=TXT_ACC_NO_"+j+" value=\""+rs.getString(5)+"\"></td>");
      out.println("<td width='10%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_branch_drill('"+rs.getString(6)+"')\"><U>"+rs.getString(6) +"<input class='txt_input' type='hidden' name=TXT_BRANCH_CODE_"+j+" value=\""+rs.getString(6)+"\"></td>");
			out.println("<td width='10%' align='left'>"+rs.getString(9) +"</td>");
      out.println("<td width='10%' align='right'>"+rs.getString(7) +"<input class='txt_input' type='hidden' name=TXT_AMOUNT_"+j+" value=\""+rs.getString(7)+"\"></td>");
      out.println("<td width='10%' align='center'>"+rs.getString(8) +"<input class='txt_input' type='hidden' name=TXT_STATUS_"+j+" value=\""+rs.getString(8)+"\"></td>");
			out.println("<td width='10%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change("+j+")\"></td>"); 
			out.println("</tr>");
     	j=j+1;
							
      }
          
			out.println("<tr>");
			out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			//----------------------------------------------------------------------------------------------------------------------------
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
				if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
		    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
