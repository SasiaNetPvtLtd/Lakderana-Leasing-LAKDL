//--
//SCREEN NAME:Credit Process - Cheque Printing/Disburse
//CREATED BY :DELANJALI	
//DATE/TIME  :25-01-2007
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_application_status_reversal extends javax.servlet.http.HttpServlet { 
	Connection conn;
	ServletOutputStream out = null;
	Statement stmt,stmt1,stmt2;
	public ResultSet rs,rs1,rs2;
	java.text.NumberFormat nf,nf1;

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
			
      String m_username = m_sn_methods.username;
			String m_head="";
		
			stmt=conn.createStatement();

			String fschema_name = m_sn_methods.schema_name;

			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);

			
			String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "DESC";
			
		
			if(req.getParameter("sort_column")!=null || req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");

		
			}

			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Application Status Reversal</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_order_by_type='DESC'");

			out.println("function before_submit(){ "); 
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("if(document.Form1.elements[chk].checked==false){"); 
			out.println("chk_chng=0");
			out.println("} ");
			out.println("else if (document.Form1.elements[chk].checked==true){");
			out.println("chk_chng=1");
			out.println("break");
			out.println("		}");
			out.println("}");
			out.println("		if(chk_chng==1){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_application_status_reversal';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			out.println("} "); 
			out.println("else{");
			out.println("alert('Please select a Application No for reversal')");
			out.println("}");
			out.println("} "); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_application_status_reversal';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_application_status_reversal';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_PRO_CR_display_application_status_reversal\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			


			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Credit Process - Application Status Reversal - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Credit Process - Application Status Reversal - \"+document.Form1.hid_status.value;"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_application_status_reversal?sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
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
			out.println("}"); 

//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Application Status Reversal "+m_head+" - New </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\" ></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			
			
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
		
			out.println("<table align='center' width='100%' class='table' borer=\"1\">"); 
			out.println("<tr>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=7 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\")' onmouseout='load_roll_value(\"Top\")'></td>");
      out.println("</tr>");
      out.println("</table>");
			
			rs= stmt.executeQuery		("SELECT DISTINCT "+ 
															"APPLICATION_NO, "+
															"NVL(MASTER_AGREEMENT_NO,'-') MASTER_AGREEMENT_NO, "+
															"NVL(FINANCE_NO,'-') FINANCE_NO, "+
															"NVL(CLIENT_CODE,'-') CLIENT_CODE,  "+ 
															""+fschema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME,  "+ 
															"DECODE(APPLICATION_STATUS,'ENTERED','Application Entered','ENT_CON','Application is edited','VERIFY1',  "+ 
															"'Verified','V-APP','Credit Score Evaluation Entered','VERIFY-M','Credit Approved - Level 1','VERIFY2',  "+ 
															"'Credit Approved - Level 2','VERIFYL','Finance No is Entered ','ACTIVATED','Purchase Order Entered') APP_STATUS,  "+ 
															"NVL(CURRENCY_CODE,'-') AS CURRENCY_CODE,  "+ 
															"APPLICATION_STATUS "+
															" ,"+fschema_name+".AF_CO_CHK_APP_DETAILS_ACT_DATE(APPLICATION_NO) "+//add by waruna 2011-04-28 to restict saving of finacial year finished data
															"FROM "+fschema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+ 
															"WHERE APPLICATION_STATUS IN ('VERIFY1','V-APP','VERIFY-M','VERIFY2','VERIFYL') "+
															"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
																			


			int j=0;
			
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			//out.println("<br>");		

			while(rs.next()){
			if(j==0){

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"15%\" align=\"left\"  style=cursor:hand;title='Click here to sort by - Application No' onclick=sort_data(\"APPLICATION_NO\")>Application No</td>");					
			out.println("<td width=\"15%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Master Agreement No' onclick=sort_data(\"MASTER_AGREEMENT_NO\")>Master Agreement No</td>");
			out.println("<td width=\"15%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Finance No' onclick=sort_data(\"FINANCE_NO\")>Finance No</td>");
			out.println("<td width=\"30%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Client Name' onclick=sort_data(\"CLIENT_NAME\")>Client</td>"); 			 
			out.println("<td width=\"20%\" align=\"left\"  style=cursor:hand;title='Click here to sort by - Application Status' onclick=sort_data(\"APP_STATUS\")>Application Status</td>"); 
			out.println("<td width=\"5%\" align=\"center\" >Reverse</td>"); 
			out.println("</tr>");		
			}
			
						
			if(j>0 && j%2==1){
    	out.println("<tr class=tr_input1 >");
			}
			else{
			
    	out.println("<tr class=tr_input >");
			}

			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			if(!rs.getString(2).equals("-")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_ML_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			}
			if(rs.getString(2).equals("-")){
			out.println("<td align=\"left\" >"+rs.getString(2)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_ML_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			}
			
			if(!rs.getString(3).equals("-")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			}
			if(rs.getString(3).equals("-")){
			out.println("<td align=\"left\">"+rs.getString(3)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			}
			if(!rs.getString(4).equals("-")){
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(4)+"')\"><U>"+rs.getString(5)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_CLIENT_CODE_"+j+" value=\""+rs.getString(4)+"\" ></td>"); 
			}
			if(rs.getString(4).equals("-")){
			out.println("<td align=\"left\" >"+rs.getString(5)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_CLIENT_CODE_"+j+" value=\""+rs.getString(4)+"\" ></td>"); 
			}
			out.println("<td align=\"left\" >"+rs.getString(6)+"<input type=\"hidden\" maxlength='10' size='20' name=TXT_APP_STATUS_"+j+" value=\""+rs.getString(8)+"\" ></td>"); 
			out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked "+rs.getString(9)+" onclick=\"check_change("+j+")\" ></td>");
			j=j+1;
			
			}
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
			out.println("<table class=table border='0' width='100%' >");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=7 align=right ><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(\"End\")'></td>");
			out.println("</tr></table>");
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\" ></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  

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
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		//		if(out!=null){try{out.close();  }catch(Exception e){}}
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}

		}
	}
}

