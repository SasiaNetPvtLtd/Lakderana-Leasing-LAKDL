//--
//SCREEN NAME	:TERMINATION APPROVAL 1
//CREATED BY	:DINETH MEEMANAGE
//DATE/TIME		: 2008-08-28
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;
  

public class LAKDL_AF_CR_TerminationApprovalReport extends javax.servlet.http.HttpServlet { 

			Connection conn;
			Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt_rental;
			java.text.NumberFormat nf,nf1;
			public ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs_rental,rs_charges;
			public String m_chksql;
			ServletOutputStream out = null;
			String reqstr;
			CallableStatement callstmt1 =null;
			public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
					try {
					
					
							LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
							conn = m_sn_methods.met_user_validate(req); 
			
							String m_html_client_url=m_sn_methods.html_client_url.trim(); 
							String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
							String m_fschema_name=m_sn_methods.client_name.trim();
							String m_schema_name = m_sn_methods.schema_name;
							String m_username 						= m_sn_methods.username;
							res.setStatus(HttpServletResponse.SC_OK); 
							res.setContentType("text/html"); 
							out = res.getOutputStream();
							
							String m_chksql="";
							nf = java.text.NumberFormat.getInstance(Locale.US);
		  				nf.setMinimumFractionDigits(2);
		  				nf.setMaximumFractionDigits(2);
							stmt=conn.createStatement();
							stmt1=conn.createStatement();
							//stmt2=conn.createStatement();
							//stmt3=conn.createStatement();
							//stmt4=conn.createStatement();
							//stmt5=conn.createStatement();
							//stmt6=conn.createStatement();
							if(req.getParameter("chksql")!=null){
									m_chksql=req.getParameter("chksql");
							}
							if (m_chksql.trim().equals("idle")) {
									out.println("idle");
							}
					
	    				/*else if(m_chksql.trim().equals("main_page")){
									out.println("<HTML>"); 
									out.println("<HEAD>");
									out.println("<TITLE>Termination Approval 1</TITLE>"); 
									out.println("</HEAD>"); 
									out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
									out.println("<SCRIPT language=\"JavaScript\">");
									out.println("function help_button_6() {"); 
									out.println("    document.Form1.hid_help_type.value=\"6\";"); 
									out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
									out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
									out.println("    HelpBox('1','10','0');"); 
									out.println("}");
									
									out.println("function help_button_7() {"); 
									out.println("    document.Form1.hid_help_type.value=\"7\";");
									//out.println("    if(document.Form1.TXT_FINANCE_NO.value != \"\"){ ");
									
									out.println("    m_sql = \"m_help_TXT_TerminationSql_sql2\";"); 
									out.println("    m_criteria = document.Form1.TXT_TERMINATION_NO.value+\"@\";"); 

									//out.println("    m_criteria = document.Form1.TXT_TERMINATION_NO.value+\"@Y@\"+document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
									//out.println("    }");
									//out.println("    else{");
									//out.println("    m_sql = \"m_help_TXT_TerminationSql_sql\";"); 
									//out.println("    m_criteria = document.Form1.TXT_TERMINATION_NO.value+\"@Y@\";"); 
									//out.println("    }");
									
									out.println("    HelpBox('1','10','0');"); 
									out.println("}");
									
									out.println("function MyDialog(){"); 
									out.println("    this.valout   = new Array(10);"); 
									out.println("}		"); 
									out.println(""); 
									
									
									out.println("function clear_window(){	"); 
									out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
									out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_TerminationApproval1?chksql=main_page';"); 
									out.println("		}"); 
									out.println("}"); 

									out.println("function new_window(){	"); 
									out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_TerminationApproval1?chksql=main_page';"); 
									out.println("}"); 
									out.println(""); 
									out.println(""); 


									
									out.println("function help_value_assign_6() {"); 
									out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
									out.println("}");
									out.println("function help_value_assign_7() {"); 
									out.println("   document.Form1.TXT_TERMINATION_NO.value=oBj.valout[2];");
									out.println("		document.Form1.hid_fin_no.value=oBj.valout[3];");
									out.println(" if(oBj.valout[7]){");
									out.println("   document.Form1.hid_term_type.value=oBj.valout[7];");
									out.println("   }");
									out.println(" else{");
									out.println("   document.Form1.hid_term_type.value=\"\";");
									out.println(" } ");
									out.println("}");
									out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
									out.println("    oBj = new MyDialog();"); 
									out.println("    oBj.valout[1]  = \" \";"); 
									out.println("    oBj.valout[2]  = \" \";"); 
									out.println("    oBj.valout[3]  = \" \";"); 
									out.println("	"); 
									out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
									out.println("	if(oBj.valout[1] !=\" \"){"); 
									out.println("	if(oBj.valout[1] !=\"Close\"){"); 
									out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
									out.println("	if(oBj.valout[1]!=\"Next\"){"); 
									out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
									out.println("	help_value_assign_6()");
	  							out.println("		}");
									out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
									out.println("	help_value_assign_7()");
	  							out.println("		}"); 
									out.println("	}"); 
									out.println("	else{"); 
									out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
									out.println("		return false;"); 
									out.println("	} "); 
									out.println("	}"); 
									out.println("	else{	"); 
									out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
									out.println("	}	"); 
									out.println("	}		"); 
									out.println("	else{");
									out.println("clear()");
									out.println("	}");
									out.println("}");
									out.println("if(oBj.valout[2]==' '){");
									out.println("clear()");
									out.println("	}	"); 
									out.println("}"); 

									out.println("function Prev(Start,End,Hid_No){"); 
									out.println("    HelpBox(Start,End,Hid_No);"); 
									out.println("}"); 
									out.println(""); 

									out.println("function Next (Start,End,Hid_No){"); 
									out.println("    HelpBox(Start,End,Hid_No);"); 
									out.println("}"); 
									out.println(""); 

									out.println("function clear(){");			
									out.println("if(document.Form1.hid_help_type.value==\"6\"){");
									out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
									out.println("}");
									out.println("else if(document.Form1.hid_help_type.value==\"7\"){");
									out.println(" document.Form1.TXT_TERMINATION_NO.value=\"\";"); 
									out.println("}");
									out.println("}");
									
									out.println("function load_roll_value(m_val){"); 
									out.println("help_box.innerHTML=\"  Termination Approval 1 - \"+m_val;"); 
									out.println("}"); 
									out.println(""); 

									out.println("function load_roll_out_value(){");
									out.println("help_box.innerHTML=\"  Termination Approval 1 - \"+document.Form1.hid_status.value;"); 
									out.println("}"); 
									
									out.println("function load_screen_status(m_val){"); 
									out.println("if(m_val==\"NEW\"){"); 
									out.println("new_window();"); 
									out.println("}");
									out.println("else if(m_val==\"HELP\"){"); 
									out.println("load_help_msg();"); 
									out.println("}"); 
									out.println("else{");
									out.println("}"); 
									out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
									out.println("if(m_val==\"NEW\"){");
									out.println("document.Form1.hid_status.value=\"New\";");
									out.println("}else{");  
									out.println("document.Form1.hid_status.value=\"\";");  
									out.println("}"); 
									out.println("}"); 




									out.println("function show_termination_appr(){");
									out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_CR_TerminationApproval1?chksql=show_report&FINANCE_NO=\"+document.Form1.hid_fin_no.value+\"&TERMINATION_TYPE=\"+document.Form1.hid_term_type.value+\"&TERMINATION_NO=\"+document.Form1.TXT_TERMINATION_NO.value+\"\";");  
			    				out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 

									out.println("  }");
									out.println("</SCRIPT>");
									//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),get_conditions(),get_remarks('M1'),validate_nic_no()\">"); 
									out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
									out.println("<FORM NAME='Form1' method='post'>"); 
									out.println("<input type=hidden name=hid_help_type value=\"\">");
									out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
									out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> ");
									out.println("<input type='hidden' name='hid_fin_no' value=\"\">");
									out.println("<input type='hidden' name='hid_term_type' value\"\">");
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
									out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Termination Approval 1</td>"); 
									out.println("</tr>"); 
									out.println("<tr>"); 
									out.println("<td  height='10px' class='pdn_txtpos'>"); 
									out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'><tr> "); 
									//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
									out.println("<td width='30%'>&nbsp;</td>");  
									out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
									out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
									out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
									out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
									out.println("</table>");  
									out.println("</td></tr><tr>");  
									out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
									out.println("</tr><tr>");  
									out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
									out.println("<br>");

									
									out.println("<table align='center' width='100%' class='table'>"); 
									out.println("<tr >");
									out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No</DIV></td>"); 
									//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assig('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
									out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15'>"); 

									out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_FINANCE' value=\" Help \" onClick=\"help_button_6()\">"); 
									//out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show Approval\" onClick=\"show_termination_appr()\"></td>"); 

									out.println("</tr >");
									out.println("<tr >");
									out.println("<td width='20%' ><DIV id='DIV_TXT_TERMINATION'  class=div_input>Termination No</DIV></td>"); 
									//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onblur=\"assig('G6'),makeRequest1(document.Form1.TXT_FINANCE_NO)\">"); 
									out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TERMINATION_NO' maxlength='15' size='15'>"); 

									out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_TERMINATION' value=\" Help \" onClick=\"help_button_7()\">"); 
									out.println("<input class='but_input' type='button' name='BUT_VIEW' style='{width:100}'  value=\"Show Approval\" onClick=\"show_termination_appr()\"></td>"); 

									out.println("</tr >");
									out.println("</table>");
									out.println("</FORM>");
									out.println("</BODY>");
									out.println("</HTML>");
					
					
					
					
					
					
					
	

							}*/
							else if(m_chksql.trim().equals("show_report")){
										String m_date_time="";
										String m_client_name="";
										String m_client_add="";
										String m_termination_date="";
										String m_termination_value_date="";
										String m_transaction_type="";
										String m_vehicle_no="";
										String m_chassis_no="";
										String m_Invoic_no="";
										String m_Invoice_no="";
										String m_asset_name_1="";
										String m_engine_no_1="";
										String m_chasis_no_1="";
										String m_reg_no_1="";
										String m_item_sub="";
										String m_item_cat="";
										double m_termination_rate=0.0;
										double m_termination_vat=0.0;
										double m_due=0.0;
										double m_odi=0.0;
										String m_asset_details="";
										double m_due_amount=0.0;
										double m_calculation_amount=0.0;
										double m_residual_value=0.0;
										double m_termination_charges=0.0;
										double m_closure_irr=0.0;
										double m_no_of_future_rentals=0.0;
										double m_no_of_rentals_paid=0.0;
										double m_no_of_rentals_arrears=0.0;
										double m_total_1=0.0;
										double m_odi_net=0.0;
										String m_finance_amount="";
										String m_nibsm="";
										String m_ami_amt="";
										String m_cap_out="";
										String m_termination_no=req.getParameter("TERMINATION_NO");
										String m_finance_no=req.getParameter("FINANCE_NO");
										String m_termination_type=req.getParameter("TERMINATION_TYPE");
										String m_App_date="";
										double m_irr=0.0;
										out.println("<HTML>"); 
										out.println("<HEAD>");
										out.println("<TITLE>Termination Approval 1</TITLE>"); 
										out.println("</HEAD>"); 
										out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
										out.println("<SCRIPT language=\"JavaScript\">");
										
										out.println("function save_data(){");
			
			
										out.println("m_table.innerHTML=\"\" ");
				
										out.println("window.print();");
		
										out.println("}");
										
										out.println("function add_button(){");
			
										
											out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			
											out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    									out.println("m_writedata+'</table>';");
										
			
			
										out.println("}");
			

										out.println("</SCRIPT>");
										
										out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"add_button()\">"); 
										out.println("<FORM NAME='Form1' method='post'>"); 

   									out.println("<br>"); 
	 									out.println("<br>"); 

										out.println("<table align='center' width='100%' class='table'>"); 
										out.println("<tr>");  
										out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
										out.println("</tr>"); 
		
										out.println("</table>");
										out.println("<br>"); 
		

										out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");
										out.println(" <tr> ");
										out.println("  <td colspan=\"5\"><div align=\"center\"> ");
										out.println("   <b>Settlement Working sheet for ("+m_finance_no+")<b></div></td>");
										out.println(" </tr>");
										
										rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY hh:mi') FROM DUAL");
										boolean more=rs.next();
										if(more){
										m_date_time=rs.getString(1);
										}
										
										
										String sql1=" SELECT "+
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME , "+//Client Name
																" NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.CLIENT_CODE),'-') ||', '|| NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.CLIENT_CODE),'-'), "+//Client Address
																" NVL(TO_CHAR(A.APPLY_DATE,'DD/MM/YYYY'),'-'), "+//Termination Date
																" NVL(TO_CHAR(A.TERMINATION_VALIDITY_DATE,'DD/MM/YYYY'),'-'), "+//Termination Value Date
																" NVL(B.TRANSACTION_TYPE,'-'), "+//Transaction Type
																" NVL(A.RATE,0), "+//Termination Rate
																" NVL(A.VAT_PER,0), "+//Termination Vat
																" NVL(A.DUE_AMOUNT,0), "+//Due Amount
																" NVL(A.AMOUNT,0), "+//Termination Calculation Amount
																" NVL(A.RESIDUAL_VALUE,0), "+//Residual Value
																" NVL(A.CHARGES,0), "+//Termination Charges
																" NVL(A.INVOICE_NO,'-'), "+//Invoice Number
																" NVL("+m_schema_name+".AF_CO_GET_NOT_INV_RENTAL(a.FINANCE_NO,'',TO_CHAR(a.APPLY_DATE,'DD-MM-YYYY')),0)+ NVL("+m_schema_name+".AF_CO_GET_DUE_AMOUNT(a.TERMINATION_NO,a.FINANCE_NO,a.APPLY_DATE),0) G, "+
																" NVL(A.ODI_NET,'0') "+//14
																" FROM "+m_schema_name+".AF_CR_PRO_Termination A, "+
																" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																" WHERE A.TERMINATION_NO='"+m_termination_no+"' "+
																" AND A.FINANCE_NO=B.FINANCE_NO ";
										
										
										rs1=stmt1.executeQuery(sql1);
										boolean more1=rs1.next();
										if(more1){
										m_client_name=rs1.getString(1);
										m_client_add=rs1.getString(2);
										m_termination_date=rs1.getString(3);
										m_termination_value_date=rs1.getString(4);
										m_transaction_type=rs1.getString(5);
										m_termination_rate=rs1.getDouble(6);
										m_termination_vat=rs1.getDouble(7);
										m_due_amount=rs1.getDouble(8);
										m_calculation_amount=rs1.getDouble(9);
										m_residual_value=rs1.getDouble(10);
										m_termination_charges=rs1.getDouble(11);
										m_Invoice_no=rs1.getString(12);
										m_due = rs1.getDouble(13);
										m_odi = rs1.getDouble(14);
										}
										
										out.println("<tr><td colspan=\"5\">");
										out.println("<table align='center' width='100%' class='table'>"); 
										out.println("<tr>"); 
										out.println("<td width=\"70%\">&nbsp;</td>");
										out.println("<td width=\"10%\" style=\"text-align:right\">Date</td>");
										out.println("<td width=\"5%\" style=\"text-align:center\">:</td>");
										out.println("<td width=\"*%\">"+m_date_time+"</td>");
										out.println("</tr>"); 
										out.println("</table>");
										out.println("</td>");
										out.println("</tr>");
										
										
										out.println(" <tr>");
 										out.println("  <td colspan=\"5\"><table width=\"100%\"  border=\"0\"  cellspacing=\"0\" cellpadding=\"0\">");
 										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Client Name</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_client_name+"</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Address</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_client_add+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination no</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_termination_no+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination Date</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_termination_date+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination Value Date</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_termination_value_date+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Transaction Type</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\">"+m_transaction_type+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
										out.println("   <td colspan=\"4\">&nbsp;</td>");
										out.println("   </tr>");
										
										rs5=stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
										boolean more5=rs5.next();
										if(more5){
										m_App_date=rs5.getString(1);
										}
										
										
										
										rs6=stmt1.executeQuery("SELECT "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
																					 "FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  FINANCE_NO= '"+m_finance_no+"'");
										boolean more6=rs6.next();
										if(more6){
										m_closure_irr=rs6.getDouble(1);
										}
										
										
										
										String sql4="SELECT VEHICLE_NO,CHASSIS_NO,PRO_INVOICE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
																" WHERE TERMINATION_NO='"+m_termination_no+"'";
										rs3=stmt1.executeQuery(sql4);
										boolean more10=rs3.next();
										if(more10){
											m_vehicle_no=m_vehicle_no+rs3.getString(1)+"@";
											m_chassis_no=m_chassis_no+rs3.getString(2)+"@";
											m_Invoic_no =m_Invoic_no +rs3.getString(3)+"@";
										}
										//out.println("user name"+m_username);
										//out.println("vehicle no"+m_vehicle_no);
										//out.println("chasis no"+m_chassis_no);
										//out.println("finance no"+m_finance_no);
										//out.println("t5");
										  callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_CR_TEMP_TERMINATION_CAPITAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12);END;");
				  						callstmt1.setString(1 ,m_username);
          						callstmt1.setString(2 ,m_vehicle_no);
          						callstmt1.setString(3 ,m_chassis_no);
          						callstmt1.setString(4 ,m_finance_no);
											callstmt1.registerOutParameter(5,java.sql.Types.CHAR);
											callstmt1.registerOutParameter(6,java.sql.Types.CHAR);
											callstmt1.registerOutParameter(7,java.sql.Types.CHAR);
											callstmt1.registerOutParameter(8,java.sql.Types.CHAR);
											callstmt1.registerOutParameter(9,java.sql.Types.CHAR);
          						callstmt1.registerOutParameter(10,java.sql.Types.CHAR);
											callstmt1.setString(11 ,m_Invoic_no);
          						callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
											/*//out parameters
											callstmt1.setString(1 ,"SM");
          						callstmt1.setString(2 ,"REG-1299");
          						callstmt1.setString(3 ,"CH-1299");
          						callstmt1.setString(4 ,"FIN2250");
											callstmt1.setString(5,"0");
											callstmt1.setString(6,"0");
											callstmt1.setString(7,"0");
											callstmt1.setString(8,"0");
											callstmt1.setString(9,"0");
          						callstmt1.setString(10,"0");
          						callstmt1.setString(11 ,"IN080205-004127");
          						callstmt1.setString(12,"0");
          
 				  */
			    callstmt1.execute();
									
