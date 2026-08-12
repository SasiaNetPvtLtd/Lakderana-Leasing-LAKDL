//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_CR_PRO_Stipulated_Loss_Value
//CREATED BY:Nuwan De Silva
//DATE/TIME: 21-06-07 10:44 am
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Stipulated_Loss_Value extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_CLOSE="";
			String m_client_name="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			String m_finance_no=req.getParameter("finance_no");
			String m_application_no=req.getParameter("application_no");
			String m_client=req.getParameter("client_code");
			
			stmt = conn.createStatement ();

		
			if(m_chksql.equals("main_page")){ 
			
			double m_amount=0;
			
			
		rs= stmt.executeQuery(	" SELECT DECODE(NVL("+m_schema_name+".AF_CO_GET_APP_NO_DOC_COUNT('VL','"+m_application_no+"'),0), "+
			" 0, "+
			" (SELECT "+
			" NVL(SUM(TOTAL_AMOUNT),0) SUM_VAL "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  AND "+
			" A.ACTIVE_STATUS='Y') "+
			","+
			" (SELECT "+
			" SUM(MAX(VALUE))SUM_VAL "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
			" GROUP BY PRO_INVOICE_NO ) "+
			"), "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME('"+m_client+"') CLIENT_NAME "+
			"FROM DUAL  " );
						 
     	
			
 		   boolean  more=rs.next();
				
				if(more)
				{
				m_amount=rs.getDouble(1);
				m_client_name=rs.getString(2);
				}
      		
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Stipulated Loss Value</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_button_client_code();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			
			out.println("     document.Form1.TXT_CLIENT_CODE.value=data_vec[0];");
			out.println("     document.Form1.TXT_CLIENT_NAME.value=data_vec[1];");
			out.println("get_client_details();");

			out.println("			}");
					 								
			out.println("}");
			
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			
				
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			
		
			out.println("else if(document.Form1.hid_chk_status.value=='M_INVENTORY' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_Offer_Issue_Inventory_val&data_val=\"+obj.value+\"&ac_status=ENT\";");	
				
			//out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_AMOUNT.value==\"\" ){  "); 
			out.println("DIV_TXT_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else if(document.Form1.TXT_DEP_RATE.value==\"\" ){  "); 
			out.println("DIV_TXT_DEP_RATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			out.println("ckeck_stipulated_data();");
			out.println("if(b_flag==0)");
			
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");

			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Stipulated_Loss_Value';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Stipulated_Loss_Value?chksql=main_page&finance_no="+m_finance_no+"&application_no="+m_application_no+"&client_code="+m_client+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Stipulated_Loss_Value?chksql=main_page&finance_no="+m_finance_no+"&application_no="+m_application_no+"&client_code="+m_client+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Stipulated_Value\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Stipulated Loss Value  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Stipulated Loss Value - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_client_code();"); 
	  	out.println("		}"); 
		
								
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
			out.println("	clear_data(IfCount);");//Added To The Clear 
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
			
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------

			out.println(""); 
			
			
			
			
			
			out.println("function clear_data(IfCount) {");
			
		
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
			out.println("document.Form1.TXT_CLIENT_NAME.value='';");
				
			
			out.println("}");
			
			
					
			
				  out.println("function help_button_client_code() {"); 
						
					out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
					
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE_new','1');"); 
										
					out.println("}"); 
					
		
					out.println("function help_value_assign_client_code() {"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
					out.println("get_client_details();");
					out.println("}"); 
			
     
			out.println("function get_client_details(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Insuarance_Date?chksql=view&data_val=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
			
			
			out.println("function check_value(obj) {"); // added by Chatura Jayawardena
			out.println("  if(parseInt(obj.value) >= 0){");
			out.println(" if(check_number_precent_value(obj,6)){");
			out.println("  return true;");
			out.println("}else{");
			out.println("return false;");
			out.println("}");
			out.println("}else{");
			out.println("alert('Depreciation Rate must be a positive value');");
			out.println("return false;");
			out.println("}");
			out.println("}");
					
	   	out.println("function check_number_precent_value(obj,size){"); // added by Chatura Jayawardena
			out.println("if(obj.value!='' && obj.value!='-' ) ");
			out.println("if(isnumberok(obj,size)){ ");
			
			out.println("if( parseInt(obj.value) <= 100 ){ ");
			out.println("format_number(obj,size) ;");
			out.println("return true;");
			out.println("}");
			
			out.println("else");
			out.println("{");
			out.println("alert('Number can not exceed 100'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("return false;");
			out.println("}");
			
			out.println("}");
			
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("return false;");
			out.println("} ");
			out.println("}");
				
					
			
			out.println("function ckeck_stipulated_data(){ "); 
			
			out.println("b_flag=0;");
						
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
									
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			
	
			//===========================================================================================
			
			
			out.println("function set_stipulated_value(obj1,obj2) {");
			
			out.println("if(obj1.value!='' && obj2.value!='' ){ ");
			out.println("if(check_value(obj1)){");  // added by Chatura Jayawardena
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Stipulated_Loss_Value?chksql=view&rate=\"+document.Form1.TXT_DEP_RATE.value+\"&amount=\"+document.Form1.TXT_AMOUNT.value+\"&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("load_interface(m_url,'NORM');");
			
			out.println("}");
			
			out.println("}");
			out.println("}");
			
			
			
			
			
			
			
				
				
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_STIPULATED_MAIN_SCREEN\">"); 

			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Stipulated Loss Value </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//	out.println("<td width='10%'></td>");
			//	out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
		
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' value='"+m_finance_no+"' size='15'  disabled>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' value='"+m_client+"'  disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' >Client Name</td>"); 
			out.println("<td width='40%'><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='20' style=\"width:250px;\"  value='"+m_client_name+"' disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AMOUNT'  class=div_input>Amount *</DIV> </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_AMOUNT' maxlength='20' onBlur=\"set_stipulated_value(this,document.Form1.TXT_DEP_RATE)\" style=\"text-align:right;\" value='"+nf.format(m_amount)+"'   ></td>"); 
	    out.println("<input type=hidden name=HID_TXT_AMOUNT value=\""+m_amount+"\">");
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_DEP_RATE'  class=div_input>Depreciation Rate (%) *</DIV> </td>"); 
			out.println("<td width='40%'><input class='txt_input' type='text' name='TXT_DEP_RATE' maxlength='20' onBlur=\"set_stipulated_value(this,document.Form1.TXT_AMOUNT)\" style=\"width:60px; text-align:right;\"  ></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
		
			
					
			
						
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>"); 
			
			
			
				
			out.println("</table>"); 
			out.println("<br>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			
      out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			stmt.close();
			
			out.flush();
			
			}
		
		else if(m_chksql.equals("view")){		
		
		m_finance_no=req.getParameter("data_val");
		String  m_amount=req.getParameter("amount");
		String  m_rate=req.getParameter("rate");
		
		double m_net_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_amount));
		double m_dep_rate  =Double.parseDouble(m_sn_methods.met_unformat_number(m_rate));
		double dep_amount=0;
		
		//calculating depretiation amount------
		dep_amount=(m_net_amount*m_dep_rate)/100;
		
		
		stmt = conn.createStatement ();
				
rs= stmt.executeQuery("		SELECT "+
"		CEIL(PERIOD*TO_NUMBER(DECODE(PAYMENT_INTERVAL,'6','6','3','4','1','12','4','3','12','1','365','1','52','4/12'))/12) "+
"		FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
"		WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
"		UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
"   AND A.ENT_DATE IN (SELECT MAX(ENT_DATE)  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING GROUP BY APPLICATION_NO)"); //Added By Sandun on 16-12-2008

			
	out.println("<br>");			
				
	out.println("<table width=\"30%\"  align=\"left\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
 
	out.println(" <tr class=pdn_txtpos2 style={padding-left:0px} > ");//pdn_txtpos2
  out.println("  <td width=\"10%\" align='left'>Year</td> ");
  out.println("  <td width=\"20%\" align='right'>Net Value</td> ");
	
  out.println(" </tr>");
	
	
		 
			int j=1;
			
			int count=0;
			if(rs.next()){
			count=rs.getInt(1);
			}
     
			while(j<=count){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			  
					if(j==1){
					out.println("  <td width=\"10%\" align='left'>Year"+j+" </td> ");
				  out.println("  <td width=\"20%\" style=\"{text-align:right} \" align='right'>"+nf.format(m_net_amount)+"</td> ");
					
			
			    out.println("<input type=hidden name=hid_Amount_Year"+j+" value="+m_net_amount+">");
					}
					  
					
						
						
					else
					{
					m_net_amount= m_net_amount - dep_amount;
					
					out.println("  <td width=\"10%\" align='left'>Year"+j+" </td> ");
				  out.println("  <td width=\"20%\" style=\"{text-align:right} \" align='right'>"+nf.format(m_net_amount)+"</td> ");
					
			
			    out.println("<input type=hidden name=hid_Amount_Year"+j+" value="+m_net_amount+">");
					
					}
			    					
     			out.println("</tr >"); 
			    
					j=j+1;
			
			
			}
			
			out.println("<input type=hidden name=hid_no_rec value="+j+">");
			
			
      out.println("</table >"); 
}
			
				
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
