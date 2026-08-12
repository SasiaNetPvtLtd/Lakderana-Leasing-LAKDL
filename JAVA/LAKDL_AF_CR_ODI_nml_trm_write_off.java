//------------------------------------------------------//
// 		SCREEN NAME	: Odi Waved Off - Normal Termination	//
//		CREATED BY	:	Susitha Janaka											// 
//		DATE/TIME		: 01-04-2011													//
//------------------------------------------------------//

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;
import java.math.BigDecimal;


public class LAKDL_AF_CR_ODI_nml_trm_write_off extends HttpServlet {
    
    Connection conn;
    Statement stmt,stmt1;
    java.text.NumberFormat nf,nf1;
    ResultSet rs,rs1,rs2;
    String m_chksql;
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
            
            int row = 0;
            
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
                
                
                
                
                out.println("           function validate_data(m_val){"); 
                //	out.println("//validations goes here"); 
                out.println("               if (document.Form1.TXT_FINANCE_NO.value == '') {");
                out.println("                   DIV_TXT_FINANCE_NO.style.color = 'red';");
                out.println("                   return false;"); 
                out.println("               }"); 
                out.println("               else if (m_val == 1) {"); 
                out.println("                   var total_row_count = parseInt(document.getElementById('HID_TOTAL_ROW_COUNT').value);"); 
                out.println("                   var selected_row_count = 0;"); 
                out.println("                   for (var i = 0; i < total_row_count; i++) {"); 
                out.println("                       if (document.getElementById('chk_trm_' + i).checked == true) {"); 
                out.println("                           selected_row_count++;"); 
                out.println("                           break;"); 
                out.println("                       }"); 
                out.println("                   }"); 
                out.println("                   if (selected_row_count == 0) {"); 
                out.println("                       alert('Warning: Please select at least a single row.');"); 
                out.println("                       return false;"); 
                out.println("                   }"); 
                out.println("               }"); 
                out.println("               return true;"); 
                out.println("           }"); 
                
                
                
                out.println("function load_lock(){	"); 
                out.println("document.oncontextmenu=new Function(\"return false\");"); 
                out.println("}	"); 
                
                out.println("function clear_window(){	"); 
                out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_nml_trm_write_off?chksql=main_page';"); 
                out.println("		}"); 
                out.println("}"); 
                
                out.println("function new_window(){	"); 
                out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_nml_trm_write_off?chksql=main_page';"); 
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
                out.println("help_box.innerHTML=\" Credit Process - ODI Waved Off  Normal Termination - \"+m_val;"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function load_roll_out_value(){");
                out.println("help_box.innerHTML=\" Credit Process - ODI Waved Off  Normal Termination - \"+document.Form1.hid_status.value;"); 
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
                out.println("if(validate_data(0)){");
                
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_nml_trm_write_off?chksql=detail&FIN_NO=\"+obj1+\"&CLN_NO=\"+obj2+\" \";");
                //out.println("window.open(m_url)");
                out.println("load_interface(m_url,'NORM');");
                out.println("}");
                out.println("}");
                
                out.println("function get_vector_normal(http_response) {");
                out.println(" request_details.innerHTML = ''; ");
                out.println(" request_details.innerHTML = http_response; ");
                
                out.println("}");			
                
                out.println("function set_amount_drill(fin_no,client_no){");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_nml_trm_write_off?chksql=settled_detail&fin_no=\"+fin_no+\"&cln_no=\"+client_no+\" \";");	
                out.println("window.open(m_url,'w01','top=150,left=150,width=500,height=400,resizable=1');");
                out.println("}");		
                
                
                out.println("function before_submit(){ "); 
                out.println("		m_option = document.Form1.hid_status.value;"); 
                out.println("		if(validate_data(1)){"); 
                out.println("		if(confirm('Are you sure you want to save?')){"); 
                out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_nml_trm_save';");  
                out.println("		document.Form1.submit();	"); 
                out.println("		}"); 
                out.println("		}"); 
                out.println("	} "); 
                
                
                out.println("function save_window(){	"); 
                out.println("before_submit();"); 
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
                out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Credit Process - ODI Waved Off Normal Termination </td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
                