					m_finance_amount=nf.format(callstmt1.getDouble(5));
					m_nibsm=nf.format(callstmt1.getDouble(6));
					m_ami_amt=nf.format(callstmt1.getDouble(7));
					m_cap_out=nf.format(callstmt1.getDouble(8));
					//extra
					
					//out.println("<AMI_CAP>"+  nf.format(callstmt1.getDouble(9))  + "</AMI_CAP>");
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Finance Amount</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'><u>"+m_finance_amount+"</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										rs1=stmt1.executeQuery("SELECT "+m_schema_name+".AF_CO_GET_APP_RATE('"+m_finance_no+"','') FROM DUAL");
										if(rs1.next()){
										m_irr=rs1.getDouble(1);
										}
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>IRR</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'><u>"+nf.format(m_irr)+"</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Closure IRR</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+nf.format(m_closure_irr)+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>AMI</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+m_ami_amt+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>NIBSM</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+m_nibsm+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Capital O/S</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'><u>"+m_cap_out+"</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										rs1=stmt1.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_CAL_FUTURE_ODI('"+m_finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) FROM DUAL");
										if(rs1.next()){
										m_odi_net=rs1.getDouble(1);
										}
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>ODI Net</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'><u>"+nf.format(m_odi_net)+"</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										
										/*out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Initial VAT</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'><u>&nbsp;</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");*/
										
										out.println("   <tr>");
										out.println("   <td colspan=\"4\">&nbsp;</td>");
										out.println("   </tr>");
										
										if(!m_termination_type.equals("null")){
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination Type</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+m_termination_type+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
									  }
									  out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination Rate</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+nf.format(m_termination_rate)+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
											
									  out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination VAT</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"15%\" style='text-align:right'>"+nf.format(m_termination_vat)+"</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
											
										/*	
									  out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Sales Price</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										out.println("    <td width=\"27%\"><u>&nbsp;</u></td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										*/
										//String sql2="SELECT VEHICLE_NO || ' ' ||CHASSIS_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
										//					" WHERE TERMINATION_NO='"+m_termination_no+"'";
										String sql2=" SELECT "+
																" B.MODEL_CODE, "+
																" nvl(B.ENGINE_NO,'-'), "+
																" nvl(B.CHASSIS_NO,'-'), "+
																" NVL(B.REG_NO,'-'), "+
																" D.ITEM_SUB_CAT, "+
																" E.ITEM_CAT_CODE "+
																" FROM "+
																" "+m_schema_name+".AF_CR_PRO_TERMINATION A, "+
																" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
																" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
																" "+m_schema_name+".AF_CO_MAS_MODEL D, "+
																" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY E"+
																" WHERE A.FINANCE_NO=C.FINANCE_NO "+
																" AND C.APPLICATION_NO=B.APPLICATION_NO "+
																" AND B.MODEL_CODE=D.MODEL_CODE "+
																" AND D.ITEM_SUB_CAT=E.ITEM_SUB_CAT "+
																" AND A.TERMINATION_NO='"+m_termination_no+"'";
																
										
										rs2=stmt1.executeQuery(sql2);
										boolean more3=rs2.next();
										if(more3){
											//m_asset_details=rs2.getString(1);
											m_asset_name_1=rs2.getString(1);
											m_engine_no_1=rs2.getString(2);
											m_chasis_no_1=rs2.getString(3);
											m_reg_no_1=rs2.getString(4);
											m_item_sub=rs2.getString(5);
											m_item_cat=rs2.getString(6);
										}
										
										
										
										out.println("   <tr>");
 										out.println("    <td width=\"18%\"><b>Termination Asset Details</b> </td>");
 										out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
 										//out.println("    <td width=\"27%\">"+m_asset_details+"</td>");
									  out.println("    <td width=\"15%\">&nbsp;</td>");
 										out.println("    <td width=\"*%\">&nbsp; </td>");
									 	out.println("   </tr>");
										//Table to show Asset Details-Added by Dineth
										out.println("   <tr>");
										out.println("   	<td colspan=\"4\" width=\"100%\">");
										out.println("     	<table width=\"100%\" border=\"0\">");
										out.println("					<tr>");
										out.println("         	<td width=\"25%\"><b>Asset</b></td>");
										out.println("           <td width=\"25%\"><b>Engine No</b></td>");
										out.println("           <td width=\"25%\"><b>Chassis No/Serial No</b></td>");
										out.println("           <td width=\"25%\"><b>Registration No</b></td>");
										out.println("					</tr>");
										if(m_item_cat.equals("EQUIPMENT")){
										out.println("					<tr>");
										out.println("         	<td width=\"25%\">"+m_item_sub+"</td>");
										out.println("           <td width=\"25%\">&nbsp;</td>");
										out.println("           <td width=\"25%\">"+m_chasis_no_1+"</td>");
										out.println("           <td width=\"25%\">&nbsp;</td>");
										out.println("					</tr>");
										}
										else{
										
										out.println("					<tr>");
										out.println("         	<td width=\"25%\">"+m_item_sub+"</td>");
										out.println("           <td width=\"25%\">"+m_engine_no_1+"</td>");
										out.println("           <td width=\"25%\">"+m_chasis_no_1+"</td>");
										out.println("           <td width=\"25%\">"+m_reg_no_1+"</td>");
										out.println("					</tr>");
										}
										out.println("       </table>");
										out.println("   	</td>");
										out.println("   </tr>");
										
										//End Table by Dineth
									  out.println(" </table>");
										//out.println(" </td>");
										//out.println(" </tr>");
										
										//out.println("   <tr>");
    //out.println("    <td colspan=\"5\" valign=\"top\"><table width=\"100%\" class='table'   border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
     out.println(" <table width=\"76%\" class='table'   border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");

			out.println("     <tr>");
      out.println("      <td width=\"100%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
       out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Due Amount</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_due_amount)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Rentals Due up to Termination Date</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_due)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Termination Calculation Amount</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_calculation_amount)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Residual Amount</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_residual_value)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("<tr>");
				out.println("<td colspan=\"2\" width=\"100%\">&nbsp;</td>");
				out.println("</tr>");
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Termination Charges</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_termination_charges)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>ODI</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_odi)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Total Amount</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_odi+m_termination_charges+m_residual_value+m_calculation_amount+m_due_amount+m_due)+"</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
        //out.println("        <td width=\"12%\">&nbsp;</td>");
       out.println("       </tr>");
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				//out.println("</td>");
				//out.println("</tr>");
				
				//out.println("<tr>");
				//out.println("<td colspan=\"5\" valign=\"top\">&nbsp;</td></tr>");
				
				//out.println("   <tr>");	
				//out.println("    <td colspan=\"5\" valign=\"top\"><table width=\"76%\" class='table' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
     out.println("<br>");
			
			//Added by Dineth on 2008-08-29
			rs4 = stmt1.executeQuery (" SELECT "+m_schema_name+".AF_CR_GET_NO_FUTURE_RENTAL(APPLICATION_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_RENTALS_PAID_TER(FINANCE_NO),"+
																	"	       "+m_schema_name+".AF_CO_GET_NO_RENTALS_ARRIES(FINANCE_NO),"+
																	"        round('"+m_termination_charges+"'*('"+m_termination_vat+"'/100)),"+
																	"        '"+m_termination_charges+"'+round('"+m_termination_charges+"'*('"+m_termination_vat+"'/100))"+
																	//"        "+m_schema_name+".AF_CO_GET_CLOSURE_IRR(FINANCE_NO,'"+m_App_date+"') "+
																	"	FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	"	WHERE  FINANCE_NO= '"+m_finance_no+"'");
			
			boolean more4=rs4.next();
			if(more4){
				m_no_of_future_rentals=rs4.getDouble(1);
				m_no_of_rentals_paid=rs4.getDouble(2);
				m_no_of_rentals_arrears=rs4.getDouble(3);
			  m_total_1=rs4.getDouble(1)+rs4.getDouble(2)+rs4.getDouble(3);
			}
			out.println("    <table width=\"76%\" class='table' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");

			out.println("     <tr>");
      out.println("      <td width=\"100%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
       out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>No of Future Rentals</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_no_of_future_rentals)+"</td>");
        
       out.println("       </tr>");
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>No of Rentals Paid</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_no_of_rentals_paid)+"</td>");
        
       out.println("       </tr>");
				
				 out.println("       </tr>");
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>No of Rentals Arrears</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_no_of_rentals_arrears)+"</td>");
        
       out.println("       </tr>");
				
				out.println("       </tr>");
				out.println("       <tr>");
        out.println("        <td width=\"64%\"><b>Total</b></td>");
        out.println("        <td width=\"12%\" style='text-align:right'>"+nf.format(m_total_1)+"</td>");
        
       out.println("       </tr>");
				
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				rs1=stmt.executeQuery(" SELECT "+
															" NVL(MOD_USER,'-'), "+
															" NVL(ACTIVE_STATUS,'-'), "+
															" NVL(TO_CHAR(MOD_DATE,'DD/MM/YYYY hh:mi'),'-'), "+
															" NVL(REMARKS,'-') "+
															" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
															" WHERE TERMINATION_NO='"+m_termination_no+"'");
				out.println("    <table width=\"100%\" class='table' border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");

			out.println("     <tr>");
      out.println("      <td width=\"100%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
       out.println("       <tr>");
        out.println("        <td width=\"20%\"><b>User</b></td>");
        out.println("        <td width=\"20%\"><b>Stage</b></td>");
				out.println("<td width=\"20%\"><b>Date/Time</b></td>");
        out.println("<td width=\"40%\"><b>Remarks</b></td>");
       out.println("       </tr>");
				
				while(rs1.next()){
						out.println("       <tr>");
        out.println("        <td width=\"20%\">"+rs1.getString(1)+"</td>");
        out.println("        <td width=\"20%\">"+rs1.getString(2)+"</td>");
				out.println("<td width=\"20%\">"+rs1.getString(3)+"</td>");
        out.println("<td width=\"40%\">"+rs1.getString(4)+"</td>");
       out.println("       </tr>");
				}
				
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("</td>");
				
				out.println("</tr>");
				
				
				
				
										
										out.println(" </table>");
										out.println("</FORM>");
										out.println("</BODY>");
										out.println("</HTML>");
										
										
							
							
							}
					}		
		
					catch (Exception ex) {
							try{out.println("Error:"+ex.toString());}catch(Exception e){}
					}
					finally{
		  				if(rs    !=null){try{rs.close();   }catch(Exception e){}}
							if(rs_rental !=null){try{rs_rental.close(); }catch(Exception e){}}
							if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
							if(stmt_rental  !=null){try{stmt_rental.close(); }catch(Exception e){}}
	    				if(conn  !=null){try{conn.close(); }catch(Exception e){}}
							if(out   !=null){try{out.close();  }catch(Exception e){}}
			
			//if(out!=null){try{out.close();  }catch(Exception e){}}
					}
			}

}

