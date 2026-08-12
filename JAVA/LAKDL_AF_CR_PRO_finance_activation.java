//--
//SCREEN NAME : FINANCE ACTIVATION
//CREATED BY  : 
//DATE/TIME   :	
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_finance_activation extends javax.servlet.http.HttpServlet { 
	 
	ServletOutputStream out =  null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
					
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
		 // String m_username 						= "AA";//m_sn_methods.username;
		  m_chksql=req.getParameter("chksql");
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			out = res.getOutputStream(); 
			
  	//=============================================================================================================================		 
			 if(m_chksql.equals("main_page")){
					
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Finance - Finance Activation</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("var vec_len=0 ;");
 					out.println("var ret_sts=\"\" ;");
					out.println("var b_flag=0;"); //added by nuwan de silva 02-08-07

					

					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_finance_activation?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
		
					out.println("function new_window(){	"); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_finance_activation?chksql=main_page';"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Finance - Finance Activation - \"+m_val;"); 
					out.println("}"); 
		
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Finance - Finance Activation - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
					
					
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_finance_activation\";"); 
					out.println("    HelpBox_msg(m_help_message);"); 
					out.println("}"); 	
			
					out.println("function HelpBox_msg(m_help_message) {"); 
					out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
					out.println("  \"&help_message_in=\"+m_help_message);"); 
					out.println("}"); 
		
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("	new_window();"); 
					out.println("}");
					out.println("else if(m_val==\"HELP\"){"); 
					out.println(" load_help_msg();");
					out.println("}"); 
					out.println("else{");
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("}else if(m_val==\"DEL\"){");  
					out.println("document.Form1.hid_status.value=\"Delete\";");  
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
		
					out.println("function MyDialog(){"); 
					out.println("    this.valout   = new Array(10);"); 
					out.println("}		"); 
					
					
				/*	out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
					out.println("    oBj = new MyDialog();"); 
					out.println("    oBj.valout[1]  = \" \";"); 
					out.println("    oBj.valout[2]  = \" \";"); 
					out.println("    oBj.valout[3]  = \" \";"); 
					out.println("	"); 
					out.println("	"); 
					out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					out.println("	if(oBj.valout[1] ==\" \"){"); 
					out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
					out.println(" finance_details.innerHTML = ''; ");
					out.println(" }");
					out.println("	else if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					out.println("if(oBj.valout[0]=='Next')  {");
					out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
					out.println("}");
					out.println("else if  (oBj.valout[0]=='Prev') {");
					out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
					out.println("}		");
					out.println("else if(oBj.valout[0] == 'Exit'){");
					out.println("}");
					out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
					out.println("if(IfCount=='1'){"); 
					out.println("		finance_assign(oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='2'){"); 
					out.println("		help_value_assign_2(oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='3'){"); 
					out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='4'){"); 
					out.println("		help_value_assign_guarantor(oBj);"); 
					out.println("}");									
					out.println("	}"); 
					out.println("	}"); 
			
					out.println("	else{"); 
					out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);"); 
					out.println("		return false;"); 
					out.println("	} "); 
					out.println("	}"); 
					out.println("	else{	"); 
					out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);"); 
					out.println("	}	"); 
					out.println("	}		"); 
					out.println("	else{");
					out.println(" document.Form1.TXT_FINANCE_NO.value = \"\" ");
					out.println(" finance_details.innerHTML = ''; ");
					out.println("	}");
					out.println("	}	"); 
					out.println("}"); 
					out.println(""); 

					out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 

					out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					
					*/
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		  out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
		
			
	       	out.println("if(IfCount=='1'){"); 
					out.println("		finance_assign(oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='2'){"); 
					out.println("		help_value_assign_2(oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='3'){"); 
					out.println("		help_value_assign_3(document.Form1.hid_row_no.value,oBj);"); 
					out.println("}");
					out.println("else if(IfCount=='4'){"); 
					out.println("		help_value_assign_guarantor(oBj);"); 
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
			out.println("	clear_data();");//Added To The Clear The Area Code
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
			
			//added by nuwan de silva on 22-08-07------------------------
			out.println("function clear_data() {");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println(" finance_details.innerHTML = ''; ");
			out.println("}");
			
			
			
					
					out.println("function help_finance() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'FinanceSql','1');");
					//out.println("    HelpBox('1','10','0',Crit,'FinanceSql_finance_act','1');");
					out.println("}");
					
					out.println("function finance_assign(oBj) {"); 
					out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
					out.println(" 	makeRequest(); ");
					out.println("}"); 	
					
					
			out.println("function ckeck_data(){ "); 
			//out.println("alert('No data to save');");
			out.println("b_flag=0;");
			out.println("if(document.Form1.hid_count.value==0){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			//out.println("alert('b_flag'+b_flag);");
      out.println("}"); 
			
					out.println("function validate_data(){"); 
					out.println("//validations goes here"); 
					out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
					out.println(" alert('Please select a Finance No '); ");
					out.println("	DIV_TXT_FINANCE_NO.style.color='red';");
					out.println("	return false;"); 
					out.println("}"); 
					out.println("else{"); 
					out.println("	return true;"); 
					out.println("}"); 
					out.println("}");
					
					out.println("function before_submit(){ "); 
					out.println("ckeck_data();");
					out.println("   m_status = document.Form1.hid_status.value ");
					out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
					out.println("		if(validate_data()){"); 
					out.println("if(b_flag==0)");
				  out.println("		if(confirm(m_save_msg)){ ");
					out.println("   for (var i=0; i < document.Form1.elements.length; i++ ) {");
					out.println("   document.Form1.elements[i].disabled=false;");
					out.println("   }");
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_finance_activation';");  
					out.println("		document.Form1.submit();	"); 
					out.println("		}"); 
					out.println("		}");			
					out.println("} "); 
					
					
					out.println("function makeRequest() {");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_finance_activation?chksql=finance_details&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
					//out.println("window.open(m_url);");
					
					out.println("load_interface(m_url,'NORM');");
					out.println("}");
					
					out.println("function get_vector_normal(http_response) {");
					out.println(" finance_details.innerHTML = ''; ");
					out.println(" finance_details.innerHTML = http_response; ");
				  out.println(" ");
				  out.println("}");

		
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); //load_lock()
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"Hid_Count\" VALUE=\"0\">");
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='Hid_Client_Code' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='Hid_Guarantor_Code' VALUE=\"\">"); 

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Finance Activation </td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='6%'>&nbsp;</td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\" ></td>");  
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
		
					out.println("<table border=\"0\" align='center' width='100%' class='table'>"); 
					
					out.println("<tr >"); 
					out.println("<td  width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number *</DIV></td>"); 
					out.println("<td  width='*%'  ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15'  OnBlur=\"makeRequest()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"...\" onClick=\"help_finance()\" > </td>"); 
					out.println("</tr>"); 
					
					out.println("</table>"); 
					
								
				 out.println("<table align='center'  border=\"0\" width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><div id='finance_details'></div></td>");
		     out.println("</tr>"); 
			
			   out.println("</table>");
					
					

					out.println("<br><br><br><br><br><br>"); 
					
				  out.println("<tr><td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr>");  
					out.println("<tr><td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
						
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					
			}		
			
