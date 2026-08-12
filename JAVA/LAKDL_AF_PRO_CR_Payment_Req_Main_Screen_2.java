/*
 * SCREEN NAME  : Credit Process - Main Screen Payment New 2
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-14
 * NOTES        :
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;

public class LAKDL_AF_PRO_CR_Payment_Req_Main_Screen_2 extends HttpServlet {
    
    LAKDL_AF_CO_conn_methods m_sn_methods;
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String sql;
    
    ServletOutputStream out;
    
    NumberFormat nf;
    
    public String m_chksql;
    public String m_chksql1;
    public String m_sql;
    String m_sort_column;
    String m_order_by_type;
    String m_screen_type;
    String m_status_edit;
    String m_status_new;
    
    String vendor_code;
    String process_status;
    
    public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        
        try {
            
            m_sn_methods = new LAKDL_AF_CO_conn_methods();
            connection = m_sn_methods.met_user_validate(httpServletRequest);
            
            String m_html_client_url = m_sn_methods.html_client_url.trim();
            String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
            String m_fschema_name = m_sn_methods.client_name.trim();
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("text/html");
            out = httpServletResponse.getOutputStream();
            
            String m_schema_name = m_sn_methods.schema_name;
            
            String m_level="";
            String m_screen_name1="";
            
            String m_username =  m_sn_methods.username;
            
            m_sql = httpServletRequest.getParameter("sql");
            m_chksql = httpServletRequest.getParameter("chksql");
            m_chksql1 = httpServletRequest.getParameter("chksql2");
            m_screen_type= httpServletRequest.getParameter("screen_type");
            m_status_new= httpServletRequest.getParameter("status_new");
            m_status_edit= httpServletRequest.getParameter("status_edit");
            if(m_screen_type==null){
                m_screen_type="NEW";
            }
            
            nf = NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(0);
            nf.setMaximumFractionDigits(0);
            String m_new_status="";
            
            if(m_chksql1.equals("B")){
                m_level="approval_main";
                // m_screen_name1="approval_main";
                m_screen_name1 = "Requisition - Processing";
                m_status_new="RE_APP";
                m_status_edit="RE_A_2";
                
                if(m_screen_type.equals("NEW")){
                    process_status="RE-APP";
                    m_new_status="Y";
                    
                }
                //if(m_screen_type.equals("EDIT")){
                if(m_screen_type.equals("REVERSE")){
                    process_status="RE_A_2";
                    m_new_status="RE_A_2";
                    
                }
            }
            
            /*else if(m_chksql1.equals("A")){
			m_level="Approval_2";
			m_screen_name1="Approval 2";

			m_status_new="APPRO1";
			m_status_edit="APPRO2";
			if(m_screen_type.equals("NEW")){
			process_status="APPRO1";
			m_new_status="APPRO1";

			}
			if(m_screen_type.equals("EDIT")){
			process_status="APPRO2";
			m_new_status="APPRO2";
			}
			}
			*/
            
            
            m_sort_column   = "APPLICATION_NO";
            m_order_by_type = "DESC";
            
            
            if (m_chksql.trim().equals("idle")) {
                out.println("idle");
            }
            
            else if (m_sql.equals("main_page")) {
                
                // m_sort_column   = "APPLICATION_NO";
                // m_order_by_type = "DESC";
                
                if ((httpServletRequest.getParameter("sort_column") != null) && (httpServletRequest.getParameter("order_by_type") != null)) {
                    m_sort_column = httpServletRequest.getParameter("sort_column");
                    m_order_by_type = httpServletRequest.getParameter("order_by_type");
                }
                
                out.println("<html>");
                out.println("   <head>");
                // out.println("       <title>Finance - Payment " + m_screen_name1 + "</title>");
                out.println("       <title>Finance - Payment Requisition - Processing</title>");
                
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url+"/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javascript\" src=\""+m_html_client_url+"/leasing_drill_down.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\""+m_html_client_url+"/validate.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\""+m_html_client_url+"/ajax_data_gateway.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\""+m_html_client_url+"/validate_v1.js\"></script>");
                
                out.println("       <script type=\"text/javascript\">");
                out.println("var st_val='';");
                out.println("var st_val1='';");
                out.println("st_val='"+m_chksql+"'");//VERIFY
                out.println("st_val1='"+m_chksql1+"'");//B
                out.println("var m_order_by_type");
                out.println("var row_arry=new Array();");
                out.println("var chk_chng=0");
                out.println("var m_row=''");
                
                
                out.println("function get_vector(data_vec) {");
                out.println("	 if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
                out.println("alert('Temporary Invoice exsits')");
                out.println("document.Form1.elements[\"BUT_VIEW_\"+m_row].disabled=true");
                out.println("			}");
                out.println("if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
                out.println("		if(confirm(\"Are you sure you want to generate Payment Requisition?\")){ "); 
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+m_row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+m_row].value+'&CURR_CODE='+document.Form1.elements[\"TXT_CURR_CODE_\"+m_row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+m_row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+m_row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+m_row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+m_row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+m_row].value+'&chksql1="+m_chksql1+"';"); 
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                out.println("			}");
                out.println("}");
                
                out.println("function check_invoice(row) {");
                out.println("m_row=row");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_settle_temp&sus_ref_no=\"+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+\"&ref_no=\"+document.Form1.elements[\"TXT_REF_NO_\"+row].value+\"\";");
                out.println("load_interface(m_url,'XML');");
                out.println("}");
                
                
                // out.println("function before_submit(){ "); 
                // out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
                // out.println("  chk=\"CHK_APP_\"+d;");
                // out.println("if(document.Form1.elements[chk].checked==false){"); 
                // out.println("chk_chng=0");
                // out.println("} ");
                // out.println("if(document.Form1.elements[chk].checked==true){"); 
                // out.println("chk_chng=1");
                // out.println("break");
                // out.println("} ");
                // out.println("		}");
                // out.println("		if(chk_chng==1){");
                // out.println("		if(confirm(\"Are you sure you want to delete?\")){ "); 
                // out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
                // out.println("document.Form1.elements[i].disabled=false;");
                // out.println("}");
                // //out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_details1?screen_type="+m_screen_type+"&level="+m_level+"&number='+document.Form1.hid_count.value+'&status="+m_status+"&chksql="+m_chksql+"&chksql1="+m_chksql1+"';");   
                // out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Save_Payment_Reverse';");   
                // out.println("		document.Form1.submit();	"); 
                // out.println("		}"); 
                // out.println("} ");
                // out.println("else{");
                // out.println("alert('Please select a Application No')");
                // out.println("}");
                // out.println("} ");
                
                
                out.println("           function clear_window() {");
                out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
                out.println("                   window.location.href = window.location.href;");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function new_window() {");
                out.println("               window.location.href = window.location.href;");
                out.println("           }");
                
                
                out.println("function edit_window(){	"); 
                //out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=REVERSE&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
                out.println("}"); 
                
                
                out.println("function load_help_msg() {");
                if(m_chksql1.equals("B")){
                    out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_1\";"); 
                }
                else if(m_chksql1.equals("A")){
                    out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
                }
                else if(m_chksql1.equals("R")){
                    out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_req\";"); 
                }
                
                out.println("    HelpBox_msg(m_help_message);"); 
                out.println("}"); 	
                
                out.println("function HelpBox_msg(m_help_message) {"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
                out.println("  \"&help_message_in=\"+m_help_message);"); 
                out.println("}"); 
                
                
                out.println("           function load_roll_value(m_val) {");
                out.println("               document.getElementById('help_box').innerHTML = 'Finance - Payment Requisition - Processing - ' + m_val;");
                out.println("           }");
                
                
                out.println("           function load_roll_out_value() {");
                out.println("               document.getElementById('help_box').innerHTML = 'Finance - Payment Requisition - Processing - ' + document.getElementById('hid_status').value;");
                out.println("           }");
                
                
                out.println("           function load_screen_status(m_val) {");
                out.println("               if(m_val == \"NEW\") {");
                out.println("                   new_window();"); 
                out.println("               }");
                out.println("               else if (m_val == \"HELP\") {");
                out.println("                   load_help_msg();"); 
                out.println("               }"); 
                out.println("               else if (m_val == \"PRINT_VOUCHER\") {");
                out.println("                   print_voucher();"); 
                out.println("               }"); 
                // out.println("else if(m_val==\"REVERSE\"){"); //Added BY Sandun on 19-01-2009
                // out.println("edit_window();"); 
                // out.println("}"); 
                // out.println("else if(m_val!=\"EDIT\"){"); 
                // out.println("}"); 
                // out.println("else{");
                // out.println("edit_window();"); 
                // out.println("}");
                // out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
                // out.println("if(m_val==\"NEW\"){");
                // out.println("document.Form1.hid_status.value=\"New\";"); 
                // out.println("document.Form1.hid_save.value=\"save\";");
                // out.println("}else if(m_val==\"EDIT\"){");
                // out.println("document.Form1.hid_status.value=\"Delete\";");
                // out.println("document.Form1.hid_save.value=\"Delete\";");
                // out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
                // out.println("document.Form1.elements[i].disabled=false;");
                // out.println("}");
                // out.println("}else if(m_val==\"DACT\"){");  
                // out.println("document.Form1.hid_status.value=\"Deactivate\";");  
                // out.println("}else if(m_val==\"RACT\"){");  
                // out.println("document.Form1.hid_status.value=\"Reactivate\";");  
                // out.println("}else{");  
                // out.println("document.Form1.hid_status.value=\"\";");  
                // out.println("}"); 
                out.println("}"); 
                
                out.println("function MyDialog(){"); 
                out.println("    this.valout   = new Array(10);"); 
                out.println("}		"); 
                
                
                out.println("           function print_voucher() {");
                out.println("               document.getElementById('bt_save').disabled = true;");
                out.println("               document.getElementById('DIV_TEXTBOX_CRITERIA').innerHTML = '' +");
                out.println("                   '   <table align=\"left\" class=\"table\">' + ");
                out.println("                   '       <tr class=\"tr_input\">' + ");
                out.println("                   '           <td style=\"width: 200px;\"><div class=\"div_input\" id=\"DIV_GROUP_PAYMENT_CODE\">Master SP Number (GP)</div></td>' + ");
                out.println("                   '           <td style=\"width: 300px;\">' + ");
                out.println("                   '               <input class=\"txt_input\" type=\"text\" name=\"TXT_GROUP_PAYMENT_CODE\" id=\"TXT_GROUP_PAYMENT_CODE\" maxlength=\"200\" style=\"width: 200px;\" onblur=\"help_button_3();\" />' + ");
                out.println("                   '               <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CLIENT_NAME\" id=\"BUT_TXT_CLIENT_NAME\" value=\"Help\" onclick=\"help_button_3();\" />' + ");
                out.println("                   '           </td>' + ");
                out.println("                   '       </tr>' + ");
                out.println("                   '   </table>' + ");
                out.println("                   '';");
                out.println("               document.getElementById('DIV_PAYMENTS').innerHTML = '';");
                out.println("           }");
                
                
                /*
                out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
                out.println("    oBj = new MyDialog();"); 
                out.println("    oBj.valout[1]  = \" \";"); 
                out.println("    oBj.valout[2]  = \" \";"); 
                out.println("    oBj.valout[3]  = \" \";"); 
                out.println("	"); 
                // out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Servlet?class_in=\"+client_name+\"AF_PRO_CR_help_select\"+"); 
                out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
                out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
                out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
                out.println("	"); 
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	if(oBj.valout[1]!=\"Next\"){"); 
                out.println(" if(document.getElementById('hid_help_type').value=='1'){"); 
                out.println("		help_value_assign_1(oBj);"); 
                out.println("}");
                out.println("		if(document.getElementById('hid_help_type').value==\"99\"){"); 
                out.println("		help_update_value_assign_99();"); 
                out.println("		}"); 
                // out.println("		if(document.getElementById('hid_help_type').value==\"1\"){"); 
                // out.println("		help_value_assign_1();"); 
                // out.println("		}"); 
                out.println("		if(document.getElementById('hid_help_type').value==\"2\"){"); 
                out.println("		help_value_assign_2();"); 
                out.println("		}"); 
                out.println("		if(document.getElementById('hid_help_type').value==\"3\"){"); 
                out.println("		help_value_assign_3();"); 
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
                out.println("	}	"); 
                out.println("}"); 
                out.println(""); 
                */
                
                
                out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
                out.println("    oBj = new MyDialog();"); 
                out.println("    oBj.valout[1]  = \" \";"); 
                out.println("    oBj.valout[2]  = \" \";"); 
                out.println("    oBj.valout[3]  = \" \";"); 
                
                out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	if(oBj.valout[1]!=\"Next\"){"); 
                out.println(" if(document.getElementById('hid_help_type').value=='1'){"); 
                out.println("		help_value_assign_1(oBj);"); 
                out.println("}");
                out.println(" else if(document.getElementById('hid_help_type').value=='2'){"); 
                out.println("		help_value_assign_2(oBj);"); 
                out.println("}");
                out.println(" else if(document.getElementById('hid_help_type').value=='3'){"); 
                out.println("		help_value_assign_3(oBj);"); 
                out.println("}");
                out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
                out.println("		help_value_assign_5(oBj);"); 
                out.println("}");
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
                out.println("clear_data()");
                out.println("	}");
                out.println("}");
                out.println("if(oBj.valout[2]==' '){");
                out.println("clear_data()");
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
                
                out.println("function help_button(row) {"); 
                
                /*if(m_level.equals("Requisition_Approval")){
                out.println("check_invoice(row)");
                }
                
                if(m_level.equals("approval_main")){
                out.println("		if(confirm(\"Are you sure you want to select account for this this Payment?\")){ "); 
                out.println("		if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)==\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&chksql1="+m_chksql1+"';"); 
                out.println("}");
                
                out.println("else	if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)!=\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}");
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                }
                if(m_level.equals("Approval_2")){
                out.println("		if(confirm(\"Are you sure you want to approve this Payment?\")){ "); 
                out.println("if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)==\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app2_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}"); 		
                out.println("else	if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)!=\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_repos2_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}"); 		
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                }
                */
                out.println("		if(confirm(\"Are you sure you want to select account for this this Payment?\")){ "); 
                //out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&chksql1="+m_chksql1+"';"); 
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&CURR_CODE='+document.Form1.elements[\"TXT_CURR_CODE_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&chksql1="+m_chksql1+"';"); 
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                
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
                
                
                
                out.println("           function sort_data(m_sort_col) {");
                out.println("               m_order_by_type = 'DESC';");
                out.println("               if (m_sort_col == '" + m_sort_column + "') {");
                out.println("                   if ('" + m_order_by_type + "' == 'DESC') {");
                out.println("                       m_order_by_type = 'ASC';");
                out.println("                   }");
                out.println("                   else {");
                out.println("                       m_order_by_type = 'DESC'; ");
                out.println("                   }");
                out.println("               }");
                out.println("               else {");
                out.println("                   m_order_by_type = 'ASC'; ");
                out.println("               }");
                
                if (m_screen_type.equals("NEW")) {
                    // out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
                    out.println("m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Req_Main_Screen_2?screen_type=NEW&sql=load_data_client&chksql=" + m_chksql + "&chksql2=" + m_chksql1 + "&m_client_name=' + document.getElementById('hid_m_client_name').value + '&sort_column=' + m_sort_col + '&order_by_type=' + m_order_by_type;");
                }
                else if (m_screen_type.equals("EDIT")) {
                    //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&status_edit="+m_status_edit+"&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
                    out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
                    //out.println(" window.location.href=m_url;"); 
                }
                else if (m_screen_type.equals("REVERSE")) {
                    out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
                }
                
                out.println("               load_interface(m_url, 'NORM');");
                out.println("           }");
                
                
                out.println("           function search_client_details(m_client_name) {");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Req_Main_Screen_2?screen_type = " + m_screen_type + "&sql=load_data_client&chksql=" + m_chksql + "&chksql2=" + m_chksql1 + "&m_client_name=' + m_client_name;");
                out.println("               load_interface(m_url, 'NORM');");
                out.println("           }");
                
                
                out.println("           function get_vector_normal(http_response) {");
                out.println("               document.getElementById('DIV_PAYMENTS').innerHTML = http_response;");
                out.println("           }");
                
                
                out.println("function check_change(row) {"); //Added By Sandun on 19-01-2009
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
                
                
                out.println("           function check_account(obj) {");
                // out.println("               assig('B2');");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_sql_validations?chksql=m_prime_chk_" + m_fschema_name + "AF_PRO_CR_display_licencee_settlement&data_val=' + obj.value + '&ac_status=Y';");
                out.println("               load_interface(m_url, 'XML');");
                out.println("           }");
                
                
                out.println("           function assig(val) {"); // Use to identify on which text box focus is on.
                out.println("               document.Form1.hid_st.value=val;");
                out.println("           }");
                
                
                out.println("           function help_button_1() {");
                out.println("               document.getElementById('hid_help_type').value = '1';");
                out.println("               m_sql = 'VendorSql';");
                out.println("               m_criteria = document.getElementById('TXT_CLIENT_NAME').value + '@Y@';");
                out.println("               HelpBox('1', '10', '0');");
                out.println("           }");
                
                
                out.println("           function help_button_2() {");
                out.println("               document.getElementById('hid_help_type').value = '2';");
                out.println("               m_sql = 'm_help_TXT_ACCOUNT_2_sql';");
                out.println("               m_criteria = document.getElementById('TXT_ACC_NO').value + '@Y@';");
                out.println("               HelpBox('1', '10', '0');");
                out.println("           }");
                
                
                out.println("           function help_button_3() {");
                out.println("               document.getElementById('hid_help_type').value = '3';");
                out.println("               m_sql = 'GroupPaymentCodeSql';");
                out.println("               m_criteria = document.getElementById('TXT_GROUP_PAYMENT_CODE').value + '@';");
                out.println("               HelpBox('1', '10', '0');");
                out.println("           }");
                
                
                out.println("           function help_value_assign_1(oBj) {"); 
                out.println("               document.getElementById('DIV_PAYMENTS').innerHTML = '';");
                out.println("               document.getElementById('HID_TOTAL_AMOUNT').value = 0;");
                out.println("               document.getElementById('DIV_TOTAL_AMOUNT').innerHTML = '0.00';");
                out.println("               document.getElementById('DIV_SELECTED_COUNT').innerHTML = '0 / 20';");
                out.println("               document.getElementById('hid_selected_checkbox_count').value = '0';");
                
                out.println("               document.getElementById('HID_CLIENT_CODE').value = oBj.valout[2];");
                out.println("               document.getElementById('TXT_CLIENT_NAME').value = oBj.valout[3];");
                out.println("               search_client_details(document.getElementById('HID_CLIENT_CODE').value);");
                out.println("           }");
                
                
                out.println("           function help_value_assign_2(oBj) {"); 
                out.println("               document.getElementById('TXT_ACC_NO').value = oBj.valout[2];");
                out.println("               document.getElementById('HID_BRANCH_CODE').value = oBj.valout[3];");
                out.println("               document.getElementById('TXT_BRANCH_NAME').value = oBj.valout[4];");
                out.println("           }");
                
                
                out.println("           function help_value_assign_3(oBj) {");
                out.println("               document.getElementById('TXT_GROUP_PAYMENT_CODE').value = oBj.valout[2];");
                
                out.println("               var group_payment_no = document.getElementById('TXT_GROUP_PAYMENT_CODE').value;");
                out.println("               var m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Group_Payment_Details_display?group_payment_no=' + group_payment_no;");
                
                out.println("               var sFeatures = '';");
                out.println("               sFeatures += 'dialogHeight: ' + (parseInt(screen.availHeight) - 100) + 'px;';");
                out.println("               sFeatures += 'dialogWidth: ' + (parseInt(screen.availWidth) - 150) + 'px;';");
                // out.println("               sFeatures += 'status: no;';");
                
                out.println("               window.showModalDialog(");
                out.println("                   m_url,");
                out.println("                   group_payment_details,");
                out.println("                   sFeatures");
                out.println("               );");
                out.println("               document.getElementById('TXT_GROUP_PAYMENT_CODE').value = '';");
                out.println("           }");
                
                
                out.println("           function group_payment_details() {");
                out.println("               var group_payment_details;");
                out.println("           }");
                
                
                out.println("           function clear_data() {");
                out.println("               if (document.getElementById('hid_help_type').value == '1') {");
                out.println("                   document.getElementById('DIV_PAYMENTS').innerHTML = '';");
                out.println("                   document.getElementById('HID_TOTAL_AMOUNT').value = 0;");
                out.println("                   document.getElementById('DIV_TOTAL_AMOUNT').innerHTML = '0.00';");
                out.println("                   document.getElementById('DIV_SELECTED_COUNT').innerHTML = '0 / 20';");
                out.println("                   document.getElementById('hid_selected_checkbox_count').value = '0';");
                
                out.println("                   document.getElementById('HID_CLIENT_CODE').value = '';");
                out.println("                   document.getElementById('TXT_CLIENT_NAME').value = '';");
                out.println("               }");
                out.println("               else if (document.getElementById('hid_help_type').value == '2') {");
                out.println("                   document.getElementById('TXT_ACC_NO').value = '';");
                out.println("                   document.getElementById('HID_BRANCH_CODE').value = '';");
                out.println("                   document.getElementById('TXT_BRANCH_NAME').value = '';");
                out.println("               }");
                out.println("               else if (document.getElementById('hid_help_type').value == '3') {");
                out.println("                   document.getElementById('TXT_GROUP_PAYMENT_CODE').value = '';");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function check_approve(row_id) {");
                out.println("               var check_status = document.getElementById('CHK_APPROVE_' + row_id).checked;");
                out.println("               var checked_count = parseInt(document.getElementById('hid_selected_checkbox_count').value);");
                out.println("               var current_total_amount = parseFloat(unformat_noobject(document.getElementById('DIV_TOTAL_AMOUNT').innerHTML));");
                out.println("               var selected_amount = parseFloat(document.getElementById('TXT_PAID_' + row_id).value);");
                
                out.println("               if (check_status == true) {");
                out.println("                   if (checked_count == 20) {");
                out.println("                       alert('Maximum allowed to approve is 20.');");
                out.println("                       document.getElementById('CHK_APPROVE_' + row_id).checked = false;");
                out.println("                   }");
                out.println("                   else {");
                out.println("                       checked_count += 1;");
                out.println("                       document.getElementById('hid_selected_checkbox_count').value = checked_count;");
                out.println("                       document.getElementById('DIV_SELECTED_COUNT').innerHTML = checked_count + ' / 20';");
                
                out.println("                       current_total_amount += selected_amount;");
                out.println("                       document.getElementById('HID_TOTAL_AMOUNT').value = current_total_amount;");
                out.println("                       document.getElementById('DIV_TOTAL_AMOUNT').innerHTML = format_noobject(current_total_amount);");
                out.println("                   }");
                out.println("               }");
                out.println("               else {");
                out.println("                   checked_count -= 1;");
                out.println("                   document.getElementById('hid_selected_checkbox_count').value = checked_count;");
                out.println("                   document.getElementById('DIV_SELECTED_COUNT').innerHTML = checked_count + ' / 20';");
                
                out.println("                   current_total_amount -= selected_amount;");
                out.println("                   document.getElementById('HID_TOTAL_AMOUNT').value = current_total_amount;");
                out.println("                   document.getElementById('DIV_TOTAL_AMOUNT').innerHTML = format_noobject(current_total_amount);");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_calendar(num, row_id) {");
                out.println("               document.getElementById('hid_cal_date').value = num;");
                out.println("               document.getElementById('hid_cal_row_id').value = row_id;");
                out.println("               popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");");
                out.println("           }");
                
                out.println("           function load_c_date(val) {");
                out.println("               if (document.getElementById('hid_cal_date').value == '1') {");
                out.println("                   var calendar_row_id = document.getElementById('hid_cal_row_id').value;");
                
                out.println("                   v_dd = val.substr(0, val.indexOf('-'));");
                out.println("                   if (v_dd.length < 2) {");
                out.println("                       v_dd = 0 + v_dd;");
                out.println("                   }");
                out.println("                   val = val.substr(val.indexOf('-') + 1, val.length);");
                out.println("                   v_mm = val.substr(0, val.indexOf('-'));");
                out.println("                   if (v_mm.length < 2) {");
                out.println("                       v_mm = 0 + v_mm;");
                out.println("                   }");
                out.println("                   v_yy = val.substr(val.indexOf('-') + 1, val.length);");
                
                out.println("                   document.getElementById('TXT_TAX_INVOICE_DATE_DD_' + calendar_row_id).value = v_dd;");
                out.println("                   document.getElementById('TXT_TAX_INVOICE_DATE_MM_' + calendar_row_id).value = v_mm;");
                out.println("                   document.getElementById('TXT_TAX_INVOICE_DATE_YY_' + calendar_row_id).value = v_yy;");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function save_window() {");
                out.println("               before_submit();");
                out.println("           }");
                
                
                out.println("           function before_submit() {");
                out.println("               if ((validate_data()) && (confirm('Are you sure you want to save?'))) {");
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Req_Main_Screen_2_save';");  
                out.println("                   document.Form1.submit();");
                out.println("               }");
                out.println("           }");
                
                out.println("           function validate_data() {");
                out.println("               if (document.getElementById('HID_CLIENT_CODE').value == '') {");
                out.println("                   alert('Please select a receiver.');");
                out.println("                   help_button_1();");
                out.println("                   return false;");
                out.println("               }");
                out.println("               else if (document.getElementById('TXT_ACC_NO').value == '') {");
                out.println("                   alert('Please select a bank account.');");
                out.println("                   help_button_2();");
                out.println("                   return false;");
                out.println("               }");
                out.println("               else if (document.getElementById('hid_selected_checkbox_count').value == '0') {");
                out.println("                   alert('Please select at leaset one payment.');");
                out.println("                   return false;");
                out.println("               }");
                out.println("               else if (document.getElementById('hid_selected_checkbox_count').value != '0') {");
                out.println("                   var total_count = document.getElementById('hid_count').value;");
                out.println("                   for (var i = 0; i < total_count; i++) {");
                out.println("                       if (document.getElementById('CHK_APPROVE_' + i).checked == true) {");
                out.println("                           if (document.getElementById('TXT_TAX_INVOICE_NO_' + i).value == '') {");
                out.println("                               alert('Please fill the relevant details.');");
                // out.println("                               alert('Please fill the relevant details. ' + ' TXT_TAX_INVOICE_NO_' + i);");
                out.println("                               document.getElementById('TXT_TAX_INVOICE_NO_' + i).focus();");
                out.println("                               return false;");
                out.println("                           }");
                out.println("                           else if (document.getElementById('TXT_TAX_INVOICE_DATE_DD_' + i).value == '') {");
                out.println("                               alert('Please fill the relevant details.');");
                // out.println("                               alert('Please fill the relevant details. ' + ' TXT_TAX_INVOICE_DATE_DD_' + i);");
                out.println("                               document.getElementById('TXT_TAX_INVOICE_DATE_DD_' + i).focus();");
                out.println("                               return false;");
                out.println("                           }");
                out.println("                           else if (document.getElementById('TXT_TAX_INVOICE_DATE_MM_' + i).value == '') {");
                out.println("                               alert('Please fill the relevant details.');");
                // out.println("                               alert('Please fill the relevant details. ' + ' TXT_TAX_INVOICE_DATE_MM_' + i);");
                out.println("                               document.getElementById('TXT_TAX_INVOICE_DATE_MM_' + i).focus();");
                out.println("                               return false;");
                out.println("                           }");
                out.println("                           else if (document.getElementById('TXT_TAX_INVOICE_DATE_YY_' + i).value == '') {");
                out.println("                               alert('Please fill the relevant details.');");
                // out.println("                               alert('Please fill the relevant details. ' + ' TXT_TAX_INVOICE_DATE_YY_' + i);");
                out.println("                               document.getElementById('TXT_TAX_INVOICE_DATE_YY_' + i).focus();");
                out.println("                               return false;");
                out.println("                           }");
                out.println("                       }");
                out.println("                   }");
                // out.println("                   return false;");
                out.println("               }");
                out.println("               return true;");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"SCREEN_NAME\" id=\"SCREEN_NAME\" value=\"NEW\" /> ");
                out.println("           <input type=\"hidden\" name=\"hid_help_type\" id=\"hid_help_type\" value=\"\" />");
                out.println("           <input type=\"hidden\" name=\"hid_status\" id=\"hid_status\" value=\"New\" />");
                out.println("           <input type=\"hidden\" name=\"hid_no\" id=\"hid_no\" value=\"\" />");
                out.println("           <input type=\"hidden\" name=\"hid_save\" id=\"hid_save\" value=\"Save\" />");
                out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" id=\"Hid_scr_name\" value=\"AF_CR_PRO_PAYMENT_REQ_MAIN\" />");
                out.println("           <input type=\"hidden\" name=\"hid_selected_checkbox_count\" id=\"hid_selected_checkbox_count\" value=\"0\" />");
                out.println("           <input type=\"hidden\" name=\"hid_cal_date\" id=\"hid_cal_date\" value=\"0\" />");
                out.println("           <input type=\"hidden\" name=\"hid_cal_row_id\" id=\"hid_cal_row_id\" value=\"0\" />");
                
                out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
                out.println("               <tr>"); 
                // out.println("                   <td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
                out.println("                   <td width='8' valign='top'></td>");
                out.println("                   <td class='border_wht' valign='top'> "); 
                out.println("                       <table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
                out.println("                           <tr> "); 
                out.println("                               <td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
                out.println("                           </tr>"); 
                out.println("                           <tr> "); 
                out.println("                               <td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
                out.println("                           </tr>"); 
                out.println("                           <tr>"); 
                // out.println("                               <td style='height: 327px'>"); 
                out.println("                               <td>");
                out.println("                                   <table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>");
                out.println("                                       <tr>"); 
                out.println("                                           <td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
                out.println("                                       </tr>"); 
                out.println("                                       <tr>"); 
                out.println("                                           <td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Requisition - Processing - New</td>"); 
                out.println("                                       </tr>"); 
                out.println("                                       <tr>"); 
                out.println("                                           <td  height='10px' class='pdn_txtpos'>"); 
                out.println("                                               <table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("                                                   <tr><td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
                //out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");
                // out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onClick='load_screen_status(\"REVERSE\")' name=\"bt_rev\" value=\"Reverse\"></td>");  
                out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window();' name=\"bt_save\" id=\"bt_save\" value=\"Save\"></td>");
                out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Print Voucher\");' onClick='load_screen_status(\"PRINT_VOUCHER\");' name=\"bt_print_voucher\" value=\"Print Voucher\" style=\"width: 100px;\" /></td>");
                // out.println("<td width='10%' align='center'><input name =\"save\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
                out.println("                                                       <td width='100%'></td>");  
                /*if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                */
                if(m_screen_type.equals("REVERSE") ){
                    out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("                                                       <td><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
                // out.println("                                                       <td width='*%' align='right' class='div_input'></td>");  
                out.println("                                                   </tr>");  
                out.println("                                               </table>");  
                out.println("                                           </td>");  
                out.println("                                       </tr>");  
                out.println("                                       <tr>");  
                out.println("                                           <td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
                out.println("                                       </tr>");  
                out.println("                                       <tr>");  
                out.println("                                           <td class='pdn_txtpos' valign='top'>");
                out.println("                                               <br />");
                
                out.println("                                               <div id=\"DIV_TEXTBOX_CRITERIA\">");
                out.println("                                               <table align=\"left\" class=\"table\">");
                out.println("                                                   <tr class=\"tr_input\">");
                out.println("                                                       <td style=\"width: 200px;\"><div class=\"div_input\" id=\"DIV_TXT_CLIENT_NAME\">Client / Receiver Name</div></td>");
                out.println("                                                       <td style=\"width: 300px;\">");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_CLIENT_NAME\" id=\"TXT_CLIENT_NAME\" maxlength=\"200\" style=\"width: 200px;\" onblur=\"help_button_1();\" />"); // onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
                out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_CLIENT_NAME\" id=\"BUT_TXT_CLIENT_NAME\" value=\"...\" onclick=\"help_button_1();\" />");
                out.println("                                                           <input type=\"hidden\" name=\"HID_CLIENT_CODE\" id=\"HID_CLIENT_CODE\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td style=\"width: 200px;\"><div class=\"div_input\" style=\"font-weight: bold;\">Total Amount</div></td>");
                out.println("                                                       <td style=\"width: 100px;\">");
                out.println("                                                           <div class=\"div_input\" id=\"DIV_TOTAL_AMOUNT\" style=\"font-weight: bold; text-align: right;\">0.00</div>");
                out.println("                                                           <input type=\"hidden\" name=\"HID_TOTAL_AMOUNT\" id=\"HID_TOTAL_AMOUNT\" value=\"0\" />");
                out.println("                                                       </td>");
                out.println("                                                   </tr>");
                
                out.println("                                                   <tr class=\"tr_input\">");
                out.println("                                                       <td><div class=\"div_input\" id=\"DIV_TXT_ACC_NO\">Account No.</div></td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_ACC_NO\" id=\"TXT_ACC_NO\" style=\"width: 90px;\" maxlength=\"20\" size=\"10\" onblur=\"help_button_2();\">"); // check_account(this)
                out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_ACC_NO\" id=\"BUT_TXT_ACC_NO\" value=\"...\" onclick=\"help_button_2();\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td><div class=\"div_input\" style=\"font-weight: bold;\">Selected Count</div></td>"); 
                out.println("                                                       <td><div class=\"div_input\" id=\"DIV_SELECTED_COUNT\" style=\"font-weight: bold; text-align: right;\">0 / 20</div></td>"); 
                out.println("                                                   </tr>");
                
                out.println("                                                   <tr class=\"tr_input\">");
                out.println("                                                       <td><div class=\"div_input\" id=\"DIV_TXT_BRANCH_NAME\">Branch Name</div></td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_BRANCH_NAME\" id=\"TXT_BRANCH_NAME\" style=\"width: 250px;\" disabled=\"disabled\" />"); // check_branch(this)
                out.println("                                                           <input type=\"hidden\" name=\"HID_BRANCH_CODE\" id=\"HID_BRANCH_CODE\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td></td>"); 
                out.println("                                                       <td></td>"); 
                out.println("                                                   </tr>");
                
                out.println("                                               </table>");
                out.println("                                               </div>");
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr class='pdn_txtpos' valign='top'>");
                out.println("                                           <td>");
                out.println("                                               <br />");
                
                out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
                // // out.println("                                               <table width='100%' class=\"table\">");
                // out.println("                                               <table class=\"table\">");
                // out.println("                                                   <tr>");  
                // // out.println("                                                       <td width=\"100%\"><div id=\"DIV_PAYMENTS\"></div></td>");
                // out.println("                                                       <td><div id=\"DIV_PAYMENTS\"></div></td>");
                // out.println("                                                   </tr>"); 
                // out.println("                                               </table>"); 
                
                
                /*out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");  
                out.println("<td width='6%'></td>");  
                if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>"); 
                */
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                               </td>");
                out.println("                           </tr>");
                out.println("                       </table>");
                out.println("                   </td>");
                out.println("               </tr>");
                out.println("           </table>");
                out.println("       </form>");
                out.println("   </body>");
                out.println("</html>");
                
            }
            
            else if (m_sql.equals("load_data_client")) {
                
                if ((httpServletRequest.getParameter("sort_column") != null) && (httpServletRequest.getParameter("order_by_type") != null)) {
                    m_sort_column = httpServletRequest.getParameter("sort_column");
                    m_order_by_type = httpServletRequest.getParameter("order_by_type");
                }
                
                if (httpServletRequest.getParameter("m_client_name") == null) {
                    vendor_code = "";
                }
                else {
                    vendor_code = httpServletRequest.getParameter("m_client_name");
                }
                
                sql = " " +
                    "   SELECT NVL(" + m_schema_name + ".AF_CO_GET_APPLICATION_NO(B.FINANCE_NO), '-') APPLICATION_NO, " +
                    "          NVL(B.FINANCE_NO, '-') FINANCE_NO, " +
                    "          A.PAYMENT_NO, " +
                    "          TO_CHAR(A.EFF_VAL_DATE, 'DD-MM-YYYY') EFF_VAL_DATE, " +
                    "          NVL(B.PAYEE_NAME, '-') PAYEE_NAME, " +
                    "          SUM(C.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT, " +
                    "          SUM(C.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT, " +
                    "          SUM(C.BAL_TO_BE_PAID) BAL_TO_BE_PAID, " +
                    // "          SUM(B.PAY_AMOUNT) PAY_AMOUNT, " +
                    "          SUM(A.SETTELED_AMOUNT) PAY_AMOUNT, " + // Sandun On 2009-03-11
                    "          DECODE(B.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash', '-') SETTLE_MODE, " +
                    "          DECODE(A.ENTRY_TYPE, 'V', 'Vendor', '-') ENTRY_TYPE, " +
                    // "          B.CLIENT_CODE, " +
                    // "          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) " +
                    "          B.PROCESS_STATUS " +
                    "   FROM   " + m_schema_name + ".AF_CR_PRO_SET_PAY_BREAKDOWN A, " +
                    "          " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT B, " +
                    "          " + m_schema_name + ".AF_RE_ACC_SUS_PAYMENT C " +
                    "   WHERE  A.PAYMENT_NO = B.PAYMENT_NO " +
                    "   AND    A.SUS_REF_NO = C.SUS_REF_NO " +
                    "   AND    B.PROCESS_STATUS = '" + process_status + "' " + // RE-APP  // Modified By Sandun on 2009-01-19
                    "   AND    A.ENTRY_TYPE = 'V' " +
                    // "   AND    UPPER(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%" + vendor_code + "%') " +
                    // "   AND    UPPER(B.PAYEE_NAME) LIKE UPPER('%" + vendor_code + "%') " +
                    "   AND    C.RECEIVER = '" + vendor_code + "' " +
                    "   AND    B.GROUP_PAYMENT_NO IS NULL " +
                    "   GROUP BY B.FINANCE_NO, " +
                    "            A.PAYMENT_NO, " +
                    "            A.EFF_VAL_DATE, " +
                    "            B.PAYEE_NAME, " +
                    "            B.SETTLE_MODE, " +
                    "            A.ENTRY_TYPE, " +
                    // "            B.CLIENT_CODE, " +
                    "            B.PROCESS_STATUS " +
                    // "            B.CLIENT_CODE " +
                    // "   ORDER BY " + m_sort_column + " " + m_order_by_type + " " +
                    "   ORDER BY A.PAYMENT_NO ASC " +
                    " ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                
                int j = 0;
                //out.println("resultSet.getString(7).substring(0,2)"+resultSet.getString(7).substring(0,2));
                // out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
                out.println("<table align=\"left\" border=\"0\" class=\"table\">");
                
                while (resultSet.next()) {
                    
                    if (j==0) {
                        out.println("<tr class=\"pdn_txtpos2\">");
                        out.println("<td width=\"2%\" align=\"right\">No.</td>");
                        out.println("<td width=\"11%\" align=\"left\" style=\"cursor: pointer;\" title=\"Click here to sort by - Application No.\" onclick=sort_data(\"APPLICATION_NO\")>Application No.</td>"); 
                        out.println("<td width=\"11%\" align=\"left\" style=\"cursor: pointer;\" title=\"Click here to sort by - Finance No. \" onclick=sort_data(\"FINANCE_NO\")>Finance No.</td>"); 
                        out.println("<td width=\"10%\" align=\"left\" style=\"cursor: pointer;\" title=\"Click here to sort by - Payment No. \" onclick=sort_data(\"PAYMENT_NO\")>Payment No.</td>"); 
                        out.println("<td width=\"8%\" align=\"left\" style=\"cursor: pointer;\" title=\"Click here to sort by - Value Date  \" onclick=sort_data(\"VALUE_DATE\")>Value Date</td>"); 
                        out.println("<td width=\"9%\" align=\"left\" style=\"cursor: pointer;\" title=\"Click here to sort by - Vendor  \" onclick=sort_data(\"VENDOR_NAME\")>Receiver</td>"); 
                        out.println("<td width=\"5%\" align=\"left\">Tax Invoice No.</td>");
                        out.println("<td width=\"15%\" align=\"left\">Tax Invoice Date</td>");
                        out.println("<td width=\"9%\" align=\"right\" style=\"cursor: pointer;\" title=\"Click here to sort by - Total Paid Amount  \" onclick=sort_data(\"PAID_AMT\")>Paid Amount</td>"); 
                        out.println("<td width=\"4%\" align=\"center\">Type</td>"); 
                        out.println("<td width=\"3%\" align=\"left\">User</td>");
                        out.println("<td width=\"4%\" align=\"center\">Approved</td>");
                        out.println("</tr>");
                    }
                    
                    if(j>0 && j%2==1){
                        out.println("<tr class=tr_input1 >");
                    }
                    else {
                        out.println("<tr class=tr_input >");
                    }
                    
                    out.println("<td  align=\"right\" style= \"cursor-color:blue\">" + (j + 1) + ".</td>");
                    out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=show_application_detail_drill('"+resultSet.getString(1)+"')><U>"+resultSet.getString(1)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_APP_NO_"+j+" value=\""+resultSet.getString(1)+"\"></td>"); 
                    out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=show_finance_detail_drill('"+resultSet.getString(2)+"')><U>"+resultSet.getString(2)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_FINANCE_NO_"+j+" value=\""+resultSet.getString(2)+"\"></td>"); 
                    out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+resultSet.getString(3)+"')\"><U>"+resultSet.getString(3)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+resultSet.getString(3)+"\"></td>"); 
                    out.println("<td  align=\"left\">"+resultSet.getString(4)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_VALUE_DATE_"+j+" value=\""+resultSet.getString(4)+"\"></td>");
                    out.println("<td  align=\"left\">"+resultSet.getString(5)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_RECEIVER_"+j+" value=\""+resultSet.getString(5)+"\"></td>");
                    out.println("<td  align=\"left\"><input  class=\"txt_input\" type=\"text\" name=\"TXT_TAX_INVOICE_NO_"+j+"\" id=\"TXT_TAX_INVOICE_NO_"+j+"\" /></td>"); 
                    
                    // out.println("<td  align=\"left\"><input  class=\"txt_input\" type=\"text\" name=\"TXT_TAX_INVOICE_DATE_"+j+"\" id=\"TXT_TAX_INVOICE_DATE_"+j+"\" /></td>"); 
                    out.println("<td  align=\"left\">");
                    out.println("   <input class=\"txt_input5\" type=\"text\" name=\"TXT_TAX_INVOICE_DATE_DD_" + j + "\" id=\"TXT_TAX_INVOICE_DATE_DD_" + j + "\" maxlength=\"2\" size=\"2\" />");
                    out.println("   <input class=\"txt_input5\" type=\"text\" name=\"TXT_TAX_INVOICE_DATE_MM_" + j + "\" id=\"TXT_TAX_INVOICE_DATE_MM_" + j + "\" maxlength=\"2\" size=\"2\" />");
                    out.println("   <input class=\"txt_input5\" type=\"text\" name=\"TXT_TAX_INVOICE_DATE_YY_" + j + "\" id=\"TXT_TAX_INVOICE_DATE_YY_" + j + "\" maxlength=\"4\" size=\"4\" onblur=\"checkMonthLength(document.getElementById('TXT_TAX_INVOICE_DATE_DD_" + j + "'), document.getElementById('TXT_TAX_INVOICE_DATE_MM_" + j + "'), document.getElementById('TXT_TAX_INVOICE_DATE_YY_" + j + "'));\" />");
                    // out.println("   <a href style=\"cursor: pointer;\" onclick=\"load_calendar('1', '" + j + "');\">Calendar</a>");
                    out.println("</td>");
                    
                    out.println("<td  align=\"right\" >"+nf.format(resultSet.getDouble(9))+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAID_"+j+" value=\""+resultSet.getString(9)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BAL_TO_BE_PAID_"+j+" value=\""+resultSet.getString(8)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BRANCH_"+j+" value=\"\"></td>"); 
                    out.println("<td  align=\"left\" >"+resultSet.getString(10)+"<input type=\"hidden\" name=TXT_ENT_TYPE_"+j+" value=\""+resultSet.getString(9)+"\"></td>"); 
                    out.println("<td  align=\"left\" >"+m_username+"</td>");  // m_username.toLowerCase() // comment nuwan de silva on 21-01-08
                    if(m_screen_type.equals("NEW")) {
                        out.println("<td align=\"center\" ><input class=\"\" type=\"checkbox\" name=\"CHK_APPROVE_"+j+"\" ID=\"CHK_APPROVE_"+j+"\" value=\"A\" onclick=\"check_approve('" + j + "');\" /></td>");
                        // out.println("<td align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 45px\" name=BUT_VIEW_"+j+" value=\"Approve\" onClick=\"help_button("+j+")\"></td>"); 
                    }
                    else {
                        out.println("<td align=\"center\" ><input type=\"checkbox\" name=CHK_APP_"+j+" value=\"\" onClick=\"check_change("+j+")\"></td>"); 
                    }
                    out.println("</tr>");
                    
                    out.println("<input type=\"hidden\" name=TXT_CURR_CODE_"+j+" value=\"\">"); 
                    out.println("<input type=\"hidden\" name=TXT_ACC_NO_"+j+" value=\"\">"); 
                    out.println("<input type=\"hidden\" name=TXT_TOT_SETTLE_AMT_"+j+" value=\""+resultSet.getDouble(6)+"\">"); 
                    out.println("<input type=\"hidden\" name=TXT_INIT_BAL_AMT_"+j+" value=\""+resultSet.getDouble(7)+"\">"); 
                    //out.println("<input type=\"hidden\" name=TXT_PAID_"+j+" value=\""+resultSet.getDouble(7)+"\">"); 
                    out.println("<input type=\"hidden\" name=HID_PAY_NO_"+j+" value=\""+resultSet.getString(3)+"\">"); //Added BY Sandun on 19-01-2009
                    out.println("<input type=\"hidden\" name=\"HID_STATUS\" value=\""+resultSet.getString(12)+"\" >"); //Added BY Sandun on 19-01-2009
                    
                    j = j + 1;
                    
                }
                statement.close();
                
                out.println("</table>");
                out.println("<input type=\"hidden\" name=\"hid_count\" id=\"hid_count\" value=\""+j+"\" />");
                
                // out.println("<table align='center' width='100%'>"); 
                // out.println("<tr>"); 
                // out.println("<td width='100%' class='note'></td>"); 
                // out.println("</tr>"); 
                // out.println("</table>"); 
                // out.println("<br>");
                
                out.println("<input type=\"hidden\" name=\"hid_m_client_name\" id=\"hid_m_client_name\" value=\"" + vendor_code + "\" />"); // added by nuwan de silva on 17-01-2008
                
            }
            
        }
        catch (Exception ex) {
            try{out.println("Error : "+ex.toString());}catch(Exception e){}
            ex.printStackTrace();
        }
        finally{
            if(resultSet    !=null){try{resultSet.close();   }catch(Exception e){}}
            
            if(statement  !=null){try{statement.close(); }catch(Exception e){}}
            
            if(connection  !=null){try{connection.close(); }catch(Exception e){}}
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
        
    }
    
}
