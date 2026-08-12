/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//  CREATED BY DINETH MEEMANAGE
//  DATE:2008-09-02
//  PURPOSE:FOR THE TERMINATION APPROVAL PROCESS
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder;

import java.util.*;

import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_TerminationApproval2 extends javax.servlet.http.HttpServlet 
{
		Connection conn;
		Statement stmt,stmt1;
		java.text.NumberFormat nf,nf1;
		public ResultSet rs,rs1;
		public String m_chksql;
		ServletOutputStream out = null;
		public synchronized void service(HttpServletRequest req, HttpServletResponse res)
		{
		
			try {
					LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
					conn = con_method.met_user_validate(req); 
					String m_html_client_url = con_method.html_client_url;
					String m_schema_name = con_method.schema_name;
					String m_servlet_client_url=con_method.servlet_client_url;
					String m_client_name=con_method.client_name;
					String m_client_t3_port=con_method.client_t3_port;
      		String m_username 						= con_method.username;
					String header_name    = con_method.header_name;
					String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
					String m_fschema_name=con_method.client_name.trim();

					out = res.getOutputStream();
					CallableStatement callstmt1 =null;
	
					
					//************************************************************
			
					nf = java.text.NumberFormat.getInstance(Locale.US);   
		  		nf.setMinimumFractionDigits(2);
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					//nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  		//nf1.setMinimumFractionDigits(4);
			
					res.setStatus(HttpServletResponse.SC_OK);
					res.setContentType("text/html");
					String m_st="NEW";
					String m_st_hid="New";
					m_chksql = req.getParameter("chksql");
					
					if (m_chksql.trim().equals("idle")) {
						out.println("idle");
					}
					
	    		else if(m_chksql.trim().equals("main_page")){
					if(req.getParameter("status") != null){
							m_st=req.getParameter("status");
							m_st_hid=req.getParameter("hstatus");
							}
					int m_cnt =0;
							out.println("<HTML>"); 
							out.println("<HEAD>"); 
							out.println("<TITLE>Termination Approval 2</TITLE>"); 
							out.println("</HEAD>");
							out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
							out.println("<SCRIPT language=\"JavaScript\">"); 
      				out.println("var b_flag1=0");
							out.println("function load_roll_value(m_val){"); 
							out.println("help_box.innerHTML=\"Finance - Termination - Approval2 - \"+m_val;"); 
							out.println("}"); 
							out.println(""); 

							out.println("function load_roll_out_value(){");
							out.println("help_box.innerHTML=\"Finance - Termination - Approval2 - \"+document.Form1.hid_status.value;"); 
							//out.println("help_box.innerHTML=\"Finance - Termination - Approval2 -"+m_st+"\";");
							out.println("}"); 
			
							out.println("function load_screen_status(m_val){"); 
							out.println("    document.Form1.hid_option.value    =m_val;"); 
							out.println("if(m_val==\"NEW\"){"); 
							//out.println("new_window();");
							//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
							/*out.println("document.Form1.CLIENT_CODE.disabled=false;"); 
							out.println("document.Form1.cli_help.disabled=false;"); 
							out.println("document.Form1.rec_help.disabled=true;"); 
							out.println("document.Form1.RECEPT_NO.disabled=true;"); */
							out.println("load_new_page();");
							out.println("}else if(m_val==\"HELP\"){"); 
							out.println("load_help_msg();"); 
							out.println("}"); 
							out.println("else if(m_val==\"DELETE\"){"); 
							/*out.println("document.Form1.CLIENT_CODE.disabled=true;"); 
							out.println("document.Form1.cli_help.disabled=true;"); 
							out.println("document.Form1.rec_help.disabled=false;"); 
							out.println("document.Form1.RECEPT_NO.disabled=false;");*/ 
							out.println("delete_window();");
							
							out.println("}"); 
							out.println("else{");
							out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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
							
							
							out.println("function save_window(){	"); 
							out.println("before_submit();"); 
							out.println("}"); 
							out.println("");
							out.println("function before_submit(){ "); 
							out.println("var row=document.Form1.hid_count.value");	
							out.println("		validate_data(row)"); 
							out.println("	if(b_flag1==0){");
							out.println("alert('Please select a Termination no')");
							out.println(" return ");
							out.println("	}");
							out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
							out.println("		document.Form1.action=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_TerminationSaveAppr2?status1="+m_st+"\";");  
							out.println("		document.Form1.submit();	"); 
							out.println("		}"); 
							out.println("		}"); 
							
							out.println("function befor_back(){");
							out.println("   close_window(); ");
		  				out.println("}");
			
							out.println("function befor_reset(){");
							out.println(" if(confirm(\"Are you sure you want to clear the screen?\")){  ");
							//out.println("  Form1.reset()   ");
		  				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_TerminationApproval2?chksql=main_page'");
		  				out.println(" }  ");
							out.println("}");
			

							out.println("function change(row) {"); 
							out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
							out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
							out.println("}");
							out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
							out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
							out.println("}");
							out.println("}"); 
							
							
							//added by jithendra 04-01-2018
							out.println("function change_not_allowed(obj,row_num) {"); 
							out.println("if(document.Form1.elements[\"drp_appro_\"+row_num].value!=\"DISAPPROVE\"){"); 
							out.println("alert('Termination Date should be in this Month,Reverse the Termination and Re-Enter');"); 
							out.println("obj.checked=false;}"); 
							out.println("}"); 
							//end by jithendra 04-01-2018
				
				
							out.println("function validate_data(row){"); 
							out.println("for(var f=0;f<row;f++){");
			
							out.println("if(document.Form1.elements[\"chk_app_\"+f].checked==true){");
			
							out.println("b_flag1=1;");
							out.println("break;");
							out.println("}");
							out.println("}");
							out.println("}");
							
							out.println("function show_termination_appr(finance_no,termination_type,termination_no){");
							out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_CR_TerminationCalculationReport?chksql=main_page&FINANCE_NO=\"+finance_no+\"&TERMINATION_TYPE=\"+termination_type+\"&TERMINATION_NO=\"+termination_no+\"\";");  
			    		out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 

							out.println("  }");
							out.println("function delete_window(){	"); 
							out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_TerminationApproval2?chksql=main_page&status=DELETE&hstatus=Delete';"); 
							
							out.println("}"); 
							out.println("function load_new_page(){");
							out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_TerminationApproval2?chksql=main_page';"); 
							out.println("}"); 
							
							
							 out.println("function show_termi_remark_history(val){"); //Added By Sandun on 26-11-2008
							out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_CR_TerminationApproval1?chksql=REMARKS_HISTORY&finance_no=\"+val+\" \";");  
			     		out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=800,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
							out.println("}");
							
							// added by udara 22-10-2015
							out.println("function show_transaction_history_new(val,val2){ "); 
							out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
							out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
							out.println("}");
							// end by udara 22-10-2015
							
							out.println("</script>"); 
							
							out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_out_value();\">"); 
							out.println("<FORM NAME='Form1' method='post'>");
							out.println("<input type='hidden' name='Hid_scr_name' value='AF_CR_TERMINATION_APPR2' > ");
							out.println("<input type='hidden' name='TXT_SCREEN_NAME' value='AF_CR_TERMINATION_APPR2' > ");
							out.println("<INPUT TYPE='Hidden' NAME='hid_date' VALUE=\"\">"); 
							out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
							out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\""+m_st_hid+"\">");
							out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"NEW\">");
							out.println("<INPUT TYPE='Hidden' NAME='hid_win_type' VALUE=\"Main\">"); 			
							out.println("<input type=hidden name=\"OPTION_DESC\" value=\"NEW\">");
							out.println("<input type=hidden name=\"tot_val\" value=\"0\">");
							out.println("<input type=hidden name=\"hid_opt_val\" value=\"0\">");
							out.println("<input type=hidden name=\"hid_win_opt\" value=\"0\">");
				
							out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\" class=table>");
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
							out.println("<td style=\"height: 327px\">");
							
				
				
				
							out.println("<table border=\"0\" cellpadding=\"0\" class=table cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
							out.println("<tr>");
							out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td align=\"left\" class=\"pdn_txtpos2\" height=\"2%\" id=help_box></td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
							out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
							out.println("<tr>");
							out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
							out.println("<td width=10%>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=reset value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
							out.println("<td>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td ><input type=button name=b_submit value=\"Save\" class=mainbut onclick=save_window(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        			out.println("<td>&nbsp;</td>");
							out.println("<td ><input type=button name=b_delete value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        			out.println("<td>&nbsp;</td>");
							out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  			out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=back value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=reset value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
							out.println("</tr></table>");
							out.println("</td>	");
							out.println("</tr>");
				
							out.println("<tr>");
							out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
							out.println("</tr>");
				
				
							out.println("<tr>");
							out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
							//Add Contents Dineth
							out.println("<table class=table border='0' width='100%' >");
							out.println("<tr class=tr_input>");
              // Modified by Dineth on 2008-09-04
          		out.println("<td colspan=13 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
          		out.println("</tr>");
					
							String m_ter_type="";
				
				  		rs = stmt.executeQuery("SELECT TERMINATION_TYPE, TERMINATION_DESC "+
				                         "FROM   "+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE ");
				 
				  		while(rs.next()){
				    		m_ter_type=m_ter_type+"<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>";
				  		}
				  		m_ter_type=m_ter_type+"</SELECT>";
				
							
							
							if(m_st.trim().equals("NEW")){
							rs = stmt.executeQuery (" SELECT A.TERMINATION_NO, A.FINANCE_NO, "+
																	"        TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),"+
																	"        A.AMOUNT-NVL(A.INVOICED_RENTALS,0), "+//"+m_schema_name+".AF_CO_GET_AMOUNT(TERMINATION_NO, FINANCE_NO,APPLY_DATE), "+
																	"        A.SETTELED_AMOUNT, "+
																	"        A.BALANCE_AMOUNT-NVL(A.INVOICED_RENTALS,0), "+//"+m_schema_name+".AF_CO_GET_AMOUNT(TERMINATION_NO, FINANCE_NO,APPLY_DATE), "+
																	"        NVL("+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL(A.FINANCE_NO,'',TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY')),0)+ "+
																	"        NVL("+m_schema_name+".AF_CO_GET_DUE_AMOUNT(A.TERMINATION_NO, A.FINANCE_NO,A.APPLY_DATE),0) G, "+
																	"        A.APPLICATION_NO,A.INVOICE_NO,A.CLIENT_CODE, "+
																	"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),  "+
																	"        NVL("+m_schema_name+".AF_CO_GET_TER_RESIDUAL(A.TERMINATION_NO, A.FINANCE_NO,A.APPLY_DATE),0) F, "+
																	"        NVL("+m_schema_name+".AF_CO_GET_ODI_DUE_TER(A.FINANCE_NO,A.TERMINATION_NO),0) H, "+//,APPLY_DATE
																	"        "+m_schema_name+".AF_CO_GET_APP_UNIT_COUNT(A.APPLICATION_NO) , "+
																	"        "+m_schema_name+".AF_CO_GET_APP_UNIT_COUNT(A.TERMINATION_NO),A.VAT_PER,NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(A.TERMINATION_TYPE),'-'),NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),'-'), "+
																	//"        ,DUE_AMOUNT,INVOICE_NO,RATE,  REMARKS, CHARGES,  "+
																	//"        TO_CHAR(APPLY_DATE,'DD-MM-YYYY'),TERMINATED_DATE, "+
																	//"        APPLICATION_NO, REG_NO "+
																	" A.TERMINATION_TYPE, "+
																	" A.RATE, "+
																	" "+m_schema_name+".AF_CO_GET_APP_RATE(A.FINANCE_NO,''), "+
																	" NVL(A.CLOSURE_IRR,0),NVL(A.ODI_NET,0), "+
																	" NVL((SELECT SUM(B.SALE_VALUE) FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES B WHERE B.TERMINATION_NO=A.TERMINATION_NO),0) "+
																	" ,NVL(AMOUNT,0) +  NVL(CHARGES,0) TOTAL_AMOUNT  "+ /*added by ns on 09-01-2012*/
																	" ,"+m_schema_name+".AF_CR_GET_IS_DATE_VALID(A.TERMINATION_NO) IS_VALID "+//Added by jithendra 04-01-2018
																	" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A"+
																	//" WHERE  ACTIVE_STATUS ='ENT' AND  CLIENT_CODE LIKE '"+m_client+"%' AND "+
																	" WHERE  A.ACTIVE_STATUS ='APPRO1' ");
																	//"        TERMINATION_VALIDITY_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') ");
					}
					else if(m_st.trim().equals("DELETE")){
							rs = stmt.executeQuery (" SELECT A.TERMINATION_NO, A.FINANCE_NO, "+
																	"        TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD-MM-YYYY'),"+
																	"        A.AMOUNT-NVL(A.INVOICED_RENTALS,0), "+//"+m_schema_name+".AF_CO_GET_AMOUNT(TERMINATION_NO, FINANCE_NO,APPLY_DATE), "+
																	"        A.SETTELED_AMOUNT, "+
																	"        A.BALANCE_AMOUNT-NVL(A.INVOICED_RENTALS,0), "+//"+m_schema_name+".AF_CO_GET_AMOUNT(TERMINATION_NO, FINANCE_NO,APPLY_DATE), "+
																	"        NVL("+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL(A.FINANCE_NO,'',TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY')),0)+ "+
																	"        NVL("+m_schema_name+".AF_CO_GET_DUE_AMOUNT(A.TERMINATION_NO, A.FINANCE_NO,A.APPLY_DATE),0) G, "+
																	"        A.APPLICATION_NO,A.INVOICE_NO,A.CLIENT_CODE, "+
																	"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),  "+
																	"        NVL("+m_schema_name+".AF_CO_GET_TER_RESIDUAL(A.TERMINATION_NO, A.FINANCE_NO,A.APPLY_DATE),0) F, "+
																	"        NVL("+m_schema_name+".AF_CO_GET_ODI_DUE_TER(A.FINANCE_NO,A.TERMINATION_NO),0) H, "+//,APPLY_DATE
																	"        "+m_schema_name+".AF_CO_GET_APP_UNIT_COUNT(A.APPLICATION_NO) , "+
																	"        "+m_schema_name+".AF_CO_GET_APP_UNIT_COUNT(A.TERMINATION_NO),A.VAT_PER,NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(A.TERMINATION_TYPE),'-'),NVL("+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),'-'),  "+
																	//"        ,DUE_AMOUNT,INVOICE_NO,RATE,  REMARKS, CHARGES,  "+
																	//"        TO_CHAR(APPLY_DATE,'DD-MM-YYYY'),TERMINATED_DATE, "+
																	//"        APPLICATION_NO, REG_NO "+
																	"        A.TERMINATION_TYPE, "+
																	" A.RATE, "+
																	" "+m_schema_name+".AF_CO_GET_APP_RATE(A.FINANCE_NO,''), "+
																	" NVL(A.CLOSURE_IRR,0),NVL(A.ODI_NET,0), "+
																	" NVL((SELECT SUM(B.SALE_VALUE) FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES B WHERE B.TERMINATION_NO=A.TERMINATION_NO),0) "+
																	" ,NVL(AMOUNT,0) +  NVL(CHARGES,0) TOTAL_AMOUNT  "+ /*added by ns on 09-01-2012*/
																	" ,"+m_schema_name+".AF_CR_GET_IS_DATE_VALID(A.TERMINATION_NO) IS_VALID"+//Added by jithendra 04-01-2018
																	" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
																	//" WHERE  ACTIVE_STATUS ='ENT' AND  CLIENT_CODE LIKE '"+m_client+"%' AND "+
																	" WHERE  A.ACTIVE_STATUS ='APPRO2' ");
																	//"        TERMINATION_VALIDITY_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') ");
					}
					
					
          out.println("<tr class=pdn_txtpos2>");
					out.println("<td  width='8%' >Finance No</td>");
					out.println("<td  width='8%' >Client Name</td>");
					out.println("<td  width='8%' >Value Date</td>");
          //out.println("<td  width='10%' ></td>");
					//out.println("<td  width='8%' align=right>Discounted Amount</td>");//Commented on 2008-11-10 by Dineth
					/*out.println("<td  width='8%' align=right>Settled Amount</td>");
					out.println("<td  width='8%' align=right>Balance Amount</td>");*/
					//out.println("<td  width='8%' align=right>Due Amount</td>");//Commented on 2008-11-10 by Dineth
					//out.println("<td  width='8%' align=right>Residual Value</td>");
					//out.println("<td  width='8%' align=right>ODI</td>");//Commented on 2008-11-10 By Dineth
					out.println("<td  width='8%' align=right>Termination Rate</td>");
					out.println("<td  width='8%' align=right>Finance Rate</td>");
					out.println("<td  width='8%' align=right>Closure IRR</td>");
					out.println("<td  width='8%' align=right>Total Bal Amount</td>");
					//if(m_st.trim().equals("NEW")){
					out.println("<td  width='8%' >Termination Type</td>");
					
					
					out.println("<td  width='12%' align=center>Remarks</td>"); //Added By SJ on 26-11-2008
					out.println("<td  width='15%' align=center>Remarks History</td>"); //Added By SJ on 26-11-2008
					
					out.println("<td  width='8%' >Action</td>");
					out.println("<td  width='4%'  align=right></td>");
					out.println("<td  width='8%' >Termination Sheet</td>");
					out.println("<td  width='8%' >User</td>");
					out.println("<td  width='8%' >Termination No</td>");
					//}
					/*else if(m_st.trim().equals("DELETE")){
					out.println("<td  width='10%' align=right>Termination Type</td>");
					out.println("<td  width='8%' align=right>Change to Approve 1</td>");
					out.println("<td  width='6%'  align=right></td>");
					}*/
					//out.println("<td  width='13%' >Amount Allocat</td>");
					//out.println("<td  width='8%' ></td>");
					out.println("</tr>");
      
           int j = 0; 
					 double m_ter_all = 0;
					 double m_ter_bal = 0;
							
              while(rs.next()){
							    //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/>"); 
									//out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(8)+"\"></td>"); // commented by udara 22-10-2015
									out.println("<td STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_history_new('','"+rs.getString(2)+"');\" ><u>"+rs.getString(2) +"</u><input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(8)+"\"></td>"); // added by udara 22-10-2015
									//out.println("<td >"+rs.getString(11)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(10)+"\"></td>");
                  //Added by Dineth on 2008-10-31
									out.println("<td class=hs onclick=\"show_client('"+rs.getString(10)+"')\">"+rs.getString(11)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(10)+"\"></td>");                  
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"V_DATE_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  //out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"><input type=hidden name=\"A_V_COUNT_"+j+"\" value=\""+rs.getString(14)+"\"></td>");//Commented by Dineth on 2008-11-10
                  //out.println("<td align=right>"+nf.format(rs.getDouble(7))+"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(9)+"\"><input type=hidden name=\"VAT_"+j+"\" value=\""+rs.getString(16)+"\"></td>");//Commented by Dineth on 2008-11-10
									/*out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"SETT_A_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"T_V_COUNT_"+j+"\" value=\""+rs.getString(15)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AM_"+j+"\" value=\""+(rs.getString(6))+"\"></td>");*/
									//out.println("<td align=right>"+nf.format(rs.getDouble(13))+"<input type=hidden name=\"ODI_NO_"+j+"\" value=\""+rs.getString(13)+"\"></td>");//Commented by Dineth on 2008-11-10
									//out.println("<td align=right>"+nf.format(rs.getDouble(12))+"<input type=hidden name=\"RES_NO_"+j+"\" value=\""+rs.getString(12)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(20))+"</td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(21))+"</td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(22))+"</td>");
									//out.println("<td align=right>"+nf.format((rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(23)+rs.getDouble(12)-rs.getDouble(24)))+"<input type=hidden name=\"DUE_AM_"+j+"\" value=\""+(rs.getString(6)+rs.getDouble(7)+rs.getDouble(13))+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble("TOTAL_AMOUNT"))+"<input type=hidden name=\"DUE_AM_"+j+"\" value=\""+(rs.getString(6)+rs.getDouble(7)+rs.getDouble(13))+"\"></td>");
									
									
									
									/*if(rs.getDouble(5)>0 && (rs.getDouble(6)+rs.getDouble(7))>0){
									  out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"   disabled>"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" disabled>");
									}else{
									  out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"  >"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
									}*/
									//out.println("     </td>");
									//if(m_st.trim().equals("NEW")){
									//out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"   >"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									out.println("<td>"+rs.getString(17)+"<input type=hidden name=\"TERM_TYPE_"+j+"\" value=\""+rs.getString(19)+"\"></td>");
									//}
									
									
										rs1 = stmt1.executeQuery(" SELECT COUNT(FINANCE_NO) "+
                  											   " FROM "+m_schema_name+".AF_CO_PRO_TERMI_APP_REMARKS "+
         												           " WHERE FINANCE_NO ='"+rs.getString(2)+"' ");
									
									
									if(rs1.next()){
									m_cnt =rs1.getInt(1);
									}
									
									out.println("<td width='12%'><input type='text' name='APP_REMARK_"+j+"' class=\"txt_input\" style=width:200 maxlength='1000'></td>");//Added By SJ on 26-11-2008
									if(m_cnt>0){
									out.println("<td width='8%'><INPUT TYPE='BUTTON' NAME='BTT_REMARK_HIS_"+j+"' class=\"but_input\" VALUE=\"View\" onClick=show_termi_remark_history('"+rs.getString(2)+"')></td>");//Added By SJ on 26-11-2008
									}
									else{
									out.println("<td width='8%'><INPUT TYPE='BUTTON' NAME='BTT_REMARK_HIS_"+j+"' class=\"but_input\" VALUE=\"View\" onClick=show_termi_remark_history('"+rs.getString(2)+"') disabled></td>");//Added By SJ on 26-11-2008
									}
									out.println("<td align=center><SELECT style=\"width:80px\" class=\"txt_input\" NAME=\"drp_appro_"+j+"\"><option value=\"APPROVE\">Approve</option>");
									out.println("<option value=\"DISAPPROVE\">Disapprove</option></SELECT></td>");
									//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\">");//commenetd by jithendra 04-01-2018
									
									//added by jithendra 04-01-2018
									if(m_st.trim().equals("NEW")){
										if(rs.getString("IS_VALID").equals("Y")){
										out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\"></td>");
										}else{
										out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change_not_allowed(this,"+j+")\"></td>");
										}
									}else{
									out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\"></td>");
								    }
									//end by jithendra 04-01-2018
									
									out.println("</td>");
									out.println("<td><input type=\"button\" value=\"display\" class=\"but_input\" name=\"btn_dis_"+j+"\" onclick=\"show_termination_appr('"+rs.getString(2)+"','"+rs.getString(17)+"','"+rs.getString(1)+"')\"></td>");
									//out.println("<td class=hs onclick=\"show_termination_appr('"+rs.getString(2)+"','"+rs.getString(17)+"','"+rs.getString(1)+"')\">"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
									out.println("<td>"+rs.getString(18)+"</td>");
									out.println("<td>"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
									out.println("</tr>");
									
										
                	j=j+1;
									if(rs.next()){
									  /*out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"* / >"); 
										out.println("<td >"+rs.getString(11)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(10)+"\"></td>");
	                  out.println("<td class=hs onclick=\"show_termination_appr('"+rs.getString(2)+"','"+rs.getString(17)+"','"+rs.getString(1)+"')\">"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
	                  out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(8)+"\"></td>");
	                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"V_DATE_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"><input type=hidden name=\"A_V_COUNT_"+j+"\" value=\""+rs.getString(14)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"SETT_A_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"T_V_COUNT_"+j+"\" value=\""+rs.getString(15)+"\"></td>");
	                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AM_"+j+"\" value=\""+(rs.getString(6))+"\"></td>");
										out.println("<td align=right>"+nf.format(rs.getDouble(7))+"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(9)+"\"><input type=hidden name=\"VAT_"+j+"\" value=\""+rs.getString(16)+"\"></td>");
										//out.println("<td align=right>"+nf.format(rs.getDouble(12))+"<input type=hidden name=\"RES_NO_"+j+"\" value=\""+rs.getString(12)+"\"></td>");
									  out.println("<td align=right>"+nf.format(rs.getDouble(13))+"<input type=hidden name=\"ODI_NO_"+j+"\" value=\""+rs.getString(13)+"\"></td>");
									  out.println("<td align=right>"+nf.format((rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(13)))+"<input type=hidden name=\"DUE_AM_"+j+"\" value=\""+(rs.getString(6)+rs.getDouble(7)+rs.getDouble(13))+"\"></td>");
									  //out.println("<td >"+rs.getString(18) +"<input type=hidden name=\"TERM_TYPE_"+j+"\" value=\""+rs.getString(18)+"\">");

										/*if(rs.getDouble(5)>0 && (rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(13))>0){
									    out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"   disabled>"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
										  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" disabled>");
										}else{
										  out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"  >"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
										  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
										}
										//if(m_st.trim().equals("NEW")){
										//out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\">"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
										out.println("<td>"+rs.getString(17)+"<input type=hidden name=\"TERM_TYPE_"+j+"\" value=\""+rs.getString(18)+"\"></td>");
										//}
										out.println("<td align=center><SELECT class=\"txt_input\" NAME=\"drp_appro_"+j+"\"><option value=\"APPROVE\">Approve</option>");
									out.println("<option value=\"DISAPPROVE\">Disapprove</option></SELECT></td>");
										out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\">");

										out.println("     </td>");
										out.println("</tr>");
										*/
										out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/>"); 
									//out.println("<td >"+rs.getString(2) +"<input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(8)+"\"></td>"); // commented by udara 22-10-2015
									out.println("<td STYLE='text-align:left; cursor:hand;' onclick=\"show_transaction_history_new('','"+rs.getString(2)+"');\" ><u>"+rs.getString(2) +"</u><input type=hidden name=\"FIN_NO_"+j+"\" value=\""+rs.getString(2)+"\"><input type=hidden name=\"APP_NO_"+j+"\" value=\""+rs.getString(8)+"\"></td>"); // added by udara 22-10-2015
									//out.println("<td >"+rs.getString(11)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(10)+"\"></td>");
                  //Added by Dineth on 2008-10-31
									out.println("<td class=hs onclick=\"show_client('"+rs.getString(10)+"')\">"+rs.getString(11)+"<input type=hidden name=\"CLI_NO_"+j+"\" value=\""+rs.getString(10)+"\"></td>");
									
                  out.println("<td >"+rs.getString(3) +"<input type=hidden name=\"V_DATE_"+j+"\" value=\""+rs.getString(3)+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
                  //out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_"+j+"\" value=\""+rs.getString(4)+"\"><input type=hidden name=\"A_V_COUNT_"+j+"\" value=\""+rs.getString(14)+"\"></td>");//Commented by Dineth on 2008-11-10
                  //out.println("<td align=right>"+nf.format(rs.getDouble(7))+"<input type=hidden name=\"INV_NO_"+j+"\" value=\""+rs.getString(9)+"\"><input type=hidden name=\"VAT_"+j+"\" value=\""+rs.getString(16)+"\"></td>");//Commented by Dineth on 2008-11-10
									/*out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"SETT_A_"+j+"\" value=\""+rs.getString(5)+"\"><input type=hidden name=\"T_V_COUNT_"+j+"\" value=\""+rs.getString(15)+"\"></td>");
                  out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AM_"+j+"\" value=\""+(rs.getString(6))+"\"></td>");*/
									//out.println("<td align=right>"+nf.format(rs.getDouble(13))+"<input type=hidden name=\"ODI_NO_"+j+"\" value=\""+rs.getString(13)+"\"></td>");//Commented by Dineth on 2008-11-10
									//out.println("<td align=right>"+nf.format(rs.getDouble(12))+"<input type=hidden name=\"RES_NO_"+j+"\" value=\""+rs.getString(12)+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(20))+"</td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(21))+"</td>");
									out.println("<td align=right>"+nf.format(rs.getDouble(22))+"</td>");
									//out.println("<td align=right>"+nf.format((rs.getDouble(6)+rs.getDouble(7)+rs.getDouble(23)+rs.getDouble(12)-rs.getDouble(24)))+"<input type=hidden name=\"DUE_AM_"+j+"\" value=\""+(rs.getString(6)+rs.getDouble(7)+rs.getDouble(13))+"\"></td>");
									out.println("<td align=right>"+nf.format(rs.getDouble("TOTAL_AMOUNT"))+"<input type=hidden name=\"DUE_AM_"+j+"\" value=\""+(rs.getString(6)+rs.getDouble(7)+rs.getDouble(13))+"\"></td>");
									
									
									/*if(rs.getDouble(5)>0 && (rs.getDouble(6)+rs.getDouble(7))>0){
									  out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"   disabled>"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" disabled>");
									}else{
									  out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"  >"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									  out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"Text_standard"+j+"\" onclick=check_status(\""+j+"\") value=\"NO\" >");
									}*/
									//out.println("     </td>");
									//if(m_st.trim().equals("NEW")){
									//out.println("<td align=right><SELECT name=\"TERM_TYPE_"+j+"\" class=\"txt_input\"   >"+m_ter_type+"</td>");//onclick=check_type(\""+j+"\")
									out.println("<td>"+rs.getString(17)+"<input type=hidden name=\"TERM_TYPE_"+j+"\" value=\""+rs.getString(19)+"\"></td>");
									//}
									
									rs1 = stmt1.executeQuery(" SELECT COUNT(FINANCE_NO) "+
                  											   " FROM "+m_schema_name+".AF_CO_PRO_TERMI_APP_REMARKS "+
         												           " WHERE FINANCE_NO ='"+rs.getString(2)+"' ");
									
									
									if(rs1.next()){
									m_cnt =rs1.getInt(1);
									}
									
									out.println("<td width='12%'><input type='text' name='APP_REMARK_"+j+"' class=\"txt_input\" style=width:200 maxlength='1000'></td>");//Added By SJ on 26-11-2008
									if(m_cnt>0){
									out.println("<td width='8%'><INPUT TYPE='BUTTON' NAME='BTT_REMARK_HIS_"+j+"' class=\"but_input\" VALUE=\"View\" onClick=show_termi_remark_history('"+rs.getString(2)+"')></td>");//Added By SJ on 26-11-2008
									}
									else{
									out.println("<td width='8%'><INPUT TYPE='BUTTON' NAME='BTT_REMARK_HIS_"+j+"' class=\"but_input\" VALUE=\"View\" onClick=show_termi_remark_history('"+rs.getString(2)+"') disabled></td>");//Added By SJ on 26-11-2008
									}
									out.println("<td align=center><SELECT style=\"width:80px\" class=\"txt_input\" NAME=\"drp_appro_"+j+"\"><option value=\"APPROVE\">Approve</option>");
									out.println("<option value=\"DISAPPROVE\">Disapprove</option></SELECT></td>");
									//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\">");//commenetd by jithendra 04-01-2018
									
									//added by jithendra 04-01-2018
									if(m_st.trim().equals("NEW")){
										if(rs.getString("IS_VALID").equals("Y")){
										out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\"></td>");
										}else{
										out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change_not_allowed(this,"+j+")\"></td>");
										}
									}else{
									out.println("<td><INPUT TYPE=\"checkbox\" NAME=\"chk_app_"+j+"\" value=\"N\" onClick=\"change("+j+")\"></td>");
								    }
									//end by jithendra 04-01-2018
									
									out.println("</td>");
									out.println("<td><input type=\"button\" value=\"display\" class=\"but_input\" name=\"btn_dis_"+j+"\" onclick=\"show_termination_appr('"+rs.getString(2)+"','"+rs.getString(17)+"','"+rs.getString(1)+"')\"></td>");
									//out.println("<td class=hs onclick=\"show_termination_appr('"+rs.getString(2)+"','"+rs.getString(17)+"','"+rs.getString(1)+"')\">"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
									out.println("<td>"+rs.getString(18)+"</td>");
									out.println("<td>"+rs.getString(1) +"<input type=hidden name=\"TER_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
									out.println("</tr>");
									
										
										
											
	                	j=j+1;
									
									
									}
								                 
              }
									
								
          
					        //out.println("<tr class=tr_input /*alt=\"Click Here to get Details\"*/ >");
									//out.println("<td colspan=10></td>");
                  //out.println("<td ></td>");
                  //out.println("<td id=total></td>");
                  //out.println("<td ></td>");
									//out.println("</tr>");
					
          //out.println("</table>");

			

          
					
					out.println("<tr class=tr_input>");
          out.println("<td align=right colspan=13><input type=hidden name=hid_inv_count value="+j+"><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
 
          out.println("<input type=hidden name=hid_count value="+j+"></tr></table>");


							
							
							
							
							//Add Dineth
							out.println("</td>");
							out.println("</tr>");
				
				
							out.println("<tr class=tr_input>");
							out.println("<td class=\"line\" height=\"1\">");
							out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
							out.println("</tr>");
				
							out.println("<tr class=tr_input>");
							out.println("<td class=\"pdn_txtpos\" style=\"height: 10px\">");
							out.println("<table cellpadding=\"2\" cellspacing=\"2\" border=\"0\" class=table>");
							out.println("<tr class=tr_input>");
				
							out.println("<td style=\"width: 6px\"></td>");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
							out.println("<td width=10%>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=new_1 value=\"New\" class=mainbut onclick=load_screen_status(\"NEW\"); onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");//document.Form1.OPTION_DESC.value
							out.println("<td>&nbsp;</td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td ><input type=button name=b_submit_1 value=\"Save\" class=mainbut onclick=save_window(); onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        			out.println("<td>&nbsp;</td>");
							out.println("<td ><input type=button name=b_delete value=\"Delete\" class=mainbut onclick=load_screen_status(\"DELETE\"); onMouseOver='load_roll_value(\"Delete\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
        			out.println("<td>&nbsp;</td>");
							out.println("<td><input class='mainbut' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled>  </td>"); 
			  			out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=back_1 value=\"Close\" class=mainbut onclick=befor_back(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
							out.println("<td>&nbsp;</td>");
							out.println("<td><input type=button name=reset_1 value=\"Cancel\" class=mainbut onclick=befor_reset(); onMouseOver='load_roll_value(\"Cancel\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
				
							out.println("</tr></table>");
							out.println("&nbsp;&nbsp;</td>");
							out.println("</tr>");

							//out.println("<tr class=tr_input>");
							//out.println("<td class=\"pdn_txtpos1 & txt-bodyRed\">");
							//out.println("</td>");
							//out.println("</tr>");
							//out.println("</table>");
							out.println("</td>");
							out.println("</tr>");
				
				
				
							out.println("</table>");
							out.println("</td>");
							out.println("</tr>");
							out.println("</table>");    
							out.println("<tr class=tr_input> ");
							out.println("<td valign=\"bottom\" height=\"20\"></td>");
							out.println("</tr>");
				
							out.println("</table>");
			

			  
				
							
							out.println("</form>"); 
							out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
							out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
							out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
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

