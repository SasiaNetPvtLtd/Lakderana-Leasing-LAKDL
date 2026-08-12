 
//Created by Chandana on 08-03-2007 
//Finance Staus Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_ODI_Write_off_report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("main_page")){
					 
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");				
				
		
			
			
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

	

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_Write_off_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_Write_off_report?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			

			out.println("function load_help_msg() {"); 
			//out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_display_credit_score_enter\";"); 
			//out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
	

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - ODI Waved Off Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - ODI Waved Off Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");

			out.println("document.Form1.BUT_HELP_MAIN_USER.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';"); 
			
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
											out.println("clear_data_edit_delete()");

			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}");  
			out.println("else if(m_val==\"DELETE\"){"); 
											out.println("clear_data_edit_delete()");
												out.println("clear_data_disable()");

			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.hid_save.value=\"Delete\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
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
			out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\"");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
  		out.println("if(IfCount=='12'){"); 
			out.println("		help_update_value_assign_12(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='14'){"); 
			out.println("		help_update_value_assign_14(oBj);"); 
			out.println("}");

		
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
			out.println("	clear_data();");
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


			out.println(" function Close_2(){");
			out.println("clear_data()	");
      out.println(" }");	
				


			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"12\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");			
			out.println("if(document.Form1.hid_help_type.value==\"14\"){");
			out.println("document.Form1.TXT_CLIENT_NO.value='';");
			out.println("}");
			out.println("}");
			
			out.println("function help_update_value_assign_12(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[4];");
			out.println("}"); 
				
			out.println("function help_update_value_assign_14(oBj) {"); 
			out.println("    document.Form1.TXT_CLIENT_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			
			out.println("function help_update_finance() {");
			out.println("    document.Form1.hid_help_type.value=\"12\";"); 
			out.println("    Sql = \"FinanceSql_Odi\";"); 
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_NO.value+\"@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,12);"); 
			out.println("}");
		
			out.println("function help_update_client() {");
			out.println("    document.Form1.hid_help_type.value=\"14\";"); 
			out.println("    Sql = \"ClientSql_odi\";"); 
			out.println("    Crit = document.Form1.TXT_CLIENT_NO.value+\"@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,14);"); 
			out.println("}"); 
				
			
			
			out.println("function report_window(obj1,obj2)	{");
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_Write_off_report?chksql=detail&FIN_NO=\"+obj1+\"&CLN_NO=\"+obj2+\" \";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			out.println("}");
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println("}");			
			
			 out.println("function set_amount_drill(fin_no,client_no){");
			 out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_Write_off_report?chksql=settled_detail&fin_no=\"+fin_no+\"&cln_no=\"+client_no+\" \";");	
			 out.println("window.open(m_url,'w01','top=150,left=150,width=500,height=400,resizable=1');");
			 out.println("}");		
				
				
				
				
        out.println("</Script>");
				
				
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Credit Process - ODI Waved Off Report </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
												
				out.println("</table>");			
				
			out.println("<br>");	
			out.println("<table align='center' width='100%' class='table' border='0'>"); 			
			
			out.println("<tr >"); 
			out.println("<td width='2%'>&nbsp;</td>");
			out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='10' size='10' onblur=\"help_update_client()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\"></td>");
		  out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >");
			out.println("<td width='2%'>&nbsp;</td>");
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='10' onblur=\"help_update_finance()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_update_finance()\"></td>"); 
			out.println("<td width='10%'><input type=\"button\" class='but_input' style='width:60px' onclick='report_window(document.Form1.TXT_FINANCE_NO.value,document.Form1.TXT_CLIENT_NO.value )' value=\"View\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 			
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
		
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
							
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("</table>");
		
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</html>");
			
						
			
			
      }
			
			//=========================================================================================================================			
			
			else if(m_chksql.trim().equals("detail")){
			
			 String m_fin_no = req.getParameter("FIN_NO");
			 String m_cln_no = req.getParameter("CLN_NO");
			 double m_doi_cal=0;
			 double m_odi_bal=0;
			 double m_odi_settled=0;
			 double  m_tot_adj=0,m_tot_set=0;
				                               
																
																
																
	rs = stmt.executeQuery( " SELECT APPROVED2_DATE,AJUSTED_AMOUNT,ODI_AMT,ODI_SETTLED_AMOUNT,APPROVED1_BY,APPROVED2_BY,ODI_BAL,RATE "+
													" FROM ("+
	                        " SELECT TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY') APPROVED2_DATE,  "+
												  " SUM(A.AJUSTED_AMOUNT) AJUSTED_AMOUNT,  "+
												 	" "+m_schema_name+".AF_CO_GET_ODI_AMT('"+m_fin_no+"',TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY')) ODI_AMT,  "+
												  //" SUM(B.ODI_SETTLED_AMOUNT) ODI_SETTLED_AMOUNT,  "+
													" "+m_schema_name+".AF_CO_GET_ODI_SETTLED_AMT('"+m_fin_no+"',TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY')) ODI_SETTLED_AMOUNT,  "+
													" "+m_schema_name+".AF_CO_GET_USER_NAME(A.APPROVED1_BY)  APPROVED1_BY,  "+
													" "+m_schema_name+".AF_CO_GET_USER_NAME(A.APPROVED2_BY)  APPROVED2_BY, "+
												 	" "+m_schema_name+".AF_CO_GET_ODI_BAL('"+m_fin_no+"',TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY')) ODI_BAL ,  "+
												  " SUM(A.AJUSTED_AMOUNT)/"+m_schema_name+".AF_CO_GET_ODI_AMT('"+m_fin_no+"',TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY'))*100  RATE "+
													" FROM "+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET A, "+ 
													" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY B  "+
													" WHERE A.ODI_NO   = B.ODI_REF_NO  "+
													" AND A.INVOICE_NO = B.INVOICE_NO  "+
													" AND A.STATUS = 'APR'  "+
													" AND B.INVOICE_NO IN (SELECT C.INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE C  "+
													"                      WHERE C.FINANCE_NO ='"+m_fin_no+"' )  "+
													" GROUP BY  TO_CHAR(A.APPROVED2_DATE,'DD-MM-YYYY'),A.APPROVED1_BY,A.APPROVED2_BY	"+
													" ) ORDER BY ROWNUM DESC ");
													
			
			
			boolean more = rs.next();
			int j=1;	
			out.println("<table border='0' width='100%' class='table'>"); 	
			
			if(!more){
			out.println("<tr>"); 
			out.println("<td width='100%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
			out.println("</tr>"); 
			}
			
			if(more){
			out.println("<tr class=pdn_txtpos2><td width='10%'><b>Date</td>");
			out.println("<td width='10%'  align='right'><b>Calculated Amount</td>");
			out.println("<td width='10%'  align='right'><b>Waved off Amount</td>");
			out.println("<td width='10%'  align='right'><b>Settled Amount</td>");
			out.println("<td width='10%'  align='right'><b>Balance Amount</td>");
			out.println("<td width='10%'  align='right'><b>Percentage</td>");
			out.println("<td width='20%'  ><b>Approval 1</td>");
			out.println("<td width='20%'  ><b>Approval 2</td>");
			out.println("<tr>");
			
			while(more){
			
			if(j%2==1){
			out.println("<tr class=tr_input>"); 
			}
			else{
			out.println("<tr class=tr_input1>"); 
			}
			if(j==1){
			m_doi_cal = rs.getDouble(3);
			m_odi_settled = rs.getDouble(4);
			m_odi_bal = m_doi_cal-(rs.getDouble(2)+rs.getDouble(4));
			
			}
			else{
			m_doi_cal = m_odi_bal;
			if(m_odi_settled>0){
			m_odi_settled = m_odi_settled - rs.getDouble(4);
			}else{
			m_odi_settled = 0;
			}
			m_odi_bal = m_doi_cal-(rs.getDouble(2)+m_odi_settled);
			
			}
			
			out.println("<td width='10%'  >"+rs.getString(1)+"</td>");
			out.println("<td width='10%'  align='right'>"+nf.format(m_doi_cal)+"</td>");
			out.println("<td width='10%'  align='right'>"+nf.format(rs.getDouble(2))+"</td>");
			out.println("<td width='10%'  align='right'>"+nf.format(m_odi_settled)+"</td>");
			out.println("<td width='10%'  align='right'>"+nf.format(m_odi_bal)+"</td>");			
			out.println("<td width='10%'  align='right'>"+nf.format(rs.getDouble(2)/m_doi_cal*100)+"%</td>");
			out.println("<td width='20%'  >"+rs.getString(5)+"</td>");
			out.println("<td width='20%'  >"+rs.getString(6)+"</td>");
			out.println("<tr>");
			j++;

			m_tot_adj = m_tot_adj+rs.getDouble(2);
			m_tot_set = m_tot_set+m_odi_settled;
			
			more = rs.next();
			
			}
			
			out.println("<tr>");
			out.println("<td width='10%'  ><b>Total</td>");
			out.println("<td width='10%'  align='right'>&nbsp;</td>");
			out.println("<td width='10%'  align='right'><b>"+nf.format(m_tot_adj)+"</td>");
			out.println("<td width='10%'  align='right' style='cursor:hand' onclick=set_amount_drill('"+m_fin_no+"','"+m_cln_no+"')><b>"+nf.format(m_tot_set)+"</td>");
			out.println("<td width='10%'  align='right'>&nbsp;</td>");			
			out.println("<td width='10%'  align='right'>&nbsp;</td>");
			out.println("<td width='20%'  >&nbsp;</td>");
			out.println("<td width='20%'  >&nbsp;</td>");
			out.println("<tr>");
			out.println("<tr>"); 
			
			
			}
			out.println("</table>"); 	
				
				
		}		
		else if(m_chksql.trim().equals("settled_detail")){
			
			String cli_no   = req.getParameter("cln_no");
			String fin_no   = req.getParameter("fin_no");
			int j=1;
			double m_tot=0;
			
			out.println("<html>");			
			out.println("<head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("<title>ODI Settled Details");
			out.println("</title>");
		  out.println("</head>");
			out.println("<body>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table' cellpadding=0 cellspacing=0 >"); 	
			out.println("<tr width='100%'><td align='center'><b><u>ODI Settlement Details<td></tr>");
			out.println("</table>");
			out.println("<br>");
			/*
			rs1 = stmt1.executeQuery( " SELECT DISTINCT B.RECEIPT_NO,TO_CHAR(C.EFF_VALDATE,'DD-MM-YYYY'),A.ODI_SETTLED_AMOUNT "+
																" FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
																" WHERE A.INVOICE_NO = B.INVOICE_NO "+
																" AND   B.RECEIPT_NO = C.REC_NO "+
																" AND  C.status NOT IN ('C','CAD') "+
																" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM  "+m_schema_name+".AF_CO_PRO_INVOICE "+
																"                      WHERE FINANCE_NO = '"+fin_no+"' ) "+
																" AND RECEIPT_NO IN (SELECT A.REC_NO "+
																" FROM  "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
																" WHERE TO_DATE(TO_CHAR(B.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND "+
																" A.REC_NO = B.REC_NO AND CLIENT_CODE = '"+cli_no+"' ) ");
			
			
			*/
			
			rs1 = stmt1.executeQuery( " SELECT RECEIPT_NO,TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'),NVL(SUM(SETTELED_AMOUNT),0)"+	
			                          " FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
														    " WHERE INVOICE_NO IN ( "+
														    " SELECT ODI_REF_NO "+
														    " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
														    " WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
														    " WHERE FINANCE_NO='"+fin_no+"' ) "+
														    " ) AND "+
														    " RECEIPT_NO IN (SELECT A.REC_NO "+
														    " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
														    " WHERE "+
														    " A.REC_NO    = B.REC_NO AND "+
														    " CLIENT_CODE = '"+cli_no+"' )"+
																" GROUP BY RECEIPT_NO,ALLOCATED_DATE" ); 
			
			
			
			
			boolean more= rs1.next();
			//out.println(more);
			out.println("<table border='1' width='100%' class='table' cellpadding=0 cellspacing=0 >"); 	
			out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width='1%'><b>No</td>");
			out.println("<td width='10%'><b>Receipt No</td>");
		  out.println("<td width='10%' align='left'><b>Settled Date</td>");			
			out.println("<td width='10%' align='right'><b>Settled Amount</td>");			
			out.println("<tr>");
			
			
			while(more){
			out.println("<tr>");
			out.println("<td width='1%'>"+j+"</td>");
			out.println("<td width='10%'>"+rs1.getString(1)+"</td>");
		  out.println("<td width='10%'  align='left'>"+rs1.getString(2)+"</td>");			
			out.println("<td width='10%'  align='right'>"+nf.format(rs1.getDouble(3))+"</td>");			
			out.println("<tr>");
			m_tot = m_tot+rs1.getDouble(3);
			more= rs1.next();
			j++;
			}
			out.println("<tr>");
			out.println("<td width='10%'  colspan=3 align='right'>Total</td>");			
			out.println("<td width='10%'  align='right'>"+nf.format(m_tot)+"</td>");			
			out.println("<tr>");
			
			out.println("</table>");
			
			out.println("</body>");
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
