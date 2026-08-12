//Option Id is 4.2  
//This File was created by SVA on 17-05-2006 
//Collection Invoice
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_PRO_Cr_book_pledge_reversal extends javax.servlet.http.HttpServlet {
	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		Connection conn = null;
		Statement stmt = null;
		java.text.NumberFormat nf = null;
		ResultSet rs = null;
		 String m_chksql = null;
		ServletOutputStream out = null;
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username			= con_method.username;
			String header_name    = con_method.header_name;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if (m_chksql.trim().equals("main_page")) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
				
				out.println("<Script>");
				out.println("var m_bsubmit = '0';");
				
				
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function help_finance() {"); 
				out.println(" 	document.Form1.hid_help_type.value=\"4\";"); 
				//out.println(" 	Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); // commented by udara 11-07-2019			
				//out.println(" 	HelpBox('1','10','0',Crit,'m_help_TXT_finance_no_sql','4');"); // commented by udara 11-07-2019
				out.println(" 	Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_LOAN_NO.value+\"@\";"); // added by udara 11-07-2019			
				out.println(" 	HelpBox('1','10','0',Crit,'m_help_TXT_finance_no_all_2_sql','4');"); // added by udara 11-07-2019
				out.println("}");
				
				
				out.println("function help_branch() {"); 
				out.println("bttn_help=\"99\";");
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_BRANCH_CODE_sql_new\";"); 
	            out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+document.Form1.TXT_BANK_CODE.value+\"@\"+\"Y@\";");
				out.println(" 	HelpBox('1','10','0',m_criteria,'m_help_TXT_BRANCH_CODE_sql_new','99');");
				out.println("}"); 
				
				out.println("function help_bank() {");
				out.println("bttn_help=\"1\";");
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
				out.println("var bank=document.Form1.TXT_BANK_CODE.value.substring(0,1)");
				out.println("    m_criteria = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
			    out.println(" 	HelpBox('1','10','0',m_criteria,'m_help_TXT_BANK_CODE_sql','1');");
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_loan() {");
				out.println("bttn_help=\"10\";");
				out.println("    document.Form1.hid_help_type.value=\"10\";"); 
				out.println("    m_sql = \"m_help_TXT_LOAN_NO_sql\";"); 
				//out.println("    m_criteria = document.Form1.TXT_LOAN_NO.value+\"@Y@\";"); // commented by udara 11-07-2019
				out.println("    m_criteria = document.Form1.TXT_LOAN_NO.value+\"@Y@\"+document.Form1.TXT_BANK_CODE.value+\"@\"+document.Form1.TXT_BRANCH_CODE.value+\"@\";"); // added by udara 11-07-2019
				out.println(" 	HelpBox('1','10','0',m_criteria,'m_help_TXT_LOAN_NO_sql','10');");
				out.println("}"); 
				out.println(""); 
			
			
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				//out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_CR_BOOK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_CR_BOOK_help_select\"+"); 
				out.println("    \"&Sql_in=\"+Sql+\"&Start_in=\"+Start+"); 
				out.println("    \"&End_in=\"+End+\"&Crit_In=\"+Crit+"); 
				out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				out.println("	");
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='4'){"); 
				out.println("		finance_assign(oBj);"); 
				out.println("	}");
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
		  	    out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("		help_update_value_assign_1(oBj);"); 
		  	    out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
				out.println("		help_update_value_assign_10(oBj);"); 
		  	    out.println("		}");	
				// added by udara 11-07-2019
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("		help_update_value_assign_4(oBj);"); 
		  	    out.println("		}");
				// end by udara 11-07-2019
				out.println("	}"); 
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
				out.println("	else{	"); 
				out.println("    clear_fields(); ");
				out.println("	}	"); 
				out.println("	}	"); 
				out.println("}");  
				
				out.println("function finance_assign(oBj) {");				
				out.println("document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			    out.println("    document.Form1.TXT_VEH_NO.value=oBj.valout[3];");
				out.println("}");
				
				out.println("function help_update_value_assign_99(oBj) {"); 
			    out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");
			    out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[4];");
				out.println("}"); 			
			
			
				out.println("function help_update_value_assign_1(oBj) {"); 
				out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[2];"); 
				out.println("}"); 	
		
				out.println("function help_update_value_assign_10(oBj) {"); 
				out.println("    document.Form1.TXT_LOAN_NO.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[3];"); // added by udara 11-07-2019
				out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[4];"); // added by udara 11-07-2019
				out.println("}"); 
				
				// added by udara 11-07-2019
				out.println("function help_update_value_assign_4(oBj) {"); 
				out.println("    document.Form1.TXT_LOAN_NO.value=oBj.valout[4];"); 
				out.println("}"); 
				// end by udara 11-07-2019
			
				out.println("function clear_fields(){ ");
				out.println("if(document.Form1.hid_help_type.value==\"4\"){");
				out.println("document.Form1.BRANCH_CODE.value=\"\";");
				out.println("document.Form1.TXT_VEH_NO.value=\"\";");
				out.println("}");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				// added by udara 11-07-2019
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_pledge_reversal?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				// end by udara 11-07-2019
				
				
				out.println("</Script>");
				
				String m_report_gen_date="";
				String m_dayend_date="";
				String m_dayend_user="";
				
				String m_rpt_date="-";
				String m_rpt_user="-";
				

				
				out.println("<body  class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"Hid_scr_name\" value=\"AF_PRO_CR_BOOK_RMV\">");
				out.println("<input type=hidden name=\"OPTION_NAME\" value=\"NEW\">");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"New\">");
				out.println("<input type=hidden name=\"hid_help_type\" value=\"\">");
				out.println("<input type=hidden name=\"m_batch_no\" value=\"\">");
				//out.println("<input type=hidden name=\"INQ_NO\" value=\"\">");
				
				
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
				out.println("<tr>");
				out.println("<td width=\"8\" valign=\"top\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				
				out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" class=table cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"6%\" class=\"pdn_mainHD\" class></td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box>CR Book Pledge Withdrawal</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align='center' width=\"60%\" class=table>");
				
				/*out.println("<tr class=tr_input>");
				out.println("<td >Finance No</td>");
				out.println("<td><input name=\"BRANCH_CODE\"   type=\"text\" class=\"txt_input\" style=\"width:150px;\" value=''> ");
				out.println("<input class='but_input' type='button' name='BUT_BRANCH_CODE' value=\"...\" onClick=\"help_branch()\" > </td>"); 
				out.println("<td><input name=\"VIEW_BTN\"   value='View' style=\"width:120px;\" id='enableBtn' onclick='load_data()'type=\"button\" class='but_input'> ");
				//out.println("<input name=\"SAVE_BTN\"   value='Save' style=\"width:120px;\" id='enableBtn' onclick='saveBatch()' type=\"button\" class='but_input'></td>");
				out.println("</td>");
				out.println("</tr>");*/
				
				out.println("<tr >"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_BANK_CODE'  class=div_input>Bank </DIV></td>"); 
				out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_CODE' maxlength='5' size='5' >"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_BANK_CODE' value=\"Help\" onClick=\"help_bank()\"></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
	       
		        out.println("<tr >"); 
	            out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch </DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10' >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_branch()\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				out.println("<tr >"); 
	            out.println("<td width='20%' ><DIV id='DIV_TXT_LOAN_NO'  class=div_input>Loan No</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_LOAN_NO' maxlength='50' size='50' >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_loan()\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
			
				out.println("<tr >"); 
	            out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No  </DIV></td>"); 
				out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='50' size='50'>"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_finance()\" ></td>"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_VEH_NO'  class=div_input>Vehicle No </DIV></td>"); 
				out.println("<td  width='10%' ><input class='txt_input' type='text' name='TXT_VEH_NO' maxlength='25' size='25' disabled>"); 
				out.println("</td>"); 
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='VIEW_BTN' value=\"View\" onClick=\"load_data()\" style='{width=150px}'>");
				out.println("</td>"); 
				out.println("</tr>");
				
				
				
				
				
				
				
				out.println("</table>");
				
				out.println("<br/><br/><br/><br/>");
				
				out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align='center' width=\"100%\" class=table>");
				out.println("<tr class=tr_input>");
				out.println("<td align='center'><div id='data'><img style='{width:30em;height:15em;display:none}' src='"+m_html_client_url+"/loader.gif' id='loader'></div></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<div  id='dataTable'></div>");
				out.println("</table>");
				
				
				out.println("<br/><br/><br/><br/>");
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td class=\"line\" height=\"1\">");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
				out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
				out.println("<tr class=tr_input>");
				
				out.println("<td style=\"width: 6px\"></td>");
				//out.println("<td width=60%>&nbsp;</td>");
				out.println("<td width=20%>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick='close_window()' ></td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick='clear_window()' ></td>");
				
				out.println("<td width=60%>&nbsp;</td>");
				
				out.println("<td><input type=button name=reset_1 value=\"Withdraw All\" class=mainbut onclick='save_all()' ></td>"); // added by udara 25-07-2019
				
				out.println("<td width=10%>&nbsp;</td>");
				
				out.println("</tr></table>");
				out.println("&nbsp;&nbsp;</td>");
				out.println("</tr>");
				
				out.println("<tr class=tr_input>");
				out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");    
				out.println("<tr class=tr_input> ");
				out.println("<td valign=\"bottom\" height=\"20\"></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</body>");
				
				out.println("<SCRIPT language1.2=\"JavaScript\" src=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT>");
				out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/jquery/fancybox/jquery-1.4.1.min.js'></script>");
				out.println("       <script type=\"text/javascript\" src='"+m_html_client_url+"/crBook/cr_book_pledge_reversal.js'></script>");
				
				out.println("</html>");
				
				
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
