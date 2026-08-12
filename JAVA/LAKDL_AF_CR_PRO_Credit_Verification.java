//--
//SCREEN NAME : FINANCE ACTIVATION
//CREATED BY  : 
//DATE/TIME   : 11-09-2006 
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Credit_Verification extends javax.servlet.http.HttpServlet { 
	 
	ServletOutputStream out =  null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
					
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
		//	String m_html_client_url = m_sn_methods.html_client_url;
			String m_schema_name = m_sn_methods.schema_name;
			String m_servlet_client_url=m_sn_methods.servlet_client_url;
			String m_client_name=m_sn_methods.client_name;
			String m_client_t3_port=m_sn_methods.client_t3_port;
			
			
			
			
			
			
			
			
		  String m_username 						= "AA";//m_sn_methods.username;
				 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 

		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Credit Process - Verification</TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		out.println("var vec_len=0 ;");
		out.println("var ret_sts=\"\" ;");
					
			
			
			out.println("function assign_status(obj) {");

      out.println("if(obj=='Btn_Guarantor_Det') {");
			out.println("document.Form1.hid_chk_status.value='GR'");
	    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Guarantor_Verification?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
	    out.println("window.open(m_url,'displayWindow2','left=100,top=133,width=800,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}");
						
		  out.println("else if(obj=='Btn_Asset_Det') {");
			out.println("document.Form1.hid_chk_status.value='AS'");
	    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Asset_Verification?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
	    out.println("window.open(m_url,'displayWindow2','left=100,top=133,width=1000,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}");
			
		  out.println("else if(obj=='Btn_Invoice_Det') {");
			out.println("document.Form1.hid_chk_status.value='IN'");
	    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Proforma_Invoice_Verification?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
	    out.println("window.open(m_url,'displayWindow2','left=100,top=133,width=1000,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}");
			
		  out.println("else if(obj=='Btn_Valuation_Det') {");
			out.println("document.Form1.hid_chk_status.value='VL'");
	    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Valuation_Verification?applicaton_no='+document.Form1.TXT_APPLICATION_NO.value+''");
	    out.println("window.open(m_url,'displayWindow2','left=100,top=133,width=1000,height=300,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}");
			//out.println("alert(document.Form1.hid_chk_status.value)");
			out.println("}");
			
			
					out.println("function disable_butttons(){	");
					out.println("document.Form1.Btn_Applicant_Det.disabled=true;");
					out.println("document.Form1.Btn_Guarantor_Det.disabled=true;");
					out.println("document.Form1.Btn_Asset_Det.disabled=true;");
					out.println("document.Form1.Btn_Valuation_Det.disabled=true;");
					out.println("document.Form1.Btn_Invoice_Det.disabled=true;");
					out.println("document.Form1.Btn_App_Doc.disabled=true;");
					out.println("}"); 
				


					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are You Sure?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Verification';"); 
					out.println("		}"); 
					out.println("}"); 
		
					out.println("function new_window(){	"); 
					out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Verification';"); 
					out.println("}"); 

		
					
					out.println("function load_roll_value(m_val){"); 
					out.println("help_box.innerHTML=\" Credit Process - Verification - \"+m_val;"); 
					out.println("}"); 
		
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Credit Process - Verification - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
		
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("}");
					out.println("else if(m_val!=\"EDIT\"){"); 
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
					
					
					
					//////////////////////////////////////////////////////////////////
					
					
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
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
				//out.println("if(IfCount=='99'){"); 
				//out.println("		help_update_value_assign_99(oBj);"); 
				//out.println("}");
				out.println("if(IfCount=='1'){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
				out.println("else if(IfCount=='2'){"); 
				out.println("		help_value_assign_2(oBj);"); 
				out.println("");
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
			out.println("		Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
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
					
								
					
			out.println("function help_button_1(Start,End,Hid_No,Sql,IfCount) {"); 

          out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DEL\" || document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ACTIVATED@\";"); 
					out.println("    } ");

					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_1(oBj) {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("    document.Form1.Hid_Client_Code.value=oBj.valout[4];");
					out.println("document.Form1.TXT_APPLICATION_NO.focus();");
					out.println("}"); 
					
					
					
					
			    out.println("function help_button_guarantor(Start,End,Hid_No,Sql,IfCount) {"); 
					
					out.println(" Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 
					out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
					out.println("}"); 
					out.println(""); 
		
					out.println("function help_value_assign_guarantor(oBj) {"); 
					out.println("    document.Form1.Hid_Guarantor_Code.value=oBj.valout[3];"); 
			    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?guarantor_code='+document.Form1.Hid_Guarantor_Code.value+''");
			    out.println("window.open(m_url,'displayWindow2','left=0,top=133,width=800,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
					out.println("document.Form1.Btn_Asset_Det.disabled=false;");
					//out.println("alert(document.Form1.Hid_Guarantor_Code.value);");
					out.println("}"); 
					
					
					out.println("function makeRequest() {");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_pop_LAKDL_AF_CR_Val_application_no&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"\";");
		      //out.println("window.open(m_url);")	;
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function get_vector(data_vec) {");
					out.println("vec_len = data_vec.length ;");
					out.println("if(data_vec.length==0) {");
					out.println("ret_sts='N' ;");
					out.println(" }");
					out.println("else {");
					out.println(" var i=0 ;");
					out.println("document.Form1.Hid_Client_Code.value=data_vec[i+1];");
					//out.println("alert(document.Form1.Hid_Client_Code.value);"); 
					out.println("ret_sts='Y' ;");
					out.println(" }");
				  out.println("display_msg(ret_sts);");
				  out.println("}");
					
					
					out.println("function display_msg(m_sts){"); 
					//out.println("alert('m_sts:'+m_sts);"); 
					out.println("if(m_sts=='Y' ) { ");
		      out.println("if(document.Form1.TXT_APPLICATION_NO.value==\"\" ) {"); 
					out.println("alert('Please Enter the Application No.');"); 
					out.println(" }");
		      out.println("else {"); 
					out.println("document.Form1.Btn_Applicant_Det.disabled=false;"); 
					out.println("  }");
					out.println(" }");
					
          out.println("else {"); 
					out.println("alert('Invalid Application No.');"); 
					out.println(" }");
					
					out.println("}");
	
		
					
					
					out.println("function display_applicant(){"); 
					
					//out.println("if(document.Form1.Hid_Client_Code.value==''){"); 
					//out.println("alert('Invalid Application No.');"); 
					//out.println(" }");
					//out.println("else{ ");
			    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_client_creation?client_code='+document.Form1.Hid_Client_Code.value+''");
			    out.println("window.open(m_url,'displayWindow1','left=0,top=133,width=800,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
					out.println("document.Form1.Btn_Guarantor_Det.disabled=false;");
					//out.println(" }");
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Verification</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='' value=\"Save\" Disabled></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
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
		
					out.println("<table align='center' width='100%' class='table'>"); 
		
				
					
					out.println("<tr >"); 
					out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number * </DIV></td>"); 
					out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15'  OnBlur=\"makeRequest(),disable_butttons()\">"); 
					out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"Help\" onClick=\"help_button_1('1','10','2','m_help_TXT_APPLICATION_NO','1')\" > </td>"); 
					out.println("</tr>"); 
					
					
					
								
				 out.println("</table>"); 
					
													
					out.println("<br>"); 
					out.println("<table align='center' width='100%'>"); 
					out.println("<tr>"); 
					out.println("<td width='100%' class='note'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					
					
					out.println("<br><br><br><br><br><br>"); 
				  out.println("<tr><td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr>");  
					out.println("<tr><td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
						
					out.println("<table class='table' cellpadding='2' border='0'> "); 
					out.println("<tr><td width='15%' align='center'><input type=\"button\" name=\"Btn_Applicant_Det\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Applicant Details\");' onclick='load_screen_status(\"Verify Applicant Details\"),display_applicant();' value=\"Verify Applicant\" Disabled></td>");  
					out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Guarantor_Det\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Guarantor Details\");' onClick='load_screen_status(\"Verify Guarantor Details\"),assign_status(\"Btn_Guarantor_Det\")' value=\"Verify Guarantor\" Disabled></td>");  
					out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Asset_Det\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Asset Details\");' onclick='load_screen_status(\"Verify Asset Details\"),assign_status(\"Btn_Asset_Det\")' value=\"Verify Asset\" Disabled></td>");  
					out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Invoice_Det\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Invoice/Pricing Details\");' onClick='load_screen_status(\"Verify Invoice/Pricing Details\"),assign_status(\"Btn_Invoice_Det\")' value=\"Verify Invoice/Pricing\" Disabled></td>");  
					out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_Valuation_Det\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Valuation Details\");' onClick='load_screen_status(\"Verify Valuation Details\"),assign_status(\"Btn_Valuation_Det\")' value=\"Verify Valuation\" Disabled></td>");  
					out.println("<td width='15%' align='center'><input type=\"button\" name=\"Btn_App_Doc\" class='mainbut' style='{width:160}' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Verify Applicants Documents\");' onClick='load_screen_status(\"Verify Applicants Documents\"),assign_status(\"Btn_App_Doc\")' value=\"Verify Documents\" Disabled></td>");  
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
  //    }
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
	}
}


