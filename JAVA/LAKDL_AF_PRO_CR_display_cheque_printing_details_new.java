//--
//SCREEN NAME:Fianace - Cheque Printing/Disburse
//CREATED BY :delanjali
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_PRO_CR_display_cheque_printing_details_new extends javax.servlet.http.HttpServlet { 
    Connection conn;
    ServletOutputStream out = null;
    Statement stmt,stmt1,stmt2;
    public ResultSet rs;
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
            stmt=conn.createStatement();
            
            String fschema_name = m_sn_methods.schema_name;
            String m_screen_name = req.getParameter("screen");
            String m_chksql = req.getParameter("chksql");
            String m_chksql1 = req.getParameter("chksql2");
            String m_head="";
            String m_head1="";
            
            nf = java.text.NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            
            if (m_chksql1.equals("PRINT")){
                m_head="Printing";
                m_head1="Printing";
                
            }
            else if (m_chksql1.equals("CANCEL")){
                m_head="Cancel";
                m_head1="Cancellation";
                
            }
            
            
            String m_sort_column   = "APPLICATION_NO";	
            String m_order_by_type = "ASC";
            
            if(m_screen_name.equals("main_page")){
                
                
                out.println("<HTML>"); 
                out.println("<HEAD>"); 
                out.println("<TITLE>Finance - Cheque "+m_head1+"   - New</TITLE>"); 
                out.println("</HEAD>"); 
                out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
                out.println("<SCRIPT language=\"JavaScript\">"); 
                out.println("var st_val='"+m_chksql+"'");
                out.println("var st_val1='"+m_chksql1+"'");
                out.println("var chk_chng=0");
                out.println("var m_order_by_type='DESC'");
                out.println("var chque_status=0");
                
                
                
                out.println("function get_vector(data_vec) {");
                out.println("	 if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.TXT_ACC_NO.value!=''){");
                out.println("document.Form1.hid_st.value=''");
                out.println("help_button_5('0','10','0','m_help_TXT_ACCOUNT_new_sql','5')");
                out.println("			}");
                out.println("	else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.TXT_ACC_NO.value!=''){");
                out.println("document.Form1.hid_st.value=''");
                out.println("request_details.innerHTML=\"\";");
                out.println("help_button_5('0','10','0','m_help_TXT_ACCOUNT_new_sql','5')");
                out.println("			}");
                out.println("}");
                
                
                out.println("function get_vector_normal(http_response) {");
                out.println(" request_details.innerHTML = ''; ");
                out.println(" request_details.innerHTML = http_response; ");
                out.println(" if(document.Form1.hid_count.value==0){");
                out.println("alert('No records')");
                out.println("document.Form1.TXT_ACC_NO.value=\"\"");
                out.println("document.Form1.TXT_BRANCH_CODE.value=\"\""); 
                out.println("document.Form1.TXT_BRANCH_NAME.value=\"\"");
                out.println("}");
                out.println("}");
                
                out.println("function makeRequest() {");
                out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=request_details&chksql="+m_chksql+"&chksql2="+m_chksql1+"&account='+document.Form1.TXT_ACC_NO.value+'&order_type="+m_sort_column+"&type="+m_order_by_type+"';");
                out.println("load_interface(m_url,'NORM');");
                out.println("}");
                
                out.println("function view() {"); 
                out.println("if(document.Form1.TXT_ACC_NO.value!=\"\"){");
                out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Cheque_details?chksql=m_cheque&data_val='+document.Form1.TXT_ACC_NO.value+'';");
                out.println("popupwin=window.open(m_url,'displayWindow1','left=200,top=210,width=550,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
                out.println("}");
                out.println("else if(document.Form1.TXT_ACC_NO.value==\"\"){");
                out.println("alert('Please enter Account No to view details')");
                out.println("}");
                out.println("}");
                
                /*
                            out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
                out.println("  chk=\"CHK_APP_\"+d;");
                out.println("chk_cheque=\"TXT_CHEQUE_NO_\"+d;");//
                out.println("if(document.Form1.elements[chk].checked==false){"); 
                out.println("chk_chng=0");
                out.println("} ");
                out.println("else if (document.Form1.elements[chk].checked==true ){");			
                out.println("chk_chng=1");
                out.println("if(document.Form1.elements[chk_cheque].value==\"\"){");//
                out.println("chque_status=2;}");//
                out.println("else{");	//
                out.println("chque_status=1;}");	//
                out.println("break");		
                out.println("		}");		
                out.println("		}");
                */
                
                
                out.println("function before_submit(){ "); 
                out.println("chk_chequeno();");
                out.println("		if(chk_chng==1){"); 
                out.println("if(chque_status==1){");//
                out.println();
                out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
                out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
                out.println("document.Form1.elements[i].disabled=false;");
                out.println("}");
                out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_cheque_printing_details_new?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");   
                out.println("		document.Form1.submit();	"); 
                out.println("		}"); 
                out.println("}");
                out.println("else{ ");// 
                out.println("alert('Enter Cheque No. before save..!');");//
                out.println("} "); //
                out.println("} "); 
                out.println("else{");
                if(m_head.equals("Printing")){
                    out.println("alert('Please select a payment ')");
                }else
                    if(m_head.equals("Cancel")){
                        out.println("alert('Please select a payment to cancel')");
                    }
                
                out.println("}");
                
                
                out.println("} "); 
                
                out.println("function chk_chequeno(){");			
                out.println("chque_status=0;");
                out.println("chk_chng=1;");			
                out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
                out.println("chk=\"TXT_CHEQUE_NO_\"+d;");
                out.println("chk_bx=\"CHK_APP_\"+d;");
                out.println("if(document.Form1.elements[chk_bx].checked==true){");	
                out.println("if(document.Form1.elements[chk].value==\"\"){");
                //out.println("alert('Enter Cheque No. before save');");
                out.println("chque_status=0;");//
                out.println("break;");//
                out.println("}");
                out.println("else {  ");
                out.println("chque_status=1;");//
                out.println("}");
                out.println("}");			
                out.println("}");
                out.println("}");
                
                
                
                out.println("function clear_window(){	"); 
                out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
                out.println("		}"); 
                out.println("}"); 
                
                out.println("function display_voucher(){");
                
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Payment_Voucher_View\";");
                out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
                out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
                
                out.println("}");
                
                
                
                out.println("function new_window(){	"); 
                out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
                out.println("}"); 
                out.println(""); 
                out.println(""); 
                
                out.println("function save_window(){	"); 
                out.println("before_submit();"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function sort_data(m_sort_col,val) {");
                out.println(" if(val=='ASC'){");
                out.println(" m_order_by_type='DESC'");
                out.println(" }");
                out.println("else{");
                out.println(" m_order_by_type='ASC'");
                out.println(" }");
                out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_new?screen=request_details&chksql="+m_chksql+"&chksql2="+m_chksql1+"&account='+document.Form1.TXT_ACC_NO.value+'&order_type='+m_sort_col+'&type='+m_order_by_type+'';");
                out.println("load_interface(m_url,'NORM');");
                out.println("}");
                
                
                out.println("function load_help_msg() {"); 
                out.println("if(st_val1=='PRINT'){");
                out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque\";"); 
                out.println("}");
                out.println("else if(st_val1=='CANCEL'){");
                out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_can\";"); 
                out.println("}");
                out.println("else if(st_val1=='DISBURSE'){");
                out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_dis\";"); 
                out.println("}");
                out.println("    HelpBox_msg(m_help_message);"); 
                out.println("}"); 	
                
                out.println("function HelpBox_msg(m_help_message) {"); 
                out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
                out.println("  \"&help_message_in=\"+m_help_message);"); 
                out.println("}"); 			
                
                
                out.println("function load_roll_value(m_val){"); 
                out.println("help_box.innerHTML=\" Finance - Cheque "+m_head1+"   - \"+m_val;"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function load_roll_out_value(){");
                out.println("help_box.innerHTML=\" Finance - Cheque "+m_head1+"  - \"+document.Form1.hid_status.value;"); 
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
                
                /*out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
                out.println("oBj = new MyDialog();");
                out.println("oBj.valout[3]  = \" \";");
                out.println("oBj.valout[4]  = \" \";");
                out.println("oBj.valout[5]  = \" \";");
                out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                out.println("	"); 
                out.println("if(oBj.valout[0]=='Next')  {");
                out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
                out.println("}");
                out.println("else if  (oBj.valout[0]=='Prev') {");
                out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
                out.println("}		");
                out.println("else if(oBj.valout[1] == 'Close'){");
                out.println("clear_data()");
                out.println("}");
                out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
                out.println("if(IfCount=='1'){"); 
                out.println("		help_value_assign_1(oBj);"); 
                out.println("}");
                out.println("else if(IfCount=='2'){"); 
                out.println("		help_value_assign_2(oBj);"); 
                out.println("}");
                out.println("else if(IfCount=='3'){"); 
                out.println("		help_value_assign_3(oBj);"); 
                out.println("}");
                out.println("else if(IfCount=='4'){"); 
                out.println("		help_value_assign_4(oBj);"); 
                out.println("}");
                out.println("else if(IfCount=='5'){"); 
                out.println("		help_value_assign_5(oBj);"); 
                out.println("}");
                out.println("else if(IfCount=='99'){"); 
                out.println("		help_update_value_assign_99(oBj);"); 
                out.println("}");
                out.println("}");
                out.println("else if(oBj.valout[4] != \" \"){ ");
                out.println("Crit = oBj.valout[4];");
                out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
                out.println("}	");
                out.println("if(oBj.valout[4]==' '){ ");
                out.println("clear_data()");
                out.println("}	");
                out.println("}");	
            */
                
                
                out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
                out.println("    oBj = new MyDialog();"); 
                out.println("    oBj.valout[1]  = \" \";"); 
                out.println("    oBj.valout[2]  = \" \";"); 
                out.println("    oBj.valout[3]  = \" \";"); 
                out.println("	"); 
                out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                // out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                
                out.println("	if(oBj.valout[1] ==\" \"){"); 
                out.println("	clear_data(document.Form1.hid_help_type.value);");
                out.println("	}else");
                out.println("	"); 
                out.println("	if(oBj.valout[1] !=\" \"){"); 
                out.println("	if(oBj.valout[1] !=\"Close\"){"); 
                out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
                out.println("	if(oBj.valout[1]!=\"Next\"){"); 
                
                out.println("if(document.Form1.hid_help_type.value=='5'){"); 
                out.println("		help_value_assign_5(oBj);"); 
                out.println("}");
                
                out.println("	}"); //end next
                
                out.println("	else{"); 
                out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
                out.println("		return false;"); 
                out.println("	} "); 
                
                out.println("	}"); //end prev
                out.println("	else{	"); 
                out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
                out.println("	}	"); 
                out.println("	}		"); ///close
                out.println("	else{");
                out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
                out.println("	}");
                
                out.println("	}	"); //
                out.println("}"); 
                out.println(""); 
                
                out.println("function Prev(Start,End,Hid_No){"); 
                out.println("    HelpBox(Start,End,Hid_No);"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function Next (Start,End,Hid_No){"); 
                out.println("    HelpBox(Start,End,Hid_No);"); 
                out.println("}"); 
                out.println(""); 
                
                out.println("function clear_data(){");
                out.println("if(document.Form1.hid_help_type.value==\"5\"){"); 
                out.println("    document.Form1.TXT_ACC_NO.value='';"); 
                out.println("    document.Form1.TXT_ACC_NO.focus();");
                out.println("    document.Form1.TXT_BRANCH_CODE.value='';");  
                out.println("    document.Form1.TXT_BRANCH_NAME.value='';");
                out.println("request_details.innerHTML=\"\";");
                out.println("}");
                out.println("}");		
                
                /*out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
                out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
                out.println("}");		
                    
                out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
                out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
                out.println("}");
                    
                out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
                out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
                out.println("}");
                */
                out.println("function help_update_value_assign_99() {"); 
                out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 
                out.println("    document.Form1.TXT_TOTAL_NET.value=oBj.valout[4];"); 
                out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[5];"); 
                out.println("    document.Form1.TXT_NAME.value=oBj.valout[6];"); 
                out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[7];"); 
                out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[8];"); 
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
                
                out.println("function check_account(obj) {");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_cheque_printing_details_new&data_val=\"+document.Form1.TXT_ACC_NO.value+\"&ac_status=\"+st_val;");
                out.println("load_interface(m_url,'XML');");
                out.println("}");
                
                out.println("function help_button_5(Start,End,Hid_No,Sql,IfCount) {"); 
                out.println("    document.Form1.hid_help_type.value=\"5\";"); 
                out.println("    Crit = document.Form1.TXT_ACC_NO.value+\"@\"+st_val+\"@Y@\";");
                //out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
                
                out.println("    m_sql = Sql;"); 
                out.println("    m_criteria = Crit"); 
                out.println("    HelpBox('1','10','0');"); 
                
                out.println("}"); 
                out.println(""); 
                
                out.println("function help_value_assign_5(oBj) {"); 
                out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
                out.println("    document.Form1.TXT_BRANCH_CODE.value=oBj.valout[3];");
                out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];");
                out.println("makeRequest()");
                out.println("}"); 
                
                //-------------------------------------------------------------------------------------------------------
                
                out.println("</script>"); 
                out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
                out.println("<FORM NAME='Form1' method='post'>"); 
                out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
                out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
                out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"0\">");
                
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
                out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Cheque "+m_head1+" </td>"); 
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
                out.println("<td width='10%' align='center'><input type=button name=voucher value=\"Voucher\" class=mainbut onclick=display_voucher(); onMouseOver='load_roll_value(\"Voucher\");' onmouseout='load_roll_value(\"Voucher\");'></td>");
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>");  
                out.println("</td></tr><tr>");  
                out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
                out.println("</tr><tr>");  
                out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
                
                out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
                out.println("<tr>");
                out.println("</r>");
                out.println("<tr>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("</r>");
                out.println("<tr>");
                out.println("</tr>");
                out.println("<tr >"); 
                out.println("<td width='30%' ><DIV id='DIV_TXT_ACC_NO'  class=div_input>Account No </DIV></td>"); 
                out.println("<td width='27%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='20' size='20' onblur=\"check_account(document.Form1.TXT_ACC_NO)\">"); 
                out.println("<input class='but_input' type='button' name='BUT_TXT_BRANCH_CODE' value=\"...\" onClick=\"help_button_5('0','10','0','m_help_TXT_ACCOUNT_new_sql','5')\">"); 
                out.println("<input class='but_input' type='button' name='BUT_TXT_ACC_DETAILS' value=\"Details\" onClick=\"view()\"></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                out.println("<tr>"); 
                out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_CODE'  class=div_input>Branch Code </DIV></td>"); 
                out.println("<td width='27%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='10' disabled></td>"); 
                out.println("<td width='10%'></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                //------ Added by Chandana on 22/05/2007 for Ref No.45 ------//
                out.println("<tr>"); 
                out.println("<td width='30%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input>Branch Name </DIV></td>"); 
                out.println("<td width='27%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' maxlength='100' size='100' style=\"width:200px;\"  disabled></td>"); 
                out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View All\" onClick=\"makeRequest()\"></td>"); 
                out.println("<td width='*%'></td>"); 
                out.println("</tr>"); 
                // ----- End Ref No.45 ------------------//
                out.println("</table>"); 
                out.println("<br>"); 
                
                out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
                out.println("<td ><div id=request_details></div></td></tr></table>");
                
                out.println("<table align='center' width='100%'>"); 
                out.println("<tr>"); 
                out.println("<td width='100%' class='note'></td>"); 
                out.println("</tr>"); 
                out.println("</table>"); 
                
                out.println("<br>");
                out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
                out.println("<td width='100%'><div id=change1></div></td></tr></table>");
                out.println("<br>"); 
                
                out.println("</form>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
                out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
                
                out.println("</body>"); 
                out.println("</html>"); 
                out.flush();
            }
            //---------------------------------------------------------------------------------------------------------------------------------------
            else if(m_screen_name.equals("request_details")){
                int j=0;
                
                String m_account=req.getParameter("account");
                m_sort_column = req.getParameter("order_type");	
                m_order_by_type= req.getParameter("type");	
                
                if(m_head.equals("Printing")){
                    
                    /*rs= stmt.executeQuery
                                                        ("SELECT C.APPLICATION_NO APPLICATION_NO, "+
                                                        "A.PAYMENT_NO PAYMENT_NO, "+
                                                        "A.SUS_REF_NO SUS_REF_NO, "+
                                                        "C.CLIENT_CODE CLIENT_CODE, "+
                                                        ""+fschema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
                                                        "B.VENDOR_CODE VENDOR_CODE, "+
                                                        ""+fschema_name+".af_co_get_vendor_name(B.VENDOR_CODE) VENDOR_NAME, "+
                                                        "A.SETTLE_MODE SETTLE_MODE, "+
                                                        "A.PAY_AMOUNT PAY_AMOUNT, "+
                                                        "TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+
                                                        "A.LIC_ACC_NO LIC_ACC_NO,D.REF_NO REF_NO "+
                                                        "FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
                                                        ""+fschema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
                                                        ""+fschema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
                                                        ""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D "+
                                                        "WHERE A.SUS_REF_NO=D.SUS_REF_NO  "+
                                                        "AND D.REF_NO=B.INVOICE_NO "+
                                                        "AND B.APPLICATION_NO=C.APPLICATION_NO "+
                                                //		"AND C.APPLICATION_STATUS='ACTIVATED' "+
                                                        "AND UPPER(A.LIC_ACC_NO) like UPPER('"+m_account+"%') "+
                                                        "AND A.PROCESS_STATUS='"+m_chksql+"' "+
                                                        
                                                        "UNION "+
                                                        
                                                        "SELECT DD.APP APPLICATION_NO, "+
                                                        "DD.PAYMENT_NO PAYMENT_NO, "+
                                                        "DD.SUS_REF_NO SUS_REF_NO, "+
                            ""+fschema_name+".af_co_get_client_CODE(DD.APP) CLIENT_CODE, "+  
                                                        ""+fschema_name+".AF_CO_GET_APP_NAME(DD.APP) NAME, "+ 
                                                        "'', "+
                            "DD.receiver VENDOR_NAME,  "+
                                                        "DD.SETTLE_MODE SETTLE_MODE,  "+
                                                        "DD.PAY_AMOUNT PAY_AMOUNT,  "+
                                                        "DD.EFF_DATE EFF_DATE,  "+
                                                        "DD.LIC_ACC_NO LIC_ACC_NO, "+
                                                        "DD.REF_NO REF_NO "+
                                                        "FROM (SELECT DISTINCT "+fschema_name+".AF_CO_GET_FIN_NO(D.REF_NO) APP, "+
                                                        "A.PAYMENT_NO PAYMENT_NO, "+
                                                        "A.SUS_REF_NO SUS_REF_NO, "+
                                                        "D.receiver receiver,  "+
                                                        "A.SETTLE_MODE SETTLE_MODE,  "+
                                                        "A.PAY_AMOUNT PAY_AMOUNT,  "+
                                                        "TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+ 
                                                        "A.LIC_ACC_NO LIC_ACC_NO ,D.REF_NO REF_NO "+
                                                        "FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,  "+
                                                        ""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D  "+
                                                        "WHERE A.SUS_REF_NO=D.SUS_REF_NO   "+
                                                        "AND UPPER(A.LIC_ACC_NO) like UPPER('"+m_account+"%')   "+
                                                        "AND A.PROCESS_STATUS='"+m_chksql+"'  "+
                            "AND D.SUSPENSE_ENTRY_TYPE in ('E','L','A'))DD  "+
                                                        "ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
                                                        
            */
                    
                    rs = stmt.executeQuery(" " +
                        "   SELECT A.PAYMENT_NO PAYMENT_NO, " +
                        "          A.SUS_REF_NO SUS_REF_NO, " +
                        "          DECODE(A.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash') SETTLE_MODE, " +
                        "          A.PAY_AMOUNT PAY_AMOUNT, " +
                        "          TO_CHAR(A.EFF_VALDATE, 'DD-MM-YYYY') EFF_DATE, " +
                        "          A.LIC_ACC_NO LIC_ACC_NO, " +
                        "          NVL(A.PAYEE_NAME, '-'), " +
                        "          NVL(A.CHEQUE_NO, '-'), " +
                        "          NVL(A.FINANCE_NO, '-'), " +
                        "          NVL(A.WHT, 0), " +
                        "          NVL(A.NET_AMOUNT, 0), " +
                        "          ( " +
                        "              SELECT Z.FULL_NAME " +
                        "              FROM   " + fschema_name + ".AF_CO_MAS_CLIENT Z " +
                        "              WHERE  Z.CLIENT_CODE = A.CLIENT_CODE " +
                        "          ) CLIENT_NAME, " +
                        "          C.INVOICE_NO, " +
                        "          NVL(C.ENGINE_NO, '-') ENGINE_NO " +
                        "   FROM   " + fschema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A, " +
                        "          " + fschema_name + ".AF_CO_PRO_APPLICATION_DETAILS B, " +
                        "          " + fschema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS C " +
                        "   WHERE  A.FINANCE_NO = B.FINANCE_NO " +
                        "   AND    B.APPLICATION_NO = C.APPLICATION_NO " +
                        "   AND    UPPER(A.LIC_ACC_NO) LIKE UPPER('" + m_account + "%') " +
                        "   AND    A.PROCESS_STATUS = '" + m_chksql + "' " +
                        " ");
                        // (" SELECT A.PAYMENT_NO PAYMENT_NO "+//1
                        // "  , A.SUS_REF_NO SUS_REF_NO "+//2
                        // "  , DECODE(A.SETTLE_MODE,'CHQ','Cheque','Cash') SETTLE_MODE "+ //3
                        // "  , A.PAY_AMOUNT PAY_AMOUNT "+ //4
                        // //	", A.net_amount "+
                        // "  , TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE "+ //5
                        // "  , A.LIC_ACC_NO LIC_ACC_NO "+ //6
                        // "  , NVL(PAYEE_NAME,'-'), "+ //7
                        // "  NVL(A.CHEQUE_NO,'-'), "+//8  //Added By sandun on 19-09-2008
                        // "  NVL(A.FINANCE_NO,'-'), "+//9  //Added By Sandun on 16-12-2008
                        // "  NVL(A.WHT,0), "+//10
                        // "  NVL(A.NET_AMOUNT,0) "+//11
                        // " FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
                        // " WHERE UPPER(A.LIC_ACC_NO) like UPPER('"+m_account+"%') "+
                        // " AND A.PROCESS_STATUS='"+m_chksql+"' ");
                    // //"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
                    
                }
                
                
                if(m_head.equals("Cancel")){
                    
                    /*rs= stmt.executeQuery("SELECT C.APPLICATION_NO APPLICATION_NO, "+
                                                        "A.PAYMENT_NO PAYMENT_NO, "+
                                                        "A.SUS_REF_NO SUS_REF_NO, "+
                                                        "C.CLIENT_CODE CLIENT_CODE, "+
                                                        ""+fschema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
                                                        "B.VENDOR_CODE VENDOR_CODE, "+
                                                        ""+fschema_name+".af_co_get_vendor_name(B.VENDOR_CODE) VENDOR_NAME, "+
                                                        "A.SETTLE_MODE SETTLE_MODE, "+
                                                        "A.PAY_AMOUNT PAY_AMOUNT, "+
                                                        "TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+
                                                        "TO_CHAR(A.MOD_DATE,'dd-mm-yyyy') PRINT_DATE, "+
                                                        "A.LIC_ACC_NO LIC_ACC_NO,d.ref_no ref_no "+
                                                        "FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
                                                        ""+fschema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
                                                        ""+fschema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
                                                        ""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D "+
                                                        "WHERE A.SUS_REF_NO=D.SUS_REF_NO "+
                                                        "AND D.REF_NO=B.INVOICE_NO "+
                                                        "AND B.APPLICATION_NO=C.APPLICATION_NO "+
                                                    //	"AND C.APPLICATION_STATUS='ACTIVATED' "+
                                                        "AND UPPER(A.LIC_ACC_NO) LIKE UPPER('"+m_account+"%') "+
                                                        "AND A.PROCESS_STATUS='"+m_chksql+"' "+
                                                        "AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') > 7 "+
                                                        
                                                        
                                                        "UNION "+
                                                        
                                                        "SELECT DD.APP APPLICATION_NO, "+
                                                        "DD.PAYMENT_NO PAYMENT_NO, "+
                                                        "DD.SUS_REF_NO SUS_REF_NO, "+
                            ""+fschema_name+".af_co_get_client_CODE(DD.APP) CLIENT_CODE, "+  
                                                        ""+fschema_name+".AF_CO_GET_APP_NAME(DD.APP) NAME, "+ 
                                                        "'', "+
                            "DD.receiver VENDOR_NAME,  "+
                                                        "DD.SETTLE_MODE SETTLE_MODE,  "+
                                                        "DD.PAY_AMOUNT PAY_AMOUNT,  "+
                                                        "DD.EFF_DATE EFF_DATE,  "+
                                                        "DD.PRINT_DATE PRINT_DATE, "+
                                                        "DD.LIC_ACC_NO LIC_ACC_NO, "+
                                                        "DD.REF_NO REF_NO "+
                                                        "FROM (SELECT DISTINCT "+fschema_name+".AF_CO_GET_FIN_NO(D.REF_NO) APP, "+
                                                        "A.PAYMENT_NO PAYMENT_NO, "+
                                                        "A.SUS_REF_NO SUS_REF_NO, "+
                                                        "D.receiver receiver,  "+
                                                        "A.SETTLE_MODE SETTLE_MODE,  "+
                                                        "A.PAY_AMOUNT PAY_AMOUNT,  "+
                                                        "TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE, "+ 
                                                        "TO_CHAR(A.MOD_DATE,'dd-mm-yyyy') PRINT_DATE, "+
                                                        "A.LIC_ACC_NO LIC_ACC_NO ,D.REF_NO REF_NO "+
                                                        "FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,  "+
                                                        ""+fschema_name+".AF_RE_ACC_SUS_PAYMENT D  "+
                                                        "WHERE A.SUS_REF_NO=D.SUS_REF_NO   "+
                                                        "AND UPPER(A.LIC_ACC_NO) like UPPER('"+m_account+"%')   "+
                                                        "AND A.PROCESS_STATUS='"+m_chksql+"'  "+
                                                        "AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') > 7 "+
                            "AND D.SUSPENSE_ENTRY_TYPE in ('E','L','A'))DD  "+
                                                        "ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
                */
                    
                    
                    rs= stmt.executeQuery
                        (" SELECT A.PAYMENT_NO PAYMENT_NO "+//1
                        "  , A.SUS_REF_NO SUS_REF_NO "+//2
                        "  , DECODE(A.SETTLE_MODE,'CHQ','Cheque','Cash') SETTLE_MODE "+ //3
                        "  , A.PAY_AMOUNT PAY_AMOUNT "+ //4
                        //", A.net_amount "+
                        "  , TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_DATE "+ //5
                        "  , A.LIC_ACC_NO LIC_ACC_NO "+ //6
                        "  , NVL(PAYEE_NAME,'-') "+ //7
                        "  ,TO_CHAR(A.MOD_DATE,'DD-MM-YYYY') PRINT_DATE, "+ //8
                        "  NVL(A.CHEQUE_NO,'-'), "+//9  //Added By sandun on 19-09-2008
                        "  NVL(A.FINANCE_NO,'-'), "+//10  //Added By Sandun on 16-12-2008
                        " NVL(A.WHT,0),"+//11
                        " NVL(A.NET_AMOUNT,0) "+//12
                        " FROM "+fschema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
                        " WHERE UPPER(A.LIC_ACC_NO) like UPPER('"+m_account+"%') "+
                        " AND A.PROCESS_STATUS='"+m_chksql+"' "+
                        "AND TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(A.MOD_DATE,'DD-MM-YYYY') > 7 ");
                    
                    
                    
                }
                
                
                
                /*out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
                out.println("<br>");		
                
                while(rs.next()){
                if(j==0){
            
                out.println("<tr class=pdn_txtpos2>");
                if(m_account.equals("")){
                out.println("<td width=\"12%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO','"+m_order_by_type+"')>Application No</td>"); 
                out.println("<td width=\"10%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Account No  '    onclick=sort_data('LIC_ACC_NO','"+m_order_by_type+"')>Acc No</td>"); 
                }
                else{
                out.println("<td width=\"12%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO','"+m_order_by_type+"')>Application No</td>"); 
                }
                
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Payment No  '    onclick=sort_data('PAYMENT_NO','"+m_order_by_type+"')>Payment No</td>"); 
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Sus Ref No  '    onclick=sort_data('SUS_REF_NO','"+m_order_by_type+"')>Sus Ref No</td>"); 
                
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Ref No  '    onclick=sort_data('REF_NO','"+m_order_by_type+"')>Ref No</td>"); 
                
                out.println("<td width=\"12%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Client Code  '    onclick=sort_data('NAME','"+m_order_by_type+"')>Client</td>"); 
                out.println("<td width=\"9%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Vendor Code  '    onclick=sort_data('VENDOR_NAME','"+m_order_by_type+"')>Receiver</td>"); 
                
                out.println("<td width=\"9%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Effective Value Date  '    onclick=sort_data('EFF_DATE','"+m_order_by_type+"')>Eff Date</td>"); 
                out.println("<td width=\"6%\" align=\"right\" style= cursor:hand; title='Click here to sort by - Payment Amount  '    onclick=sort_data('PAY_AMOUNT','"+m_order_by_type+"')>Paid Amount</td>"); 
                if(m_head.equals("Cancel")){
                out.println("<td width=\"10%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Printed Date  '    onclick=sort_data('PRINT_DATE','"+m_order_by_type+"')>Print Date</td>"); 
                }
                
                out.println("<td width=\"4%\" align=\"center\" >Approve</td>"); 
                out.println("</tr>");
                
                }
                
                            
                if(j>0 && j%2==1){
            out.println("<tr class=tr_input1 >");
                }
                else{
                
                out.println("<tr class=tr_input >");
                }
                if(m_account.equals("")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input  type=\"hidden\" name=TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
                if(m_head.equals("Printing")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs.getString(11)+"')\"><U>"+rs.getString(11)+"</td>"); 
                }
                if(m_head.equals("Cancel")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_licensee_settle_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"</td>"); 
                }
        
                }
        
                else{
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input  type=\"hidden\" name=TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
                }
        
                
                out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(2)+"\" ></td>"); 
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"</td>"); 
                
                if(m_head.equals("Printing")){
                
                if(rs.getString(12).substring(0,2).equals("PI")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"</td>"); 
                }
                if(rs.getString(12).substring(0,2).equals("AD")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_advertistment_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"</td>"); 
                }
                
                
                if(rs.getString(12).substring(0,2).equals("LN")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_legal_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"</td>"); 
                }
                
                if(rs.getString(12).substring(0,2).equals("RP")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_repossession_drill('"+rs.getString(12)+"')\"><U>"+rs.getString(12)+"</td>"); 
                }
    }
    
                if(m_head.equals("Cancel")){
                
                if(rs.getString(13).substring(0,2).equals("PI")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(13)+"')\"><U>"+rs.getString(13)+"</td>"); 
                }
                if(rs.getString(13).substring(0,2).equals("AD")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_advertistment_drill('"+rs.getString(13)+"')\"><U>"+rs.getString(13)+"</td>"); 
                }
                
                
                if(rs.getString(13).substring(0,2).equals("LN")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_legal_drill('"+rs.getString(13)+"')\"><U>"+rs.getString(13)+"</td>"); 
                }
                
                if(rs.getString(13).substring(0,2).equals("RP")){
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_repossession_drill('"+rs.getString(13)+"')\"><U>"+rs.getString(13)+"</td>"); 
                }
                }
                
                
                
                out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(4)+"')\"><U>"+rs.getString(5)+"</td>"); 
            
                if(m_head.equals("Printing")){
        
                if(rs.getString(12).substring(0,2).equals("PI")){
                out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs.getString(6)+"')\"><U>"+rs.getString(7)+"</td>"); 
                }
                if(!rs.getString(12).substring(0,2).equals("PI")){
                out.println("<td align=\"left\" >"+rs.getString(7)+"</td>"); 
                }
                }
                
                if(m_head.equals("Cancel")){
        
                if(rs.getString(13).substring(0,2).equals("PI")){
                out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs.getString(6)+"')\"><U>"+rs.getString(7)+"</td>"); 
                }
                if(!rs.getString(13).substring(0,2).equals("PI")){
                out.println("<td align=\"left\" >"+rs.getString(7)+"</td>"); 
                }
                }
            
            
                out.println("<td align=\"left\">"+rs.getString(10)+"</td>"); 
                out.println("<td align=\"right\">"+nf.format(rs.getDouble(9))+"</td>"); 
                if(m_head.equals("Cancel")){
                out.println("<td align=\"left\">"+rs.getString(11)+"</td>"); 
                    
                }
                out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\" ></td>");
                out.println("</tr>");		
                j=j+1;
                }
                out.println("<input type=hidden name=hid_count value="+j+">");
                out.println("</table>");
                */
                
                
                
                //_____________________ added by nuwan de silva on 24-01-2008 ______________________
                out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
                out.println("<br>");		
                out.println("<tr class=pdn_txtpos2>");
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Payment No  '    onclick=sort_data('PAYMENT_NO','"+m_order_by_type+"')>Payment No</td>"); 
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('FINANCE_NO','"+m_order_by_type+"')>Finance No</td>"); //Added By sandun on 16-12-2008
                /* Added By Samitha Kulatilaka On 2011-06-21 */
                if (m_head.equals("Printing")) {
                    out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Customer Name  '    onclick=sort_data('CLIENT_NAME','"+m_order_by_type+"')>Customer Name</td>");
                    out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Engine No  '    onclick=sort_data('ENGINE_NO','"+m_order_by_type+"')>Engine No</td>");
                    out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Invoice No  '    onclick=sort_data('INVOICE_NO','"+m_order_by_type+"')>Invoice No</td>");
                }
                out.println("<td width=\"11%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Settlement Mode  '    onclick=sort_data('PAYMENT_NO','"+m_order_by_type+"')>Settlement Mode</td>"); 
                out.println("<td width=\"9%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Vendor Code  '    onclick=sort_data('VENDOR_NAME','"+m_order_by_type+"')>Receiver</td>"); 
                out.println("<td width=\"7%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Effective Value Date  '    onclick=sort_data('EFF_DATE','"+m_order_by_type+"')>Eff Date</td>"); 
                out.println("<td width=\"8%\" align=\"right\" style= cursor:hand; title='Click here to sort by - Cheque No  '    onclick=sort_data('CHEQUE_NO','"+m_order_by_type+"') >Cheque No.</td>");//Added By sandun on 19-09-2008
                out.println("<td width=\"6%\" align=\"right\" style= cursor:hand; title='Click here to sort by - Payment Amount  '    onclick=sort_data('WHT','"+m_order_by_type+"')>WHT</td>"); //Sandun on 24-12-2008
                out.println("<td width=\"6%\" align=\"right\" style= cursor:hand; title='Click here to sort by - Payment Amount  '    onclick=sort_data('NET_AMOUNT','"+m_order_by_type+"')>Amount</td>"); //Sandun on 24-12-2008
                out.println("<td width=\"6%\" align=\"right\" style= cursor:hand; title='Click here to sort by - Payment Amount  '    onclick=sort_data('PAY_AMOUNT','"+m_order_by_type+"')>Paid Amount</td>"); 
                out.println("<td width=\"4%\" align=\"center\" >Approve</td>"); 
                out.println("</tr>");
                
                while(rs.next()){
                    
                    if(j>0 && j%2==1){
                        out.println("<tr class=tr_input1 >");
                    }
                    else{
                        out.println("<tr class=tr_input >");
                    }
                    out.println("<td align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
                    out.println("<td align=\"left\">"+rs.getString(9)+"</td>");//Added By Sandun on 16-12-2008  
                    /* Added By Samitha Kulatilaka On 2011-06-21 */
                    if (m_head.equals("Printing")) {
                        out.println("<td align=\"left\">"+rs.getString(12)+"</td>"); 
                        out.println("<td align=\"left\">"+rs.getString(14)+"</td>"); 
                        out.println("<td align=\"left\">"+rs.getString(13)+"</td>"); 
                    }
                    out.println("<td align=\"left\">"+rs.getString(3)+"</td>"); 
                    out.println("<td align=\"left\">"+rs.getString(7)+"</td>"); 
                    out.println("<td align=\"left\">"+rs.getString(5)+"</td>"); 
                    if(m_head.equals("Cancel")){
                        out.println("<td align=\"right\"><input type='text' class=\"txt_input2\" style=\"{width:100px}\" type=\"text\" maxlength='10' size='25' name='TXT_CHEQUE_NO_"+j+"' value=\""+rs.getString(9)+"\" disabled></td>"); //Added By sandun on 19-09-2008
                    }
                    if(m_head.equals("Printing")){
                        out.println("<td align=\"right\"><input type='text' class=\"txt_input2\" style=\"{width:100px}\" type=\"text\" maxlength='10' size='25' name='TXT_CHEQUE_NO_"+j+"' ></td>"); //Added By sandun on 19-09-2008
                    }
                    out.println("<td align=\"right\">"+nf.format(rs.getDouble(10))+"</td>"); //Added By Sandun on 24-12-2008
                    out.println("<td align=\"right\">"+nf.format(rs.getDouble(11))+"</td>"); //Added By Sandun on 24-12-2008
                    out.println("<td align=\"right\">"+nf.format(rs.getDouble(4))+"</td>"); 
                    out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\" ></td>");
                    out.println("</tr>");		
                    j=j+1;
                }
                out.println("<input type=hidden name=hid_count value="+j+">");
                out.println("</table>");			
            }			
            
        }
        catch (Exception ex) {
            try{out.println("Error:"+ex.toString());}catch(Exception e){}
        }
        finally{
            if(rs    !=null){try{rs.close();   }catch(Exception e){}}
            if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
            if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
            if(stmt2  !=null){try{stmt2.close(); }catch(Exception e){}}
            
            if(conn  !=null){try{conn.close(); }catch(Exception e){}}
            
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
    }
}
