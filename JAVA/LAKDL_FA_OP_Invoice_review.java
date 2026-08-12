      
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_OP_Invoice_review extends javax.servlet.http.HttpServlet { 
  Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;
	ServletOutputStream out = null;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		 
			     
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			
			String m_chksql = req.getParameter("chksql");
			stmt=conn.createStatement();
			 
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoice Review</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_review?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_review?chksql=main_page';"); 
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
			out.println("	help_box.innerHTML=\" Invoice Review- \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Invoice Review \";"); 
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
			//out.println(" m_sql = \"m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql\";"); 
			out.println(" m_sql = \"m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql_new\";"); 
			out.println(" m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 
			out.println(" HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_facility() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"2\";"); 
			//out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_FACILITY_sql_new\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_1() {"); 
			out.println("		document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_value_assign_2() {"); 
			out.println("		document.Form1.TXT_FACILITY_NO.value=oBj.valout[2];"); 
			out.println("}");
			
			out.println("function help_update_debtor() {"); 
			out.println(" 	document.Form1.hid_help_type.value=\"3\";"); 
			//out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_sql\";"); 
			out.println(" 	m_sql = \"m_help_DIV_TXT_DEBTOR_SQL_NEW\";"); 
			out.println(" 	m_criteria = document.Form1.TXT_DEBTOR_CODE.value+\"@\"+document.Form1.TXT_FACILITY_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\";");
			out.println(" 	HelpBox('1','10','0');"); 
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
			
			
			out.println("function load_invoces() {");
			out.println("client_code = document.Form1.TXT_CLIENT_CODE.value");
			out.println("facility_code = document.Form1.TXT_FACILITY_NO.value");
			out.println("debtor_code = document.Form1.TXT_DEBTOR_CODE.value");
			out.println(" if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_review?chksql=review_invoice_detail&client_code=\"+client_code+\"&facility_code=\"+facility_code+\"&debtor_code=\"+debtor_code+\"\";");
			out.println("load_interface(m_url,'NO');");
			out.println("}");
			out.println("}");
			
			out.println("function validate_data(){");
			out.println("client_code = document.Form1.TXT_CLIENT_CODE.value");
			out.println("facility_code = document.Form1.TXT_FACILITY_NO.value");
			out.println("debtor_code = document.Form1.TXT_DEBTOR_CODE.value");
			out.println("if(client_code!=\"\" && facility_code!=\"\" && debtor_code!=\"\"){");
			out.println("return true;");
			out.println("}else{");
			out.println("return false;");
			out.println("}");
			out.println("}");
			
			out.println("function get_vector_normal(m_data){");
			out.println("invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function change_status(k){");
			out.println("if(document.Form1.elements['CHK_'+k].checked==true){");
			out.println("document.Form1.elements['CHK_'+k].value='on';");
			out.println("}");
			out.println("else if(document.Form1.elements['CHK_'+k].checked==false){");
			out.println("document.Form1.elements['CHK_'+k].value='off';");
			out.println("}");
			out.println("}");
			
			
			out.println("function before_submit(){ "); 
			out.println("	if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to save ?\")){ ");
			out.println("			for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("				document.Form1.elements[i].disabled=false;");
			out.println("			}");
			out.println("			if(validate_data()){"); 
			out.println("				document.Form1.action='"+m_class_url+"/"+m_fschema_name+"FA_OP_Save_Invoice_review';");  
			out.println("				document.Form1.submit();"); 
		  out.println("	    }"); 			
			out.println("		}"); 
			out.println("	}else{"); 
			out.println("alert('Fields values cannot be null...!');"); 
			out.println("}"); 
			out.println("}"); 
			
		
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Invoice Review </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
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
			out.println("<table class='table' width='100%'  border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input><b>Client Code</b></DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='10' size='50' style='width:100' onblur=\"help_update()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%' align='left' ></td>");
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FACILITY_NO'  class=div_input><B>Facility No</B></DIV></td>"); 
			out.println("<td width='17%' ><input class='txt_input' type='text' name='TXT_FACILITY_NO' maxlength='20' size='10' style='width:100' onblur=\"help_update_facility()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' value=\"Help\" onClick=\"help_update_facility()\"></td>"); 
			out.println("<td width='*%' align='left' ></td>");
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_DEBTOR_CODE'  class=div_input><b>Debtor Code </b></DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_DEBTOR_CODE' maxlength='10' size='10' style='width:100' onblur=\"help_update_debtor()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_DEBTOR_MAIN' value=\"Help\" onClick=\"help_update_debtor()\">");
			out.println("</td>");
			out.println("<td width='10%' align='center'>");
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_invoces()\">");
			out.println("</td>"); 
			out.println("<td width='*%'>");
			out.println("</tr>");			
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<br>");
			out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
			}
			else if(m_chksql.equals("review_invoice_detail")){
			
			String m_client_code = req.getParameter("client_code");
			String m_debtor_code = req.getParameter("debtor_code");
			String m_facility_code = req.getParameter("facility_code");
			int j=1;
			int i=1;
			
			 rs= stmt.executeQuery(" SELECT "+
														 " A.INVOICE_NO, "+//1
														 " TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'), "+//2
														 " B.INVOICE_BATCH_DATE, "+//3
														 " NVL(A.INVOICE_AMOUNT,0), "+//4
														 " NVL(A.BATCH_NO,'-') "+	//5
														 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A ,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
														 " WHERE A.BATCH_NO     = B.BATCH_NO "+
														 " AND A.SETTLE_AMOUNT  = 0 "+
														 " AND A.INVOICE_STATUS = 'CONF' "+
														 " AND A.DEBTOR_CODE = '"+m_debtor_code+"' "+
														 " AND B.CLIENT_CODE = '"+m_client_code+"' "+
														 " AND B.FACILITY_NO = '"+m_facility_code+"' "+
														 " ORDER BY B.INVOICE_BATCH_DATE ");
  
			boolean more = rs.next();
			
			
			out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");				
				out.println("<td width='5%' ><DIV class=div_input><b>No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input><B>Value Date</b></DIV></td>");
				out.println("<td width='15%' align='right' ><DIV class=div_input><B>Amount</b></DIV></td>");
				out.println("<td width='5%'  align='center'><DIV class=div_input><B>&nbsp;</b></DIV></td>");
				out.println("</tr>");
				
				while(more){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
				out.println("<td width='5%' ><DIV class=div_input>"+i+"</DIV></td>");
				out.println("<td width='20%' ><DIV class=div_input>"+rs.getString(1)+"</DIV><input type='hidden' name='HID_INV_"+i+"' value=\""+rs.getString(1)+"\"></td>");
				out.println("<td width='20%' ><DIV class=div_input>"+rs.getString(5)+"</DIV><input type='hidden' name='HID_BATCH_"+i+"' value=\""+rs.getString(5)+"\"></td>");
				out.println("<td width='20%' ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>");
				out.println("<td width='15%' align='right' ><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>");
				out.println("<td width='5%'  align='center'><DIV class=div_input><input type='checkbox' name='CHK_"+i+"' value='off' onclick=\"change_status("+i+")\"></b></DIV></td>");
				out.println("</tr>");
			  i++;
				more = rs.next();
				}
				out.println("<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">");
				out.println("</table>");
				
			
			
			
			}
			
			out.flush();
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