                out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("<tr><td width='10%' align='center'></td>");  
                out.println("<td width='6%'></td>");  
                out.println("<td width='10%' align='center'></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
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
                out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"60%\" >");
                
                //------------------------------------
                
                out.println("<tr >"); 
                out.println("<td width='2%'>&nbsp;</td>");
                out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_NO'  class=div_input>Client Code</DIV></td>"); 
                out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_NO' maxlength='10' size='10' onblur=\"help_update_client()\" >"); 
                out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_client()\"></td>");
                out.println("<td >&nbsp;</td>");
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                out.println("<tr >");
                out.println("<td width='2%'>&nbsp;</td>");
                out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No</DIV></td>");// onblur=\"help_update_finance()\" 
                out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='10' >"); 
                out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_update_finance()\"></td>"); 
                out.println("<td width='10%'><input type=\"button\" class='but_input' style='width:60px' onclick='report_window(document.Form1.TXT_FINANCE_NO.value,document.Form1.TXT_CLIENT_NO.value )' value=\"View\"></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 			
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
                
                //------------------------------------
                
                out.println("<table align='center' width='100%' class='table'>"); 
                out.println("<tr>");  
                out.println("<td width=\"100%\"><DIV ID='request_details'> </DIV></td>");
                out.println("</tr>"); 
                out.println("</table>");
                
                
                out.println("</table>"); 
                out.println("<br>"); 
                out.println("<table align='center' width='100%'>"); 
                out.println("<tr>"); 
                out.println("<td width='100%' class='note'><INPUT TYPE='hidden' name='num_row' value='"+row+"'></td>"); 
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
            else if(m_chksql.trim().equals("detail")){	
                
                /*
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
            
            */
                
                
                //out.println( " select a.finance_no,"+m_schema_name+".AF_CO_GET_ODI_AMT(a.finance_no,TO_CHAR(sysdate,'DD-MM-YYYY' )),a.client_code,a.client_no,"+m_schema_name+".AF_CO_GET_APP_TER_STATUS_NT(A.application_no)  "+
                /*rs = stmt.executeQuery( " SELECT A.FINANCE_NO,"+m_schema_name+".AF_CO_GET_ODI_DUE(A.FINANCE_NO),A.APPLICATION_NO,A.CLIENT_CODE,A.CLIENT_NO,"+m_schema_name+".AF_CO_GET_APP_TER_STATUS_NT(A.APPLICATION_NO) "+
                                                                "	FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
                                                                "	WHERE "+m_schema_name+".AF_CO_GET_APP_TER_STATUS_NT(A.APPLICATION_NO)='Normal-Termination' ORDER BY A.FINANCE_NO "+ 
                                                                " " );
                */
                
                
                
                String m_finance_no = req.getParameter("FIN_NO");
                String m_client = req.getParameter("CLN_NO");
                int m_row_count = 0;
                
                String sqlQuery = null;
                
                sqlQuery = " " +
                    "   SELECT K.FINANCE_NO, " +
                    "          K.ODI_DUE, " +
                    "          K.APPLICATION_NO, " +
                    "          K.CLIENT_CODE, " +
                    "          K.CLIENT_NO, " +
                    "          K.NT, " +
                    "          K.CONTRACT_BALANCE " +
                    "   FROM ( " +
                    "       SELECT R.FINANCE_NO, " +
                    "              R.ODI_DUE, " +
                    "              R.APPLICATION_NO, " +
                    "              R.CLIENT_CODE, " +
                    "              R.CLIENT_NO, " +
                    "              " + m_schema_name + ".AF_CO_GET_APP_TER_STATUS_NT(R.APPLICATION_NO) NT, " +
                    "              R.CONTRACT_BALANCE " +
                    "       FROM ( " +
                    "           SELECT A.FINANCE_NO, " +
                    "                  " + m_schema_name + ".AF_CO_GET_ODI_DUE(A.FINANCE_NO) ODI_DUE, " +
                    "                  A.APPLICATION_NO, " +
                    "                  A.CLIENT_CODE, " +
                    "                  A.CLIENT_NO, " +
                    "                  " + m_schema_name + ".AF_CO_GET_CONTRACT_BAL(A.FINANCE_NO, A.CLIENT_CODE, TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'LAKDL') CONTRACT_BALANCE " +
                    "           FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A " +
                    "           WHERE  A.APPLICATION_STATUS = 'ACTIVATED' " +
                    "           AND    UPPER(A.FINANCE_NO) LIKE UPPER('%" + m_finance_no + "%') " +
                    "           AND    UPPER(A.CLIENT_CODE) LIKE UPPER('%" + m_client + "%') " +
                    "       ) R " +
                    "       WHERE R.ODI_DUE <> 0 " +
                    "   ) K " +
                    "   WHERE K.NT = 'Normal-Termination' " +
                    "   AND   K.CONTRACT_BALANCE < 0 " +
                    "   ORDER BY K.FINANCE_NO ASC " +
                    " ";
                
                // out.println(sqlQuery);
                rs = stmt.executeQuery(sqlQuery);
                boolean more = rs.next();
                
                out.println("<table border='0' width='450' class='table'   >"); 	
                if (!more){
                    out.println("<tr>"); 
                    out.println("<td width='100%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
                    out.println("</tr>"); 
                }
                
                if (more) {
                    
                    out.println("<tr class=pdn_txtpos2 >");
                    out.println("<td  align='center'><b>Contract No</td>");
                    //out.println("<td width='8%' align='center'><b>Application No</td>");
                    out.println("<td  align='center'><b>Ledger Balance</td>");
                    out.println("<td  align='center'><b>Odi Amount</td>");
                    out.println("<td  align='center'><b>ODI Allocation (Against To Credit balance)</td>");
                    out.println("<td  align='center'><b>ODI Waiver</td>");
                    
                    out.println("<td width='10%'  align='center'><b>Terminate</td>");	
                    out.println("<tr>");
                    
                    BigDecimal m_ledger_balance = null;
                    BigDecimal m_odi_amount = null;
                    
                    while(more){
                        
                        if(row>0 && row%2==1){out.println("<tr  bgcolor=silver >");} 
                        else{out.println("<tr >");} 
                        
                        m_ledger_balance = rs.getBigDecimal("CONTRACT_BALANCE").setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        m_odi_amount = rs.getBigDecimal("ODI_DUE").setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        
                        out.println("<td  >"+rs.getString(1)+"</td>");
                        out.println("<INPUT TYPE='Hidden' NAME='fin_no_"+row+"' VALUE=\""+rs.getString(1)+"\">");
                        //out.println("<td width='8%'  >"+rs.getString(3)+"</td>");
                        out.println("<td   align='right'>"+nf.format(m_ledger_balance)+"</td>");
                        out.println("<td   align='right'>"+nf.format(m_odi_amount)+"</td>");
                        out.println("<td   align='right'>"+nf.format(m_ledger_balance.negate())+"</td>");
                        out.println("<td   align='right'>"+nf.format(m_odi_amount.subtract(m_ledger_balance.negate()))+"</td>");
                        // out.println("<INPUT TYPE='Hidden' NAME='amt_"+row+"' VALUE=\""+rs.getDouble(2)+"\">");
                        out.println("<INPUT TYPE='Hidden' NAME='amt_"+row+"' VALUE=\""+m_odi_amount.subtract(m_ledger_balance.negate())+"\">");
                        out.println("<td width='10%'  align='center'><INPUT TYPE='checkbox' id='chk_trm_"+row+"' NAME='chk_trm_"+row+"' ></TD>");
                        out.println("</tr>");
                        
                        row++;
                        m_row_count++;
                        more = rs.next();
                    }
                    
                    
                    
                    
                }
                
                out.println("<input type=\"hidden\" id=\"HID_TOTAL_ROW_COUNT\" name=\"HID_TOTAL_ROW_COUNT\" value=\"" + m_row_count + "\" />");
                
            }
            
            //=========================================================================================================================			
            
            
            
            
            
            
            
            
            
            
            
            
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