//=================================================================================================================================				

			else if(m_chksql.equals("finance_details")){
			
			 	 String m_finance_no = req.getParameter("finance_no");																		
					
				rs = stmt.executeQuery ("SELECT  B.FULL_NAME,A.TRANSACTION_TYPE,A.TOTAL_FINANCE_AMOUNT "+
 																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
 																	" WHERE  A.CLIENT_CODE = b.CLIENT_CODE "+
    														  " AND A.APPLICATION_STATUS = 'VERIFYL' "+ //VERIFY2
    														  " AND A.FINANCE_NO='"+m_finance_no+"' ");													
      
				/*	rs = stmt.executeQuery 
				("SELECT  distinct B.FULL_NAME,A.TRANSACTION_TYPE,A.TOTAL_FINANCE_AMOUNT "+
 																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_CLIENT B ,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER C "+
 																	" WHERE  A.CLIENT_CODE = b.CLIENT_CODE "+
																	"AND A.APPLICATION_NO=C.APPLICATION_NO "+
																	"AND C.ACTIVE_STATUS='VERIFY' "+
    														  " AND A.APPLICATION_STATUS = 'VERIFYL' "+ //VERIFY2
    														  " AND A.FINANCE_NO='"+m_finance_no+"' ");													
        */

				
					boolean more = rs.next();

					
					out.println("<table class='table' border='0' width='100%' >");
					
           int j = 0;      					
							
              while(more){
							
										String m_trans_type = rs.getString(2);
										
									  out.println("<tr >");
										out.println("<td  width='20%'>Client Name</td> ");
										out.println("<td  width='*%' ><input class='txt_input' type=text style=\"{width=200px}\" name=\"CLIENT_NAME_"+j+"\" value=\""+rs.getString(1)+"\" disabled ></td>");
										out.println("</tr>");
										
										out.println("<tr>");
										out.println("<td  width='20%' >Transaction Type </td> ");
										out.println("<td  width='*%' ><input class='txt_input' type=text name=\"TRANS_TYPE_"+j+"\" value=\""+rs.getString(2)+"\" disabled ></td>");
										out.println("</tr>");
										
										out.println("<tr>");
										out.println("<td  width='20%' >Total Amount </td> ");
										out.println("<td  width='*%' ><input class='txt_input' type=text name=\"AMOUNT_"+j+"\" STYLE=\"{text-align:right;}\"  value=\""+nf.format(rs.getDouble(3))+"\" disabled ></td> ");
										out.println("</tr>");				
										more = rs.next();
	              }
					
			    out.println("</table>");
							
		/*				rs1 = stmt.executeQuery ("SELECT  B.PURCHASE_ORDER_NO,B.TOTAL_NET,B.TOTAL_VAT,B.CURR_CODE,C.NAME,E.LOCATION_DESC "+
 																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B, "+m_schema_name+".AF_CO_MAS_VENDORS C, "+
   																  " "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION D,"+m_schema_name+".AF_CO_MAS_LOCATION E "+
 																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
    															  " AND B.VENDER_CODE = c.VENDOR_CODE "+
    															  " AND B.VENDER_CODE = D.VENDOR_CODE "+
    																" AND D.LOCATION_CODE = E.LOCATION_CODE "+
    															  " AND A.APPLICATION_STATUS = 'VERIFYL' "+ //VERIFY2
    															  " AND B.ACTIVE_STATUS='VERIFY' "+   // CANCEL
    															  " AND A.FINANCE_NO='"+m_finance_no+"' ");
			*/
			

            //ADDED BY NUWAN DE SILVA ON 22-08-07---------------------------------------------------
						rs1 = stmt.executeQuery (" SELECT B.PURCHASE_ORDER_NO,B.TOTAL_NET,B.TOTAL_VAT,B.CURR_CODE,C.NAME,NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_LOCATION_CODE(B.BRANCH_CODE,VENDER_CODE)),'-') LOC_DESC  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B, "+m_schema_name+".AF_CO_MAS_VENDORS C  "+
						" WHERE A.APPLICATION_NO = B.APPLICATION_NO  "+
						" AND B.VENDER_CODE = C.VENDOR_CODE  "+
						" AND A.APPLICATION_STATUS = 'VERIFYL'  "+
						" AND B.ACTIVE_STATUS='VERIFY'    "+ 
						" AND A.FINANCE_NO='"+m_finance_no+"'  ");
						//======================================================================================

					boolean more1 = rs1.next();																		

					out.println("<table class='table'  align='center' border='0' width='100%' >");
					if(more1) {
					out.println("<br><br>"); 						
          out.println("<tr >");
					out.println("<td  width='15%'               ><b>Purchase Order No </b></td>");
					out.println("<td  width='14%' align='right' ><b>Amount </b></td>");
					out.println("<td  width='1%'                >&nbsp;</td> ");
					out.println("<td  width='15%'               ><b>Currency Code </b></td>");
          out.println("<td  width='15%'               ><b>Vendor Name </b></td>");
					out.println("<td  width='*%'                ><b>Vendor Location </b></td>");
					out.println("</tr>");
					
					}

					 double m_amount = 0;
						
              while(more1){
									
									m_amount = rs1.getDouble(2)+rs1.getDouble(3);
                  out.println("<tr class=tr_input >");
									out.println("<td width='15%' ><input class='txt_input' type=text  name=\"PURCHASE_NO_"+j+"\" value=\""+rs1.getString(1)+"\" disabled ></td>");
									out.println("<td width='14%' align='right'><input class='txt_input' type=text  name=\"AMOUNT_"+j+"\" value=\""+nf.format(m_amount)+"\" STYLE=\"{text-align:right;}\" disabled ></td>");
									out.println("<td  width='1%' >&nbsp;</td> ");
									out.println("<td width='15%' ><input class='txt_input' type=text  name=\"CURR_CODE_"+j+"\" value=\""+rs1.getString(4)+"\" disabled ></td>");
									out.println("<td width='15%' ><input class='txt_input' type=text  name=\"VEN_NAME_"+j+"\" value=\""+rs1.getString(5)+"\" disabled ></td>");
									out.println("<td width='*%' ><input class='txt_input' type=text  name=\"VEN_LOC_"+j+"\" value=\""+rs1.getString(6)+"\" disabled ></td>");
									out.println("</tr>");
								  more1 = rs1.next();
                	j=j+1;
									
	         }
 
          out.println("<input type=hidden name=hid_count value="+j+"></table>");
			
			
			}
			
			
		//=================================================================================================================================	
					
	  }catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  //if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			//if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    //if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e2){}}
			//try{conn.setAutoCommit(true);
		}
	}//service method
}


