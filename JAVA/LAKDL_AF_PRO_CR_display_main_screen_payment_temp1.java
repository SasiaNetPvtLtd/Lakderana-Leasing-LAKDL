//--
//SCREEN NAME:Credit Process Temporary Payment Higher Approval - NEW
//CREATED BY:Delanjali
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_main_screen_payment_temp1 extends javax.servlet.http.HttpServlet { 

	Connection conn;
	ServletOutputStream out = null;
	java.text.NumberFormat nf;
	public ResultSet rs;
	Statement stmt;
	public String m_sql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt = conn.createStatement();
			
			String m_schema_name = m_sn_methods.schema_name;
			String m_username =  m_sn_methods.username;	
			String m_sort_column   = "";	
			String m_order_by_type = "";

			
		  m_sql = req.getParameter("sql");
		
			if (m_sql.trim().equals("idle")) {
			out.println("idle");
			}

			else if(m_sql.trim().equals("main_page")){
		  m_sort_column   = "PAYMENT_NO";	
		  m_order_by_type = "DESC";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			}

	
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Payment Requisition - Special Approval - New</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_order_by_type");

			out.println("function check_po(){ "); 
			out.println("if(!count_po()){"); 
			out.println("alert('Please Select Payment No to approve');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else");
			out.println("b_flag=0;");
      out.println("}"); 

			out.println("function count_po(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<document.Form1.hid_count.value;i++){");
			out.println("m_chk_tmp=\"CHK_APP_\"+i;");
			out.println("if(document.Form1.elements[m_chk_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			
			out.println("check_po()");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_temp1';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("} "); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp1?sql=main_page';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp1?sql=main_page';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_payment_temp\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance - Payment Requisition - Special Approval - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance - Payment Requisition - Special Approval - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.hid_save.value=\"Delete\";");
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

			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'DESC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp1?sql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println(" window.location.href=m_url;"); 
			out.println("}");

			out.println("function check_change(row) {"); 
			out.println("  chk=\"CHK_APP_\"+row;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"N\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"Y\";");
			out.println("chk_chng=0");
			out.println("}");
			
			out.println(" if(document.Form1.elements[\"TXT_STATUS\"+row].value==\"N\"){");
      out.println("  alert('Invoice will be canceled')");
	    out.println("}");

			out.println("}");
			
			out.println("function close_window_1() {");
			out.println("close_window()");
			out.println("}");
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
			out.println("function call_alert(row) {");
			out.println(" if(document.Form1.elements[\"TXT_STATUS\"+row].value==\"N\"){");
      out.println("  alert('Invoice will be canceled')");
	    out.println("}");
      out.println("}");
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Requisition - Special Approval - New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
		
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<br>");
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_top     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_bot);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");

			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");

			rs= stmt.executeQuery(			
			"SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+//--1
			"E.PAYMENT_NO PAYMENT_NO, "+//--2
			"C.FINANCE_NO FINANCE_NO, "+//--3
			"C.CLIENT_CODE , "+//--4
			""+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+//--5
			"B.SUS_REF_NO SUS_REF_NO, "+//--6
			"B.REF_NO REF_NO, "+//--7
			"F.REG_NO REG_NO, "+//--10
			"F.ENGINE_NO ENGINE_NO, "+//--8
			"NVL(F.CHASSIS_NO,'-') CHASSIS_NO, "+//--9
			"NVL(D.REG_NO,'-') REG_NO1, "+//--13
			"NVL(D.ENGINE_NO,'-') ENG_NO, "+//--11
			"NVL(D.CHASSIS_NO,'-') CHAS_NO, "+//--12
			"E.PAY_AMOUNT, "+
			"E.LIC_BRANCH_CODE, "+
			"TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') "+
			"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
			""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E, "+
			""+m_schema_name+".AF_CR_PRO_PAY_VEHICLE_DETAILS F "+
			"WHERE "+//UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') AND "+
			"B.REF_NO=D.INVOICE_NO "+
			"AND D.APPLICATION_NO=C.APPLICATION_NO "+
			"AND B.SUS_REF_NO=E.SUS_REF_NO "+
			"AND E.PROCESS_STATUS='TEMP' "+
			"AND E.PAYMENT_NO=F.PAYMENT_NO "+
			"AND B.REF_NO=F.INVOICE_NO "+
			"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
						
			
			boolean more=rs.next();
			int j=0;
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">"); 
		
			while(more){
									
	
			if(j==0){

			out.println("<tr class=\"pdn_txtpos2\">");
			
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by -  Payment No  \" onclick=sort_data(\"PAYMENT_NO\")>Payment No</td>"); 
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Invoice No  \" onclick=sort_data(\"REF_NO\")>Invoice No</td>"); 
			out.println("<td width=\"13%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Client Name \" onclick=sort_data(\"NAME\")>Client Name</td>");
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Vehicle No  \" onclick=sort_data(\"REG_NO\")>Vehicle No-Temp</td>"); 
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Engine No  \" onclick=sort_data(\"ENGINE_NO\")>Engine No-Temp</td>"); 
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Chassis No  \" onclick=sort_data(\"CHASSIS_NO\")>Chassis No-Temp</td>"); 
			out.println("<td width=\"8%\" align=\"left\" 	style=cursor:hand; title=\"Click here to sort by - Vehicle No  \" onclick=sort_data(\"REG_NO1\")>Vehicle No </td>"); 
			out.println("<td width=\"8%\" align=\"left\"  style=cursor:hand; title=\"Click here to sort by - Engine No  \" onclick=sort_data(\"ENG_NO\")>Engine No</td>"); 
			out.println("<td width=\"8%\" align=\"left\" 	style=cursor:hand; title=\"Click here to sort by - Chassis No  \" onclick=sort_data(\"CHAS_NO\")>Chassis No</td>"); 
			out.println("<td width=\"6%\" align=\"center\">Status</td>"); 
			out.println("<td width=\"2%\" align=\"center\">&nbsp</td>"); 

			out.println("</tr>");

			}
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{									
      out.println("<tr class=tr_input >");
			}
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(2)+"\"></td>"); 
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(7)+"')\"><U>"+rs.getString(7)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(4)+"')\"><U>"+rs.getString(5)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_CLIENT_CODE_"+j+" value=\""+rs.getString(4)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_CLIENT_NAME_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(8)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REG_NO_TEMP_"+j+" value=\""+rs.getString(8)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(9)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_ENGINE_NO_TEMP_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(10)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_CHASSIS_NO_TEMP_"+j+" value=\""+rs.getString(10)+"\"></td>"); 
			out.println("<td align=\"left\" >"+rs.getString(11)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REG_NO_"+j+" value=\""+rs.getString(11)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(12)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_ENGINE_NO_"+j+" value=\""+rs.getString(12)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(13)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_CHASSIS_NO_"+j+" value=\""+rs.getString(13)+"\"></td>"); 
			out.println("<td align=\"center\"><select name=TXT_STATUS"+j+" class=\"txt_input5\" onchange=\"call_alert("+j+")\">");
			out.println("<option value=\"N\" >New</option>");
			out.println("<option value=\"O\" >Old</option></select></TD>");
		
			out.println("<td  align=\"center\" ><input type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\"></td>");
			out.println("</tr>");
			out.println("<input  class=\"txt_input\" type=\"hidden\" name=TXT_APP_NO_"+j+" value=\""+rs.getString(1)+"\">"); 
			out.println("<input  class=\"txt_input\" type=\"hidden\" name=TXT_SUS_REF_NO_"+j+" value=\""+rs.getString(6)+"\">");
			out.println("<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAID_AMT_"+j+" value=\""+rs.getDouble(14)+"\">");
			out.println("<input  class=\"txt_input\" type=\"hidden\" name=TXT_BRANCH_"+j+" value=\""+rs.getString(15)+"\">");
			out.println("<input  class=\"txt_input\" type=\"hidden\" name=TXT_VAL_DATE_"+j+" value=\""+rs.getString(16)+"\">");

			more=rs.next();
			j=j+1;
			}
		
			out.println("<input type=\"hidden\" name=hid_count value="+j+"></td>");
			out.println("</table>");
			
			
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>");
			
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_bot     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_top);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");

			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("<br>"); 
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
